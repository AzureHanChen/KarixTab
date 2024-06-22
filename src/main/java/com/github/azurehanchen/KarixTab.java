package com.github.azurehanchen;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.github.azurehanchen.Commands.MainCommand;
import com.github.azurehanchen.Configuration.ConfigManager;
import com.github.azurehanchen.Listeners.PlayerListener;
import com.github.azurehanchen.Utils.CommonUtils;
import com.sun.tools.javac.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class KarixTab extends JavaPlugin {

    public static int MC_VERSION;
    public static String ORIGINAL_MC_VERSION;
    private static KarixTab kt;
    {
        kt = this;
    }
    private static ProtocolManager protocolManager;


    @Override
    public void onEnable() {
        CommonUtils.ConsoleLog(" &b __                 .__          __        ___.    ");
        CommonUtils.ConsoleLog(" &b|  | _______ _______|__|__  ____/  |______ \\_ |__  ");
        CommonUtils.ConsoleLog(" &b|  |/ /\\__  \\\\_  __ \\  \\  \\/  /\\   __\\__  \\ | __ \\ ");
        CommonUtils.ConsoleLog(" &b|    <  / __ \\|  | \\/  |>    <  |  |  / __ \\| \\_\\ \\");
        CommonUtils.ConsoleLog(" &b|__|_ \\(____  /__|  |__/__/\\_ \\ |__| (____  /___  /");
        CommonUtils.ConsoleLog(" &b     \\/     \\/               \\/           \\/    \\/ ");
        CommonUtils.ConsoleLog(" &6* &f插件版本 &ev"+getDescription().getVersion());
        CommonUtils.ConsoleLog(" &6* &f游戏版本 &ev"+Bukkit.getBukkitVersion());
        MC_VERSION = Integer.parseInt(Bukkit.getBukkitVersion().substring(0,4).replace(".",""));
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            CommonUtils.ConsoleLog(" &6* &fPlaceholderAPI &a√");
        } else {
            CommonUtils.ConsoleLog(" &6* &fPlaceholderAPI &c×");
            CommonUtils.ConsoleLog(" &6* &f>这意味着您将只能使用&e#player#&f这一个变量");
        }
        if (!(KarixTab.getKT().MC_VERSION > 113)) {
            if (Bukkit.getPluginManager().getPlugin("ProtocolLib") != null) {
                protocolManager = ProtocolLibrary.getProtocolManager();
                CommonUtils.ConsoleLog(" &6* &fProtocolLib &a√");
            } else {
                CommonUtils.ConsoleLog(" &6* &fProtocolLib &c×");
                CommonUtils.ConsoleLog(" &6* &f>&c致命错误,插件无法找到关键前置ProtocolLib");
                Bukkit.getPluginManager().disablePlugin(this);
            }
        }
        saveDefaultConfig();
        ConfigManager.reload();
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginCommand("karixtab").setExecutor(new MainCommand());
        CommonUtils.ConsoleLog(" &6*** &fKarixTab已加载");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(KarixTab.getKT());
    }

    public static KarixTab getKT(){
        return kt;
    }
    public static ProtocolManager getPM(){
        return protocolManager;
    }
}
