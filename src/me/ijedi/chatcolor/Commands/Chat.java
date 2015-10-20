package me.ijedi.chatcolor.Commands;

import me.ijedi.chatcolor.Menus.MainMenu;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Chat implements CommandExecutor{

    //Command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        //Check for player
        if(sender instanceof Player){
            //Check command
            if(command.getName().equalsIgnoreCase("CHAT")){
                //Get player & check for perms
                Player player = (Player) sender;
                if(!player.hasPermission("chatColor.command.chat")){
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] " + ChatColor.RED + "You do not have permission to use this command.");
                    return true;
                }

                //Open MainMenu
                player.openInventory(new MainMenu().getInventory());
                player.playSound(player.getLocation(), Sound.CHEST_OPEN, 7, 1);
            } //Else do nothing

        }else{
            sender.sendMessage("This command is only for players!");
        }
        return true;
    }
}
