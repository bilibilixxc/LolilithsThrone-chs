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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] a tail pussy, so nothing happens...)]</p>");
		}
		if(owner!=null && owner.getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(Due to being made out of "+owner.getBodyMaterial().getName()+", the wetness of [npc.namePos] "+Wetness.SEVEN_DROOLING.getDescriptor()+" tail pussy can't be changed...)]</p>");
		}
		
		int oldWetness = this.wetness;
		this.wetness = Math.max(0, Math.min(wetness, Wetness.SEVEN_DROOLING.getValue()));
		if(owner==null) {
			return "";
		}
		
		int wetnessChange = this.wetness - oldWetness;
		
		if(wetnessChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The wetness of [npc.namePos] tail pussy doesn't change...)]</p>");
		}
		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.NamePos] [npc.eyes] widen as [npc.she] [npc.verb(feel)] moisture beading around [npc.her] tail pussy,"
							+ " and [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(realise)] that it's lubricating itself and [style.boldGrow(getting wetter)].<br/>"
						+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " tail pussy)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(shift)] about uncomfortably and [npc.verb(let)] out a frustrated groan as [npc.she] [npc.verb(feel)] [npc.her] tail pussy [style.boldShrink(getting drier)].<br/>"
						+ "The transformation quickly passes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " tail pussy)]!"
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] a tail pussy, so nothing happens...)]</p>");
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The capacity of [npc.namePos] tail pussy doesn't change...)]</p>");
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "An involuntary, shocked gasp escapes from [npc.namePos] mouth as [npc.she] [npc.verb(feel)] [npc.her] tail pussy uncontrollably dilate and stretch."
						+ " Within moments, the feeling has passed, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] tail pussy's internal [style.boldGrow(capacity has increased)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " tail pussy)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a cry as [npc.she] [npc.verb(feel)] [npc.her] tail pussy uncontrollably tighten and clench."
						+ " Within moments, the feeling has passed, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] tail pussy's internal [style.boldShrink(capacity has decreased)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " tail pussy)]!"
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] a tail pussy, so nothing happens...)]</p>");
		}
		if(owner!=null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
			return UtilText.parse(owner,
					"<p style='text-align:center;'>[style.colourSex(Due to being made out of "+owner.getBodyMaterial().getName()+", the depth of [npc.namePos] "+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+" tail pussy can't be changed...)]</p>");
		}
		
		int oldDepth = this.depth;
		this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
		if(owner==null) {
			return "";
		}
		
		int depthChange = this.depth - oldDepth;
		
		if(depthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The depth of [npc.namePos] tail pussy doesn't change...)]</p>");
		}
		
		String depthDescriptor = getDepth(owner).getDescriptor();
		if(depthChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] an alarming pressure pulsating up from [npc.her] tail pussy deep into [npc.her] lower abdomen."
						+ " Before [npc.her] gasp can turn into a distressed cry, the pressure suddenly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] tail pussy [style.boldGrow(has deepened)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " tail pussy)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] an alarming tightening sensation moving its way down [npc.her] lower abdomen into [npc.her] tail pussy."
						+ " Before [npc.her] gasp can turn into a distressed cry, the feeling suddenly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] tail pussy [style.boldShrink(has become shallower)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " tail pussy)]!"
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] a tail pussy, so nothing happens...)]</p>");
		}
		int oldElasticity = this.elasticity;
		if (owner.getBodyMaterial().isOrificesAlwaysMaximumElasticity()) {
			this.elasticity = OrificeElasticity.SEVEN_ELASTIC.getValue();
			if (oldElasticity != this.elasticity) {
				return UtilText.parse(owner,
						"<p style='text-align:center;'>[style.colourSex(Due to being made out of "+owner.getBodyMaterial().getName()+", the [npc.namePos] "+OrificeElasticity.SEVEN_ELASTIC.getDescriptor()+" tail pussys can't be changed...)]</p>");
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The elasticity of [npc.namePos] tail pussy doesn't change...)]</p>");
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] a strange slackening sensation pulsating deep within [npc.her] tail pussy."
						+ " Just as quickly as it started, the feeling passes, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] tail pussy's [style.boldGrow(elasticity has increased)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " tail pussy)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] a strange clenching sensation pulsating deep within [npc.her] tail pussy."
						+ " Just as quickly as it started, the feeling passes, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] tail pussy's [style.boldShrink(elasticity has decreased)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " tail pussy)]!"
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] a tail pussy, so nothing happens...)]</p>");
		}
		int oldPlasticity = this.plasticity;
		this.plasticity = Math.max(0, Math.min(plasticity, OrificePlasticity.SEVEN_MOULDABLE.getValue()));
		if(owner==null) {
			return "";
		}
		
		int plasticityChange = this.plasticity - oldPlasticity;
		
		if (plasticityChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The plasticity of [npc.namePos] tail pussy doesn't change...)]</p>");
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a shocked gasp as [npc.she] suddenly [npc.verb(feel)] a strange hardening sensation pulsating deep within [npc.her] tail pussy."
						+ " Before [npc.she] [npc.has] any time to panic, the feeling quickly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] tail pussy's [style.boldGrow(plasticity has increased)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " tail pussy)]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner, 
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out a shocked gasp as [npc.she] suddenly [npc.verb(feel)] a strange softening sensation pulsating deep within [npc.her] tail pussy."
						+ " Before [npc.she] [npc.has] any time to panic, the feeling quickly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] tail pussy's [style.boldShrink(plasticity has decreased)].<br/>"
						+ "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " tail pussy)]!"
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
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		orificeModifiers.add(modifier);
		
		if(owner==null || owner.getBody()==null) {
			return "";
		}
		if(!owner.hasTussy()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Nothing happens, as [npc.name] [npc.verb(lack)] a tail pussy...)]</p>");
		}

        return switch (modifier) {
            case MUSCLE_CONTROL -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] can't help but let out [npc.a_moan+] as an intense pressure swells up deep within [npc.her] tail pussy, but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
                            + " With an experimental clench, [npc.she] [npc.verb(discover)] that the inner walls of [npc.her] tail pussy are now lined with [style.boldGrow(extra muscles)],"
                            + " which [npc.she] can use to expertly grip and squeeze down on any penetrating object.<br/>"
                            + "[style.boldSex([npc.NamePos] tail pussy is now lined with an intricate series of muscles!)]"
                            + "</p>");
            case RIBBED -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] can't help but let out [npc.a_moan+] as an intense pressure swells up deep within [npc.her] tail pussy, but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
                            + " Shifting around a little, [npc.she] [npc.verb(discover)] that the inside of [npc.her] tail pussy is now lined with [style.boldGrow(fleshy, highly-sensitive ribs)],"
                            + " which provide extreme pleasure when stimulated.<br/>"
                            + "[style.boldSex([npc.NamePos] tail pussy is now lined with fleshy, pleasure-inducing ribs!)]"
                            + "</p>");
            case TENTACLED -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] can't help but let out [npc.a_moan+] as an intense pressure swells up deep within [npc.her] tail pussy, but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
                            + " With an experimental clench, [npc.she] [npc.verb(discover)] that the inside of [npc.her] tail pussy is now lined with [style.boldGrow(little wriggling tentacles)], over which [npc.sheHasFull] limited control.<br/>"
                            + "[style.boldSex(The inside of [npc.namePos] tail pussy is now filled with small tentacles, which wriggle and caress any intruding object with a mind of their own!)]"
                            + "</p>");
            case PUFFY -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] can't help but let out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a tingling sensation running over [npc.her] tail pussy, before it suddenly [style.boldGrow(puffs up)].<br/>"
                            + "[style.boldSex([npc.NamePos] tail pussy is now extremely swollen and puffy!)]"
                            + "</p>");
        };
		
		// Catch:
    }

	@Override
	public String removeOrificeModifier(GameCharacter owner, OrificeModifier modifier) {
		if(!hasOrificeModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		orificeModifiers.remove(modifier);
		
		if(owner==null) {
			return "";
		}

        return switch (modifier) {
            case MUSCLE_CONTROL -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] can't help but let out a startled cry as an intense pressure swells up deep within [npc.her] tail pussy, but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
                            + " With an experimental clench, [npc.she] [npc.verb(discover)] that the interior of [npc.her] tail pussy has [style.boldShrink(lost its extra muscles)].<br/>"
                            + "[style.boldSex([npc.NamePos] tail pussy is no longer lined with an intricate series of muscles!)]"
                            + "</p>");
            case RIBBED -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] can't help but let out a startled cry as an intense pressure swells up deep within [npc.her] tail pussy, but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
                            + " Shifting around a little, [npc.she] [npc.verb(discover)] that the [style.boldShrink(fleshy, highly-sensitive ribs)] that once lined the walls of [npc.her] tail pussy [style.boldShrink(have vanished)].<br/>"
                            + "[style.boldSex([npc.NamePos] tail pussy is no longer lined with fleshy, pleasure-inducing ribs!)]"
                            + "</p>");
            case TENTACLED -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] can't help but let out a startled cry as an intense pressure swells up deep within [npc.her] tail pussy, but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
                            + " With an experimental clench, [npc.she] [npc.verb(discover)] that the [style.boldShrink(wriggling tentacles)] within [npc.her] tail pussy [style.boldShrink(have all disappeared)].<br/>"
                            + "[style.boldSex(The inside of [npc.namePos] tail pussy is no longer filled with tentacles!)]"
                            + "</p>");
            case PUFFY -> UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] can't help but let out a startled cry as [npc.she] [npc.verb(feel)] a tingling sensation running over [npc.her] tail pussy,"
                            + " before it suddenly [style.boldShrink(shrinks down)] to take on a more average size.<br/>"
                            + "[style.boldSex([npc.NamePos] tail pussy is no longer extra puffy!)]"
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