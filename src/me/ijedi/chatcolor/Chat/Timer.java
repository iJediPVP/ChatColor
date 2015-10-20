package me.ijedi.chatcolor.Chat;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable{

    //Variables
    private JavaPlugin plugin;
    private Player player;


    //Constructor
    public Timer(JavaPlugin plugin, Player player){
        this.plugin = plugin;
        this.player = player;
    }

    //Run
    private boolean bool = false;
    @Override
    public void run() {
        if(bool){
            ChatPlayer cp = new ChatManager().getChatPlayer(player);
            cp.setTagVisibility(true, 0);
            this.cancel();
        }else{
            bool = true;
        }
    }
}
