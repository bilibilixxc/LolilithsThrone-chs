package com.lilithsthrone.game.character.body;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.utils.Util;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @since 0.1.83
 * @version 0.4.9.7
 * @author Innoxia
 */
public class FluidCum implements FluidInterface {

	public AbstractFluidType type;
	public FluidFlavour flavour;
	protected Set<FluidModifier> fluidModifiers;
	protected List<ItemEffect> transformativeEffects;

	public FluidCum(AbstractFluidType type) {
		this.type = type;
		this.flavour = type.getFlavour();
		transformativeEffects = new ArrayList<>();
		
		fluidModifiers = new HashSet<>();
		fluidModifiers.addAll(type.getDefaultFluidModifiers());
	}

	public FluidCum(FluidCum cumToCopy) {
		this.type = cumToCopy.type;
		this.flavour = cumToCopy.flavour;
		this.fluidModifiers = new HashSet<>(cumToCopy.fluidModifiers);
		this.transformativeEffects = new ArrayList<>(cumToCopy.transformativeEffects);
	}
	
	public Element saveAsXML(String rootElementName, Element parentElement, Document doc) {
		Element element = doc.createElement(rootElementName);
		parentElement.appendChild(element);

		XMLUtil.addAttribute(doc, element, "type", FluidType.getIdFromFluidType(this.type));
		XMLUtil.addAttribute(doc, element, "flavour", this.flavour.toString());

		for(FluidModifier fm : this.getFluidModifiers()) {
			Element mod = doc.createElement("mod");
			mod.setTextContent(fm.toString());
			element.appendChild(mod);
		}
		
		return element;
	}

	public static FluidCum loadFromXML(String rootElementName, Element parentElement, Document doc) {
		return loadFromXML(rootElementName, parentElement, doc, null);
	}
	
	/**
	 * 
	 * @param parentElement
	 * @param doc
	 * @param baseType If you pass in a baseType, this method will ignore the saved type in parentElement.
	 */
	public static FluidCum loadFromXML(String rootElementName, Element parentElement, Document doc, AbstractFluidType baseType) {
		Element cum = (Element)parentElement.getElementsByTagName(rootElementName).item(0);

		AbstractFluidType fluidType = FluidType.CUM_HUMAN;
		
		if(baseType!=null) {
			fluidType = baseType;
			
		} else {
			try {
				fluidType = FluidType.getFluidTypeFromId(cum.getAttribute("type"));
			} catch(Exception ex) {
			}
		}
		
		FluidCum fluidCum = new FluidCum(fluidType);
		
		String flavourId = cum.getAttribute("flavour");
		if(flavourId.equalsIgnoreCase("SLIME")) {
			fluidCum.flavour = FluidFlavour.BUBBLEGUM;
		} else {
			fluidCum.flavour = FluidFlavour.valueOf(flavourId);
		}
		

		Element cumModifiers = (Element)cum.getElementsByTagName("cumModifiers").item(0);
		fluidCum.fluidModifiers.clear();
		if(cumModifiers!=null) {
			Set<FluidModifier> fluidModifiers = fluidCum.fluidModifiers;
			Body.handleLoadingOfModifiers(FluidModifier.values(), null, cumModifiers, fluidModifiers);
		} else {
			NodeList mods = cum.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				fluidCum.fluidModifiers.add(FluidModifier.valueOf(e.getTextContent()));
			}
		}
		
