package dk.muj.derius.api.lvl;


public class LvlStatusCalculatorLinear implements LvlStatusCalculator
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Amount of experience required for the first level.
	private final int startExp;
	public int getStartExp() { return this.startExp; }
	
	// Amount of extra required exp per level
	private final int expPerLevel;
	public int getExpPerLevel() { return this.expPerLevel; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	private LvlStatusCalculatorLinear(int startExp, int expPerLevel)
	{
		this.startExp = startExp;
		this.expPerLevel = expPerLevel;
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public LvlStatus calculateLvlStatus(long exp)
	{
		int level = 0;
		int nextLvlExp;
		for(nextLvlExp = startExp; nextLvlExp < exp; level++)
		{
			exp -= nextLvlExp;
			nextLvlExp += expPerLevel;
		}
		
		return LvlStatusDefault.valueOf(level, (int) exp, nextLvlExp);
	}
	
	// -------------------------------------------- //
	// FACTORY
	// -------------------------------------------- //

	public static LvlStatusCalculatorLinear valueOf(int startExp, int expPerLevel)
	{
		return new LvlStatusCalculatorLinear(startExp, expPerLevel);
	}
	
}
