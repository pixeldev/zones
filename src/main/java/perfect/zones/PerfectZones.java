package perfect.zones;

import org.bukkit.plugin.java.JavaPlugin;
import perfect.zones.commands.ZonesCMD;
import perfect.zones.files.managers.PAllFiles;
import perfect.zones.files.managers.PFilesManager;
import perfect.zones.managers.EventsManager;
import perfect.zones.user.manager.UserZoneManager;
import perfect.zones.managers.zone.ZoneManager;
import perfect.zones.placeholder.ZonesExpansion;

public class PerfectZones extends JavaPlugin {

    private PAllFiles allFiles;
    private ZoneManager zoneManager;
    private UserZoneManager userZoneManager;
    private String prefix;

    private static PerfectZones perfectZones;
    public static PerfectZones getPerfectZones(){
        return perfectZones;
    }

    @Override
    public void onEnable(){
        perfectZones = this;

        PFilesManager.setPlugin(this);
        PFilesManager.addFile("data");
        PFilesManager.addFile("lang");
        PFilesManager.addFile("menu");
        PFilesManager.addFile("config");

        allFiles = new PAllFiles(PFilesManager.getFiles("config").getData(), PFilesManager.getFiles("data").getData(), PFilesManager.getFiles("lang").getData(), PFilesManager.getFiles("menu").getData());
        this.zoneManager = new ZoneManager(this);
        this.prefix = this.allFiles.getLang().parseColor(this.allFiles.getLang().getString("Messages.Prefix"));
        this.userZoneManager = new UserZoneManager(this);

        getCommand("perfectzones").setExecutor(new ZonesCMD(this));
        new ZonesExpansion().register();
        new EventsManager(this);

        this.zoneManager.loadZones();
    }

    @Override
    public void onDisable(){
        this.zoneManager.saveZones();
    }

    public ZoneManager getZoneManager() {
        return zoneManager;
    }

    public PAllFiles getAllFiles() {
        return allFiles;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public UserZoneManager getUserZoneManager() {
        return userZoneManager;
    }
}