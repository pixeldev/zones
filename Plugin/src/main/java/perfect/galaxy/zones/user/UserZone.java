package perfect.galaxy.zones.user;

import org.bukkit.entity.Player;
import perfect.galaxy.zones.PerfectZones;
import perfect.galaxy.zones.managers.zone.Zone;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserZone implements PUserZone{

    private Zone zone;
    private final Player player;
    private final UUID uuid;
    private List<Zone> foundedZones;
    private String zoneName;

    public UserZone(Player player, UUID uuid) {
        this.zone = null;
        this.player = player;
        this.uuid = uuid;
        this.zoneName = PerfectZones.getPerfectZones().getFilesManager().getConfig().parseColor(PerfectZones.getPerfectZones().getFilesManager().getConfig().getString("Zone.Default_Zone"));
        this.foundedZones = new ArrayList<>();
    }

    public UserZone(Player player, UUID uuid, List<Zone> foundedZones) {
        this.player = player;
        this.uuid = uuid;
        this.foundedZones = foundedZones;
        this.zone = null;
        this.zoneName = PerfectZones.getPerfectZones().getFilesManager().getConfig().parseColor(PerfectZones.getPerfectZones().getFilesManager().getConfig().getString("Zone.Default_Zone"));
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Zone getZone() {
        return this.zone;
    }

    @Override
    public void setZone(Zone zone) {
        this.zone = zone;
    }

    @Override
    public String getZoneName() {
        return this.zoneName;
    }

    @Override
    public void setZoneName(String zone) {
        this.zoneName = zone;
    }

    @Override
    public List<Zone> getFoundedZones() {
        return this.foundedZones;
    }

    @Override
    public void setFoundedZones(List<Zone> foundedZones) {
        this.foundedZones = foundedZones;
    }

    @Override
    public boolean isFoundZone(Zone zone) {
        return this.foundedZones.contains(zone);
    }

    public UUID getUUID() {
        return uuid;
    }

    @Override
    public void addFoundZone(Zone zone) {
        if(isFoundZone(zone)) return;
        this.foundedZones.add(zone);
    }

    @Override
    public void removeFoundZone(Zone zone) {
        if(!isFoundZone(zone)) return;
        this.foundedZones.remove(zone);
    }
}