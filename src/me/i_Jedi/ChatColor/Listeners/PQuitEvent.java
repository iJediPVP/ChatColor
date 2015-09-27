package me.i_Jedi.ChatColor.Listeners;

import me.i_Jedi.ChatColor.Main;
import me.i_Jedi.ChatColor.PlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class PQuitEvent implements Listener {
    //Class vars
    private JavaPlugin plugin;
    private HashMap<Player, Player> lastMsgHM = Main.lastMsgHM;

    //Constructor
    public PQuitEvent(JavaPlugin jp){
        plugin = jp;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //Player quit
    @EventHandler
    public void pQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        try{
            lastMsgHM.remove(player);
        }catch(Exception e){} //Do nothing..
        PlayerInfo pInfo = new PlayerInfo(plugin, player);
        pInfo.removeTeam();
        try{
            Bukkit.getScheduler().cancelTask(PlayerInfo.taskList.get(player));
        }catch(NullPointerException npe){}

        //Custom msg
        event.setQuitMessage(player.getPlayerListName() + ChatColor.GOLD + " has left.");
    }

}