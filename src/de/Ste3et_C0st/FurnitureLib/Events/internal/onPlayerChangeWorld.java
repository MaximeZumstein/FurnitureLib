package de.Ste3et_C0st.FurnitureLib.Events.internal;

import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class onPlayerChangeWorld extends EventLibrary implements Listener {
    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        final Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskLater(FurnitureLib.getInstance(), () -> getFurnitureMgr().updatePlayerView(player), 5);
    }

}
