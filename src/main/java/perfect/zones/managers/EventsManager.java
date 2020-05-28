package perfect.zones.managers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import perfect.zones.PerfectZones;
import perfect.zones.listeners.*;

public class EventsManager {

    private PerfectZones pz;

    public EventsManager(PerfectZones pz) {
        this.pz = pz;
        register();
    }

    public void register(){
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new PlayerInteractListener(pz), pz);
        pm.registerEvents(new DropItemListener(pz), pz);
        pm.registerEvents(new PlayerJoinListener(pz), pz);
        pm.registerEvents(new InventoryClickListener(pz), pz);
        pm.registerEvents(new PlayerEnterZoneListener(pz), pz);
        pm.registerEvents(new PlayerMoveListener(pz), pz);
    }
}