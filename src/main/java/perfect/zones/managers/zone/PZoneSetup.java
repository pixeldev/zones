package perfect.zones.managers.zone;

import org.bukkit.Location;
import perfect.zones.cuboid.PCuboid;

public interface PZoneSetup {

    String getCreator();

    void setCreator(String creator);

    String getName();

    void setName(String name);

    Location getDown();

    void setDown(Location down);

    Location getUp();

    void setUp(Location up);

    String getDate();

    void setDate(String date);

    PCuboid getCuboid();

    void setCuboid(PCuboid cuboid);

}