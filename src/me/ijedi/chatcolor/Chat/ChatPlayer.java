package me.ijedi.chatcolor.Chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class ChatPlayer {

    //Variables
    private Player player;
    private JavaPlugin plugin;
    private File file;
    private FileConfiguration config;
    private boolean isNameTag;
    private ChatColor nameColor, nameStyle, chatColor, chatStyle;
    private Colors colors;
    private static HashMap<Player, Integer> taskList = new HashMap<>();

    //Constructor
    public ChatPlayer(Player player, JavaPlugin plugin){
        this.player = player;
        this.plugin = plugin;
        file = new File(plugin.getDataFolder() + "/playerData/" + player.getUniqueId() + ".yml");
        config = YamlConfiguration.loadConfiguration(file);
        colors = new Colors();
        isNameTag = plugin.getConfig().getBoolean("nameTag");

        //Get player info
        setNameColor(config.getString("nameColor"));
        setNameStyle(config.getString("nameStyle"));
        setChatColor(config.getString("chatColor"));
        setChatStyle(config.getString("chatStyle"));
    }

    //Save
    private void save(){
        try{
            config.save(file);
        }catch(IOException ioe){
            plugin.getLogger().info("Error saving player file.");
        }
    }

    //************************ NAME ************************
    //Set name color
    public void setNameColor(String string){
        nameColor = colors.toColor(string);
        config.set("nameColor", string);
        save();
        updateListName();
        if(isNameTag){
            setTeam();
        }
    }
    //Set name style
    public void setNameStyle(String string){
        nameStyle = colors.toColor(string);
        config.set("nameStyle", string);
        save();
        updateListName();
        if(isNameTag){
            setTeam();
        }
    }
    //Get name (with colors)
    public String getName(){
        if(nameStyle.equals(ChatColor.RESET)){
            return nameColor + player.getName();
        }else{
            return nameColor + "" + nameStyle + player.getName();
        }
    }
    //Update playerListName
    private void updateListName(){
        try{
            if(!nameStyle.equals(ChatColor.RESET)){
                player.setPlayerListName(nameColor + "" + nameStyle + player.getName());
            }else{
                player.setPlayerListName(nameColor + player.getName());
            }
        }catch(NullPointerException npe){
            player.setPlayerListName(nameColor + player.getName());
        }
    }

    //************************ CHAT ************************
    //Set chat color
    public void setChatColor(String string){
        chatColor = colors.toColor(string);
        config.set("chatColor", string);
        save();
    }
    //Set chat style
    public void setChatStyle(String string){
        chatStyle = colors.toColor(string);
        config.set("chatStyle", string);
        save();
    }
    //Get chat prefix (colors)
    public String getChatPrefix(){
        if(chatStyle.equals(ChatColor.RESET)){
            return chatColor + "";
        }else{
            return chatColor + "" + chatStyle;
        }
    }


    //************************ TEAM ************************
    //Remove from taskList
    public void removeTask(){
        if(taskList.containsKey(player)){
            taskList.remove(player);
        }
    }
    //Set team
    public void setTeam(){
        //Remove old team
        removeTeam();

        //Create new one
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team;
        try{
            team = board.registerNewTeam(player.getName());
        }catch(IllegalArgumentException iae){ //Team exists
            team = board.getTeam(player.getName());
        }

        //Set prefix and add player to their team
        String teamPrefix;
        try{
            if(nameStyle.equals(ChatColor.RESET)){
                teamPrefix = nameColor + "";
            }else{
                teamPrefix = nameColor + "" + nameStyle;
            }
        }catch(NullPointerException npe){
            teamPrefix = nameColor + "" + nameStyle;
        }

        team.setPrefix(teamPrefix);
        team.addEntry(player.getName());
    }
    //Get team
    public Team getTeam() throws NullPointerException{
        return Bukkit.getScoreboardManager().getMainScoreboard().getTeam(player.getName());
    }
    //Remove team
    public void removeTeam() {
        try{
            Set<Team> teamList = Bukkit.getScoreboardManager().getMainScoreboard().getTeams();
            if (teamList.contains(player.getName())) {
                Bukkit.getScoreboardManager().getMainScoreboard().getTeam(player.getName()).unregister();
            }
        } catch (NullPointerException npe) {} //Team doesn't exist. Do nothing.
    }
    //Set tag visibility
    public void setTagVisibility(boolean bool, int seconds){
        if(isNameTag){
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
                BukkitTask task = new Timer(plugin, player).runTaskTimer(plugin, 0L, seconds * 20L);
                taskList.put(player, task.getTaskId());
            }

        }//Else do nothing.
    }



}
