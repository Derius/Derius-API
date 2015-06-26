package dk.muj.derius.api.lvl;

public class LvlStatusCalculatorExponential implements LvlStatusCalculator
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Amount of experience required for the first level.
	private final int startExp;
	public int getStartExp() { return this.startExp; }
	
	// Amount of extra required exp per level
	private final double multiplyPerLevel;
	public double getMultiplyPerLevel() { return this.multiplyPerLevel; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	private LvlStatusCalculatorExponential(int startExp, double multiplyPerLevel)
	{
		this.startExp = startExp;
		this.multiplyPerLevel = multiplyPerLevel;
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
			nextLvlExp *= multiplyPerLevel;
		}
		
		return LvlStatusDefault.valueOf(level, (int) exp, nextLvlExp);
	}
	
	// -------------------------------------------- //
	// FACTORY
	// -------------------------------------------- //

	public static LvlStatusCalculatorExponential valueOf(int startExp, double expPerLevel)
	{
		return new LvlStatusCalculatorExponential(startExp, expPerLevel);
	}
	
}
