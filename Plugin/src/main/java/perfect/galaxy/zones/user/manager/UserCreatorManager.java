package perfect.galaxy.zones.user.manager;

import perfect.galaxy.zones.user.UserCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserCreatorManager {

    private final List<UserCreator> userCreators;

    public UserCreatorManager() {
        userCreators = new ArrayList<>();
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
}