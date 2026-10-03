package com.lilithsthrone.game;

import static com.lilithsthrone.LolificationProject.ConfigStuff.Configurations.DRAKE_MODE;

/**
 * @since 0.2.2
 * @version 0.3.8.9
 * @author Innoxia
 */
public enum PropertyValue {
	
	debugMode(false),
	mapReveal(false),
	concealedSlotsReveal(false),
	allStickersUnlocked(false),
	
	enchantmentLimits(true),
	levelDrain(true),
	
	artwork(true),
	thumbnail(true),
	
	lightTheme(false),
	overwriteWarning(true),
	fadeInText(false),
	calendarDisplay(true),
	tattooRemovalConfirmations(true),
	sillyMode(false),
	weatherInterruptions(true),
	automaticDialogueCopy(false),

	autoLocale(true),
	metricSizes(true),
	metricFluids(true),
	metricWeights(true),
	twentyFourHourTime(true),
	internationalDate(true),

	autoSexStrip(false),
	autoSexClothingManagement(true),

	rapePlayAtSexStart(false),
	
	companionContent(false),

	badEndContent(true),
	ageContent(true),
	furryTailPenetrationContent(false),
	sadisticSexContent(true),
	inflationContent(true),

	lipstickMarkingContent(true),
	facialHairContent(true),
	pubicHairContent(true),
	bodyHairContent(true),
	assHairContent(false),
	feminineBeardsContent(false),
	furryHairContent(true),
	scalyHairContent(false),
	lipLispContent(false),
	
	nonConContent(true, true),
	incestContent(true, true),
	lactationContent(true, true),
	udderContent(true, true),
	urethralContent(false, true),
	analContent(true, true),
	footContent(true, true),
	armpitContent(true, true),
	nipplePenContent(true, true),
	gapeContent(true, true),
	feralContent(true, true), // No associated fetish yet
	muskContent(true, true), // No associated fetish yet
	
	cumRegenerationContent(true),
	penetrationLimitations(true),
	elasticityAffectDepth(true), // Added in PR#1413
	
	futanariTesticles(true),
	bipedalCloaca(true),
	vestigialMultiBreasts(true),
	
	// I know a lot of people hate NTR, but this 'voluntary NTR' setting is just giving the player the option to give their companion to other people, rather than having their companion taken against their will.
	// For this reason, it's on by default (to give the player more options), but involutary NTR will always be off by default.
	voluntaryNTR(true),
	involuntaryNTR(false),

	spittingEnabled(true),
	opportunisticAttackers(true),
	offspringEncounters(true),
	
	// Game properties:
	levelUpHightlight(false),
	sharedEncyclopedia(false),
	newWeaponDiscovered(false),
	newClothingDiscovered(false),
	newItemDiscovered(false),
	newRaceDiscovered(false),
	mapZoomedIn(true),



	peeContent(false, true),
	extremeAgeContent(false, true),
	enableHug(false),
	importedFlagAsModded(true),
	showAge(true),
	showTrueAge(true),
	showEnhancedDescription(DRAKE_MODE ? true : false),
	scaleHeightBasedOnAgeAppearance(true),
	scaleHeightBasedOnGenderOrAppearance(true),
	enableSexImages(false),
	extremeCaseCalculations(false),
	scaleBodyBasedOnApparentAge(true),
	changeBody(true),
	changeBreasts(true),
	changeNipples(true),
	changeHips(true),
	changeVagina(true),
	changePenis(true),
	changeTesticle(true),
	changeThroat(true),
	changeAss(true),
	changeAnus(true),
	changeUrethra(true),
	ageProgression(false),
	equipUniques(DRAKE_MODE ? true : false), // CURRENTLY ONLY WORKS IN EQUIPMENT SHOP DIALOGUES
	enslaveLumi(false),
	combatLeakage(true),
	forceClean(false),
	enableSlovenly(DRAKE_MODE ? false : true),
	enablePiercings(true),
	enableTattoos(true),
	enableMakeup(true),
	enableFrenchKiss(true),
	enableTussyContent(false),
    enableExtraSelfContent(true),
	enableEnforcers(DRAKE_MODE ? false : true),
	forceOrgasmicDrain(false),
	enablePersonality(true),
	applyMoneyMultToIncome(false),
	applyPrefToOffspring(false),
	offspringLevelParent(false),
	excludeAmorphous(true),
	impPreferences(false),
	minimumAgeSpeech(true),
	originalAge(true),
	lootTheMuseum(DRAKE_MODE ? true : false),
	wakeAffectionLoss(DRAKE_MODE ? false : true),
	vanillaJobConditions(false),
	enablePentUp(DRAKE_MODE ? false : true),
	nerfedAffObed(false),
	relativeHeight(false),
	kinMode(false),
	preventGiants(true),
	changeApparentAge(false),
	lexiMenuOptions(false);

	private boolean defaultValue;
	private boolean fetishRelated;

	private PropertyValue(boolean defaultValue) {
		this(defaultValue, false);
	}
	
	private PropertyValue(boolean defaultValue, boolean fetishRelated) {
		this.defaultValue = defaultValue;
		this.fetishRelated = fetishRelated;
	}
	
	public boolean getDefaultValue() {
		return defaultValue;
	}
	
	public boolean isFetishRelated() {
		return fetishRelated;
	}
}
