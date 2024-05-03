package online.espectral.uhcespectralclases.item;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.util.DelayedTask;
import online.espectral.uhcespectralclases.util.ServerMessage;
import online.espectral.uhcespectralclases.util.Time;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BlazeItem implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();
    HashMap<UUID, Long> cooldown;
    long cooldownTime = Time.minutes(1);

    public BlazeItem() {
        this.cooldown = new HashMap<>();
    }


    public static ItemStack give() {
        ItemStack itemStack = new ItemStack(Material.FIRE_CHARGE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Blaze Ability Item");
        itemMeta.setDisplayName(ChatColor.GOLD + "Activar Habilidad");
        itemMeta.setEnchantmentGlintOverride(true);
        itemMeta.setMaxStackSize(1);

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "Click Derecho para activar la habilidad");

        itemMeta.setLore(itemLore);
        itemMeta.setFireResistant(true);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            ItemStack itemStack = e.getItem();
            if (itemStack != null && itemStack.isSimilar(give())) {
                e.setCancelled(true);
                Player player = e.getPlayer();
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.BLAZE)) {
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
        if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.BLAZE)) {
            Player player = uhcPlayer.getPlayer();
            World world = player.getWorld();
            world.spawnParticle(Particle.FLAME, player.getLocation(), 60, 2,2,2);
            world.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1.0f, 1.0f);
            world.playSound(player.getLocation(), Sound.ENTITY_BLAZE_AMBIENT, 1.0f, 1.0f);
            uhcPlayer.setBlazeAcive(true);
            player.setVisualFire(true);
            new DelayedTask(() -> {
                uhcPlayer.setBlazeAcive(false);
                player.setVisualFire(false);
                world.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 1.0f);
            }, Time.secondsToTicks(10));
        }
    }
}
