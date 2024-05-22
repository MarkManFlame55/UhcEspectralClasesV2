package online.espectral.uhcespectralclases.classes;

import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.item.CannonItem;
import online.espectral.uhcespectralclases.item.PluginItems;

public class GunnerAbility {
    public static void init(UhcPlayer uhcPlayer) {
        uhcPlayer.getPlayer().getInventory().addItem(CannonItem.give());
        uhcPlayer.getPlayer().getInventory().setHelmet(CannonItem.helmet());
        uhcPlayer.getPlayer().getInventory().addItem(PluginItems.cannonBullet(10));
    }
}
