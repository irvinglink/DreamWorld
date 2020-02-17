package com.github.irvinglink.DreamWorld;

import com.github.irvinglink.DreamWorld.Commands.DreamCommand;
import com.github.irvinglink.DreamWorld.Handlers.SpawnHandler;
import com.github.irvinglink.DreamWorld.Listeners.AsyncPlayerChat;
import com.github.irvinglink.DreamWorld.Listeners.PlayerBedEnter;
import com.github.irvinglink.DreamWorld.Packets.V1_14_R1;
import com.github.irvinglink.DreamWorld.Packets.V1_8_R3;
import com.github.irvinglink.DreamWorld.Packets.Version;
import com.github.irvinglink.DreamWorld.Utils.CommonUse;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MClass extends JavaPlugin {

    private CommonUse commonUse;
    private SpawnHandler spawnHandler;

    private File spawnYAML;
    private FileConfiguration spawn;

    private List<UUID> dreamingList = new ArrayList<>();

    private Version version;

    @Override
    public void onEnable() {

        setVersion();

        ///////////////
        //CONFIG FILES
        spawnYAML = new File(getDataFolder(), "spawns.yml");

        if (!getDataFolder().exists()) {
            try {
                Files.createDirectory(getDataFolder().toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!spawnYAML.exists()) {
            try {
                Files.copy(Objects.requireNonNull(getResource(spawnYAML.getName())), spawnYAML.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ///////////////

        commonUse = new CommonUse(this);
        spawnHandler = new SpawnHandler(this);

        spawnHandler.loadWorldList();

        Objects.requireNonNull(getServer().getPluginCommand("dreamworld")).setExecutor(new DreamCommand(this));

        getServer().getPluginManager().registerEvents(new PlayerBedEnter(this), this);
        getServer().getPluginManager().registerEvents(new AsyncPlayerChat(this), this);


    }

    public void reloadConfig() {
        if (!(spawn == null)) {
            this.spawn = YamlConfiguration.loadConfiguration(this.spawnYAML);
        }
    }

    public void saveConfig() {
        if (!(spawn == null)) {
            try {
                this.spawn.save(this.spawnYAML);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setVersion(){

        String version = Bukkit.getBukkitVersion().split("-")[0];

        switch (version){
            case "1.8.8":
                this.version = new V1_8_R3(this);
                break;
            case "1.14.4":
                this.version = new V1_14_R1(this);
                break;
        }
    }

    public Version getVersion(){
        return this.version;
    }

    public CommonUse getCommonUse(){
        return commonUse;
    }

    public SpawnHandler getSpawnHandler(){
        return spawnHandler;
    }

    public List<UUID> getDreamingList() {
        return dreamingList;
    }

    public FileConfiguration getSpawnFile() {
        if (!(spawn == null)) return spawn;
        spawn = new YamlConfiguration();
        try {
            spawn.load(spawnYAML);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return spawn;
    }

}
