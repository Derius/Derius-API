package dk.muj.derius.api;

import java.util.Collection;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivecore.xlib.gson.Gson;

import dk.muj.derius.api.ability.Ability;
import dk.muj.derius.api.config.DLang;
import dk.muj.derius.api.inventory.SpecialItemManager;
import dk.muj.derius.api.mixin.BlockMixin;
import dk.muj.derius.api.mixin.MaxLevelMixin;
import dk.muj.derius.api.mixin.StaminaMixin;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.skill.Skill;

public class DeriusAPI
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// This field is set using reflection
	private static final Derius CORE = null;
	public static Derius getCore() { return CORE; }
	public static Derius getCoreEnsured() { ensureHasCore(); return getCore(); }
	
	private static void ensureHasCore() { if (CORE == null) throw new RuntimeException("core is null"); }
	
	private static DLang DLANG;
	public static DLang getDLang() { return DLANG; }
	public static void setDLang(DLang lang) { DLANG = lang; }
	
	// -------------------------------------------- //
	// FIELDS: MIXIN
	// -------------------------------------------- //
	
	private static MaxLevelMixin maxLevelMixin;
	public static MaxLevelMixin getMaxLevelMixin() { return maxLevelMixin; }
	public static void setMaxLevelMixin (MaxLevelMixin val) { maxLevelMixin = val; }
	
	private static BlockMixin blockMixin;
	public static BlockMixin getBlockMixin() { return blockMixin; }
	public static void setBlockMixin (BlockMixin val) { blockMixin = val; }
	
	private static StaminaMixin staminaMixin;
	public static StaminaMixin getStaminaMixin() { return staminaMixin; }
	public static void setStaminaMixin (StaminaMixin val) { staminaMixin = val; }

	// -------------------------------------------- //
	// DATABASE
	// -------------------------------------------- //

	public static Gson getGson(Plugin plugin)
	{
		return getCoreEnsured().getGson(plugin);
	}
	
	// -------------------------------------------- //
	// DPLAYERS
	// -------------------------------------------- //
	
	/**
	 * Gets all players registered by Derius.
	 * @return {Collection<? extends DPlayer>} all DPlayers
	 */
	public static Collection< ? extends DPlayer> getAllDPlayers()
	{
		return getCoreEnsured().getAllDPlayers();
	}
	
	/**
	 * Gets a DPlayer (can be offline)
	 * @param {Object} an object for the players
	 * can be one of the following
	 * 1. id (as string)
	 * 2. uuid
	 * 3. player/commandsender bukkit object
	 * @return {DPlayer} the corresponding dplayer
	 */
	public static DPlayer getDPlayer(Object senderObject)
	{
		return getCoreEnsured().getDPlayer(senderObject);
	}
	
	// -------------------------------------------- //
	// SKILLS
	// -------------------------------------------- //
	
	/**
	 * Gets all registered skills
	 * @return{Collection<? extends Skill>} all skills
	 */
	public static Collection< ? extends Skill> getAllSkills()
	{
		return getCoreEnsured().getAllSkills();
	}
	
	/**
	 * Gets the skill corresponding to passed id-
	 * @param {String} id of the skill you want to get.
	 * @return {Skill} the skill
	 */
	public static Skill getSkill(String id)
	{
		return getCoreEnsured().getSkill(id);
	}

	/**
	 * Registers a skill into the system.
	 * @param {Skill} skill to register.
	 */
	public static void registerSkill(Skill skill)
	{
		getCoreEnsured().registerSkill(skill);
	}
	
	// -------------------------------------------- //
	// ABILITIES
	// -------------------------------------------- //
	
	/**
	 * Gets all registered abilities
	 * @return{Collection<? extends Ability>} all abilities
	 */
	public static Collection< ? extends Ability<?>> getAllAbilities()
	{
		return getCoreEnsured().getAllAbilities();
	}
	
	/**
	 * Gets the ability corresponding to passed id-
	 * @param {String} id of the ability you want to get.
	 * @return {Ability} the ability
	 */
	public static Ability<?> getAbility(String id)
	{
		return getCoreEnsured().getAbility(id);
	}
	
	public static void registerAbility(Ability<?> ability)
	{
		getCoreEnsured().registerAbility(ability);
	}
	
	// -------------------------------------------- //
	// EXP
	// -------------------------------------------- //
	
	/**
	 * Registers an exp gain so exp will automatically be gained.
	 * @param {BlockBreakExpGain} expgainer to register.
	 */
	public static void registerExpGain(BlockBreakExpGain gainer)
	{
		getCoreEnsured().registerExpGain(gainer);
	}
	
	// -------------------------------------------- //
	// PREPARABLE TOOLS
	// -------------------------------------------- //
	
	/**
	 * Gets the tool which a player can prepare.
	 * When a player prepare a tool they get a message and
	 * skills can use it to only activate if the player has a tool prepared.
	 * @return {Collection<Material>} collection of tools that can be prepared.
	 */
	public static Collection<Material> getPreparableTools()
	{
		return getCoreEnsured().getPreparableTools();
	}
	
	/** @see Derius#getPreparableTools this is just a mutative operation on that collection.*/
	public static void registerPreparableTools(Collection<Material> materials)
	{
		getCoreEnsured().registerPreparableTools(materials);
	}
	
	/** @see Derius#getPreparableTools this is just a mutative operation on that collection.*/
	public static void registerPreparableTool(Material material)
	{
		getCoreEnsured().registerPreparableTool(material);
	}
	
	/** @see Derius#getPreparableTools this is just doing a check on that collection.*/
	public static boolean isRegisteredAsPreparable(Material material)
	{
		return getCoreEnsured().isRegisteredAsPreparable(material);
	}
	
	/** @see Derius#getPreparableTools this is just doing a check on that collection.*/
	public static void unregisterPreparableTool(Material material)
	{
		getCoreEnsured().unregisterPreparableTool(material);
	}
	
	// -------------------------------------------- //
	// SCHEDULED DEACTIVATE
	// -------------------------------------------- //
	
	/**
	 * Checks if a scheduled deactive is scheduled yet.
	 * @param {ScheduledDeactivate} scheduled deactivate to check for.
	 * @return {boolean} true is scheduled.
	 */
	public static boolean isDeactivateScheduled(ScheduledDeactivate sd)
	{
		return getCoreEnsured().isScheduled(sd);
	}
	
	/**
	 * Scheduled a scheduled deactivate
	 * @param {ScheduledDeactivate} scheduled deactivate to schedule.
	 */
	public static void scheduleDeactivate(ScheduledDeactivate sd)
	{
		getCoreEnsured().schedule(sd);
	}
	
	// -------------------------------------------- //
	// OTHER
	// -------------------------------------------- //
	
	/**
	 * Registers a special item manager, used to avoid cheating.
	 * @param {SpecialItemManager} manager to activate.
	 */
	public static void registerSpecialItemManager(SpecialItemManager manager)
	{
		getCoreEnsured().registerSpecialItemManager(manager);
	}
	
	public static void debug(int level, String format, Object... args)
	{
		getCoreEnsured().debug(level, format, args);
	}
	
	// -------------------------------------------- //
	// BLOCK MIXIN
	// -------------------------------------------- //
	
	/**
	 * Checks whether the block at this PS was placed by a player.
	 * @param {PS} The PS in question
	 * @return {boolean} true if it was placed by a player.
	 */
	public static boolean isBlockPlacedByPlayer(PS ps)
	{
		return getBlockMixin().isBlockPlacedByPlayer(ps);
	}
	
	// Default
	/**
	 * Checks whether that block was placed by a player.
	 * @param {Block} The block in question
	 * @return {boolean} true if it was placed by a player.
	 */
	public static boolean isBlockPlacedByPlayer(Block block)
	{
		return getBlockMixin().isBlockPlacedByPlayer(block);
	}
	
	/**
	 * Checks whether the block at this location was placed by a player.
	 * @param {Location} The location in question
	 * @return {boolean} true if it was placed by a player.
	 */
	public static boolean isBlockPlacedByPlayer(Location loc)
	{
		return getBlockMixin().isBlockPlacedByPlayer(loc);
	}
	
	// Listening
	/**
	 * Gives the materials which are currently listened for.
	 * @return {Set<Material>} The set of materials.
	 */
	public static Set<Material> getBlocksTypesToListenFor()
	{
		return getBlockMixin().getBlocksTypesToListenFor();
	}
	
	/**
	 * Sets the materials which are listening for.
	 * @param {Collection<Material>} The Collection of materials you want to set it to
	 */
	public static void setBlocksTypesToListenFor(Collection<Material> blocks)
	{
		getBlockMixin().setBlocksTypesToListenFor(blocks);
	}
	
	/**
	 * Add the materials passed in to the current Set of materials to listen for.
	 * @param {Collection<Material>} The collection of Materials you want to add
	 */
	public static void addBlockTypesToListenFor(Collection<Material> materials)
	{
		getBlockMixin().addBlockTypesToListenFor(materials);
	}
	
	/**
	 * Add the materials passed in to the current Set of materials to listen for.
	 * @param {Material...} The Array of Materials you want to add
	 */
	public static void addBlockTypesToListenFor(Material... materials)
	{
		getBlockMixin().addBlockTypesToListenFor(materials);
	}
	
	/**
	 * Evaluates whether this material is listened for or not.
	 * @param {Material} The material you want to check
	 * @return {boolean} whether or not it is listened for
	 */
	public static boolean isListenedFor(Material material)
	{
		return getBlockMixin().isListenedFor(material);
	}
	
	// -------------------------------------------- //
	// STAMINA MIXIN
	// -------------------------------------------- //
	
	/**
	 * The amount of millis it will take for this player to fully regen stamina
	 * @param {long} millis it would take from 0 to max
	 */
	public static long staminaRegenTime(Player senderObject)
	{
		return getStaminaMixin().regenTime(senderObject);
	}
	
	public static double sprintStaminaMultiplier(Player player)
	{
		return getStaminaMixin().sprintMultiplier(player);
	}
	public static double walkStaminaMultiplier(Player player)
	{
		return getStaminaMixin().walkMultiplier(player);
	}
	public static double sneaStaminakMultiplier(Player player)
	{
		return getStaminaMixin().sneakMultiplier(player);
	}
	public static double standStillStaminaMutiplier(Player player)
	{
		return getStaminaMixin().standStillMultiplier(player);
	}
	
	public static double getStaminaMultiplier(Player playerObject)
	{
		return getStaminaMixin().getMultiplier(playerObject);
	}
	
	public static boolean standsStill(Player player)
	{
		return getStaminaMixin().standsStill(player);
	}
	
	public static double staminaSprintLimit(Player player)
	{
		return getStaminaMixin().noSprintStamina(player);
	}

}
