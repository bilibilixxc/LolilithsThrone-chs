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
public class OrificeMouth implements OrificeInterface {
	
	public int wetness;
	public float capacity;
	public float stretchedCapacity;
	public int depth;
	public int elasticity;
	public int plasticity;
	public boolean virgin;
	public Set<OrificeModifier> orificeModifiers;

	public OrificeMouth(int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.depth = depth;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		
		this.orificeModifiers = new HashSet<>(orificeModifiers);
	}

	public OrificeMouth(OrificeMouth orificeMouthToCopy) {
		this.wetness = orificeMouthToCopy.wetness;
		this.capacity = orificeMouthToCopy.capacity;
		this.stretchedCapacity = orificeMouthToCopy.stretchedCapacity;
		this.depth = orificeMouthToCopy.depth;
		this.elasticity = orificeMouthToCopy.elasticity;
		this.plasticity = orificeMouthToCopy.plasticity;
		this.virgin = orificeMouthToCopy.virgin;
		
		this.orificeModifiers = new HashSet<>(orificeMouthToCopy.orificeModifiers);
	}
	
	@Override
	public Wetness getWetness(GameCharacter owner) {
		if(owner!=null && owner.getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
			return Wetness.SEVEN_DROOLING;
		}
		return Wetness.valueOf(wetness);
	}

	@Override
	public String setWetness(GameCharacter owner, int wetness) {
		if(owner!=null && owner.getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(由于是由"+owner.getBodyMaterial().getName()+"构成的，[npc.namePos][npc.asshole]"+Wetness.SEVEN_DROOLING.getDescriptor()+"的湿润度无法改变……)]</p>");
		}
		
