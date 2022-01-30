package com.github.azurehanchen.Listeners;

import com.github.azurehanchen.Configuration.ConfigManager;
import com.github.azurehanchen.KarixTab;
import com.github.azurehanchen.Manage.TablistManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        if ((Boolean) ConfigManager.getMap().get("Tablist_Enable")){
            if ((Boolean) ConfigManager.getMap().get("Tablist_Refresh")){
                new BukkitRunnable() {
                    public void run() {
                        TablistManager.sendTablist(p);
                    }
                }.runTaskTimerAsynchronously(KarixTab.getKT(), 0, (Long)ConfigManager.getMap().get("Tablist_Refresh_CD"));
            }
            else{
                TablistManager.sendTablist(p);
            }
        }
        if ((Boolean) ConfigManager.getMap().get("Tab_Enable")){
            if ((Boolean) ConfigManager.getMap().get("Tab_Refresh")){
                new BukkitRunnable() {
                    public void run() {
                        TablistManager.changeTab(p);
                    }
                }.runTaskTimerAsynchronously(KarixTab.getKT(), 0, (Long)ConfigManager.getMap().get("Tab_Refresh_CD"));
            }
            else{
                TablistManager.changeTab(p);
            }
        }

    }

}
