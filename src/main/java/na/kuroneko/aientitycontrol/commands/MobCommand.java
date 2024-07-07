package na.kuroneko.aientitycontrol.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MobCommand extends BukkitCommand {

    public MobCommand(@NotNull String name) {
        super(name);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            World world = player.getWorld();
            Location dummy = player.getLocation().add(player.getLocation().getDirection().multiply(13));

            ArmorStand armorStand = world.spawn(dummy, ArmorStand.class, entity -> {
                entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50.0);
                entity.setHealth(50.0);
            });

            world.spawn(player.getLocation(), Zombie.class, entity -> {
                entity.setCustomName("와왁왁");
                entity.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
                entity.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
                entity.getEquipment().setItemInOffHand(new ItemStack(Material.NETHERITE_SWORD));
                entity.getPathfinder().setCanPassDoors(true);
                entity.getPathfinder().setCanPassDoors(true);
            });
        }
        return false;
    }
}
