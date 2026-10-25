package com.lilithsthrone.LolificationProject.Lexi.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.OrificeInterface;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @since 0.4.101
 * @version 0.4.101
 * @author Lexi
 */
public class OrificeTussy implements OrificeInterface {

	public int wetness;
	public float capacity;
	public float stretchedCapacity;
	public int depth;
	public int elasticity;
	public int plasticity;
	public boolean virgin;
	public final Set<OrificeModifier> orificeModifiers;

	public OrificeTussy() {
		this.wetness = Wetness.THREE_WET.getValue();
		this.capacity = Capacity.TWO_TIGHT.getMedianValue();
		this.stretchedCapacity = this.capacity;
		this.depth = OrificeDepth.TWO_AVERAGE.getValue();
		this.elasticity = OrificeElasticity.FOUR_LIMBER.getValue();
		this.plasticity = OrificePlasticity.THREE_RESILIENT.getValue();
		this.virgin = true;

		this.orificeModifiers = new HashSet<>(Set.of(OrificeModifier.MUSCLE_CONTROL));
	}

	public OrificeTussy(OrificeTussy orificeTussyToCopy) {
		this.wetness = orificeTussyToCopy.wetness;
		this.capacity = orificeTussyToCopy.capacity;
		this.stretchedCapacity = orificeTussyToCopy.stretchedCapacity;
		this.depth = orificeTussyToCopy.depth;
		this.elasticity = orificeTussyToCopy.elasticity;
		this.plasticity = orificeTussyToCopy.plasticity;
		this.virgin = orificeTussyToCopy.virgin;

		this.orificeModifiers = new HashSet<>(orificeTussyToCopy.orificeModifiers);
	}
	
	public String getDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<>();
		
