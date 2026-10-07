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
public class OrificeNipples implements OrificeInterface {

	protected int wetness;
	public float capacity;
	public float stretchedCapacity;
	protected int depth;
	protected int elasticity;
	protected int plasticity;
	protected boolean virgin;
	public Set<OrificeModifier> orificeModifiers;

	protected boolean crotchNipples;

	public OrificeNipples(int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin, boolean crotchNipples, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.depth = depth;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		
		this.orificeModifiers = new HashSet<>(orificeModifiers);
		
		this.crotchNipples = crotchNipples;
	}

	public OrificeNipples(OrificeNipples orificeNipplesToCopy) {
		this.wetness = orificeNipplesToCopy.wetness;
		this.capacity = orificeNipplesToCopy.capacity;
		this.stretchedCapacity = orificeNipplesToCopy.stretchedCapacity;
		this.depth = orificeNipplesToCopy.depth;
		this.elasticity = orificeNipplesToCopy.elasticity;
		this.plasticity = orificeNipplesToCopy.plasticity;
		this.virgin = orificeNipplesToCopy.virgin;
		
		this.orificeModifiers = new HashSet<>(orificeNipplesToCopy.orificeModifiers);
		
		this.crotchNipples = orificeNipplesToCopy.crotchNipples;
	}
	
	@Override
	public Wetness getWetness(GameCharacter owner) {
		return owner.getBreastMilkStorage().getAssociatedWetness();
	}
	
