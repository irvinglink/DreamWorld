package com.github.irvinglink.DreamWorld.Handlers;

import com.github.irvinglink.DreamWorld.MClass;
import com.github.irvinglink.DreamWorld.Utils.CommonUse;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class SettingsMenu implements InventoryHolder {

    private final MClass plugin;
    private String worldName;

    public SettingsMenu(MClass plugin){
        this.plugin = plugin;
    }

    public void  setWorldName(String name){
        this.worldName = name;
    }

    public String getWorldName(){
        return this.worldName;
    }

    @Override
    public Inventory getInventory(){

        CommonUse commonUse = plugin.getCommonUse();
        Inventory menu = Bukkit.createInventory(new SettingsMenu(plugin), InventoryType.CHEST,
                commonUse.Chat("&b&lWorld &e&lSettings &7&l: &a&l"+getWorldName()));


        return menu;
    }

}
