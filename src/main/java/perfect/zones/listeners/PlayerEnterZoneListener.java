package perfect.zones.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import perfect.zones.PerfectZones;
import perfect.zones.events.PlayerEnterZoneEvent;
import perfect.zones.packets.PTitle;

public class PlayerEnterZoneListener implements Listener {

    private final PerfectZones perfectZones;

    public PlayerEnterZoneListener(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
    }

    @EventHandler
    public void onEnter(PlayerEnterZoneEvent event){
        Player player = event.getPlayer();
        if(!event.isCancelled()) {
            if(!perfectZones.getUserZoneManager().getUserZone(player).getZone().isDefault()){
                if(!perfectZones.getUserZoneManager().getUserZone(player).getFoundedZones().contains(perfectZones.getUserZoneManager().getUserZone(player).getZone())){
                    if(!perfectZones.getUserZoneManager().isPlayerInZone(player.getUniqueId())){
                        perfectZones.getUserZoneManager().addPlayerInZone(player.getUniqueId());
                        perfectZones.getUserZoneManager().getUserZone(player).addFoundZone(perfectZones.getUserZoneManager().getUserZone(player).getZone());
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 2, 1);
                        PTitle.sendTitle(player, 20, 60, 20, "&bNew zone founded: &9"+perfectZones.getUserZoneManager().getUserZone(player).getZone().getName());
                    }
                } else {
                    if(!perfectZones.getUserZoneManager().isPlayerInZone(player.getUniqueId())){
                        perfectZones.getUserZoneManager().addPlayerInZone(player.getUniqueId());
                        PTitle.sendTitle(player, 20, 60, 20, "&bZone: &9"+perfectZones.getUserZoneManager().getUserZone(player).getZone().getName());
                    }
                }
            } else {
                if(!perfectZones.getUserZoneManager().isPlayerInZone(player.getUniqueId())){
                    perfectZones.getUserZoneManager().addPlayerInZone(player.getUniqueId());
                    PTitle.sendTitle(player, 20, 60, 20, "&bZone: &9"+perfectZones.getUserZoneManager().getUserZone(player).getZone().getName());
                }
            }
        } else {
            perfectZones.getUserZoneManager().removePlayerInZone(player.getUniqueId());
            perfectZones.getUserZoneManager().getUserZone(player).setZone(null);
            perfectZones.getUserZoneManager().getUserZone(player).setZoneName(perfectZones.getAllFiles().getConfig().parseColor(perfectZones.getAllFiles().getConfig().getString("Zone.Default_Zone")));
        }
    }
}