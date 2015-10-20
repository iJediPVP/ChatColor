package me.ijedi.chatcolor.Menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.Collections;

public class MainMenu {

    //Variables
    private Inventory mainMenu = Bukkit.createInventory(null, 27, "ChatColor Menu");

    //Get inventory
    public Inventory getInventory(){
        CreateItem ci = new CreateItem();

        //First - EMPTY
        //Second
        mainMenu.setItem(12, ci.createItem(Material.NAME_TAG, (short) 0, ChatColor.translateAlternateColorCodes('$', "$3$lName Color"),
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Edit the color and style of your name.")), false));
        mainMenu.setItem(14, ci.createItem(Material.BOOK_AND_QUILL, (short) 0, ChatColor.translateAlternateColorCodes('$', "$a$lChat Color"),
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Edit the color and style of your chat.")), false));

        //Third
        mainMenu.setItem(22, ci.createItem(Material.BARRIER, (short) 0, ChatColor.translateAlternateColorCodes('$', "$4$lExit"),
                Collections.singletonList(ChatColor.translateAlternateColorCodes('$', "$6Close this menu.")), false));

        return mainMenu;
    }

    //Get name
    public String getName(){
        return mainMenu.getName();
    }
}
