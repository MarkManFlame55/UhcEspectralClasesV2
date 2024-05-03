package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.util.DelayedTask;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerMilkPrevnt implements Listener {
    UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    @EventHandler
    public void onPlayerDrinkMilk(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = e.getItem();
        World world = player.getWorld();
        UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
        if (uhcPlayer != null && uhcPlayer.hasUhcClass()) {
            if (itemStack.getType().equals(Material.MILK_BUCKET)) {
                new DelayedTask(() -> {
                    switch (uhcPlayer.getUhcClass()) {
                        case DOLPHIN:
                            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                            if (world.getEnvironment().equals(World.Environment.NETHER)) {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                            }
                            break;
                        case SPIDER:
                            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAVING, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                            break;
                        case IRON_GOLEM:
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                            break;
                        case MINER:
                            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                            break;
                        case BLAZE:
                            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                            break;
                        case BREEZE:
                            player.addPotionEffect(new PotionEffect(PotionEffectType.WIND_CHARGED, PotionEffect.INFINITE_DURATION, 0, false, false, false));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, PotionEffect.INFINITE_DURATION, 1, false, false, false));
                            break;
                    }

                },1);
            }
        }
    }
}
