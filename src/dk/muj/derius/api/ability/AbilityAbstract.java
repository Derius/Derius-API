package dk.muj.derius.api.ability;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.massivecraft.massivecore.collections.WorldExceptionSet;
import com.massivecraft.massivecore.util.TimeUnit;
import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.DeriusAPI;
import dk.muj.derius.api.MillisLastCalculator;
import dk.muj.derius.api.Req;
import dk.muj.derius.api.VerboseLevel;
import dk.muj.derius.api.config.DLang;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.util.AbilityUtil;

public abstract class AbilityAbstract implements Ability
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //

	private boolean enabled = true;
	public boolean isEnabled() { return enabled && this.getSkill().isEnabled(); }
	public void setEnabled(boolean enabled) { this.enabled = enabled; }
	public boolean getEnabled() { return this.enabled; }
	
	private String name;
	public String getName() { return name; }
	public void setName(String newName) { this.name = newName; }
	
	private String desc = "";
	public String getDesc() { return desc; }
	public void setDesc(String newDescription) { this.desc = newDescription; }
	
	private int millisCooldown = 1000*60*2;
	public void setCooldownMillis(int millis) { this.millisCooldown = millis; }
	public int getCooldownMillis() { return this.millisCooldown; }
	public boolean hasCooldown() { return this.getCooldownMillis() > 0; }
	
	private WorldExceptionSet worldsUse = new WorldExceptionSet();
	public WorldExceptionSet getWorldsUse() { return this.worldsUse; }
	public void setWorldsUse(WorldExceptionSet worldsUse) { this.worldsUse = worldsUse; }
	
	private double staminaUsage = 0.0;
	public void setStaminaUsage(double stamina) { this.staminaUsage = stamina; }
	public double getStaminaUsage() { return this.staminaUsage; }
	
	private double staminaMultiplier = 1.0;
	public void setStaminaMultiplier(double multiplier) { this.staminaMultiplier = multiplier; }
	public double getStaminaMultiplier() { return this.staminaMultiplier; }
	
	private transient AbilityType type;
	public AbilityType getType() { return this.type; }
	public void setType(AbilityType newType){ this.type = newType; }
	
	private transient List<Req> seeRequirements = new CopyOnWriteArrayList<Req>();
	public List<Req> getSeeRequirements() { return this.seeRequirements; }
	public void setSeeRequirements(List<Req> requirements) { this.seeRequirements = requirements; }
	public void addSeeRequirements(Req... requirements) { this.seeRequirements.addAll(Arrays.asList(requirements)); }
	
	private transient List<Req> activateRequirements = new CopyOnWriteArrayList<Req>();
	public List<Req> getActivateRequirements() { return this.activateRequirements; }
	public void setActivateRequirements(List<Req> requirements) { this.activateRequirements = requirements; }
	public void addActivateRequirements(Req... requirements) { this.activateRequirements.addAll(Arrays.asList(requirements)); }
	
	// Lambda
	private transient MillisLastCalculator levelToTicks = level -> (int) ((2 + level/50.0) * TimeUnit.MILLIS_PER_SECOND);
	public int getDurationMillis(int level) { return this.levelToTicks.calcDuration(level); }
	public final void setDurationAlgorithm(MillisLastCalculator algorithm){ this.levelToTicks = algorithm; }
	public final MillisLastCalculator getDurationAlgorithm(){ return this.levelToTicks; }
	
	// -------------------------------------------- //
	// OVERRIDE: ENTITY
	// -------------------------------------------- //
	
	@Override
	public Ability load(Ability that)
	{
		if (that == null || that == this) return that;
		
		this.setEnabled(that.getEnabled());
		this.setCooldownMillis(that.getCooldownMillis());
		this.setStaminaUsage(that.getStaminaUsage());
		this.setStaminaMultiplier(that.getStaminaMultiplier());
		
		if ( ! isNull(that.getName())) this.setName(that.getName());
		if ( ! isNull(that.getDesc())) this.setDesc(that.getDesc()); 
		if ( ! isNull(that.getWorldsUse())) this.setWorldsUse(that.getWorldsUse());
		
		return this;
	}
	
	private boolean isNull(Object str)
	{
		return str == null || "null".equals(str) || "NULL".equals(str);
	}
	
	// -------------------------------------------- //
	// REGISTER
	// -------------------------------------------- //
	
	/**
	 * Registers an ability to our system.
	 * This should be done on server startup.
	 */
	public void register()
	{
		DeriusAPI.registerAbility(this);
	}
	
	@Override
	public boolean isRegistered()
	{
		return DeriusAPI.getAllAbilities().contains(this);
	}
	
	// -------------------------------------------- //
	// DISPLAY
	// -------------------------------------------- //
	
	public String getDisplayedDescription(DPlayer dplayer)
	{
		String name = this.getDisplayName(dplayer);
		String format = DLang.get().getAbilityDisplayedDescription();
		String ret = format
				.replaceAll("{name}", name)
				.replaceAll("{desc}", this.getDesc());
		return Txt.parse(ret);
	}
	
	public String getDisplayName(DPlayer dplayer)
	{
		boolean can = AbilityUtil.canPlayerActivateAbility(dplayer, this, VerboseLevel.ALWAYS);
		String color = can ? DLang.get().getAbilityColorPlayerCanUse() : DLang.get().getAbilityColorPlayerCantUse();
		return color + this.getName();
	}

	// -------------------------------------------- //
	// EQUALS & HASH CODE
	// -------------------------------------------- //
	
	@Override
	public boolean equals(Object obj)
	{		
		return obj == this;
	}
	
	@Override
	public int hashCode()
	{
		int result = 1;
		
		result += (this.getId() != null) ? this.getId().hashCode() : 1;
		
		return result;
	}
	
	// -------------------------------------------- //
	// TO STRING
	// -------------------------------------------- //
	
	@Override
	public String toString()
	{
		return this.getName();
	}
	
}
