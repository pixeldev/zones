package perfect.galaxy.zones.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import perfect.galaxy.zones.managers.zone.Zone;

public class CreateZoneEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final Zone zone;
    private boolean cancelled = false;

    public CreateZoneEvent(Player player, Zone zone){
        this.player = player;
        this.zone = zone;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Zone getZone() {
        return this.zone;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}