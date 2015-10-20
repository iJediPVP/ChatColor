package me.ijedi.chatcolor.Chat;

import org.bukkit.ChatColor;

import java.util.HashMap;

public class Colors {

    //Variables
    private HashMap<String, ChatColor> colorMap = new HashMap<String, ChatColor>(){{
        put("RED", ChatColor.RED);
        put("BLUE", ChatColor.BLUE);
        put("GREEN", ChatColor.GREEN);
        put("AQUA", ChatColor.AQUA);
        put("GRAY", ChatColor.GRAY);
        put("LIGHT PURPLE", ChatColor.LIGHT_PURPLE);
        put("GOLD", ChatColor.GOLD);
        put("DARK RED", ChatColor.DARK_RED);
        put("DARK BLUE", ChatColor.DARK_BLUE);
        put("DARK GREEN", ChatColor.DARK_GREEN);
        put("DARK AQUA", ChatColor.DARK_AQUA);
        put("DARK GRAY", ChatColor.DARK_GRAY);
        put("DARK PURPLE", ChatColor.DARK_PURPLE);
        put("YELLOW", ChatColor.YELLOW);
        put("BLACK", ChatColor.BLACK);
        put("WHITE", ChatColor.WHITE);
        put("BOLD", ChatColor.BOLD);
        put("ITALIC", ChatColor.ITALIC);
        put("UNDERLINE", ChatColor.UNDERLINE);
        put("STRIKETHROUGH", ChatColor.STRIKETHROUGH);
        put("MAGIC", ChatColor.MAGIC);
        put("CLEAR", ChatColor.RESET);
    }};

    //String to color
    public ChatColor toColor(String string){
        try{
            string = ChatColor.stripColor(string);
            string = string.toUpperCase();

            //If the map doesn't have it return RESET
            if(colorMap.containsKey(string)){
                return colorMap.get(string);
            }else{
                return colorMap.get("CLEAR");
            }
        }catch(NullPointerException npe){
            return colorMap.get("CLEAR");
        }

    }
}
