package dk.muj.derius.api.ability;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;

import org.bukkit.Material;

import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.util.LevelUtil;

/* 
 * This class is made for similar abilities that all,
 * extend the durability of an item depending on,
 * the users (player) level in a ceratin skill.
 * Furthermore these abilites will automatically
 * be handled/activated if they are activated.
 */
public abstract class AbilityDurabilityMultiplier extends AbilityAbstract<Object>
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	public AbilityDurabilityMultiplier()
	{
		this.setName("Carefull {skillname}".replace("{skillname}", this.getSkill().getName()));
		this.setDesc("Extends {toolname} durability".replace("{toolname}", this.getToolName()));

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
		if ( ! optMultiplier.isPresent()) return Optional.empty();
		double multiplier = optMultiplier.getAsDouble();
		return Optional.of(String.format("<i>{toolname} durability multiplied by <h>%.2f".replace("{toolname}", this.getToolName()), multiplier));
	}
	
	@Override
	public Object onActivate(DPlayer dplayer, Object other)
	{
		// All the logic is done in the core.
		// So the event is only thrown when actually activated.
		return other;
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
