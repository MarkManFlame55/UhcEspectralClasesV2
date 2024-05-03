package online.espectral.uhcespectralclases.item;

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
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.awt.Color;
import java.util.HashMap;
import java.util.UUID;

public class AntmanItem implements Listener {

    UhcGame game = UhcEspectralClases.getUhcGame();

    double BLOCK_ENTITY_INTERACTION_RANGE_DEFAULT = 5.0D;
    double STEP_HEIGHT_DEFAULT = 0.6D;
    double JUMP_STRENGTH_DEFAULT = 0.41999998688697815D;
    long cooldownTime = Time.minutes(1);
    HashMap<UUID, Long> cooldown;

    public AntmanItem() {
        this.cooldown = new HashMap<>();
    }

    public static ItemStack give() {
        ItemStack itemStack = new ItemStack(Material.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setMaxStackSize(1);
        itemMeta.setItemName("espectral.shape_shifter");
        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Cambia Formas");
        itemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        itemMeta.setEnchantmentGlintOverride(true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            ItemStack itemStack = e.getItem();
            Player player = e.getPlayer();
            if (itemStack != null && itemStack.getItemMeta().getItemName().equals("espectral.shape_shifter")) {
                UhcPlayer uhcPlayer = game.getPlayer(player.getUniqueId());
                if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.ANTMAN)) {
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
        } else if (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR)) {
            ItemStack itemStack = e.getItem();
            Player player = e.getPlayer();
            if (itemStack != null && itemStack.equals(give())) {
                UhcPlayer uhcPlayer = game.getPlayer(player.getUniqueId());
                if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.ANTMAN)) {
                    leftClickAbility(uhcPlayer);
                } else {
                    ServerMessage.unicastError(player, ServerMessage.NOT_ALLOWED);
                }
            }
        }
    }

    private void rightClickAbility(UhcPlayer uhcPlayer) {
        Player player = uhcPlayer.getPlayer();
        World world = player.getWorld();
        AttributeInstance scale = player.getAttribute(Attribute.GENERIC_SCALE);
        AttributeInstance entityInteraction = player.getAttribute(Attribute.PLAYER_ENTITY_INTERACTION_RANGE);
        AttributeInstance blockInteraction = player.getAttribute(Attribute.PLAYER_BLOCK_INTERACTION_RANGE);
        AttributeInstance stepHeight = player.getAttribute(Attribute.GENERIC_STEP_HEIGHT);
        AttributeInstance jumpStrength = player.getAttribute(Attribute.GENERIC_JUMP_STRENGTH);
        if (scale != null && entityInteraction != null && blockInteraction != null && stepHeight != null && jumpStrength != null) {
            if (uhcPlayer.getScaleState()) {
                scale.setBaseValue(1.5D);
                world.spawnParticle(Particle.TRIAL_SPAWNER_DETECTION, player.getLocation(), 50,1,1,1);
                player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, Time.secondsToTicks(30), 2, false, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, Time.secondsToTicks(30), 0, false, false, false));
                entityInteraction.setBaseValue(BLOCK_ENTITY_INTERACTION_RANGE_DEFAULT+1);
                blockInteraction.setBaseValue(BLOCK_ENTITY_INTERACTION_RANGE_DEFAULT+1);
                stepHeight.setBaseValue(STEP_HEIGHT_DEFAULT+0.5);
                jumpStrength.setBaseValue(0.5D);
            } else {
                scale.setBaseValue(0.5D);
                world.spawnParticle(Particle.TRIAL_SPAWNER_DETECTION_OMINOUS, player.getLocation(), 50,1,1,1);
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Time.secondsToTicks(30), 0, false, false, false));
                entityInteraction.setBaseValue(BLOCK_ENTITY_INTERACTION_RANGE_DEFAULT-1.5);
                blockInteraction.setBaseValue(BLOCK_ENTITY_INTERACTION_RANGE_DEFAULT-1.5);
                stepHeight.setBaseValue(0.3D);
                jumpStrength.setBaseValue(0.37D);
            }
            uhcPlayer.setSizeChanged(true);
            world.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE,1.0f, 1.0f);

            new DelayedTask(() -> {
                scale.setBaseValue(1.0D);
                blockInteraction.setBaseValue(BLOCK_ENTITY_INTERACTION_RANGE_DEFAULT);
                entityInteraction.setBaseValue(BLOCK_ENTITY_INTERACTION_RANGE_DEFAULT);
                stepHeight.setBaseValue(STEP_HEIGHT_DEFAULT);
                jumpStrength.setBaseValue(JUMP_STRENGTH_DEFAULT);
                world.spawnParticle(Particle.SMOKE, player.getLocation(), 10, 1,1,1);
                world.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 1.0f);
                uhcPlayer.setSizeChanged(false);
            }, Time.secondsToTicks(30));
        }
    }
    private void leftClickAbility(UhcPlayer uhcPlayer) {
        Player player = uhcPlayer.getPlayer();
        uhcPlayer.setScaleState(!uhcPlayer.getScaleState());
        player.playSound(player, Sound.ITEM_LODESTONE_COMPASS_LOCK, 1.0f, 1.0f);
        if (uhcPlayer.getScaleState()) {
            player.sendTitle("" , net.md_5.bungee.api.ChatColor.of(new Color(255, 152, 28)) + "+", 0, 10,0);
        } else {
            player.sendTitle("", net.md_5.bungee.api.ChatColor.of(new Color(28, 255,231)) + "-", 0, 10 ,0);
        }
    }
}
