package perfect.galaxy.zones.managers.zone;

import org.bukkit.Location;

import perfect.galaxy.zones.cuboid.PCuboid;

public interface PZoneSetup {

    String getCreator();

    void setCreator(String creator);

    String getName();

    void setName(String name);

    String getMaterial();

    void setMaterial(String material);

    int getData();

    void setData(int data);

    Location getDown();

    void setDown(Location down);

    Location getUp();

    void setUp(Location up);

    String getDate();

    void setDate(String date);

    PCuboid getCuboid();

    void setCuboid(PCuboid cuboid);

}