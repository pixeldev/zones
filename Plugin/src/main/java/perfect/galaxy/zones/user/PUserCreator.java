package perfect.galaxy.zones.user;

import java.util.List;
import java.util.UUID;

public interface PUserCreator {

    UUID getUUID();

    List<String> getZones();

    void setZones(List<String> zones);

    void addZone(String zone);

    void removeZone(String zone);

}