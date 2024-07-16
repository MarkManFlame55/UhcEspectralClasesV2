package online.espectral.uhcespectralclases.item;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.util.ServerMessage;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MinerPickaxeItem implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();
    double BLOCK_INTERACTION_RANGE_DEFAULT = 5.0D;
    float extraDropChance = 0.0f;

    public static ItemStack stone() {
        ItemStack itemStack = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Miner Pickaxe");
        itemMeta.setDisplayName(ChatColor.YELLOW + "Pico del Minero Espectral");
        itemMeta.setUnbreakable(true);
        itemMeta.setEnchantmentGlintOverride(true);

        /*AttributeModifier blockRange = new AttributeModifier(UUID.randomUUID(), "player.block_interaction_range", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        itemMeta.addAttributeModifier(Attribute.PLAYER_BLOCK_INTERACTION_RANGE, blockRange);*/

        List<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.DARK_GRAY + "Tier 1");
        itemMeta.setLore(itemLore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack iron() {
        ItemStack itemStack = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Miner Pickaxe");
        itemMeta.setDisplayName(ChatColor.YELLOW + "Pico del Minero Espectral");
        itemMeta.setUnbreakable(true);
        itemMeta.setEnchantmentGlintOverride(true);

        /*AttributeModifier blockRange = new AttributeModifier(UUID.randomUUID(), "player.block_interaction_range", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        itemMeta.addAttributeModifier(Attribute.PLAYER_BLOCK_INTERACTION_RANGE, blockRange);*/

        List<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.DARK_GRAY + "Tier 2");
        itemMeta.setLore(itemLore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack diamond() {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Miner Pickaxe");
        itemMeta.setDisplayName(ChatColor.YELLOW + "Pico del Minero Espectral");
        itemMeta.setUnbreakable(true);
        itemMeta.setEnchantmentGlintOverride(true);

        //AttributeModifier blockRange = new AttributeModifier(UUID.randomUUID(), "player.block_interaction_range", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        //itemMeta.addAttributeModifier(Attribute.PLAYER_BLOCK_INTERACTION_RANGE, blockRange);

        List<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.DARK_GRAY + "Tier 3");
        itemMeta.setLore(itemLore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    @EventHandler
    public void onPlayerBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack.hasItemMeta() && itemStack.getItemMeta().getItemName().equalsIgnoreCase("Miner Pickaxe")) {
            UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
            if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.MINER)) {
                Block block = e.getBlock();
                Material blockType = block.getType();
                Random random = new Random();
                World world = block.getWorld();
                if (itemStack.isSimilar(stone())) {
                    extraDropChance = 0.1f;
                }
                if (itemStack.isSimilar(iron())) {
                    extraDropChance = 0.25f;
                }
                if (itemStack.isSimilar(diamond())) {
                    extraDropChance = 0.5f;
                }

                if (blockType.equals(Material.DIAMOND_ORE) || blockType.equals(Material.DEEPSLATE_DIAMOND_ORE)) {
                    if (random.nextFloat() <= extraDropChance) {
                        world.dropItemNaturally(block.getLocation(), new ItemStack(Material.DIAMOND));
                    }
                }
                if (blockType.equals(Material.IRON_ORE) ||blockType.equals(Material.DEEPSLATE_IRON_ORE)) {
                    if (random.nextFloat() <= extraDropChance) {
                        world.dropItemNaturally(block.getLocation(), new ItemStack(Material.RAW_IRON));
                    }
                }
                if (blockType.equals(Material.GOLD_ORE) || blockType.equals(Material.DEEPSLATE_GOLD_ORE)) {
                    if (random.nextFloat() <= extraDropChance) {
                        world.dropItemNaturally(block.getLocation(), new ItemStack(Material.RAW_GOLD));
                    }
                }
                if (blockType.equals(Material.EMERALD_ORE) || blockType.equals(Material.DEEPSLATE_EMERALD_ORE)) {
                    if (random.nextFloat() <= extraDropChance) {
                        world.dropItemNaturally(block.getLocation(), new ItemStack(Material.EMERALD));
                    }
                }
                if (blockType.equals(Material.COPPER_ORE) || blockType.equals(Material.DEEPSLATE_COPPER_ORE)) {
                    if (random.nextFloat() <= extraDropChance) {
                        int extra = random.nextInt(4);
                        world.dropItemNaturally(block.getLocation(), new ItemStack(Material.RAW_COPPER, extra));
                    }
                }
                if (blockType.equals(Material.LAPIS_ORE) || blockType.equals(Material.DEEPSLATE_LAPIS_ORE)) {
                    if (random.nextFloat() <= extraDropChance) {
                        int extra = random.nextInt(4);
                        world.dropItemNaturally(block.getLocation(), new ItemStack(Material.LAPIS_LAZULI, extra));
                    }
                }
                if (blockType.equals(Material.REDSTONE_ORE) || blockType.equals(Material.DEEPSLATE_REDSTONE_ORE)) {
                    if (random.nextFloat() <= extraDropChance) {
                        int extra = random.nextInt(9);
                        world.dropItemNaturally(block.getLocation(), new ItemStack(Material.REDSTONE, extra));
                    }
                }
                if (blockType.equals(Material.COAL_ORE) || blockType.equals(Material.DEEPSLATE_COAL_ORE)) {
                    if (random.nextFloat() <= extraDropChance) {
                        world.dropItemNaturally(block.getLocation(), new ItemStack(Material.COAL));
                    }
                }
                if (blockType.equals(Material.NETHER_QUARTZ_ORE)) {
                    if (random.nextFloat() <= extraDropChance) {
                        world.dropItemNaturally(block.getLocation(), new ItemStack(Material.QUARTZ));
                    }
                }
                if (blockType.equals(Material.NETHER_GOLD_ORE)) {
                    if (random.nextFloat() <= extraDropChance) {
                        int extra = random.nextInt(7);
                        world.dropItemNaturally(block.getLocation(), new ItemStack(Material.GOLD_NUGGET, extra));
                    }
                }
            } else {
                e.setCancelled(true);
                ServerMessage.unicastError(player, ServerMessage.NOT_ALLOWED);
            }
        }
    }
    @EventHandler
    public void onPLayerHold(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();
        int slot = e.getNewSlot();
        ItemStack itemStack = player.getInventory().getItem(slot);
        if (itemStack != null) {
            if (itemStack.isSimilar(stone())) {
                AttributeInstance range = player.getAttribute(Attribute.PLAYER_BLOCK_INTERACTION_RANGE);
                if (range != null) {
                    range.setBaseValue(BLOCK_INTERACTION_RANGE_DEFAULT+1);
                }
            } else if (itemStack.isSimilar(iron())) {
                AttributeInstance range = player.getAttribute(Attribute.PLAYER_BLOCK_INTERACTION_RANGE);
                if (range != null) {
                    range.setBaseValue(BLOCK_INTERACTION_RANGE_DEFAULT+2);
                }
            } else if (itemStack.isSimilar(diamond())) {
                AttributeInstance range = player.getAttribute(Attribute.PLAYER_BLOCK_INTERACTION_RANGE);
                if (range != null) {
                    range.setBaseValue(BLOCK_INTERACTION_RANGE_DEFAULT+3);
                }
            } else {
                AttributeInstance range = player.getAttribute(Attribute.PLAYER_BLOCK_INTERACTION_RANGE);
                if (range != null) {
                    range.setBaseValue(BLOCK_INTERACTION_RANGE_DEFAULT);
                }
            }
        } else {
            AttributeInstance range = player.getAttribute(Attribute.PLAYER_BLOCK_INTERACTION_RANGE);
            if (range != null) {
                range.setBaseValue(BLOCK_INTERACTION_RANGE_DEFAULT);
            }
        }
    }
}
