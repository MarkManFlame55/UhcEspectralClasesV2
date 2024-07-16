package online.espectral.uhcespectralclases.item;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.util.ServerMessage;
import online.espectral.uhcespectralclases.util.Time;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.StructureSearchResult;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CannonItem implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();
    HashMap<UUID, Long> cooldown;
    long cooldownTime = Time.seconds(1);

    public  CannonItem() {
        this.cooldown = new HashMap<>();
    }


    public static ItemStack give() {
        ItemStack itemStack = new ItemStack(Material.COAST_ARMOR_TRIM_SMITHING_TEMPLATE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Powder Cannon");
        itemMeta.setDisplayName(ChatColor.RED + "Cañon de Polvora");
        itemMeta.setEnchantmentGlintOverride(true);
        itemMeta.setMaxStackSize(1);
        itemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "Click Derecho para Disparar");
        itemLore.add(ChatColor.DARK_GRAY + "(3s Cooldown)");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack helmet() {
        ItemStack itemStack = new ItemStack(Material.IRON_HELMET);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Gunner Helmet");
        itemMeta.setDisplayName(ChatColor.RED + "Casco de Cañonero");
        itemMeta.addEnchant(Enchantment.BLAST_PROTECTION, 5, true);
        itemMeta.setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    private ItemStack tnt() {
        ItemStack itemStack = new ItemStack(Material.TNT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("tnt-ammo");
        itemMeta.setEnchantmentGlintOverride(true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            ItemStack itemStack = e.getItem();
            if (itemStack != null && itemStack.isSimilar(give())) {
                Player player = e.getPlayer();
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.GUNNER)) {
                    if (!this.cooldown.containsKey(player.getUniqueId())) {
                        this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                        rightClickAbility(player);
                    } else {
                        long timeElapsed = System.currentTimeMillis() - this.cooldown.get(player.getUniqueId());
                        if (timeElapsed >= cooldownTime) {
                            this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                            rightClickAbility(player);
                        }
                    }
                } else {
                    ServerMessage.unicastError(player, ServerMessage.NOT_ALLOWED);
                }
            }
        }
    }
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Snowball snowball) {
            if (snowball.getItem().isSimilar(tnt()) && e.getEntity().getShooter() instanceof Player player) {
                Location pos = snowball.getLocation();
                World world = snowball.getWorld();
                world.createExplosion(pos, 1.55f,false, false, player);
            }
        }
    }
    private void rightClickAbility(Player player) {
        boolean gotItem = false;
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack.isSimilar(PluginItems.cannonBullet())) {
                if (!player.getGameMode().equals(GameMode.CREATIVE)) {
                    itemStack.setAmount(itemStack.getAmount() - 1);
                }
                Snowball snowball = player.launchProjectile(Snowball.class);
                Vector direction = player.getEyeLocation().getDirection();
                snowball.setItem(tnt());
                snowball.setVelocity(direction.multiply(1));
                gotItem = true;
            }
        }
        if (!gotItem) {
            ServerMessage.unicastError(player, "Necesitas Balas de Cañon para usar este item");
        }
    }
}
