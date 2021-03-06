package dk.muj.derius.api.req;

import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.Req;
import dk.muj.derius.api.VerboseLevel;
import dk.muj.derius.api.config.DLang;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.req.util.ReqAbilityToSkill;
import dk.muj.derius.api.req.util.ReqNoDefault;
import dk.muj.derius.api.skill.Skill;

public class ReqIsSpecialised implements Req, ReqAbilityToSkill, ReqNoDefault
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static ReqIsSpecialised i = new ReqIsSpecialised();
	public static ReqIsSpecialised get() { return i; }
	private ReqIsSpecialised() {}
	
	// -------------------------------------------- //
	// OVERRIDE: VERBOSE LEVEL
	// -------------------------------------------- //
	
	@Override
	public VerboseLevel getVerboseLevel()
	{
		return VerboseLevel.HIGH;
	}
	
	// -------------------------------------------- //
	// OVERRIDE: SKILL
	// -------------------------------------------- //
	@Override
	public boolean apply(DPlayer dplayer, Skill skill)
	{
		return dplayer.isSpecialisedIn(skill);
	}
	
	@Override
	public String createErrorMessage(DPlayer dplayer, Skill skill)
	{
		return Txt.parse(DLang.get().getSpecialisationIsnt().replace("{skill}", skill.getDisplayName(dplayer)));
	}

}
