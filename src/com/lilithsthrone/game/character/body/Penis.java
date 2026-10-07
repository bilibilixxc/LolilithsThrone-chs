package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Penis implements BodyPartInterface {
	
	public static final float TWO_PENIS_SIZE_MULTIPLIER = 1.6f;

	protected AbstractPenisType type;
	public AbstractPenisType previousType;
	public int length;
	protected int girth;
	public boolean pierced;
	public boolean virgin;
	public Set<PenetrationModifier> penisModifiers;
	
	public Testicle testicle;
	public OrificePenisUrethra orificeUrethra;

	public Penis(AbstractPenisType type, int length, boolean usePenisSizePreference, int girth, int testicleSize, int cumProduction, int testicleCount) {
		this.type = type;
		if(usePenisSizePreference) {
			this.length = Math.max(1, Math.min(PenisLength.SEVEN_STALLION.getMaximumValue(), length)+Main.getProperties().penisSizePreference);
		} else {
			this.length = Math.min(PenisLength.SEVEN_STALLION.getMaximumValue(), length);
		}
		this.girth = Math.min(PenetrationGirth.getMaximum(), girth);
		pierced = false;
		virgin = true;
		
		testicle = new Testicle(type.getTesticleType(), testicleSize, cumProduction, testicleCount);
		
		orificeUrethra = new OrificePenisUrethra(testicle.getCumStorage().getAssociatedWetness().getValue(), 0, 2, OrificeElasticity.ZERO_UNYIELDING.getValue(), OrificePlasticity.THREE_RESILIENT.getValue(), true, new ArrayList<>());
		
		this.penisModifiers = new HashSet<>();
		this.penisModifiers.addAll(type.getDefaultRacialPenetrationModifiers());
	}

	public Penis(Penis penisToCopy) {
		this.type = penisToCopy.type;
		this.length = penisToCopy.length;
		this.girth = penisToCopy.girth;
		this.pierced = penisToCopy.pierced;
		this.virgin = penisToCopy.virgin;
		
		this.testicle = new Testicle(penisToCopy.testicle);
		
		this.orificeUrethra = new OrificePenisUrethra(penisToCopy.orificeUrethra);
		
		this.penisModifiers = new HashSet<>(penisToCopy.penisModifiers);
	}
	
	@Override
	public AbstractPenisType getType() {
		return type;
	}
	
	public Testicle getTesticle() {
		return testicle;
	}
	
	public OrificePenisUrethra getOrificeUrethra() {
		return orificeUrethra;
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
		List<String> list = new ArrayList<>();
        
		if(owner.getPenisSize()!=PenisLength.TWO_AVERAGE) {
			list.add(owner.getPenisSize().getDescriptor());
		}
		
		if(owner.getPenisGirth()!=PenetrationGirth.THREE_AVERAGE) {
			list.add(owner.getPenisGirth().getName());
		}
		
		for(PenetrationModifier pm : penisModifiers) {
			if(!Main.game.isInSex() && pm!=PenetrationModifier.SHEATHED) {
				list.add(pm.getName());
			}
		}
		
		if(owner.getPenisCovering()!=null) {
			list.add(owner.getCovering(owner.getPenisCovering()).getColourDescriptor(owner, false, false));
		}
		
		if(owner.isPenisFeral()) {
			list.add(Util.randomItemFrom(Util.newArrayListOfValues(
					"兽态",
					"兽性",
					"似兽")));
		} else {
			list.add(type.getDescriptor(owner));
		}
		
		if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
			if(owner.hasErection()) {
				list.add("坚硬");
				if(this.getType()!=PenisType.DILDO) {
					list.add("颤动");
				}
			} else {
				list.add("柔软");
				list.add("松弛");
				if(owner.isErectionPreventedPhysically()) {
					list.add("带锁");
					list.add("禁锢");
				}
			}
		}
		
		if(owner.getBodyMaterial().getPartDescriptors()!=null && !owner.getBodyMaterial().getPartDescriptors().isEmpty()) {
			list.add(Util.randomItemFrom(owner.getBodyMaterial().getPartDescriptors()));
		}
		
		list.removeIf(d->d.isEmpty());
		if(list.isEmpty()) {
			return "";
		}
		return Util.randomItemFrom(list);
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
	
	public String getPenisHeadName(GameCharacter gc) {
		List<String> list = new ArrayList<>();
		list.add("龟头");
        
		if(penisModifiers.contains(PenetrationModifier.TAPERED)) {
			list.add("顶端");
		}

		return Util.randomItemFrom(list);
	}
	
	public String getPenisHeadDescriptor(GameCharacter gc) {
		List<String> list = new ArrayList<>();
        
		if(penisModifiers.contains(PenetrationModifier.TAPERED)) {
			list.add("锥形");
			list.add("尖头");
		}
		if(penisModifiers.contains(PenetrationModifier.FLARED)) {
			list.add("宽头");
			list.add("平头");
			list.add("平坦");
		}

		return Util.randomItemFrom(list);
	}

	public String setType(GameCharacter owner, AbstractPenisType type) {
		return setType(owner, type, true);
	}
	
	/**
	 * @param owner The GameCharacter whose penis this is.
	 * @param type The type of penis which should be applied.
	 * @param applyExtraEffects true if you want the default behaviour of the penis type applying its default appearance.
	 * @return A description of the transformation.
	 */
	public String setType(GameCharacter owner, AbstractPenisType type, boolean applyExtraEffects) {
		if(type==PenisType.NONE) {
			setPreviousType(this.type);
		}
		if(this.type==PenisType.NONE) {
			this.orificeUrethra.setStretchedCapacity(this.orificeUrethra.getRawCapacityValue());
		}
		
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			testicle.setType(owner, type.getTesticleType());
			if(owner!=null) {
				owner.resetAreaKnownByCharacters(CoverableArea.PENIS);
				owner.resetAreaKnownByCharacters(CoverableArea.TESTICLES);
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type==PenisType.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经没有阴茎了，所以无事发生……)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有[npc.a_cockRace]的阴茎了，所以无事发生……)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();

		sb.append("<p>");
			if(!owner.hasPenisIgnoreDildo()) {
				if(length<1 && applyExtraEffects) {
					length = 1;
				}
				sb.append(
					"[npc.Name]感到一股热量涌上了自己的腹股沟，不自觉地发出一阵[npc.a_moan+]，感觉到"
								+ (owner.hasVagina()
										? (!owner.isTaur()
											?"在[npc.her]淫穴上面"
											:"在[npc.her]淫穴下面")
										: "在[npc.her]双腿之间")
							+ "的皮肤收紧并开始向外挤压。"
						+ "转瞬之间，"
							+ (owner.hasVagina()
									? (!owner.isTaur()
										?"在[npc.her]阴柔的缝隙上"
										:"在[npc.her]女性化的缝隙下")
									: "在[npc.her]腹股沟中间")
							+ "形成了一个明显的肿块，伴随着分裂的感觉，那肿块伸出并形成了一个阴茎。");
			
				if(owner.isInternalTesticles()) {
					sb.append(
							"[npc.her]新生的阴茎耷拉下来"
								+ (owner.hasVagina()
									? (!owner.isTaur()
											?"跟阴户贴在了一起，随后又感到[npc.a_balls]在腹股沟内部生长起来，"
											:"在阴道下方，随后又感到[npc.a_balls]在腹股沟内部生长起来，")
									: "位于股间，随后又感到[npc.a_balls]在腹股沟内部生长起来，")
							+ "等到这套全新的性器生长完毕，[npc.she]才后知后觉地发出一声[npc.moan]。<br/>");
				} else {
					sb.append(
							"[npc.her]新生的阴茎耷拉下来"
								+ (owner.hasVagina()
									? (!owner.isTaur()
											?"跟阴户贴在了一起，随后又感到[npc.a_balls]出现在了两套性器间，"
											:"在阴道下方，随后又感到[npc.a_balls]出现在了两套性器间，")
									: "位于股间，随后又感到[npc.a_balls]出现在新生肉棒的基部，")
							+ "等到这套全新的性器生长完毕，[npc.she]才后知后觉地发出一声[npc.moan]。<br/>");
				}
				
			} else {
				sb.append(
						"[npc.Name]感觉自己的[npc.cock]忽然立了起来，不禁微微吸了一口凉气，"
								+ "还没等[npc.sheIs]控制住自己突然的勃起，原本的喘息声就变成了[npc.a_moan+]，阴茎已经开始发生了转化。<br/>");
			}
		sb.append("</p>");
		
		if(applyExtraEffects) {
			// Parse existing content before transformation:
			String s = UtilText.parse(owner, sb.toString());
			sb.setLength(0);
			sb.append(s);
			sb.append("<p>");
				sb.append(this.type.applyAdditionalTransformationEffects(owner, false));
				this.type = type;
				testicle.setType(owner, type.getTesticleType());
				owner.resetAreaKnownByCharacters(CoverableArea.PENIS);
				owner.resetAreaKnownByCharacters(CoverableArea.TESTICLES);
				sb.append(this.type.getTransformationDescription(owner));
				sb.append(this.type.applyAdditionalTransformationEffects(owner, true));
			sb.append("</p>");
			
			if(type!=PenisType.NONE) { // Don't remove modifiers for NONE so that they can be 'restored' if applyExtraEffects==false
				penisModifiers.clear();
				penisModifiers.addAll(type.getDefaultRacialPenetrationModifiers());
				
				sb.append("<p>");
					sb.append("[npc.her]阴茎曾经拥有的所有修饰词都会因为转化而[style.boldShrink(消失)]！");
					if(!penisModifiers.isEmpty()) {
						sb.append("<br/>而[npc.her]新生的阴茎将会是:");
						for(PenetrationModifier pm : penisModifiers) {
							sb.append("<br/>[style.boldGrow("+Util.capitaliseSentence(pm.getName())+")]");
						}
					}
				sb.append("</p>");
			}
			
		} else {
			this.type = type;
			testicle.setType(owner, type.getTesticleType());
			AbstractRace penisRace = type.getRace();
			
			sb.append("<p>");
				sb.append("[npc.She]现在拥有了"+UtilText.generateSingularDeterminer(penisRace.getName(true))+"<b style='color:"+penisRace.getColour().toWebHexString()+";'>"+penisRace.getName(true)+"一般的阴茎</b>，"
						+ "[npc.materialDescriptor][npc.penisFullDescription(true)]。");
				sb.append("<br/>[npc.She]拥有<b style='color:"+penisRace.getColour().toWebHexString()+";'>[npc.ballsCount]颗#IF(npc.isInternalTesticles())体内#ENDIF阴囊</b>,"
						+ "[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的<b style='color:"+penisRace.getColour().toWebHexString()+";'>"+penisRace.getName(true)+"精液</b>。");
			sb.append("</p>");
		}
		
		String postTF = owner.postTransformationCalculation(false); // Calculate this before parsing, as it updates covering colours
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ postTF
				+ "</p>";
	}

	public AbstractPenisType getPreviousType() {
		return previousType;
	}

	public void setPreviousType(AbstractPenisType previousType) {
		this.previousType = previousType;
	}
	
	// Girth:

	public PenetrationGirth getGirth() {
		return PenetrationGirth.getGirthFromInt(girth);
	}

	public int getRawGirthValue() {
		return girth;
	}

	/**
	 * Sets the girth. Value is bound to >=0 && <=PenisGirth.FOUR_FAT.getValue()
	 */
	public String setPenisGirth(GameCharacter owner, int girth) {
		if(!isCharacterInitialised(owner)) {
			this.girth = Math.max(0, Math.min(girth, PenetrationGirth.getMaximum()));
			return "";
		}
		
		if(!owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		int girthChange = 0;
		
		if (girth <= 0) {
			if (this.girth != 0) {
				girthChange = 0 - this.girth;
				this.girth = 0;
			}
		} else if (girth >= PenetrationGirth.getMaximum()) {
			if (this.girth != PenetrationGirth.getMaximum()) {
				girthChange = PenetrationGirth.getMaximum() - this.girth;
				this.girth = PenetrationGirth.getMaximum();
			}
		} else {
			if (this.girth != girth) {
				girthChange = girth - this.girth;
				this.girth = girth;
			}
		}
		
		if(girthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.cock]的周长没有变化……)]</p>");
		}
		
		if (girthChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感到阴茎的基部出现一股搏动感，不禁冒出一阵[npc.a_moan+]。"
						+ "这感觉传遍了整根肉棒，[npc.Her]脸颊一下子红了，一小股先走液从已经硬邦邦的老二里挤了出来，"
							+ "[npc.she]意识到阴茎变得[style.boldGrow(更粗)]了。<br/>"
						+ "[npc.She]现在拥有[style.boldSex([npc.a_penisGirth]的[npc.cock])]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感到阴茎的基部出现一股搏动感，不禁冒出一阵[npc.a_moan+]。"
						+ "这感觉传遍了整根肉棒，[npc.Her]脸颊一下子红了，一小股先走液从已经硬邦邦的老二里挤了出来，"
							+ "[npc.she]意识到阴茎变得[style.boldShrink(更细)]了。<br/>"
						+ "[npc.She]现在拥有[style.boldSex([npc.a_penisGirth]的[npc.cock])]！"
					+ "</p>");
		}
	}
	
	// Length:

	public PenisLength getLength() {
		return PenisLength.getPenisLengthFromInt(length);
	}

	public int getRawLengthValue() {
		return length;
	}

	/**
	 * Sets the length. Value is bound to >=0 && <=PenisLength.SEVEN_STALLION.getMaximumValue()
	 */
	public String setPenisLength(GameCharacter owner, int length) {
		if(owner!=null && !owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		int lengthChange = 0;
		
		if (length <= 0) {
			if (this.length != 0) {
				lengthChange = 0 - this.length;
				this.length = 0;
			}
		} else if (length >= PenisLength.SEVEN_STALLION.getMaximumValue()) {
			if (this.length != PenisLength.SEVEN_STALLION.getMaximumValue()) {
				lengthChange = PenisLength.SEVEN_STALLION.getMaximumValue() - this.length;
				this.length = PenisLength.SEVEN_STALLION.getMaximumValue();
			}
		} else {
			if (this.length != length) {
				lengthChange = length - this.length;
				this.length = length;
			}
		}
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if(lengthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.cock]的长度没有变化……)]</p>");
		}
		
		if (lengthChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感到阴茎的基部出现一股搏动感，不禁冒出一声[pc.a_moan]。"
						+ "这感觉传遍了整根肉棒，[npc.Her]脸颊一下子红了，一小股先走液从已经硬邦邦的老二里挤了出来，"
							+ "[npc.she]意识到阴茎变得[style.boldGrow(更长)]了。<br/>"
						+ "[npc.she]现在拥有[style.boldSex([npc.a_penisSize]的[npc.cock])]！"
					+ "</p>");
		} else {
			return UtilText.parse(owner,
					"<p>"
							+ "[npc.Name]感到阴茎的基部出现一股紧缩感，不禁冒出一声[pc.a_moan]。"
							+ "这感觉传遍了整根肉棒，[npc.Her]脸颊一下子红了，一小股先走液从已经硬邦邦的老二里挤了出来，"
								+ "[npc.she]意识到阴茎变得[style.boldShrink(更短)]了。<br/>"
						+ "[npc.she]现在拥有[style.boldSex([npc.a_penisSize]的[npc.cock])]！"
					+ "</p>");
		}
	}
	
	// Diameter:

	public static float getGenericDiameter(int length, PenetrationGirth girth) {
		return getGenericDiameter(length, girth, new HashSet<>());
	}
	
	public static float getGenericDiameter(int length, PenetrationGirth girth, Set<PenetrationModifier> mods) {
		float baseDiameterModifier = 0.2f;
		baseDiameterModifier = Math.max(0.15f, baseDiameterModifier - (Math.max(length-15, 0) * 0.0025f)); // Every cm over 15 (6 inches) reduces the base diameter modifier by 0.25%
		
		return Units.round((length * baseDiameterModifier) * (1f + girth.getDiameterPercentageModifier() + (mods.contains(PenetrationModifier.FLARED)?0.05f:0) + (mods.contains(PenetrationModifier.TAPERED)?-0.05f:0)), 2);
	}
	
	public float getDiameter() {
		return getGenericDiameter(length, getGirth(), penisModifiers);
	}
	
	public boolean isPierced() {
		return pierced;
	}

	public String setPierced(GameCharacter owner, boolean pierced) {
		if(this.pierced == pierced || !owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		this.pierced = pierced;
		
		if(pierced) {
			return UtilText.parse(owner, "<p>[npc.NamePos]的[npc.cock]现在已经[style.boldGrow(穿孔)]！</p>");
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_PENIS);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			return UtilText.parse(owner,
					"<p>[npc.NamePos]的[npc.cock][style.boldShrink(不再有穿孔)]了！</p>"
					+piercingUnequip);
		}
	}
	
	public boolean isVirgin() {
		return virgin;
	}

	public void setVirgin(boolean virgin) {
		this.virgin = virgin;
	}
	
	public Set<PenetrationModifier> getPenisModifiers() {
		return penisModifiers;
	}
	
	public boolean hasPenisModifier(PenetrationModifier modifier) {
		return penisModifiers.contains(modifier);
	}

	public String addPenisModifier(GameCharacter owner, PenetrationModifier modifier) {
		if(hasPenisModifier(modifier)) {
			return owner == null ? "" : "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		if(!isCharacterInitialised(owner) || owner.getBody()==null) {
			penisModifiers.add(modifier);
			return "";
		}
		
		if(!owner.hasPenisIgnoreDildo()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]没有阴茎，所以无事发生……)]</p>");
		}
		
		penisModifiers.add(modifier);

		List<String> pmsRemoved = new ArrayList<>();
		
		for(PenetrationModifier pm : modifier.getMutuallyExclusivePenetrationModifiers()) {
			if(hasPenisModifier(pm)) {
				pmsRemoved.add(pm.getName());
				penisModifiers.remove(pm);
			}
		}
		String removedText = "";
		if(!pmsRemoved.isEmpty()) {
			removedText = "<br/>[style.italicsMinorBad(由于与“"+modifier.getName()+"”修饰词冲突，[npc.namePos]的阴茎不再"+Util.stringsToStringList(pmsRemoved, false)+"了。)]";
		}
		
		String returnText = "";
		
		switch(modifier) {
			case RIBBED:
				returnText = "一股强烈的压迫感在[npc.namePos]的[npc.cock]上浮现，但没等[npc.she]有任何反应，整根上就出现了一连串[style.boldGrow(肉质的硬质凸起)]。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.cock]现在长有螺纹！)]";
				break;
			case TENTACLED:
				returnText = "一股搏动的暖流在[npc.namePos]的[npc.cock]上浮现，但没等[npc.she]有任何反应，整根上就出现了一串[style.boldGrow(扭动的细小触手)]。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.cock]现在覆盖着细小的触手，自行蜿蜒扭动着！)]";
				break;
			case BARBED:
				returnText = "一股强烈的暖意在[npc.namePos]的[npc.cock]顶端浮现，但没等[npc.she]有任何反应，整根上就出现了一连串[style.boldGrow(微小的肉质倒刺)]。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.cock]现在衬着肉质的倒刺！)]";
				break;
			case FLARED:
				returnText = "一股强烈的暖意在[npc.namePos]的[npc.cock]顶端浮现，但没等[npc.she]有任何反应，[style.boldGrow(顶端就变成了平头)]，跟马阴茎类似。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.cock]现在有着宽大的平头！)]";
				break;
			case BLUNT:
				returnText = "一股强烈的暖意在[npc.namePos]的[npc.cock]顶端浮现，但没等[npc.she]有任何反应，[style.boldGrow(顶端就变得非常光滑)]，跟爬行动物的阴茎类似。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.cock]现在拥有光滑圆钝的龟头！)]";
				break;
			case KNOTTED:
				returnText = "一股强烈的暖意在[npc.namePos]的[npc.cock]根部浮现，但没等[npc.she]有任何反应，一个[style.boldGrow(粗大的锁结)]立刻生长了出来。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.cock]现在在根部长着粗大的锁结！)]";
				break;
			case PREHENSILE:
				returnText = "一股奇妙的刺痒感传遍了[npc.namePos]的[npc.cock]，[npc.she]忽然意识到这根[npc.cock]变得[style.boldGrow(十分灵活)]。"
								+ "这使得[npc.herHim]能够像灵长目的尾巴一样弯曲移动它。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.cock]现在灵活可控！)]";
				break;
			case SHEATHED:
				returnText = "一股强烈的压力在[npc.namePos]的[npc.cock]根部浮现，但没等[npc.she]有任何反应，[npc.cock]就迅速缩回了全新的[style.boldGrow(阴茎鞘)]中。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.cock]现在被鞘保护着！)]";
				break;
			case TAPERED:
				returnText = "一股强烈的暖意在[npc.namePos]的[npc.cock]上浮现，但没等[npc.she]有任何反应，肉竿就忽然[style.boldGrow(变尖)]了。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.cock]现在是尖头的！)]";
				break;
			case VEINY:
				returnText = "一股强烈的暖意在[npc.namePos]的[npc.cock]上浮现，但没等[npc.she]有任何反应，整根上就出现了一连串[style.boldGrow(突起的静脉)]。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.cock]现在青筋暴起！)]";
				break;
			case OVIPOSITOR:
				returnText = "一股奇妙的刺痒感传遍了[npc.namePos]的[npc.cock]，[npc.she]感受到[npc.cock]正在变成[style.boldGrow(产卵器)]，一声[npc.a_moan+]脱口而出。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.cock]现在能够产卵了！)]"
							+ "<br/><i>(若要具备完整功能，[npc.name]需要在产卵前使卵受精。卵无法产在已经怀孕的对象的阴道中。)</i>";
				break;
		}
		
		if(returnText.isEmpty()) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		return UtilText.parse(owner,
				"<p>"
					+returnText
					+removedText
				+"</p>");
	}

	public String removePenisModifier(GameCharacter owner, PenetrationModifier modifier) {
		if(!hasPenisModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		penisModifiers.remove(modifier);
		
		switch(modifier) {
			case RIBBED:
				return "<p>"
							+ "一股舒缓的凉意在[npc.namePos]的[npc.penis]上浮现，但没等[npc.she]有任何反应，那肉质的硬质螺纹[style.boldGrow(消失)]了。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.penis]不再有螺纹了！)]"
						+ "</p>";
			case TENTACLED:
				return "<p>"
							+ "一股舒缓的凉意在[npc.namePos]的[npc.penis]上浮现，但没等[npc.she]有任何反应，那扭动的细小触手就[style.boldGrow(消失)]了。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.penis]不再覆盖有细小的触手了！)]"
						+ "</p>";
			case BARBED:
				return "<p>"
							+ "一股舒缓的凉意在[npc.namePos]的[npc.penis]上浮现，但没等[npc.she]有任何反应，那小小的肉质倒刺便[style.boldGrow(消失)]了。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.penis]不再有倒刺了！)]"
						+ "</p>";
			case FLARED:
				return "<p>"
							+ "一股舒缓的凉意在[npc.namePos]的[npc.penis]顶端浮现，但没等[npc.she]有任何反应，那平坦的龟头便[style.boldGrow(缩了下去)]。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.penis]不再拥有平坦的龟头！)]"
						+ "</p>";
			case BLUNT:
				return "<p>"
							+ "一股舒缓的凉意在[npc.namePos]的[npc.penis]顶端浮现，但没等[npc.she]有任何反应，那圆钝的龟头便[style.boldGrow(缩了下去)]，变成人类的样子。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.penis]不再拥有圆钝的龟头！)]"
						+ "</p>";
			case KNOTTED:
				return "<p>"
							+ "一股舒缓的凉意在[npc.namePos]的[npc.penis]根部浮现，但没等[npc.she]有任何反应，那粗大的锁结便[style.boldGrow(缩了下去)]，最后消失了。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.penis]不再拥有锁结！)]"
						+ "</p>";
			case PREHENSILE:
				return "<p>"
							+ "一股奇怪的刺痒感传遍了[npc.namePos]的[npc.penis]，[npc.she]忽然意识到[npc.penis]正在变得[style.boldShrink(不再灵活可控)]。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.penis]不再灵活可控了！)]"
						+ "</p>";
			case SHEATHED:
				return "<p>"
							+ "一股舒缓的凉意在[npc.namePos]的[npc.penis]根部浮现，但没等[npc.she]有任何反应，那阴茎鞘便[style.boldGrow(消失)]了。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.penis]不再有鞘保护了！)]"
						+ "</p>";
			case TAPERED:
				return "<p>"
							+ "一股强烈的暖意在[npc.namePos]的[npc.penis]上浮现，但没等[npc.she]有任何反应，肉竿就突然[style.boldGrow(变宽)]了。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.penis]不再是尖头的了！)]"
						+ "</p>";
			case VEINY:
				return "<p>"
							+ "一股强烈的暖意在[npc.namePos]的[npc.penis]上浮现，但没等[npc.she]有任何反应，那突起的静脉就[style.boldGrow(消失)]了。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.penis]不再青筋暴起了！)]"
						+ "</p>";
			case OVIPOSITOR:
				return "<p>"
							+ "一股奇怪的刺痒感传遍了[npc.namePos]的[npc.penis]，[npc.she]不禁发出一声[npc.a_moan+]，感觉到[npc.penis]正在转化为"
								+ "[style.boldShrink(不再拥有产卵器的功能)]。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.penis]不再是产卵器了！)]"
						+ "</p>";
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
	}
	
	public void clearPenisModifiers() {
		penisModifiers.clear();
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner) || getType()==PenisType.NONE) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Penis.class) && getType().getRace().isFeralPartsAvailable());
	}
}
