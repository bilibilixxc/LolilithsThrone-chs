package com.lilithsthrone.game.character.body;

import com.lilithsthrone.LolificationProject.Body.ModBody;
import com.lilithsthrone.LolificationProject.Lexi.character.body.OrificeTussy;
import com.lilithsthrone.LolificationProject.ModCommon.Conversions;
import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTongueType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringSkinToneColorHelper;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodyShape;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.FluidTypeBase;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.StartingSkinTone;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.pregnancy.Litter;
import com.lilithsthrone.game.character.pregnancy.PregnancyPossibility;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractRacialBody;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FeralAttributes;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Builder;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @since 0.1.0
 * @version 0.4.0
 * @author Innoxia
 */
@SuppressWarnings("all")
public class Body extends ModBody implements XMLSaving {
	
	/** This determines the maximum amount of fluid (in mL) that can be stored in the SexAreaOrifice.VAGINA and SexAreaOrifice.URETHRA_VAGINA while pregnant. */
	public static final int MAXIMUM_CREAMPIE_WHILE_PREGNANT = 250;
	
	// Required:
	public Arm arm;
	public Ass ass;
	public Breast breast;
	public Face face;
	public Eye eye;
	public Ear ear;
	public Hair hair;
	public Leg leg;
	public Torso torso;
	public BodyMaterial bodyMaterial;
	public AbstractSubspecies fleshSubspecies;

	// Optional:
	public Antenna antenna;
	public BreastCrotch breastCrotch;
	public Horn horn;
	public Penis penis;
	public Tail tail;
	public Tentacle tentacle;
	public Vagina vagina;
	public Wing wing;

	public OrificeSpinneret spinneret;

	public GenitalArrangement genitalArrangement;
	
	public boolean feral;
	
	public Map<AbstractRace, Integer> raceWeightMap = new ConcurrentHashMap<>();
	public AbstractSubspecies subspecies;
	/** This keeps track of what this body's subspecies was at the moment of last being saved. it's only used in BodyChanging.java as part of save/load transformation presets. */
	public AbstractSubspecies loadedSubspecies;
	public RaceStage raceStage;
	public boolean piercedStomach = false;
	public AbstractSubspecies subspeciesOverride = null;
	public AbstractSubspecies halfDemonSubspecies = null;
	public int height;
	public int femininity;
	public int bodySize;
	public int muscle;
	public BodyHair pubicHair;

	public Set<AbstractBodyCoveringType> heavyMakeup;
	public Map<AbstractBodyCoveringType, Covering> coverings;
	public Set<AbstractBodyCoveringType> coveringsDiscovered;

	public List<BodyPartInterface> allBodyParts;
	public List<BodyPartInterface> allBodyPartsExtended;
	
	public boolean takesAfterMother = true;
	
	
	public static class BodyBuilder implements Builder<Body> {
		// Required parameters:
		public final Arm arm;
		public final Ass ass;
		public final Breast breast;
		public final Face face;
		public final Eye eye;
		public final Ear ear;
		public final Hair hair;
		public final Leg leg;
		public final Torso torso;
		public final BodyMaterial bodyMaterial;
		public GenitalArrangement genitalArrangement;
		public final int height;
		public final int femininity, bodySize, muscle;
		
		// Optional parameters - initialised to null values:
		public Antenna antenna = new Antenna(AntennaType.NONE, 0);
		public BreastCrotch breastCrotch = new BreastCrotch(BreastType.NONE, BreastShape.ROUND, 0, 0, 1, 1, NippleShape.NORMAL, 1, AreolaeShape.NORMAL, 1, 0, 2, 0, 0,
 true);
		public Horn horn = new Horn(HornType.NONE, 0);
		public Penis penis = new Penis(PenisType.NONE, 0, false, 0, 0, 0, 0);
		public Tail tail = new Tail(TailType.NONE);
		public Tentacle tentacle = new Tentacle(TentacleType.NONE);
		public Vagina vagina = new Vagina(VaginaType.NONE, 0, 0, 0, 0, 0, 2, 3, 3, true);
		public Wing wing = new Wing(WingType.NONE, 0);
		public OrificeSpinneret spinneret = new OrificeSpinneret();
		public OrificeTussy tussy = new OrificeTussy();

		public BodyBuilder(Arm arm, Ass ass, Breast breast, Face face, Eye eye, Ear ear, Hair hair, Leg leg, Torso torso, BodyMaterial bodyMaterial, GenitalArrangement genitalArrangement, int height, int femininity, int bodySize, int muscle) {
			this.arm = arm;
			this.ass = ass;
			this.breast = breast;
			this.face = face;
			this.eye = eye;
			this.ear = ear;
			this.hair = hair;
			this.leg = leg;
			this.torso = torso;
			this.bodyMaterial = bodyMaterial;
			this.genitalArrangement = genitalArrangement;
			this.height = height;
			this.femininity = femininity;
			this.bodySize = bodySize;
			this.muscle = muscle;
		}
		
		public BodyBuilder antenna(Antenna antenna) {
			this.antenna = antenna;
			return this;
		}
		
		public BodyBuilder breastCrotch(BreastCrotch breastCrotch) {
			this.breastCrotch = breastCrotch;
			return this;
		}

		public BodyBuilder horn(Horn horn) {
			this.horn = horn;
			return this;
		}

		public BodyBuilder penis(Penis penis) {
			this.penis = penis;
			return this;
		}
		
		public BodyBuilder tail(Tail tail) {
			this.tail = tail;
			return this;
		}

		public BodyBuilder tentacle(Tentacle tentacle) {
			this.tentacle = tentacle;
			return this;
		}

		public BodyBuilder vagina(Vagina vagina) {
			this.vagina = vagina;
			return this;
		}

		public BodyBuilder wing(Wing wing) {
			this.wing = wing;
			return this;
		}

		public BodyBuilder spinneret(OrificeSpinneret spinneret) {
			this.spinneret = spinneret;
			return this;
		}

		public BodyBuilder tussy(OrificeTussy tussy) {
			this.tussy = tussy;
			return this;
		}

		@Override
		public Body build() {
			return new Body(this);
		}
	}

	private Body(BodyBuilder builder) {
		antenna = builder.antenna;
		arm = builder.arm;
		ass = builder.ass;
		breast = builder.breast;
		breastCrotch = builder.breastCrotch;
		face = builder.face;
		eye = builder.eye;
		ear = builder.ear;
		hair = builder.hair;
		leg = builder.leg;
		torso = builder.torso;
		horn = builder.horn;
		penis = builder.penis;
		tail = builder.tail;
		tentacle = builder.tentacle;
		vagina = builder.vagina;
		wing = builder.wing;
		
		spinneret = builder.spinneret;
		tussy = builder.tussy;
		
		bodyMaterial = builder.bodyMaterial;
		genitalArrangement = builder.genitalArrangement;
		
		feral = false;
		
		height = builder.height;
		femininity = builder.femininity;
		bodySize = builder.bodySize;
		muscle = builder.muscle;
		
		this.pubicHair = BodyHair.ZERO_NONE;
		
		handleAllBodyPartsList();
		
		coverings = new HashMap<>();
		heavyMakeup = new HashSet<>();

		applyStartingCoveringValues();
		
		coveringsDiscovered = new HashSet<>();
		for(BodyPartInterface bp : allBodyPartsExtended) {
			if(bp.getBodyCoveringType(this)!=null) {
				coveringsDiscovered.add(bp.getBodyCoveringType(this));
			}
		}
		
		addDiscoveredBodyCoveringsFromMaterial(bodyMaterial);
		
		calculateRace(null);
		
		coveringsDiscovered.add(getBodyHairCoveringType(this.getRace()));
	}
	
	public Body(Body bodyToCopy) {
		// Core:
		this.genitalArrangement = bodyToCopy.getGenitalArrangement();
		this.feral = bodyToCopy.isFeral();
		this.subspecies = bodyToCopy.getSubspecies();
		this.piercedStomach = bodyToCopy.isPiercedStomach();
		if(bodyToCopy.getSubspeciesOverride()!=null) {
			this.subspeciesOverride = bodyToCopy.getSubspeciesOverride();
		}
		this.height = bodyToCopy.getHeightValue();
		this.femininity = bodyToCopy.getFemininity();
		this.bodySize = bodyToCopy.getBodySize();
		this.muscle = bodyToCopy.getMuscle();
		this.pubicHair = bodyToCopy.getPubicHair();
		this.bodyMaterial = bodyToCopy.getBodyMaterial();
		this.takesAfterMother = bodyToCopy.isTakesAfterMother();
		
		// Coverings:
		
		this.coverings = new HashMap<>();
		for(Entry<AbstractBodyCoveringType, Covering> entry : bodyToCopy.coverings.entrySet()) {
			coverings.put(entry.getKey(), new Covering(entry.getValue()));
		}
		this.coveringsDiscovered = new HashSet<>(bodyToCopy.coveringsDiscovered);
		
		this.heavyMakeup = new HashSet<>(bodyToCopy.heavyMakeup);
		
		// Body parts:
		
		this.antenna = new Antenna(bodyToCopy.antenna);
		this.arm = new Arm(bodyToCopy.arm);
		this.ass = new Ass(bodyToCopy.ass);
		this.breast = new Breast(bodyToCopy.breast);
		this.breastCrotch = new BreastCrotch(bodyToCopy.breastCrotch);
		this.ear = new Ear(bodyToCopy.ear);
		this.eye = new Eye(bodyToCopy.eye);
		this.face = new Face(bodyToCopy.face);
		this.hair = new Hair(bodyToCopy.hair);
		this.horn = new Horn(bodyToCopy.horn);
		this.leg = new Leg(bodyToCopy.leg);
		this.penis = new Penis(bodyToCopy.penis);
		this.spinneret = new OrificeSpinneret(bodyToCopy.spinneret);
		this.torso = new Torso(bodyToCopy.torso);
		this.tail = new Tail(bodyToCopy.tail);
		this.tentacle = new Tentacle(bodyToCopy.tentacle);
		this.vagina = new Vagina(bodyToCopy.vagina);
		this.wing = new Wing(bodyToCopy.wing);

		handleAllBodyPartsList();
		calculateRace(null);
	}
	
	public void handleAllBodyPartsList() {
		allBodyParts = new ArrayList<>();
		allBodyParts.add(antenna);
		allBodyParts.add(arm);
		allBodyParts.add(ass);
		allBodyParts.add(breast);
		allBodyParts.add(breastCrotch);
		allBodyParts.add(face);
		allBodyParts.add(eye);
		allBodyParts.add(ear);
		allBodyParts.add(hair);
		allBodyParts.add(leg);
		allBodyParts.add(torso);
		allBodyParts.add(horn);
		allBodyParts.add(penis);
		allBodyParts.add(tail);
		allBodyParts.add(tentacle);
		allBodyParts.add(vagina);
		allBodyParts.add(wing);

		allBodyPartsExtended = new ArrayList<>();
		allBodyPartsExtended.addAll(allBodyParts);
		allBodyPartsExtended.add(ass.getAnus());
		allBodyPartsExtended.add(breast.getNipples());
		allBodyPartsExtended.add(breastCrotch.getNipples());
		allBodyPartsExtended.add(face.getMouth());
		allBodyPartsExtended.add(face.getTongue());
	}
	
	public void addDiscoveredBodyCoveringsFromMaterial(BodyMaterial bodyMaterial) {
		if(bodyMaterial!=BodyMaterial.FLESH) {
			for(BodyCoveringCategory cat : BodyCoveringCategory.values()) {
				if(cat.isInfluencedByMaterialType()) {
					coveringsDiscovered.add(BodyCoveringType.getMaterialBodyCoveringType(bodyMaterial, cat));
				}
			}
			
		} else {
			coveringsDiscovered.add(BodyCoveringType.EYE_SCLERA);
			coveringsDiscovered.add(BodyCoveringType.NIPPLES);
			coveringsDiscovered.add(BodyCoveringType.NIPPLES_CROTCH);
			coveringsDiscovered.add(BodyCoveringType.TONGUE);
			coveringsDiscovered.add(BodyCoveringType.MOUTH);
			coveringsDiscovered.add(BodyCoveringType.ANUS);
			coveringsDiscovered.add(BodyCoveringType.VAGINA);
			coveringsDiscovered.add(BodyCoveringType.PENIS);
			coveringsDiscovered.add(BodyCoveringType.SPINNERET);
		}
	}
	
	public static AbstractBodyCoveringType getBodyHairCoveringType(AbstractRace race) {
		return race.getRacialBody().getBodyHairType();
	}
	
