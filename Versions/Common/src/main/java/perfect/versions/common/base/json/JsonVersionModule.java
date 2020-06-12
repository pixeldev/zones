package perfect.versions.common.base.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import perfect.versions.common.base.MinecraftVersion;

import java.beans.ConstructorProperties;

public class JsonVersionModule {

    @NotNull private final MinecraftVersion version;

    @JsonProperty("class_path")
    @NotNull private final String classPath;

    @JsonProperty("loader_class_path")
    @Nullable private final String loaderClassPath;

    @ConstructorProperties({"version", "class_path", "loader_class_path"})
    public JsonVersionModule(@NotNull MinecraftVersion version, @NotNull String classPath, @Nullable String loaderClassPath) {
        this.version = version;
        this.classPath = classPath;
        this.loaderClassPath = loaderClassPath;
    }

    public MinecraftVersion getVersion() {
        return version;
    }

    public String getClassPath() {
        return classPath;
    }

    public String getLoaderClassPath() {
        return loaderClassPath;
    }
}