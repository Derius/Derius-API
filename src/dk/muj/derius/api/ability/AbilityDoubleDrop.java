package dk.muj.derius.api.ability;

import java.util.Collection;
import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import dk.muj.derius.api.player.DPlayer;

public abstract class AbilityDoubleDrop extends AbilityAbstract
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public AbilityDoubleDrop()
	{
		this.setDesc("Gives double drop");
		this.setName("Double Drop");
		
		this.setType(AbilityType.PASSIVE);
		this.setCooldownMillis(-1);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public Optional<String> getLvlDescriptionMsg(int lvl)
	{
		double percent = Math.min(100.0, (double) lvl/this.getLevelsPerPercent());
		return Optional.of("<i>Chance to double drop: <h>" + String.valueOf(percent) + "%");
	}

	@Override
	public Object onActivate(DPlayer dplayer, Object obj)
	{
		Block block = (Block) obj;
		ItemStack inHand = dplayer.getPlayer().getItemInHand();
		Location blockLoc = block.getLocation();
		
		for(ItemStack item : block.getDrops(inHand))
		{
			block.getWorld().dropItem(blockLoc, item);
		}
		
		return obj;
	}

	@Override public void onDeactivate(DPlayer dplayer, Object other) { }

	// -------------------------------------------- //
	// ABSTRACT
	// -------------------------------------------- //
	
	public abstract Collection<Material> getBlockTypes();
	public abstract int getLevelsPerPercent();

}
