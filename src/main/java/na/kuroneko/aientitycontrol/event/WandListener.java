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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;

public class WandListener implements Listener {
    private final World world = Bukkit.getWorld("world");
    private int taskId;

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

                    taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(AiEntityControl.instance, new BukkitRunnable() {
                        double yawIncrement = 1.8;
                        int timePassed = 0;
                        int duration = 60;

                        @Override
                        public void run() {
                            if (timePassed >= duration) {
                                display.remove();
                                Bukkit.getScheduler().cancelTask(taskId);
                            } else {
                                Location currentLoc = display.getLocation();
                                currentLoc.setYaw((float) (currentLoc.getYaw() + yawIncrement));
                                display.teleport(currentLoc);
                                timePassed++;
                            }
                        }
                    }, 0, 1);
                }
            }
        }
    }
}
