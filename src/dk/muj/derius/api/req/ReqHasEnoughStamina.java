package dk.muj.derius.api.req;

import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.Req;
import dk.muj.derius.api.VerboseLevel;
import dk.muj.derius.api.ability.Ability;
import dk.muj.derius.api.config.DLang;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.req.util.ReqNoDefault;
import dk.muj.derius.api.req.util.ReqNoSkill;

public class ReqHasEnoughStamina implements Req, ReqNoSkill, ReqNoDefault
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static ReqHasEnoughStamina i = new ReqHasEnoughStamina();
	public static ReqHasEnoughStamina get() { return i; }
	private ReqHasEnoughStamina() {}
	
	// -------------------------------------------- //
	// OVERRIDE: VERBOSE LEVEL
	// -------------------------------------------- //
	
	@Override
	public VerboseLevel getVerboseLevel()
	{
		return VerboseLevel.HIGH;
	}
	
	// -------------------------------------------- //
	// OVERRIDE: ABILITY
	// -------------------------------------------- //
	
	@Override
	public boolean apply(DPlayer dplayer, Ability<?> ability)
	{
		return dplayer.hasEnoughStamina(ability.getStaminaUsage());
	}

	@Override
	public String createErrorMessage(DPlayer dplayer, Ability<?> ability)
	{
		return Txt.parse(DLang.get().getMustHaveEnoughStamina().replace("{ability}", ability.getDisplayName(dplayer)));
	}

}
