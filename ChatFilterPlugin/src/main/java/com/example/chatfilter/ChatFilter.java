package com.example.chatfilter;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Arrays;
import java.util.List;

public class ChatFilter extends JavaPlugin implements Listener {

    private final List<String> bannedWords = Arrays.asList("fuck", "shit", "bitch");

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("register").setExecutor(new AuthCommands());
        this.getCommand("login").setExecutor(new AuthCommands());
        getLogger().info("ChatFilter plugin enabled!");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        for (String word : bannedWords) {
            message = message.replaceAll("(?i)\\b" + word + "\\b", "****");
        }
        event.setMessage(message);
    }
}