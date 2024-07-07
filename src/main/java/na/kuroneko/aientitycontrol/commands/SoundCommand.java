package na.kuroneko.aientitycontrol.commands;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SoundCommand extends BukkitCommand {

    public SoundCommand(@NotNull String name) {
        super(name);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            final ProtocolManager pm = ProtocolLibrary.getProtocolManager();

            final PacketContainer container = new PacketContainer(PacketType.Play.Server.NAMED_SOUND_EFFECT);
//            container.getInstants().write(0,container.getId());
        }
        return false;
    }
}
