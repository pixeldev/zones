package perfect.versions.common;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface TitleMessenger {

    void sendTitle(@NotNull Player player, @NotNull String message, @NotNull int enter, @NotNull int in, @NotNull int out);

    void sentSubtitle(@NotNull Player player, @NotNull String message, @NotNull int enter, @NotNull int in, @NotNull int out);

    void sendFull(@NotNull Player player, @NotNull String title, @NotNull String subtitle, @NotNull int enter, @NotNull int in, @NotNull int out);

}