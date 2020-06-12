package perfect.versions.v1_13_R2;

import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import perfect.versions.common.ActionbarMessenger;

public class ActionbarMessenger_v1_13_R2 implements ActionbarMessenger {

    public void sendActionbar(@NotNull Player player, @NotNull String message) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        IChatBaseComponent chatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}");
        PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.ACTIONBAR,
                chatBaseComponent,
                20, 60, 20
        );
        craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutTitle);
    }

}