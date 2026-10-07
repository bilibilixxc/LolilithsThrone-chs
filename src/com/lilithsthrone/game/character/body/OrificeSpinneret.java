package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.4
 * @version 0.4.9.7
 * @author Innoxia
 */
public class OrificeSpinneret implements OrificeInterface {

	public int wetness;
	public float capacity;
	public float stretchedCapacity;
	public int depth;
	public int elasticity;
	public int plasticity;
	public boolean virgin;
	public Set<OrificeModifier> orificeModifiers;

	public OrificeSpinneret() {
		this.wetness = Wetness.ONE_SLIGHTLY_MOIST.getValue();
		this.capacity = Capacity.ONE_EXTREMELY_TIGHT.getMedianValue();
		this.stretchedCapacity = this.capacity;
		this.depth = OrificeDepth.TWO_AVERAGE.getValue();
		this.elasticity = OrificeElasticity.FOUR_LIMBER.getValue();
		this.plasticity = OrificePlasticity.THREE_RESILIENT.getValue();
		this.virgin = true;

		this.orificeModifiers = new HashSet<>();
	}

	public OrificeSpinneret(OrificeSpinneret orificeSpinneretToCopy) {
		this.wetness = orificeSpinneretToCopy.wetness;
		this.capacity = orificeSpinneretToCopy.capacity;
		this.stretchedCapacity = orificeSpinneretToCopy.stretchedCapacity;
		this.depth = orificeSpinneretToCopy.depth;
		this.elasticity = orificeSpinneretToCopy.elasticity;
		this.plasticity = orificeSpinneretToCopy.plasticity;
		this.virgin = orificeSpinneretToCopy.virgin;

		this.orificeModifiers = new HashSet<>(orificeSpinneretToCopy.orificeModifiers);
	}
	
	public String getDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<>();
		
		for(OrificeModifier om : this.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		
		String wetnessDescriptor = this.getWetness(owner).getDescriptor();
		if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
			if(Main.sex.hasLubricationTypeFromAnyone(owner, SexAreaOrifice.VAGINA)) {
				wetnessDescriptor = "湿润";
			}
		}
		descriptorList.add(wetnessDescriptor);
		
		descriptorList.add(Capacity.getCapacityFromValue(this.getStretchedCapacity()).getDescriptor().replaceAll(" ", "-"));

