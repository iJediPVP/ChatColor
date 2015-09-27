package me.i_Jedi.ChatColor.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PDeathEvent implements Listener {

    //Constructor
    public PDeathEvent(JavaPlugin jp){
        jp.getServer().getPluginManager().registerEvents(this, jp);
    }

    //Event
    @EventHandler
    public void pDeathEvent(PlayerDeathEvent event){
        //Store players
        Player player = event.getEntity();
        Player killer;
        try{ //Player killed by player message
            killer = player.getKiller();
            String message = player.getPlayerListName() + ChatColor.GOLD + " was killed by " + killer.getPlayerListName();

            //Check for item name
            if(killer.getItemInHand().getItemMeta().hasDisplayName()){
                message = message + ChatColor.GOLD + " using " + ChatColor.AQUA + "[" + killer.getItemInHand().getItemMeta().getDisplayName() + "]";
            }
            event.setDeathMessage(message + ChatColor.GOLD + "!");

        }catch(NullPointerException npe){ //Player killed by something else
            String message = event.getDeathMessage().substring(player.getPlayerListName().length() + 1);
            event.setDeathMessage(player.getPlayerListName() + ChatColor.GOLD + " " + message + "!");
        }
    }


}
