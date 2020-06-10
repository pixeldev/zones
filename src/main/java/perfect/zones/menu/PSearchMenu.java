package perfect.zones.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import perfect.zones.PerfectZones;
import perfect.zones.managers.zone.Zone;
import perfect.zones.utils.ItemBuilder;
import perfect.zones.utils.PPageUtils;
import perfect.zones.utils.Serialize;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PSearchMenu {

    private final int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);

    private final PerfectZones perfectZones;
    private final Player player;

    public PSearchMenu(PerfectZones perfectZones, Player player) {
        this.perfectZones = perfectZones;
        this.player = player;
    }

    public void open(int page, String key){
        Inventory inventory = Bukkit.getServer().createInventory(null, 9*5, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Search.Name")));

        ItemStack decoration = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 9,
                perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Search.Items.Decoration.Name")),
                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Search.Items.Decoration.Lore")));
        ItemStack search = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("OAK_SIGN") : Material.valueOf("SIGN"), 1, 0, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Search.Items.Search.Name")),
                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Search.Items.Search.Lore")));
        ItemStack close = ItemBuilder.getItem(Material.BARRIER, 1, 0, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Search.Items.Close.Name")),
                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Search.Items.Close.Lore")));
        ItemStack back = ItemBuilder.getItem(Material.ARROW, 1, 0, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Search.Items.Back.Name")),
                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Search.Items.Back.Lore")));

        List<Zone> filters = new ArrayList<>();
        perfectZones.getZoneManager().getZones().forEach(zone -> {
            if(zone.getName().toLowerCase().contains(key.toLowerCase())){
                filters.add(zone);
            }
        });

        List<ItemStack> items = new ArrayList<>();
        if (!filters.isEmpty()) {
            filters.sort(Comparator.comparing(Zone::getName));
            filters.forEach(zone -> {
                List<String> lore = new ArrayList<>();

                perfectZones.getAllFiles().getMenu().getList("Menu.Search.Items.Zone.Lore").forEach(s -> lore.add(ChatColor.translateAlternateColorCodes('&', s.replace("%creator%", zone.getCreator()).replace("%date%", zone.getDate())
                        .replace("%location_one%", Serialize.serializeLocation(zone.getDown())).replace("%location_two%", Serialize.serializeLocation(zone.getUp()) + ""))));

                ItemStack item = ItemBuilder.getItem(Material.valueOf(zone.getMaterial()), 1, zone.getData(),
                        perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Search.Items.Zone.Name").replace("%name%", zone.getName())),
                        lore);

                items.add(item);
            });
        } else {
            ItemStack empty = ItemBuilder.getItem(Material.GLASS_BOTTLE, 1, 0,
                    perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Search.Items.No_Zone.Name")),
                    perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Search.Items.No_Zone.Lore")));
            inventory.setItem(13, empty);
        }

        ItemStack left;
        if(PPageUtils.isPageValid(items, page - 1, 27)) {
            left = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 14,
                    perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Search.Items.Back_Page.Name")),
                    perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Search.Items.Back_Page.Lore")));
        } else {
            left = new ItemStack(Material.AIR);
        }

        ItemStack right;
        if(PPageUtils.isPageValid(items, page + 1, 27)) {
            right = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 5,
                    perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Search.Items.Next_Page.Name")),
                    perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Search.Items.Next_Page.Lore")));
        } else {
            right = new ItemStack(Material.AIR);
        }

        if(!filters.isEmpty()){
            PPageUtils.getPageItems(items, page, 27).forEach(itemStack -> inventory.setItem(inventory.firstEmpty(), itemStack));
        } else {
            ItemStack empty = ItemBuilder.getItem(Material.GLASS_BOTTLE, 1, 0,
                    perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Search.Items.No_Zone.Name")),
                    perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Search.Items.No_Zone.Lore")));
            inventory.setItem(13, empty);
        }

        for(int i=27; i<36; i++){
            inventory.setItem(i, decoration);
        }

        inventory.setItem(39, back);
        inventory.setItem(41, close);
        inventory.setItem(36, left);
        inventory.setItem(40, search);
        inventory.setItem(44, right);

        player.openInventory(inventory);
    }

}