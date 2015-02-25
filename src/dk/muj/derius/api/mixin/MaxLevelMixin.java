package dk.muj.derius.api.mixin;

import dk.muj.derius.api.DPlayer;
import dk.muj.derius.api.Skill;

public interface MaxLevelMixin extends Mixin
{
	// Abstract
	public int getMaxLevel(DPlayer mplayer, Skill skill);

}
