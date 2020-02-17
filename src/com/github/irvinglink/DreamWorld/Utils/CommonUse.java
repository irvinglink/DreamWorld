package com.github.irvinglink.DreamWorld.Utils;

import com.github.irvinglink.DreamWorld.MClass;
import org.bukkit.ChatColor;

public class CommonUse {

    private final MClass plugin;

    public CommonUse(MClass plugin){
        this.plugin = plugin;
    }

    public String Chat(String textToTranslate, boolean value){
        if (value) return ChatColor.translateAlternateColorCodes('&', "&8[&bDream&eWorld&8]&7 " + textToTranslate);
        return ChatColor.translateAlternateColorCodes('&', textToTranslate);
    }

    public String Chat(String textToTranslate){
        return ChatColor.translateAlternateColorCodes('&', textToTranslate);
    }
}
