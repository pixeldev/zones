package perfect.galaxy.zones.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import perfect.galaxy.zones.PerfectZones;

public final class ZonesExpansion extends PlaceholderExpansion {

    private PerfectZones perfectZones;

    public ZonesExpansion(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
    }

    @Override
    public boolean canRegister(){
        return Bukkit.getPluginManager().getPlugin("PerfectZones") != null;
    }

    @Override
    public boolean register(){
        if(!canRegister()){
            return false;
        }

        perfectZones = (PerfectZones) Bukkit.getPluginManager().getPlugin("PerfectZones");

        if(perfectZones == null){
            return false;
        }

        return super.register();
    }

    @Override
    public String getIdentifier() {
        return "perfectZonesones";
    }

    @Override
    public String getAuthor() {
        return "PerfectGalaxy";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier){
        if(player == null){
            return "";
        }

        if(identifier.equals("playerzone")){
            return perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZoneName();
        }

        return null;
    }
}