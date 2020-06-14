package perfect.versions.v1_10_R1;

import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import perfect.versions.common.TitleMessenger;

public class TitleMessenger_v1_10_R1 implements TitleMessenger {

    public void sendTitle(@NotNull Player player, @NotNull String message, @NotNull int enter, @NotNull int in, @NotNull int out) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.TITLE,
                IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message.replace("&", "§") + "\"}"),
                enter, in, out
        );

        craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutTitle);
    }

    public void sentSubtitle(@NotNull Player player, @NotNull String message, @NotNull int enter, @NotNull int in, @NotNull int out) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
                IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message.replace("&", "§") + "\"}"),
                enter, in, out
        );

        craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutTitle);
    }

    public void sendFull(@NotNull Player player, @NotNull String title, @NotNull String subtitle, @NotNull int enter, @NotNull int in, @NotNull int out) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        PacketPlayOutTitle packetTitle = new PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.TITLE,
                IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + title.replace("&", "§") + "\"}"),
                enter, in, out
        );

        PacketPlayOutTitle packetSubtitle = new PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
                IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + subtitle.replace("&", "§") + "\"}"),
                enter, in, out
        );

        craftPlayer.getHandle().playerConnection.sendPacket(packetTitle);
        craftPlayer.getHandle().playerConnection.sendPacket(packetSubtitle);
    }

}