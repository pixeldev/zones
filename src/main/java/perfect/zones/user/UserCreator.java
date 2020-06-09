package perfect.zones.user;

import perfect.zones.managers.zone.Zone;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserCreator implements PUserCreator {

    private final UUID uuid;
    private List<Zone> zones;

    public UserCreator(UUID uuid) {
        this.uuid = uuid;
        this.zones = new ArrayList<>();
    }

    public UserCreator(UUID uuid, List<Zone> zones) {
        this.uuid = uuid;
        this.zones = zones;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public List<Zone> getZones() {
        return zones;
    }

    @Override
    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    @Override
    public void addZone(Zone zone) {
        this.zones.add(zone);
    }

    @Override
    public void removeZone(Zone zone) {
        this.zones.remove(zone);
    }
}