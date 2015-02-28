package dk.muj.derius.api;

import com.massivecraft.massivecore.util.Txt;

public enum VerboseLevel
{
	// -------------------------------------------- //
	// ENUM
	// -------------------------------------------- //
	
	NEVER(),
	LOWEST(),
	LOW(),
	NORMAL(),
	HIGH(),
	HIGHEST(),
	ALWAYS(),
	
	// END OF LIST
	;
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	private VerboseLevel()
	{
		
	}
	
	// -------------------------------------------- //
	// NICER NAMES
	// -------------------------------------------- //
	
	public int priority()
	{
		return this.ordinal();
	}
	
	// -------------------------------------------- //
	// ORDER
	// -------------------------------------------- //
	
	/**
	 * Get all VerbooseLevels of higher priority than this.
	 * @param {boolean} is this included
	 * @return {List<VerbooseLevel>} all VerbooseLevels of higher priority than this.
	 */
	public VerboseLevel[] getHigher(boolean inclusive)
	{
		VerboseLevel[] values = values();
		
		int offset = this.ordinal() + (inclusive ? 0 : 1);
		VerboseLevel[] ret = new VerboseLevel[values.length - offset];
		
		for (int i = 0; i < ret.length; i++)
		{
			ret[i] = values[offset + i];
		}
		
		return ret;
	}
	
	/**
	 * Get all VerbooseLevels of lower priority than this.
	 * @param {boolean} is this included
	 * @return {List<VerbooseLevel>} all VerbooseLevels of lower priority.
	 */
	public VerboseLevel[] getLower(boolean inclusive)
	{
		VerboseLevel[] values = values();
		VerboseLevel[] ret = new VerboseLevel[this.ordinal() + (inclusive ? 1 : 0)];
		
		for (int i = 0; i < ret.length; i++)
		{
			ret[i] = values[i];
		}
		
		return ret;
	}
	
	/**
	 * Tells whether or not the passed verbooselevel is included.
	 * it is included if it is of higher value or equal to this.
	 * @param {VerbooseLevel} level to check for.
	 * @return {boolean} true if included.
	 */
	public boolean includes(VerboseLevel level)
	{
		if (level == null) return false;
		return level.priority() >= this.priority();
	}
	
	/**
	 * Tells whether or not the passed verbooselevel is excluded.
	 * it is excluded if it is of lower value thanm this.
	 * @param {VerbooseLevel} level to check for.
	 * @return {boolean} true if included.
	 */
	public boolean excludes(VerboseLevel level)
	{
		return ! this.includes(level);
	}
	
	// -------------------------------------------- //
	// TO STRING
	// -------------------------------------------- //
	
	@Override
	public String toString()
	{
		return Txt.getNicedEnumString(this.name());
	}
	
}
