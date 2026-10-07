package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.1
 * @version 0.4.9.7
 * @author Innoxia
 */
public class BreastCrotch implements BodyPartInterface {
	
	public static final int MAXIMUM_BREAST_ROWS = 6;
	public static final int MAXIMUM_NIPPLES_PER_BREAST = 4;
	
	protected AbstractBreastType type;
	protected BreastShape shape;
	protected int size;
	protected int rows;
	protected int milkStorage;
	public float milkStored;
	public int milkRegeneration;
	protected int nippleCountPerBreast;
	
	public Nipples nipples;
	public FluidMilk milk;
	
	public BreastCrotch(AbstractBreastType type, BreastShape shape, int size, int milkStorage, int rows,
			int nippleSize, NippleShape nippleShape, int areolaeSize, AreolaeShape areolaeShape, int nippleCountPerBreast, float capacity, int depth, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.shape = shape;
		this.size = size;
		this.milkStorage = milkStorage;
		milkStored = milkStorage;
		milkRegeneration = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay();
		this.rows = rows;
		this.nippleCountPerBreast = nippleCountPerBreast;
		
		nipples = new Nipples(type.getNippleType(), nippleSize, nippleShape, areolaeSize, areolaeShape, Lactation.getLactationFromInt(milkStorage).getAssociatedWetness().getValue(), capacity, depth, elasticity, plasticity, virgin, true);
		
		milk = new FluidMilk(type.getFluidType(), true);
	}

	public BreastCrotch(BreastCrotch breastCrotchToCopy) {
		this.type = breastCrotchToCopy.type;
		this.shape = breastCrotchToCopy.shape;
		this.size = breastCrotchToCopy.size;
		this.milkStorage = breastCrotchToCopy.milkStorage;
		this.milkStored = breastCrotchToCopy.milkStored;
		this.milkRegeneration = breastCrotchToCopy.milkRegeneration;
		this.rows = breastCrotchToCopy.rows;
		this.nippleCountPerBreast = breastCrotchToCopy.nippleCountPerBreast;
		
		this.nipples = new Nipples(breastCrotchToCopy.nipples);
		
		this.milk = new FluidMilk(breastCrotchToCopy.milk);
	}
	
	@Override
	public AbstractBreastType getType() {
		return type;
	}

	public boolean isVisibleThroughClothing(GameCharacter owner) {
		if(owner.getLegConfiguration()==LegConfiguration.BIPEDAL) { // Non-bipedal clothing is assumed to conceal the body so that crotch-boobs are not visible.
			return size>=CupSize.A.getMeasurement();
		} else {
			return false;
		}
	}
	
	public BreastShape getShape() {
		return shape;
	}
	
