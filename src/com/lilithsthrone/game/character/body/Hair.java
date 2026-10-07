package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Hair implements BodyPartInterface {

	protected AbstractHairType type;
	public int length;
	protected HairStyle style;
	public boolean neckFluff;

	public Hair(AbstractHairType type, int length, HairStyle style, RaceStage ownerRaceStage) {
		this.type = type;
		this.length = length;
		this.style = style;
		
		neckFluff = false;
		if((!type.isNeckFluffRequiresGreater() || ownerRaceStage!=RaceStage.GREATER) && Math.random()<type.getNeckFluffChance()) {
			neckFluff = true;
		}
	}

	public Hair(Hair hairToCopy) {
		this.type = hairToCopy.type;
		this.length = hairToCopy.length;
		this.style = hairToCopy.style;
		this.neckFluff = hairToCopy.neckFluff;
	}
	
	@Override
	public AbstractHairType getType() {
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
	
	public String getDescriptor(GameCharacter owner) {
		return type.getDescriptor(owner);
	}
	
	public String setType(GameCharacter owner, AbstractHairType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有[npc.a_hairRace]的[npc.hair(true)]了，所以无事发生……)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(
				"<p>"
					+ "[npc.NamePos]头皮刺痛发痒，[npc.she]摸了摸头顶，感觉到[npc.hair(true)]正开始转变。");

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		
		sb.append(type.getTransformationDescription(owner));
		sb.append("</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public HairLength getLength() {
		return HairLength.getHairLengthFromInt(length);
	}

	public int getRawLengthValue() {
		return length;
	}

	/**
	 * Sets the length value. Value is bound to >=0 && <=HairLength.SEVEN_TO_FLOOR.getMaximumValue()
	 */
	public String setLength(GameCharacter owner, int length) {
		int oldLength = this.length;
		this.length = Math.max(0, Math.min(length, HairLength.SEVEN_TO_FLOOR.getMaximumValue()));
		int sizeChange = this.length - oldLength;
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		String styleChange = "";
		if(this.length < owner.getHairStyle().getMinimumLengthRequired()) {
			styleChange = "<p>"
							+ "[npc.Her] [npc.hair(true)] "+(owner.getHairType().isDefaultPlural(owner)?"are":"is")+" too short for [npc.her] current hair style!"
						+ "</p>"
						+ owner.setHairStyle(HairStyle.NONE);
		}
		
		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.hair(true)]的长度没有改变……)]</p>");
			
		} else if (sizeChange < 0) {
			String hairChangedText;
			if (this.length == 0 && owner.isFaceBaldnessNatural()) {
				hairChangedText = "没有[npc.hair(true)]";
			} else {
				hairChangedText = "[npc.hairLength]，" + Units.size(this.length, Units.UnitType.LONG_SINGULAR) +"[npc.hair(true)]";
			}
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]下意识地叫出声，伸手去摸头顶，感觉到[npc.her]的[npc.hair(true)][style.boldShrink(变得更短)]了。<br/>"
						+ "[npc.She]现在拥有[style.boldTfGeneric(" + hairChangedText + ")]！"
					+ "</p>"
					+ styleChange);
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]下意识地叫出声，伸手去摸头顶，感觉到[npc.her]的[npc.hair(true)][style.boldShrink(变得更长)]了。<br/>"
						+ "[npc.She]现在拥有[style.boldTfGeneric([npc.hairLength]，"+ Units.size(this.length, Units.UnitType.LONG_SINGULAR) +"[npc.hair(true)])]！"
					+ "</p>"
					+ styleChange);
		}
	}

	public HairStyle getStyle() {
		return style;
	}
	
	public String setStyle(GameCharacter owner, HairStyle style) {
		this.style = style;
		
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<p>");
			sb.append("[npc.NamePos][npc.hair(true)] "+"现在是");
			switch(style) { //TODO This should be handled in the style itself
				case BRAIDED:
					sb.append("编成了辫子。");
					break;
				case CURLY:
					sb.append("卷曲的。");
					break;
				case LOOSE:
					sb.append("松散开的。");
					break;
				case NONE:
					sb.append("没理特定发型的，且会继续保持着自然形态。");
					break;
				case PONYTAIL:
					sb.append("扎成了马尾。");
					break;
				case STRAIGHT:
					sb.append("拉直了的。");
					break;
				case TWIN_TAILS:
					sb.append("扎成了双马尾。");
					break;
				case WAVY:
					sb.append("呈现波浪形的。");
					break;
				case MOHAWK:
					sb.append("理成了莫霍克发型。");
					break;
				case AFRO:
					sb.append("理成了爆炸头。");
					break;
				case SIDECUT:
					sb.append("理成了侧剃。");
					break;
				case BOB_CUT:
					sb.append("理成了波波头。");
					break;
				case PIXIE:
					sb.append("理成了精灵短发。");
					break;
				case SLICKED_BACK:
					sb.append("梳成了背头。");
					break;
				case MESSY:
					sb.append("十分凌乱，没什么造型。");
					break;
				case HIME_CUT:
					sb.append("拉直后，理成了姬发式。");
					break;
				case CHONMAGE:
					sb.append("拉直后涂油保养，扎成了月代头。");
					break;
				case DREADLOCKS:
					sb.append("编成了脏辫。");
					break;
				case TOPKNOT:
					sb.append("束起，拢成顶髻。");
					break;
				case BIRD_CAGE:
					sb.append("编成了优美的鸟笼形。");
					break;
				case TWIN_BRAIDS:
					sb.append("编成了双麻花辫。");
					break;
				case DRILLS:
					sb.append("理成了公主卷发。");
					break;
				case LOW_PONYTAIL:
					sb.append("扎成了低马尾。");
					break;
				case CROWN_BRAID:
					sb.append("编成了法式冠编发。");
					break;
				case BUN:
					sb.append("编成了丸子头。");
					break;
				case CHIGNON:
					sb.append("绑起，盘成了发髻。");
					break;
				case SIDE_BRAIDS:
					sb.append("编成麻花辫后侧搭在脸旁。");
					break;
				case SIDE_PARTED:
					sb.append("理成了侧分发型。");
					break;
			}
		sb.append("</p>");
		return UtilText.parse(owner, sb.toString());
	}

	public boolean isNeckFluff() {
		return neckFluff;
	}

	public String setNeckFluff(GameCharacter owner, boolean neckFluff) {
		if(owner!=null && this.neckFluff == neckFluff) {
			if(this.neckFluff) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有颈部[npc.hair(true)]了，所以无事发生……)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经没有颈部[npc.hair(true)]了，所以无事发生……)]</p>");
			}
		}
		this.neckFluff = neckFluff;
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			return "";
		}
		
		if (neckFluff) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]发出一声惊呼，感觉到惊人茂密的[npc.hair(true)][style.boldGrow(从脖子与上胸围处长了出来)]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]发出一声惊呼，感觉到脖颈周围的[npc.hair(true)][style.boldGrow(萎缩消失了)]！"
					+ "</p>");
		}
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Hair.class) && getType().getRace().isFeralPartsAvailable());
	}
}
