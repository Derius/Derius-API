package dk.muj.derius.api.ability;

import java.util.LinkedHashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivecore.util.TimeDiffUtil;
import com.massivecraft.massivecore.util.TimeUnit;
import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.inventory.SpecialItemManager;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.req.ReqCooldownIsExpired;
import dk.muj.derius.api.util.AbilityUtil;

public abstract class AbilitySpecialItem extends AbilityAbstract
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	public AbilitySpecialItem()
	{
		this.setType(AbilityType.ACTIVE);
		this.addActivateRequirements(ReqCooldownIsExpired.get());
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public String getLvlDescriptionMsg(int lvl)
	{
		int millis = this.getDurationMillis(lvl);
		
		LinkedHashMap<TimeUnit, Long> unitcounts = TimeDiffUtil.limit(TimeDiffUtil.unitcounts(millis, TimeUnit.getAllButMillis()), 3);
		
		String entry = Txt.parse("<v>%1$d <k>%3$s");
		String comma = TimeDiffUtil.FORMAT_COMMA_VERBOOSE;
		String and = TimeDiffUtil.FORMAT_AND_VERBOOSE;
		String durationDesc = TimeDiffUtil.formated(unitcounts, entry, comma, and, "<yellow>");
		
		return "<i>Lasts " + durationDesc;
	}

	@Override
	public Object onActivate(DPlayer dplayer, Object other)
	{
		if ( ! dplayer.isPlayer()) return AbilityUtil.CANCEL;
		Player player = dplayer.getPlayer();
		ItemStack inHand = player.getItemInHand();
		if (inHand == null || inHand.getType() == Material.AIR) return AbilityUtil.CANCEL;
		
		this.getSpecialItemManager().toSpecial(inHand);
		
		player.updateInventory();
		return player.getItemInHand();
	}

	@Override
	public void onDeactivate(DPlayer dplayer, Object other)
	{
		if ( ! dplayer.isPlayer()) return;
		
		this.getSpecialItemManager().clearInventory(dplayer.getPlayer().getInventory());
		
		dplayer.getPlayer().updateInventory();
		
		return;
	}

	// -------------------------------------------- //
	// ABSTRACT
	// -------------------------------------------- //
	
	public abstract SpecialItemManager getSpecialItemManager();

}
