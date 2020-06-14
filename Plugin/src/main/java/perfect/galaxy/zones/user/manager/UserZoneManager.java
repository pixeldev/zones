package perfect.galaxy.zones.user.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import perfect.galaxy.zones.PerfectZones;
import perfect.galaxy.zones.managers.zone.Zone;
import perfect.galaxy.zones.user.UserZone;

import java.util.*;

public class UserZoneManager {

    private final PerfectZones perfectZones;

    private final Map<UUID, UserZone> userZone;
    private final List<UUID> playersInZone;

    public UserZoneManager(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
        this.playersInZone = new ArrayList<>();
        this.userZone = new HashMap<>();
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

    public Map<UUID, UserZone> getUserZone() {
        return userZone;
    }

    public boolean containsUser(UUID player){
        return userZone.containsKey(player);
    }

    public UserZone getUser(UUID player){
        return userZone.get(player);
    }

    public void addUser(UUID player, UserZone userZone){
        this.userZone.put(player, userZone);
    }

    public void removeUser(UUID player){
        userZone.remove(player);
    }

    public void saveUsersZone() {
        if(!Bukkit.getOnlinePlayers().isEmpty()) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                UserZone user = getUser(player.getUniqueId());
                List<String> zonesFounded = new ArrayList<>();
                if(!user.getFoundedZones().isEmpty()) {
                    for(Zone zone : user.getFoundedZones()) {
                        zonesFounded.add(zone.getName());
                    }
                }
                perfectZones.getFilesManager().getData().set("Users." + user.getUUID().toString() + ".Zones_Founded", zonesFounded);
                perfectZones.data.save();
            });
        }
    }

    public void loadUsersZone() {
        if(!Bukkit.getOnlinePlayers().isEmpty()) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                if(perfectZones.getFilesManager().getData().contains("Users." + player.getUniqueId().toString())) {
                    this.userZone.put(player.getUniqueId(), new UserZone(player, player.getUniqueId(),
                            perfectZones.getZoneManager().getZonesByName(perfectZones.getFilesManager().getData().getList("Users." + player.getUniqueId().toString() + ".Zones_Founded"))
                    ));
                }
            });
        }
    }

    public void saveUserZone(Player player) {
        if(!this.userZone.isEmpty()) {
            UserZone zone = getUser(player.getUniqueId());
            List<String> zonesFounded = new ArrayList<>();
            zone.getFoundedZones().forEach(zone1 -> zonesFounded.add(zone1.getName()));
            perfectZones.getFilesManager().getData().set("Users." + player.getUniqueId().toString() + ".Zones_Founded", zonesFounded);
            perfectZones.data.save();
        }
    }

    public void loadUserZone(Player player) {
        addUser(player.getUniqueId(), new UserZone(player, player.getUniqueId(),
                perfectZones.getZoneManager().getZonesByName(
                        perfectZones.getFilesManager().getData().getList("Users." + player.getUniqueId().toString() + ".Zones_Founded"))
        ));
    }

}