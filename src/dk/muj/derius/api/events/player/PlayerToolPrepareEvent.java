package dk.muj.derius.api.events.player;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import dk.muj.derius.api.DPlayer;
import dk.muj.derius.api.events.DeriusEvent;

public class PlayerToolPrepareEvent extends DeriusEvent implements Cancellable, DPlayerEvent
{
	// -------------------------------------------- //
	// REQUIRED EVENT CODE
	// -------------------------------------------- //
	
	private static final HandlerList handlers = new HandlerList();
	@Override public HandlerList getHandlers() {	return handlers;	} 
	public static HandlerList getHandlerList() {	return handlers;	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private final Material tool;
	public Material getTool() { return tool; }
	
	private DPlayer dplayer;
	public DPlayer getDPlayer() { return dplayer; }
	
	private boolean cancelled = false;
	public boolean isCancelled() { return this.cancelled; }
	public void setCancelled(boolean cancel) { this.cancelled = cancel; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public PlayerToolPrepareEvent(Material material, DPlayer dplayer)
	{
		Validate.notNull(dplayer, "dplayer mustn't be null");
		Validate.notNull(material, "tool mustn't be null");
		
		this.dplayer  = dplayer;
		this.tool = material;
	}

	// -------------------------------------------- //
	// TO STRING
	// -------------------------------------------- //
	
	@Override
	public String toString()
	{
		return this.getDPlayer().getName() + " prepared " + getTool().name();
	}
	
	// -------------------------------------------- //
	// EQUALS & HASH CODE
	// -------------------------------------------- //
	
	@Override
	public boolean equals(Object obj)
	{		
		if (obj == null) return false;
		if ( ! (obj instanceof PlayerToolPrepareEvent)) return false;
		PlayerToolPrepareEvent that = (PlayerToolPrepareEvent) obj;
	
		if (that.getDPlayer() == this.getDPlayer() && that.getTool() == this.getTool()) return true;
		
		return false;
	}
	
	@Override
	public int hashCode()
	{
		int result = 1;
		
		int prime = 31;
		
		result += this.getDPlayer().hashCode()*prime;
		result += this.getTool().hashCode()*prime;
		
		return result;
	}
	
}
