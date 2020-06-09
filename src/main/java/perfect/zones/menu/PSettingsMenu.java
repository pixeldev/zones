package perfect.zones.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import perfect.zones.PerfectZones;
import perfect.zones.utils.ItemBuilder;

public class PSettingsMenu {

    private final int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);

    private final Player player;
    private final PerfectZones perfectZones;

    public PSettingsMenu(Player player, PerfectZones perfectZones) {
        this.player = player;
        this.perfectZones = perfectZones;
    }

    public void open(){
        Inventory inventory = Bukkit.getServer().createInventory(null, 9*6, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Settings.Name")));

        ItemStack decoration = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 9,
                perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Settings.Items.Decoration.Name")),
                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Settings.Items.Decoration.Lore")));


        for(int i=0; i<54; i++){
            inventory.setItem(i, decoration);
        }

        player.openInventory(inventory);
    }
}