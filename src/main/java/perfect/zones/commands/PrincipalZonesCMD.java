package perfect.zones.commands;

import me.fixeddev.ebcm.parametric.CommandClass;
import me.fixeddev.ebcm.parametric.annotation.ACommand;
import me.fixeddev.ebcm.parametric.annotation.Injected;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import perfect.zones.PerfectZones;
import perfect.zones.events.CreateZoneEvent;
import perfect.zones.managers.Page;
import perfect.zones.managers.zone.Zone;
import perfect.zones.menu.PMainMenu;
import perfect.zones.menu.PMenuTest;
import perfect.zones.menu.PZonesMenu;
import perfect.zones.user.UserFilter;
import perfect.zones.user.manager.PSaveInventory;

import java.util.Arrays;

@ACommand(names = {"perfectzones", "pz", "pzones"})
public class PrincipalZonesCMD implements CommandClass {

    private final PerfectZones perfectZones;

    public PrincipalZonesCMD(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
    }

    @ACommand(names = "create")
    public boolean mainCommand(@Injected(true)CommandSender sender, String name){
        if(!(sender instanceof Player)){
            sender.sendMessage("Sorry! This command is only available for players!");
            return false;
        }
        Player player = (Player) sender;

        if(perfectZones.getZoneManager().alreadyZone(name)){
            player.sendMessage(perfectZones.getAllFiles().getLang().parseColor(perfectZones.getAllFiles().getLang().getString("Messages.Commands.Create.Already_Zone").replace("%prefix%", perfectZones.getPrefix()).replace("%name%", name)));
            return true;
        }

        Zone zone = new Zone(player.getName(), name, false);
        CreateZoneEvent createZoneEvent = new CreateZoneEvent(player, zone);
        Bukkit.getServer().getPluginManager().callEvent(createZoneEvent);

        if(createZoneEvent.isCancelled()) return true;

        perfectZones.getZoneManager().addNewSetuperfectZonesone(player.getUniqueId(), zone);
        perfectZones.getZoneManager().addZone(zone);
        player.sendMessage(perfectZones.getAllFiles().getLang().parseColor(perfectZones.getAllFiles().getLang().getString("Messages.Commands.Create.Success_Create").replace("%prefix%", perfectZones.getPrefix()).replace("%name%", name)));

        if(!perfectZones.getZoneManager().containsSaveInventory(player.getUniqueId())){
            putCreator(player);
            player.sendMessage(perfectZones.getAllFiles().getLang().parseColor(perfectZones.getAllFiles().getLang().getString("Messages.Commands.Create.Join_Creator").replace("%prefix%", perfectZones.getPrefix())));
        } else {
            player.sendMessage(perfectZones.getAllFiles().getLang().parseColor(perfectZones.getAllFiles().getLang().getString("Messages.Commands.Create.Already_Creator").replace("%prefix%", perfectZones.getPrefix())));
        }

        return true;
    }

    @ACommand(names = "menu")
    public boolean menuCommand(@Injected(true) CommandSender sender){
        if(!(sender instanceof Player)){
            sender.sendMessage("Sorry! This command is only available for players!");
            return false;
        }
        Player player = (Player) sender;
        PMainMenu menu = new PMainMenu(perfectZones, player);
        return true;
    }

    @ACommand(names = "test")
    public boolean testCommand(@Injected(true) CommandSender sender){
        Player player = (Player) sender;
        new PMenuTest(perfectZones, player);
        return true;
    }

    @ACommand(names = "menuzones")
    public boolean zonesMenuCommand(@Injected(true) CommandSender sender){
        if(!(sender instanceof Player)){
            sender.sendMessage("Sorry! This command is only available for players!");
            return false;
        }
        Player player = (Player) sender;
        if(!perfectZones.getUserFilterManager().isUserFilter(player.getUniqueId())){
            perfectZones.getUserFilterManager().getUsersFilter().add(new UserFilter(player.getUniqueId(), UserFilter.Type.NORMAL));
        }
        if(!perfectZones.getPageManager().isUser(player.getUniqueId())){
            new PZonesMenu(perfectZones, player);
            perfectZones.getPageManager().getPages().add(new Page(player.getUniqueId(), 1));
        } else {
            new PZonesMenu(perfectZones, player).open(perfectZones.getPageManager().getUserPage(player.getUniqueId()).getPage());
        }
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