		for(OrificeModifier om : this.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		
		String wetnessDescriptor = this.getWetness(owner).getDescriptor();
		if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
			if(Main.sex.hasLubricationTypeFromAnyone(owner, SexAreaOrifice.TUSSY)) {
				wetnessDescriptor = "wet";
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
		if(owner!=null && !owner.hasTussy()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)]尾穴，所以什么都没发生...)]</p>");
		}
		if(owner!=null && owner.getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(由于由"+owner.getBodyMaterial().getName()+"构成，[npc.namePos] "+Wetness.SEVEN_DROOLING.getDescriptor()+"尾穴的湿润度无法改变...)]</p>");
		}
		
		int oldWetness = this.wetness;
		this.wetness = Math.max(0, Math.min(wetness, Wetness.SEVEN_DROOLING.getValue()));
		if(owner==null) {
			return "";
		}
		
		int wetnessChange = this.wetness - oldWetness;
		
		if(wetnessChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]尾穴的湿润度没有改变...)]</p>");
		}
		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.NamePos] [npc.eyes] 睁大，[npc.she] [npc.verb(feel)]到湿气在[npc.her]尾穴周围凝结，"
							+ " 当[npc.she] [npc.verb(realise)]它正在自行润滑并[style.boldGrow(变得更湿)]时，[npc.she] [npc.verb(let)]出了一声明亮的[npc.a_moan+]。<br/>"
						+ "变换很快过去，给[npc.herHim]留下[style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + "尾穴)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] 不安地[npc.verb(shift)]着身子，在[npc.she] [npc.verb(feel)]到[npc.her]尾穴[style.boldShrink(变得干燥)]时，发出一声沮丧的呻吟。<br/>"
						+ "变换很快过去，给[npc.herHim]留下[style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + "尾穴)]!"
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
		if (owner!=null && !owner.hasTussy()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)]尾穴，所以什么都没发生...)]</p>");
		}
		
		float oldCapacity = this.capacity;
		this.capacity = Math.max(0, Math.min(capacity, Capacity.SEVEN_GAPING.getMaximumValue(false)));
		if(setStretchedValueToNewValue) {
			this.stretchedCapacity = this.capacity;
		}
		if(owner==null) {
			return "";
		}
		
		float capacityChange = this.capacity - oldCapacity;
		
		if (capacityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]尾穴的容量没有改变...)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "一声不由自主、受惊的抽气声从[npc.namePos]嘴中逸出，[npc.she] [npc.verb(feel)]到[npc.her]尾穴不受控制地扩张与拉伸。"
						+ " 片刻之间，这种感觉便已过去，[npc.she]很快[npc.verb(realise)]到自己的尾穴内部[style.boldGrow(容量已经增大了)]。<br/>"
						+ "[npc.Name] 现在[npc.has][style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + "尾穴)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] 发出一声叫喊，[npc.she] [npc.verb(feel)]到自己的尾穴不受控制地收紧与挛缩。"
						+ " 片刻之间，这种感觉便已过去，[npc.she]很快[npc.verb(realise)]到自己的尾穴内部[style.boldShrink(容量已经减小了)]。<br/>"
						+ "[npc.Name] 现在[npc.has][style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + "尾穴)]!"
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
		if (owner!=null && !owner.hasTussy()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)]尾穴，所以什么都没发生...)]</p>");
		}
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(由于由"+owner.getBodyMaterial().getName()+"构成，[npc.namePos] "+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+"尾穴的深度无法改变...)]</p>");
		}
		
		int oldDepth = this.depth;
		this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
		if(owner==null) {
			return "";
		}
		
		int depthChange = this.depth - oldDepth;
		
		if(depthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]尾穴的深度没有改变...)]</p>");
		}
		
		String depthDescriptor = getDepth(owner).getDescriptor();
		if(depthChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] 不由自主地发出一声惊讶的抽气，[npc.she] [npc.verb(feel)]到一股令人不安的压力从[npc.her]尾穴深处搏动着传向[npc.her]下腹部。"
						+ " 在[npc.her]抽气声变成痛苦的叫喊之前，这股压力突然消散，让[npc.herHim]本能地知道自己[style.boldGrow(尾穴已经变深了)]。<br/>"
						+ "[npc.Name] 现在[npc.has][style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + "尾穴)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] 不由自主地发出一声惊讶的抽气，[npc.she] [npc.verb(feel)]到一股令人不安的收紧感从[npc.her]下腹部一路朝[npc.her]尾穴蔓延。"
						+ " 在[npc.her]抽气声变成痛苦的叫喊之前，这种感觉突然消散，让[npc.herHim]本能地知道自己[style.boldShrink(尾穴已经变浅了)]。<br/>"
						+ "[npc.Name] 现在[npc.has][style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + "尾穴)]!"
					+ "</p>");
		}
	}
	
	@Override
	public OrificeElasticity getElasticity() {
		return OrificeElasticity.getElasticityFromInt(elasticity);
	}

	@Override
	public String setElasticity(GameCharacter owner, int elasticity) {
		if (owner!=null && !owner.hasTussy()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)]尾穴，所以什么都没发生...)]</p>");
		}
		int oldElasticity = this.elasticity;
		if (owner.getBodyMaterial().isOrificesAlwaysMaximumElasticity()) {
			this.elasticity = OrificeElasticity.SEVEN_ELASTIC.getValue();
			if (oldElasticity != this.elasticity) {
				return UtilText.parse(owner,
						"<p style='text-align:center;'>[style.colourSex(由于由"+owner.getBodyMaterial().getName()+"构成，[npc.namePos] "+OrificeElasticity.SEVEN_ELASTIC.getDescriptor()+"尾穴无法改变...)]</p>");
			} else {
				return "";
			}
		}
		this.elasticity = Math.max(0, Math.min(elasticity, OrificeElasticity.SEVEN_ELASTIC.getValue()));
		if(owner==null) {
			return "";
		}
		
		int elasticityChange = this.elasticity - oldElasticity;
		
		if (elasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]尾穴的弹性没有改变...)]</p>");
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] 不由自主地发出一声惊讶的抽气，[npc.she] [npc.verb(feel)]到一阵奇怪的松弛感在[npc.her]尾穴深处搏动。"
						+ " 这种感觉和开始时一样迅速地过去，[npc.she]很快[npc.verb(realise)]到自己的尾穴[style.boldGrow(弹性已经增大了)]。<br/>"
						+ "[npc.Name] 现在[npc.has][style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + "尾穴)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] 不由自主地发出一声惊讶的抽气，[npc.she] [npc.verb(feel)]到一阵奇怪的挛缩感在[npc.her]尾穴深处搏动。"
						+ " 这种感觉和开始时一样迅速地过去，[npc.she]很快[npc.verb(realise)]到自己的尾穴[style.boldShrink(弹性已经减小了)]。<br/>"
						+ "[npc.Name] 现在[npc.has][style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + "尾穴)]!"
					+ "</p>");
		}
	}
	
	@Override
	public OrificePlasticity getPlasticity() {
		return OrificePlasticity.getElasticityFromInt(plasticity);
	}

	@Override
	public String setPlasticity(GameCharacter owner, int plasticity) {
		if (owner!=null && !owner.hasTussy()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)]尾穴，所以什么都没发生...)]</p>");
		}
		int oldPlasticity = this.plasticity;
		this.plasticity = Math.max(0, Math.min(plasticity, OrificePlasticity.SEVEN_MOULDABLE.getValue()));
		if(owner==null) {
			return "";
		}
		
		int plasticityChange = this.plasticity - oldPlasticity;
		
		if (plasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]尾穴的延展性没有改变...)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] 受惊地抽了一口气，[npc.she]突然[npc.verb(feel)]到一阵奇怪的硬化感在[npc.her]尾穴深处搏动。"
						+ " 在[npc.she] [npc.has]来得及惊慌之前，这种感觉很快消散，让[npc.herHim]本能地知道自己的尾穴[style.boldGrow(可塑性已经增大了)]。<br/>"
						+ "[npc.Name] 现在[npc.has][style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + "尾穴)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] 受惊地抽了一口气，[npc.she]突然[npc.verb(feel)]到一阵奇怪的软化感在[npc.her]尾穴深处搏动。"
						+ " 在[npc.she] [npc.has]来得及惊慌之前，这种感觉很快消散，让[npc.herHim]本能地知道自己的尾穴[style.boldShrink(可塑性已经减小了)]。<br/>"
						+ "[npc.Name] 现在[npc.has][style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + "尾穴)]!"
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
			return "<p style='text-align:center;'>[style.colourDisabled(什么都没发生...)]</p>";
		}
		
		orificeModifiers.add(modifier);
		
		if(owner==null || owner.getBody()==null) {
			return "";
		}
		if(!owner.hasTussy()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(什么都没发生，因为[npc.name] [npc.verb(lack)]尾穴...)]</p>");
		}

        return switch (modifier) {
            case MUSCLE_CONTROL -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] 不由自主地发出一声明亮的[npc.a_moan+]，一股强烈的压力在[npc.her]尾穴深处膨胀，但在[npc.sheHasFull]任何反应的机会之前，这种感觉便迅速消散。"
                            + " 尝试性地一缩，[npc.she] [npc.verb(discover)]到自己尾穴的内壁上现在布满[style.boldGrow(额外的肌肉)]，"
                            + " 可以用它们熟练地抓握并紧压住任何插入的物体。<br/>"
                            + "[style.boldSex([npc.NamePos]尾穴现在布满了复杂精密的肌肉！)]"
                            + "</p>");
            case RIBBED -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] 不由自主地发出一声明亮的[npc.a_moan+]，一股强烈的压力在[npc.her]尾穴深处膨胀，但在[npc.sheHasFull]任何反应的机会之前，这种感觉便迅速消散。"
                            + " 稍微扭动一下，[npc.she] [npc.verb(discover)]到自己尾穴的内部现在布满[style.boldGrow(肉质的、高度敏感的褶皱)]，"
                            + " 在被刺激时会产生极度的快感。<br/>"
                            + "[style.boldSex([npc.NamePos]尾穴现在布满了肉质的、带来快感的褶皱！)]"
                            + "</p>");
            case TENTACLED -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] 不由自主地发出一声明亮的[npc.a_moan+]，一股强烈的压力在[npc.her]尾穴深处膨胀，但在[npc.sheHasFull]任何反应的机会之前，这种感觉便迅速消散。"
                            + " 尝试性地一缩，[npc.she] [npc.verb(discover)]到自己尾穴的内部现在布满[style.boldGrow(不断扭动的小触手)]，对它们[npc.sheHasFull]有限的控制。<br/>"
                            + "[style.boldSex([npc.namePos]尾穴的内部现在充满了小触手，它们会自行蠕动并爱抚任何侵入的物体！)]"
                            + "</p>");
            case PUFFY -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] 不由自主地发出一声明亮的[npc.a_moan+]，[npc.she] [npc.verb(feel)]到一阵刺痒的感觉滑过[npc.her]尾穴，然后它突然[style.boldGrow(肿胀起来)]。<br/>"
                            + "[style.boldSex([npc.NamePos]尾穴现在极其肿胀又蓬松！)]"
                            + "</p>");
        };
		
		// Catch:
    }

	@Override
	public String removeOrificeModifier(GameCharacter owner, OrificeModifier modifier) {
		if(!hasOrificeModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(什么都没发生...)]</p>";
		}
		
		orificeModifiers.remove(modifier);
		
		if(owner==null) {
			return "";
		}

        return switch (modifier) {
            case MUSCLE_CONTROL -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] 不由自主地发出一声受惊的叫喊，一股强烈的压力在[npc.her]尾穴深处膨胀，但在[npc.sheHasFull]任何反应的机会之前，这种感觉便迅速消散。"
                            + " 尝试性地一缩，[npc.she] [npc.verb(discover)]到自己尾穴的内部已经[style.boldShrink(失去那额外的肌肉)]。<br/>"
                            + "[style.boldSex([npc.NamePos]尾穴不再布满复杂精密的肌肉！)]"
                            + "</p>");
            case RIBBED -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] 不由自主地发出一声受惊的叫喊，一股强烈的压力在[npc.her]尾穴深处膨胀，但在[npc.sheHasFull]任何反应的机会之前，这种感觉便迅速消散。"
                            + " 稍微扭动一下，[npc.she] [npc.verb(discover)]到曾经布满[npc.her]尾穴内壁的[style.boldShrink(肉质的、高度敏感的褶皱)][style.boldShrink(已经消失了)]。<br/>"
                            + "[style.boldSex([npc.NamePos]尾穴不再布满肉质的、带来快感的褶皱！)]"
                            + "</p>");
            case TENTACLED -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] 不由自主地发出一声受惊的叫喊，一股强烈的压力在[npc.her]尾穴深处膨胀，但在[npc.sheHasFull]任何反应的机会之前，这种感觉便迅速消散。"
                            + " 尝试性地一缩，[npc.she] [npc.verb(discover)]到[npc.her]尾穴内的[style.boldShrink(不断扭动的触手)][style.boldShrink(已经全部消失了)]。<br/>"
                            + "[style.boldSex([npc.namePos]尾穴的内部不再充满触手！)]"
                            + "</p>");
            case PUFFY -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] 不由自主地发出一声受惊的叫喊，[npc.she] [npc.verb(feel)]到一阵刺痒的感觉滑过[npc.her]尾穴，"
                            + " 然后它突然[style.boldShrink(收缩回去)]恢复到更正常的尺寸。<br/>"
                            + "[style.boldSex([npc.NamePos]尾穴不再额外蓬松！)]"
                            + "</p>");
        };
		
		// Catch:
    }

	public Set<OrificeModifier> getOrificeModifiers() {
		return orificeModifiers;
	}
	
	public void clearOrificeModifiers() {
		orificeModifiers.clear();
	}

}