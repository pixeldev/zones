package perfect.versions.common.base.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import perfect.versions.common.base.MinecraftVersion;
import perfect.versions.common.base.VersionProviderRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

public interface JsonModulesLoader {

    void load(@NotNull VersionProviderRegistry registry, @NotNull MinecraftVersion version, @NotNull ObjectMapper objectMapper, @NotNull InputStream jsonInputStream)
            throws IllegalArgumentException, IOException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException;

    void registerModuleClassLoader(@NotNull JsonModuleClassLoader classLoader);

    @NotNull static JsonModulesLoader createJsonModulesLoader() {
        return new JsonModulesLoaderImpl();
    }

}