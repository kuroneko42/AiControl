package na.kuroneko.aientitycontrol;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import na.kuroneko.aientitycontrol.commands.MobCommand;
import na.kuroneko.aientitycontrol.event.WandListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class AiEntityControl extends JavaPlugin {

    private static ProtocolManager protocolManager;
    public static AiEntityControl instance;

    public static @NotNull Optional<ProtocolManager> getProtocolManager() {
        return Optional.ofNullable(protocolManager);
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getCommandMap().register("", new MobCommand("test"));

        Bukkit.getPluginManager().registerEvents(new WandListener(), this);

        protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new PingAdapter(this));
        protocolManager.addPacketListener(new PongAdapter(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
