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
public class OrificeAnus implements OrificeInterface {
	
	public int wetness;
	public float capacity;
	public float stretchedCapacity;
	public int depth;
	public int elasticity;
	public int plasticity;
	public boolean virgin;
	protected Set<OrificeModifier> orificeModifiers;

	public OrificeAnus(int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.depth = depth;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		
		this.orificeModifiers = new HashSet<>(orificeModifiers);
	}

	public OrificeAnus(OrificeAnus orificeAnusToCopy) {
		this.wetness = orificeAnusToCopy.wetness;
		this.capacity = orificeAnusToCopy.capacity;
		this.stretchedCapacity = orificeAnusToCopy.stretchedCapacity;
		this.depth = orificeAnusToCopy.depth;
		this.elasticity = orificeAnusToCopy.elasticity;
		this.plasticity = orificeAnusToCopy.plasticity;
		this.virgin = orificeAnusToCopy.virgin;
		
		this.orificeModifiers = new HashSet<>(orificeAnusToCopy.orificeModifiers);
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.asshole]的湿润度没有变化……)]</p>");
		}

		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			return UtilText.parse(owner,
						"<p>"
							+ "，[npc.NamePos]肛门周围凝结了一圈水汽，[npc.she]睁大了眼睛，"
								+ "当[npc.she]意识到屁股在自我润滑并[style.boldGrow(变得更湿润)]时，[npc.she]不禁发出一声深深的[npc.moan]。<br/>"
							+ "转化很快过去了，[npc.herHim]得到了[style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + "" + wetnessDescriptor + "肛门)]！"
						+ "</p>");
			
		} else {
			return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]难受地到处踱步，发出一声沮丧的呻吟，[npc.she]感觉到[npc.her]的屁股[style.boldShrink(变得更干燥)]了。<br/>"
							+ "转化很快过去了，[npc.herHim]得到了[style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + "" + wetnessDescriptor + "肛门)]！"
						+ "</p>");
		}
	}

	@Override
	public Capacity getCapacity() {
		return Capacity.getCapacityFromValue(capacity);
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.asshole]的直径没有变化……)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.namePos]口中不由得漏出一声惊讶的喘息，[npc.she]感到[npc.her]的肛门正在不受控制地扩张和伸展。"
						+ "片刻之间，那感觉已经过去了，[npc.she]很快意识到[npc.her]的肛门内部[style.boldGrow(直径增加)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + "" + capacityDescriptor + "肛门)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]发出一声尖叫，[npc.she]感觉到[npc.her]的肛门正无法控制地收缩和变紧。"
						+ "片刻之后，那感觉已经过去了，[npc.she]很快意识到[npc.her]的肛门内部[style.boldShrink(直径减少)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + "" + capacityDescriptor + "肛门)]！"
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
		return (int) (owner.getHeightValue() * 0.12f * depth.getDepthPercentage());
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
					"<p style='text-align:center;'>[style.colourSex(由于是由"+owner.getBodyMaterial().getName()+"构成的，[npc.namePos]"+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+"[npc.asshole]的深度无法改变……)]</p>");
		}
		
		int oldDepth = this.depth;
		this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int depthChange = this.depth - oldDepth;
		
		if(depthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.asshole]的深度没有变化……)]</p>");
		}
		
		String depthDescriptor = getDepth(owner).getDescriptor();
		if(depthChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]不由得发出一声惊讶的喘息，[npc.she]感觉到一种令人不安的压力感一直在肛门深处搏动。"
						+ "这股压力很快便消失了，[npc.her]的喘息却还是化作了惊恐的叫喊，只让[npc.herHim]本能地知道[npc.her]的肛门[style.boldGrow(更深了)]。<br/>"
						+ "[npc.Name]现在拥有[style.boldSex(" + depthDescriptor + "的肛门)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]不由得发出一声惊讶的喘息，[npc.she]感觉到一种令人不安的紧缩感一直在肛门深处搏动。"
						+ "这股压力很快便消失了，[npc.her]的喘息却还是化作了惊恐的叫喊，只让[npc.herHim]本能地知道[npc.her]的肛门[style.boldShrink(更浅了)]。<br/>"
						+ "[npc.Name]现在拥有[style.boldSex(" + depthDescriptor + "的肛门)]！"
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.asshole]的弹性没有变化……)]</p>");
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]不由得发出一声惊讶的喘息，感觉到一种奇异的松弛感一直在肛门深处搏动。"
						+ "可那感觉来得快去得也快，[npc.she]立马就意识到[npc.her]肛门的[style.boldGrow(弹性登记提高)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + elasticityDescriptor + "的肛门)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]不由得发出一声惊讶的喘息，感觉到一种奇异的收紧感一直在肛门深处搏动。"
						+ "可那感觉来得快去得也快，[npc.she]立马就意识到[npc.her]肛门的[style.boldShrink(弹性等级降低)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + elasticityDescriptor + "的肛门)]！"
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.asshole]的可塑性等级没有变化……)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]吓得喘了一声，感觉到一种奇异的硬化感一直在肛门深处搏动。"
						+ "[npc.she]还没来得及慌张，那感觉就散去了，只让[npc.herHim]本能地知道[npc.her]肛门的[style.boldGrow(可塑性等级增加)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + plasticityDescriptor + "的肛门)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]吓得喘了一声，感觉到一种奇异的软化感一直在肛门深处搏动。"
						+ "[npc.she]还没来得及慌张，那感觉就散去了，只让[npc.herHim]本能地知道[npc.her]肛门的[style.boldShrink(可塑性等级降低)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + plasticityDescriptor + "的肛门)]！"
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
							+ "[npc.Name]难以自抑地发出一声[npc.a_moan+]，一阵强烈的压力在[npc.her]的屁股深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
							+ "[npc.she]试探性地收缩了一下，发现[npc.her]肛门的内壁现在有着大量可以自由控制的[style.boldGrow(肌肉)]，"
								+ "能够让[npc.she]用来巧妙地抓住并挤榨任何插入进来的物体。<br/>"
							+ "[style.boldSex([npc.NamePos]的肛门内现在拥有一系列复杂精细的肌肉！)]"
						+ "</p>");
				
			case RIBBED:
				return UtilText.parse(owner,
						"<p>"
						+ "[npc.Name]难以自抑地发出一声[npc.a_moan+]，一阵强烈的压力在[npc.her]的屁股深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
						+ "稍微摸了一下，[npc.she]发现[npc.her]的肛门内部现在衬着[style.boldGrow(极度敏感的肉质螺纹)]，"
							+ "在接受刺激时将带来极致的快感。<br/>"
						+ "[style.boldSex([npc.NamePos]的肛门现在拥有了能催生快感的肉质螺纹！)]"
					+ "</p>");
				
			case TENTACLED:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]难以自抑地发出一声[npc.a_moan+]，一阵强烈的压力在[npc.her]的屁股深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
							+ "[npc.she]试探性地收缩了一下，发现肛门内部现在衬着[style.boldGrow(蠕动的微型触须)]，而且并不完全处于[npc.she]的控制之下。<br/>"
							+ "[style.boldSex([npc.namePos]的肛门内部现在长满了小触手，自行蠕动着，爱抚着任何侵入的物体！)]"
						+ "</p>");
					
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]不禁大叫一声，[npc.she]感觉到一阵麻刺感席卷了[npc.her]的[npc.ass]，"
								+ "接着[npc.her]的[npc.asshole]边缘突然[style.boldGrow(略微膨起)]，形成了一个松软的甜甜圈似的环。<br/>"
							+ "[style.boldSex([npc.namePos]肛门的边缘略微膨起，变得更加柔软了！)]"
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
							+ "[npc.Name]不禁发出一声愕然的尖叫，一阵强烈的压力在[npc.her]的屁股深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
							+ "[npc.she]试着缩紧了一下，才发现肛门内部已经[style.boldShrink(失去了额外的肌肉)]。<br/>"
							+ "[style.boldSex([npc.NamePos]的肛门内不再拥有一系列复杂精细的肌肉！)]"
						+ "</p>");
					
			case RIBBED:
				return UtilText.parse(owner,
						"<p>"
						+ "[npc.Name]不禁发出一声愕然的尖叫，一阵强烈的压力在[npc.her]的屁股深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
						+ "稍微摸了一下，[npc.she]发现[npc.her]发现曾经衬在肛门内部的[style.boldShrink(极度敏感的肉质螺纹)][style.boldShrink(已经消失)]。<br/>"
						+ "[style.boldSex([npc.NamePos]的肛门里不再拥有能催生快感的肉质螺纹了！)]"
					+ "</p>");
					
			case TENTACLED:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]不禁发出一声愕然的尖叫，一阵强烈的压力在[npc.her]的屁股深处迅速增长，但[npc.her]甚至都还没反应过来，这感觉就迅速地消散了。"
							+ "试探性地收缩了一下，[npc.she]发现曾经长满肛门内部的[style.boldShrink(蠕动的微型触须)][style.boldShrink(已经全部消失)]。<br/>"
							+ "[style.boldSex([npc.NamePos]肛门内不再长满触手！)]"
						+ "</p>");
					
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]不禁大叫一声，[npc.she]感觉到一阵麻刺感席卷了[npc.her]的[npc.ass]，"
								+ "接着[npc.her][npc.asshole][style.boldShrink(充满肉感的边缘)][style.boldShrink(缩了回去)]，变回了正常的形状。<br/>"
							+ "[style.boldSex([npc.namePos]肛门的边缘不再膨起，那么有肉感了！)]"
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