		int oldWetness = this.wetness;
		this.wetness = Math.max(0, Math.min(wetness, Wetness.SEVEN_DROOLING.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int wetnessChange = this.wetness - oldWetness;
		
		if (wetnessChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]喉咙的湿润度没有变化……)]</p>");
		}
		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]忽然开始涎水大作，尽管立刻就全部咽了下去，但仍旧感到嘴巴和喉咙永远变得[style.boldGrow(更加湿润)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + "" + wetnessDescriptor + "喉咙)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]焦躁地叹息，[npc.she]感觉自己的嘴巴和喉咙变得[style.boldShrink(更加干燥)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + "" + wetnessDescriptor + "喉咙)]！"
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]喉咙的直径没有变化……)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.namePos]口中不由得漏出一声惊讶的喘息，[npc.she]感到[npc.her]的喉咙正十分放松地舒展着。"
						+ "片刻之间，那种令人担忧的感觉已经过去了，[npc.she]很快意识到[npc.her]的喉咙内部[style.boldGrow(直径增加)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + "" + capacityDescriptor + "喉咙)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]发出一声叫喊，因为[npc.she]感觉到[npc.her]的喉咙正无法控制地收缩和变紧。"
						+ "片刻之后，那种令人担忧的感觉已经过去了，[npc.she]很快意识到[npc.her]的喉咙内部[style.boldShrink(直径减少)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + "" + capacityDescriptor + "喉咙)]！"
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
		return (int) (owner.getHeightValue() * 0.1f * depth.getDepthPercentage());
	}
	
	@Override
	public int getMaximumPenetrationDepthUncomfortable(GameCharacter owner, OrificeDepth depth) {
		if(Main.game.isElasticityAffectDepthEnabled() && OrificeElasticity.getElasticityFromInt(elasticity).isExtendingUncomfortableDepth()) {
			return (int) (getMaximumPenetrationDepthComfortable(owner, depth) * (float)elasticity/1.5f);
		} else {
			return getMaximumPenetrationDepthComfortable(owner, depth) * 2;
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
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(由于是由"+owner.getBodyMaterial().getName()+"构成的，[npc.namePos]喉咙"+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+"的深度无法改变……)]</p>");
		}
		
		int oldDepth = this.depth;
		this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int depthChange = this.depth - oldDepth;
		
		if(depthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]喉咙的深度没有变化……)]</p>");
		}
		
		String depthDescriptor = getDepth(owner).getDescriptor();
		if(depthChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]不由得发出一声惊讶的喘息，[npc.she]感觉到一种令人不安的压力感从[npc.her]的喉咙深处一直搏动到身体。"
						+ "在[npc.her]的喘息变成痛苦的叫喊前，那股压力感突然消失了，只是让[npc.herHim]出于本能地明白，[npc.her]的喉咙[style.boldGrow(变深)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + "" + depthDescriptor + "喉咙)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]不由得发出一声惊讶的喘息，因为令人不安的紧绷感正从[npc.her]的身体一路向上，直进入[npc.her]的喉咙中。"
						+ "在[npc.her]的喘息变成痛苦的叫喊前，那股感觉突然消失了，只是让[npc.herHim]出于本能地明白，[npc.her]的喉咙[style.boldShrink(变浅)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + "" + depthDescriptor + "喉咙)]！"
					+ "</p>");
		}
	}
	
	@Override
	public OrificeElasticity getElasticity() {
		return OrificeElasticity.getElasticityFromInt(elasticity);
	}

	@Override
	public String setElasticity(GameCharacter owner, int elasticity) {
		int oldElasticity = this.elasticity;
		this.elasticity = Math.max(0, Math.min(elasticity, OrificeElasticity.SEVEN_ELASTIC.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int elasticityChange = this.elasticity - oldElasticity;
		
		if (elasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]喉咙的弹性等级没有变化……)]</p>");
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]难以自抑地发出一声惊叹，[npc.she]感觉到一种奇异的松弛感在[npc.her]的喉咙深处跃动着。"
						+ "来时快去时也快，那感觉离开了，[npc.she]很快意识到[npc.her]的喉咙[style.boldGrow(弹性等级增加)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + "" + elasticityDescriptor + "喉咙)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]难以自抑地发出一声惊叹，[npc.she]感觉到一种奇异的收紧感在[npc.her]的喉咙深处跃动着。"
						+ "来时快去时也快，那感觉离开了，[npc.she]很快意识到[npc.her]的喉咙[style.boldShrink(弹性等级减少)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + "" + elasticityDescriptor + "喉咙)]！"
					+ "</p>");
		}
	}
	
	@Override
	public OrificePlasticity getPlasticity() {
		return OrificePlasticity.getElasticityFromInt(plasticity);
	}

	@Override
	public String setPlasticity(GameCharacter owner, int plasticity) {
		int oldPlasticity = this.plasticity;
		this.plasticity = Math.max(0, Math.min(plasticity, OrificePlasticity.SEVEN_MOULDABLE.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int plasticityChange = this.plasticity - oldPlasticity;
		
		if (plasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]喉咙的可塑性等级没有变化……)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]惊慌地深吸一口气，[npc.she]忽然觉得喉咙深处出现了一阵什么在变硬的奇妙感觉。"
						+ "[npc.she]甚至都来不及恐慌，那种感觉就很快消失了，只让[npc.herHim]本能地知道[npc.her]喉咙的[style.boldGrow(可塑性增加了)]。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + "" + plasticityDescriptor + "喉咙)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]发出一声震惊的喘息，[npc.she]突然感觉到一阵奇特的软化感在[npc.her]的喉咙深处鼓动。"
						+ "[npc.she]甚至都来不及恐慌，那种感觉就很快消失了，只让[npc.herHim]本能地知道[npc.her]的喉咙[style.boldShrink(可塑性等级减少)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + "" + plasticityDescriptor + "喉咙)]！"
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
		
		orificeModifiers.add(modifier);
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		switch(modifier) {
			case MUSCLE_CONTROL:
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name]不禁发出一小声尖叫，一阵强烈的压力在[npc.her]的喉咙深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
								+ "[npc.she]试探性地收缩了一下，发现喉咙内部现在有着大量可以自由控制的[style.boldGrow(肌肉)]，"
									+ "能够让[npc.she]用来巧妙地抓住并挤榨任何插入进来的物体。<br/>"
								+ "[style.boldSex([npc.NamePos]的喉咙内现在拥有一系列复杂精细的肌肉！)]"
							+ "</p>");
			case RIBBED:
					return UtilText.parse(owner,
							"<p>"
							+ "[npc.Name]不禁发出一小声尖叫，一阵强烈的压力在[npc.her]的喉咙深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
							+ "稍微摸了一下，[npc.she]发现[npc.her]的喉咙内部现在衬着[style.boldGrow(极度敏感的肉质螺纹)]，"
								+ "在接受刺激时将带来极致的快感。<br/>"
							+ "[style.boldSex([npc.NamePos]的喉咙现在拥有了能催生快感的肉质螺纹！)]"
						+ "</p>");
			case TENTACLED:
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name]不禁发出一小声尖叫，一阵强烈的压力在[npc.her]的喉咙深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
								+ "试探性地收缩了一下，[npc.she]发现[npc.her]的喉咙内部现在衬着[style.boldGrow(蠕动的微型触须)]，而且并不完全处于[npc.she]的控制之下。<br/>"
								+ "[style.boldSex([npc.namePos]的喉咙内部现在布满了细小的触手，自行蜿蜒扭动着！)]"
							+ "</p>");
			case PUFFY:
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name]不由得叫了一声，[npc.she]感觉到一阵麻刺感席卷了[npc.her]的嘴巴，随后[npc.her]的嘴唇便[style.boldGrow(略微膨起)]了。<br/>"
								+ "[style.boldSex([npc.NamePos]的嘴唇现在十分有肉感！)]"
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
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name]不禁发出一小声尖叫，一阵强烈的压力在[npc.her]的喉咙深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
								+ "[npc.she]试着缩紧了一下，才发现喉咙内部已经[style.boldShrink(失去了额外的肌肉)]。<br/>"
								+ "[style.boldSex([npc.NamePos]的喉咙不再拥有一系列复杂精细的肌肉！)]"
							+ "</p>");
			case RIBBED:
					return UtilText.parse(owner,
							"<p>"
							+ "[npc.Name]不禁发出一小声尖叫，一阵强烈的压力在[npc.her]的喉咙深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
							+ "[npc.she]稍微摸了摸喉咙，发现曾经衬在喉咙内部的[style.boldShrink(极度敏感的肉质螺纹)][style.boldShrink(已经消失)]<br/>"
							+ "[style.boldSex([npc.NamePos]的喉咙里不再拥有能催生快感的肉质螺纹了！)]"
						+ "</p>");
			case TENTACLED:
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name]不禁发出一小声尖叫，一阵强烈的压力在[npc.her]的喉咙深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
								+ "试探性地收缩了一下，[npc.she]发现曾经长满[npc.her]喉咙内部的[style.boldShrink(蠕动的微型触须)][style.boldShrink(已经全部消失)]。<br/>"
								+ "[style.boldSex([npc.namePos]的喉咙内部不再长满触手！)]"
							+ "</p>");
			case PUFFY:
					return UtilText.parse(owner,
							"<p>"
								+ "[npc.Name]不免得叫了一声，[npc.she]感觉到一阵麻刺感席卷了[npc.her]的嘴唇，随后[npc.her]的嘴唇便突然[style.boldShrink(瘪了一点)]，变成正常的样子了。<br/>"
								+ "[style.boldSex([npc.NamePos]的嘴唇不再那么蓬松柔软！)]"
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