		return fluidCum;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof FluidCum){
			if(((FluidCum)o).getType().equals(this.getType())
				&& ((FluidCum)o).getFlavour() == this.getFlavour()
				&& ((FluidCum)o).getFluidModifiers().equals(this.getFluidModifiers())
				&& ((FluidCum)o).getTransformativeEffects().equals(this.getTransformativeEffects())){
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.getType().hashCode();
		result = 31 * result + this.getFlavour().hashCode();
		result = 31 * result + this.getFluidModifiers().hashCode();
		result = 31 * result + this.getTransformativeEffects().hashCode();
		return result;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
	}

	@Override
	public String getName(GameCharacter gc) {
		return this.getType().getName(false, gc);
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return this.getType().getNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		return this.getType().getNamePlural(gc);
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		String modifierDescriptor = "";
		if(!fluidModifiers.isEmpty()) {
			modifierDescriptor = new ArrayList<>(fluidModifiers).get(Util.random.nextInt(fluidModifiers.size())).getName();
		}
		
		return UtilText.returnStringAtRandom(
				(gc.getAttributeValue(Attribute.VIRILITY)>=20?"有力":""),
				"滚烫",
				modifierDescriptor,
				flavour.getRandomFlavourDescriptor(),
				type.getDescriptor(gc));
	}

	@Override
	public AbstractFluidType getType() {
		return type;
	}

	public void setType(AbstractFluidType type) {
		this.type = type;
	}

	public FluidFlavour getFlavour() {
		return flavour;
	}

	public String setFlavour(GameCharacter owner, FluidFlavour flavour) {
		if(!isCharacterInitialised(owner)) {
			this.flavour = flavour;
			return "";
		}
		
		if(this.flavour == flavour || !owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		this.flavour = flavour;
		
		return UtilText.parse(owner,
				"<p>"
					+ "一股舒缓的暖流向下蔓延到[npc.namePos][npc.balls+]中，令[npc.herHim]不由自主地发出[npc.moan]声。<br/>"
					+ "[npc.NamePos]的[npc.cum]"
					+ (flavour==FluidFlavour.FLAVOURLESS
						?"现在是<b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>"
						:"现在尝起来像<b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>。")
				+ "</p>");
	}
	
	public boolean hasFluidModifier(FluidModifier fluidModifier) {
		return fluidModifiers.contains(fluidModifier);
	}
	
	public String addFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(!isCharacterInitialised(owner) && !fluidModifiers.contains(fluidModifier)) {
			fluidModifiers.add(fluidModifier);
			return "";
		}
		
		if(fluidModifiers.contains(fluidModifier) || !owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		fluidModifiers.add(fluidModifier);
		
		switch(fluidModifier) {
			case ADDICTIVE:
				return UtilText.parse(owner,
						"<p>"
							+ "一股奇妙而躁动的热流深入了[npc.namePos]的[npc.balls]，让[npc.herHim]下意识地发出一声[npc.a_moan+]。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在是[style.boldGrow(成瘾的)]！"
						+ "</p>");
			case ALCOHOLIC:
				fluidModifiers.remove(FluidModifier.ALCOHOLIC_WEAK);
				return UtilText.parse(owner,
						"<p>"
							+ "一股奇妙而舒缓的暖流深入到[npc.namePos]的[npc.balls]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在是[style.boldGrow(含高度酒精的)]！"
						+ "</p>");
			case ALCOHOLIC_WEAK:
				fluidModifiers.remove(FluidModifier.ALCOHOLIC);
				return UtilText.parse(owner,
						"<p>"
							+ "一股奇妙而舒缓的暖流深入到[npc.namePos]的[npc.balls]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在是[style.boldGrow(含酒精的)]！"
						+ "</p>");
			case BUBBLING:
				return UtilText.parse(owner,
						"<p>"
							+ "一股轻微的气泡感在[npc.namePos]的[npc.balls]中出现，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在会[style.boldGrow(起泡)]！"
						+ "</p>");
			case HALLUCINOGENIC:
				return UtilText.parse(owner,
						"<p>"
							+ "一连串奇妙的搏动感直冲[npc.namePos]的[npc.balls]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在是[style.boldGrow(致幻的)]！"
						+ "</p>");
			case MINERAL_OIL:
				return UtilText.parse(owner,
						"<p>"
							+ "一股舒缓的暖流沁入[npc.namePos]的[npc.balls]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在充满了[style.boldGrow(矿物油)]，可以融化避孕套了！"
						+ "</p>");
			case MUSKY:
				return UtilText.parse(owner,
						"<p>"
							+ "一股缓慢蠕动的暖意从[npc.namePos]的[npc.balls]中升腾，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在[style.boldGrow(充满了淫味)]！"
						+ "</p>");
			case SLIMY:
				return UtilText.parse(owner,
						"<p>"
							+ "一股奇妙而舒缓的暖流沁入[npc.namePos]的[npc.balls]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在是[style.boldGrow(粘滑的)]！"
						+ "</p>");
			case STICKY:
				return UtilText.parse(owner,
						"<p>"
							+ "一股厚重的暖流流入[npc.namePos]的[npc.balls]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在是[style.boldGrow(黏糊糊的)]！"
						+ "</p>");
			case VISCOUS:
				return UtilText.parse(owner,
						"<p>"
							+ "一股强烈的热流缓缓在[npc.namePos]的[npc.balls]中升腾，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在是[style.boldGrow(粘稠的)]！"
						+ "</p>");
		}
		
		return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
	}
	
	public String removeFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(!isCharacterInitialised(owner)) {
			fluidModifiers.remove(fluidModifier);
			return "";
		}
		
		if(!fluidModifiers.contains(fluidModifier) || !owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		fluidModifiers.remove(fluidModifier);
		
		switch(fluidModifier) {
			case ADDICTIVE:
				return UtilText.parse(owner,
						"<p>"
							+ "一股微凉感在[npc.namePos]的[npc.balls]中蔓延，让[npc.herHim]不禁发出一声轻叹。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在[style.boldShrink(不再有成瘾性)]！"
						+ "</p>");
			case ALCOHOLIC:
			case ALCOHOLIC_WEAK:
				return UtilText.parse(owner,
						"<p>"
							+ "一股微凉感在[npc.namePos]的[npc.balls]中蔓延，让[npc.herHim]不禁发出一声轻叹。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在[style.boldShrink(不再含酒精)]！"
						+ "</p>");
			case BUBBLING:
				return UtilText.parse(owner,
						"<p>"
							+ "一股平静感在[npc.namePos]的[npc.balls]中出现，让[npc.herHim]不禁发出一声轻叹。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在[style.boldShrink(不再起泡)]！"
						+ "</p>");
			case HALLUCINOGENIC:
				return UtilText.parse(owner,
						"<p>"
							+ "一连串舒缓的浪潮涌上[npc.namePos]的[npc.balls]，让[npc.herHim]不禁发出一声轻叹。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在[style.boldShrink(不再有致幻性)]！"
						+ "</p>");
			case MUSKY:
				return UtilText.parse(owner,
						"<p>"
							+ "一股微凉感在[npc.namePos]的[npc.balls]中升腾，让[npc.herHim]不禁发出一声轻叹。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在[style.boldShrink(不再充满淫味)]！"
						+ "</p>");
			case SLIMY:
				return UtilText.parse(owner,
						"<p>"
							+ "一股舒缓的凉爽感流入[npc.namePos]的[npc.balls]，让[npc.herHim]不禁发出一声轻叹。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在[style.boldShrink(不再粘滑)]！"
						+ "</p>");
			case STICKY:
				return UtilText.parse(owner,
						"<p>"
							+ "一股微暖沁入了[npc.namePos]的[npc.balls]，让[npc.herHim]不禁发出一声轻叹。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在[style.boldShrink(不再黏糊糊)]！"
						+ "</p>");
			case VISCOUS:
				return UtilText.parse(owner,
						"<p>"
							+ "一股微凉感在[npc.namePos]的[npc.balls]中升腾，让[npc.herHim]不禁发出一声轻叹。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在[style.boldShrink(不再粘稠)]！"
						+ "</p>");
			case MINERAL_OIL:
				return UtilText.parse(owner,
						"<p>"
							+ "一股短暂的释放感流入[npc.namePos]的[npc.balls]，让[npc.herHim]不禁发出一声轻叹。<br/>"
							+ "[npc.NamePos]的[npc.cum]现在[style.boldShrink(不再如矿物油一般)]！"
						+ "</p>");
		}
		
		return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
	}
	
	public List<ItemEffect> getTransformativeEffects() {
		return transformativeEffects;
	}
	
	public void addTransformativeEffect(ItemEffect ie) {
		transformativeEffects.add(ie);
	}

	/**
	 * DO NOT MODIFY!
	 */
	public Set<FluidModifier> getFluidModifiers() {
		return fluidModifiers;
	}

	public void clearFluidModifiers() {
		fluidModifiers.clear();
	}

	public float getValuePerMl() {
		return 0.1f * type.getValueModifier();
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(FluidCum.class) && getType().getRace().isFeralPartsAvailable());
	}
}
