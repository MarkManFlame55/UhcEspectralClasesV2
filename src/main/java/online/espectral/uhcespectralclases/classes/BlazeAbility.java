package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.item.BlazeItem;
import online.espectral.uhcespectralclases.util.ServerMessage;
import online.espectral.uhcespectralclases.util.Time;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class BlazeAbility implements Listener {

    private final long cooldownTime = Time.seconds(10);
    private final HashMap<UUID, Long> cooldown = new HashMap<>();

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    public static void init(UhcPlayer uhcPlayer) {
        uhcPlayer.getPlayer().getInventory().addItem(BlazeItem.give());
        uhcPlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 0, false, false, false));
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player player) {
            UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
            if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.BLAZE)) {
                if (uhcPlayer.isBlazeActive()) {
                    Entity entity = e.getEntity();
                    entity.setFireTicks(160);
                }
            }
        }
        if (e.getDamager() instanceof Arrow arrow) {
            if (arrow.getShooter() instanceof Player player) {
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.BLAZE)) {
                    if (uhcPlayer.isBlazeActive()) {
                        Entity entity = e.getEntity();
                        entity.setFireTicks(160);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent e) {
        Player player = e.getPlayer();
        Location pos = player.getLocation();
        Block block = pos.getBlock();
        if (e.isSneaking()) {
            if (player.getLocation().getBlock().isLiquid()) {
                if (block.getType().equals(Material.LAVA)) {
                    UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                    if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.BLAZE)) {
                        if (!this.cooldown.containsKey(player.getUniqueId())) {
                            this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                            ability(player);
                        } else {
                            long timeElapsed = System.currentTimeMillis() - this.cooldown.get(player.getUniqueId());
                            if (timeElapsed >= this.cooldownTime) {
                                this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                                ability(player);
                            } else {
                                ServerMessage.unicastError(player, ServerMessage.ON_COOLDOWN);
                            }
                        }
                    }
                }
            }
        }
    }
    private void ability(Player player) {
        Vector direction = player.getLocation().getDirection();
        player.setVelocity(direction.multiply(2));
        player.playSound(player, Sound.ENTITY_BLAZE_AMBIENT, 1.0f, 1.5f);
    }
}
