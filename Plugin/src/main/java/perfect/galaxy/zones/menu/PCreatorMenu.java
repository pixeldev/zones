package perfect.galaxy.zones.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import perfect.galaxy.zones.PerfectZones;
import perfect.galaxy.zones.managers.zone.Zone;
import perfect.galaxy.zones.utils.ItemBuilder;
import perfect.galaxy.zones.utils.Serialize;

import java.util.ArrayList;
import java.util.List;

public class PCreatorMenu implements PMenu {

    private final int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);

    private final PerfectZones perfectZones;
    private final Player player;

    public PCreatorMenu(PerfectZones perfectZones, Player player) {
        this.perfectZones = perfectZones;
        this.player = player;

        open();
    }

    public void open() {
        Zone zone = perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getZone();
        Inventory inventory = Bukkit.getServer().createInventory(null, 9*6,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Creator.Name")));

        ItemStack decoration = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 9,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Creator.Items.Decoration.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Creator.Items.Decoration.Lore")));

        List<String> zonel = new ArrayList<>();
        perfectZones.getFilesManager().getMenu().getList("Menu.Creator.Items.Zone.Lore").forEach(s -> zonel.add(ChatColor.translateAlternateColorCodes('&', s.replace("%creator%", zone.getCreator()).replace("%date%", zone.getDate())
                .replace("%location_one%", (zone.getDown() != null) ? Serialize.serializeLocation(zone.getDown()) : perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Creator.Items.Zone.No_Location")))
                .replace("%location_two%", (zone.getUp() != null) ? Serialize.serializeLocation(zone.getUp()) : perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Creator.Items.Zone.No_Location"))))));

        ItemStack zonea = ItemBuilder.getItem(Material.valueOf(zone.getMaterial()), 1, zone.getData(),
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Creator.Items.Zone.Name").replace("%name%", zone.getName())),
                zonel);
        ItemStack changeItem = ItemBuilder.getItem(Material.CHEST, 1, 0,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Creator.Items.Change_Item.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Creator.Items.Change_Item.Lore")));

        List<String> r = zone.getRewards();
        List<String> rewardsl = new ArrayList<>();

        if(!r.isEmpty()){
            for(int i=0; i<r.size(); i++) {
                if ((i + 1) > 10) {
                    rewardsl.add(perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Creator.Items.Rewards.Lore").replace("%amount%", (r.size() - 10) + "")));
                    break;
                }
                rewardsl.add(ChatColor.translateAlternateColorCodes('&', "&9- " + r.get(i)));
            }
        } else {
            rewardsl.add(perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Creator.Items.Rewards.No_Rewards")));
        }

        ItemStack rewards = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_PAPER") : Material.valueOf("PAPER"), 1, 0,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Creator.Items.Rewards.Name")),
                rewardsl);
        ItemStack defaultZone = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1,
                (zone.isDefault()) ? 5 : 14, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Creator.Items.Default_Zone.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Creator.Items.Default_Zone.Lore")));
        ItemStack delete = ItemBuilder.getItem(Material.REDSTONE_BLOCK, 1, 0,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Creator.Items.Delete.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Creator.Items.Delete.Lore")));
        ItemStack rename = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_NAME_TAG") : Material.valueOf("NAME_TAG"), 1, 0,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Creator.Items.Rename.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Creator.Items.Rename.Lore")));
        ItemStack setup = ItemBuilder.getItem(Material.BLAZE_ROD, 1, 0,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Creator.Items.Setup.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Creator.Items.Setup.Lore")));
        ItemStack close = ItemBuilder.getItem(Material.BARRIER, 1, 0, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Creator.Items.Close.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Creator.Items.Close.Lore")));
        ItemStack back = ItemBuilder.getItem(Material.ARROW, 1, 0, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Creator.Items.Back.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Creator.Items.Back.Lore")));

        for(int i=0; i<54; i++){
            inventory.setItem(i, decoration);
        }

        inventory.setItem(4, zonea);
        inventory.setItem(38, changeItem);
        inventory.setItem(42, delete);
        inventory.setItem(11, rename);
        inventory.setItem(15, defaultZone);
        inventory.setItem(22, rewards);
        inventory.setItem(48, back);
        inventory.setItem(50, close);
        inventory.setItem(31, setup);

        player.openInventory(inventory);
    }

}