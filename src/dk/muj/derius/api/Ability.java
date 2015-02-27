package dk.muj.derius.api;

import java.util.List;

import com.massivecraft.massivecore.Registerable;
import com.massivecraft.massivecore.collections.WorldExceptionSet;

public interface Ability extends Registerable
{
	// -------------------------------------------- //
	// REGISTER
	// -------------------------------------------- //
	
	/**
	 * Registers an ability to our system.
	 * This should be done on server startup.
	 */
	public void register();
	
	// -------------------------------------------- //
	// FIELD: ENABLED
	// -------------------------------------------- //
	
	/**
	 * Tells if this skill is enabled.
	 * If not players can't use it AT ALL.
	 * @return {boolean} true if skill is enabled
	 */
	public boolean isEnabled();
	
	/**
	 * Sets the enabled status of this skill.	 
	 * If not players can't use it AT ALL.
	 * @param {boolean} true if enabled
	 */
	public void setEnabled(boolean enabled);
	
	// -------------------------------------------- //
	// FIELD: NAME
	// -------------------------------------------- //
	
	/**
	 * Gets the name shown to players.
	 * @return {String} the name shown to players.
	 */
	public String getName();
	
	/**
	 * Sets the name shown to players.
	 * @param {String} this skills new name.
	 */
	public void setName(String newName);
	
	// -------------------------------------------- //
	// FIELD: DESCRIPTION
	// -------------------------------------------- //
	
	/**
	 * Gets a human friendly description of this skill.
	 * @return {String} this skills description.
	 */
	public String getDesc();
	
	/**
	 * Sets a new human friendly description for this skill.
	 * @param {String} this skills new description.
	 */
	public void setDesc(String newDescription);
	
	// -------------------------------------------- //
	// DISPLAY
	// -------------------------------------------- //
	
	/**
	 * Gets the name & description, as it would be displayed
	 * to the passed player
	 * @param {Object} player (id/uuid/mplayer/bukkit-player) to see description
	 * @return {String} how the player should see the description
	 */
	public String getDisplayedDescription(Object watcherObject);
	
	/**
	 * Gets the name  as it would be displayed to the passed player
	 * @param {Object} player (id/uuid/mplayer/bukkit-player) to see description
	 * @return {String} how the player should see the description
	 */
	public String getDisplayName(Object watcherObject);
	
	// -------------------------------------------- //
	// FIELD: DURATION
	// -------------------------------------------- //

	/**
	 * Gets how many ticks this ability will last
	 * @param {int} the level to check for
	 * @return {int} amount of ticks, this ability would last.
	 * @deprecated we use millis now
	 */
	public default int getDuration(int level)
	{
		return this.getDurationMillis(level)/50;
	}

	/**
	 * Gets how many millis this ability will last
	 * @param {int} the level to check for
	 * @return {int} amount of millis, this ability would last.
	 */
	public int getDurationMillis(int level);
	
	/**
	 * Each ability can have a different way to calculate the cooldown time.
	 * We don't know it, but we store the level, which this is depending on.
	 * This will change the algorithm for this ability.
	 * @param algorithm
	 */
	public void setDurationAlgorithm(MillisLastCalculator algorithm);
	
	/**
	 * Each ability can have a different way to calculate the cooldown time.
	 * We don't know it, but we store the level, which this is depending on.
	 * This will get the cooldown calculation algorithm for this ability.
	 * @return {TicksLastCalculator} The algorithm which is being used for this ability.
	 */
	public MillisLastCalculator getDurationAlgorithm();
	
	// -------------------------------------------- //
	// FIELD: COOLDOWN
	// -------------------------------------------- //
	
	/**
	 * Sets how many ticks the cooldown will last.
	 * @param {int} The ticks it will last
	 * @deprecated we use millis now
	 */
	public default void setTicksCooldown(int ticks)
	{
		this.setMillisCooldown(ticks*50);
	}
	
	/**
	 * Gets how many ticks the cooldown will last
	 * @return {int} amount of ticks, the cooldown will be.
	 * @deprecated we use millis now
	 */
	public default int getCooldownTicks()
	{
		return this.getCooldownMillis()/50;
	}
	
	/**
	 * Sets how many millis the cooldown will last.
	 * @param {int} The millis it will last
	 */
	public void setMillisCooldown(int millis);
	/**
	 * Gets how many millis the cooldown will last
	 * @return {int} amount of millis, the cooldown will be.
	 */
	public int getCooldownMillis();
	
	// -------------------------------------------- //
	// FIELD: STAMINA
	// -------------------------------------------- //
	
