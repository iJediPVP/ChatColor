package me.i_Jedi.ChatColor.Chat;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class ChatColors {

    //Convert string to color
    public ChatColor toColor(String colorName){
        switch (colorName){
            case "AQUA":
                return ChatColor.AQUA;
            case "BLACK":
                return ChatColor.BLACK;
            case "BLUE":
                return ChatColor.BLUE;
            case "DARK_AQUA":
                return ChatColor.DARK_AQUA;
            case "DARK_BLUE":
                return ChatColor.DARK_BLUE;
            case "DARK_GRAY":
                return ChatColor.DARK_GRAY;
            case "DARK_GREEN":
                return ChatColor.DARK_GREEN;
            case "DARK_PURPLE":
                return ChatColor.DARK_PURPLE;
            case "DARK_RED":
                return ChatColor.DARK_RED;
            case "GOLD":
                return ChatColor.GOLD;
            case "GRAY":
                return ChatColor.GRAY;
            case "GREEN":
                return ChatColor.GREEN;
            case "LIGHT_PURPLE":
                return ChatColor.LIGHT_PURPLE;
            case "RED":
                return ChatColor.RED;
            case "WHITE":
                return ChatColor.WHITE;
            case "YELLOW":
                return ChatColor.YELLOW;

            //STYLES
            case "BOLD":
                return ChatColor.BOLD;
            case "ITALIC":
                return ChatColor.ITALIC;
            case "UNDERLINE":
                return ChatColor.UNDERLINE;
            case "STRIKETHROUGH":
                return ChatColor.STRIKETHROUGH;
            default:
                return ChatColor.RESET;
        }
    }

    //Convert color to string
    public String toString(ChatColor color){
        switch (color){
            //COLORS
            case AQUA:
                return "AQUA";
            case BLACK:
                return "BLACK";
            case BLUE:
                return "BLUE";
            case DARK_AQUA:
                return "DARK_AQUA";
            case DARK_BLUE:
                return "DARK_BLUE";
            case DARK_GRAY:
                return "DARK_GRAY";
            case DARK_GREEN:
                return "DARK_GREEN";
            case DARK_PURPLE:
                return "DARK_PURPLE";
            case DARK_RED:
                return "DARK_RED";
            case GOLD:
                return "GOLD";
            case GRAY:
                return "GRAY";
            case GREEN:
                return "GREEN";
            case LIGHT_PURPLE:
                return "LIGHT_PURPLE";
            case RED:
                return "RED";
            case WHITE:
                return "WHITE";
            case YELLOW:
                return "YELLOW";

            //STYLES
            case BOLD:
                return "BOLD";
            case ITALIC:
                return "ITALIC";
            case UNDERLINE:
                return "UNDERLINE";
            case STRIKETHROUGH:
                return "STRIKETHROUGH";
            default:
                return "RESET";
        }
    }

    //Convert string to style
    public ChatColor toStyle(String styleName){
        switch(styleName){
            case "BOLD":
                return ChatColor.BOLD;
            case "ITALIC":
                return ChatColor.ITALIC;
            case "UNDERLINE":
                return ChatColor.UNDERLINE;
            case "STRIKETHROUGH":
                return ChatColor.STRIKETHROUGH;
            default:
                return ChatColor.RESET;
        }
    }

    //Get color list
    public List<String> getColorList(){
        @SuppressWarnings("serial")
        List<String> colorList = new ArrayList<String>(){{
            add("RED");
            add("BLUE");
            add("GREEN");
            add("AQUA");
            add("GRAY");
            add("LIGHT_PURPLE");
            add("GOLD");
            add("DARK_RED");
            add("DARK_BLUE");
            add("DARK_GREEN");
            add("DARK_AQUA");
            add("DARK_GRAY");
            add("DARK_PURPLE");
            add("YELLOW");
            add("BLACK");
            add("WHITE");
        }};
        return colorList;
    }

    //Get style list
    public List<String> getStyleList(){
        @SuppressWarnings("serial")
        List<String> styleList = new ArrayList<String>(){{
            add("BOLD");
            add("ITALIC");
            add("UNDERLINE");
            add("STRIKETHROUGH");
        }};
        return styleList;
    }
}
