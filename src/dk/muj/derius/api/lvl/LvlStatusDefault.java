package dk.muj.derius.api.lvl;

import java.util.OptionalInt;

import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.config.DLang;


public class LvlStatusDefault implements LvlStatus
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	/**
	 * This is the current lvl
	 */
	private final int level;
	public int getLvl () {	return level;	}
	public LvlStatus setLvl (int level) { return new LvlStatusDefault(level, this.exp, this.expToNext); }
	
	/**
	 * This is the exp left after calculating the lvl.
	 * This is /not/ the total exp
	 */
	private final OptionalInt  exp;
	public OptionalInt getExp () {	return exp;	}
	public LvlStatus withExp(OptionalInt  exp) { return new LvlStatusDefault(this.level, exp, this.expToNext); }
	
	/**
	 * This is how much exp is required to reach next lvl
	 * It ignores the current exp
	 */
	private final OptionalInt  expToNext;
	public OptionalInt  getExpToNextLvl () { return expToNext; }
	public LvlStatus withExpToNextLvl(OptionalInt  expToNextLvl) { return new LvlStatusDefault(this.level,this.exp, expToNextLvl); }
	
	// -------------------------------------------- //
	// CONSTRUCTOR
	// -------------------------------------------- //
	
	private LvlStatusDefault(int level)
	{
		this(level, OptionalInt.empty(), OptionalInt.empty());
	}
	
	private LvlStatusDefault(int level, int currentExperience, int expToNextLvl)
	{
		this(level, OptionalInt.of(currentExperience), OptionalInt.of(expToNextLvl));
	}
	
	private LvlStatusDefault(int level, OptionalInt  currentExperience, OptionalInt  expToNextLvl)
	{
		this.level = level;
		this.exp = currentExperience;
		this.expToNext = expToNextLvl;
	}
	
	// -------------------------------------------- //
	// FACTORY
	// -------------------------------------------- //
	
	public static LvlStatusDefault valueOf(int level)
	{
		return new LvlStatusDefault(level);
	}
	
	public static LvlStatusDefault valueOf(int level, int  currentExperience, int  expToNextLvl)
	{
		return new LvlStatusDefault(level, currentExperience, expToNextLvl);
	}
	
	public static LvlStatusDefault valueOf(int level, OptionalInt  currentExperience, OptionalInt  expToNextLvl)
	{
		return new LvlStatusDefault(level, currentExperience, expToNextLvl);
	}
	
	// -------------------------------------------- //
	// TO STRING
	// -------------------------------------------- //
	
	@Override
	public String toString()
	{
		//  Example Output (before applying the colors): "<navy>LVL: <lime>1 <navy>XP: <lime>120<yellow>/<lime>5000"
		
		final String ret;
		
		if ( ! this.getExp().isPresent() || ! this.getExpToNextLvl().isPresent())
		{
			final String format = DLang.get().getLevelStatusFormatMini();
			ret = format
					.replace("{level}", String.valueOf(this.getLvl()));
		}
		else
		{
		
			final String format = DLang.get().getLevelStatusFormat();
			ret = format
				.replace("{level}", String.valueOf(this.getLvl()))
				.replace("{exp}", String.valueOf(this.getExp().getAsInt()))
				.replace("{expToNext}", String.valueOf(this.getExpToNextLvl().getAsInt()));
		}
		return Txt.parse(ret);
	}
	
	// -------------------------------------------- //
	// EQUALS
	// -------------------------------------------- //
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this) return true;
		if ( ! (obj instanceof LvlStatus)) return false;
		LvlStatus that = (LvlStatus) obj;
		
		if (this.getLvl() != that.getLvl()) return false;
		if ( ! this.getExp().equals(that.getExp())) return false;
		if ( ! this.getExpToNextLvl().equals(that.getExpToNextLvl())) return false;
		
		return true;
	}

}