	private void applyStartingCoveringValues() {
		
		// Everything is based on human skin value:
		StartingSkinTone tone = StartingSkinTone.values()[Util.random.nextInt(StartingSkinTone.values().length)];
		
		for(AbstractBodyCoveringType s : BodyCoveringType.getAllBodyCoveringTypes()) {
			// Specials:
			// orifice exterior/interior
			// makeup
			if(s == BodyCoveringType.MAKEUP_BLUSHER
					|| s == BodyCoveringType.MAKEUP_EYE_LINER
					|| s == BodyCoveringType.MAKEUP_EYE_SHADOW
					|| s == BodyCoveringType.MAKEUP_LIPSTICK
					|| s == BodyCoveringType.MAKEUP_NAIL_POLISH_FEET
					|| s == BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS) {
				coverings.put(s, new Covering(s,
						CoveringPattern.NONE,
						PresetColour.COVERING_NONE, false,
						PresetColour.COVERING_NONE, false));
				continue;
			}
			
			List<Colour> colourApplicationList = BodyCoveringSkinToneColorHelper.getAcceptableColoursForPrimary(tone, s);
			Colour primary = colourApplicationList.get(Util.random.nextInt(colourApplicationList.size()));
			
			Colour secondary = primary;
			
			if(!s.getNaturalColoursSecondary().isEmpty()) {
				colourApplicationList = new ArrayList<>(BodyCoveringSkinToneColorHelper.getAcceptableColoursForSecondary(tone, s));
				colourApplicationList.remove(primary);
				if(!colourApplicationList.isEmpty()) {
					secondary = colourApplicationList.get(Util.random.nextInt(colourApplicationList.size()));
				}
			}
			
//			List<CoveringPattern> availablePatterns = new ArrayList<>(s.getNaturalPatterns().keySet());
//			if(availablePatterns.size()>1) {
//				availablePatterns.remove(CoveringPattern.FRECKLED); // Do not start with freckles
//			}
			
			CoveringPattern pattern = Util.getRandomObjectFromWeightedMap(s.getNaturalPatterns());//availablePatterns.get(Util.random.nextInt(availablePatterns.size()));
			if(pattern==CoveringPattern.EYE_IRISES) {
				if (Math.random() > ModProps.heterochromiaChance / 100f)
					pattern = CoveringPattern.EYE_IRISES_HETEROCHROMATIC;
			}
			if(pattern == CoveringPattern.EYE_IRISES_HETEROCHROMATIC) {
				if(primary == secondary) {
					List<Colour> secondaryIrisColours = new ArrayList<>(colourApplicationList);
					secondaryIrisColours.remove(primary);
					if(secondaryIrisColours.isEmpty()) {
						pattern = CoveringPattern.EYE_IRISES;
						secondary = primary;
					} else {
						secondary = Util.randomItemFrom(secondaryIrisColours);
					}
				}
			}
			
			coverings.put(s, new Covering(s,
					pattern,
					primary, false,
					secondary, false));
		}

		updateCoverings(true, true, true, true);
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		// Core:
		Element bodyCore = doc.createElement("bodyCore");
		parentElement.appendChild(bodyCore);
		XMLUtil.addAttribute(doc, bodyCore, "version", Main.VERSION_NUMBER);
		XMLUtil.addAttribute(doc, bodyCore, "piercedStomach", String.valueOf(this.isPiercedStomach()));
		XMLUtil.addAttribute(doc, bodyCore, "height", String.valueOf(this.getHeightValue()));
		XMLUtil.addAttribute(doc, bodyCore, "femininity", String.valueOf(this.getFemininity()));
		XMLUtil.addAttribute(doc, bodyCore, "bodySize", String.valueOf(this.getBodySize()));
		XMLUtil.addAttribute(doc, bodyCore, "muscle", String.valueOf(this.getMuscle()));
		XMLUtil.addAttribute(doc, bodyCore, "pubicHair", String.valueOf(this.getPubicHair()));
		XMLUtil.addAttribute(doc, bodyCore, "bodyMaterial", String.valueOf(this.getBodyMaterial()));
		XMLUtil.addAttribute(doc, bodyCore, "genitalArrangement", String.valueOf(this.getGenitalArrangement()));
		XMLUtil.addAttribute(doc, bodyCore, "feral", String.valueOf(this.isFeral()));
		if(this.getSubspeciesOverride()!=null) {
			XMLUtil.addAttribute(doc, bodyCore, "subspeciesOverride", Subspecies.getIdFromSubspecies(this.getSubspeciesOverride()));
		}
		XMLUtil.addAttribute(doc, bodyCore, "subspecies", Subspecies.getIdFromSubspecies(this.getSubspecies()));
		XMLUtil.addAttribute(doc, bodyCore, "takesAfterMother", String.valueOf(this.isTakesAfterMother()));
		
		for(AbstractBodyCoveringType bct : BodyCoveringType.getAllBodyCoveringTypes()) {
			Covering covering = this.coverings.get(bct);
			if(this.isBodyCoveringTypesDiscovered(bct)
					|| ((bct == BodyCoveringType.MAKEUP_BLUSHER
							|| bct == BodyCoveringType.MAKEUP_EYE_LINER
							|| bct == BodyCoveringType.MAKEUP_EYE_SHADOW
							|| bct == BodyCoveringType.MAKEUP_LIPSTICK
							|| bct == BodyCoveringType.MAKEUP_NAIL_POLISH_FEET
							|| bct == BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS)
							&& covering.getPrimaryColour()!=PresetColour.COVERING_NONE)
					|| bct == BodyCoveringType.EYE_PUPILS
					|| bct.getCategory()==BodyCoveringCategory.FLUID
					|| bct == getBodyHairCoveringType(this.getRace())) {
				Element element = doc.createElement("bodyCovering");
				bodyCore.appendChild(element);
				
				XMLUtil.addAttribute(doc, element, "type", BodyCoveringType.getIdFromBodyCoveringType(bct));
				XMLUtil.addAttribute(doc, element, "pattern", covering.getPattern().toString());
				XMLUtil.addAttribute(doc, element, "modifier", covering.getModifier().toString());
				XMLUtil.addAttribute(doc, element, "c1", covering.getPrimaryColour().getId());
				if(covering.isPrimaryGlowing()) {
					XMLUtil.addAttribute(doc, element, "g1", String.valueOf(covering.isPrimaryGlowing()));
				}
				if(covering.getPrimaryColour()!=covering.getSecondaryColour()) {
					XMLUtil.addAttribute(doc, element, "c2", covering.getSecondaryColour().getId());
				}
				if(covering.isSecondaryGlowing()) {
					XMLUtil.addAttribute(doc, element, "g2", String.valueOf(covering.isSecondaryGlowing()));
				}
			}
		}
		
		if(!heavyMakeup.isEmpty()) {
			Element element = doc.createElement("heavyMakeup");
			bodyCore.appendChild(element);
			for(AbstractBodyCoveringType bct : heavyMakeup) {
				Element bctElement = doc.createElement("type");
				element.appendChild(bctElement);
				bctElement.setTextContent(BodyCoveringType.getIdFromBodyCoveringType(bct));
			}
		}
		

		// Antennae:
		Element bodyAntennae = doc.createElement("antennae");
		parentElement.appendChild(bodyAntennae);
			XMLUtil.addAttribute(doc, bodyAntennae, "type", AntennaType.getIdFromAntennaType(this.antenna.getType()));
			XMLUtil.addAttribute(doc, bodyAntennae, "rows", String.valueOf(this.antenna.getAntennaRows()));
			XMLUtil.addAttribute(doc, bodyAntennae, "length", String.valueOf(this.antenna.length));
			XMLUtil.addAttribute(doc, bodyAntennae, "antennaePerRow", String.valueOf(this.antenna.antennaePerRow));
		
		// Arm:
		Element bodyArm = doc.createElement("arm");
		parentElement.appendChild(bodyArm);
			XMLUtil.addAttribute(doc, bodyArm, "type", ArmType.getIdFromArmType(this.arm.getType()));
			XMLUtil.addAttribute(doc, bodyArm, "rows", String.valueOf(this.arm.getArmRows()));
			XMLUtil.addAttribute(doc, bodyArm, "underarmHair", this.arm.getUnderarmHair().toString());
		
		// Ass:
		Element bodyAss = doc.createElement("ass");
		parentElement.appendChild(bodyAss);
			XMLUtil.addAttribute(doc, bodyAss, "type", AssType.getIdFromAssType(this.ass.getType()));
			XMLUtil.addAttribute(doc, bodyAss, "assSize", String.valueOf(this.ass.getAssSize().getValue()));
			XMLUtil.addAttribute(doc, bodyAss, "hipSize", String.valueOf(this.ass.getHipSize().getValue()));

		Element bodyAnus = doc.createElement("anus");
		parentElement.appendChild(bodyAnus);
			XMLUtil.addAttribute(doc, bodyAnus, "wetness", String.valueOf(this.ass.anus.orificeAnus.wetness));
			XMLUtil.addAttribute(doc, bodyAnus, "depth", String.valueOf(this.ass.anus.orificeAnus.depth));
			XMLUtil.addAttribute(doc, bodyAnus, "elasticity", String.valueOf(this.ass.anus.orificeAnus.elasticity));
			XMLUtil.addAttribute(doc, bodyAnus, "plasticity", String.valueOf(this.ass.anus.orificeAnus.plasticity));
			XMLUtil.addAttribute(doc, bodyAnus, "capacity", String.valueOf(this.ass.anus.orificeAnus.capacity));
			XMLUtil.addAttribute(doc, bodyAnus, "stretchedCapacity", String.valueOf(this.ass.anus.orificeAnus.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyAnus, "virgin", String.valueOf(this.ass.anus.orificeAnus.virgin));
			XMLUtil.addAttribute(doc, bodyAnus, "bleached", String.valueOf(this.ass.anus.bleached));
			XMLUtil.addAttribute(doc, bodyAnus, "assHair", this.ass.anus.assHair.toString());
			for(OrificeModifier om : this.ass.anus.orificeAnus.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyAnus.appendChild(mod);
			}
		
		// Breasts:
		Element bodyBreast = doc.createElement("breasts");
		parentElement.appendChild(bodyBreast);
			XMLUtil.addAttribute(doc, bodyBreast, "type", BreastType.getIdFromBreastType(this.breast.getType()));
			XMLUtil.addAttribute(doc, bodyBreast, "shape", this.breast.shape.toString());
			XMLUtil.addAttribute(doc, bodyBreast, "size", String.valueOf(this.breast.size));
			XMLUtil.addAttribute(doc, bodyBreast, "rows", String.valueOf(this.breast.rows));
			XMLUtil.addAttribute(doc, bodyBreast, "milkStorage", String.valueOf(this.breast.milkStorage));
			XMLUtil.addAttribute(doc, bodyBreast, "storedMilk", String.valueOf(this.breast.milkStored));
			XMLUtil.addAttribute(doc, bodyBreast, "milkRegeneration", String.valueOf(this.breast.milkRegeneration));
			XMLUtil.addAttribute(doc, bodyBreast, "nippleCountPerBreast", String.valueOf(this.breast.nippleCountPerBreast));

		Element bodyNipple = doc.createElement("nipples");
		parentElement.appendChild(bodyNipple);
			XMLUtil.addAttribute(doc, bodyNipple, "depth", String.valueOf(this.breast.nipples.orificeNipples.depth));
			XMLUtil.addAttribute(doc, bodyNipple, "elasticity", String.valueOf(this.breast.nipples.orificeNipples.elasticity));
			XMLUtil.addAttribute(doc, bodyNipple, "plasticity", String.valueOf(this.breast.nipples.orificeNipples.plasticity));
			XMLUtil.addAttribute(doc, bodyNipple, "capacity", String.valueOf(this.breast.nipples.orificeNipples.capacity));
			XMLUtil.addAttribute(doc, bodyNipple, "stretchedCapacity", String.valueOf(this.breast.nipples.orificeNipples.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyNipple, "virgin", String.valueOf(this.breast.nipples.orificeNipples.virgin));
			XMLUtil.addAttribute(doc, bodyNipple, "pierced", String.valueOf(this.breast.nipples.pierced));
			XMLUtil.addAttribute(doc, bodyNipple, "nippleSize", String.valueOf(this.breast.nipples.nippleSize));
			XMLUtil.addAttribute(doc, bodyNipple, "nippleShape", this.breast.nipples.nippleShape.toString());
			XMLUtil.addAttribute(doc, bodyNipple, "areolaeSize", String.valueOf(this.breast.nipples.areolaeSize));
			XMLUtil.addAttribute(doc, bodyNipple, "areolaeShape", this.breast.nipples.areolaeShape.toString());
			for(OrificeModifier om : this.breast.nipples.orificeNipples.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyNipple.appendChild(mod);
			}
			
		this.breast.milk.saveAsXML("milk", parentElement, doc);
		
		// Crotch Breasts:
		Element bodyCrotchBreast = doc.createElement("breastsCrotch");
		parentElement.appendChild(bodyCrotchBreast);
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "type", BreastType.getIdFromBreastType(this.breastCrotch.getType()));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "shape", this.breastCrotch.shape.toString());
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "size", String.valueOf(this.breastCrotch.size));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "rows", String.valueOf(this.breastCrotch.rows));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "milkStorage", String.valueOf(this.breastCrotch.milkStorage));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "storedMilk", String.valueOf(this.breastCrotch.milkStored));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "milkRegeneration", String.valueOf(this.breastCrotch.milkRegeneration));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "nippleCountPerBreast", String.valueOf(this.breastCrotch.nippleCountPerBreast));

		Element bodyCrotchNipple = doc.createElement("nipplesCrotch");
		parentElement.appendChild(bodyCrotchNipple);
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "depth", String.valueOf(this.breastCrotch.nipples.orificeNipples.depth));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "elasticity", String.valueOf(this.breastCrotch.nipples.orificeNipples.elasticity));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "plasticity", String.valueOf(this.breastCrotch.nipples.orificeNipples.plasticity));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "capacity", String.valueOf(this.breastCrotch.nipples.orificeNipples.capacity));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "stretchedCapacity", String.valueOf(this.breastCrotch.nipples.orificeNipples.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "virgin", String.valueOf(this.breastCrotch.nipples.orificeNipples.virgin));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "pierced", String.valueOf(this.breastCrotch.nipples.pierced));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "nippleSize", String.valueOf(this.breastCrotch.nipples.nippleSize));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "nippleShape", this.breastCrotch.nipples.nippleShape.toString());
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "areolaeSize", String.valueOf(this.breastCrotch.nipples.areolaeSize));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "areolaeShape", this.breastCrotch.nipples.areolaeShape.toString());
			for(OrificeModifier om : this.breastCrotch.nipples.orificeNipples.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyCrotchNipple.appendChild(mod);
			}
			
		this.breastCrotch.milk.saveAsXML("milkCrotch", parentElement, doc);
		
		
		// Ear:
		Element bodyEar = doc.createElement("ear");
		parentElement.appendChild(bodyEar);
			XMLUtil.addAttribute(doc, bodyEar, "type", EarType.getIdFromEarType(this.ear.type));
			XMLUtil.addAttribute(doc, bodyEar, "pierced", String.valueOf(this.ear.pierced));

		// Eye:
		Element bodyEye = doc.createElement("eye");
		parentElement.appendChild(bodyEye);
			XMLUtil.addAttribute(doc, bodyEye, "type", EyeType.getIdFromEyeType(this.eye.type));
			XMLUtil.addAttribute(doc, bodyEye, "eyePairs", String.valueOf(this.eye.eyePairs));
			XMLUtil.addAttribute(doc, bodyEye, "irisShape", this.eye.irisShape.toString());
			XMLUtil.addAttribute(doc, bodyEye, "pupilShape", this.eye.pupilShape.toString());
		
		// Face:
		Element bodyFace = doc.createElement("face");
		parentElement.appendChild(bodyFace);
			XMLUtil.addAttribute(doc, bodyFace, "type", FaceType.getIdFromFaceType(this.face.type));
			XMLUtil.addAttribute(doc, bodyFace, "piercedNose", String.valueOf(this.face.piercedNose));
			XMLUtil.addAttribute(doc, bodyFace, "facialHair", this.face.facialHair.toString());

		Element bodyMouth = doc.createElement("mouth");
		parentElement.appendChild(bodyMouth);
			XMLUtil.addAttribute(doc, bodyMouth, "depth", String.valueOf(this.face.mouth.orificeMouth.depth));
			XMLUtil.addAttribute(doc, bodyMouth, "elasticity", String.valueOf(this.face.mouth.orificeMouth.elasticity));
			XMLUtil.addAttribute(doc, bodyMouth, "plasticity", String.valueOf(this.face.mouth.orificeMouth.plasticity));
			XMLUtil.addAttribute(doc, bodyMouth, "capacity", String.valueOf(this.face.mouth.orificeMouth.capacity));
			XMLUtil.addAttribute(doc, bodyMouth, "wetness", String.valueOf(this.face.mouth.orificeMouth.wetness));
			XMLUtil.addAttribute(doc, bodyMouth, "stretchedCapacity", String.valueOf(this.face.mouth.orificeMouth.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyMouth, "virgin", String.valueOf(this.face.mouth.orificeMouth.virgin));
			XMLUtil.addAttribute(doc, bodyMouth, "piercedLip", String.valueOf(this.face.mouth.piercedLip));
			XMLUtil.addAttribute(doc, bodyMouth, "lipSize", String.valueOf(this.face.mouth.lipSize));
			for(OrificeModifier om : this.face.mouth.orificeMouth.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyMouth.appendChild(mod);
			}
			
		Element bodyTongue = doc.createElement("tongue");
		parentElement.appendChild(bodyTongue);
			XMLUtil.addAttribute(doc, bodyTongue, "piercedTongue", String.valueOf(this.face.tongue.pierced));
			XMLUtil.addAttribute(doc, bodyTongue, "tongueLength", String.valueOf(this.face.tongue.tongueLength));
			for(TongueModifier tm : this.face.tongue.tongueModifiers) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(tm.toString());
				bodyTongue.appendChild(mod);
			}
			
		
		// Hair:
		Element bodyHair = doc.createElement("hair");
		parentElement.appendChild(bodyHair);
			XMLUtil.addAttribute(doc, bodyHair, "type", HairType.getIdFromHairType(this.hair.type));
			XMLUtil.addAttribute(doc, bodyHair, "length", String.valueOf(this.hair.length));
			XMLUtil.addAttribute(doc, bodyHair, "hairStyle", this.hair.style.toString());
			XMLUtil.addAttribute(doc, bodyHair, "neckFluff", String.valueOf(this.hair.neckFluff));
		
		// Horn:
		Element bodyHorn = doc.createElement("horn");
		parentElement.appendChild(bodyHorn);
			XMLUtil.addAttribute(doc, bodyHorn, "type", HornType.getIdFromHornType(this.horn.type));
			XMLUtil.addAttribute(doc, bodyHorn, "length", String.valueOf(this.horn.length));
			XMLUtil.addAttribute(doc, bodyHorn, "rows", String.valueOf(this.horn.rows));
			XMLUtil.addAttribute(doc, bodyHorn, "hornsPerRow", String.valueOf(this.horn.hornsPerRow));
		
		// Leg:
		Element bodyLeg = doc.createElement("leg");
		parentElement.appendChild(bodyLeg);
			XMLUtil.addAttribute(doc, bodyLeg, "type", LegType.getIdFromLegType(this.leg.type));
			XMLUtil.addAttribute(doc, bodyLeg, "footStructure", this.leg.footStructure.toString());
			XMLUtil.addAttribute(doc, bodyLeg, "configuration", this.leg.legConfiguration.toString());
			XMLUtil.addAttribute(doc, bodyLeg, "tailLength", String.valueOf(this.leg.lengthAsPercentageOfHeight));
		
		// Penis:
		Element bodyPenis = doc.createElement("penis");
		parentElement.appendChild(bodyPenis);
			XMLUtil.addAttribute(doc, bodyPenis, "type", PenisType.getIdFromPenisType(this.penis.type));
			if(this.penis.previousType!=null) {
				XMLUtil.addAttribute(doc, bodyPenis, "previousType", PenisType.getIdFromPenisType(this.penis.previousType));
			}
			XMLUtil.addAttribute(doc, bodyPenis, "size", String.valueOf(this.penis.length));
			XMLUtil.addAttribute(doc, bodyPenis, "girth", String.valueOf(this.penis.girth));
			XMLUtil.addAttribute(doc, bodyPenis, "pierced", String.valueOf(this.penis.pierced));
			XMLUtil.addAttribute(doc, bodyPenis, "virgin", String.valueOf(this.penis.virgin));
			for(PenetrationModifier pm : this.penis.getPenisModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(pm.toString());
				bodyPenis.appendChild(mod);
			}
			XMLUtil.addAttribute(doc, bodyPenis, "depth", String.valueOf(this.penis.orificeUrethra.depth));
			XMLUtil.addAttribute(doc, bodyPenis, "elasticity", String.valueOf(this.penis.orificeUrethra.elasticity));
			XMLUtil.addAttribute(doc, bodyPenis, "plasticity", String.valueOf(this.penis.orificeUrethra.plasticity));
			XMLUtil.addAttribute(doc, bodyPenis, "capacity", String.valueOf(this.penis.orificeUrethra.capacity));
			XMLUtil.addAttribute(doc, bodyPenis, "stretchedCapacity", String.valueOf(this.penis.orificeUrethra.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyPenis, "urethraVirgin", String.valueOf(this.penis.orificeUrethra.virgin));
			for(OrificeModifier om : this.penis.orificeUrethra.getOrificeModifiers()) {
				Element mod = doc.createElement("modUrethra");
				mod.setTextContent(om.toString());
				bodyPenis.appendChild(mod);
			}
			
		Element bodyTesticle = doc.createElement("testicles");
		parentElement.appendChild(bodyTesticle);
			XMLUtil.addAttribute(doc, bodyTesticle, "testicleSize", String.valueOf(this.penis.testicle.testicleSize));
			XMLUtil.addAttribute(doc, bodyTesticle, "cumStorage", String.valueOf(this.penis.testicle.cumStorage));
			XMLUtil.addAttribute(doc, bodyTesticle, "storedCum", String.valueOf(this.penis.testicle.cumStored));
			XMLUtil.addAttribute(doc, bodyTesticle, "cumRegeneration", String.valueOf(this.penis.testicle.cumRegeneration));
			XMLUtil.addAttribute(doc, bodyTesticle, "cumExpulsion", String.valueOf(this.penis.testicle.cumExpulsion));
			XMLUtil.addAttribute(doc, bodyTesticle, "numberOfTesticles", String.valueOf(this.penis.testicle.testicleCount));
			XMLUtil.addAttribute(doc, bodyTesticle, "internal", String.valueOf(this.penis.testicle.internal));
		
		this.penis.testicle.cum.saveAsXML("cum", parentElement, doc);

		// Spinneret:
		Element bodySpinneret = doc.createElement("spinneret");
		parentElement.appendChild(bodySpinneret);
			XMLUtil.addAttribute(doc, bodySpinneret, "wetness", String.valueOf(this.spinneret.wetness));
			XMLUtil.addAttribute(doc, bodySpinneret, "depth", String.valueOf(this.spinneret.depth));
			XMLUtil.addAttribute(doc, bodySpinneret, "elasticity", String.valueOf(this.spinneret.elasticity));
			XMLUtil.addAttribute(doc, bodySpinneret, "plasticity", String.valueOf(this.spinneret.plasticity));
			XMLUtil.addAttribute(doc, bodySpinneret, "capacity", String.valueOf(this.spinneret.capacity));
			XMLUtil.addAttribute(doc, bodySpinneret, "stretchedCapacity", String.valueOf(this.spinneret.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodySpinneret, "virgin", String.valueOf(this.spinneret.virgin));
			for(OrificeModifier om : this.spinneret.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodySpinneret.appendChild(mod);
			}


		// Tussy:
		if (tussy != null) {
			Element bodyTussy = doc.createElement("tussy");
			parentElement.appendChild(bodyTussy);
			XMLUtil.addAttribute(doc, bodyTussy, "wetness", String.valueOf(this.tussy.wetness));
			XMLUtil.addAttribute(doc, bodyTussy, "depth", String.valueOf(this.tussy.depth));
			XMLUtil.addAttribute(doc, bodyTussy, "elasticity", String.valueOf(this.tussy.elasticity));
			XMLUtil.addAttribute(doc, bodyTussy, "plasticity", String.valueOf(this.tussy.plasticity));
			XMLUtil.addAttribute(doc, bodyTussy, "capacity", String.valueOf(this.tussy.capacity));
			XMLUtil.addAttribute(doc, bodyTussy, "stretchedCapacity", String.valueOf(this.tussy.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyTussy, "virgin", String.valueOf(this.tussy.virgin));
			for(OrificeModifier om : this.tussy.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyTussy.appendChild(mod);
			}
		}


		// Torso:
		Element bodyTorso = doc.createElement("torso");
		parentElement.appendChild(bodyTorso);
			XMLUtil.addAttribute(doc, bodyTorso, "type", TorsoType.getIdFromTorsoType(this.torso.type));
		
		// Tail:
		Element bodyTail = doc.createElement("tail");
		parentElement.appendChild(bodyTail);
			XMLUtil.addAttribute(doc, bodyTail, "type", TailType.getIdFromTailType(this.tail.type));
			XMLUtil.addAttribute(doc, bodyTail, "count", String.valueOf(this.tail.tailCount));
			XMLUtil.addAttribute(doc, bodyTail, "girth", String.valueOf(this.tail.girth));
			XMLUtil.addAttribute(doc, bodyTail, "length", String.valueOf(this.tail.lengthAsPercentageOfHeight));
		
		// Tentacle:
		Element bodyTentacle = doc.createElement("tentacle");
		parentElement.appendChild(bodyTentacle);
			XMLUtil.addAttribute(doc, bodyTentacle, "type", TentacleType.getIdFromTentacleType(this.tentacle.type));
			XMLUtil.addAttribute(doc, bodyTentacle, "count", String.valueOf(this.tentacle.tentacleCount));
			XMLUtil.addAttribute(doc, bodyTentacle, "girth", String.valueOf(this.tentacle.girth));
			XMLUtil.addAttribute(doc, bodyTentacle, "length", String.valueOf(this.tentacle.lengthAsPercentageOfHeight));
			
		// Vagina
		Element bodyVagina = doc.createElement("vagina");
		parentElement.appendChild(bodyVagina);
			XMLUtil.addAttribute(doc, bodyVagina, "type", VaginaType.getIdFromVaginaType(this.vagina.type));
			XMLUtil.addAttribute(doc, bodyVagina, "labiaSize", String.valueOf(this.vagina.labiaSize));
			XMLUtil.addAttribute(doc, bodyVagina, "clitSize", String.valueOf(this.vagina.clitoris.clitSize));
			XMLUtil.addAttribute(doc, bodyVagina, "clitGirth", String.valueOf(this.vagina.clitoris.girth));
			for(PenetrationModifier pm : this.vagina.clitoris.getClitorisModifiers()) {
				Element mod = doc.createElement("modClit");
				mod.setTextContent(pm.toString());
				bodyVagina.appendChild(mod);
			}
			XMLUtil.addAttribute(doc, bodyVagina, "pierced", String.valueOf(this.vagina.pierced));
			XMLUtil.addAttribute(doc, bodyVagina, "eggLayer", String.valueOf(this.vagina.eggLayer));
			
			XMLUtil.addAttribute(doc, bodyVagina, "wetness", String.valueOf(this.vagina.orificeVagina.wetness));
			XMLUtil.addAttribute(doc, bodyVagina, "depth", String.valueOf(this.vagina.orificeVagina.depth));
			XMLUtil.addAttribute(doc, bodyVagina, "elasticity", String.valueOf(this.vagina.orificeVagina.elasticity));
			XMLUtil.addAttribute(doc, bodyVagina, "plasticity", String.valueOf(this.vagina.orificeVagina.plasticity));
			XMLUtil.addAttribute(doc, bodyVagina, "capacity", String.valueOf(this.vagina.orificeVagina.capacity));
			XMLUtil.addAttribute(doc, bodyVagina, "stretchedCapacity", String.valueOf(this.vagina.orificeVagina.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyVagina, "virgin", String.valueOf(this.vagina.orificeVagina.virgin));
			XMLUtil.addAttribute(doc, bodyVagina, "hymen", String.valueOf(this.vagina.orificeVagina.hymen));
			XMLUtil.addAttribute(doc, bodyVagina, "squirter", String.valueOf(this.vagina.orificeVagina.squirter));
			for(OrificeModifier om : this.vagina.orificeVagina.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyVagina.appendChild(mod);
			}

			XMLUtil.addAttribute(doc, bodyVagina, "urethraDepth", String.valueOf(this.vagina.orificeUrethra.depth));
			XMLUtil.addAttribute(doc, bodyVagina, "urethraElasticity", String.valueOf(this.vagina.orificeUrethra.elasticity));
			XMLUtil.addAttribute(doc, bodyVagina, "urethraPlasticity", String.valueOf(this.vagina.orificeUrethra.plasticity));
			XMLUtil.addAttribute(doc, bodyVagina, "urethraCapacity", String.valueOf(this.vagina.orificeUrethra.capacity));
			XMLUtil.addAttribute(doc, bodyVagina, "urethraStretchedCapacity", String.valueOf(this.vagina.orificeUrethra.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyVagina, "urethraVirgin", String.valueOf(this.vagina.orificeUrethra.virgin));
			for(OrificeModifier om : this.vagina.orificeUrethra.getOrificeModifiers()) {
				Element mod = doc.createElement("modUrethra");
				mod.setTextContent(om.toString());
				bodyVagina.appendChild(mod);
			}
			
		this.vagina.girlcum.saveAsXML("girlcum", parentElement, doc);

		// Wing:
		Element bodyWing = doc.createElement("wing");
		parentElement.appendChild(bodyWing);
		XMLUtil.addAttribute(doc, bodyWing, "type", WingType.getIdFromWingType(this.wing.type));
		XMLUtil.addAttribute(doc, bodyWing, "size", String.valueOf(this.wing.size));

//		System.out.println("Difference1: "+(System.nanoTime()-timeStart)/1000000000f);
		
		return parentElement;
	}

	
	public void setBodyCoveringForXMLImport(AbstractBodyCoveringType bct, CoveringPattern pattern, CoveringModifier modifier, Colour primary, boolean primaryGlow, Colour secondary, boolean secondaryGlow) {
		this.getCoverings().put(bct, new Covering(bct, pattern, modifier, primary, primaryGlow, secondary, secondaryGlow));
	}
	public void setBodyCoveringForXMLImport(AbstractBodyCoveringType bct, CoveringPattern pattern, Colour primary, boolean primaryGlow, Colour secondary, boolean secondaryGlow) {
		this.getCoverings().put(bct, new Covering(bct, pattern, primary, primaryGlow, secondary, secondaryGlow));
	}
	
	public static Body loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		
		// **************** Core **************** //
		
		Element element = (Element) parentElement.getElementsByTagName("bodyCore").item(0);
		
		String version = element.getAttribute("version");
		
		int importedFemininity = (Integer.valueOf(element.getAttribute("femininity")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set femininity: "+Integer.valueOf(element.getAttribute("femininity")));
		
		int importedHeight =(Integer.valueOf(element.getAttribute("height")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set height: "+Integer.valueOf(element.getAttribute("height")));
		
		int importedBodySize = (Integer.valueOf(element.getAttribute("bodySize")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set body size: "+Integer.valueOf(element.getAttribute("bodySize")));
	
		int importedMuscle = (Integer.valueOf(element.getAttribute("muscle")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set muscle: "+Integer.valueOf(element.getAttribute("muscle")));
		
		GenitalArrangement importedGenitalArrangement = GenitalArrangement.NORMAL;
		if(element.getAttribute("genitalArrangement") != null && !element.getAttribute("genitalArrangement").isEmpty()) {
			importedGenitalArrangement = GenitalArrangement.valueOf(element.getAttribute("genitalArrangement"));
		}
		
		BodyMaterial importedBodyMaterial = BodyMaterial.FLESH;
		if(element.getAttribute("bodyMaterial") != null && !element.getAttribute("bodyMaterial").isEmpty()) {
			importedBodyMaterial = BodyMaterial.valueOf(element.getAttribute("bodyMaterial"));
		}
		
		boolean feralBody = false;
		if(element.getAttribute("feral") != null && !element.getAttribute("feral").isEmpty()) {
			feralBody = Boolean.valueOf(element.getAttribute("feral"));
		}
		
		AbstractSubspecies importedSubspeciesOverride = null;
		try {
			if(element.getAttribute("subspeciesOverride") != null && !element.getAttribute("subspeciesOverride").isEmpty()) {
				importedSubspeciesOverride = Subspecies.getSubspeciesFromId(element.getAttribute("subspeciesOverride"));
			}
		} catch(Exception ex) {	
		}

		AbstractSubspecies importedLoadedSubspecies = null;
		try {
			if(element.getAttribute("subspecies") != null && !element.getAttribute("subspecies").isEmpty()) {
				importedLoadedSubspecies = Subspecies.getSubspeciesFromId(element.getAttribute("subspecies"));
			}
		} catch(Exception ex) {	
		}
		
		
		
		// **************** Antenna **************** //
		
		Element antennae = (Element)parentElement.getElementsByTagName("antennae").item(0);
		
		Antenna importedAntenna = new Antenna(AntennaType.getAntennaTypeFromId(antennae.getAttribute("type")), 0);
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Antennae:"+ "<br/>type: "+importedAntenna.getType());
		importedAntenna.setAntennaRows(null, Integer.valueOf(antennae.getAttribute("rows")));
		if(!antennae.getAttribute("length").isEmpty()) {
			importedAntenna.length = Integer.valueOf(antennae.getAttribute("length"));
		}
		if(!antennae.getAttribute("antennaePerRow").isEmpty()) {
			importedAntenna.antennaePerRow = Integer.valueOf(antennae.getAttribute("antennaePerRow"));
		}
		
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>rows: "+importedAntenna.getAntennaRows());
		
		
		// **************** Arm **************** //
		
		Element arm = (Element)parentElement.getElementsByTagName("arm").item(0);
		
		Arm importedArm = new Arm(ArmType.getArmTypeFromId(arm.getAttribute("type")), Integer.valueOf(arm.getAttribute("rows")));
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Arm:"+ "<br/>type: "+importedArm.getType());

		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>rows: "+importedArm.getArmRows());

		try {
			importedArm.underarmHair = BodyHair.valueOf(arm.getAttribute("underarmHair"));
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/>underarm hair: "+importedArm.getUnderarmHair());
		} catch(IllegalArgumentException e) {
			importedArm.underarmHair = BodyHair.ZERO_NONE;
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/>underarm hair: OLD_VALUE - Set to NONE");
		}
		
		// **************** Ass **************** //
		
		Element ass = (Element)parentElement.getElementsByTagName("ass").item(0);
		Element anus = (Element)parentElement.getElementsByTagName("anus").item(0);
		
		int depth = OrificeDepth.TWO_AVERAGE.getValue();
		String depthAttribute = anus.getAttribute("depth");
		if(!depthAttribute.isEmpty()) {
			depth = Integer.valueOf(depthAttribute);
		}
		
		Ass importedAss = new Ass(AssType.getAssTypeFromId(ass.getAttribute("type")),
				Integer.valueOf(ass.getAttribute("assSize")),
				Integer.valueOf(ass.getAttribute("hipSize")),
				Integer.valueOf(anus.getAttribute("wetness")),
				handleCapacityLoading(Float.valueOf(anus.getAttribute("capacity"))),
				depth,
				Integer.valueOf(anus.getAttribute("elasticity")),
				Integer.valueOf(anus.getAttribute("plasticity")),
				Boolean.valueOf(anus.getAttribute("virgin")));

		importedAss.anus.orificeAnus.stretchedCapacity = handleCapacityLoading(Float.valueOf(anus.getAttribute("stretchedCapacity")));
		importedAss.anus.bleached = (Boolean.valueOf(anus.getAttribute("bleached")));
		try {
			importedAss.anus.assHair = (BodyHair.valueOf(anus.getAttribute("assHair")));
		} catch(IllegalArgumentException e) {
			importedAss.anus.assHair = BodyHair.ZERO_NONE;
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/>ass hair: OLD_VALUE - Set to NONE");
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Ass:"
				+ "<br/>type: "+importedAss.getType()
				+ "<br/>assSize: "+importedAss.getAssSize()
				+ "<br/>hipSize: "+importedAss.getHipSize());
		
		if (anus != null) {
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Anus:"
					+ "<br/>wetness: "+importedAss.anus.orificeAnus.wetness
					+ "<br/>depth: "+importedAss.anus.orificeAnus.depth
					+ "<br/>elasticity: "+importedAss.anus.orificeAnus.elasticity
					+ "<br/>elasticity: "+importedAss.anus.orificeAnus.plasticity
					+ "<br/>capacity: "+importedAss.anus.orificeAnus.capacity
					+ "<br/>stretchedCapacity: "+importedAss.anus.orificeAnus.stretchedCapacity
					+ "<br/>virgin: "+importedAss.anus.orificeAnus.virgin
					+ "<br/>bleached: "+importedAss.anus.bleached
					+ "<br/>assHair: "+importedAss.anus.assHair
					+"<br/>Modifiers:");
			
			Collection<OrificeModifier> anusModifiers = importedAss.anus.orificeAnus.orificeModifiers;
			anusModifiers.clear();
			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element anusModifiersElement = (Element)anus.getElementsByTagName("anusModifiers").item(0);
				if(anusModifiersElement!=null) {
					if(anusModifiersElement.hasAttribute("EXTRA_DEEP")) {
						importedAss.anus.orificeAnus.setDepth(null, depth+2);
					}
					handleLoadingOfModifiers(OrificeModifier.values(), log, anusModifiersElement, anusModifiers);
				}
				
			} else {
				NodeList mods = anus.getElementsByTagName("mod");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedAss.anus.orificeAnus.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
				}
			}
		}
		

		// **************** Breasts **************** //
		
		Element breasts = (Element)parentElement.getElementsByTagName("breasts").item(0);
		Element nipples = (Element)parentElement.getElementsByTagName("nipples").item(0);
		
		BreastShape breastShape = BreastShape.ROUND;
		try {
			breastShape = BreastShape.valueOf(breasts.getAttribute("shape"));
		} catch(Exception e) {
		}
		
		int milkStorage = 0;
		try {
			if(!breasts.getAttribute("lactation").isEmpty()) {
				milkStorage = Integer.valueOf(breasts.getAttribute("lactation"));
			} else {
				milkStorage = Integer.valueOf(breasts.getAttribute("milkStorage"));
			}
		} catch(Exception ex) {
		}

		depth = OrificeDepth.TWO_AVERAGE.getValue();
		depthAttribute = nipples.getAttribute("depth");
		if(!depthAttribute.isEmpty()) {
			depth = Integer.valueOf(depthAttribute);
		}
		
		Breast importedBreast = new Breast(BreastType.getBreastTypeFromId(breasts.getAttribute("type")),
				breastShape,
				Integer.valueOf(breasts.getAttribute("size")),
				milkStorage,
				Integer.valueOf(breasts.getAttribute("rows")),
				Integer.valueOf(nipples.getAttribute("nippleSize")),
				NippleShape.valueOf(nipples.getAttribute("nippleShape")),
				Integer.valueOf(nipples.getAttribute("areolaeSize")),
				AreolaeShape.valueOf(nipples.getAttribute("areolaeShape")),
				Integer.valueOf(breasts.getAttribute("nippleCountPerBreast")),
				handleCapacityLoading(Float.valueOf(nipples.getAttribute("capacity"))),
				depth,
				Integer.valueOf(nipples.getAttribute("elasticity")),
				Integer.valueOf(nipples.getAttribute("plasticity")),
				Boolean.valueOf(nipples.getAttribute("virgin")));

		try {
			importedBreast.milkStored = Float.valueOf(breasts.getAttribute("storedMilk"));
			importedBreast.milkRegeneration = Integer.valueOf(breasts.getAttribute("milkRegeneration"));
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.9")) { // Change from percentage-based to set value:
				importedBreast.milkRegeneration = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay();
			}
		} catch(Exception ex) {
		}
		
		importedBreast.nipples.crotchNipples = false;
		importedBreast.nipples.orificeNipples.stretchedCapacity = handleCapacityLoading(Float.valueOf(nipples.getAttribute("stretchedCapacity")));
		importedBreast.nipples.pierced = (Boolean.valueOf(nipples.getAttribute("pierced")));
//		importedBreast.nipples.areolaeShape = (AreolaeShape.valueOf(nipples.getAttribute("areolaeShape")));
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Breasts:"
				+ "<br/>type: "+importedBreast.getType()
				+ "<br/>size: "+importedBreast.getSize()
				+ "<br/>rows: "+importedBreast.getRows()
				+ "<br/>lactation: "+importedBreast.getRawMilkStorageValue()
				+ "<br/>nippleCountPer: "+importedBreast.getNippleCountPerBreast()
				
				+ "<br/><br/>Nipples:"
				+ "<br/>depth: "+importedBreast.nipples.orificeNipples.getDepth(null)
				+ "<br/>elasticity: "+importedBreast.nipples.orificeNipples.getElasticity()
				+ "<br/>plasticity: "+importedBreast.nipples.orificeNipples.getPlasticity()
				+ "<br/>capacity: "+importedBreast.nipples.orificeNipples.getRawCapacityValue()
				+ "<br/>stretchedCapacity: "+importedBreast.nipples.orificeNipples.getStretchedCapacity()
				+ "<br/>virgin: "+importedBreast.nipples.orificeNipples.isVirgin()
				+ "<br/>pierced: "+importedBreast.nipples.isPierced()
				+ "<br/>nippleSize: "+importedBreast.nipples.getNippleSize()
				+ "<br/>nippleShape: "+importedBreast.nipples.getNippleShape()
				+ "<br/>areolaeSize: "+importedBreast.nipples.getAreolaeSize()
				+ "<br/>areolaeShape: "+importedBreast.nipples.getAreolaeShape()
				+"<br/>Modifiers:");

		Collection<OrificeModifier> nippleOrificeModifiers = importedBreast.nipples.orificeNipples.orificeModifiers;
		nippleOrificeModifiers.clear();
		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			Element nippleModifiersElement = (Element)nipples.getElementsByTagName("nippleModifiers").item(0);
			if(nippleModifiersElement!=null) {
				if(nippleModifiersElement.hasAttribute("EXTRA_DEEP")) {
					importedBreast.nipples.orificeNipples.setDepth(null, depth+2);
				}
				handleLoadingOfModifiers(OrificeModifier.values(), log, nippleModifiersElement, nippleOrificeModifiers);
			}
	
		} else {
			NodeList mods = nipples.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedBreast.nipples.orificeNipples.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
			}
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Milk:");
		
		importedBreast.milk = FluidMilk.loadFromXML("milk", parentElement, doc, importedBreast.getType().getFluidType(), false);
		if(Main.isVersionOlderThan(Main.VERSION_NUMBER, "0.2.5.1")) {
			importedBreast.milk.type = importedBreast.getType().getFluidType();
		}
		
		// **************** Ear **************** //
		
		Element ear = (Element)parentElement.getElementsByTagName("ear").item(0);

		Ear importedEar = new Ear(EarType.getEarTypeFromId(ear.getAttribute("type")));
		
		importedEar.pierced = (Boolean.valueOf(ear.getAttribute("pierced")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Ear:"
				+ "<br/>type: "+importedEar.getType()
				+ "<br/>pierced: "+importedEar.isPierced());

		
		
		// **************** Eye **************** //
		
		Element eye = (Element)parentElement.getElementsByTagName("eye").item(0);
		
		String eyeTypeFromSave = eye.getAttribute("type");
		
		Map<String, String> eyeTypeConverterMap = new HashMap<>();
		eyeTypeConverterMap.put("EYE_HUMAN", "HUMAN");
		eyeTypeConverterMap.put("EYE_ANGEL", "ANGEL");
		eyeTypeConverterMap.put("EYE_DEMON_COMMON", "DEMON_COMMON");
		eyeTypeConverterMap.put("EYE_DOG_MORPH", "DOG_MORPH");
		eyeTypeConverterMap.put("EYE_LYCAN", "LYCAN");
		eyeTypeConverterMap.put("EYE_FELINE", "CAT_MORPH");
		eyeTypeConverterMap.put("EYE_SQUIRREL", "SQUIRREL_MORPH");
		eyeTypeConverterMap.put("EYE_HORSE_MORPH", "HORSE_MORPH");
		eyeTypeConverterMap.put("EYE_HARPY", "HARPY");
		eyeTypeConverterMap.put("EYE_SLIME", "SLIME");
		if(eyeTypeConverterMap.containsKey(eyeTypeFromSave)) {
			eyeTypeFromSave = eyeTypeConverterMap.get(eyeTypeFromSave);
		}
		
		Eye importedEye = new Eye(EyeType.getEyeTypeFromId(eyeTypeFromSave));
		
		if(Main.isVersionOlderThan(version, "0.3.9") && importedSubspeciesOverride==Subspecies.HALF_DEMON) { // Fix to taurs spawning with no demon parts at all in v0.3.8.9
			importedEye = new Eye(EyeType.DEMON_COMMON);
		}
		
		importedEye.eyePairs = (Integer.valueOf(eye.getAttribute("eyePairs")));
		importedEye.irisShape = (EyeShape.valueOf(eye.getAttribute("irisShape")));
		importedEye.pupilShape = (EyeShape.valueOf(eye.getAttribute("pupilShape")));
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Eye:"
				+ "<br/>type: "+importedEye.getType()
				+ "<br/>pairs: "+importedEye.getEyePairs()
				+ "<br/>iris shape: "+importedEye.getIrisShape()
				+ "<br/>pupil shape: "+importedEye.getPupilShape());
		
		
		// **************** Face **************** //
	
		Element face = (Element)parentElement.getElementsByTagName("face").item(0);
		Element mouth = (Element)parentElement.getElementsByTagName("mouth").item(0);
		
		boolean oldPantherReplacement = face.getAttribute("type").equals("CAT_MORPH_PANTHER");
		
		Face importedFace = new Face(FaceType.getFaceTypeFromId(face.getAttribute("type")), Integer.valueOf(mouth.getAttribute("lipSize")));
		
		importedFace.piercedNose = (Boolean.valueOf(face.getAttribute("piercedNose")));
		try {
			importedFace.facialHair = (BodyHair.valueOf(face.getAttribute("facialHair")));
		} catch(IllegalArgumentException e) {
			importedFace.facialHair = BodyHair.ZERO_NONE;
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/>facial hair: OLD_VALUE - Set to NONE");
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Face: "
				+ "<br/>type: "+importedFace.getType()
				+ "<br/>piercedNose: "+importedFace.isPiercedNose()
				+ "<br/>facial hair: "+importedFace.getFacialHair()
				
				+ "<br/><br/>Mouth: ");

		depth = OrificeDepth.TWO_AVERAGE.getValue();
		depthAttribute = mouth.getAttribute("depth");
		if(!depthAttribute.isEmpty()) {
			depth = Integer.valueOf(depthAttribute);
		}
		importedFace.mouth.orificeMouth.depth = depth;
		importedFace.mouth.orificeMouth.elasticity = (Integer.valueOf(mouth.getAttribute("elasticity")));
		importedFace.mouth.orificeMouth.plasticity = (Integer.valueOf(mouth.getAttribute("plasticity")));
		importedFace.mouth.orificeMouth.capacity = handleCapacityLoading(Float.valueOf(mouth.getAttribute("capacity")));
		try {
			importedFace.mouth.orificeMouth.wetness = (Integer.valueOf(mouth.getAttribute("wetness")));
		} catch(Exception ex) {
		}
		importedFace.mouth.orificeMouth.stretchedCapacity = handleCapacityLoading(Float.valueOf(mouth.getAttribute("stretchedCapacity")));
		importedFace.mouth.orificeMouth.virgin = (Boolean.valueOf(mouth.getAttribute("virgin")));
		importedFace.mouth.piercedLip = (Boolean.valueOf(mouth.getAttribute("piercedLip")));
		
		Main.game.getCharacterUtils().appendToImportLog(log, 
				"<br/>深度: "+importedFace.mouth.orificeMouth.getDepth(null)
				+ "<br/>弹性等级: "+importedFace.mouth.orificeMouth.getElasticity()
				+ "<br/>可塑性等级: "+importedFace.mouth.orificeMouth.getPlasticity()
				+ "<br/>容量: "+importedFace.mouth.orificeMouth.getCapacity()
				+ "<br/>撑开后容量: "+importedFace.mouth.orificeMouth.getStretchedCapacity()
				+ "<br/>处女: "+importedFace.mouth.orificeMouth.isVirgin()
				+ "<br/>已穿孔嘴唇: "+importedFace.mouth.isPiercedLip()
				+ "<br/>嘴唇尺寸: "+importedFace.mouth.getLipSize()
				+ "<br/>修饰词: ");

		Collection<OrificeModifier> mouthOrificeModifiers = importedFace.mouth.orificeMouth.orificeModifiers;
		mouthOrificeModifiers.clear();
		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			Element mouthModifiersElement = (Element)mouth.getElementsByTagName("mouthModifiers").item(0);
			if(mouthModifiersElement!=null) {
				if(mouthModifiersElement.hasAttribute("EXTRA_DEEP")) {
					importedFace.mouth.orificeMouth.setDepth(null, depth+2);
				}
				handleLoadingOfModifiers(OrificeModifier.values(), log, mouthModifiersElement, mouthOrificeModifiers);
			}
			
		} else {
			NodeList mods = mouth.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedFace.mouth.orificeMouth.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
			}
		}

		Element tongue = (Element)parentElement.getElementsByTagName("tongue").item(0);
			importedFace.tongue.pierced = (Boolean.valueOf(tongue.getAttribute("piercedTongue")));
			importedFace.tongue.tongueLength = (Integer.valueOf(tongue.getAttribute("tongueLength")));
			
			Main.game.getCharacterUtils().appendToImportLog(log, 
					"<br/><br/>舌头: "
					+ "<br/>已穿孔舌头: "+importedFace.tongue.isPierced()
					+ "<br/>舌头长度: "+importedFace.tongue.getTongueLength()
					+ "<br/>修饰词: ");

			Collection<TongueModifier> tongueModifiers = importedFace.tongue.tongueModifiers;
			tongueModifiers.clear();
			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element tongueModifiersElement = (Element)tongue.getElementsByTagName("tongueModifiers").item(0);
				if(tongueModifiersElement!=null) {
					handleLoadingOfModifiers(TongueModifier.values(), log, tongueModifiersElement, tongueModifiers);
				}
				if(version.isEmpty()) { // Version tracking was added in v0.3.7, so if there is no version, it is before then. Add new default modifiers added in v0.3.7:
					for(TongueModifier mod :importedFace.tongue.getType().getDefaultRacialTongueModifiers()) {
						if(mod==TongueModifier.FLAT || mod==TongueModifier.WIDE || mod==TongueModifier.STRONG) {
							importedFace.tongue.tongueModifiers.add(mod);
						}
					}
				}
				
			} else {
				NodeList mods = tongue.getElementsByTagName("mod");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedFace.tongue.tongueModifiers.add(TongueModifier.valueOf(e.getTextContent()));
				}
			}
			
			
		// **************** Hair **************** //
		
		Element hair = (Element)parentElement.getElementsByTagName("hair").item(0);
		String hairTypeFromSave = hair.getAttribute("type");
		
		Map<String, String> hairTypeConverterMap = new HashMap<>();
		hairTypeConverterMap.put("HAIR_HUMAN", "HUMAN");
		hairTypeConverterMap.put("HAIR_ANGEL", "ANGEL");
		hairTypeConverterMap.put("HAIR_DEMON", "DEMON_COMMON");
		hairTypeConverterMap.put("HAIR_CANINE_FUR", "DOG_MORPH");
		hairTypeConverterMap.put("HAIR_LYCAN_FUR", "LYCAN");
		hairTypeConverterMap.put("HAIR_FELINE_FUR", "CAT_MORPH");
		hairTypeConverterMap.put("HAIR_HORSE_HAIR", "HORSE_MORPH");
		hairTypeConverterMap.put("HAIR_SQUIRREL_FUR", "SQUIRREL_MORPH");
		hairTypeConverterMap.put("SLIME", "SLIME");
		hairTypeConverterMap.put("HAIR_HARPY", "HARPY");
		if(hairTypeConverterMap.containsKey(hairTypeFromSave)) {
			hairTypeFromSave = hairTypeConverterMap.get(hairTypeFromSave);
		}
		
		Hair importedHair = new Hair(HairType.getHairTypeFromId(hairTypeFromSave),
				Integer.valueOf(hair.getAttribute("length")),
				HairStyle.valueOf(hair.getAttribute("hairStyle")),
				null);
		
		importedHair.neckFluff = Boolean.valueOf(hair.getAttribute("neckFluff"));
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Hair: "
				+ "<br/>type: "+importedHair.getType()
				+ "<br/>length: "+importedHair.getLength()
				+ "<br/>hairStyle: "+importedHair.getStyle());

		
		// **************** Horn **************** //
		Element horn = (Element)parentElement.getElementsByTagName("horn").item(0);
		
		Horn importedHorn = new Horn(HornType.NONE, 0);
		int rows = (Integer.valueOf(horn.getAttribute("rows")));
		
		String hornType = horn.getAttribute("type");
		if(hornType.equals("DEMON")) {
			hornType = "";
		}
		if(hornType.equals("BOVINE")) {
			hornType = "";
		}
		int length = 0;
		if(!hornType.equals("NONE")) {
			length = HornLength.TWO_LONG.getMedianValue();
		}
		if(!horn.getAttribute("length").isEmpty()) {
			try {
				length = Integer.valueOf(horn.getAttribute("length"));
			} catch(IllegalArgumentException e) {
			}
		}
		int hornsPerRow = 2;
		if(!horn.getAttribute("hornsPerRow").isEmpty()) {
			try {
				hornsPerRow = Integer.valueOf(horn.getAttribute("hornsPerRow"));
			} catch(IllegalArgumentException e) {
			}
		}
		try {
			importedHorn = new Horn(HornType.getHornTypeFromId(hornType), length);
			importedHorn.rows = rows;
			importedHorn.hornsPerRow = hornsPerRow;
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Horn: "
					+ "<br/>type: "+importedHorn.getType()
					+ "<br/>length: "+length
					+ "<br/>rows: "+importedHorn.getHornRows()
					+ "<br/>horns per row: "+importedHorn.getHornsPerRow());
			
		} catch(IllegalArgumentException e) {
			if(horn.getAttribute("type").startsWith("DEMON")) {
				importedHorn = new Horn(HornType.SWEPT_BACK, length);
				importedHorn.rows = rows;
				
			} else if(horn.getAttribute("type").startsWith("BOVINE")) {
				importedHorn = new Horn(HornType.CURVED, length);
				importedHorn.rows = rows;
			}
			
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Horn: "
					+ "<br/>type NOT FOUND, defaulted to: "+importedHorn.getType()
					+ "<br/>rows: "+importedHorn.getHornRows());
		}
		
		
			
		// **************** Leg **************** //
		
		Element leg = (Element)parentElement.getElementsByTagName("leg").item(0);

		AbstractLegType legType = LegType.getLegTypeFromId(leg.getAttribute("type"));
		
		LegConfiguration configuration = LegConfiguration.BIPEDAL;
		try {
			configuration = LegConfiguration.getValueFromString(leg.getAttribute("configuration"));
		} catch(Exception ex) {}
		
		FootStructure footStructure = legType.getDefaultFootStructure(configuration);
		try {
			footStructure = FootStructure.valueOf(leg.getAttribute("footStructure"));
		} catch(Exception ex) {}
		
		
		Leg importedLeg = new Leg(legType, configuration);
		importedLeg.setFootStructure(null, footStructure);
		try {
			float tailLength = Float.valueOf(leg.getAttribute("tailLength"));
			importedLeg.setLengthAsPercentageOfHeight(null, tailLength);
		} catch(Exception ex) {}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Leg: "
				+ "<br/>type: "+importedLeg.getType());

		
		// **************** Penis **************** //
		
		Element penis = (Element)parentElement.getElementsByTagName("penis").item(0);
		Element testicles = (Element)parentElement.getElementsByTagName("testicles").item(0);
		
		int girth = 2;
		if(penis.getAttribute("girth") != null && !penis.getAttribute("girth").isEmpty()) {
			girth = Integer.valueOf(penis.getAttribute("girth"));
			if(Main.isVersionOlderThan(version, "0.3.7.6")) {// An extra level was added in 0.3.7.6, so +1 if the version is older than that
				girth+=1;
			}
		}
		
		int cumStorage = 0;
		try {
			if(testicles.hasAttribute("cumProduction")) {
				cumStorage = Integer.valueOf(testicles.getAttribute("cumProduction"));
			} else {
				cumStorage = Integer.valueOf(testicles.getAttribute("cumStorage"));
			}
		} catch(Exception ex) {
		}
		
		Penis importedPenis = new Penis(PenisType.getPenisTypeFromId(penis.getAttribute("type")),
				Integer.valueOf(penis.getAttribute("size")),
				false,
				girth,
				Integer.valueOf(testicles.getAttribute("testicleSize")),
				cumStorage,
				Integer.valueOf(testicles.getAttribute("numberOfTesticles")));
		
		importedPenis.pierced = (Boolean.valueOf(penis.getAttribute("pierced")));
		
		if(!penis.getAttribute("virgin").isEmpty()) {
			importedPenis.virgin = (Boolean.valueOf(penis.getAttribute("virgin")));
		}

		if(!penis.getAttribute("previousType").isEmpty()) {
			importedPenis.previousType = PenisType.getPenisTypeFromId(penis.getAttribute("previousType"));
		}
		
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Penis: "
				+ "<br/>type: "+importedPenis.getType()
				+ "<br/>size: "+importedPenis.getRawLengthValue()
				+ "<br/>pierced: "+importedPenis.isPierced()
				+ "<br/>Penis Modifiers: ");

		Collection<PenetrationModifier> penisModifiers = importedPenis.penisModifiers;
		penisModifiers.clear();
		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			Element penisModifiersElement = (Element)penis.getElementsByTagName("penisModifiers").item(0);
			if (penisModifiersElement != null) {
				handleLoadingOfModifiers(PenetrationModifier.values(), log, penisModifiersElement, penisModifiers);
			}
			
		} else {
			NodeList mods = penis.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedPenis.addPenisModifier(null, PenetrationModifier.valueOf(e.getTextContent()));
			}
		}
		
		depth = OrificeDepth.TWO_AVERAGE.getValue();
		depthAttribute = penis.getAttribute("depth");
		if(!depthAttribute.isEmpty()) {
			depth = Integer.valueOf(depthAttribute);
		}
		importedPenis.orificeUrethra.depth = depth;
		importedPenis.orificeUrethra.elasticity = (Integer.valueOf(penis.getAttribute("elasticity")));
		importedPenis.orificeUrethra.plasticity = (Integer.valueOf(penis.getAttribute("plasticity")));
		importedPenis.orificeUrethra.capacity = handleCapacityLoading(Float.valueOf(penis.getAttribute("capacity")));
		importedPenis.orificeUrethra.stretchedCapacity = handleCapacityLoading(Float.valueOf(penis.getAttribute("stretchedCapacity")));
		if(!penis.getAttribute("urethraVirgin").isEmpty()) {
			importedPenis.orificeUrethra.virgin = (Boolean.valueOf(penis.getAttribute("urethraVirgin")));
		} else {
			importedPenis.orificeUrethra.virgin = true;
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, 
				"<br/>深度: "+importedPenis.orificeUrethra.getDepth(null)
				+ "<br/>弹性等级: "+importedPenis.orificeUrethra.getElasticity()
				+ "<br/>可塑性等级: "+importedPenis.orificeUrethra.getPlasticity()
				+ "<br/>容量: "+importedPenis.orificeUrethra.getCapacity()
				+ "<br/>撑开后容量: "+importedPenis.orificeUrethra.getStretchedCapacity()
				+ "<br/>处女: "+importedPenis.orificeUrethra.isVirgin()
				+ "<br/>尿道修饰词:");

		Collection<OrificeModifier> urethraOrificeModifiers = importedPenis.orificeUrethra.orificeModifiers;
		urethraOrificeModifiers.clear();
		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			Element urethraModifiersElement = (Element)penis.getElementsByTagName("urethraModifiers").item(0);
			if (urethraModifiersElement != null) {
				if(urethraModifiersElement.hasAttribute("EXTRA_DEEP")) {
					importedPenis.orificeUrethra.setDepth(null, depth+2);
				}
				handleLoadingOfModifiers(OrificeModifier.values(), log, urethraModifiersElement, urethraOrificeModifiers);
			}
			
		} else {
			NodeList mods = penis.getElementsByTagName("modUrethra");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedPenis.orificeUrethra.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
			}
		}
		
		importedPenis.testicle.internal = (Boolean.valueOf(testicles.getAttribute("internal")));
		
		try {
			importedPenis.testicle.cumStored = Float.valueOf(testicles.getAttribute("storedCum"));
			importedPenis.testicle.cumRegeneration = Integer.valueOf(testicles.getAttribute("cumRegeneration"));
			importedPenis.testicle.setCumExpulsion(null, Integer.valueOf(testicles.getAttribute("cumExpulsion")));
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.9")) { // Change from percentage-based to set value:
				importedPenis.testicle.cumRegeneration = FluidRegeneration.CUM_REGEN_DEFAULT;
			}
		} catch(Exception ex) {
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Testicles: "
				+ "<br/>cumProduction: "+importedPenis.testicle.getCumStorage()
				+ "<br/>numberOfTesticles: "+importedPenis.testicle.getTesticleCount()
				+ "<br/>testicleSize: "+importedPenis.testicle.getTesticleSize()
				+ "<br/>internal: "+importedPenis.testicle.internal);
		
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Cum:");
		
		importedPenis.testicle.cum = FluidCum.loadFromXML("cum", parentElement, doc, importedPenis.getType().getTesticleType().getFluidType());

		
		// **************** Skin **************** //
		
		Element torso;
		if(parentElement.getElementsByTagName("skin").getLength()>0) {
			torso = (Element)parentElement.getElementsByTagName("skin").item(0);
		} else {
			torso = (Element)parentElement.getElementsByTagName("torso").item(0);
		}
		String torsoTypeFromSave = torso.getAttribute("type");
		
		Torso importedTorso = new Torso(TorsoType.getTorsoTypeFromId(torsoTypeFromSave));
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Torso: "
				+ "<br/>type: "+importedTorso.getType());


		// **************** Spinneret **************** //
		
		Element spinneret = (Element)parentElement.getElementsByTagName("spinneret").item(0);

		OrificeSpinneret importedSpinneret = new OrificeSpinneret();
		
		if(spinneret!=null) {
			importedSpinneret.wetness = Integer.valueOf(spinneret.getAttribute("wetness"));
			importedSpinneret.capacity = handleCapacityLoading(Float.valueOf(spinneret.getAttribute("capacity")));
			depth = OrificeDepth.TWO_AVERAGE.getValue();
			depthAttribute = spinneret.getAttribute("depth");
			if(!depthAttribute.isEmpty()) {
				depth = Integer.valueOf(depthAttribute);
			}
			importedSpinneret.depth = depth;
			importedSpinneret.elasticity = Integer.valueOf(spinneret.getAttribute("elasticity"));
			importedSpinneret.plasticity = Integer.valueOf(spinneret.getAttribute("plasticity"));
			importedSpinneret.virgin = Boolean.valueOf(spinneret.getAttribute("virgin"));
			
			importedSpinneret.stretchedCapacity = handleCapacityLoading(Float.valueOf(spinneret.getAttribute("stretchedCapacity")));

			Collection<OrificeModifier> spinneretOrificeModifiers = importedSpinneret.orificeModifiers;
			spinneretOrificeModifiers.clear();
			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element spinneretModifiers = (Element)spinneret.getElementsByTagName("spinneretModifiers").item(0);
				if(spinneretModifiers!=null) {
					handleLoadingOfModifiers(OrificeModifier.values(), log, spinneretModifiers, spinneretOrificeModifiers);
				}
				
			} else {
				NodeList mods = spinneret.getElementsByTagName("mod");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedSpinneret.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
				}
			}
		}



		// **************** Tussy **************** //

		Element tussy = (Element)parentElement.getElementsByTagName("tussy").item(0);

		OrificeTussy importedTussy = new OrificeTussy();

		if(tussy!=null) {
			importedTussy.wetness = Integer.valueOf(tussy.getAttribute("wetness"));
			importedTussy.capacity = handleCapacityLoading(Float.valueOf(tussy.getAttribute("capacity")));
			depth = OrificeDepth.TWO_AVERAGE.getValue();
			depthAttribute = tussy.getAttribute("depth");
			if(!depthAttribute.isEmpty()) {
				depth = Integer.valueOf(depthAttribute);
			}
			importedTussy.depth = depth;
			importedTussy.elasticity = Integer.valueOf(tussy.getAttribute("elasticity"));
			importedTussy.plasticity = Integer.valueOf(tussy.getAttribute("plasticity"));
			importedTussy.virgin = Boolean.valueOf(tussy.getAttribute("virgin"));

			importedTussy.stretchedCapacity = handleCapacityLoading(Float.valueOf(tussy.getAttribute("stretchedCapacity")));

			Collection<OrificeModifier> tussyOrificeModifiers = importedTussy.orificeModifiers;
			tussyOrificeModifiers.clear();
			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element tussyModifiers = (Element)tussy.getElementsByTagName("tussyModifiers").item(0);
				if(tussyModifiers!=null) {
					handleLoadingOfModifiers(OrificeModifier.values(), log, tussyModifiers, tussyOrificeModifiers);
				}

			} else {
				NodeList mods = tussy.getElementsByTagName("mod");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedTussy.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
				}
			}
		}

		
		// **************** Tail **************** //
		
		Element tail = (Element)parentElement.getElementsByTagName("tail").item(0);
		AbstractTailType tailType = TailType.getTailTypeFromId(tail.getAttribute("type"));
		
		Tail importedTail = new Tail(tailType);
		
		importedTail.tailCount = (Integer.valueOf(tail.getAttribute("count")));
		
		if(tail.getAttribute("girth") != null && !tail.getAttribute("girth").isEmpty()) {
			int tailGirth = Integer.valueOf(tail.getAttribute("girth"));
			if(Main.isVersionOlderThan(version, "0.3.7.6")) {// An extra level was added in 0.3.7.6, so +1 if the version is older than that
				tailGirth+=1;
			}
			importedTail.girth = tailGirth;
		}
		
		if(Main.isVersionOlderThan(version, "0.3.10")) {
			importedTail.lengthAsPercentageOfHeight = tailType.getDefaultLengthAsPercentageOfHeight();
			
		} else {
			if(tail.getAttribute("length") != null && !tail.getAttribute("length").isEmpty()) {
				float tailLength = Float.valueOf(tail.getAttribute("length"));
				importedTail.lengthAsPercentageOfHeight = tailLength;
			}
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Tail: "
				+ "<br/>type: "+importedTail.getType()
				+ "<br/>count: "+importedTail.getTailCount());

		
		// **************** Tentacle **************** //

		Tentacle importedTentacle = new Tentacle(TentacleType.NONE);	
		
		Element tentacle = (Element)parentElement.getElementsByTagName("tentacle").item(0);
		if(tentacle!=null) {
			AbstractTentacleType tentacleType = TentacleType.getTentacleTypeFromId(tentacle.getAttribute("type"));
	
			importedTentacle = new Tentacle(tentacleType);
			
			importedTentacle.tentacleCount = (Integer.valueOf(tentacle.getAttribute("count")));
			
			if(tentacle.getAttribute("girth") != null && !tentacle.getAttribute("girth").isEmpty()) {
				int tentacleGirth = Integer.valueOf(tentacle.getAttribute("girth"));
				importedTentacle.girth = tentacleGirth;
			}
			
			if(tentacle.getAttribute("length")!=null && !tentacle.getAttribute("length").isEmpty()) {
				float tentacleLength = Float.valueOf(tentacle.getAttribute("length"));
				importedTentacle.lengthAsPercentageOfHeight = tentacleLength;
			} else {
				importedTentacle.lengthAsPercentageOfHeight = tentacleType.getDefaultLengthAsPercentageOfHeight();
			}
			
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Tentacle: "
					+ "<br/>type: "+importedTentacle.getType()
					+ "<br/>count: "+importedTentacle.getTentacleCount());
		}
		
		
		// **************** Vagina **************** //
		
		Element vagina = (Element)parentElement.getElementsByTagName("vagina").item(0);

		depth = OrificeDepth.TWO_AVERAGE.getValue();
		depthAttribute = vagina.getAttribute("depth");
		if(!depthAttribute.isEmpty()) {
			depth = Integer.valueOf(depthAttribute);
		}
		
		Vagina importedVagina = new Vagina(VaginaType.getVaginaTypeFromId(vagina.getAttribute("type")),
				(vagina.getAttribute("labiaSize").isEmpty()?1:Integer.valueOf(vagina.getAttribute("labiaSize"))),
				Integer.valueOf(vagina.getAttribute("clitSize")),
				vagina.hasAttribute("clitGirth")?Integer.valueOf(vagina.getAttribute("clitGirth")):PenetrationGirth.THREE_AVERAGE.getValue(),
				Integer.valueOf(vagina.getAttribute("wetness")),
				handleCapacityLoading(Float.valueOf(vagina.getAttribute("capacity"))),
				depth,
				Integer.valueOf(vagina.getAttribute("elasticity")),
				Integer.valueOf(vagina.getAttribute("plasticity")),
				Boolean.valueOf(vagina.getAttribute("virgin")));

		Collection<PenetrationModifier> clitModifiers = importedVagina.clitoris.clitModifiers;
		clitModifiers.clear();
		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			try {
				Element clitModifiersElement = (Element)vagina.getElementsByTagName("clitModifiers").item(0);
				if (clitModifiersElement != null) {
					handleLoadingOfModifiers(PenetrationModifier.values(), log, clitModifiersElement, clitModifiers);
				}
				
			} catch(Exception ex) {
			}
			
		} else {
			NodeList mods = vagina.getElementsByTagName("modClit");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedVagina.getClitoris().addClitorisModifier(null, PenetrationModifier.valueOf(e.getTextContent()));
			}
		}
		
		importedVagina.pierced = (Boolean.valueOf(vagina.getAttribute("pierced")));
		
		if(vagina.hasAttribute("eggLayer")) {
			importedVagina.eggLayer = (Boolean.valueOf(vagina.getAttribute("eggLayer")));
		} else if(vagina.getAttribute("type").equals("DEMON_EGGS") || vagina.getAttribute("type").equals("NoStepOnSnek_snake_vagina_e")){ // Removed egg-laying vagina variants in 0.4
			importedVagina.eggLayer = true;
		}
		
		importedVagina.orificeVagina.stretchedCapacity = handleCapacityLoading(Float.valueOf(vagina.getAttribute("stretchedCapacity")));
		try {
			importedVagina.orificeVagina.squirter = (Boolean.valueOf(vagina.getAttribute("squirter")));
		} catch(Exception ex) {
		}
		if(Main.isVersionOlderThan(version, "0.3.6.9")) {
			importedVagina.orificeVagina.hymen = importedVagina.getOrificeVagina().isVirgin();
			
		} else {
			importedVagina.orificeVagina.hymen = Boolean.valueOf(vagina.getAttribute("hymen"));
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Vagina: "
				+ "<br/>type: "+importedVagina.getType()
				+ "<br/>clitSize: "+importedVagina.clitoris.getClitorisSize()
				+ "<br/>pierced: "+importedVagina.isPierced()

				+ "<br/>wetness: "+importedVagina.orificeVagina.wetness
				+ "<br/>depth: "+importedVagina.orificeVagina.getDepth(null)
				+ "<br/>elasticity: "+importedVagina.orificeVagina.getElasticity()
				+ "<br/>plasticity: "+importedVagina.orificeVagina.getPlasticity()
				+ "<br/>capacity: "+importedVagina.orificeVagina.getCapacity()
				+ "<br/>stretchedCapacity: "+importedVagina.orificeVagina.getStretchedCapacity()
				+ "<br/>virgin: "+importedVagina.orificeVagina.isVirgin());

		Collection<OrificeModifier> vaginaOrificeModifiers = importedVagina.orificeVagina.orificeModifiers;
		vaginaOrificeModifiers.clear();
		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			Element vaginaModifiers = (Element)vagina.getElementsByTagName("vaginaModifiers").item(0);
			if(vaginaModifiers!=null) {
				if(vaginaModifiers.hasAttribute("EXTRA_DEEP")) {
					importedVagina.orificeVagina.setDepth(null, depth+2);
				}
				handleLoadingOfModifiers(OrificeModifier.values(), log, vaginaModifiers, vaginaOrificeModifiers);
			}
			
		} else {
			NodeList mods = vagina.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedVagina.orificeVagina.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
			}
		}
		
		try {
			depth = OrificeDepth.TWO_AVERAGE.getValue();
			depthAttribute = vagina.getAttribute("urethraDepth");
			if(!depthAttribute.isEmpty()) {
				depth = Integer.valueOf(depthAttribute);
			}
			importedVagina.orificeUrethra.depth = depth;
			importedVagina.orificeUrethra.elasticity = (Integer.valueOf(vagina.getAttribute("urethraElasticity")));
			importedVagina.orificeUrethra.plasticity = (Integer.valueOf(vagina.getAttribute("urethraPlasticity")));
			importedVagina.orificeUrethra.capacity = handleCapacityLoading(Float.valueOf(vagina.getAttribute("urethraCapacity")));
			importedVagina.orificeUrethra.stretchedCapacity = handleCapacityLoading(Float.valueOf(vagina.getAttribute("urethraStretchedCapacity")));
			if(!vagina.getAttribute("urethraVirgin").isEmpty()) {
				importedVagina.orificeUrethra.virgin = (Boolean.valueOf(vagina.getAttribute("urethraVirgin")));
			} else {
				importedVagina.orificeUrethra.virgin = true;
			}

			Collection<OrificeModifier> vaginaUrethraOrificeModifiers = importedVagina.orificeUrethra.orificeModifiers;
			vaginaUrethraOrificeModifiers.clear();
			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element urethraModifiersElement = (Element)vagina.getElementsByTagName("urethraModifiers").item(0);
				if (urethraModifiersElement != null) {
					if(urethraModifiersElement.hasAttribute("EXTRA_DEEP")) {
						importedVagina.orificeUrethra.setDepth(null, depth+2);
					}
					handleLoadingOfModifiers(OrificeModifier.values(), log, urethraModifiersElement, vaginaUrethraOrificeModifiers);
				}
				
			} else {
				NodeList mods = vagina.getElementsByTagName("modUrethra");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedVagina.orificeUrethra.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
				}
			}
			
		} catch(Exception ex) {
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Girlcum:");
		
		importedVagina.girlcum = FluidGirlCum.loadFromXML("girlcum", parentElement, doc, importedVagina.getType().getFluidType());
		
		// **************** Wing **************** //
		
		Element wing = (Element)parentElement.getElementsByTagName("wing").item(0);
		int wingSize = 0;
		if(!wing.getAttribute("size").isEmpty()) {
			wingSize = Integer.valueOf(wing.getAttribute("size"));
		}
		Wing importedWing = new Wing(WingType.getWingTypeFromId(wing.getAttribute("type")), wingSize);
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Wing: "
				+ "<br/>type: "+importedWing.getType()+"<br/>"
				+ "<br/>size: "+importedWing.getSizeValue()+"<br/>");


		// ************** Version Overrides **************//

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.5.1")) {
			importedVagina.girlcum.type = importedVagina.getType().getFluidType();
			importedPenis.testicle.cum.type = importedPenis.getType().getTesticleType().getFluidType();
			importedBreast.milk.type = importedBreast.getType().getFluidType();
		}


		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.8")) {
			// Convert all sizes from inch to cm
			importedHair.length *= 2.54;
			importedHorn.length *= 2.54;
			importedFace.tongue.tongueLength *= 2.54;
			importedPenis.length *= 2.54;
			importedVagina.clitoris.clitSize *= 2.54;

			// Convert all capacities from inch to cm
			importedFace.mouth.orificeMouth.capacity *= 2.54;
			importedFace.mouth.orificeMouth.stretchedCapacity *= 2.54;
			importedPenis.orificeUrethra.capacity *= 2.54;
			importedPenis.orificeUrethra.stretchedCapacity *= 2.54;
			importedVagina.orificeVagina.capacity *= 2.54;
			importedVagina.orificeVagina.stretchedCapacity *= 2.54;
			importedVagina.orificeUrethra.capacity *= 2.54;
			importedVagina.orificeUrethra.stretchedCapacity *= 2.54;
			importedAss.anus.orificeAnus.capacity *= 2.54;
			importedAss.anus.orificeAnus.stretchedCapacity *= 2.54;
			importedBreast.nipples.orificeNipples.capacity *= 2.54;
			importedBreast.nipples.orificeNipples.stretchedCapacity *= 2.54;
		}
		
		if(oldPantherReplacement) {
			if(importedArm.getType().getRace()==Race.CAT_MORPH) {
				importedArm.setType(null, ArmType.getArmTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedAss.getType().getRace()==Race.CAT_MORPH) {
				importedAss.setType(null, AssType.getAssTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedBreast.getType().getRace()==Race.CAT_MORPH) {
				importedBreast.setType(null, BreastType.getBreastTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedFace.getType().getRace()==Race.CAT_MORPH) {
				importedFace.setType(null, FaceType.getFaceTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedEye.getType().getRace()==Race.CAT_MORPH) {
				importedEye.setType(null, EyeType.getEyeTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedEar.getType().getRace()==Race.CAT_MORPH) {
				importedEar.setType(null, EarType.getEarTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedHair.getType().getRace()==Race.CAT_MORPH) {
				importedHair.setType(null, HairType.getHairTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedLeg.getType().getRace()==Race.CAT_MORPH) {
				importedLeg.setType(null, LegType.getLegTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedTorso.getType().getRace()==Race.CAT_MORPH) {
				importedTorso.setType(null, TorsoType.getTorsoTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			
			if(importedVagina.getType().getRace()==Race.CAT_MORPH) {
				importedVagina.setType(null, VaginaType.getVaginaTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedPenis.getType().getRace()==Race.CAT_MORPH) {
				importedPenis.setType(null, PenisType.getPenisTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedTail.getType().getRace()==Race.CAT_MORPH) {
				importedTail.setType(null, TailType.getTailTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
		}
		
		Body body = new Body.BodyBuilder(
				importedArm,
				importedAss,
				importedBreast,
				importedFace,
				importedEye,
				importedEar,
				importedHair,
				importedLeg,
				importedTorso,
				importedBodyMaterial,
				importedGenitalArrangement,
				importedHeight,
				importedFemininity,
				importedBodySize,
				importedMuscle)
						.vagina(importedVagina)
						.penis(importedPenis)
						.horn(importedHorn)
						.antenna(importedAntenna)
						.tail(importedTail)
						.wing(importedWing)
						.spinneret(importedSpinneret)
						.tussy(importedTussy)
						.tentacle(importedTentacle)
						.build();


		// **************** Crotch Breasts **************** //

		breasts = (Element)parentElement.getElementsByTagName("breastsCrotch").item(0);
		nipples = (Element)parentElement.getElementsByTagName("nipplesCrotch").item(0);
		BreastCrotch importedCrotchBreast = null;

		if(breasts!=null) {
			breastShape = BreastShape.ROUND;
			try {
				breastShape = BreastShape.valueOf(breasts.getAttribute("shape"));
			} catch(Exception e) {
			}

			milkStorage = 0;
			try {
				if(!breasts.getAttribute("lactation").isEmpty()) {
					milkStorage = Integer.valueOf(breasts.getAttribute("lactation"));
				} else {
					milkStorage = Integer.valueOf(breasts.getAttribute("milkStorage"));
				}
			} catch(Exception ex) {
			}

			AbstractBreastType crotchBoobType = BreastType.getBreastTypeFromId(breasts.getAttribute("type"));
			if(oldPantherReplacement) {
				if(crotchBoobType.getRace()==Race.CAT_MORPH) {
					crotchBoobType = BreastType.getBreastTypes(Race.getRaceFromId("innoxia_panther")).get(0);
				}
			}
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.6") && importedLeg.getLegConfiguration().isBipedalPositionedCrotchBoobs()) { // Reset crotch-boob type as I accidentally applied crotch-boobs to demons
				if(body.isFeminine()) {
					crotchBoobType = RacialBody.valueOfRace(body.getRace()).getBreastCrotchType();
				} else {
					crotchBoobType = BreastType.NONE;
				}
			}
			
			depth = OrificeDepth.TWO_AVERAGE.getValue();
			depthAttribute = nipples.getAttribute("depth");
			if(!depthAttribute.isEmpty()) {
				depth = Integer.valueOf(depthAttribute);
			}
			
			importedCrotchBreast = new BreastCrotch(crotchBoobType,
					breastShape,
					Integer.valueOf(breasts.getAttribute("size")),
					milkStorage,
					Integer.valueOf(breasts.getAttribute("rows")),
					Integer.valueOf(nipples.getAttribute("nippleSize")),
					NippleShape.valueOf(nipples.getAttribute("nippleShape")),
					Integer.valueOf(nipples.getAttribute("areolaeSize")),
					AreolaeShape.valueOf(nipples.getAttribute("areolaeShape")),
					Integer.valueOf(breasts.getAttribute("nippleCountPerBreast")),
					Float.valueOf(nipples.getAttribute("capacity")),
					depth,
					Integer.valueOf(nipples.getAttribute("elasticity")),
					Integer.valueOf(nipples.getAttribute("plasticity")),
					Boolean.valueOf(nipples.getAttribute("virgin")));

			try {
				importedCrotchBreast.milkStored = Float.valueOf(breasts.getAttribute("storedMilk"));
				importedCrotchBreast.milkRegeneration = Integer.valueOf(breasts.getAttribute("milkRegeneration"));
				if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.2")) { // Change from percentage-based to set value:
					importedCrotchBreast.milkRegeneration = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay();
				}
			} catch(Exception ex) {
			}

			importedCrotchBreast.nipples.crotchNipples = true;
			importedCrotchBreast.nipples.orificeNipples.stretchedCapacity = (Float.valueOf(nipples.getAttribute("stretchedCapacity")));
			importedCrotchBreast.nipples.pierced = (Boolean.valueOf(nipples.getAttribute("pierced")));
//			importedCrotchBreast.nipples.areolaeShape = (AreolaeShape.valueOf(nipples.getAttribute("areolaeShape")));

			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Crotch Breasts:"
					+ "<br/>type: "+importedCrotchBreast.getType()
					+ "<br/>size: "+importedCrotchBreast.getSize()
					+ "<br/>rows: "+importedCrotchBreast.getRows()
					+ "<br/>lactation: "+importedCrotchBreast.getRawMilkStorageValue()
					+ "<br/>nippleCountPer: "+importedCrotchBreast.getNippleCountPerBreast()

					+ "<br/><br/>Nipples:"
					+ "<br/>depth: "+importedCrotchBreast.nipples.orificeNipples.getDepth(null)
					+ "<br/>elasticity: "+importedCrotchBreast.nipples.orificeNipples.getElasticity()
					+ "<br/>plasticity: "+importedCrotchBreast.nipples.orificeNipples.getPlasticity()
					+ "<br/>capacity: "+importedCrotchBreast.nipples.orificeNipples.getRawCapacityValue()
					+ "<br/>stretchedCapacity: "+importedCrotchBreast.nipples.orificeNipples.getStretchedCapacity()
					+ "<br/>virgin: "+importedCrotchBreast.nipples.orificeNipples.isVirgin()
					+ "<br/>pierced: "+importedCrotchBreast.nipples.isPierced()
					+ "<br/>nippleSize: "+importedCrotchBreast.nipples.getNippleSize()
					+ "<br/>nippleShape: "+importedCrotchBreast.nipples.getNippleShape()
					+ "<br/>areolaeSize: "+importedCrotchBreast.nipples.getAreolaeSize()
					+ "<br/>areolaeShape: "+importedCrotchBreast.nipples.getAreolaeShape()
					+"<br/>Modifiers:");


			Collection<OrificeModifier> crotchNippleOrificeModifiers = importedCrotchBreast.nipples.orificeNipples.orificeModifiers;
			crotchNippleOrificeModifiers.clear();
			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element nippleModifiersElement = (Element)nipples.getElementsByTagName("nippleModifiers").item(0);
				if (nippleModifiersElement != null) {
					if(nippleModifiersElement.hasAttribute("EXTRA_DEEP")) {
						importedCrotchBreast.nipples.orificeNipples.setDepth(null, depth+2);
					}
					handleLoadingOfModifiers(OrificeModifier.values(), log, nippleModifiersElement, crotchNippleOrificeModifiers);
				}
				
			} else {
				NodeList mods = nipples.getElementsByTagName("mod");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedCrotchBreast.nipples.orificeNipples.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
				}
			}

			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Milk:");

			if(parentElement.getElementsByTagName("milkCrotch").item(0)==null) {
				importedCrotchBreast.milk = FluidMilk.loadFromXML("milk", parentElement, doc, importedCrotchBreast.getType().getFluidType(), true);
			} else {
				importedCrotchBreast.milk = FluidMilk.loadFromXML("milkCrotch", parentElement, doc, importedCrotchBreast.getType().getFluidType(), true);
			}
			if(Main.isVersionOlderThan(Main.VERSION_NUMBER, "0.2.5.1")) {
				importedCrotchBreast.milk.type = importedCrotchBreast.getType().getFluidType();
			}
		}


		if(importedCrotchBreast!=null) {
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.8")) {
				importedCrotchBreast.nipples.orificeNipples.capacity *= 2.54;
				importedCrotchBreast.nipples.orificeNipples.stretchedCapacity *= 2.54;
			}

			body.setBreastCrotch(importedCrotchBreast);
		}
		
		body.setSubspeciesOverride(importedSubspeciesOverride);
		body.loadedSubspecies = importedLoadedSubspecies;
		
		body.setPiercedStomach(Boolean.valueOf(element.getAttribute("piercedStomach")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set piercedStomach: "+Boolean.valueOf(element.getAttribute("piercedStomach")));
		
		try {
			if(element.getAttribute("takesAfterMother") != null && !element.getAttribute("takesAfterMother").isEmpty()) {
				body.setTakesAfterMother(Boolean.valueOf(element.getAttribute("takesAfterMother")));
			}
		} catch(Exception ex) {	
		}
		
		if(element.getAttribute("pubicHair")!=null && !element.getAttribute("pubicHair").isEmpty()) {
			try {
				body.setPubicHair(BodyHair.valueOf(element.getAttribute("pubicHair")));
				Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set pubicHair: "+body.getPubicHair());
			} catch(IllegalArgumentException e) {
				body.pubicHair = BodyHair.ZERO_NONE;
				Main.game.getCharacterUtils().appendToImportLog(log, "<br/>pubic hair: OLD_VALUE - Set to NONE");
			}
		}
		
		NodeList bodyCoverings = element.getElementsByTagName("bodyCovering");
		for(int i = 0; i < bodyCoverings.getLength(); i++){
			Element e = ((Element)bodyCoverings.item(i));

			String type = e.getAttribute("type");
			if(type.equals("HORN_COW") || type.equals("HORN_DEMON")) {
				type = "HORN";
			}
			try {
				String colourPrimary = e.getAttribute("colourPrimary");
				if(colourPrimary.isEmpty()) {
					colourPrimary = e.getAttribute("c1");
				}
				String colourSecondary = e.getAttribute("colourSecondary");
				if(colourSecondary.isEmpty()) {
					colourSecondary = e.getAttribute("c2");
				}
				if(colourSecondary.isEmpty()) {
					colourSecondary = colourPrimary; // If secondary colour is missing, then it's the same as the primary
				}
				
				if(type.startsWith("HAIR_")) {
					if(colourPrimary.equals("COVERING_TAN")) {
						colourPrimary = "COVERING_DIRTY_BLONDE";
					}
					if(colourSecondary.equals("COVERING_TAN")) {
						colourSecondary = "COVERING_DIRTY_BLONDE";
					}
				}

				if(type.startsWith("EYE_")) {
					if(colourPrimary.equals("COVERING_BLUE")) {
						colourPrimary = "COVERING_BLUE_LIGHT";
					}
					if(colourSecondary.equals("COVERING_BLUE")) {
						colourSecondary = "COVERING_BLUE_LIGHT";
					}
				}

				AbstractBodyCoveringType coveringType = BodyCoveringType.getBodyCoveringTypeFromId(type);
				CoveringPattern loadedPattern = CoveringPattern.valueOf(e.getAttribute("pattern"));
				if(!coveringType.getAllPatterns().containsKey(loadedPattern)) {
					loadedPattern = Util.getRandomObjectFromWeightedMap(coveringType.getNaturalPatterns());
				}
				if(e.getAttribute("modifier").isEmpty()) {
					body.setBodyCoveringForXMLImport(coveringType,
							loadedPattern,
							PresetColour.getColourFromId(colourPrimary),
							!e.getAttribute("g1").isEmpty()?Boolean.valueOf(e.getAttribute("g1")):false,
							PresetColour.getColourFromId(colourSecondary),
							!e.getAttribute("g2").isEmpty()?Boolean.valueOf(e.getAttribute("g2")):false);
					
				} else { //TODO
					CoveringModifier modifier = CoveringModifier.valueOf(e.getAttribute("modifier"));
					
					body.setBodyCoveringForXMLImport(coveringType,
							loadedPattern,
							coveringType.getNaturalModifiers().contains(modifier) || coveringType.getExtraModifiers().contains(modifier) ? modifier : coveringType.getNaturalModifiers().get(0),
							PresetColour.getColourFromId(colourPrimary),
							!e.getAttribute("g1").isEmpty()?Boolean.valueOf(e.getAttribute("g1")):false,
							PresetColour.getColourFromId(colourSecondary),
							!e.getAttribute("g2").isEmpty()?Boolean.valueOf(e.getAttribute("g2")):false);
				}
				
				if(oldPantherReplacement && coveringType==BodyCoveringType.FELINE_FUR) {
					Covering felineCovering = body.getCovering(coveringType, false);
					body.setBodyCoveringForXMLImport(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_fur"),
							felineCovering.getPattern(),
							felineCovering.getModifier(),
							felineCovering.getPrimaryColour(),
							felineCovering.isPrimaryGlowing(),
							felineCovering.getSecondaryColour(),
							felineCovering.isSecondaryGlowing());
					body.addBodyCoveringTypesDiscovered(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_fur"));
				}
				
//				if(!e.getAttribute("discovered").isEmpty() && Boolean.valueOf(e.getAttribute("discovered"))) {
					body.addBodyCoveringTypesDiscovered(BodyCoveringType.getBodyCoveringTypeFromId(type));
//				}
				
				Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set bodyCovering: "+colourPrimary+", "+colourSecondary);
			} catch(Exception ex) {
			}
		}
		
		
		try {
			Element heavyMakeupElement = (Element)element.getElementsByTagName("heavyMakeup").item(0);
			
			NodeList bodyTypes = heavyMakeupElement.getElementsByTagName("type");
			for(int i = 0; i < bodyTypes.getLength(); i++){
				Element e = ((Element)bodyTypes.item(i));
				body.addHeavyMakeup(BodyCoveringType.getBodyCoveringTypeFromId(e.getTextContent()));
			}
		} catch(Exception ex) {	
		}
		
		body.feral = feralBody;
		
		body.addDiscoveredBodyCoveringsFromMaterial(importedBodyMaterial);
		
		body.calculateRace(null);
		
		// Converting harpy bald eagles to raptor bald eagles:
		if(Main.isVersionOlderThan(version, "0.4.2.5")) {
			if(body.getRace()==Race.HARPY) {
				AbstractBodyCoveringType feathers = body.getBodyMaterial()==BodyMaterial.SLIME?BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.MAIN_FEATHER):BodyCoveringType.FEATHERS;
				AbstractBodyCoveringType headFeathers = body.getBodyMaterial()==BodyMaterial.SLIME?BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.HAIR):BodyCoveringType.HAIR_HARPY;
				
				if(body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_BROWN_DARK && body.getCoverings().get(headFeathers).getPrimaryColour()==PresetColour.COVERING_WHITE) {

					if(body.getArmType().getRace()==Race.HARPY) {
						body.setArmType(null, ArmType.getArmTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getAssType().getRace()==Race.HARPY) {
						body.setAssType(null, AssType.getAssTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getBreastType().getRace()==Race.HARPY) {
						body.setBreastType(null, BreastType.getBreastTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getBreastCrotchType().getRace()==Race.HARPY) {
						body.setBreastCrotchType(null, BreastType.getBreastTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getFaceType().getRace()==Race.HARPY) {
						body.setFaceType(null, FaceType.getFaceTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getEyeType().getRace()==Race.HARPY) {
						body.setEyeType(null, EyeType.getEyeTypeFromId("innoxia_raptor_eye"));
					}
					if(body.getEarType().getRace()==Race.HARPY) {
						body.setEarType(null, EarType.getEarTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getHairType().getRace()==Race.HARPY) {
						body.setHairType(null, HairType.getHairTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getLegType().getRace()==Race.HARPY) {
						body.setLegType(null, LegType.getLegTypeFromId("innoxia_raptor_leg_large"));
					}
					if(body.getTorsoType().getRace()==Race.HARPY) {
						body.setTorsoType(null, TorsoType.getTorsoTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					
					if(body.getVaginaType().getRace()==Race.HARPY) {
						body.setVaginaType(null, VaginaType.getVaginaTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getPenisType().getRace()==Race.HARPY) {
						body.setPenisType(null, PenisType.getPenisTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getTailType().getRace()==Race.HARPY) {
						body.setTailType(null, TailType.getTailTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					
					// coverings:
					coveringCopyConversion(body, BodyCoveringType.FEATHERS, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_feathers"));
					coveringCopyConversion(body, BodyCoveringType.HARPY_SKIN, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_skin"));
					coveringCopyConversion(body, BodyCoveringType.BODY_HAIR_HARPY, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_body_hair"));
					coveringCopyConversion(body, BodyCoveringType.EYE_HARPY, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_eye"));
					coveringCopyConversion(body, BodyCoveringType.HAIR_HARPY, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_hair"));
				}
			}
		}
		
		if(Main.isVersionOlderThan(version, "0.3.0.5")) {
			body.updateNippleCrotchColouring();
		}
		
		return body;
	}
	
	public static void coveringCopyConversion(Body body, AbstractBodyCoveringType coveringTypeToCopy, AbstractBodyCoveringType newCoveringType) {
		Covering covering = body.getCovering(coveringTypeToCopy, false);
		body.setBodyCoveringForXMLImport(newCoveringType,
				covering.getPattern(),
				covering.getModifier(),
				covering.getPrimaryColour(),
				covering.isPrimaryGlowing(),
				covering.getSecondaryColour(),
				covering.isSecondaryGlowing());
		body.addBodyCoveringTypesDiscovered(newCoveringType);
	}

	public static <T extends Enum<T>> void handleLoadingOfModifiers(T[] enumValues, StringBuilder log, Element modifiersElement, Collection<T> modifiers) {
		for(T enumValue : enumValues) {
			String attributeValue = modifiersElement.getAttribute(enumValue.toString());
			if(!attributeValue.isEmpty()) {
				if(Boolean.valueOf(attributeValue)) {
					if(!modifiers.contains(enumValue)) {
						modifiers.add(enumValue);
					}
					if(log!=null) {
						Main.game.getCharacterUtils().appendToImportLog(log, "<br/>"+enumValue.toString()+":true");
					}
				} else if (!attributeValue.isEmpty()) {
					modifiers.remove(enumValue);
					if(log!=null) {
						Main.game.getCharacterUtils().appendToImportLog(log, "<br/>"+enumValue.toString()+":false");
					}
				} else {
					if(log!=null) {
						Main.game.getCharacterUtils().appendToImportLog(log, "<br/>"+enumValue.toString()+":not present, defaulted to "+modifiers.contains(enumValue));
					}
				}
			}
		}
	}
	
	public static float handleCapacityLoading(float input) {
		float loadedCapacity = input;
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.7")) {
			loadedCapacity = Penis.getGenericDiameter((int)loadedCapacity, PenetrationGirth.THREE_AVERAGE);
		}
		return loadedCapacity;
	}
	
	public List<BodyPartInterface> getAllBodyParts() {
		return allBodyParts;
	}
	
	public List<BodyPartInterface> getAllBodyPartsWithAllOrifices() {
		return allBodyPartsExtended;
	}
	
	private String getHeader(String header) {
		return "<p style='padding-top:0; margin-top:0;'>[style.colourDisabled("+header+":)]<br/>";
	}
	
	/**
	 * @param owner
	 * @return
	 */
	public String getDescription(GameCharacter owner) {
		StringBuilder sb = new StringBuilder();
		boolean observant = Main.game.getPlayer().hasTrait(Perk.OBSERVANT, true);
		// Describe race:
		sb.append(getHeader("总览"));
		String colouredHeightValue = "<span style='color:"+this.getHeight().getColour().toWebHexString()+";'>[npc.heightValue]</span>";
		
		String heightDescription = "[npc.she]站直后的身高是"+colouredHeightValue;
		if(owner.isFeral() && !owner.getFeralAttributes().isSizeHeight()) {
			if(owner.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
				heightDescription = "[npc.Her]的体长"+colouredHeightValue+"，再加上[npc.tailLength]长的尾巴，总共长有"
						+ "<span style='color:"+Height.getHeightFromInt(owner.getHeightValue()+owner.getLegTailLength(false)).getColour().toWebHexString()+";'>"
							+ Units.size(owner.getHeightValue()+owner.getLegTailLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG)
						+"</span>";
				
//				heightDescription = " From head to tail,  [npc.she] [npc.verb(measure)] "
//						+Units.size(owner.getHeightValue()+owner.getLegTailLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG);
			} else {
				heightDescription = "[npc.she]从头到尾高度为"+colouredHeightValue;
			}
		}
		
		if (owner.isPlayer()) {
			sb.append("你是[pc.name]，"
							+(owner.getRace()==Race.HUMAN
								?"<span style='color:"+owner.getFemininity().getColour().toWebHexString()+";'>[pc.a_femininity]</span>的[pc.gender(true)][style.colourHuman(人类)]。"
								:"[pc.gender(true)]，是个[pc.a_fullRace(true)]。")
						+ owner.getAppearsAsGenderDescription(true)
						+heightDescription+"。");
		} else {
			if(owner.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer()) && owner.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())) {
				sb.append("[npc.Name]是"
							+(owner.getRace()==Race.HUMAN
								?"<span style='color:"+owner.getFemininity().getColour().toWebHexString()+";'>[npc.a_femininity]</span>的[npc.gender(true)][style.colourHuman(人类)]。"
								:"[npc.gender(true)]，是个[npc.a_fullRace(true)]。")
						+ owner.getAppearsAsGenderDescription(true)
						+ heightDescription);
			} else {
				if(observant) {
					sb.append("凭借着“敏锐”天赋，你辨认出[npc.name]是<span style='color:"+getGender().getColour().toWebHexString()+";'>[npc.a_gender]</span>[npc.raceStage][npc.race]。"
							+ owner.getAppearsAsGenderDescription(true)
							+ heightDescription);
				} else {
					sb.append("[npc.Name]是个[npc.a_fullRace(true)]。"
								+ owner.getAppearsAsGenderDescription(true)
								+ heightDescription);
				}
			}
			if(owner.isSizeDifferenceTallerThan(Main.game.getPlayer())) {
				String descriptor = owner.isFeral() && !owner.getFeralAttributes().isSizeHeight()?"更长":"更高";
				sb.append("，使[npc.herHim]显然比你<span style='color:"+PresetColour.BODY_SIZE_FOUR.toWebHexString()+";'>"+descriptor+"</span>。");
			} else if(owner.isSizeDifferenceShorterThan(Main.game.getPlayer())) {
				sb.append("，使[npc.herHim]显然比你<span style='color:"+PresetColour.BODY_SIZE_ZERO.toWebHexString()+";'>" + (owner.isFeral() && !owner.getFeralAttributes().isSizeHeight()?"更短":"更矮") + "</span>。");
			} else {
				sb.append("。");
			}
		}
		
		switch(owner.getLegConfiguration()) {
			case ARACHNID:
				if(owner.isFeral()) {
					sb.append("[style.colourFeral([npc.Her]的整个身体都已经转化为兽态的[npc.legRace]。"
							+ "[npc.Her]的外生殖器位于[npc.her]兽态[npc.assRace]身体的底部，而[npc.her]肛门则位于[npc.her]兽态腹部的后端。)]");
				} else {
					sb.append("[style.colourFeral([npc.Her]的整个下半身——腰部以下——都已经转化为[npc.legRace]的巨大身躯。"
							+ "[npc.Her]的腿和外生殖器已经完全是野兽的形态，与[npc.a_assRace]的极端相似。"
							+ "[npc.Her]的外生殖器位于[npc.her]兽态[npc.assRace]身体的底部，而[npc.her]肛门则位于[npc.her]兽态腹部的后端。)]");
				}
				break;
			case AVIAN:
				if(owner.isFeral()) {
					sb.append("[style.colourFeral([npc.Her]的整个身体都已经转化为兽态的[npc.legRace]。"
							+ "[npc.Her]的外生殖器位于[npc.her]兽态[npc.assRace]身体的底部偏后的泄殖腔内 。)]");
				} else {
					sb.append("[style.colourFeral([npc.Her]的整个下半身——腰部以下——都已经转化为[npc.legRace]的巨大身躯。"
							+ "[npc.Her]的腿和外生殖器已经完全是野兽的形态，与[npc.a_assRace]的极端相似。"
							+ "[npc.Her]的外生殖器位于[npc.her]兽态[npc.assRace]身体的底部偏后的泄殖腔内 。)]");
				}
				break;
			case CEPHALOPOD:
				if(owner.isFeral()) {
					sb.append("[style.colourFeral([npc.Her]的整个身体都已经转化为兽态的[npc.legRace]。"
							+ "[npc.Her]的外生殖器和肛门位于[npc.her]兽态身体底部的泄殖腔内。)]");
				} else {
					sb.append("[style.colourFeral([npc.Her]的整个下半身——腰部以下——都已经转化为[npc.legRace]的巨大身躯。"
							+ "[npc.Her]的腿和外生殖器几乎完全是野兽的形态，与[npc.a_assRace]的极端相似。"
							+ "[npc.Her]的外生殖器和肛门位于[npc.her]兽态身体底部的泄殖腔内。)]");
				}
				break;
			case BIPEDAL:
			case WINGED_BIPED:
				break;
			case TAIL:
				if(owner.isFeral()) {
					sb.append("[style.colourFeral([npc.Her]的整个身体都已经转化为兽态的[npc.legRace]。"
							+ "[npc.Her]的肛门和外生殖器现在位于[npc.her]兽态身体底部的泄殖腔内。)]");
				} else {
					sb.append("[style.colourFeral([npc.Her]的整个下半身——腰部以下——都已经转化为[npc.legRace]的尾巴。"
							+ "[npc.Her]的肛门和外生殖器保留了一些人类的特征，现在位于前向泄殖腔内。)]");
	
					if(owner.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE)) {
						sb.append("由于附近没有水体，[npc.her]的尾巴自动[style.colourTan(转化为了腿)]，令[npc.herHim]能够轻易地穿越陆地。");
					}
				}
				break;
			case TAIL_LONG:
				if(owner.isFeral()) {
					sb.append("[style.colourFeral([npc.Her]的整个身体都已经转化为兽态的[npc.legRace]。"
							+ "[npc.Her]的肛门和外生殖器现在位于[npc.her]兽态身体底部的泄殖腔内。)]");
				} else {
					sb.append("[style.colourFeral([npc.Her]的整个下半身——腰部以下——都已经转化为[npc.a_legRace]的长尾，"
								+ "长度为"+Units.size(owner.getLegTailLength(false))+"。"
							+ "[npc.Her]的肛门和外生殖器已经完全是野兽形态，与[npc.a_assRace]的完全相同，现在位于前向的泄殖腔内。)]");
				}
				break;
			case QUADRUPEDAL:
				if(owner.isFeral()) {
					sb.append("[style.colourFeral([npc.Her]的整个身体都已经转化为兽态的[npc.legRace]。"
							+ "[npc.Her]的外生殖器和肛门位于[npc.her]兽态身体的后端。)]");
				} else {
					sb.append("[style.colourFeral([npc.Her]的整个下半身——腰部以下——都已经转化为[npc.a_legRace]的样子。"
							+ "[npc.Her]的腿、尾巴、屁股、外生殖器已经完全是野兽的形态，与[npc.a_assRace]的完全相同。"
							+ "[npc.Her]的外生殖器和肛门位于[npc.her]兽态身体的后端，与通常的[npc.a_assRace]位于同样位置。)]");
				}
				break;
		}

		if (Main.getProperties().hasValue(PropertyValue.ageContent) && owner != Main.game.getPlayer()) {
			sb.append(Conversions.getAgeDescriptor(owner));
		}
		sb.append("</p>");
		
		switch(this.getBodyMaterial()) {
			case FLESH:
				break;
			case SLIME:
				sb.append("<p>"
							+ "[npc.NamePos]的整个身体都由[npc.skinFullDescription(true)]制成，除了心脏原本的位置是一个发光的小圆球！"
							+ "[npc.She]所有的身体部位都不需要穿孔就可以装备珠宝，因为[npc.she]可以随意化形身体！"
						+ "</p>");
				break;
			case SILICONE:
			case AIR:
			case ARCANE:
			case STONE:
			case RUBBER:
			case ICE:
			case WATER:
			case FIRE:
				sb.append("<p>"
							+ "[npc.NamePos]的整个身体都由"
								+ "<b style='color:"+this.getBodyMaterial().getColour().toWebHexString()+";'>"+this.getBodyMaterial().getName()+"</b>制成，除了心脏原本的位置是一个黑曜石制的小圆球！"
						+ "</p>");
				break;
		}
		
		// Describe face (ears, eyes & horns):
		// Femininity:
		sb.append(getHeader("面部"));
		sb.append(face.getType().getBodyDescription(owner));
		if(owner.getBlusher().getPrimaryColour()!=PresetColour.COVERING_NONE) {
			sb.append("[npc.SheIsFull]的面颊上轻覆着一层淡淡的"+owner.getBlusher().getColourDescriptor(owner, true, false)+"腮红。");
		}
		
		// Hair:
		if (hair.getRawLengthValue() == 0) {
			if(face.isBaldnessNatural() || owner.isFeral()) {
				sb.append("[npc.Her]的脑袋[npc.materialDescriptor][npc.faceFullDescription(true)]。");
			} else {
				sb.append("[npc.SheHasFull]的头上没有头发，露出了覆盖[npc.her]的[npc.faceSkin]。");
			}
			
		} else {
			sb.append(hair.getType().getBodyDescription(owner).trim());
			
			if(hair.getType().getTags().contains(BodyPartTag.HAIR_NATURAL_MANE) && owner.getFaceType().getRace()==hair.getType().getRace()) {
				sb.append("，有如一缕鬃毛沿着后颈披落下来，"); // If hair and face races match, the mane is fully formed
			} else {
				sb.append("，");
			}
			
			switch (hair.getStyle()) {
				case NONE:
					sb.append("没有什么造型。");
					break;
				case BRAIDED:
					sb.append("编成了长辫。");
					break;
				case CURLY:
					sb.append("披散着，十分卷曲。");
					break;
				case LOOSE:
					sb.append("披散着，没有什么造型。");
					break;
				case PONYTAIL:
					sb.append("扎成了马尾。");
					break;
				case STRAIGHT:
					sb.append("经过拉直，披散着。");
					break;
				case TWIN_TAILS:
					sb.append("扎成了双马尾。");
					break;
				case WAVY:
					sb.append("披散着，卷曲似波浪。");
					break;
				case MOHAWK:
					sb.append("打扮成了莫霍克发型。");
					break;
				case AFRO:
					sb.append("打扮成了爆炸头。");
					break;
				case SIDECUT:
					sb.append("理成了侧剃。");
					break;
				case BOB_CUT:
					sb.append("打扮成了波波头。");
					break;
				case PIXIE:
					sb.append("打扮成了精灵短发。");
					break;
				case SLICKED_BACK:
					sb.append("梳成了背头。");
					break;
				case MESSY:
					sb.append("十分凌乱，没什么造型。");
					break;
				case HIME_CUT:
					sb.append("拉直后打扮成了姬发式。");
					break;
				case CHONMAGE:
					sb.append("拉直后涂油保养，扎成了月代头髻。");
					break;
				case TOPKNOT:
					sb.append("打扮成了髻发。");
					break;
				case DREADLOCKS:
					sb.append("编成了脏辫。");
					break;
				case BIRD_CAGE:
					sb.append("编成了优美的鸟笼形"+UtilText.returnStringAtRandom("。","，里面并没有鸟。"));
					break;
				case TWIN_BRAIDS:
					sb.append("编成了长长的双麻花辫。");
					break;
				case DRILLS:
					sb.append("打扮成了公主卷发。");
					break;
				case LOW_PONYTAIL:
					sb.append("扎成了低马尾。");
					break;
				case CROWN_BRAID:
					sb.append("编成了冠辫发。");
					break;
				case BUN:
					sb.append("打扮成了丸子头。");
					break;
				case CHIGNON:
					sb.append("打扮成了低髻。");
					break;
				case SIDE_BRAIDS:
					sb.append("编成麻花辫后侧搭在脸旁。");
					break;
				case SIDE_PARTED:
					sb.append("完全梳向了头的其中一侧。");
					break;
			}
		}
		if (hair.isNeckFluff()) {
			sb.append("一大片[npc.hair(true)]生长在脖子和胸口偏上的位置。");
		}
		
		// Horns:
		
		if(owner.hasHorns()) {
			sb.append(""+owner.getHornType().getBodyDescription(owner));
		}
		
		// Antenna:

		if(owner.hasAntennae()) {
			sb.append(""+owner.getAntennaType().getBodyDescription(owner));
		}
		
		// Nose:
		
		if(face.isPiercedNose()) {
			sb.append("[npc.Her]的[npc.nose]已经穿过孔。");
		}
		
		// Eyes:

		if(owner.isAreaKnownByCharacter(CoverableArea.EYES, Main.game.getPlayer())) {
			sb.append(getEyeDescription(owner));
		} else {
			sb.append("[style.colourDisabled(你从未见过[npc.her]的眼睛，所以也不知道到底长什么样子。)]");
		}
		
		// Ear:
		if(owner.isFeral()) {
			sb.append("[npc.She]有一对[npc.earRace]耳朵，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，已经打过耳洞#ENDIF。");
			
		} else {
			sb.append(""+ear.getType().getBodyDescription(owner));
			
			if(Main.game.isFacialHairEnabled()) {
				if(owner.getFacialHairType().getType()==BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR) {
					switch(owner.getFacialHair()) {
						case ZERO_NONE:
							if(!owner.isFeminine()) {
								sb.append("[npc.She]的[npc.face]上看不出任何"+owner.getFacialHairType().getName(owner)+"的迹象。");
							}
							break;
						case ONE_STUBBLE:
							sb.append("[npc.SheHasFull]的[npc.face]上长着一茬"+owner.getFacialHairType().getFullDescription(owner, true)+"。");
							break;
						case TWO_MANICURED:
							sb.append("[npc.SheHasFull]的[npc.face]上长着一片又短又硬的"+owner.getFacialHairType().getFullDescription(owner, true)+"，与胡须很相似。");
							break;
						case THREE_TRIMMED:
							sb.append("[npc.SheHasFull]的[npc.face]上长着一片硬质的"+owner.getFacialHairType().getFullDescription(owner, true)+"，与胡须很相似。");
							break;
						case FOUR_NATURAL:
							sb.append("[npc.SheHasFull]的[npc.face]上长着一片自然生长的"+owner.getFacialHairType().getFullDescription(owner, true)+"，与胡须很相似。");
							break;
						case FIVE_UNKEMPT:
							sb.append("[npc.SheHasFull]的[npc.face]上长着一片凌乱的"+owner.getFacialHairType().getFullDescription(owner, true)+"，与胡须很相似。");
							break;
						case SIX_BUSHY:
							sb.append("[npc.SheHasFull]的[npc.face]上长着一片浓厚的"+owner.getFacialHairType().getFullDescription(owner, true)+"，与胡须很相似。");
							break;
						case SEVEN_WILD:
							sb.append("[npc.SheHasFull]的[npc.face]上长着一片野蛮生长的"+owner.getFacialHairType().getFullDescription(owner, true)+"，与胡须很相似。");
							break;
					}
				} else {
					switch(owner.getFacialHair()) {
						case ZERO_NONE:
							if(!owner.isFeminine()) {
								sb.append("没有任何面部"+owner.getFacialHairType().getName(owner)+"的踪迹。");
							}
							break;
						case ONE_STUBBLE:
							sb.append("[npc.SheHasFull]的[npc.face]上长着一茬"+owner.getFacialHairType().getFullDescription(owner, true)+"。");
							break;
						case TWO_MANICURED:
							sb.append("[npc.SheHasFull]的[npc.face]上长着一小片"+owner.getFacialHairType().getFullDescription(owner, true)+"。");
							break;
						case THREE_TRIMMED:
							sb.append("[npc.SheHasFull]的[npc.face]上长着一片修剪整齐的"+owner.getFacialHairType().getFullDescription(owner, true)+"。");
							break;
						case FOUR_NATURAL:
							sb.append("[npc.SheHasFull]的[npc.face]上长着一片自然生长的"+owner.getFacialHairType().getFullDescription(owner, true)+"。");
							break;
						case FIVE_UNKEMPT:
							sb.append("[npc.SheHasFull]的[npc.face]上长着一片凌乱的"+owner.getFacialHairType().getFullDescription(owner, true)+"。");
							break;
						case SIX_BUSHY:
							sb.append("[npc.SheHasFull]的[npc.face]上长着一片浓厚的"+owner.getFacialHairType().getFullDescription(owner, true)+"。");
							break;
						case SEVEN_WILD:
							sb.append("[npc.SheHasFull]的[npc.face]上长着一片野蛮生长的"+owner.getFacialHairType().getFullDescription(owner, true)+"。");
							break;
					}
				}
			}
		}
		sb.append("</p>");
		
		
		// Mouth & lips:
		sb.append(getHeader("嘴巴"));
		sb.append(face.getMouth().getType().getBodyDescription(owner));
		
		// Throat modifiers:
		for(OrificeModifier om : OrificeModifier.values()) {
			if(owner.hasFaceOrificeModifier(om)) {
				switch(om) {
					case PUFFY:
						sb.append("[npc.Her][npc.lips]十分饱满，比普通的更加有肉感。");
						break;
					case MUSCLE_CONTROL:
						sb.append("[npc.SheHasFull]的喉咙拥有一片活动自如的肌肉，使得[npc.herHim]能够精妙地抓住、挤压任何入侵的物体。");
						break;
					case RIBBED:
						sb.append("[npc.her]的喉咙内部长满了敏感的肉质突起，被刺激时可以给[npc.herHim]提供额外的快感。");
						break;
					case TENTACLED:
						sb.append("[npc.Her]的喉咙长满了细小的触手，自行蜿蜒扭动着。");
						break;
				}
			}
		}
		
		
		// Tongue & blowjob:
		if(owner.isFeral()) {
			sb.append("[npc.Her]的嘴巴里有一根[npc.tongueColour(true)]的[npc.tongueRace][npc.tongue]，看上去[npc.a_tongueLength]#IF(npc.isPiercedTongue())，且已经穿过孔#ENDIF。");
		} else {
			sb.append(""+face.getTongue().getType().getBodyDescription(owner));
		}
		
		for(TongueModifier tm : TongueModifier.values()) {
			if(owner.hasTongueModifier(tm)) {
				switch(tm) {
					case RIBBED:
						sb.append("整根[npc.tongue]上都长满了稍硬的肉质突起，绝对能为任何被插入的腔穴带来额外的快感。");
						break;
					case TENTACLED:
						sb.append("一片细小的触手覆盖在[npc.tongue]表面，自行蜿蜒扭动着。");
						break;
					case BIFURCATED:
						sb.append("在靠近尖端处一分为二，像蛇一样分叉开来。");
						break;
					case FLAT:
						if(owner.hasTongueModifier(TongueModifier.WIDE)) {
							sb.append("比起一般的来说显得既平又宽。");
						} else {
							sb.append("比起一般来的来说显得更加平直。");
						}
						break;
					case STRONG:
						sb.append("比起一般的来说更加有力。");
						break;
					case WIDE:
						if(!owner.hasTongueModifier(TongueModifier.FLAT)) {
							sb.append("比起一般的来说显得更加宽大。");
						}
						break;
					case TAPERED:
						sb.append("从根部开始逐渐收紧，越靠近尖端就变得越细。");
						break;
				}
			}
		}
		
		
		if (owner.isPlayer() || owner.isAreaKnownByCharacter(CoverableArea.MOUTH, Main.game.getPlayer())) {
			if(face.getMouth().getOrificeMouth().isVirgin()) { //TODO
				sb.append("[npc.SheHas][style.colourExcellent(从来没有口交过)]，所以不清楚[npc.she]的喉咙能够容纳多大的物体。</span>");
				
			} else {
				boolean virginityLossFound = false;
				for(SexAreaPenetration pt : SexAreaPenetration.values()) {
					if(pt.isTakesVirginity()) {
						if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pt))!=null) {
							sb.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pt)) + "</span>");
							virginityLossFound = true;
							break;
						}
					}
				}
				if(!virginityLossFound) {
					sb.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name]已经失去了口部贞操。</span>");
				}
				
				sb.append("[npc.her]"+Capacity.getCapacityFromValue(face.getMouth().getOrificeMouth().getStretchedCapacity()).getDescriptor(true)+"的[npc.mouth]能够轻松容纳直径达"
						+ "[style.colourSex("+ Units.size(Capacity.getMaximumComfortableDiameter(face.getMouth().getOrificeMouth().getElasticity(), face.getMouth().getOrificeMouth().getRawCapacityValue(), true)) + ")]的物体。");
				
				if(Main.game.isPenetrationLimitationsEnabled()) {
					switch(owner.getFaceDepth()) {
						default:
							sb.append("[npc.Her]的喉咙<span style='color:"+owner.getFaceDepth().getColour().toWebHexString()+";'>[npc.throatDepth]</span>，");
							break;
						case TWO_AVERAGE:
							sb.append("[npc.Her]的喉咙<span style='color:"+owner.getFaceDepth().getColour().toWebHexString()+";'>深度适中</span>，");
							break;
					}
					if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
						if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
							sb.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，将其能够[style.colourMinorGood(舒适地)]容纳的物体最大长度增加至"
									+ "[style.colourSex("+Units.size(owner.getFaceMaximumPenetrationDepthUncomfortable())+")]。");
						} else {
							sb.append("使得[npc.herHim]能够[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getFaceMaximumPenetrationDepthComfortable())+")]长的物体插入，"
									+ "以及[style.colourMinorBad(不舒服地)]容纳[style.colourSex("+Units.size(owner.getFaceMaximumPenetrationDepthUncomfortable())+")]长的物体插入。");
						}
						
					} else {
						if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
							sb.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，加之[npc.her]的身体由[npc.bodyMaterial]构成，"
									+ "[npc.she]能够[style.colourMinorGood(舒适地)]容纳[style.colourSex(任何长度)]的物体。");
						} else {
							sb.append("使得[npc.herHim]能够[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getFaceMaximumPenetrationDepthComfortable())+")]长的物体插入，"
									+ "且由于[npc.her]的身体[npc.bodyMaterial]构成，[npc.she]能够[style.colourMinorBad(不舒服地)]容纳[style.colourSex(任何长度)]的物体。");
						}
					}
				}
				
				// Throat wetness:
				switch (face.getMouth().getOrificeMouth().getWetness(owner)) {
					case ZERO_DRY:
						sb.append("[npc.Her]的嘴巴和喉咙[style.colourWetness(极其干燥)]，[npc.she]很难分泌出唾液。");
						break;
					case ONE_SLIGHTLY_MOIST:
						sb.append("[npc.Her]的嘴巴和喉咙[style.colourWetness(比一般人更干燥)]，[npc.she]只能分泌出一点唾液。");
						break;
					case TWO_MOIST:
						sb.append("[npc.Her]的嘴巴和喉咙[style.colourWetness(稍有些干燥)]，[npc.she]比一般人分泌的唾液要更少。");
						break;
					case THREE_WET:
						sb.append("[npc.Her]的嘴巴和喉咙[style.colourWetness(还算湿润)]，[npc.she]能够分泌适量唾液。");
						break;
					case FOUR_SLIMY:
						sb.append("[npc.Her]的嘴巴和喉咙[style.colourWetness(更湿润些)]，[npc.she]比一般人分泌的唾液要更多。");
						break;
					case FIVE_SLOPPY:
						sb.append("[npc.Her]的嘴巴和喉咙[style.colourWetness(相当湿润)]，[npc.she]比一般人分泌的唾液要多得多。");
						break;
					case SIX_SOPPING_WET:
						sb.append("[npc.Her]的嘴巴和喉咙[style.colourWetness(总是十分湿润粘滑)]，[npc.she]能够分泌大量唾液，每过几秒钟就能感觉到口中有新的唾液。");
						break;
					case SEVEN_DROOLING:
						sb.append("[npc.Her]的嘴巴和喉咙[style.colourWetness(总是溢满了唾液)]，"
								+ "尽管[npc.her]不断地吞下这些粘滑的东西，但依然有口水从嘴边溢出。");
						break;
				}

				// Elasticity & plasticity:
				sb.append("[npc.Her]的喉咙");
				switch (face.getMouth().getOrificeMouth().getElasticity()) {
					case ZERO_UNYIELDING:
						sb.append("[style.colourElasticity(几乎不会扩张)]，");
						break;
					case ONE_RIGID:
						sb.append("[style.colourElasticity(想要被撑开需要费九牛二虎之力)]，");
						break;
					case TWO_FIRM:
						sb.append("[style.colourElasticity(不太容易撑开)]，");
						break;
					case THREE_FLEXIBLE:
						sb.append("被用于性交时[style.colourElasticity(撑不太开)]，");
						break;
					case FOUR_LIMBER:
						sb.append("[style.colourElasticity(被扩张时有些抵抗)]，");
						break;
					case FIVE_STRETCHY:
						sb.append("[style.colourElasticity(可以比较简单地撑开)]，");
						break;
					case SIX_SUPPLE:
						sb.append("[style.colourElasticity(可以相当轻松地撑开)]，");
						break;
					case SEVEN_ELASTIC:
						sb.append("[style.colourElasticity(弹性极佳)]，");
						break;
				}
				sb.append("在被使用之后，会"+face.getMouth().getOrificeMouth().getPlasticity().getDescription()+"。");
			}
			
		} else {
			sb.append("[style.colourDisabled(你对[npc.herHim]的认识还不够充分，不知道[npc.she]的口交能力。)]");
		}
		
		sb.append("</p>");

		
		// Describe body:
		sb.append(getHeader("躯干"));
		if(owner.isFeral()) {
			sb.append("[style.colourFeral([npc.Her]的躯干表面上看完全是兽态的，与普通的[npc.race]并无二致。)] ");
		}
		sb.append(owner.getTorsoType().getBodyDescription(owner));
		sb.append("[npc.SheHasFull]拥有<span style='color:"+ BodySize.valueOf(getBodySize()).getColour().toWebHexString() + ";'>" + BodySize.valueOf(getBodySize()).getName(true) + "</span>且"
						+ "<span style='color:"+ Muscle.valueOf(getMuscle()).getColour().toWebHexString() + ";'>" +Muscle.valueOf(getMuscle()).getName(false) + "</span>"
							+ "的身体，塑造了[npc.herHim]<span style='color:"+ owner.getBodyShape().toWebHexStringColour() + ";'>[npc.bodyShape]</span>的身材。");
		if(torso.getType().getTags().contains(BodyPartTag.ALLOWS_FLIGHT)) {
			sb.append("[style.colourBlue([npc.namePos]身体的特殊构造使得[npc.herHim]可以飞翔！)]");
		}
		
		
		// Pregnancy:
		if(owner.isVisiblyPregnant()) {
			if(owner.getLegConfiguration()!=LegConfiguration.BIPEDAL) {
				sb.append("[npc.her][style.colourFeral(兽态身躯)]的肚子");
			} else {
				sb.append("[npc.Her]的肚子");
			}
			if(owner.hasStatusEffect(StatusEffect.PREGNANT_1)){
				sb.append("微微隆起，只需要仔细一看就能知道<span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc.sheIs]怀孕了</span>。");
				
			} else if(owner.hasStatusEffect(StatusEffect.PREGNANT_2)){
				sb.append("高高隆起，只需要朝这边看一眼就能知道<span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc.sheIs]怀孕了</span>。");
			
			} else if(owner.hasStatusEffect(StatusEffect.PREGNANT_3)){
				sb.append("胀得巨大，只需要稍微朝这边扫一眼就能知道"
						+ "<span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc.sheIs]临盆了</span>。");
			}
		}
		sb.append("</p>");
		
		
		
		// Breasts:
		sb.append(getHeader("胸部"));
		if(owner.isFeral() && !owner.getFeralAttributes().isBreastsPresent()) {
			sb.append("由于[npc.nameHasFull]的身体是兽态[npc.race]，[npc.she]没有乳房！");
			
		} else {
			Breast viewedBreast = breast;
			if(Main.game.getPlayer().hasIngestedPsychoactiveFluidType(FluidTypeBase.MILK)) {
				viewedBreast = new Breast(breast.getType(),
						breast.getShape(),
						(int)(breast.getRawSizeValue()*(1.75f)),
						(int)((breast.getRawMilkStorageValue()+100)*(2.25f)),
						breast.getRows(),
						breast.getNipples().getNippleSizeValue(),
						breast.getNipples().getNippleShape(),
						breast.getNipples().getAreolaeSizeValue(),
						breast.getNipples().getAreolaeShape(),
						breast.getNippleCountPerBreast(),
						breast.getNipples().getOrificeNipples().getRawCapacityValue(),
						breast.getNipples().getOrificeNipples().getDepth(null).getValue(),
						breast.getNipples().getOrificeNipples().getElasticity().getValue(),
						breast.getNipples().getOrificeNipples().getPlasticity().getValue(),
						breast.getNipples().getOrificeNipples().isVirgin());
				sb.append("<i style='color:"+PresetColour.PSYCHOACTIVE.toWebHexString()+";'>你最近摄入的致幻乳汁导致你看到"+(owner.isPlayer()?"你的":"[npc.namePos]的")+"乳房发生了扭曲！</i>");
			}
			if(viewedBreast.getRawSizeValue()>0){
				sb.append("[npc.SheHasFull]拥有" + Util.intToString(owner.getBreastRows()) + "对");
				if(Main.game.isVestigialMultiBreastsEnabled() && owner.getBreastRows()>1) {
					sb.append("[npc.breasts]");
				} else {
					sb.append(viewedBreast.getSize().getDescriptor()+"的[npc.breasts]");
				}
				
				if(owner.getBreastRows()==1) {
					if (viewedBreast.getSize().isTrainingBraSize()) {
						sb.append("，适合穿着训练内衣。");
					} else {
						sb.append("，适合穿着"+UtilText.generateSingularDeterminer(viewedBreast.getSize().getCupSizeName())+""+viewedBreast.getSize().getCupSizeName()+"罩杯的内衣。");
					}
					
				} else if(owner.getBreastRows()==2) {
					if (viewedBreast.getSize().isTrainingBraSize()) {
						sb.append("，[npc.her]最上方的一对[npc.breast]适合穿着训练内衣，"
								+(Main.game.isVestigialMultiBreastsEnabled()
									?"而下方的一对已经退化了。"
									:"而下方的一对会更小一些。"));
					} else {
						sb.append("，[npc.her]最上方的一对[npc.breast]适合穿着"
								+UtilText.generateSingularDeterminer(viewedBreast.getSize().getCupSizeName())+""+viewedBreast.getSize().getCupSizeName()+"罩杯内衣，"
										+(Main.game.isVestigialMultiBreastsEnabled()
												?"而下方的一对已经退化了。"
												:"而下方的一对会更小一些。"));
					}
					
				} else if(owner.getBreastRows()>2) {
					if (viewedBreast.getSize().isTrainingBraSize()) {
						sb.append("，[npc.her]最上方的一对[npc.breast]适合穿着训练内衣，"
								+(Main.game.isVestigialMultiBreastsEnabled()
										?"而下方几对已经退化了。"
										:"而下方几对逐渐变小。"));
					} else {
						sb.append("，[npc.her]最上方的一对[npc.breast]适合穿着"
									+UtilText.generateSingularDeterminer(viewedBreast.getSize().getCupSizeName())+""+viewedBreast.getSize().getCupSizeName()+"罩杯内衣，"
											+(Main.game.isVestigialMultiBreastsEnabled()
													?"而下方几对已经退化了。"
													:"而下方几对逐渐变小。"));
					}
				}
				
			} else {
				sb.append("[npc.SheHasFull]的胸部一片平坦");
				if(owner.getBreastRows()==1) {
					sb.append("，胸部平坦极了。");
				} else {
					sb.append("，只有"+Util.intToString(owner.getBreastRows())+"对平板胸部。");
				}
			}
			
			sb.append("" + getBreastDescription(owner, viewedBreast));
		}
		sb.append("</p>");
		
		
		// BreastsCrotch:
		if(owner.isAreaKnownByCharacter(CoverableArea.BREASTS_CROTCH, Main.game.getPlayer())) {
			if(owner.hasBreastsCrotch()) {
				sb.append(getHeader(owner.getBreastCrotchShape()==BreastShape.UDDERS?"腹乳":"胯乳"));
				sb.append(getBreastCrotchDescription(owner, breastCrotch));
				sb.append("</p>");
				
			} else if(this.leg.getLegConfiguration()!=LegConfiguration.BIPEDAL
					|| this.isFeral()
					|| (this.getRaceStage()==RaceStage.GREATER && RacialBody.valueOfRace(this.getRace()).getBreastCrotchType()!=BreastType.NONE && Main.getProperties().getUddersLevel()==2)) {
				sb.append(getHeader(owner.getBreastCrotchShape()==BreastShape.UDDERS?"腹乳":"胯乳"));
				sb.append("[style.colourDisabled([npc.She]没有胯乳和腹乳。)]");
				sb.append("</p>");
			}
			
		} else if(leg.getLegConfiguration()!=LegConfiguration.BIPEDAL
				|| this.isFeral()
				|| (this.getRaceStage()==RaceStage.GREATER && Main.getProperties().getUddersLevel()==2)) {
			sb.append(getHeader(owner.getBreastCrotchShape()==BreastShape.UDDERS?"腹乳":"胯乳"));
			if(owner.hasBreastsCrotch() && owner.isBreastsCrotchVisibleThroughClothing() && leg.getLegConfiguration().isBipedalPositionedCrotchBoobs()) {
				sb.append("尽管你没有看过[npc.her]露出肚子，但[npc.her][npc.crotchBoobsSize]的[npc.crotchBoobsCups]罩杯[npc.crotchBoobs]很明显地从[npc.her]的[npc.topClothing(STOMACH)]之下凸显出来。");
			} else {
				sb.append("[style.colourDisabled(你没有见过[npc.her]露出肚子，所以并不清楚[npc.sheHasFull]有没有胯乳或腹乳。)]");
			}
			sb.append("</p>");
		}
		
		
		// Arms and legs:

		// Arms:
		sb.append(getHeader("手臂"));
		if(owner.isFeral() && !owner.getFeralAttributes().isArmsOrWingsPresent()) {
			if(owner.getLegConfiguration()==LegConfiguration.AVIAN) {
				sb.append("由于[npc.nameHasFull]的身体是兽态[npc.race]，[npc.she]拥有[npc.armRows]翅膀，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]，十分美丽！");
			} else {
				sb.append("由于[npc.nameHasFull]的身体是兽态[npc.race]，[npc.she]没有手臂！");
			}
			
		} else {
			sb.append(arm.getType().getBodyDescription(owner));
			
			if(owner.getHandNailPolish().getPrimaryColour() != PresetColour.COVERING_NONE) {
				if(owner.getArmType().equals(ArmType.HARPY)) {
					sb.append("[npc.her]拇指的爪子上涂着"+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS).getFullDescription(owner, true)+"。");
				} else {
					sb.append("[npc.Her]的手指甲上涂着"+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS).getFullDescription(owner, true)+"。");
				}
			}
			
			if(Main.game.isBodyHairEnabled()) {
				if(owner.getUnderarmHairType().getType()==BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR) {
					switch(owner.getUnderarmHair()) {
						case ZERO_NONE:
							sb.append("[npc.her]的腋下没有硬质的"+owner.getUnderarmHairType().getName(owner)+"的踪迹。");
							break;
						case ONE_STUBBLE:
							sb.append("[npc.SheHasFull]的腋下长着一片硬质的"+owner.getUnderarmHairType().getFullDescription(owner, true)+"。");
							break;
						case TWO_MANICURED:
							sb.append("[npc.SheHasFull]的腋下长着一片硬质的"+owner.getUnderarmHairType().getFullDescription(owner, true)+"。");
							break;
						case THREE_TRIMMED:
							sb.append("[npc.SheHasFull]的腋下长着一片硬质的"+owner.getUnderarmHairType().getFullDescription(owner, true)+"。");
							break;
						case FOUR_NATURAL:
							sb.append("[npc.SheHasFull]的腋下自然生长着一片硬质的"+owner.getUnderarmHairType().getFullDescription(owner, true)+"。");
							break;
						case FIVE_UNKEMPT:
							sb.append("[npc.SheHasFull]的腋下凌乱地长着一大片硬质的"+owner.getUnderarmHairType().getFullDescription(owner, true)+"。");
							break;
						case SIX_BUSHY:
							sb.append("[npc.SheHasFull]的腋下厚实地长着一大片硬质的"+owner.getUnderarmHairType().getFullDescription(owner, true)+"。");
							break;
						case SEVEN_WILD:
							sb.append("[npc.SheHasFull]的腋下野蛮生长着一片硬质的"+owner.getUnderarmHairType().getFullDescription(owner, true)+"。");
							break;
					}
				} else {
					switch(owner.getUnderarmHair()) {
						case ZERO_NONE:
							sb.append("[npc.her]的腋下没有"+owner.getUnderarmHairType().getName(owner)+"的踪迹。");
							break;
						case ONE_STUBBLE:
							sb.append("[npc.SheHasFull]的腋下长着一小茬"+owner.getUnderarmHairType().getFullDescription(owner, true)+"。");
							break;
						case TWO_MANICURED:
							sb.append("[npc.SheHasFull]的腋下长着一小片整齐的"+owner.getUnderarmHairType().getFullDescription(owner, true)+"。");
							break;
						case THREE_TRIMMED:
							sb.append("[npc.SheHasFull]的腋下长着一片修剪过的"+owner.getUnderarmHairType().getFullDescription(owner, true)+"。");
							break;
						case FOUR_NATURAL:
							sb.append("[npc.SheHasFull]的腋下自然生长着一片"+owner.getUnderarmHairType().getFullDescription(owner, true)+"。");
							break;
						case FIVE_UNKEMPT:
							sb.append("[npc.SheHasFull]的腋下凌乱地长着一大片"+owner.getUnderarmHairType().getFullDescription(owner, true)+"。");
							break;
						case SIX_BUSHY:
							sb.append("[npc.SheHasFull]的腋下厚实地长着一大片"+owner.getUnderarmHairType().getFullDescription(owner, true)+"。");
							break;
						case SEVEN_WILD:
							sb.append("[npc.SheHasFull]的腋下野蛮生长着一大片"+owner.getUnderarmHairType().getFullDescription(owner, true)+"。");
							break;
					}
				}
			}
			
			if (!(owner.isFeral() && owner.getArmType().allowsFlight())) {
				sb.append("[npc.Her]的[npc.arms]显得"+(Util.randomItemFrom(owner.getBodyShape().getLimbDescriptors()))
						+"，看上去<span style='color:"+owner.getFemininity().getColour().toWebHexString()+";'>"+owner.getFemininity().getName(false)+"。");
			}
			
			if(arm.getType().allowsFlight()) {
				if(this.getBodyMaterial() == BodyMaterial.SLIME) {
					sb.append("[style.colourSlime(由于[npc.wings]由粘液构成，基本不可能飞得起来……)]");
				} else if(this.getBodyMaterial() == BodyMaterial.SILICONE) {
					sb.append("[style.colourDoll(由于[npc.arm]由硅胶构成，基本不可能飞得起来……)]");
				} else if(this.getLeg().getLegConfiguration().getMinimumWingSizeForFlight(this).getValue()>WingSize.THREE_LARGE.getValue()) {
					sb.append("[npc.her]的[npc.wings]尺寸不足，不足以让[npc.herHim]飞翔。");
				} else {
					sb.append("[style.colourBlue([npc.her]的[npc.wings]尺寸适合且足够强壮，使得[npc.herHim]能够飞翔！)]");
				}
			}
		}
		sb.append("</p>");

		
		// Legs:
		if(owner.getLegType().isLegsReplacedByTentacles()) {
			sb.append(getHeader("触手状腿"));
			
			sb.append("[npc.Name]拥有[npc.tentacleCount]根触手，而不是腿。");
			sb.append(owner.getTentacleType().getGirthDescription(owner));
			sb.append("每一根的长度为[npc.tentacleLength(true)]，而根部的直径为[npc.tentacleBaseDiameter(true)](周长为[npc.tentacleBaseCircumference(true)])。");
			for(BodyPartTag bpt : owner.getTentacleType().getTags()) {
				switch(bpt) {
					case TAIL_PREHENSILE:
						sb.append("[npc.She]能够精确地控制每一根触手，因而可以用来抓取或操纵物体。");
						break;
					case TAIL_SLEEP_HUGGING:
						break;
					case TAIL_SUITABLE_FOR_PENETRATION:
						sb.append("每一根都能在性爱时用作插入物，当插入腔穴时，最多能够插入[npc.tentaclePenetrationLength(true)]。");
						break;
					case TAIL_TAPERING_EXPONENTIAL:
						sb.append("每一根的周长从根部开始快速减少，所以[npc.tentacleTip]的直径为[npc.tentacleTipDiameter(true)](周长为[npc.tentacleTipCircumference(true)])。");
						break;
					case TAIL_TAPERING_LINEAR:
						sb.append("每一根的周长从根部开始稳步减少，所以[npc.tentacleTip]的直径为[npc.tentacleTipDiameter(true)](周长为[npc.tentacleBaseCircumference(true)])。");
						break;
					case TAIL_TAPERING_NONE:
						sb.append("每一根从根部开始粗细变化不大，所以一直到[npc.tentacleTip]都拥有同样的直径。");
						break;
					case TAIL_TAPERING_BULBOUS:
						sb.append("每一根的周长从根部开始匀速地先增加后减少，形成了鼓起的椭圆形状。");
						break;
					default:
						break;
				}
			}
			sb.append("<br/>");
			
		} else {
			sb.append(getHeader("腿部"));
		}
		if(owner.isFeral()) {
			String feralLegsPrefix = "就像[npc.her]身体的其他部分一样，[npc.her]";
			switch(owner.getLegConfiguration()) {
				case ARACHNID:
				case CEPHALOPOD:
				case AVIAN:
					sb.append(feralLegsPrefix).append("[npc.legRace]的[npc.legs]已经完全[style.colourFeral(兽态化)]。");
					break;
				case BIPEDAL:
					break;
				case TAIL:
					sb.append(feralLegsPrefix).append("[npc.legRace]的尾巴已经完全[style.colourFeral(兽态化)]，取代了腿部。");
					break;
				case TAIL_LONG:
					sb.append(feralLegsPrefix).append("[npc.legRace]的长尾已经完全[style.colourFeral(兽态化)]，取代了腿部。");
					if(!owner.isFeral()) {
						sb.append("身体有[npc.heightValue]的长度都由[npc.legs]提供，");
						sb.append("从头部开始，整个身体逐渐匀速变细，到[npc.tailtip]的位置直径为[npc.tailTipDiameter(true)](周长为[npc.tailTipCircumference(true)])。");
						sb.append("用以插入腔穴时，最多能够插入[npc.tailPenetrationLength(true)]。");
					} else {
						sb.append("[npc.her]的身体总长为"+Units.size(owner.getHeightValue()+owner.getLegTailLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG)+"，而[npc.tail]就有[npc.tailLength]。");
						sb.append("[npc.tail]逐渐匀速变细，直到[npc.tailtip]的位置直径为[npc.tailTipDiameter(true)](周长为[npc.tailTipCircumference(true)])。");
						sb.append("用以插入腔穴时，尾巴和身体都可以插入，最多[npc.tailPenetrationLength(true)]。");
					}
					break;
				case QUADRUPEDAL:
				case WINGED_BIPED:
					sb.append(feralLegsPrefix).append("的[npc.legs]已经完全[style.colourFeral(兽态化)]，成为了[npc.her][npc.legRace]身体的一部分。");
					break;
			}
			if(owner.getLegConfiguration().getNumberOfLegs()>0) {
				sb.append("[npc.Her]的腿[npc.materialCompositionDescriptor][npc.legFullDescription(true)]，而脚已经转化为").append(owner.getLegType().getFootType().getFootNamePlural()).append("。");
			} else {
				sb.append("[npc.legs][npc.materialCompositionDescriptor][npc.legFullDescription(true)]。");
			}
			
		} else {
			switch(owner.getLegConfiguration()) {
				case ARACHNID:
				case CEPHALOPOD:
				case AVIAN:
					sb.append("[npc.Her]的[npc.legs]已经完全[style.colourFeral(兽态化)]，成为了[npc.her][npc.legRace]身体的一部分。");
					break;
				case BIPEDAL:
					break;
				case TAIL:
					sb.append("[npc.Her][npc.legRace]的尾巴已经完全[style.colourFeral(兽态化)]，取代了腿部。");
					break;
				case TAIL_LONG:
					sb.append("[npc.Her][npc.legRace]的长尾已经完全[style.colourFeral(兽态化)]，取代了腿部。");
					sb.append("长度为").append(Units.size(owner.getLegTailLength(false))).append("，");
					sb.append("且逐渐匀速变细，直到[npc.tailtip]的位置直径为[npc.tailTipDiameter(true)](周长为[npc.tailTipCircumference(true)])。");
					sb.append("用以插入腔穴时，最多能够插入[npc.tailPenetrationLength(true)]。");
					break;
				case QUADRUPEDAL:
				case WINGED_BIPED:
					sb.append("[npc.Her]的[npc.legs]已经完全[style.colourFeral(兽态化)]，成为了[npc.her][npc.legRace]身体的一部分。");
					break;
			}
			if(owner.getLegConfiguration().getNumberOfLegs()>0) {
				sb.append(leg.getType().getBodyDescription(owner));
			} else {
				sb.append("[npc.legs][npc.materialCompositionDescriptor][npc.legFullDescription(true)]。");
			}
		}

		if(owner.getLegConfiguration().getNumberOfLegs()>0) {
			switch(owner.getFootStructure()) {
				case NONE:
				case TENTACLED:
					break;
				case DIGITIGRADE:
					sb.append("[npc.Her]的[npc.legs]和[npc.feet]使行走方式为[style.colourTFGeneric("+owner.getFootStructure().getName()+")]，意味着[npc.she]天生用脚趾行走。");
					break;
				case PLANTIGRADE:
					sb.append("[npc.Her]的[npc.legs]和[npc.feet]使行走方式为[style.colourTFGeneric("+owner.getFootStructure().getName()+")]，意味着[npc.she]用脚掌在地面行走。");
					break;
				case UNGULIGRADE:
					sb.append("[npc.Her]的[npc.legs]和[npc.feet]使行走方式为[style.colourTFGeneric("+owner.getFootStructure().getName()+")]，意味着[npc.she]天生用蹄子行走。");
					break;
				case ARACHNOID:
					sb.append("[npc.Her]的[npc.legs]和[npc.feet]使行走方式为[style.colourTFGeneric("+owner.getFootStructure().getName()+")]，意味着[npc.she]使用蛛化的节肢末端行走。");
					break;
			}
		}
		
		if(owner.getFootNailPolish().getPrimaryColour() != PresetColour.COVERING_NONE) {
			sb.append(owner.getLegType().getFootType().getFootNailPolishDescription(owner));
		}
		
		sb.append("<br/>");
		
		if(owner.getLegCount()>0) {
			sb.append("[npc.Her]的[npc.legs]显得"+(Util.randomItemFrom(owner.getBodyShape().getLimbDescriptors()))+"，");
			if (femininity <= Femininity.MASCULINE_STRONG.getMaximumFemininity()) {
				sb.append("[style.colourMasculineStrong(极显男性风度)]。");
				
			} else if (femininity <= Femininity.MASCULINE.getMaximumFemininity()) {
				sb.append("[style.colourMasculine(显出男性风度)]。");
				
			} else if (femininity <= Femininity.ANDROGYNOUS.getMaximumFemininity()) {
				sb.append("[style.colourAndrogynous(看上去十分中性)]。");
				
			} else if (femininity <= Femininity.FEMININE.getMaximumFemininity()) {
				sb.append("[style.colourFeminine(显出女性韵味)]。");
				
			} else {
				sb.append("[style.colourFeminineStrong(极显女性韵味)]。");
			}
			
		} else if(!owner.isFeral()) {
			sb.append("[npc.Her]的[npc.leg(true)]显得"+(Util.randomItemFrom(owner.getBodyShape().getLimbDescriptors()))+"，");
			if (femininity <= Femininity.MASCULINE_STRONG.getMaximumFemininity()) {
				sb.append("[style.colourMasculineStrong(极显男性风度)]。");
				
			} else if (femininity <= Femininity.MASCULINE.getMaximumFemininity()) {
				sb.append("[style.colourMasculine(显出男性风度)]。");
				
			} else if (femininity <= Femininity.ANDROGYNOUS.getMaximumFemininity()) {
				sb.append("[style.colourAndrogynous(看上去十分中性)]。");
				
			} else if (femininity <= Femininity.FEMININE.getMaximumFemininity()) {
				sb.append("[style.colourFeminine(显出女性韵味)]。");
				
			} else {
				sb.append("[style.colourFeminineStrong(极显女性韵味)]。");
			}
		}
		
		sb.append("</p>");
		
		
		// Wing:
		if(wing.getType()!=WingType.NONE) {
			sb.append(getHeader("翅膀"));
			if(owner.getLegConfiguration().isWingsOnLegConfiguration()) {
				sb.append("生长在[npc.her][npc.legConfiguration]身体的两侧，");
			} else {
				sb.append("生长在[npc.her]的肩头，");
			}
			sb.append(wing.getType().getBodyDescription(owner));
			if(wing.getType().allowsFlight()) {
				if(this.getBodyMaterial() == BodyMaterial.SLIME) {
					sb.append("[style.colourSlime(由于[npc.arms]由粘液构成，基本不可能飞得起来……)]");
				} else if(this.getBodyMaterial() == BodyMaterial.SILICONE) {
					sb.append("[style.colourDoll(由于[npc.arm]由硅胶构成，基本不可能飞得起来……)]");
				} else if(wing.getSizeValue()>=owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getValue()) {
					sb.append("[style.colourBlue([npc.her]的[npc.wings]尺寸适合且足够强壮，使得[npc.herHim]能够飞翔！)]");
				} else {
					sb.append("[npc.her]的[npc.wings]尺寸不足，不足以让[npc.herHim]飞翔。");
				}
				
			} else {
				sb.append("[npc.her]的[npc.wings]完全没有飞行能力。");
			}
			sb.append("</p>");
		}
	
		
		// Tail:
		if(tail.getType()!=TailType.NONE) {
			sb.append(getHeader("尾巴"));
			sb.append(owner.getTailType().getBodyDescription(owner));
			sb.append(owner.getTailType().getGirthDescription(owner));
			if(owner.getTailCount()==1) {
				sb.append("长度为[npc.tailLength(true)]，而根部的直径为[npc.tailBaseDiameter(true)](周长为[npc.tailBaseCircumference(true)])。");
				if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_EXPONENTIAL)) {
					sb.append("从根部开始快速变细，所以[npc.tailTip]的直径为[npc.tailTipDiameter(true)](周长为[npc.tailTipCircumference(true)])。");
					
				} else if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_LINEAR)) {
					sb.append("从根部开始逐渐匀速变细，所以[npc.tailTip]的直径为[npc.tailTipDiameter(true)](周长为[npc.tailTipCircumference(true)])。");
					
				} else if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_BULBOUS)) {
					sb.append("周长从根部开始匀速地先增加后减少，形成了鼓起的椭圆形状。");
					
				} else {
					sb.append("从根部开始粗细变化不大，所以一直到[npc.tailTip]都拥有同样的直径。");
				}
				
			} else {
				sb.append("每一根的长度为[npc.tailLength(true)]，而根部的直径为[npc.tailBaseDiameter(true)](周长为[npc.tailBaseCircumference(true)])。");
				if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_EXPONENTIAL)) {
					sb.append("每一根都从根部开始快速变细，所以[npc.tailTip]的直径为[npc.tailTipDiameter(true)](周长为[npc.tailTipCircumference(true)])。");
					
				} else if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_LINEAR)) {
					sb.append("每一根都从根部开始逐渐匀速变细，所以[npc.tailTip]的直径为[npc.tailTipDiameter(true)](周长为[npc.tailTipCircumference(true)])。");
					
				} else if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_BULBOUS)) {
					sb.append("每一根的周长都从根部开始匀速地先增加后减少，形成了鼓起的椭圆形状。");
					
				} else {
					sb.append("每一根都从根部开始粗细变化不大，所以一直到[npc.tailTip]都拥有同样的直径。");
				}
			}
			
			if(owner.isTailSuitableForPenetration()) {
				sb.append("用以插入腔穴时，最多能够插入[npc.tailPenetrationLength(true)]。");
			} else {
				sb.append("不适合插入腔穴中。");
			}
			sb.append("</p>");
		}

		// Tussy:
		if(owner.hasTussy()) {
			sb.append(getHeader("Tussy"));
			sb.append(getTussyDescription(owner));
			sb.append("</p>");
		}

		// Spinneret:
		if(owner.hasSpinneret()) {
			sb.append(getHeader("丝囊"));
			sb.append(getSpinneretDescription(owner));
			sb.append("</p>");
		}
		
		
		// Cloaca:
		if(owner.isPlayer()
				|| owner.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer())
				|| owner.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())
				|| owner.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())) {
			switch(owner.getGenitalArrangement()) {
				case NORMAL:// Don't need to add a description for normal arrangement I think.
					break;
				case CLOACA:
				case CLOACA_BEHIND:
					sb.append(getHeader("泄殖腔"));
					sb.append("[style.colourFeral("+owner.getGenitalArrangement().getDescription()+")]");
					sb.append("</p>");
					break;
			}
		}
		
		
		// Ass & hips:
		sb.append(getHeader("屁股"));
		if(!owner.isFeral() && ass.isFeral(owner)) {
			sb.append("[npc.Her][npc.hips+]和[npc.assSize]的[npc.ass]是[style.colourFeral(其兽态下半身的一部分)]，[npc.materialCompositionDescriptor][npc.assFullDescription(true)]。");
		} else {
			sb.append("[npc.Her][npc.hips+]和[npc.assSize]的[npc.ass][npc.materialCompositionDescriptor][npc.assFullDescription(true)]。");
		}
		
		if(owner.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer())) {
			sb.append(getAssDescription(owner, false));
		} else {
			sb.append("[style.colourDisabled(你从未见过[npc.her]裸露的屁股，所以也不知道菊花到底长什么样子。)]");
		}
		sb.append("</p>");
		
		if(owner.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer()) && owner.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())) {
			// Vagina, virgin/capacity, wetness:
			if (vagina.getType() == VaginaType.NONE && penis.getType() == PenisType.NONE) {
				sb.append(getHeader("外生殖器"));
				sb.append(getMoundDescription(owner));
				sb.append("</p>");
			}
		}

		if(owner.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())) {
			// Penises, cum production, testicle size, capacity:
			if (owner.hasPenis()) {
				sb.append(getHeader("阴茎"));
				sb.append(getPenisDescription(owner));
				sb.append("</p>");
			}
			
		} else {
			sb.append(getHeader("阴茎"));
			if (observant) {
				if(owner.hasPenis()) {
					sb.append("[style.colourDisabled(多亏了你的'"+Perk.OBSERVANT.getName(Main.game.getPlayer())+"'特性，你辨认出[npc.sheHasFull]长着阴茎，"
										+ "但由于你从未见过[npc.her]裸露的胯部，所以也不知道到底长什么样子。)]");
				} else {
					sb.append("[style.colourDisabled(你从未见过[npc.her]裸露的胯部，但多亏了你的'"+Perk.OBSERVANT.getName(Main.game.getPlayer())+"'特性，你辨认出[npc.she]并没有阴茎。)]");
				}
				
			} else {
				sb.append("[style.colourDisabled(你从未见过[npc.her]裸露的胯部，所以也不知道[npc.her]阴茎的样子，甚至不知道[npc.her]到底有没有阴茎。)]");
			}
			sb.append("</p>");
		}
		
		if(owner.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())) {
			// Vagina, virgin/capacity, wetness:
			if (vagina.getType() != VaginaType.NONE) {
				sb.append(getHeader("阴道"));
				sb.append(getVaginaDescription(owner));
				sb.append("</p>");
			}
		} else {
			sb.append(getHeader("阴道"));
			if (observant){
				if(vagina.getType() != VaginaType.NONE){
					sb.append("[style.colourDisabled(多亏了你的'"+Perk.OBSERVANT.getName(Main.game.getPlayer())+"'特性，你辨认出[npc.sheHasFull]长着阴道，"
									+ "但由于你从未见过[npc.her]裸露的胯部，所以也不知道到底长什么样子。)]");
					
				} else {
					sb.append("[style.colourDisabled(你从未见过[npc.her]裸露的胯部，但多亏了你的'"+Perk.OBSERVANT.getName(Main.game.getPlayer())+"'特性，你辨认出[npc.she]并没有阴道。)]");
				}
				
			} else {
				sb.append("[style.colourDisabled(你从未见过[npc.her]裸露的胯部，所以也不知道[npc.her]阴道的样子，甚至不知道[npc.her]到底有没有阴道。)]");
			}
			sb.append("</p>");
		}
		
		// Tattoos:
		StringBuilder tattooSB = new StringBuilder();
		for(Entry<InventorySlot, Tattoo> tattoEntry : owner.getTattoos().entrySet()) {
			InventorySlot tattooSlot = tattoEntry.getKey();
			boolean knowsArea = owner.isPlayer();
			if(!knowsArea) {
				caLoop:
				for(CoverableArea ca : CoverableArea.values()) {
					for(InventorySlot slot : ca.getAssociatedInventorySlots(owner)) {
						if(tattooSlot==slot) {
							knowsArea = true;
							break caLoop;
						}
					}
				}
			}
			if(knowsArea) {
				if(tattooSB.length()>0) {
					tattooSB.append("<br/>");
				}
				Tattoo tattoo = tattoEntry.getValue();
				if(tattoo.getBodyOverviewDescription().isEmpty()) { // Old version support
					tattooSB.append("<span style='color:"+tattoo.getPrimaryColour().toWebHexString()+";'>"+Util.capitaliseSentence(tattooSlot.getTattooSlotName())+":</span> ");
					tattooSB.append(tattoo.getDescription(owner));
					tattooSB.append("。");
					
				} else {
					tattooSB.append("在[npc.her]的[style.boldBlueSteel("+tattooSlot.getTattooSlotName()+")]纹有");
					tattooSB.append(tattoo.getBodyOverviewDescription());
					if(tattoo.getType()!=TattooType.getTattooTypeFromId("innoxia_misc_none")) {
						tattooSB.append("，主要配色为");
						tattooSB.append("<span style='color:"+tattoo.getPrimaryColour().toWebHexString()+";'>"+tattoo.getPrimaryColour().getName()+"</span>");
					}
					tattooSB.append("。");
				}
				if(tattoo.getWriting()!=null && tattoo.getWriting().getText()!=null && !tattoo.getWriting().getText().isEmpty()) {
					tattooSB.append("上面写着:'"+tattoo.getFormattedWritingOutput()+"'");
				}
				if(tattoo.getCounter()!=null && tattoo.getCounter().getType()!=TattooCounterType.NONE) {
					String counterName = tattoo.getCounter().getType().getName();
					if(tattoo.getWriting()!=null && tattoo.getWriting().getText()!=null && !tattoo.getWriting().getText().isEmpty()) {
						tattooSB.append("，并能附魔记录");
					} else {
						tattooSB.append("该纹身能附魔记录");
					}
					tattooSB.append(UtilText.generateSingularDeterminer(counterName)+"“"+counterName+"”的数量，现在记录为：“"+tattoo.getFormattedCounterOutput(owner)+"”。");
				} else if(tattoo.getWriting()!=null && tattoo.getWriting().getText()!=null && !tattoo.getWriting().getText().isEmpty()) {
					tattooSB.append("。");
				}
			}
		}
		if(tattooSB.length()>0) {
			sb.append(getHeader("纹身"));
			sb.append(tattooSB.toString());
		}
		
		if(!owner.isPlayer()) {
			sb.append(getSexDetails(owner));
			
			sb.append(getPregnancyDetails(owner));

			sb.append(getIncubationPregnancyDetails(owner));
		}

		return UtilText.parse(owner, sb.toString());
	}

	private void addRaceWeight(Map<AbstractRace, Integer> raceWeightMap, AbstractRace race, int weight) {
		if(race!=null && race!=Race.NONE) {
			raceWeightMap.putIfAbsent(race, 0);
			raceWeightMap.put(race, raceWeightMap.get(race)+weight);
		}
	}

	
	/** To be called after every transformation. Returns the body's race. */
	public void calculateRace(GameCharacter target) {

		// Every time race is calculated, it's because parts have changed, so reset the body parts list:
		handleAllBodyPartsList();
		
//		if(target!=null) {
//			target.removeStatusEffect(StatusEffect.SUBSPECIES_BONUS);
//		}
		
		AbstractRace race = Race.HUMAN;
		if(this.getBodyMaterial()==BodyMaterial.SLIME) {
			race = Race.SLIME;
			this.raceStage = RaceStage.GREATER;
			
		} else if(this.getBodyMaterial()==BodyMaterial.SILICONE) {
			race = Race.DOLL;
			this.raceStage = RaceStage.GREATER;
			
		} else if(target!=null && target.isElemental()) {
			race = Race.ELEMENTAL;
			this.raceStage = RaceStage.GREATER;
			
		} else {
			race = getRaceFromPartWeighting();
			this.raceStage = getRaceStageFromPartWeighting();
		}
		
		subspecies = AbstractSubspecies.getSubspeciesFromBody(this, race);
//		boolean overrideSubspecies = false;

		halfDemonSubspecies = null; // reset so it will be recalculated when accessed

		if(subspecies.getSubspeciesOverridePriority()>0 && (this.getSubspeciesOverride()==null || subspecies.getSubspeciesOverridePriority()>=this.getSubspeciesOverride().getSubspeciesOverridePriority())) {
			this.setSubspeciesOverride(subspecies);
		}
		
//		switch(subspecies) {
//			case IMP:
//				overrideSubspecies = this.getSubspeciesOverride()!=Subspecies.IMP_ALPHA
//						&& this.getSubspeciesOverride()!=Subspecies.HALF_DEMON
//						&& this.getSubspeciesOverride()!=Subspecies.DEMON
//						&& this.getSubspeciesOverride()!=Subspecies.LILIN
//						&& this.getSubspeciesOverride()!=Subspecies.ELDER_LILIN;
//				break;
//			case IMP_ALPHA:
//				overrideSubspecies = this.getSubspeciesOverride()!=Subspecies.HALF_DEMON
//						&& this.getSubspeciesOverride()!=Subspecies.DEMON
//						&& this.getSubspeciesOverride()!=Subspecies.LILIN
//						&& this.getSubspeciesOverride()!=Subspecies.ELDER_LILIN;
//				break;
//			case HALF_DEMON:
//				overrideSubspecies = this.getSubspeciesOverride()!=Subspecies.DEMON
//						&& this.getSubspeciesOverride()!=Subspecies.LILIN
//						&& this.getSubspeciesOverride()!=Subspecies.ELDER_LILIN;
//				break;
//			case DEMON:
//				overrideSubspecies = this.getSubspeciesOverride()!=Subspecies.LILIN
//						&& this.getSubspeciesOverride()!=Subspecies.ELDER_LILIN;
//				break;
//			case ELDER_LILIN:
//			case LILIN:
//				overrideSubspecies = true;
//				break;
//			default:
//				break;
//		}
//		
//		if(overrideSubspecies) {
//			this.setSubspeciesOverride(subspecies);
//		}
		
//		if(target!=null && target.getBody()!=null && Main.game.isStarted()) { // Apparently this is needed to stop Lyssieth from losing her status effect???
//			target.addStatusEffect(StatusEffect.SUBSPECIES_BONUS, -1);
//		}
	}

	public AbstractRace getRaceFromPartWeighting() {
		return getRaceFromPartWeighting(false);
	}
	
	public AbstractRace getRaceFromPartWeighting(boolean ignoreOverride) {
		AbstractRace race = Race.HUMAN;
		
		raceWeightMap.clear();
		
		addRaceWeight(raceWeightMap, torso.getType().getRace(), 4);
		addRaceWeight(raceWeightMap, face.getType().getRace(), 4);
		
		addRaceWeight(raceWeightMap, arm.getType().getRace(), 3);
		addRaceWeight(raceWeightMap, leg.getType().getRace(), 3);

		addRaceWeight(raceWeightMap, antenna.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, eye.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, ear.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, hair.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, tail.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, wing.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, horn.getType().getRace(), 1);
		
		// Best to leave this out...
		// Breast, ass, penis, and vagina have very low weighting so that the more visible parts of a character's body are counted more towards their subspecies
//		addRaceWeight(raceWeightMap, breast.getType().getRace(), 1);
//		addRaceWeight(raceWeightMap, breastCrotch.getType().getRace(), 1);
//		addRaceWeight(raceWeightMap, ass.getType().getRace(), 1);
//		addRaceWeight(raceWeightMap, penis.getType().getRace(), 1);
//		addRaceWeight(raceWeightMap, vagina.getType().getRace(), 1);
		
		raceWeightMap.remove(Race.NONE);
		
		int max = 0;
		boolean demonPartFound = false;
		
		for(Entry<AbstractRace, Integer> e : raceWeightMap.entrySet()) {
			if(e.getKey()!=null && e.getKey()==Race.DEMON) {
				demonPartFound = true;
				
			} else if(e.getKey()!=null && e.getKey()!=Race.HUMAN && e.getValue()>max) {
				race = e.getKey();
				max = e.getValue();
			}
		}
		if(!ignoreOverride && demonPartFound) { // Just one demon part is enough to make any character a demon:
			return Race.DEMON;
		}
		
		return race;
	}
	
	public RaceStage getRaceStageFromPartWeighting() {
		if(this.isFeral()) {
			return RaceStage.FERAL;
		}
		if(raceWeightMap.containsKey(Race.DEMON) && !raceWeightMap.containsKey(Race.HUMAN) && raceWeightMap.size()==2) {
			return RaceStage.GREATER;
		}
		if(raceWeightMap.size()==1) {
			if(raceWeightMap.containsKey(Race.HUMAN)) {
				return RaceStage.HUMAN;
			} else {
				return RaceStage.GREATER;
			}
			
		} else {
			return RaceStage.LESSER;
		}
	}

	public Map<AbstractRace, Integer> getRaceWeightMap() {
		return raceWeightMap;
	}
	
	public AbstractRace getRace() {
		if(subspecies == null) {
			calculateRace(null);
		}
		return subspecies.getRace();
	}

	/**
	 * @return This body's true race. If this body does not have a subspecies override, this will be the same as getRace(). If they do have an override, however, it will return the race of that override.
	 */
	public AbstractRace getTrueRace() {
		if(this.getSubspeciesOverride()!=null) {
			return this.getSubspeciesOverride().getRace();
		}
		return getRace();
	}
	
	public AbstractSubspecies getSubspecies() {
		return subspecies;
	}
	
	public AbstractSubspecies getLoadedSubspecies() {
		if(loadedSubspecies==null) {
			return getSubspecies();
		}
		return loadedSubspecies;
	}

	/**
	 * @return This body's true subspecies. If this body does not have a subspecies override, this will be the same as getSubspecies(). If they do have an override, however, it will return that override.
	 */
	public AbstractSubspecies getTrueSubspecies() {
		if(this.getSubspeciesOverride()!=null) {
			return this.getSubspeciesOverride();
		}
		return getSubspecies();
	}
	
	public RaceStage getRaceStage() {
		return raceStage;
	}

	public AbstractSubspecies getSubspeciesOverride() {
		return subspeciesOverride;
	}

	public void setSubspeciesOverride(AbstractSubspecies subspeciesOverride) {
		this.subspeciesOverride = subspeciesOverride;
	}

	public AbstractSubspecies getHalfDemonSubspecies() {
		if (halfDemonSubspecies == null) {
			halfDemonSubspecies = AbstractSubspecies.getSubspeciesFromBody(this, getRaceFromPartWeighting(true));
		}
		return halfDemonSubspecies;
	}
	
	public boolean isDoll() {
		return this.getRace()==Race.DOLL;
	}

	public Antenna getAntenna() {
		return antenna;
	}

	public Arm getArm() {
		return arm;
	}

	public AbstractArmType getArmType() {
		return arm.getType();
	}

	public Ass getAss() {
		return ass;
	}

	public AbstractAssType getAssType() {
		return ass.getType();
	}
	
	public Breast getBreast() {
		return breast;
	}

	public AbstractBreastType getBreastType() {
		return breast.getType();
	}
	
	public boolean hasBreasts() {
		return getBreastType()!=BreastType.NONE;
	}
	
	public BreastCrotch getBreastCrotch() {
		return breastCrotch;
	}

	public AbstractBreastType getBreastCrotchType() {
		return breastCrotch.getType();
	}
	
	public boolean hasBreastsCrotch() {
		return getBreastCrotchType()!=BreastType.NONE;
	}
	
	public Face getFace() {
		return face;
	}

	public AbstractFaceType getFaceType() {
		return face.getType();
	}
	
	public Eye getEye() {
		return eye;
	}

	public AbstractEyeType getEyeType() {
		return eye.getType();
	}

	public Ear getEar() {
		return ear;
	}

	public AbstractEarType getEarType() {
		return ear.getType();
	}

	public Hair getHair() {
		return hair;
	}

	public AbstractHairType getHairType() {
		return hair.getType();
	}

	public Horn getHorn() {
		return horn;
	}

	public AbstractHornType getHornType() {
		return horn.getType();
	}

	public boolean hasGenericHorns() {
		return getHorn().getType().isGeneric();
	}
	
	public List<AbstractHornType> getAvailableHornTypes(boolean retainNone) {
		return HornType.getHornTypes(this.getRace(), retainNone);
	}
	
	public Leg getLeg() {
		return leg;
	}

	public AbstractLegType getLegType() {
		return leg.getType();
	}

	public LegConfiguration getLegConfiguration() {
		return leg.getLegConfiguration();
	}

	public boolean isTaur() {
		return !getLegConfiguration().isBipedalPositionedGenitals();
	}
	
	public Penis getPenis() {
		return penis;
	}

	public AbstractPenisType getPenisType() {
		return penis.getType();
	}

	public boolean hasPenis() {
		return penis.getType() != PenisType.NONE;
	}

	public boolean hasPenisIgnoreDildo() {
		return hasPenis() && penis.getType() != PenisType.DILDO;
	}

	public boolean hasPenisIgnoresDildo() {
		return hasPenisIgnoreDildo();
	}
	
	public OrificeSpinneret getSpinneret() {
		return spinneret;
	}

	public OrificeTussy getTussy() {
		return tussy;
	}

	public boolean hasTussy() {
		return hasTussy();
	}

	public boolean hasTailSpinneret() {
		return getTailType().hasSpinneret();
	}
	
	public boolean hasLegSpinneret() {
		return getLegConfiguration()==LegConfiguration.ARACHNID && getLegType().hasSpinneret();
	}
	
	public boolean hasSpinneret() {
		return hasTailSpinneret() || hasLegSpinneret();
	}

	public Torso getTorso() {
		return torso;
	}

	public AbstractTorsoType getTorsoType() {
		return torso.getType();
	}

	public Tail getTail() {
		return tail;
	}

	public AbstractTailType getTailType() {
		return tail.getType();
	}

	public Tentacle getTentacle() {
		return tentacle;
	}

	public AbstractTongueType getTongueType() {
		return face.getTongue().getType();
	}

	public Vagina getVagina() {
		return vagina;
	}

	public AbstractVaginaType getVaginaType() {
		return vagina.getType();
	}

	public boolean hasVagina() {
		return vagina.getType() != VaginaType.NONE;
	}

	public boolean hasVaginaIgnoreOnahole() {
		return hasVagina() && vagina.getType() != VaginaType.ONAHOLE;
	}
	
	public Wing getWing() {
		return wing;
	}

	public int getWingSizeValue() {
		return wing.getSizeValue();
	}

	public int getMinimumWingSizeValueForFlight() {
		return leg.getLegConfiguration().getMinimumWingSizeForFlight(this).getValue();
	}

	public AbstractWingType getWingType() {
		return wing.getType();
	}

	public List<AbstractWingType> getAvailableWingTypes() {
		return WingType.getWingTypes(this.getRace());
	}
	
	public void setAntenna(Antenna antenna) {
		this.antenna = antenna;
	}

	public void setArm(Arm arm) {
		this.arm = arm;
	}

	public String setArmType(AbstractArmType type) {
		return this.arm.setType(null, type);
	}

	public String setArmType(GameCharacter owner, AbstractArmType type) {
		return this.arm.setType(owner, type);
	}

	public String setArmRows(int armRows) {
		return this.arm.setArmRows(null, armRows);
	}

	public String setArmRows(GameCharacter owner, int armRows) {
		return this.arm.setArmRows(owner, armRows);
	}

	public void setAss(Ass ass) {
		this.ass = ass;
	}

	public String setAssType(GameCharacter owner, AbstractAssType type) {
		return this.ass.setType(owner, type);
	}
	
	public void setBreast(Breast breast) {
		this.breast = breast;
	}

	public String setBreastType(GameCharacter owner, AbstractBreastType type) {
		return this.breast.setType(owner, type);
	}

	public void setBreastCrotch(BreastCrotch breastCrotch) {
		this.breastCrotch = breastCrotch;
	}

	public String setBreastCrotchType(GameCharacter owner, AbstractBreastType type) {
		return this.breastCrotch.setType(owner, type);
	}

	public void setFace(Face face) {
		this.face = face;
	}

	public String setFaceType(AbstractFaceType type) {
		return face.setType(null, type);
	}

	public String setFaceType(GameCharacter owner, AbstractFaceType type) {
		return face.setType(owner, type);
	}

	public void setEye(Eye eye) {
		this.eye = eye;
	}

	public String setEyeType(GameCharacter owner, AbstractEyeType type) {
		return this.eye.setType(owner, type);
	}

	public void setEar(Ear ear) {
		this.ear = ear;
	}

	public String setEarType(AbstractEarType type) {
		return this.ear.setType(null, type);
	}

	public String setEarType(GameCharacter owner, AbstractEarType type) {
		return this.ear.setType(owner, type);
	}

	public void setHair(Hair hair) {
		this.hair = hair;
	}

	public String setHairType(AbstractHairType type) {
		return this.hair.setType(null, type);
	}

	public String setHairType(GameCharacter owner, AbstractHairType type) {
		return this.hair.setType(owner, type);
	}

	public void setLeg(Leg leg) {
		this.leg = leg;
	}

	public String setLegType(AbstractLegType type) {
		return this.leg.setType(null, type);
	}

	public String setLegType(GameCharacter owner, AbstractLegType type) {
		return this.leg.setType(owner, type);
	}

	public void setLegConfigurationForced(AbstractLegType type, LegConfiguration legConfiguration) {
		this.leg.setLegConfigurationForced(type, legConfiguration);
	}

	public void setTongueType(AbstractTongueType type) {
		this.face.getTongue().setType(type);
	}

	public void setTorso(Torso torso) {
		this.torso = torso;
	}
	
	public String setTorsoType(AbstractTorsoType type) {
		return this.torso.setType(null, type);
	}

	public String setTorsoType(GameCharacter owner, AbstractTorsoType type) {
		return this.torso.setType(owner, type);
	}

	public void setHorn(Horn horn) {
		this.horn = horn;
	}

	public String setHornType(AbstractHornType type) {
		return this.horn.setType(null, type);
	}

	public String setHornType(GameCharacter owner, AbstractHornType type) {
		return this.horn.setType(owner, type);
	}

	public void setPenis(Penis penis) {
		this.penis = penis;
	}

	public String setPenisType(AbstractPenisType type) {
		return this.penis.setType(null, type);
	}

	public String setPenisType(GameCharacter owner, AbstractPenisType type) {
		return this.penis.setType(owner, type);
	}

	public String addPenisModifier(PenetrationModifier modifier) {
		return this.penis.addPenisModifier(null, modifier);
	}

	public String addPenisModifier(GameCharacter owner, PenetrationModifier modifier) {
		return this.penis.addPenisModifier(owner, modifier);
	}

	public void setTail(Tail tail) {
		this.tail = tail;
	}

	public String setTailType(AbstractTailType type) {
		return this.tail.setType(null, type);
	}

	public String setTailType(GameCharacter owner, AbstractTailType type) {
		return this.tail.setType(owner, type);
	}

	public void setTentacle(Tentacle tentacle) {
		this.tentacle = tentacle;
	}

	public void setVagina(Vagina vagina) {
		this.vagina = vagina;
	}

	public String setVaginaType(AbstractVaginaType type) {
		return this.vagina.setType(null, type);
	}

	public String setVaginaType(GameCharacter owner, AbstractVaginaType type) {
		return this.vagina.setType(owner, type);
	}

	public void setWing(Wing wing) {
		this.wing = wing;
	}

	public String setWingSize(int size) {
		return this.wing.setSize(null, size);
	}

	public String setWingSize(GameCharacter owner, int size) {
		return this.wing.setSize(owner, size);
	}

	public String setWingSizeToMinimumWingSizeForFlight() {
		return wing.setSize(null, getMinimumWingSizeValueForFlight());
	}

	public String setWingSizeToMinimumWingSizeForFlight(GameCharacter owner) {
		return wing.setSize(owner, getMinimumWingSizeValueForFlight());
	}

	public String setWingType(AbstractWingType type) {
		return this.wing.setType(null, type);
	}

	public String setWingType(GameCharacter owner, AbstractWingType type) {
		return this.wing.setType(owner, type);
	}

	public void applyLegConfigurationTransformation(AbstractLegType legType, LegConfiguration legConfiguration, boolean applyFullEffects) {
		this.setLegType(legType);
		this.leg.getType().applyLegConfigurationTransformation(this, legConfiguration, applyFullEffects);
	}

	public Boolean hasTongueModifier(TongueModifier modifier) {
		return face.getTongue().hasTongueModifier(modifier);
	}

	public String addTongueModifier(TongueModifier modifier) {
		return addTongueModifier(null, modifier);
	}

	public String addTongueModifier(GameCharacter owner, TongueModifier modifier) {
		return face.getTongue().addTongueModifier(owner, modifier);
	}

	public void resetTongueModifiers() {
		face.getTongue().resetTongueModifiers();
	}

	public boolean hasWings() {
		return getWingType() != WingType.NONE;
	}
	
	public boolean hasGenericWings() {
		return getWingType().isGeneric();
	}

	public boolean isFaceHuman() {
		return face.getType().getRace() == Race.HUMAN;
	}

	// Descriptions:
	private StringBuilder descriptionSB;

	public String getEyeDescription(GameCharacter owner) {
		StringBuilder sb = new StringBuilder();
		
		if(owner.isFeral()) {
			sb.append("[npc.SheHasFull]拥有[npc.eyePairs][npc.eyeRace]的眼睛，[npc.irisColour(true)]虹膜呈[npc.irisShape]，[npc.pupilColour(true)]瞳孔呈[npc.pupilShape]，巩膜则为[npc.scleraColour(true)]。");
		} else {
			sb.append(""+eye.getType().getBodyDescription(owner));
		}
		
		// Eye makeup:
		if(owner.getEyeLiner().getPrimaryColour()!=PresetColour.COVERING_NONE) {
			sb.append("[npc.her]的[npc.eyes]周描有"+owner.getEyeLiner().getColourDescriptor(owner, true, false)+"的眼线。");
		}
		if(owner.getEyeShadow().getPrimaryColour()!=PresetColour.COVERING_NONE) {
			sb.append("[npc.SheIs]涂有淡淡的"+owner.getEyeShadow().getFullDescription(owner, true)+"。");
		}
		
		return sb.toString();
	}
	
	/**
	 * @param owner The person whose ass is to be described.
	 * @param locationSpecific Whether this description is specific to looking at the person's ass. If they have a cloaca, and you pass in true, it will say something along the lines of "there's no asshole here".
	 * @return A description of this character's asshole.
	 */
	public String getAssDescription(GameCharacter owner, boolean locationSpecific) {
		descriptionSB = new StringBuilder();
		
		switch(owner.getGenitalArrangement()) {
			case CLOACA:
				if(locationSpecific) {
					return UtilText.parse(owner, "[style.italicsFeral([npc.Her]的肛门并不在屁股缝里，而是位于前向的泄殖腔处！)]");
				} else {
					descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her]的肛门位于其细缝状的前向泄殖腔处。)] "));
				}
				break;
			case CLOACA_BEHIND:
				descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her]的肛门位于其细缝状的后向泄殖腔处。)] "));
				break;
			case NORMAL:
			break;
		}
		
		descriptionSB.append(ass.getType().getBodyDescription(owner).trim());
		
		// Colour:
		if(ass.getAnus().isBleached()) {
			descriptionSB.append("，已经经过了漂白，其边缘于周围的[npc.assSkin]颜色已无二致。");
		} else {
			descriptionSB.append("，其边缘比起周围[npc.assSkin]的颜色更深一些。");
		}
		
		if(owner.isFeral()) {
			descriptionSB.append("[style.colourFeral(不出所料，已经完全兽态化，与普通的[npc.assRace]并无二致。)]");
		} else if(ass.isFeral(owner)) {
			descriptionSB.append("[style.colourFeral(作为[npc.her]兽态下半身的一部分，已经完全兽态化，与兽态的[npc.assRace])]并无二致。");
		}

		descriptionSB.append("[npc.her]的菊穴"+Capacity.getCapacityFromValue(ass.getAnus().getOrificeAnus().getStretchedCapacity()).getDescriptor(true)+"，润滑后可以舒适地容纳最大"
				+ "直径为[style.colourSex("+ Units.size(Capacity.getMaximumComfortableDiameter(ass.getAnus().getOrificeAnus().getElasticity(), ass.getAnus().getOrificeAnus().getRawCapacityValue(), true)) + ")]的物体。");

		if(Main.game.isPenetrationLimitationsEnabled()) {
			switch(owner.getAssDepth()) {
				default:
					descriptionSB.append("[npc.Her]的屁股<span style='color:"+owner.getAssDepth().getColour().toWebHexString()+";'>[npc.assDepth]</span>，");
					break;
				case TWO_AVERAGE:
					descriptionSB.append("[npc.Her]的屁股<span style='color:"+owner.getAssDepth().getColour().toWebHexString()+";'>深度适中</span>，");
					break;
			}
			if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，将其能够[style.colourMinorGood(舒适地)]容纳的物体最大长度增加至"
							+ "[style.colourSex("+Units.size(owner.getAssMaximumPenetrationDepthUncomfortable())+")]。");
				} else {
					descriptionSB.append("让[npc.herHim]能[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getAssMaximumPenetrationDepthComfortable())+")]长的物体插入，"
							+ "以及[style.colourMinorBad(不舒服地)]容纳[style.colourSex("+Units.size(owner.getAssMaximumPenetrationDepthUncomfortable())+")]长的物体插入。");
				}
				
			} else {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，加之[npc.her]的身体由[npc.bodyMaterial]构成，"
							+ "[npc.she]能够[style.colourMinorGood(舒适地)]容纳[style.colourSex(任何长度)]的物体。");
				} else {
					descriptionSB.append("让[npc.herHim]能[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getAssMaximumPenetrationDepthComfortable())+")]长的物体插入，"
							+ "且由于[npc.her]的身体[npc.bodyMaterial]构成，[npc.she]能够[style.colourMinorBad(不舒服地)]容纳[style.colourSex(任何长度)]的物体。");
				}
			}
		}
		
		if (ass.getAnus().getOrificeAnus().isVirgin()) {
			descriptionSB.append("[style.colourExcellent([npc.Name]还保留着菊穴贞操。)]");
			
		} else {
			boolean virginityLossFound = false;
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				if(pt.isTakesVirginity()) {
					if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pt))!=null) {
						descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pt)) + "</span>");
						virginityLossFound = true;
						break;
					}
				}
			}
			if(!virginityLossFound) {
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name]已经失去了菊穴贞操。</span>");
			}
		}
		
		// Ass wetness:
		switch (ass.getAnus().getOrificeAnus().getWetness(owner)) {
			case ZERO_DRY:
				descriptionSB.append("目前[style.colourWetness(极其干燥)]，需要在性交前润滑。");
				break;
			case ONE_SLIGHTLY_MOIST:
				descriptionSB.append("目前[style.colourWetness(略微潮湿)]，但仍需在性交前润滑。");
				break;
			case TWO_MOIST:
				descriptionSB.append("目前[style.colourWetness(比较潮湿)]，但仍需在性交前润滑。");
				break;
			case THREE_WET:
				descriptionSB.append("[style.colourWetness(表面不断有潮湿的滴液出现)]，已经足够用作性交的润滑。");
				break;
			case FOUR_SLIMY:
				descriptionSB.append("表面总是[style.colourWetness(很潮湿)]，已经准备好被插入。");
				break;
			case FIVE_SLOPPY:
				descriptionSB.append("表面总是[style.colourWetness(又潮湿又粘滑)]，为插入提供了极佳的润滑。");
				break;
			case SIX_SOPPING_WET:
				descriptionSB.append("表面永远[style.colourWetness(十分湿滑)]，为插入提供了极佳的润滑。");
				break;
			case SEVEN_DROOLING:
				descriptionSB.append("不断地[style.colourWetness(流出水来)]，[style.colourWetness(湿滑)]的入口已经随时准备好被插入。");
				break;
		}
		// Ass elasticity & plasticity:
		switch (ass.getAnus().getOrificeAnus().getElasticity()) {
			case ZERO_UNYIELDING:
				descriptionSB.append("[style.colourElasticity(几乎不会扩张)]，");
				break;
			case ONE_RIGID:
				descriptionSB.append("[style.colourElasticity(想要被撑开需要费九牛二虎之力)]，");
				break;
			case TWO_FIRM:
				descriptionSB.append("[style.colourElasticity(不太容易撑开)]，");
				break;
			case THREE_FLEXIBLE:
				descriptionSB.append("被用于性交时[style.colourElasticity(撑不太开)]，");
				break;
			case FOUR_LIMBER:
				descriptionSB.append("[style.colourElasticity(被扩张时有些抵抗)]，");
				break;
			case FIVE_STRETCHY:
				descriptionSB.append("[style.colourElasticity(可以比较简单地撑开)]，");
				break;
			case SIX_SUPPLE:
				descriptionSB.append("[style.colourElasticity(可以相当轻松地撑开)]，");
				break;
			case SEVEN_ELASTIC:
				descriptionSB.append("[style.colourElasticity(弹性极佳)]，");
				break;
		}
		descriptionSB.append("在被使用之后，会"+ass.getAnus().getOrificeAnus().getPlasticity().getDescription()+"。");
		
		if(Main.game.isAssHairEnabled()) {
			switch(ass.getAnus().getAssHair()) {
				case ZERO_NONE:
					descriptionSB.append("[npc.her]的肛门周围没有"+owner.getAssHairType().getName(owner)+"的踪迹。");
					break;
				case ONE_STUBBLE:
					descriptionSB.append("[npc.her]的肛门周围有几缕"+owner.getAssHairType().getName(owner)+"。");
					break;
				case TWO_MANICURED:
					descriptionSB.append("[npc.her]的肛门周围有极少量"+owner.getAssHairType().getName(owner)+"。");
					break;
				case THREE_TRIMMED:
					descriptionSB.append("[npc.her]的肛门周围有少量"+owner.getAssHairType().getName(owner)+"。");
					break;
				case FOUR_NATURAL:
					descriptionSB.append("[npc.her]的肛门周围自然生长着"+owner.getAssHairType().getName(owner)+"。");
					break;
				case FIVE_UNKEMPT:
					descriptionSB.append("[npc.her]的肛门周围凌乱地生长着"+owner.getAssHairType().getName(owner)+"。");
					break;
				case SIX_BUSHY:
					descriptionSB.append("[npc.her]的肛门周围浓密地生长着"+owner.getAssHairType().getName(owner)+"。");
					break;
				case SEVEN_WILD:
					descriptionSB.append("[npc.her]的肛门周围野蛮地生长着"+owner.getAssHairType().getName(owner)+"。");
					break;
			}
		}
		
		for(OrificeModifier om : OrificeModifier.values()) {
			if(owner.hasAssOrificeModifier(om)) {
				switch(om) {
					case MUSCLE_CONTROL:
						descriptionSB.append("[npc.her]的[npc.asshole]拥有一片活动自如的肌肉，使得[npc.herHim]能够精妙地抓住、挤压任何入侵的物体。");
						break;
					case PUFFY:
						descriptionSB.append("[npc.her]的[npc.asshole]边缘略微膨起，形成了一个松软的甜甜圈似的环。");
						break;
					case RIBBED:
						descriptionSB.append("[npc.her][npc.asshole]的内部长满了敏感的肉质突起，被刺激时可以给[npc.herHim]提供额外的快感。");
						break;
					case TENTACLED:
						descriptionSB.append("[npc.Her]的[npc.asshole]长满了满了细小的触手，自行蜿蜒扭动着。");
						break;
				}
			}
		}
		
		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getBreastDescription(GameCharacter owner) {
		return getBreastDescription(owner, breast);
	}
	
	public String getBreastDescription(GameCharacter owner, Breast viewedBreast) {
		descriptionSB = new StringBuilder();
		
		boolean playerKnowledgeOfBreasts = owner.isAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer());
		
		if(!owner.isPlayer() && !playerKnowledgeOfBreasts) {
			descriptionSB.append("[style.colourDisabled(你从未见过[npc.her]裸露的胸部，所以也不知道乳头到底长什么样子。)]");
			
		} else {
			descriptionSB.append("在每个"+(owner.hasBreasts()?owner.getBreastShape().getDescriptor()+"乳房":"平板胸部")+"上，都有"+Util.intToString(owner.getNippleCountPerBreast())+"颗");
			
			switch(owner.getNippleShape()) {
				case NORMAL:
					descriptionSB.append("[npc.nipplePrimaryColour(true)]的");
					break;
				case INVERTED:
					descriptionSB.append("[npc.nipplePrimaryColour(true)]的内陷");
					break;
				case LIPS:
					descriptionSB.append("[npc.nipplePrimaryColour(true)]唇的");
					break;
				case VAGINA:
					descriptionSB.append("[npc.nipplePrimaryColour(true)]唇的");
					break;
			}
			if(owner.getNippleCountPerBreast()>1) {
				descriptionSB.append("[npc.nipples]，尺寸[npc.nippleSize]，");
			} else {
				descriptionSB.append("[npc.nipple(true)]，尺寸[npc.nippleSize]，");
			}
			
			switch(owner.getAreolaeShape()) {
				case NORMAL:
					descriptionSB.append("周围是[npc.areolaeSize]的环形乳晕。");
					break;
				case HEART:
					descriptionSB.append("周围是[npc.areolaeSize]的心形乳晕。");
					break;
				case STAR:
					descriptionSB.append("周围是[npc.areolaeSize]的星形乳晕。");
					break;
			}

			if(breast.isFeral(owner)) {
				descriptionSB.append("[style.colourFeral(由于[npc.her]的整个身体都完全兽态化，[npc.her]的[npc.nipples]与兽态[npc.breastRace]的并无二致。)]");
			}
			
			if(owner.isPiercedNipple()) {
				descriptionSB.append("都已经穿孔。");
			}
			
			if(owner.getNippleCapacity() != Capacity.ZERO_IMPENETRABLE && Main.game.isNipplePenEnabled()) {
				if (viewedBreast.isFuckable()) {
					descriptionSB.append("<br/>[npc.Her]的[npc.breasts]内有[npc.nippleSecondaryColour(true)]的通道，使[npc.her][npc.breastCapacity]的[npc.nipples]润滑后可以舒适地容纳最大"
							+ "直径为[style.colourSex("+ Units.size(Capacity.getCapacityFromValue(viewedBreast.getNipples().getOrificeNipples().getStretchedCapacity()).getMaximumValue(true)) + ")]的物体。");

					if(Main.game.isPenetrationLimitationsEnabled()) {
						switch(owner.getNippleDepth()) {
							default:
								descriptionSB.append("[npc.Her]足以插入的[npc.nipples]<span style='color:"+owner.getNippleDepth().getColour().toWebHexString()+";'>[npc.breastDepth]</span>，");
								break;
							case TWO_AVERAGE:
								descriptionSB.append("[npc.Her]足以插入的[npc.nipples]<span style='color:"+owner.getNippleDepth().getColour().toWebHexString()+";'>深度适中</span>，");
								break;
						}
						if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
							if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
								descriptionSB.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，将其能够[style.colourMinorGood(舒适地)]容纳的物体最大长度增加至"
										+ "[style.colourSex("+Units.size(owner.getNippleMaximumPenetrationDepthUncomfortable())+")]。");
							} else {
								descriptionSB.append("让[npc.herHim]能[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getNippleMaximumPenetrationDepthComfortable())+")]长的物体插入，"
										+ "以及[style.colourMinorBad(不舒服地)]容纳[style.colourSex("+Units.size(owner.getNippleMaximumPenetrationDepthUncomfortable())+")]长的物体插入。");
							}
							
						} else {
							if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
								descriptionSB.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，加之[npc.her]的身体由[npc.bodyMaterial]构成，"
										+ "[npc.she]能够[style.colourMinorGood(舒适地)]容纳[style.colourSex(任何长度)]的物体。");
							} else {
								descriptionSB.append("让[npc.herHim]能[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getNippleMaximumPenetrationDepthComfortable())+")]长的物体插入，"
										+ "且由于[npc.her]的身体[npc.bodyMaterial]构成，[npc.she]能够[style.colourMinorBad(不舒服地)]容纳[style.colourSex(任何长度)]的物体。");
							}
						}
					}
					
				} else {
					descriptionSB.append("<br/>[npc.Her]的[npc.breasts]内有[npc.nippleSecondaryColour(true)]的通道，"
							+ "但[style.colourBad(至少需要到达C罩杯)]，"
							+ Capacity.getCapacityFromValue(viewedBreast.getNipples().getOrificeNipples().getStretchedCapacity()).getDescriptor(true)+"的[npc.nipples]才能被插入。");
				}

				// Nipple elasticity & plasticity:
				switch (viewedBreast.getNipples().getOrificeNipples().getElasticity()) {
					case ZERO_UNYIELDING:
						descriptionSB.append("[style.colourElasticity(几乎不会扩张)]，");
						break;
					case ONE_RIGID:
						descriptionSB.append("[style.colourElasticity(想要被撑开需要费九牛二虎之力)]，");
						break;
					case TWO_FIRM:
						descriptionSB.append("[style.colourElasticity(不太容易撑开)]，");
						break;
					case THREE_FLEXIBLE:
						descriptionSB.append("被用于性交时[style.colourElasticity(撑不太开)]，");
						break;
					case FOUR_LIMBER:
						descriptionSB.append("[style.colourElasticity(被扩张时有些抵抗)]，");
						break;
					case FIVE_STRETCHY:
						descriptionSB.append("[style.colourElasticity(可以比较简单地撑开)]，");
						break;
					case SIX_SUPPLE:
						descriptionSB.append("[style.colourElasticity(可以相当轻松地撑开)]，");
						break;
					case SEVEN_ELASTIC:
						descriptionSB.append("[style.colourElasticity(弹性极佳)]，");
						break;
				}
				descriptionSB.append("在被使用之后，它们会"+viewedBreast.getNipples().getOrificeNipples().getPlasticity().getDescriptionPlural()+"。");
				
				for(OrificeModifier om : OrificeModifier.values()) {
					if(owner.hasNippleOrificeModifier(om)) {
						switch(om) {
							case MUSCLE_CONTROL:
								descriptionSB.append("[npc.She]的[npc.nipples]拥有一片活动自如的肌肉，使得[npc.herHim]能够精妙地抓住、挤压任何入侵的物体。");
								break;
							case PUFFY:
								descriptionSB.append("[npc.her]的[npc.nipples]略微膨起，格外丰满有肉感。");
								break;
							case RIBBED:
								descriptionSB.append("[npc.her][npc.nipples]的内部长满了敏感的肉质突起，被刺激时可以给[npc.herHim]提供额外的快感。");
								break;
							case TENTACLED:
								descriptionSB.append("[npc.Her]的[npc.nipples]长满了满了细小的触手，自行蜿蜒扭动着。");
								break;
						}
					}
				}
				
				if (!viewedBreast.getNipples().getOrificeNipples().isVirgin()) {
					boolean virginityLossFound = false;
					for(SexAreaPenetration pt : SexAreaPenetration.values()) {
						if(pt.isTakesVirginity()) {
							if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, pt))!=null) {
								descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, pt)) + "</span>");
								virginityLossFound = true;
								break;
							}
						}
					}
					if(!virginityLossFound) {
						descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name]已经失去了乳头贞操。</span>");
					}
					
				} else {
					descriptionSB.append("[style.colourExcellent([npc.Name]还保留着乳头贞操。)]");
				}
				
			} else {
				if(owner.hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
					descriptionSB.append("[npc.her]的[npc.nipples]略微膨起，格外丰满有肉感。");
				}	
			}
			
			if (viewedBreast.getRawMilkStorageValue() > 0) {
				descriptionSB.append("<br/>[npc.SheIsFull]能够分泌"+ Units.fluid(viewedBreast.getRawMilkStorageValue(), Units.UnitType.LONG) + "[npc.milkPrimaryColour(true)]的[npc.milk]"
						+ "(目前积蓄了"+ Units.fluid(viewedBreast.getRawStoredMilkValue()) + ")，分泌速度[npc.milkRegen]。");
				
				switch(viewedBreast.getMilk().getFlavour()) {
					case MILK:
						descriptionSB.append("[npc.Her][npc.milkColour(true)]的[npc.milk]尝起来与一般的乳汁差不多。");
						break;
					case BUBBLEGUM:
						descriptionSB.append("[npc.Her][npc.milkColour(true)]的[npc.milk]尝起来像水果泡泡糖的味道。");
						break;
					case FLAVOURLESS:
						descriptionSB.append("[npc.Her][npc.milkColour(true)]的[npc.milk]完全没有味道。");
						break;
					default:
						descriptionSB.append("[npc.Her][npc.milkColour(true)]的[npc.milk]尝起来跟"+viewedBreast.getMilk().getFlavour().getName()+"一模一样。");
						break;
				}
				
				for(FluidModifier fm : FluidModifier.values()) {
					if(owner.hasMilkModifier(fm)) {
						descriptionSB.append(fm.getBriefDescription());
					}
				}
				
			} else {
				descriptionSB.append("<br/>[npc.SheIsFull]不分泌乳汁。");
			}
		}

		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getBreastCrotchDescription(GameCharacter owner) {
		return getBreastCrotchDescription(owner, breastCrotch);
	}

	public String getBreastCrotchDescription(GameCharacter owner, BreastCrotch viewedBreastCrotch) {
		descriptionSB = new StringBuilder();
		
//		boolean playerKnowledgeOfUdders = owner.isAreaKnownByCharacter(CoverableArea.NIPPLES_CROTCH, Main.game.getPlayer());
//		
//		if(owner.isPlayer() || playerKnowledgeOfUdders) {
			if(owner.isFeral()) {
				if(owner.getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
					descriptionSB.append("[npc.her]兽态身体的腹股沟之下，");
				} else {
					descriptionSB.append("[npc.her]兽态身体的腹股沟上方一点，");
				}
			} else {
				descriptionSB.append(leg.getLegConfiguration().getCrotchBoobLocationDescription());
			}
			if(breastCrotch.getShape()==BreastShape.UDDERS) {
				if(breastCrotch.getRawSizeValue()>0){
					descriptionSB.append("有[npc.a_crotchBoobsSize]的");
				} else {
					descriptionSB.append("只有一片平坦的");
				}
				if(breastCrotch.getRows()>0) {
					descriptionSB.append((breastCrotch.getRows()>1?"数对":"一对")+"腹乳。");
					
				} else {
					descriptionSB.append("腹乳，");
				}
				
			} else {
				if(breastCrotch.getRawSizeValue()>0){
					descriptionSB.append("[npc.she]拥有[npc.crotchBoobsRows][npc.crotchBoobsCups]罩杯的[npc.crotchBoobsSize][npc.crotchBoobs]");
				} else {
					descriptionSB.append("[npc.she]拥有[npc.crotchBoobsRows]完全平坦的[npc.crotchBoobs]");
				}
				if(leg.getLegConfiguration().isBipedalPositionedCrotchBoobs() && breastCrotch.getRows()>1) {
					descriptionSB.append("，均位于[npc.her]肚子偏上方。");
					
				} else if(breastCrotch.getRows()>1) {
					descriptionSB.append("，多出的腹乳则位于[npc.her]的偏下腹部。");
					
				} else {
					descriptionSB.append("。");
				}
			}
			
			if(viewedBreastCrotch.getShape()==BreastShape.UDDERS) {
				if(breastCrotch.getRows()>0) {
					descriptionSB.append("总共有[npc.totalCrotchBoobs]处突出，每个上面都有[npc.crotchBoobsNipplesPerBreast]颗[npc.crotchNippleSize]的");
				} else {
					descriptionSB.append("上面有[npc.crotchBoobsNipplesPerBreast]颗[npc.crotchNippleSize]的");
				}
			} else {
				descriptionSB.append("[npc.her][npc.totalCrotchBoobs]个[npc.crotchBoobs]的每一个上面，都有[npc.crotchBoobsNipplesPerBreast]颗[npc.crotchNippleSize]的");
			}
			
			switch(owner.getNippleCrotchShape()) {
				case NORMAL:
					descriptionSB.append("[npc.crotchNipplePrimaryColour(true)]");
					break;
				case INVERTED:
					descriptionSB.append("[npc.crotchNipplePrimaryColour(true)]内陷");
					break;
				case LIPS:
					descriptionSB.append("[npc.crotchNipplePrimaryColour(true)]唇");
					break;
				case VAGINA:
					descriptionSB.append("[npc.crotchNipplePrimaryColour(true)]唇");
					break;
			}
			if(owner.getNippleCrotchCountPerBreast()>1) {
				descriptionSB.append("[npc.crotchNipples]，");
			} else {
				descriptionSB.append("[npc.crotchNipple(true)]，");
			}
			
			switch(owner.getAreolaeCrotchShape()) {
				case NORMAL:
					descriptionSB.append("周围是[npc.crotchBoobsAreolaSize]的环形乳晕。");
					break;
				case HEART:
					descriptionSB.append("周围是[npc.crotchBoobsAreolaSize]的心形乳晕。");
					break;
				case STAR:
					descriptionSB.append("周围是[npc.crotchBoobsAreolaSize]的星形乳晕。");
					break;
			}
			if(owner.isFeral()) {
				descriptionSB.append("[style.colourFeral(由于[npc.her]的整个身体都完全兽态化，[npc.her]的[npc.crotchBoobs]与兽态[npc.crotchBoobRace]的并无二致。)]");
			} else if(breastCrotch.isFeral(owner)) {
				descriptionSB.append("[style.colourFeral(由于[npc.her]的整个身体都完全兽态化，[npc.her]的[npc.crotchBoobs]与兽态[npc.crotchBoobRace]的并无二致。)]");
			}
			
			if(owner.isPiercedNippleCrotch()) {
				descriptionSB.append("都已经穿孔。");
			}
			
			if(owner.getNippleCrotchCapacity() != Capacity.ZERO_IMPENETRABLE && Main.game.isNipplePenEnabled()) {
				if (viewedBreastCrotch.isFuckable()) {
					descriptionSB.append("<br/>[npc.Her]的[npc.crotchBoobs]内有[npc.crotchNippleSecondaryColour(true)]的通道，使[npc.her][npc.crotchBoobsCapacity]的[npc.crotchNipples]润滑后可以舒适地容纳最大"
								+ "[style.colourSex("+ Units.size(Capacity.getCapacityFromValue(viewedBreastCrotch.getNipples().getOrificeNipples().getStretchedCapacity()).getMaximumValue(true)) + ")]的物体。");

					if(Main.game.isPenetrationLimitationsEnabled()) {
						switch(owner.getNippleCrotchDepth()) {
							default:
								descriptionSB.append("[npc.Her]足以插入的[npc.crotchNipples]<span style='color:"+owner.getNippleCrotchDepth().getColour().toWebHexString()+";'>[npc.crotchBreastDepth]</span>，");
								break;
							case TWO_AVERAGE:
								descriptionSB.append("[npc.Her]足以插入的[npc.crotchNipples]<span style='color:"+owner.getNippleCrotchDepth().getColour().toWebHexString()+";'>深度适中</span>，");
								break;
						}
						if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
							if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
								descriptionSB.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，将其能够[style.colourMinorGood(舒适地)]容纳的物体最大长度增加至"
										+ "[style.colourSex("+Units.size(owner.getNippleCrotchMaximumPenetrationDepthUncomfortable())+")]。");
							} else {
								descriptionSB.append("让[npc.herHim]能[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getNippleCrotchMaximumPenetrationDepthComfortable())+")]的物体插入，"
										+ "以及[style.colourMinorBad(不舒服地)]容纳[style.colourSex("+Units.size(owner.getNippleCrotchMaximumPenetrationDepthUncomfortable())+")]长的物体插入。");
							}
							
						} else {
							if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
								descriptionSB.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，加之[npc.her]的身体由[npc.bodyMaterial]构成，"
										+ "[npc.she]能够[style.colourMinorGood(舒适地)]容纳[style.colourSex(任何长度)]的物体。");
							} else {
								descriptionSB.append("让[npc.herHim]能[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getNippleCrotchMaximumPenetrationDepthComfortable())+")]的物体插入，"
										+ "且由于[npc.her]的身体[npc.bodyMaterial]构成，[npc.she]能够[style.colourMinorBad(不舒服地)]容纳[style.colourSex(任何长度)]的物体。");
							}
						}
					}
					
				} else {
					descriptionSB.append("<br/>[npc.Her]的[npc.crotchBoobs]内有[npc.crotchNippleSecondaryColour(true)]的通道，"
							+ "但[style.colourBad(至少需要到达C罩杯)]，"
							+ Capacity.getCapacityFromValue(viewedBreastCrotch.getNipples().getOrificeNipples().getStretchedCapacity()).getDescriptor(true)+ "的[npc.crotchNipples]才能被插入。");
				}

				// Nipple elasticity & plasticity:
				switch (viewedBreastCrotch.getNipples().getOrificeNipples().getElasticity()) {
					case ZERO_UNYIELDING:
						descriptionSB.append("[style.colourElasticity(几乎不会扩张)]，");
						break;
					case ONE_RIGID:
						descriptionSB.append("[style.colourElasticity(想要被撑开需要费九牛二虎之力)]，");
						break;
					case TWO_FIRM:
						descriptionSB.append("[style.colourElasticity(不太容易撑开)]，");
						break;
					case THREE_FLEXIBLE:
						descriptionSB.append("被用于性交时[style.colourElasticity(撑不太开)]，");
						break;
					case FOUR_LIMBER:
						descriptionSB.append("[style.colourElasticity(被扩张时有些抵抗)]，");
						break;
					case FIVE_STRETCHY:
						descriptionSB.append("[style.colourElasticity(可以比较简单地撑开)]，");
						break;
					case SIX_SUPPLE:
						descriptionSB.append("[style.colourElasticity(可以相当轻松地撑开)]，");
						break;
					case SEVEN_ELASTIC:
						descriptionSB.append("[style.colourElasticity(弹性极佳)]，");
						break;
				}
				descriptionSB.append("在被使用之后，它们会"+viewedBreastCrotch.getNipples().getOrificeNipples().getPlasticity().getDescriptionPlural()+"。");
				
				for(OrificeModifier om : OrificeModifier.values()) {
					if(owner.hasNippleCrotchOrificeModifier(om)) {
						switch(om) {
							case MUSCLE_CONTROL:
								descriptionSB.append("[npc.She]的[npc.crotchNipples]拥有一片活动自如的肌肉，使得[npc.herHim]能够精妙地抓住、挤压任何入侵的物体。");
								break;
							case PUFFY:
								descriptionSB.append("[npc.her]的[npc.crotchNipples]略微膨起，格外丰满有肉感。");
								break;
							case RIBBED:
								descriptionSB.append("[npc.her][npc.crotchNipples]的内部长满了敏感的肉质突起，被刺激时可以给[npc.herHim]提供额外的快感。");
								break;
							case TENTACLED:
								descriptionSB.append("[npc.Her]的[npc.crotchNipples]长满了满了细小的触手，自行蜿蜒扭动着。");
								break;
						}
					}
				}
				
				if (!viewedBreastCrotch.getNipples().getOrificeNipples().isVirgin()) {
					boolean virginityLossFound = false;
					for(SexAreaPenetration pt : SexAreaPenetration.values()) {
						if(pt.isTakesVirginity()) {
							if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE_CROTCH, pt))!=null) {
								descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE_CROTCH, pt)) + "</span>");
								virginityLossFound = true;
								break;
							}
						}
					}
					if(!virginityLossFound) {
						descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name]已经失去了[npc.crotchNipple]贞操。</span>");
					}
					
				} else {
					descriptionSB.append("[style.colourExcellent([npc.Name]还保留着[npc.crotchNipple]贞操。)]");
				}
				
			} else {
				if(owner.hasNippleCrotchOrificeModifier(OrificeModifier.PUFFY)) {
					descriptionSB.append("[npc.her]的[npc.crotchNipples]略微膨起，格外丰满有肉感。");
				}	
			}
			
			if (viewedBreastCrotch.getRawMilkStorageValue() > 0) {
				descriptionSB.append("<br/>[npc.SheIsFull]能够分泌"
						+ Units.fluid(viewedBreastCrotch.getRawMilkStorageValue(), Units.UnitType.LONG) + "[npc.crotchMilkPrimaryColour(true)]的[npc.crotchMilk]("
						+ "目前积蓄了" + Units.fluid(viewedBreastCrotch.getRawStoredMilkValue(), Units.UnitType.LONG) + ")，分泌速度[npc.crotchMilkRegen]。");
				
				switch(viewedBreastCrotch.getMilk().getFlavour()) {
					case MILK:
						descriptionSB.append("[npc.Her][npc.crotchMilkColour(true)]的[npc.crotchMilk]尝起来与一般的乳汁差不多。");
						break;
					case BUBBLEGUM:
						descriptionSB.append("[npc.Her][npc.crotchMilkColour(true)]的[npc.crotchMilk]尝起来像水果泡泡糖的味道。");
						break;
					case FLAVOURLESS:
						descriptionSB.append("[npc.Her][npc.crotchMilkColour(true)]的[npc.crotchMilk]完全没有味道。");
						break;
					default:
						descriptionSB.append("[npc.Her][npc.crotchMilkColour(true)]的[npc.crotchMilk]尝起来跟"+viewedBreastCrotch.getMilk().getFlavour().getName()+"一模一样。");
						break;
				}
				
				for(FluidModifier fm : FluidModifier.values()) {
					if(owner.hasMilkCrotchModifier(fm)) {
						descriptionSB.append(fm.getBriefDescription());
					}
				}
				
			} else {
				descriptionSB.append("<br/>[npc.SheIsFull]不分泌乳汁。");
			}
