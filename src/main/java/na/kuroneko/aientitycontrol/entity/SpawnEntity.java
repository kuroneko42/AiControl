package na.kuroneko.aientitycontrol.entity;

import dev.lone.itemsadder.api.CustomStack;
import na.kuroneko.aientitycontrol.AiEntityControl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;

import java.util.ArrayList;
import java.util.List;

public class SpawnEntity {
    private final World world = Bukkit.getWorld("World");
    public static List<Zombie> mob = new ArrayList<>();

    public void spawnZombie(Player player) {
        Location spawnLoc = player.getTargetBlock(null, 30).getLocation().add(0,1,0);
        Zombie zombie = world.spawn(spawnLoc, Zombie.class, entity -> {
            entity.setCustomName("왕왕왕");
            entity.setAI(false);
            entity.setSilent(true);
            entity.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
            entity.getEquipment().setItemInOffHand(new ItemStack(Material.END_ROD));
            entity.getEquipment().setItemInMainHand(new ItemStack(Material.END_ROD));
            entity.getPathfinder().setCanOpenDoors(true);
        });
        mob.add(zombie);
    }

    public void spawnDisplay(Player player) {
        Location spawnLoc = player.getTargetBlock(null, 30).getLocation().add(0,1,0);
        CustomStack customStack = CustomStack.getInstance("magic_circle2");
        if (customStack != null) {
            ItemStack magicCircle = customStack.getItemStack();
            ItemDisplay itemDisplay = world.spawn(spawnLoc, ItemDisplay.class, display -> {
                display.setItemStack(magicCircle);
                display.setRotation(0f, 90f);

                Transformation transformation = display.getTransformation();
                transformation.getScale().mul(3f);

                display.setTransformation(transformation);
            });

            new BukkitRunnable() {
                double yawIncrement = 18.0;
                int timePassed = 0;
                int duration = 50;

                @Override
                public void run() {
                    if (timePassed >= duration) {
                        itemDisplay.remove();
                        this.cancel();
                    } else {
                        Location currentLoc = itemDisplay.getLocation();
                        currentLoc.setYaw((float) (currentLoc.getYaw() + yawIncrement));
                        itemDisplay.teleport(currentLoc);
                        timePassed++;
                    }
                }
            }.runTaskTimer(AiEntityControl.instance, 0L, 1L);
        }
    }
}
