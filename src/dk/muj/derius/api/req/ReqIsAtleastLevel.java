package dk.muj.derius.api.req;

import java.util.function.IntSupplier;

import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.Req;
import dk.muj.derius.api.VerboseLevel;
import dk.muj.derius.api.config.DLang;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.req.util.ReqAbilityToSkill;
import dk.muj.derius.api.req.util.ReqNoDefault;
import dk.muj.derius.api.skill.Skill;

public class ReqIsAtleastLevel implements Req, ReqAbilityToSkill, ReqNoDefault
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	public static ReqIsAtleastLevel get(IntSupplier levelGetter) { return new ReqIsAtleastLevel(levelGetter); }
	private ReqIsAtleastLevel(IntSupplier levelGetter) { this.levelGetter = levelGetter; }
	
	// -------------------------------------------- //
	// OVERRIDE: VERBOSE LEVEL
	// -------------------------------------------- //
	
	@Override
	public VerboseLevel getVerboseLevel()
	{
		return VerboseLevel.NORMAL;
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private final IntSupplier levelGetter;
	public int getlevel() { return this.levelGetter.getAsInt(); }

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
