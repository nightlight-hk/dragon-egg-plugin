package org.nightcat.dragonEggPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class DragonEggPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new DragonEggPortalListener(this), this);
        getServer().getPluginManager().registerEvents(new DragonEggDropListener(this), this);

        // Add server log
        if (getDescription() != null) {
            getLogger().info(getDescription().getName() + " v" + getDescription().getVersion() + " enabled");
        } else {
            getLogger().info("DragonEggPlugin enabled");
        }
    }

    @Override
    public void onDisable() {
        // Add server log
        if (getDescription() != null) {
            getLogger().info(getDescription().getName() + " v" + getDescription().getVersion() + " disabled");
        } else {
            getLogger().info("DragonEggPlugin disabled");
        }
    }
}
