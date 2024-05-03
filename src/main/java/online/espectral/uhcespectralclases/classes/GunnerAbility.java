package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.item.CannonItem;

public class GunnerAbility {
    public static void init(UhcPlayer uhcPlayer) {
        uhcPlayer.getPlayer().getInventory().addItem(CannonItem.give());
        uhcPlayer.getPlayer().getInventory().setHelmet(CannonItem.helmet());
    }
}
