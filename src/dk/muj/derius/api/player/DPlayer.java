package dk.muj.derius.api.player;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dk.muj.derius.api.ability.Ability;
import dk.muj.derius.api.lvl.LvlStatus;
import dk.muj.derius.api.skill.Skill;

public interface DPlayer
{
	// -------------------------------------------- //
	// FIELD: EXP
	// -------------------------------------------- //
	
	/**
	 * Gets players exp in said skill.
	 * @param {Skill} the skill
	 * @return {long} players exp in said skill
	 */
	public long getExp(Skill skill);
	
	/**
	 * Adds exp to user in said skill
	 * @param {Skill} the skill
	 * @param {long} the amount to add to players exp
	 */
	public void addExp(Skill skill, double exp);
	
	/**
	 * Takes users exp in said skill.
	 * @param {Skill} the skill
	 * @param {long} the amount of exp to take away.
	 */
	public void takeExp(Skill skill, double exp);
	
	// -------------------------------------------- //
	// FIELD: STAMINA
	// -------------------------------------------- //
	
	// RAW
	/**
	 * Gets players stamina.
	 * @return {int} players stamina
	 */
	public double getStamina();
	
	// FINER
	/**
	 * Adds stamina to user
	 * @param {int} the amount of stamina to add
	 */
	public void addStamina(double stamina);
	
	/**
	 * Removes stamina from user
	 * @param {int} the amount of stamina to take away
	 */
	public void takeStamina(double stamina);
	
	/**
	 * Has the player the amount of stamina
	 * @return {boolean} if he has enough stamina
	 */
	public default boolean hasEnoughStamina(double amount)
	{
		return this.getStamina() >= amount;
	}
	
	/**
	 * Gets the bonus max stamina amount bonus this player has gotten
	 * @return {Map<String, Integer>}
	 * the string is the reason, it could be "perm", "quest", "whatever"
	 * the double is the amount for that reason.
	 */
	public Map<String, Double> getStaminaBonus();
	
	public double getStaminaMax();
	
	// -------------------------------------------- //
	// FIELD: STAMINABOARD
	// -------------------------------------------- //
	
	/**
	 * Gets whether the scoreboard stays or not.
	 * @return {boolean} whether the scoreboard stays or not
	 */
	public boolean getStaminaBoardStay();
	
	/**
	 * Sets whether the scoreboard stays or not.
	 * @param {boolean} whether the scoreboard stays or not
	 */
	public void setStaminaBoardStay(boolean value);
	
	// -------------------------------------------- //
	// FIELD: BOARDSHOWATALL
	// -------------------------------------------- //
	
	/**
	 * Gets whether the scoreboard shows up at all.
	 * @return {boolean} whether the scoreboard shows or not
	 */
	public boolean getBoardShowAtAll();
	
	/**
	 * Sets whether the scoreboard shows up at all.
	 * @param {boolean} whether the scoreboard shows or not
	 */
	public void setBoardShowAtAll(boolean value);
	
	// -------------------------------------------- //
	// LEVEL
	// -------------------------------------------- //
	
	/**
	 * Gets a LvlStatus for said skill in this DPlayer
	 * @param {String} id of the skill
	 * @return {LvlStatus} The LvlStatus for said skill & this player
	 */
	public LvlStatus getLvlStatus(Skill skill);
	
	/**
	 * Gets level for said skill in this DPlayer
	 * @param {String} id of the skill
	 * @return {int} players level in said skill
	 */
	public int getLvl(Skill skill);
	
	/**
	 * The maximum level this player can reach in said skill
	 * @param {Skill} skill to check for
	 * @return {int} the level the player can reach
	 */
	public int getMaxLevel(Skill skill);
	
	// -------------------------------------------- //
	// FIELD: SPECIALISATION
	// -------------------------------------------- //
	
	/**
	 * Tells whether or not the player is specialised in this skill
	 * @param {Skill} the skill
	 * @return {boolean} true if the player is specialised in the skill
	 */
	public boolean isSpecialisedIn(Skill skill);
	
	/**
	 * Sets the player specialised in the skill.
	 * This will not succeed if the player is filled with specialisations already
	 * or the skill is on the spcialisationAutomatic or black list.
	 * @param {Skill} the skill
	 * @param {boolean} inform them if not
	 * @return {boolean} true if the player is explicitely specialised in the skill now.
	 * by explicitly we do not mean auto assigning.
	 */
	public boolean setSpecialisedIn(Skill skill, boolean verbooseNot);
	
	
	/**
	 * Sets the player to not be specialised in the skill.
	 * you must do all checks yourself.
	 * @param {Skill} the skill
	 */
	public void setNotSpecialisedIn(Skill skill);
	
	/**
	 * Gets a list of the skills this player has explicitely specialised in
	 * not the ones they are automatically specialised in.
	 * @return {Skill[]} the skills this player has specialised in
	 */
	public List<Skill> getSpecialisedSkills();
	
	// -------------------------------------------- //
	// SPECIALISATION: SLOTS
	// -------------------------------------------- //
	
