package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.item.CobwebBallItem;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpiderAbility implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    public static void init(UhcPlayer uhcPlayer) {
        uhcPlayer.getPlayer().getInventory().addItem(CobwebBallItem.give());
        uhcPlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAVING, PotionEffect.INFINITE_DURATION, 0, false, false, false));
    }
    @EventHandler
    public void onPlayerEffect(EntityPotionEffectEvent e) {
        if (e.getEntity() instanceof Player player && e.getNewEffect() != null) {
            PotionEffectType type = e.getNewEffect().getType();
            if (type.equals(PotionEffectType.POISON)) {
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.SPIDER)) {
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onPlayerDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player player && e.getDamager() instanceof LivingEntity livingEntity) {
            UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
            if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.SPIDER)) {
                ItemStack itemStack = livingEntity.getEquipment().getItemInMainHand();
                if (itemStack != null && itemStack.containsEnchantment(Enchantment.BANE_OF_ARTHROPODS)) {
                    int level = itemStack.getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS);
                    System.out.println(e.getDamage() + (level*2.5));
                    e.setDamage(e.getDamage() + (level*2.5));
                }
            }
        }
    }
}
