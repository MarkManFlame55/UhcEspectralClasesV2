package online.espectral.uhcespectralclases.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

public class RecipeManager {
    public static void init() {
        craftConcentratedIron();
        craftCannonBullet();
    }
    private static void craftConcentratedIron() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("concentrated_iron"), PluginItems.concentratedIron());
        recipe.shape(" G ","GIG"," G ");
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('I', Material.IRON_BLOCK);

        Bukkit.addRecipe(recipe);
    }
    private static void craftCannonBullet() {
        ShapelessRecipe recipe = new ShapelessRecipe(NamespacedKey.minecraft("cannon_bullet"), PluginItems.cannonBullet());
        recipe.addIngredient(Material.GUNPOWDER);
        recipe.addIngredient(Material.REDSTONE);
        recipe.addIngredient(Material.SAND);

        Bukkit.addRecipe(recipe);
    }
}
