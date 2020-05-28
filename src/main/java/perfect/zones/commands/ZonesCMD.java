package perfect.zones.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import perfect.zones.PerfectZones;
import perfect.zones.events.CreateZoneEvent;
import perfect.zones.managers.zone.Zone;
import perfect.zones.menu.PMainMenu;
import perfect.zones.user.manager.PSaveInventory;

import java.util.Arrays;

public class ZonesCMD implements CommandExecutor {

    private PerfectZones pz;

    public ZonesCMD(PerfectZones pz) {
        this.pz = pz;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("[PerfectZones] This command is only available for players!");
            return false;
        } else {
            Player player = (Player) sender;

            if(args.length > 0){
                switch (args[0].toLowerCase()){
                    case "create":
                        //zones create name
                        if(player.hasPermission("perfectzones.admin") || player.hasPermission("perfectzones.create")){
                            if(args.length >= 3){
                                String name = args[1];
                                boolean def = Boolean.parseBoolean(args[2]);

                                if(pz.getZoneManager().alreadyZone(name)){
                                    player.sendMessage(pz.getAllFiles().getLang().parseColor(pz.getAllFiles().getLang().getString("Messages.Commands.Create.Already_Zone").replace("%prefix%", pz.getPrefix()).replace("%name%", name)));
                                    break;
                                }

                                Zone zone = new Zone(player.getName(), name, def);
                                CreateZoneEvent createZoneEvent = new CreateZoneEvent(player, zone);
                                Bukkit.getServer().getPluginManager().callEvent(createZoneEvent);

                                if(createZoneEvent.isCancelled()) break;

                                pz.getZoneManager().addNewSetupZone(player.getUniqueId(), zone);
                                pz.getZoneManager().addZone(zone);
                                player.sendMessage(pz.getAllFiles().getLang().parseColor(pz.getAllFiles().getLang().getString("Messages.Commands.Create.Success_Create").replace("%prefix%", pz.getPrefix()).replace("%name%", name)));

                                if(!pz.getZoneManager().containsSaveInventory(player.getUniqueId())){
                                    putCreator(player);
                                    player.sendMessage(pz.getAllFiles().getLang().parseColor(pz.getAllFiles().getLang().getString("Messages.Commands.Create.Join_Creator").replace("%prefix%", pz.getPrefix())));
                                } else {
                                    player.sendMessage(pz.getAllFiles().getLang().parseColor(pz.getAllFiles().getLang().getString("Messages.Commands.Create.Already_Creator").replace("%prefix%", pz.getPrefix())));
                                    break;
                                }
                            } else {
                                player.sendMessage(pz.getAllFiles().getLang().parseColor(pz.getAllFiles().getLang().getString("Messages.Commands.Create.Invalid_Use").replace("%prefix%", pz.getPrefix())));
                                break;
                            }
                        } else {
                            player.sendMessage(pz.getAllFiles().getLang().parseColor(pz.getAllFiles().getLang().getString("Messages.No_Permission").replace("%prefix%", pz.getPrefix())));
                            break;
                        }
                        break;
                    default:
                        //Send help
                        break;
                }
            } else {
                PMainMenu menu = new PMainMenu(pz, player);
                //Send help
            }
            return true;
        }
    }

    public void putCreator(Player player){
        PSaveInventory saveInventory = new PSaveInventory(player.getInventory().getContents(), player.getInventory().getArmorContents(), player.getGameMode(), player.getAllowFlight());
        pz.getZoneManager().addSaveInventory(player.getUniqueId(), saveInventory);

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