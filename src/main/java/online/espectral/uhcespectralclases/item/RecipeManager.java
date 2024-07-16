package online.espectral.uhcespectralclases.item;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.Plugin;

import javax.naming.Name;

public class RecipeManager {

    private static Plugin plugin = UhcEspectralClases.getPlugin(UhcEspectralClases.class);
    static NamespacedKey concentrated_iron = new NamespacedKey(plugin, "concentrated_iron");
    static NamespacedKey cannon_bullet = new NamespacedKey(plugin, "cannon_bullet");
    static NamespacedKey iron_wither_sword = new NamespacedKey(plugin, "iron_wither_sword");
    static NamespacedKey diamond_wither_sword = new NamespacedKey(plugin, "diamond_wither_sword");
    static NamespacedKey iron_miner_pickaxe = new NamespacedKey(plugin, "iron_miner_pickaxe");
    static NamespacedKey diamond_miner_pickaxe = new NamespacedKey(plugin, "diamond_miner_pickaxe");

    public static void init() {
        craftConcentratedIron();
        craftCannonBullet();
        craftWitherIronTier();
        craftWitherDiamondTier();
        craftMinerIronTier();
        craftMinerDiamondTier();
    }
    public static void unload() {
        Bukkit.removeRecipe(concentrated_iron);
        Bukkit.removeRecipe(cannon_bullet);
        Bukkit.removeRecipe(iron_wither_sword);
        Bukkit.removeRecipe(diamond_wither_sword);
        Bukkit.removeRecipe(iron_miner_pickaxe);
        Bukkit.removeRecipe(diamond_miner_pickaxe);
    }
    private static void craftConcentratedIron() {
        ShapedRecipe recipe = new ShapedRecipe(concentrated_iron, PluginItems.concentratedIron());
        recipe.shape(" G ","GIG"," G ");
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setCategory(CraftingBookCategory.MISC);

        Bukkit.addRecipe(recipe);

    }
    private static void craftCannonBullet() {
        ShapelessRecipe recipe = new ShapelessRecipe(cannon_bullet, PluginItems.cannonBullet());
        recipe.addIngredient(Material.GUNPOWDER);
        recipe.addIngredient(Material.REDSTONE);
        recipe.addIngredient(Material.SAND);
        recipe.setCategory(CraftingBookCategory.MISC);

        Bukkit.addRecipe(recipe);
    }
    private static void craftWitherIronTier() {
        ShapedRecipe recipe = new ShapedRecipe(iron_wither_sword, WitherSwordItem.iron());
        recipe.shape("CDC","CBC","CSC");
        recipe.setIngredient('C', Material.COAL);
        recipe.setIngredient('D', Material.IRON_INGOT);
        recipe.setIngredient('B', Material.IRON_BLOCK);
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(WitherSwordItem.stone()));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);

        Bukkit.addRecipe(recipe);
    }
    private static void craftWitherDiamondTier() {

        ShapedRecipe recipe = new ShapedRecipe(diamond_wither_sword, WitherSwordItem.diamond());
        recipe.shape("CDC","CBC","CSC");
        recipe.setIngredient('C', Material.COAL);
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('B', Material.DIAMOND_BLOCK);
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(WitherSwordItem.iron()));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);

        Bukkit.addRecipe(recipe);
    }
    private static void craftMinerIronTier() {
        ShapedRecipe recipe = new ShapedRecipe(iron_miner_pickaxe, MinerPickaxeItem.iron());
        recipe.shape("IBI"," C "," P ");
        recipe.setIngredient('I', new RecipeChoice.MaterialChoice(Material.IRON_INGOT));
        recipe.setIngredient('B', new RecipeChoice.MaterialChoice(Material.IRON_BLOCK));
        recipe.setIngredient('P', new RecipeChoice.ExactChoice(MinerPickaxeItem.stone()));
        recipe.setIngredient('C', new RecipeChoice.MaterialChoice(Material.COPPER_INGOT));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);

        Bukkit.addRecipe(recipe);
    }
    private static void craftMinerDiamondTier() {
        ShapedRecipe recipe = new ShapedRecipe(diamond_miner_pickaxe, MinerPickaxeItem.diamond());
        recipe.shape("DBD"," C "," P ");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('B', Material.DIAMOND_BLOCK);
        recipe.setIngredient('C', Material.COPPER_INGOT);
        recipe.setIngredient('P', new RecipeChoice.ExactChoice(MinerPickaxeItem.iron()));
        recipe.setCategory(CraftingBookCategory.EQUIPMENT);


        Bukkit.addRecipe(recipe);
    }
}
