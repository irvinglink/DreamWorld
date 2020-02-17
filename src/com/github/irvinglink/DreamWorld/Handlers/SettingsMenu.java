package com.github.irvinglink.DreamWorld.Handlers;

import com.github.irvinglink.DreamWorld.MClass;
import com.github.irvinglink.DreamWorld.Utils.CommonUse;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class SettingsMenu implements InventoryHolder {

    private final MClass plugin;
    private static String worldName;

    public SettingsMenu(MClass plugin) {
        this.plugin = plugin;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public String getWorldName() {
        return worldName;
    }

    @Override
    public Inventory getInventory() {

        System.out.println(worldName);

        CommonUse commonUse = plugin.getCommonUse();
        Inventory menu = Bukkit.createInventory(new SettingsMenu(plugin), InventoryType.CHEST,
                commonUse.Chat("&b&lWorld &e&lSettings &7&l: &a&l" + getWorldName()));


        return menu;
    }

}
