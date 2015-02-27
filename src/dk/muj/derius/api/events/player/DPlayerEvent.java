package dk.muj.derius.api.events.player;

import org.bukkit.entity.Player;

import dk.muj.derius.api.DPlayer;

public interface DPlayerEvent
{
	public abstract DPlayer getDPlayer();
	public default Player getPlayer()
	{
		return this.getDPlayer().getPlayer();
	}
	
}
