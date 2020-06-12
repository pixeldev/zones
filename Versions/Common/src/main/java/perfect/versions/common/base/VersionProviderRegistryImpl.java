package perfect.versions.common.base;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class VersionProviderRegistryImpl implements VersionProviderRegistry {

    @NotNull private final Map<Class<?>, VersionProvider<?>> versionProviders;

    VersionProviderRegistryImpl() {
        this.versionProviders = new HashMap<>();
    }

    @Override
    public <T> void registerVersionProvider(@NotNull Class<T> apiInterface, @NotNull MinecraftVersion version, @NotNull T implementation) {
        this.versionProviders.computeIfAbsent(apiInterface, k -> new VersionProviderImpl<>(apiInterface));
        this.getVersionProvider(apiInterface).registerVersion(version, implementation);
    }

    @Override
    public <T> boolean hasRegisteredVersionProvider(@NotNull Class<T> apiInterface) {
        return this.versionProviders.containsKey(apiInterface);
    }

    @Override
    public @NotNull <T> VersionProvider<T> getVersionProvider(@NotNull Class<T> apiInterface) throws IllegalArgumentException {
        if (this.versionProviders.get(apiInterface) == null) {
            throw new IllegalArgumentException("Api interface " + apiInterface.getName() + " is not registered");
        }
        return (VersionProvider<T>) this.versionProviders.get(apiInterface);
    }

    @NotNull
    @Override
    public Map<Class<?>, VersionProvider<?>> getVersionProviders() {
        return versionProviders;
    }
}