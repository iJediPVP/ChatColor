package me.i_Jedi.ChatColor.Chat;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ChatConfig {

    //Class vars
    JavaPlugin plugin;
    File file;
    FileConfiguration config;

    //Constructor
    public ChatConfig(JavaPlugin jp){
        plugin = jp;
        file = new File(plugin.getDataFolder() + "/config.yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    //See if exists
    public boolean exists(){
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    //Set config defaults
    public void setDefaults(){
        config.set("nameTagEnabled", false);
        saveFile();
    }

    //Save file
    public void saveFile(){
        try{
            config.save(file);
        }catch(IOException ioe){
            plugin.getLogger().info("ChatConfig - Error saving file.");
        }
    }

    //Get nametagenabled
    public boolean isNameTagEnabled(){
        try{
            return config.getBoolean("nameTagEnabled");
        }catch(NullPointerException npe){
            return false;
        }
    }
}
