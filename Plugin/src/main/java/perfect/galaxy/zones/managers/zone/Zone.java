package perfect.galaxy.zones.managers.zone;

import org.bukkit.Location;

import perfect.galaxy.zones.cuboid.PCuboid;

import java.util.ArrayList;
import java.util.List;

public class Zone implements PZone {

    private String creator;
    private String name;
    private int data;
    private Location down;
    private Location up;
    private String date;
    private PCuboid cuboid;
    private String material;
    private boolean def;
    private List<String> messagesRewards;
    private List<String> rewards;
    private List<ZoneSetup> zoneSetups;

    public Zone(String creator, String name, boolean def) {
        this.creator = creator;
        this.def = def;
        this.name = name;
        this.data = 0;
        this.messagesRewards = new ArrayList<>();
        this.rewards = new ArrayList<>();
        this.material = "STONE";
        this.zoneSetups = new ArrayList<>();
    }

    public Zone(String creator, String name, Location down, Location up, String date, PCuboid cuboid, boolean def,
                List<ZoneSetup> zoneSetups, List<String> rewards, List<String> messagesRewards, String material) {
        this.creator = creator;
        this.messagesRewards = messagesRewards;
        this.rewards = rewards;
        this.material = material;
        this.def = def;
        this.name = name;
        this.down = down;
        this.up = up;
        this.date = date;
        this.cuboid = cuboid;
        this.zoneSetups = zoneSetups;
    }

    @Override
    public String getCreator() {
        return this.creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Location getDown() {
        return this.down;
    }

    @Override
    public void setDown(Location down) {
        this.down = down;
    }

    @Override
    public Location getUp() {
        return this.up;
    }

    @Override
    public void setUp(Location up) {
        this.up = up;
    }

    @Override
    public String getDate() {
        return this.date;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public PCuboid getCuboid() {
        return this.cuboid;
    }

    @Override
    public void setCuboid(PCuboid cuboid) {
        this.cuboid = cuboid;
    }

    @Override
    public List<ZoneSetup> getZones() {
        return this.zoneSetups;
    }

    @Override
    public void setZones(List<ZoneSetup> zones) {
        this.zoneSetups = zones;
    }

    @Override
    public void addZone(ZoneSetup zoneSetup){
        this.zoneSetups.add(zoneSetup);
    }

    @Override
    public void removeZone(ZoneSetup zoneSetup){
        this.zoneSetups.remove(zoneSetup);
    }

    @Override
    public boolean isDefault() {
        return this.def;
    }

    @Override
    public void setDefault(boolean def) {
        this.def = def;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public int getData() {
        return data;
    }

    @Override
    public void setData(int data) {
        this.data = data;
    }

    @Override
    public List<String> getRewards() {
        return rewards;
    }

    @Override
    public void setRewards(List<String> rewards) {
        this.rewards = rewards;
    }

    @Override
    public void addReward(String reward){
        this.rewards.add(reward);
    }

    @Override
    public List<String> getMessagesRewards() {
        return messagesRewards;
    }

    @Override
    public void setMessagesRewards(List<String> messagesRewards) {
        this.messagesRewards = messagesRewards;
    }
}