package com.github.azurehanchen.Configuration;

import com.github.azurehanchen.KarixTab;
import com.github.azurehanchen.Manage.TablistManager;
import com.github.azurehanchen.Utils.CommonUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    static Map Values = new HashMap();

    public static void Loading(){
        KarixTab.getKT().reloadConfig();
        FileConfiguration config = KarixTab.getKT().getConfig();
        try {
            Values.put("CheckUpdate",config.getBoolean("Plugin.CheckUpdate"));
            Values.put("Metrics",config.getBoolean("Plugin.Metrics"));
            Values.put("ConfigVersion",config.getString("ConfigVersion"));
            Values.put("Tab_Enable",config.getBoolean("Tab.Enable"));
            Values.put("Tab_Content",config.getString("Tab.Content"));
            Values.put("Tab_Refresh",config.getBoolean("Tab.Refresh.Enable"));
            Values.put("Tab_Refresh_CD",config.getLong("Tab.Refresh.Cooldown"));
            Values.put("Tablist_Enable",config.getBoolean("Tablist.Enable"));
            Values.put("Tablist_Header",config.getString("Tablist.Header"));
            Values.put("Tablist_Footer",config.getString("Tablist.Footer"));
            Values.put("Tablist_Refresh",config.getBoolean("Tablist.Refresh.Enable"));
            Values.put("Tablist_Refresh_CD",config.getLong("Tablist.Refresh.Cooldown"));
        }
        catch (Exception ex){
            ex.printStackTrace();
            CommonUtils.ConsoleLog("");
        }

    }
    public static void reload() {
        Values.clear();
        Loading();
        Bukkit.getScheduler().cancelTasks(KarixTab.getKT());
        for (Player p : Bukkit.getOnlinePlayers()) {
            if ((Boolean) Values.get("Tab_Enable")) {
                if ((Boolean) Values.get("Tab_Refresh")) {
                    new BukkitRunnable() {
                        public void run() {
                            TablistManager.changeTab(p);
                        }
                    }.runTaskTimerAsynchronously(KarixTab.getKT(), 0, (Long) Values.get("Tab_Refresh_CD"));
                } else {
                    TablistManager.changeTab(p);
                }
            }
            if ((Boolean) Values.get("Tablist_Enable")){
                if ((Boolean) Values.get("Tab_Refresh")){
                    new BukkitRunnable() {
                        public void run() {
                            TablistManager.sendTablist(p);
                        }
                    }.runTaskTimerAsynchronously(KarixTab.getKT(), 0, (Long) Values.get("Tablist_Refresh_CD"));
                }
                else{
                    TablistManager.sendTablist(p);
                }
            }
        }
    }

    public static Map getMap(){
        return Values;
    }

}
