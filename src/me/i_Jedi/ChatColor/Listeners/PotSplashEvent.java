package me.i_Jedi.ChatColor.Listeners;

import me.i_Jedi.ChatColor.PlayerInfo;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class PotSplashEvent implements Listener {

    //Class vars
    private JavaPlugin plugin;
    //Constructor
    public PotSplashEvent(JavaPlugin jp){
        plugin = jp;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //Event
    @EventHandler
    public void potSplashEvent(PotionSplashEvent event){

        //Check for invisibility
        for(PotionEffect pe : event.getPotion().getEffects()){
            if(pe.getType().equals(PotionEffectType.INVISIBILITY)){

                //Get affected players
                List<Player> pList = new ArrayList<Player>();
                for(Entity e : event.getAffectedEntities()){
                    if(e instanceof Player){
                        final Player player = (Player) e;
                        //Wait 1 tick before getting the potion effect length.
                        new BukkitRunnable(){
                            @Override
                            public void run(){
                                for(PotionEffect potE : player.getActivePotionEffects()){
                                    if(potE.getType().equals(PotionEffectType.INVISIBILITY)){
                                        int count = (int) Math.floor(potE.getDuration() / 20);
                                        PlayerInfo pInfo = new PlayerInfo(plugin, player);
                                        pInfo.setTagVisibility(false, count);
                                    }
                                }
                                this.cancel();
                            }
                        }.runTaskLater(plugin, 1L);
                    }
                }

                return;
            }
        }


    }
}
