package com.github.irvinglink.DreamWorld.Listeners;

import com.github.irvinglink.DreamWorld.Handlers.DreamPlayer;
import com.github.irvinglink.DreamWorld.Handlers.SettingsMenu;
import com.github.irvinglink.DreamWorld.MClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    private final MClass plugin;

    public InventoryClick(MClass plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        DreamPlayer player = new DreamPlayer((Player) event.getWhoClicked());

        if (event.getInventory().getHolder() instanceof SettingsMenu){

            player.sendMessage("&a&lClicked!");

        }

    }

}
