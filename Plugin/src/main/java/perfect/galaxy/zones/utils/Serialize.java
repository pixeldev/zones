package perfect.galaxy.zones.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Serialize {

    public static String serializeLocation(Location location){
        return location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() +";" + location.getZ();
    }

    public static Location deserializeLocation(String location){
        String[] loc = location.split(";");
        World world = Bukkit.getWorld(loc[0]);
        double x = Double.parseDouble(loc[1]);
        double y = Double.parseDouble(loc[2]);
        double z = Double.parseDouble(loc[3]);
        return new Location(world, x, y, z);
    }

}