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

    String getMaterial();

    void setMaterial(String material);

    Type getType();

    void setType(Type type);

    int getPage();

    void setPage(int page);

    int getPageSearch();

    void setPageSearch(int pageSearch);

    public enum Type {
        A_Z, Z_A, NORMAL
    }

}