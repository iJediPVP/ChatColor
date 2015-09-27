package me.i_Jedi.ChatColor.Listeners;

import me.i_Jedi.ChatColor.Chat.ChatColors;
import me.i_Jedi.ChatColor.Chat.ChatInventory;
import me.i_Jedi.ChatColor.PlayerInfo;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class InvClickEvent implements Listener {

    //Class vars
    private JavaPlugin plugin;

    //Constructor
    public InvClickEvent(JavaPlugin jp){
        plugin = jp;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //Event
    @EventHandler
    public void invClickEvent(InventoryClickEvent event){
        //Store inventory, itemname and player
        Inventory inv = event.getInventory();
        String itemName = null;
        try{
            itemName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
        }catch(NullPointerException npe){
            return;
        }
        Player player = (Player) event.getWhoClicked();

        //See which inventory
        ChatInventory invInfo = new ChatInventory();
        //***** Main *****
        if(inv.getName().equals(invInfo.getMainColorInvName())){
            //See which item
            //Change name
            if(itemName.equals("Edit Name Color")){
                player.openInventory(invInfo.getColorSelInv(true));
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 7, 1);
            //Change chat
            }else if(itemName.equals("Edit Chat Color")){
                player.openInventory(invInfo.getColorSelInv(false));
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 7, 1);
            //Exit
            }else if(itemName.equals("Exit")){
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.CHEST_CLOSE, 7, 1);
            }
            event.setCancelled(true);

        //***** Color Selection *****
        }else if(inv.getName().equals(invInfo.getColorSelInvName())){
            ChatColors cInfo = new ChatColors();
            List<String> cList = cInfo.getColorList();
            //See which item
            //Color item
            if(cList.contains(itemName)){
                //See which thing to change
                String changeType = event.getCurrentItem().getItemMeta().getLore().get(2);
                if(changeType.equals("name")){
                    //Change player name color
                    PlayerInfo pInfo = new PlayerInfo(plugin, player);
                    pInfo.setNameColor(cInfo.toColor(itemName));
                    player.playSound(player.getLocation(), Sound.ANVIL_USE, 7, 1);
                }else{
                    //Change player chat color
                    PlayerInfo pInfo = new PlayerInfo(plugin, player);
                    pInfo.setChatColor(cInfo.toColor(itemName));
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 7, 1);
                }

            //Reset
            }else if(itemName.equals("RESET")){
                //See which thing to reset
                String changeType = event.getCurrentItem().getItemMeta().getLore().get(2);
                if(changeType.equals("name")){
                    //Reset player name color
                    //Change player name color
                    PlayerInfo pInfo = new PlayerInfo(plugin, player);
                    pInfo.setNameColor(cInfo.toColor(itemName));
                    player.playSound(player.getLocation(), Sound.SPLASH, 7, 2);
                }else{
                    //Reset player chat color
                    PlayerInfo pInfo = new PlayerInfo(plugin, player);
                    pInfo.setChatColor(cInfo.toColor(itemName));
                    player.playSound(player.getLocation(), Sound.SPLASH2, 7, 2);
                }

            //Style
            }else if(itemName.equals("Edit Style")){
                String changeType = event.getCurrentItem().getItemMeta().getLore().get(3);
                if(changeType.equals("name")){
                    player.openInventory(invInfo.getStyleSelInv(true));
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 7, 1);
                }else{
                    player.openInventory(invInfo.getStyleSelInv(false));
                }

            //Back
            }else if(itemName.equals("BACK")){
                player.openInventory(invInfo.getMainColorInv());
                player.playSound(player.getLocation(), Sound.DIG_STONE, 7, 1);
            }
            event.setCancelled(true);

        //***** Style Selection *****
        }else if(inv.getName().equals(invInfo.getStyleSelInvName())){
            ChatColors cc = new ChatColors();
            List<String> sList = cc.getStyleList();
            //See which item
            //Style item
            if(sList.contains(itemName)){
                //See which thing to change
                String changeType = event.getCurrentItem().getItemMeta().getLore().get(2);
                if(changeType.equals("name")){
                    PlayerInfo pInfo = new PlayerInfo(plugin, player);
                    pInfo.setNameStyle(cc.toStyle(itemName));
                    player.playSound(player.getLocation(), Sound.ANVIL_USE, 7, 1);
                }else{
                    PlayerInfo pInfo = new PlayerInfo(plugin, player);
                    pInfo.setChatStyle(cc.toStyle(itemName));
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 7, 1);
                }

                //Reset
            }else if(itemName.equals("RESET")){
                PlayerInfo pInfo = new PlayerInfo(plugin, player);
                String changeType = inv.getItem(11).getItemMeta().getLore().get(2);
                if(changeType.equals("name")){
                    pInfo.setNameStyle(cc.toColor(itemName));
                    player.playSound(player.getLocation(), Sound.SPLASH, 7, 2);
                }else{
                    pInfo.setChatStyle(cc.toColor(itemName));
                    player.playSound(player.getLocation(), Sound.SPLASH2, 7, 2);
                }
                //Back
            }else if(itemName.equals("BACK")){
                String changeType = inv.getItem(11).getItemMeta().getLore().get(2);
                if(changeType.equals("name")){
                    player.openInventory(invInfo.getColorSelInv(true));
                }else{
                    player.openInventory(invInfo.getColorSelInv(false));
                }
                player.playSound(player.getLocation(), Sound.DIG_STONE, 7, 1);
            }
            event.setCancelled(true);
        }
    }
}
