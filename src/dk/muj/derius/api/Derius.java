package dk.muj.derius.api;

import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.xlib.gson.Gson;

import dk.muj.derius.api.ability.Ability;
import dk.muj.derius.api.inventory.SpecialItemManager;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.skill.Skill;

public interface Derius
{
	// -------------------------------------------- //
	// DATABASE
	// -------------------------------------------- //
	
	/**
	 * Get gson for passed plugin
	 * @param  @nullable {Plugin}to get gson for.
	 * @return {Gson} gson for that plugin or for the core if plugin had no gson.
	 */
	public Gson getGson(Plugin plugin);
	
	// -------------------------------------------- //
	// DPLAYERS
	// -------------------------------------------- //
	
	/**
	 * Gets all players registered by Derius.
	 * @return {Collection<? extends DPlayer>} all DPlayers
	 */
	public abstract Collection< ? extends DPlayer> getAllDPlayers();
	
	/**
	 * Gets a DPlayer (can be offline)
	 * @param {Object} an object for the players
	 * can be one of the following
	 * 1. id (as string)
	 * 2. uuid
	 * 3. player/commandsender bukkit object
	 * @return {DPlayer} the corresponding dplayer
	 */
	public abstract DPlayer getDPlayer(Object senderObject);
	
	// -------------------------------------------- //
	// SKILLS
	// -------------------------------------------- //
	
	/**
	 * Gets all registered skills
	 * @return{Collection<? extends Skill>} all skills
	 */
	public abstract Collection< ? extends Skill> getAllSkills();
	
	/**
	 * Gets the skill corresponding to passed id-
	 * @param {String} id of the skill you want to get.
	 * @return {Skill} the skill
	 */
	public abstract Skill getSkill(String id);
	
	/**
	 * Registers a skill into the system.
	 * @param {Skill} skill to register.
	 */
	public abstract void registerSkill(Skill skill);
	
	// -------------------------------------------- //
	// ABILITIES
	// -------------------------------------------- //
	
	/**
	 * Gets all registered abilities
	 * @return{Collection<? extends Ability>} all abilities
	 */
	public abstract Collection< ? extends Ability> getAllAbilities();
	
	/**
	 * Gets the ability corresponding to passed id-
	 * @param {String} id of the ability you want to get.
	 * @return {Ability} the ability
	 */
	public abstract Ability getAbility(String id);
	
	/**
	 * Registers an ability into the system.
	 * @param {Ability} ability to register.
	 */
	public abstract void registerAbility(Ability ability);
	
	
	// -------------------------------------------- //
	// PREPARABLE TOOLS
	// -------------------------------------------- //
	
	
	/**
	 * Gets the tool which a player can prepare.
	 * When a player prepare a tool they get a message and
	 * skills can use it to only activate if the player has a tool prepared.
	 * @return {Collection<Material>} collection of tools that can be prepared.
	 */
	public abstract Collection<Material> getPreparableTools();
	/** @see Derius#getPreparableTools this is just a mutative operation on that collection.*/
	public abstract void registerPreparableTools(Collection<Material> materials);
	/** @see Derius#getPreparableTools this is just a mutative operation on that collection.*/
	public abstract void registerPreparableTool(Material material);
	/** @see Derius#getPreparableTools this is just doing a check on that collection.*/
	public abstract boolean isRegisteredAsPreparable(Material material);
	/** @see Derius#getPreparableTools this is just doing a check on that collection.*/
	public abstract void unregisterPreparableTool(Material material);
	
	// -------------------------------------------- //
	// SCHEDULED DEACTIVATE
	// -------------------------------------------- //
	
	/**
	 * Checks if a scheduled deactive is scheduled yet.
	 * @param {ScheduledDeactivate} scheduled deactivate to check for.
	 * @return {boolean} true is scheduled.
	 */
	public abstract boolean isScheduled(ScheduledDeactivate sd);
	
	/**
	 * Scheduled a scheduled deactivate
	 * @param {ScheduledDeactivate} scheduled deactivate to schedule.
	 */
	public abstract void schedule(ScheduledDeactivate sd);
	
	// -------------------------------------------- //
	// OTHER
	// -------------------------------------------- //
	
	/**
	 * Registers a special item manager, used to avoid cheating.
	 * @param {SpecialItemManager} manager to activate.
	 */
	public abstract void registerSpecialItemManager(SpecialItemManager manager);
	
	public abstract void debug(int level, String format, Object... args);
}
