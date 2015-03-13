package dk.muj.derius.api.events;

import org.apache.commons.lang.Validate;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import dk.muj.derius.api.ability.Ability;
import dk.muj.derius.api.events.player.DPlayerEvent;
import dk.muj.derius.api.player.DPlayer;

/**
 * Thrown whenever a player activates an ability.
 * Some abilities don't throw this event due to the way they work,
 * that is however the exception rather than the norm.
 * Some abilities are still cancelled even if this event is thrown.
 */
public class AbilityActivatePreEvent extends DeriusEvent implements Cancellable, AbilityEvent, DPlayerEvent
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
	
	private final Ability ability;
	public Ability getAbility() { return ability; }
	
	private DPlayer dplayer;
	public DPlayer getDPlayer() { return dplayer; }
	
	private boolean cancelled = false;
	public boolean isCancelled() { return this.cancelled; }
	public void setCancelled(boolean cancel) { this.cancelled = cancel; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public AbilityActivatePreEvent(Ability ability, DPlayer dplayer)
	{
		Validate.notNull(dplayer, "dplayer mustn't be null");
		Validate.notNull(ability, "ability mustn't be null");
		
		this.dplayer  = dplayer;
		this.ability = ability;
	}

	// -------------------------------------------- //
	// TO STRING
	// -------------------------------------------- //
	
	@Override
	public String toString()
	{
		return this.getDPlayer().getName() + " activated " + getAbility().getName();
	}
	
	// -------------------------------------------- //
	// EQUALS & HASH CODE
	// -------------------------------------------- //
	
	@Override
	public boolean equals(Object obj)
	{		
		if (obj == this) return true;
		if ( ! (obj instanceof AbilityActivatePreEvent)) return false;
		AbilityActivatePreEvent that = (AbilityActivatePreEvent) obj;
	
		if (this.getDPlayer() != that.getDPlayer()) return false;
		if (this.getAbility() != that.getAbility()) return false;
		
		return true;
	}
	
	@Override
	public int hashCode()
	{
		int result = 1;
		int prime = 31;
		
		result += this.getDPlayer().hashCode()*prime;
		result += this.getAbility().hashCode()*prime;
		
		return result;
	}

}
