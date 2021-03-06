package dk.muj.derius.api.req.util;

import dk.muj.derius.api.Req;
import dk.muj.derius.api.ability.Ability;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.skill.Skill;

public interface ReqToDefault extends Req
{
	// -------------------------------------------- //
	// OVERRIDE: SKILL
	// -------------------------------------------- //
	
	@Override
	public default boolean apply(DPlayer dplayer, Skill skill)
	{
		return this.apply(dplayer);
	}

	@Override
	public default String createErrorMessage(DPlayer dplayer, Skill skill)
	{
		return this.createErrorMessage(dplayer);
	}
	
	// -------------------------------------------- //
	// OVERRIDE: ABILITY
	// -------------------------------------------- //
	
	@Override
	public default boolean apply(DPlayer dplayer, Ability<?> ability)
	{
		return this.apply(dplayer);
	}

	@Override
	public default String createErrorMessage(DPlayer dplayer, Ability<?> ability)
	{
		return this.createErrorMessage(dplayer);
	}
	
}
