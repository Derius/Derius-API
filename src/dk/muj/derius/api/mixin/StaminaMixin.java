package dk.muj.derius.api.mixin;

import org.bukkit.entity.Player;

public interface StaminaMixin extends Mixin
{
	// -------------------------------------------- //
	// REGEN
	// -------------------------------------------- //
	
	/**
	 * The amount of millis it will take for this player to fully regen stamina
	 * @param {long} millis it would take from 0 to max
	 */
	public abstract long regenTime(Player player);
	
	/*
	 * Only one of the following multipliers can be applied.
	 * These multiplies can be negative.
	 * They are tested for in this order.
	 * 1. standsStill
	 * 2. sprints
	 * 3. sneaks
	 * If none of the four above applied they are walking.
	 */
	
	/**
	 * Players will not be able to sprint if their stamina goes below this.
	 */
	public abstract double noSprintStamina(Player playerObject);
	
	// -------------------------------------------- //
	// MULTIPLIER
	// -------------------------------------------- //
	
	public abstract double sprintMultiplier(Player player);
	public abstract double walkMultiplier(Player player);
	public abstract double sneakMultiplier(Player player);
	public abstract double standStillMultiplier(Player player);
	
	
	public default double getMultiplier(Player player)
	{
		if (player == null) return 0.0;
		
		if (standsStill(player)) return this.standStillMultiplier(player);
		else if (player.isSprinting()) return this.sprintMultiplier(player);
		else if (player.isSneaking()) return this.sneakMultiplier(player);
		else return this.walkMultiplier(player);
	}
	
	// -------------------------------------------- //
	// UTIL
	// -------------------------------------------- //
	
	public abstract boolean standsStill(Player player);
	
}
