package me.nozembr.events;



import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.security.Permission;
import java.util.ArrayList;


public class warpsmanager implements Listener {

    public static ArrayList<String> teleportkitpvp = new ArrayList();
    public static Permission perms = null;

    public warpsmanager() {
    }

    @EventHandler
    public void TeleportKitpvp(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (teleportkitpvp.contains(p.getName())) {
            e.setCancelled(true);
        }

    }
    
}
