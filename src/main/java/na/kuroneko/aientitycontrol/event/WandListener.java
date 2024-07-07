package na.kuroneko.aientitycontrol.event;

import dev.lone.itemsadder.api.CustomStack;
import na.kuroneko.aientitycontrol.AiEntityControl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;

import javax.swing.*;

public class WandListener implements Listener {
    private final World world = Bukkit.getWorld("world");

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHand = player.getInventory().getItemInMainHand();

        if (mainHand.getType() == Material.STICK && mainHand.hasItemMeta()) {
            if (event.getAction().isRightClick() && player.isSneaking()) {
                event.setCancelled(true);
                world.spawn(player.getLocation(), Zombie.class, entity -> {
                    entity.setCustomName("와왁왁");
                    entity.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
                    entity.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
                    entity.getEquipment().setItemInOffHand(new ItemStack(Material.NETHERITE_SWORD));
                    entity.getPathfinder().setCanOpenDoors(true);
                    entity.getPathfinder().setCanPassDoors(true);
                });

                CustomStack customStack = CustomStack.getInstance("magic_circle2");
                Location moveLoc = player.getLocation().add(player.getLocation().getDirection().multiply(10));
                if (customStack != null) {
                    ItemStack magicCircle = customStack.getItemStack();

                    ItemDisplay display = world.spawn(player.getLocation(), ItemDisplay.class, entity -> {
                        entity.setItemStack(magicCircle);

                        Transformation transformation = entity.getTransformation();
                        transformation.getScale().mul(3.f);
                        entity.setTransformation(transformation);
                    });

                    Bukkit.getScheduler().scheduleSyncRepeatingTask(AiEntityControl.instance, () -> {
                    }, 0, 20);
                }


//                    new BukkitRunnable() {
//                        double timePassed = 0; // Track elapsed time in ticks
//                        double yawIncrement = 1.8; // Smaller yaw increment for smoother rotation
//                        double totalDurationTicks = 200; // 10 seconds in ticks (20 ticks/second)
//                        double updateFrequencyTicks = 1; // Update every tick for smoother rotation
//
//                        @Override
//                        public void run() {
//                            if (timePassed >= totalDurationTicks) {
//                                display.remove();
//                                this.cancel();
//                            } else {
//                                Location currentLocation = display.getLocation();
//                                currentLocation.setYaw((float) (currentLocation.getYaw() + yawIncrement));
//                                display.teleport(currentLocation);
//                            }
//                            timePassed += updateFrequencyTicks;
//                        }
//                    }.runTaskTimer(AiEntityControl.instance, 0L, 1L);
//                }
            }

            if (event.getAction().isLeftClick()) {
                event.setCancelled(true);
                Bukkit.getScheduler().scheduleSyncRepeatingTask(AiEntityControl.instance, () -> {
                }, 0, 20);
            }
            Bukkit.getScheduler().cancelTask(10);
        }

    }
}
