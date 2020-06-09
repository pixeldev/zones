package perfect.zones.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PageManager {

    private final List<Page> pages;

    public PageManager() {
        pages = new ArrayList<>();
    }

    public List<Page> getPages() {
        return pages;
    }

    public Page getUserPage(UUID uuid){
        for(Page page : pages){
            if(page.getUuid().equals(uuid)){
                return page;
            }
        }
        return null;
    }

    public boolean isUser(UUID uuid){
        for(Page page : pages){
            if(page.getUuid().equals(uuid)){
                return true;
            }
        }
        return false;
    }

}