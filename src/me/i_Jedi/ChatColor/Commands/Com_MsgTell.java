package me.i_Jedi.ChatColor.Commands;

import me.i_Jedi.ChatColor.Main;
import me.i_Jedi.ChatColor.PlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Com_MsgTell implements CommandExecutor {

    //Class vars
    private static HashMap<Player, Player> lastMsgHM = Main.lastMsgHM;
    private JavaPlugin plugin;

    //Constructor
    public Com_MsgTell(JavaPlugin jp){
        plugin = jp;
    }

    //Command
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        //Check for player
        if(sender instanceof Player){
            //Store command and player
            Player player = (Player) sender;
            String command = cmd.getName().toUpperCase();

            //Check command
            if(command.equals("MSG") || command.equals("TELL")){
                //Check for args
                if(args.length > 0){
                    //Try and get receiving player from args[0]
                    Player rPlayer;
                    try{
                        rPlayer = Bukkit.getPlayer(args[0]);
                    }catch(NullPointerException npe){
                        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] " + ChatColor.GOLD + "Could not find specified player.");
                        return true;
                    }


                    //Make sure the player is not sending a message to themselves
                    if(player.equals(rPlayer)){
                        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] " + ChatColor.GOLD + "You can not send a message to yourself!");
                        return true;
                    }

                    //Make sure there is a message
                    if(args.length < 2){
                        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] " + ChatColor.GOLD + "You forgot to put a message to send!");

                        return true;
                    }else{
                        //Get message
                        String message = null;

                        for(int x = 1 ; x < args.length; x++){
                            if(x == 1){
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
                            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor]" + ChatColor.GOLD + " This player is not online!");
                        }

                        //Store last messaged stuff
                        lastMsgHM.remove(player);
                        lastMsgHM.put(player, rPlayer);
                        lastMsgHM.remove(rPlayer);
                        lastMsgHM.put(rPlayer, player);

                    }
                    //Give player a help message for /msg and /tell
                }else{
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] " +ChatColor.GOLD + "Error! Correct usage: " + ChatColor.GREEN + "" + ChatColor.BOLD + "/" + command.toLowerCase() + " <player> <message>");
                }
            }
        }
        return true;
    }
}
