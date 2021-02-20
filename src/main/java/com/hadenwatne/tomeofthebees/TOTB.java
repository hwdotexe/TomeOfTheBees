package com.hadenwatne.tomeofthebees;

import com.hadenwatne.tomeofthebees.commands.BeesCommand;
import com.hadenwatne.tomeofthebees.listeners.BeeListener;
import com.hadenwatne.tomeofthebees.storage.PlayerInfo;
import com.hadenwatne.tomeofthebees.storage.StorageController;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TOTB extends JavaPlugin {
    // TODO jar of bees

    private StorageController _storage;

    public void onEnable(){
        _storage = new StorageController(this);

        // Register events.
        Bukkit.getPluginManager().registerEvents(new BeeListener(this), this);

        // Register commands.
        this.getCommand("bees").setExecutor(new BeesCommand(this));
    }

    public void onDisable(){
        for(PlayerInfo pi : _storage.getLoadedPlayers()){
            _storage.savePlayerInfo(pi);
        }
    }

    public StorageController getStorage(){
        return _storage;
    }
}
