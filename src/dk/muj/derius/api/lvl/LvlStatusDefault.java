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
	public LvlStatus withExp (OptionalInt  exp) { return new LvlStatusDefault(this.level, exp, this.expToNext); }
	
	/**
	 * This is how much exp is required to reach next lvl
	 * It ignores the current exp
	 */
	private final OptionalInt  expToNext;
	public OptionalInt  getExpToNextLvl () { return expToNext; }
	public LvlStatus withExpToNextLvl (OptionalInt  expToNextLvl) { return new LvlStatusDefault(this.level,this.exp, expToNextLvl); }
	
	// -------------------------------------------- //
	// CONSTRUCTOR
	// -------------------------------------------- //
	
	public LvlStatusDefault(int level)
	{
		this(level, OptionalInt.empty(), OptionalInt.empty());
	}
	
	public LvlStatusDefault(int level, OptionalInt  currentExperience, OptionalInt  expToNextLvl)
	{
		this.level = level;
		this.exp = currentExperience;
		this.expToNext = expToNextLvl;
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
					.replaceAll("{level}", String.valueOf(this.getLvl()));
		}
		else
		{
		
			final String format = DLang.get().getLevelStatusFormat();
			ret = format
				.replaceAll("{level}", String.valueOf(this.getLvl()))
				.replaceAll("{exp}", String.valueOf(this.getExp().getAsInt()))
				.replaceAll("{expToNext}", String.valueOf(this.getExpToNextLvl().getAsInt()));
		}
		return Txt.parse(ret);
	}
	
	// -------------------------------------------- //
	// EQUALS
	// -------------------------------------------- //
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) return false;
		if ( ! (obj instanceof LvlStatus)) return false;
		
		LvlStatus that = (LvlStatus) obj;
		if (that.getLvl() == this.getLvl() && that.getExp() == this.getExp() && that.getExpToNextLvl() == this.getExpToNextLvl()) return true;
		return false;
	}

}
