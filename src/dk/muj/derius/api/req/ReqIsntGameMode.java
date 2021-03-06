package dk.muj.derius.api.req;

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.GameMode;

import com.massivecraft.massivecore.util.IdUtil;
import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.Req;
import dk.muj.derius.api.VerboseLevel;
import dk.muj.derius.api.config.DLang;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.req.util.ReqToDefault;

public class ReqIsntGameMode implements Req, ReqToDefault
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	public static ReqIsntGameMode get(GameMode... gameModes) { return get(Arrays.asList(gameModes)); }
	public static ReqIsntGameMode get(Collection<GameMode> gameModes) { return new ReqIsntGameMode(gameModes); }
	public ReqIsntGameMode(Collection<GameMode> gameModes) { this.gameModes = gameModes; }
	
	// -------------------------------------------- //
	// OVERRIDE: VERBOSE LEVEL
	// -------------------------------------------- //
	
	@Override
	public VerboseLevel getVerboseLevel()
	{
		return VerboseLevel.HIGH;
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private final Collection<GameMode> gameModes;
	public Collection<GameMode> getGameModes() { return this.gameModes; }
	
	// -------------------------------------------- //
	// OVERRIDE: DEFAULT
	// -------------------------------------------- //
	
	@Override
	public boolean apply(DPlayer dplayer)
	{
		for (GameMode gm : gameModes)
		{
			if ( IdUtil.isGameMode(dplayer, gm, false)) return false;
		}
		return true;
	}
	
	@Override
	public String createErrorMessage(DPlayer dplayer)
	{
		//																					Using String.valueOf null is handled.
		return Txt.parse(DLang.get().getMustNotBeGamemode().replace("{gm}", Txt.getNicedEnum(String.valueOf(IdUtil.getGameMode(dplayer, null)))));
	}

}
