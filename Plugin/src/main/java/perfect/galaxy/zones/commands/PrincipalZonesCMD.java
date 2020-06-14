package perfect.galaxy.zones.commands;

import me.fixeddev.ebcm.parametric.CommandClass;
import me.fixeddev.ebcm.parametric.annotation.ACommand;
import me.fixeddev.ebcm.parametric.annotation.Injected;

import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import perfect.galaxy.zones.PerfectZones;
import perfect.galaxy.zones.events.CreateZoneEvent;
import perfect.galaxy.zones.managers.zone.Zone;
import perfect.galaxy.zones.menu.PMainMenu;
import perfect.galaxy.zones.user.PUserEditor;
import perfect.galaxy.zones.user.UserEditor;
import perfect.galaxy.zones.utils.PSaveInventory;

import java.util.Arrays;

@ACommand(names = {"perfectzones", "pz", "pzones"}, desc = "PerfectZones principal command")
public class PrincipalZonesCMD implements CommandClass {

    private final PerfectZones perfectZones;

    public PrincipalZonesCMD(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
    }

    @ACommand(names = "create", desc = "PerfectZones create command")
    public boolean mainCommand(@Injected(true) CommandSender sender, String name){
        if(!(sender instanceof Player)){
            sender.sendMessage("Sorry! This command is only available for players!");
            return false;
        }
        Player player = (Player) sender;

        if(perfectZones.getZoneManager().alreadyZone(name)){
            player.sendMessage(perfectZones.getFilesManager().getLang().parseColor(perfectZones.getFilesManager().getLang().getString("Messages.Commands.Create.Already_Zone").replace("%prefix%", perfectZones.getPrefix()).replace("%name%", name)));
            return true;
        }

        Zone zone = new Zone(player.getName(), name, false);
        CreateZoneEvent createZoneEvent = new CreateZoneEvent(player, zone);
        Bukkit.getServer().getPluginManager().callEvent(createZoneEvent);

        if(createZoneEvent.isCancelled()) return true;

        perfectZones.getZoneManager().addNewSetuperfectZonesone(player.getUniqueId(), zone);
        perfectZones.getZoneManager().addZone(zone);
        player.sendMessage(perfectZones.getFilesManager().getLang().parseColor(perfectZones.getFilesManager().getLang().getString("Messages.Commands.Create.Success_Create").replace("%prefix%", perfectZones.getPrefix()).replace("%name%", name)));

        if(!perfectZones.getZoneManager().containsSaveInventory(player.getUniqueId())){
            putCreator(player);
            player.sendMessage(perfectZones.getFilesManager().getLang().parseColor(perfectZones.getFilesManager().getLang().getString("Messages.Commands.Create.Join_Creator").replace("%prefix%", perfectZones.getPrefix())));
        } else {
            player.sendMessage(perfectZones.getFilesManager().getLang().parseColor(perfectZones.getFilesManager().getLang().getString("Messages.Commands.Create.Already_Creator").replace("%prefix%", perfectZones.getPrefix())));
        }

        return true;
    }

    @ACommand(names = "menu", desc = "PerfectZones menu command")
    public boolean menuCommand(@Injected(true) CommandSender sender){
        if(!(sender instanceof Player)){
            sender.sendMessage("Sorry! This command is only available for players!");
            return false;
        }
        Player player = (Player) sender;

        if(!perfectZones.getUserEditorManager().isUserEditor(player.getUniqueId())) {
            perfectZones.getUserEditorManager().getEditors().add(new UserEditor(player.getUniqueId()));
        }

        perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setType(PUserEditor.Type.NORMAL);

        new PMainMenu(perfectZones, player);
        return true;
    }

    private void putCreator(Player player){
        PSaveInventory saveInventory = new PSaveInventory(player.getInventory().getContents(), player.getInventory().getArmorContents(), player.getGameMode(), player.getAllowFlight());
        perfectZones.getZoneManager().addSaveInventory(player.getUniqueId(), saveInventory);

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setGameMode(GameMode.CREATIVE);
        player.setAllowFlight(true);

        ItemStack wand = new ItemStack(Material.BLAZE_ROD);
        ItemMeta wandm = wand.getItemMeta();
        wandm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lCreator wand"));
        wandm.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7The best wand to create any type of zone!"),
                ChatColor.translateAlternateColorCodes('&', "&7"), ChatColor.translateAlternateColorCodes('&', "&9LEFT CLICK &bto select point #1"),
                ChatColor.translateAlternateColorCodes('&', "&9RIGHT CLICK &bto select point #2")));
        wandm.addEnchant(Enchantment.DURABILITY, 3, true);
        wandm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        wand.setItemMeta(wandm);
        player.getInventory().setItem(4, wand);
    }

}