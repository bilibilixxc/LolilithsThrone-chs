package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTesticleType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.FluidExpulsion;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Testicle implements BodyPartInterface {

	
	public static final int MIN_TESTICLE_COUNT = 2;
	public static final int MAX_TESTICLE_COUNT = 8;

	public static final int MINIMUM_VALUE_FOR_ALL_CUM_TO_BE_EXPELLED = 5; //ml
	
	
	protected AbstractTesticleType type;
	protected int testicleSize;
	protected int cumStorage;
	public float cumStored;
	/** Measured in mL/day */
    public int cumRegeneration;
	protected int testicleCount;
	protected int cumExpulsion;
	public boolean internal;
	
	public FluidCum cum;

	public Testicle(AbstractTesticleType type, int testicleSize, int cumStorage, int testicleCount) {
		this.type = type;
		this.testicleSize = Math.max(0, Math.min(testicleSize, TesticleSize.SEVEN_ABSURD.getValue()));
		this.cumStorage = cumStorage;
		cumStored = cumStorage;
		cumRegeneration = FluidRegeneration.CUM_REGEN_DEFAULT;
		cumExpulsion = FluidExpulsion.THREE_LARGE.getMinimumValue();
		
		this.testicleCount = Math.max(MIN_TESTICLE_COUNT, Math.min(testicleCount, MAX_TESTICLE_COUNT));
		
		internal = type.isInternal();
		
		cum = new FluidCum(type.getFluidType());
	}

	public Testicle(Testicle testicleToCopy) {
		this.type = testicleToCopy.type;
		this.testicleSize = testicleToCopy.testicleSize;
		this.cumStorage = testicleToCopy.cumStorage;
		this.cumStored = testicleToCopy.cumStored;
		this.cumRegeneration = testicleToCopy.cumRegeneration;
		this.cumExpulsion = testicleToCopy.cumExpulsion;
		
		this.testicleCount = testicleToCopy.testicleCount;
		
		this.internal = testicleToCopy.internal;
		
		this.cum = new FluidCum(testicleToCopy.cum);
	}

	public void setCum(FluidCum cum) {
		this.cum = cum;
	}
	
	public FluidCum getCum() {
		return cum;
	}

	@Override
	public AbstractTesticleType getType() {
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
	public String getDescriptor(GameCharacter owner) {
		List<String> list = new ArrayList<>();
		
		list.add(owner.getTesticleSize().getDescriptor());
		
		list.add(type.getDescriptor(owner));
		
		return Util.randomItemFrom(list);
	}
	
	public void setType(GameCharacter owner, AbstractTesticleType type) {
		this.type = type;
		cum.setType(type.getFluidType());
	}
	
	public TesticleSize getTesticleSize() {
		return TesticleSize.getTesticleSizeFromInt(testicleSize);
	}

	public String setTesticleSize(GameCharacter owner, int testicleSize) {
		if(owner!=null && !owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		int oldSize = this.testicleSize;
		this.testicleSize = Math.max(0, Math.min(testicleSize, TesticleSize.SEVEN_ABSURD.getValue()));
		int sizeChange = this.testicleSize - oldSize;

		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.balls]的大小没有变化……)]</p>");
			
		} else if (sizeChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]发出一声淫荡的呻吟，感到[npc.her]的[npc.balls]忽然涨了起来，[style.boldGrow(变得更大)]了。<br/>"
						+ "[npc.She]现在拥有[style.boldSex(" + owner.getTesticleSize().getDescriptor() + "的[pc.balls])]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]倒吸一口凉气，感到[npc.balls]忽然[style.boldShrink(缩了下去)]。<br/>"
						+ "[npc.She]现在拥有[style.boldSex(" +owner.getTesticleSize().getDescriptor()+ "的[npc.balls])]！"
					+ "</p>");
		}
	}

	public int getTesticleCount() {
		return testicleCount;
	}

	public String setTesticleCount(GameCharacter owner, int testicleCount) {
		testicleCount = Math.max(MIN_TESTICLE_COUNT, Math.min(testicleCount, MAX_TESTICLE_COUNT));
		
		if(!isCharacterInitialised(owner) || owner.getBody()==null) {
			this.testicleCount = testicleCount;
			return "";
		}
		
		if(owner.getTesticleCount() == testicleCount || !owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		boolean removingTesticles = this.testicleCount > testicleCount;
		this.testicleCount = testicleCount;
		
		if(removingTesticles) {
			return UtilText.parse(owner,
					"<p>"
						+ "一股刺痒感蔓延至[npc.namePos]的[npc.balls]，[npc.she]忽然惊呼一声，感到有些[npc.balls]缩了下去，最后[style.boldShrink(消失)]了。<br/>"
						+ "过了一会儿，[npc.sheIs]最终得到了[style.boldTfGeneric([npc.a_balls])]。"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "一股刺痒感蔓延至[npc.namePos]的[npc.balls]，[npc.she]忽然轻呼一声，感到有的[npc.balls]开始[style.boldGrow(增殖起来)]。<br/>"
						+ "过了一会儿，[npc.sheIs]最终得到了[style.boldTfGeneric([npc.a_balls])]。"
					+ "</p>");
		}
	}

	public boolean isInternal(GameCharacter owner) {
		if(!Main.game.isFutanariTesticlesEnabled() && owner!=null && owner.hasVagina()) {
			return true;
		}
		return internal;
	}

	public String setInternal(GameCharacter owner, boolean internal) {
		if(!isCharacterInitialised(owner)) {
			this.internal = internal;
			return "";
		}
		
		if(owner.isInternalTesticles() == internal || !owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}

		this.internal = internal;
		
		if(internal) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉自己的[npc.balls]紧紧收缩了一下，便[style.boldShrink(退回)]了腹股沟内部，"
							+ "[npc.she]意识到[npc.balls]现在已经坐落在身体内部，不禁惊呼一声。<br/>"
						+ "[npc.Her][npc.balls+][style.boldTfGeneric(现在位于体内)]。"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉位于体内的[npc.balls]忽然一松，[style.boldGrow(垂落下来)]，安稳地待在了外部的囊袋之中，[npc.she]这才长长地[npc.moan]一声。<br/>"
						+ "[npc.Her][npc.balls+][style.boldTfGeneric(现在位于体外)]。"
					+ "</p>");
		}
	}
	
	// CumProduction:

	public CumProduction getCumStorage() {
		return CumProduction.getCumProductionFromInt(cumStorage);
	}

	public int getRawCumStorageValue() {
		return cumStorage;
	}

	/**
	 * Sets the cumStorage. Value is bound to >=0 && <=CumProduction.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()
	 */
	public String setCumStorage(GameCharacter owner, int cumStorage) {
		int oldCumProduction = this.cumStorage;
		this.cumStorage = Math.max(0, Math.min(cumStorage, CumProduction.SEVEN_MONSTROUS.getMaximumValue()));
		int cumChange = this.cumStorage - oldCumProduction;

		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if(cumChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]能够产出的[npc.cum]量没有变化……)]</p>");
		}
		
		String cumDescriptor = getCumStorage().getDescriptor();
		if(cumChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.balls]深处催生出一股奇怪的躁动翻腾的感觉，"
							+ "忽然一小股先走液从[npc.her]的[npc.cock]中流了出来，害得[npc.she]不禁发出了[npc.a_moan+]；"
								+ "这意味着[npc.her]的[npc.cum]产量[style.boldGrow(增加)]了。<br/>"
						+ "[npc.SheIsFull]现在能够产生[style.boldSex(" + cumDescriptor + "[npc.cum])]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.balls]深处催生出一股奇怪的吸取感，"
							+ "[npc.she]忽然禁不住倒吸了一口凉气，意识到[npc.her]的[npc.cum]产量竟有些[style.boldShrink(枯涸)]了。<br/>"
						+ "[npc.SheIsFull]现在能够产生[style.boldSex(" + cumDescriptor + "[npc.cum])]！"
					+ "</p>");
		}
	}
	
	// Stored cum:

	public CumProduction getStoredCum() {
		return CumProduction.getCumProductionFromInt((int)cumStored);
	}
	
	public float getRawStoredCumValue() {
		return cumStored;
	}

	/**
	 * Sets the cumStorage. Value is bound to >=0 && <=getRawCumStorageValue()
	 */
	public String setStoredCum(GameCharacter owner, float cumStored, boolean withFormatting) {
		float oldStoredCum = this.cumStored;
		this.cumStored = Math.max(0, (Math.min(cumStored, getRawCumStorageValue())));
		float cumChange = oldStoredCum - this.cumStored;

		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (cumChange <= 0) {
			return "";
		} else {
			StringBuilder storedCumSB = new StringBuilder();
			if(withFormatting) {
				storedCumSB.append("<p style='text-align:center;'>[style.italicsCum(");
			}
			storedCumSB.append(UtilText.returnStringAtRandom(
					Units.fluid(cumChange, Units.UnitType.LONG)+"[npc.cum+]从[npc.her][npc.cock+]中挤出。",
					Units.fluid(cumChange, Units.UnitType.LONG)+"[npc.cum+]从[npc.her][npc.cock+]中射出。",
					Units.fluid(cumChange, Units.UnitType.LONG)+"[npc.cum+]从[npc.her][npc.cock+]中喷出。"));
			if(withFormatting) {
				storedCumSB.append(")]");
			}
			if(this.cumStored==0) {
				storedCumSB.append("<br/><i>[npc.Name]的[npc.balls]现在不再储存[npc.cum]了！</i>");
			}
			if(withFormatting) {
				storedCumSB.append("</p>");
			}
			return UtilText.parse(owner, storedCumSB.toString());
//			return UtilText.parse(owner, "<p style='text-align:center;'><i style='color:"+PresetColour.CUM.toWebHexString()+";'>"
//					+ UtilText.returnStringAtRandom(
//							Units.fluid(cumChange, Units.UnitType.LONG)+" of [npc.cum+] squirts out of [npc.her] [npc.cock+].",
//							Units.fluid(cumChange, Units.UnitType.LONG)+" of [npc.cum+] shoots out of [npc.her] [npc.cock+].",
//							Units.fluid(cumChange, Units.UnitType.LONG)+" of [npc.cum+] spurts out of [npc.her] [npc.cock+].")
//				+ "</i>"
//				+ (this.cumStored==0
//					?"<br/><i>[npc.Name] now [npc.has] no more [npc.cum] stored in [npc.her] [npc.balls]!</i>"
//					:"")
//				+ "</p>");
		}
	}
	public String setStoredCum(GameCharacter owner, float cumStored) {
		return setStoredCum(owner, cumStored, true);
	}

	// Regeneration:

	public FluidRegeneration getCumProductionRegeneration() {
		return FluidRegeneration.getFluidRegenerationFromInt(cumRegeneration);
	}

	public int getRawCumProductionRegenerationValue() {
		return cumRegeneration;
	}

	/**
	 * Sets the cumRegeneration. Value is bound to >=0 && <=FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay()
	 */
	public String setCumProductionRegeneration(GameCharacter owner, int cumRegeneration) {
		int oldRegeneration = this.cumRegeneration;
		this.cumRegeneration = Math.max(0, Math.min(cumRegeneration, FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay()));
		int regenerationChange = this.cumRegeneration - oldRegeneration;
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (regenerationChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]的[npc.cum]再生速率没有变化……)]</p>");
		}
		
		String regenerationDescriptor = getCumProductionRegeneration().getName();
		if (regenerationChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.balls]深处催生出一股令人不安的躁动翻腾的感觉，"
							+ "忽然一小股先走液从[npc.her]的[npc.cock]中流了出来，害得[npc.she]不禁发出了[npc.a_moan+]；"
								+ "这意味着[npc.her]的[npc.cum]再生速率[style.boldGrow(增加)]了。<br/>"
						+ "[npc.Her]的[npc.cum]再生速率现在[style.boldSex(" + regenerationDescriptor + ")]("+Units.fluid(cumRegeneration)+"/天)！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "感觉到[npc.balls]深处催生出一股奇怪的吸取感，"
							+ "[npc.she]忽然禁不住倒吸了一口凉气，意识到[npc.her]的[npc.cum]再生速率竟然[style.boldShrink(下降)]了。<br/>"
						+ "[npc.Her]的[npc.cum]再生速率现在[style.boldSex(" + regenerationDescriptor + ")]("+Units.fluid(cumRegeneration)+"/天)！"
					+ "</p>");
		}
	}

	// Expulsion:

	public FluidExpulsion getCumExpulsion() {
		return FluidExpulsion.getFluidExpulsionFromInt(cumExpulsion);
	}

	public int getRawCumExpulsionValue() {
		return cumExpulsion;
	}

	public String setCumExpulsion(GameCharacter owner, int cumExpulsion) {
		int oldExpulsion = this.cumExpulsion;
		this.cumExpulsion = Math.max(0, Math.min(cumExpulsion, FluidExpulsion.FOUR_HUGE.getMaximumValue()));
		int expulsionChange = this.cumExpulsion - oldExpulsion;

		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (expulsionChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]的[npc.cum]射出量没有变化……)]</p>");
		}
		
		String expulsionDescriptor = getCumExpulsion().getDescriptor();
		if (expulsionChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]忽然感觉到[npc.balls]深处催生出一股奇怪的收缩感和压迫感，"
								+ "忽然一小股先走液从[npc.her]的[npc.cock]中喷射而出，让[npc.she]不禁发出一声[npc.a_moan+]；"
									+ "这意味着[npc.her]的[npc.cum]射出量[style.boldGrow(增加)]了。<br/>"
						+ "[npc.She]现在每次高潮时射出存储的[style.boldSex("+expulsionDescriptor+")]精液！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]忽然感觉到[npc.balls]深处催生出一股奇怪的松弛感，压力也有所下降，"
							+ "忽然一小股先走液从[npc.her]的[npc.cock]中流淌而出，让[npc.she]不禁发出一声[npc.a_moan+]；"
								+ "这意味着[npc.her]的[npc.cum]射出量[style.boldShrink(减少)]了。<br/>"
						+ "[npc.She]现在每次高潮时射出存储的[style.boldSex("+expulsionDescriptor+")]精液！"
					+ "</p>");
		}
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Testicle.class) && getType().getRace().isFeralPartsAvailable());
	}
}