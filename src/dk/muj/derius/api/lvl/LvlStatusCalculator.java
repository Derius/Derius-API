package dk.muj.derius.api.lvl;

import java.util.function.Function;

@FunctionalInterface
public interface LvlStatusCalculator extends Function<Long, LvlStatus>
{
	// -------------------------------------------- //
	// ABSTRACT
	// -------------------------------------------- //

	public LvlStatus calculateLvlStatus(long exp);
	
	// -------------------------------------------- //
	// DEFAULT
	// -------------------------------------------- //
	
	default LvlStatus apply(Long exp)
	{
		return this.calculateLvlStatus(exp);
	}
	
	// -------------------------------------------- //
	// FACTORY
	// -------------------------------------------- //
	
	public static LvlStatusCalculator exponentialOf(int startExp, double multiplyPerLeve)
	{
		return LvlStatusCalculatorExponential.valueOf(startExp, multiplyPerLeve);
	}
	
	public static LvlStatusCalculator linearOf(int startExp, int addPerLevel)
	{
		return LvlStatusCalculatorLinear.valueOf(startExp, addPerLevel);
	}
	
}
