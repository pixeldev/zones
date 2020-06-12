package perfect.galaxy.zones.user.manager;

import perfect.galaxy.zones.user.UserEditor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserEditorManager {

    private final List<UserEditor> editors;

    public UserEditorManager() {
        this.editors = new ArrayList<>();
    }

    public List<UserEditor> getEditors() {
        return editors;
    }

    public UserEditor getUserEditor(UUID uuid){
        for(UserEditor userEditor : editors){
            if(userEditor.getUUID().equals(uuid)){
                return userEditor;
            }
        }
        return null;
    }

    public boolean isUserEditor(UUID uuid){
        for(UserEditor userEditor : editors){
            if(userEditor.getUUID().equals(uuid)){
                return true;
            }
        }
        return false;
    }

}