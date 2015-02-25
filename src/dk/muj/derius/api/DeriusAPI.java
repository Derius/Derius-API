package dk.muj.derius.api;

import java.util.Collection;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.massivecraft.massivecore.ps.PS;

import dk.muj.derius.api.mixin.BlockMixin;
import dk.muj.derius.api.mixin.MaxLevelMixin;
import dk.muj.derius.api.mixin.StaminaMixin;

public class DeriusAPI
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// This field is set using reflection
	private static final Derius core = null;
	public static Derius getCore() { return core; }
	
	private static void ensureHasCore() { if (core == null) throw new RuntimeException("core is null"); }
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
	// DPLAYERS
	// -------------------------------------------- //
	
	/**
	 * Gets all players registered by Derius.
	 * @return {Collection<? extends DPlayer>} all DPlayers
	 */
	public static Collection< ? extends DPlayer> getAllDPlayers()
	{
		ensureHasCore();
		return getCore().getAllDPlayers();
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
		ensureHasCore();
		return getCore().getDPlayer(senderObject);
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
		ensureHasCore();
		return getCore().getAllSkills();
	}
	
	/**
	 * Gets the skill corresponding to passed id-
	 * @param {String} id of the skill you want to get.
	 * @return {Skill} the skill
	 */
	public static Skill getSkill(String id)
	{
		ensureHasCore();
		return getCore().getSkill(id);
	}
	
	// -------------------------------------------- //
	// ABILITIES
	// -------------------------------------------- //
	
	/**
	 * Gets all registered abilities
	 * @return{Collection<? extends Ability>} all abilities
	 */
	public static Collection< ? extends Ability> getAllAbilities()
	{
		ensureHasCore();
		return getCore().getAllAbilities();
	}
	
	/**
	 * Gets the ability corresponding to passed id-
	 * @param {String} id of the ability you want to get.
	 * @return {Ability} the ability
	 */
	public static Ability getAbility(String id)
	{
		ensureHasCore();
		return getCore().getAbility(id);
	}
	
	// -------------------------------------------- //
	// BLOCK MIXIN
	// -------------------------------------------- //
	
	public static boolean isBlockPlacedByPlayer(PS ps)
	{
		return getBlockMixin().isBlockPlacedByPlayer(ps);
	}
	
	// Default
	public static boolean isBlockPlacedByPlayer(Block block)
	{
		return getBlockMixin().isBlockPlacedByPlayer(block);
	}
	
	public static boolean isBlockPlacedByPlayer(Location loc)
	{
		return getBlockMixin().isBlockPlacedByPlayer(loc);
	}
	
	// Listening
	public static Set<Material> getBlocksTypesToListenFor()
	{
		return getBlockMixin().getBlocksTypesToListenFor();
	}
	
	public static void setBlocksTypesToListenFor(Collection<Material> blocks)
	{
		getBlockMixin().setBlocksTypesToListenFor(blocks);
	}
	
	public static void addBlockTypesToListenFor(Collection<Material> blocks)
	{
		getBlockMixin().addBlockTypesToListenFor(blocks);
	}
	
	public static void addBlockTypesToListenFor(Material... blocks)
	{
		getBlockMixin().addBlockTypesToListenFor(blocks);
	}
	
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
	public static long staminaRegenTime(Object senderObject)
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
	
	public static double getStaminaMultiplier(Object playerObject)
	{
		return getStaminaMixin().getMultiplier(playerObject);
	}
	
	public static boolean standsStill(Player player)
	{
		return getStaminaMixin().standsStill(player);
	}
	
}
