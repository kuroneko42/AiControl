package na.kuroneko.aientitycontrol;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundPacket {
    private final ProtocolManager protocolManager;

    public SoundPacket(ProtocolManager protocolManager) {
        this.protocolManager = protocolManager;
    }

    public void playSound(Player player, Location location) {

        PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.NAMED_SOUND_EFFECT);

        packet.getSoundCategories()
                .write(0, EnumWrappers.SoundCategory.VOICE);

        packet.getSoundEffects()
                .write(0, Sound.ENTITY_PLAYER_LEVELUP);

        packet.getIntegers()
                .write(0, location.getBlockX() * 8)
                .write(1, location.getBlockY() * 8)
                .write(2, location.getBlockZ() * 8);

        packet.getFloat()
                .write(0, 1F)
                .write(1, 1F);

        packet.getLongs()
                .write(0, 0L);

        protocolManager.sendServerPacket(player, packet);
    }
}
