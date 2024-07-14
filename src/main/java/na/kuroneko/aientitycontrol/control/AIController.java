package na.kuroneko.aientitycontrol.control;

import na.kuroneko.aientitycontrol.AiEntityControl;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;

public class AIController implements AIControl{



    @Override
    public void move(Zombie zombie, Player player) {
        Location targetLocation = player.getTargetBlock(null, 30).getLocation();
        zombie.getPathfinder().moveTo(targetLocation);
        new BukkitRunnable() {
            public void run() {
                if (zombie.isDead() || !zombie.isValid() || zombie.getLocation().distance(targetLocation) < 2) {
                    zombie.setAI(false);
                    this.cancel();
                }
            }
        }.runTaskTimer(AiEntityControl.instance, 0L, 20L);
    }

    @Override
    public void attack(Zombie zombie, LivingEntity target) {
        zombie.setTarget(target);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!target.isValid() || target.isDead()) {
                    zombie.setAI(false);
                    zombie.setTarget(null);
                    this.cancel();
                }
            }
        }.runTaskTimer(AiEntityControl.instance, 0L, 20L);
    }

    @Override
    public void siege(Zombie zombie, LivingEntity target) {
        Location targetLoc = target.getLocation();
        zombie.getPathfinder().moveTo(target);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!target.isValid() || target.isDead() || zombie.getLocation().distance(targetLoc) < 3) {
                    zombie.setAI(false);
                    zombie.setTarget(null);
                    this.cancel();
                }
            }
        }.runTaskTimer(AiEntityControl.instance, 0L, 20L);
    }
}
