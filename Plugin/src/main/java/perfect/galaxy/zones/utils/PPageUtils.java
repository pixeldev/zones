package perfect.galaxy.zones.utils;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PPageUtils {

    public static List<ItemStack> getPageItems(List<ItemStack> items, int page, int spaces){
        int upperBound = page * spaces; //36
        int lowerBound = upperBound - spaces; //36-36 = 0

        List<ItemStack> newItems = new ArrayList<>();
        for(int i = lowerBound; i < upperBound; i++){
            if(i >= items.size()) break;
            newItems.add(items.get(i));
        }
        return newItems;
    }

    public static boolean isPageValid(List<ItemStack> items, int page, int spaces){
        if(page <= 0) return false;

        int upperBound = page * spaces;
        int lowerBound = upperBound - spaces;

        return items.size() > lowerBound;
    }
}