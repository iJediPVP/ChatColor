package me.i_Jedi.ChatColor;

import me.i_Jedi.ChatColor.Chat.ChatColors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PlayerInfo {

    //Class vars
    private JavaPlugin plugin;
    private File file;
    private FileConfiguration config;
    private Player player;
    private boolean isNameTagEnabled = false;

    public static HashMap<Player, Integer> taskList = new HashMap<Player, Integer>();

    //Constructor
    public PlayerInfo(JavaPlugin jp, Player p){
        plugin = jp;
        file = new File(plugin.getDataFolder() + "/playerData/" + p.getUniqueId() + ".yml");
        config = YamlConfiguration.loadConfiguration(file);
        player = p;

        File cFile = new File(plugin.getDataFolder() + "/config.yml");
        FileConfiguration cConfig = YamlConfiguration.loadConfiguration(cFile);
        isNameTagEnabled = cConfig.getBoolean("nameTagEnabled");

    }

    //Save
    public void saveFile(){
        try{
            config.save(file);
        }catch(IOException ioe){
            plugin.getLogger().info("PlayerInfo - Error saving player file.");
            return;
        }
    }

    //Exists
    public boolean fileExists(){
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    //Set defaults
    public void setDefaults(){
        config.set("nameColor", "RESET");
        config.set("nameStyle", "RESET");
        config.set("chatColor", "RESET");
        config.set("chatStyle", "RESET");
        saveFile();
    }

    //********** CHAT **********

    //Set chat color
    public void setChatColor(ChatColor color){
        ChatColors cc = new ChatColors();
        String str;
        ChatColor style = getChatStyle();
        if(style.equals(ChatColor.RESET)){
            str = color + cc.toString(color);
        }else{
            str = color + "" + style + cc.toString(color);
        }

        //Check for reset color
        if(color.equals(ChatColor.RESET)){
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] " + ChatColor.GOLD + "Your chat color has been reset.");
        }else{
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] " + ChatColor.GOLD + "Your chat color has been changed to " + str + ChatColor.GOLD + ".");
        }
        config.set("chatColor", cc.toString(color));
        saveFile();
    }

    //Get chat color
    public ChatColor getChatColor(){
        String colorStr = config.getString("chatColor");
        ChatColors cc = new ChatColors();
        try{
            return cc.toColor(colorStr);
        }catch(NullPointerException npe){
            return ChatColor.RESET;
        }
    }

    //Set chat style
    public void setChatStyle(ChatColor style){
        ChatColors cc = new ChatColors();
        ChatColor color = getChatColor();

        //Check for reset style
        if(style.equals(ChatColor.RESET)){
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] " + ChatColor.GOLD + "Your chat style has been reset.");
        }else{
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] " + ChatColor.GOLD + "Your chat style has been changed to " + color + "" + style + cc.toString(style) + ChatColor.GOLD + ".");
        }

        config.set("chatStyle", cc.toString(style));
        saveFile();
    }

    //Get chat style
    public ChatColor getChatStyle(){
        String styleStr = config.getString("chatStyle");
        ChatColors cc = new ChatColors();
        try{
            return cc.toStyle(styleStr);
        }catch(NullPointerException npe){
            return ChatColor.RESET;
        }
    }

    //********** NAME **********

    //Set name color
    public void setNameColor(ChatColor color){
        ChatColors cc = new ChatColors();
        String pName;
        ChatColor style = getNameStyle();
        //Check for reset style
        if(style.equals(ChatColor.RESET)){
            pName = color + player.getName();
        }else{
            pName = color + "" + style + player.getName();
        }

        //Check for reset color
        if(color.equals(ChatColor.RESET)){
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] " + ChatColor.GOLD + "Your name color has been reset.");
        }else{
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] " + ChatColor.GOLD + "Your name has been changed to " + pName + ChatColor.GOLD + ".");
        }

        player.setPlayerListName(pName);
        config.set("nameColor", cc.toString(color));
        saveFile();

        //Team
        if(isNameTagEnabled){
            setTeam();
            for(PotionEffect pe : player.getActivePotionEffects()){
                if(pe.getType().equals(PotionEffectType.INVISIBILITY)){
                    int count = (int) Math.floor(pe.getDuration() / 20);
                    setTagVisibility(false, count);
                }
            }
        }


    }

    //Get name color
    public ChatColor getNameColor(){
        ChatColors cc = new ChatColors();
        try{
            return cc.toColor(config.getString("nameColor"));
        }catch(NullPointerException npe){
            return ChatColor.RESET;
        }
    }

    //Set name style
    public void setNameStyle(ChatColor style){
        ChatColors cc = new ChatColors();
        ChatColor color = getNameColor();

        //Check for reset style
        if(style.equals(ChatColor.RESET)){
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] " + ChatColor.GOLD + "Your name style has been reset.");
            player.setPlayerListName(color + player.getName());
        }else{
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[ChatColor] " + ChatColor.GOLD + "Your name has been changed to " + color + "" + style + player.getName() + ChatColor.GOLD + ".");
            player.setPlayerListName(color + "" + style + player.getName());
        }

        config.set("nameStyle", cc.toString(style));
        saveFile();

        //Team
        if(isNameTagEnabled){
            setTeam();
            for(PotionEffect pe : player.getActivePotionEffects()){
                if(pe.getType().equals(PotionEffectType.INVISIBILITY)){
                    int count = (int) Math.floor(pe.getDuration() / 20);
                    setTagVisibility(false, count);
                }
            }
        }
    }

    //Get name style
    public ChatColor getNameStyle(){
        ChatColors cc = new ChatColors();
        try{
            return cc.toColor(config.getString("nameStyle"));
        }catch(NullPointerException npe){
            return ChatColor.RESET;
        }
    }

    //********** TEAM STUFF ***********
    //Set team
    public void setTeam(){
        removeTeam();
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team;
        try{
            team = board.registerNewTeam(player.getName());
        }catch(IllegalArgumentException iae){
            team = board.getTeam(player.getName());
        }
        String teamName;
        ChatColor style = getNameStyle();
        ChatColor color = getNameColor();

        //Check for reset style
        if(style.equals(ChatColor.RESET)){
            teamName = color + "";
        }else{
            teamName = color + "" + style + "";
        }

        //Set the team prefix & add the player
        team.setPrefix(teamName);
        team.addEntry(player.getName());
    }

    //Get team
    public Team getTeam() throws NullPointerException{
        return Bukkit.getScoreboardManager().getMainScoreboard().getTeam(player.getName());
    }

    //Delete team
    public void removeTeam(){
        try{
            Bukkit.getScoreboardManager().getMainScoreboard().getTeam(player.getName()).unregister();
        }catch(NullPointerException npe){}
    }

    //Set team name visibility
    public void setTagVisibility(boolean bool, int count){
        if(isNameTagEnabled){
            Team team = getTeam();
            //Try to cancel the task first to keep from doing it twice
            try{
                Bukkit.getScheduler().cancelTask(taskList.get(player));
            }catch (NullPointerException npe){}//Do nothing.

            //Show nametag
            if(bool){
                team.setNameTagVisibility(NameTagVisibility.ALWAYS);

            //Hide name tag
            }else{
                team.setNameTagVisibility(NameTagVisibility.NEVER);
                BukkitTask task = new Timer(plugin, player).runTaskTimer(plugin, 0L, count * 20L);
                taskList.put(player, task.getTaskId());
            }
        }
    }


















}
