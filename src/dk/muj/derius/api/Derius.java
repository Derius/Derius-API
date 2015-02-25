package dk.muj.derius.api;

import java.util.Collection;

public interface Derius
{
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
	
}
