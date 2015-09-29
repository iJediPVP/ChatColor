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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        //Remove this player's key from the HM
        try{
            lastMsgHM.remove(player);
        }catch(Exception e){} //Do nothing..
        PlayerInfo pInfo = new PlayerInfo(plugin, player);
        pInfo.removeTeam();

        //Remove this player from other player's HM
        List<Player> pList = new ArrayList<Player>();
        for(Player p : lastMsgHM.keySet()){
            try{
                if(lastMsgHM.get(p).equals(player)){
                    pList.add(p);
                }
            }catch(NullPointerException npe){
                continue;
            }
        }
        for(Player p : pList){
            lastMsgHM.put(p, null);
        }

        //Cancel task for player
        try{
            Bukkit.getScheduler().cancelTask(PlayerInfo.taskList.get(player));
        }catch(NullPointerException npe){}

        //Custom msg
        event.setQuitMessage(player.getPlayerListName() + ChatColor.GOLD + " has left.");
    }

}