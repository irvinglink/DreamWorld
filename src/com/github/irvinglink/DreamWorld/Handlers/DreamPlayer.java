package com.github.irvinglink.DreamWorld.Handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;

public class DreamPlayer {

    private Player player;

    public DreamPlayer(Player player) {
        this.player = player;
    }

    public DreamPlayer(String name) {
        this.player = Bukkit.getPlayer(name);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void teleport(Spawn spawn) {
        getPlayer().teleport(spawn.getLocation());
    }

    public void sendMessage(String message){
        getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public Location getLocation(){
        return getPlayer().getLocation();
    }

}
