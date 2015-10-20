package me.ijedi.chatcolor.Menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.Collections;

public class ChatColorMenu {

    //Variables
    private Inventory chatColorInv = Bukkit.createInventory(null, 54, "Chat Color");

    //Get inventory
    public Inventory getInventory(){
        CreateItem ci = new CreateItem();
        //First - EMPTY
        //Second
        chatColorInv.setItem(10, ci.createItem(Material.STAINED_GLASS_PANE, (short) 14, "$c$lRed",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $cYour chat here.")), false));
        chatColorInv.setItem(11, ci.createItem(Material.STAINED_GLASS_PANE, (short) 11, "$9$lBlue",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $9Your chat here.")), false));
        chatColorInv.setItem(12, ci.createItem(Material.STAINED_GLASS_PANE, (short) 13, "$a$lGreen",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $aYour chat here.")), false));
        chatColorInv.setItem(13, ci.createItem(Material.STAINED_GLASS_PANE, (short) 9, "$b$lAqua",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $bYour chat here.")), false));
        chatColorInv.setItem(14, ci.createItem(Material.STAINED_GLASS_PANE, (short) 7, "$7$lGray",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $7Your chat here.")), false));
        chatColorInv.setItem(15, ci.createItem(Material.STAINED_GLASS_PANE, (short) 2, "$d$lLight Purple",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $dYour chat here.")), false));
        chatColorInv.setItem(16, ci.createItem(Material.STAINED_GLASS_PANE, (short) 4, "$6$lGold",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $6Your chat here.")), false));

        //Third
        chatColorInv.setItem(19, ci.createItem(Material.WOOL, (short) 14, "$4$lDark Red",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $4Your chat here.")), false));
        chatColorInv.setItem(20, ci.createItem(Material.WOOL, (short) 11, "$1$lDark Blue",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $1Your chat here.")), false));
        chatColorInv.setItem(21, ci.createItem(Material.WOOL, (short) 13, "$2$lDark Green",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $2Your chat here.")), false));
        chatColorInv.setItem(22, ci.createItem(Material.WOOL, (short) 9, "$3$lDark Aqua",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $3Your chat here.")), false));
        chatColorInv.setItem(23, ci.createItem(Material.WOOL, (short) 7, "$8$lDark Gray",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $8Your chat here.")), false));
        chatColorInv.setItem(24, ci.createItem(Material.WOOL, (short) 10, "$5$lDark Purple",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $5Your chat here.")), false));
        chatColorInv.setItem(25, ci.createItem(Material.WOOL, (short) 4, "$e$lYellow",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $eYour chat here.")), false));

        //Fourth
        chatColorInv.setItem(30, ci.createItem(Material.STAINED_GLASS_PANE, (short) 15, "$0$lBlack",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $0Your chat here.")), false));
        chatColorInv.setItem(31, ci.createItem(Material.STAINED_GLASS_PANE, (short) 0, "$f$lWhite",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $fYour chat here.")), false));
        chatColorInv.setItem(32, ci.createItem(Material.GLASS, (short) 0, "$f$lReset",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Reset the color of your name.")), false));

        //Fifth - EMPTY
        //Sixth
        chatColorInv.setItem(45, ci.createItem(Material.SPRUCE_DOOR_ITEM, (short) 0, "$6$lBack",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Return to the Main menu.")), false));
        chatColorInv.setItem(49, ci.createItem(Material.BARRIER, (short) 0, "$4$lExit",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Close this menu.")), false));
        chatColorInv.setItem(53, ci.createItem(Material.FEATHER, (short) 0, "$6$oEdit Style",
                Arrays.asList(ChatColor.translateAlternateColorCodes('$', "$6Change the style of your name."),
                        ChatColor.translateAlternateColorCodes('$', "$a$lBold$6, $a$oItalic$6,"),
                        ChatColor.translateAlternateColorCodes('$', "$a$mStrikethrough$6, $a$nUnderline$6,"),
                        ChatColor.translateAlternateColorCodes('$', "$6or $aMagic$6: $a$kMagic")), false));

        return chatColorInv;
    }

    //Get name
    public String getName(){
        return chatColorInv.getName();
    }
}
