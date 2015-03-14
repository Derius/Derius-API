package dk.muj.derius.api.req;

import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.Req;
import dk.muj.derius.api.VerboseLevel;
import dk.muj.derius.api.config.DLang;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.req.util.ReqAbilityToSkill;
import dk.muj.derius.api.req.util.ReqNoDefault;
import dk.muj.derius.api.skill.Skill;
import dk.muj.derius.lib.Getter;

public class ReqIsAtleastLevel implements Req, ReqAbilityToSkill, ReqNoDefault
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private final Getter<Integer> levelGetter;
	public int getlevel() { return this.levelGetter.get(); }
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	public static ReqIsAtleastLevel get(Getter<Integer> levelGetter) { return new ReqIsAtleastLevel(levelGetter); }
	private ReqIsAtleastLevel(Getter<Integer> levelGetter) { this.levelGetter = levelGetter; }
	
	// -------------------------------------------- //
	// OVERRIDE: VERBOSE LEVEL
	// -------------------------------------------- //
	
	@Override
	public VerboseLevel getVerboseLevel()
	{
		return VerboseLevel.NORMAL;
	}

	// -------------------------------------------- //
	// OVERRIDE: SKILL
	// -------------------------------------------- //
	
	@Override
	public boolean apply(DPlayer dplayer, Skill skill)
	{
		return (dplayer.getLvl(skill) >= this.getlevel());
	}

	@Override
	public String createErrorMessage(DPlayer dplayer, Skill skill)
	{
		return Txt.parse(DLang.get().getSkillLevelIsTooLow().replace("{level}", String.valueOf(this.getlevel())).replace("{skill}", skill.getDisplayName(dplayer)));
	}

}
