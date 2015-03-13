package dk.muj.derius.api.util;

import java.util.Optional;

import org.apache.commons.lang.Validate;

import com.massivecraft.massivecore.util.IdUtil;

import dk.muj.derius.api.DeriusAPI;
import dk.muj.derius.api.Req;
import dk.muj.derius.api.ScheduledDeactivate;
import dk.muj.derius.api.VerboseLevel;
import dk.muj.derius.api.ability.Ability;
import dk.muj.derius.api.ability.Ability.AbilityType;
import dk.muj.derius.api.events.AbilityActivatePostEvent;
import dk.muj.derius.api.events.AbilityActivatePreEvent;
import dk.muj.derius.api.events.AbilityDeactivateEvent;
import dk.muj.derius.api.player.DPlayer;

public final class AbilityUtil
{
	// -------------------------------------------- //
	// CONSTRUCTOR (FORBIDDEN)
	// -------------------------------------------- //
	
	private AbilityUtil()
	{
		
	}
	
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	/**
	 * The singletone object that should be returned when activation of an ability fails.
	 */
	public static final Object CANCEL = new Object();
	
	// -------------------------------------------- //
	// REQUIREMENTS
	// -------------------------------------------- //
	
	/**
	 * Tells whether or not the player can use said ability.
	 * This is based on the ability requirements
	 * @param {DPlayer} the player you want to check
	 * @param {Ability} ability to check for
	 * @param {VerboseLevel} verboselevel for error message @see dk.muj.derius.api.VerboseLevel
	 * @return {boolean} true if the player can use said ability
	 */
	public static boolean canPlayerActivateAbility(DPlayer dplayer, Ability ability, VerboseLevel verboseLevel)
	{
		Validate.notNull(dplayer, "dplayer mustn't be null");
		Validate.notNull(ability, "ability mustn't be null");
		Validate.notNull(verboseLevel, "verboselevel mustn't be null");
		
		for (Req req : ability.getActivateRequirements())
		{
			if (req.apply(dplayer, ability)) continue;
			
			// Debug
			DeriusAPI.debug(10000, "<i>The player <h>%s <i>can't activate the ability <h>%s <i>because of the req <h>%s",
					dplayer.getDisplayName(IdUtil.getConsole()), ability.getName(), req.getClass().getSimpleName());
			
			if (verboseLevel.includes(req.getVerboseLevel())) dplayer.sendMessage(req.createErrorMessage(dplayer, ability));
			return false;
		}
		return true;
	}
	
	/**
	 * Tells whether or not the player can see said ability.
	 * This is based on the skill requirements
	 * @param {DPlayer} the player you want to check
	 * @param {Ability} ability to check for
	 * @param {VerboseLevel} verboselevel for error message @see dk.muj.derius.api.VerboseLevel
	 * @return {boolean} true if the player can see said ability
	 */
	public static boolean canPlayerSeeAbility(DPlayer dplayer, Ability ability, VerboseLevel verboseLevel)
	{
		Validate.notNull(dplayer, "dplayer mustn't be null");
		Validate.notNull(ability, "ability mustn't be null");
		Validate.notNull(verboseLevel, "verboselevel mustn't be null");
		
		for (Req req : ability.getSeeRequirements())
		{
			if (req.apply(dplayer, ability)) continue;
			
			// Debug
			DeriusAPI.debug(10000, "<i>The player <h>%s <i>can't see the ability <h>%s <i>because of the req <h>%s",
					dplayer.getDisplayName(IdUtil.getConsole()), ability.getName(), req.getClass().getSimpleName());
			
			if (verboseLevel.includes(req.getVerboseLevel())) dplayer.sendMessage(req.createErrorMessage(dplayer, ability));
			return false;
		}
		return true;
	}
	
	// -------------------------------------------- //
	// ACTIVATION: PUBLIC
	// -------------------------------------------- //
	
