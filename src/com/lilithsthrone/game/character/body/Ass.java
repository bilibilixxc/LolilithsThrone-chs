package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Ass implements BodyPartInterface {

	protected AbstractAssType type;
	protected int assSize;
	protected int hipSize;
	
	public Anus anus;

	public Ass(AbstractAssType type, int assSize, int hipSize, int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.assSize = assSize;
		this.hipSize = hipSize;
		
		anus = new Anus(type.getAnusType(), wetness, capacity, depth, elasticity, plasticity, virgin);
	}

	public Ass(Ass assToCopy) {
		this.type = assToCopy.type;
		this.assSize = assToCopy.assSize;
		this.hipSize = assToCopy.hipSize;
		
		this.anus = new Anus(assToCopy.anus);
	}
	
	public Anus getAnus() {
		return anus;
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
		List<String> list = new ArrayList<>();
		
		list.add(type.getDescriptor(gc));
		list.add(this.getAssSize().getDescriptor());

		if(gc.getBodyMaterial().getPartDescriptors()!=null && !gc.getBodyMaterial().getPartDescriptors().isEmpty()) {
			list.add(Util.randomItemFrom(gc.getBodyMaterial().getPartDescriptors()));
		}
		
		return Util.randomItemFrom(list);
	}

	@Override
	public AbstractAssType getType() {
		return type;
	}

	public String setType(GameCharacter owner, AbstractAssType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			anus.setType(type.getAnusType());
			if(owner!=null) {
				owner.resetAreaKnownByCharacters(CoverableArea.ANUS);
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经具有[npc.a_assRace]的屁股，所以无事发生……)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(
				"<p>"
				+ "[npc.NamePos]突然感觉到[npc.her]的[npc.ass]变得柔软且极度敏感，随着刺激感来到[npc.her]的[npc.asshole]处，[npc.she]的喉间冒出了一声[npc.a_moan+]。"
				+ "[npc.she]意识到整个后庭的变化后，喘息着，断断续续地发出着不情愿的[npc.moan]。<br/>");
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		anus.setType(type.getAnusType());
		owner.resetAreaKnownByCharacters(CoverableArea.ANUS);

		sb.append(type.getTransformationDescription(owner)+"</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
				+ owner.postTransformationCalculation(false)
				+ "</p>";
	}
	
	public AssSize getAssSize() {
		return AssSize.getAssSizeFromInt(assSize);
	}

	/**
	 * Sets assSize attribute.<br/>
	 * Value is bound to >=0 && <=AssSize.SEVEN_GIGANTIC.getValue()
	 * 
	 * @param owner The character to change.
	 * @param assSize Value to set assSize to.
	 * @return Description of the change.
	 */
	public String setAssSize(GameCharacter owner, int assSize) {
		int oldSize = this.assSize;
		this.assSize = Math.max(0, Math.min(assSize, AssSize.SEVEN_GIGANTIC.getValue()));
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		int sizeChange = this.assSize - oldSize;
		
		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]的屁股尺寸没有变化……)]</p>");
		}
		
		String sizeDescriptor = getAssSize().getDescriptor();
		if (sizeChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "你的[pc.ass]突然变得沉重，你感到微微的不平衡感，于是实验地摇晃了一下，确信[pc.ass][style.boldGrow(变得更大了)]。<br/>"
							+ "你现在拥有[style.boldSex(" + UtilText.generateSingularDeterminer(sizeDescriptor) + "" + sizeDescriptor + "的屁股)]！"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]的[npc.ass]似乎突然变大了，[npc.she]看上去有些不太平衡，于是实验地摇晃了一下，确信[npc.her]的[npc.ass][style.boldGrow(变得更大了)]。<br/>"
							+ "[npc.She]现在拥有[style.boldSex(" + UtilText.generateSingularDeterminer(sizeDescriptor) + "" + sizeDescriptor + "的屁股)]！"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "你忽然感觉到身后轻了一块，于是实验地摇晃了一下[pc.ass]，确信[pc.ass][style.boldShrink(缩小了)]。<br/>"
							+ "你现在拥有[style.boldSex(" + UtilText.generateSingularDeterminer(sizeDescriptor) + "" + sizeDescriptor + "的屁股)]！"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]的[npc.ass]似乎突然变小了，[npc.she]看上去有些不太平衡，于是实验地摇晃了一下，确信[npc.her]的[npc.ass][style.boldShrink(缩小了)]。<br/>"
							+ "[npc.She]现在拥有[style.boldSex(" + UtilText.generateSingularDeterminer(sizeDescriptor) + "" + sizeDescriptor + "的屁股)]！"
						+ "</p>");
			}
		}
	}

	public HipSize getHipSize() {
		return HipSize.getHipSizeFromInt(hipSize);
	}
	
	/**
	 * Sets hipSize attribute.<br/>
	 * Value is bound to >=0 && <=HipSize.SEVEN_ABSURDLY_WIDE.getValue()
	 * 
	 * @param owner The character to change.
	 * @param hipSize Value to set hipSize to.
	 * @return Description of the change.
	 */
	public String setHipSize(GameCharacter owner, int hipSize) {
		int oldSize = this.hipSize;
		this.hipSize = Math.max(0, Math.min(hipSize, HipSize.SEVEN_ABSURDLY_WIDE.getValue()));
		int sizeChange = this.hipSize - oldSize;
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]臀部的尺寸没有变化……)]</p>");
		}
		
		String styledSizeDescriptor = "[style.boldSex("+ getHipSize().getDescriptor() + "臀部)]";
		if (sizeChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]惊讶的倒吸一口冷气，[npc.she]感觉道自己的臀部正在重塑、向外突出并变得[style.boldGrow(越来越宽)]。<br/>"
						+ "[npc.She]现在拥有了" + styledSizeDescriptor + "！"
					+ "</p>");
				
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]惊讶地倒吸一口冷气，[npc.she]感觉到[npc.her]的臀部正在变化、向内塌陷并变的[style.boldShrink(越来越窄)]。<br/>"
						+ "[npc.She]现在拥有了" + styledSizeDescriptor + "！"
					+ "</p>");
		}
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Ass.class) && getType().getRace().isFeralPartsAvailable());
	}
	
}