	@Override
	/**<b>DO NOT USE THIS. NIPPLE WETNESS IS DETERMINED BY BREAST LACTATION.</b>*/
	public String setWetness(GameCharacter owner, int wetness) {
		throw new IllegalAccessError("Nipple wetness was attempted to be set manually!");
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
		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		if (capacityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]的"+nipplesString+"容量没有改变……)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			if(oldCapacity == 0) { // Getting fuckable nipples:
				return UtilText.parse(owner,
						"<p>" 
							+ "[npc.Name]不舒服地蠕动着，"+nipplesString+"变得异常坚硬且敏感。"
							+ "一种奇怪的压力在躯干内积聚，[npc.she]深深地叹了口气，"+breastsString+"内部发生了剧烈的转化。"
							+ "[npc.namePos]很快被"+breastsString+"内不断增加的压力所淹没，口中不断溢出的叹息变成了[npc.a_moan+]，"
								+ "[npc.her]的"+nipplesString+"突然张开，露出了形成途中可插入的深邃通道。<br/>"
							+ "[npc.Name]现在拥有[style.boldSex(" + capacityDescriptor + "可以插入的"+nipplesString+")]！"
						+ "</p>");
				
			} else { // Expanding fuckable nipples:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]发出一声[npc.a_moan+]，[npc.she]感觉到一股熟悉的压力感从[npc.her]那可插入的"+nipplesString+"中升起，然后它们突然间[style.boldGrow(成长)]得更宽且更深了。<br/>"
							+ "[npc.Name]现在拥有[style.boldSex(" +capacityDescriptor+ "的"+nipplesString+")]！"
						+ "</p>");
			}
			
		} else {
			if(capacity == 0) { // Losing fuckable nipples:
				return UtilText.parse(owner,
						"<p>" 
							+ "[npc.Name]不舒服地蠕动着，"+nipplesString+"变得异常坚硬且敏感。"
							+ "一股巨大的压力从[npc.her]体内升起，接着，[npc.she]发出了一声沉重的叹息，[npc.her]的"+nipplesString+"突然间[style.boldShrink(缩紧)]，失去了它们可被插入的特性。<br/>"
							+ "[npc.Name]现在拥有[style.boldSex(" + capacityDescriptor + "，不可插入的"+nipplesString+")]！"
						+ "</p>");
				
			} else { // Shrinking fuckable nipples:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]发出一声[npc.a_moan+]，[npc.she]感觉到一股熟悉的压力感从[npc.her]那可插入的"+nipplesString+"中升起， 然后它们突然间[style.boldShrink(收缩)]起来，并且变得更紧了。<br/>"
							+ "[npc.Name]现在拥有[style.boldSex(" +capacityDescriptor+ "的"+nipplesString+")]！"
						+ "</p>");
			}
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
		// Calculate breast depth as simply owner.getBreastSize().getMeasurement()
		if(this.isCrotchNipples()) {
			return (int) ((owner.getBreastCrotchSize().getMeasurement()) * 0.5f * depth.getDepthPercentage());
		}
		return (int) ((owner.getBreastSize().getMeasurement()) * 0.5f * depth.getDepthPercentage());
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
		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(由于其材质为"+owner.getBodyMaterial().getName()+"，[npc.namePos]"+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+""+nipplesString+"的深度无法被改变……)]</p>");
		}
		
		int oldDepth = this.depth;
		this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int depthChange = this.depth - oldDepth;
		
		if(depthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]的"+nipplesString+"深度没有被改变……)]</p>");
		}
		
		String depthDescriptor = getDepth(owner).getDescriptor();
		if(depthChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]不由自主地发出一声惊讶的喘息，[npc.she]感觉到一股令人震惊的压力正在[npc.her]的"+breastsString+"深处脉动。"
						+ "在[npc.her]的喘息变为悲伤的啜泣之前，那股压力突然消失了。[npc.herHim]本能地明白，自己的"+nipplesString+"[style.boldGrow(变深)]了。<br/>"
						+ "[npc.Name]现在拥有[style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + depthDescriptor + "的"+nipplesString+")]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]不由自主地发出一声惊讶的喘息，感觉自己的"+breastsString+"深处产生了一种令人震怖的紧缩感。"
						+ "在[npc.her]的喘息变为悲伤的叫喊之前，那股压力突然消失了，[npc.herHim]本能地明白，自己的"+nipplesString+"[style.boldShrink(变浅)]了。<br/>"
						+ "[npc.Name]现在拥有[style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + depthDescriptor + "的"+nipplesString+")]！"
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
		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		if (elasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]的"+nipplesString+"弹性等级没有被改变……)]</p>");
		}
		
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]不由自主地发出一声惊讶的喘息，[npc.she]感觉到[npc.her]的"+breastsString+"深处产生了一种奇异的松懈感。"
						+ "那感觉来得快，去得也快，[npc.she]很快意识到[npc.her]的"+nipplesString+"[style.boldGrow(获得了些许弹性)]。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + "" + elasticityDescriptor + ""+nipplesString+")]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]不由自主地发出一声惊讶的喘息，感觉到一种奇异的紧绷感在"+breastsString+"深处悸动。"
						+ "那感觉来得快，去得也快，[npc.she]很快意识到自己的"+nipplesString+"[style.boldShrink(失去了些许弹性)]。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + "" + elasticityDescriptor + ""+nipplesString+")]！"
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
		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		if (plasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]的"+nipplesString+"可塑性等级没有被改变……)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]发出一声震惊的喘息，[npc.she]突然感觉到一阵奇特的硬化感在[npc.her]的"+breastsString+"深处搏动。"
						+ "[npc.she]甚至都来不及恐慌，那种感觉就很快消失了，只让[npc.herHim]本能地知道[npc.her]的"+nipplesString+"[style.boldGrow(可塑性等级增加)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + plasticityDescriptor + "的"+nipplesString+")]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name]发出一声震惊的喘息，[npc.she]突然感觉到一阵奇特的软化感在[npc.her]的"+breastsString+"深处鼓动。"
						+ "[npc.she]甚至都来不及恐慌，那种感觉就很快消失了，只让[npc.herHim]本能地知道[npc.her]的"+nipplesString+"[style.boldShrink(可塑性等级减少)]了。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + plasticityDescriptor + "的"+nipplesString+")]！"
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

	public boolean isCrotchNipples() {
		return crotchNipples;
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

		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股强烈的压力感在[npc.namePos]的"+breastsString+"深处升起，[npc.she]不禁发出了[npc.a_moan+]，感觉大量[style.boldGrow(额外的肌肉)]"
									+ "在[npc.her]"+nipplesString+"的内部催生出来。"
								+ "[npc.sheIs]发现自己竟然能够随意地控制这些肌肉，足以巧妙地抓住并挤榨任何插入进来的物体，让[npc.she]大吃一惊。<br/>"
								+ "[style.boldSex([npc.namePos]的"+nipplesString+"内现在拥有一系列复杂精细的肌肉！)]"
							+ "</p>");
				}
				break;
			case RIBBED:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股强烈的压力感在[npc.namePos]的"+breastsString+"深处升起，[npc.she]不禁发出了[npc.a_moan+]，感觉大量[style.boldGrow(高度敏感的肉质螺纹)]"
									+ "在[npc.her]"+nipplesString+"的内部催生出来。"
								+ "[npc.she]往"+breastsString+"那里摸了摸，一阵快感顿时传遍了全身，而新生螺纹相互摩擦的感觉，竟又让[npc.her]冒出一声[npc.moan+]。<br/>"
								+ "[style.boldSex([npc.namePos]的"+nipplesString+"内部现在拥有了能催生快感的肉质螺纹！)]"
							+ "</p>");
				}
				break;
			case TENTACLED:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股强烈的压力感在[npc.namePos]的"+breastsString+"深处升起，[npc.she]不禁发出了[npc.a_moan+]，感觉到奇妙的刺痒感又出现在"+nipplesString+"里。"
								+ "这种感觉愈发强烈，[npc.she]忍不住惊呼了一声，忽然发觉"+nipplesString+"的内部现在布满了"
										+ "[style.boldGrow(扭动的细小触手)]，而且自己难以控制。<br/>"
								+ "[style.boldSex([npc.namePos]"+nipplesString+"的内部现在覆盖着细小的触手，自行蜿蜒扭动着！)]"
							+ "</p>");
				}
				break;
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]不由得叫了一声，[npc.she]感觉到一阵麻刺感席卷了[npc.her]的"+nipplesString+"，随后便[style.boldGrow(略微膨起)]了。<br/>"
							+ "[style.boldSex([npc.NamePos]的"+nipplesString+"现在十分有肉感！)]"
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

		String nipplesString = isCrotchNipples()?"[npc.crotchNipples]":"[npc.nipples]";
		String breastsString = isCrotchNipples()?"[npc.crotchBoobs]":"[npc.breasts]";
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股暖流缓慢涌上[npc.namePos]的躯干，[npc.her][npc.lips]间不禁发出[npc.moan]，只觉得"+breastsString+"里[style.boldShrink(额外的肌肉)]"
									+ "化作了原样。<br/>"
								+ "[style.boldSex([npc.namePos]的"+nipplesString+"内不再拥有一系列复杂精细的肌肉！)]"
							+ "</p>");
				}
				break;
			case RIBBED:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股暖流缓慢涌上[npc.namePos]的躯干，[npc.her][npc.lips]间不禁发出[npc.moan]"
									+ "，只觉得"+breastsString+"里[style.boldShrink(能够催生快感的肉质螺纹)]化为了原样。<br/>"
								+ "[style.boldSex([npc.namePos]"+nipplesString+"的内部不再有螺纹了！)]"
							+ "</p>");
				}
				break;
					
			case TENTACLED:
				if(isCrotchNipples()?owner.isBreastCrotchFuckableNipplePenetration():owner.isBreastFuckableNipplePenetration()) {
					return UtilText.parse(owner,
							"<p>"
								+ "一股暖流缓慢涌上[npc.namePos]的躯干，[npc.her][npc.lips]间不禁发出[npc.moan]"
									+ "，只觉得"+breastsString+"里[style.boldShrink(扭动的细小触手)]化为了原样。<br/>"
								+ "[style.boldSex([npc.namePos]"+nipplesString+"的内部不再长满细小的触手！)]"
							+ "</p>");
				}
				break;
			case PUFFY:
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]叹了口气，之间自己的"+nipplesString+"[style.boldShrink(瘪了下去)]，不再那么有肉感了。<br/>"
							+ "[style.boldSex([npc.NamePos]的"+nipplesString+"不再那么有肉感了！)]"
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
