package dk.muj.derius.api.ability;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;

import org.bukkit.Material;

import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.util.AbilityUtil;
import dk.muj.derius.api.util.LevelUtil;

/* 
 * This class is made for similar abilities that all,
 * extend the durability of an item depending on,
 * the users (player) level in a ceratin skill.
 * Furthermore these abilites will automatically
 * be handled/activated if they are activated.
 */
public abstract class AbilityDurabilityMultiplier extends AbilityAbstract
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	public AbilityDurabilityMultiplier()
	{
		this.setDesc("Extends {toolname} durability".replaceAll("{toolname}", this.getToolName()));

		this.setType(AbilityType.PASSIVE);
		this.setCooldownMillis(-1);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public Optional<String> getLvlDescriptionMsg(int lvl)
	{
		OptionalDouble optMultiplier = LevelUtil.getLevelSettingFloat(this.getDurabilityMultiplier(), lvl);
		if ( ! optMultiplier.isPresent()) return Optional.of("<i>No change");
		double multiplier = optMultiplier.getAsDouble();
		return Optional.of(String.format("<i>{toolname} durability multiplied by <h>%.2f".replaceAll("{toolname}", this.getToolName()), multiplier));
	}
	
	@Override
	public Object onActivate(DPlayer dplayer, Object other)
	{
		if ( ! (other instanceof Number))return AbilityUtil.CANCEL;
		double multiplier = ((Number) other).doubleValue();
		
		
		// EXAMPLES: 1 / 3 = 0.333 so if multiplier is 3 in every third case damage occurs
		// EXAMPLES: 1 / 0.333 = 3 so if multiplier is lower than 1. Damage will be multiplied.
		int damage = (int) MUtil.probabilityRound(1D / multiplier);
		
		return damage;
	}
	
	@Override
	public void onDeactivate(DPlayer p, Object other) { }

	// -------------------------------------------- //
	// ABSTRACT
	// -------------------------------------------- //
	
	public abstract Collection<Material> getToolTypes();
	public abstract Map<Integer, Double> getDurabilityMultiplier();
	public abstract String getToolName();
	
}
