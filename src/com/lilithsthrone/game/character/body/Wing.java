package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Wing implements BodyPartInterface {

	protected AbstractWingType type;
	protected int size;
	
	public Wing(AbstractWingType type, int size) {
		this.type = type;
		this.size = size;
	}

	public Wing(Wing wingToCopy) {
		this.type = wingToCopy.type;
		this.size = wingToCopy.size;
	}

	@Override
	public AbstractWingType getType() {
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
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		return type.getDescriptor(gc);
	}

	public void setType(AbstractWingType type) {
		this.type = type;
	}

	public String setType(GameCharacter owner, AbstractWingType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			this.setSize(owner, this.getSizeValue());
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type == WingType.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经没有翅膀了，所以无事发生……)]</p>");
				
			} else if(type.getRace()!=Race.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有[npc.a_wingRace]的[npc.wings]了，所以无事发生……)]</p>");
				
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有"+type.getTransformName()+"[npc.wings]了，所以无事发生……)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();

		if(type != WingType.NONE) {
			sb.append(
					"<p>"
						+ "[npc.Name]感到背部忽然一阵躁动感，正准备回头看，就有什么东西从[npc.skin]下破体而出。");
			
		} else {
			sb.append(
					"<p>"
						+ "[npc.NamePos]的[npc.wings]突然开始不受控制地颤抖扇动起来，[npc.she]感到转化已经开始了，禁不住倒吸一口凉气。");
		}
		
		sb.append(type.getTransformationDescription(owner));
		
		if(type!=WingType.NONE) {
			if(this.getSize().getValue() >= owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getValue()) {
				sb.append("</br>"
						+ "[npc.sheIs]尝试着扇动了一下，惊喜地发现竟然[style.colourGood(足够有力以支持[npc.herHim]飞翔)]了！"
						+ "<br/>"
						+ "[style.italics("+Util.capitaliseSentence(owner.getLegConfiguration().getName())+"的身躯需要翅膀至少达到“"+owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getName()+"”尺寸才能够飞翔。)]");
			} else {
				sb.append("</br>"
						+ "[npc.sheIs]尝试着扇动了一下，失望地发现还是[style.colourBad(并不足以支持[npc.herHim]飞翔)]……"
						+ "<br/>"
						+ "[style.italics("+Util.capitaliseSentence(owner.getLegConfiguration().getName())+"的身躯需要翅膀至少达到“"+owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getName()+"”尺寸才能够飞翔。)]");
			}
		}
		sb.append("</p>");

		this.type = type;
		this.setSize(owner, this.getSizeValue());
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public WingSize getSize() {
		return WingSize.getWingSizeFromInt(size);
	}

	public int getSizeValue() {
		return size;
	}

	public void setSize(int wingSize) {
		this.size = Math.max(0, Math.min(wingSize, WingSize.getLargest()));
	}

	public void setTypeAndSize(AbstractWingType type, int wingSize) {
		setType(type);
		setSize(wingSize);
	}
	
	public String setSize(GameCharacter owner, int wingSize) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			int effectiveSize = Math.max(this.getType().getMinimumSize().getValue(), Math.min(wingSize, this.getType().getMaximumSize().getValue()));
			this.size = effectiveSize;
			return "";
		}
		
		if(this.getType()==WingType.NONE) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.NamePos]没有翅膀，所以无事发生……)]</p>");
		}
		
		int effectiveSize = Math.max(this.getType().getMinimumSize().getValue(), Math.min(wingSize, this.getType().getMaximumSize().getValue()));
		if(owner.getWingSizeValue() == effectiveSize) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.wings]的尺寸没有变化……)]</p>");
		}

		StringBuilder sb = new StringBuilder();
		
		if(this.size > effectiveSize) {
			sb.append(
					UtilText.parse(owner, "<p>[npc.Name]感受到一股舒缓的清凉感从[npc.her][npc.wings+]中升腾而起，忍不住惊呼一声，[npc.wings]紧接着[style.boldShrink(缩小)]了。<br/>"));
			
		} else {
			sb.append(
					UtilText.parse(owner, "<p>[npc.Name]感受到一股搏动的温暖感从[npc.her][npc.wings+]中升腾而起，忍不住惊呼一声，[npc.wings]紧接着[style.boldGrow(变大)]了。<br/>"));
		}
		
		this.size = effectiveSize;

		sb.append(UtilText.parse(owner, "[npc.Name]现在拥有[style.boldSex([npc.wingSize]的[npc.wings])]！"));

		if(type!=WingType.NONE) {
			if(this.getSize().getValue() >= owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getValue()) {
				sb.append("</br>"
						+ "[npc.sheIs]尝试着扇动了一下，惊喜地发现竟然[style.colourGood(足够有力以支持[npc.herHim]飞翔)]了！"
						+ "<br/>"
						+ "[style.italics("+Util.capitaliseSentence(owner.getLegConfiguration().getName())+"的身躯需要翅膀至少达到“"+owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getName()+"”尺寸才能够飞翔。)]");
			} else {
				sb.append("</br>"
						+ "[npc.sheIs]尝试着扇动了一下，失望地发现还是[style.colourBad(并不足以支持[npc.herHim]飞翔)]……"
						+ "<br/>"
						+ "[style.italics("+Util.capitaliseSentence(owner.getLegConfiguration().getName())+"的身躯需要翅膀至少达到“"+owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getName()+"”尺寸才能够飞翔。)]");
			}
		}
		sb.append("</p>");
		
		return UtilText.parse(owner, sb.toString());
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Wing.class) && getType().getRace().isFeralPartsAvailable());
	}
}
