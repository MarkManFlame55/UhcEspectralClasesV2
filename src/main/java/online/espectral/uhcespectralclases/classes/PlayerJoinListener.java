package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

import static online.espectral.uhcespectralclases.UhcEspectralClases.getUhcGame;

public class PlayerJoinListener implements Listener {

    UhcGame uhcGame = getUhcGame();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        System.out.println(uuid);
        for (UhcPlayer uhcPlayer : uhcGame.getPlayers()) {
            System.out.println(uhcPlayer.getUuid());
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {

    }
}
