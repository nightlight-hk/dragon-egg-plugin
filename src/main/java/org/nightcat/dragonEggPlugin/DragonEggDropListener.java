package org.nightcat.dragonEggPlugin;

import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public final class DragonEggDropListener implements Listener {

    private static final double EGG_CHANCE = 0.10D;
    private final DragonEggPlugin plugin;

    public DragonEggDropListener(DragonEggPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDragonDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof EnderDragon dragon)) {
            return;
        }

        World world = dragon.getWorld();
        if (world.getEnvironment() != World.Environment.THE_END) {
            return;
        }

        Location portalLocation = null;
        if (world.getEnderDragonBattle() != null) {
            portalLocation = world.getEnderDragonBattle().getEndPortalLocation();
        }

        if (portalLocation == null) {
            plugin.getLogger().warning("Dragon died but End portal location is unavailable; skipping egg roll.");
            return;
        }

        final Location finalPortalLocation = portalLocation.clone();
        int chunkX = finalPortalLocation.getBlockX() >> 4;
        int chunkZ = finalPortalLocation.getBlockZ() >> 4;

        // Run one tick later to enforce final state after vanilla dragon-death logic.
        Bukkit.getRegionScheduler().runDelayed(plugin, world, chunkX, chunkZ,
                task -> applyEggRoll(world, finalPortalLocation), 1L);
    }

    private void applyEggRoll(World world, Location portalLocation) {
        Location eggLocation = portalLocation.clone().add(0.0D, 4.0D, 0.0D);
        Block eggBlock = eggLocation.getBlock();

        if (eggBlock.getType() == Material.DRAGON_EGG) {
            eggBlock.setType(Material.AIR, false);
        }

        double roll = ThreadLocalRandom.current().nextDouble();
        boolean shouldSpawnEgg = roll < EGG_CHANCE;

        if (shouldSpawnEgg) {
            eggBlock.setType(Material.DRAGON_EGG, false);
        }

        plugin.getLogger().info(String.format(
                "Dragon egg roll at %s (%d, %d, %d): roll=%.4f chance=%.2f%% result=%s",
                world.getName(),
                eggLocation.getBlockX(),
                eggLocation.getBlockY(),
                eggLocation.getBlockZ(),
                roll,
                EGG_CHANCE * 100.0D,
                shouldSpawnEgg ? "spawned" : "not_spawned"
        ));
    }
}

