package dk.muj.derius.api.mixin;

import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.skill.Skill;

public interface MaxLevelMixin
{
	// Abstract
	public int getMaxLevel(DPlayer mplayer, Skill skill);

}
