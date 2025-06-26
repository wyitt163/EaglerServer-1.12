package com.example.chatfilter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class AuthCommands implements CommandExecutor {

    private final Map<String, String> registeredUsers = new HashMap<>();
    private final Map<String, Boolean> loggedInUsers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command.");
            return true;
        }

        Player player = (Player) sender;
        String playerName = player.getName();

        if (command.getName().equalsIgnoreCase("register")) {
            if (registeredUsers.containsKey(playerName)) {
                player.sendMessage("You are already registered.");
                return true;
            }

            if (args.length != 1) {
                player.sendMessage("Usage: /register <password>");
                return true;
            }

            registeredUsers.put(playerName, args[0]);
            loggedInUsers.put(playerName, true);
            player.sendMessage("Registered and logged in successfully!");
            return true;
        }

        if (command.getName().equalsIgnoreCase("login")) {
            if (!registeredUsers.containsKey(playerName)) {
                player.sendMessage("You are not registered. Use /register <password>");
                return true;
            }

            if (args.length != 1) {
                player.sendMessage("Usage: /login <password>");
                return true;
            }

            String correctPassword = registeredUsers.get(playerName);
            if (correctPassword.equals(args[0])) {
                loggedInUsers.put(playerName, true);
                player.sendMessage("Logged in successfully!");
            } else {
                player.sendMessage("Incorrect password!");
            }

            return true;
        }

        return false;
    }
}