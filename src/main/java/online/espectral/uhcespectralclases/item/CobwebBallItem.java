package online.espectral.uhcespectralclases.item;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.util.DelayedTask;
import online.espectral.uhcespectralclases.util.ServerMessage;
import online.espectral.uhcespectralclases.util.Time;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

public class CobwebBallItem implements Listener {

    static UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    HashMap<UUID, Long> cooldown;
    long cooldownTime = Time.seconds(45);

    public CobwebBallItem() {
        this.cooldown = new HashMap<>();
    }


    public static ItemStack give() {
        ItemStack itemStack = new ItemStack(Material.SNOWBALL,64);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Cobweb Ball");
        itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Bomba de Telarañas");
        itemMeta.setEnchantmentGlintOverride(true);
        itemMeta.setMaxStackSize(64);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    @EventHandler
    public void onPlayerShoot(ProjectileLaunchEvent e) {
        if (e.getEntity() instanceof Snowball snowball && snowball.getShooter() instanceof Player player) {
            ItemStack itemStack = snowball.getItem();
            if (itemStack.isSimilar(give())) {
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.SPIDER)) {
                    if (!this.cooldown.containsKey(player.getUniqueId())) {
                        this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                        snowball.setItem(new ItemStack(Material.COBWEB));
                    } else {
                        long timeElapsed = System.currentTimeMillis() - this.cooldown.get(player.getUniqueId());
                        if (timeElapsed >= cooldownTime) {
                            this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                            snowball.setItem(new ItemStack(Material.COBWEB));
                        } else {
                            e.setCancelled(true);
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
    @EventHandler
    public static void onSnowballHit(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Snowball snowball && snowball.getItem().getType().equals(Material.COBWEB)) {
            if (e.getHitBlock() != null) {
                Location pos = e.getHitBlock().getLocation().clone().add(0,1,0);
                generateCobweb(pos);
            } else if (e.getHitEntity() != null) {
                Location pos = e.getHitEntity().getLocation();
                generateCobweb(pos);
            }
        }
    }
    private static void generateCobweb(Location pos) {
        World world = pos.getWorld();
        assert world != null;
        Block block = world.getBlockAt(pos);
        if (block.isPassable()) {
            block.setType(Material.COBWEB);
            for (BlockFace blockFace : BlockFace.values()) {
                if (!blockFace.equals(BlockFace.UP) && !blockFace.equals(BlockFace.DOWN)) {
                    Block relative = block.getRelative(blockFace);
                    if (relative.isPassable()) {
                        relative.setType(Material.COBWEB);
                        new DelayedTask(() -> {
                            relative.setType(Material.AIR);
                        }, Time.seconds(10));
                    }
                }
            }
            world.playSound(pos, Sound.BLOCK_COBWEB_PLACE, 1.0f, 1.0f);
        }
    }
}
