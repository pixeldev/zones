package perfect.zones.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import perfect.zones.PerfectZones;

public class DropItemListener implements Listener {

    private PerfectZones pz;

    public DropItemListener(PerfectZones pz) {
        this.pz = pz;
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        if(pz.getZoneManager().containsPlayer(player.getUniqueId())){
            event.setCancelled(true);
        }
    }
}