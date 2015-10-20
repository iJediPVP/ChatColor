package me.ijedi.chatcolor.Listeners;

import me.ijedi.chatcolor.Chat.ChatManager;
import me.ijedi.chatcolor.Chat.ChatPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatEvent implements Listener {

    //Variables
    private JavaPlugin plugin;

    //Constructor
    public ChatEvent(JavaPlugin plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //Event
    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent event){
        //Don't change if command
        if(event.getMessage().substring(0, 1).equals("/")){
            event.setCancelled(true);
           return;
        }

        //Get player and try to get ChatPlayer
        try{
            Player player = event.getPlayer();
            ChatPlayer cp = new ChatManager().getChatPlayer(player);

            //Get name and format message
            String pName = cp.getName();
            String message = event.getMessage().replaceAll("%", "%%");
            message = "<" + pName + ChatColor.RESET + "> " + cp.getChatPrefix() + message;

            //Output
            event.setFormat(message);
        }catch(NullPointerException npe){} //Do nothing.

    }
}
