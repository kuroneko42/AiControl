package na.kuroneko.aientitycontrol.event;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import na.kuroneko.aientitycontrol.AiEntityControl;
import na.kuroneko.aientitycontrol.SoundPacket;
import na.kuroneko.aientitycontrol.control.AIControl;
import na.kuroneko.aientitycontrol.control.AIController;
import na.kuroneko.aientitycontrol.entity.SpawnEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;

import java.util.ArrayList;
import java.util.List;

public class WandListener implements Listener {
    private final World world = Bukkit.getWorld("world");
    private static int taskId;
    private final AIController controller = new AIController();

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        Location spawnLoc = player.getTargetBlock(null, 30).getLocation().add(0, 1,0);
        boolean isBlockBelowSolid = spawnLoc.clone().add(0,-1,0).getBlock().getType().isSolid();


        if (!(mainHand.getType() == Material.STICK && mainHand.hasItemMeta()) || !isBlockBelowSolid) return;


        if (event.getAction().isRightClick() && player.isSneaking()) {
            SpawnEntity spawnEntity = new SpawnEntity();
            spawnEntity.spawnZombie(player);
            spawnEntity.spawnDisplay(player);

            SoundPacket soundPacket = new SoundPacket(AiEntityControl.protocolManager);
            soundPacket.playSound(player, player.getLocation());
        }

        if (event.getAction().isRightClick()) {
            for (Zombie zombie : SpawnEntity.mob) {
                zombie.setAI(true);
                controller.move(zombie, player);
            }
        }

        if (event.getAction().isLeftClick()) {
            RayTraceResult rayTraceResult = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(),
                    30, e -> e instanceof LivingEntity && !e.equals(player));
            if (rayTraceResult != null && rayTraceResult.getHitEntity() != null) {
                LivingEntity target = (LivingEntity) rayTraceResult.getHitEntity();
                for (Zombie zombie : SpawnEntity.mob) {
                    zombie.setAI(true);
                    controller.attack(zombie, target);
                }
            }
        }

        if (event.getAction().isLeftClick() && player.isSneaking()) {
            RayTraceResult rayTraceResult = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(),
                    30, e -> e instanceof LivingEntity && !e.equals(player));
            if (rayTraceResult != null && rayTraceResult.getHitEntity() != null) {
                LivingEntity target = (LivingEntity) rayTraceResult.getHitEntity();
                for (Zombie zombie : SpawnEntity.mob) {
                    zombie.setAI(true);
                    controller.siege(zombie, target);
                }
            }
        }
    }
}