	/**
	 * Activates an ability
	 * This is the proper way to activate an ability
	 * @param {DPlayer} player to activate ability on
	 * @param {Ability} the ability to activate
	 * @param {Object} some abilities need another object. Check for the individual ability
	 * @param {boolean} inform the player if not allowed
	 * @param {VerboseLevel} the verboselevel for checking if player can activate this skill.
	 */
	public static Object activateAbility(DPlayer dplayer, final Ability ability, Object other, VerboseLevel verboseLevel)
	{	
		Validate.notNull(dplayer, "dplayer mustn't be null");
		Validate.notNull(ability, "ability mustn't be null");
		Validate.notNull(verboseLevel, "verboselevel mustn't be null");
		
		// Debug
		DeriusAPI.debug(9900, "<i>A request of activating the ability <h>%s <i>for the player <h>%s <i>was sent.",
				ability.getName(), dplayer.getDisplayName(IdUtil.getConsole()));
		
		// CHECKS
		if ( ! AbilityUtil.canPlayerActivateAbility(dplayer, ability, verboseLevel)) return CANCEL;
		
		// STAMINA
		dplayer.takeStamina(ability.getStaminaUsage());
		
		// EVENT
		AbilityActivatePreEvent preEvent = new AbilityActivatePreEvent(ability, dplayer);
		if ( ! preEvent.runEvent()) return CANCEL;
		
		// ACTIVATE
		if (ability.getType() == AbilityType.PASSIVE)
		{
			other = activatePassiveAbility(dplayer, ability, other);
		}
		else if (ability.getType() == AbilityType.ACTIVE)
		{
			other = activateActiveAbility(dplayer, ability, other);
		}
		else
		{
			// This might be cruel but will actually help squash bugs faster.
			throw new RuntimeException("Passed abiliy does not have a valid ability type");
		}
		
		AbilityActivatePostEvent postEvent = new AbilityActivatePostEvent(ability, dplayer, other);
		other = postEvent.getOther();
		
		return other;
	}
	
	/**
	 * Deactivates an ability for dplayer player.
	 * This should however automatically be done by our scheduled tasks.
	 * @param {DPlayer} player to deactivate ability on
	 * @param {Ability} the ability to deactivate
	 */
	public static void deactivateActiveAbility(DPlayer dplayer, Object other)
	{
		Validate.notNull(dplayer, "dplayer mustn't be null");
		
		Optional<Ability> optActivated = dplayer.getActivatedAbility();
		if ( ! optActivated.isPresent()) throw new IllegalStateException("The player does not currently have a enabled ability");
		Ability ability = optActivated.get();
		
		AbilityDeactivateEvent deactivateEvent = new AbilityDeactivateEvent(ability, dplayer);
		if ( ! deactivateEvent.runEvent()) return;
		
		ability.onDeactivate(dplayer, other);
		dplayer.setActivatedAbility(Optional.empty());
		
		if(ability.hasCooldown())
		{
			dplayer.setCooldownExpireInMillis(ability.getCooldownMillis());
		}
	}
	
	// -------------------------------------------- //
	// ACTIVATION: PRIVATE
	// -------------------------------------------- //
	
	private static Object activatePassiveAbility(DPlayer dplayer, final Ability ability, Object other)
	{
		Validate.isTrue(ability.getType() == AbilityType.PASSIVE, "abilitytype must be passive");
	
		final Object obj = ability.onActivate(dplayer, other);
		if (obj == CANCEL) return CANCEL;
		ability.onDeactivate(dplayer, other);
		
		if(ability.hasCooldown())
		{
			dplayer.setCooldownExpireInMillis(ability.getCooldownMillis());
		}
		
		return obj;
	}
	
	private static Object activateActiveAbility(final DPlayer dplayer, final Ability ability, Object other)
	{
		Validate.isTrue(ability.getType() == AbilityType.ACTIVE, "abilitytype must be active");
		if (dplayer.hasActivatedAny()) return CANCEL;

		dplayer.setActivatedAbility(Optional.of(ability));

		final Object obj = ability.onActivate(dplayer, other);
		if (obj == CANCEL) return CANCEL;
		
		int duration = ability.getDurationMillis(dplayer.getLvl(ability.getSkill()));
		ScheduledDeactivate sd = new ScheduledDeactivate(dplayer, duration, other);
		sd.schedule();
		
		return obj;
	}
	
}
