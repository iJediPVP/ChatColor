package me.i_Jedi.ChatColor.Commands;

import me.i_Jedi.ChatColor.Main;
import me.i_Jedi.ChatColor.PlayerInfo;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Com_R implements CommandExecutor {

    //Class vars
    private JavaPlugin plugin;
    private static HashMap<Player, Player> lastMsgHM = Main.lastMsgHM;

    //Constructor
    public Com_R(JavaPlugin jp){
        plugin = jp;
    }

    //Command
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        //Check for player
        if(sender instanceof Player){
            //Store player and command
            Player player = (Player) sender;
            String command = cmd.getName().toUpperCase();

            //Check command
            if(command.equals("R")){
                try{
                    //Check if the player has a "last messaged"
                    if(!lastMsgHM.get(player).equals(null) && lastMsgHM.get(player) instanceof Player){
                        //Store the player
                        Player rPlayer = lastMsgHM.get(player);
                        lastMsgHM.remove(player);
                        lastMsgHM.put(player, rPlayer);

                        //Check for message
                        String message = null;
                        if(args.length < 1){
                            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[iChatColor] " + ChatColor.GOLD + "You forgot to put a message to send!");
                            return true;
                        }else{
                            for(int x = 0 ; x < args.length; x++){
                                if(x == 0){
                                    message = args[x];
                                }else{
                                    message = message + " " + args[x];
                                }
                            }
                            //Check if player has a color or style
                            PlayerInfo pInfo = new PlayerInfo(plugin, player);
                            ChatColor pCColor = pInfo.getChatColor();
                            ChatColor pCStyle = pInfo.getChatStyle();
                            if(pCStyle.equals(ChatColor.RESET)){
                                message = pCColor + message;
                            }else{
                                message = pCColor + "" + pCStyle + message;
                            }
                            //Send message
                            try{
                                player.sendMessage(ChatColor.GOLD + "[You] to " + rPlayer.getPlayerListName() + ChatColor.GOLD + ": " + message);
                                rPlayer.sendMessage(ChatColor.GOLD + "[" + player.getPlayerListName() + ChatColor.GOLD + "] to You: " + message);
                            }catch(NullPointerException npe){
                                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[iChatColor]" + ChatColor.GOLD + " This player is not online!");
                            }
                        }

                    }else{//Else tell them they haven't messaged anyone yet
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[iChatColor] " +ChatColor.GOLD + "You haven't messaged anyone yet!" );
                    }
                }catch (NullPointerException npe){
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[iChatColor] " +ChatColor.GOLD + "You haven't messaged anyone yet!" );
                }
            }
        }
        return true;
    }
}
