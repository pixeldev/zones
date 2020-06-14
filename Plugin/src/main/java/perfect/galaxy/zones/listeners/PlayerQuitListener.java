package perfect.galaxy.zones.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import perfect.galaxy.zones.PerfectZones;

public class PlayerQuitListener implements Listener {

    private final PerfectZones perfectZones;

    public PlayerQuitListener(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        perfectZones.getUserZoneManager().saveUserZone(player);
    }

}