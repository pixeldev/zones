package perfect.zones.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import perfect.zones.PerfectZones;
import perfect.zones.managers.zone.Zone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PMenuTest {

    private final PerfectZones perfectZones;
    private final Player player;

    public PMenuTest(PerfectZones perfectZones, Player player) {
        this.perfectZones = perfectZones;
        this.player = player;
        open();
    }

    public void open(){
        Inventory inventory = Bukkit.getServer().createInventory(null, 9*6, "Test");

        List<Zone> zones = perfectZones.getZoneManager().getZones();

        zones.sort(Comparator.comparing(Zone::getName).reversed());

        List<ItemStack> items = new ArrayList<>();
        for(Zone zone: zones){
            ItemStack itemStack = new ItemStack(Material.PAPER);
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(zone.getName());
            itemStack.setItemMeta(meta);
            items.add(itemStack);
        }

        for(int i=0; i<items.size(); i++){
            inventory.setItem(i, items.get(i));
        }

        player.openInventory(inventory);
    }
}