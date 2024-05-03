package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.item.DolphinItems;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DolphinAbility implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();
    public static void init(UhcPlayer uhcPlayer) {
        uhcPlayer.getPlayer().getInventory().addItem(DolphinItems.helmet(), DolphinItems.trident());
        uhcPlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, PotionEffect.INFINITE_DURATION, 0, false, false, false));
        uhcPlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, PotionEffect.INFINITE_DURATION, 0, false, false, false));
    }
    @EventHandler
    public void onPortalEnter(PlayerPortalEvent e) {
        Player player = e.getPlayer();
        World world = e.getTo().getWorld();
        UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
        if (uhcPlayer != null && uhcPlayer.hasUhcClass()) {
            if (uhcPlayer.getUhcClass().equals(UhcClass.DOLPHIN)) {
                if (world != null && world.getEnvironment().equals(World.Environment.NETHER)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                } else {
                    player.removePotionEffect(PotionEffectType.WEAKNESS);
                    player.removePotionEffect(PotionEffectType.SLOWNESS);
                }
            }
        }
    }
}
