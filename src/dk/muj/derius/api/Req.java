package dk.muj.derius.api;

import dk.muj.derius.api.ability.Ability;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.skill.Skill;

public interface Req
{
	// -------------------------------------------- //
	// ABSTRACT
	// -------------------------------------------- //
	
	// SKill requirements
	public abstract boolean apply(DPlayer dplayer, Skill skill);
	public abstract  String createErrorMessage(DPlayer dplayer, Skill skill);
	
	// Ability<?> requirements
	public abstract  boolean apply(DPlayer dplayer, Ability<?> ability);
	public abstract  String createErrorMessage(DPlayer dplayer, Ability<?> ability);
	
	// Default
	public abstract  boolean apply(DPlayer dplayer);
	public abstract  String createErrorMessage(DPlayer dplayer);
	
	// Verboose level
	public abstract VerboseLevel getVerboseLevel();

}
