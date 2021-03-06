package dk.muj.derius.api.mixin;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import com.massivecraft.massivecore.ps.PS;

public interface BlockMixin
{
	// Placed by player
	
	// Abstract
	public abstract boolean isBlockPlacedByPlayer(PS ps);
	
	// Default
	public default boolean isBlockPlacedByPlayer(Block block)
	{
		return this.isBlockPlacedByPlayer(PS.valueOf(block));
	}
	
	public default boolean isBlockPlacedByPlayer(Location loc)
	{
		return this.isBlockPlacedByPlayer(PS.valueOf(loc));
	}
	
	
	// Listening
	public abstract Set<Material> getBlocksTypesToListenFor();
	public abstract void setBlocksTypesToListenFor(Collection<Material> blocks);
	public abstract void addBlockTypesToListenFor(Collection<Material> blocks);
	
	public default void addBlockTypesToListenFor(Material... blocks)
	{
		this.addBlockTypesToListenFor(Arrays.asList(blocks));
	}
	
	public abstract boolean isListenedFor(Material material);
	
}
