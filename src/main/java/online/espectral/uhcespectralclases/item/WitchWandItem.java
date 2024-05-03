package online.espectral.uhcespectralclases.item;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.game.WandAbility;
import online.espectral.uhcespectralclases.util.ServerMessage;
import online.espectral.uhcespectralclases.util.Time;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class WitchWandItem implements Listener {

    static UhcGame uhcGame = UhcEspectralClases.getUhcGame();
    HashMap<UUID, Long> cooldown;
    long cooldownTime = Time.seconds(30);

    public WitchWandItem() {
        this.cooldown = new HashMap<>();
    }
     public static ItemStack give() {
        ItemStack itemStack = new ItemStack(Material.STICK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Witch Wand");
        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Varita de Bruja");
        itemMeta.setMaxStackSize(1);
        itemMeta.setEnchantmentGlintOverride(true);

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "Click Izquierdo para cambiar modo");
        itemLore.add(ChatColor.DARK_GRAY + "Click Derecho para usar habilidad");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            ItemStack itemStack = e.getItem();
            if (itemStack != null &&  itemStack.isSimilar(give())) {
                Player player = e.getPlayer();
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.WITCH)) {
                    World world = player.getWorld();
                    switch (uhcPlayer.getAbility()) {
                        case HEAL:
                            uhcPlayer.setAbility(WandAbility.HURT);
                            player.sendTitle("", ChatColor.DARK_RED + "☠", 1, 10, 1);
                            break;
                        case HURT:
                            uhcPlayer.setAbility(WandAbility.FIREBALL);
                            player.sendTitle("", ChatColor.GOLD + "🔥", 1, 10, 1);
                            break;
                        case FIREBALL:
                            uhcPlayer.setAbility(WandAbility.REVEAL);
                            player.sendTitle("", ChatColor.YELLOW + "☼", 1, 10, 1);
                            break;
                        case REVEAL:
                            uhcPlayer.setAbility(WandAbility.HEAL);
                            player.sendTitle("", ChatColor.RED + "❤", 1, 10, 1);
                            break;
                    }
                    world.playSound(player, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1.0f, 2.0f);
                } else {
                    ServerMessage.unicastError(player, ServerMessage.NOT_ALLOWED);
                }
            }
        } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            ItemStack itemStack = e.getItem();
            Player player = e.getPlayer();
            if (itemStack != null && itemStack.isSimilar(give())) {
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.WITCH)) {
                    if (!this.cooldown.containsKey(player.getUniqueId())) {
                        this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                        rightClickAbility(uhcPlayer);
                    } else {
                        long timeElapsed = System.currentTimeMillis() - this.cooldown.get(player.getUniqueId());
                        if (timeElapsed >= cooldownTime) {
                            this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                            rightClickAbility(uhcPlayer);
                        } else {
                            if (uhcPlayer.canSeeCooldown()) {
                                ServerMessage.cooldownMesssage(player, timeElapsed, cooldownTime);
                            }
                        }
                    }
                } else {
                    ServerMessage.unicastError(player, ServerMessage.NOT_ALLOWED);
                }
            }
        }
    }
    private void rightClickAbility(UhcPlayer uhcPlayer) {
        Player player = uhcPlayer.getPlayer();
        World world = player.getWorld();
        switch (uhcPlayer.getAbility()) {
            case HEAL:
                for (Entity entity : player.getNearbyEntities(3,2,3)) {
                    if (entity instanceof LivingEntity livingEntity) {
                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 1, false, false, false));
                    }
                    world.spawnParticle(Particle.HEART, player.getLocation(), 20,2,2,2);
                    world.playSound(player.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1.0f, 1.0f);
                }
                break;
            case HURT:
                for (Entity entity : player.getNearbyEntities(3,2,3)) {
                    if (entity instanceof LivingEntity livingEntity) {
                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE, 1, 0, false, false, false));
                    }
                    world.spawnParticle(Particle.ANGRY_VILLAGER, player.getLocation(),20, 2,2,2);
                    world.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1.0f, 1.0f);
                }
                break;
            case FIREBALL:
                Fireball fireball = player.launchProjectile(Fireball.class);
                Vector direction = player.getLocation().getDirection();
                fireball.setDirection(direction.multiply(2));
                fireball.setYield(2f);
                world.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1.0f, 1.0f);
                break;
            case REVEAL:
                player.playSound(player, Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 1.0f, 1.0f);
                for (Entity entity : player.getNearbyEntities(32,32,32)) {
                    if (entity instanceof Player player1) {
                        player1.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Time.secondsToTicks(10), 0, false, false));
                        player1.playSound(player1, Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.0f);
                        ServerMessage.unicastInfo(player1, ChatColor.GRAY + "Una bruja te ha detectado usando su hechizo...");
                    }
                }
                break;
        }
    }
}
