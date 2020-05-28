package perfect.zones.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import perfect.zones.PerfectZones;
import perfect.zones.cuboid.PCuboid;
import perfect.zones.managers.zone.Zone;
import perfect.zones.user.manager.PSaveInventory;
import perfect.zones.utils.Serialize;

import java.text.SimpleDateFormat;

public class PlayerInteractListener implements Listener {

    private final PerfectZones pz;

    public PlayerInteractListener(PerfectZones pz) {
        this.pz = pz;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack itemStack = event.getItem();

        if(pz.getZoneManager().containsPlayer(player.getUniqueId())){
            if(itemStack != null && itemStack.getType() != null && !itemStack.getType().equals(Material.AIR)){
                if(!itemStack.getType().equals(Material.BLAZE_ROD)) return;
                if(!itemStack.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&b&lCreator wand"))) return;
                if(itemStack.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&b&lCreator wand"))){
                    if (itemStack.getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
                        event.setCancelled(true);
                        Zone zone = pz.getZoneManager().getNewSetupZone().get(player.getUniqueId());

                        if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
                            Location location = event.getClickedBlock().getLocation();
                            player.playSound(player.getLocation(), Sound.CLICK, 2, 3);
                            player.sendMessage(pz.getAllFiles().getLang().parseColor(pz.getAllFiles().getLang().getString("Messages.Zone.Setup.Point2_Select").replace("%prefix%", pz.getPrefix())
                                    .replace("%location%", Serialize.serializeLocation(event.getClickedBlock().getLocation()))));
                            zone.setUp(location);
                        } else if (action.equals(Action.LEFT_CLICK_BLOCK)) {
                            Location location = event.getClickedBlock().getLocation();
                            player.playSound(player.getLocation(), Sound.CLICK, 2, 3);
                            player.sendMessage(pz.getAllFiles().getLang().parseColor(pz.getAllFiles().getLang().getString("Messages.Zone.Setup.Point1_Select").replace("%prefix%", pz.getPrefix())
                                    .replace("%location%", Serialize.serializeLocation(event.getClickedBlock().getLocation()))));
                            zone.setDown(location);
                        }
                        if(zone.getUp() != null && zone.getDown() != null){
                            PCuboid cuboid = new PCuboid(zone.getDown(), zone.getUp());
                            zone.setCuboid(cuboid);
                            zone.setDate(new SimpleDateFormat("dd/MM/yyyy-hh:mm").format(System.currentTimeMillis()));
                            pz.getZoneManager().removeNewSetupZone(player.getUniqueId());
                            quitCreator(player);
                            player.playSound(player.getLocation(), Sound.NOTE_PLING, 2, 3);
                            player.sendMessage(pz.getAllFiles().getLang().parseColor(pz.getAllFiles().getLang().getString("Messages.Zone.Setup.Success_Setup").replace("%prefix%", pz.getPrefix()).replace("%name%", zone.getName())));
                        }
                    }
                }
            }
        }
    }

    public void quitCreator(Player player){
        PSaveInventory saveInventory = pz.getZoneManager().getSaveInventory().get(player.getUniqueId());
        player.getInventory().setContents(saveInventory.getInventory());
        player.getInventory().setArmorContents(saveInventory.getArmor());
        player.setGameMode(saveInventory.getGameMode());
        player.setAllowFlight(saveInventory.isFly());
        pz.getZoneManager().removeSaveInventory(player.getUniqueId());
    }
}