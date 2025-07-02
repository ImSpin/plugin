package com.fallengod.testament;

import com.fallengod.testament.advancements.AdvancementManager;
import com.fallengod.testament.altars.AltarManager;
import com.fallengod.testament.altars.AltarType;
import com.fallengod.testament.commands.GenerateAltarCommand;
import com.fallengod.testament.commands.LocateAltarCommand;
import com.fallengod.testament.commands.TestamentCommand;
import com.fallengod.testament.items.FragmentManager;
import com.fallengod.testament.listeners.AltarInteractionListener;
import com.fallengod.testament.listeners.PlayerEventListener;
import com.fallengod.testament.world.AltarPlacementManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class FallenGodPlugin extends JavaPlugin {
    private AdvancementManager advancementManager;
    private AltarManager altarManager;
    private FragmentManager fragmentManager;
    private Map<String, AltarType> altarTypes;
    private AltarPlacementManager placementManager;

    @Override
    public void onEnable() {
        // Save default config files
        saveDefaultConfig();
        saveResource("altars.yml", false);
        
        // Initialize managers
        this.advancementManager = new AdvancementManager(this);
        this.fragmentManager = new FragmentManager(this);
        this.altarTypes = loadAltarTypes();
        this.altarManager = new AltarManager(this, altarTypes);
        this.placementManager = new AltarPlacementManager(altarTypes, this, 1000, 10000);
        
        // Register commands
        registerCommands();
        
        // Register event listeners
        registerListeners();
        
        getLogger().info("FallenGod Testament Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("FallenGod Testament Plugin has been disabled!");
    }

    private void registerCommands() {
        // Register testament command
        getCommand("testament").setExecutor(new TestamentCommand(this));
        
        // Register altar commands
        getCommand("locatealtar").setExecutor(new LocateAltarCommand(altarTypes, this));
        getCommand("generatealtars").setExecutor(new GenerateAltarCommand(altarTypes, this, 1000, 10000));
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerEventListener(this), this);
        getServer().getPluginManager().registerEvents(new AltarInteractionListener(this), this);
    }

    private Map<String, AltarType> loadAltarTypes() {
        Map<String, AltarType> types = new HashMap<>();
        
        // Load from altars.yml
        File altarsFile = new File(getDataFolder(), "altars.yml");
        if (!altarsFile.exists()) {
            saveResource("altars.yml", false);
        }
        
        FileConfiguration altarsConfig = YamlConfiguration.loadConfiguration(altarsFile);
        ConfigurationSection altarsSection = altarsConfig.getConfigurationSection("altars");
        
        if (altarsSection != null) {
            for (String key : altarsSection.getKeys(false)) {
                ConfigurationSection altarSection = altarsSection.getConfigurationSection(key);
                if (altarSection != null) {
                    try {
                        AltarType altarType = AltarType.fromConfig(key, altarSection);
                        types.put(key, altarType);
                        getLogger().info("Loaded altar type: " + key);
                    } catch (Exception e) {
                        getLogger().log(Level.WARNING, "Failed to load altar type: " + key, e);
                    }
                }
            }
        }
        
        return types;
    }

    public AdvancementManager getAdvancementManager() {
        return advancementManager;
    }

    public AltarManager getAltarManager() {
        return altarManager;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public Map<String, AltarType> getAltarTypes() {
        return altarTypes;
    }

    public AltarPlacementManager getPlacementManager() {
        return placementManager;
    }
}