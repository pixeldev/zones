package perfect.zones;

import me.fixeddev.ebcm.Authorizer;
import me.fixeddev.ebcm.CommandManager;
import me.fixeddev.ebcm.Messager;
import me.fixeddev.ebcm.SimpleCommandManager;
import me.fixeddev.ebcm.bukkit.BukkitAuthorizer;
import me.fixeddev.ebcm.bukkit.BukkitCommandManager;
import me.fixeddev.ebcm.bukkit.BukkitMessager;
import me.fixeddev.ebcm.bukkit.parameter.provider.BukkitModule;
import me.fixeddev.ebcm.parameter.provider.ParameterProviderRegistry;
import me.fixeddev.ebcm.parametric.ParametricCommandBuilder;
import me.fixeddev.ebcm.parametric.ReflectionParametricCommandBuilder;
import org.bukkit.plugin.java.JavaPlugin;
import perfect.zones.commands.PrincipalZonesCMD;
import perfect.zones.files.Files;
import perfect.zones.files.managers.PAllFiles;
import perfect.zones.managers.EventsManager;
import perfect.zones.managers.PageManager;
import perfect.zones.user.manager.UserCreatorManager;
import perfect.zones.user.manager.UserFilterManager;
import perfect.zones.user.manager.UserZoneManager;
import perfect.zones.managers.zone.ZoneManager;
import perfect.zones.placeholder.ZonesExpansion;

public final class PerfectZones extends JavaPlugin {

    private PAllFiles allFiles;
    private ZoneManager zoneManager;
    private PageManager pageManager;
    private UserZoneManager userZoneManager;
    private UserFilterManager userFilterManager;
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

        config = new Files(this, "config");
        data = new Files(this, "data");
        lang = new Files(this, "lang");
        menu = new Files(this, "menu");

        allFiles = new PAllFiles(config, data, lang, menu);

        this.zoneManager = new ZoneManager(this);
        this.prefix = this.allFiles.getLang().parseColor(this.allFiles.getLang().getString("Messages.Prefix"));
        this.userZoneManager = new UserZoneManager();
        this.pageManager = new PageManager();
        this.userFilterManager = new UserFilterManager();
        this.userCreatorManager = new UserCreatorManager(this);

        new ZonesExpansion(this).register();
        new EventsManager(this);

        registerCommands();

        this.userCreatorManager.loadCreators();
        this.zoneManager.loadZones();
    }

    @Override
    public void onDisable(){
        this.zoneManager.saveZones();
        this.userCreatorManager.saveCreators();
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

    public PageManager getPageManager() {
        return pageManager;
    }

    public ZoneManager getZoneManager() {
        return zoneManager;
    }

    public UserCreatorManager getUserCreatorManager() {
        return userCreatorManager;
    }

    public UserFilterManager getUserFilterManager() {
        return userFilterManager;
    }

    public PAllFiles getAllFiles() {
        return allFiles;
    }

    private void registerCommands(){
        ParametricCommandBuilder builder = new ReflectionParametricCommandBuilder();

        Authorizer authorizer = new BukkitAuthorizer();
        ParameterProviderRegistry providerRegistry = ParameterProviderRegistry.createRegistry();
        Messager messager = new BukkitMessager();
        CommandManager commandManager = new SimpleCommandManager(authorizer, messager, providerRegistry);
        providerRegistry.installModule(new BukkitModule());

        BukkitCommandManager bukkitCommandManager = new BukkitCommandManager(commandManager, this.getName());

        bukkitCommandManager.registerCommands(builder.fromClass(new PrincipalZonesCMD(this)));
    }
}