	/**
	 * Sets the amount of stamina used upon activation.
	 * @param {double} the amount which is used
	 */
	public void setStaminaUsage(double stamina);
	
	/**
	 * Gets the amount of stamina used upon activation.
	 * @return {double} the amount of stamina
	 */
	public double getStaminaUsage();
	
	// -------------------------------------------- //
	// FIELD TYPE:
	// -------------------------------------------- //
	
	public enum AbilityType
	{
		/**
		 * Active skills last over a duration of time
		 */
		ACTIVE(),
		/**
		 * Passive abilities are activated once & don't last over time
		 */
		PASSIVE();
	}
	
	/**
	 * Gets the ability type (passive/active) of this ability
	 * @return {AbilityType} the type of this ability
	 */
	public AbilityType getType();
	
	/**
	 * Sets the ability type (passive/active) of this ability
	 * @param {AbilityType} the new type of this ability
	 */
	public void setType(AbilityType newType);
	
	// -------------------------------------------- //
	// FIELD: WORLDEXCEPTION
	// -------------------------------------------- //
	
	/**
	 * Gets an exception set for the worlds in which
	 * it is possible to use this ability.
	 * @return {WorldExceptionSet} worlds where you can use this ability.
	 */
	public WorldExceptionSet getWorldsUse();
	
	/**
	 * Sets the exception set for which worlds
	 * it is possible to use this ability.
	 * @param {WorldExceptionSet} worlds where you can use this ability.
	 */
	public void setWorldsUse(WorldExceptionSet worldsUse);
	
	// -------------------------------------------- //
	// RESTRICTION
	// -------------------------------------------- //
	
	/**
	 * This will return the list of requirements
	 * that must be met in order for a player to see the ability
	 * if they can't see the skill, they should not see it anywhere.
	 * @return {List<Req>} list of requirements to see the ability
	 */
	public List<Req> getSeeRequirements();
	
	/**
	 * This will set the list of requirements
	 * that must be met in order for a player to see the ability
	 * if they can't see the skill, they should not see it anywhere.
	 * @param {List<Req>} list of requirements to see the ability 
	 */
	public void setSeeRequirements(List<Req> requirements);
	
	/**
	 * This will add to the list of requirements
	 * that must be met in order for a player to see the ability
	 * if they can't see the skill, they should not see it anywhere.
	 * @param {Req...} added requirements to see the ability
	 */
	public void addSeeRequirements(Req... requirements);
	
	/**
	 * This will give the list of requirements
	 * that must be met in order for a player to activate the ability
	 * @return {List<Req>} list of requirements to activate the ability
	 */
	public List<Req> getActivateRequirements();
	
	/**
	 * This will set the list of requirements
	 * that must be met in order for a player to activate the ability
	 * @param {List<Req>} list of requirements to activate the ability
	 */
	public void setActivateRequirements(List<Req> requirements);
	
	/**
	 * This will add  to the list of requirements
	 * that must be met in order for a player to activate the ability
	 * @param {List<Req>} added requirements to activate the ability
	 */
	public void addActivateRequirements(Req... requirements);
	
	// -------------------------------------------- //
	// ABSTRACT
	// -------------------------------------------- //
	
	/**
	 * Gets the skill associated with this ability
	 * @return {Skill} the skill associated with this ability
	 */
	public Skill getSkill();
	
	/**
	 * Gets the id of the ability. This id is only used by plugins
	 * & is never seen by the player/user.
	 * MUST be unique & should never be changed
	 * @return {String} the abilities unique id.
	 */
	public String getId();
	
	/**
	 * Gets a description based on passed level
	 * example "Double drop. Chance for double drop is 10%"
	 * if someone with that level had 10% chance to double drop.
	 * @param {int} the level you want to test for
	 * @return {String} the actual string message
	 */
	public String getLvlDescriptionMsg(int lvl);
	
	// Ability Execution methods
	/**
	 * This is the method called by Derius to run your ability. 
	 * It is similar to bukkits onEnable method.
	 * @param {DPlayer} the player to use the ability
	 * @param {Object} other parameter used in some abilities
	 * @return {Object} this object will be passed to onDeactivate for data transferring.
	 */
	public Object onActivate(DPlayer dplayer, Object other);
	
	/**
	 * This is the method called by Derius when your ability
	 * is deactivated. It is similar to bukkits onDisable method.
	 * @param {DPlayer} the player to stop using the ability
	 * @param {Object} object received from onActivate
	 */
	public void onDeactivate(DPlayer dplayer, Object other);
}
