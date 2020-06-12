package perfect.galaxy.zones.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import perfect.galaxy.zones.utils.ItemBuilder;
import perfect.galaxy.zones.PerfectZones;

public class PMainMenu implements PMenu {

    private final int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);
    private final PerfectZones perfectZones;
    private final Player player;

    public PMainMenu(PerfectZones perfectZones, Player player){
        this.player = player;
        this.perfectZones = perfectZones;
        open();
    }

    public void open(){
        Inventory inventory = Bukkit.getServer().createInventory(null, 9*6, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Main.Name")));

        ItemStack decoration = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 9,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Main.Items.Decoration.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Main.Items.Decoration.Lore")));
        ItemStack creator = ItemBuilder.getItem(Material.BLAZE_ROD, 1, 0, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Main.Items.Creator.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Main.Items.Creator.Lore")));
        ItemStack allZones = ItemBuilder.getItem(Material.PAPER, 1, 0, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Main.Items.Zones.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Main.Items.Zones.Lore")));
        ItemStack users = ItemBuilder.getItem(Material.BOOK, 1, 0, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Main.Items.Users.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Main.Items.Users.Lore")));
        ItemStack close = ItemBuilder.getItem(Material.BARRIER, 1, 0, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Main.Items.Close.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Main.Items.Close.Lore")));
        ItemStack settings = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_TRIPWIRE_HOOK") : Material.valueOf("TRIPWIRE_HOOK"), 1, 0,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Main.Items.Settings.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Main.Items.Settings.Lore")));

        for(int i=0; i<54; i++){
            inventory.setItem(i, decoration);
        }

        inventory.setItem(11, creator);
        inventory.setItem(13, allZones);
        inventory.setItem(15, users);
        inventory.setItem(31, settings);
        inventory.setItem(49, close);

        player.openInventory(inventory);
    }

}