package perfect.galaxy.zones.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import perfect.galaxy.zones.PerfectZones;

public class AsyncPlayerChatListener implements Listener {

    private final PerfectZones perfectZones;

    public AsyncPlayerChatListener(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getZone() != null) {
            if (perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).isReward()) {
                event.setCancelled(true);
                if (event.getMessage().startsWith("/")) {
                    player.sendMessage(perfectZones.getFilesManager().getLang().parseColor(
                            perfectZones.getFilesManager().getLang().getString("Messages.Zone.General.Reward.No_Slash"))
                            .replace("%prefix%", perfectZones.getPrefix())
                    );
                    return;
                }

                if(event.getMessage().toLowerCase().contains("cancel")) {
                    player.sendMessage(
                            perfectZones.getFilesManager().getLang().parseColor(
                                    perfectZones.getFilesManager().getLang().getString("Messages.Zone.General.Cancel_Add_Reward")
                                    .replace("%prefix%", perfectZones.getPrefix())
                            )
                    );
                    perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setReward(false);
                    return;
                }

                perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).getZone().addReward(event.getMessage());
                perfectZones.getUserEditorManager().getUserEditor(player.getUniqueId()).setReward(false);
                player.sendMessage(perfectZones.getFilesManager().getLang().parseColor(
                        perfectZones.getFilesManager().getLang().getString("Messages.Zone.General.Reward.Success_Add_Reward")
                                .replace("%prefix%", perfectZones.getPrefix())
                                .replace("%reward%", event.getMessage())
                ));
            }
        }
    }
}