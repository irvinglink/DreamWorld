package com.github.irvinglink.DreamWorld.Handlers;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Spawn {

    private final Location location;
    private final String name;

    Spawn(String name, Location location){
        this.location = location;
        this.name = name;
    }

    public World getWorld() {
        return this.location.getWorld();
    }

    public String getName() {
        return this.name;
    }

    public Location getLocation() {
        return this.location;
    }

}
