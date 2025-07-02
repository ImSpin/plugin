package com.fallengod.testament.altars;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;

public class AltarType {
    private final String id;
    private final String displayName;
    private final Material centerBlock;
    private final Material ringBlock;
    private final Material cornerBlock;
    private final int cooldownSeconds;
    private final String rewardItem;
    private final String requiredItem;
    private final int requiredAmount;
    private final Particle effectParticle;
    private final Sound effectSound;

    public AltarType(String id, String displayName, Material centerBlock, Material ringBlock, 
                     Material cornerBlock, int cooldownSeconds, String rewardItem, 
                     String requiredItem, int requiredAmount, Particle effectParticle, Sound effectSound) {
        this.id = id;
        this.displayName = displayName;
        this.centerBlock = centerBlock;
        this.ringBlock = ringBlock;
        this.cornerBlock = cornerBlock;
        this.cooldownSeconds = cooldownSeconds;
        this.rewardItem = rewardItem;
        this.requiredItem = requiredItem;
        this.requiredAmount = requiredAmount;
        this.effectParticle = effectParticle;
        this.effectSound = effectSound;
    }

    public static AltarType fromConfig(String id, ConfigurationSection config) {
        String displayName = config.getString("display_name", id);
        
        ConfigurationSection pattern = config.getConfigurationSection("pattern");
        Material centerBlock = Material.valueOf(pattern.getString("center", "ENCHANTING_TABLE"));
        Material ringBlock = Material.valueOf(pattern.getString("ring", "GOLD_BLOCK"));
        Material cornerBlock = Material.valueOf(pattern.getString("corners", "OBSIDIAN"));
        
        int cooldownSeconds = config.getInt("cooldown_seconds", 3600);
        String rewardItem = config.getString("reward", "fallengod:testament_complete");
        
        ConfigurationSection requirements = config.getConfigurationSection("requirements");
        String requiredItem = requirements.getString("item", "fallengod:testament_fragment");
        int requiredAmount = requirements.getInt("amount", 7);
        
        ConfigurationSection effects = config.getConfigurationSection("effects");
        Particle effectParticle = Particle.valueOf(effects.getString("particles", "SOUL_FIRE_FLAME"));
        Sound effectSound = Sound.valueOf(effects.getString("sound", "BLOCK_BEACON_ACTIVATE"));
        
        return new AltarType(id, displayName, centerBlock, ringBlock, cornerBlock, 
                           cooldownSeconds, rewardItem, requiredItem, requiredAmount, 
                           effectParticle, effectSound);
    }

    // Getters
    public String getId() { return id; }
    public String getDisplayName() { return displayName; }
    public Material getCenterBlock() { return centerBlock; }
    public Material getRingBlock() { return ringBlock; }
    public Material getCornerBlock() { return cornerBlock; }
    public int getCooldownSeconds() { return cooldownSeconds; }
    public String getRewardItem() { return rewardItem; }
    public String getRequiredItem() { return requiredItem; }
    public int getRequiredAmount() { return requiredAmount; }
    public Particle getEffectParticle() { return effectParticle; }
    public Sound getEffectSound() { return effectSound; }
}