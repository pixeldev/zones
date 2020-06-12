package perfect.galaxy.zones.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {

    @SuppressWarnings("deprecation")
    public static ItemStack getItem(Material material, int amount, int data, String title, List<String> lore) {
        ItemStack itemStack = new ItemStack(material, amount, (byte)data);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(title);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack getItem(Material material, int amount, int data) {
        ItemStack itemStack = new ItemStack(material, amount, (byte)data);
        return itemStack;
    }

}