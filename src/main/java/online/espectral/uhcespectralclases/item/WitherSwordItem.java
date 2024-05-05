package online.espectral.uhcespectralclases.item;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.util.ServerMessage;
import online.espectral.uhcespectralclases.util.Time;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WitherSwordItem implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    public static ItemStack give() {
        ItemStack itemStack = new ItemStack(Material.STONE_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Wither Sword");
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "Espada Putrefacta");
        itemMeta.setUnbreakable(true);
        itemMeta.setEnchantmentGlintOverride(true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player player) {
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            if (itemStack != null && itemStack.isSimilar(give())) {
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.WITHER)) {
                    if (e.getEntity() instanceof LivingEntity livingEntity) {
                        if (livingEntity instanceof Player player1) {
                            if (!player1.isBlocking()) {
                                player1.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, Time.secondsToTicks(3), 1, false, false, false));
                            }
                        } else {
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, Time.secondsToTicks(3), 1, false, false, false));
                        }

                    } else {
                        e.setCancelled(true);
                        ServerMessage.unicastError(player, ServerMessage.NOT_ALLOWED);
                    }
                }
            }
        }
    }
}
