package dk.muj.derius.api;

import java.util.Collection;
import java.util.Map;

import org.bukkit.Material;

import dk.muj.derius.api.skill.Skill;

/**
 * If you register this exp will automatically be given,
 * when a player breaks certain block with certain tools.
 */
public interface BlockBreakExpGain
{
	/**
	 * Returns a map consisting of block types as keys
	 * and a value telling how much exp should be granted when breaking such block.
	 * The exp is only granted if the tooltype is present in
	 * the tool types this exp gainer provides.
	 */
	public abstract Map<Material, Integer> getBlockTypes();
	
	/**
	 * If a player is holding a tool of this type
	 * they can be granted experience.
	 * If they don't they cannot.
	 * @return tools
	 * The tool types which the player
	 * can hold in order to be granted experience.
	 */
	public abstract Collection<Material> getToolTypes();
	
	/**
	 * Gets the skill which exp should be granted in
	 * when this exp gainer grants exp.
	 * @return skill
	 * The skill which exp should be granted in.
	 */
	public abstract Skill getSkill();
	
	/**
	 * Registers this exp gain
	 */
	public default void register()
	{
		DeriusAPI.registerExpGain(this);
	}
}
