package me.ijedi.chatcolor.Chat;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class ChatManager {

    //Variables
    private static HashMap<Player, ChatPlayer> chatPlayerMap = new HashMap<>();
    private static HashMap<Player, Player> lastMsgHM = new HashMap<>();

    //*************** CHAT PLAYER ***************
    //Get ChatPlayer
    public ChatPlayer getChatPlayer(Player player) throws NullPointerException{
        if(chatPlayerMap.containsKey(player)){
            return chatPlayerMap.get(player);
        }else{
            throw new NullPointerException("ChatPlayer not found for specified player.");
        }
    }
    //Add ChatPlayer
    public void addChatPlayer(Player player, ChatPlayer chatPlayer){
        chatPlayerMap.put(player, chatPlayer);
    }
    //Remove ChatPlayer
    public void removeChatPlayer(Player player){
        if(chatPlayerMap.containsKey(player)){
            chatPlayerMap.remove(player);
        }
    }


    //*************** LAST MESSAGE **************
    //Add lastMsg
    public void addLastMSG(Player sender, Player receiver){
        lastMsgHM.put(sender, receiver);
    }
    //Remove lastMsg
    public void removeLastMSG(Player player){
        if(lastMsgHM.containsKey(player)){
            lastMsgHM.remove(player);
        }
    }
    //Get lastMsg
    public Player getLastMSG(Player player) throws NullPointerException{
        if(lastMsgHM.containsKey(player)){
            return lastMsgHM.get(player);
        }else{
            throw new NullPointerException("This player has not messaged anyone.");
        }
    }
}
