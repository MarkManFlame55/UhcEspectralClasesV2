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

import java.util.ArrayList;
import java.util.List;

public class WitherSwordItem implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    public static ItemStack stone() {
        ItemStack itemStack = new ItemStack(Material.STONE_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Wither Sword");
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "Espada Putrefacta");
        itemMeta.setUnbreakable(true);
        itemMeta.setEnchantmentGlintOverride(true);

        List<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.DARK_GRAY + "Tier 1");
        itemMeta.setLore(itemLore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack iron() {
        ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Wither Sword");
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "Espada Putrefacta");
        itemMeta.setUnbreakable(true);
        itemMeta.setEnchantmentGlintOverride(true);

        List<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.DARK_GRAY + "Tier 2");
        itemMeta.setLore(itemLore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack diamond() {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Wither Sword");
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "Espada Putrefacta");
        itemMeta.setUnbreakable(true);
        itemMeta.setEnchantmentGlintOverride(true);

        List<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.DARK_GRAY + "Tier 3");
        itemMeta.setLore(itemLore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player player) {
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            if (itemStack.hasItemMeta() && itemStack.getItemMeta().getItemName().equalsIgnoreCase("Wither Sword")) {
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.WITHER)) {
                    if (e.getEntity() instanceof LivingEntity livingEntity) {
                        if (itemStack.isSimilar(stone())) {
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, Time.secondsToTicks(3), 0, false, false, false));
                        }
                        if (itemStack.isSimilar(iron())) {
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, Time.secondsToTicks(5), 1, false, false, false));
                        }
                        if (itemStack.isSimilar(diamond())) {
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, Time.secondsToTicks(5), 2, false, false, false));
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