	public String setShape(GameCharacter owner, BreastShape shape) {
		if (shape == getShape()) {
			if(!isCharacterInitialised(owner)) {
				return "";
			}
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有"+shape.getDescriptor()+"[npc.crotchBoobs]了，所以无事发生……)]</p>");
		}
		
		this.shape = shape;
		if(shape!=BreastShape.UDDERS && this.getRows()==0) {
			this.rows = 1;
		}
		if(shape==BreastShape.UDDERS && this.getRows()==0 && this.getNippleCountPerBreast()==1) {
			this.nippleCountPerBreast = 2;
		}
		
		if(!owner.hasBreastsCrotch()) {
			return UtilText.parse(owner,
					"<p>"
						+ "一种奇怪的刺痛感在[npc.namePos]的胯部弥漫，但是[npc.she][npc.do]没有腹乳，什么事也没有发生……<br/>"
						+ "如果[npc.she]曾长过腹乳，那[npc.name]现在会有[style.boldSex("+shape.getDescriptor()+"腹乳)]！"
					+ "</p>");
		}
		return UtilText.parse(owner,
				"<p>"
					+ "一种奇怪的刺痛感涌上[npc.namePos]的胯部，在[npc.she]还没来得及搞清发生了什么时，[npc.her]的[npc.crotchBoobs]已经转变成了新的模样……<br/>"
					+ "[npc.Name]现在拥有了[style.boldSex("+shape.getDescriptor()+"[npc.crotchBoobs])]！"
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
		if(getShape()==BreastShape.UDDERS && this.getRows()==0) {
			return getNameSingular(gc);
		}
		return getNamePlural(gc);
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		if(getShape()==BreastShape.UDDERS) {
			return "腹乳";
		}
		return type.getCrotchNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		if(getShape()==BreastShape.UDDERS) {
			return "腹乳";
		}
		return type.getCrotchNamePlural(gc);
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
		return type!=BreastType.NONE;
	}

	public String setType(GameCharacter owner, AbstractBreastType type) {
		this.shape = Util.randomItemFrom(RacialBody.valueOfRace(type.getRace()).getBreastCrotchShapes());
		
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
		
		if(type==BreastType.NONE && owner.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH)!=null) {
			return UtilText.parse(owner, "<p style='text-align:center;'>由于[npc.nameIsFull]的[npc.crotchBoobs]正在孵卵，"
					+ "[style.colourMinorBad("+(getShape()==BreastShape.UDDERS && this.getRows()==0?"":"")+"无法移除)]！</p>");
		}
		
		if (type == getType()) {
			if(type.equals(BreastType.NONE)) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经没有[npc.crotchBoobs]了，所以无事发生……)]</p>");
			}
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有[npc.a_breastRace]的[npc.crotchBoobs]了，所以无事发生……)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(type.equals(BreastType.NONE)) { // Removal:
			sb.append(
					"<p>"
						+ "当[npc.namePos]感觉到身体开始发生变化时，突然觉得[npc.she]胯部往上的部位异常柔软且敏感，令[npc.she]情不自禁地发出[npc.a_moan+]。"
						+"[npc.her][npc.crotchBoobs]处传来刺痛感且变得软化，乳头和乳晕首先产生转变，整个[npc.crotchBoobs]迅速缩小并消失在覆盖下腹部的[npc.skin]中。"
						+ "<br/>");
			
		} else if(this.getType().equals(BreastType.NONE)) { // New addition:
			sb.append(
					"<p>"
						+ "当[npc.namePos]感觉到身体开始发生变化时，突然觉得[npc.she]胯部往上的部位异常柔软且敏感，令[npc.she]情不自禁地发出[npc.a_moan+]。"
						+ "在[npc.her]下腹部的[npc.skin]上，[npc.CrotchBoobsRows]开始长出坚硬的小疙瘩，"
							+ "当它们迅速膨胀成[npc.crotchBoobsSize][npc.crotchBoobs]时，[npc.she]难以自抑地发出一声惊叹。"
						+ "<br/>");
			
			
		} else {
			sb.append(
					"<p>"
						+ "当[npc.namePos]感觉到身体开始发生变化时，突然觉得[npc.she]胯部往上的部位异常柔软且敏感，令[npc.she]情不自禁地发出[npc.a_moan+]。"
						+"[npc.her][npc.crotchBoobs]的乳头和乳晕感到刺痛并逐渐变硬，使得[npc.herHim]不断地喘息并发出淫荡的[npc.moan]。"
						+ "虽然外貌没有变化，但[npc.she]感觉到[npc.her][npc.crotchBoobs]的内部结构发生了变化，变成了新的形态。"
						+ "不一会，这种转化就结束了，留给[npc.herHim][npc.totalNipples]颗焕然一新的乳头。"
						+ "<br/>");
		}
		
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		nipples.setType(owner, type.getNippleType());
		milk.setType(type.getFluidType());
		owner.resetAreaKnownByCharacters(CoverableArea.BREASTS);
		owner.resetAreaKnownByCharacters(CoverableArea.NIPPLES);

		sb.append(type.getTransformationCrotchDescription(owner)+"</p>");
		
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
		int oldSize = this.size;
		this.size = Math.max(0, Math.min(size, CupSize.getMaximumCupSize().getMeasurement()));
		int sizeChange = this.size - oldSize;
		if(!isCharacterInitialised(owner)) {
			this.size = size;
			return "";
		}

		if(!isAbleToIncubateEggs() && owner.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH)!=null) {
			this.size = CupSize.getMinimumCupSizeForEggIncubation().getMeasurement();
			return UtilText.parse(owner, "<p style='text-align:center;'>由于[npc.namePos][npc.crotchBoobs]正在孵卵，"
					+ "[style.colourMinorBad("+(getShape()==BreastShape.UDDERS && this.getRows()==0?"它":"它")+"无法缩小至"+CupSize.getMinimumCupSizeForEggIncubation().getCupSizeName()+"罩杯)]！</p>");
		}

		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos]的[npc.crotchBoobs]尺寸没有变化……)]</p>");
		}
		
		if (sizeChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到一阵酥麻的热感迅速席卷了[npc.her]的下半躯干，在[npc.she]难以自抑地发出一声[npc.a_moan+]的同时，[npc.her]的[npc.crotchBoobs]膨胀着[style.boldGrow(变得更大了)]。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex([npc.crotchBoobsSize][npc.crotchBoobs])]！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到一阵酥麻的热感迅速席卷了[npc.her]的下半躯干，"
							+ "令[npc.she]不禁发出一声苦闷的[npc.moan]，同时[npc.her]的[npc.crotchBoobs]也收缩着，[style.boldShrink(变得更小)]。<br/>"
						+ "[npc.Name]现在拥有了[style.boldSex([npc.crotchBoobsSize][npc.crotchBoobs])]！"
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.nameIsFull]所能产生的[npc.crotchMilk]总量没有变化……)]</p>");
		}
		
		String lactationDescriptor = getMilkStorage().getDescriptor();
		if (lactationChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.her]的[npc.crotchBoobs]深处有种奇怪的感觉在沸腾并翻滚，[npc.a_moan+]从[npc.her][npc.lips]之间飘出，同时几滴[npc.crotchMilk]从"
							+ "[npc.her]的[npc.crotchNipples]漏下；显然[npc.her]的[npc.crotchMilk]产量[style.boldGrow(增加)]了。<br/>"
						+ "[npc.NameIsFull]现在可以生产[style.boldSex(" + lactationDescriptor + "[npc.crotchMilk])]了！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.her]的[npc.crotchBoobs]深处有一种奇怪的吮吸感，"
							+ "一声苦闷的叹息从[npc.her][npc.lips]间飘出，同时[npc.she]意识到这正是[npc.her][npc.crotchMilk]产量[style.boldShrink(枯竭)]的感觉。<br/>"
						+ "[npc.NameIsFull]现在可以生产[style.boldSex(" + lactationDescriptor + "[npc.crotchMilk])]了。"
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
									Units.fluid(lactationChange, Units.UnitType.LONG)+"[npc.namePos][npc.crotchMilk]从[npc.her][npc.crotchNipples+]中挤出。",
									Units.fluid(lactationChange, Units.UnitType.LONG)+"的[npc.crotchMilk+]从[npc.namePos][npc.crotchNipples+]中挤出。",
									Units.fluid(lactationChange, Units.UnitType.LONG)+"[npc.namePos][npc.crotchMilk]从[npc.her][npc.crotchNipples+]中漏出。",
									Units.fluid(lactationChange, Units.UnitType.LONG)+"的[npc.crotchMilk+]从[npc.namePos][npc.crotchNipples+]中漏出。")
					+ "</i>"
					+ (this.milkStored==0
						?"<br/><i>[npc.Name]的[npc.crotchBoobs]中已经没有更多的[npc.crotchMilk]存量了！</i>"
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.crotchMilk]再生速率没有变化……)]</p>");
		}
		
		String regenerationDescriptor = getLactationRegeneration().getName();
		if (regenerationChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.her]的[npc.crotchBoobs]深处有种惊人的感觉在沸腾并翻滚，[npc.a_moan+]从[npc.her][npc.lips]之间飘出，同时几滴[npc.crotchMilk]从"
							+ "[npc.her]的[npc.crotchNipples]漏下；显然[npc.her]的[npc.crotchMilk]再生速率[style.boldGrow(增加)]了。<br/>"
						+ "[npc.NamePos][npc.crotchMilk]再生速率现在是[style.boldSex(" + regenerationDescriptor + ")]("+Units.fluid(milkRegeneration)+"/日)！"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name]感觉到[npc.her]的[npc.crotchBoobs]深处有一种奇怪的吮吸感，"
							+ "一声苦闷的叹息从[npc.her][npc.lips]间飘出，同时[npc.she]意识到这正是[npc.her]的[npc.crotchMilk]再生速率[style.boldShrink(减少)]的感觉。<br/>"
						+ "[npc.NamePos][npc.crotchMilk]再生速率现在是[style.boldSex(" + regenerationDescriptor + ")]("+Units.fluid(milkRegeneration)+"/日)！"
					+ "</p>");
		}
	}
	
	
	
	// Rows:
	
	public int getRows() {
		return rows;
	}

	public String setRows(GameCharacter owner, int rows) {
		if(this.getShape()==BreastShape.UDDERS) { // Udders can be configured into one single udder (as '0' rows):
			rows = Math.max(0, Math.min(rows, MAXIMUM_BREAST_ROWS));
		} else {
			rows = Math.max(1, Math.min(rows, MAXIMUM_BREAST_ROWS));
		}
		
		if(rows==0 && this.nippleCountPerBreast==1) {
			this.nippleCountPerBreast = 2;
		}
		
		if(!isCharacterInitialised(owner)) {
			this.rows = rows;
			return "";
		}
		
		if(rows == getRows()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.name]的胸部排数没有变化……)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		int rowsDifference = Math.abs(rows - getRows());
		
		if (rows < getRows()) {
			sb.append("<p>"
							+ "[npc.Name]担心地向下瞄去，[npc.she]的腹股沟升起了一股奇怪的紧绷感，然后在[npc.she]还没有完全反应过来之前，");
			
			if(this.getShape()==BreastShape.UDDERS) {
				sb.append((rowsDifference==1
						?"最顶上的那对[npc.crotchNipples]"
						:"最顶上的"+Util.intToString(rowsDifference)+"那对[npc.crotchNipples]"));
				sb.append("迅速地缩进[npc.her]腹乳之下，然后[style.boldShrink(消失)]了。<br/>");
			} else {
				sb.append((rowsDifference==1
						?"最顶上的那对[npc.crotchBoobs]"
						:"最顶上的"+Util.intToString(rowsDifference)+"那对[npc.crotchBoobs]"));
				sb.append("迅速地缩进[npc.her]腹部的[npc.skin]之下，然后[style.boldShrink(消失)]了。<br/>");
			}
			
			
			
		} else if (rows > getRows()) {
			sb.append("<p>"
						+ "[npc.Name]的腹股沟升起了一股奇怪的肿胀感，[npc.she]惊讶地倒吸了口气，然后在[npc.she]还没有完全反应过来之前，");
			
			if(this.getShape()==BreastShape.UDDERS) {
				sb.append((rowsDifference==1
						?"一对额外的[npc.crotchNipples]"
						:Util.intToString(rowsDifference)+"额外的一对[npc.crotchNipples]"));
				sb.append("从[npc.her]腹乳的上方快速地[style.boldGrow(生长)]出来。<br/>");
			} else {
				sb.append((rowsDifference==1
						?"一对额外的[npc.crotchBoobs]"
						:"额外的一对[npc.crotchBoobs]"));
				sb.append("从[npc.her]腹部的[npc.skin]下快速地[style.boldGrow(生长)]出来。<br/>");
			}
		}

		if(this.getShape()==BreastShape.UDDERS) {
			if(rows==0) {
				sb.append("[npc.Name]现在拥有了[style.boldSex(一对腹乳)]！" 
						+ "</p>");
			} else {
				sb.append("[npc.Name]现在拥有[style.boldSex("+ Util.intToString(rows) + "对腹乳)]！" 
						+ "</p>");
			}
		} else {
			sb.append("[npc.Name]现在拥有[style.boldSex("+ Util.intToString(rows) + "对"+ "[npc.crotchBoobs])]！" 
					+ "</p>");
		}

		this.rows = rows;

		return UtilText.parse(owner,sb.toString());
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
		if(this.getShape()==BreastShape.UDDERS && this.getRows()==0) {
			nippleCountPerBreast = Math.max(2, Math.min(nippleCountPerBreast, MAXIMUM_NIPPLES_PER_BREAST));
		} else {
			nippleCountPerBreast = Math.max(1, Math.min(nippleCountPerBreast, MAXIMUM_NIPPLES_PER_BREAST));
		}
		
		if(!isCharacterInitialised(owner)) {
			this.nippleCountPerBreast = nippleCountPerBreast;
			return "";
		}
		
		if (this.nippleCountPerBreast == nippleCountPerBreast) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.nameHasFull]的[npc.crotchNipples]数量没有变化……)]</p>");
		}
		
		String transformation = "";
		
		if (nippleCountPerBreast < getNippleCountPerBreast()) {
				transformation = UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]感觉到在覆盖着[npc.her][npc.crotchBoobs]的[npc.breastCrotchSkin]表面下传来一阵奇异的麻刺感。"
							+ "当那股力量从[npc.her]的[npc.crotchNipples]中突然冒出，[npc.her]口中发出一声震惊的吸气声，"
								+ "[npc.she]接着[npc.moaning]，与此同时那些[npc.crotchBoobs][style.boldShrink(收缩)]进入了[npc.her]的[npc.breasts]之中。<br/>"
							+ "[npc.Name]现在拥有[style.boldSex("+ Util.intToString(nippleCountPerBreast) + (nippleCountPerBreast > 1 ? "[npc.crotchNipples]" : "[npc.crotchNipple]") + "在[npc.her]每对[npc.crotchBoobs]的上)]！" 
						+ "</p>");
			
			
		} else if (nippleCountPerBreast > getNippleCountPerBreast()) {
				transformation = UtilText.parse(owner,
						"<p>"
							+ "[npc.Name]感觉到在覆盖着[npc.her][npc.crotchBoobs]的[npc.breastCrotchSkin]表面下传来一阵奇异的麻刺感。"
							+ "当那股力量从[npc.her]的[npc.crotchNipples]中突然冒出，[npc.her]口中发出一声震惊的吸气声，"
								+ "[npc.she]接着[npc.moaning]，与此同时[npc.she]感觉到新的[npc.nipples]从[npc.crotchBoobs]上[style.boldGrow(生长)]出来。<br/>"
							+ "[npc.Name]现在拥有[style.boldSex("+ Util.intToString(nippleCountPerBreast) + (nippleCountPerBreast > 1 ? "[npc.crotchNipples]" : "[npc.crotchNipple]") + "在[npc.her]每对[npc.crotchBoobs]的上)]！" 
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
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(BreastCrotch.class) && getType().getRace().isFeralPartsAvailable());
	}

}
