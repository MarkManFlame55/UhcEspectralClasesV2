package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class ClassHurtSounds implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player player) {
            UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
            if (uhcPlayer != null && uhcPlayer.hasUhcClass()) {
                World world = player.getWorld();
                switch (uhcPlayer.getUhcClass()) {
                    case WITCH -> world.playSound(player.getLocation(), Sound.ENTITY_WITCH_HURT, 1.0f, 1.0f);
                    case DOLPHIN -> world.playSound(player.getLocation(), Sound.ENTITY_DOLPHIN_HURT, 1.0f, 1.0f);
                    case SPIDER -> world.playSound(player.getLocation(), Sound.ENTITY_SPIDER_HURT, 1.0f, 1.0f);
                    case BREEZE -> world.playSound(player.getLocation(), Sound.ENTITY_BREEZE_HURT, 1.0f, 1.0f);
                    case WITHER -> world.playSound(player.getLocation(), Sound.ENTITY_WITHER_SKELETON_HURT, 1.0f, 1.0f);
                    case BLAZE -> world.playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, 1.0f, 1.0f);
                    case IRON_GOLEM -> world.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1.0f, 1.0f);
                }
            }
        }
    }
}
