package com.github.irvinglink.DreamWorld.Listeners;

import com.github.irvinglink.DreamWorld.MClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener {


    private MClass plugin;

    public AsyncPlayerChat(MClass plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onType(AsyncPlayerChatEvent event) {

        if (event.isCancelled()) return;

        Player player = event.getPlayer();

        if (player.isSleeping()) {

            if (event.getMessage().equalsIgnoreCase("Yes")) {

                plugin.getVersion().wakeUpPlayer(player);

                event.setCancelled(true);

            } else if (event.getMessage().equalsIgnoreCase("No")) {

                player.sendMessage(plugin.getCommonUse().Chat("&cTeletransportation has been disable.", true));
                event.setCancelled(true);

            }
        }
    }
}
