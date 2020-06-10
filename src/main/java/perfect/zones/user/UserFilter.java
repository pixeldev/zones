package perfect.zones.user;

import java.util.UUID;

public class UserFilter {

    private final UUID uuid;
    private String key;
    private Type type;

    public UserFilter(UUID uuid, Type type) {
        this.uuid = uuid;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        A_Z, Z_A, NORMAL
    }

}