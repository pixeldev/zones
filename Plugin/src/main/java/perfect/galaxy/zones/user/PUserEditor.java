package perfect.galaxy.zones.user;

import perfect.galaxy.zones.managers.zone.Zone;

import java.util.List;
import java.util.UUID;

public interface PUserEditor {

    UUID getUUID();

    Zone getZone();

    void setZone(Zone zone);

    String getKey();

    void setKey(String key);

    Type getType();

    void setType(Type type);

    int getPage();

    void setPage(int page);

    int getPageSearch();

    void setPageSearch(int pageSearch);

    boolean isSetup();

    void setSetup(boolean setup);

    boolean isReward();

    void setReward(boolean reward);

    boolean isMessageReward();

    void setMessageReward(boolean messageReward);

    enum Type {
        A_Z, Z_A, NORMAL
    }

}