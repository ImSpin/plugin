package com.fallengod.testament.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fallengod.testament.FallenGodPlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class TestamentCommand implements CommandExecutor {
    private final FallenGodPlugin plugin;

    public TestamentCommand(FallenGodPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Component.text("Only players can use this command.").color(NamedTextColor.RED));
            return true;
        }

        Player player = (Player) sender;
        
        if (args.length == 0) {
            showHelp(player);
            return true;
        }

        String subCommand = args[0].toLowerCase();
        
        switch (subCommand) {
            case "help":
                showHelp(player);
                break;
            case "status":
                showStatus(player);
                break;
            case "reunite":
                attemptReunite(player);
                break;
            default:
                player.sendMessage(Component.text("Unknown subcommand: " + subCommand).color(NamedTextColor.RED));
                showHelp(player);
                break;
        }
        
        return true;
    }

    private void showHelp(Player player) {
        player.sendMessage(Component.text("=== Testament Commands ===").color(NamedTextColor.GOLD));
        player.sendMessage(Component.text("/testament help - Show this help").color(NamedTextColor.YELLOW));
        player.sendMessage(Component.text("/testament status - View your fragment progress").color(NamedTextColor.YELLOW));
        player.sendMessage(Component.text("/testament reunite - Attempt testament reunification").color(NamedTextColor.YELLOW));
    }

    private void showStatus(Player player) {
        // This would show the player's current fragment collection status
        player.sendMessage(Component.text("Fragment collection status:").color(NamedTextColor.GOLD));
        player.sendMessage(Component.text("This feature is not yet implemented.").color(NamedTextColor.GRAY));
    }

    private void attemptReunite(Player player) {
        // This would attempt to reunite fragments at an altar
        player.sendMessage(Component.text("Testament reunification:").color(NamedTextColor.GOLD));
        player.sendMessage(Component.text("This feature is not yet implemented.").color(NamedTextColor.GRAY));
    }
}