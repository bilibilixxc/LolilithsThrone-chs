package com.lilithsthrone.game.character.body.abstractTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.main.Main;
import org.w3c.dom.Document;
import com.lilithsthrone.threading.DocBuilders;
import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.8.2
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractNippleType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;

	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	List<OrificeModifier> defaultRacialOrificeModifiers;
	
	/**
	 * @param coveringType What covers this nipple.
	 * @param race What race has this ass type.
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this ass type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this ass type.
	 * @param defaultRacialOrificeModifiers Which modifiers this nipple naturally spawns with.
	 */
	public AbstractNippleType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			List<OrificeModifier> defaultRacialOrificeModifiers) {
		
		this.coveringType = coveringType;
		this.race = race;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;

		if(defaultRacialOrificeModifiers==null) {
			this.defaultRacialOrificeModifiers = new ArrayList<>();
		} else {
			this.defaultRacialOrificeModifiers = defaultRacialOrificeModifiers;
		}
	}

	public AbstractNippleType(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = DocBuilders.parseDoc(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in statusEffect files it's <statusEffect>

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.race = Race.getRaceFromId(coreElement.getMandatoryFirstOf("race").getTextContent());
				this.coveringType = BodyCoveringType.getBodyCoveringTypeFromId(coreElement.getMandatoryFirstOf("coveringType").getTextContent());
				
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
				
				this.defaultRacialOrificeModifiers = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("defaultOrificeModifiers").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("defaultOrificeModifiers").getAllOf("modifier")) {
						defaultRacialOrificeModifiers.add(OrificeModifier.valueOf(e.getTextContent()));
					}
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractNippleType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
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
	public String getDeterminer(GameCharacter gc) {
		if(gc.getBreastRows()==1) {
			return "一对";
		} else if(gc.getBreastRows()==2) {
			return "两对";
		} else {
			return "三对";
		}
	}

	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		return true;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		switch(gc.getNippleShape()) {
			case LIPS:
				return  UtilText.returnStringAtRandom("唇化乳头", "乳唇");
			case INVERTED:
			case NORMAL:
				if(gc.hasBreasts()) {
					return UtilText.returnStringAtRandom("乳头", "奶头");
				} else {
					return "乳头";
				}
			case VAGINA:
				return UtilText.returnStringAtRandom("乳头穴", "乳穴");
		}
		return "";
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(gc.getNippleShape()) {
			case LIPS:
				return  UtilText.returnStringAtRandom("唇化乳头", "乳唇");
			case INVERTED:
			case NORMAL:
				if(gc.hasBreasts()) {
					return UtilText.returnStringAtRandom("乳头", "奶头");
				} else {
					return "乳头";
				}
			case VAGINA:
				return UtilText.returnStringAtRandom("乳头穴", "乳穴");
		}
		return "";
	}

	public String getNameCrotchSingular(GameCharacter gc) {
		switch(gc.getNippleCrotchShape()) {
			case LIPS:
				return  UtilText.returnStringAtRandom("唇化乳头", "乳唇");
			case INVERTED:
			case NORMAL:
				if(gc.hasBreasts()) {
					return UtilText.returnStringAtRandom("乳头", "奶头");
				} else {
					return "乳头";
				}
			case VAGINA:
				return UtilText.returnStringAtRandom("乳头穴", "乳穴");
		}
		return "";
	}
	
	public String getNameCrotchPlural(GameCharacter gc) {
		switch(gc.getNippleCrotchShape()) {
			case LIPS:
				return  UtilText.returnStringAtRandom("唇化乳头", "乳唇");
			case INVERTED:
			case NORMAL:
				if(gc.hasBreasts()) {
					return UtilText.returnStringAtRandom("乳头", "奶头");
				} else {
					return "乳头";
				}
			case VAGINA:
				return UtilText.returnStringAtRandom("乳头穴", "乳穴");
		}
		return "";
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(descriptorsFeminine);
		} else {
			return Util.randomItemFrom(descriptorsMasculine);
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

	public List<OrificeModifier> getDefaultRacialOrificeModifiers() {
		return defaultRacialOrificeModifiers;
	}
}
