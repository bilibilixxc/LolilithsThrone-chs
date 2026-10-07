package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Breast implements BodyPartInterface {
	
	public static final int MAXIMUM_BREAST_ROWS = 6;
	public static final int MAXIMUM_NIPPLES_PER_BREAST = 4;
	
	protected AbstractBreastType type;
	protected BreastShape shape;
	protected int size;
	protected int rows;
	protected int milkStorage;
	public float milkStored;
	/** Measured in mL/day */
    public int milkRegeneration;
	protected int nippleCountPerBreast;
	
	public Nipples nipples;
	public FluidMilk milk;
	
	/**
	 * @param size in inches from bust to underbust using the UK system.
	 * @param lactation in mL.
	 */
	public Breast(AbstractBreastType type, BreastShape shape, int size, int milkStorage, int rows,
			int nippleSize, NippleShape nippleShape, int areolaeSize, AreolaeShape areolaeShape, int nippleCountPerBreast, float capacity, int depth, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.shape = shape;
		this.size = size;
		this.milkStorage = milkStorage;
		this.milkStored = milkStorage;
		this.milkRegeneration = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay();
		this.rows = rows;
		this.nippleCountPerBreast = nippleCountPerBreast;
		
		this.nipples = new Nipples(type.getNippleType(), nippleSize, nippleShape, areolaeSize, areolaeShape, Lactation.getLactationFromInt(milkStorage).getAssociatedWetness().getValue(), capacity, depth, elasticity, plasticity, virgin, false);
		
		this.milk = new FluidMilk(type.getFluidType(), false);
	}

	public Breast(Breast breastToCopy) {
		this.type = breastToCopy.type;
		this.shape = breastToCopy.shape;
		this.size = breastToCopy.size;
		this.milkStorage = breastToCopy.milkStorage;
		this.milkStored = breastToCopy.milkStored;
		this.milkRegeneration = breastToCopy.milkRegeneration;
		this.rows = breastToCopy.rows;
		this.nippleCountPerBreast = breastToCopy.nippleCountPerBreast;
		
		this.nipples = new Nipples(breastToCopy.nipples);
		
		this.milk = new FluidMilk(breastToCopy.milk);
	}
	
	@Override
	public AbstractBreastType getType() {
		return type;
	}

	public BreastShape getShape() {
		return shape;
	}

	public String setShape(GameCharacter owner, BreastShape shape) {
		if (shape == getShape()) {
			if(!isCharacterInitialised(owner)) {
				return "";
			}
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经拥有了"+shape.getDescriptor()+" 的胸部，所以无事发生……)]</p>");
		}
		
		this.shape = shape;
		
		if(!owner.hasBreasts()) {
			return UtilText.parse(owner,
					"<p>"
						+ "一种奇怪的刺痛感涌上[npc.namePos][npc.breasts+]，但由于[npc.she]没有乳房，所以无事发生……<br/>"
						+ "如果[npc.she]曾发育过乳房，那么[npc.name]现在将会拥有[style.boldSex("+shape.getDescriptor()+"的乳房)]！"
					+ "</p>");
		}
		
		return UtilText.parse(owner,
				"<p>"
					+ "一种奇怪的刺痛感涌上[npc.namePos][npc.breasts+]，在[npc.she]还没搞清楚发生了什么的时候，[npc.she]的乳房已经变成了新的模样……<br/>"
					+ "[npc.Name]现在拥有了[style.boldSex("+shape.getDescriptor()+"乳房)]！"
				+ "</p>");
		
	}

	public Nipples getNipples() {
		return nipples;
	}

	public void setMilk(FluidMilk milk) {
		this.milk = milk;
	}

	public FluidMilk getMilk() {
		return milk;
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
		List<String> list = new ArrayList<>();
		
		if(nippleCountPerBreast == 4) {
			list.add("四乳头");
		} else if(nippleCountPerBreast == 3) {
			list.add("三乳头");
		} else if(nippleCountPerBreast == 2) {
			list.add("双乳头");
		} 
		
		list.add(type.getDescriptor(owner));
		list.add(this.getSize().getDescriptor());
		list.add(this.getShape().getDescriptor());
		
		if(owner.getBodyMaterial().getPartDescriptors()!=null && !owner.getBodyMaterial().getPartDescriptors().isEmpty()) {
			list.add(Util.randomItemFrom(owner.getBodyMaterial().getPartDescriptors()));
		}
		
		return Util.randomItemFrom(list);
	}

	public boolean hasBreasts() {
		return size>=CupSize.getMinimumCupSizeForBreasts().getMeasurement();
	}

	public String setType(GameCharacter owner, AbstractBreastType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			nipples.setType(owner, type.getNippleType());
			milk.setType(type.getFluidType());
			if(owner!=null) {
				owner.resetAreaKnownByCharacters(CoverableArea.BREASTS);
				owner.resetAreaKnownByCharacters(CoverableArea.NIPPLES);
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]早已具有[npc.a_breastRace]的乳房，所以无事发生……)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(
				"<p>"
					+ "当[npc.she]感觉到身体开始发生变化时，突然觉得[npc.namePos]身体前部异常柔软且敏感，让[npc.she]不由自主地发出了一声[npc.a_moan+]。"
					+"随着这种感觉越来越强烈，[npc.Her]乳头和乳晕感到刺痛并逐渐变硬，使得[npc.herHim]不断地喘息并发出淫荡的[npc.moan]。"
					+ "尽管[npc.her][npc.breasts]的[npc.breastFullDescription]显示没有发生变化，"
						+ "但是[npc.she]感觉到内部结构发生了变化，变成了新的模样。"
					+ "不一会，这种转化就结束了，留给[npc.herHim][npc.totalNipples]颗焕然一新的乳头。"
					+ "<br/>");
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		nipples.setType(owner, type.getNippleType());
		milk.setType(type.getFluidType());
		owner.resetAreaKnownByCharacters(CoverableArea.BREASTS);
		owner.resetAreaKnownByCharacters(CoverableArea.NIPPLES);

		sb.append(type.getTransformationDescription(owner)+"</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
				+ owner.postTransformationCalculation(false)
				+ "</p>";
	}


	// Size:

	public CupSize getSize() {
		return CupSize.getCupSizeFromInt(size);
	}

	public int getRawSizeValue() {
		return size;
	}

	/**
	 * Sets the raw size value. Value is bound to >=0 && <=CupSize.MAXIMUM.getMeasurement()
	 * 
	 * @param size Value to set size to.
	 * @return description of size change
	 */
	public String setSize(GameCharacter owner, int size) {
		boolean hadBreasts = hasBreasts();
		
		int oldSize = this.size;
		this.size = Math.max(0, Math.min(size, CupSize.getMaximumCupSize().getMeasurement()));
		int sizeChange = this.size - oldSize;
		if(!isCharacterInitialised(owner)) {
			this.size = size;
			return "";
		}
		
		if(!isAbleToIncubateEggs() && owner.getIncubationLitter(SexAreaOrifice.NIPPLE)!=null) {
			this.size = CupSize.getMinimumCupSizeForEggIncubation().getMeasurement();
			return UtilText.parse(owner, "<p style='text-align:center;'>由于[npc.namePos]乳房正在孵卵，"
					+ "[style.colourMinorBad(导致乳房的体积无法缩小"+CupSize.getMinimumCupSizeForEggIncubation().getCupSizeName()+"罩杯)]！</p>");
		}

		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.breasts]大小无法改变……)]</p>");
		}
		
		String sizeDescriptor = getSize().getDescriptor();
		if (sizeChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到一阵酥麻的热感迅速席卷了[npc.her]的躯干，令[npc.she]难以自抑地发出一声[npc.a_moan+]的同时"
						+ (hadBreasts
								? "[npc.breasts]膨胀[style.boldGrow(变大)]。<br/>"
								: "胸脯涨大起来，在[npc.she]意识到发生了什么之前，一对奶子已经在[npc.her]那曾经平坦的躯干上[style.boldGrow(长出)]。<br/>")
						+ "[npc.Name]现在拥有了[style.boldSex(" + sizeDescriptor + (getSize().getMeasurement()>CupSize.AA.getMeasurement()?"的"+getSize().getCupSizeName()+"罩杯":"") + "乳房)]！"
					+ "</p>");
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到一阵酥麻的热感迅速席卷了[npc.her]的躯干，"
							+ "令[npc.she]不禁发出一声苦闷的[npc.moan]，同时[npc.her]的[npc.breasts]也收缩起来并且[style.boldShrink(变得更小)]。<br/>"
						+ (this.size==0
							? "[npc.Name]现在拥有了[style.boldSex(完全平坦的胸部)]！"
							: "[npc.Name]现在拥有了[style.boldSex(" + sizeDescriptor + (getSize().getMeasurement()>CupSize.AA.getMeasurement()?"的"+getSize().getCupSizeName()+"罩杯":"") + "乳房)]！")
					+ "</p>");
		}
	}

	// Lactation:

	public Lactation getMilkStorage() {
		return Lactation.getLactationFromInt(milkStorage);
	}

	public int getRawMilkStorageValue() {
		return milkStorage;
	}

	/**
	 * Sets the milkStorage. Value is bound to >=0 && <=Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()
	 */
	public String setMilkStorage(GameCharacter owner, int milkStorage) {
		int oldLactation = this.milkStorage;
		this.milkStorage = Math.max(0, Math.min(milkStorage, Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()));
		int lactationChange = this.milkStorage - oldLactation;
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (lactationChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.nameIsFull]所能产生的[npc.milk]总量没有变化……)]</p>");
		}
		
		String lactationDescriptor = getMilkStorage().getDescriptor();
		if (lactationChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.her]的[npc.breasts]深处有种奇怪的感觉在沸腾并翻滚，[npc.a_moan+]从[npc.her][npc.lips]之间飘出，同时几滴[npc.milk]从"
							+ "[npc.her]的[npc.nipples]漏下；显然[npc.her]的[npc.milk]产量[style.boldGrow(增加)]了。<br/>"
						+ "[npc.NameIsFull]现在可以生产[style.boldSex(" + lactationDescriptor + "[npc.milk])]了！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.her]的[npc.breasts]深处有一种奇怪的吮吸感，"
							+ "一声苦闷的叹息从[npc.her][npc.lips]间飘出，同时[npc.she]意识到这正是[npc.her]的[npc.milk]产量[style.boldShrink(枯竭)]的感觉。<br/>"
						+ "[npc.NameIsFull]现在可以生产[style.boldSex(" + lactationDescriptor + "[npc.milk])]。"
					+ "</p>");
		}
	}
	
	// Stored milk:

	public Lactation getStoredMilk() {
		return Lactation.getLactationFromInt((int) milkStored);
	}
	
	public float getRawStoredMilkValue() {
		return milkStored;
	}

	/**
	 * Sets the milkStorage. Value is bound to >=0 && <=getRawMilkStorageValue()
	 */
	public String setStoredMilk(GameCharacter owner, float milkStored) {
		float oldStoredMilk = this.milkStored;
		this.milkStored = Math.max(0, (Math.min(milkStored, getRawMilkStorageValue())));
		float lactationChange = oldStoredMilk - this.milkStored;
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (lactationChange <= 0) {
			return "";
		} else {
			return UtilText.parse(owner,
					"<p style='text-align:center;'><i style='color:"+PresetColour.BASE_YELLOW_LIGHT.toWebHexString()+";'>"
							+ UtilText.returnStringAtRandom(
									Units.fluid(lactationChange, Units.UnitType.LONG)+"的[npc.milk]从[npc.namePos][npc.nipples+]中挤出。",
									Units.fluid(lactationChange, Units.UnitType.LONG)+"的[npc.milk+]从[npc.namePos][npc.nipples+]中挤出。",
									Units.fluid(lactationChange, Units.UnitType.LONG)+"的[npc.milk]从[npc.namePos][npc.nipples+]中漏出。",
									Units.fluid(lactationChange, Units.UnitType.LONG)+"的[npc.milk+]从[npc.namePos][npc.nipples+]中漏出。")
					+ "</i>"
					+ (this.milkStored==0
						?"<br/><i>[npc.Name]的乳房中已经没有更多的[npc.milk]存量了！</i>"
						:"")
					+ "</p>");
		}
	}

	// Regeneration:

	public FluidRegeneration getLactationRegeneration() {
		return FluidRegeneration.getFluidRegenerationFromInt(milkRegeneration);
	}

	public int getRawLactationRegenerationValue() {
		return milkRegeneration;
	}

	/**
	 * Sets the milkRegeneration. Value is bound to >=0 && <=FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay()
	 */
	public String setLactationRegeneration(GameCharacter owner, int milkRegeneration) {
		int oldRegeneration = this.milkRegeneration;
		this.milkRegeneration = Math.max(0, Math.min(milkRegeneration, FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay()));
		int regenerationChange = this.milkRegeneration - oldRegeneration;

		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (regenerationChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.milk]再生速率没有变化……)]</p>");
		}
		
		String regenerationDescriptor = getLactationRegeneration().getName();
		if (regenerationChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.her]的[npc.breasts]深处有种令人担忧的感觉在沸腾并翻滚，[npc.a_moan+]从[npc.her][npc.lips]之间飘出，同时几滴[npc.milk]从"
							+ "[npc.her]的[npc.nipples]漏下；显然[npc.her]的[npc.milk]再生速率[style.boldGrow(增加)]了。<br/>"
						+ "[npc.NamePos][npc.milk]再生速率现在是[style.boldSex(" + regenerationDescriptor + ")] ("+Units.fluid(milkRegeneration)+"/日)！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.her]的[npc.breasts]深处有一种奇怪的吮吸感，"
							+ "一声苦闷的叹息从[npc.her][npc.lips]间飘出，同时[npc.she]意识到这正是[npc.her]的[npc.milk]再生速率[style.boldShrink(减少)]的感觉。<br/>"
						+ "[npc.NamePos][npc.milk]再生速率现在是[style.boldSex(" + regenerationDescriptor + ")] ("+Units.fluid(milkRegeneration)+"/日)！"
					+ "</p>");
		}
	}
	
	
	
	// Rows:
	
	public int getRows() {
		return rows;
	}

	public String setRows(GameCharacter owner, int rows) {
		rows = Math.max(1, Math.min(rows, MAXIMUM_BREAST_ROWS));
		
		if(!isCharacterInitialised(owner)) {
			this.rows = rows;
			return "";
		}
		
		if(rows == getRows()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.nameHasFull]的胸部排数没有变化……)]</p>");
		}
		
		String transformation = "";
		
		int rowsDifference = Math.abs(rows - getRows());
		
		if (rows < getRows()) {
			transformation = UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]担心地向下瞄去，[npc.she]的躯干感受到了一种奇怪的紧绷感，然后在[npc.she]还没有完全反应过来之前，[npc.her]"
							+ (rowsDifference==1
								?"最下方的一对[npc.breasts]"
								:"最下方的"+Util.intToString(rowsDifference)+"几对[npc.breasts]")
							+ "迅速地缩进[npc.her]躯干的[npc.skin]之下，然后[style.boldShrink(消失)]了。<br/>"
							+ "[npc.Name]现在拥有[style.boldSex("+ Util.intToString(rows) + "对"+ (hasBreasts() ? "乳房" : "平板胸部") +")]！" 
						+ "</p>");
			
		} else if (rows > getRows()) {
			transformation = UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]惊讶地向下瞄去，[npc.she]的躯干感受到了一种奇怪的紧绷感，然后在[npc.she]还没有完全反应过来之前，[npc.her]"
							+ (rowsDifference==1
								?"一对额外的[npc.breasts]"
								:Util.intToString(rowsDifference)+"额外的几对[npc.breasts]")
							+ "从[npc.her]躯干的[npc.skin]下快速地[style.boldGrow(生长)]出来。<br/>"
							+ "[npc.Name]现在拥有[style.boldSex("+ Util.intToString(rows) + "对"+ (hasBreasts() ? "乳房" : "平板胸部") +")]！" 
						+ "</p>");
		}

		this.rows = rows;

		return transformation;
	}

	public boolean isFuckable() {
		return nipples.getOrificeNipples().getCapacity() != Capacity.ZERO_IMPENETRABLE
				&& size >= CupSize.getMinimumCupSizeForPenetration().getMeasurement()
				&& Main.game.isNipplePenEnabled();
	}

	public boolean isAbleToIncubateEggs() {
		return this.size>=CupSize.getMinimumCupSizeForEggIncubation().getMeasurement();
	}

	public int getNippleCountPerBreast() {
		return nippleCountPerBreast;
	}

	/**
	 * Minimum 1, maximum MAXIMUM_NIPPLES_PER_BREAST
	 */
	public String setNippleCountPerBreast(GameCharacter owner, int nippleCountPerBreast) {
		nippleCountPerBreast = Math.max(1, Math.min(nippleCountPerBreast, MAXIMUM_NIPPLES_PER_BREAST));

		if(!isCharacterInitialised(owner)) {
			this.nippleCountPerBreast = nippleCountPerBreast;
			return "";
		}
		
		if (this.nippleCountPerBreast == nippleCountPerBreast) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.nameHasFull]的[npc.nipples]数量没有变化……)]</p>");
		}
		
		String transformation = "";
		
		if (nippleCountPerBreast < getNippleCountPerBreast()) {
			transformation = UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到在覆盖着[npc.her][npc.breasts]的[npc.breastSkin]表面下传来一阵奇异的麻刺感。"
						+ "当那股力量从[npc.her]的[npc.nipples]中突然冒出，[npc.her]口中发出一声震惊的吸气声，"
							+ "接着[npc.she][npc.moaning]，与此同时那些[npc.nipples][style.boldShrink(收缩)]进入了[npc.her]的[npc.breasts]之中。<br/>"
						+ "[npc.Name]现在拥有[style.boldSex("+ Util.intToString(nippleCountPerBreast) + ""+ (nippleCountPerBreast > 1 ? "[npc.nipples]" : "[npc.nipple(true)]") + "在[npc.her]的每个" + (hasBreasts() ? "乳房上" : "平板胸部上") +")]！" 
					+ "</p>");
			
		} else if (nippleCountPerBreast > getNippleCountPerBreast()) {
			transformation = UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到在覆盖着[npc.her][npc.breasts]的[npc.breastSkin]表面下传来一阵奇异的麻刺感。"
						+ "当那股力量从[npc.her]的[npc.nipples]中突然冒出，[npc.her]口中发出一声震惊的吸气声，"
							+ "[npc.she]接着[npc.moaning]，与此同时[npc.she]感觉到新的[npc.nipples]从[npc.breasts]上[style.boldGrow(生长)]出来。<br/>"
						+ "[npc.Name]现在拥有[style.boldSex("+ Util.intToString(nippleCountPerBreast) + ""+ (nippleCountPerBreast > 1 ? "[npc.nipples]" : "[npc.nipple(true)]") + "在[npc.her]的每个" + (hasBreasts() ? "乳房上" : "平板胸部上") +")]！" 
					+ "</p>");
			
		}
		
		this.nippleCountPerBreast = nippleCountPerBreast;

		return transformation;
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Breast.class) && getType().getRace().isFeralPartsAvailable());
	}

}
