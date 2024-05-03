package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.item.WitchWandItem;
import org.bukkit.event.Listener;

public class WitchClassAbility implements Listener {
    public static void init(UhcPlayer uhcPlayer) {
        uhcPlayer.getPlayer().getInventory().addItem(WitchWandItem.give());
    }
}
