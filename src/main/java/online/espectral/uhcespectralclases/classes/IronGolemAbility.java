package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.item.GolemHammerItem;
import online.espectral.uhcespectralclases.item.PluginItems;
import online.espectral.uhcespectralclases.util.Time;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class IronGolemAbility implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    public static void init(UhcPlayer uhcPlayer) {
        uhcPlayer.getPlayer().getInventory().addItem(GolemHammerItem.give());
        uhcPlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, PotionEffect.INFINITE_DURATION, 0, false, false, false));
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player player) {
            UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
            if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.IRON_GOLEM)) {
                e.setDamage(e.getDamage()*0.75);
            }
        }
    }
    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent e) {
        if (e.getItem().isSimilar(PluginItems.concentratedIron())) {
            Player player = e.getPlayer();
            UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
            if (uhcPlayer != null && uhcPlayer.hasUhcClass()) {
                if (uhcPlayer.getUhcClass().equals(UhcClass.IRON_GOLEM)) {
                    double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
                    player.setHealth(Math.min(player.getHealth() + 2, maxHealth));
                } else {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, Time.secondsToTicks(5), 1, false, false, false));
                    player.sendMessage(ChatColor.DARK_GRAY + "Demasiado hierro para tu cuerpo...");
                }
            }
        }
    }
}
