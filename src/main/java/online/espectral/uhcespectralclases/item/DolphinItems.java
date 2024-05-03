package online.espectral.uhcespectralclases.item;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

import java.util.UUID;

public class DolphinItems {
    public static ItemStack helmet() {
        ItemStack itemStack = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        assert leatherArmorMeta != null;
        leatherArmorMeta.setItemName("Dolphin Helmet");
        leatherArmorMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.AQUA + "Casco de Delfin");
        leatherArmorMeta.setColor(Color.fromRGB(53,241,237));
        leatherArmorMeta.addEnchant(Enchantment.AQUA_AFFINITY, 1, false);
        leatherArmorMeta.addEnchant(Enchantment.PROTECTION, 2, false);

        AttributeModifier armor = new AttributeModifier(UUID.randomUUID(),"generic.armor", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        leatherArmorMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armor);

        leatherArmorMeta.addItemFlags(ItemFlag.HIDE_DYE, ItemFlag.HIDE_ARMOR_TRIM);
        leatherArmorMeta.setUnbreakable(true);
        ArmorMeta armorMeta = (ArmorMeta) leatherArmorMeta;
        armorMeta.setTrim(new ArmorTrim(TrimMaterial.DIAMOND, TrimPattern.FLOW));
        itemStack.setItemMeta(armorMeta);
        return itemStack;
    }
    public static ItemStack trident() {
        ItemStack itemStack = new ItemStack(Material.TRIDENT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Dolphin Trident");
        itemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.AQUA + "Tridente de Delfin");
        itemMeta.setUnbreakable(true);
        itemMeta.addEnchant(Enchantment.LOYALTY, 3, false);
        itemMeta.addEnchant(Enchantment.IMPALING, 3, false);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
