package perfect.versions.common;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface ActionbarMessenger {

    void sendActionbar(@NotNull Player player, @NotNull String message);

}