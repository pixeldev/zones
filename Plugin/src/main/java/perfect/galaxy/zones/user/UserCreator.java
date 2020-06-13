package perfect.galaxy.zones.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserCreator implements PUserCreator {

    private final UUID uuid;
    private List<String> zones;

    public UserCreator(UUID uuid) {
        this.uuid = uuid;
        this.zones = new ArrayList<>();
    }

    public UserCreator(UUID uuid, List<String> zones) {
        this.uuid = uuid;
        this.zones = zones;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public List<String> getZones() {
        return zones;
    }

    @Override
    public void setZones(List<String> zones) {
        this.zones = zones;
    }

    @Override
    public void addZone(String zone){
        this.zones.add(zone);
    }

    @Override
    public void removeZone(String zone){
        this.zones.remove(zone);
    }
}