package perfect.versions.v1_14_R1;

import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import perfect.versions.common.ActionbarMessenger;

public class ActionbarMessenger_v1_14_R1 implements ActionbarMessenger {

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