package perfect.versions.common.base;

import org.jetbrains.annotations.NotNull;

public interface VersionModule {

    void configure(@NotNull VersionProviderRegistry registry);

}