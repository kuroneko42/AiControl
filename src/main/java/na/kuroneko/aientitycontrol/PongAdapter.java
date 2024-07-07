package na.kuroneko.aientitycontrol;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class PongAdapter extends PacketAdapter {

    public PongAdapter(Plugin plugin) {
        super(plugin, ListenerPriority.LOWEST, PacketType.Status.Server.PONG);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        Bukkit.getLogger().warning("Send Pong");
    }
}
