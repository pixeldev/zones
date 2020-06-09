package perfect.zones.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import perfect.zones.PerfectZones;
import perfect.zones.menu.PZonesMenu;

public class InventoryClickListener implements Listener {

    private final PerfectZones pz;
    private final int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);

    public InventoryClickListener(PerfectZones pz) {
        this.pz = pz;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (pz.getZoneManager().containsPlayer(player.getUniqueId())) {
            event.setCancelled(true);
        }

        if (event.getCurrentItem() != null && event.getCurrentItem().getType() != null
                && event.getView().getTitle().equals(pz.getAllFiles().getMenu().parseColor(pz.getAllFiles().getMenu().getString("Menu.Zones.Name")))){
            if (event.getInventory().getItem(45) != null && event.getInventory().getItem(45).getType() != null
                 && event.getSlot() == 45 && event.getCurrentItem().getType().equals((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"))) {
                new PZonesMenu(pz, player).open(pz.getPageManager().getUserPage(player.getUniqueId()).getPage() - 1);
                setPage(player, pz.getPageManager().getUserPage(player.getUniqueId()).getPage() - 1);
            } else if (event.getInventory().getItem(53) != null && event.getInventory().getItem(53).getType() != null
                    && event.getSlot() == 53 && event.getCurrentItem().getType().equals((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"))) {
                new PZonesMenu(pz, player).open(pz.getPageManager().getUserPage(player.getUniqueId()).getPage() + 1);
                setPage(player, pz.getPageManager().getUserPage(player.getUniqueId()).getPage() + 1);
            }
            event.setCancelled(true);
        }
    }

    private void setPage(Player player, int amount){
        pz.getPageManager().getUserPage(player.getUniqueId()).setPage(amount);
    }

}