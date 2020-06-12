package perfect.versions.v1_10_R1;

import org.jetbrains.annotations.NotNull;
import perfect.versions.common.ActionbarMessenger;
import perfect.versions.common.TitleMessenger;
import perfect.versions.common.base.MinecraftVersion;
import perfect.versions.common.base.VersionModule;
import perfect.versions.common.base.VersionProviderRegistry;

public class Module_v1_10_R1 implements VersionModule {

    public void configure(@NotNull VersionProviderRegistry registry) {
        registry.registerVersionProvider(ActionbarMessenger.class, MinecraftVersion.v1_10_R1, new ActionbarMessenger_v1_10_R1());
        registry.registerVersionProvider(TitleMessenger.class, MinecraftVersion.v1_10_R1, new TitleMessenger_v1_10_R1());
    }
}