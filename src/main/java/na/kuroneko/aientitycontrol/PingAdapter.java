package na.kuroneko.aientitycontrol;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class PingAdapter extends PacketAdapter {

    public PingAdapter(Plugin plugin) {
        super(plugin, ListenerPriority.LOWEST, PacketType.Status.Client.PING);
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        Bukkit.getLogger().warning("Receive Ping");
    }
}
