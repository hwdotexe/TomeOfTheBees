package tech.hadenw.tomeofthebees;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import tech.hadenw.tomeofthebees.commands.BeesCommand;
import tech.hadenw.tomeofthebees.listeners.BeeListener;
import tech.hadenw.tomeofthebees.storage.PlayerInfo;
import tech.hadenw.tomeofthebees.storage.StorageController;

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
