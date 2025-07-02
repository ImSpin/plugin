package com.fallengod.testament.listeners;

import com.fallengod.testament.FallenGodPlugin;
import com.fallengod.testament.advancements.AdvancementManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEventListener implements Listener {
    private final FallenGodPlugin plugin;
    private final AdvancementManager advancementManager;

    public PlayerEventListener(FallenGodPlugin plugin) {
        this.plugin = plugin;
        this.advancementManager = plugin.getAdvancementManager();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        // Grant the root advancement when player joins
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            advancementManager.grantAdvancement(player, "root");
        }, 20L); // 1 second delay to ensure player is fully loaded
    }
}