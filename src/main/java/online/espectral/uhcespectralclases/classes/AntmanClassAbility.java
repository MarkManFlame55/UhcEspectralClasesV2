package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.item.AntmanItem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class AntmanClassAbility implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    public static void init(UhcPlayer uhcPlayer) {
        uhcPlayer.getPlayer().getInventory().addItem(AntmanItem.give());
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player player) {
            if (uhcGame.getPlayer(player.getUniqueId()) != null) {
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                if (uhcPlayer.getUhcClass().equals(UhcClass.ANTMAN) && uhcPlayer.isSizeChanged()) {
                    if (uhcPlayer.getScaleState()) {
                        e.setDamage(e.getDamage()*0.25);
                    } else {
                        e.setDamage(e.getDamage()*1.50);
                    }
                }
            }
        }
    }
}
