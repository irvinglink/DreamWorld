package com.github.irvinglink.DreamWorld.Packets;

import com.github.irvinglink.DreamWorld.Handlers.DreamPlayer;
import com.github.irvinglink.DreamWorld.Handlers.Spawn;
import com.github.irvinglink.DreamWorld.Handlers.SpawnHandler;
import com.github.irvinglink.DreamWorld.MClass;
import net.minecraft.server.v1_14_R1.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class V1_14_R1 implements Version {

    private final MClass plugin;

    public V1_14_R1(MClass plugin) {
        this.plugin = plugin;
    }

    @Override
    public void wakeUpPlayer(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {

                PacketPlayOutAnimation packet = new PacketPlayOutAnimation(((CraftPlayer) player).getHandle(), 2);

                                for (Player op : Bukkit.getOnlinePlayers()) {
                    ((CraftPlayer) op).getHandle().playerConnection.sendPacket(packet);
                }

                Spawn spawn = plugin.getSpawnHandler().randomSpawn();
                DreamPlayer dreamPlayer = new DreamPlayer(player);

                dreamPlayer.teleport(spawn);

                plugin.getDreamingList().add(player.getUniqueId());

            }
        }.runTask(plugin);
    }
}
