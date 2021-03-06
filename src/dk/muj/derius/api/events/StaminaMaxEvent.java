package dk.muj.derius.api.events;

import org.apache.commons.lang.Validate;
import org.bukkit.event.HandlerList;

import dk.muj.derius.api.events.player.DPlayerEvent;
import dk.muj.derius.api.player.DPlayer;

public class StaminaMaxEvent extends DeriusEvent implements DPlayerEvent
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
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public StaminaMaxEvent(DPlayer dplayer)
	{
		Validate.notNull(dplayer, "dplayer mustn't be null");
		
		this.dplayer = dplayer;	
	}
	
	// -------------------------------------------- //
	// EQUALS & HASH CODE
	// -------------------------------------------- //
	
	@Override
	public boolean equals(Object obj)
	{		
		if (obj == this) return true;
		if ( ! (obj instanceof StaminaMaxEvent)) return false;
		StaminaMaxEvent that = (StaminaMaxEvent) obj;
	
		if (this.getDPlayer() != that.getDPlayer()) return false;
		
		return true;
	}
	
	@Override
	public int hashCode()
	{
		int result = 1;
		int prime = 31;
		
		result += this.getDPlayer().hashCode() * prime;
		
		return result;
	}
	
}

