package me.ijedi.chatcolor;

import me.ijedi.chatcolor.Chat.ChatManager;
import me.ijedi.chatcolor.Chat.ChatPlayer;
import me.ijedi.chatcolor.Commands.Chat;
import me.ijedi.chatcolor.Commands.MsgTell;
import me.ijedi.chatcolor.Commands.Reply;
import me.ijedi.chatcolor.Listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin{

    //Enabled
    @Override
    public void onEnable(){
        //Default config
        saveDefaultConfig();

        //Add online players to ChatManager
        ChatManager cm = new ChatManager();
        for(Player p : Bukkit.getOnlinePlayers()){
            ChatPlayer cp = new ChatPlayer(p, this);

            //Check for invisibility
            for(PotionEffect pe : p.getActivePotionEffects()){
                if(pe.getType().equals(PotionEffectType.INVISIBILITY)){
                    int time = (int) Math.floor(pe.getDuration());
                    cp.setTagVisibility(false, time);
                    break;
                }
            }

            //Add player
            cm.addChatPlayer(p, cp);
        }

        //Get commands
        getCommand("CHAT").setExecutor(new Chat());
        getCommand("MSG").setExecutor(new MsgTell());
        getCommand("REPLY").setExecutor(new Reply());

        //Call events
        new ChatEvent(this);
        new InvClickEvent(this);
        new PConsumeEvent(this);
        new PJoinEvent(this);
        new PotSplashEvent(this);
        new PQuitEvent(this);

        //Log
        this.getLogger().info("ChatColor has been enabled!");
    }

    //Disabled
    @Override
    public void onDisable(){
        //Log
        this.getLogger().info("ChatColor has been disabled!");
    }
}
