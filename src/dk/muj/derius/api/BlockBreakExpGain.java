package dk.muj.derius.api;

import java.util.Collection;
import java.util.Map;

import org.bukkit.Material;

/**
 * If you register this exp will automatically be given,
 * when aplayer breaks certain block with certain tools.
 */
public interface BlockBreakExpGain
{
	public abstract Map<Material, Integer> getBlockTypes();
	public abstract Collection<Material> getToolTypes();
}
