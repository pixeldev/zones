package perfect.zones.managers;

import java.util.UUID;

public class Page {

    private final UUID uuid;
    private int page;

    public Page(UUID uuid, int page) {
        this.uuid = uuid;
        this.page = page;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}