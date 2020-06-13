package perfect.galaxy.zones.user.manager;

import perfect.galaxy.zones.PerfectZones;
import perfect.galaxy.zones.user.UserCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserCreatorManager {

    private final PerfectZones perfectZones;
    private final List<UserCreator> userCreators;

    public UserCreatorManager(PerfectZones perfectZones) {
        userCreators = new ArrayList<>();
        this.perfectZones = perfectZones;
    }

    public List<UserCreator> getUserCreators() {
        return userCreators;
    }

    public UserCreator getUserCreator(UUID uuid){
        for(UserCreator userCreator : userCreators){
            if(userCreator.getUUID().equals(uuid)){
                return userCreator;
            }
        }
        return null;
    }

    public boolean isUserCreator(UUID uuid){
        for(UserCreator userCreator : userCreators){
            if(userCreator.getUUID().equals(uuid)){
                return true;
            }
        }
        return false;
    }

    public void loadCreators() {
        if (perfectZones.getFilesManager().getData().contains("Creators")) {
            for (String s : perfectZones.getFilesManager().getData().getConfigurationSection("Creators").getKeys(false)) {
                this.userCreators.add(new UserCreator(UUID.fromString(s), perfectZones.getFilesManager().getData().getList("Creators." + s + ".Zones")));
            }
        }
    }

    public void saveCreators() {
        if (!this.userCreators.isEmpty()) {
            userCreators.forEach(userCreator -> {
                perfectZones.getFilesManager().getData().set("Creators." + userCreator.getUUID().toString() + ".Zones", userCreator.getZones());
            });
            perfectZones.data.save();
        }
    }

}