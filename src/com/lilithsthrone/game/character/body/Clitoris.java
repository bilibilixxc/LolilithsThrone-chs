package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Clitoris implements BodyPartInterface {

	public int clitSize;
	protected int girth;
	public Set<PenetrationModifier> clitModifiers;
	
	public Clitoris(int clitSize, int girth) {
		this.clitSize = clitSize;
		this.girth = girth;
		
		clitModifiers = new HashSet<>();
	}

	public Clitoris(Clitoris clitorisToCopy) {
		this.clitSize = clitorisToCopy.clitSize;
		this.girth = clitorisToCopy.girth;
		
		this.clitModifiers = new HashSet<>(clitorisToCopy.clitModifiers);
	}
	
	@Override
	public BodyPartTypeInterface getType() {
		return null;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}

	@Override
	public String getName(GameCharacter gc) {
		return getNameSingular(gc);
	}
	
	@Override
	public String getName(GameCharacter gc, boolean withDescriptor) {
		String name = getName(gc);
		String descriptor = getDescriptor(gc);
		return (withDescriptor && descriptor!=null && !descriptor.isEmpty()?descriptor+"的":"")+name;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		if(this.getClitorisSize()==ClitorisSize.ZERO_AVERAGE) {
			return UtilText.returnStringAtRandom("阴蒂", "阴蒂", "豆豆", "豆豆", "阴核");
		} else {
			return UtilText.returnStringAtRandom("阴蒂", "阴蒂", "阴蒂", "阴蒂屌");
		}
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		if(this.getClitorisSize()==ClitorisSize.ZERO_AVERAGE) {
			return UtilText.returnStringAtRandom("阴蒂", "阴蒂", "豆豆", "豆豆", "阴核");
		} else {
			return UtilText.returnStringAtRandom("阴蒂", "阴蒂", "阴蒂", "阴蒂屌");
		}
	}

	@Override
	public AbstractBodyCoveringType getBodyCoveringType(GameCharacter gc) {
		return gc.getVaginaType().getBodyCoveringType(gc);
	}

	@Override
	public AbstractBodyCoveringType getBodyCoveringType(Body body) {
		return body.getVagina().getBodyCoveringType(body);
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		List<String> descriptors = new ArrayList<>();
		
		descriptors.add("敏感");
		
		if(this.getGirth()!=PenetrationGirth.THREE_AVERAGE) {
			descriptors.add(this.getGirth().getName());
		}
		
		if(this.getClitorisSize()!=ClitorisSize.ZERO_AVERAGE) {
			descriptors.add(this.getClitorisSize().getDescriptor());
		} else {
			descriptors.add("小巧");
		}
		
		if(!this.getClitorisModifiers().isEmpty()) {
			PenetrationModifier mod = Util.randomItemFrom(this.getClitorisModifiers());
			if(mod!=PenetrationModifier.OVIPOSITOR) {
				descriptors.add(mod.getName());
			}
		}

		if(gc.getBodyMaterial().getPartDescriptors()!=null && !gc.getBodyMaterial().getPartDescriptors().isEmpty()) {
			descriptors.add(Util.randomItemFrom(gc.getBodyMaterial().getPartDescriptors()));
		}
		
		return Util.randomItemFrom(descriptors);
	}
	
	public String getClitTipNameSingular(GameCharacter gc) {
		return UtilText.returnStringAtRandom("尖端", "顶端", "末端");
	}
	
	public String getClitTipNamePlural(GameCharacter gc) {
		return UtilText.returnStringAtRandom("尖端", "顶端", "末端");
	}
	
	public String getClitTipDescriptor(GameCharacter gc) {
		List<String> descriptors = new ArrayList<>();
		for(PenetrationModifier mod : this.getClitorisModifiers()) {
			switch(mod) {
				case BARBED:
				case KNOTTED:
				case OVIPOSITOR:
				case PREHENSILE:
				case RIBBED:
				case SHEATHED:
				case TENTACLED:
				case VEINY:
					break;
				case BLUNT:
					descriptors.add("圆头");
					break;
				case FLARED:
					descriptors.add("平头");
					break;
				case TAPERED:
					descriptors.add("尖头");
					break;
			}
		}
		if(descriptors.isEmpty()) {
			return "";
		}
		return Util.randomItemFrom(descriptors);
	}
	
	public ClitorisSize getClitorisSize() {
		return ClitorisSize.getClitorisSizeFromInt(clitSize);
	}

	public int getRawClitorisSizeValue() {
		return clitSize;
	}
	
	public String setClitorisSize(GameCharacter owner, int clitSize) {
		if(!isCharacterInitialised(owner)) {
			this.clitSize = Math.max(0, Math.min(clitSize, ClitorisSize.SEVEN_STALLION.getMaximumValue()));
			return "";
		}
		
		if(!owner.hasVagina()) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		int oldSize = this.clitSize;
		this.clitSize = Math.max(0, Math.min(clitSize, ClitorisSize.SEVEN_STALLION.getMaximumValue()));
		int sizeChange = this.clitSize - oldSize;
		
		if (sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(你阴蒂的尺寸没有变化……)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]阴蒂的尺寸没有变化……)]</p>");
			}
		} else if (sizeChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "你感到[pc.pussy]深处出现一股搏动感，不禁冒出一声[pc.a_moan]。"
							+ "当这股感觉逐渐集中到阴蒂上时，你的脸颊顿时染上了绯红，轻声喘息过后，你觉得那里[style.boldGrow(变得更大了)]。<br/>"
							+ "你现在拥有[style.boldSex([pc.a_clitSize]的[pc.clit])]！"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]感到[npc.pussy]深处出现一股搏动感，不禁冒出一声[npc.a_moan]。"
							+ "当这股感觉逐渐集中到阴蒂上时，[npc.her]的脸颊顿时染上了绯红，轻声喘息过后，[npc.she]觉得那里[style.boldGrow(变得更大了)]。<br/>"
							+ "[npc.she]现在拥有[style.boldSex([npc.a_clitSize]的[npc.clit])]！"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "你感到[pc.pussy]深处出现一股搏动感，不禁冒出一声[pc.a_moan]。"
							+ "当这股感觉逐渐集中到阴蒂上时，你的脸颊顿时染上了绯红，轻声喘息过后，你觉得那里[style.boldShrink(缩小了)]。<br/>"
							+ "你现在拥有[style.boldSex([pc.a_clitSize]的[pc.clit])]！"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name] lets out [npc.a_moan] as [npc.she] feels a deep throbbing sensation  building up within [npc.her] [npc.pussy]"
								+ "当这股感觉逐渐集中到阴蒂上时，[npc.her]的脸颊顿时染上了绯红，轻声喘息过后，[npc.she]觉得那里[style.boldShrink(缩小了)]。<br/>"
								+ "[npc.she]现在拥有[style.boldSex([npc.a_clitSize]的[npc.clit])]！"
						+ "</p>");
			}
		}
	}
	

	// Girth:

	public PenetrationGirth getGirth() {
		return PenetrationGirth.getGirthFromInt(girth);
	}

	public int getRawGirthValue() {
		return girth;
	}

	/**
	 * Sets the girth. Value is bound to >=0 && <=PenetrationGirth.FOUR_FAT.getValue()
	 */
	public String setGirth(GameCharacter owner, int girth) {
		if(!isCharacterInitialised(owner)) {
			this.girth = Math.max(0, Math.min(girth, PenetrationGirth.getMaximum()));
			return "";
		}
		
		if(!owner.hasVagina()) {
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]的[npc.clit]的周长没有变化……)]</p>");
		}
		
		if (girthChange > 0) {
			return UtilText.parse(owner,
					"</p>"
						+ "[npc.Name]感到阴蒂出现一股强烈的搏动感，不禁冒出一声[npc.a_moan+]。"
						+ "当这股感觉逐渐传遍了整个[npc.pussy+]时，[npc.her]的脸颊顿时染上了绯红，又一声[npc.moan+]脱口而出，[npc.her]的阴蒂突然[style.boldGrow(变得更粗了)]。<br/>"
						+ "[npc.she]现在拥有[style.boldSex([npc.a_clitGirth]的[npc.clit])]！"
					+ "</p>");
		} else {
			return UtilText.parse(owner,
					"</p>"
						+ "[npc.Name]感到阴蒂出现一股强烈的搏动感，不禁冒出一声[npc.a_moan+]。"
						+ "当这股感觉逐渐传遍了整个[npc.pussy+]时，[npc.her]的脸颊顿时染上了绯红，又一声[npc.moan+]脱口而出，[npc.her]的阴蒂突然[style.boldShrink(变得更细了)]。<br/>"
						+ "[npc.she]现在拥有[style.boldSex([npc.a_clitGirth]的[npc.cock])]！"
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
		return getGenericDiameter(clitSize, getGirth(), clitModifiers);
	}

	public Set<PenetrationModifier> getClitorisModifiers() {
		return clitModifiers;
	}
	
	public boolean hasClitorisModifier(PenetrationModifier modifier) {
		return clitModifiers.contains(modifier);
	}

	public String addClitorisModifier(GameCharacter owner, PenetrationModifier modifier) {
		if(hasClitorisModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}

		clitModifiers.add(modifier);
		
		List<String> pmsRemoved = new ArrayList<>();
		
		for(PenetrationModifier pm : modifier.getMutuallyExclusivePenetrationModifiers()) {
			if(hasClitorisModifier(pm)) {
				pmsRemoved.add(pm.getName());
				clitModifiers.remove(pm);
			}
		}

		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if(!owner.hasVagina()) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(你没有阴蒂，所以无事发生……)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]没有阴蒂，所以无事发生……)]</p>");
			}
		}
		
		
		String removedText = "";
		if(!pmsRemoved.isEmpty()) {
			removedText = "<br/>[style.italicsMinorBad(由于跟“"+modifier.getName()+"”修饰词冲突，[npc.namePos]的阴蒂不再"+Util.stringsToStringList(pmsRemoved, false)+"了。)]";
		}
		
		String returnText = "";
		
		switch(modifier) {
			case RIBBED:
				returnText = "一股强烈的压迫感在[npc.namePos]的[npc.clit]上浮现，但没等[npc.she]有任何反应，整根上就出现了一连串[style.boldGrow(肉质的硬质凸起)]。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.clit]现在长有螺纹！)]";
				break;
			case TENTACLED:
				returnText = "一股悸动的暖流在[npc.namePos]的[npc.clit]上浮现，但没等[npc.she]有任何反应，整根上就出现了一连串[style.boldGrow(扭动的细小触手)]。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.clit]上现在覆盖着细小的触手，自行蜿蜒扭动着！)]";
				break;
			case BARBED:
				returnText = "一股强烈的暖流在[npc.namePos]的[npc.clit]上浮现，但没等[npc.she]有任何反应，整根上就出现了一连串[style.boldGrow(肉质的倒刺)]。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.clit]上现在排列着肉质的倒刺！)]";
				break;
			case FLARED:
				returnText = "一股强烈的压迫感在[npc.namePos][npc.clit]的尖端浮现，但没等[npc.she]有任何反应，[style.boldGrow(头部就平坦下来)]，像是马阴茎一般。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.clit]现在有着宽大的平头！)]";
				break;
			case BLUNT:
				returnText = "一股强烈的压迫感在[npc.namePos][npc.clit]的尖端浮现，但没等[npc.she]有任何反应，[style.boldGrow(头部变得圆钝)]，像是爬行类的阴茎一般。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.clit]现在有着光滑的圆头！)]";
				break;
			case KNOTTED:
				returnText = "一股强烈的压迫感在[npc.namePos][npc.clit]的根部浮现，但没等[npc.she]有任何反应，一个[style.boldGrow(粗大的结)]就生长了出来。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.clit]现在在根部长着一个粗大的结！)]";
				break;
			case PREHENSILE:
				returnText = "一种奇怪的刺痛感在[npc.namePos]的整根[npc.clit]上散播，[npc.she]忽然意识到阴蒂正在变得[style.boldGrow(灵活可控)]，"
								+ "这使得[npc.herHim]能够像灵长目的尾巴一样弯曲移动它。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.clit]现在灵活可控了！)]";
				break;
			case SHEATHED:
				returnText = "一股强烈的压迫感在[npc.namePos][npc.clit]的根部浮现，但没等[npc.she]有任何反应，阴蒂便缩入一个全新的[style.boldGrow(鞘)]中。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.clit]现在拥有阴蒂鞘了！)]";
				break;
			case TAPERED:
				returnText = "一股强烈的暖流在[npc.namePos]的[npc.clit]上浮现，但没等[npc.she]有任何反应，整根就突然[style.boldGrow(变成了锥形)]。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.clit]现在是锥形的！)]";
				break;
			case VEINY:
				returnText = "一股强烈的暖流在[npc.namePos]的[npc.clit]上浮现，但没等[npc.she]有任何反应，整根上就出现了一大片[style.boldGrow(明显的青筋)]。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.clit]现在布满青筋！)]";
				break;
			case OVIPOSITOR:
				returnText = "一种奇怪的刺痛感在[npc.namePos]的整根[npc.clit]上散播，[npc.she]感受到阴蒂正在变成[style.boldGrow(产卵器)]，一声[npc.a_moan+]脱口而出。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.clit]现在能产卵了！)]"
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

	public String removeClitorisModifier(GameCharacter owner, PenetrationModifier modifier) {
		if(!hasClitorisModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		clitModifiers.remove(modifier);
		
		switch(modifier) {
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "你感到一阵舒缓的凉爽感传遍了[pc.clit]，没等你有任何反应，肉质的硬质突起突然[style.boldShrink(消失了)]。<br/>"
								+ "[style.boldSex(你的[pc.clit]不再有螺纹了！)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "一股舒缓的凉爽感在[npc.namePos]的[npc.clit]上浮现，但没等[npc.she]有任何反应，肉质的硬质突起突然[style.boldShrink(消失了)]。<br/>"
								+ "[style.boldSex([npc.NamePos]的[npc.clit]不再有螺纹了！)]"
							+ "</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "你感到一阵舒缓的凉爽感在[pc.clit]上浮现，但没等你有所反应，扭动的细小触手[style.boldShrink(消失了)]。<br/>"
								+ "[style.boldSex(你的[pc.clit]不再覆盖细小的触手了！)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "一股舒缓的凉爽感在[npc.namePos]的[npc.clit]上浮现，但没等[npc.she]有任何反应，扭动的细小触手便[style.boldShrink(消失了)]。<br/>"
								+ "[style.boldSex([npc.NamePos]的[npc.clit]不再覆盖细小的触手了！)]"
							+ "</p>";
				}
			case BARBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "你感到一阵舒缓的凉爽感在[pc.clit]上浮现，但没等你有所反应，小小的肉质倒刺便[style.boldShrink(消失了)]。<br/>"
								+ "[style.boldSex(你的[pc.clit]不再有倒刺了！)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "一股舒缓的凉爽感在[npc.namePos]的[npc.clit]上浮现，但没等[npc.she]有任何反应，小小的肉质倒刺便[style.boldShrink(消失了)]。<br/>"
								+ "[style.boldSex([npc.NamePos]的[npc.clit]不再有倒刺了！)]"
							+ "</p>";
				}
			case FLARED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "你感到一阵舒缓的凉爽感在[pc.clit]的尖端上浮现，没等你有任何反应，平头的阴蒂便[style.boldShrink(缩了下去)]，"
									+ "让你的阴蒂看起来像一个人类的阴蒂。<br/>"
								+ "[style.boldSex(你的[pc.clit]不再有着宽大的平头！)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "一股舒缓的凉爽感在[npc.namePos]的[npc.clit]尖端上浮现，但没等[npc.she]有任何反应，平头的阴蒂便[style.boldShrink(缩小了)]，"
									+ "让[npc.her]的阴蒂看起来像普通的人类阴蒂。<br/>"
								+ "[style.boldSex([npc.NamePos]的[pc.clit]不再有着宽大的平头！)]"
							+ "</p>";
				}
			case BLUNT:
				if(owner.isPlayer()) {
					return "<p>"
								+ "你感到一阵舒缓的凉爽感在[pc.clit]的尖端上浮现，没等你有任何反应，圆头的阴蒂[style.boldShrink(缩小了)]，"
									+ "让你的阴蒂看起来像普通的人类阴蒂。<br/>"
								+ "[style.boldSex(你的[pc.clit]不再有着圆头！)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "一股舒缓的凉爽感在[npc.namePos]的[npc.clit]尖端上浮现，但没等[npc.she]有任何反应，圆头的阴蒂便[style.boldShrink(缩小了)]。"
									+ "让[npc.her]的阴蒂看起来像普通的人类阴蒂。<br/>"
								+ "[style.boldSex([npc.NamePos]的[pc.clit]不再有着圆头！)]"
							+ "</p>";
				}
			case KNOTTED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "你感到一阵舒缓的凉爽感在[pc.clit]的底端上浮现，没等你有任何反应，肥厚的结[style.boldShrink(缩小了)]并消失了。<br/>"
								+ "[style.boldSex(你的[pc.clit]不再有肉结了！)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "一阵舒缓的凉爽感在[npc.namePos]的[pc.clit]底端上浮现，但没等[npc.she]有任何反应，肥厚的结便[style.boldShrink(缩小了)]并消失了。<br/>"
								+ "[style.boldSex([npc.NamePos]的[npc.clit]不再有肉结了！)]"
							+ "</p>";
				}
			case PREHENSILE:
				if(owner.isPlayer()) {
					return "<p>"
								+ "你感到一阵奇怪的刺痛感在整根[pc.clit]上散播，你忽然意识到阴蒂正在变得[style.boldShrink(不再灵活可控)]。<br/>"
								+ "[style.boldSex(你的[pc.clit]不再灵活可控！)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "一种奇怪的刺痛感在[npc.namePos]的整根[npc.clit]上散播，[npc.she]忽然意识到阴蒂正在变得[style.boldShrink(不再灵活可控)]。<br/>"
								+ "[style.boldSex([npc.NamePos]的[npc.clit]不再灵活可控！)]"
							+ "</p>";
				}
			case SHEATHED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "你感到一阵舒缓的凉爽感在[pc.clit]的底端上浮现，没等你有任何反应，阴蒂鞘便[style.boldShrink(消失了)]。<br/>"
								+ "[style.boldSex(你的[pc.clit]不再有阴蒂鞘了！)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "一阵舒缓的凉爽感在[npc.namePos]的[pc.clit]底端上浮现，但没等[npc.she]有任何反应，阴蒂鞘便[style.boldShrink(消失了)]。<br/>"
								+ "[style.boldSex([npc.NamePos]的[npc.clit]不再有阴蒂鞘了！)]"
							+ "</p>";
				}
			case TAPERED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "你感到一股强烈的暖流在[pc.clit]上浮现，但没等你有所反应，肉柱突然[style.boldShrink(变大了)]。<br/>"
								+ "[style.boldSex(你的[pc.clit]不再是锥形的了！)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "一股强烈的暖流在[npc.namePos]的[npc.clit]上浮现，但没等[npc.she]有任何反应，肉柱突然[style.boldShrink(变大了)]。<br/>"
								+ "[style.boldSex([npc.NamePos]的[npc.clit]不再是锥形的！)]"
							+ "</p>";
				}
			case VEINY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "你感到一股强烈的暖流在[pc.clit]上浮现，但没等你有所反应，明显的青筋便[style.boldShrink(消失了)]。<br/>"
								+ "[style.boldSex(你的[pc.clit]不再布满青筋了！)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "一股强烈的暖流在[npc.namePos]的[npc.clit]上浮现，但没等[npc.she]有任何反应，明显的青筋便[style.boldShrink(消失了)]。<br/>"
								+ "[style.boldSex([npc.NamePos]的[npc.clit]不再布满青筋了！)]"
							+ "</p>";
				}
			case OVIPOSITOR:
				return "<p>"
							+ "一种奇怪的刺痛感在[npc.namePos]的整根[npc.clit]上散播，[npc.she]感受到阴蒂正在变成，一声[npc.a_moan+]脱口而出"
								+ "[style.boldShrink(不再拥有产卵器的功能)]。"
							+ "<br/>[style.boldSex([npc.NamePos]的[npc.clit]不再是产卵器了！)]"
						+ "</p>";
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
	}

	public void resetClitorisModifiers() {
		clitModifiers = new HashSet<>();
	}
	
	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Clitoris.class) && getType().getRace().isFeralPartsAvailable());
	}
	
}