	/**
	 * Gets the maximum amount of specialisation slots
	 * this player could have open at once, based on permissions.
	 * @return {int} maximum possible amount of open specialisation slots
	 */
	public int getMaxSpecialisationSlots();
	
	/**
	 * Gets the bonus specialisation slot bonus this player has gotten
	 * @return {Map<String, Integer>}
	 * the string is the reason, it could be "perm", "quest", "whatever"
	 * the integer is the amount for that reason.
	 */
	public Map<String, Integer> getSpecialisationSlotBonus();
	
	/**
	 * Gets the amount of open specialisation slots this player has
	 * @return {int}
	 */
	public int getOpenSpecialisationSlots();
	
	// -------------------------------------------- //
	// FIELD: ABILITY ACTIVATION COOLDOWN
	// -------------------------------------------- //
	
	/**
	 * This cooldown is for activating abilities.
	 * Sets users time when the global cooldown should expire.
	 * this is system millis
	 * @param {long} the cooldown to set it to
	 */
	public void setCooldownExpire(long cooldownTime);

	/**
	 * Sets the Cooldown to run out the passed amount of millis in the future
	 * @param {int} millis in the future the cooldown should be set to.
	 */
	public default void setCooldownExpireInMillis(int millis)
	{
		long currentTime = System.currentTimeMillis();
		setCooldownExpire(currentTime+millis);
	}
	
	/**
	 * Gets players cooldown.
	 * this is system millis
	 * @return {long} players global cooldown
	 */
	public long getCooldownExpire();
	
	/**
	 * Gets amount of milliseconds till cooldown expire
	 * @return {long} amount of milliseconds till cooldown expire
	 */
	public long getCooldownExpireIn();
	
	/**
	 * Checks whether the cooldown has expired or not
	 * @return {boolean} true if cooldown has expired
	 */
	public default boolean isCooldownExpired ()
	{
		return System.currentTimeMillis() >= getCooldownExpire();
	}
	
	// -------------------------------------------- //
	// FIELD: SPECIALISATION COOLDOWN
	// -------------------------------------------- //
	
	/**
	 * Gets the last time a player either specialised or unspecialised in a skill
	 * This is used so players don't change their specialisation all the time.
	 * @return {long} system millis for last specialisation change
	 */
	public long getSpecialisationChangeMillis();
	
	/**
	 * Sets the last time a player either specialised or unspecialised in a skill
	 * This is used so players don't change their specialisation all the time.
	 * @param {long} system millis for last specialisation change
	 */
	public void setSpecialisationChangeMillis(long millis);
	
	/**
	 * Gets when the specialisation cooldown will expire
	 * This is the last time they changed any specialisation + some time modfied by server owner
	 * This is used so players don't change their specialisation all the time.
	 * @return {long} system millis for last specialisation change + some cooldown
	 */
	public long getSpecialisationCooldownExpire();
	
	/**
	 * Checks whether the specialisation cooldown has expired
	 * @return {boolean} true if specialisation cooldown has expired
	 */
	public boolean isSpecialisationCooldownExpired();
	
	// -------------------------------------------- //
	// FIELD: PREPARED TOOL
	// -------------------------------------------- //
	
	/**
	 * Gets the tool which the user has prepared
	 * this is used for activating active abilities
	 * @return {Material} the tool the player has prepared
	 */
	public Optional<Material> getPreparedTool();

	/**
	 * Sets the tool which the user has prepared
	 * this is used for activating active abilities
	 * @param {Material} the tool the player will have prepared
	 */
	public void setPreparedTool(final Optional<Material> tool);
	
	// -------------------------------------------- //
	// ABILITIES
	// -------------------------------------------- //
	
	/**
	 * Tells whether or not the player has ANY abilities activated.
	 * @return {boolean} true if the player has ANY abilities activated.
	 */
	public boolean hasActivatedAny();
	
	/**
	 * Gets the id of the activated ability
	 * null if no ability is activated
	 * @return {Ability} the ability which is activated. null if none
	 */
	public Optional<Ability<?>> getActivatedAbility();
	
	/**
	 * Gets the id of the activated ability
	 * null if no ability is activated
	 * @return {Ability} the ability which is activated. null if none
	 */
	public void setActivatedAbility(Optional<Ability<?>> ability);
	
	// -------------------------------------------- //
	// SENDER/PLAYER METHODS
	// -------------------------------------------- //
	
	public Player getPlayer();
	public boolean isPlayer();
	public CommandSender getSender();
	public String getId();
	public String getName();
	public String getDisplayName(Object watcherObject);
	
	// -------------------------------------------- //
	// MESSAGE
	// -------------------------------------------- //
	
	// CONVENIENCE SEND MESSAGE
	public boolean sendMessage(String message);
	public boolean sendMessage(String... messages);
	public boolean sendMessage(Collection<String> messages);
	
	// CONVENIENCE MSG
	public boolean msg(String msg);
	public boolean msg(String msg, Object... args);
	public boolean msg(Collection<String> msgs);

}
