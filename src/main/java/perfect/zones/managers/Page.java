package perfect.zones.managers;

import java.util.UUID;

public class Page {

    private final UUID uuid;
    private int page;
    private int pageSearch;

    public Page(UUID uuid) {
        this.uuid = uuid;
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

    public int getPageSearch() {
        return pageSearch;
    }

    public void setPageSearch(int pageSearch){
        this.pageSearch = pageSearch;
    }

}