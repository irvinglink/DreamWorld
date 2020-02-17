package com.github.irvinglink.DreamWorld.Handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

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

    public void sendMessage(String message, boolean value){
        if (value) sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&bDream&eWorld&8]&7 " + message));
        else sendMessage(message);
    }

    public Location getLocation(){
        return getPlayer().getLocation();
    }

}
