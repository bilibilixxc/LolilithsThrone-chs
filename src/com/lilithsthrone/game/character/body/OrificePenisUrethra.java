package com.lilithsthrone.game.character.body;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.OrificeInterface;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.?
 * @version 0.4.9.7
 * @author Innoxia
 */
public class OrificePenisUrethra implements OrificeInterface {

	protected int wetness;
	public float capacity;
	public float stretchedCapacity;
	public int depth;
	public int elasticity;
	public int plasticity;
	public boolean virgin;
	public Set<OrificeModifier> orificeModifiers;

	public OrificePenisUrethra(int wetness, int capacity, int depth, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.depth = depth;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		
		this.orificeModifiers = new HashSet<>(orificeModifiers);
	}

	public OrificePenisUrethra(OrificePenisUrethra orificePenisUrethraToCopy) {
		this.wetness = orificePenisUrethraToCopy.wetness;
		this.capacity = orificePenisUrethraToCopy.capacity;
		this.stretchedCapacity = orificePenisUrethraToCopy.stretchedCapacity;
		this.depth = orificePenisUrethraToCopy.depth;
		this.elasticity = orificePenisUrethraToCopy.elasticity;
		this.plasticity = orificePenisUrethraToCopy.plasticity;
		this.virgin = orificePenisUrethraToCopy.virgin;
		
		this.orificeModifiers = new HashSet<>(orificePenisUrethraToCopy.orificeModifiers);
	}
	
	@Override
	/** <b>This method is not used, as a penis's wetness is determined by its cum production.</b> */
	public Wetness getWetness(GameCharacter owner) {
		return Wetness.valueOf(wetness);
	}

