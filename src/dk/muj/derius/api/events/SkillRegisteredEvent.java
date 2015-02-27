package dk.muj.derius.api.events;

import org.apache.commons.lang.Validate;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import dk.muj.derius.api.Skill;

/**
 * This event is called when a skill is registered into the system.
 * Note the same skills will be registered on server startup each time.
 */
public class SkillRegisteredEvent extends DeriusEvent implements Cancellable, SkillEvent
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
	
	private final Skill skill;
	public Skill getSkill() { return skill; }
	
	private boolean cancelled = false;
	public boolean isCancelled() { return this.cancelled; }
	public void setCancelled(boolean cancel) { this.cancelled = cancel; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public SkillRegisteredEvent(Skill registeredSkill)
	{
		Validate.notNull(registeredSkill, "skill mustn't be null");
		
		this.skill = registeredSkill;
	}
	
	// -------------------------------------------- //
	// TO STRING
	// -------------------------------------------- //
	
	@Override
	public String toString()
	{
		return this.getSkill().getName() + " is registered";
	}
	
	// -------------------------------------------- //
	// EQUALS & HASH CODE
	// -------------------------------------------- //
	
	@Override
	public boolean equals(Object obj)
	{		
		if (obj == this) return true;
		if ( ! (obj instanceof SkillRegisteredEvent)) return false;
		SkillRegisteredEvent that = (SkillRegisteredEvent) obj;
	
		if (this.getSkill() != that.getSkill()) return false;
		
		return true;
	}
	
	@Override
	public int hashCode()
	{
		int result = 1;
		int prime = 31;
		
		result += this.getSkill().hashCode()*prime;
		
		return result;
	}
	
}
