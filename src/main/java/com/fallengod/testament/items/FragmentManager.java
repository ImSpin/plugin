package com.fallengod.testament.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FragmentManager {
    private final Plugin plugin;
    private final Map<String, FragmentType> fragmentTypes;

    public FragmentManager(Plugin plugin) {
        this.plugin = plugin;
        this.fragmentTypes = new HashMap<>();
        initializeFragmentTypes();
    }

    private void initializeFragmentTypes() {
        // Fallen God Fragments
        fragmentTypes.put("fallen_fragment_1", new FragmentType("fallen", 1, "Fragment of Creation", Material.ECHO_SHARD));
        fragmentTypes.put("fallen_fragment_2", new FragmentType("fallen", 2, "Fragment of Power", Material.ECHO_SHARD));
        fragmentTypes.put("fallen_fragment_3", new FragmentType("fallen", 3, "Fragment of Wisdom", Material.ECHO_SHARD));
        fragmentTypes.put("fallen_fragment_4", new FragmentType("fallen", 4, "Fragment of Destruction", Material.ECHO_SHARD));
        fragmentTypes.put("fallen_fragment_5", new FragmentType("fallen", 5, "Fragment of Redemption", Material.ECHO_SHARD));
        fragmentTypes.put("fallen_fragment_6", new FragmentType("fallen", 6, "Fragment of Sacrifice", Material.ECHO_SHARD));
        fragmentTypes.put("fallen_fragment_7", new FragmentType("fallen", 7, "Fragment of Eternity", Material.ECHO_SHARD));

        // Banishment God Fragments
        fragmentTypes.put("banishment_fragment_1", new FragmentType("banishment", 1, "Banishment Fragment I", Material.NETHER_STAR));
        fragmentTypes.put("banishment_fragment_2", new FragmentType("banishment", 2, "Banishment Fragment II", Material.NETHER_STAR));
        fragmentTypes.put("banishment_fragment_3", new FragmentType("banishment", 3, "Banishment Fragment III", Material.NETHER_STAR));
        fragmentTypes.put("banishment_fragment_4", new FragmentType("banishment", 4, "Banishment Fragment IV", Material.NETHER_STAR));
        fragmentTypes.put("banishment_fragment_5", new FragmentType("banishment", 5, "Banishment Fragment V", Material.NETHER_STAR));
        fragmentTypes.put("banishment_fragment_6", new FragmentType("banishment", 6, "Banishment Fragment VI", Material.NETHER_STAR));
        fragmentTypes.put("banishment_fragment_7", new FragmentType("banishment", 7, "Banishment Fragment VII", Material.NETHER_STAR));

        // Abyssal God Fragments
        fragmentTypes.put("abyssal_fragment_1", new FragmentType("abyssal", 1, "Abyssal Fragment I", Material.PRISMARINE_SHARD));
        fragmentTypes.put("abyssal_fragment_2", new FragmentType("abyssal", 2, "Abyssal Fragment II", Material.PRISMARINE_SHARD));
        fragmentTypes.put("abyssal_fragment_3", new FragmentType("abyssal", 3, "Abyssal Fragment III", Material.PRISMARINE_SHARD));
        fragmentTypes.put("abyssal_fragment_4", new FragmentType("abyssal", 4, "Abyssal Fragment IV", Material.PRISMARINE_SHARD));
        fragmentTypes.put("abyssal_fragment_5", new FragmentType("abyssal", 5, "Abyssal Fragment V", Material.PRISMARINE_SHARD));
        fragmentTypes.put("abyssal_fragment_6", new FragmentType("abyssal", 6, "Abyssal Fragment VI", Material.PRISMARINE_SHARD));
        fragmentTypes.put("abyssal_fragment_7", new FragmentType("abyssal", 7, "Abyssal Fragment VII", Material.PRISMARINE_SHARD));

        // Sylvan God Fragments
        fragmentTypes.put("sylvan_fragment_1", new FragmentType("sylvan", 1, "Sylvan Fragment I", Material.EMERALD));
        fragmentTypes.put("sylvan_fragment_2", new FragmentType("sylvan", 2, "Sylvan Fragment II", Material.EMERALD));
        fragmentTypes.put("sylvan_fragment_3", new FragmentType("sylvan", 3, "Sylvan Fragment III", Material.EMERALD));
        fragmentTypes.put("sylvan_fragment_4", new FragmentType("sylvan", 4, "Sylvan Fragment IV", Material.EMERALD));
        fragmentTypes.put("sylvan_fragment_5", new FragmentType("sylvan", 5, "Sylvan Fragment V", Material.EMERALD));
        fragmentTypes.put("sylvan_fragment_6", new FragmentType("sylvan", 6, "Sylvan Fragment VI", Material.EMERALD));
        fragmentTypes.put("sylvan_fragment_7", new FragmentType("sylvan", 7, "Sylvan Fragment VII", Material.EMERALD));

        // Tempest God Fragments
        fragmentTypes.put("tempest_fragment_1", new FragmentType("tempest", 1, "Tempest Fragment I", Material.LIGHTNING_ROD));
        fragmentTypes.put("tempest_fragment_2", new FragmentType("tempest", 2, "Tempest Fragment II", Material.LIGHTNING_ROD));
        fragmentTypes.put("tempest_fragment_3", new FragmentType("tempest", 3, "Tempest Fragment III", Material.LIGHTNING_ROD));
        fragmentTypes.put("tempest_fragment_4", new FragmentType("tempest", 4, "Tempest Fragment IV", Material.LIGHTNING_ROD));
        fragmentTypes.put("tempest_fragment_5", new FragmentType("tempest", 5, "Tempest Fragment V", Material.LIGHTNING_ROD));
        fragmentTypes.put("tempest_fragment_6", new FragmentType("tempest", 6, "Tempest Fragment VI", Material.LIGHTNING_ROD));
        fragmentTypes.put("tempest_fragment_7", new FragmentType("tempest", 7, "Tempest Fragment VII", Material.LIGHTNING_ROD));

        // Veil God Fragments
        fragmentTypes.put("veil_fragment_1", new FragmentType("veil", 1, "Veil Fragment I", Material.SOUL_SOIL));
        fragmentTypes.put("veil_fragment_2", new FragmentType("veil", 2, "Veil Fragment II", Material.SOUL_SOIL));
        fragmentTypes.put("veil_fragment_3", new FragmentType("veil", 3, "Veil Fragment III", Material.SOUL_SOIL));
        fragmentTypes.put("veil_fragment_4", new FragmentType("veil", 4, "Veil Fragment IV", Material.SOUL_SOIL));
        fragmentTypes.put("veil_fragment_5", new FragmentType("veil", 5, "Veil Fragment V", Material.SOUL_SOIL));
        fragmentTypes.put("veil_fragment_6", new FragmentType("veil", 6, "Veil Fragment VI", Material.SOUL_SOIL));
        fragmentTypes.put("veil_fragment_7", new FragmentType("veil", 7, "Veil Fragment VII", Material.SOUL_SOIL));
    }

    public ItemStack createFragment(String fragmentId) {
        FragmentType fragmentType = fragmentTypes.get(fragmentId);
        if (fragmentType == null) {
            return null;
        }

        ItemStack item = new ItemStack(fragmentType.getMaterial());
        ItemMeta meta = item.getItemMeta();
        
        if (meta != null) {
            meta.displayName(Component.text(fragmentType.getDisplayName())
                .color(getColorForGod(fragmentType.getGodType()))
                .decoration(TextDecoration.ITALIC, false));
            
            meta.lore(Arrays.asList(
                Component.text("A fragment of divine power").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.text("from the " + fragmentType.getGodType() + " god").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Fragment " + fragmentType.getNumber() + " of 7").color(NamedTextColor.DARK_GRAY).decoration(TextDecoration.ITALIC, false)
            ));
            
            item.setItemMeta(meta);
        }
        
        return item;
    }

    private NamedTextColor getColorForGod(String godType) {
        switch (godType.toLowerCase()) {
            case "fallen": return NamedTextColor.DARK_PURPLE;
            case "banishment": return NamedTextColor.WHITE;
            case "abyssal": return NamedTextColor.DARK_AQUA;
            case "sylvan": return NamedTextColor.GREEN;
            case "tempest": return NamedTextColor.YELLOW;
            case "veil": return NamedTextColor.DARK_GRAY;
            default: return NamedTextColor.GRAY;
        }
    }

    public FragmentType getFragmentType(String fragmentId) {
        return fragmentTypes.get(fragmentId);
    }

    public Map<String, FragmentType> getAllFragmentTypes() {
        return new HashMap<>(fragmentTypes);
    }

    public static class FragmentType {
        private final String godType;
        private final int number;
        private final String displayName;
        private final Material material;

        public FragmentType(String godType, int number, String displayName, Material material) {
            this.godType = godType;
            this.number = number;
            this.displayName = displayName;
            this.material = material;
        }

        public String getGodType() { return godType; }
        public int getNumber() { return number; }
        public String getDisplayName() { return displayName; }
        public Material getMaterial() { return material; }
    }
}