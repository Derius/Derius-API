package dk.muj.derius.api;

import java.util.ArrayList;
import java.util.List;

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
	// ORDER
	// -------------------------------------------- //
	
	/**
	 * Get all VerbooseLevels of higher priority than this.
	 * @param {boolean} is this included
	 * @return {List<VerbooseLevel>} all VerbooseLevels of higher priority than this.
	 */
	public List<VerboseLevel> getHigher(boolean inclusive)
	{
		List<VerboseLevel> ret = new ArrayList<>();
		
		for (VerboseLevel level : VerboseLevel.values())
		{
			if (level.ordinal() > this.ordinal()) ret.add(level); 
		}
		
		if (inclusive) ret.add(this);
		
		return ret;
	}
	
	/**
	 * Get all VerbooseLevels of lower priority than this.
	 * @param {boolean} is this included
	 * @return {List<VerbooseLevel>} all VerbooseLevels of lower priority.
	 */
	public List<VerboseLevel> getLower(boolean inclusive)
	{
		List<VerboseLevel> ret = new ArrayList<>();
		
		for (VerboseLevel level : VerboseLevel.values())
		{
			if (level.ordinal() < this.ordinal()) ret.add(level); 
		}
		
		if (inclusive) ret.add(this);
		
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
		return level.ordinal() >= this.ordinal();
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
	// CONSTRUCT
	// -------------------------------------------- //
	
	private VerboseLevel()
	{
		
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
