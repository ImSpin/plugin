package com.fallengod.testament.advancements;

import com.fallengod.testament.FallenGodPlugin;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class AdvancementManager {
    private final Plugin plugin;
    private final Map<String, NamespacedKey> advancementKeys;
    private final Map<UUID, Map<String, Boolean>> playerProgress;

    public AdvancementManager(Plugin plugin) {
        this.plugin = plugin;
        this.advancementKeys = new HashMap<>();
        this.playerProgress = new HashMap<>();
        initializeAdvancementKeys();
    }

    private void initializeAdvancementKeys() {
        // Root advancement
        advancementKeys.put("root", new NamespacedKey(plugin, "testament_of_gods"));
        
        // Fallen God advancements
        for (int i = 1; i <= 7; i++) {
            advancementKeys.put("fallen_fragment_" + i, new NamespacedKey(plugin, "fallen_fragment_" + i));
        }
        advancementKeys.put("fallen_complete", new NamespacedKey(plugin, "fallen_complete"));
        
        // Banishment God advancements
        for (int i = 1; i <= 7; i++) {
            advancementKeys.put("banishment_fragment_" + i, new NamespacedKey(plugin, "banishment_fragment_" + i));
        }
        advancementKeys.put("banishment_complete", new NamespacedKey(plugin, "banishment_complete"));
        
        // Abyssal God advancements
        for (int i = 1; i <= 7; i++) {
            advancementKeys.put("abyssal_fragment_" + i, new NamespacedKey(plugin, "abyssal_fragment_" + i));
        }
        advancementKeys.put("abyssal_complete", new NamespacedKey(plugin, "abyssal_complete"));
        
        // Sylvan God advancements
        for (int i = 1; i <= 7; i++) {
            advancementKeys.put("sylvan_fragment_" + i, new NamespacedKey(plugin, "sylvan_fragment_" + i));
        }
        advancementKeys.put("sylvan_complete", new NamespacedKey(plugin, "sylvan_complete"));
        
        // Tempest God advancements
        for (int i = 1; i <= 7; i++) {
            advancementKeys.put("tempest_fragment_" + i, new NamespacedKey(plugin, "tempest_fragment_" + i));
        }
        advancementKeys.put("tempest_complete", new NamespacedKey(plugin, "tempest_complete"));
        
        // Veil God advancements
        for (int i = 1; i <= 7; i++) {
            advancementKeys.put("veil_fragment_" + i, new NamespacedKey(plugin, "veil_fragment_" + i));
        }
        advancementKeys.put("veil_complete", new NamespacedKey(plugin, "veil_complete"));
        
        // Master advancement
        advancementKeys.put("master_of_testaments", new NamespacedKey(plugin, "master_of_testaments"));
    }

    public void grantAdvancement(Player player, String advancementId) {
        NamespacedKey key = advancementKeys.get(advancementId);
        if (key == null) {
            plugin.getLogger().warning("Unknown advancement ID: " + advancementId);
            return;
        }

        Advancement advancement = Bukkit.getAdvancement(key);
        if (advancement == null) {
            plugin.getLogger().warning("Advancement not found: " + key);
            return;
        }

        AdvancementProgress progress = player.getAdvancementProgress(advancement);
        if (!progress.isDone()) {
            for (String criteria : progress.getRemainingCriteria()) {
                progress.awardCriteria(criteria);
            }
            
            // Track progress internally
            playerProgress.computeIfAbsent(player.getUniqueId(), k -> new HashMap<>())
                    .put(advancementId, true);
            
            plugin.getLogger().info("Granted advancement " + advancementId + " to " + player.getName());
        }
    }

    public void grantFragmentAdvancement(Player player, String godType, int fragmentNumber) {
        String advancementId = godType.toLowerCase() + "_fragment_" + fragmentNumber;
        grantAdvancement(player, advancementId);
        
        // Check if this completes all fragments for this god
        if (hasAllFragments(player, godType)) {
            // Small delay to ensure fragment advancement is processed first
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                checkAndGrantTestamentCompletion(player, godType);
            }, 5L);
        }
    }

    public void grantTestamentCompletion(Player player, String godType) {
        String advancementId = godType.toLowerCase() + "_complete";
        grantAdvancement(player, advancementId);
        
        // Check if this completes all testaments
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            checkAndGrantMasterAdvancement(player);
        }, 5L);
    }

    private boolean hasAllFragments(Player player, String godType) {
        for (int i = 1; i <= 7; i++) {
            String fragmentId = godType.toLowerCase() + "_fragment_" + i;
            NamespacedKey key = advancementKeys.get(fragmentId);
            if (key != null) {
                Advancement advancement = Bukkit.getAdvancement(key);
                if (advancement != null) {
                    AdvancementProgress progress = player.getAdvancementProgress(advancement);
                    if (!progress.isDone()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void checkAndGrantTestamentCompletion(Player player, String godType) {
        if (hasAllFragments(player, godType)) {
            grantTestamentCompletion(player, godType);
        }
    }

    private void checkAndGrantMasterAdvancement(Player player) {
        String[] godTypes = {"fallen", "banishment", "abyssal", "sylvan", "tempest", "veil"};
        
        for (String godType : godTypes) {
            String completionId = godType + "_complete";
            NamespacedKey key = advancementKeys.get(completionId);
            if (key != null) {
                Advancement advancement = Bukkit.getAdvancement(key);
                if (advancement != null) {
                    AdvancementProgress progress = player.getAdvancementProgress(advancement);
                    if (!progress.isDone()) {
                        return; // Not all testaments completed
                    }
                }
            }
        }
        
        // All testaments completed, grant master advancement
        grantAdvancement(player, "master_of_testaments");
    }

    public boolean hasAdvancement(Player player, String advancementId) {
        NamespacedKey key = advancementKeys.get(advancementId);
        if (key == null) return false;
        
        Advancement advancement = Bukkit.getAdvancement(key);
        if (advancement == null) return false;
        
        return player.getAdvancementProgress(advancement).isDone();
    }

    public void revokeAdvancement(Player player, String advancementId) {
        NamespacedKey key = advancementKeys.get(advancementId);
        if (key == null) return;
        
        Advancement advancement = Bukkit.getAdvancement(key);
        if (advancement == null) return;
        
        AdvancementProgress progress = player.getAdvancementProgress(advancement);
        for (String criteria : advancement.getCriteria()) {
            progress.revokeCriteria(criteria);
        }
        
        // Remove from internal tracking
        Map<String, Boolean> playerAdvancements = playerProgress.get(player.getUniqueId());
        if (playerAdvancements != null) {
            playerAdvancements.remove(advancementId);
        }
    }

    public NamespacedKey getAdvancementKey(String advancementId) {
        return advancementKeys.get(advancementId);
    }
}