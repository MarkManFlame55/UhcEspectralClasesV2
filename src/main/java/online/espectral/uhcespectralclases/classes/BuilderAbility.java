package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.item.BuilderItem;
import org.bukkit.event.Listener;

public class BuilderAbility implements Listener {

    public static void init(UhcPlayer uhcPlayer) {
        uhcPlayer.getPlayer().getInventory().addItem(BuilderItem.tool());
        uhcPlayer.getPlayer().getInventory().addItem(BuilderItem.block());
    }
}
