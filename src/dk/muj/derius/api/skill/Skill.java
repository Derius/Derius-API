package dk.muj.derius.api.skill;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.Registerable;
import com.massivecraft.massivecore.collections.WorldExceptionSet;
import com.massivecraft.massivecore.xlib.gson.JsonObject;

import dk.muj.derius.api.Req;
import dk.muj.derius.api.ability.Ability;
import dk.muj.derius.api.ability.Ability.AbilityType;
import dk.muj.derius.api.lvl.LvlStatus;
import dk.muj.derius.api.lvl.LvlStatusCalculator;
import dk.muj.derius.api.player.DPlayer;

public interface Skill extends Registerable
{
	
	// -------------------------------------------- //
	// ID
	// -------------------------------------------- //
	
	/**
	 * Gets the id of the skill. This id is only used by plugins
	 * & is never seen by the player/user.
	 * MUST be unique & should never be changed
	 * This should be lowercase.
	 * @return {String} the skills unique id.
	 */
	public String getId();
	
	// -------------------------------------------- //
	// REGISTER
	// -------------------------------------------- //
	
	/**
	 * Registers a skill to our system.
	 * This should be done on server startup.
	 */
	public void register();
	
	// -------------------------------------------- //
	// DATABASE
	// -------------------------------------------- //
	
	/**
	 * Used for database stuff. You should just extend DeriusSkill
	 * and not care about this method.
	 */
	public Skill load(Skill that);
	
	public Plugin getPlugin();
	
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
	
	/**
	 * Gets the field value of enabled.
	 * Even if this is true it might be disabled for other reasons.
	 * @return {boolaen} field value of enabled
	 */
	public boolean getEnabled();
	
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
	 * @param {Objet} object for a player
	 * @return {String} how the player should see the description
	 */
	public String getDisplayedDescription(DPlayer dplayer);
	
	/**
	 * Returns a colorcode name
	 * based on the players ability to learn stated skill or not.
	 * @param {Objet} object for a player
	 * @return {String} The colorcode for the txt.parse method.
	 */
	public String getDisplayName(DPlayer dplayer);
	
	// -------------------------------------------- //
	// FIELD: EARN EXP DESC
	// -------------------------------------------- //
	
	/**
	 * Gets the descriptions on how to earn exp in this skill.
	 * @return {List<String>} descriptions on how to earn exp in this skill.
	 */
	public List<String> getEarnExpDescs();
	
	/**
	 * Sets the list of descriptions on how to add earn exp
	 * @param {List<String>} the new list of descriptions on how to earn exp for this skill
	 */
	public void setEarnExpDescs(List<String> descs);
	
	/**
	 * Adds descriptions on how to earn exp in this skill
	 * @param {String...} some new descriptions on how to earn exp in this skill
	 */
	public void addEarnExpDescs(String... desc);
	
	// -------------------------------------------- //
	// FIELD: ICON
	// -------------------------------------------- //
	
	/**
	 * Gets the icon of the skill. This is showed in a chest GUI
	 * @return {Material} the skills
	 */
	public Material getIcon();
	
	/**
	 * Sets the icon for this skill shown in chest GUIs
	 * @param {Material} 
	 */
	public void setIcon(Material icon);
	
	// -------------------------------------------- //
	// FIELD: LEVELSTATUS CALCULATOR
	// -------------------------------------------- //
	
	/**
	 * Each skill can have a different way of calculating levels.
	 * We don't always know it.
	 * The level calculation algorithm can also be change later on.
	 * @param {long} the amount of exp to convert to level
	 * @return {LevelStatus} The levelstatus equivalent to the amount of exp passed.
	 */
	public LvlStatus getLvlStatusFromExp(long exp);
	
	/**
	 * Each skill can have a different way of calculating levels.
	 * We don't know it, but we store the exp.
	 * This will change the algorithm
	 * @param {LvlStatusCalculator} The new algorithm to calculate levels for this skill
	 */
	public void setLvlStatusAlgorithm(LvlStatusCalculator algorithm);
	
	/**
	 * Each skill can have a different way of calculating levels.
	 * We don't know it, but we store the exp.
	 * This will get the level calculation algorithm for this skill
	 * @param {LvlStatusCalculator} The new algorithm to calculate levels for this skill
	 */
	public LvlStatusCalculator getLvlStatusAlgorithm();
	
	// -------------------------------------------- //
	// FIELD: CAP
	// -------------------------------------------- //
	
	/**
	 * Gets the max level a player can reach in this skill without specialising.
	 * @return {int} the max level reachable without specialisation.
	 */
	public int getSoftCap();
	
	/**
	 * Sets the max level a player can reach in this skill without specialising.
	 * @param {int} the max level reachable without specialisation.
	 */
	public void setSoftCap(int cap);
	
	/**
	 * Gets the max level a player can reach in this skill  with specialising.
	 * @return {int} the max level reachable with specialisation.
	 */
	public int getHardCap();
	
	/**
	 * Sets the max level a player can reach in this skill with specialising.
	 * @param {int} the max level reachable with specialisation.
	 */
	public void setHardtCap(int cap);
	
	// -------------------------------------------- //
	// FIELD: WORLDEXCEPTION
	// -------------------------------------------- //
	
