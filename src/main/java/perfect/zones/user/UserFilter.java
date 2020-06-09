package perfect.zones.user;

import java.util.UUID;

public class UserFilter {

    private final UUID uuid;
    private Type type;

    public UserFilter(UUID uuid, Type type) {
        this.uuid = uuid;
        this.type = type;
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

    enum Type {
        A_Z, Z_A, NORMAL
    }

}