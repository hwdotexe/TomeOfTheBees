package com.hadenwatne.tomeofthebees.storage;

import com.google.gson.Gson;
import com.hadenwatne.tomeofthebees.TOTB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates, loads, and tracks file storage for player data.
 */
public class StorageController {
    private Gson _gson;
    private List<PlayerInfo> _players;
    private TOTB plugin;

    /**
     * Discovers all player information files currently stored, and loads them into memory.
     */
    public StorageController(TOTB c) {
        _gson = new Gson();
        _players = new ArrayList<PlayerInfo>();
        plugin=c;

        for(File f : discoverPlayerFiles(plugin.getDataFolder()+"/playerdata")) {
            PlayerInfo pi = _gson.fromJson(loadFileData(f), PlayerInfo.class);
            _players.add(pi);
        }
    }

    /**
     * Retrieves a comprehensive list of player data files in the system.
     * @return A list of PlayerInfo objects.
     */
    public List<PlayerInfo> getLoadedPlayers(){
        return _players;
    }

    /**
     * Retrieves a given UUID's information file, or creates a new one if it does not exist.
     * @param UUID The UUID to search for.
     * @return A PlayerInfo object.
     */
    public PlayerInfo getPlayerInfo(String UUID) {
        for(PlayerInfo pi : _players) {
            if(pi.getUUID().equalsIgnoreCase(UUID)) {
                return pi;
            }
        }

        PlayerInfo pi = new PlayerInfo(UUID);
        _players.add(pi);

        return pi;
    }

    /**
     * Saves a PlayerInfo object to file.
     * @param pi The object to save.
     */
    public void savePlayerInfo(PlayerInfo pi) {
        byte[] bytes = _gson.toJson(pi).toString().getBytes();

        try {
            File pif = new File(plugin.getDataFolder()+"/playerdata/"+pi.getUUID()+".json");
            FileOutputStream os = new FileOutputStream(pif);

            if(!pif.exists())
                pif.createNewFile();

            os.write(bytes);
            os.flush();
            os.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the contents of a file and returns it as a string.
     * @param f The file to read.
     * @return A String representation of the file's contents.
     */
    private String loadFileData(File f) {
        try {
            int data;
            FileInputStream is = new FileInputStream(f);
            String jsonData = "";

            while ((data = is.read()) != -1) {
                jsonData += (char) data;
            }

            is.close();

            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Scans a directory and builds a list of JSON files inside it.
     * @return A list of files.
     */
    private List<File> discoverPlayerFiles(String direc) {
        File dir = new File(direc);

        if (!dir.exists())
            dir.mkdirs();

        File[] files = dir.listFiles();
        List<File> pInfoFiles = new ArrayList<File>();

        for (File f : files) {
            if (f.isFile()) {
                if (f.getName().endsWith(".json")) {
                    pInfoFiles.add(f);
                }
            }
        }

        return pInfoFiles;
    }
}
