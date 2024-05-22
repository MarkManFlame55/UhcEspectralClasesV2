package online.espectral.uhcespectralclases.item;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.FoodComponent;
import org.bukkit.potion.PotionEffect;

import java.util.List;
import java.util.Map;

public class PluginItems {
    public static ItemStack concentratedIron() {
        ItemStack itemStack = new ItemStack(Material.IRON_INGOT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Hierro Concentrado");
        itemMeta.setEnchantmentGlintOverride(true);

        FoodComponent foodComponent = itemMeta.getFood();
        foodComponent.setEatSeconds(2);
        foodComponent.setSaturation(3);
        foodComponent.setCanAlwaysEat(true);
        itemMeta.setFood(foodComponent);

        itemMeta.setMaxStackSize(16);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack cannonBullet() {
        ItemStack itemStack = new ItemStack(Material.GRAY_DYE, 2);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Cannon Bullet");
        itemMeta.setDisplayName(ChatColor.GRAY + "Bala de Cañon");
        itemMeta.setEnchantmentGlintOverride(true);
        itemMeta.setMaxStackSize(16);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack cannonBullet(int amount) {
        ItemStack itemStack = cannonBullet();
        itemStack.setAmount(amount);
        return itemStack;
    }
}
