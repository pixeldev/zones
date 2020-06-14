package perfect.galaxy.zones.listeners;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import perfect.galaxy.zones.PerfectZones;
import perfect.galaxy.zones.cuboid.PCuboid;
import perfect.galaxy.zones.managers.zone.Zone;
import perfect.galaxy.zones.menu.PCreatorMenu;
import perfect.galaxy.zones.utils.PSaveInventory;
import perfect.galaxy.zones.utils.Serialize;

import java.text.SimpleDateFormat;

public class PlayerInteractListener implements Listener {

    private final int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);
    private final PerfectZones perfectZones;

    public PlayerInteractListener(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack itemStack = event.getItem();

        if(perfectZones.getZoneManager().containsPlayer(player.getUniqueId())){
            if(itemStack != null && itemStack.getType() != null && !itemStack.getType().equals(Material.AIR)){
                if(!itemStack.getType().equals(Material.BLAZE_ROD)) return;
                if(!itemStack.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&b&lCreator wand"))) return;
                if(itemStack.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&b&lCreator wand"))){
                    if (itemStack.getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
                        event.setCancelled(true);
                        Zone zone = perfectZones.getZoneManager().getNewSetuperfectZonesone().get(player.getUniqueId());

                        if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
                            Location location = event.getClickedBlock().getLocation();
                            player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                            player.sendMessage(perfectZones.getFilesManager().getLang().parseColor(perfectZones.getFilesManager().getLang().getString("Messages.Zone.Setup.Point2_Select").replace("%prefix%", perfectZones.getPrefix())
                                    .replace("%location%", Serialize.serializeLocation(event.getClickedBlock().getLocation()))));
                            zone.setUp(location);
                        } else if (action.equals(Action.LEFT_CLICK_BLOCK)) {
                            Location location = event.getClickedBlock().getLocation();
                            player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                            player.sendMessage(perfectZones.getFilesManager().getLang().parseColor(perfectZones.getFilesManager().getLang().getString("Messages.Zone.Setup.Point1_Select").replace("%prefix%", perfectZones.getPrefix())
                                    .replace("%location%", Serialize.serializeLocation(event.getClickedBlock().getLocation()))));
                            zone.setDown(location);
                        }
                        if(zone.getUp() != null && zone.getDown() != null){
                            PCuboid cuboid = new PCuboid(zone.getDown(), zone.getUp());
                            zone.setCuboid(cuboid);
                            zone.setDate(new SimpleDateFormat("dd/MM/yyyy-hh:mm").format(System.currentTimeMillis()));
                            perfectZones.getZoneManager().removeNewSetuperfectZonesone(player.getUniqueId());
                            quitCreator(player);
                            player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("BLOCK_NOTE_BLOCK_PLING") : Sound.valueOf("NOTE_PLING"), 2, 3);
                            player.sendMessage(perfectZones.getFilesManager().getLang().parseColor(perfectZones.getFilesManager().getLang().getString("Messages.Zone.Setup.Success_Setup").replace("%prefix%", perfectZones.getPrefix()).replace("%name%", zone.getName())));

                            if(perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).isSetup()) {
                                new PCreatorMenu(perfectZones, player);
                            }
                        }
                    }
                }
            }
        }
    }

    public void quitCreator(Player player){
        PSaveInventory saveInventory = perfectZones.getZoneManager().getSaveInventory().get(player.getUniqueId());
        player.getInventory().setContents(saveInventory.getInventory());
        player.getInventory().setArmorContents(saveInventory.getArmor());
        player.setGameMode(saveInventory.getGameMode());
        player.setAllowFlight(saveInventory.isFly());
        perfectZones.getZoneManager().removeSaveInventory(player.getUniqueId());
    }
}