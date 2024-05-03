package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.item.WarriorItem;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

public class WarriorAbility implements Listener {

    static UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    public static void init(UhcPlayer uhcPlayer) {
        uhcPlayer.getPlayer().getInventory().addItem(WarriorItem.give());
    }
    @EventHandler
    public static void onPlayerHurt(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player player) {
            World world = player.getWorld();
            UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
            if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.WARRIOR)) {
                if (uhcPlayer.isEnraged()) {
                    uhcPlayer.setEnraged(false);
                    player.removePotionEffect(PotionEffectType.STRENGTH);
                    player.removePotionEffect(PotionEffectType.SPEED);
                    player.removePotionEffect(PotionEffectType.GLOWING);
                    world.playSound(player.getLocation(), Sound.ENTITY_WARDEN_DEATH, 2.0f, 2.5f);
                    player.addPotionEffects(uhcPlayer.getSavedEffects());
                }
                if (e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
                    e.setDamage(e.getDamage()*1.25);
                }
            }
        }
    }
    @EventHandler
    public static void onPlayerDamgeEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player player) {
            World world = player.getWorld();
            UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
            if (e.getEntity() instanceof LivingEntity entity) {
                if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.WARRIOR)) {
                    if (uhcPlayer.isEnraged()) {
                        uhcPlayer.setEnraged(false);
                        player.removePotionEffect(PotionEffectType.STRENGTH);
                        player.removePotionEffect(PotionEffectType.SPEED);
                        player.removePotionEffect(PotionEffectType.GLOWING);
                        world.playSound(player.getLocation(), Sound.ITEM_MACE_SMASH_GROUND_HEAVY, 1.0f, 1.0f);
                        world.spawnParticle(Particle.EXPLOSION, entity.getLocation(), 50, 1, 1, 1);
                        player.addPotionEffects(uhcPlayer.getSavedEffects());
                    }
                }
            }
        }
    }
}
