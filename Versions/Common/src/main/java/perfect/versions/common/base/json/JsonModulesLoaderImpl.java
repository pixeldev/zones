package perfect.versions.common.base.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import perfect.versions.common.base.MinecraftVersion;
import perfect.versions.common.base.VersionModule;
import perfect.versions.common.base.VersionProviderRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class JsonModulesLoaderImpl implements JsonModulesLoader {

    @NotNull private final DefaultJsonModuleClassLoader defaultClassLoader = new DefaultJsonModuleClassLoader();

    @Override
    public void load(@NotNull VersionProviderRegistry registry, @NotNull MinecraftVersion version, @NotNull ObjectMapper objectMapper, @NotNull InputStream jsonInputStream)
            throws IllegalArgumentException, IOException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        JsonVersionModule[] modulesArray = objectMapper.readValue(jsonInputStream, JsonVersionModule[].class);
        for (JsonVersionModule jsonModule : modulesArray) {
            if (jsonModule.getVersion() == version) {
                if (jsonModule.getLoaderClassPath() == null) {
                    VersionModule versionModule = this.defaultClassLoader.loadFromClass(Class.forName(jsonModule.getClassPath()));
                    versionModule.configure(registry);
                } else {
                    if (!JsonModuleClassLoader.class.isAssignableFrom(Class.forName(jsonModule.getLoaderClassPath()))) {
                        throw new IllegalArgumentException(jsonModule.getLoaderClassPath() + " is not a class loader");
                    }
                    JsonModuleClassLoader classLoader = (JsonModuleClassLoader) Class.forName(jsonModule.getLoaderClassPath()).newInstance();
                    VersionModule versionModule = classLoader.loadFromClass(Class.forName(jsonModule.getClassPath()));
                    versionModule.configure(registry);
                }
            }
        }
    }
}