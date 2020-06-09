package perfect.zones.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.concurrent.EventCountCircuitBreaker;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import perfect.zones.PerfectZones;
import perfect.zones.utils.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class PCreatorsMenu {

    private final int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);
    private final PerfectZones perfectZones;
    private final Player player;

    public PCreatorsMenu(PerfectZones perfectZones, Player player) {
        this.perfectZones = perfectZones;
        this.player = player;
        open();
    }

    @SuppressWarnings("deprecation")
    public void open(){
        Inventory inventory = Bukkit.getServer().createInventory(null, 9*4, perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Creators.Name")));

        ItemStack decoration = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1, 9,
                perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Creators.Items.Decoration.Name")),
                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Creators.Items.Decoration.Lore")));

        if(!perfectZones.getUserCreatorManager().getUsersCreator().isEmpty()){
            for(int i=0; i<perfectZones.getUserCreatorManager().getUsersCreator().size(); i++){
                List<String> skullLore = new ArrayList<>();
                for(int j=0; j<perfectZones.getUserCreatorManager().getUsersCreator().get(i).getZones().size(); j++){
                    if((j+1) > 10) {
                        skullLore.add(ChatColor.translateAlternateColorCodes('&', "&bAnd " + (perfectZones.getUserCreatorManager().getUsersCreator().get(i).getZones().size() - 10) + " &bmore..."));
                        break;
                    }
                    skullLore.add(ChatColor.translateAlternateColorCodes('&', "&9- " + perfectZones.getUserCreatorManager().getUsersCreator().get(i).getZones().get(j)));
                }

                ItemStack skull = ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_SKULL_ITEM") : Material.valueOf("SKULL_ITEM"), 1, 3,
                        perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Creators.Items.Creator.Name")
                                .replace("%name%", Bukkit.getOfflinePlayer(perfectZones.getUserCreatorManager().getUsersCreator().get(i).getUUID()).getName())),
                                skullLore);

                SkullMeta meta = (SkullMeta) skull.getItemMeta();
                meta.setOwner(Bukkit.getOfflinePlayer(perfectZones.getUserCreatorManager().getUsersCreator().get(i).getUUID()).getName());
                skull.setItemMeta(meta);
                inventory.setItem(i, skull);
            }
        } else {
            ItemStack empty = ItemBuilder.getItem(Material.GLASS_BOTTLE, 1, 0,
                    perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Creators.Items.No_Creator.Name")),
                    perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Creators.Items.No_Creator.Lore")));
            inventory.setItem(4, empty);
        }

        for(int i=18; i<27; i++){
            inventory.setItem(i, decoration);
        }

        player.openInventory(inventory);
    }
}