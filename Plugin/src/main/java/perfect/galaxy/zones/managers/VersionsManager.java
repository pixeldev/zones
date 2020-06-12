package perfect.galaxy.zones.managers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.Bukkit;
import perfect.galaxy.zones.PerfectZones;
import perfect.versions.common.base.MinecraftVersion;
import perfect.versions.common.base.VersionProviderRegistry;
import perfect.versions.common.base.json.JsonModulesLoader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import static org.bukkit.Bukkit.getServer;

public class VersionsManager {

    private final PerfectZones perfectZones;
    private VersionProviderRegistry versionProviderRegistry;
    private MinecraftVersion currentVersion;

    public VersionsManager(PerfectZones perfectZones) {
        this.perfectZones = perfectZones;

        registerVersions();
    }

    private void registerVersions() {
        versionProviderRegistry = VersionProviderRegistry.createRegistry();
        JsonModulesLoader jsonModulesLoader = JsonModulesLoader.createJsonModulesLoader();
        currentVersion = MinecraftVersion.valueOf(Bukkit.getServer().getClass().getPackage().getName().substring(
                getServer().getClass().getPackage().getName().lastIndexOf(".") + 1
        ));

        InputStream modulesJsonStream = perfectZones.getClass().getClassLoader().getResourceAsStream("modules.json");
        try {
            jsonModulesLoader.load(versionProviderRegistry, currentVersion, new ObjectMapper(), modulesJsonStream);
        } catch (IOException | IllegalAccessException | InstantiationException |
                ClassNotFoundException | NoSuchMethodException | InvocationTargetException exception) {
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