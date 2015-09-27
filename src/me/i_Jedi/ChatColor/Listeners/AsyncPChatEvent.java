package me.i_Jedi.ChatColor.Listeners;

import me.i_Jedi.ChatColor.PlayerInfo;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AsyncPChatEvent implements Listener {

    //Class vars
    private JavaPlugin plugin;

    //Constructor
    public AsyncPChatEvent(JavaPlugin jp){
        plugin = jp;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //Event
    @EventHandler
    public void pChatEvent(AsyncPlayerChatEvent event){
        String test = event.getMessage().substring(0, 1);
        if(test.equals("/")){
            event.setCancelled(true);
            return;
        }
        Player player = event.getPlayer();
        //***** Name *****
        PlayerInfo pInfo = new PlayerInfo(plugin, player);
        String pName;
        String message = event.getMessage();
        //Check for reset style
        ChatColor nStyle = pInfo.getNameStyle();
        ChatColor nColor  = pInfo.getNameColor();
        if(nStyle.equals(ChatColor.RESET)){
            pName = nColor + player.getName();
        }else{
            pName = nColor + "" + nStyle + player.getName();
        }

        //***** Chat *****
        message = message.replaceAll("%", "%%");
        //Check for reset style
        ChatColor cStyle = pInfo.getChatStyle();
        ChatColor cColor = pInfo.getChatColor();
        if(cStyle.equals(ChatColor.RESET)){
            event.setFormat("<" + pName + ChatColor.RESET + "> " + cColor + message);
        }else{
            event.setFormat("<" + pName + ChatColor.RESET + "> " + cColor + "" + cStyle + message);
        }
    }
}
