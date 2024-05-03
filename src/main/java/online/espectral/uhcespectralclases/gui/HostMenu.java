package online.espectral.uhcespectralclases.gui;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.item.MenuItems;
import online.espectral.uhcespectralclases.item.SelectorItem;
import online.espectral.uhcespectralclases.util.Description;
import online.espectral.uhcespectralclases.util.ServerMessage;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HostMenu implements Listener {

    static UhcGame uhcGame = UhcEspectralClases.getUhcGame();
    static String inventoryName = "Panel de Control de Hosts";

    public static void open(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 27, inventoryName);

        for (int i = 0; i < inventory.getSize();i++) {
            inventory.setItem(i, MenuItems.empty());
        }

        inventory.setItem(10, MenuItems.selector());
        inventory.setItem(12, MenuItems.startButton());
        inventory.setItem(14, MenuItems.reload());
        inventory.setItem(16, MenuItems.help());

        player.openInventory(inventory);
        player.playSound(player, Sound.BLOCK_ENDER_CHEST_OPEN, 1.0f, 1.0f);
    }

    @EventHandler
    public static void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase(inventoryName)) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            ItemStack itemStack = e.getCurrentItem();
            if (itemStack != null) {
                if (itemStack.isSimilar(MenuItems.selector())) {
                    Server server = player.getServer();
                    player.closeInventory();
                    for (Player player1 : server.getOnlinePlayers()) {
                        uhcGame.addPlayer(player1.getUniqueId());
                        if (!player1.getInventory().contains(SelectorItem.give())) {
                            player1.getInventory().addItem(SelectorItem.give());
                            ServerMessage.unicastSuccess(player1, "Has recibido el " + SelectorItem.give().getItemMeta().getDisplayName());
                            player1.playSound(player1, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
                        }
                    }
                }
                if (itemStack.isSimilar(MenuItems.startButton())) {
                    uhcGame.start();
                    player.closeInventory();
                }
                if (itemStack.isSimilar(MenuItems.reload())) {
                    uhcGame.reload();
                    player.closeInventory();
                }
                if (itemStack.isSimilar(MenuItems.help())) {
                    for (int i = 0; i < Description.helpMessage().size(); i++) {
                        player.sendMessage(Description.helpMessage().get(i));
                    }
                    player.closeInventory();
                }
            }
        }
    }
}
