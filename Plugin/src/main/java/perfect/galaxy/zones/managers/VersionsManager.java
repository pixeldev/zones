package perfect.galaxy.zones.managers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import perfect.galaxy.zones.PerfectZones;
import perfect.versions.common.base.MinecraftVersion;
import perfect.versions.common.base.VersionProviderRegistry;
import perfect.versions.common.base.json.JsonModulesLoader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

public class VersionsManager {

    private VersionProviderRegistry versionProviderRegistry;
    private MinecraftVersion currentVersion;

    public VersionsManager() {
        registerVersions();
    }

    private void registerVersions() {
        versionProviderRegistry = VersionProviderRegistry.createRegistry();
        JsonModulesLoader jsonModulesLoader = JsonModulesLoader.createJsonModulesLoader();
        currentVersion = MinecraftVersion.valueOf(Bukkit.getServer().getClass().getPackage().getName().substring(
                (Bukkit.getServer().getClass().getPackage().getName().lastIndexOf('.') + 1)
        ));

        InputStream modulesJsonStream = JavaPlugin.getPlugin(PerfectZones.class).getClass().getClassLoader().getResourceAsStream("modules.json");
        try {
            jsonModulesLoader.load(versionProviderRegistry, currentVersion, new ObjectMapper(), modulesJsonStream);
        } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }

    public VersionProviderRegistry getVersionProviderRegistry() {
        return versionProviderRegistry;
    }

    public MinecraftVersion getCurrentVersion() {
        return currentVersion;
    }
}