		return Util.randomItemFrom(descriptorList);
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
		if(owner!=null && !owner.hasSpinneret()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]缺少丝囊，所以无事发生……)]</p>");
		}
		if(owner!=null && owner.getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(由于是由"+owner.getBodyMaterial().getName()+"构成的，[npc.namePos]丝囊"+Wetness.SEVEN_DROOLING.getDescriptor()+"的湿润度无法改变……)]</p>");
		}
		
		int oldWetness = this.wetness;
		this.wetness = Math.max(0, Math.min(wetness, Wetness.SEVEN_DROOLING.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int wetnessChange = this.wetness - oldWetness;
		
		if(wetnessChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]丝囊的湿润度没有变化……)]</p>");
		}
		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "，[npc.NamePos]丝囊周围凝结了一圈水汽，[npc.she]睁大了眼睛，"
							+ "[npc.she]发出[npc.a_moan+]，[npc.she]意识到它在自我润滑并[style.boldGrow(变得更湿润)]了。<br/>"
						+ "转化很快过去了，[npc.herHim]得到了[style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + "" + wetnessDescriptor + "丝囊)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]难受地到处踱步，发出一声沮丧的呻吟，[npc.she]感觉到[npc.her]的丝囊[style.boldShrink(变得更干燥)]了。<br/>"
						+ "转化很快过去了，[npc.herHim]得到了[style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + "" + wetnessDescriptor + "丝囊)]！"
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
		if (owner!=null && !owner.hasSpinneret()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]缺少丝囊，所以无事发生……)]</p>");
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]丝囊的直径没有变化……)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.namePos]口中不由得漏出一声惊讶的喘息，[npc.she]感到[npc.her]的丝囊正在不受控制地扩张和伸展。"
						+ "片刻之间，那感觉已经过去了，[npc.she]很快意识到[npc.her]的丝囊内部[style.boldGrow(直径增加)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + "" + capacityDescriptor + "丝囊)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]发出一声尖叫，[npc.she]感觉到[npc.her]的丝囊正无法控制地收缩和变紧。"
						+ "片刻之后，那感觉已经过去了，[npc.she]很快意识到[npc.her]的丝囊内部[style.boldShrink(直径减少)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + "" + capacityDescriptor + "丝囊)]！"
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
	public OrificeDepth getMinimumDepthForSizeComfortable(GameCharacter owner, int insertionSize) {
		OrificeDepth depth = OrificeDepth.ONE_SHALLOW;
		while((int) (owner.getHeightValue() * 0.1f * depth.getDepthPercentage())<insertionSize) {
			if(depth == OrificeDepth.SEVEN_FATHOMLESS) {
				return depth;
			}
			depth = OrificeDepth.getDepthFromInt(depth.getValue()+1);
		}
		return depth;
	}

	@Override
	public OrificeDepth getMinimumDepthForSizeUncomfortable(GameCharacter owner, int insertionSize) {
		OrificeDepth depth = OrificeDepth.ONE_SHALLOW;
		while((int) ((owner.getHeightValue() * 0.1f * depth.getDepthPercentage())*1.5f)<insertionSize) {
			if(depth == OrificeDepth.SEVEN_FATHOMLESS) {
				return depth;
			}
			depth = OrificeDepth.getDepthFromInt(depth.getValue()+1);
		}
		return depth;
	}
	
	@Override
	public int getMaximumPenetrationDepthComfortable(GameCharacter owner, OrificeDepth depth) { // Same formula as vagina depth
		return (int) (owner.getHeightValue() * 0.1f * depth.getDepthPercentage());
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
		if (owner!=null && !owner.hasSpinneret()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]缺少丝囊，所以无事发生……)]</p>");
		}
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(由于是由"+owner.getBodyMaterial().getName()+"构成的，[npc.namePos]丝囊"+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+"的深度无法改变……)]</p>");
		}
		
		int oldDepth = this.depth;
		this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int depthChange = this.depth - oldDepth;
		
		if(depthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]丝囊的深度没有变化……)]</p>");
		}
		
		String depthDescriptor = getDepth(owner).getDescriptor();
		if(depthChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]不由得发出一声惊讶的喘息，[npc.she]感觉到一种令人不安的压力感从[npc.her]的丝囊深处一直搏动到下腹。"
						+ "在[npc.her]的喘息变成痛苦的叫喊前，那股压力感突然消失了，只是让[npc.herHim]出于本能地明白，[npc.her]的丝囊[style.boldGrow(变深)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + "" + depthDescriptor + "丝囊)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]不由得发出一声惊讶的喘息，因为一阵令人不安的紧绷感正从[npc.her]的下腹部一路直下，直进入[npc.her]的丝囊中。"
						+ "在[npc.her]的喘息变成痛苦的叫喊前，那股感觉突然消失了，只是让[npc.herHim]出于本能地明白，[npc.her]的丝囊[style.boldShrink(变浅)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + "" + depthDescriptor + "丝囊)]！"
					+ "</p>");
		}
	}
	
	@Override
	public OrificeElasticity getElasticity() {
		return OrificeElasticity.getElasticityFromInt(elasticity);
	}

	@Override
	public String setElasticity(GameCharacter owner, int elasticity) {
		if (owner!=null && !owner.hasSpinneret()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]缺少丝囊，所以无事发生……)]</p>");
		}
		int oldElasticity = this.elasticity;
		this.elasticity = Math.max(0, Math.min(elasticity, OrificeElasticity.SEVEN_ELASTIC.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int elasticityChange = this.elasticity - oldElasticity;
		
		if (elasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]丝囊的弹性等级没有变化……)]</p>");
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]难以自抑地发出一声惊叹，[npc.she]感觉到一种奇异的松弛感在[npc.her]的丝囊深处跃动着。"
						+ "来时快去时也快，那感觉离开了，[npc.she]很快意识到[npc.her]的丝囊[style.boldGrow(弹性等级增加)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + "" + elasticityDescriptor + "丝囊)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]难以自抑地发出一声惊叹，[npc.she]感觉到一种奇异的收紧感在[npc.her]的丝囊深处跃动着。"
						+ "来时快去时也快，那感觉离开了，[npc.she]很快意识到[npc.her]的丝囊[style.boldShrink(弹性等级减少)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + "" + elasticityDescriptor + "丝囊)]！"
					+ "</p>");
		}
	}
	
	@Override
	public OrificePlasticity getPlasticity() {
		return OrificePlasticity.getElasticityFromInt(plasticity);
	}

	@Override
	public String setPlasticity(GameCharacter owner, int plasticity) {
		if (owner!=null && !owner.hasSpinneret()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]缺少丝囊，所以无事发生……)]</p>");
		}
		int oldPlasticity = this.plasticity;
		this.plasticity = Math.max(0, Math.min(plasticity, OrificePlasticity.SEVEN_MOULDABLE.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int plasticityChange = this.plasticity - oldPlasticity;
		
		if (plasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]丝囊的可塑性等级没有变化……)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]发出一声震惊的喘息，[npc.she]突然感觉到一阵奇特的硬化感在[npc.her]的丝囊深处搏动。"
						+ "[npc.she]甚至都来不及恐慌，那种感觉就很快消失了，只让[npc.herHim]本能地知道[npc.her]的丝囊[style.boldGrow(可塑性等级增加)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + "" + plasticityDescriptor + "丝囊)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]发出一声震惊的喘息，[npc.she]突然感觉到一阵奇特的软化感在[npc.her]的丝囊深处鼓动。"
						+ "[npc.she]甚至都来不及恐慌，那种感觉就很快消失了，只让[npc.herHim]本能地知道[npc.her]的丝囊[style.boldShrink(可塑性等级减少)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + "" + plasticityDescriptor + "丝囊)]！"
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
		
		if(!isCharacterInitialised(owner) || owner.getBody()==null) {
			return "";
		}
		if(!owner.hasSpinneret()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(无事发生，因为[npc.name]并没有丝囊……)]</p>");
		}
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]难以自抑地发出一声[npc.a_moan+]，一阵强烈的压力在[npc.her]的丝囊深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
							+ "试探性地收缩了一下，[npc.she]发现[npc.her]的丝囊内壁现在衬着[style.boldGrow(额外的肌肉)]，"
								+ "能够让[npc.she]用来巧妙地抓住并挤榨任何插入进来的物体。<br/>"
							+ "[style.boldSex([npc.NamePos]的丝囊内现在拥有一系列复杂精细的肌肉！)]"
						+ "</p>");
				
			case RIBBED:
				return UtilText.parse(owner,
						"<p>"
						+ "[npc.Name]难以自抑地发出一声[npc.a_moan+]，一阵强烈的压力在[npc.her]的丝囊深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
						+ "稍微摸了一下，[npc.she]发现[npc.her]的丝囊内部现在衬着[style.boldGrow(极度敏感的肉质螺纹)]，"
							+ "在接受刺激时将带来极致的快感。<br/>"
						+ "[style.boldSex([npc.NamePos]的丝囊现在拥有了能催生快感的肉质螺纹！)]"
					+ "</p>");
				
			case TENTACLED:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]难以自抑地发出一声[npc.a_moan+]，一阵强烈的压力在[npc.her]的丝囊深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
							+ "试探性地收缩了一下，[npc.she]发现[npc.her]的丝囊内部现在衬着[style.boldGrow(蠕动的微型触须)]，而且并不完全处于[npc.she]的控制之下。<br/>"
							+ "[style.boldSex([npc.namePos]的丝囊内部现在长满了小触手，它们将以自己的意愿蠕动着爱抚任何侵入的物体！)]"
						+ "</p>");
					
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]情不自禁地发出[npc.a_moan+]，[npc.she]感觉到一阵麻刺感席卷了[npc.her]的丝囊，接着它突然[style.boldGrow(变得蓬松起来)]。<br/>"
							+ "[style.boldSex([npc.namePos]的丝囊现在变得十分蓬松柔软！)]"
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
							+ "[npc.Name]不禁发出一声愕然的尖叫，一阵强烈的压力在[npc.her]的丝囊深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
							+ "试探性地收缩了一下，[npc.she]发现[npc.her]的丝囊内部现在[style.boldShrink(失去了额外的肌肉)]。<br/>"
							+ "[style.boldSex([npc.NamePos]的丝囊内不再拥有一系列复杂精细的肌肉！)]"
						+ "</p>");
					
			case RIBBED:
				return UtilText.parse(owner,
						"<p>"
						+ "[npc.Name]不禁发出一声愕然的尖叫，一阵强烈的压力在[npc.her]的丝囊深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
						+ "稍微摸了一下，[npc.she]发现曾经衬在[npc.her]丝囊内壁的[style.boldShrink(极度敏感的肉质螺纹)][style.boldShrink(已经消失)]。<br/>"
						+ "[style.boldSex([npc.NamePos]的丝囊不再拥有能催生快感的肉质螺纹！)]"
					+ "</p>");
					
			case TENTACLED:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]不禁发出一声愕然的尖叫，一阵强烈的压力在[npc.her]的丝囊深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
							+ "试探性地收缩了一下，[npc.she]发现曾经长满[npc.her]丝囊内部的[style.boldShrink(蠕动的微型触须)][style.boldShrink(已经全部消失)]。<br/>"
							+ "[style.boldSex([npc.NamePos]丝囊内不再长满触手！)]"
						+ "</p>");
					
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]不由得惊叫起来，[npc.she]感觉到一阵麻刺感席卷了[npc.her]的丝囊，"
								+ "接着它突然[style.boldShrink(缩短)]回到了更平均的尺寸。<br/>"
							+ "[style.boldSex([npc.NamePos]丝囊不再那么蓬松柔软！)]"
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
