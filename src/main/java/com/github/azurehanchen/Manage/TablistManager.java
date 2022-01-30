package com.github.azurehanchen.Manage;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.github.azurehanchen.Configuration.ConfigManager;
import com.github.azurehanchen.KarixTab;
import com.github.azurehanchen.Utils.CommonUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TablistManager {

    private static ProtocolManager protocolManager = KarixTab.getPM();

    public static void sendTablist(Player p){
        String header = CommonUtils.format((String) ConfigManager.getMap().get("Tablist_Header")).replaceAll("#player#",p.getName());
        header = PlaceholderAPI.setPlaceholders(p, header);
        String footer = CommonUtils.format((String) ConfigManager.getMap().get("Tablist_Footer")).replaceAll("#player#",p.getName());
        footer = PlaceholderAPI.setPlaceholders(p, footer);
        if (!(KarixTab.getKT().MC_VERSION > 13)){
            PacketContainer pc = protocolManager.createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
            pc.getChatComponents().write(0, (WrappedChatComponent) WrappedChatComponent.fromText(header)).write(1, (WrappedChatComponent) WrappedChatComponent.fromText(footer));
            try {
                protocolManager.sendServerPacket(p, pc);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            p.setPlayerListHeaderFooter(header,footer);
        }

    }
    public static void changeTab(Player p){
        String tab = CommonUtils.format((String) ConfigManager.getMap().get("Tab_Content")).replaceAll("#player#",p.getName());
        tab = PlaceholderAPI.setPlaceholders(p, tab);
        p.setPlayerListName(tab);
    }
}
