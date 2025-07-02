package com.fallengod.testament.altars;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AltarManager {
    private final Plugin plugin;
    private final Map<String, AltarType> altarTypes;
    private final Map<UUID, Long> playerCooldowns;

    public AltarManager(Plugin plugin, Map<String, AltarType> altarTypes) {
        this.plugin = plugin;
        this.altarTypes = altarTypes;
        this.playerCooldowns = new HashMap<>();
    }

    public boolean isValidAltar(Location location, AltarType altarType) {
        // Check if the location matches the altar pattern
        if (location.getBlock().getType() != altarType.getCenterBlock()) {
            return false;
        }
        
        // Additional pattern validation logic would go here
        return true;
    }

    public boolean canUseAltar(Player player, AltarType altarType) {
        UUID playerId = player.getUniqueId();
        long currentTime = System.currentTimeMillis();
        
        if (playerCooldowns.containsKey(playerId)) {
            long lastUse = playerCooldowns.get(playerId);
            long cooldownMs = altarType.getCooldownSeconds() * 1000L;
            
            if (currentTime - lastUse < cooldownMs) {
                return false;
            }
        }
        
        return true;
    }

    public void setPlayerCooldown(Player player) {
        playerCooldowns.put(player.getUniqueId(), System.currentTimeMillis());
    }

    public AltarType getAltarType(String id) {
        return altarTypes.get(id);
    }

    public Map<String, AltarType> getAllAltarTypes() {
        return new HashMap<>(altarTypes);
    }
}