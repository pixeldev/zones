package perfect.zones.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import perfect.zones.PerfectZones;
import perfect.zones.managers.Page;
import perfect.zones.menu.PCreatorsMenu;
import perfect.zones.menu.PMainMenu;
import perfect.zones.menu.PSettingsMenu;
import perfect.zones.menu.PZonesMenu;
import perfect.zones.user.UserFilter;
import perfect.zones.utils.AnvilGUI;
import perfect.zones.utils.ItemBuilder;

public class InventoryClickListener implements Listener {

    private final PerfectZones perfectZones;
    private final int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);

    public InventoryClickListener(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(event.getInventory().getType() != InventoryType.CHEST || event.getView().getTitle() == null || event.getClickedInventory().getType() != InventoryType.CHEST) {
            return;
        }

        if (perfectZones.getZoneManager().containsPlayer(player.getUniqueId())) {
            event.setCancelled(true);
        }

        if (event.getCurrentItem() != null && event.getCurrentItem().getType() != null
                && event.getView().getTitle().equals(perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Zones.Name")))){
            switch (event.getSlot()){
                case 45:
                    if(event.getCurrentItem() == null || event.getCurrentItem().getType() == null || event.getCurrentItem().getType() == Material.AIR){
                        break;
                    }
                    player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                    new PZonesMenu(perfectZones, player, perfectZones.getUserFilterManager().getUserFilter(player.getUniqueId()).getType()).open(perfectZones.getPageManager().getUserPage(player.getUniqueId()).getPage() - 1);
                    setPage(player, perfectZones.getPageManager().getUserPage(player.getUniqueId()).getPage() - 1);
                    break;
                case 46:
                    player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                    new PMainMenu(perfectZones, player);
                    break;
                case 48:
                    player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                    new PCreatorsMenu(perfectZones, player);
                    break;
                case 53:
                    if(event.getCurrentItem() == null || event.getCurrentItem().getType() == null || event.getCurrentItem().getType() == Material.AIR){
                        break;
                    }
                    player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                    new PZonesMenu(perfectZones, player, perfectZones.getUserFilterManager().getUserFilter(player.getUniqueId()).getType()).open(perfectZones.getPageManager().getUserPage(player.getUniqueId()).getPage() + 1);
                    setPage(player, perfectZones.getPageManager().getUserPage(player.getUniqueId()).getPage() + 1);
                    break;
                case 52:
                    player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                    player.closeInventory();
                default:
                    break;
            }
            event.setCancelled(true);
        }

        if(event.getView().getTitle().equals(perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Main.Name")))){
            event.setCancelled(true);
            if(event.getCurrentItem() != null && event.getCurrentItem().getType() != null){
                switch (event.getSlot()){
                    case 13:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        if(!perfectZones.getUserFilterManager().isUserFilter(player.getUniqueId())){
                            perfectZones.getUserFilterManager().getUsersFilter().add(new UserFilter(player.getUniqueId(), UserFilter.Type.NORMAL));
                        }
                        if(!perfectZones.getPageManager().isUser(player.getUniqueId())){
                            new PZonesMenu(perfectZones, player, perfectZones.getUserFilterManager().getUserFilter(player.getUniqueId()).getType()).open(1);
                            perfectZones.getPageManager().getPages().add(new Page(player.getUniqueId(), 1));
                        } else {
                            new PZonesMenu(perfectZones, player, perfectZones.getUserFilterManager().getUserFilter(player.getUniqueId()).getType()).open(perfectZones.getPageManager().getUserPage(player.getUniqueId()).getPage());
                        }
                        break;
                    case 31:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        new PSettingsMenu(player, perfectZones).open();
                        break;
                    case 49:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        player.closeInventory();
                        break;
                    default:
                        break;
                }
            }
        }

        if(event.getView().getTitle().equals(perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Settings.Name")))){
            event.setCancelled(true);
            if(event.getCurrentItem() != null && event.getCurrentItem().getType() != null) {
                switch (event.getSlot()) {
                    case 19:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        perfectZones.config.set("Zone.Title", !perfectZones.getAllFiles().getConfig().getBoolean("Zone.Title"));
                        event.getInventory().setItem(19, ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1,
                                (perfectZones.getAllFiles().getConfig().getBoolean("Zone.Title") ? 5 : 14),
                                perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Settings.Items.Title_Click.Name")),
                                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Settings.Items.Title_Click.Lore"))));
                        perfectZones.config.save();
                        break;
                    case 21:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        perfectZones.config.set("Zone.Actionbar", !perfectZones.getAllFiles().getConfig().getBoolean("Zone.Actionbar"));
                        event.getInventory().setItem(21, ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1,
                                (perfectZones.getAllFiles().getConfig().getBoolean("Zone.Actionbar") ? 5 : 14),
                                perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Settings.Items.Actionbar_Click.Name")),
                                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Settings.Items.Actionbar_Click.Lore"))));
                        perfectZones.config.save();
                        break;
                    case 23:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        perfectZones.config.set("Zone.Message", !perfectZones.getAllFiles().getConfig().getBoolean("Zone.Message"));
                        event.getInventory().setItem(23, ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1,
                                (perfectZones.getAllFiles().getConfig().getBoolean("Zone.Message") ? 5 : 14),
                                perfectZones.getAllFiles().getMenu().parseColor(perfectZones.getAllFiles().getMenu().getString("Menu.Settings.Items.Message_Click.Name")),
                                perfectZones.getAllFiles().getMenu().parseColorList(perfectZones.getAllFiles().getMenu().getList("Menu.Settings.Items.Message_Click.Lore"))));
                        perfectZones.config.save();
                        break;
                    case 25:
                        player.closeInventory();
                        AnvilGUI GUI = new AnvilGUI(player, e -> {
                            if(e.getSlot() == AnvilGUI.AnvilSlot.OUTPUT && e.hasText()) {
                                e.setWillClose(true);
                                perfectZones.config.set("Zone.Default_Zone", e.getText());
                                perfectZones.config.save();
                                player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("BLOCK_NOTE_BLOCK_PLING") : Sound.valueOf("NOTE_PLING"), 2, 3);
                                player.sendMessage(perfectZones.getAllFiles().getLang().parseColor(perfectZones.getAllFiles().getLang().getString("Messages.Zone.Menu.Success_Change_Default_Zone_Name").replace("%prefix%", perfectZones.getPrefix()).replace("%name%", e.getText())));
                            }
                        });
                        ItemStack i = new ItemStack(Material.PAPER);

                        GUI.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, i);
                        GUI.setSlotName(AnvilGUI.AnvilSlot.INPUT_LEFT, "Type the name");
                        GUI.setTitle("Default Zone name");
                        GUI.open();
                        break;
                    case 48:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        new PMainMenu(perfectZones, player);
                        break;
                    case 50:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        player.closeInventory();
                        break;
                }
            }
        }
    }

    private void setPage(Player player, int amount){
        perfectZones.getPageManager().getUserPage(player.getUniqueId()).setPage(amount);
    }

}