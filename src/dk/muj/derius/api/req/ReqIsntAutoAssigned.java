package dk.muj.derius.api.req;

import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.Req;
import dk.muj.derius.api.VerboseLevel;
import dk.muj.derius.api.config.DLang;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.req.util.ReqAbilityToSkill;
import dk.muj.derius.api.req.util.ReqNoDefault;
import dk.muj.derius.api.skill.Skill;

public class ReqIsntAutoAssigned implements Req, ReqAbilityToSkill, ReqNoDefault
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static ReqIsntAutoAssigned i = new ReqIsntAutoAssigned();
	public static ReqIsntAutoAssigned get() { return i; }
	private ReqIsntAutoAssigned() {}
	
	// -------------------------------------------- //
	// OVERRIDE: VERBOSE LEVEL
	// -------------------------------------------- //
	
	@Override
	public VerboseLevel getVerboseLevel()
	{
		return VerboseLevel.LOW;
	}
	
	// -------------------------------------------- //
	// OVERRIDE: SKILL
	// -------------------------------------------- //

	@Override
	public boolean apply(DPlayer dplayer, Skill skill)
	{
		return ! skill.isSpAutoAssigned();
	}

	@Override
	public String createErrorMessage(DPlayer dplayer, Skill skill)
	{
		return Txt.parse(DLang.get().getSpecialisationAutoAssigned().replace("{skill}", skill.getDisplayName(dplayer)));
	}
	
}
