package com.lilithsthrone.game.character.body.abstractTypes;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.*;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.*;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.character.race.*;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.BodyPartClothingBlock;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.threading.DocBuilders;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import org.w3c.dom.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 0.3.1
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractLegType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;
	
	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;

	private String transformationName;
	
	private Map<LegConfiguration, FootStructure> defaultFootStructure;
	private AbstractFootType footType;
	
	private String determiner;
	
	private String name;
	private String namePlural;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private List<String> footDescriptorsMasculine;
	private List<String> footDescriptorsFeminine;
	
	private List<String> toeDescriptorsMasculine;
	private List<String> toeDescriptorsFeminine;
	
	private String legTransformationDescription;
	private String legBodyDescription;
	
	private List<LegConfiguration> allowedLegConfigurations;
	
	private boolean spinneret;

	private AbstractTentacleType tentacleType;
	private int tentacleCount;
	
	/**
	 * @param coveringType What covers this leg type (i.e skin/fur/feather type).
	 * @param race What race has this leg type.
	 * @param defaultFootStructure The default foot structure for this leg type.
	 * @param footType The type of foot attached to this leg type.
	 * @param determiner Will usually be "a pair of".
	 * @param name The singular name of the leg. This will usually just be "leg".
	 * @param namePlural The plural name of the leg. This will usually just be "legs".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this leg type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this leg type.
	 * @param footDescriptorsMasculine The <b>additional</b> descriptors that are applied to a masculine form of the associated foot type. (Base descriptors are in the AbstractFootType class.)
	 * @param footDescriptorsFeminine The <b>additional</b> descriptors that are applied to a feminine form of the associated foot type. (Base descriptors are in the AbstractFootType class.)
	 * @param toeDescriptorsMasculine The <b>additional</b> descriptors that are applied to a masculine form of the associated toe type. (Base descriptors are in the AbstractFootType class.)
	 * @param toeDescriptorsFeminine The <b>additional</b> descriptors that are applied to a feminine form of the associated toe type. (Base descriptors are in the AbstractFootType class.)
	 * @param legTransformationDescription A paragraph describing a character's legs transforming into this leg type. Parsing assumes that the character already has this leg type and associated skin covering.
	 * @param legBodyDescription A sentence or two to describe this leg type, as seen in the character view screen. It should follow the same format as all of the other entries in the LegType class.
	 * @param allowedLegConfigurations A list of LegConfigurations that are allowed for this LegType.
	 * @param spinneret true if this leg type has a spinneret.
	 */
	public AbstractLegType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			FootStructure defaultFootStructure,
			AbstractFootType footType,
			String determiner,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			List<String> footDescriptorsMasculine,
			List<String> footDescriptorsFeminine,
			List<String> toeDescriptorsMasculine,
			List<String> toeDescriptorsFeminine,
			String legTransformationDescription,
			String legBodyDescription,
			List<LegConfiguration> allowedLegConfigurations,
			boolean spinneret) {
		
		this.coveringType = coveringType;
		this.race = race;

		this.transformationName = null; // Use default race transformation name
		
		this.defaultFootStructure = Util.newHashMapOfValues(new Value<>(LegConfiguration.BIPEDAL, defaultFootStructure));
		this.footType = footType;
		
		this.allowedLegConfigurations = allowedLegConfigurations;
		
		this.determiner = determiner;
		
		this.name = name;
		this.namePlural = namePlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.footDescriptorsMasculine = footDescriptorsMasculine;
		this.footDescriptorsFeminine = footDescriptorsFeminine;
		
		this.toeDescriptorsMasculine = toeDescriptorsMasculine;
		this.toeDescriptorsFeminine = toeDescriptorsFeminine;
		
		this.legTransformationDescription = legTransformationDescription;
		this.legBodyDescription = legBodyDescription;
		
		this.spinneret = spinneret;
		
		this.tentacleType = TentacleType.NONE;
		this.tentacleCount = 0;
	}
	
	public AbstractLegType(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = DocBuilders.parseDoc(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile);

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.race = Race.getRaceFromId(coreElement.getMandatoryFirstOf("race").getTextContent());
				this.coveringType = BodyCoveringType.getBodyCoveringTypeFromId(coreElement.getMandatoryFirstOf("coveringType").getTextContent());

				this.transformationName = coreElement.getMandatoryFirstOf("transformationName").getTextContent();
				
				this.defaultFootStructure = new HashMap<>();
				FootStructure defaultStructure = FootStructure.valueOf(coreElement.getMandatoryFirstOf("defaultFootStructure").getTextContent());
				for(LegConfiguration config : LegConfiguration.values()) {
					this.defaultFootStructure.put(config, defaultStructure);
				}
				for(Element e : coreElement.getAllOf("additionalFootStructure")) {
					this.defaultFootStructure.put(LegConfiguration.valueOf(e.getAttribute("legConfiguration")), FootStructure.valueOf(e.getTextContent()));
				}
				
				this.footType = FootType.getFootTypeFromId(coreElement.getMandatoryFirstOf("footType").getTextContent());
				this.spinneret = Boolean.valueOf(coreElement.getMandatoryFirstOf("spinneret").getTextContent());
				
				this.tentacleType = TentacleType.getTentacleTypeFromId(coreElement.getMandatoryFirstOf("tentacleType").getTextContent());
				this.tentacleCount = Integer.valueOf(coreElement.getMandatoryFirstOf("tentacleCount").getTextContent());
				
				this.allowedLegConfigurations = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("allowedLegConfigurations").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("allowedLegConfigurations").getAllOf("configuration")) {
						allowedLegConfigurations.add(LegConfiguration.valueOf(e.getTextContent()));
					}
				}
				if(allowedLegConfigurations.isEmpty()) {
					allowedLegConfigurations.add(LegConfiguration.BIPEDAL);
				}
				
				this.name = coreElement.getMandatoryFirstOf("name").getTextContent();
				this.namePlural = coreElement.getMandatoryFirstOf("namePlural").getTextContent();

				this.descriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("descriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("descriptorsMasculine").getAllOf("descriptor")) {
						descriptorsMasculine.add(e.getTextContent());
					}
				}
				this.descriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("descriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("descriptorsFeminine").getAllOf("descriptor")) {
						descriptorsFeminine.add(e.getTextContent());
					}
				}

				this.footDescriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("footDescriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("footDescriptorsMasculine").getAllOf("descriptor")) {
						footDescriptorsMasculine.add(e.getTextContent());
					}
				}
				this.footDescriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("footDescriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("footDescriptorsFeminine").getAllOf("descriptor")) {
						footDescriptorsFeminine.add(e.getTextContent());
					}
				}

				this.toeDescriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("toeDescriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("toeDescriptorsMasculine").getAllOf("descriptor")) {
						toeDescriptorsMasculine.add(e.getTextContent());
					}
				}
				this.toeDescriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("toeDescriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("toeDescriptorsFeminine").getAllOf("descriptor")) {
						toeDescriptorsFeminine.add(e.getTextContent());
					}
				}
				
				this.legTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.legBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractLegType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}


	@Override
	public String getTransformationNameOverride() {
		return transformationName;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc==null) {
			return determiner;
		}
		if(gc.getLegCount()==1) {
			return "一条";
		} else if(gc.getLegCount()==2) {
			return "一双";
		}
		return Util.intToString(gc.getLegCount());
	}

	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		if(gc==null) {
			return true;
		}
		return gc.getLegCount()>1;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		if(gc!=null) {
			switch(gc.getLegConfiguration()) {
				case ARACHNID:
				case AVIAN:
				case BIPEDAL:
				case QUADRUPEDAL:
				case WINGED_BIPED:
					return "腿";
				case CEPHALOPOD:
					return "触手";
				case TAIL:
				case TAIL_LONG:
					return "尾巴";
			}
		}
		return name;
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(gc!=null) {
			switch(gc.getLegConfiguration()) {
				case ARACHNID:
				case AVIAN:
				case BIPEDAL:
				case QUADRUPEDAL:
				case WINGED_BIPED:
					return "腿";
				case CEPHALOPOD:
					return "触手";
				case TAIL:
				case TAIL_LONG:
					return "尾巴";
			}
		}
		return namePlural;
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if(gc!=null && !gc.isFeminine()) {
			return Util.randomItemFrom(descriptorsMasculine);
		} else {
			return Util.randomItemFrom(descriptorsFeminine);
		}
	}

	@Override
	public AbstractBodyCoveringType getBodyCoveringType(Body body) {
		return coveringType;
	}

	@Override
	public AbstractRace getRace() {
		return race;
	}

	@Override
	public TFModifier getTFModifier() {
		return getTFTypeModifier(LegType.getLegTypes(race));
	}

	public AbstractFootType getFootType() {
		return footType;
	}

	public FootStructure getDefaultFootStructure(LegConfiguration legConfiguration) {
		if(!defaultFootStructure.containsKey(legConfiguration)) {
			return defaultFootStructure.get(LegConfiguration.BIPEDAL);
		}
		return defaultFootStructure.get(legConfiguration);
	}
	
	
	public String getFootNameSingular(GameCharacter gc) {
		switch(gc.getLegConfiguration()) {
			case ARACHNID:
			case AVIAN:
			case BIPEDAL:
			case QUADRUPEDAL:
			case WINGED_BIPED:
				break;
			case CEPHALOPOD:
				return FootType.TENTACLE.getFootName();
			case TAIL:
				return "鳍";
			case TAIL_LONG:
				return "尾巴";
		}
		return this.getFootType().getFootName();
	}
	
	public String getFootNamePlural(GameCharacter gc) {
		switch(gc.getLegConfiguration()) {
			case ARACHNID:
			case AVIAN:
			case BIPEDAL:
			case QUADRUPEDAL:
			case WINGED_BIPED:
				break;
			case CEPHALOPOD:
				return FootType.TENTACLE.getFootNamePlural();
			case TAIL:
				return "鳍";
			case TAIL_LONG:
				return "尾巴";
		}
		return this.getFootType().getFootNamePlural();
	}

	public String getFootDescriptor(GameCharacter gc) {
		switch(gc.getLegConfiguration()) {
			case ARACHNID:
			case AVIAN:
			case BIPEDAL:
			case QUADRUPEDAL:
			case WINGED_BIPED:
				break;
			case CEPHALOPOD:
				if (gc.isFeminine()) {
					return Util.randomItemFrom(Util.mergeLists(FootType.TENTACLE.getFootDescriptorsFeminine(), footDescriptorsFeminine));
				} else {
					return Util.randomItemFrom(Util.mergeLists(FootType.TENTACLE.getFootDescriptorsMasculine(), footDescriptorsMasculine));
				}
			case TAIL:
			case TAIL_LONG:
				if (gc.isFeminine()) {
					return Util.randomItemFrom(Util.newArrayListOfValues("阳刚","有力"));
				} else {
					return Util.randomItemFrom(Util.newArrayListOfValues("阴柔", "有力", "修长"));
				}
		}
		if (gc.isFeminine()) {
			return Util.randomItemFrom(Util.mergeLists(this.getFootType().getFootDescriptorsFeminine(), footDescriptorsFeminine));
		} else {
			return Util.randomItemFrom(Util.mergeLists(this.getFootType().getFootDescriptorsMasculine(), footDescriptorsMasculine));
		}
	}
	

	public String getToeNameSingular(GameCharacter gc) {
		switch(gc.getLegConfiguration()) {
			case ARACHNID:
			case AVIAN:
			case BIPEDAL:
			case QUADRUPEDAL:
			case WINGED_BIPED:
				break;
			case CEPHALOPOD:
				return FootType.TENTACLE.getToeSingularName();
			case TAIL:
				return "尾巴尖";
			case TAIL_LONG:
				return "尾巴尖";
		}
		return this.getFootType().getToeSingularName();
	}
	
	public String getToeNamePlural(GameCharacter gc) {
		switch(gc.getLegConfiguration()) {
			case ARACHNID:
			case AVIAN:
			case BIPEDAL:
			case QUADRUPEDAL:
			case WINGED_BIPED:
				break;
			case CEPHALOPOD:
				return FootType.TENTACLE.getToePluralName();
			case TAIL:
				return "尾巴尖";
			case TAIL_LONG:
				return "尾巴尖";
		}
		return this.getFootType().getToePluralName();
	}

	public String getToeDescriptor(GameCharacter gc) {
		switch(gc.getLegConfiguration()) {
			case ARACHNID:
			case AVIAN:
			case BIPEDAL:
			case QUADRUPEDAL:
			case WINGED_BIPED:
				break;
			case CEPHALOPOD:
				if (gc.isFeminine()) {
					return Util.randomItemFrom(Util.mergeLists(FootType.TENTACLE.getToeDescriptorsFeminine(), footDescriptorsFeminine));
				} else {
					return Util.randomItemFrom(Util.mergeLists(FootType.TENTACLE.getToeDescriptorsFeminine(), footDescriptorsMasculine));
				}
			case TAIL:
			case TAIL_LONG:
				if (gc.isFeminine()) {
					return Util.randomItemFrom(Util.newArrayListOfValues("阳刚","有力"));
				} else {
					return Util.randomItemFrom(Util.newArrayListOfValues("阴柔", "有力", "修长"));
				}
		}
		if (gc.isFeminine()) {
			return Util.randomItemFrom(Util.mergeLists(this.getFootType().getToeDescriptorsFeminine(), toeDescriptorsFeminine));
		} else {
			return Util.randomItemFrom(Util.mergeLists(this.getFootType().getToeDescriptorsMasculine(), toeDescriptorsMasculine));
		}
	}
	
	
