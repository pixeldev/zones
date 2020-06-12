package perfect.galaxy.zones.user;

import org.bukkit.entity.Player;
import perfect.galaxy.zones.managers.zone.Zone;

import java.util.List;

public interface PUserZone {

    Player getPlayer();

    Zone getZone();

    void setZone(Zone zone);

    String getZoneName();

    void setZoneName(String zone);

    List<Zone> getFoundedZones();

    void setFoundedZones(List<Zone> foundedZones);

    boolean isFoundZone(Zone zone);

    void addFoundZone(Zone zone);

    void removeFoundZone(Zone zone);

}