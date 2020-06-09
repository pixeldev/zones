package perfect.zones.managers.zone;

import java.util.List;

public interface PZone extends PZoneSetup {

    List<ZoneSetup> getZones();

    void setZones(List<ZoneSetup> zones);

    void addZone(ZoneSetup zoneSetup);

    void removeZone(ZoneSetup zoneSetup);

    boolean isDefault();

    void setDefault(boolean def);

}