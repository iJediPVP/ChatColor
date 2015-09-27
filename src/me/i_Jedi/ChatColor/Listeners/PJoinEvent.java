package me.i_Jedi.ChatColor.Listeners;

import me.i_Jedi.ChatColor.Main;
import me.i_Jedi.ChatColor.PlayerInfo;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PJoinEvent implements Listener {

    //Class vars
    private HashMap<Player, Player> lastMsgHM = Main.lastMsgHM;
    private JavaPlugin plugin;

    //Constructor
    public PJoinEvent(JavaPlugin jp){
        plugin = jp;
        jp.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //Player join
    @EventHandler
    public void pJoin(PlayerJoinEvent event){
        //Store player
        Player player = event.getPlayer();

        //Make sure player file exists. If not set default values
        File pFile = new File(plugin.getDataFolder() + "/playerData/" + player.getUniqueId() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        PlayerInfo pInfo = new PlayerInfo(plugin, player);
        if(!pFile.exists()){
            pConfig.set("nameColor", "RESET");
            pConfig.set("nameStyle", "RESET");
            pConfig.set("chatColor", "RESET");
            pConfig.set("chatStyle", "RESET");
            try{
                pConfig.save(pFile);
            }catch(IOException ioe){}
        }else{
            ChatColor style = pInfo.getNameStyle();
            ChatColor color = pInfo.getNameColor();
            String pName;
            //Check for reset style
            if(style.equals(ChatColor.RESET)){
                pName = color + player.getName();
            }else{
                pName = color + "" + style + player.getName();
            }
            player.setPlayerListName(pName);

            //Set team
            pInfo.setTeam();
        }

        //Check for invis
        for(PotionEffect pe : player.getActivePotionEffects()){
            if(pe.getType().equals(PotionEffectType.INVISIBILITY)){
                int count = (int) Math.floor(pe.getDuration() / 20);
                pInfo.setTagVisibility(false, count);
            }
        }

        //Store hashmap data
        lastMsgHM.put(player, null);

        //Custom Join message
        event.setJoinMessage(player.getPlayerListName() + ChatColor.GOLD + " has joined!");
    }
}
