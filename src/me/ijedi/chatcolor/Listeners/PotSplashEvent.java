package me.ijedi.chatcolor.Listeners;

import me.ijedi.chatcolor.Chat.ChatManager;
import me.ijedi.chatcolor.Chat.ChatPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PotSplashEvent implements Listener {

    //Variables
    private JavaPlugin plugin;

    //Constructor
    public PotSplashEvent(JavaPlugin plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //Event
    @EventHandler
    public void potSplash(PotionSplashEvent event){
        //Check for invisibility
        for(PotionEffect potionEffect : event.getPotion().getEffects()){
            if(potionEffect.getType().equals(PotionEffectType.INVISIBILITY)){

                //Loop through affected players
                for(Entity e : event.getAffectedEntities()){
                    if(e instanceof Player){
                        final Player player = (Player) e;
                        final ChatPlayer cp = new ChatManager().getChatPlayer(player);

                        //Wait 1 tick before getting potion time
                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                //Get time of invisibility
                                for(PotionEffect pe : player.getActivePotionEffects()){
                                    if(pe.getType().equals(PotionEffectType.INVISIBILITY)){
                                        int time = (int) Math.floor(pe.getDuration() / 20);

                                        //Hide name tag for time length
                                        cp.setTagVisibility(false, time);
                                    }
                                }
                            }
                        }.runTaskLater(plugin, 1l);
                    }
                }

                return;
            }//Else continue
        }
    }
}
