package me.i_Jedi.ChatColor.Listeners;

import me.i_Jedi.ChatColor.PlayerInfo;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PConsumeEvent implements Listener {

    //Class vars
    private JavaPlugin plugin;

    //Constructor
    public PConsumeEvent(JavaPlugin jp){
        plugin = jp;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //Event
    @SuppressWarnings("deprecation")
    @EventHandler
    public void pConsume(PlayerItemConsumeEvent event){

        if(event.getItem().getType().equals(Material.POTION)){
            //Get the item's durability
            short dur = event.getItem().getDurability();
            if(dur == (short) 8238){ //Short invis potion
                int shortTime = 180;
                PlayerInfo pInfo = new PlayerInfo(plugin, event.getPlayer());
                pInfo.setTagVisibility(false, shortTime);
            }else if(dur == (short) 8270){ //Longer invis potion
                int longTime = 480;
                PlayerInfo pInfo = new PlayerInfo(plugin, event.getPlayer());
                pInfo.setTagVisibility(false, longTime);
            }

        }else if(event.getItem().getType().equals(Material.MILK_BUCKET)){
            PlayerInfo pInfo = new PlayerInfo(plugin, event.getPlayer());
            pInfo.setTagVisibility(true, 0);
        }
    }
}
