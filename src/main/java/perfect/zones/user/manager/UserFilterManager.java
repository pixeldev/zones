package perfect.zones.user.manager;

import perfect.zones.user.UserFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserFilterManager {

    private final List<UserFilter> usersFilter;

    public UserFilterManager() {
        usersFilter = new ArrayList<>();
    }

    public List<UserFilter> getUsersFilter() {
        return usersFilter;
    }

    public UserFilter getUserFilter(UUID uuid) {
        for(UserFilter userFilter : usersFilter){
            if(userFilter.getUuid().equals(uuid)){
                return userFilter;
            }
        }
        return null;
    }

    public boolean isUserFilter(UUID uuid) {
        for(UserFilter userFilter : usersFilter){
            if(userFilter.getUuid().equals(uuid)){
                return true;
            }
        }
        return false;
    }
}