package com.fallengod.testament.world;

import com.fallengod.testament.altars.AltarType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import java.util.Map;
import java.util.Random;

public class AltarPlacementManager {
    private final Map<String, AltarType> altarTypes;
    private final Plugin plugin;
    private final int minDistance;
    private final int maxDistance;
    private final Random random;

    public AltarPlacementManager(Map<String, AltarType> altarTypes, Plugin plugin, int minDistance, int maxDistance) {
        this.altarTypes = altarTypes;
        this.plugin = plugin;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.random = new Random();
    }

    public Location findNearestAltar(World world, AltarType altarType, Location center) {
        // Search for existing altars in a radius
        int searchRadius = 1000;
        
        for (int x = -searchRadius; x <= searchRadius; x += 16) {
            for (int z = -searchRadius; z <= searchRadius; z += 16) {
                Location checkLoc = new Location(world, center.getX() + x, 64, center.getZ() + z);
                
                // Find the highest solid block
                for (int y = world.getMaxHeight() - 1; y >= world.getMinHeight(); y--) {
                    checkLoc.setY(y);
                    if (checkLoc.getBlock().getType().isSolid()) {
                        checkLoc.setY(y + 1);
                        break;
                    }
                }
                
                // Check if this location has an altar
                if (isAltarAt(checkLoc, altarType)) {
                    return checkLoc;
                }
            }
        }
        
        return null;
    }

    public Location generateAltar(World world, AltarType altarType, Location center) {
        // Find a suitable location
        Location altarLocation = findSuitableLocation(world, center);
        if (altarLocation == null) {
            return null;
        }

        // Generate the altar structure
        buildAltar(altarLocation, altarType);
        
        return altarLocation;
    }

    private Location findSuitableLocation(World world, Location center) {
        int attempts = 100;
        
        for (int i = 0; i < attempts; i++) {
            int x = center.getBlockX() + random.nextInt(maxDistance * 2) - maxDistance;
            int z = center.getBlockZ() + random.nextInt(maxDistance * 2) - maxDistance;
            
            // Find surface level
            Location testLoc = new Location(world, x, world.getMaxHeight() - 1, z);
            for (int y = world.getMaxHeight() - 1; y >= world.getMinHeight(); y--) {
                testLoc.setY(y);
                if (testLoc.getBlock().getType().isSolid()) {
                    testLoc.setY(y + 1);
                    break;
                }
            }
            
            // Check if location is suitable (flat area, not in water, etc.)
            if (isSuitableForAltar(testLoc)) {
                return testLoc;
            }
        }
        
        return null;
    }

    private boolean isSuitableForAltar(Location location) {
        // Check if the area is relatively flat and suitable for building
        World world = location.getWorld();
        int baseY = location.getBlockY();
        
        // Check 5x5 area around the location
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                Location checkLoc = location.clone().add(x, 0, z);
                
                // Check if ground level is relatively consistent
                int groundY = getGroundLevel(checkLoc);
                if (Math.abs(groundY - baseY) > 2) {
                    return false;
                }
                
                // Check if not in water or lava
                if (checkLoc.getBlock().getType() == Material.WATER || 
                    checkLoc.getBlock().getType() == Material.LAVA) {
                    return false;
                }
            }
        }
        
        return true;
    }

    private int getGroundLevel(Location location) {
        World world = location.getWorld();
        for (int y = location.getBlockY(); y >= world.getMinHeight(); y--) {
            Location checkLoc = new Location(world, location.getX(), y, location.getZ());
            if (checkLoc.getBlock().getType().isSolid()) {
                return y + 1;
            }
        }
        return world.getMinHeight();
    }

    private void buildAltar(Location center, AltarType altarType) {
        World world = center.getWorld();
        
        // Clear the area first
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                for (int y = 0; y <= 3; y++) {
                    Location clearLoc = center.clone().add(x, y, z);
                    clearLoc.getBlock().setType(Material.AIR);
                }
            }
        }
        
        // Build the altar pattern
        // Center block
        center.getBlock().setType(altarType.getCenterBlock());
        
        // Ring blocks (4 blocks around center)
        center.clone().add(1, 0, 0).getBlock().setType(altarType.getRingBlock());
        center.clone().add(-1, 0, 0).getBlock().setType(altarType.getRingBlock());
        center.clone().add(0, 0, 1).getBlock().setType(altarType.getRingBlock());
        center.clone().add(0, 0, -1).getBlock().setType(altarType.getRingBlock());
        
        // Corner blocks
        center.clone().add(1, 0, 1).getBlock().setType(altarType.getCornerBlock());
        center.clone().add(-1, 0, 1).getBlock().setType(altarType.getCornerBlock());
        center.clone().add(1, 0, -1).getBlock().setType(altarType.getCornerBlock());
        center.clone().add(-1, 0, -1).getBlock().setType(altarType.getCornerBlock());
    }

    private boolean isAltarAt(Location location, AltarType altarType) {
        // Check if the center block matches
        if (location.getBlock().getType() != altarType.getCenterBlock()) {
            return false;
        }
        
        // Check ring blocks
        if (location.clone().add(1, 0, 0).getBlock().getType() != altarType.getRingBlock() ||
            location.clone().add(-1, 0, 0).getBlock().getType() != altarType.getRingBlock() ||
            location.clone().add(0, 0, 1).getBlock().getType() != altarType.getRingBlock() ||
            location.clone().add(0, 0, -1).getBlock().getType() != altarType.getRingBlock()) {
            return false;
        }
        
        // Check corner blocks
        if (location.clone().add(1, 0, 1).getBlock().getType() != altarType.getCornerBlock() ||
            location.clone().add(-1, 0, 1).getBlock().getType() != altarType.getCornerBlock() ||
            location.clone().add(1, 0, -1).getBlock().getType() != altarType.getCornerBlock() ||
            location.clone().add(-1, 0, -1).getBlock().getType() != altarType.getCornerBlock()) {
            return false;
        }
        
        return true;
    }
}