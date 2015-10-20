package me.ijedi.chatcolor.Listeners;

import me.ijedi.chatcolor.Chat.ChatManager;
import me.ijedi.chatcolor.Chat.ChatPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PQuitEvent implements Listener {

    //Variables
    private JavaPlugin plugin;

    //Constructor
    public PQuitEvent(JavaPlugin plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //Event
    @EventHandler
    public void pQuit(PlayerQuitEvent event){
        //Get Player
        Player player = event.getPlayer();
        ChatManager cm = new ChatManager();
        ChatPlayer cp = cm.getChatPlayer(player);

        //Cancel task for player
        cp.removeTask();

        //Remove last messaged
        try{
            cm.removeLastMSG(cm.getLastMSG(player));
        }catch(NullPointerException npe){}
        cm.removeChatPlayer(player);

        //Remove team
        cp.removeTeam();

        //Remove from ChatManager
        cm.removeChatPlayer(player);
    }
}
