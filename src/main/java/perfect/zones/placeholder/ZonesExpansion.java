package perfect.zones.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import perfect.zones.PerfectZones;
import perfect.zones.user.PUserZone;

public class ZonesExpansion extends PlaceholderExpansion {

    private PerfectZones perfectZones;

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
        return "pzones";
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
            return PerfectZones.getPerfectZones().getUserZoneManager().getUserZone(player).getZoneName();
        }

        return null;
    }
}