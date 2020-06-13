package perfect.galaxy.zones.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import perfect.galaxy.zones.utils.ItemBuilder;
import perfect.galaxy.zones.utils.PPageUtils;
import perfect.galaxy.zones.utils.Serialize;
import perfect.galaxy.zones.PerfectZones;
import perfect.galaxy.zones.managers.zone.Zone;

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
        Inventory inventory = Bukkit.getServer().createInventory(null, 9*5, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Search.Name")));

        ItemStack decoration = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 9,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Search.Items.Decoration.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Search.Items.Decoration.Lore")));
        ItemStack search = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("OAK_SIGN") : Material.valueOf("SIGN"), 1, 0, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Search.Items.Search.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Search.Items.Search.Lore")));
        ItemStack close = ItemBuilder.getItem(Material.BARRIER, 1, 0, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Search.Items.Close.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Search.Items.Close.Lore")));
        ItemStack back = ItemBuilder.getItem(Material.ARROW, 1, 0, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Search.Items.Back.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Search.Items.Back.Lore")));

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

                perfectZones.getFilesManager().getMenu().getList("Menu.Search.Items.Zone.Lore").forEach(s -> lore.add(ChatColor.translateAlternateColorCodes('&', s.replace("%creator%", zone.getCreator()).replace("%date%", zone.getDate())
                        .replace("%location_one%", (zone.getDown() != null) ? Serialize.serializeLocation(zone.getDown()) : perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Search.Items.Zone.No_Location")))
                        .replace("%location_two%", (zone.getUp() != null) ? Serialize.serializeLocation(zone.getUp()) : perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Search.Items.Zone.No_Location"))))));

                ItemStack item = ItemBuilder.getItem(Material.valueOf(zone.getMaterial()), 1, zone.getData(),
                        perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Search.Items.Zone.Name").replace("%name%", zone.getName())),
                        lore);

                items.add(item);
            });
        } else {
            ItemStack empty = ItemBuilder.getItem(Material.GLASS_BOTTLE, 1, 0,
                    perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Search.Items.No_Zone.Name")),
                    perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Search.Items.No_Zone.Lore")));
            inventory.setItem(13, empty);
        }

        ItemStack left;
        if(PPageUtils.isPageValid(items, page - 1, 27)) {
            left = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 14,
                    perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Search.Items.Back_Page.Name")),
                    perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Search.Items.Back_Page.Lore")));
        } else {
            left = new ItemStack(Material.AIR);
        }

        ItemStack right;
        if(PPageUtils.isPageValid(items, page + 1, 27)) {
            right = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 5,
                    perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Search.Items.Next_Page.Name")),
                    perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Search.Items.Next_Page.Lore")));
        } else {
            right = new ItemStack(Material.AIR);
        }

        if(!filters.isEmpty()){
            PPageUtils.getPageItems(items, page, 27).forEach(itemStack -> inventory.setItem(inventory.firstEmpty(), itemStack));
        } else {
            ItemStack empty = ItemBuilder.getItem(Material.GLASS_BOTTLE, 1, 0,
                    perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Search.Items.No_Zone.Name")),
                    perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Search.Items.No_Zone.Lore")));
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