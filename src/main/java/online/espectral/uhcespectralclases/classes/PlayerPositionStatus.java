package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class PlayerPositionStatus {

    static UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    public static void init(Server server) {
        BukkitTask checkPlayerPosition = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : server.getOnlinePlayers()) {
                    UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                    if (uhcPlayer != null && uhcPlayer.hasUhcClass()) {
                        if (uhcPlayer.getUhcClass().equals(UhcClass.MINER)) {
                            double y = player.getLocation().getY();
                            World world = player.getWorld();
                            if (y < world.getHighestBlockYAt(player.getLocation())) {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 1, false, false, false));
                            }
                        }
                        if (uhcPlayer.getUhcClass().equals(UhcClass.BLAZE)) {
                            if (player.isInWater() && !player.isInsideVehicle()) {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 10, 0, false, false, false));
                                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 0, false, false, false));
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(UhcEspectralClases.getPlugin(UhcEspectralClases.class), 0, 1);
    }
}
