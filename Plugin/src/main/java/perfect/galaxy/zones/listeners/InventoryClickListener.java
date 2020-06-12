package perfect.galaxy.zones.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import perfect.galaxy.zones.PerfectZones;
import perfect.galaxy.zones.menu.*;
import perfect.galaxy.zones.user.PUserEditor;
import perfect.galaxy.zones.user.UserEditor;
import perfect.galaxy.zones.utils.AnvilGUI;
import perfect.galaxy.zones.utils.ItemBuilder;

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

        if (event.getCurrentItem() != null && event.getCurrentItem().getType() != null
                && event.getView().getTitle().equals(perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Zones.Name")))){
            if(event.getSlot() >= 0 && event.getSlot() < 36) {
                if (event.getCurrentItem() == null || event.getCurrentItem().getType() == null || event.getCurrentItem().getType() == Material.AIR) {
                    return;
                }

                if(!perfectZones.getUserEditorManager().isUserEditor(player.getUniqueId())){
                    perfectZones.getUserEditorManager().getEditors().add(new UserEditor(player.getUniqueId()));
                }
                perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setZone(perfectZones.getZoneManager().getZone(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())));

                player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                new PEditorMenu(perfectZones, player);
            } else {
                switch (event.getSlot()){
                    case 45:
                        if(event.getCurrentItem() == null || event.getCurrentItem().getType() == null || event.getCurrentItem().getType() == Material.AIR){
                            break;
                        }
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        new PZonesMenu(perfectZones, player).open(perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getPage() - 1);
                        setPage(player, perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getPage() - 1);
                        break;
                    case 46:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        new PMainMenu(perfectZones, player);
                        break;
                    case 48:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        new PCreatorsMenu(perfectZones, player);
                        break;
                    case 50:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);

                        if (perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getType() == PUserEditor.Type.NORMAL) {
                            perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setType(PUserEditor.Type.A_Z);
                        } else if (perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getType() == PUserEditor.Type.A_Z) {
                            perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setType(PUserEditor.Type.Z_A);
                        } else {
                            perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setType(PUserEditor.Type.NORMAL);
                        }

                        new PZonesMenu(perfectZones, player).open(perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getPage());

                        break;
                    case 51:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        player.closeInventory();

                        AnvilGUI GUI = new AnvilGUI(player, e -> {
                            if(e.getSlot() == AnvilGUI.AnvilSlot.OUTPUT && e.hasText()) {
                                player.closeInventory();
                                player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("BLOCK_NOTE_BLOCK_PLING") : Sound.valueOf("NOTE_PLING"), 2, 3);
                                perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setKey(e.getText());
                                perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setPageSearch(1);
                                new PSearchMenu(perfectZones, player).open(1, e.getText());
                            }
                        });
                        ItemStack i = new ItemStack(Material.PAPER);

                        GUI.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, i);
                        GUI.setSlotName(AnvilGUI.AnvilSlot.INPUT_LEFT, "Type the key");
                        GUI.setTitle("Search zone");
                        GUI.open();

                        break;
                    case 53:
                        if(event.getCurrentItem() == null || event.getCurrentItem().getType() == null || event.getCurrentItem().getType() == Material.AIR){
                            break;
                        }
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        new PZonesMenu(perfectZones, player).open(perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getPage() + 1);
                        setPage(player, perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getPage() + 1);
                        break;
                    case 52:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        player.closeInventory();
                    default:
                        break;
                }
            }
            event.setCancelled(true);
        }

        if(event.getView().getTitle().equals(perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Main.Name")))){
            event.setCancelled(true);
            if(event.getCurrentItem() != null && event.getCurrentItem().getType() != null){
                switch (event.getSlot()){
                    case 13:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setType(PUserEditor.Type.NORMAL);
                        perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setPage(1);

                        new PZonesMenu(perfectZones, player).open(perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getPage());

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

        if(event.getView().getTitle().equals(perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Settings.Name")))){
            event.setCancelled(true);
            if(event.getCurrentItem() != null && event.getCurrentItem().getType() != null) {
                switch (event.getSlot()) {
                    case 19:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        perfectZones.config.set("Zone.Title", !perfectZones.getFilesManager().getConfig().getBoolean("Zone.Title"));
                        event.getInventory().setItem(19, ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1,
                                (perfectZones.getFilesManager().getConfig().getBoolean("Zone.Title") ? 5 : 14),
                                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Settings.Items.Title_Click.Name")),
                                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Settings.Items.Title_Click.Lore"))));
                        perfectZones.config.save();
                        break;
                    case 21:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        perfectZones.config.set("Zone.Actionbar", !perfectZones.getFilesManager().getConfig().getBoolean("Zone.Actionbar"));
                        event.getInventory().setItem(21, ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1,
                                (perfectZones.getFilesManager().getConfig().getBoolean("Zone.Actionbar") ? 5 : 14),
                                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Settings.Items.Actionbar_Click.Name")),
                                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Settings.Items.Actionbar_Click.Lore"))));
                        perfectZones.config.save();
                        break;
                    case 23:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        perfectZones.config.set("Zone.Message", !perfectZones.getFilesManager().getConfig().getBoolean("Zone.Message"));
                        event.getInventory().setItem(23, ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1,
                                (perfectZones.getFilesManager().getConfig().getBoolean("Zone.Message") ? 5 : 14),
                                perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Settings.Items.Message_Click.Name")),
                                perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Settings.Items.Message_Click.Lore"))));
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
                                player.sendMessage(perfectZones.getFilesManager().getLang().parseColor(perfectZones.getFilesManager().getLang().getString("Messages.Zone.Menu.Success_Change_Default_Zone_Name").replace("%prefix%", perfectZones.getPrefix()).replace("%name%", e.getText())));
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
                    default:
                        break;
                }
            }
        }

        if(event.getView().getTitle().equals(perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Search.Name")))) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != null) {
                switch (event.getSlot()) {
                    case 36:
                        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == null || event.getCurrentItem().getType() == Material.AIR) {
                            break;
                        }
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        new PSearchMenu(perfectZones, player).open(perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getPageSearch() - 1, perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getKey());
                        setPageSearch(player,perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getPageSearch() - 1);
                        break;
                    case 39:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        new PZonesMenu(perfectZones, player).open(perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getPage());
                        break;
                    case 40:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        player.closeInventory();

                        AnvilGUI GUI = new AnvilGUI(player, e -> {
                            if(e.getSlot() == AnvilGUI.AnvilSlot.OUTPUT && e.hasText()) {
                                player.closeInventory();
                                player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("BLOCK_NOTE_BLOCK_PLING") : Sound.valueOf("NOTE_PLING"), 2, 3);
                                perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setKey(e.getText());
                                perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setPageSearch(1);
                                new PSearchMenu(perfectZones, player).open(1, e.getText());
                            }
                        });
                        ItemStack i = new ItemStack(Material.PAPER);

                        GUI.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, i);
                        GUI.setSlotName(AnvilGUI.AnvilSlot.INPUT_LEFT, "Type the key");
                        GUI.setTitle("Search zone");
                        GUI.open();

                        break;
                    case 41:
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        player.closeInventory();
                        break;
                    case 44:
                        if(event.getCurrentItem() == null || event.getCurrentItem().getType() == null || event.getCurrentItem().getType() == Material.AIR){
                            break;
                        }
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        new PSearchMenu(perfectZones, player).open(perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getPageSearch() + 1, perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getKey());
                        setPageSearch(player,perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getPageSearch() + 1);
                        break;
                    default:
                        break;
                }
            }
        }

        if(perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getZone() != null){
            if(event.getView().getTitle().equals(perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Editor.Name").replace("%zone%", perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getZone().getName())))) {
                if(event.getClickedInventory().getHolder() instanceof Player) {
                    player.sendMessage("Si");
                    if (event.getCurrentItem() != null && event.getCurrentItem().getType() != null) {
                        player.sendMessage("Si, material: " + event.getCurrentItem().getType().toString());
                        perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setMaterial(event.getCurrentItem().getType().toString());
                    }
                }
                event.setCancelled(true);
                if (event.getCurrentItem() != null && event.getCurrentItem().getType() != null) {
                    switch (event.getRawSlot()) {
                        case 11:
                            player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                            player.closeInventory();

                            AnvilGUI GUI = new AnvilGUI(player, e -> {
                                if(e.getSlot() == AnvilGUI.AnvilSlot.OUTPUT && e.hasText()) {
                                    player.closeInventory();
                                    player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("BLOCK_NOTE_BLOCK_PLING") : Sound.valueOf("NOTE_PLING"), 2, 3);

                                    if(perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getZone().getName().equals(e.getText())){
                                        new PEditorMenu(perfectZones, player);
                                        player.sendMessage(perfectZones.getFilesManager().getLang().parseColor(perfectZones.getFilesManager().getLang().getString("Messages.Zone.Menu.Same_Zone_Name").replace("%prefix%", perfectZones.getPrefix())));
                                        return;
                                    }

                                    if(perfectZones.getZoneManager().alreadyZone(e.getText())){
                                        new PEditorMenu(perfectZones, player);
                                        player.sendMessage(perfectZones.getFilesManager().getLang().parseColor(perfectZones.getFilesManager().getLang().getString("Messages.Zone.Menu.Already_Zone").replace("%prefix%", perfectZones.getPrefix()).replace("%name%", e.getText())));
                                        return;
                                    }

                                    perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getZone().setName(e.getText());
                                    new PEditorMenu(perfectZones, player);
                                }
                            });
                            ItemStack i = new ItemStack(Material.PAPER);

                            GUI.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, i);
                            GUI.setSlotName(AnvilGUI.AnvilSlot.INPUT_LEFT, "Type the new name");
                            GUI.setTitle("Rename zone");
                            GUI.open();

                            break;
                        case 15:
                            player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                            perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getZone().setDefault(!perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getZone().isDefault());
                            event.getInventory().setItem(15, ItemBuilder.getItem((versionId >= 13) ? Material.valueOf("LEGACY_STAINED_GLASS_PANE") : Material.valueOf("STAINED_GLASS_PANE"), 1,
                                    (perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getZone().isDefault()) ? 5 : 14,
                                    perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Editor.Items.Default_Zone.Name")),
                                    perfectZones.getFilesManager().getMenu().parseColorList(perfectZones.getFilesManager().getMenu().getList("Menu.Editor.Items.Default_Zone.Lore"))));
                            break;
                        case 38:
                            player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                            if(perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getMaterial() != null) {
                                perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getZone().setMaterial(perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getMaterial());
                            }

                            new PEditorMenu(perfectZones, player);
                            break;
                        case 48:
                            player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                            new PZonesMenu(perfectZones, player).open(perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getPage());
                            break;
                        case 50:
                            player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                            player.closeInventory();
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        if(perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getZone() != null) {
            if (event.getView().getTitle().equals(perfectZones.getFilesManager().getMenu().parseColor(perfectZones.getFilesManager().getMenu().getString("Menu.Items.Name").replace("%zone%", perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getZone().getName())))) {
                event.setCancelled(true);
                if (event.getCurrentItem() != null && event.getCurrentItem().getType() != null) {
                    if(event.getSlot() >= 0 && event.getSlot() < 36) {
                        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == null || event.getCurrentItem().getType() == Material.AIR) {
                            return;
                        }

                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                        perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getZone().setMaterial(event.getCurrentItem().getType().toString());
                        new PEditorMenu(perfectZones, player);
                    } else {
                        switch (event.getRawSlot()) {
                            case 48:
                                player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                                new PEditorMenu(perfectZones, player);
                                break;
                            case 50:
                                player.closeInventory();
                                player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("UI_BUTTON_CLICK") : Sound.valueOf("CLICK"), 2, 3);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

    private void setPage(Player player, int amount){
        perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setPage(amount);
    }

    private void setPageSearch(Player player, int amount){
        perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setPageSearch(amount);
    }

}