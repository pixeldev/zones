package perfect.galaxy.zones.managers.zone;

import org.bukkit.Location;

import perfect.galaxy.zones.cuboid.PCuboid;

public class ZoneSetup implements PZoneSetup {

    private String creator;
    private String name;
    private Location down;
    private Location up;
    private String date;
    private PCuboid cuboid;

    public ZoneSetup(String creator, String name) {
        this.creator = creator;
        this.name = name;
    }

    public ZoneSetup(String creator, String name, Location down, Location up, String date, PCuboid cuboid) {
        this.creator = creator;
        this.name = name;
        this.down = down;
        this.up = up;
        this.date = date;
        this.cuboid = cuboid;
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
    public String getMaterial() {
        return null;
    }

    @Override
    public void setMaterial(String material) {

    }

    @Override
    public int getData() {
        return 0;
    }

    @Override
    public void setData(int data) {

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
}