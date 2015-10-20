package me.ijedi.chatcolor.Menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Collections;

public class NameStyleMenu {

    //Variables
    private Inventory nameStyleInv = Bukkit.createInventory(null, 36, "Name Style");

    //Get inventory
    public Inventory getInventory(Player player){
        CreateItem ci = new CreateItem();

        //First - EMPTY
        //Second
        nameStyleInv.setItem(11, ci.createItem(Material.OBSIDIAN, (short) 0, ChatColor.translateAlternateColorCodes('$', "$a$lBold"),
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $a$l" + player.getName())), false));
        nameStyleInv.setItem(12, ci.createItem(Material.STRING, (short) 0, ChatColor.translateAlternateColorCodes('$', "$a$oItalic"),
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $a$o" + player.getName())), false));
        nameStyleInv.setItem(13, ci.createItem(Material.WOOD_PLATE, (short) 0, ChatColor.translateAlternateColorCodes('$', "$a$nUnderline"),
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $a$n" + player.getName())), false));
        nameStyleInv.setItem(14, ci.createItem(Material.FENCE, (short) 0, ChatColor.translateAlternateColorCodes('$', "$a$mStrikethrough"),
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $a$m" + player.getName())), false));
        nameStyleInv.setItem(15, ci.createItem(Material.ENCHANTMENT_TABLE, (short) 0, ChatColor.translateAlternateColorCodes('$', "$aMagic"),
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Example: $a$k" + player.getName())), false));

        //Third
        nameStyleInv.setItem(22, ci.createItem(Material.GLASS, (short) 0, "$f$lReset",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Reset the style of your name.")), false));

        //Fourth
        nameStyleInv.setItem(30, ci.createItem(Material.SPRUCE_DOOR_ITEM, (short) 0, "$6$lBack",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Return to the Name Color menu.")), false));
        nameStyleInv.setItem(32, ci.createItem(Material.BARRIER, (short) 0, "$4$lExit",
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Close this menu.")), false));

        return nameStyleInv;
    }

    //Get name
    public String getName(){
        return nameStyleInv.getName();
    }



}
