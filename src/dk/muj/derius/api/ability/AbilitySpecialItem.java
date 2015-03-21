package dk.muj.derius.api.ability;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivecore.util.TimeDiffUtil;
import com.massivecraft.massivecore.util.TimeUnit;
import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.inventory.SpecialItemManager;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.req.ReqCooldownIsExpired;
/*
 * This class is for abilities that make an item special,
 * while they are activated.
 * To make it even better we offer automatic activation of these abilities.
 * Automatic activation can however be disabled by returning,
 * empty collections in the abstract methods.
 */
public abstract class AbilitySpecialItem extends AbilityAbstract<Object>
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	public AbilitySpecialItem()
	{
		this.setType(AbilityType.ACTIVE);
		this.addActivateRequirements(ReqCooldownIsExpired.get());
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public Optional<String> getLvlDescriptionMsg(int lvl)
	{
		// How long does the ability last?
		int millis = this.getDurationMillis(lvl);
		
		// Get the time unit count thingy.
		LinkedHashMap<TimeUnit, Long> unitcounts = TimeDiffUtil.limit(TimeDiffUtil.unitcounts(millis, TimeUnit.getAllButMillis()), 3);
		
		// We have a custom entry to allow spaces.
		String entry = Txt.parse("<v>%1$d <k>%3$s");
		String comma = TimeDiffUtil.FORMAT_COMMA_VERBOOSE;
		String and = TimeDiffUtil.FORMAT_AND_VERBOOSE;
		String durationDesc = TimeDiffUtil.formated(unitcounts, entry, comma, and, "<yellow>");
		
		// Example: "Lasts 16 seconds"
		return Optional.of("<i>Lasts " + durationDesc);
	}

	@Override
	public Object onActivate(DPlayer dplayer, Object other)
	{
		// Must be player.
		Player player = dplayer.getPlayer();
		ItemStack inHand = player.getItemInHand();
		
		this.getSpecialItemManager().toSpecial(inHand);
		
		player.setItemInHand(inHand);
		
		player.updateInventory();
		return player.getItemInHand();
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other)
	{
		if ( ! dplayer.isPlayer()) return;
		
		this.getSpecialItemManager().clearInventory(dplayer.getPlayer().getInventory());
		
		dplayer.getPlayer().updateInventory();
		
		return;
	}

	// -------------------------------------------- //
	// ABSTRACT
	// -------------------------------------------- //
	
	/**
	 *  The special item manager to make the items special.
	 */
	public abstract SpecialItemManager getSpecialItemManager();
	
	/**
	 * The tool types that a player can have prepared to activate this ability.
	 */
	public abstract Collection<Material> getToolTypes();
	
	/**
	 * The block types a player can break to activate this ability.
	 */
	public abstract Collection<Material> getBlockTypes();
	
}
