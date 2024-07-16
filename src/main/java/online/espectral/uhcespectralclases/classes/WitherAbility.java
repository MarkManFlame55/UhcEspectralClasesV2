package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.item.WitherSwordItem;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class WitherAbility implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    public static void init(UhcPlayer uhcPlayer) {
        uhcPlayer.getPlayer().getInventory().addItem(WitherSwordItem.stone());
    }

    @EventHandler
    public void onPlayerEffect(EntityPotionEffectEvent e) {
        if (e.getEntity() instanceof Player player && e.getNewEffect() != null) {
            UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
            if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.WITHER)) {
                PotionEffectType type = e.getNewEffect().getType();
                if (type.equals(PotionEffectType.WITHER)) {
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player player && e.getDamager() instanceof LivingEntity livingEntity) {
            UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
            if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.WITHER)) {
                ItemStack itemStack = livingEntity.getEquipment().getItemInMainHand();
                if (itemStack != null && itemStack.containsEnchantment(Enchantment.SMITE)) {
                    int level = itemStack.getEnchantmentLevel(Enchantment.SMITE);
                    e.setDamage(e.getDamage() + (level*2.5));
                }
            }
        }
    }
}
