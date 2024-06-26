package online.espectral.uhcespectralclases.gui;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.item.MenuItems;
import online.espectral.uhcespectralclases.util.Description;
import online.espectral.uhcespectralclases.util.ServerMessage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.awt.*;

public class SelectorMenu implements Listener {

    static UhcGame uhcGame = UhcEspectralClases.getUhcGame();
    static String inventoryName = "Selector de Clases";

    public static void open(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54, inventoryName);

        UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
        if (uhcGame.getPlayer(player.getUniqueId()) == null) {
            uhcGame.addPlayer(player.getUniqueId());
            uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
        }

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, MenuItems.empty());
        }

        inventory.setItem(10, MenuItems.witch(uhcPlayer));
        inventory.setItem(11, MenuItems.breeze(uhcPlayer));
        inventory.setItem(12, MenuItems.spider(uhcPlayer));
        inventory.setItem(14, MenuItems.dolphin(uhcPlayer));
        inventory.setItem(15, MenuItems.iron_golem(uhcPlayer));
        inventory.setItem(16, MenuItems.warrior(uhcPlayer));
        inventory.setItem(28, MenuItems.gunner(uhcPlayer));
        inventory.setItem(29, MenuItems.builder(uhcPlayer));
        inventory.setItem(30, MenuItems.miner(uhcPlayer));
        inventory.setItem(32, MenuItems.antman(uhcPlayer));
        inventory.setItem(33, MenuItems.wither(uhcPlayer));
        inventory.setItem(34, MenuItems.blaze(uhcPlayer));
        inventory.setItem(45, MenuItems.credits());
        inventory.setItem(53, MenuItems.cooldownToggle(uhcPlayer));

        player.openInventory(inventory);
        player.playSound(player, Sound.BLOCK_VAULT_ACTIVATE, 1.0f, 1.0f);
    }
    @EventHandler
    public static void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase(inventoryName)) {
            e.setCancelled(true);
            ItemStack itemStack = e.getCurrentItem();
            Player player = (Player) e.getWhoClicked();
            uhcGame.addPlayer(player.getUniqueId());
            if (itemStack != null) {
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                if (uhcPlayer != null) {
                    if (itemStack.isSimilar(MenuItems.cooldownToggle(uhcPlayer))) {
                        uhcPlayer.setSeeCooldown(!uhcPlayer.canSeeCooldown());
                        e.getInventory().setItem(53, MenuItems.cooldownToggle(uhcPlayer));
                    } else if (itemStack.isSimilar(MenuItems.credits())) {
                        for (String i : Description.creditsMessage()) {
                            player.sendMessage(i);
                        }
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 2.0f);
                        player.closeInventory();
                    } else {
                        assert itemStack.getItemMeta() != null;
                        if (itemStack.getItemMeta().getItemName().equalsIgnoreCase("Witch Selector Item")) {
                            if (e.isLeftClick()) {
                                uhcPlayer.setUhcClass(UhcClass.WITCH);
                            }
                            if (e.isRightClick()) {
                                for (String i : Description.witch()) {
                                    player.sendMessage(i);
                                }
                                player.closeInventory();
                            }
                        } else if (itemStack.getItemMeta().getItemName().equalsIgnoreCase("Breeze Selector Item")) {
                            if (e.isLeftClick()) {
                                uhcPlayer.setUhcClass(UhcClass.BREEZE);
                            }
                            if (e.isRightClick()) {
                                for (String i : Description.breeze()) {
                                    player.sendMessage(i);
                                }
                                player.closeInventory();
                            }
                        } else if (itemStack.getItemMeta().getItemName().equalsIgnoreCase("Spider Selector Item")) {
                            if (e.isLeftClick()) {
                                uhcPlayer.setUhcClass(UhcClass.SPIDER);
                            }
                            if (e.isRightClick()) {
                                for (String i : Description.spider()) {
                                    player.sendMessage(i);
                                }
                                player.closeInventory();
                            }
                        } else if (itemStack.getItemMeta().getItemName().equalsIgnoreCase("Dolphin Selector Item")) {
                            if (e.isLeftClick()) {
                                uhcPlayer.setUhcClass(UhcClass.DOLPHIN);
                            }
                            if (e.isRightClick()) {
                                for (String i : Description.dolphin()) {
                                    player.sendMessage(i);
                                }
                                player.closeInventory();
                            }
                        } else if (itemStack.getItemMeta().getItemName().equalsIgnoreCase("Iron Golem Selector Item")) {
                            if (e.isLeftClick()) {
                                uhcPlayer.setUhcClass(UhcClass.IRON_GOLEM);
                            }
                            if (e.isRightClick()) {
                                for (String i : Description.iron_golem()) {
                                    player.sendMessage(i);
                                }
                                player.closeInventory();
                            }
                        } else if (itemStack.getItemMeta().getItemName().equalsIgnoreCase("Warrior Selector Item")) {
                            if (e.isLeftClick()) {
                                uhcPlayer.setUhcClass(UhcClass.WARRIOR);
                            }
                            if (e.isRightClick()) {
                                for (String i : Description.warrior()) {
                                    player.sendMessage(i);
                                }
                                player.closeInventory();
                            }
                        } else if (itemStack.getItemMeta().getItemName().equalsIgnoreCase("Archer Selector Item")) {
                            if (e.isLeftClick()) {
                                uhcPlayer.setUhcClass(UhcClass.GUNNER);
                            }
                            if (e.isRightClick()) {
                                for (String i : Description.gunner()) {
                                    player.sendMessage(i);
                                }
                                player.closeInventory();
                            }
                        } else if (itemStack.getItemMeta().getItemName().equalsIgnoreCase("Builder Selector Item")) {
                            if (e.isLeftClick()) {
                                uhcPlayer.setUhcClass(UhcClass.BUILDER);
                            }
                            if (e.isRightClick()) {
                                for (String i : Description.builder()) {
                                    player.sendMessage(i);
                                }
                                player.closeInventory();
                            }
                        } else if (itemStack.getItemMeta().getItemName().equalsIgnoreCase("Miner Selector Item")) {
                            if (e.isLeftClick()) {
                                uhcPlayer.setUhcClass(UhcClass.MINER);
                            }
                            if (e.isRightClick()) {
                                for (String i : Description.miner()) {
                                    player.sendMessage(i);
                                }
                                player.closeInventory();
                            }
                        } else if (itemStack.getItemMeta().getItemName().equalsIgnoreCase("Antman Selector Item")) {
                            if (e.isLeftClick()) {
                                uhcPlayer.setUhcClass(UhcClass.ANTMAN);
                            }
                            if (e.isRightClick()) {
                                for (String i : Description.antman()) {
                                    player.sendMessage(i);
                                }
                                player.closeInventory();
                            }
                        } else if (itemStack.getItemMeta().getItemName().equalsIgnoreCase("Wither Selector Item")) {
                            if (e.isLeftClick()) {
                                uhcPlayer.setUhcClass(UhcClass.WITHER);
                            }
                            if (e.isRightClick()) {
                                for (String i : Description.wither()) {
                                    player.sendMessage(i);
                                }
                                player.closeInventory();
                            }
                        } else if (itemStack.getItemMeta().getItemName().equalsIgnoreCase("Blaze Selector Item")) {
                            if (e.isLeftClick()) {
                                uhcPlayer.setUhcClass(UhcClass.BLAZE);
                            }
                            if (e.isRightClick()) {
                                for (String i : Description.blaze()) {
                                    player.sendMessage(i);
                                }
                                player.closeInventory();
                            }
                        } else {
                            return;
                        }
                        if (e.getClick().isLeftClick()) {
                            player.closeInventory();
                            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                            ServerMessage.broadcast(player.getDisplayName() + " ha seleccionado la clase " + itemStack.getItemMeta().getDisplayName());
                        }
                    }
                }
            }
        }
    }
}
