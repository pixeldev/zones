package perfect.zones.user.manager;

import org.bukkit.entity.Player;
import perfect.zones.PerfectZones;
import perfect.zones.user.UserZone;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserZoneManager {

    private PerfectZones pz;
    private final List<UserZone> userZone;
    private final List<UUID> playersInZone;

    public UserZoneManager(PerfectZones pz) {
        this.pz = pz;
        this.playersInZone = new ArrayList<>();
        this.userZone = new ArrayList<>();
    }

    public boolean isPlayerInZone(UUID player){
        return this.playersInZone.contains(player);
    }

    public void addPlayerInZone(UUID player){
        if(isPlayerInZone(player)) return;
        this.playersInZone.add(player);
    }

    public void removePlayerInZone(UUID player){
        if(!isPlayerInZone(player)) return;
        this.playersInZone.remove(player);
    }

    public List<UUID> getPlayersInZone() {
        return playersInZone;
    }

    public List<UserZone> getUserZone() {
        return userZone;
    }

    public boolean isUserZone(UserZone zone){
        return (this.userZone.contains(zone));
    }

    public void addUserZone(UserZone zone){
        if(isUserZone(zone)) return;
        this.userZone.add(zone);
    }

    public void removeUserZone(UserZone zone){
        if(!isUserZone(zone)) return;
        this.userZone.remove(zone);
    }

    public UserZone getUserZone(Player player){
        for(UserZone u : this.userZone){
            if(u.getPlayer().getName().equals(player.getName())){
                return u;
            }
        }
        return null;
    }
}