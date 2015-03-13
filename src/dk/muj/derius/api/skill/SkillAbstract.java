package dk.muj.derius.api.skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bukkit.Material;

import com.massivecraft.massivecore.collections.WorldExceptionSet;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivecore.xlib.gson.JsonElement;
import com.massivecraft.massivecore.xlib.gson.JsonObject;
import com.massivecraft.massivecore.xlib.gson.reflect.TypeToken;

import dk.muj.derius.api.DeriusAPI;
import dk.muj.derius.api.Req;
import dk.muj.derius.api.VerboseLevel;
import dk.muj.derius.api.ability.Ability;
import dk.muj.derius.api.config.DLang;
import dk.muj.derius.api.lvl.LvlStatus;
import dk.muj.derius.api.lvl.LvlStatusCalculator;
import dk.muj.derius.api.player.DPlayer;
import dk.muj.derius.api.util.SkillUtil;

public abstract class SkillAbstract implements Skill
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Enabled
	private boolean enabled = true;
	@Override public boolean isEnabled() { return enabled; }
	@Override public void setEnabled(boolean enabled) { this.enabled = enabled; }
	@Override public boolean getEnabled() { return this.enabled; }
	
	// Abilities
	private transient List<Ability> abilities = new CopyOnWriteArrayList<>();
	@Override public List<Ability> getAbilities() { return this.abilities; }
	
	// -------------------------------------------- //
	// FIELDS: DESCRIPTIVE
	// -------------------------------------------- //
	
	private String name;
	@Override public String getName() { return name; }
	@Override public void setName(String newName) { this.name = newName; }
	
	private String desc = "";
	@Override public String getDesc() { return desc; }
	@Override public void setDesc(String newDescription) { this.desc = newDescription; }
	
	private List<String> earnExpDescs = new CopyOnWriteArrayList<String>();
	@Override public List<String> getEarnExpDescs() { return new ArrayList<>(earnExpDescs); }
	@Override public void setEarnExpDescs(List<String> descs) { this.earnExpDescs = descs; }
	@Override public void addEarnExpDescs(String... desc) { this.earnExpDescs.addAll(Arrays.asList(desc)); }
	
	private Material icon = Material.AIR;
	@Override public Material getIcon() { return this.icon; }
	@Override public void setIcon(Material icon) { this.icon = icon; }
	
	// -------------------------------------------- //
	// FIELDS: CONFIGURITIVE
	// -------------------------------------------- //
	
	private int softCap = 1000;
	@Override public int getSoftCap() { return  this.softCap; }
	@Override public void setSoftCap(int cap) { this.softCap = cap; }
	
	private int hardCap = 2000;
	@Override public int getHardCap() { return  this.hardCap; }
	@Override public void setHardtCap(int cap) { this.hardCap = cap; }
	
	private boolean spAutoAssigned = false;
	@Override public boolean isSpAutoAssigned() { return this.spAutoAssigned; }
	@Override public void setSpAutoAssiged(boolean assigned) { this.spAutoAssigned = assigned; }
	
	private boolean spBlacklisted = false;
	@Override public boolean isSpBlackListed() { return this.spBlacklisted; }
	@Override public void setSpBlackListed(boolean assigned) { this.spAutoAssigned = assigned; }
	
	private WorldExceptionSet worldsEarn = new WorldExceptionSet();
	@Override public WorldExceptionSet getWorldsEarn() { return worldsEarn; }
	@Override public void setWorldsEarn(WorldExceptionSet worldsEarn) { this.worldsEarn = worldsEarn; }
	
	// Configuration
	private JsonObject configuration = new JsonObject();
	@Override public JsonObject getConfiguration() { return this.configuration; }
	@Override public void setConfiguration(JsonObject conf) { this.configuration = conf; }
	
	// -------------------------------------------- //
	// FIELDS: REQUIRING
	// -------------------------------------------- //
	
	// See requirements
	private transient List<Req> seeRequirements = new CopyOnWriteArrayList<Req>();
	@Override public List<Req> getSeeRequirements() { return this.seeRequirements; }
	@Override public void setSeeRequirements(List<Req> requirements) { this.seeRequirements = requirements; }
	@Override public void addSeeRequirements(Req... requirements) { this.seeRequirements.addAll(Arrays.asList(requirements)); }
	
	// Learn requirements
	private transient List<Req> learnRequirements = new CopyOnWriteArrayList<Req>();
	@Override public List<Req> getLearnRequirements() { return this.learnRequirements; }
	@Override public void setLearnRequirements(List<Req> requirements) { this.learnRequirements = requirements; }
	@Override public void addLearnRequirements(Req... requirements) { this.learnRequirements.addAll(Arrays.asList(requirements)); }
	
	// Specialise requirements
	private transient List<Req> specialiseRequirements = new CopyOnWriteArrayList<Req>();
	@Override public List<Req> getSpecialiseRequirements() { return this.specialiseRequirements; }
	@Override public void setSpecialiseRequirements(List<Req> requirements) { this.specialiseRequirements = requirements; }
	@Override public void addSpecialiseRequirements(Req... requirements) { this.specialiseRequirements.addAll(Arrays.asList(requirements)); }
	
	// -------------------------------------------- //
	// FIELDS: CALCULATIVE
	// -------------------------------------------- //
	
	// Lambda, This is the default algorithm
	private transient LvlStatusCalculator expToLvlStatus = LvlStatusCalculator.exponentialOf(1024, 1.01);
	@Override public final LvlStatus getLvlStatusFromExp(long exp) { return this.expToLvlStatus.calculateLvlStatus(exp); }
	@Override public final void setLvlStatusAlgorithm(LvlStatusCalculator algorithm) { this.expToLvlStatus = algorithm; }
	@Override public final LvlStatusCalculator getLvlStatusAlgorithm() { return this.expToLvlStatus; }
	
	// -------------------------------------------- //
	// OVERRIDE: ENTITY
	// -------------------------------------------- //
	
	@Override
	public Skill load(Skill that)
	{
		if (that == null || that == this) return that;
		
		this.setEnabled(that.getEnabled());
		this.setSpAutoAssiged(that.isSpAutoAssigned());
		this.setSpBlackListed(that.isSpBlackListed());
		
		if ( ! isNull(that.getName())) this.setName(that.getName());
		if ( ! isNull(that.getDesc())) this.setDesc(that.getDesc());
		if ( ! isNull(that.getEarnExpDescs())) this.setEarnExpDescs(that.getEarnExpDescs()); 
		if ( ! isNull(that.getIcon())) this.setIcon(that.getIcon()); 
		if ( ! isNull(that.getWorldsEarn())) this.setWorldsEarn(that.getWorldsEarn());
		
		// If one of them has keys the other doesn't, they are now merged.
		for(Entry<String, JsonElement> entry: that.getConfiguration().entrySet())
		{
			this.configuration.add(entry.getKey(), entry.getValue());
		}
		
		return this;
	}
	
	private boolean isNull(Object str)
	{
		return str == null || "null".equals(str) || "NULL".equals(str);
	}
	
	// -------------------------------------------- //
	// REGISTER
	// -------------------------------------------- //
	
	@Override
	public void register()
	{
		DeriusAPI.registerSkill(this);
	}
	
	@Override
	public boolean isRegistered()
	{
		return DeriusAPI.getAllSkills().contains(this);
	}
	
	// -------------------------------------------- //
	// CONFIGURATION
	// -------------------------------------------- //

	/**
	 * Reads a value from the skills custom configuration
	 * @param {String} name of the value
	 * @param {Class<T>} type of value
	 * @return {T} the value
	 */
	public <T> T readConfig(String field, Class<T> fieldType)
	{
		return DeriusAPI.getGson(this.getPlugin()).fromJson(this.configuration.get(field), fieldType);
	}
	
	/**
	 * Reads a value from the skills custom configuration
	 * @param {String} name of the value
	 * @param {TypeToken<T>} typetoken for generics
	 * @return {T} the value
	 */
	public <T> T readConfig(String field, TypeToken<T> typeToken)
	{
		return DeriusAPI.getGson(this.getPlugin()).fromJson(this.configuration.get(field), typeToken.getType());
	}
	
	/**
	 * Writes a value to the skills custom configuration
	 * @param {String} name of the value
	 * @param {Class<T>} type of value
	 * @return {T} the value
	 */
	public void writeConfig(String field, Object value)
	{
		this.configuration.add(field, DeriusAPI.getGson(this.getPlugin()).toJsonTree(value));
	}
	
	/**
	 * Writes a value to the skills custom configuration
	 * @param {String} name of the value
	 * @param {TypeToken<T>} typetoken for generics
	 * @return {T} the value
	 */
	public <T> void writeConfig(String field, Object value, TypeToken<T> typeToken)
	{
		this.configuration.add(field, DeriusAPI.getGson(this.getPlugin()).toJsonTree(value, typeToken.getType()));
	}
	
	
	// -------------------------------------------- //
	// DESCRIPTION
	// -------------------------------------------- //
	
	@Override 
	public String getDisplayedDescription(DPlayer dplayer)
	{
		String name = this.getDisplayName(dplayer);
		String format = DLang.get().getSkillDisplayedDescription();
		String ret = format
				.replace("{name}", name)
				.replace("{desc}", this.getDesc());
		return Txt.parse(ret);
	}
	
	@Override 
	public String getDisplayName (DPlayer dplayer)
	{
		if (dplayer.isSpecialisedIn(this))
		{
			return Txt.parse(DLang.get().getSkillColorPlayerIsSpecialised() + this.getName());
		}
		else if (SkillUtil.canPlayerLearnSkill(dplayer, this, VerboseLevel.ALWAYS))
		{
			return Txt.parse(DLang.get().getSkillColorPlayerCanUse() + this.getName());
		}
		else
		{
			return  Txt.parse(DLang.get().getSkillColorPlayerCantUse() + this.getName());
		}
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
		
		String id = this.getId();
		result += (id != null) ? id.hashCode() : 1;
		
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
