package perfect.galaxy.zones.user;

import perfect.galaxy.zones.managers.zone.Zone;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserEditor implements PUserEditor {

    private final UUID uuid;
    private String key;
    private Zone zone;
    boolean reward;
    private PUserEditor.Type type;
    private int page;
    private int pageSearch;
    boolean setup;
    boolean messageReward;

    public UserEditor(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public Zone getZone() {
        return zone;
    }

    @Override
    public void setZone(Zone zone) {
        this.zone = zone;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int getPageSearch() {
        return pageSearch;
    }

    @Override
    public boolean isReward() {
        return reward;
    }

    @Override
    public void setReward(boolean reward) {
        this.reward = reward;
    }

    @Override
    public boolean isSetup() {
        return setup;
    }

    @Override
    public void setSetup(boolean setup) {
        this.setup = setup;
    }

    @Override
    public boolean isMessageReward() {
        return messageReward;
    }

    @Override
    public void setMessageReward(boolean messageReward) {
        this.messageReward = messageReward;
    }

    @Override
    public void setPageSearch(int pageSearch) {
        this.pageSearch = pageSearch;
    }
}