//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, legBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, legTransformationDescription);
	}


	/**
	 * Applies the related leg configuration transformation for this leg type, and returns a description of the changes.<br/><br/>
	 * 
	 * <b>When overriding, consider:</b><br/>
	 * Ass.class (type)<br/>
	 * BreastCrotch.class (type)<br/>
	 * Tail.class (type)<br/>
	 * Tentacle.class (type)<br/>
	 * Penis.class (type, size, cloaca)<br/>
	 * Vagina.class (type, capacity, cloaca)<br/>
	 * 
	 * @param legConfiguration The leg configuration to be applied.
	 * @param character The character which is being transformed.
	 * @param applyEffects Whether the transformative effects should be applied. Pass in false to get the transformation description without applying any of the actual effects.
	 * @param applyFullEffects Pass in true if you want the additional transformations to include attribute changes (such as penis resizing, vagina capacity resetting, etc.).
	 * 
	 * @return A description of the transformation.
	 */
	public String applyLegConfigurationTransformation(GameCharacter character, LegConfiguration legConfiguration, boolean applyEffects, boolean applyFullEffects) {
		StringBuilder feralStringBuilder = new StringBuilder();

		if(character.isFeral()) {
			return "<p style='text-align:center;'>"
						+ UtilText.parse(character, "[style.italicsDisabled(由于[npc.Name]是兽态[npc.race]，[npc.sheIsFull]无法转化腿部配置，所以无事发生！)]")
					+ "</p>";
		}
		
		if(character.getLegConfiguration()==legConfiguration && character.getLegType().equals(this)) {
			return "<p>"
						+ UtilText.parse(character, "[style.italicsDisabled(由于[npc.name]在腿部配置中已经拥有了[npc.a_legRace]的下半身，所以无事发生……)]")
					+ "</p>";
		}
		
		if(!character.getLegType().isLegConfigurationAvailable(legConfiguration)) {
			return "<p>"
					+ UtilText.parse(character, "[style.italicsDisabled(由于[npc.namePos]的下半身无法转化为'"+legConfiguration.getName()+"'配置，所以无事发生……)]")
				+ "</p>";
		}
		
		feralStringBuilder.append(handleLegConfigurationChanges(character.getBody(), legConfiguration, applyEffects, applyFullEffects));

		feralStringBuilder.append("<p style='text-align:center;'>");
			if(character.hasWings()) {
				if(legConfiguration.isWingsOnLegConfiguration()) {
					feralStringBuilder.append("[style.italicsFeral([npc.Her][npc.wingSize]、[npc.wings+]现在正位于[npc.her]"+legConfiguration.getName()+"的身体两侧！)]");
				} else {
					feralStringBuilder.append("[style.italicsFeral([npc.Her][npc.wingSize]、[npc.wings+]现在正位于[npc.her]上半身的背部。)]");
				}
			} else {
				if(legConfiguration.isWingsOnLegConfiguration()) {
					feralStringBuilder.append("[style.italicsFeral(若[npc.she]长出翅膀，将会位于[npc.her]"+legConfiguration.getName()+"的身体两侧！)]");
				} else {
					feralStringBuilder.append("[style.italicsFeral(若[npc.she]长出翅膀，将会位于[npc.her]的上半身。)]");
				}
			}
			if(!legConfiguration.isAbleToGrowTail()) {
				feralStringBuilder.append("<br/>");
				feralStringBuilder.append("[style.italicsFeral(当前腿部配置不允许[npc.name]长出尾巴！)]");
			}
		feralStringBuilder.append("</p>");
		
		if(applyEffects) {
			character.getBody().getLeg().setLegConfigurationForced(this, legConfiguration);
		}
		
		character.calculateStatusEffects(0);
		
		feralStringBuilder.append("<p>"
									+ character.postTransformationCalculation()
								+ "</p>");
		
		return UtilText.parse(character, feralStringBuilder.toString());
	}
	
	/**
	 * For use in modifying bodies without an attached character. Outside of the Subspecies class, you should probably always be calling the version of this method that takes in a GameCharacter.
	 * <br/>
	 * <b>Note:</b> If the body's LegConfiguration is already set to legConfiguration, then nothing will happen!
	 * 
	 * @param body The body to be modified.
	 * @param legConfiguration The LegConfiguration to be applied.
	 * @param applyFullEffects Pass in true if you want the additional transformations to include attribute changes (such as penis resizing, vagina capacity resetting, etc.).
	 */
	public void applyLegConfigurationTransformation(Body body, LegConfiguration legConfiguration, boolean applyFullEffects) {
		if(body.getLegConfiguration()==legConfiguration) {
			return;
		}
		handleLegConfigurationChanges(body, legConfiguration, true, applyFullEffects);
		body.getLeg().setLegConfigurationForced(this, legConfiguration);
	}

	/**
	 * @param applyFullEffects Pass in true if you want the additional transformations to include attribute changes (such as penis resizing, vagina capacity resetting, etc.).
	 */
	private String handleLegConfigurationChanges(Body body, LegConfiguration legConfiguration, boolean applyEffects, boolean applyFullEffects) {
		
		String feralRaceName = this.getRace().getFeralName(new LegConfigurationAffinity(legConfiguration, body.getSubspecies().getAffinity()), false);
		String feralRaceNameDeterminer = UtilText.generateSingularDeterminer(feralRaceName);
		StringBuilder feralStringBuilder = new StringBuilder();
		String feralRaceNameWithDeterminer = feralRaceNameDeterminer+feralRaceName;
		String raceColorString = this.getRace().getColour().toWebHexString();
		boolean feral = true;
		
		switch(legConfiguration) {
			case BIPEDAL:
				feral = false;
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, body.getLeg().getLegConfiguration(), legConfiguration.isLargeGenitals(), applyFullEffects); // revert feral parts based on current configuration
					// Changing back to bipedal reverts crotch-boobs based on preferences:
					AbstractRacialBody startingBodyType = RacialBody.valueOfRace(this.getRace());
					if(body.getRaceStage()!=RaceStage.GREATER || Main.getProperties().getUddersLevel()<2 || !body.getGender().isFeminine()) {
						body.setBreastCrotch(
								new BreastCrotch(
									BreastType.NONE,
									Util.randomItemFrom(startingBodyType.getBreastCrotchShapes()),
									startingBodyType.getBreastCrotchSize(),
									startingBodyType.getBreastCrotchLactationRate(),
									startingBodyType.getBreastCrotchCount(),
									startingBodyType.getBreastCrotchNippleSize(),
									startingBodyType.getBreastCrotchNippleShape(),
									startingBodyType.getBreastCrotchAreolaeSize(),
									startingBodyType.getBreastCrotchAreolaeShape(),
									startingBodyType.getNippleCountPerBreastCrotch(),
									startingBodyType.getBreastCrotchCapacity(),
									startingBodyType.getBreastCrotchDepth(),
									startingBodyType.getBreastCrotchElasticity(),
									startingBodyType.getBreastCrotchPlasticity(), 
									true));
					}
					body.setGenitalArrangement(startingBodyType.getGenitalArrangement());
				}
				feralStringBuilder.append(
						"<p>"
							+ "[npc.NamePos]的下半身变回了通常的两足配置，生殖器也回到了[npc.legs]间原本的位置。<br/>"
							+ "[npc.Name]现在拥有[style.boldTfGeneric(两条)]<b style='color:"+raceColorString+";'>"+this.getTransformName()+"腿</b>，覆盖着[npc.legFullDescription]。"
						+ "</p>");
				break;
			case ARACHNID:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					if(!legConfiguration.getAvailableGenitalConfigurations().contains(body.getGenitalArrangement())) {
						body.setGenitalArrangement(legConfiguration.getAvailableGenitalConfigurations().get(0));
					}
				}
				feralStringBuilder.append(
						"<p>"
							+ "没有任何预兆，[npc.name]的[npc.legs]便顿时没了力气，[npc.she]惊叫一声倒在地上。"
							+ "还没等[npc.sheIs]对突如其来的事态做出反应，下半身便开始迅速转化起来……"
						+ "</p>"
						+ "<p>"
							+ "[npc.name]低头一看，难以置信地目睹着自己的[npc.legs]分散开来，变成了八条修长而分节的腿。"
							+ "然而变化还未停止，[npc.her]的下半身继续迅速化形，成了八条腿的巨大"+feralRaceName+"的样子。"
							+ "[npc.her]的生殖器转移到了其庞大的蛛形身体的下方，不禁漏出一声性奋的[npc.moan]，"
									+ "而[npc.her]的肛门"+(body.getLeg().getType().hasSpinneret()?"和丝囊":"")+"则位于其腹部的尖端。"
							+ "[style.italicsSex(由于[npc.her]的生殖器只有从下方才能看见，所以即使没有覆盖其蛛形身躯的衣物[npc.she]也不会感到尴尬。)]<br/>"
							+ "[npc.Name]现在拥有<b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>的[style.boldTfGeneric(蛛形身躯)]，覆盖着[npc.legFullDescription]。"
						+ "</p>");
				break;
			case AVIAN:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					if(!legConfiguration.getAvailableGenitalConfigurations().contains(body.getGenitalArrangement())) {
						body.setGenitalArrangement(legConfiguration.getAvailableGenitalConfigurations().get(0));
					}
				}
				feralStringBuilder.append(
						"<p>"
							+ "没有任何预兆，[npc.name]的[npc.legs]便顿时没了力气，[npc.she]惊叫一声倒在地上。"
							+ "还没等[npc.sheIs]对突如其来的事态做出反应，下半身便开始迅速转化起来……"
						+ "</p>"
						+ "<p>"
							+ "[npc.name]低头一看，难以置信地目睹着自己的整个下半身迅速化形为巨大的"+feralRaceName+"的样子。"
							+ "[npc.her]的生殖器转移到泄殖腔的位置，位于其庞大的鸟类身躯下端向后，不禁漏出一声性奋的[npc.moan]。"
							+ "[style.italicsSex(由于[npc.her]的生殖器只有从下方才能看见，所以即使没有覆盖其鸟类身躯的衣物[npc.she]也不会感到尴尬。)]<br/>"
							+ "[npc.Name]现在拥有<b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>的[style.boldTfGeneric(鸟类身躯)]，覆盖着[npc.legFullDescription]。"
						+ "</p>");
				break;
			case CEPHALOPOD:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					body.setGenitalArrangement(GenitalArrangement.CLOACA);
				}
				feralStringBuilder.append(
						"<p>"
							+ "没有任何预兆，[npc.name]的[npc.legs]便顿时没了力气，[npc.she]惊叫一声倒在地上。"
							+ "还没等[npc.sheIs]对突如其来的事态做出反应，下半身便开始迅速转化起来……"
						+ "</p>"
						+ "<p>"
							+ "[npc.name]低头一看，难以置信地目睹着自己的[npc.legs]分散开来，变成了八条长而粗壮的触手。"
							+ "然而变化还未停止，[npc.her]的下半身继续迅速化形，成了八条腿的巨大"+feralRaceName+"的样子。"
							+ "[npc.her]的生殖器转移到泄殖腔的位置，位于那一团触手的下端正中，不禁漏出一声性奋的[npc.moan]。"
							+ "[style.italicsSex(由于[npc.her]的泄殖腔只有从下方才能看见，所以即使没有覆盖其触手身躯的衣物[npc.she]也不会感到尴尬。)]<br/>"
							+ "[npc.Name]现在拥有<b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>的[style.boldTfGeneric(触手身躯)]，覆盖着[npc.legFullDescription]。"
						+ "</p>");
				break;
			case TAIL:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					body.setGenitalArrangement(GenitalArrangement.CLOACA);
				}
				feralStringBuilder.append(
						"<p>"
							+ "[npc.NamePos]的下半身变成了"+feralRaceNameWithDeterminer+"的尾巴样子，生殖器和肛门则坐落在前向的泄殖腔处，"
									+ "与[npc.her]腿间生殖器常规的位置相似。<br/>"
							+ "[npc.Name]现在拥有<b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>的[style.boldTfGeneric(尾巴)]，覆盖着[npc.legFullDescription]。"
						+ "</p>");
				break;
			case TAIL_LONG:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					body.setGenitalArrangement(GenitalArrangement.CLOACA);
				}
				feralStringBuilder.append(
						"<p>"
							+ "[npc.NamePos]的下半身变成了巨大"+feralRaceNameWithDeterminer+"尾巴的样子，生殖器和肛门则坐落在前向的泄殖腔处，"
									+ "与[npc.her]腿间生殖器常规的位置相似。<br/>"
							+ "[npc.Name]现在拥有<b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>的[style.boldTfGeneric(巨型尾巴)]，覆盖着[npc.legFullDescription]。"
						+ "</p>");
				break;
			case WINGED_BIPED:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					AbstractRacialBody startingBodyType = RacialBody.valueOfRace(this.getRace());
					body.setGenitalArrangement(startingBodyType.getGenitalArrangement());
				}

				feralStringBuilder.append(
						"<p>"
							+ "[npc.NamePos]的下半身变回了通常的两足配置，生殖器也回到了[npc.legs]间原本的位置。"
							+ "[npc.Name]发出一声尖叫，[npc.she]感觉到脊椎正不受控制地弓起，迅速变形着。"
							+ "转化只持续了一段时间，[npc.Name]已经可以自然地将[npc.arms]当做前腿使用，来支撑[npc.her]截然不同的身躯。<br/>"
							+ "[npc.Name]现在拥有[style.boldTfGeneric(两条)]<b style='color:"+raceColorString+";'>"+this.getTransformName()+"腿</b>，覆盖着[npc.legFullDescription]，"
									+ "而[style.boldTfGeneric([npc.arms]也可以用作前腿使用)]。"
						+ "</p>");
				break;
			case QUADRUPEDAL:
				feralStringBuilder.append(
						"<p>"
							+ "一股强烈的不安与刺痛感逐渐在[npc.namePos][npc.legs+]上传播开来，");
				
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					body.setGenitalArrangement(body.getLegType().getRace().getRacialBody().getGenitalArrangement());
				}
				
				feralStringBuilder.append(
						"[npc.she]忽然失去平衡，倒在了地上，不禁慌乱地惊呼一声。"
							+ "[npc.NamePos]的下半身就在眼前转化起来，肢体缩回了中间的[npc.bodyMaterial]之中，"
								+ "接着又瞬间伸出，却已经变成了四足的配置。"
						+ "</p>"
						+ "<p>"
							+ "转化仍在继续，[npc.her][npc.ass+]忽然变大，向外突出出来，很快躯体部分就变化为与腿部相称的造型。"
							+ "尽管过程十分惊悚，但意外的是，并没有任何痛感，[npc.name]连连[npc.moans]着，显然是受到了惊吓。"
						+ "</p>"
						+ "<p>"
							+ "又过了一会儿，[npc.namePos]的下半身已经变得跟"+feralRaceNameWithDeterminer
								+"完全一样，生殖器和肛门也转移到与兽态"+feralRaceName+"相同的位置。<br/>"
							+ "[npc.Name]现在拥有<b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>样子的[style.boldTfGeneric(下半身)]，覆盖着[npc.legFullDescription(true)]。"
						+ "</p>");
				break;
		}

		// Increase or decrease height based on configuration:
		if(applyEffects) {
			if(!body.getLegConfiguration().isTall() && legConfiguration.isTall()) {
				int newHeight = (int) (body.getHeightValue()*1.33f);
				if(body.isShortStature()) {
					newHeight = Math.min(Height.getShortStatureCutOff()-1, newHeight);
				}
				body.setHeight(newHeight);
				String colouredHeightValue = "<span style='color:"+body.getHeight().getColour().toWebHexString()+";'>[npc.heightValue]</span>";
				feralStringBuilder.append("<p>[npc.namePos]的下半身变样之后，让[npc.herHim]更高了，挺直身子的身高是"+colouredHeightValue+"。</p>");
				
			} else if(body.getLegConfiguration().isTall() && !legConfiguration.isTall()) {
				int newHeight = (int) (body.getHeightValue()/1.33f);
				if(!body.isShortStature()) {
					newHeight = Math.max(Height.getShortStatureCutOff(), newHeight);
				}
				if(!body.isFairySized()) {
					newHeight = Math.max(Height.getFairySizeCutOff(), newHeight);
				}
				newHeight = Math.max(newHeight, Height.NEGATIVE_TWO_MINIMUM.getMinimumValue()); // Do not reduce into tiny size
				body.setHeight(newHeight);
				String colouredHeightValue = "<span style='color:"+body.getHeight().getColour().toWebHexString()+";'>[npc.heightValue]</span>";
				feralStringBuilder.append("<p>[npc.namePos]的下半身变样之后，让[npc.herHim]更矮了，挺直身子的身高是"+colouredHeightValue+"。</p>");
			}
		}
		
		
		if(legConfiguration.isTailLostOnInitialTF()) {
			if(body.getTail().getType()!=TailType.NONE) {
				body.getTail().setType(null, TailType.NONE);
				// Tail description is handled below
//				feralStringBuilder.append(
//						"<p style='text-align:center;'>"
//								+ "[style.italicsFeral(As part of the transformation, [npc.name] [nc.has] lost [npc.her] tail!)]"
//						+ "</p>");
			}
		}
		
		if(Main.getProperties().getUddersLevel()==0 && !body.isFeral()) {
			AbstractRacialBody startingBodyType = RacialBody.valueOfRace(this.getRace());
			body.setBreastCrotch(
					new BreastCrotch(
						BreastType.NONE,
						Util.randomItemFrom(startingBodyType.getBreastCrotchShapes()),
						startingBodyType.getBreastCrotchSize(),
						startingBodyType.getBreastCrotchLactationRate(),
						startingBodyType.getBreastCrotchCount(),
						startingBodyType.getBreastCrotchNippleSize(),
						startingBodyType.getBreastCrotchNippleShape(),
						startingBodyType.getBreastCrotchAreolaeSize(),
						startingBodyType.getBreastCrotchAreolaeShape(),
						startingBodyType.getNippleCountPerBreastCrotch(),
						startingBodyType.getBreastCrotchCapacity(),
						startingBodyType.getBreastCrotchDepth(),
						startingBodyType.getBreastCrotchElasticity(),
						startingBodyType.getBreastCrotchPlasticity(), 
						true));
		}
		
		feralStringBuilder.append("<p><i>"
				+ "[npc.her]下半身的[style.boldTfGeneric(各个部位)]都已经与"+(feral?"兽态"+feralRaceName:"常规"+this.getRace().getName(false))+"无异，也就是说[npc.she]现在拥有");
		
		List<String> partsList = new ArrayList<>();
		// Tail:
		if(body.getTail().getType()==TailType.NONE) {
			partsList.add(" [style.boldTfGeneric(no tail)]");
		} else {
			partsList.add(" [style.boldTfGeneric("
					+(body.getTail().getTailCount()==1
						?(body.getTail().getType()==TailType.HARPY?"a plume of ":UtilText.generateSingularDeterminer(body.getTail().getName(null))+" ")
						:Util.intToString(body.getTail().getTailCount())+" "+(body.getTail().getType()==TailType.HARPY?" plumes of ":""))
					+body.getTail().getName(null)+")]");
		}
		// Ass:
		partsList.add(feralRaceNameWithDeterminer+"'s ass");
		// Crotch boobs:
		if((Main.getProperties().getUddersLevel()==1 && legConfiguration!=LegConfiguration.BIPEDAL)
				|| (Main.getProperties().getUddersLevel()==2 && body.getRaceStage()==RaceStage.GREATER)
				|| body.isFeral()) {
			if(body.getBreastCrotch().getType()!=BreastType.NONE && !legConfiguration.isBipedalPositionedCrotchBoobs()) {
				partsList.add("animal-like "+body.getBreastCrotch().getName(null));
			}
		}
		feralStringBuilder.append(Util.stringsToStringList(partsList, false)+"。");
		
		Penis penis = body.getPenis();
		if(penis.getType()!=PenisType.NONE) {
			if(feral) {
				feralStringBuilder.append("[npc.Her]的阴茎变得跟兽态"+feralRaceName+"十分类似，不仅会产出类似动物的味道浓重的精液，而且格外地长，有"+Units.size(penis.getRawLengthValue())+"。");
			} else {
				feralStringBuilder.append("[npc.Her]的阴茎变得跟常规的"+this.getRace().getName(false)+"十分类似，有"+Units.size(penis.getRawLengthValue())+"长。");
			}
		}
		
		Vagina vagina = body.getVagina();
		if(vagina.getType()!=VaginaType.NONE) {
			if(feral) {
				feralStringBuilder.append("[npc.Her]气味浓厚的小穴已经与"+feralRaceNameWithDeterminer
						+"的并无二致，并且变得"+vagina.getOrificeVagina().getCapacity().getDescriptor()+"，以适应相应的兽态阴茎。");
			} else {
				feralStringBuilder.append("[npc.Her]"+vagina.getOrificeVagina().getCapacity().getDescriptor()+"的小穴已经与常规的相差无几。");
			}
		}
		feralStringBuilder.append("</i></p>");
		
		if(feral) {
			feralStringBuilder.append("<p><i>[npc.she][style.colourGood(不再)]因为自己[style.italicsFeral(动物般的生殖器"
					+(legConfiguration.isBipedalPositionedCrotchBoobs()?(body.getBreastCrotch().getShape()==BreastShape.UDDERS?"和腹乳":"和胯乳"):"")
					+ ")]暴露在外而感到[style.colourGood(尴尬)]，因为这很自然！</i></p>");
		}

		if(body.getLeg().getType().hasSpinneret()) {
			feralStringBuilder.append("<p>[npc.Her]的小腹部有着[style.italicsFeral(一套丝囊)]，不仅可以吐出结实的粘性网，[style.italicsSex(还能用来当作性交腔穴)]！</p>");
		} else if(body.getTail().getType().hasSpinneret()) {
			feralStringBuilder.append("<p>[npc.Her]的尾巴上有着[style.italicsFeral(一套丝囊)]，不仅可以吐出结实的粘性网，[style.italicsSex(还能用来当作性交腔穴)]！</p>");
		}
		
		int landSpeed = body.getLeg().getType().getLandSpeedModifier() + legConfiguration.getLandSpeedModifier();
		if(landSpeed>0) {
			feralStringBuilder.append("<p style='text-align:center;'><i>"
											+ "[npc.Her]的下半身不再像曾经双足一般适应在陆地活动，"
												+ "因而在[style.colourEarth(陆地上时)]，[style.colourTerrible([npc.she]移动得比平常更慢)]！"
											+ "<br/>"
											+ "位于陆地时[style.colourTerrible(+"+landSpeed+"%)]移动时间！"
										+ "</i></p>");
		} else if(landSpeed<0) {
			feralStringBuilder.append("<p style='text-align:center;'><i>"
											+ "[npc.Her]的下半身相比曾经双足时可以获得更快的速度，"
												+ "因而在[style.colourEarth(陆地上时)]，[style.colourExcellent([npc.she]移动得比平常更快)]！"
											+ "<br/>"
											+ "位于陆地时[style.colourExcellent("+landSpeed+"%)]移动时间！"
										+ "</i></p>");
		}

		int waterSpeed = body.getLeg().getType().getWaterSpeedModifier() + legConfiguration.getWaterSpeedModifier();
		if(waterSpeed>0) {
			feralStringBuilder.append("<p style='text-align:center;'><i>"
											+ "[npc.SheIsFull]相比曾经双足时更难在水中移动了，"
												+ "因而在[style.colourWater(在水中)][style.colourTerrible([npc.she]移动得比平常更慢)]！"
											+ "<br/>"
											+ "位于水中时[style.colourTerrible(+"+landSpeed+"%)]移动时间！"
										+ "</i></p>");
		} else if(waterSpeed<0) {
			feralStringBuilder.append("<p style='text-align:center;'><i>"
											+ "[npc.SheIsFull]相比曾经双足时更适合在水中移动了，"
												+ "因而在[style.colourWater(在水中)][style.colourExcellent([npc.she]移动得比平常更快)]！"
											+ "<br/>"
											+ "位于水中时[style.colourExcellent("+landSpeed+"%)]移动时间！"
										+ "</i></p>");
		}
		
		if(legConfiguration==LegConfiguration.TAIL) {
			feralStringBuilder.append("<p style='text-align:center;'><i>"
											+ "在[style.colourTan(陆地上时)]，[npc.namePos]的下半身会[style.colourTfGeneric(自动转化为双足配置)]！"
											+ "该形态下[npc.she]会承受一些负面的副作用。"
											+ "<br/>"
											+ "相反在[style.colourBlueLight(水中)]，[npc.namePos]的下半身会[style.colourTfGeneric(自动转化为鱼尾形态)]！"
											+ "该状态下[npc.she]会获得一些正面的副作用。"
										+ "</i></p>");
		}
		
		return feralStringBuilder.toString();
	}
	
	// Setting parts is applied directly through body to circumvent transformation blocks
	/**
	 * @param applyFullEffects Pass in true if you want the additional transformations to include attribute changes (such as penis resizing, vagina capacity resetting, etc.).
	 **/
	private void applyExtraLegConfigurationTransformations(Body body, LegConfiguration legConfiguration, boolean largeGenitals, boolean applyFullEffects) {
		AbstractRacialBody startingBodyType = RacialBody.valueOfRace(this.getRace());
		
		boolean demon = body.getRace()==Race.DEMON;
		
		if(legConfiguration.getFeralParts().contains(Ass.class)) { // Ass (includes Anus):
			if(!applyFullEffects) {
				body.getAss().setType(null, (demon
						?AssType.DEMON_COMMON
						:startingBodyType.getAssType()));
			} else {
				boolean virgin = body.getAss().getAnus().getOrificeAnus().isVirgin();
				body.setAss(
						new Ass(
							(demon
								?AssType.DEMON_COMMON
								:startingBodyType.getAssType()),
							(body.isFeminine() ? startingBodyType.getFemaleAssSize() : startingBodyType.getMaleAssSize()),
							(body.isFeminine() ? startingBodyType.getFemaleHipSize() : startingBodyType.getMaleHipSize()),
							startingBodyType.getAnusWetness(),
							(float) (startingBodyType.getAnusCapacity()*(largeGenitals?2.5:1)),
							startingBodyType.getAnusDepth()+(largeGenitals?2:0),
							startingBodyType.getAnusElasticity(),
							startingBodyType.getAnusPlasticity(),
							true));
				body.getAss().getAnus().getOrificeAnus().setVirgin(virgin);
			}
		}
		if(legConfiguration.getFeralParts().contains(BreastCrotch.class)) { // Crotch-boobs:
			AbstractBreastType crotchBoobType = BreastType.NONE;
			if(body.isFeminine()) {
				if(demon) {
					crotchBoobType = BreastType.DEMON_COMMON;
				} else {
					crotchBoobType = startingBodyType.getBreastCrotchType();
				}
			}
			
			if(!applyFullEffects) {
				body.getBreastCrotch().setType(null, crotchBoobType);
				
			} else {
				body.setBreastCrotch(
						new BreastCrotch(
							crotchBoobType,
							Util.randomItemFrom(startingBodyType.getBreastCrotchShapes()),
							startingBodyType.getBreastCrotchSize(),
							startingBodyType.getBreastCrotchLactationRate(),
							startingBodyType.getBreastCrotchCount(),
							startingBodyType.getBreastCrotchNippleSize(),
							startingBodyType.getBreastCrotchNippleShape(),
							startingBodyType.getBreastCrotchAreolaeSize(),
							startingBodyType.getBreastCrotchAreolaeShape(),
							startingBodyType.getNippleCountPerBreastCrotch(),
							startingBodyType.getBreastCrotchCapacity(),
							startingBodyType.getBreastCrotchDepth(),
							startingBodyType.getBreastCrotchElasticity(),
							startingBodyType.getBreastCrotchPlasticity(), 
							true));
			}
		}
		if(legConfiguration.getFeralParts().contains(Tail.class)) { // Tail:
			if(body.getLeg().getType().getRace()==Race.DEMON) {
				body.setTail(new Tail(TailType.DEMON_HORSE));
			} else {
				if(body.getTail().getType().getRace()!=startingBodyType.getTailType().get(0).getRace()) {
					body.setTail(new Tail(startingBodyType.getTailType().get(0)));
				}
			}
		}
		if(legConfiguration.getFeralParts().contains(Tentacle.class)) { // Tentacle:
			body.setTentacle(new Tentacle(startingBodyType.getTentacleType()));
		}
		if(legConfiguration.getFeralParts().contains(Penis.class)) { // Penis (includes Testicle):
			if(!applyFullEffects) {
				if(body.getPenis().getType()!=PenisType.NONE && body.getPenis().getType()!=PenisType.DILDO) {
					body.getPenis().setType(null,
								(demon
									?PenisType.DEMON_COMMON
									:startingBodyType.getPenisType()));
				}
				
			} else {
				boolean virgin = body.getPenis().isVirgin();
				body.setPenis(body.getPenis().getType()!=PenisType.NONE && body.getPenis().getType()!=PenisType.DILDO
						? new Penis(
							(demon
								?PenisType.DEMON_COMMON
								:startingBodyType.getPenisType()),
							(int) (startingBodyType.getPenisSize()*(largeGenitals?2.5:1)),
							true,
							startingBodyType.getPenisGirth()+(largeGenitals?1:0),
							startingBodyType.getTesticleSize()+(largeGenitals?1:0),
							startingBodyType.getCumProduction()*(largeGenitals?10:1),
							startingBodyType.getTesticleQuantity())
						: new Penis(PenisType.NONE, 0, false, 0, 0, 0, 2));
//				body.getPenis().getTesticle().getCum().addFluidModifier(null, FluidModifier.MUSKY);
				body.getPenis().setVirgin(virgin);
			}
		}
		if(legConfiguration.getFeralParts().contains(Vagina.class)) { // Vagina (includes Clitoris):
			if(!applyFullEffects) {
				if(body.getVagina().getType()!=VaginaType.NONE && body.getVagina().getType()!=VaginaType.ONAHOLE) {
					body.getVagina().setType(null,
								(demon
									?VaginaType.DEMON_COMMON
									:startingBodyType.getVaginaType()));
				}
				
			} else {
				boolean virgin = body.getVagina().getType()!=VaginaType.NONE?body.getVagina().getOrificeVagina().isVirgin():true;
				boolean hymen = body.getVagina().getType()!=VaginaType.NONE?body.getVagina().getOrificeVagina().hasHymen():true;
				body.setVagina(
						body.getVagina().getType()!=VaginaType.NONE && body.getVagina().getType()!=VaginaType.ONAHOLE
							? new Vagina(
									(demon
										?VaginaType.DEMON_COMMON
										:startingBodyType.getVaginaType()),
									LabiaSize.getRandomLabiaSize().getValue(),
									startingBodyType.getClitSize(),
									startingBodyType.getClitGirth(),
									startingBodyType.getVaginaWetness(),
									(float) (startingBodyType.getVaginaCapacity()*(largeGenitals?2.5:1)),
									startingBodyType.getVaginaDepth()+(largeGenitals?2:0),
									startingBodyType.getVaginaElasticity(),
									startingBodyType.getVaginaPlasticity(),
									true)
							: new Vagina(VaginaType.NONE, 0, 0, 0, 0, 0, 2, 3, 3, true));
//				body.getVagina().getGirlcum().addFluidModifier(null, FluidModifier.MUSKY);
				body.getVagina().getOrificeVagina().setVirgin(virgin);
				body.getVagina().getOrificeVagina().setHymen(null, hymen);
			}
		}
 	}
	
	public List<LegConfiguration> getAllowedLegConfigurations() {
		return allowedLegConfigurations;
	}

	/**
	 * @param legConfiguration The configuration to check transformation availability of.
	 * @return True if this configuration is allowed for this LegType.
	 */
	public boolean isLegConfigurationAvailable(LegConfiguration legConfiguration) {
		return allowedLegConfigurations.contains(legConfiguration);
	}
	
	public boolean hasSpinneret() {
		return spinneret;
	}

	/**
	 * @return Usually TentacleType.NONE. If it returns an actual TentacleType, then that means this LegType has tentacles in place of legs.
	 */
	public AbstractTentacleType getTentacleType() {
		return tentacleType;
	}
	
	public boolean isLegsReplacedByTentacles() {
		return getTentacleType()!=TentacleType.NONE;
	}
	
	public int getTentacleCount() {
		return tentacleCount;
	}
	
	/**
	 * @return By default, LegTypes return a modification of 0, but if a LegType requires a modifier, then this can be overridden and its effects will be handled alongside LegConfiguration's getLandSpeedModifier().
	 */
	public int getLandSpeedModifier() {
		return 0;
	}

	/**
	 * @return By default, LegTypes return a modification of 0, but if a LegType requires a modifier, then this can be overridden and its effects will be handled alongside LegConfiguration's getWaterSpeedModifier().
	 */
	public int getWaterSpeedModifier() {
		return 0;
	}
	
	@Override
	public BodyPartClothingBlock getBodyPartClothingBlock() {
		if(this.getFootType()==FootType.HOOFS) {
			return new BodyPartClothingBlock(
					Util.newArrayListOfValues(
							InventorySlot.FOOT),
					this.getRace(),
					"由于[npc.namePos]蹄子的形状，只有与蹄子兼容的服装能够装备至该栏位。",
					Util.newArrayListOfValues(ItemTag.FITS_HOOFS, ItemTag.FITS_HOOFS_EXCLUSIVE));
		}
		if(this.getFootType()==FootType.TALONS) {
			return new BodyPartClothingBlock(
					Util.newArrayListOfValues(
							InventorySlot.FOOT),
					this.getRace(),
					"由于[npc.namePos]利爪的形状，只有与利爪兼容的服装能够装备至该栏位。",
					Util.newArrayListOfValues(ItemTag.FITS_TALONS, ItemTag.FITS_TALONS_EXCLUSIVE));
		}
		return null;
	}
}
