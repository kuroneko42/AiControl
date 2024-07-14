package na.kuroneko.aientitycontrol.commands;

import dev.lone.itemsadder.api.CustomStack;
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
            CustomStack customStack = CustomStack.getInstance("magic_staff");
            if (customStack == null) {
                player.sendMessage("찾지 못했습니다.");
                return true;
            }
            ItemStack wandItem = customStack.getItemStack();
            player.getInventory().addItem(wandItem);
        }
        return false;
    }
}
