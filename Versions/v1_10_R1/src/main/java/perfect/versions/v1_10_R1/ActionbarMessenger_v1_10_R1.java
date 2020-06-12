package perfect.versions.v1_10_R1;

import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import perfect.versions.common.ActionbarMessenger;

public class ActionbarMessenger_v1_10_R1 implements ActionbarMessenger {

    public void sendActionbar(@NotNull Player player, @NotNull String message) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        IChatBaseComponent chatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}");
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(chatBaseComponent, (byte) 2);
        craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutChat);
    }

}