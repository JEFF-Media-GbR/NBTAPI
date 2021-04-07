package de.jeff_media.nbtapi;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class NBTAPI {
    
    private static Plugin main;
    
    public static void init(Plugin plugin) {
        main=plugin;
    }

    public static @Nullable String getNBT(@NotNull ItemStack item, String key) {
        Objects.requireNonNull(main,"NBTAPI hasn't been initialized.");
        Objects.requireNonNull(item,"item must not be null");
        if(!item.hasItemMeta()) return null;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(main,key);
        if(pdc.has(namespacedKey, PersistentDataType.STRING)) {
            return pdc.get(namespacedKey, PersistentDataType.STRING);
        }
        return null;
    }

    public static @Nullable String getNBT(@NotNull Entity entity, String key) {
        Objects.requireNonNull(main,"NBTAPI hasn't been initialized.");
        Objects.requireNonNull(entity,"entity must not be null");
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(main,key);
        if(pdc.has(namespacedKey, PersistentDataType.STRING)) {
            return pdc.get(namespacedKey, PersistentDataType.STRING);
        }
        return null;
    }

    public static void addNBT(@NotNull ItemStack item, String key, String value) {
        Objects.requireNonNull(main,"NBTAPI hasn't been initialized.");
        Objects.requireNonNull(item,"item must not be null");
        ItemMeta meta = item.hasItemMeta() ? item.getItemMeta() : Bukkit.getItemFactory().getItemMeta(item.getType());
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(main, key);
        pdc.set(namespacedKey, PersistentDataType.STRING, value);
        item.setItemMeta(meta);
    }

    public static void addNBT(@NotNull Entity entity, String key, String value) {
        Objects.requireNonNull(main,"NBTAPI hasn't been initialized.");
        Objects.requireNonNull(entity,"entity must not be null");
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(main, key);
        pdc.set(namespacedKey, PersistentDataType.STRING, value);
    }

    public static boolean hasNBT(@NotNull ItemStack item, String key) {
        Objects.requireNonNull(main,"NBTAPI hasn't been initialized.");
        Objects.requireNonNull(item,"item must not be null");
        if(!item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        return pdc.has(new NamespacedKey(main,key),PersistentDataType.STRING);
    }

    public static boolean hasNBT(@NotNull Entity entity, String key) {
        Objects.requireNonNull(main,"NBTAPI hasn't been initialized.");
        Objects.requireNonNull(entity,"entity must not be null");
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        return pdc.has(new NamespacedKey(main,key),PersistentDataType.STRING);
    }

}
