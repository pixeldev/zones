package perfect.zones.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import perfect.zones.PerfectZones;

public class InventoryClickListener implements Listener {

    private PerfectZones pz;

    public InventoryClickListener(PerfectZones pz) {
        this.pz = pz;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(pz.getZoneManager().containsPlayer(player.getUniqueId())){
            event.setCancelled(true);
        }
    }
}