package me.ijedi.chatcolor.Menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.Collections;

public class ChatStyleMenu {

    //Variables
    private Inventory chatStyleInv = Bukkit.createInventory(null, 36, "Chat Style");

    //Get inventory
    public Inventory getInventory(){
        CreateItem ci = new CreateItem();

        //First - EMPTY
        //Second
        chatStyleInv.setItem(11, ci.createItem(Material.OBSIDIAN, (short) 0, ChatColor.translateAlternateColorCodes('$', "$a$lBold"),
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $a$lYour chat here.")), false));
        chatStyleInv.setItem(12, ci.createItem(Material.STRING, (short) 0, ChatColor.translateAlternateColorCodes('$', "$a$oItalic"),
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $a$oYour chat here.")), false));
        chatStyleInv.setItem(13, ci.createItem(Material.WOOD_PLATE, (short) 0, ChatColor.translateAlternateColorCodes('$', "$a$nUnderline"),
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $a$nYour chat here.")), false));
        chatStyleInv.setItem(14, ci.createItem(Material.FENCE, (short) 0, ChatColor.translateAlternateColorCodes('$', "$a$mStrikethrough"),
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $a$mYour chat here.")), false));
        chatStyleInv.setItem(15, ci.createItem(Material.ENCHANTMENT_TABLE, (short) 0, ChatColor.translateAlternateColorCodes('$', "$aMagic"),
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $a$kYour chat here.")), false));

        //Third
        chatStyleInv.setItem(22, ci.createItem(Material.GLASS, (short) 0, "$f$lReset",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Reset the style of your chat.")), false));

        //Fourth
        chatStyleInv.setItem(30, ci.createItem(Material.SPRUCE_DOOR_ITEM, (short) 0, "$6$lBack",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Return to the Chat Color menu.")), false));
        chatStyleInv.setItem(32, ci.createItem(Material.BARRIER, (short) 0, "$4$lExit",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Close this menu.")), false));

        return chatStyleInv;
    }

    //Get name
    public String getName(){
        return chatStyleInv.getName();
    }
}
