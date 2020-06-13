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

public class PDeleteMenu implements PMenu {

    private final int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);

    private final PerfectZones perfectZones;
    private final Player player;

    public PDeleteMenu(PerfectZones perfectZones, Player player) {
        this.perfectZones = perfectZones;
        this.player = player;

        open();
    }

    public void open() {
        Zone zone = perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getZone();

        Inventory inventory = Bukkit.getServer().createInventory(null, 9*4,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Delete.Name")));

        ItemStack decoration = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 9,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Delete.Items.Decoration.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Delete.Items.Decoration.Lore")));
        ItemStack accept = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_CLAY") : Material.valueOf("STAINED_CLAY"), 1, 5,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Delete.Items.Accept.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Delete.Items.Accept.Lore")));
        ItemStack deny = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_CLAY") : Material.valueOf("STAINED_CLAY"), 1, 14,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Delete.Items.Deny.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Delete.Items.Deny.Lore")));

        List<String> lore = new ArrayList<>();

        perfectZones.getFilesManager().getMenu().getList("Menu.Delete.Items.Zone.Lore").forEach(s -> lore.add(ChatColor.translateAlternateColorCodes('&', s.replace("%creator%", zone.getCreator()).replace("%date%", zone.getDate())
                .replace("%location_one%", Serialize.serializeLocation(zone.getDown())).replace("%location_two%", Serialize.serializeLocation(zone.getUp())+""))));

        ItemStack item = ItemBuilder.getItem(Material.valueOf(zone.getMaterial()), 1, zone.getData(),
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Delete.Items.Zone.Name").replace("%name%", zone.getName())),
                lore);

        for (int i=0; i<36; i++) {
            inventory.setItem(i, decoration);
        }

        inventory.setItem(21, accept);
        inventory.setItem(13, item);
        inventory.setItem(23, deny);

        player.openInventory(inventory);
    }

}