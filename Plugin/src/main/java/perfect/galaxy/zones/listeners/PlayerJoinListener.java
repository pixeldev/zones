package perfect.galaxy.zones.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import perfect.galaxy.zones.PerfectZones;
import perfect.galaxy.zones.user.UserZone;

public class PlayerJoinListener implements Listener {

    private final PerfectZones perfectZones;

    public PlayerJoinListener(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(!perfectZones.getUserZoneManager().containsUser(player.getUniqueId())) {
            perfectZones.getUserZoneManager().addUser(player.getUniqueId(), new UserZone(player, player.getUniqueId()));
        } else {
            perfectZones.getUserZoneManager().loadUserZone(player);
        }
    }
}