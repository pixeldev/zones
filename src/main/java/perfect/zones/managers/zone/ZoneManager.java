package perfect.zones.managers.zone;

import org.bukkit.Bukkit;
import perfect.zones.PerfectZones;
import perfect.zones.cuboid.PCuboid;
import perfect.zones.user.manager.PSaveInventory;
import perfect.zones.utils.Serialize;

import java.util.*;

public class ZoneManager {

    private final PerfectZones perfectZones;
    private List<Zone> zones;
    private final Map<UUID, Zone> newSetuperfectZonesone;
    private final Map<UUID, Map<Zone, ZoneSetup>> addSetuperfectZonesone;
    private final Map<UUID, PSaveInventory> saveInventory;

    public ZoneManager(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;
        this.newSetuperfectZonesone = new HashMap<>();
        this.saveInventory = new HashMap<>();
        this.addSetuperfectZonesone = new HashMap<>();
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

    public boolean containsPlayerAddSetuperfectZonesone(UUID player){
        return this.addSetuperfectZonesone.containsKey(player);
    }

    public void addAddSetuperfectZonesone(UUID player, Map<Zone, ZoneSetup> zoneZoneSetupMap){
        if(containsPlayerAddSetuperfectZonesone(player)) return;
        this.addSetuperfectZonesone.put(player, zoneZoneSetupMap);
    }

    public void removeAddSetuperfectZonesone(UUID player){
        if(!containsPlayerAddSetuperfectZonesone(player)) return;
        this.addSetuperfectZonesone.remove(player);
    }

    public Map<UUID, Map<Zone, ZoneSetup>> getAddSetuperfectZonesone() {
        return addSetuperfectZonesone;
    }

    public boolean containsPlayer(UUID player){
        return this.newSetuperfectZonesone.containsKey(player);
    }

    public void addNewSetuperfectZonesone(UUID player, Zone zone){
        if(containsPlayer(player)) return;
        this.newSetuperfectZonesone.put(player, zone);
    }

    public void removeNewSetuperfectZonesone(UUID player){
        if(!containsPlayer(player)) return;
        this.newSetuperfectZonesone.remove(player);
    }

    public Map<UUID, Zone> getNewSetuperfectZonesone() {
        return newSetuperfectZonesone;
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
            if(zone.getName().equals(name)){
                return zone;
            }
        }
        return null;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void loadZones(){
        this.zones = new ArrayList<>();
        if(perfectZones.getAllFiles().getData().contains("Zones")){
            for(String s : perfectZones.getAllFiles().getData().getConfigurationSection("Zones").getKeys(false)){
                List<ZoneSetup> zoneSetups = new ArrayList<>();
                if(perfectZones.getAllFiles().getData().contains("Zones." + s + ".Sub_Zones")){
                    for(String c : perfectZones.getAllFiles().getData().getConfigurationSection("Zones." + s + ".Sub_Zones").getKeys(false)){
                        PCuboid cuboid = new PCuboid(Serialize.deserializeLocation(perfectZones.getAllFiles().getData().getString("Zones." + s + ".Sub_Zones." + c + ".Location_DOWN")),
                                Serialize.deserializeLocation(perfectZones.getAllFiles().getData().getString("Zones." + s + ".Sub_Zones." + c + ".Location_UP")));
                        ZoneSetup zoneSetup = new ZoneSetup(perfectZones.getAllFiles().getData().getString("Zones." + s + ".Sub_Zones." + c + ".Creator"),
                                c, Serialize.deserializeLocation(perfectZones.getAllFiles().getData().getString("Zones." + s + ".Sub_Zones." + c + ".Location_DOWN")),
                                Serialize.deserializeLocation(perfectZones.getAllFiles().getData().getString("Zones." + s + ".Sub_Zones." + c + ".Location_UP")),
                                perfectZones.getAllFiles().getData().getString("Zones." + s + ".Sub_Zones." + c + ".Date"), cuboid);
                        zoneSetups.add(zoneSetup);
                    }
                } else {
                    Bukkit.getConsoleSender().sendMessage("§9[§bPerfectZones§9] §cNo sub-zones founded for §b" + s + "§c...");
                }

                PCuboid cuboid = new PCuboid(Serialize.deserializeLocation(perfectZones.getAllFiles().getData().getString("Zones." + s + ".Location_DOWN")),
                        Serialize.deserializeLocation(perfectZones.getAllFiles().getData().getString("Zones." + s + ".Location_UP")));

                Zone zone = new Zone(perfectZones.getAllFiles().getData().getString("Zones." + s + ".Creator"), s, Serialize.deserializeLocation(perfectZones.getAllFiles().getData().getString("Zones." + s + ".Location_DOWN")),
                        Serialize.deserializeLocation(perfectZones.getAllFiles().getData().getString("Zones." + s + ".Location_UP")), perfectZones.getAllFiles().getData().getString("Zones." + s + ".Date"),
                        cuboid, perfectZones.getAllFiles().getData().getBoolean("Zones." + s + ".Default"), zoneSetups,
                        perfectZones.getAllFiles().getData().getString("Zones." + s + ".Material"));

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
                perfectZones.getAllFiles().getData().set("Zones." + zone.getName() + ".Creator", zone.getCreator());
                perfectZones.getAllFiles().getData().set("Zones." + zone.getName() + ".Date", zone.getDate());
                perfectZones.getAllFiles().getData().set("Zones." + zone.getName() + ".Location_UP", Serialize.serializeLocation(zone.getUp()));
                perfectZones.getAllFiles().getData().set("Zones." + zone.getName() + ".Location_DOWN", Serialize.serializeLocation(zone.getDown()));
                perfectZones.getAllFiles().getData().set("Zones." + zone.getName() + ".Default", zone.isDefault());
                perfectZones.getAllFiles().getData().set("Zones." + zone.getName() + ".Material", zone.getMaterial());

                if(!zone.getZones().isEmpty()){
                    for(ZoneSetup zoneSetup : zone.getZones()){
                        perfectZones.getAllFiles().getData().set("Zones." + zone.getName() + ".Sub_Zones." + zoneSetup.getName() + ".Creator", zoneSetup.getCreator());
                        perfectZones.getAllFiles().getData().set("Zones." + zone.getName() + ".Sub_Zones." + zoneSetup.getName() + ".Date", zoneSetup.getDate());
                        perfectZones.getAllFiles().getData().set("Zones." + zone.getName() + ".Sub_Zones." + zoneSetup.getName() + ".Location_UP", Serialize.serializeLocation(zoneSetup.getUp()));
                        perfectZones.getAllFiles().getData().set("Zones." + zone.getName() + ".Sub_Zones." + zoneSetup.getName() + ".Location_DOWN", Serialize.serializeLocation(zoneSetup.getDown()));
                    }
                } else {
                    Bukkit.getConsoleSender().sendMessage("§9[§bPerfectZones§9] §cNo sub-zones founded for the zone §b" + zone.getName() + "§c...");
                }
            }
            Bukkit.getConsoleSender().sendMessage("§9[§bPerfectZones§9] §aSaving all zones...");
            perfectZones.data.save();
        } else {
            Bukkit.getConsoleSender().sendMessage("§9[§bPerfectZones§9] §cNo zones founded...");
        }
    }
}