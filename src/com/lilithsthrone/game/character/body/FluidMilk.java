package com.lilithsthrone.game.character.body;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
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

import java.util.*;

/**
 * @since 0.1.83
 * @version 0.4.9.7
 * @author Innoxia
 */
public class FluidMilk implements FluidInterface {

	
	public AbstractFluidType type;
	public FluidFlavour flavour;
	protected Set<FluidModifier> fluidModifiers;
	protected List<ItemEffect> transformativeEffects;
	
	protected boolean crotchMilk;

	public FluidMilk(AbstractFluidType type, boolean crotchMilk) {
		this.type = type;
		this.flavour = type.getFlavour();
		transformativeEffects = new ArrayList<>();
		
		fluidModifiers = new HashSet<>();
		fluidModifiers.addAll(type.getDefaultFluidModifiers());
		
		this.crotchMilk = crotchMilk;
	}

	public FluidMilk(FluidMilk milkToCopy) {
		this.type = milkToCopy.type;
		this.flavour = milkToCopy.flavour;
		this.fluidModifiers = new HashSet<>(milkToCopy.fluidModifiers);
		this.transformativeEffects = new ArrayList<>(milkToCopy.transformativeEffects);
		this.crotchMilk = milkToCopy.crotchMilk;
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
	
	public static FluidMilk loadFromXML(String rootElementName, Element parentElement, Document doc) {
		return loadFromXML(rootElementName, parentElement, doc, null, false);
	}
	
	/**
	 * 
	 * @param parentElement
	 * @param doc
	 * @param baseType If you pass in a baseType, this method will ignore the saved type in parentElement.
	 */
	public static FluidMilk loadFromXML(String rootElementName, Element parentElement, Document doc, AbstractFluidType baseType, boolean crotchMilk) {
		
		Element milk = (Element)parentElement.getElementsByTagName(rootElementName).item(0);
		
		AbstractFluidType fluidType = FluidType.MILK_HUMAN;
		
		if(baseType!=null) {
			fluidType = baseType;
			
		} else {
			try {
				fluidType = FluidType.getFluidTypeFromId(milk.getAttribute("type"));
			} catch(Exception ex) {
			}
		}
		
		FluidMilk fluidMilk = new FluidMilk(fluidType, crotchMilk);
		
		String flavourId = milk.getAttribute("flavour");
		if(flavourId.equalsIgnoreCase("SLIME")) {
			fluidMilk.flavour = FluidFlavour.BUBBLEGUM;
		} else {
			fluidMilk.flavour = FluidFlavour.valueOf(flavourId);
		}
		
		Element milkModifiersElement = (Element)milk.getElementsByTagName("milkModifiers").item(0);
		fluidMilk.fluidModifiers.clear();
		if(milkModifiersElement!=null) {
			if(milkModifiersElement!=null) {
				Collection<FluidModifier> milkFluidModifiers = fluidMilk.fluidModifiers;
				Body.handleLoadingOfModifiers(FluidModifier.values(), null, milkModifiersElement, milkFluidModifiers);
			}
		} else {
			NodeList mods = milk.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				fluidMilk.fluidModifiers.add(FluidModifier.valueOf(e.getTextContent()));
			}
		}
		
		return fluidMilk;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof FluidMilk){
			if(((FluidMilk)o).getType().equals(this.getType())
				&& ((FluidMilk)o).getFlavour() == this.getFlavour()
				&& ((FluidMilk)o).getFluidModifiers().equals(this.getFluidModifiers())
				&& ((FluidMilk)o).getTransformativeEffects().equals(this.getTransformativeEffects())){
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
		return type.getName(gc);
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		return type.getNamePlural(gc);
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		String modifierDescriptor = "";
		if(!fluidModifiers.isEmpty()) {
			modifierDescriptor = new ArrayList<>(fluidModifiers).get(Util.random.nextInt(fluidModifiers.size())).getName();
		}
		
		return UtilText.returnStringAtRandom(
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
		
		if(this.flavour == flavour) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}

		this.flavour = flavour;
		
		if(this.isCrotchMilk()) {
			return UtilText.parse(owner,
					"<p>"
						+ "一股舒缓的暖流向下蔓延到[npc.namePos]的[npc.crotchBoobs]中，令[npc.herHim]不由自主地发出[npc.moan]声。<br/>"
						+ "[npc.NamePos]的[npc.crotchMilk]"
						+ (flavour==FluidFlavour.FLAVOURLESS
							?"现在是<b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>"
							:"现在尝起来像<b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>。")
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "一股舒缓的暖流蔓延到[npc.namePos]的[npc.breasts]中，令[npc.herHim]不由自主地发出[npc.moan]声。<br/>"
						+ "[npc.NamePos]的[npc.milk]"
						+ (flavour==FluidFlavour.FLAVOURLESS
							?"现在是<b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>"
							:"现在尝起来像<b style='color:"+flavour.getColour().toWebHexString()+";'>"+flavour.getName()+"</b>。")
					+ "</p>");
		}
	}
	
	public boolean hasFluidModifier(FluidModifier fluidModifier) {
		return fluidModifiers.contains(fluidModifier);
	}
	
	public String addFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(fluidModifiers.contains(fluidModifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		fluidModifiers.add(fluidModifier);
		
		if(owner == null) {
			return "";
		}
		
		switch(fluidModifier) {
			case ADDICTIVE:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股奇妙而躁动的热流深入了[npc.namePos]的[npc.crotchBoobs]，让[npc.herHim]下意识地发出一声[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在是[style.boldGrow(成瘾的)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股奇妙而躁动的热流深入了[npc.namePos]的[npc.breasts]，让[npc.herHim]下意识地发出一声[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在是[style.boldGrow(成瘾的)]！"
							+ "</p>");
				}
			case ALCOHOLIC:
				fluidModifiers.remove(FluidModifier.ALCOHOLIC_WEAK);
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股奇妙而舒缓的暖流沁入[npc.namePos]的[npc.crotchBoobs]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在是[style.boldGrow(含高度酒精的)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股奇妙而舒缓的暖流沁入[npc.namePos]的[npc.breasts]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在是[style.boldGrow(含高度酒精的)]！"
							+ "</p>");
				}
			case ALCOHOLIC_WEAK:
				fluidModifiers.remove(FluidModifier.ALCOHOLIC);
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股奇妙而舒缓的暖流沁入[npc.namePos]的[npc.crotchBoobs]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在是[style.boldGrow(含酒精的)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股奇妙而舒缓的暖流沁入[npc.namePos]的[npc.breasts]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在是[style.boldGrow(含酒精的)]！"
							+ "</p>");
				}
			case BUBBLING:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股轻微的气泡感在[npc.namePos]的[npc.crotchBoobs]中出现，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在是[style.boldGrow(起泡的)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股轻微的气泡感在[npc.namePos]的[npc.breasts]中出现，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在是[style.boldGrow(起泡的)]！"
							+ "</p>");
				}
			case HALLUCINOGENIC:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一连串奇妙的搏动感直冲[npc.namePos]的[npc.crotchBoobs]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在是[style.boldGrow(致幻的)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一连串奇妙的搏动感直冲[npc.namePos]的[npc.breasts]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在是[style.boldGrow(致幻的)]！"
							+ "</p>");
				}
			case MINERAL_OIL:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股舒缓的暖流沁入[npc.namePos]的[npc.crotchBoobs]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在充满了[style.boldGrow(矿物油)]，可以融化避孕套了！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股舒缓的暖流沁入[npc.namePos]的[npc.breasts]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在充满了[style.boldGrow(矿物油)]，可以融化避孕套了！"
							+ "</p>");
				}
			case MUSKY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股缓慢蠕动的暖意从[npc.namePos]的[npc.crotchBoobs]中升腾，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在是[style.boldGrow(麝香味的)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股缓慢蠕动的暖意从[npc.namePos]的[npc.breasts]中升腾，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在是[style.boldGrow(麝香味的)]！"
							+ "</p>");
				}
			case SLIMY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股奇妙而舒缓的暖流沁入[npc.namePos]的[npc.crotchBoobs]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在是[style.boldGrow(粘滑的)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股奇妙而舒缓的暖流沁入[npc.namePos]的[npc.breasts]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在是[style.boldGrow(粘滑的)]！"
							+ "</p>");
				}
			case STICKY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股厚重的暖流流入[npc.namePos]的[npc.crotchBoobs]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在是[style.boldGrow(黏糊糊的)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股厚重的暖流流入[npc.namePos]的[npc.breasts]，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在是[style.boldGrow(黏糊糊的)]！"
							+ "</p>");
				}
			case VISCOUS:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股强烈的热流缓缓在[npc.namePos]的[npc.crotchBoobs]中升腾，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在是[style.boldGrow(粘稠的)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股强烈的热流缓缓在[npc.namePos]的[npc.breasts]中升腾，让[npc.herHim]不禁发出[npc.a_moan+]。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在是[style.boldGrow(粘稠的)]！"
							+ "</p>");
				}
		}
		
		return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
	}
	
	public String removeFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
		if(!fluidModifiers.contains(fluidModifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		fluidModifiers.remove(fluidModifier);
		
		if(owner == null) {
			return "";
		}
		
		switch(fluidModifier) {
			case ADDICTIVE:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股微凉感在[npc.namePos]的[npc.crotchBoobs]中蔓延，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在[style.boldShrink(不再有成瘾性)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股微凉感在[npc.namePos]的[npc.breasts]中蔓延，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在[style.boldShrink(不再有成瘾性)]！"
							+ "</p>");
				}
			case ALCOHOLIC:
			case ALCOHOLIC_WEAK:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股微凉感在[npc.namePos]的[npc.crotchBoobs]中蔓延，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在[style.boldShrink(不再含酒精)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股微凉感在[npc.namePos]的[npc.breasts]中蔓延，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在[style.boldShrink(不再含酒精)]！"
							+ "</p>");
				}
			case BUBBLING:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股平静感在[npc.namePos]的[npc.crotchBoobs]中出现，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在[style.boldShrink(不再起泡)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股平静感在[npc.namePos]的[npc.breasts]中出现，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在[style.boldShrink(不再起泡)]！"
							+ "</p>");
				}
			case HALLUCINOGENIC:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一连串舒缓的浪潮涌向[npc.namePos]的[npc.crotchBoobs]，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在[style.boldShrink(不再有致幻性)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一连串舒缓的浪潮涌上[npc.namePos]的[npc.breasts]，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在[style.boldShrink(不再有致幻性)]！"
							+ "</p>");
				}
			case MUSKY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股微凉感在[npc.namePos]的[npc.crotchBoobs]中升腾，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在[style.boldShrink(不再充满淫味)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股微凉感在[npc.namePos]的[npc.breasts]中升腾，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在[style.boldShrink(不再充满淫味)]！"
							+ "</p>");
				}
			case SLIMY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股舒缓的凉爽感流入[npc.namePos]的[npc.crotchBoobs]，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在[style.boldShrink(不再黏滑)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股舒缓的凉爽感流入[npc.namePos]的[npc.breasts]，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在[style.boldShrink(不再粘滑)]！"
							+ "</p>");
				}
			case STICKY:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股微暖沁入了[npc.namePos]的[npc.crotchBoobs]，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在[style.boldShrink(不再黏糊糊)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股微暖沁入了[npc.namePos]的[npc.breasts]，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在[style.boldShrink(不再黏糊糊)]！"
							+ "</p>");
				}
			case VISCOUS:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股微凉感在[npc.namePos]的[npc.crotchBoobs]中升腾，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在[style.boldShrink(不再粘稠)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股微凉感在[npc.namePos]的[npc.breasts]中升腾，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在[style.boldShrink(不再粘稠)]！"
							+ "</p>");
				}
			case MINERAL_OIL:
				if(this.isCrotchMilk()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股短暂的释放感流入[npc.namePos]的[npc.crotchBoobs],，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.crotchMilk]现在[style.boldShrink(不再含矿物油)]！"
							+ "</p>");
				} else {
					return UtilText.parse(owner,
							"<p>"
								+ "一股短暂的释放感流入[npc.namePos]的[npc.breasts]，让[npc.herHim]不禁发出一声轻叹。<br/>"
								+ "[npc.NamePos]的[npc.milk]现在[style.boldShrink(不再含矿物油)]！"
							+ "</p>");
				}
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
		return 0.01f * type.getValueModifier();
	}

	public boolean isCrotchMilk() {
		return crotchMilk;
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(FluidMilk.class) && getType().getRace().isFeralPartsAvailable());
	}
}
