package com.github.azurehanchen.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommonUtils {

    public static void ConsoleLog(String text){
        Bukkit.getConsoleSender().sendMessage(format(text));
    }
    public static void OnlinePlayerMessage(Player player, String text){
        player.sendMessage(format(text));
    }
    public static String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static String formatTime(long timeInMillis) {
        long seconds = timeInMillis / 1000;
        long milliseconds = timeInMillis % 1000;
        return seconds + "." + String.format("%03d", milliseconds) + " 秒";
    }

}
