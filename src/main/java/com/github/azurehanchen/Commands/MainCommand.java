package com.github.azurehanchen.Commands;

import com.github.azurehanchen.Configuration.ConfigManager;
import com.github.azurehanchen.Enum.Messages;
import com.github.azurehanchen.KarixTab;
import com.github.azurehanchen.Utils.CommonUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.azurehanchen.Utils.CommonUtils.format;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!sender.hasPermission("karixtab.manage")){
        sender.sendMessage(format(Messages.PREFIX.getMessage()+Messages.NO_PERMISSION.getMessage()));
        return true;
    }
    else if (args.length == 1 && args[0].equalsIgnoreCase("reload") ){
        sender.sendMessage(format(Messages.PREFIX.getMessage()+"&f插件正在重载中..."));
        try {
            long startTime = System.currentTimeMillis();
            ConfigManager.reload();
            long endTime = System.currentTimeMillis();
            long reloadTime = endTime - startTime;
            sender.sendMessage(format(Messages.PREFIX.getMessage()+"&f插件在&e"+reloadTime+"ms&f内重置完毕&a√"));
        }catch (Exception ex){
            CommonUtils.ConsoleLog(Messages.PREFIX.getMessage()+Messages.FATAL_ERROR);
            sender.sendMessage(Messages.PREFIX.getMessage()+Messages.FATAL_ERROR);
            ex.printStackTrace();
        }
        return true;
    }
    else{
        sender.sendMessage(format(Messages.PREFIX.getMessage()+"&f插件版本 &av"+ KarixTab.getKT().getDescription().getVersion()+" &f插件作者 &aAzureHanChen"));
        sender.sendMessage(format(Messages.PREFIX.getMessage()+"&f开源地址 &ahttps://github.com/AzureHanChen/KarixTab"));
        sender.sendMessage(format(Messages.PREFIX.getMessage()+"&f使用 &e/karixtab reload &f重置插件"));
        return true;
    }
}
}
