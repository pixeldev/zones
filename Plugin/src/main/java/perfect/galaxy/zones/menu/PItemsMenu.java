package perfect.galaxy.zones.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import perfect.galaxy.zones.PerfectZones;
import perfect.galaxy.zones.utils.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class PItemsMenu implements PMenu {

    private final int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);

    private final PerfectZones perfectZones;
    private final Player player;
    private final List<Material> materialList;

    {
        materialList = new ArrayList<>();
        materialList.add(Material.STONE); materialList.add(Material.APPLE); materialList.add(Material.DIAMOND_BLOCK);
        materialList.add((versionId >= 13) ? Material.valueOf("LEGACY_LEAVES") : Material.valueOf("LEAVES")); materialList.add(Material.GLASS); materialList.add(Material.DISPENSER);
        materialList.add((versionId >= 13) ? Material.valueOf("LEGACY_PISTON_BASE") : Material.valueOf("PISTON_BASE")); materialList.add(Material.LAPIS_BLOCK); materialList.add((versionId >= 13) ? Material.valueOf("LEGACY_WEB") : Material.valueOf("WEB"));
        materialList.add((versionId >= 13) ? Material.valueOf("LEGACY_WOOL") : Material.valueOf("WOOL")); materialList.add(Material.GOLD_BLOCK); materialList.add(Material.EMERALD_BLOCK);
        materialList.add(Material.TNT); materialList.add((versionId >= 13) ? Material.valueOf("LEGACY_NETHERRACK") : Material.valueOf("NETHERRACK")); materialList.add(Material.SOUL_SAND);
        materialList.add(Material.PUMPKIN); materialList.add(Material.BEACON); materialList.add(Material.QUARTZ_BLOCK);
        materialList.add(Material.CAKE); materialList.add(Material.BONE); materialList.add(Material.COBBLESTONE);
        materialList.add(Material.COOKIE); materialList.add(Material.FURNACE); materialList.add(Material.GLOWSTONE);
        materialList.add(Material.ICE); materialList.add(Material.PRISMARINE); materialList.add(Material.SANDSTONE);
        materialList.add(Material.SAND); materialList.add(Material.SPONGE); materialList.add(Material.COAL);
        materialList.add((versionId >= 13) ? Material.valueOf("LEGACY_ENCHANTMENT_TABLE") : Material.valueOf("ENCHANTMENT_TABLE")); materialList.add(Material.BOOKSHELF); materialList.add((versionId >= 13) ? Material.valueOf("LEGACY_ENDER_PORTAL_FRAME") : Material.valueOf("ENDER_PORTAL_FRAME"));
        materialList.add(Material.ANVIL); materialList.add(Material.SLIME_BLOCK); materialList.add(Material.HAY_BLOCK);
    }

    public PItemsMenu(PerfectZones perfectZones, Player player) {
        this.perfectZones = perfectZones;
        this.player = player;

        open();
    }

    public void open() {
        Inventory inventory = Bukkit.getServer().createInventory(null, 9*6, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Items.Name")));

        ItemStack decoration = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 9,
                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Items.Items.Decoration.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Items.Items.Decoration.Lore")));
        ItemStack close = ItemBuilder.getItem(Material.BARRIER, 1, 0, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Items.Items.Close.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Items.Items.Close.Lore")));
        ItemStack back = ItemBuilder.getItem(Material.ARROW, 1, 0, perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Items.Items.Back.Name")),
                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Items.Items.Back.Lore")));

        for(int i=0; i<materialList.size(); i++) {
            inventory.setItem(i, new ItemStack(materialList.get(i)));
        }

        for(int i=36; i<45; i++) {
            inventory.setItem(i, decoration);
        }

        inventory.setItem(48, back);
        inventory.setItem(50, close);

        player.openInventory(inventory);
    }
}