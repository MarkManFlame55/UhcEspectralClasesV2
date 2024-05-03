package online.espectral.uhcespectralclases.item;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.util.DelayedTask;
import online.espectral.uhcespectralclases.util.ServerMessage;
import online.espectral.uhcespectralclases.util.Time;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class WarriorItem implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    HashMap<UUID, Long> cooldown;
    long cooldownTime = Time.seconds(45);

    public WarriorItem() {
        this.cooldown = new HashMap<>();
    }

    public static ItemStack give() {
        ItemStack itemStack = new ItemStack(Material.GOLDEN_AXE);
        Damageable itemMeta = (Damageable) itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Warrior Axe");
        itemMeta.setMaxStackSize(1);
        itemMeta.setDisplayName(ChatColor.GOLD + "Hacha de Guerrero");
        itemMeta.setMaxDamage(1024);

        AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", 6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier speed = new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", -3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, speed);

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "Click Derecho para usar habilidad!");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            Player player = e.getPlayer();
            if (player.isSneaking()) {
                ItemStack itemStack = e.getItem();
                if (itemStack != null && itemStack.getItemMeta().getItemName().equalsIgnoreCase("Warrior Axe")) {
                    UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                    if (uhcPlayer != null && uhcPlayer.getUhcClass().equals(UhcClass.WARRIOR)) {
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
    }

    private void rightClickAbility(UhcPlayer uhcPlayer) {
        assert uhcPlayer != null;
        Player player = uhcPlayer.getPlayer();
        World world = player.getWorld();
        world.playSound(player.getLocation(), Sound.ENTITY_WARDEN_ROAR, 2.0f, 2.5f);
        Collection<PotionEffect> playerEffects = player.getActivePotionEffects();
        uhcPlayer.saveEffects(playerEffects);
        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Time.secondsToTicks(10), 0, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Time.secondsToTicks(10), 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, Time.secondsToTicks(10), 1, false, false, false));
        uhcPlayer.setEnraged(true);
        new DelayedTask(() -> {
            if (uhcPlayer.isEnraged()) {
                uhcPlayer.setEnraged(false);
                player.playSound(player, Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 1.0f);
            }
        }, Time.secondsToTicks(10));
    }

}
