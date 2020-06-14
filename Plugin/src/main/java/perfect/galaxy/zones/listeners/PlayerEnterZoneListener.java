package perfect.galaxy.zones.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import perfect.galaxy.zones.PerfectZones;
import perfect.galaxy.zones.events.PlayerEnterZoneEvent;
import perfect.galaxy.zones.managers.zone.Zone;
import perfect.galaxy.zones.user.UserZone;
import perfect.versions.common.ActionbarMessenger;
import perfect.versions.common.TitleMessenger;

import java.util.List;

public class PlayerEnterZoneListener implements Listener {

    private final PerfectZones perfectZones;
    private final ActionbarMessenger actionbarMessenger;
    private final TitleMessenger titleMessenger;
    private final int versionId = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);

    public PlayerEnterZoneListener(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
        this.titleMessenger =  perfectZones.getVersionsManager().getVersionProviderRegistry().getVersionProvider(TitleMessenger.class)
                .getImplementation(perfectZones.getVersionsManager().getCurrentVersion());
        this.actionbarMessenger = perfectZones.getVersionsManager().getVersionProviderRegistry().getVersionProvider(ActionbarMessenger.class)
                .getImplementation(perfectZones.getVersionsManager().getCurrentVersion());
    }

    @EventHandler
    public void onEnter(PlayerEnterZoneEvent event){
        Player player = event.getPlayer();
        if(!event.isCancelled()) {
            if(!perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().isDefault()){
                if(!perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getFoundedZones().contains(perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone())){
                    if(!perfectZones.getUserZoneManager().isPlayerInZone(player.getUniqueId())){
                        perfectZones.getUserZoneManager().addPlayerInZone(player.getUniqueId());
                        perfectZones.getUserZoneManager().getUser(player.getUniqueId()).addFoundZone(perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone());
                        giveRewards(player);
                        player.playSound(player.getLocation(), (versionId >= 13) ? Sound.valueOf("ENTITY_PLAYER_LEVELUP") : Sound.valueOf("LEVEL_UP"), 2, 1);
                        sendActions(player, false);
                    }
                } else {
                    if(!perfectZones.getUserZoneManager().isPlayerInZone(player.getUniqueId())){
                        perfectZones.getUserZoneManager().addPlayerInZone(player.getUniqueId());
                        sendActions(player, true);
                    }
                }
            } else {
                if(!perfectZones.getUserZoneManager().isPlayerInZone(player.getUniqueId())){
                    perfectZones.getUserZoneManager().addPlayerInZone(player.getUniqueId());
                    sendActions(player, true);
                }
            }
        } else {
            perfectZones.getUserZoneManager().removePlayerInZone(player.getUniqueId());
            perfectZones.getUserZoneManager().getUser(player.getUniqueId()).setZone(null);

            perfectZones.getUserZoneManager().getUser(player.getUniqueId()).setZoneName(
                    perfectZones.getFilesManager().getConfig().parseColor(perfectZones.getFilesManager().getConfig().getString("Zone.Default_Zone"))
            );

            sendActions(player, true);
        }
    }

    private void giveRewards(Player player) {
        UserZone userZone = perfectZones.getUserZoneManager().getUser(player.getUniqueId());
        if(userZone.getZone().getRewards().isEmpty()) {
            return;
        }
        userZone.getZone().getRewards().forEach(s -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("%player%", player.getName())));
    }

    private void sendActions(Player player, boolean def) {
        if(def) {
            if(perfectZones.getFilesManager().getConfig().getBoolean("Zone.Message")) {
                if(!perfectZones.getFilesManager().getLang().getList("Messages.Zone.General.Message_Zone").isEmpty()) {
                    perfectZones.getFilesManager().getLang().getList("Messages.Zone.General.Message_Zone").forEach(s ->
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    s.replace("%player%", player.getName())
                                            .replace("%name%",
                                                    perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getName())))
                    );
                }
            }
            if(perfectZones.getFilesManager().getConfig().getBoolean("Zone.Title")) {
                titleMessenger.sendTitle(player,
                        perfectZones.getFilesManager().getLang().getString("Messages.Zone.General.Title_Zone")
                                .replace("%name%", perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getName()),
                            20, 60, 20
                );
            }
            if(perfectZones.getFilesManager().getConfig().getBoolean("Zone.Actionbar")) {
                actionbarMessenger.sendActionbar(player, perfectZones.getFilesManager().getLang().getString("Messages.Zone.General.Actionbar_Zone")
                        .replace("%name%", perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getName())
                );
            }
        } else {
            if(perfectZones.getFilesManager().getConfig().getBoolean("Zone.Message")) {
                if(!perfectZones.getFilesManager().getLang().getList("Messages.Zone.General.Message_New_Zone").isEmpty()) {
                    List<String> messagesRewards = perfectZones.getFilesManager().getLang().getList("Messages.Zone.General.Message_New_Zone");
                    if(!perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getRewards().isEmpty()) {
                        String joiner = String.join("\n", perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getMessagesRewards());
                        messagesRewards.forEach(s ->
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', s.
                                        replace("%player%", player.getName()).replace("%messages_rewards%", joiner)
                                        .replace("%name%", perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getName()))));
                    }
                }
            }
            if(perfectZones.getFilesManager().getConfig().getBoolean("Zone.Title")) {
                titleMessenger.sendTitle(player,
                        perfectZones.getFilesManager().getLang().getString("Messages.Zone.General.Title_New_Zone")
                                .replace("%name%", perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getName()),
                        20, 60, 20
                );
            }
            if(perfectZones.getFilesManager().getConfig().getBoolean("Zone.Actionbar")) {
                actionbarMessenger.sendActionbar(player, perfectZones.getFilesManager().getLang().getString("Messages.Zone.General.Actionbar_New_Zone")
                        .replace("%name%", perfectZones.getUserZoneManager().getUser(player.getUniqueId()).getZone().getName())
                );
            }
        }
    }
}