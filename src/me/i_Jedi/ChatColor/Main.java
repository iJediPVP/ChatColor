/*TODO LIST
Make a branch of this that uses permissions
* */

package me.i_Jedi.ChatColor;

import me.i_Jedi.ChatColor.Chat.ChatConfig;
import me.i_Jedi.ChatColor.Commands.Com_Chat;
import me.i_Jedi.ChatColor.Commands.Com_MsgTell;
import me.i_Jedi.ChatColor.Commands.Com_R;
import me.i_Jedi.ChatColor.Listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class Main extends JavaPlugin {

    //Variables
    public static HashMap<Player, Player> lastMsgHM = new HashMap<>();

    //Enabled
    @Override
    public void onEnable(){

        //Check if config exists
        ChatConfig cConfig = new ChatConfig(this);
        if(!cConfig.exists()){
            cConfig.setDefaults();
        }

        //See if you need to hide name tags
        boolean isNameTagEnabled = cConfig.isNameTagEnabled();
        if(isNameTagEnabled){
            for(Player p : Bukkit.getOnlinePlayers()){
                for(PotionEffect pe : p.getActivePotionEffects()){
                    if(pe.getType().equals(PotionEffectType.INVISIBILITY)){
                        int count = (int) Math.floor(pe.getDuration() / 20);
                        PlayerInfo pInfo = new PlayerInfo(this, p);
                        pInfo.setTagVisibility(false, count);
                    }
                }
            }
        }

        //Register commands
        getCommand("chat").setExecutor(new Com_Chat());
        getCommand("msg").setExecutor(new Com_MsgTell(this));
        getCommand("tell").setExecutor(new Com_MsgTell(this));
        getCommand("r").setExecutor(new Com_R(this));

        //Register events
        new AsyncPChatEvent(this);
        new InvClickEvent(this);
        new PConsumeEvent(this);
        new PJoinEvent(this);
        new PotSplashEvent(this);
        new PQuitEvent(this);

        //Clear lastMsgHM and add each player back to it
        lastMsgHM.clear();
        for(Player p : Bukkit.getOnlinePlayers()){
            lastMsgHM.put(p, null);
        }
        getLogger().info("ChatColor has been enabled!!");
    }

    //Disabled
    @Override
    public void onDisable(){
        Bukkit.getScheduler().cancelTasks(this);
        getLogger().info("ChatColor has been disabled!");
    }


}
