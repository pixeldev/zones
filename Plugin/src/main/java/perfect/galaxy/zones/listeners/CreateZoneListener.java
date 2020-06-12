package perfect.galaxy.zones.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import perfect.galaxy.zones.PerfectZones;
import perfect.galaxy.zones.events.CreateZoneEvent;
import perfect.galaxy.zones.user.UserCreator;

public class CreateZoneListener implements Listener {

    private final PerfectZones perfectZones;

    public CreateZoneListener(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
    }

    @EventHandler
    public void onCreate(CreateZoneEvent event){
        Player player = event.getPlayer();
        if(!perfectZones.getUserCreatorManager().isUserCreator(player.getUniqueId())){
           perfectZones.getUserCreatorManager().getUserCreators().add(new UserCreator(player.getUniqueId()));
        }
        perfectZones.getUserCreatorManager().getUserCreator(player.getUniqueId()).addZone(event.getZone().getName());
    }
}