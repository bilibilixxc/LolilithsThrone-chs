package com.lilithsthrone.game.character.body.abstractTypes;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.threading.DocBuilders;
import com.lilithsthrone.utils.Util;
import org.w3c.dom.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.3.8.9
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractTentacleType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;
	
	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;

	private String transformationName;
	
	private int defaultGirth;
	private float defaultLengthAsPercentageOfHeight;

	private String determiner;
	private String determinerPlural;
	
	private String name;
	private String namePlural;
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;

	private String tipName;
	private String tipNamePlural;
	private List<String> tipDescriptorsMasculine;
	private List<String> tipDescriptorsFeminine;
	
	private String tentacleTransformationDescription;
	private String tentacleBodyDescription;

	private List<BodyPartTag> tags;
	
	/**
	 * @param coveringType What covers this tentacle type (i.e skin/fur/feather type).
	 * @param race What race has this tentacle type.
	 * @param defaultGirth The girth which this TentacleType spawns with.
	 * @param lengthAsPercentageOfHeight The percentage, as a float from 0->1, of this tentacle's length as a proportion of the owner's body height.
	 * @param transformationName The name that should be displayed when offering this tentacle type as a transformation. Should be something like "demonic spaded" or "demonic hair-tipped".
	 * @param determiner The singular determiner which should be used for this tentacle type. Should normally be left blank unless the tentacle is of a special type (such as harpy 'tentacle feathers' needing 'a plume of' as the determiner).
	 * @param determinerPlural The plural determiner which should be used for this tentacle type, appended after a number. Should normally be left blank unless the tentacle is of a special type (such as harpy 'tentacle feathers' needing 'plumes of' as the determiner).
	 * @param name The singular name of the tentacle. This will usually just be "tentacle".
	 * @param namePlural The plural name of the tentacle. This will usually just be "tentacles".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this tentacle type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this tentacle type.
	 * @param tipName The singular name of the tip of this tentacle. This will usually just be "tip".
	 * @param tipNamePlural The plural name of tip of this tentacle. This will usually just be "tips".
	 * @param tipDescriptorsMasculine The descriptors that can be used to describe a masculine form of this tentacle type's tip. Will usually be blank.
	 * @param tipDescriptorsFeminine The descriptors that can be used to describe a feminine form of this tentacle type's tip. Will usually be blank.
	 * @param tentacleTransformationDescription A paragraph describing a character's tentacles transforming into this tentacle type. Parsing assumes that the character already has this tentacle type and associated skin covering.
	 * @param tentacleBodyDescription A sentence or two to describe this tentacle type, as seen in the character view screen. It should follow the same format as all of the other entries in the TentacleType class.
	 * @param tags The tags which define this tentacle's properties.
	 */
	public AbstractTentacleType(
			AbstractBodyCoveringType coveringType,
			AbstractRace race,
			PenetrationGirth defaultGirth,
			float defaultLengthAsPercentageOfHeight,
			String transformationName,
			String determiner,
			String determinerPlural,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String tipName,
			String tipNamePlural,
			List<String> tipDescriptorsMasculine,
			List<String> tipDescriptorsFeminine,
			String tentacleTransformationDescription,
			String tentacleBodyDescription,
			List<BodyPartTag> tags) {
		
		this.coveringType = coveringType;
		this.race = race;

		this.defaultGirth = defaultGirth.getValue();
		this.defaultLengthAsPercentageOfHeight = defaultLengthAsPercentageOfHeight;
		
		this.transformationName = transformationName;
		
		this.determiner = determiner;
		this.determinerPlural = determinerPlural;
		
		this.name = name;
		this.namePlural = namePlural;
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.tipName = tipName;
		this.tipNamePlural = tipNamePlural;
		this.tipDescriptorsMasculine = tipDescriptorsMasculine;
		this.tipDescriptorsFeminine = tipDescriptorsFeminine;
		
		this.tentacleTransformationDescription = tentacleTransformationDescription;
		this.tentacleBodyDescription = tentacleBodyDescription;
		
		this.tags = tags;
	}

	public AbstractTentacleType(File XMLFile, String author, boolean mod) {
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
				
				this.defaultGirth = Integer.valueOf(coreElement.getMandatoryFirstOf("defaultGirth").getTextContent());
				this.defaultLengthAsPercentageOfHeight = Float.valueOf(coreElement.getMandatoryFirstOf("defaultLengthAsPercentageOfHeight").getTextContent());

				this.tags = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("tags").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("tags").getAllOf("tag")) {
						tags.add(BodyPartTag.getBodyPartTagFromId(e.getTextContent()));
					}
				}
				if(tags.isEmpty()) {
					tags.add(BodyPartTag.TAIL_TYPE_GENERIC);
					tags.add(BodyPartTag.TAIL_TAPERING_NONE);
				}
				
				this.determiner = coreElement.getMandatoryFirstOf("determiner").getTextContent();
				this.determinerPlural = coreElement.getMandatoryFirstOf("determinerPlural").getTextContent();
				
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
				
				this.tipName = coreElement.getMandatoryFirstOf("tipName").getTextContent();
				this.tipNamePlural = coreElement.getMandatoryFirstOf("tipNamePlural").getTextContent();
				this.tipDescriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("tipDescriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("tipDescriptorsMasculine").getAllOf("descriptor")) {
						tipDescriptorsMasculine.add(e.getTextContent());
					}
				}
				this.tipDescriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("tipDescriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("tipDescriptorsFeminine").getAllOf("descriptor")) {
						tipDescriptorsFeminine.add(e.getTextContent());
					}
				}
				
				this.tentacleTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.tentacleBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractTentacleType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}

	public int getDefaultGirth() {
		return defaultGirth;
	}

	public float getDefaultLengthAsPercentageOfHeight() {
		return defaultLengthAsPercentageOfHeight;
	}
	
	@Override
	public List<BodyPartTag> getTags() {
		return tags;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc==null) {
			return "";
		}
		if(gc.getTentacleCount()==1) {
			return Util.intToString(gc.getTentacleCount())+"根";
		}
		return Util.intToString(gc.getTentacleCount())+"根";
	}
	
	@Override
	public String getTransformationNameOverride() {
		return transformationName;
	}
	
	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		return false;
	}
	
	@Override
	public String getName(GameCharacter gc){
		if(isDefaultPlural(gc) || (gc!=null && gc.getTentacleCount()!=1)) {
			return getNamePlural(gc);
		} else {
			return getNameSingular(gc);
		}
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return name;
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return namePlural;
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(descriptorsFeminine);
		} else {
			return Util.randomItemFrom(descriptorsMasculine);
		}
	}
	
	public String getTentacleTipNameSingular(GameCharacter gc) {
		return tipName;
	}
	
	public String getTentacleTipNamePlural(GameCharacter gc) {
		return tipNamePlural;
	}
	
	public String getTentacleTipDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(tipDescriptorsFeminine);
		} else {
			return Util.randomItemFrom(tipDescriptorsMasculine);
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

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, tentacleBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, tentacleTransformationDescription);
	}
	
	/**
	 * @return A description of this tentacle's girth, based on the TYPE tag and the owner's girth.
	 */
	public String getGirthDescription(GameCharacter owner) {
		StringBuilder sb = new StringBuilder();
		
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FUR)) {
			if(owner.getTentacleCount()>1) {
				sb.append(UtilText.parse(owner, "[npc.Her]的[npc.tentacles]"));
			} else {
				sb.append(UtilText.parse(owner, "[npc.Her]的[npc.tentacle]"));
			}
			switch(owner.getTentacleGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, "非常纤细，相比于身体其他部分显得没什么绒毛。"));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, "比较修长，相比于身体其他部分绒毛不多。"));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, "比较细，相比于身体其他部分绒毛稍少。"));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, "宽度合适，和身体其他部分差不多蓬松。"));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, "比较粗大，相比于身体其他部分更加松软。"));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, "十分粗壮，相比于身体其他部分更加松软。"));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, "格外粗壮，相比于身体其他部分更加松软。"));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, "极其粗壮，相比于身体其他部分更加松软。"));
					break;
			}
			
		} else {
			if(owner.getTentacleCount()>1) {
				sb.append(UtilText.parse(owner, "[npc.Her]的[npc.tentacles]"));
			} else {
				sb.append(UtilText.parse(owner, "[npc.Her]的[npc.tentacle]"));
			}
			switch(owner.getTentacleGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, "非常纤细，考虑到身体的其他部分的话。"));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, "比较修长，考虑到身体的其他部分的话。"));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, "比较细，考虑到身体的其他部分的话。"));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, "粗细适中，考虑到身体的其他部分的话。"));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, "比较粗大，考虑到身体的其他部分的话。"));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, "十分粗壮，考虑到身体的其他部分的话。"));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, "格外粗壮，考虑到身体的其他部分的话。"));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, "极其粗壮，考虑到身体的其他部分的话。"));
					break;
			}	
		}
		return sb.toString();
	}

	public String getGirthDescriptor(GameCharacter owner) {
		return getGirthDescriptor(owner.getTentacleGirth());
	}
	
	public String getGirthDescriptor(PenetrationGirth girth) {
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FUR)) {
			switch(girth) {
				case ZERO_THIN:
					return "纤细";
				case ONE_SLENDER:
					return "修长";
				case TWO_NARROW:
					return "细窄";
				case THREE_AVERAGE:
					return "毛茸茸";
				case FOUR_GIRTHY:
					return "松软";
				case FIVE_THICK:
					return "很松软";
				case SIX_CHUBBY:
					return "极其松软";
				case SEVEN_FAT:
					return "松软到离谱";
			}
			
		} else {
			switch(girth) {
				case ZERO_THIN:
					return "纤细";
				case ONE_SLENDER:
					return "修长";
				case TWO_NARROW:
					return "细窄";
				case THREE_AVERAGE:
					return "适中";
				case FOUR_GIRTHY:
					return "较粗";
				case FIVE_THICK:
					return "粗壮";
				case SIX_CHUBBY:
					return "极粗";
				case SEVEN_FAT:
					return "粗到离谱";
			}
		}
		
		return girth.getName();
	}
	
	public String getGirthTransformationDescription(GameCharacter owner, boolean positive) {
		String tentacleText = "[npc.a_tentacleGirth]的[npc.tentacle]";
		if(owner.getTentacleCount()>1) {
			tentacleText = "[npc.tentacleGirth]的[npc.tentacles]";
		}
		
		if(positive) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.her]的[npc.tentacles]根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
						+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
						+(owner.getTentacleCount()>1
								?"[npc.tentacles]突然[style.boldGrow(变粗了)]。"
								:"[npc.tentacle]突然[style.boldGrow(变粗了)]。")
						+ "<br/>"
						+ "[npc.She]现在拥有了[style.boldSex("+tentacleText+")]！"
					+ "</p>");
				
		} else {
			return UtilText.parse(owner,
					"<p>"
							+ "[npc.Name]感觉到[npc.her]的[npc.tentacles]根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
							+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
							+(owner.getTentacleCount()>1
									?"[npc.tentacles]突然[style.boldGrow(变细了)]。"
									:"[npc.tentacle]突然[style.boldGrow(变细了)]。")
							+ "<br/>"
						+ "[npc.She]现在拥有了[style.boldSex("+tentacleText+")]！"
					+ "</p>");
		}
	}
	
	public String getLengthTransformationDescription(GameCharacter owner, boolean positive) {
		String heightPercentageDescription = "(长度为[npc.namePos]身高的"+((int)(owner.getTentacleLengthAsPercentageOfHeight()*100))+"%)";
		if(positive) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.her]的[npc.tentacles][npc.tentacleTip]突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
						+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的[npc.tentacles]突然[style.boldGrow(伸长了)]。"
						+ "<br/>"
						+ "[npc.She]现在拥有了[style.boldTfGeneric([npc.tentacleLength]的[npc.tentacles])]"+heightPercentageDescription+"！"
					+ "</p>");
				
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.her]的[npc.tentacles][npc.tentacleTip]突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
						+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的[npc.tentacles]突然[style.boldShrink(缩短了)]。"
						+ "<br/>"
						+ "[npc.She]现在拥有了[style.boldTfGeneric([npc.tentacleLength]的[npc.tentacles])]"+heightPercentageDescription+"！"
					+ "</p>");
		}
	}
	
	public boolean isPrehensile() {
		return tags.contains(BodyPartTag.TAIL_PREHENSILE);
	}
	
	public boolean isSuitableForPenetration() {
		return this.isPrehensile() && tags.contains(BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION);
	}
	
	public boolean isSuitableForSleepHugging() {
		return tags.contains(BodyPartTag.TAIL_SLEEP_HUGGING);
	}

	@Override
	public TFModifier getTFModifier() {
		return this==TentacleType.NONE ? TFModifier.REMOVAL : getTFTypeModifier(TentacleType.getTentacleTypes(race));
	}
}
