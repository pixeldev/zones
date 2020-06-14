package perfect.galaxy.zones.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import perfect.galaxy.zones.PerfectZones;
import perfect.galaxy.zones.events.PlayerEnterZoneEvent;
import perfect.galaxy.zones.managers.zone.Zone;

public class PlayerMoveListener implements Listener {

    private final PerfectZones perfectZones;

    public PlayerMoveListener(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(perfectZones.getUserZoneManager().containsUser(player.getUniqueId())) {
            if(!perfectZones.getZoneManager().getZones().isEmpty()){
                for(Zone zone : perfectZones.getZoneManager().getZones()){
                    if(zone.getCuboid() != null){
                        if(zone.getCuboid().containsLocation(player.getLocation())){
                            perfectZones.getUserZoneManager().getUser(player.getUniqueId()).setZone(zone);
                            perfectZones.getUserZoneManager().getUser(player.getUniqueId()).setZoneName(zone.getName());
                            if(!perfectZones.getUserZoneManager().isPlayerInZone(player.getUniqueId())) {
                                PlayerEnterZoneEvent playerEnterZoneEvent = new PlayerEnterZoneEvent(player,
                                        perfectZones.getUserZoneManager().getUser(player.getUniqueId()), zone, zone.getName());
                                Bukkit.getServer().getPluginManager().callEvent(playerEnterZoneEvent);
                            }
                        }
                    }
                }
                if(perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone() != null){
                    if(!perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getCuboid().containsLocation(player.getLocation())){
                        perfectZones.getUserZoneManager().removePlayerInZone(player.getUniqueId());
                        perfectZones.getUserZoneManager().getUser(player.getUniqueId()).setZone(null);
                        perfectZones.getUserZoneManager().getUser(player.getUniqueId()).setZoneName(
                                perfectZones.getFilesManager().getConfig().parseColor(perfectZones.getFilesManager().getConfig().getString("Zone.Default_Zone"))
                        );
                    }
                }
            }
        }
    }
}
