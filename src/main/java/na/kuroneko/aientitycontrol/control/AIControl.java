package na.kuroneko.aientitycontrol.control;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

public interface AIControl {
    void move(Zombie zombie, Player player);
    void attack(Zombie zombie, LivingEntity target);
    void siege(Zombie zombie, LivingEntity target);
}
