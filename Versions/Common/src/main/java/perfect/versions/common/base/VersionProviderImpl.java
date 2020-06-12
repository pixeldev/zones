package perfect.versions.common.base;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class VersionProviderImpl<T> implements VersionProvider<T> {

    @NotNull private final Class<T> apiInterface;
    @NotNull private final Map<MinecraftVersion, T> implementations;

    VersionProviderImpl(@NotNull Class<T> apiInterface) {
        this.apiInterface = apiInterface;
        this.implementations = new HashMap<>();
    }

    @Override
    public @NotNull T getImplementation(@NotNull MinecraftVersion minecraftVersion) throws IllegalArgumentException {
        if (this.implementations.get(minecraftVersion) == null) {
            throw new IllegalArgumentException("Version " + minecraftVersion.name() + " is not supported");
        }
        return this.implementations.get(minecraftVersion);
    }

    @Override
    public void registerVersion(@NotNull MinecraftVersion minecraftVersion, @NotNull T implementation) {
        this.implementations.computeIfAbsent(minecraftVersion, k -> implementation);
    }

    @Override
    public boolean hasRegisteredVersion(@NotNull MinecraftVersion minecraftVersion) {
        return this.implementations.containsKey(minecraftVersion);
    }

    @NotNull
    @Override
    public Class<T> getApiInterface() {
        return apiInterface;
    }

    @NotNull
    @Override
    public Map<MinecraftVersion, T> getImplementations() {
        return implementations;
    }
}