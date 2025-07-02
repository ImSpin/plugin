package com.fallengod.testament.listeners;

import com.fallengod.testament.FallenGodPlugin;
import com.fallengod.testament.altars.AltarManager;
import com.fallengod.testament.altars.AltarType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;

public class AltarInteractionListener implements Listener {
    private final FallenGodPlugin plugin;
    private final AltarManager altarManager;

    public AltarInteractionListener(FallenGodPlugin plugin) {
        this.plugin = plugin;
        this.altarManager = plugin.getAltarManager();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Player player = event.getPlayer();
        Location clickedLocation = event.getClickedBlock().getLocation();

        // Check if the clicked location is an altar
        for (AltarType altarType : altarManager.getAllAltarTypes().values()) {
            if (altarManager.isValidAltar(clickedLocation, altarType)) {
                handleAltarInteraction(player, altarType, clickedLocation);
                event.setCancelled(true);
                break;
            }
        }
    }

    private void handleAltarInteraction(Player player, AltarType altarType, Location altarLocation) {
        if (!altarManager.canUseAltar(player, altarType)) {
            player.sendMessage(Component.text("You must wait before using this altar again.")
                .color(NamedTextColor.RED));
            return;
        }

        // Check if player has required fragments
        // This would be implemented with inventory checking logic
        player.sendMessage(Component.text("You have interacted with the " + altarType.getDisplayName() + " altar!")
            .color(NamedTextColor.GOLD));
        
        // Play effects
        player.getWorld().spawnParticle(altarType.getEffectParticle(), altarLocation.add(0, 1, 0), 20);
        player.getWorld().playSound(altarLocation, altarType.getEffectSound(), 1.0f, 1.0f);
    }
}