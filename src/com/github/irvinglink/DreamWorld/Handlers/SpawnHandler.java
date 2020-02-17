package com.github.irvinglink.DreamWorld.Handlers;

import com.github.irvinglink.DreamWorld.MClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.*;

public class SpawnHandler {

    private final MClass plugin;
    private static List<Spawn> spawnList = new ArrayList<>();

    public SpawnHandler(MClass plugin) {
        this.plugin = plugin;
    }

    public Spawn getSpawn(String name) {
        if (!(plugin.getSpawnFile().contains("Worlds." + name))) return null;
        return new Spawn(name, (Location) plugin.getSpawnFile().get("Worlds." + name));
    }

    public Spawn randomSpawn() {

        if (!getSpawnList().isEmpty()) {

            int r = new Random().nextInt(getSpawnList().size());

            return getSpawn(getSpawnList().get(r).getName());
        }
        return null;
    }

    public void createSpawn(String name, Location location) {

        plugin.getSpawnFile().set("Worlds." + name, location);

        if (!getSpawnList().contains(getSpawn(name))) getSpawnList().add(new Spawn(name, location));

        plugin.saveConfig();
    }

    public void deleteSpawn(String name) {

        plugin.getSpawnFile().set("Worlds." + name, null);

        if (!getSpawnList().contains(getSpawn(name))) getSpawnList().remove(getSpawn(name));

        plugin.saveConfig();
    }

    public static List<Spawn> getSpawnList() {
        return spawnList;
    }

    public void loadWorldList() {

        try {

            Set<String> worlds = Objects.requireNonNull(plugin.getSpawnFile().getConfigurationSection("Worlds.")).getKeys(true);

            if (worlds.isEmpty()) {
                this.spawnList = new ArrayList<>();
                return;
            }

            for (String world : worlds) {
                getSpawnList().add(getSpawn(world));
            }

        } catch (NullPointerException ignored) {
            Bukkit.getServer().getLogger().info("There are no worlds registered.");
        }

    }
}
