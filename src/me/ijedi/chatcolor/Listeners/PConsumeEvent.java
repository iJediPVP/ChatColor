package me.ijedi.chatcolor.Listeners;

import me.ijedi.chatcolor.Chat.ChatManager;
import me.ijedi.chatcolor.Chat.ChatPlayer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PConsumeEvent implements Listener{

    //Variables
    private JavaPlugin plugin;

    //Constructor
    public PConsumeEvent(JavaPlugin plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //Event
    @EventHandler
    public void pConsume(PlayerItemConsumeEvent event){
        //Check for potion
        if(event.getItem().getType().equals(Material.POTION)){
            //Check for invisibility potions
            short durability = event.getItem().getDurability();
            ChatPlayer cp = new ChatManager().getChatPlayer(event.getPlayer());
            if(durability == (short) 8238){
                int time = 180;
                cp.setTagVisibility(false, time);

            }else if(durability == (short) 8270){
                int time = 480;
                cp.setTagVisibility(false, time);

            }//Else nothing.

        //Milk bucket
        }else if(event.getItem().getType().equals(Material.MILK_BUCKET)){
            //Set tag visibility to true
            ChatPlayer cp = new ChatManager().getChatPlayer(event.getPlayer());
            cp.setTagVisibility(true, 0);
        }
    }
}
