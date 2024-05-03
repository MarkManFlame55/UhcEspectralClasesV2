package online.espectral.uhcespectralclases.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class RecipeManager {
    public static void init() {
        craftConcentratedIron();
    }
    private static void craftConcentratedIron() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("concentrated_iron"), PluginItems.concentratedIron());
        recipe.shape(" G ","GIG"," G ");
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('I', Material.IRON_BLOCK);

        Bukkit.addRecipe(recipe);
    }
}
