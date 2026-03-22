package org.nightcat.dragonEggPlugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;

public final class DragonEggPortalListener implements Listener {

    private final DragonEggPlugin plugin;

    public DragonEggPortalListener(DragonEggPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityPortalEnter(EntityPortalEnterEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof FallingBlock fallingBlock)) {
            return;
        }

        if (fallingBlock.getBlockData().getMaterial() != Material.DRAGON_EGG) {
            return;
        }

        Block portalBlock = event.getLocation().getBlock();
        if (portalBlock.getType() != Material.END_PORTAL) {
            return;
        }

        Location location = entity.getLocation();
        entity.remove();

        plugin.getLogger().info(String.format(
                "Removed dragon egg falling block entering End portal at %s (%d, %d, %d), uuid=%s",
                location.getWorld() != null ? location.getWorld().getName() : "unknown",
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ(),
                entity.getUniqueId()
        ));
    }
}