//		}

		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getPenisDescription(GameCharacter owner) {
		boolean isPlayer = owner.isPlayer();
		
		descriptionSB = new StringBuilder();
		
		Penis viewedPenis = owner.getCurrentPenis();
		
		if(Main.game.getPlayer().hasIngestedPsychoactiveFluidType(FluidTypeBase.CUM)) {
			viewedPenis = new Penis(penis.getType(),
					(int) (penis.getRawLengthValue() * 2.25f),
					false,
					PenetrationGirth.FIVE_THICK.getValue(),
					penis.getTesticle().getTesticleSize().getValue()*2,
					(int) ((penis.getTesticle().getRawCumStorageValue()+100) * 3.25f),
					penis.getTesticle().getTesticleCount());
			descriptionSB.append("<i style='color:"+PresetColour.PSYCHOACTIVE.toWebHexString()+";'>你最近摄入的致幻精液导致你看到"+(owner.isPlayer()?"你的":"[npc.namePos]的")+"阴茎发生了扭曲！</i>");
		}

		switch(owner.getGenitalArrangement()) {
			case CLOACA:
				descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her]的阴茎为位于其细缝状的前向泄殖腔处。)] "));
				break;
			case CLOACA_BEHIND:
				descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her]阴茎位于其细缝状的后向泄殖腔处。)] "));
				break;
			case NORMAL:
			break;
		}
		
		String penisAppearance = owner.getPenisType().getBodyDescription(owner);
		if(Main.game.getPlayer().hasIngestedPsychoactiveFluidType(FluidTypeBase.CUM)) {
			penisAppearance = penisAppearance.replaceAll("\\[npc\\.a_cockGirth\\]", UtilText.generateSingularDeterminer(viewedPenis.getGirth().getName())+" "+viewedPenis.getGirth().getName());
			penisAppearance = penisAppearance.replaceAll("\\[npc\\.cockLengthValue\\]", Units.size(viewedPenis.getRawLengthValue(), Units.UnitType.LONG_SINGULAR));
			descriptionSB.append(penisAppearance);
			descriptionSB.append("直径为"+Units.size(viewedPenis.getDiameter(), Units.UnitType.LONG)+"(周长为"+Units.size(viewedPenis.getDiameter()* Math.PI, Units.UnitType.LONG)+")。");
			
		} else {
			descriptionSB.append(penisAppearance);
			descriptionSB.append("直径为[npc.penisDiameter(true)](周长为[npc.penisCircumference(true)])。");
		}

		if(viewedPenis.getType()!=PenisType.DILDO) {
			if(owner.isFeral()) {
				descriptionSB.append("[style.colourFeral(不出所料，[npc.her]的阴茎[npc.cock]已经完全兽态化，与普通的[npc.penisRace]并无二致。)]");
			} else if(penis.isFeral(owner)) {
				descriptionSB.append("[style.colourFeral(由于[npc.her]的外生殖器位于其身体的动物部分，[npc.her]的[npc.penis]与兽态[npc.penisRace]的功能完全相同。)]");
			}
		}
		
		for(PenetrationModifier pm : PenetrationModifier.getPenetrationModifiers()) {
			if(owner.hasPenisModifier(pm)) {
				switch(pm) {
					case RIBBED:
						descriptionSB.append("整根都长满了稍硬的肉质突起，绝对能为任何被插入的腔穴带来额外的快感。");
						break;
					case TENTACLED:
						descriptionSB.append("一片细小的触手覆盖在表面，自行蜿蜒扭动着。");
						break;
					case BARBED:
						descriptionSB.append("肉质的倒刺布满其表面。");
						break;
					case BLUNT:
						descriptionSB.append("头部形成了光滑的弯曲表面。");
						break;
					case FLARED:
						descriptionSB.append("头部又平又宽。");
						break;
					case KNOTTED:
						descriptionSB.append("根部有一个膨大的结。");
						break;
					case PREHENSILE:
						descriptionSB.append("极其灵活，能够像灵长目的尾巴一样操纵。");
						break;
					case SHEATHED:
						descriptionSB.append("未使用时会缩回根部的鞘内。");
						break;
					case TAPERED:
						descriptionSB.append("肉竿从根部开始逐渐变细，直到顶端。");
						break;
					case VEINY:
						descriptionSB.append("表面布满了明显的静脉脉络。");
						break;
					case OVIPOSITOR:
						descriptionSB.append("内部结构使得其能够作产卵器，允许[npc.name]将[npc.her]已受精的卵产在目标的腔穴内。");
						break;
				}
			}
		}

		if(owner.getCurrentPenis().getType()!=PenisType.DILDO) {
			if (!owner.getCurrentPenis().isVirgin()) {
				boolean virginityLossFound = false;
				for(SexAreaOrifice ot : SexAreaOrifice.values()) {
					if(ot.isInternalOrifice()) {
						if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, ot))!=null) {
							descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, ot)) + "</span>");
							virginityLossFound = true;
							break;
						}
					}
				}
				if(!virginityLossFound) {
					descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name]已经失去了童贞。</span>");
				}
				
			} else {
				descriptionSB.append("[style.colourExcellent([npc.Name]还保留着童贞。)]");
			}
		}
		
		// Capacity:
		if (Capacity.getCapacityFromValue(viewedPenis.getOrificeUrethra().getStretchedCapacity()) != Capacity.ZERO_IMPENETRABLE) {
			descriptionSB.append("<br/>[npc.Her]的阴茎尿道已经足够松弛，目前适合插入，润滑后可以舒适地容纳最大"
					+ "[style.colourSex("+ Units.size(Capacity.getCapacityFromValue(viewedPenis.getOrificeUrethra().getStretchedCapacity()).getMaximumValue(true)) + ")]的物体。");

			if(Main.game.isPenetrationLimitationsEnabled()) {
				switch(owner.getUrethraDepth()) {
					default:
						descriptionSB.append("[npc.Her]足以插入的尿道<span style='color:"+owner.getUrethraDepth().getColour().toWebHexString()+";'>[npc.penisUrethraDepth]</span>，");
						break;
					case TWO_AVERAGE:
						descriptionSB.append("[npc.Her]足以插入的尿道<span style='color:"+owner.getUrethraDepth().getColour().toWebHexString()+";'>深度适中</span>，");
						break;
				}
				if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
					if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						descriptionSB.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，将其能够[style.colourMinorGood(舒适地)]容纳的物体最大长度增加至"
								+ "[style.colourSex("+Units.size(owner.getUrethraMaximumPenetrationDepthUncomfortable())+")]。");
					} else {
						descriptionSB.append("让[npc.herHim]能[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getUrethraMaximumPenetrationDepthComfortable())+")]的物体插入，"
								+ "以及[style.colourMinorBad(不舒服地)]容纳[style.colourSex("+Units.size(owner.getUrethraMaximumPenetrationDepthUncomfortable())+")]长的物体插入。");
					}
					
				} else {
					if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						descriptionSB.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，加之[npc.her]的身体由[npc.bodyMaterial]构成，"
								+ "[npc.she]能够[style.colourMinorGood(舒适地)]容纳[style.colourSex(任何长度)]的物体。");
					} else {
						descriptionSB.append("让[npc.herHim]能[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getUrethraMaximumPenetrationDepthComfortable())+")]的物体插入，"
								+ "且由于[npc.her]的身体[npc.bodyMaterial]构成，[npc.she]能够[style.colourMinorBad(不舒服地)]容纳[style.colourSex(任何长度)]的物体。");
					}
				}
			}
			
			switch (viewedPenis.getOrificeUrethra().getElasticity()) {
				case ZERO_UNYIELDING:
					descriptionSB.append("[style.colourElasticity(几乎不会扩张)]，");
					break;
				case ONE_RIGID:
					descriptionSB.append("[style.colourElasticity(想要被撑开需要费九牛二虎之力)]，");
					break;
				case TWO_FIRM:
					descriptionSB.append("[style.colourElasticity(不太容易撑开)]，");
					break;
				case THREE_FLEXIBLE:
					descriptionSB.append("被用于性交时[style.colourElasticity(撑不太开)]，");
					break;
				case FOUR_LIMBER:
					descriptionSB.append("[style.colourElasticity(被扩张时有些抵抗)]，");
					break;
				case FIVE_STRETCHY:
					descriptionSB.append("[style.colourElasticity(可以比较简单地撑开)]，");
					break;
				case SIX_SUPPLE:
					descriptionSB.append("[style.colourElasticity(可以相当轻松地撑开)]，");
					break;
				case SEVEN_ELASTIC:
					descriptionSB.append("[style.colourElasticity(弹性极佳)]，");
					break;
			}
			descriptionSB.append("在被使用之后，会"+viewedPenis.getOrificeUrethra().getPlasticity().getDescription()+"。");
			
			for(OrificeModifier om : OrificeModifier.values()) {
				if(owner.hasUrethraOrificeModifier(om)) {
					switch(om) {
						case PUFFY:
							descriptionSB.append("[npc.Her]的尿道边缘略微膨起，变得更加有肉感了。");
							break;
						case MUSCLE_CONTROL:
							descriptionSB.append("[npc.She]的尿道拥有一片活动自如的肌肉，使得[npc.herHim]能够精妙地抓住、挤压任何入侵的物体。");
							break;
						case RIBBED:
							descriptionSB.append("[npc.her]的尿道内部长满了敏感的肉质突起，被刺激时可以给[npc.herHim]提供额外的快感。");
							break;
						case TENTACLED:
							descriptionSB.append("[npc.Her]的尿道长满了满了细小的触手，自行蜿蜒扭动着。");
							break;
					}
				}
			}
		}

		if(viewedPenis.getType()!=PenisType.DILDO) {
			if (isPlayer && !owner.isUrethraVirgin()) {
				for(SexAreaPenetration pt : SexAreaPenetration.values()) {
					if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, pt))!=null) {
						descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, pt)) + "</span>");
						break;
					}
				}
			}
			descriptionSB.append("<br/>");
		}
		

		if(viewedPenis.getType()!=PenisType.DILDO) {
			// Pubic Hair:
			if(Main.game.isPubicHairEnabled()) {
				if(owner.getPubicHairType().getType()==BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR) {
					switch(owner.getPubicHair()) {
						case ZERO_NONE:
							descriptionSB.append("[npc.her]的阴茎根部周围没有硬质"+owner.getPubicHairType().getName(owner)+"的踪迹。");
							break;
						case ONE_STUBBLE:
							descriptionSB.append("[npc.She]的阴茎根部周围长着一小片硬质的"+owner.getPubicHairType().getFullDescription(owner, true)+"。");
							break;
						case TWO_MANICURED:
							descriptionSB.append("[npc.She]的阴茎根部周围长着一片硬质的"+owner.getPubicHairType().getFullDescription(owner, true)+"。");
							break;
						case THREE_TRIMMED:
							descriptionSB.append("[npc.She]的阴茎根部周围长着一片硬质的"+owner.getPubicHairType().getFullDescription(owner, true)+"。");
							break;
						case FOUR_NATURAL:
							descriptionSB.append("[npc.She]的阴茎根部周围自然生长着一片硬质的"+owner.getPubicHairType().getFullDescription(owner, true)+"。");
							break;
						case FIVE_UNKEMPT:
							descriptionSB.append("[npc.She]的阴茎根部周围凌乱地长着一大片硬质的"+owner.getPubicHairType().getFullDescription(owner, true)+"。");
							break;
						case SIX_BUSHY:
							descriptionSB.append("[npc.She]的阴茎根部周围浓密地生长着一大片硬质的"+owner.getPubicHairType().getFullDescription(owner, true)+"。");
							break;
						case SEVEN_WILD:
							descriptionSB.append("[npc.She]的阴茎根部周围野蛮地生长着一大片硬质的"+owner.getPubicHairType().getFullDescription(owner, true)+"。");
							break;
					}
				} else {
					switch(owner.getPubicHair()) {
						case ZERO_NONE:
							descriptionSB.append("的阴茎根部周围没有任何"+owner.getPubicHairType().getName(owner)+"的踪迹。");
							break;
						case ONE_STUBBLE:
							descriptionSB.append("[npc.her]的阴茎根部周围没有"+owner.getPubicHairType().getName(owner)+"的踪迹。");
							break;
						case TWO_MANICURED:
							descriptionSB.append("[npc.She]的阴茎根部周围长着一小片整齐的"+owner.getPubicHairType().getFullDescription(owner, true)+"。");
							break;
						case THREE_TRIMMED:
							descriptionSB.append("[npc.She]的阴茎根部周围长着一小片修剪过的"+owner.getPubicHairType().getFullDescription(owner, true)+"。");
							break;
						case FOUR_NATURAL:
							descriptionSB.append("[npc.She]的阴茎根部周围自然生长着一片"+owner.getPubicHairType().getFullDescription(owner, true)+"。");
							break;
						case FIVE_UNKEMPT:
							descriptionSB.append("[npc.She]的阴茎根部周围凌乱地生长着一片"+owner.getPubicHairType().getFullDescription(owner, true)+"。");
							break;
						case SIX_BUSHY:
							descriptionSB.append("[npc.She]的阴茎根部周围浓密地生长着一大片"+owner.getPubicHairType().getFullDescription(owner, true)+"。");
							break;
						case SEVEN_WILD:
							descriptionSB.append("[npc.She]的阴茎根部周围野蛮地生长着一大片"+owner.getPubicHairType().getFullDescription(owner, true)+"。");
							break;
					}
				}
			}
	
			descriptionSB.append("<br/>");
		}
		
		// Testicle size and cum production:

		if(viewedPenis.getType()!=PenisType.DILDO) {
			if(owner.isInternalTesticles()) {
				descriptionSB.append("[npc.Her]的[npc.ballsCount]颗[npc.balls+]现在位于体内，表面上的男性生殖器官只有[npc.her]的[npc.cock]。");
				
			} else {
				switch (viewedPenis.getTesticle().getTesticleSize()) {
					case ZERO_VESTIGIAL:
						descriptionSB.append("[npc.Her]的[npc.ballsCount]颗[npc.balls][npc.materialCompositionDescriptor][npc.ballFullDescription(true)]，"
								+ "但过于微小，看上去只是蜷缩在[npc.cock]下方的小突起。");
						break;
					case ONE_TINY:
						descriptionSB.append("[npc.Her]的[npc.ballsCount]颗[npc.balls][npc.materialCompositionDescriptor][npc.ballFullDescription(true)]，尺寸并不大，能够安稳地待在[npc.cock]之下。");
						break;
					case TWO_AVERAGE:
						descriptionSB.append("[npc.Her]的[npc.ballsCount]颗[npc.balls+][npc.materialCompositionDescriptor][npc.ballFullDescription(true)]，在[npc.cock]下面晃荡着。");
						break;
					case THREE_LARGE:
						descriptionSB.append("[npc.Her]的[npc.ballsCount]颗[npc.balls+][npc.materialCompositionDescriptor][npc.ballFullDescription(true)]，在[npc.cock]下面悬挂着。");
						break;
					case FOUR_HUGE:
						descriptionSB.append("[npc.Her]的[npc.ballsCount]颗[npc.balls+][npc.materialCompositionDescriptor][npc.ballFullDescription(true)]，在[npc.cock]下面悬挂着。");
						break;
					case FIVE_MASSIVE:
						descriptionSB.append("[npc.Her]的[npc.ballsCount]颗[npc.balls+][npc.materialCompositionDescriptor][npc.ballFullDescription(true)]，在[npc.cock]下面悬挂着。");
						break;
					case SIX_GIGANTIC:
						descriptionSB.append("[npc.Her]的[npc.ballsCount]颗[npc.balls+][npc.materialCompositionDescriptor][npc.ballFullDescription(true)]，在[npc.cock]下面悬挂着。");
						break;
					case SEVEN_ABSURD:
						descriptionSB.append("[npc.Her]的[npc.ballsCount]颗[npc.balls+][npc.materialCompositionDescriptor][npc.ballFullDescription(true)]，在[npc.cock]下面悬挂着。");
						break;
				}
			}
			
			switch (viewedPenis.getTesticle().getCumStorage()) {
				case ZERO_NONE:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() > TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append("尽管尺寸很大，但只");
					} else {
						descriptionSB.append("");
					}
					descriptionSB.append("无法产生任何[npc.cum+]。");
					break;
				case ONE_TRICKLE:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() > TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append("尽管尺寸很大，但只");
					} else {
						descriptionSB.append("");
					}
					descriptionSB.append("能产生微量[npc.cum+]。");
					break;
				case TWO_SMALL_AMOUNT:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() > TesticleSize.THREE_LARGE.getValue()) {
						descriptionSB.append("尽管尺寸很大，但");
					} else {
						descriptionSB.append("");
					}
					descriptionSB.append("能产生少量[npc.cum+]。");
					break;
				case THREE_AVERAGE:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() > TesticleSize.FOUR_HUGE.getValue()) {
						descriptionSB.append("尽管尺寸巨大，但只");
					} else {
						descriptionSB.append("");
					}
					descriptionSB.append("能产生差不多量[npc.cum+]。");
					break;
				case FOUR_LARGE:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append("尽管尺寸不大，但");
					} else {
						descriptionSB.append("");
					}
					descriptionSB.append("能产生大量[npc.cum+]。");
					break;
				case FIVE_HUGE:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append("尽管尺寸不大，但");
					} else {
						descriptionSB.append("");
					}
					descriptionSB.append("能产生巨量[npc.cum+]。");
					break;
				case SIX_EXTREME:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append("尽管尺寸不大，但");
					} else {
						descriptionSB.append("");
					}
					descriptionSB.append("能产生极巨量[npc.cum+]。");
					break;
				case SEVEN_MONSTROUS:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append("尽管尺寸不大，但");
					} else {
						descriptionSB.append("");
					}
					descriptionSB.append("能产生恐怖量[npc.cum+]。");
					break;
			}
			
			descriptionSB.append("[npc.Her]的[npc.cumColour(true)][npc.cum]");
			
			switch(viewedPenis.getTesticle().getCum().getFlavour()) {
				case CUM:
					descriptionSB.append("，不出所料，尝起来像精液。");
					break;
				case BUBBLEGUM:
					descriptionSB.append("尝起来像水果口味的泡泡糖。");
					break;
				case FLAVOURLESS:
					descriptionSB.append("没有任何味道。");
					break;
				default:
					descriptionSB.append("尝起来跟"+viewedPenis.getTesticle().getCum().getFlavour().getName()+"一模一样。");
					break;
			}
			
			for(FluidModifier fm : FluidModifier.values()) {
				if(owner.hasCumModifier(fm)) {
					descriptionSB.append(fm.getBriefDescription());
				}
			}
		}
		
		return UtilText.parse(owner, descriptionSB.toString());
	}
	
	public String getVaginaDescription(GameCharacter owner) {
		boolean isPlayer = owner.isPlayer();
		
		descriptionSB = new StringBuilder();

		Vagina viewedVagina = vagina;
		
		boolean hallucinating = false;
		if(Main.game.getPlayer().hasIngestedPsychoactiveFluidType(FluidTypeBase.GIRLCUM)) {
			viewedVagina = new Vagina(vagina.getType(),
					vagina.getRawLabiaSizeValue(),
					vagina.getClitoris().getRawClitorisSizeValue(),
					vagina.getClitoris().getRawGirthValue(),
					Wetness.SEVEN_DROOLING.getValue(),
					vagina.getOrificeVagina().getRawCapacityValue() *3,
					vagina.getOrificeVagina().getDepth(null).getValue(),
					vagina.getOrificeVagina().getElasticity().getValue(),
					vagina.getOrificeVagina().getPlasticity().getValue(),
					vagina.getOrificeVagina().isVirgin());
			viewedVagina.setPierced(owner, vagina.isPierced());
			descriptionSB.append("<i style='color:"+PresetColour.PSYCHOACTIVE.toWebHexString()+";'>你最近摄入的致幻爱液导致你看到"+(owner.isPlayer()?"你的":"[npc.namePos]的")+"小穴发生了扭曲！</i>");
			hallucinating = true;
		}

		switch(owner.getGenitalArrangement()) {
			case CLOACA:
				descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her]的阴道位于其细缝状的前向泄殖腔处。)] "));
				break;
			case CLOACA_BEHIND:
				descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her][npc.Her]的阴道位于其细缝状的后向泄殖腔处。)] "));
				break;
			case NORMAL:
				break;
		}
		
		if(owner.hasPenis()) {
			descriptionSB.append("位于[npc.her]的[npc.penis]下方，");
		} else if(owner.hasLegs()) {
			descriptionSB.append("位于[npc.her]的[npc.legs]间，");
		} else {
			descriptionSB.append("位于[npc.her]的腹股沟处，");
		}
		
		descriptionSB.append(viewedVagina.getType().getBodyDescription(owner));
		
		if(owner.isImpregnationPhysicallyPossible()) {
			if(owner.isVaginaEggLayer()) {
				descriptionSB.append("由于其生殖器官的配置，[npc.she][style.colourEgg(通过育卵繁育后代，而非分娩)]。");
			} else {
				descriptionSB.append("由于其生殖器官的配置，[npc.she][style.colourSex(通过分娩繁育后代)]。");
			}
		}
		
		if(owner.isFeral()) {
			descriptionSB.append("[style.colourFeral(不出所料，[npc.her]的[npc.pussy]已经完全兽态化，与普通的[npc.vaginaRace]并无二致。)]");
		} else if(vagina.isFeral(owner)) {
			descriptionSB.append("[style.colourFeral([npc.her]的[npc.pussy]位于其兽态的下半身，已经与兽态的[npc.vaginaRace]并无二致。)]");
		}
		
		// Clit:
		descriptionSB.append("<br/>[npc.She]拥有[npc.a_clitSize]"+(owner.getClitorisGirth()==PenetrationGirth.THREE_AVERAGE?"":"，[npc.clitGirth]")
				+"的阴蒂，长度为[npc.clitSizeValue]，直径为[npc.clitDiameter(true)](周长为[npc.clitCircumference(true)])。");
		if(owner.isClitorisPseudoPenis()) {
			descriptionSB.append("[style.colourSex(其可观的尺寸使得[npc.herHim]能够将其当作假屌使用)]。");
		}
		
		for(PenetrationModifier pm : PenetrationModifier.getPenetrationModifiers()) {
			if(owner.hasClitorisModifier(pm)) {
				switch(pm) {
					case RIBBED:
						descriptionSB.append("整根都长满了稍硬的肉质突起，绝对能为任何被插入的腔穴带来额外的快感。");
						break;
					case TENTACLED:
						descriptionSB.append("一片细小的触手覆盖在表面，自行蜿蜒扭动着。");
						break;
					case BARBED:
						descriptionSB.append("肉质的倒刺布满其表面。");
						break;
					case BLUNT:
						descriptionSB.append("顶端形成了光滑的弯曲表面。");
						break;
					case FLARED:
						descriptionSB.append("顶部又平又宽，像马屌一样。");
						break;
					case KNOTTED:
						descriptionSB.append("根部有一个膨大的结。");
						break;
					case PREHENSILE:
						descriptionSB.append("极其灵活，能够像灵长目的尾巴一样操纵。");
						break;
					case SHEATHED:
						descriptionSB.append("阴蒂包皮转化为了巨大的鞘，无论[npc.clit]有多大，都可以收回其中。");
						break;
					case TAPERED:
						descriptionSB.append("肉竿从根部开始逐渐变细，直到顶端。");
						break;
					case VEINY:
						descriptionSB.append("表面布满了明显的静脉脉络。");
						break;
					case OVIPOSITOR:
						descriptionSB.append("内部结构使得其能够作产卵器，允许[npc.name]将[npc.her]已受精的卵产在目标的腔穴内。");
						break;
				}
			}
		}
		
		
		// Virgin/capacity:
		descriptionSB.append("<br/>");
		if(viewedVagina.getOrificeVagina().isVirgin()) {
			if(isPlayer || !hallucinating) {
				if(viewedVagina.getOrificeVagina().hasHymen()) {
					descriptionSB.append("[npc.her]" + Capacity.getCapacityFromValue(viewedVagina.getOrificeVagina().getStretchedCapacity()).getDescriptor(true)
							+ "的[npc.pussy]内，[style.colourMinorGood(处女膜仍旧完好)]，[style.colourExcellent([npc.she]还保留着阴道贞操)]。");
					
				} else {
					if(owner.isDoll()) {
						descriptionSB.append("作为性爱玩偶，[npc.name]没有处女膜，[style.colourExcellent(还保留着阴道贞操)]。");
						
					} else {
						descriptionSB.append("[npc.her]" + Capacity.getCapacityFromValue(viewedVagina.getOrificeVagina().getStretchedCapacity()).getDescriptor(true)
								+ "的[npc.pussy]内，尽管[style.colourMinorBad(处女膜已经撕裂)]，但[style.colourExcellent([npc.she]还保留着阴道贞操)]。");
					}
				}
			}
			
		} else {
			boolean virginityLossFound = false;
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				if(pt.isTakesVirginity()) {
					if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pt))!=null) {
						descriptionSB.append("[style.colourArcane("+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pt)) + ")]");
						virginityLossFound = true;
						break;
					}
				}
			}
			if(!virginityLossFound) {
				descriptionSB.append("[style.colourArcane([npc.Name]已经失去了贞操。)]");
			}
			if(viewedVagina.getOrificeVagina().hasHymen()) {
				descriptionSB.append(" 尽管[npc.sheIsFull]不再是处女，[npc.her]的阴道内[style.colourMinorGood(仍拥有完好的处女膜)]。");
			} else {
				if(owner.isDoll()) {
					descriptionSB.append("作为性爱玩偶，[npc.name]没有处女膜。");
					
				} else {
					descriptionSB.append("不出所料，作为一个不再是处女的人，[style.colourMinorBad([npc.her]的处女膜已经撕裂了)]。");
				}
			}
		}
		
		if (isPlayer || !hallucinating) {
			descriptionSB.append("[npc.Her]的小穴" + Capacity.getCapacityFromValue(viewedVagina.getOrificeVagina().getStretchedCapacity()).getDescriptor(true)+"，润滑后可以舒适地容纳最大"
					+ "直径为[style.colourSex("+ Units.size(Capacity.getMaximumComfortableDiameter(viewedVagina.orificeVagina.getElasticity(), viewedVagina.getOrificeVagina().getRawCapacityValue(), true)) + ")]的物体。");
		}

		if(Main.game.isPenetrationLimitationsEnabled()) {
			switch(owner.getVaginaDepth()) {
				default:
					descriptionSB.append("[npc.Her]的小穴<span style='color:"+owner.getVaginaDepth().getColour().toWebHexString()+";'>[npc.pussyDepth]</span>，");
					break;
				case TWO_AVERAGE:
					descriptionSB.append("[npc.Her]的小穴<span style='color:"+owner.getVaginaDepth().getColour().toWebHexString()+";'>深度适中</span>，");
					break;
			}
			if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，将其能够[style.colourMinorGood(舒适地)]容纳的物体最大长度增加至"
							+ "[style.colourSex("+Units.size(owner.getVaginaMaximumPenetrationDepthUncomfortable())+")]。");
				} else {
					descriptionSB.append("让[npc.herHim]能[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getVaginaMaximumPenetrationDepthComfortable())+")]长的物体插入，"
							+ "以及[style.colourMinorBad(不舒服地)]容纳[style.colourSex("+Units.size(owner.getVaginaMaximumPenetrationDepthUncomfortable())+")]长的物体插入。");
				}
				
			} else {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，加之[npc.her]的身体由[npc.bodyMaterial]构成，"
							+ "[npc.she]能够[style.colourMinorGood(舒适地)]容纳[style.colourSex(任何长度)]的物体。");
				} else {
					descriptionSB.append("让[npc.herHim]能[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getVaginaMaximumPenetrationDepthComfortable())+")]长的物体插入，"
							+ "且由于[npc.her]的身体[npc.bodyMaterial]构成，[npc.she]能够[style.colourMinorBad(不舒服地)]容纳[style.colourSex(任何长度)]的物体。");
				}
			}
		}
		
		// Wetness:
		switch (viewedVagina.getOrificeVagina().getWetness(owner)) {
			case ZERO_DRY:
				descriptionSB.append("目前[style.colourWetness(极其干燥，且永远不会湿润)]，无论[npc.she]有多兴奋。");
				break;
			case ONE_SLIGHTLY_MOIST:
				descriptionSB.append("目前[style.colourWetness(略微潮湿)]，还需要大量的刺激才能变得湿润。");
				break;
			case TWO_MOIST:
				descriptionSB.append("目前[style.colourWetness(比较潮湿)]，但还需要不少刺激才能变得湿润。");
				break;
			case THREE_WET:
				descriptionSB.append("目前[style.colourWetness(湿润度适中)]，只需要少量前戏就能够变得足够湿润，进行一场欢愉的性交了。");
				break;
			case FOUR_SLIMY:
				descriptionSB.append("表面造势[style.colourWetness(又潮湿又粘滑)]，已经随时为插入做好了准备。");
				break;
			case FIVE_SLOPPY:
				descriptionSB.append("表面总是覆盖着[style.colourWetness(粘滑的湿气)]，而在其小穴内部，则已经[style.colourWetness(溢满了淫液)]，渴望着挨操。");
				break;
			case SIX_SOPPING_WET:
				descriptionSB.append("[npc.Her]的小穴永远[style.colourWetness(十分湿滑)]，一滴滴天然润滑液不停地从秘缝中流出。");
				break;
			case SEVEN_DROOLING:
				descriptionSB.append("[npc.Her]的小穴[style.colourWetness(已经湿到每走一步都会发出滋滋的水声)]，不断地有蜜汁从诱人的穴中流淌而出。");
				break;
		}
		
		if(viewedVagina.getOrificeVagina().isSquirter()) {
			descriptionSB.append("[npc.She]会[style.colourArcane(潮吹)]，每次高潮时[style.colourWetness(都会产生大量的溅出物)]。");
		}
		
		// Elasticity & plasticity:
		switch (viewedVagina.getOrificeVagina().getElasticity()) {
			case ZERO_UNYIELDING:
				descriptionSB.append("[style.colourElasticity(几乎不会扩张)]，");
				break;
			case ONE_RIGID:
				descriptionSB.append("[style.colourElasticity(想要被撑开需要费九牛二虎之力)]，");
				break;
			case TWO_FIRM:
				descriptionSB.append("[style.colourElasticity(不太容易撑开)]，");
				break;
			case THREE_FLEXIBLE:
				descriptionSB.append("被插入时[style.colourElasticity(撑不太开)]，");
				break;
			case FOUR_LIMBER:
				descriptionSB.append("[style.colourElasticity(被扩张时有些抵抗)]，");
				break;
			case FIVE_STRETCHY:
				descriptionSB.append("[style.colourElasticity(可以比较简单地撑开)]，");
				break;
			case SIX_SUPPLE:
				descriptionSB.append("[style.colourElasticity(可以相当轻松地撑开)]，");
				break;
			case SEVEN_ELASTIC:
				descriptionSB.append("[style.colourElasticity(弹性极佳)]，");
				break;
		}
		descriptionSB.append("在被使用之后，会"+viewedVagina.getOrificeVagina().getPlasticity().getDescription()+"。");
		
		for(OrificeModifier om : OrificeModifier.values()) {
			if(owner.hasVaginaOrificeModifier(om)) {
				switch(om) {
					case MUSCLE_CONTROL:
						descriptionSB.append("[npc.She]的[npc.vagina]拥有一片活动自如的肌肉，使得[npc.herHim]能够精妙地抓住、挤压任何入侵的物体。");
						break;
					case PUFFY:
						descriptionSB.append("[npc.Her]的阴唇略微膨起，变得又大又软。");
						break;
					case RIBBED:
						descriptionSB.append("[npc.her][npc.vagina]的内部长满了敏感的肉质突起，被刺激时可以给[npc.herHim]提供额外的快感。");
						break;
					case TENTACLED:
						descriptionSB.append("[npc.Her]的[npc.vagina]长满了满了细小的触手，自行蜿蜒扭动着。");
						break;
				}
			}
		}
		
		
		// Girlcum:
		
		descriptionSB.append("[npc.Her]的[npc.girlcumColour(true)][npc.girlcum]");
		
		switch(viewedVagina.getGirlcum().getFlavour()) {
			case GIRL_CUM:
				descriptionSB.append("，不出所料，尝起来就像普通的爱液。");
				break;
			case BUBBLEGUM:
				descriptionSB.append("尝起来像水果口味的泡泡糖。");
				break;
			case FLAVOURLESS:
				descriptionSB.append("没有任何味道。");
				break;
			default:
				descriptionSB.append("尝起来跟"+viewedVagina.getGirlcum().getFlavour().getName()+"一模一样。");
				break;
		}
		
		for(FluidModifier fm : FluidModifier.values()) {
			if(viewedVagina.getGirlcum().getFluidModifiers().contains(fm)) {
				descriptionSB.append(fm.getBriefDescription());
			}
		}
		
		descriptionSB.append("<br/>");
		
		// Urethra:
		if (Capacity.getCapacityFromValue(viewedVagina.getOrificeUrethra().getStretchedCapacity()) != Capacity.ZERO_IMPENETRABLE) {
			descriptionSB.append("[npc.Her]的阴部尿道已经足够松弛，目前适合插入，润滑后可以舒适地容纳最大"
					+ "直径为[style.colourSex("+ Units.size(Capacity.getMaximumComfortableDiameter(viewedVagina.getOrificeUrethra().getElasticity(), viewedVagina.getOrificeUrethra().getRawCapacityValue(), true)) + ")]的物体。");
			
			if(Main.game.isPenetrationLimitationsEnabled()) {
				switch(owner.getVaginaUrethraDepth()) {
					default:
						descriptionSB.append("[npc.Her]足以插入的尿道<span style='color:"+owner.getVaginaUrethraDepth().getColour().toWebHexString()+";'>[npc.pussyUrethraDepth]</span>，");
						break;
					case TWO_AVERAGE:
						descriptionSB.append("[npc.Her]足以插入的尿道<span style='color:"+owner.getVaginaUrethraDepth().getColour().toWebHexString()+";'>深度适中</span>，");
						break;
				}
				if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
					if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						descriptionSB.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，将其能够[style.colourMinorGood(舒适地)]容纳的物体最大长度增加至"
								+ "[style.colourSex("+Units.size(owner.getVaginaUrethraMaximumPenetrationDepthUncomfortable())+")]。");
					} else {
						descriptionSB.append("让[npc.herHim]能[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getVaginaUrethraMaximumPenetrationDepthComfortable())+")]长的物体插入，"
								+ "以及[style.colourMinorBad(不舒服地)]容纳[style.colourSex("+Units.size(owner.getVaginaUrethraMaximumPenetrationDepthUncomfortable())+")]长的物体插入。");
					}
					
				} else {
					if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						descriptionSB.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，加之[npc.her]的身体由[npc.bodyMaterial]构成，"
								+ "[npc.she]能够[style.colourMinorGood(舒适地)]容纳[style.colourSex(任何长度)]的物体。");
					} else {
						descriptionSB.append("让[npc.herHim]能[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getVaginaUrethraMaximumPenetrationDepthComfortable())+")]长的物体插入，"
								+ "且由于[npc.her]的身体[npc.bodyMaterial]构成，[npc.she]能够[style.colourMinorBad(不舒服地)]容纳[style.colourSex(任何长度)]的物体。");
					}
				}
			}
			
			// Elasticity & plasticity:
			switch (viewedVagina.getOrificeUrethra().getElasticity()) {
				case ZERO_UNYIELDING:
					descriptionSB.append("[style.colourElasticity(几乎不会扩张)]，");
					break;
				case ONE_RIGID:
					descriptionSB.append("[style.colourElasticity(想要被撑开需要费九牛二虎之力)]，");
					break;
				case TWO_FIRM:
					descriptionSB.append("[style.colourElasticity(不太容易撑开)]，");
					break;
				case THREE_FLEXIBLE:
					descriptionSB.append("被插入时[style.colourElasticity(撑不太开)]，");
					break;
				case FOUR_LIMBER:
					descriptionSB.append("[style.colourElasticity(被扩张时有些抵抗)]，");
					break;
				case FIVE_STRETCHY:
					descriptionSB.append("[style.colourElasticity(可以比较简单地撑开)]，");
					break;
				case SIX_SUPPLE:
					descriptionSB.append("[style.colourElasticity(可以相当轻松地撑开)]，");
					break;
				case SEVEN_ELASTIC:
					descriptionSB.append("[style.colourElasticity(弹性极佳)]，");
					break;
			}
			descriptionSB.append("在被使用之后，会"+viewedVagina.getOrificeUrethra().getPlasticity().getDescription()+"。");
			
			for(OrificeModifier om : OrificeModifier.values()) {
				if(owner.hasVaginaUrethraOrificeModifier(om)) {
					switch(om) {
						case PUFFY:
							descriptionSB.append("[npc.Her]的尿道边缘略微膨起，变得更加有肉感了。");
							break;
						case MUSCLE_CONTROL:
							descriptionSB.append("[npc.She]的尿道拥有一片活动自如的肌肉，使得[npc.herHim]能够精妙地抓住、挤压任何入侵的物体。");
							break;
						case RIBBED:
							descriptionSB.append("[npc.her]的尿道内部长满了敏感的肉质突起，被刺激时可以给[npc.herHim]提供额外的快感。");
							break;
						case TENTACLED:
							descriptionSB.append("[npc.Her]的尿道长满了满了细小的触手，自行蜿蜒扭动着。");
							break;
					}
				}
			}
		}
		
		// Pubic Hair:
		if(Main.game.isPubicHairEnabled()) {
			if(owner.getPubicHairType().getType()==BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR) {
				switch(owner.getPubicHair()) {
					case ZERO_NONE:
						descriptionSB.append("[npc.her]的[npc.pussy]周围没有硬质"+owner.getPubicHairType().getName(owner)+"的踪迹。");
						break;
					case ONE_STUBBLE:
						descriptionSB.append("[npc.her]的[npc.pussy]周围长着一小片硬质的"+owner.getPubicHairType().getName(owner)+"。");
						break;
					case TWO_MANICURED:
						descriptionSB.append("[npc.her]的[npc.pussy]周围长着一片硬质的"+owner.getPubicHairType().getName(owner)+"。");
						break;
					case THREE_TRIMMED:
						descriptionSB.append("[npc.her]的[npc.pussy]周围长着一片硬质的"+owner.getPubicHairType().getName(owner)+"。");
						break;
					case FOUR_NATURAL:
						descriptionSB.append("[npc.her]的[npc.pussy]周围自然生长着一片硬质的"+owner.getPubicHairType().getName(owner)+"。");
						break;
					case FIVE_UNKEMPT:
						descriptionSB.append("[npc.her]的[npc.pussy]周围凌乱地生长着一大片硬质的"+owner.getPubicHairType().getName(owner)+"。");
						break;
					case SIX_BUSHY:
						descriptionSB.append("[npc.her]的[npc.pussy]周围浓密地生长着一大片硬质的"+owner.getPubicHairType().getName(owner)+"。");
						break;
					case SEVEN_WILD:
						descriptionSB.append("[npc.her]的[npc.pussy]周围野蛮地生长着一大片硬质的"+owner.getPubicHairType().getName(owner)+"。");
						break;
				}
			} else {
				switch(owner.getPubicHair()) {
					case ZERO_NONE:
						descriptionSB.append("[npc.her]的[npc.pussy]周围没有"+owner.getPubicHairType().getName(owner)+"的踪迹。");
						break;
					case ONE_STUBBLE:
						descriptionSB.append("[npc.her]的[npc.pussy]周围长着一茬"+owner.getPubicHairType().getName(owner)+"。");
						break;
					case TWO_MANICURED:
						descriptionSB.append("[npc.her]的[npc.pussy]周围长着一小片整齐的"+owner.getPubicHairType().getName(owner)+"。");
						break;
					case THREE_TRIMMED:
						descriptionSB.append("[npc.her]的[npc.pussy]周围长着一小片修剪过的"+owner.getPubicHairType().getName(owner)+"。");
						break;
					case FOUR_NATURAL:
						descriptionSB.append("[npc.her]的[npc.pussy]周围自然地生长着一片"+owner.getPubicHairType().getName(owner)+"。");
						break;
					case FIVE_UNKEMPT:
						descriptionSB.append("[npc.her]的[npc.pussy]周围凌乱地生长着一片"+owner.getPubicHairType().getName(owner)+"。");
						break;
					case SIX_BUSHY:
						descriptionSB.append("[npc.her]的[npc.pussy]周围浓密地生长着一大片"+owner.getPubicHairType().getName(owner)+"。");
						break;
					case SEVEN_WILD:
						descriptionSB.append("[npc.her]的[npc.pussy]周围野蛮地生长着一大片"+owner.getPubicHairType().getName(owner)+"。");
						break;
				}
			}
		}
		
		
		if (isPlayer && !owner.isVaginaUrethraVirgin()) {
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, pt))!=null) {
					descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, pt))+ "</span>");
					break;
				}
			}
		}
		
		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getMoundDescription(GameCharacter owner) {
		return UtilText.parse(owner,
				"由于[npc.she]没有外生殖器，于是只有一片无法辨识性别的光秃秃表面。"
				+ "尽管[npc.she]缺乏性器官，这里仍是十分敏感的区域，经过刺激后仍可以到达假性高潮。");
	}
	
	public String getSpinneretDescription(GameCharacter owner) {
		if(!owner.hasSpinneret()) {
			return "";
		}
		
		descriptionSB = new StringBuilder();
		
		if(owner.hasLegSpinneret()) {
			descriptionSB.append("在[npc.her][npc.legRace]腹部的后端，");
		} else {
			descriptionSB.append("在[npc.her][npc.tail+]的后端，");
		}
		descriptionSB.append("拥有一个[npc.spinneretFullDescription(true)]。不仅可以吐出结实的粘性网，还能用来当作性交腔穴。");
		
		// Virgin/capacity:
		if(spinneret.isVirgin()) {
			descriptionSB.append("[npc.She][style.colourExcellent(还保留着丝囊贞操)]。");
			
		} else {
			boolean virginityLossFound = false;
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				if(pt.isTakesVirginity()) {
					if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.SPINNERET, pt))!=null) {
						descriptionSB.append("[style.colourArcane("+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.SPINNERET, pt)) + ")]");
						virginityLossFound = true;
						break;
					}
				}
			}
			if(!virginityLossFound) {
				descriptionSB.append("[style.colourArcane([npc.Name]已经失去了丝囊贞操。)]");
			}
		}
		
		descriptionSB.append("[npc.Her]的丝囊" + Capacity.getCapacityFromValue(spinneret.getStretchedCapacity()).getDescriptor(true)+"，润滑后可以舒适地容纳最大"
				+ "直径为[style.colourSex("+ Units.size(Capacity.getMaximumComfortableDiameter(spinneret.getElasticity(), spinneret.getRawCapacityValue(), true)) + ")]的物体。");

		if(Main.game.isPenetrationLimitationsEnabled()) {
			switch(owner.getSpinneretDepth()) {
				default:
					descriptionSB.append("其<span style='color:"+owner.getSpinneretDepth().getColour().toWebHexString()+";'>[npc.spinneretDepth]</span>，");
					break;
				case TWO_AVERAGE:
					descriptionSB.append("其<span style='color:"+owner.getSpinneretDepth().getColour().toWebHexString()+";'>深度适中</span>，");
					break;
			}
			if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，将其能够[style.colourMinorGood(舒适地)]容纳的物体最大长度增加至"
							+ "[style.colourSex("+Units.size(owner.getSpinneretMaximumPenetrationDepthUncomfortable())+")]。");
				} else {
					descriptionSB.append("让[npc.herHim]能[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getSpinneretMaximumPenetrationDepthComfortable())+")]长的物体插入，"
							+ "以及[style.colourMinorBad(不舒服地)]容纳[style.colourSex("+Units.size(owner.getSpinneretMaximumPenetrationDepthUncomfortable())+")]长的物体插入。");
				}
				
			} else {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append("且由于[npc.sheIsFull]认为"+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"，加之[npc.her]的身体由[npc.bodyMaterial]构成，"
							+ "[npc.she]能够[style.colourMinorGood(舒适地)]容纳[style.colourSex(任何长度)]的物体。");
				} else {
					descriptionSB.append("让[npc.herHim]能[style.colourMinorGood(舒适地)]容纳[style.colourSex("+Units.size(owner.getSpinneretMaximumPenetrationDepthComfortable())+")]长的物体插入，"
							+ "且由于[npc.her]的身体[npc.bodyMaterial]构成，[npc.she]能够[style.colourMinorBad(不舒服地)]容纳[style.colourSex(任何长度)]的物体。");
				}
			}
		}
		
		// Wetness:
		switch (spinneret.getWetness(owner)) {
			case ZERO_DRY:
				descriptionSB.append("目前[style.colourWetness(极其干燥，且永远不会湿润)]，无论[npc.she]有多兴奋。");
				break;
			case ONE_SLIGHTLY_MOIST:
				descriptionSB.append("目前[style.colourWetness(略微潮湿)]，还需要大量的刺激才能变得湿润。");
				break;
			case TWO_MOIST:
				descriptionSB.append("目前[style.colourWetness(比较潮湿)]，但还需要不少刺激才能变得湿润。");
				break;
			case THREE_WET:
				descriptionSB.append("目前[style.colourWetness(湿润度适中)]，只需要少量前戏就能够变得足够湿润，进行一场欢愉的性交了。");
				break;
			case FOUR_SLIMY:
				descriptionSB.append("表面造势[style.colourWetness(又潮湿又粘滑)]，已经随时为插入做好了准备。");
				break;
			case FIVE_SLOPPY:
				descriptionSB.append("表面总是覆盖着[style.colourWetness(粘滑的湿气)]，而在其丝囊内部，则已经[style.colourWetness(溢满了淫液)]，渴望着挨操。");
				break;
			case SIX_SOPPING_WET:
				descriptionSB.append("[npc.Her]的丝囊永远[style.colourWetness(十分湿滑)]，一滴滴天然润滑液不停地从其中流出。");
				break;
			case SEVEN_DROOLING:
				descriptionSB.append("[npc.Her]的丝囊[style.colourWetness(已经湿到每走一步都会发出滋滋的水声)]，不断地有蜜汁从诱人的穴中流淌而出。");
				break;
		}
		
		// Elasticity & plasticity:
		switch (spinneret.getElasticity()) {
			case ZERO_UNYIELDING:
				descriptionSB.append("[style.colourElasticity(几乎不会扩张)]，");
				break;
			case ONE_RIGID:
				descriptionSB.append("[style.colourElasticity(想要被撑开需要费九牛二虎之力)]，");
				break;
			case TWO_FIRM:
				descriptionSB.append("[style.colourElasticity(不太容易撑开)]，");
				break;
			case THREE_FLEXIBLE:
				descriptionSB.append("被插入时[style.colourElasticity(撑不太开)]，");
				break;
			case FOUR_LIMBER:
				descriptionSB.append("[style.colourElasticity(被扩张时有些抵抗)]，");
				break;
			case FIVE_STRETCHY:
				descriptionSB.append("[style.colourElasticity(可以比较简单地撑开)]，");
				break;
			case SIX_SUPPLE:
				descriptionSB.append("[style.colourElasticity(可以相当轻松地撑开)]，");
				break;
			case SEVEN_ELASTIC:
				descriptionSB.append("[style.colourElasticity(弹性极佳)]，");
				break;
		}
		descriptionSB.append("在被使用之后，会"+spinneret.getPlasticity().getDescription()+"。");
		
		for(OrificeModifier om : OrificeModifier.values()) {
			if(owner.hasSpinneretOrificeModifier(om)) {
				switch(om) {
					case MUSCLE_CONTROL:
						descriptionSB.append("[npc.She]的丝囊拥有一片活动自如的肌肉，使得[npc.herHim]能够精妙地抓住、挤压任何入侵的物体。");
						break;
					case PUFFY:
						descriptionSB.append("边缘略微膨起，变得又大又软。");
						break;
					case RIBBED:
						descriptionSB.append("[npc.her]丝囊的内部长满了敏感的肉质突起，被刺激时可以给[npc.herHim]提供额外的快感。");
						break;
					case TENTACLED:
						descriptionSB.append("[npc.Her]的丝囊长满了满了细小的触手，自行蜿蜒扭动着。");
						break;
				}
			}
		}
		
		return UtilText.parse(owner, descriptionSB.toString());
	}
	
	private String getSexDetails(GameCharacter owner) {
		
		if(owner.getTotalTimesHadSex(Main.game.getPlayer()) >=1) {
			descriptionSB = new StringBuilder();

			descriptionSB.append(getHeader("性爱经历"));
			
			// Amount of sex:
			
			descriptionSB.append(
					UtilText.parse(owner,
							"<span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>"
							+ "你已经和[npc.name]做过了"+Util.intToString(owner.getTotalTimesHadSex(Main.game.getPlayer()))+"次。"
						+"</span>"));
			
			if(owner.getSexConsensualCount(Main.game.getPlayer())>=1) {
				if(owner.getSexConsensualCount(Main.game.getPlayer()) == owner.getTotalTimesHadSex(Main.game.getPlayer())) {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner,"你跟[npc.herHim]做的一次是两厢情愿。"));
					} else {
						descriptionSB.append(UtilText.parse(owner,"所有"+Util.intToString(owner.getTotalTimesHadSex(Main.game.getPlayer()))+"次都是两厢情愿。"));
					}
					
				} else {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner,"你跟[npc.herHim]做的一次是两厢情愿。"));
					} else {
						descriptionSB.append(UtilText.parse(owner,"其中"+Util.capitaliseSentence(Util.intToString(owner.getSexConsensualCount(Main.game.getPlayer())))+"次是两厢情愿。"));
					}
				}
			}
			if(owner.getSexAsSubCount(Main.game.getPlayer())>=1) {
				if(owner.getSexAsSubCount(Main.game.getPlayer()) == owner.getTotalTimesHadSex(Main.game.getPlayer())) {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner,"你跟[npc.herHim]做的一次，你是支配方。"));
					} else {
						descriptionSB.append(UtilText.parse(owner,"所有"+Util.intToString(owner.getTotalTimesHadSex(Main.game.getPlayer()))+"次你都是支配方。"));
					}
					
				} else {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner,"你跟[npc.herHim]做的一次，你是支配方。"));
					} else {
						descriptionSB.append(UtilText.parse(owner,"其中"+Util.capitaliseSentence(Util.intToString(owner.getSexAsSubCount(Main.game.getPlayer())))+"次你是支配方。"));
					}
				}
			}
			if(owner.getSexAsDomCount(Main.game.getPlayer())>=1) {
				if(owner.getSexAsDomCount(Main.game.getPlayer()) == owner.getTotalTimesHadSex(Main.game.getPlayer())) {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner,"你跟[npc.herHim]做的一次，你是服从方。"));
					} else {
						descriptionSB.append(UtilText.parse(owner,"所有"+Util.intToString(owner.getTotalTimesHadSex(Main.game.getPlayer()))+"次你都是服从方。"));
					}
					
				} else {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner,"你跟[npc.herHim]做的一次，你是服从方。"));
					} else {
						descriptionSB.append(UtilText.parse(owner,"其中"+Util.capitaliseSentence(Util.intToString(owner.getSexAsDomCount(Main.game.getPlayer())))+"次你是服从方。"));
					}
				}
			}
			descriptionSB.append("</p>");

			return UtilText.parse(owner, descriptionSB.toString());
		
		} else {
			return "";
		}
	}
	
	private String getPregnancyDetails(GameCharacter owner) {
		descriptionSB = new StringBuilder();
		
		boolean sectionAdded = false;
		// NPC is mother:
		
		if(owner.isVisiblyPregnant()) {
			descriptionSB.append(getHeader("怀孕"));
			GameCharacter father = owner.getPregnantLitter().getFather();
			if(father == null) {
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>某次性行为后，[npc.name]怀孕了。</span>");
			} else if(father.isPlayer()) {
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>某次性行为后，你终于让[npc.name]受孕了。</span>");
			} else {
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>某次性行为后，"+father.getName(true)+"终于让[npc.name]受孕了。</span>");
			}
			
			if(owner.hasStatusEffect(StatusEffect.PREGNANT_1)) {
				descriptionSB.append("[npc.Her]的肚子微微隆起，正处于怀孕的最初阶段。");
			} else if(owner.hasStatusEffect(StatusEffect.PREGNANT_2)) {
				descriptionSB.append("[npc.Her]的肚子肉眼可见地隆起，孕期非常顺利。");
			} else {
				descriptionSB.append("[npc.Her]的肚子高高隆起，尽管已经做好了准备，但[npc.sheIs]还没决定是否分娩。");
			}
			descriptionSB.append("</p>");
			sectionAdded = true;
		}
		
		if(!owner.getLittersBirthed().isEmpty()) {
			if(!sectionAdded) {
				descriptionSB.append(getHeader("怀孕"));
			} else {
				descriptionSB.append("<p>");
			}
			descriptionSB.append(
					"<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"
						+ "[npc.Name]已经分娩过"+Util.intToString(owner.getLittersBirthed().size())+"次。</span>");
			
			for(Litter litter : owner.getLittersBirthed()) {
				if(litter.getFather() == null) {
					descriptionSB.append("<br/>"+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
							+"，[npc.she]受孕了，随后"+Units.date(litter.getBirthDate(), Units.DateType.LONG)+"，[npc.she]分娩出了");
					
				} else if(litter.getFather().isPlayer()) {
					descriptionSB.append("<br/>"+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
							+"，你让[npc.herHim]受孕了，随后"+Units.date(litter.getBirthDate(), Units.DateType.LONG)+"，[npc.she]分娩出了");
					
				} else {
					descriptionSB.append("<br/>"+Units.date(litter.getConceptionDate(), Units.DateType.LONG)+"，"+litter.getFather().getName(true)
							+"让[npc.herHim]受孕了，随后"+Units.date(litter.getBirthDate(), Units.DateType.LONG)+"，[npc.she]分娩出了");
				}
				
				descriptionSB.append(litter.getBirthedDescription());
				
				descriptionSB.append("。");
			}
			
			descriptionSB.append("</p>");
			sectionAdded = true;
		}
		
		// NPC is father:
		
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			for(PregnancyPossibility pp : Main.game.getPlayer().getPotentialPartnersAsMother()) {
				if(pp.getFather()!=null && pp.getFather().equals(owner)) {
					if(!sectionAdded) {
						descriptionSB.append(getHeader("怀孕"));
					} else {
						descriptionSB.append("<p>");
					}
					descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>某次性行为后，你受孕了，[npc.name]有可能是孩子的父亲。</span>");
					descriptionSB.append("</p>");
					sectionAdded = true;
					break;
				}
			}
		}
		
		if(!Main.game.getPlayer().getLittersBirthed().isEmpty()) {
			int fatheredLitters = 0;
			
			for(Litter litter : Main.game.getPlayer().getLittersBirthed()) {
				if(litter.getFather()!=null && litter.getFather().equals(owner)){
					fatheredLitters++;
				}
			}
			
			if(fatheredLitters!=0) {
				if(!sectionAdded) {
					descriptionSB.append(getHeader("怀孕"));
				} else {
					descriptionSB.append("<p>");
				}
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"
							+ "[npc.Name]是你某个孩子的父亲，并且总共让你受孕了"+Util.intToString(fatheredLitters)+" 次。</span>");
				
				for(Litter litter : Main.game.getPlayer().getLittersBirthed()) {
					if(litter.getFather()!=null && litter.getFather().equals(owner)){
						descriptionSB.append("<br/>"+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
								+"，[npc.she]让你受孕了，随后"+Units.date(litter.getBirthDate(), Units.DateType.LONG)+"，你分娩出了"+litter.getBirthedDescription()+"。");
					}
				}
				
				descriptionSB.append("</p>");
			}
		}

		return UtilText.parse(owner, descriptionSB.toString());
	}
	

	private String getIncubationPregnancyDetails(GameCharacter owner) {
		descriptionSB = new StringBuilder();
		boolean sectionAdded = false;
		
		if(!owner.getIncubatingLitters().isEmpty()) {
			sectionAdded = true;
			descriptionSB.append(getHeader("孵化"));
			int i=0;
			for(Entry<SexAreaOrifice, Litter> entry : owner.getIncubatingLitters().entrySet()) {
				if(i>0) {
					descriptionSB.append("<br/>");
				}
				String areaEgged = "";
				String stage = "";
				switch(entry.getKey()) {
					case ANUS:
					case MOUTH:
						areaEgged = "肚子";
						if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_1)) {
							stage = " [npc.Her]的肚子只是微微隆起，因为肚子里的卵只是刚刚被注入。";
						} else if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_2)) {
							stage = " [npc.Her]的肚子已经肉眼可见的隆起，因为肚子里的卵已经有些时间，开始生长成熟了。";
						} else {
							stage = " [npc.Her]的肚子高高隆起，不过[npc.sheIs]明显早有准备，还不准备将在肚中孵化的卵产下。";
						}
						break;
					case NIPPLE:
						areaEgged = "[npc.breasts]";
						if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_1)) {
							stage = " [npc.Her]的[npc.breasts]只是微微隆起，因为[npc.nipples]里的卵只是刚刚被注入。";
						} else if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_2)) {
							stage = " [npc.Her]的[npc.breasts]已经肉眼可见的隆起，因为[npc.nipples]里的卵已经有些时间，开始生长成熟了。";
						} else {
							stage = " [npc.Her]的[npc.breasts]高高隆起，不过[npc.sheIs]明显早有准备，还不准备将在[npc.nipples]中孵化的卵产下。";
						}
						break;
					case NIPPLE_CROTCH:
						areaEgged = "[npc.crotchBoobs]";
						if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_1)) {
							stage = " [npc.Her]的[npc.crotchBoobs]只是微微隆起，因为[npc.crotchNipples]里的卵只是刚刚被注入。";
						} else if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_2)) {
							stage = " [npc.Her]的[npc.crotchBoobs]已经肉眼可见的隆起，因为[npc.crotchNipples]里的卵已经有些时间，开始生长成熟了。";
						} else {
							stage = " [npc.Her]的[npc.crotchBoobs]高高隆起，不过[npc.sheIs]明显早有准备，还不准备将在[npc.crotchNipples]中孵化的卵产下。";
						}
						break;
					case SPINNERET:
						areaEgged = "[npc.spinneret]";
						String spinneretArea = owner.hasTailSpinneret()?"[npc.tail]":"abdomen";
						if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_1)) {
							stage = " [npc.Her]的"+spinneretArea+"只是微微隆起，因为丝囊里的卵只是刚刚被注入。";
						} else if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_2)) {
							stage = " [npc.Her]的"+spinneretArea+"已经肉眼可见的隆起，因为丝囊里的卵已经有些时间，开始生长成熟了。";
						} else {
							stage = " [npc.Her]的"+spinneretArea+"高高隆起，不过[npc.sheIs]明显早有准备，还不准备将在丝囊中孵化的卵产下。";
						}
						break;
					case VAGINA:
						areaEgged = "子宫";
						if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_1)) {
							stage = " [npc.Her]的肚子只是微微隆起，因为子宫里的卵只是刚刚被注入。";
						} else if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_2)) {
							stage = " [npc.Her]的肚子已经肉眼可见的隆起，因为子宫里的卵已经有些时间，开始生长成熟了。";
						} else {
							stage = " [npc.Her]的肚子高高隆起，不过[npc.sheIs]明显早有准备，还不准备将在子宫中孵化的卵产下。";
						}
						break;
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
						break;
				}
				GameCharacter mother = entry.getValue().getMother();
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>");
					if(mother == null) {
						descriptionSB.append("某次性行为后，[npc.name]在[npc.her]的"+areaEgged+"里填满了卵。");
					} else if(mother.isPlayer()) {
						descriptionSB.append("某次性行为后，你在[npc.namePos]的"+areaEgged+"里填满了卵。");
					} else {
						descriptionSB.append("某次性行为后，[npc.name]的"+areaEgged+"里填满了"+mother.getName(true)+"的卵。");
					}
				descriptionSB.append("</span>");
				descriptionSB.append(stage);
				i++;
			}
			descriptionSB.append("</p>");
		}
		
		if(!owner.getLittersIncubated().isEmpty()) {
			if(!sectionAdded) {
				descriptionSB.append(getHeader("孵化"));
			} else {
				descriptionSB.append("<p>");
			}
			sectionAdded = true;
				descriptionSB.append(
						"<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"
							+ "[npc.Name]已经孵化、产卵过"+Util.intToString(owner.getLittersIncubated().size())+"次。"
						+ "</span>");

				//Litter.getMother is the character who passed on the eggs to the incubator
				for(Litter litter : owner.getLittersIncubated()) {
					if(litter.getMother()==null) {
						descriptionSB.append("<br/>"+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
								+"，[npc.she]被注入"+litter.getTotalLitterCount()+"颗卵，随后"+Units.date(litter.getBirthDate(), Units.DateType.LONG)+"，[npc.she]将卵再次产出，诞下了");
						
					} else if(litter.getMother().isPlayer()) {
						descriptionSB.append("<br/>"+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
								+"，你在[npc.herHim]体内注入"+litter.getTotalLitterCount()+"颗卵，随后"+Units.date(litter.getBirthDate(), Units.DateType.LONG)+"，[npc.she]将卵再次产出，诞下了");
						
					} else {
						descriptionSB.append("<br/>"+Units.date(litter.getConceptionDate(), Units.DateType.LONG)+"，"+litter.getMother().getName(true)
								+"在[npc.herHim]体内注入"+litter.getTotalLitterCount()+"颗卵，随后"+Units.date(litter.getBirthDate(), Units.DateType.LONG)+"，[npc.she]将卵再次产出，诞下了");
					}
					
					descriptionSB.append(litter.getBirthedDescription());
					
					descriptionSB.append("。");
				}
			descriptionSB.append("</p>");
		}
		
		// NPC is egg implanter:
		
		if(!Main.game.getPlayer().getIncubatingLitters().isEmpty()) {
			List<String> areasEgged = new ArrayList<>();
			for(Entry<SexAreaOrifice, Litter> entry : Main.game.getPlayer().getIncubatingLitters().entrySet()) {
				if(entry.getValue().getMother()!=null && entry.getValue().getMother().equals(owner)) {
					String areaEgged = "";
					switch(entry.getKey()) {
						case ANUS:
						case MOUTH:
							areaEgged = "肚子";
							break;
						case NIPPLE:
							areaEgged = "[pc.nipples]";
							break;
						case NIPPLE_CROTCH:
							areaEgged = "[pc.crotchNipples]";
							break;
						case SPINNERET:
							areaEgged = "丝囊";
							break;
						case VAGINA:
							areaEgged = "子宫";
							break;
						case ARMPITS:
						case ASS:
						case BREAST:
						case BREAST_CROTCH:
						case THIGHS:
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
					}
					areasEgged.add(areaEgged);
				}
			}
			if(!areasEgged.isEmpty()) {
				if(!sectionAdded) {
					descriptionSB.append(getHeader("孵化"));
				} else {
					descriptionSB.append("<p>");
				}
				sectionAdded = true;
				descriptionSB.append(
						"<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"
							+ "是[npc.NameIsFull]将卵注入到了你的"+Util.stringsToStringList(areasEgged, false)+"内。"
						+ "</span>");
				descriptionSB.append("</p>");
			}
		}
		
		if(!Main.game.getPlayer().getLittersIncubated().isEmpty()) {
			int incubatedLitters = 0;
			
			for(Litter litter : Main.game.getPlayer().getLittersIncubated()) {
				if(litter.getMother()!=null && litter.getMother().equals(owner)) {
					incubatedLitters++;
				}
			}
			
			if(incubatedLitters!=0) {
				if(!sectionAdded) {
					descriptionSB.append(getHeader("孵化"));
				} else {
					descriptionSB.append("<p>");
				}
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"
							+ "[npc.Name]曾经将卵注入到了你的体内，[npc.sheHasFull]总共这样做过"+Util.intToString(incubatedLitters)+"次。</span>");
				
				for(Litter litter : Main.game.getPlayer().getLittersIncubated()) {
					if(litter.getMother()!=null && litter.getMother().equals(owner)) {
						descriptionSB.append("<br/>"+Units.date(litter.getIncubationStartDate(), Units.DateType.LONG)
								+"，[npc.she]在你的体内注入了一窝卵，随后"+Units.date(litter.getBirthDate(), Units.DateType.LONG)+"，你将卵再次产出，孵化出了"+litter.getBirthedDescription()+"。");
					}
				}
				
				descriptionSB.append("</p>");
			}
		}

		return UtilText.parse(owner, descriptionSB.toString());
	}

	
	/**
	 * Gender is calculated based on body femininity and sexual organs.
	 * 
	 * @return gender of this body
	 */
	public Gender getGender() {
		boolean hasPenis = penis.getType() != PenisType.NONE;
		boolean hasVagina = vagina.getType() != VaginaType.NONE;
		boolean hasBreasts = breast.hasBreasts();
		if(this.isFeral() && this.getSubspecies().getFeralAttributes(this)!=null) {
			hasBreasts = this.getSubspecies().getFeralAttributes(this).isBreastsPresent() || this.getBreastCrotch().hasBreasts();
		}
		
		// Looks male:
		if (femininity <= Femininity.MASCULINE.getMaximumFemininity()) {
			if(hasPenis) {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.M_P_V_B_HERMAPHRODITE;
					} else {
						return Gender.M_P_V_HERMAPHRODITE;
					}
				} else {
					if(hasBreasts) {
						return Gender.M_P_B_BUSTYBOY;
					} else {
						return Gender.M_P_MALE;
					}
				}
			} else {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.M_V_B_BUTCH;
					} else {
						return Gender.M_V_CUNTBOY;
					}
				} else {
					if(hasBreasts) {
						return Gender.M_B_MANNEQUIN;
					} else {
						return Gender.M_MANNEQUIN;
					}
				}
			}

		// Looks androgynous:
		} else if (femininity <= Femininity.ANDROGYNOUS.getMaximumFemininity()){
			if(hasPenis) {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.N_P_V_B_HERMAPHRODITE;
					} else {
						return Gender.N_P_V_HERMAPHRODITE;
					}
				} else {
					if(hasBreasts) {
						return Gender.N_P_B_SHEMALE;
					} else {
						return Gender.N_P_TRAP;
					}
				}
			} else {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.N_V_B_TOMBOY;
					} else {
						return Gender.N_V_TOMBOY;
					}
				} else {
					if(hasBreasts) {
						return Gender.N_B_DOLL;
					} else {
						return Gender.N_NEUTER;
					}
				}
			}
		
		// Looks feminine:
		} else {
			if(hasPenis) {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.F_P_V_B_FUTANARI;
					} else {
						return Gender.F_P_V_FUTANARI;
					}
				} else {
					if(hasBreasts) {
						return Gender.F_P_B_SHEMALE;
					} else {
						return Gender.F_P_TRAP;
					}
				}
			} else {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.F_V_B_FEMALE;
					} else {
						return Gender.F_V_FEMALE;
					}
				} else {
					if(hasBreasts) {
						return Gender.F_B_DOLL;
					} else {
						return Gender.F_DOLL;
					}
				}
			}
			
		}
	}

	/**
	 * @return weight in kilograms
	 */
	public int getCalculatedWeight() {
		//TODO?
		// Was this ever going to be used?
		// If so, it should take into account BodySize and Muscle

		// Weight = 0.4 * height
		int weight = (int) (height * 0.4f);

		// If harpy wings, your bones & muscles are really light, so *0.5
		if (arm.getType().equals(ArmType.HARPY)) {
			weight *= 0.5;
		}

		// If tauric lower body, weight is based on horse-sized animal, so weight*0.6 + 250 (horses are 400kg at lightest and centaur bodies are smaller than horse's)
		if (leg.getLegConfiguration()==LegConfiguration.QUADRUPEDAL
				|| leg.getLegConfiguration()==LegConfiguration.ARACHNID
				|| leg.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
			weight *= 0.6;
			weight += 250;
		}

		return weight;
	}
	
	/**
	 * @return true if this character's Height value is less than Height.ZERO_TINY. This means that a fairy-sized body will also return true for this method.
	 */
	public boolean isShortStature() {
		return this.getHeight().isShortStature();
	}
	
	public boolean isFairySized() {
		return this.getHeight().isFairySized();
	}
	
	/** Height is measured in cm. **/
	public int getHeightValue() {
		return height;
	}
	
	public Height getHeight() {
		return Height.getHeightFromInt(height);
	}

	/**
	 * Sets height attribute. Bound between 15cm (5.9 inches) and 365cm (12 feet).
	 * 
	 * @param height Value to set height to.
	 * @return True if height was changed.
	 */
	public boolean setHeight(int height) {
		if (this.height == height) {
			return false;
		}
		
		this.height = Math.max(Height.NEGATIVE_THREE_MINIMUM.getMinimumValue(), Math.min(height, Height.SEVEN_COLOSSAL.getMaximumValue()));

		return true;
	}

	public boolean isFeminine() {
		return getFemininity() >= Femininity.ANDROGYNOUS.getMinimumFemininity();
	}
	
	public int getFemininity() {
		return femininity;
	}

	/**
	 * @param femininity
	 *            Value to set femininity to.
	 * @return True if femininity was changed.
	 */
	public boolean setFemininity(int femininity) {
		if (this.femininity == femininity) {
			return false;
		}
		
		if (femininity <= 0) {
			if (this.femininity == 0)
				return false;
			this.femininity = 0;
			return true;
		}
		if (femininity >= 100) {
			if (this.femininity == 100)
				return false;
			this.femininity = 100;
			return true;
		}
		
		this.femininity = femininity;
		return true;
	}
	
	public BodyHair getPubicHair() {
		return pubicHair;
	}
	
	public void setPubicHair(BodyHair pubicHair) {
		this.pubicHair = pubicHair;
	}
	
	public int getBodySize() {
		return bodySize;
	}

	/**
	 * @param bodySize Value to set femininity to.
	 * @return True if bodySize was changed.
	 */
	public boolean setBodySize(int bodySize) {
		if (this.bodySize == bodySize) {
			return false;
		}
		
		if (bodySize <= 0) {
			if (this.bodySize == 0)
				return false;
			this.bodySize = 0;
			return true;
		}
		if (bodySize >= 100) {
			if (this.bodySize == 100)
				return false;
			this.bodySize = 100;
			return true;
		}
		
		this.bodySize = bodySize;
		return true;
	}
	
	public int getMuscle() {
		return muscle;
	}

	/**
	 * @param muscle Value to set muscle to.
	 * @return True if muscle was changed.
	 */
	public boolean setMuscle(int muscle) {
		if (this.muscle == muscle) {
			return false;
		}
		
		if (muscle <= 0) {
			if (this.muscle == 0)
				return false;
			this.muscle = 0;
			return true;
		}
		if (muscle >= 100) {
			if (this.muscle == 100)
				return false;
			this.muscle = 100;
			return true;
		}
		
		this.muscle = muscle;
		return true;
	}
	
	public BodyShape getBodyShape() {
		return BodyShape.valueOf(Muscle.valueOf(getMuscle()), BodySize.valueOf(getBodySize()));
	}
	
	public BodyMaterial getBodyMaterial() {
		return bodyMaterial;
	}
	
	public boolean setBodyMaterial(BodyMaterial bodyMaterial) {
		if(this.bodyMaterial == bodyMaterial) {
			return false;
		}
		
		addDiscoveredBodyCoveringsFromMaterial(bodyMaterial);
		
		this.bodyMaterial = bodyMaterial;
		
		return true;
	}

	public GenitalArrangement getGenitalArrangement() {
		return genitalArrangement;
	}

	public void setGenitalArrangement(GenitalArrangement genitalArrangement) {
		this.genitalArrangement = genitalArrangement;
	}

	public boolean isFeral() {
		return feral;
	}

	public boolean isFeralOrHasLegConfiguration(LegConfiguration... values) {
		return feral || leg.getLegConfiguration().isOneOf(values);
	}

	/**
	 * @param subspecies Pass in the AbstractSubspecies to which this character should be transformed into a feral version of. Pass in null to transform back from feral to a standard anthro.
	 */
	public void setFeral(GameCharacter target, AbstractSubspecies subspecies) {
		LegConfiguration initialLegConfiguration = target.getLegConfiguration(); // For use in pregnant feral reversion (into taur)
		
		AbstractSubspecies targetSubspecies = subspecies == null ? getSubspecies() : subspecies;
		FeralAttributes attributes = targetSubspecies.getFeralAttributes(this);
		if(attributes==null) {
			System.err.println("Error in Body.setFeral(): subspecies '"+Subspecies.getIdFromSubspecies(targetSubspecies)+"' does not support FeralAttributes!");
			return;
		}
		
		this.feral = subspecies!=null;
		// Set body to full subspecies:
		Main.game.getCharacterUtils().reassignBody(
				target,
				this,
				this.getGender(),
				targetSubspecies,
				RaceStage.GREATER,
				false);
		
		if (subspecies == null) {
			// If the target is pregnant as a feral, then feral reversion should always go into the tauric (or equivalent) lower body to better handle taur offspring
			if(target.isPregnant() && target.getLegConfiguration()!=initialLegConfiguration) {
				target.setLegConfiguration(initialLegConfiguration, true);
			}
			
			return; 
		}
		
		attributes.applySpecialPreFeralTransformationChanges(this);
		
		// Set feral-specific attributes:
		this.getLeg().getType().applyLegConfigurationTransformation(this, attributes.getLegConfiguration(), true);
		
//		this.getLeg().setLegConfigurationForced(this.getLeg().getType(), attributes.getLegConfiguration());
		this.setHeight(attributes.getSize());
		this.getLeg().setLengthAsPercentageOfHeight(null, attributes.getSerpentTailLength());
		
		// Set breast and crotch-boob counts:
		this.getBreast().setRows(null, attributes.getBreastRowCount());
		this.getBreast().setNippleCountPerBreast(null, attributes.getNipplesPerBreastCount());
		this.getBreastCrotch().setRows(null, attributes.getCrotchBreastRowCount());
		this.getBreastCrotch().setNippleCountPerBreast(null, attributes.getNipplesPerCrotchBreastCount());
		if(attributes.getCrotchBreastRowCount() == 0) {
			this.getBreastCrotch().setType(null, BreastType.NONE);
		}
		
		// Set genital relative sizes:
		AbstractRacialBody rb = targetSubspecies.getRace().getRacialBody();
		float proportionSizeDifference = ((float)attributes.getSize())/(this.isFeminine()?rb.getFemaleHeight():rb.getMaleHeight());
		if(attributes.getLegConfiguration().isLargeGenitals()) {
			proportionSizeDifference += 1; // If large genitals, increase by 100%
		}
		this.getPenis().setPenisLength(null, (int) (rb.getPenisSize()*proportionSizeDifference));
		this.getPenis().setPenisGirth(null, (int) (rb.getPenisGirth()*proportionSizeDifference));
		this.getPenis().getTesticle().setTesticleSize(null, (int) (rb.getTesticleSize()*proportionSizeDifference));
		
		// Set hair:
		this.getHair().setStyle(null, HairStyle.NONE);
		if(attributes.isHairPresent()) {
			this.getHair().setLength(null, HairLength.TWO_SHORT.getMedianValue());
		} else {
			this.getHair().setLength(null, 0);
		}
		
		removeAllMakeup();
		
		CharacterModificationUtils.resetCoveringsToBeApplied();
	}

	public void removeAllMakeup() {
		for(AbstractBodyCoveringType makeup : BodyCoveringType.allMakeupTypes) {
			if(coverings.containsKey(makeup)) {
				coverings.put(makeup, new Covering(makeup, CoveringPattern.NONE, CoveringModifier.SMOOTH, PresetColour.COVERING_NONE, false, PresetColour.COVERING_NONE, false));
			}
		}
		CharacterModificationUtils.resetCoveringsToBeApplied();
	}
	
	public boolean isPiercedStomach() {
		return piercedStomach;
	}

	public void setPiercedStomach(boolean piercedStomach) {
		this.piercedStomach = piercedStomach;
	}

	public Set<AbstractBodyCoveringType> getHeavyMakeup() {
		return heavyMakeup;
	}

	public boolean isHeavyMakeup(AbstractBodyCoveringType type) {
		return heavyMakeup.contains(type);
	}
	
	public void addHeavyMakeup(AbstractBodyCoveringType type) {
		heavyMakeup.add(type);
	}
	
	public boolean removeHeavyMakeup(AbstractBodyCoveringType type) {
		return heavyMakeup.remove(type);
	}

	public Map<AbstractBodyCoveringType, Covering> getCoverings() {
		return coverings;
	}

	/**
	 * This should only be used in special cases, where the covering map needs to be overwritten for some reason!
	 */
	public void setCoverings(Map<AbstractBodyCoveringType, Covering> coverings) {
		this.coverings = coverings;
	}
	
	public String setCovering(AbstractBodyCoveringType coveringType, Covering newCovering) {
		return setCovering(null, coveringType, newCovering);
	}
	public String setCovering(GameCharacter target, AbstractBodyCoveringType coveringType, Covering newCovering) {
		StringBuilder sb = new StringBuilder();
		try {
			if(Main.game.isStarted() && target!=null) {
				Covering initialCovering = target.getCovering(coveringType);
				if(!initialCovering.equals(newCovering)) {
					sb.append("<p>");
						sb.append(UtilText.parse(target,
								"[npc.NamePos]的身体愈发瘙痒起来，[npc.she]惊呼一声，[npc.her]的"+coveringType.getName(target)+"迅速地变了颜色。<br/>"
									+ "[npc.She]现在有了 "+newCovering.getFullDescription(target, true)+"。"));
					sb.append("</p>");
				}
			}
		} catch(Exception ex) {
			// Safely catch an exception that can be thrown when  creating a new GameCharacter - the character will call this method before initialisation of the Body is finished, which results in the following errors:
			/*
			Exception in thread "JavaFX Application Thread" java.lang.NullPointerException
				at com.lilithsthrone.game.character.GameCharacter.getBodyMaterial(GameCharacter.java:28013)
				at com.lilithsthrone.game.character.GameCharacter.getCovering(GameCharacter.java:31010)
				at com.lilithsthrone.game.character.body.Body.setCovering(Body.java:6697)
			*/
		}
		coverings.put(coveringType, newCovering);
		
		return sb.toString();
	}
	
	public String setCovering(AbstractBodyCoveringType coveringType, CoveringPattern pattern, CoveringModifier modifier, Colour primaryColor, boolean primaryGlow, Colour secondaryColor, boolean secondaryGlow) {
		return setCovering(null, coveringType, pattern, modifier, primaryColor, primaryGlow, secondaryColor, secondaryGlow);
	}
	public String setCovering(GameCharacter target, AbstractBodyCoveringType coveringType, CoveringPattern pattern, CoveringModifier modifier, Colour primaryColor, boolean primaryGlow, Colour secondaryColor, boolean secondaryGlow) {
		return setCovering(target, coveringType, new Covering(coveringType, pattern, modifier, primaryColor, primaryGlow, secondaryColor, secondaryGlow));
	}

	public String setCovering(AbstractBodyCoveringType coveringType, CoveringPattern pattern, Colour primaryColor, boolean primaryGlow, Colour secondaryColor, boolean secondaryGlow) {
		return setCovering(null, coveringType, pattern, primaryColor, primaryGlow, secondaryColor, secondaryGlow);
	}
	public String setCovering(GameCharacter target, AbstractBodyCoveringType coveringType, CoveringPattern pattern, Colour primaryColor, boolean primaryGlow, Colour secondaryColor, boolean secondaryGlow) {
		return setCovering(target, coveringType, new Covering(coveringType, pattern, primaryColor, primaryGlow, secondaryColor, secondaryGlow));
	}

	public AbstractBodyCoveringType getCoveringType(BodyPartInterface bodyPart) {
		return bodyPart.getBodyCoveringType(this);
	}

	public Covering getCoveringFromType(BodyPartInterface bodyPart) {
		return getCovering(bodyPart.getBodyCoveringType(this), false);
	}
	
	public Covering getCovering(AbstractBodyCoveringType bodyCoveringType, boolean accountForNonFleshMaterial) {
		if(!accountForNonFleshMaterial) {
			return this.getCoverings().get(bodyCoveringType);
		}
		AbstractBodyCoveringType handledType = this.getBodyMaterial()!=BodyMaterial.FLESH
												?BodyCoveringType.getMaterialBodyCoveringType(this.getBodyMaterial(), bodyCoveringType.getCategory())
												:bodyCoveringType;
		return this.getCoverings().get(handledType);
	}

	public CoveringModifier getCoveringModifier(AbstractBodyCoveringType bodyCoveringType, boolean accountForNonFleshMaterial) {
		return getCovering(bodyCoveringType, accountForNonFleshMaterial).getModifier();
	}

	public void setCoveringModifier(AbstractBodyCoveringType bodyCoveringType, boolean accountForNonFleshMaterial, CoveringModifier modifier) {
		getCovering(bodyCoveringType, accountForNonFleshMaterial).setModifier(modifier);
	}

	public CoveringPattern getCoveringPattern(AbstractBodyCoveringType bodyCoveringType, boolean accountForNonFleshMaterial) {
		return getCovering(bodyCoveringType, accountForNonFleshMaterial).getPattern();
	}

	public void setCoveringPattern(AbstractBodyCoveringType bodyCoveringType, boolean accountForNonFleshMaterial, CoveringPattern pattern) {
		getCovering(bodyCoveringType, accountForNonFleshMaterial).setPattern(pattern);
	}

	public boolean isBodyCoveringTypesDiscovered(AbstractBodyCoveringType bct) {
		return coveringsDiscovered.contains(bct);
	}

	public boolean addBodyCoveringTypesDiscovered(AbstractBodyCoveringType bct) {
		return coveringsDiscovered.add(bct);
	}
	
