package dk.muj.derius.api;

import java.util.Collection;

public class DeriusAPI
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// This field is set using reflection
	private static Derius core;
	public static Derius getCore() { return core; }
	
	// -------------------------------------------- //
	// DPLAYERS
	// -------------------------------------------- //
	
	/**
	 * Gets all players registered by Derius.
	 * @return {Collection<? extends DPlayer>} all DPlayers
	 */
	public static Collection< ? extends DPlayer> getAllDPlayers()
	{
		return core.getAllDPlayers();
	}
	
	/**
	 * Gets a DPlayer (can be offline)
	 * @param {Object} an object for the players
	 * can be one of the following
	 * 1. id (as string)
	 * 2. uuid
	 * 3. player/commandsender bukkit object
	 * @return {DPlayer} the corresponding dplayer
	 */
	public static DPlayer getDPlayer(Object senderObject)
	{
		return core.getDPlayer(senderObject);
	}
}