	/**
	 * Gets an exception set for the worlds in which
	 * it is possible to use this ability.
	 * @return {WorldExceptionSet} worlds where you can use this ability
	 */
	public WorldExceptionSet getWorldsEarn();
	
	/**
	 * Sets the exception set for which worlds
	 * it is possible to use this ability.
	 * @param {WorldExceptionSet} worlds where you can use this ability
	 */
	public void setWorldsEarn(WorldExceptionSet worldsEarn);
	
	// -------------------------------------------- //
	// SPECIALISATION
	// -------------------------------------------- //
	
	/**
	 * @return {boolean} true if a player is automatically specialised in this skill.
	 */
	public boolean isSpAutoAssigned();
	
	/**
	 * @param {boolean} true if players should be automatically specialised in this skill.
	 */
	public void setSpAutoAssiged(boolean assiged);
	
	/**
	 * @return {boolean} true if this skill can not be specialised.
	 */
	public boolean isSpBlackListed();
	
	/**
	 * @param {boolean} true if players should be automatically specialised in this skill.
	 */
	public void setSpBlackListed(boolean assiged);
	
	// -------------------------------------------- //
	// FIELD: CONFIGURATION
	// -------------------------------------------- //
	
	/**
	 * Get a custom JsonObject configuration.
	 * This can be used for the user to configure each skill or store data about the skill.
	 * Make sure you don't override someone elses data.
	 * @return {JsonObject} this skills configuration.
	 */
	public JsonObject getConfiguration();
	
	/**
	 * Set a custom JsonObject configuration.
	 * This can be used for the user to configure each skill or store data about the skill.
	 * Make sure you don't override someone elses data.
	 * @param {JsonObject} this skills configuration.
	 */
	public void setConfiguration(JsonObject conf);
	
	// -------------------------------------------- //
	// FIELD: SEE REQUIREMENTS
	// -------------------------------------------- //
	
	/**
	 * This will give the list of requirements
	 * that must be filled in order for a player to see the skill
	 * if they can't see the skill, they should not see it anywhere.
	 * @return {List<Req>} list of requirements to see the skill
	 */
	public List<Req> getSeeRequirements();
	
	/**
	 * This will set the list of requirements
	 * that must be filled in order for a player to see the skill
	 * if they can't see the skill, they should not see it anywhere.
	 * (old requirements will NOT be kept)
	 * @param {List<Req>} list of requirements to see the skill 
	 */
	public void setSeeRequirements(List<Req> requirements);
	
	/**
	 * This will add  to the list of requirements
	 * that must be filled in order for a player to see the skill
	 * if they can't see the skill, they should not see it anywhere.
	 * (old requirements WILL be kept)
	 * @param {List<Req>} added requirements to see the skill
	 */
	public void addSeeRequirements(Req... requirements);
	
	// -------------------------------------------- //
	// FIELD: LEARN REQUIREMENTS
	// -------------------------------------------- //
	
	/**
	 * This will give the list of requirements
	 * that must be filled in order for a player to learn the skill (earn exp)
	 * @return {List<Req>} list of requirements to learn the skill
	 */
	public List<Req> getLearnRequirements();
	
	/**
	 * This will set the list of requirements
	 * that must be filled in order for a player to learn the skill (earn exp)
	 * (old requirements will NOT be kept)
	 * @param {List<Req>} list of requirements to learn the skill 
	 */
	public void setLearnRequirements(List<Req> requirements);
	
	/**
	 * This will add  to the list of requirements
	 * that must be filled in order for a player to learn the skill (earn exp)
	 * (old requirements WILL be kept)
	 * @param {List<Req>} added requirements to learn the skill
	 */
	public void addLearnRequirements(Req... requirements);
	
	// -------------------------------------------- //
	// FIELD: SPECIALISE REQUIREMENTS
	// -------------------------------------------- //
	
	/**
	 * This will give the list of requirements
	 * that must be filled in order for a player to specialise in the skill
	 * @return {List<Req>} list of requirements to specialise the skill
	 */
	public List<Req> getSpecialiseRequirements();
	
	/**
	 * This will set the list of requirements
	 * that must be filled in order for a player to specialise in the skill
	 * (old requirements will NOT be kept)
	 * @param {List<Req>} list of requirements to specialise the skill 
	 */
	public void setSpecialiseRequirements(List<Req> requirements);
	
	/**
	 * This will add  to the list of requirements
	 * that must be filled in order for a player to specialise in the skill
	 * (old requirements WILL be kept)
	 * @param {List<Req>} added requirements to specialise the skill
	 */
	public void addSpecialiseRequirements(Req... requirements);
	
	// -------------------------------------------- //
	// FIELD: ABILITIES
	// -------------------------------------------- //
	
	/**
	 * Gets the list of abilities in this skill with a specific type.
	 * @return {List<Ability>} all active abilities related to this skill
	 */
	public default List<Ability> getAbilitiesOfType(AbilityType type)
	{
		return this.getAbilities().stream().filter(a -> a.getType() == type).collect(Collectors.toList());
	}
	
	/**
	 * Gets the list of all abilities related to skill
	 * @return {List<Ability>} all abilities related to this skill
	 */
	public abstract List<Ability> getAbilities();
	
}
