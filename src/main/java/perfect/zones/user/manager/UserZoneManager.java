package perfect.zones.user.manager;

import perfect.zones.user.UserZone;

import java.util.*;

public class UserZoneManager {

    private final Map<UUID, UserZone> userZone;
    private final List<UUID> playersInZone;

    public UserZoneManager() {
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
}