package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ClassDeathRemoval implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
        if (uhcPlayer != null && uhcPlayer.hasUhcClass()) {
            uhcPlayer.setUhcClass(null);
            uhcPlayer.setDefaultConfig();
            uhcGame.removePlayer(uhcPlayer.getUuid());
            for (UhcPlayer uhcPlayer1 : uhcGame.getPlayers()) {
                if (uhcPlayer1.hasUhcClass() && uhcPlayer1.getUhcClass().equals(UhcClass.MINER)) {
                    uhcPlayer1.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 1, false, false, false));
                }
            }
        }
    }
}
