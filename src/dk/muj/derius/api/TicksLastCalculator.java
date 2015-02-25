package dk.muj.derius.api;

import java.util.function.Function;

@FunctionalInterface
public interface TicksLastCalculator extends Function<Integer, Integer>
{
	// -------------------------------------------- //
	// ABSTRACT
	// -------------------------------------------- //

	public int calcDuration(int level);
	
	// -------------------------------------------- //
	// DEFAULT
	// -------------------------------------------- //
	
	default Integer apply(Integer level)
	{
		return this.calcDuration(level);
	}
	
}
