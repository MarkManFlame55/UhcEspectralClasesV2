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
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;

public class GolemHammerItem implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    HashMap<UUID, Long> cooldown;
    long cooldownTime = Time.seconds(30);

    public GolemHammerItem() {
        this.cooldown = new HashMap<>();
    }

    public static ItemStack give() {
        ItemStack itemStack = new ItemStack(Material.IRON_AXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Golem Hammer");
        itemMeta.setDisplayName(ChatColor.GRAY + "Martillo de Golem");

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "Click Derecho al suelo para usar la habilidad");
        itemMeta.setLore(itemLore);

        AttributeModifier nodamage = new AttributeModifier(UUID.randomUUID(), "nodamage", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        itemMeta.addAttributeModifier(Attribute.GENERIC_LUCK, nodamage);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getBlockFace().equals(BlockFace.UP)) {
            Player player = e.getPlayer();
            ItemStack itemStack = e.getItem();
            if (itemStack != null && itemStack.getItemMeta().getItemName().equalsIgnoreCase("Golem Hammer")) {
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                Block block = e.getClickedBlock();
                if (block != null) {
                    if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.IRON_GOLEM)) {
                        if (!this.cooldown.containsKey(player.getUniqueId())) {
                            this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                            rightClickAbility(uhcPlayer, block);
                        } else {
                            long timeElapsed = System.currentTimeMillis() - this.cooldown.get(player.getUniqueId());
                            if (timeElapsed >= cooldownTime) {
                                this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                                rightClickAbility(uhcPlayer, block);
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
    private void rightClickAbility(UhcPlayer uhcPlayer, Block block) {
        Player player = uhcPlayer.getPlayer();
        World world = player.getWorld();
        Location pos = block.getLocation();
        world.spawnParticle(Particle.SWEEP_ATTACK, pos.clone().add(0,1,0), 50, 3,0,3);
        for (Entity entity : world.getNearbyEntities(pos, 3, 3, 3)) {
            if (entity instanceof LivingEntity livingEntity && livingEntity != player) {
                Location entityPos = livingEntity.getLocation();
                Vector launchDirection = entityPos.toVector().add(entityPos.toVector().multiply(-1));
                launchDirection.setY(1.5);
                livingEntity.damage(10, player);
                new DelayedTask(() -> {
                    livingEntity.setVelocity(launchDirection);
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0, false, false, false));
                },1);
            }
        }
        world.playSound(pos, Sound.ITEM_MACE_SMASH_GROUND_HEAVY, 1.0f, 1.0f);
        new DelayedTask(() -> {
            world.playSound(pos, Sound.BLOCK_ANVIL_LAND, 0.5f, 1.0f);
        },20);
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, Time.secondsToTicks(3), 1, false, false, false));
    }
}
