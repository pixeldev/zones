package perfect.zones.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import perfect.zones.PerfectZones;
import perfect.zones.user.UserZone;

public class PlayerJoinListener implements Listener {

    private final PerfectZones perfectZones;

    public PlayerJoinListener(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UserZone userZone = new UserZone(player);
        perfectZones.getUserZoneManager().addUserZone(userZone);
    }
}