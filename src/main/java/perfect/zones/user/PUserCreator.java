package perfect.zones.user;

import perfect.zones.managers.zone.Zone;

import java.util.List;
import java.util.UUID;

public interface PUserCreator {

    UUID getUUID();

    List<Zone> getZones();

    void setZones(List<Zone> zones);

    void addZone(Zone zone);

    void removeZone(Zone zone);

}