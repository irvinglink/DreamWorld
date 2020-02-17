package com.github.irvinglink.DreamWorld.Listeners;

import com.github.irvinglink.DreamWorld.Handlers.DreamPlayer;
import com.github.irvinglink.DreamWorld.MClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class PlayerBedEnter implements Listener {

    private final MClass plugin;

    public PlayerBedEnter(MClass plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void getIntoBed(PlayerBedEnterEvent event){

        if (event.isCancelled()) return;

        DreamPlayer player = new DreamPlayer(event.getPlayer());

        player.sendMessage(plugin.getCommonUse().Chat("&bDo you want to discover new worlds? &aYes&7/&cNo", true));

    }
}
