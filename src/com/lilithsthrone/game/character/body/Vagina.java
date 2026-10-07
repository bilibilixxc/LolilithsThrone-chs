package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Vagina implements BodyPartInterface {
	
	protected AbstractVaginaType type;
	public Clitoris clitoris;
	protected int labiaSize;
	public boolean pierced;
	public boolean eggLayer;
	
	public OrificeVagina orificeVagina;
	public FluidGirlCum girlcum;
	public OrificeVaginaUrethra orificeUrethra;

	public Vagina(AbstractVaginaType type, int labiaSize, int clitSize, int clitGirth, int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.labiaSize = labiaSize;
		this.clitoris = new Clitoris(clitSize, clitGirth);
		this.pierced = false;
		this.eggLayer = type.isEggLayer();
		orificeVagina = new OrificeVagina(wetness, capacity, depth, elasticity, plasticity, virgin, type.getDefaultRacialOrificeModifiers());
		orificeUrethra = new OrificeVaginaUrethra(Wetness.TWO_MOIST.getValue(), 0, 2, OrificeElasticity.ZERO_UNYIELDING.getValue(), OrificePlasticity.THREE_RESILIENT.getValue(), true, new ArrayList<>());
		girlcum = new FluidGirlCum(type.getFluidType());
	}

	public Vagina(Vagina vaginaToCopy) {
		this.type = vaginaToCopy.type;
		this.labiaSize = vaginaToCopy.labiaSize;
		this.clitoris = new Clitoris(vaginaToCopy.clitoris);
		this.pierced = vaginaToCopy.pierced;
		this.eggLayer = vaginaToCopy.eggLayer;
		this.orificeVagina = new OrificeVagina(vaginaToCopy.orificeVagina);
		this.orificeUrethra = new OrificeVaginaUrethra(vaginaToCopy.orificeUrethra);
		this.girlcum = new FluidGirlCum(vaginaToCopy.girlcum);
	}
	
	public OrificeVagina getOrificeVagina() {
		return orificeVagina;
	}

	public void setGirlcum(FluidGirlCum girlcum) {
		this.girlcum = girlcum;
	}

	public FluidGirlCum getGirlcum() {
		return girlcum;
	}
	
	public OrificeVaginaUrethra getOrificeUrethra() {
		return orificeUrethra;
	}

	@Override
	public AbstractVaginaType getType() {
		return type;
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
	public String getDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<>();
		
		for(OrificeModifier om : orificeVagina.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		
		String wetnessDescriptor = orificeVagina.getWetness(owner).getDescriptor();
		if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
			if(Main.sex.hasLubricationTypeFromAnyone(owner, SexAreaOrifice.VAGINA)) {
				wetnessDescriptor = "湿润";
			}
		}
		descriptorList.add(wetnessDescriptor);
		if(owner.getPubicHair().getValue()>=BodyHair.FOUR_NATURAL.getValue() && Main.game.isPubicHairEnabled()) {
			descriptorList.add("多毛");
		}
		
		// It doesn't make much sense to be referencing taste in a generic context
//		if(this.getGirlcum().getFlavour()!=FluidFlavour.GIRL_CUM && this.getGirlcum().getFlavour()!=FluidFlavour.FLAVOURLESS) {
//			descriptorList.add(this.getGirlcum().getFlavour().getName()+"-flavoured");
//		}
		
		if(owner.isVaginaFeral()) {
			descriptorList.add(Util.randomItemFrom(Util.newArrayListOfValues(
					"兽态",
					"兽性",
					"动物化")));
		} else {
			descriptorList.add(type.getDescriptor(owner));
		}
		
		if(owner.getBodyMaterial().getPartDescriptors()!=null && !owner.getBodyMaterial().getPartDescriptors().isEmpty()) {
			descriptorList.add(Util.randomItemFrom(owner.getBodyMaterial().getPartDescriptors()));
		}
		
		descriptorList.add(Capacity.getCapacityFromValue(orificeVagina.getStretchedCapacity()).getDescriptor().replaceAll(" ", "-"));

		descriptorList.removeIf(d->d==null || d.isEmpty());
		if(descriptorList.isEmpty()) {
			return "";
		}
		return Util.randomItemFrom(descriptorList);
	}
	
	public String getUrethraDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<>();
		
		for(OrificeModifier om : orificeUrethra.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		
		descriptorList.add(type.getDescriptor(owner));
		
		descriptorList.add(Capacity.getCapacityFromValue(orificeUrethra.getStretchedCapacity()).getDescriptor().replaceAll(" ", "-"));

		return Util.randomItemFrom(descriptorList);
	}
	
	public String setType(GameCharacter owner, AbstractVaginaType type) {
		return setType(owner, type, false);
	}
	
	public String setType(GameCharacter owner, AbstractVaginaType type, boolean overridePregnancyPrevention) {
		if(this.type==VaginaType.NONE) {
			this.orificeVagina.setStretchedCapacity(this.orificeVagina.getRawCapacityValue());
			this.orificeUrethra.setStretchedCapacity(this.orificeUrethra.getRawCapacityValue());
			this.orificeVagina.hymen=true;
		}
		
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {// This always overrides pregnancy prevention, as the only times where this is true are for utility methods:
			this.type = type;
			this.girlcum.setType(type.getFluidType());
			this.eggLayer = type.isEggLayer();
			if(owner!=null) {
				owner.resetAreaKnownByCharacters(CoverableArea.VAGINA);
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == owner.getVaginaType()) {
			if(type == VaginaType.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经没有阴道了，所以无事发生……)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有[npc.a_vaginaRace]的小穴了，所以无事发生……)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		// Cannot transform if pregnant:
		if (!overridePregnancyPrevention
				&& type==VaginaType.NONE
				&& (owner.isPregnant() || owner.hasStatusEffect(StatusEffect.PREGNANT_0) || owner.getIncubationLitter(SexAreaOrifice.VAGINA)!=null)) {
			sb.append(UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]发出一声淫荡的呻吟，感到[npc.her][npc.pussy+]开始发烫并变得敏感，"
							+ "当一阵刺痛的兴奋感席卷[npc.her]的下腹部时，[npc.her]的呻吟变成了绝望的喘息。"
						+ "令[npc.her]惊讶的是，这种感觉来得快去得也快，[npc.she]叹了口气，意识到"
						+ (owner.getIncubationLitter(SexAreaOrifice.VAGINA)!=null
							?"<b>着床在[npc.her]子宫中的受精卵阻止了[npc.her]的阴道被移除</b>！"
							:(owner.hasStatusEffect(StatusEffect.PREGNANT_0)
								?"<b>[npc.her]可能受孕，因此无法移除[npc.her]的阴道</b>！"
								:"<b>[npc.her]正在怀孕，因此无法移除[npc.her]的阴道</b>！"))
						+ "<br/>"
						+ "[npc.NamePos]的小穴保持[style.boldTfSex(不变)]。"
					+ "</p>"));
			
			return sb.toString()
					+ "<p>"
						+owner.postTransformationCalculation()
					+"</p>";
		}
		
		// If have no vagina:
		if(owner.getVaginaType() == VaginaType.NONE) {
			sb.append(UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感到一股奇怪的热量在腹股沟处蔓延开来，脸上泛起红晕并情不自禁地低声发出[npc.moan]，"
						+ (!owner.hasPenisIgnoreDildo()
							? "在[npc.her]腹股沟中间"
							: (!owner.isTaur()
									?"在[npc.her]阴茎下面"
									:"在[npc.her]阴茎上方和后方"))
						+ "开始向内凹陷并形成一条浅沟。"
						+ "这个奇怪的新凹痕继续加深，似乎毫无停下的意思，又一阵热浪迅速向[npc.namePos]下腹部冲去。"
						+ "当第二波热浪褪去，一股突然的插入感向[npc.her]的下体袭来，虽然并不疼，"
							+ "但[npc.she]还是不禁大叫一声，下体突然分开一道缝，露出深深的穴道。"
						+ "这个新开口迅速转变成阴道的独特形状，有着[npc.clitSize]的阴蒂和[npc.labiaSize]的阴唇。"
					+ "</p>"
					+ "<p>"
						+ "正当[npc.she]以为转化已经步入尾声，最后一阵强烈的灼热感在[npc.namePos]的下腹部躁动起来，"
							+ "[npc.she]禁不住发出一声[npc.moan]，只靠直觉就反应过来一套功能完全的女性生殖器官已经在体内幻化而出。"
						+ "转化终于结束，[npc.namePos]忽然意识到[npc.her]新生的秘缝已经由于快感润湿了，"
							+ "惹得[npc.herHim]发出最后一声畅快的[npc.moan]。"
						+ "<br/>"));
			
			if(owner.isVaginaVirgin()) {
				sb.append(UtilText.parse(owner,
						"[npc.Name]现在拥有[style.colourExcellent(依然纯洁)]的[style.boldTfSex(阴道)]，[style.colourExcellent(处女膜完整如初)]！"));
			} else {
				sb.append(UtilText.parse(owner,
						"[npc.Name]现在长出了[style.boldTfSex(阴道)]，虽然[npc.she]已经并不觉得自己是处女了，但至少[style.colourExcellent(处女膜还完整如初)]！"));
			}
					
			sb.append("</p>");
				
			if(owner.hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
				if(owner.isVaginaVirgin()) {
					sb.append(UtilText.parse(owner,
							"<p style='text-align:center;'>"
								+ "[style.boldExcellent(贞洁处女)]"
								+ "<br/><i>"
								+ "[npc.name]已经有了阴道，终于能够认为自己是个真正的贞洁处女了！"
								+ "[npc.she]欣然露出了笑容，不禁感到无比的喜悦，只要[npc.she]还保有贞洁，"
									+ "就总能代表那自豪而善良的完美形象！"
								+ "</i>"
								+ "<br/>"
								+ "[npc.NameIsFull]现在是个[style.boldExcellent(贞洁处女)]！"
							+ "</p>"));
				} else {
					sb.append(UtilText.parse(owner,
							"<p style='text-align:center;'>"
								+ "[style.boldGood(贞洁“处女”)]"
								+ "<br/><i>"
								+ "[npc.Name]发现自己的阴道依然完好如初，顿时感到一阵狂喜。"
								+ "[npc.she]心中暗暗说服自己，只要处女膜尚在，那理论上就又是处女了，想着想着，喜悦的泪水就从[npc.eyes]中溢出。"
								+ "尽管表面上确实喜悦，但[npc.her]脑海深处却总是萦绕着一阵无法驱散的唠叨声，时刻提醒着[npc.herHim]永远也不能在成为“真正”的处女了。"
								+ "<br/>"
								+ "[npc.Name]晃了晃脑袋，想把这扫兴的想法抛之脑后，而是专心到另一件事上，就是只要[npc.her]的处女膜完好无损，那至少就能假装自己从来还没被插进去过。"
								+ "[npc.she]甚至不愿去想自己的小穴此后或许会再次失守，"
									+ "只是昂着头挺着胸，告诉自己她又变回那个自豪而善良的完美形象了！"
								+ "</i>"
								+ "<br/>"
								+ "[npc.NameIsFull]现在是个[style.boldGood(贞洁“处女”)]！"
							+ "</p>"));
				}
			}
			
			this.type = VaginaType.HUMAN;
			owner.resetAreaKnownByCharacters(CoverableArea.VAGINA);
			
			if(type==VaginaType.HUMAN) {
				this.girlcum.setType(type.getFluidType());
				return sb.toString()
						+ "<p>"
							+owner.postTransformationCalculation()
						+"</p>";
			} else {
				return sb.toString()
						+ owner.setVaginaType(type);
			}
			
		} else {
			sb.append(UtilText.parse(owner,
					"<p>"
						+"[npc.Name]感到一阵奇怪的灼热感在阴道内部躁动着，[npc.she]不禁脸红心跳，喘了起来，而此时转化也正式开始。<br/>"));
		}

		sb.append(this.type.applyAdditionalTransformationEffects(owner, false));
		this.type = type;
		this.girlcum.setType(type.getFluidType());
		this.eggLayer = type.isEggLayer();
		owner.resetAreaKnownByCharacters(CoverableArea.VAGINA);
		sb.append(this.type.getTransformationDescription(owner));
		sb.append(this.type.applyAdditionalTransformationEffects(owner, true));
		
		sb.append("</p>");

		if(this.type != VaginaType.NONE) {
			sb.append("<p style='text-align:center;'>");
				if(this.eggLayer) {
					sb.append(UtilText.parse(owner,"<i>[npc.name]不再通过分娩繁育后代，而是[style.colourEgg(产卵)]！</i>"));
				} else {
					sb.append(UtilText.parse(owner,"<i>[npc.Name]现在[style.colourSex(通过分娩繁育后代)]！</i>"));
				}
			sb.append("</p>");
		}
		
		orificeVagina.getOrificeModifiers().clear();
		for(OrificeModifier om : type.getDefaultRacialOrificeModifiers()) {
			orificeVagina.addOrificeModifier(owner, om);
		}
		
		sb.append(UtilText.parse(owner,"<p>"
				+ "[npc.her]的阴道原有的所有修饰词都有可能[style.boldShrink(因转化而消失)]！"));
		
		if(orificeVagina.getOrificeModifiers().isEmpty()) {
			sb.append("</p>");
		} else {
			sb.append(UtilText.parse(owner,"<br/>"
					+ "取而代之的是，[npc.her]新生的阴道现在:"));
			
			for(OrificeModifier om : orificeVagina.getOrificeModifiers()) {
				sb.append("<br/>[style.boldGrow("+Util.capitaliseSentence(om.getName())+")]");
			}
			sb.append("</p>");
		}
		
		return sb.toString()
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}
	
	
	public LabiaSize getLabiaSize() {
		return LabiaSize.getLabiaSizeFromInt(labiaSize);
	}
	
	public int getRawLabiaSizeValue() {
		return labiaSize;
	}
	
	public String setLabiaSize(GameCharacter owner, int labiaSize) {
		if(!owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		int oldSize = this.labiaSize;
		this.labiaSize = Math.max(0, Math.min(labiaSize, LabiaSize.FOUR_MASSIVE.getValue()));
		int sizeChange = this.labiaSize - oldSize;

		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			return "";
		}
		
		if (sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(你的阴唇大小没有变化……)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]的阴唇大小没有变化……)]</p>");
			}
		} else if (sizeChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "一声[pc.A_moan+]从你[pc.lips+]间溢出，你感到一股温暖的刺痒感传到了[pc.pussy]上。"
							+ "你扭动着身子，忽然倒吸了一口凉气，你感到自己的阴唇肿了起来，[style.boldGrow(变得更大)]了。<br/>"
							+ "你现在拥有[style.boldSex([pc.labiaSize]的阴唇)]！"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "一声[pc.A_moan+]从[npc.Name][npc.lips+]间溢出，[npc.she]感到一股温暖的刺痒感传到了[npc.pussy]上。"
							+ "[npc.her]扭动着身子，忽然倒吸了一口凉气，[npc.she]感到自己的阴唇肿了起来，[style.boldGrow(变得更大)]了。<br/>"
							+ "[npc.Name]现在拥有[style.boldSex([npc.labiaSize]的阴唇)]！"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>"
						+ "一声[pc.A_moan+]从你[pc.lips+]间溢出，你感到一股微凉的刺痒感传到了[pc.pussy]上。"
						+ "你扭动着身子，忽然倒吸了一口凉气，你感到自己的阴唇缩了下去，[style.boldShrink(变得更小)]了。<br/>"
						+ "你现在拥有[style.boldSex([pc.labiaSize]的阴唇)]！"
					+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "一声[pc.A_moan+]从[npc.Name][npc.lips+]间溢出，[npc.she]感到一股微凉的刺痒感传到了[npc.pussy]上。"
							+ "[npc.her]扭动着身子，忽然倒吸了一口凉气，[npc.she]感到自己的阴唇缩了下去，[style.boldShrink(变得更小)]了。<br/>"
							+ "[npc.Name]现在拥有[style.boldSex([npc.labiaSize]的阴唇)]！"
						+ "</p>");
			}
		}
	}

	public boolean isPierced() {
		return pierced;
	}

	public String setPierced(GameCharacter owner, boolean pierced) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.pierced = pierced;
			return "";
		}
		
		if(this.pierced == pierced || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		this.pierced = pierced;
		
		if(pierced) {
			return UtilText.parse(owner, "<p>[npc.NamePos]的[npc.pussy]现在已经[style.boldGrow(穿孔)]！</p>");
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_VAGINA);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.NamePos]的[npc.pussy][style.boldShrink(不在有穿孔了)]！"
					+ "</p>"
					+piercingUnequip);
		}
	}

	public boolean isEggLayer() {
		return eggLayer;
	}

	public String setEggLayer(GameCharacter owner, boolean eggLayer) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.eggLayer = eggLayer;
			return "";
		}
		
		if(this.eggLayer == eggLayer || !owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		if(owner.isPregnant()) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]惊讶地喘息一声，一股不适的刺痒感瞬间传遍了[npc.her]的下腹部。"
						+ "然而不安感转瞬即逝，[npc.name]忽然意识到是因为自己怀了孕，子宫才无法发生变化！"
					+ "</p>");
		}
		
		this.eggLayer = eggLayer;
		
		if(eggLayer) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]惊讶地喘息一声，一股不适的刺痒感瞬间传遍了[npc.her]的下腹部。"
						+ "随之而来的是强烈的痉挛，让原先的喘息变成了痛苦的呻吟。"
						+ "<br/>"
						+ "所幸，令人不适的转化很快就结束了，[npc.Name]惊喘了一口气，立刻便知晓[style.boldEgg([npc.she]现在将会产产卵，而不再是生育孩童了)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]惊讶地喘息一声，一股不适的刺痒感瞬间传遍了[npc.her]的下腹部。"
						+ "随之而来的是强烈的痉挛，让原先的喘息变成了痛苦的呻吟。"
						+ "<br/>"
						+ "所幸，令人不适的转化很快就结束了，[npc.Name]惊喘了一口气，立刻便知晓[style.boldSex([npc.she]现在将会生育孩童，而不再是产产卵了)]！"
					+ "</p>");
		}
	
	}

	public Clitoris getClitoris() {
		return clitoris;
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Vagina.class) && getType().getRace().isFeralPartsAvailable());
	}
}