//	public Set<BodyCoveringType> getBodyCoveringTypesDiscovered() {
//		return coveringsDiscovered;
//	}
	
	/**
	 * Updates this body's covering to more realistic values.
	 * @param updateEyes If true, human eyes will be set to non-heterochromatic.
	 * @param updateHair If true, human hair and body hair will be set to unpatterned.
	 * @param updateBodyHairColours If true, the colour of all body hair values will be set to the matching colour of the body's head hair.
	 * @param updateSkin If true, all values for anus, lips, vagina, nipples, and penis will be set to the matching colour of the body's skin.
	 */
	public void updateCoverings(boolean updateEyes, boolean updateHair, boolean updateBodyHairColours, boolean updateSkin) {
		
//		if(this.getBodyMaterial()==BodyMaterial.SLIME) {
//			coverings.put(BodyCoveringType.SLIME, new Covering
//					(BodyCoveringType.SLIME, coverings.get(BodyCoveringType.SLIME).getPattern(), coverings.get(BodyCoveringType.SLIME).getPrimaryColour(), false, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(), false));
//		}
		
		// Make eyes normal:
		if(updateEyes) {
			coverings.put(BodyCoveringType.EYE_HUMAN, new Covering
					(BodyCoveringType.EYE_HUMAN, CoveringPattern.EYE_IRISES, coverings.get(BodyCoveringType.EYE_HUMAN).getPrimaryColour(), false, coverings.get(BodyCoveringType.EYE_HUMAN).getPrimaryColour(), false));
		}
		
		// Make hair non-highlighted:
		if(updateHair) {
			coverings.put(BodyCoveringType.HAIR_HUMAN, new Covering
					(BodyCoveringType.HAIR_HUMAN, CoveringPattern.NONE, coverings.get(BodyCoveringType.HAIR_HUMAN).getPrimaryColour(), false, coverings.get(BodyCoveringType.HAIR_HUMAN).getPrimaryColour(), false));
	
			coverings.put(BodyCoveringType.BODY_HAIR_HUMAN, new Covering
					(BodyCoveringType.BODY_HAIR_HUMAN, CoveringPattern.NONE, coverings.get(BodyCoveringType.BODY_HAIR_HUMAN).getPrimaryColour(), false, coverings.get(BodyCoveringType.BODY_HAIR_HUMAN).getPrimaryColour(), false));
			
		}
		
		if(updateBodyHairColours) {
			for(AbstractRace race : Race.getAllRaces()) {
				if(!HairType.getHairTypes(race).isEmpty()) {
					coverings.put(race.getRacialBody().getBodyHairType(), new Covering(race.getRacialBody().getBodyHairType(), coverings.get(HairType.getHairTypes(race).get(0).getBodyCoveringType(this)).getPrimaryColour()));
				}
			}
		}
		
		// Make all orifice colours the same as their surroundings:
		if(updateSkin) {
			for(BodyMaterial mat : BodyMaterial.values()) { // Update all non-flesh parts to be the same colour as main skin:
				if(mat!=BodyMaterial.FLESH) {
					AbstractBodyCoveringType coreSlimeCovering = BodyCoveringType.getMaterialBodyCoveringType(mat, BodyCoveringCategory.MAIN_SKIN);
					Covering currentCovering = this.getCovering(coreSlimeCovering, true);
					
					for(BodyCoveringCategory cat : BodyCoveringCategory.values()) {
						if(cat.isInfluencedByMaterialType()) {
							AbstractBodyCoveringType nonFleshCovering = BodyCoveringType.getMaterialBodyCoveringType(mat, cat);
							CoveringPattern pattern = currentCovering.getPattern();
							if(!nonFleshCovering.getAllPatterns().keySet().contains(pattern)) {
								pattern = nonFleshCovering.getNaturalPatterns().entrySet().iterator().next().getKey();
							}
							CoveringModifier modifier = currentCovering.getModifier();
							if(!nonFleshCovering.getAllModifiers().contains(modifier)) {
								modifier = nonFleshCovering.getNaturalModifiers().get(0);
							}
							
							coverings.put(nonFleshCovering,
									new Covering(nonFleshCovering,
											pattern, //nonFleshCovering.getNaturalPatterns().entrySet().iterator().next().getKey(),
											modifier,
											coverings.get(coreSlimeCovering).getPrimaryColour(),
											false,
											coverings.get(coreSlimeCovering).getPrimaryColour(),
											false));
						}
					}
				}
			}
			
			updateAnusColouring();

			updateNippleColouring();
			
			updateNippleCrotchColouring();

			updateMouthColouring();

			updateVaginaColouring();
			
			updatePenisColouring();
			
			updateSpinneretColouring();
		}
	}
	
	public void updateAnusColouring() {
		if(ass.getType().getRace()==Race.ANGEL) {
			coverings.put(BodyCoveringType.ANUS, new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(ass.getType().getRace()==Race.DEMON) {
			coverings.put(BodyCoveringType.ANUS, new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.ANUS, new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}
	
	public void updateNippleColouring() {
		if(breast.getType().getRace()==Race.ANGEL) {
			coverings.put(BodyCoveringType.NIPPLES, new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(breast.getType().getRace()==Race.DEMON) {
			coverings.put(BodyCoveringType.NIPPLES, new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.NIPPLES, new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}
	
	public void updateNippleCrotchColouring() {
		if(breastCrotch.getType().getRace()==Race.ANGEL) {
			coverings.put(BodyCoveringType.NIPPLES_CROTCH,
					new Covering(BodyCoveringType.NIPPLES_CROTCH, CoveringPattern.ORIFICE_NIPPLE_CROTCH, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(breastCrotch.getType().getRace()==Race.DEMON) {
			coverings.put(BodyCoveringType.NIPPLES_CROTCH,
					new Covering(BodyCoveringType.NIPPLES_CROTCH, CoveringPattern.ORIFICE_NIPPLE_CROTCH, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.NIPPLES_CROTCH,
					new Covering(BodyCoveringType.NIPPLES_CROTCH, CoveringPattern.ORIFICE_NIPPLE_CROTCH, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}
	
	public void updateMouthColouring() {
		if(face.getType().getRace()==Race.ANGEL) {
			coverings.put(BodyCoveringType.MOUTH, new Covering(BodyCoveringType.MOUTH, CoveringPattern.ORIFICE_MOUTH, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(face.getType().getRace()==Race.DEMON) {
			coverings.put(BodyCoveringType.MOUTH, new Covering(BodyCoveringType.MOUTH, CoveringPattern.ORIFICE_MOUTH, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.MOUTH, new Covering(BodyCoveringType.MOUTH, CoveringPattern.ORIFICE_MOUTH, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}
	
	public void updateVaginaColouring() {
		AbstractRace race = vagina.getType()!=VaginaType.NONE?vagina.getType().getRace():getRace();
		if(race==Race.ANGEL) {
			coverings.put(BodyCoveringType.VAGINA, new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(race==Race.DEMON) {
			coverings.put(BodyCoveringType.VAGINA, new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.VAGINA, new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}
	
	public void updatePenisColouring() {
		AbstractRace race = penis.getType()!=PenisType.NONE?penis.getType().getRace():getRace();
		if(race==Race.ANGEL) {
			coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(race==Race.DEMON) {
			coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(race==Race.DOG_MORPH || race==Race.WOLF_MORPH || race==Race.FOX_MORPH) {
			coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, PresetColour.SKIN_RED, false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}

	public void updateSpinneretColouring() {
		coverings.put(BodyCoveringType.SPINNERET, new Covering(BodyCoveringType.SPINNERET, CoveringPattern.ORIFICE_SPINNERET, coverings.get(BodyCoveringType.VAGINA).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
	}
	
	public boolean isAbleToFlyFromArms() {
		if(this.getBodyMaterial()==BodyMaterial.SLIME
				|| this.getBodyMaterial()==BodyMaterial.SILICONE
				|| this.getLeg().getLegConfiguration().getMinimumWingSizeForFlight(this).getValue()>WingSize.THREE_LARGE.getValue()) {
			return false;
		}
		return arm.getType().allowsFlight();
	}
	
	public boolean isAbleToFlyFromWings() {
		if(this.getBodyMaterial()==BodyMaterial.SLIME || this.getBodyMaterial()==BodyMaterial.SILICONE) {
			return false;
		}
		return wing.getType().allowsFlight() && wing.getSize().getValue()>=this.getLeg().getLegConfiguration().getMinimumWingSizeForFlight(this).getValue();
	}

	public boolean isAbleToFlyFromExtraParts() {
		return getAllBodyParts().stream().anyMatch(bpi -> bpi.getType().getTags().contains(BodyPartTag.ALLOWS_FLIGHT));
	}

	/**
	 * Basically the opposite of isAbleToFly, but skips the minimumWingSizeForFlight-checks
	 * and the checks for slime bodies.
	 * @return true if the body is incapable of flight
	 */
	public Boolean isInCapableOfFlight() {
		return !(
			isAbleToFlyFromExtraParts() ||
			arm.getType().allowsFlight() ||
			wing.getType().allowsFlight());
	}

	public boolean isTakesAfterMother() {
		return takesAfterMother;
	}

	public void setTakesAfterMother(boolean takesAfterMother) {
		this.takesAfterMother = takesAfterMother;
	}

	/**
	 * Returns a randomly chosen BodyPart-type from the list of types (e. g. multiple WingTypes)
	 * provided as parameters. To give one or more types more weight these types can be repeated.
	 * @param values List of BodyPartTypes to choose from randomly
	 * @return The randomly chosen type of the corresponding BodyPart
	 */
	public BodyPartTypeInterface randomTypeFrom(BodyPartTypeInterface... values) {
		return Util.randomItemFrom(Util.newArrayListOfValues(values));
	}

	/**
	 * This is reset to null after every transformation, and is then recalculated in AbstractSubspecies.
	 * @return The subspecies which this character appears to be if they were made of flesh.
	 *  Use getTrueSubspecies() or do some checks with getSubspeciesOverride() to get their true Subspecies, but for 99.9% of the time, that won't be necessary and this method is fine to use.
	 */
	public AbstractSubspecies getFleshSubspecies() {
		if(fleshSubspecies==null) {
			fleshSubspecies = AbstractSubspecies.getSubspeciesFromBody(this, this.getRaceFromPartWeighting());
		}
		return fleshSubspecies;
	}

	public void setFleshSubspecies(AbstractSubspecies fleshSubspecies) {
		this.fleshSubspecies = fleshSubspecies;
	}

}
