package dk.muj.derius.api.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class DeriusEvent extends Event implements Runnable
{
	// -------------------------------------------- //
	// REQUIRED EVENT CODE
	// -------------------------------------------- //
	
	private static final HandlerList handlers = new HandlerList();
	@Override public HandlerList getHandlers() {	return handlers;	} 
	public static HandlerList getHandlerList() {	return handlers;	}
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public DeriusEvent()
	{
		this(false);
	}
	
	public DeriusEvent(boolean isAsync)
	{
		super(isAsync);
	}
	
	// -------------------------------------------- //
	// OVERRIDE: RUNNABLE
	// -------------------------------------------- //
	
	@Override
	public void run()
	{
		this.preRun();
		Bukkit.getPluginManager().callEvent((Event) this);
		this.postRun();
	}
	
	// Pre & post run
	public void preRun() {};
	public void postRun() {};
	
	// -------------------------------------------- //
	// SMART RUN
	// -------------------------------------------- //
	
	/**
	 * this is a smarter run, that will tell you whether or not it suceeded.
	 * @return true if event was not cancelled
	 */
	public boolean runEvent()
	{
		this.run();
		
		if (this instanceof Cancellable)
		{
			Cancellable i = (Cancellable) this;
			return ! i.isCancelled();
		}
		return true;
	}

}