	@Override
	/** <b>This method is not used, as a penis's wetness is determined by its cum production.</b> */
	public String setWetness(GameCharacter owner, int wetness) {
		if (owner!=null && !owner.hasPenis()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]缺少阴茎，所以什么也没发生……)]</p>");
		}
		
		int oldWetness = this.wetness;
		this.wetness = Math.max(0, Math.min(wetness, Wetness.SEVEN_DROOLING.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int wetnessChange = this.wetness - oldWetness;
		
		if (wetnessChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.NamePos]先走液产量没有变化……)]</p>");
		}
		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "，[npc.NamePos][npc.cock+]突然硬起，[npc.she]立刻睁大了眼睛，"
							+ "同时发出[npc.a_moan+]，一小股先走液从尖端渗出，它的产量[style.boldGrow(增加)]了。<br/>"
						+ "[npc.She]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + "" + wetnessDescriptor + "尿道)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]难受地四处乱走，发出一声沮丧的呻吟，[npc.she]感觉到[npc.her]的先走液产量[style.boldShrink(下降)]了。<br/>"
						+ "[npc.She]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + "" + wetnessDescriptor + "尿道)]！"
					+ "</p>");
		}
	}

	@Override
	public Capacity getCapacity() {
		return Capacity.getCapacityFromValue((int)capacity);
	}

	@Override
	public float getRawCapacityValue() {
		return capacity;
	}

	@Override
	public String setCapacity(GameCharacter owner, float capacity, boolean setStretchedValueToNewValue) {
		if (owner!=null && !owner.hasPenis()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]缺少阴茎，所以什么也没发生……)]</p>");
		}
		
		float oldCapacity = this.capacity;
		this.capacity = Math.max(0, Math.min(capacity, Capacity.SEVEN_GAPING.getMaximumValue(false)));
		if(setStretchedValueToNewValue) {
			this.stretchedCapacity = this.capacity;
		}
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		float capacityChange = this.capacity - oldCapacity;
		
		if (capacityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]尿道的直径没有变化……)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.namePos]口中不由得漏出一声惊讶的喘息，[npc.she]感到[npc.her]的阴茎尿道正十分放松地舒展着。"
						+ "片刻之间，那种令人担忧的感觉已经过去了，[npc.she]很快意识到[npc.her]的尿道内部[style.boldGrow(直径增加)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + "" + capacityDescriptor + "尿道)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]发出一声叫喊，因为[npc.she]感觉到[npc.her]的阴茎尿道正无法控制地收缩和变紧。"
						+ "片刻之后，那种令人担忧的感觉已经过去了，[npc.she]很快意识到[npc.her]的尿道内部[style.boldShrink(直径减少)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + "" + capacityDescriptor + "尿道)]！"
					+ "</p>");
		}
	}
	
	@Override
	public float getStretchedCapacity() {
		return stretchedCapacity;
	}

	@Override
	public boolean setStretchedCapacity(float stretchedCapacity) {
		float oldStretchedCapacity = this.stretchedCapacity;
		this.stretchedCapacity = Math.max(0, Math.min(stretchedCapacity, Capacity.SEVEN_GAPING.getMaximumValue(false)));
		return oldStretchedCapacity != this.stretchedCapacity;
	}
	
	@Override
	public int getMaximumPenetrationDepthComfortable(GameCharacter owner, OrificeDepth depth) {
		return (int) (owner.getPenisRawSizeValue() * 1.5f * depth.getDepthPercentage());
	}
	
	@Override
	public int getMaximumPenetrationDepthUncomfortable(GameCharacter owner, OrificeDepth depth) {
		if(Main.game.isElasticityAffectDepthEnabled() && OrificeElasticity.getElasticityFromInt(elasticity).isExtendingUncomfortableDepth()) {
			return (int) (getMaximumPenetrationDepthComfortable(owner, depth) * (float)elasticity/1.8f);
		} else {
			return (int) (getMaximumPenetrationDepthComfortable(owner, depth) * 1.5f);
		}
	}

	@Override
	public OrificeDepth getDepth(GameCharacter owner) {
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return OrificeDepth.SEVEN_FATHOMLESS;
		}
		return OrificeDepth.getDepthFromInt(depth);
	}

	@Override
	public String setDepth(GameCharacter owner, int depth) {
		if (owner!=null && !owner.hasPenis()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]缺少阴茎，所以什么也没发生……)]</p>");
		}
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(由于是由"+owner.getBodyMaterial().getName()+"构成的，[npc.namePos]尿道"+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+"的深度无法改变……)]</p>");
		}
		
		int oldDepth = this.depth;
		this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int depthChange = this.depth - oldDepth;
		
		if(depthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]尿道的深度没有变化……)]</p>");
		}
		
		String depthDescriptor = getDepth(owner).getDescriptor();
		if(depthChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]不由得发出一声惊讶的喘息，[npc.she]感觉到一种令人不安的压力感从[npc.her]的阴茎直到腹股沟处搏动着。"
						+ "在[npc.her]的喘息变成痛苦的叫喊前，那股压力感突然消失了，只是让[npc.herHim]出于本能地明白，[npc.her]的阴茎尿道[style.boldGrow(变深)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + "" + depthDescriptor + "尿道)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]不由得发出一声惊讶的喘息，因为一阵令人不安的紧绷感正从[npc.her]的腹部一路直下，到达[npc.her]的阴茎根部。"
						+ "在[npc.her]的喘息变成痛苦的叫喊前，那股感觉突然消失了，只是让[npc.herHim]出于本能地明白，[npc.her]的阴茎尿道[style.boldShrink(变浅)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + "" + depthDescriptor + "尿道)]！"
					+ "</p>");
		}
	}
	
	@Override
	public OrificeElasticity getElasticity() {
		return OrificeElasticity.getElasticityFromInt(elasticity);
	}

	@Override
	public String setElasticity(GameCharacter owner, int elasticity) {
		if (owner!=null && !owner.hasPenis()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]缺少阴茎，所以什么也没发生……)]</p>");
		}
		
		int oldElasticity = this.elasticity;
		this.elasticity = Math.max(0, Math.min(elasticity, OrificeElasticity.SEVEN_ELASTIC.getValue()));
		int elasticityChange = this.elasticity - oldElasticity;
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (elasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]尿道的弹性等级没有变化……)]</p>");
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]难以自抑地发出一声惊叹，[npc.she]感觉到一种奇异的松弛感在[npc.her]的阴茎深处跃动着。"
						+ "来时快去时也快，那感觉离开了，[npc.she]很快意识到[npc.her]的尿道[style.boldGrow(弹性等级增加)]了。<br/>"
						+ "[npc.Name]现在拥有[style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + "" + elasticityDescriptor + "尿道)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]难以自抑地发出一声惊叹，[npc.she]感觉到一种奇异的收紧感在[npc.her]的阴茎深处跃动着。"
						+ "来时快去时也快，那感觉离开了，[npc.she]很快意识到[npc.her]的尿道[style.boldShrink(弹性等级降低)]了。<br/>"
						+ "[npc.Name]现在拥有[style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + "" + elasticityDescriptor + "尿道)]！"
					+ "</p>");
		}
	}
	
	@Override
	public OrificePlasticity getPlasticity() {
		return OrificePlasticity.getElasticityFromInt(plasticity);
	}

	@Override
	public String setPlasticity(GameCharacter owner, int plasticity) {
		if (owner!=null && !owner.hasPenis()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]缺少阴茎，所以什么也没发生……)]</p>");
		}
		
		int oldPlasticity = this.plasticity;
		this.plasticity = Math.max(0, Math.min(plasticity, OrificePlasticity.SEVEN_MOULDABLE.getValue()));
		int plasticityChange = this.plasticity - oldPlasticity;
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (plasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]尿道的可塑性等级没有变化……)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]发出一声震惊的喘息，[npc.she]突然感觉到一阵奇特的硬化感在[npc.her]的阴茎深处搏动。"
						+ "[npc.she]甚至都来不及恐慌，那种感觉就很快消失了，只让[npc.herHim]本能地知道[npc.her]的尿道[style.boldGrow(可塑性等级增加)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + "" + plasticityDescriptor + "尿道)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]发出一声震惊的喘息，[npc.she]突然感觉到一阵奇特的软化感在[npc.her]的阴茎深处鼓动。"
						+ "[npc.she]甚至都来不及恐慌，那种感觉就很快消失了，只让[npc.herHim]本能地知道[npc.her]的尿道[style.boldShrink(可塑性等级减少)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + "" + plasticityDescriptor + "尿道)]！"
					+ "</p>");
		}
	}

	@Override
	public boolean isVirgin() {
		return virgin;
	}

	@Override
	public void setVirgin(boolean virgin) {
		this.virgin = virgin;
	}

	@Override
	public boolean hasOrificeModifier(OrificeModifier modifier) {
		return orificeModifiers.contains(modifier);
	}

	@Override
	public String addOrificeModifier(GameCharacter owner, OrificeModifier modifier) {
		if(hasOrificeModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		if(owner!=null && !owner.hasPenis()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]缺少阴茎，所以什么也没发生……)]</p>");
		}
		
		orificeModifiers.add(modifier);
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name]难以自抑地发出一声[npc.a_moan+]，一阵强烈的压力在[npc.her]的[npc.cock]根部迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
								+ "[npc.she]试探性地收缩了一下，发现尿道内部现在有着大量可以自由控制的[style.boldGrow(肌肉)]，"
									+ "能够让[npc.she]用来巧妙地抓住并挤榨任何插入进来的物体。<br/>"
								+ "[style.boldSex([npc.NamePos]的阴茎尿道内现在拥有一系列复杂精细的肌肉！)]"
							+ "</p>");
				}
				break;
			case RIBBED:
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>"
							+ "[npc.Name]难以自抑地发出一声[npc.a_moan+]，一阵强烈的压力在[npc.her]的[npc.cock]根部迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
							+ "稍微摸了一下，[npc.she]发现[npc.her]的尿道内部现在衬着[style.boldGrow(极度敏感的肉质螺纹)]，"
								+ "在接受刺激时将带来极致的快感。<br/>"
							+ "[style.boldSex([npc.NamePos]的阴茎尿道现在拥有了能催生快感的肉质螺纹！)]"
						+ "</p>");
				}
				break;
			case TENTACLED:
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name]难以自抑地发出一声[npc.a_moan+]，一阵强烈的压力在[npc.her]的[npc.cock]根部迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
								+ "试探性地收缩了一下，[npc.she]发现[npc.her]的尿道内部现在衬着[style.boldGrow(蠕动的微型触须)]，而且并不完全处于[npc.she]的控制之下。<br/>"
								+ "[style.boldSex([npc.namePos]的阴茎尿道内部现在长满了小触手，它们将以自己的意愿蠕动着爱抚任何侵入的物体！)]"
							+ "</p>");
				}
				break;
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]情不自禁地发出[npc.a_moan+]，[npc.she]感觉到一阵麻刺感沿着[npc.her]的[npc.cock]爬升，"
								+ "接着[npc.her]的尿道边缘突然[style.boldGrow(略微膨起)]，形成了一个松软的甜甜圈似的环。<br/>"
							+ "[style.boldSex([npc.namePos]阴茎尿道的边缘略微膨起，变得更加柔软了！)]"
						+ "</p>");
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
	}

	@Override
	public String removeOrificeModifier(GameCharacter owner, OrificeModifier modifier) {
		if(!hasOrificeModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		orificeModifiers.remove(modifier);
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name]不由得发出一声受到惊吓的叫喊，一股强大的压力在[npc.her]的[npc.cock]中暴增，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
								+ "试探性地收缩了一下，[npc.she]发现[npc.her]的尿道内部现在[style.boldShrink(失去了额外的肌肉)]。<br/>"
								+ "[style.boldSex([npc.NamePos]的阴茎尿道内不再拥有一系列复杂精细的肌肉！)]"
							+ "</p>");
				}
				break;
			case RIBBED:
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>"
							+ "[npc.Name]不由得发出一声受到惊吓的叫喊，一股强大的压力在[npc.her]的[npc.cock]中暴增，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
							+ "稍微摸了一下[npc.her]的[npc.pussy]，[npc.she]发现曾经衬在[npc.her]尿道内部的[style.boldShrink(极度敏感的肉质螺纹)][style.boldShrink(已经消失)]。<br/>"
							+ "[style.boldSex([npc.NamePos]的阴茎尿道不再拥有能催生快感的肉质螺纹！)]"
						+ "</p>");
				}
				break;
			case TENTACLED:
				if(owner.isUrethraFuckable()) {
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name]不由得发出一声受到惊吓的叫喊，一股强大的压力在[npc.her]的[npc.cock]中暴增，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
								+ "试探性地收缩了一下，[npc.she]发现曾经长满[npc.her]尿道内部的[style.boldShrink(蠕动的微型触须)][style.boldShrink(已经全部消失)]。<br/>"
								+ "[style.boldSex([npc.NamePos]阴茎尿道内不再长满触手！)]"
							+ "</p>");
				}
				break;
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]不由得惊叫起来，[npc.she]感觉到一阵麻刺感沿着[npc.her]的[npc.cock]爬升，"
								+ "接着[npc.her]尿道[style.boldShrink(充满肉感的边缘)][style.boldShrink(缩了回去)]，变回了正常的形状。<br/>"
							+ "[style.boldSex([npc.namePos]阴茎尿道的边缘不再膨起，那么有肉感了！)]"
						+ "</p>");
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
	}

	public Set<OrificeModifier> getOrificeModifiers() {
		return orificeModifiers;
	}

	public void clearOrificeModifiers() {
		orificeModifiers.clear();
	}

}
