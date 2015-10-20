package me.ijedi.chatcolor.Commands;

import me.ijedi.chatcolor.Chat.ChatManager;
import me.ijedi.chatcolor.Chat.ChatPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgTell implements CommandExecutor {

    //Variables
    private String pluginPrefix = ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] ";

    //Command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        //Check for player
        if(sender instanceof Player){

            //Check command
            String cmd = command.getName().toUpperCase();
            if(cmd.equals("MSG")){

                //Get player
                Player player = (Player) sender;

                //Check for perms
                if(!player.hasPermission("chatColor.command.msg")){
                    player.sendMessage(pluginPrefix + ChatColor.RED + "You do not have permission to use this command.");
                    return true;
                }

                ChatManager cm = new ChatManager();
                ChatPlayer chatPlayer = cm.getChatPlayer(player);

                //Check for args
                if(args.length == 0){
                    player.sendMessage(pluginPrefix + ChatColor.RED + "You did not specify a player to message!");
                    return true;

                }else if(args.length >= 1){
                    //Try to get other player
                    Player receiver;
                    for(Player p : Bukkit.getOnlinePlayers()){
                        if(p.getName().equalsIgnoreCase(args[0])){
                            receiver = p;

                            //Make sure the player isn't sending themselves a message
                            if(player.equals(receiver)){
                                player.sendMessage(pluginPrefix + ChatColor.RED + "You can not send a message to yourself!");
                                return true;
                            }

                            ChatPlayer receiverCP = cm.getChatPlayer(receiver);

                            //Check for message
                            if(args.length >= 2){
                                //Get message
                                String message = "";
                                for(int x = 1; x < args.length; x++){
                                    if(x == 1){
                                        message = args[x];
                                    }else{
                                        message = message + " " + args[x];
                                    }
                                }
                                //Replace % with %% to keep chat color from breaking & color it
                                message.replaceAll("%", "%%");
                                message = chatPlayer.getChatPrefix() + message;

                                //Send messages
                                player.sendMessage(ChatColor.GOLD + "[You] to " + receiverCP.getName() + ChatColor.GOLD + ": " + message);
                                receiver.sendMessage(ChatColor.GOLD + "[" + chatPlayer.getName() + ChatColor.GOLD + "] to You: " + message);

                                //Store last messaged
                                cm.addLastMSG(player, receiver);
                                cm.addLastMSG(receiver, player);

                                return true;

                            }else{
                                player.sendMessage(pluginPrefix + ChatColor.RED + "You forgot to send a message!");
                                return true;
                            }
                        }
                    }
                    player.sendMessage(pluginPrefix + ChatColor.RED + "Could not find the specified player!");
                }
            }
        }else{
            sender.sendMessage("This command is only for players!");
        }
        return true;
    }
}
