package com.example.myplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("MyPlugin enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("MyPlugin disabled.");
    }
}
