package me.i_Jedi.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable {

    //Class vars
    JavaPlugin plugin;
    Player player;
    //Constructor
    public Timer(JavaPlugin jp, Player p){
        plugin = jp;
        player = p;
    }

    //Run
    boolean bool = false;
    public void run(){ //Skip the first loop, do things on the second.
        if(bool){
            PlayerInfo pInfo = new PlayerInfo(plugin, player);
            pInfo.setTagVisibility(true, 0);
            this.cancel();
        }else{
            bool = true;
        }
    }
}
