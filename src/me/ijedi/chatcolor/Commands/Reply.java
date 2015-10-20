package me.ijedi.chatcolor.Commands;

import me.ijedi.chatcolor.Chat.ChatManager;
import me.ijedi.chatcolor.Chat.ChatPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reply implements CommandExecutor {

    //Variables
    private String pluginPrefix = ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] ";

    //Command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        //Check for player
        if(sender instanceof Player){
            //Get player & check command
            Player player = (Player) sender;
            String cmd = command.getName().toUpperCase();
            if(cmd.equals("REPLY")){

                //Check for perms
                if(!player.hasPermission("chatColor.command.reply")){
                    player.sendMessage(pluginPrefix + ChatColor.RED + "You do not have permission to use this command.");
                    return true;
                }

                //Check for last msg
                ChatManager cm = new ChatManager();
                try{
                    Player lastPlayer = cm.getLastMSG(player);
                    ChatPlayer chatPlayer = cm.getChatPlayer(player);
                    ChatPlayer lastPlayerCP = cm.getChatPlayer(lastPlayer);

                    //Check for args & get message
                    String message = "";
                    if(args.length <= 0){
                        player.sendMessage(pluginPrefix + ChatColor.RED + "You forgot to send a message!");
                        return true;
                    }else{
                        //Get message
                        for(int x = 0; x < args.length; x++){
                            if(x == 0){
                                message = args[x];
                            }else{
                                message = message + " " + args[x];
                            }
                        }
                        message.replaceAll("%", "%%");
                    }

                    //Color message & send it to players
                    message = chatPlayer.getChatPrefix() + message;

                    //Send messages
                    player.sendMessage(ChatColor.GOLD + "[You] to " + lastPlayerCP.getName() + ChatColor.GOLD + ": " + message);
                    lastPlayer.sendMessage(ChatColor.GOLD + "[" + chatPlayer.getName() + ChatColor.GOLD + "] to You: " + message);

                    //Store last messaged
                    cm.addLastMSG(player, lastPlayer);
                    cm.addLastMSG(lastPlayer, player);

                }catch(NullPointerException npe){
                    player.sendMessage(pluginPrefix + ChatColor.RED + "You have not messaged anyone yet!");
                }

            } //Else do nothing

        }else{
            sender.sendMessage("This command is only for players!");
        }
        return true;
    }
}
