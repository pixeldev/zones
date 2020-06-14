package perfect.galaxy.zones.managers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import perfect.galaxy.zones.listeners.*;
import perfect.galaxy.zones.PerfectZones;

public class EventsManager {

    private final PerfectZones perfectZones;

    public EventsManager(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
        register();
    }

    public void register(){
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new PlayerInteractListener(perfectZones), perfectZones);
        pm.registerEvents(new DropItemListener(perfectZones), perfectZones);
        pm.registerEvents(new PlayerJoinListener(perfectZones), perfectZones);
        pm.registerEvents(new InventoryClickListener(perfectZones), perfectZones);
        pm.registerEvents(new PlayerEnterZoneListener(perfectZones), perfectZones);
        pm.registerEvents(new PlayerMoveListener(perfectZones), perfectZones);
        pm.registerEvents(new PlayerQuitListener(perfectZones), perfectZones);
        pm.registerEvents(new CreateZoneListener(perfectZones), perfectZones);
        pm.registerEvents(new AsyncPlayerChatListener(perfectZones), perfectZones);
    }
}