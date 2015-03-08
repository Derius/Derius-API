package dk.muj.derius.api.events.player;

import org.apache.commons.lang.Validate;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import dk.muj.derius.api.events.DeriusEvent;
import dk.muj.derius.api.player.DPlayer;

public class PlayerStaminaUpdateEvent extends DeriusEvent implements Cancellable, DPlayerEvent
{
	// -------------------------------------------- //
	// REQUIRED EVENT CODE
	// -------------------------------------------- //
	
	private static final HandlerList handlers = new HandlerList();
	@Override public HandlerList getHandlers() { return handlers; }
	public static HandlerList getHandlerList() { return handlers; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private final DPlayer dplayer;
	public DPlayer getDPlayer() { return dplayer; }
	
	private double amount;
	public double getStaminaAmount() { return amount; }
	public void setStaminaAmount(double staminaAmount) { this.amount = staminaAmount; }
	
	private boolean cancelled = false;
	public boolean isCancelled() { return this.cancelled; }
	public void setCancelled(boolean cancel) { this.cancelled = cancel; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public PlayerStaminaUpdateEvent(DPlayer dplayer, double staminaAmount)
	{
		Validate.notNull(dplayer, "dplayer mustn't be null");
		
		this.dplayer = dplayer;
		this.amount = staminaAmount;
		
	}
	
	// -------------------------------------------- //
	// TO STRING
	// -------------------------------------------- //
	
	@Override
	public String toString()
	{
		return this.getDPlayer().getName() + " got " + this.getStaminaAmount() + " stamina updated.";
	}
	
	// -------------------------------------------- //
	// EQUALS & HASH CODE
	// -------------------------------------------- //
	
	@Override
	public boolean equals(Object obj)
	{		
		if (obj == this) return true;
		if ( ! (obj instanceof PlayerStaminaUpdateEvent)) return false;
		PlayerStaminaUpdateEvent that = (PlayerStaminaUpdateEvent) obj;
	
		if (this.getStaminaAmount() != that.getStaminaAmount()) return false;
		if (this.getDPlayer() != that.getDPlayer()) return false;
		
		return true;
	}
	
	@Override
	public int hashCode()
	{
		int result = 1;
		int prime = 31;
		
		result += this.getStaminaAmount() * prime;
		result += this.getDPlayer().hashCode() * prime;
		
		return result;
	}

}
