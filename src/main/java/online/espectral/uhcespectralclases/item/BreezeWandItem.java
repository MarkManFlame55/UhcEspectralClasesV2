package online.espectral.uhcespectralclases.item;

import net.md_5.bungee.api.ChatColor;
import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.util.DelayedTask;
import online.espectral.uhcespectralclases.util.ServerMessage;
import online.espectral.uhcespectralclases.util.Time;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BreezeWandItem implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();
    HashMap<UUID, Long> cooldownR;
    HashMap<UUID, Long> cooldownL;
    double SAFE_FALL_DISTANCE_DEFAULT = 3.0D;
    long cooldownTimeR = Time.seconds(1);
    long cooldownTimeL = Time.seconds(60);

    public static BukkitTask BREEZE_FALL_CHECK;

    public BreezeWandItem() {
        this.cooldownL = new HashMap<>();
        this.cooldownR = new HashMap<>();
    }

    public static ItemStack give() {
        ItemStack itemStack = new ItemStack(Material.BREEZE_ROD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Breeze Wand");
        itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.of(new Color(178, 242, 245)) + "Varita de Breeze");
        itemMeta.setMaxStackSize(1);
        itemMeta.setEnchantmentGlintOverride(true);

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "Click Izquierdo para Impulso");
        itemLore.add(ChatColor.DARK_GRAY + "Click Derecho para Wind Charge");
        itemMeta.setLore(itemLore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack itemStack = e.getItem();
            if (itemStack != null && itemStack.isSimilar(give())) {
                Player player = e.getPlayer();
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.BREEZE)) {
                    if (!this.cooldownR.containsKey(player.getUniqueId())) {
                        this.cooldownR.put(player.getUniqueId(), System.currentTimeMillis());
                        rightClickAbility(player);
                    } else {
                        long timeElapsed = System.currentTimeMillis() - this.cooldownR.get(player.getUniqueId());
                        if (timeElapsed >= cooldownTimeR) {
                            this.cooldownR.put(player.getUniqueId(), System.currentTimeMillis());
                            rightClickAbility(player);
                        }
                    }
                }
            }
        } else if (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR)) {
            ItemStack itemStack = e.getItem();
            if (itemStack != null && itemStack.isSimilar(give())) {
                Player player = e.getPlayer();
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.BREEZE)) {
                    if (!this.cooldownL.containsKey(player.getUniqueId())) {
                        this.cooldownL.put(player.getUniqueId(), System.currentTimeMillis());
                        leftClickAbility(player);
                    } else {
                        long timeElapsed = System.currentTimeMillis() - this.cooldownL.get(player.getUniqueId());
                        if (timeElapsed >= cooldownTimeL) {
                            this.cooldownL.put(player.getUniqueId(), System.currentTimeMillis());
                            leftClickAbility(player);
                        } else {
                            if (uhcPlayer.canSeeCooldown()) {
                                ServerMessage.cooldownMesssage(player, timeElapsed, cooldownTimeL);
                            }
                        }
                    }
                } else {
                    ServerMessage.unicastError(player, ServerMessage.NOT_ALLOWED);
                }
            }
        }
    }
    private void rightClickAbility(Player player) {
        World world = player.getWorld();
        WindCharge windCharge = player.launchProjectile(WindCharge.class);
        Vector direction = player.getLocation().getDirection();
        windCharge.setDirection(direction.multiply(5));
        world.playSound(player.getLocation(), Sound.ENTITY_BREEZE_SHOOT, 1.0f, 1.0f);
        player.setCooldown(Material.BREEZE_ROD, 20);
    }
    private void leftClickAbility(Player player) {
        World world = player.getWorld();
        Vector direction = player.getLocation().getDirection().setY(0.45);
        player.setVelocity(direction.multiply(2));
        player.setVelocity(player.getVelocity().add(new Vector(0,0.45, 0)));
        world.playSound(player.getLocation(), Sound.ENTITY_BREEZE_JUMP, 1.0f, 1.0f);
        AttributeInstance fallDamage = player.getAttribute(Attribute.GENERIC_SAFE_FALL_DISTANCE);
        if (fallDamage != null) {
            fallDamage.setBaseValue(319);
        }
        new DelayedTask(() -> {
            BREEZE_FALL_CHECK = new BukkitRunnable() {
                @Override
                public void run() {
                    if (player.isOnGround()) {
                        world.playSound(player.getLocation(), Sound.ENTITY_BREEZE_WIND_BURST, 1.0f, 1.0f);
                        world.spawnParticle(Particle.GUST, player.getLocation(), 10, 3,1,3);
                        for (Entity entity : player.getNearbyEntities(3,2,3)) {
                            if (entity instanceof LivingEntity livingEntity) {
                                Location entityPos = livingEntity.getLocation();
                                Vector launchDirection = entityPos.toVector().add(entityPos.toVector().multiply(-1));
                                launchDirection.setY(1);
                                livingEntity.setVelocity(launchDirection);
                            }
                        }
                        new DelayedTask(() -> {
                            if (fallDamage != null) {
                                fallDamage.setBaseValue(SAFE_FALL_DISTANCE_DEFAULT);
                            }
                        }, 1);
                        this.cancel();
                    }
                }
            }.runTaskTimer(UhcEspectralClases.getPlugin(UhcEspectralClases.class), 0, 1);
        },5);

    }
}
