package dk.muj.derius.api.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/*
 * This SpecialItemManager adds an removes an enchant from the item.
 */
public abstract class SpecialItemManagerEnchant implements SpecialItemManager
{
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public ItemStack toSpecial(ItemStack item)
	{
		Validate.notNull(item, "item mustn't be null");
		if ( ! this.matches(item)) return item;
		if (this.isSpecial(item)) return item;
		
		// Meta
		ItemMeta meta = item.getItemMeta();
		
		// Set enchant
		int lvl = this.calcBuff(item);
		meta.addEnchant(this.getEnchantment(), lvl, true);
		
		// Set lore
		List<String> lore = this.getLore(meta);
		lore.add(this.getLoreTag());
		meta.setLore(lore);
		
		// Apply
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public ItemStack toNormal(ItemStack item)
	{
		Validate.notNull(item, "item mustn't be null");
		if ( ! this.isSpecial(item)) return item;
		
		int lvl = this.calcDebuff(item);
		
		ItemMeta meta = item.getItemMeta();
		
		List<String> lore = this.getLore(meta);
		lore.remove(this.getLoreTag());
		meta.setLore(lore);
		
		meta.removeEnchant(this.getEnchantment());
		if (lvl > 0) meta.addEnchant(this.getEnchantment(), lvl, true);
		
		item.setItemMeta(meta);
		return item;
	}
	
	@Override
	public boolean isSpecial(ItemStack item)
	{
		Validate.notNull(item, "item mustn't be null");
		if ( ! matches(item)) return false;
		
		ItemMeta meta = item.getItemMeta();
		List<String> lore = this.getLore(meta);
		
		return lore.contains(this.getLoreTag());
	}

	@Override
	public boolean matches(ItemStack item)
	{
		Validate.notNull(item, "item mustn't be null");
		return this.getToolTypes().contains(item.getType());
	}
	
	// -------------------------------------------- //
	// ABSTRACT
	// -------------------------------------------- //
	
	public abstract Collection<Material> getToolTypes();
	public abstract String getLoreTag();
	public abstract Enchantment getEnchantment();
	public abstract int getBuff();
	
	// -------------------------------------------- //
	// UTIL
	// -------------------------------------------- //

	private List<String> getLore(ItemMeta meta)
	{
		return meta.hasLore() ? meta.getLore() : new ArrayList<>(1);
	}
	
	private int calcBuff(ItemStack item)
	{
		int lvlBefore = item.getEnchantmentLevel(this.getEnchantment());
		if (lvlBefore < 0) lvlBefore = 0;
		int lvl = lvlBefore + this.getBuff();
		
		return lvl;
	}
	
	private int calcDebuff(ItemStack item)
	{
		int lvlBefore = item.getEnchantmentLevel(this.getEnchantment());
		int lvl = lvlBefore - this.getBuff();
		if (lvl < 0) lvl = 0;
		
		return lvl;
	}
	
}
