package perfect.zones.managers.zone;

import org.bukkit.Bukkit;
import perfect.zones.PerfectZones;
import perfect.zones.cuboid.PCuboid;
import perfect.zones.user.manager.PSaveInventory;
import perfect.zones.utils.Serialize;

import java.util.*;

public class ZoneManager {

    private final PerfectZones pz;
    private List<Zone> zones;
    private final Map<UUID, Zone> newSetupZone;
    private final Map<UUID, Map<Zone, ZoneSetup>> addSetupZone;
    private final Map<UUID, PSaveInventory> saveInventory;

    public ZoneManager(PerfectZones pz) {
        this.pz = pz;
        this.newSetupZone = new HashMap<>();
        this.saveInventory = new HashMap<>();
        this.addSetupZone = new HashMap<>();
    }

    public boolean alreadyZone(String name){
        for(Zone zone : this.zones){
            if(zone.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean containsSaveInventory(UUID player){
        return this.saveInventory.containsKey(player);
    }

    public void addSaveInventory(UUID player, PSaveInventory saveInventory){
        if(containsSaveInventory(player)) return;
        this.saveInventory.put(player, saveInventory);
    }

    public void removeSaveInventory(UUID player){
        if(!containsSaveInventory(player)) return;
        this.saveInventory.remove(player);
    }

    public Map<UUID, PSaveInventory> getSaveInventory() {
        return saveInventory;
    }

    public boolean containsPlayerAddSetupZone(UUID player){
        return this.addSetupZone.containsKey(player);
    }

    public void addAddSetupZone(UUID player, Map<Zone, ZoneSetup> zoneZoneSetupMap){
        if(containsPlayerAddSetupZone(player)) return;
        this.addSetupZone.put(player, zoneZoneSetupMap);
    }

    public void removeAddSetupZone(UUID player){
        if(!containsPlayerAddSetupZone(player)) return;
        this.addSetupZone.remove(player);
    }

    public Map<UUID, Map<Zone, ZoneSetup>> getAddSetupZone() {
        return addSetupZone;
    }

    public boolean containsPlayer(UUID player){
        return this.newSetupZone.containsKey(player);
    }

    public void addNewSetupZone(UUID player, Zone zone){
        if(containsPlayer(player)) return;
        this.newSetupZone.put(player, zone);
    }

    public void removeNewSetupZone(UUID player){
        if(!containsPlayer(player)) return;
        this.newSetupZone.remove(player);
    }

    public Map<UUID, Zone> getNewSetupZone() {
        return newSetupZone;
    }

    public boolean containsZone(Zone zone){
        return this.zones.contains(zone);
    }

    public void addZone(Zone zone){
        if(containsZone(zone)) return;
        this.zones.add(zone);
    }

    public void removeZone(Zone zone){
        if(!containsZone(zone)) return;
        this.zones.remove(zone);
    }

    public Zone getZone(String name){
        for(Zone zone : this.zones){
            return (zone.getName().equals(name)) ? zone : null;
        }
        return null;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void loadZones(){
        this.zones = new ArrayList<>();
        if(pz.getAllFiles().getData().contains("Zones")){
            for(String s : pz.getAllFiles().getData().getConfigurationSection("Zones").getKeys(false)){
                List<ZoneSetup> zoneSetups = new ArrayList<>();
                if(pz.getAllFiles().getData().contains("Zones." + s + ".Sub_Zones")){
                    for(String c : pz.getAllFiles().getData().getConfigurationSection("Zones." + s + ".Sub_Zones").getKeys(false)){
                        PCuboid cuboid = new PCuboid(Serialize.deserializeLocation(pz.getAllFiles().getData().getString("Zones." + s + ".Sub_Zones." + c + ".Location_DOWN")),
                                Serialize.deserializeLocation(pz.getAllFiles().getData().getString("Zones." + s + ".Sub_Zones." + c + ".Location_UP")));
                        ZoneSetup zoneSetup = new ZoneSetup(pz.getAllFiles().getData().getString("Zones." + s + ".Sub_Zones." + c + ".Creator"),
                                c, Serialize.deserializeLocation(pz.getAllFiles().getData().getString("Zones." + s + ".Sub_Zones." + c + ".Location_DOWN")),
                                Serialize.deserializeLocation(pz.getAllFiles().getData().getString("Zones." + s + ".Sub_Zones." + c + ".Location_UP")),
                                pz.getAllFiles().getData().getString("Zones." + s + ".Sub_Zones." + c + ".Date"), cuboid);
                        zoneSetups.add(zoneSetup);
                    }
                } else {
                    Bukkit.getConsoleSender().sendMessage("§9[§bPerfectZones§9] §cNo sub-zones founded for §b" + s + "§c...");
                }

                PCuboid cuboid = new PCuboid(Serialize.deserializeLocation(pz.getAllFiles().getData().getString("Zones." + s + ".Location_DOWN")),
                        Serialize.deserializeLocation(pz.getAllFiles().getData().getString("Zones." + s + ".Location_UP")));

                Zone zone = new Zone(pz.getAllFiles().getData().getString("Zones." + s + ".Creator"), s, Serialize.deserializeLocation(pz.getAllFiles().getData().getString("Zones." + s + ".Location_DOWN")),
                        Serialize.deserializeLocation(pz.getAllFiles().getData().getString("Zones." + s + ".Location_UP")), pz.getAllFiles().getData().getString("Zones." + s + ".Date"),
                        cuboid, pz.getAllFiles().getData().getBoolean("Zones." + s + ".Default"), zoneSetups,
                        pz.getAllFiles().getData().getString("Zones." + s + ".Material"));

                this.zones.add(zone);
            }
            Bukkit.getConsoleSender().sendMessage("§9[§bPerfectZones§9] §aLoading all zones...");
        } else {
            Bukkit.getConsoleSender().sendMessage("§9[§bPerfectZones§9] §cNo zones founded...");
        }
    }

    public void saveZones(){
        if(!this.zones.isEmpty()){
            for(Zone zone : this.zones) {
                pz.getAllFiles().getData().set("Zones." + zone.getName() + ".Creator", zone.getCreator());
                pz.getAllFiles().getData().set("Zones." + zone.getName() + ".Date", zone.getDate());
                pz.getAllFiles().getData().set("Zones." + zone.getName() + ".Location_UP", Serialize.serializeLocation(zone.getUp()));
                pz.getAllFiles().getData().set("Zones." + zone.getName() + ".Location_DOWN", Serialize.serializeLocation(zone.getDown()));
                pz.getAllFiles().getData().set("Zones." + zone.getName() + ".Default", zone.isDefault());
                pz.getAllFiles().getData().set("Zones." + zone.getName() + ".Material", zone.getMaterial());

                if(!zone.getZones().isEmpty()){
                    for(ZoneSetup zoneSetup : zone.getZones()){
                        pz.getAllFiles().getData().set("Zones." + zone.getName() + ".Sub_Zones." + zoneSetup.getName() + ".Creator", zoneSetup.getCreator());
                        pz.getAllFiles().getData().set("Zones." + zone.getName() + ".Sub_Zones." + zoneSetup.getName() + ".Date", zoneSetup.getDate());
                        pz.getAllFiles().getData().set("Zones." + zone.getName() + ".Sub_Zones." + zoneSetup.getName() + ".Location_UP", Serialize.serializeLocation(zoneSetup.getUp()));
                        pz.getAllFiles().getData().set("Zones." + zone.getName() + ".Sub_Zones." + zoneSetup.getName() + ".Location_DOWN", Serialize.serializeLocation(zoneSetup.getDown()));
                    }
                } else {
                    Bukkit.getConsoleSender().sendMessage("§9[§bPerfectZones§9] §cNo sub-zones founded for the zone §b" + zone.getName() + "§c...");
                }
            }
            Bukkit.getConsoleSender().sendMessage("§9[§bPerfectZones§9] §aSaving all zones...");
            pz.data.save();
        } else {
            Bukkit.getConsoleSender().sendMessage("§9[§bPerfectZones§9] §cNo zones founded...");
        }
    }
}