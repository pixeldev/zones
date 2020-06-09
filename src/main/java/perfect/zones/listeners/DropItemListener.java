package perfect.zones.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import perfect.zones.PerfectZones;

public class DropItemListener implements Listener {

    private final PerfectZones perfectZones;

    public DropItemListener(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        if(perfectZones.getZoneManager().containsPlayer(player.getUniqueId())){
            event.setCancelled(true);
        }
    }
}