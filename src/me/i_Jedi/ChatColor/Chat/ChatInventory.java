package me.i_Jedi.ChatColor.Chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ChatInventory {
    private Inventory mainColorInv = Bukkit.createInventory(null, 36, "Chat Color Menu");
    private Inventory colorSelInv = Bukkit.createInventory(null, 54, "Select Color");
    private Inventory styleSelInv = Bukkit.createInventory(null, 36, "Select Style");


    //Get color selection inv
    public Inventory getColorSelInv(boolean isName){

        //Determine lore and reset strings
        String loreStr = null;
        String loreStr2 = null;
        String resetLore = null;
        if(isName){
            loreStr = "Change your name to this color.";
            loreStr2 = "name";
            resetLore = ChatColor.GOLD + "Remove the color of your name.";
        }else{
            loreStr = "Change your chat to this color.";
            loreStr2 = "chat";
            resetLore = ChatColor.GOLD + "Remove the color of your chat.";
        }

        //First row
        colorSelInv.setItem(10, getItem(Material.WOOL, (short) 14, ChatColor.RED, loreStr, loreStr2));
        colorSelInv.setItem(11, getItem(Material.WOOL, (short) 11, ChatColor.BLUE, loreStr, loreStr2));
        colorSelInv.setItem(12, getItem(Material.WOOL, (short) 13, ChatColor.GREEN, loreStr, loreStr2));
        colorSelInv.setItem(13, getItem(Material.WOOL, (short) 9, ChatColor.AQUA, loreStr, loreStr2));
        colorSelInv.setItem(14, getItem(Material.WOOL, (short) 7, ChatColor.GRAY, loreStr, loreStr2));
        colorSelInv.setItem(15, getItem(Material.WOOL, (short) 2, ChatColor.LIGHT_PURPLE, loreStr, loreStr2));
        colorSelInv.setItem(16, getItem(Material.GOLD_BLOCK, (short) 0, ChatColor.GOLD, loreStr, loreStr2));

        //Second row
        colorSelInv.setItem(19, getItem(Material.CARPET, (short) 14, ChatColor.DARK_RED, loreStr, loreStr2));
        colorSelInv.setItem(20, getItem(Material.CARPET, (short) 11, ChatColor.DARK_BLUE, loreStr, loreStr2));
        colorSelInv.setItem(21, getItem(Material.CARPET, (short) 13, ChatColor.DARK_GREEN, loreStr, loreStr2));
        colorSelInv.setItem(22, getItem(Material.CARPET, (short) 9, ChatColor.DARK_AQUA, loreStr, loreStr2));
        colorSelInv.setItem(23, getItem(Material.CARPET, (short) 7, ChatColor.DARK_GRAY, loreStr, loreStr2));
        colorSelInv.setItem(24, getItem(Material.CARPET, (short) 10, ChatColor.DARK_PURPLE, loreStr, loreStr2));
        colorSelInv.setItem(25, getItem(Material.CARPET, (short) 4, ChatColor.YELLOW, loreStr, loreStr2));

        //Third row
        colorSelInv.setItem(30, getItem(Material.WOOL, (short) 15, ChatColor.BLACK, loreStr, loreStr2));
        colorSelInv.setItem(31, getItem(Material.WOOL, (short) 0, ChatColor.WHITE, loreStr, loreStr2));
        ItemStack iStack = new ItemStack(Material.GLASS, 1);
        ItemMeta iMeta = iStack.getItemMeta();
        iMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "RESET");
        iMeta.setLore(Arrays.asList(ChatColor.GOLD + resetLore, "Change Type:", loreStr2));
        iStack.setItemMeta(iMeta);
        colorSelInv.setItem(32, iStack);

        //Forth row
        iStack = new ItemStack(Material.ARROW, 1);
        iMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "BACK");
        iMeta.setLore(Arrays.asList(ChatColor.GOLD + "Return to the main menu."));
        iStack.setItemMeta(iMeta);
        colorSelInv.setItem(36, iStack);

        iStack = new ItemStack(Material.FEATHER, 1);
        iMeta.setDisplayName(ChatColor.GOLD + "" + "" + ChatColor.BOLD + "Edit Style");
        iMeta.setLore(Arrays.asList(ChatColor.GOLD + "Change the styling of your " + loreStr2 + ".", ChatColor.GREEN + "Style List: " +
                        ChatColor.GREEN + "" + ChatColor.BOLD + "BOLD" + ChatColor.GOLD + ", " +
                        ChatColor.GREEN + "" + ChatColor.ITALIC + "ITALIC" + ChatColor.GOLD + ", " +
                        ChatColor.GREEN + "" + ChatColor.UNDERLINE + "UNDERLINE" + ChatColor.GOLD + ", " +
                        ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "STRIKETHROUGH" + ChatColor.GOLD + ".",
                "Change Type:", loreStr2));
        iStack.setItemMeta(iMeta);
        colorSelInv.setItem(44, iStack);

        //Return inv
        return colorSelInv;
    }

    //Get colorSelInv name
    public String getColorSelInvName(){
        return colorSelInv.getName();
    }

    //Get main inv
    public Inventory getMainColorInv(){
        //Name
        ItemStack iStack = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta iMeta = iStack.getItemMeta();
        iMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Edit Name Color");
        iMeta.setLore(Arrays.asList(ChatColor.AQUA + "Change the color of your name."));
        iStack.setItemMeta(iMeta);
        mainColorInv.setItem(12, iStack);

        //Chat
        iStack = new ItemStack(Material.BOOK, 1);
        iMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Edit Chat Color");
        iMeta.setLore(Arrays.asList(ChatColor.GREEN + "Change the color of your chat."));
        iStack.setItemMeta(iMeta);
        mainColorInv.setItem(14, iStack);

        //Exit
        iStack = new ItemStack(Material.ARROW, 1);
        iMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Exit");
        iMeta.setLore(Arrays.asList(ChatColor.GOLD + "Close this menu."));
        iStack.setItemMeta(iMeta);
        mainColorInv.setItem(22, iStack);
        return mainColorInv;
    }

    //Get main inv name
    public String getMainColorInvName(){
        return mainColorInv.getName();
    }

    //Get styleSelInv
    public Inventory getStyleSelInv(boolean isName){
        //Determine lore and reset strings
        String loreStr = null;
        String loreStr2 = null;
        String resetLore = null;
        if(isName){
            loreStr = "Change your name styling to ";
            loreStr2 = "name";
            resetLore = ChatColor.GOLD + "Remove the styling from your name.";
        }else{
            loreStr = "Change your chat styling to ";
            loreStr2 = "chat";
            resetLore = ChatColor.GOLD + "Remove the styling from your chat.";
        }

        //BOLD
        ChatColors cc = new ChatColors();
        ItemStack iStack = new ItemStack(Material.OBSIDIAN, 1);
        ItemMeta iMeta = iStack.getItemMeta();
        iMeta.setDisplayName(ChatColor.GOLD + "" + cc.toStyle("BOLD") + "BOLD");
        iMeta.setLore(Arrays.asList(ChatColor.GOLD + "" + cc.toStyle("BOLD") + loreStr, "Change Type:", loreStr2));
        iStack.setItemMeta(iMeta);
        styleSelInv.setItem(11, iStack);

        //ITALIC
        iStack = new ItemStack(Material.FENCE, 1);
        iMeta = iStack.getItemMeta();
        iMeta.setDisplayName(ChatColor.GOLD + "" + cc.toStyle("ITALIC") + "ITALIC");
        iMeta.setLore(Arrays.asList(ChatColor.GOLD + "" + cc.toStyle("ITALIC") + loreStr, "Change Type:", loreStr2));
        iStack.setItemMeta(iMeta);
        styleSelInv.setItem(12, iStack);

        //UNDERLINE
        iStack = new ItemStack(Material.STONE_PLATE, 1);
        iMeta = iStack.getItemMeta();
        iMeta.setDisplayName(ChatColor.GOLD + "" + cc.toStyle("UNDERLINE") + "UNDERLINE");
        iMeta.setLore(Arrays.asList(ChatColor.GOLD + "" + cc.toStyle("UNDERLINE") + loreStr, "Change Type:", loreStr2));
        iStack.setItemMeta(iMeta);
        styleSelInv.setItem(13, iStack);

        //STRIKETHROUGH
        iStack = new ItemStack(Material.STRING, 1);
        iMeta = iStack.getItemMeta();
        iMeta.setDisplayName(ChatColor.GOLD + "" + cc.toStyle("STRIKETHROUGH") + "STRIKETHROUGH");
        iMeta.setLore(Arrays.asList(ChatColor.GOLD + "" + cc.toStyle("STRIKETHROUGH") + loreStr, "Change Type:", loreStr2));
        iStack.setItemMeta(iMeta);
        styleSelInv.setItem(14, iStack);

        //RESET
        iStack = new ItemStack(Material.GLASS, 1);
        iMeta = iStack.getItemMeta();
        iMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "RESET");
        iMeta.setLore(Arrays.asList(ChatColor.GOLD + resetLore, "Change Type:", loreStr2));
        iStack.setItemMeta(iMeta);
        styleSelInv.setItem(15, iStack);

        //BACK
        iStack = new ItemStack(Material.ARROW, 1);
        iMeta = iStack.getItemMeta();
        iMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "BACK");
        iMeta.setLore(Arrays.asList(ChatColor.GOLD + "Return to the main menu."));
        iStack.setItemMeta(iMeta);
        styleSelInv.setItem(22, iStack);

        return styleSelInv;
    }

    //Get styleSelInv name
    public String getStyleSelInvName(){
        return styleSelInv.getName();
    }

    //Create and get item
    public ItemStack getItem(Material mat, short id, ChatColor color, String lore, String lore2){
        ChatColors cc = new ChatColors();
        ItemStack iStack = new ItemStack(mat, 1, id);
        ItemMeta iMeta = iStack.getItemMeta();
        String colorStr = cc.toString(color);
        iMeta.setDisplayName(color + "" + ChatColor.BOLD + colorStr);
        iMeta.setLore(Arrays.asList(color + lore, "Change Type:", lore2));
        iStack.setItemMeta(iMeta);
        return iStack;
    }
}
