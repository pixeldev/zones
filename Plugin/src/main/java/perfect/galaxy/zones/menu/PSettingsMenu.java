package perfect.galaxy.zones.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import perfect.galaxy.zones.utils.ItemBuilder;
import perfect.galaxy.zones.PerfectZones;

public class PSettingsMenu implements PMenu {

    private final int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);

    private final Player player;
    private final PerfectZones perfectZones;

    public PSettingsMenu(Player player, PerfectZones perfectZones) {
        this.player = player;
        this.perfectZones = perfectZones;
    }

    public void open(){
        Inventory inventory = Bukkit.getServer().createInventory(null, 9*6, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Settings.Name")));

        ItemStack decoration = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 9,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Settings.Items.Decoration.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Settings.Items.Decoration.Lore")));
        ItemStack title = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_PAPER") : Material.valueOf("PAPER"), 1, 0,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Settings.Items.Title.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Settings.Items.Title.Lore")));
        ItemStack actionbar = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_PAPER") : Material.valueOf("PAPER"), 1, 0,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Settings.Items.Actionbar.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Settings.Items.Actionbar.Lore")));
        ItemStack message = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("OAK_SIGN") : Material.valueOf("SIGN"), 1, 0,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Settings.Items.Message.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Settings.Items.Message.Lore")));
        ItemStack defaultZone = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_ANVIL") : Material.valueOf("ANVIL"), 1, 0,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Settings.Items.Default_Zone.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Settings.Items.Default_Zone.Lore")));

        ItemStack close = ItemBuilder.getItem(Material.BARRIER, 1, 0, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Zones.Items.Close.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Settings.Items.Close.Lore")));
        ItemStack back = ItemBuilder.getItem(Material.ARROW, 1, 0, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Zones.Items.Back.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Settings.Items.Back.Lore")));

        ItemStack titlea = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1,
                (perfectZones.getFilesManager().getConfig().getBoolean("Zone.Title") ? 5 : 14),
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Settings.Items.Title_Click.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Settings.Items.Title_Click.Lore")));
        ItemStack actionbara = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1,
                (perfectZones.getFilesManager().getConfig().getBoolean("Zone.Actionbar") ? 5 : 14),
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Settings.Items.Actionbar_Click.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Settings.Items.Actionbar_Click.Lore")));
        ItemStack messagea = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1,
                (perfectZones.getFilesManager().getConfig().getBoolean("Zone.Message") ? 5 : 14),
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Settings.Items.Message_Click.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Settings.Items.Message_Click.Lore")));

        ItemStack defaultZonea = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_NAME_TAG") : Material.valueOf("NAME_TAG"), 1, 0,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Settings.Items.Default_Zone_Click.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Settings.Items.Default_Zone_Click.Lore")));

        for(int i=0; i<54; i++){
            inventory.setItem(i, decoration);
        }

        inventory.setItem(10, title);
        inventory.setItem(19, titlea);
        inventory.setItem(12, actionbar);
        inventory.setItem(21, actionbara);
        inventory.setItem(14, message);
        inventory.setItem(23, messagea);
        inventory.setItem(16, defaultZone);
        inventory.setItem(25, defaultZonea);
        inventory.setItem(48, back);
        inventory.setItem(50, close);

        player.openInventory(inventory);
    }
}