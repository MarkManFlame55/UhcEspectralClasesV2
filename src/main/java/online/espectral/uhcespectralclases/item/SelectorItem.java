package online.espectral.uhcespectralclases.item;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.gui.SelectorMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SelectorItem implements Listener {
    static UhcGame uhcGame = UhcEspectralClases.getUhcGame();
    public static ItemStack give() {
        ItemStack itemStack = new ItemStack(Material.AMETHYST_CLUSTER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Class Selector");
        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Selector de Clases v2");
        itemMeta.setMaxStackSize(1);
        itemMeta.setEnchantmentGlintOverride(true);

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "Click Derecho para abrir el selector");

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    @EventHandler
    public static void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack itemStack = e.getItem();
            if (itemStack != null && itemStack.isSimilar(give())) {
                Player player = e.getPlayer();
                SelectorMenu.open(player);
                e.setUseItemInHand(Event.Result.ALLOW);
            }
        }
    }
    @EventHandler
    public static void onBlockPlace(BlockPlaceEvent e) {
        if (e.getItemInHand().isSimilar(give())) {
            e.setCancelled(true);
        }
    }
}
