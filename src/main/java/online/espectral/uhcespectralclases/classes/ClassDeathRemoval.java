package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

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
        }
    }
}
