package com.fallengod.testament.commands;

import com.fallengod.testament.altars.AltarType;
import com.fallengod.testament.world.AltarPlacementManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class LocateAltarCommand implements CommandExecutor {
    private final Map<String, AltarType> altarTypes;
    private final Plugin plugin;

    public LocateAltarCommand(Map<String, AltarType> altarTypes, Plugin plugin) {
        this.altarTypes = altarTypes;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Component.text("Only players can use this command.").color(NamedTextColor.RED));
            return true;
        }
        
        Player player = (Player) sender;
        if (!player.hasPermission("fallengod.admin.locate")) {
            player.sendMessage(Component.text("You do not have permission to use this command.").color(NamedTextColor.RED));
            return true;
        }
        
        if (args.length < 1) {
            player.sendMessage(Component.text("Usage: /locatealtar <type>").color(NamedTextColor.YELLOW));
            return true;
        }
        
        String type = args[0].toLowerCase();
        AltarType altarType = altarTypes.get(type);
        if (altarType == null) {
            player.sendMessage(Component.text("Unknown altar type: " + type).color(NamedTextColor.RED));
            return true;
        }
        
        AltarPlacementManager manager = new AltarPlacementManager(altarTypes, plugin, 0, 0);
        Location altarLocation = manager.findNearestAltar(player.getWorld(), altarType, player.getLocation());
        
        if (altarLocation == null) {
            player.sendMessage(Component.text("No altar of type '" + type + "' found in this world.")
                .color(NamedTextColor.YELLOW));
        } else {
            player.sendMessage(Component.text("Nearest altar of type '" + type + "' is at: " + 
                altarLocation.getBlockX() + ", " + altarLocation.getBlockY() + ", " + altarLocation.getBlockZ())
                .color(NamedTextColor.GREEN));
        }
        
        return true;
    }
}