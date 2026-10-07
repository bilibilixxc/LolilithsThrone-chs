package com.lilithsthrone.game.character.body.abstractTypes;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.TailType;
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
 * @since 0.3.7
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractTailType implements BodyPartTypeInterface {

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
	
	private String tailTransformationDescription;
	private String tailBodyDescription;

	private List<BodyPartTag> tags;
	
	private boolean spinneret;
	
	/**
	 * @param coveringType What covers this tail type (i.e skin/fur/feather type).
	 * @param race What race has this tail type.
	 * @param defaultGirth The girth which this TailType spawns with.
	 * @param defaultLengthAsPercentageOfHeight The percentage, as a float from 0->1, of this tail's length as a proportion of the owner's body height.
	 * @param transformationName The name that should be displayed when offering this tail type as a transformation. Should be something like "demonic spaded" or "demonic hair-tipped".
	 * @param determiner The singular determiner which should be used for this tail type. Should normally be left blank unless the tail is of a special type (such as harpy 'tail feathers' needing 'a plume of' as the determiner).
	 * @param determinerPlural The plural determiner which should be used for this tail type, appended after a number. Should normally be left blank unless the tail is of a special type (such as harpy 'tail feathers' needing 'plumes of' as the determiner).
	 * @param name The singular name of the tail. This will usually just be "tail".
	 * @param namePlural The plural name of the tail. This will usually just be "tails".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this tail type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this tail type.
	 * @param tipName The singular name of the tip of this tail. This will usually just be "tip".
	 * @param tipNamePlural The plural name of tip of this tail. This will usually just be "tips".
	 * @param tipDescriptorsMasculine The descriptors that can be used to describe a masculine form of this tail type's tip. Will usually be blank.
	 * @param tipDescriptorsFeminine The descriptors that can be used to describe a feminine form of this tail type's tip. Will usually be blank.
	 * @param tailTransformationDescription A paragraph describing a character's tails transforming into this tail type. Parsing assumes that the character already has this tail type and associated skin covering.
	 * @param tailBodyDescription A sentence or two to describe this tail type, as seen in the character view screen. It should follow the same format as all of the other entries in the TailType class.
	 * @param tags The tags which define this tail's properties.
	 * @param spinneret true if this tail type has a spinneret.
	 */
	public AbstractTailType(
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
			String tailTransformationDescription,
			String tailBodyDescription,
			List<BodyPartTag> tags,
			boolean spinneret) {
		
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
		
		this.tailTransformationDescription = tailTransformationDescription;
		this.tailBodyDescription = tailBodyDescription;
		
		this.tags = tags;
		
		this.spinneret = spinneret;
	}
	
	public AbstractTailType(File XMLFile, String author, boolean mod) {
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
				
				this.spinneret = Boolean.valueOf(coreElement.getMandatoryFirstOf("spinneret").getTextContent());

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
				
				this.tailTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.tailBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractTailType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
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
		if(gc.getTailCount()==1) {
			return Util.intToString(gc.getTailCount())+"根";
		}
		return Util.intToString(gc.getTailCount())+"根";
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
		if(isDefaultPlural(gc) || (gc!=null && gc.getTailCount()!=1)) {
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
	
	public String getTailTipNameSingular(GameCharacter gc) {
		return tipName;
	}
	
	public String getTailTipNamePlural(GameCharacter gc) {
		return tipNamePlural;
	}
	
	public String getTailTipDescriptor(GameCharacter gc) {
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

	//@keldon helppp this needs to also return LexiRace so i can keep Manticores in LexiRace.java
	@Override
	public AbstractRace getRace() {
		return race;
	}

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, tailBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, tailTransformationDescription);
	}
	
	/**
	 * @return A description of this tail's girth, based on the TYPE tag and the owner's girth.
	 */
	public String getGirthDescription(GameCharacter owner) {
		StringBuilder sb = new StringBuilder();
		
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_SKIN) || this.getTags().contains(BodyPartTag.TAIL_TYPE_SCALES)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, "[npc.Her]的尾巴"));
			} else {
				sb.append(UtilText.parse(owner, "[npc.Her]的尾巴"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, "<colourStart>非常纤细<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, "<colourStart>十分修长<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, "<colourStart>比较细<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, "<colourStart>粗细适中<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, "<colourStart>比较粗大<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, "<colourStart>十分粗壮<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, "<colourStart>格外粗壮<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, "<colourStart>极其粗壮<colourEnd>，考虑到身体的其他部分的话。"));
					break;
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FUR)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, "[npc.Her]的尾巴"));
			} else {
				sb.append(UtilText.parse(owner, "[npc.Her]的尾巴"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, "<colourStart>非常纤细也没什么绒毛<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, "<colourStart>比较修长，绒毛也不多<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, "<colourStart>比较细，相比于身体其他部分绒毛稍少。<colourEnd>"));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, "<colourStart>粗细合适，和身体其他部分绒毛差不多浓密。<colourEnd>"));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, "<colourStart>相当蓬松，相比于身体其他部分很多毛。<colourEnd>"));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, "<colourStart>十分粗壮，相比于身体其他部分更松软。<colourEnd>"));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, "<colourStart>难以置信的蓬松，相比于身体其他部分更松软。<colourEnd>"));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, "<colourStart>极其蓬松，相比于身体其他部分更松软。<colourEnd>"));
					break;
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_TUFT)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, "[npc.Her]簇毛的尾巴"));
			} else {
				sb.append(UtilText.parse(owner, "[npc.Her]簇毛的尾巴"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, "<colourStart>非常细小，相比于身体其他部分绒毛不多。<colourEnd>"));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, "<colourStart>相当小，相比于身体其他部分绒毛不多。<colourEnd>"));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, "<colourStart>有点小，相比于身体其他部分绒毛不多。<colourEnd>"));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, "<colourStart>大小适中，和身体其他部分差不多蓬松。<colourEnd>"));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, "<colourStart>相当蓬松，相比于身体其他部分很多毛。<colourEnd>"));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, "<colourStart>十分蓬松，相比于身体其他部分非常蓬松。<colourEnd>"));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, "<colourStart>难以置信的蓬松，相比于身体其他部分更松软。<colourEnd>"));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, "<colourStart>极其蓬松，相比于身体其他部分更松软。<colourEnd>"));
					break;
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_HAIR)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, "[npc.Her]的马尾巴"));
			} else {
				sb.append(UtilText.parse(owner, "[npc.Her]的马尾巴"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, "与[npc.her]身体的其他部分相比，<colourStart>显得太小了。<colourEnd>"));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, "与[npc.her]身体的其他部分相比，<colourStart>显得很小。<colourEnd>"));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, "与[npc.her]身体的其他部分相比，<colourStart>就显得有点小了。<colourEnd>"));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, "<colourStart>大小适中<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, "<colourStart>相当丰满<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, "<colourStart>非常丰满<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, "<colourStart>难以置信的丰满<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, "<colourStart>超级丰满<colourEnd>，考虑到身体的其他部分的话。"));
					break;
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FEATHER)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, "[npc.Her]的翎毛"));
			} else {
				sb.append(UtilText.parse(owner, "[npc.Her]的翎毛"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, "<colourStart>非常小而且缺少体积感<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, "<colourStart>很小而且有些缺少体积感<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, "<colourStart>有些细而且缺少体积感<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, "<colourStart>尺寸和大小都适中<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, "<colourStart>相当大而且很松<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, "<colourStart>非常大而且很松<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, "<colourStart>难以置信的丰满<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, "<colourStart>超级丰满<colourEnd>，考虑到身体的其他部分的话。"));
					break;
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_GENERIC)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, "[npc.Her]的尾巴"));
			} else {
				sb.append(UtilText.parse(owner, "[npc.Her]的尾巴"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, "<colourStart>非常纤细<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, "<colourStart>有点纤细<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, " <colourStart>有点细<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, "<colourStart>粗细适中<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, "<colourStart>比较大<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, "<colourStart>非常大<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, "<colourStart>格外粗大<colourEnd>，考虑到身体的其他部分的话。"));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, "<colourStart>超级粗大<colourEnd>，考虑到身体的其他部分的话。"));
					break;
			}
		}
		
		String returnString = sb.toString();
		String colourStartTag = "<span style='color:"+owner.getTailGirth().getColour().toWebHexString()+";'>";
		String colourEndTag = "</span>";
		returnString = returnString.replaceAll("<colourStart>", colourStartTag);
		returnString = returnString.replaceAll("<colourEnd>", colourEndTag);
		
		return returnString;
	}

	public String getGirthDescriptor(GameCharacter owner) {
		return getGirthDescriptor(owner.getTailGirth());
	}

	public String getGirthDescriptor(Body body) {
		return getGirthDescriptor(body.getTail().getGirth());
	}
	
	public String getGirthDescriptor(PenetrationGirth girth) {
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_SKIN)
				|| this.getTags().contains(BodyPartTag.TAIL_TYPE_SCALES)) {
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
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_TUFT)) {
			switch(girth) {
				case ZERO_THIN:
					return "娇小";
				case ONE_SLENDER:
					return "较小";
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
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_HAIR)) {
			switch(girth) {
				case ZERO_THIN:
					return "纤细";
				case ONE_SLENDER:
					return "较小";
				case TWO_NARROW:
					return "细窄";
				case THREE_AVERAGE:
					return "适中";
				case FOUR_GIRTHY:
					return "蓬松";
				case FIVE_THICK:
					return "格外蓬松";
				case SIX_CHUBBY:
					return "异常蓬松";
				case SEVEN_FAT:
					return "蓬到离谱";
			}
		}

		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FEATHER)) {
			switch(girth) {
				case ZERO_THIN:
					return "纤细";
				case ONE_SLENDER:
					return "较小";
				case TWO_NARROW:
					return "细窄";
				case THREE_AVERAGE:
					return "适中";
				case FOUR_GIRTHY:
					return "庞大";
				case FIVE_THICK:
					return "格外庞大";
				case SIX_CHUBBY:
					return "异常庞大";
				case SEVEN_FAT:
					return "大到离谱";
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_GENERIC)) {
			switch(girth) {
				case ZERO_THIN:
					return "娇小";
				case ONE_SLENDER:
					return "较小";
				case TWO_NARROW:
					return "细窄";
				case THREE_AVERAGE:
					return "适中";
				case FOUR_GIRTHY:
					return "大";
				case FIVE_THICK:
					return "庞大";
				case SIX_CHUBBY:
					return "巨大";
				case SEVEN_FAT:
					return "大得离谱";
			}
		}
		
		return girth.getName();
	}
	
	public String getGirthTransformationDescription(GameCharacter owner, boolean positive) {
		String tailText = "[npc.a_tailGirth][npc.tail]";
		if(owner.getTailCount()>1) {
			tailText = "[npc.tailGirth][npc.tails]";
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_SKIN)
				|| this.getTags().contains(BodyPartTag.TAIL_TYPE_SCALES)
				|| this.getTags().contains(BodyPartTag.TAIL_TYPE_FUR)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
							+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
							+(owner.getTailCount()>1
									?"[npc.tails]突然[style.boldGrow(变粗了)]。"
									:"[npc.tail]突然[style.boldGrow(变粗了)]。")
							+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric("+tailText+")]了！"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
								+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
								+(owner.getTailCount()>1
										?"[npc.tails]突然[style.boldGrow(变细了)]。"
										:"[npc.tail]突然[style.boldGrow(变细了)]。")
								+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric("+tailText+")]了！"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_TUFT)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
							+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
							+(owner.getTailCount()>1
									?"[npc.tails]突然[style.boldGrow(变得更加蓬松和粗大了)]。"
									:"[npc.tail]突然[style.boldGrow(变得更加蓬松和粗大了)]。")
							+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric("+tailText+")]了！"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
								+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
								+(owner.getTailCount()>1
										?"[npc.tails]突然[style.boldGrow(变细了)]。"
										:"[npc.tail]突然[style.boldGrow(变细了)]。")
								+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric("+tailText+")]了！"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_HAIR)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
							+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
							+(owner.getTailCount()>1
									?"[npc.tails]突然[style.boldGrow(膨胀，变得更粗大了)]。"
									:"[npc.tail]突然[style.boldGrow(膨胀，变得更粗大了)]。")
							+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric("+tailText+")]了！"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
								+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
								+(owner.getTailCount()>1
										?"[npc.tails]突然[style.boldGrow(变细，体积也缩小了)]。"
										:"[npc.tail]突然[style.boldGrow(变细，体积也缩小了)]。")
								+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric("+tailText+")]了！"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FEATHER)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
							+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
							+(owner.getTailCount()>1
									?"[npc.tails]突然[style.boldGrow(膨胀，变得更粗大了)]。"
									:"[npc.tail]突然[style.boldGrow(膨胀，变得更粗大了)]。")
							+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric("+tailText+")]了！"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
								+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
								+(owner.getTailCount()>1
										?"[npc.tails]突然[style.boldGrow(变细，体积也缩小了)]。"
										:"[npc.tail]突然[style.boldGrow(变细，体积也缩小了)]。")
								+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric("+tailText+")]了！"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_GENERIC)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
							+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
							+(owner.getTailCount()>1
									?"[npc.tails]突然[style.boldGrow(变大了)]。"
									:"[npc.tail]突然[style.boldGrow(变大了)]。")
							+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric("+tailText+")]了！"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
								+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
								+(owner.getTailCount()>1
										?"[npc.tail]突然[style.boldShrink(变细了)]。"
										:"[npc.tail]突然[style.boldGrow(变细了)]。")
								+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric("+tailText+")]了！"
						+ "</p>");
			}
		}
		return "";
	}
	
	public String getLengthTransformationDescription(GameCharacter owner, boolean positive) {
		String heightPercentageDescription = " (长度为[npc.namePos]身高的"+((int)(owner.getTailLengthAsPercentageOfHeight()*100))+"%)";
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_SKIN)
				|| this.getTags().contains(BodyPartTag.TAIL_TYPE_SCALES)
				|| this.getTags().contains(BodyPartTag.TAIL_TYPE_FUR)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
							+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
							+(owner.getTailCount()>1
									?"[npc.tails]突然[style.boldGrow(变长了)]。"
									:"[npc.tail]突然[style.boldGrow(变长了)]。")
							+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric([npc.a_tailLength][npc.tail])]"+heightPercentageDescription+"！"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
								+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
								+(owner.getTailCount()>1
										?"[npc.tails]突然[style.boldGrow(变短了)]。"
										:"[npc.tail]突然[style.boldGrow(变短了)]。")
								+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric([npc.a_tailLength][npc.tail])]"+heightPercentageDescription+"！"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_TUFT)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
							+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
							+(owner.getTailCount()>1
									?"[npc.tails]突然[style.boldGrow(变得更加蓬松，且更长了)]。"
									:"[npc.tail]突然[style.boldGrow(变得更加蓬松，且更长了)]。")
							+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric([npc.a_tailLength][npc.tail])]"+heightPercentageDescription+"！"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
								+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
								+(owner.getTailCount()>1
										?"[npc.tails]突然[style.boldGrow(变短了)]。"
										:"[npc.tail]突然[style.boldGrow(变短了)]。")
								+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric([npc.a_tailLength][npc.tail])]"+heightPercentageDescription+"！"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_HAIR)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
							+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
							+(owner.getTailCount()>1
									?"[npc.tails]突然[style.boldGrow(变长了)]。"
									:"[npc.tail]突然[style.boldGrow(变长了)]。")
							+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric([npc.a_tailLength][npc.tail])]"+heightPercentageDescription+"！"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
								+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
								+(owner.getTailCount()>1
										?"[npc.tails]突然[style.boldGrow(变短了)]。"
										:"[npc.tail]突然[style.boldGrow(变短了)]。")
								+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric([npc.a_tailLength][npc.tail])]"+heightPercentageDescription+"！"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FEATHER)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
							+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
							+(owner.getTailCount()>1
									?"[npc.tails]突然[style.boldGrow(变长了)]。"
									:"[npc.tail]突然[style.boldGrow(变长了)]。")
							+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric([npc.a_tailLength][npc.tail])]"+heightPercentageDescription+"！"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
								+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
								+(owner.getTailCount()>1
										?"[npc.tails]突然[style.boldGrow(变短了)]。"
										:"[npc.tail]突然[style.boldGrow(变短了)]。")
								+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric([npc.a_tailLength][npc.tail])]"+heightPercentageDescription+"！"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_GENERIC)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
							+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
							+(owner.getTailCount()>1
									?"[npc.tails]突然[style.boldGrow(变长了)]。"
									:"[npc.tail]突然[style.boldGrow(变长了)]。")
							+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric([npc.a_tailLength][npc.tail])]"+heightPercentageDescription+"！"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name]感觉到[npc.her]的脊柱根部突然出现一股震颤感，变得越来越强烈，不禁发出一声[npc.a_moan+]。"
								+ "在对即将发生的事情没有任何预兆的情况下，[npc.her]的"
								+(owner.getTailCount()>1
										?"[npc.tails]突然[style.boldGrow(变短了)]。"
										:"[npc.tail]突然[style.boldGrow(变短了)]。")
								+ "<br/>"
							+ "[npc.She]现在拥有[style.boldTfGeneric([npc.a_tailLength][npc.tail])]"+heightPercentageDescription+"！"
						+ "</p>");
			}
		}
		return "";
	}
	
	public boolean isPrehensile() {
		return tags.contains(BodyPartTag.TAIL_PREHENSILE);
	}
	
	public boolean isSuitableForSleepHugging() {
		return tags.contains(BodyPartTag.TAIL_SLEEP_HUGGING);
	}
	
	public boolean isSuitableForAttack() {
		return tags.contains(BodyPartTag.TAIL_ATTACK);
	}

	public boolean isOvipositor() {
		return tags.contains(BodyPartTag.TAIL_OVIPOSITOR);
	}


	public boolean isTussy() {
		return tags.contains(BodyPartTag.TAIL_FUCKABLE);
	}

	@Override
	public TFModifier getTFModifier() {
		return this == TailType.NONE ? TFModifier.REMOVAL : getTFTypeModifier(TailType.getTailTypes(race));
	}
	
	public boolean hasSpinneret() {
		return spinneret;
	}
}
