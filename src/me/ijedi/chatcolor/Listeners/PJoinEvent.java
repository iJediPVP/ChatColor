package me.ijedi.chatcolor.Listeners;

import me.ijedi.chatcolor.Chat.ChatManager;
import me.ijedi.chatcolor.Chat.ChatPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PJoinEvent implements Listener {

    //Variables
    private JavaPlugin plugin;

    //Constructor
    public PJoinEvent(JavaPlugin plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //Event
    @EventHandler
    public void pJoin(PlayerJoinEvent event){
        //Add ChatPlayer to ChatManager
        Player player = event.getPlayer();
        ChatPlayer cp = new ChatPlayer(player, plugin);

        //Check for invisibility
        for(PotionEffect pe : player.getActivePotionEffects()){
            if(pe.getType().equals(PotionEffectType.INVISIBILITY)){
                int time = (int) Math.floor(pe.getDuration() / 20);
                cp.setTagVisibility(false, time);
            }
        }

        new ChatManager().addChatPlayer(player, cp);
    }
}
