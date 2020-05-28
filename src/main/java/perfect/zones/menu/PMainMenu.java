package perfect.zones.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import perfect.zones.PerfectZones;
import perfect.zones.utils.ItemBuilder;

public class PMainMenu {

    private int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);
    private PerfectZones perfectZones;
    private Player player;

    public PMainMenu(PerfectZones perfectZones, Player player){
        this.player = player;
        this.perfectZones = perfectZones;
        open();
    }

    public void open(){
        Inventory inventory = Bukkit.getServer().createInventory(null, 9*6, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Main.Name")));

        ItemStack decoration = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 9, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Main.Items.Decoration.Name")),
                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Main.Items.Decoration.Lore")));
        ItemStack creator = ItemBuilder.getItem(Material.BLAZE_ROD, 1, 0, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Main.Items.Creator.Name")),
                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Main.Items.Creator.Lore")));
        ItemStack allZones = ItemBuilder.getItem(Material.PAPER, 1, 0, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Main.Items.Zones.Name")),
                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Main.Items.Zones.Lore")));

        for(int i=0; i<9; i++){
            inventory.setItem(i, decoration);
        }

        for(int i=45; i<54; i++){
            inventory.setItem(i, decoration);
        }

        inventory.setItem(10, creator);
        inventory.setItem(12, allZones);

        player.openInventory(inventory);
    }

}