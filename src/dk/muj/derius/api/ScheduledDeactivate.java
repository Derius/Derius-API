package dk.muj.derius.api;

import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.util.AbilityUtil;

public class ScheduledDeactivate implements Runnable
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private final DPlayer dplayer;
	public DPlayer getDPlayer() { return this.dplayer; }
	public String getPlayerId() { return this.getDPlayer().getId(); }
	
	private final long delayMillis;
	public long getDelayMillis() { return this.delayMillis; }
	
	private long dueMillis;
	public long getDueMillis() { return this.dueMillis; }
	public void setDueMillis(long dueMillis) { this.dueMillis = dueMillis; }
	public boolean isDue(long now) { return now >= this.dueMillis; }

	private Object other;
	public Object getOther() { return this.other; }
	public void setOther(Object other) { this.other = other; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public ScheduledDeactivate(DPlayer dplayer, long delayMillis, Object other)
	{
		this.dplayer = dplayer;
		this.delayMillis = delayMillis;
		this.dueMillis = 0;
		
		this.other = other;
	}
	
	// -------------------------------------------- //
	// SCHEDULING
	// -------------------------------------------- //
		
	public boolean isScheduled()
	{
		return DeriusAPI.isDeactivateScheduled(this);
	}
	
	public void schedule()
	{
		DeriusAPI.scheduleDeactivate(this);
	}

	// -------------------------------------------- //
	// OVERRIDE: RUNNABLE
	// -------------------------------------------- //
	
	@Override
	public void run()
	{
		AbilityUtil.deactivateActiveAbility(dplayer, other);
	}
	
}
