package me.i_Jedi.ChatColor.Commands;

import me.i_Jedi.ChatColor.Chat.ChatInventory;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Com_Chat implements CommandExecutor {

    //Class vars

    //Command method
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        //Check for instance of player
        if(sender instanceof Player){
            //Store player and command
            Player player = (Player) sender;
            String command = cmd.getName().toUpperCase();
            //Open chat menu
            if(command.equals("CHAT")){
                ChatInventory invInfo = new ChatInventory();
                player.openInventory(invInfo.getMainColorInv());
                player.playSound(player.getLocation(), Sound.CHEST_OPEN, 7, 1);
            }
        }
        return true;
    }
}
