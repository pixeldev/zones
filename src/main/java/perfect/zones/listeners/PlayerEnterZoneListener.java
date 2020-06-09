package perfect.zones.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import perfect.zones.PerfectZones;
import perfect.zones.events.PlayerEnterZoneEvent;
import perfect.zones.packets.PActionbar;
import perfect.zones.packets.PTitle;

public class PlayerEnterZoneListener implements Listener {

    private final PerfectZones perfectZones;
    private final int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);

    public PlayerEnterZoneListener(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
    }

    @EventHandler
    public void onEnter(PlayerEnterZoneEvent event){
        Player player = event.getPlayer();
        if(!event.isCancelled()) {
            if(!perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().isDefault()){
                if(!perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getFoundedZones().contains(perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone())){
                    if(!perfectZones.getUserZoneManager().isPlayerInZone(player.getUniqueId())){
                        perfectZones.getUserZoneManager().addPlayerInZone(player.getUniqueId());
                        perfectZones.getUserZoneManager().getUser(player.getUniqueId()).addFoundZone(perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone());
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("ENTITY_PLAYER_LEVELUP") : Sound.valueOf("LEVEL_UP"), 2, 1);
                        PActionbar.sendActionBar(player, "New Zone: " + perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getName());
                        PTitle.sendTitle(player, 20, 60, 20, "&bNew zone founded: &9"+perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getName());
                    }
                } else {
                    if(!perfectZones.getUserZoneManager().isPlayerInZone(player.getUniqueId())){
                        perfectZones.getUserZoneManager().addPlayerInZone(player.getUniqueId());
                        PActionbar.sendActionBar(player, "Zone: " + perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getName());
                        PTitle.sendTitle(player, 20, 60, 20, "&bZone: &9"+perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getName());
                    }
                }
            } else {
                if(!perfectZones.getUserZoneManager().isPlayerInZone(player.getUniqueId())){
                    perfectZones.getUserZoneManager().addPlayerInZone(player.getUniqueId());
                    PActionbar.sendActionBar(player, "Zone: " + perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getName());
                    PTitle.sendTitle(player, 20, 60, 20, "&bZone: &9"+perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getName());
                }
            }
        } else {
            perfectZones.getUserZoneManager().removePlayerInZone(player.getUniqueId());
            perfectZones.getUserZoneManager().getUser(player.getUniqueId()).setZone(null);
            PActionbar.sendActionBar(player, "Zone: " + perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getName());
            perfectZones.getUserZoneManager().getUser(player.getUniqueId()).setZoneName(perfectZones.getAllFiles().getConfig().parseColor(perfectZones.getAllFiles().getConfig().getString("Zone.Default_Zone")));
        }
    }
}