package me.ijedi.chatcolor.Listeners;

import me.ijedi.chatcolor.Chat.ChatManager;
import me.ijedi.chatcolor.Chat.ChatPlayer;
import me.ijedi.chatcolor.Chat.Colors;
import me.ijedi.chatcolor.Menus.*;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class InvClickEvent implements Listener {

    //Variables
    private JavaPlugin plugin;
    private String mainTitle, nameColorTitle, nameStyleTitle, chatColorTitle, chatStyleTitle;
    private String pluginPrefix = ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] ";

    //Constructor
    public InvClickEvent(JavaPlugin plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        //Get titles
        mainTitle = new MainMenu().getName();
        nameColorTitle = new NameColorMenu().getName();
        nameStyleTitle = new NameStyleMenu().getName();
        chatColorTitle = new ChatColorMenu().getName();
        chatStyleTitle = new ChatStyleMenu().getName();
    }

    //Event
    @EventHandler
    public void invClick(InventoryClickEvent event){
        //Check for player
        if(event.getWhoClicked() instanceof Player){
            //Store player and get their ChatPlayer
            Player player = (Player) event.getWhoClicked();
            ChatPlayer cp = new ChatPlayer(player, plugin);

            //Try to get itemName
            String itemName;
            try{
                ItemStack item = event.getCurrentItem();
                if(item.hasItemMeta()){
                    itemName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
                }else{
                    return;
                }
            }catch(NullPointerException npe){
                return;
            }

            //Check inventory name
            String invName = ChatColor.stripColor(event.getInventory().getName());
            //********** MAIN **********
            if(invName.equalsIgnoreCase(mainTitle)){

                //Check itemName
                if(itemName.equalsIgnoreCase("Name Color")){
                    //Open NameColorMenu
                    player.openInventory(new NameColorMenu().getInventory(player));
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 7, 1);

                }else if(itemName.equalsIgnoreCase("Chat Color")){
                    //Open ChatColorMenu
                    player.openInventory(new ChatColorMenu().getInventory());
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 7, 1);

                }else if(itemName.equalsIgnoreCase("Exit")){
                    //Close
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.CHEST_CLOSE, 7, 1);
                }

                //Cancel & return
                event.setCancelled(true);
                return;

            //********** NAME COLOR **********
            }else if(invName.equalsIgnoreCase(nameColorTitle)){

                //Check itemName
                if(itemName.equalsIgnoreCase("Back")){
                    //Return to MainMenu
                    player.openInventory(new MainMenu().getInventory());
                    player.playSound(player.getLocation(), Sound.DIG_STONE, 7, 1);

                }else if(itemName.equalsIgnoreCase("Exit")){
                    //Close
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.CHEST_CLOSE, 7, 1);

                }else if(itemName.equalsIgnoreCase("Edit Style")){
                    //Open NameStyleMenu
                    player.openInventory(new NameStyleMenu().getInventory(player));
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 7, 1);

                }else{ //Set name color
                    cp.setNameColor(itemName);
                    updateChatManager(player, cp);
                    if(itemName.equalsIgnoreCase("Reset")){
                        player.playSound(player.getLocation(), Sound.SPLASH, 7, 1);
                        player.sendMessage(pluginPrefix + ChatColor.GOLD + "Your name color has been reset.");
                    }else{
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 7, 1);
                        player.sendMessage(pluginPrefix + ChatColor.GOLD + "Your name color has been set to: " + new Colors().toColor(itemName) + itemName);
                    }
                }

                //Cancel & return
                event.setCancelled(true);
                return;

            //********** NAME STYLE **********
            }else if(invName.equalsIgnoreCase(nameStyleTitle)){

                //Check itemName
                if(itemName.equalsIgnoreCase("Back")){
                    //Return to NameColorMenu
                    player.openInventory(new NameColorMenu().getInventory(player));
                    player.playSound(player.getLocation(), Sound.DIG_STONE, 7, 1);

                }else if(itemName.equalsIgnoreCase("Exit")){
                    //Close
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.CHEST_CLOSE, 7, 1);

                }else{ //Set name style
                    if(itemName.equalsIgnoreCase("Reset")){
                        cp.setNameStyle(itemName);
                        updateChatManager(player, cp);
                        player.playSound(player.getLocation(), Sound.SPLASH2, 7, 1);
                        player.sendMessage(pluginPrefix + ChatColor.GOLD + "Your name style has been reset.");

                    }else if(itemName.equalsIgnoreCase("Magic")){
                        //Check for permissions
                        if(player.hasPermission("chatColor.style.magic")){
                            cp.setNameStyle(itemName);
                            updateChatManager(player, cp);
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 7, 1);
                            player.sendMessage(pluginPrefix + ChatColor.GOLD + "Your name style has been set to: " + ChatColor.GREEN + "" + new Colors().toColor(itemName) + itemName);
                        }else{
                            player.sendMessage(pluginPrefix + ChatColor.RED + "You do not have permission to use this style.");
                        }

                    }else{
                        cp.setNameStyle(itemName);
                        updateChatManager(player, cp);
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 7, 1);
                        player.sendMessage(pluginPrefix + ChatColor.GOLD + "Your name style has been set to: " + ChatColor.GREEN + "" + new Colors().toColor(itemName) + itemName);
                    }
                }

                //Cancel & return
                event.setCancelled(true);
                return;

            //********** CHAT COLOR **********
            }else if(invName.equalsIgnoreCase(chatColorTitle)){

                //Check itemName
                if(itemName.equalsIgnoreCase("Back")) {
                    //Return to MainMenu
                    player.openInventory(new MainMenu().getInventory());
                    player.playSound(player.getLocation(), Sound.DIG_STONE, 7, 1);

                }else if(itemName.equalsIgnoreCase("Exit")) {
                    //Close
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.CHEST_CLOSE, 7, 1);

                }else if(itemName.equalsIgnoreCase("Edit Style")){
                    //Open ChatStyleMenu
                    player.openInventory(new ChatStyleMenu().getInventory());
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 7, 1);

                }else{ //Set chat color
                    cp.setChatColor(itemName);
                    updateChatManager(player, cp);
                    if(itemName.equalsIgnoreCase("Reset")){
                        player.playSound(player.getLocation(), Sound.SPLASH2, 7, 1);
                        player.sendMessage(pluginPrefix + ChatColor.GOLD + "Your chat color has been reset.");
                    }else{
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 7, 1);
                        player.sendMessage(pluginPrefix + ChatColor.GOLD + "Your chat color has been set to: " + new Colors().toColor(itemName) + itemName);
                    }
                }

                //Cancel & return
                event.setCancelled(true);
                return;

            //********** CHAT STYLE **********
            }else if(invName.equalsIgnoreCase(chatStyleTitle)){

                //Check itemName
                if(itemName.equalsIgnoreCase("Back")) {
                    //Return to ChatColorMenu
                    player.openInventory(new ChatColorMenu().getInventory());
                    player.playSound(player.getLocation(), Sound.DIG_STONE, 7, 1);

                }else if(itemName.equalsIgnoreCase("Exit")){
                    //Close
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.CHEST_CLOSE, 7, 1);

                }else{ //Set chat style
                    if(itemName.equalsIgnoreCase("Reset")){
                        cp.setChatStyle(itemName);
                        updateChatManager(player, cp);
                        player.playSound(player.getLocation(), Sound.SPLASH2, 7, 1);
                        player.sendMessage(pluginPrefix + ChatColor.GOLD + "Your chat style has been reset.");

                    }else if(itemName.equalsIgnoreCase("Magic")){
                        //Check for perms
                        if(player.hasPermission("chatColor.style.magic")){
                            cp.setChatStyle(itemName);
                            updateChatManager(player, cp);
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 7, 1);
                            player.sendMessage(pluginPrefix + ChatColor.GOLD + "Your chat style has been set to: " + ChatColor.GREEN + "" + new Colors().toColor(itemName) + itemName);
                        }else{
                            player.sendMessage(pluginPrefix + ChatColor.RED + "You do not have permission to use this style.");
                        }

                    }else{
                        cp.setChatStyle(itemName);
                        updateChatManager(player, cp);
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 7, 1);
                        player.sendMessage(pluginPrefix + ChatColor.GOLD + "Your chat style has been set to: " + ChatColor.GREEN + "" + new Colors().toColor(itemName) + itemName);
                    }
                }

                //Cancel & return
                event.setCancelled(true);
                return;
            }
        }
    }

    //Update ChatManager
    public void updateChatManager(Player player, ChatPlayer chatPlayer){
        new ChatManager().addChatPlayer(player, chatPlayer);
    }
}
