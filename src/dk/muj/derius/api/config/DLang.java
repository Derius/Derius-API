package dk.muj.derius.api.config;

import dk.muj.derius.api.DeriusAPI;

public interface DLang
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	public static DLang get()
	{
		return DeriusAPI.getDLang();
	}
	
	public static void set(DLang dlang)
	{
		DeriusAPI.setDLang(dlang);
	}
	
	// -------------------------------------------- //
	// ASSORTED
	// -------------------------------------------- //
	
	public String getSkill();
	public void setSkill(String skill);
	
	public String getSkills();
	public void setSkills(String prefix);
	
	public String getYourSkills();
	public void setYourSkills(String yourSkills);
	
	public String getPrefix();
	public void setPrefix(String prefix);

	public String getLevelUp();
	public void setLevelUp(String levelUp);

	public String getExhausted();
	public void setExhausted(String exhausted);

	public String getLevelStatusFormat();
	public void setLevelStatusFormat(String levelStatusFormat);

	public String getLevelStatusFormatMini();
	public void setLevelStatusFormatMini(String levelStatusFormatMini);

	public String getToolPrepared();
	public void setToolPrepared(String toolPrepared);

	public String getToolNotPrepared();
	public void setToolNotPrepared(String toolNotPrepared);

	public String getMustBeGamemode();
	public void setMustBeGamemode(String mustBeGamemode);

	public String getMustNotBeGamemode();
	public void setMustNotBeGamemode(String mustNotBeGamemode);

	public String getMustHaveEnoughStamina();
	public void setMustHaveEnoughStamina(String mustHaveEnoughStamina);

	// -------------------------------------------- //
	// ABILITIES
	// -------------------------------------------- //
	
	public String getAbilityActivated();
	public void setAbilityActivated(String abilityActivated);

	public String getAbilityDeactivated();
	public void setAbilityDeactivated(String abilityDeactivated);

	public String getAbilityAreaIllegal();
	public void setAbilityAreaIllegal(String abilityAreaIllegal);

	public String getAbilityColorPlayerCanUse();
	public void setAbilityColorPlayerCanUse(String abilityColorPlayerCanUse);

	public String getAbilityColorPlayerCantUse();
	public void setAbilityColorPlayerCantUse(String abilityColorPlayerCantUse);

	public String getAbilityNoSuchId();
	public void setAbilityNoSuchId(String abilityInvalidId);

	public String getAbilityDisabled();
	public void setAbilityDisabled(String abilityDisabled);

	public String getAbilityDisplayedDescription();
	public void setAbilityDisplayedDescription(String abilityDisplayedDescription);

	// -------------------------------------------- //
	// SKILLS
	// -------------------------------------------- //
	
	public String getSkillDisplayedDescription();
	public void setSkillDIsplayedDescription(String skillDisplayedDesc);
	
	public String getSkillInfoExpGain();
	public void setSkillInfoExpGain(String skillInfoExpGain);

	public String getSkillInfoAbilities();
	public void setSkillInfoAbilities(String skillInfoAbilities);

	public String getSkillInfoLevelStats();
	public void setSkillInfoLevelStats(String skillInfoLevelStats);

	public String getSkillColorPlayerCanUse();
	public void setSkillColorPlayerCanUse(String skillColorPlayerCanUse);

	public String getSkillColorPlayerCantUse();
	public void setSkillColorPlayerCantUse(String skillColorPlayerCantUse);

	public String getSkillColorPlayerIsSpecialised();
	public void setSkillColorPlayerIsSpecialised(String skillColorPlayerIsSpecialised);

	public String getSkillLevelIsTooLow();
	public void setSkillLevelIsTooLow(String skillLevelIsTooLow);

	public String getSkillDisabled();
	public void setSkillDisabled(String skillDisabled);

	public String getSpecialisationAutoAssigned();
	public void setSpecialisationAutoAssigned(String specialisationAutoAssigned);

	public String getSpecialisationBlacklisted();
	public void setSpecialisationBlacklisted(String specialisationBlacklisted);

	public String getSpecialisationHasAlready();
	public void setSpecialisationHasAlready(String specialisationHasAlready);

	public String getSpecialisationTooMany();
	public void setSpecialisationTooMany(String specialisationTooMany);

	public String getSpecialisationSuccess();
	public void setSpecialisationSuccess(String specialisationSuccess);

	public String getSpecialisationRemoved();
	public void setSpecialisationRemoved(String specialisationRemoved);

	public String getSpecialisationIsnt();
	public void setSpecialisationIsnt(String specialisationIsnt);

	public String getSpecialisationCantChange();
	public void setSpecialisationCantChange(String specialisationCantChange);

	public String getSpecialisationMoveCooldown();
	public void setSpecialisationMoveCooldown(String specialisationMoveCooldown);

	public String getSpecialisationChangeCooldown();
	public void setSpecialisationChangeCooldown(String specialisationChangeCooldown);

	public String getSpecialisationInfo();
	public void setSpecialisationInfo(String specialisationInfo);

	// -------------------------------------------- //
	// CHAT KEYS
	// -------------------------------------------- //
	
	public String getKeyAddSuccess();
	public void setKeyAddSuccess(String keyAddSuccess);

	public String getKeyRemoveSuccess();
	public void setKeyRemoveSuccess(String keyRemoveSuccess);

	public String getKeyAlreadyHas();
	public void setKeyAlreadyHas(String keyAlreadyHas);

	public String getKeyHasnt();
	public void setKeyHasnt(String keyHanst);

	public String getKeysClearSuccess();
	public void setKeysClearSuccess(String keysClearSuccess);

	// -------------------------------------------- //
	// TITLE TIMES
	// -------------------------------------------- //
	
	public int getTimeLvlUpFadeIn();
	public void setTimeLvlUpFadeIn(int timeLvlUpFadeIn);

	public int getTimeLvlUpStay();
	public void setTimeLvlUpStay(int timeLvlUpStay);

	public int getTimeLvlUpFadeOut();
	public void setTimeLvlUpFadeOut(int timeLvlUpFadeOut);

	public int getTimeAbilityActivateFadeIn();
	public void setTimeAbilityActivateFadeIn(int timeAbilityActivateFadeIn);

	public int getTimeAbilityActivateStay();
	public void setTimeAbilityActivateStay(int timeAbilityActivateStay);

	public int getTimeAbilityActivateFadeOut();
	public void setTimeAbilityActivateFadeOut(int timeAbilityActivateFadeOut);

	public int getTimeAbilityDeactivateFadeIn();
	public void setTimeAbilityDeactivateFadeIn(int timeAbilityDeactivateFadeIn);

	public int getTimeAbilityDeactivateStay();
	public void setTimeAbilityDeactivateStay(int timeAbilityDeactivateStay);

	public int getTimeAbilityDeactivateFadeOut();
	public void setTimeAbilityDeactivateFadeOut(int timeAbilityDeactivateFadeOut);

}
