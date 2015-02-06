package dk.muj.derius.api;

import java.util.Collection;

public interface Derius
{
	// -------------------------------------------- //
	// DPLAYERS
	// -------------------------------------------- //
	
	/**
	 * Gets all players registered by Derius.
	 * @return {Collection<? extends DPlayer>} all DPlayers
	 */
	public abstract Collection< ? extends DPlayer> getAllDPlayers();
	
	/**
	 * Gets a DPlayer (can be offline)
	 * @param {Object} an object for the players
	 * can be one of the following
	 * 1. id (as string)
	 * 2. uuid
	 * 3. player/commandsender bukkit object
	 * @return {DPlayer} the corresponding dplayer
	 */
	public abstract DPlayer getDPlayer(Object senderObject);
	
}
