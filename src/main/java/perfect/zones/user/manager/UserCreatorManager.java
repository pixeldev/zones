package perfect.zones.user.manager;

import perfect.zones.PerfectZones;
import perfect.zones.user.UserCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserCreatorManager {

    private final List<UserCreator> usersCreator;
    private final PerfectZones perfectZones;

    public UserCreatorManager(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
        this.usersCreator = new ArrayList<>();
    }

    public List<UserCreator> getUsersCreator() {
        return usersCreator;
    }

    public UserCreator getUserCreator(UUID uuid){
        for(UserCreator userCreator : usersCreator){
            if(userCreator.getUUID().equals(uuid)){
                return userCreator;
            }
        }
        return null;
    }

    public boolean isUserCreator(UUID uuid){
        for(UserCreator userCreator : usersCreator){
            if(userCreator.getUUID().equals(uuid)){
                return true;
            }
        }
        return false;
    }

    public void saveCreators(){
        if(!usersCreator.isEmpty()){
            for(UserCreator userCreator : usersCreator){
                perfectZones.data.set("Creators." + userCreator.getUUID().toString() + ".Zones", userCreator.getZones());
            }
            perfectZones.data.save();
        }
    }

    public void loadCreators(){
        if(perfectZones.data.contains("Creators")){
            for(String uuid : perfectZones.data.getConfigurationSection("Creators").getKeys(false)){
                usersCreator.add(new UserCreator(UUID.fromString(uuid), perfectZones.data.getStringList("Creators." + uuid +".Zones")));
            }
        }
    }
}