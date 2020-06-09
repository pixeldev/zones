package perfect.zones.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import perfect.zones.PerfectZones;
import perfect.zones.managers.zone.Zone;
import perfect.zones.user.UserFilter;
import perfect.zones.utils.ItemBuilder;
import perfect.zones.utils.PPageUtils;
import perfect.zones.utils.Serialize;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PZonesMenu {

    private final int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);
    private final Player player;
    private final UserFilter.Type type;
    private final PerfectZones perfectZones;

    public PZonesMenu(PerfectZones perfectZones, Player player, UserFilter.Type type) {
        this.type = type;
        this.player = player;
        this.perfectZones = perfectZones;
    }

    public void open(int page){
        Inventory inventory = Bukkit.getServer().createInventory(null, 9*6, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Zones.Name")));

        ItemStack decoration = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 9,
                perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Zones.Items.Decoration.Name")),
                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Zones.Items.Decoration.Lore")));
        ItemStack creator = ItemBuilder.getItem(Material.BLAZE_ROD, 1, 0, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Zones.Items.Creator.Name")),
                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Zones.Items.Creator.Lore")));
        ItemStack filter = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_HOPPER") : Material.valueOf("HOPPER"), 1, 0, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Zones.Items.Filter.Name")),
                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Zones.Items.Filter.Lore")));
        ItemStack byCreator = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_NAME_TAG") : Material.valueOf("NAME_TAG"), 1, 0, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Zones.Items.ByCreator.Name")),
                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Zones.Items.ByCreator.Lore")));
        ItemStack close = ItemBuilder.getItem(Material.BARRIER, 1, 0, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Zones.Items.Close.Name")),
                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Zones.Items.Close.Lore")));
        ItemStack back = ItemBuilder.getItem(Material.ARROW, 1, 0, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Zones.Items.Back.Name")),
                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Zones.Items.Back.Lore")));

        List<ItemStack> zones = new ArrayList<>();
        List<String> names = new LinkedList<>();
        if(!perfectZones.getZoneManager().getZones().isEmpty()){
            for(Zone zone : perfectZones.getZoneManager().getZones()){
                List<String> lore = new ArrayList<>();

                for(String s : perfectZones.getAllFiles().getMenu().getList("Menu.Zones.Items.Zone.Lore")){
                    lore.add(ChatColor.translateAlternateColorCodes('&', s.replace("%creator%", zone.getCreator()).replace("%date%", zone.getDate())
                            .replace("%location_one%", Serialize.serializeLocation(zone.getDown())).replace("%location_two%", Serialize.serializeLocation(zone.getUp())+"")));
                }

                ItemStack item = ItemBuilder.getItem(Material.valueOf(zone.getMaterial()), 1, zone.getData(),
                        perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Zones.Items.Zone.Name").replace("%name%", zone.getName())),
                        lore);

                zones.add(item);
            }
        } else {
            ItemStack empty = ItemBuilder.getItem(Material.GLASS_BOTTLE, 1, 0,
                    perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Zones.Items.No_Zone.Name")),
                    perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Zones.Items.No_Zone.Lore")));
            inventory.setItem(13, empty);
        }

        ItemStack left;
        if(PPageUtils.isPageValid(zones, page - 1, 36)) {
            left = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 14,
                    perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Zones.Items.Back_Page.Name")),
                    perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Zones.Items.Back_Page.Lore")));
        } else {
            left = new ItemStack(Material.AIR);
        }

        ItemStack right;
        if(PPageUtils.isPageValid(zones, page + 1, 36)) {
            right = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 5,
                    perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Zones.Items.Next_Page.Name")),
                    perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Zones.Items.Next_Page.Lore")));
        } else {
            right = new ItemStack(Material.AIR);
        }

        if(!zones.isEmpty()){
            if(type == UserFilter.Type.NORMAL){
                for(ItemStack itemStack : PPageUtils.getPageItems(zones, page, 36)){
                    inventory.setItem(inventory.firstEmpty(), itemStack);
                }
            } else if (type == UserFilter.Type.A_Z){
                for(ItemStack itemStack : PPageUtils.getPageItems(zones, page, 36)){
                    inventory.setItem(inventory.firstEmpty(), itemStack);
                }
            }
        } else {
            ItemStack empty = ItemBuilder.getItem(Material.GLASS_BOTTLE, 1, 0,
                    perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Zones.Items.No_Zone.Name")),
                    perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Zones.Items.No_Zone.Lore")));
            inventory.setItem(13, empty);
        }

        for(int i = 36; i < 45; i++){
            inventory.setItem(i, decoration);
        }

        inventory.setItem(49, creator);
        inventory.setItem(45, left);
        inventory.setItem(52, close);
        inventory.setItem(46, back);
        inventory.setItem(53, right);
        inventory.setItem(50, filter);
        inventory.setItem(48, byCreator);

        player.openInventory(inventory);
    }
}