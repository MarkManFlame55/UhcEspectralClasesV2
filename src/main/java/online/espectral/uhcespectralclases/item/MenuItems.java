package online.espectral.uhcespectralclases.item;

import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.md_5.bungee.api.ChatColor.of;

public class MenuItems {

    public static ItemStack witch(UhcPlayer uhcPlayer) {
        ItemStack itemStack = new ItemStack(Material.STICK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Witch Selector Item");
        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Bruja");

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "--> Click Izquierdo para Seleccionar <--");
        itemLore.add(ChatColor.DARK_GRAY + "-> Click Derecho para ver Descripción <-");
        itemMeta.setLore(itemLore);

        itemMeta.setMaxStackSize(1);
        if (uhcPlayer.getUhcClass() != null) itemMeta.setEnchantmentGlintOverride(uhcPlayer.getUhcClass().equals(UhcClass.WITCH));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack breeze(UhcPlayer uhcPlayer) {
        ItemStack itemStack = new ItemStack(Material.WIND_CHARGE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Breeze Selector Item");
        itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.of(new Color(178, 242, 245)) + "" + ChatColor.BOLD + "Breeze");

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "--> Click Izquierdo para Seleccionar <--");
        itemLore.add(ChatColor.DARK_GRAY + "-> Click Derecho para ver Descripción <-");
        itemMeta.setLore(itemLore);

        itemMeta.setMaxStackSize(1);
        if (uhcPlayer.getUhcClass() != null) {
            itemMeta.setEnchantmentGlintOverride(uhcPlayer.getUhcClass().equals(UhcClass.BREEZE));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack spider(UhcPlayer uhcPlayer) {
        ItemStack itemStack = new ItemStack(Material.FERMENTED_SPIDER_EYE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Spider Selector Item");
        itemMeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Araña");

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "--> Click Izquierdo para Seleccionar <--");
        itemLore.add(ChatColor.DARK_GRAY + "-> Click Derecho para ver Descripción <-");
        itemMeta.setLore(itemLore);

        itemMeta.setMaxStackSize(1);
        if (uhcPlayer.getUhcClass() != null) itemMeta.setEnchantmentGlintOverride(uhcPlayer.getUhcClass().equals(UhcClass.SPIDER));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack dolphin(UhcPlayer uhcPlayer) {
        ItemStack itemStack = new ItemStack(Material.HEART_OF_THE_SEA);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Dolphin Selector Item");
        itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Delfin");

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "--> Click Izquierdo para Seleccionar <--");
        itemLore.add(ChatColor.DARK_GRAY + "-> Click Derecho para ver Descripción <-");
        itemMeta.setLore(itemLore);

        itemMeta.setMaxStackSize(1);
        if (uhcPlayer.getUhcClass() != null) itemMeta.setEnchantmentGlintOverride(uhcPlayer.getUhcClass().equals(UhcClass.DOLPHIN));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack iron_golem(UhcPlayer uhcPlayer) {
        ItemStack itemStack = new ItemStack(Material.IRON_BLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Iron Golem Selector Item");
        itemMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Iron Golem");

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "--> Click Izquierdo para Seleccionar <--");
        itemLore.add(ChatColor.DARK_GRAY + "-> Click Derecho para ver Descripción <-");
        itemMeta.setLore(itemLore);

        itemMeta.setMaxStackSize(1);
        if (uhcPlayer.getUhcClass() != null) {
            itemMeta.setEnchantmentGlintOverride(uhcPlayer.getUhcClass().equals(UhcClass.IRON_GOLEM));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack warrior(UhcPlayer uhcPlayer) {
        ItemStack itemStack = new ItemStack(Material.IRON_AXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Warrior Selector Item");
        itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.of(new Color(203,109,81)) + "" + ChatColor.BOLD + "Guerrero");

        AttributeModifier nodamage = new AttributeModifier(UUID.randomUUID(), "generic.damage", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, nodamage);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "--> Click Izquierdo para Seleccionar <--");
        itemLore.add(ChatColor.DARK_GRAY + "-> Click Derecho para ver Descripción <-");
        itemMeta.setLore(itemLore);

        itemMeta.setMaxStackSize(1);
        if (uhcPlayer.getUhcClass() != null) {
            itemMeta.setEnchantmentGlintOverride(uhcPlayer.getUhcClass().equals(UhcClass.WARRIOR));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack gunner(UhcPlayer uhcPlayer) {
        ItemStack itemStack = new ItemStack(Material.TNT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Archer Selector Item");
        itemMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Cañonero");

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "--> Click Izquierdo para Seleccionar <--");
        itemLore.add(ChatColor.DARK_GRAY + "-> Click Derecho para ver Descripción <-");
        itemMeta.setLore(itemLore);

        itemMeta.setMaxStackSize(1);
        if (uhcPlayer.getUhcClass() != null) {
            itemMeta.setEnchantmentGlintOverride(uhcPlayer.getUhcClass().equals(UhcClass.GUNNER));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack builder(UhcPlayer uhcPlayer) {
        ItemStack itemStack = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Builder Selector Item");
        itemMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Arquitecto");

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "--> Click Izquierdo para Seleccionar <--");
        itemLore.add(ChatColor.DARK_GRAY + "-> Click Derecho para ver Descripción <-");
        itemMeta.setLore(itemLore);

        itemMeta.setMaxStackSize(1);
        if (uhcPlayer.getUhcClass() != null) {
            itemMeta.setEnchantmentGlintOverride(uhcPlayer.getUhcClass().equals(UhcClass.BUILDER));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack miner(UhcPlayer uhcPlayer) {
        ItemStack itemStack = new ItemStack(Material.GOLDEN_PICKAXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Miner Selector Item");
        itemMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Minero");

        AttributeModifier nodamage = new AttributeModifier(UUID.randomUUID(), "generic.damage", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, nodamage);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "--> Click Izquierdo para Seleccionar <--");
        itemLore.add(ChatColor.DARK_GRAY + "-> Click Derecho para ver Descripción <-");
        itemMeta.setLore(itemLore);

        itemMeta.setMaxStackSize(1);
        if (uhcPlayer.getUhcClass() != null) {
            itemMeta.setEnchantmentGlintOverride(uhcPlayer.getUhcClass().equals(UhcClass.MINER));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack antman(UhcPlayer uhcPlayer) {
        ItemStack itemStack = new ItemStack(Material.POLISHED_BLACKSTONE_BUTTON);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Antman Selector Item");
        itemMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Antman");

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "--> Click Izquierdo para Seleccionar <--");
        itemLore.add(ChatColor.DARK_GRAY + "-> Click Derecho para ver Descripción <-");
        itemMeta.setLore(itemLore);

        itemMeta.setMaxStackSize(1);
        if (uhcPlayer.getUhcClass() != null) {
            itemMeta.setEnchantmentGlintOverride(uhcPlayer.getUhcClass().equals(UhcClass.ANTMAN));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack wither(UhcPlayer uhcPlayer) {
        ItemStack itemStack = new ItemStack(Material.WITHER_ROSE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Wither Selector Item");
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Wither");

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "--> Click Izquierdo para Seleccionar <--");
        itemLore.add(ChatColor.DARK_GRAY + "-> Click Derecho para ver Descripción <-");
        itemMeta.setLore(itemLore);

        itemMeta.setMaxStackSize(1);
        if (uhcPlayer.getUhcClass() != null) itemMeta.setEnchantmentGlintOverride(uhcPlayer.getUhcClass().equals(UhcClass.WITHER));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack blaze(UhcPlayer uhcPlayer) {
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Blaze Selector Item");
        itemMeta.setDisplayName(net.md_5.bungee.api.ChatColor.of(new Color(249,195,13)) + "" + ChatColor.BOLD + "Blaze");

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "--> Click Izquierdo para Seleccionar <--");
        itemLore.add(ChatColor.DARK_GRAY + "-> Click Derecho para ver Descripción <-");
        itemMeta.setLore(itemLore);

        itemMeta.setMaxStackSize(1);
        if (uhcPlayer.getUhcClass() != null) {
            itemMeta.setEnchantmentGlintOverride(uhcPlayer.getUhcClass().equals(UhcClass.BLAZE));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack cooldownToggle(UhcPlayer uhcPlayer) {
        ItemStack itemStack = new ItemStack(Material.CLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Visual Cooldown Toggle");
        itemMeta.setDisplayName(ChatColor.GOLD + "Mostrar/Ocultar mensaje de Cooldown");

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        if (uhcPlayer.canSeeCooldown()) {
            itemLore.add(ChatColor.GRAY + "Mensaje de Cooldown: " + ChatColor.GREEN + ChatColor.BOLD + "ON");
        } else {
            itemLore.add(ChatColor.GRAY + "Mensaje de Cooldown: " + ChatColor.RED + ChatColor.BOLD + "OFF");
        }
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack startButton() {
        ItemStack itemStack = new ItemStack(Material.LIME_DYE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Class Start Button");
        itemMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Comenzar Partida");
        itemMeta.setMaxStackSize(1);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack reload() {
        ItemStack itemStack = new ItemStack(Material.MUSIC_DISC_CHIRP);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Reload Classes Button");
        itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Reiniciar Clases");
        itemMeta.setMaxStackSize(1);
        itemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack selector() {
        ItemStack itemStack = new ItemStack(Material.AMETHYST_CLUSTER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Give Selector Button");
        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Dar Selector a los jugadores");
        itemMeta.setMaxStackSize(1);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack help() {
        ItemStack itemStack = new ItemStack(Material.KNOWLEDGE_BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Command Helper Button");
        itemMeta.setDisplayName(ChatColor.YELLOW + "Mostrar ayuda de Comandos");
        itemMeta.setMaxStackSize(1);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack empty() {
        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setHideTooltip(true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
