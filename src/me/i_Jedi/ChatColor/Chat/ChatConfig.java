package me.i_Jedi.ChatColor.Chat;

import org.bukkit.plugin.java.JavaPlugin;

public class ChatConfig {

    //Class vars
    JavaPlugin plugin;

    //Constructor
    public ChatConfig(JavaPlugin jp){
        plugin = jp;
        plugin.saveDefaultConfig();
    }


    //Get nametagenabled
    public boolean isNameTagEnabled(){
        try{
            return plugin.getConfig().getBoolean("nameTagEnabled");
        }catch(NullPointerException npe){
            return false;
        }
    }
}
