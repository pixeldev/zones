package perfect.galaxy.zones;

import me.fixeddev.ebcm.bukkit.BukkitCommandManager;
import me.fixeddev.ebcm.parametric.ParametricCommandBuilder;
import me.fixeddev.ebcm.parametric.ReflectionParametricCommandBuilder;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import perfect.galaxy.zones.commands.PrincipalZonesCMD;
import perfect.galaxy.zones.files.Files;
import perfect.galaxy.zones.managers.EventsManager;
import perfect.galaxy.zones.managers.VersionsManager;
import perfect.galaxy.zones.managers.zone.ZoneManager;
import perfect.galaxy.zones.files.managers.PFilesManager;
import perfect.galaxy.zones.user.manager.*;
import perfect.galaxy.zones.placeholder.ZonesExpansion;

import static org.bukkit.Bukkit.getServer;

public final class PerfectZones extends JavaPlugin {

    private PFilesManager FilesManager;
    private ZoneManager zoneManager;
    private UserZoneManager userZoneManager;
    private UserEditorManager userEditorManager;
    private VersionsManager versionsManager;
    private UserCreatorManager userCreatorManager;

    public Files config;
    public Files data;
    public Files lang;
    public Files menu;

    private String prefix;

    private static PerfectZones perfectZones;
    public static PerfectZones getPerfectZones(){
        return perfectZones;
    }

    @Override
    public void onEnable(){
        perfectZones = this;

        Bukkit.getConsoleSender().sendMessage(Bukkit.getServer().getClass().getPackage().getName().substring(
                getServer().getClass().getPackage().getName().lastIndexOf(".") + 1));

        config = new Files(this, "config");
        data = new Files(this, "data");
        lang = new Files(this, "lang");
        menu = new Files(this, "menu");

        FilesManager = new PFilesManager(config, data, lang, menu);

        this.zoneManager = new ZoneManager(this);
        this.prefix = this.FilesManager.getLang().parseColor(this.FilesManager.getLang().getString("Messages.Prefix"));
        this.userZoneManager = new UserZoneManager(this);
        this.userEditorManager = new UserEditorManager();
        this.versionsManager = new VersionsManager();
        this.userCreatorManager = new UserCreatorManager(this);

        new ZonesExpansion(this).register();
        new EventsManager(this);

        registerCommands();

        this.zoneManager.loadZones();
        this.userCreatorManager.loadCreators();
        this.userZoneManager.loadUsersZone();
    }

    @Override
    public void onDisable(){
        this.zoneManager.saveZones();
        this.userCreatorManager.saveCreators();
        this.userZoneManager.saveUsersZone();
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

    public ZoneManager getZoneManager() {
        return zoneManager;
    }

    public UserCreatorManager getUserCreatorManager() {
        return userCreatorManager;
    }

    public UserEditorManager getUserEditorManager() {
        return userEditorManager;
    }

    public PFilesManager getFilesManager() {
        return FilesManager;
    }

    public VersionsManager getVersionsManager() {
        return versionsManager;
    }

    private void registerCommands(){
        ParametricCommandBuilder builder = new ReflectionParametricCommandBuilder();
        BukkitCommandManager bukkitCommandManager = new BukkitCommandManager(getName());

        bukkitCommandManager.registerCommands(builder.fromClass(new PrincipalZonesCMD(this)));
    }
}