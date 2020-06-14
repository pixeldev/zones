package perfect.galaxy.zones.managers.zone;

import java.util.List;

public interface PZone extends PZoneSetup {

    List<ZoneSetup> getZones();

    void setZones(List<ZoneSetup> zones);

    void addZone(ZoneSetup zoneSetup);

    void removeZone(ZoneSetup zoneSetup);

    List<String> getRewards();

    void setRewards(List<String> rewards);

    List<String> getMessagesRewards();

    void setMessagesRewards(List<String> messagesRewards);

    void addReward(String reward);

    boolean isDefault();

    void setDefault(boolean def);

}