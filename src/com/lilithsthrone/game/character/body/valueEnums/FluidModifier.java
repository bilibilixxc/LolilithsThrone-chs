package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.FluidInterface;
import com.lilithsthrone.game.character.effects.Addiction;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.83
 * @version 0.4.4
 * @author Innoxia
 */
public enum FluidModifier {
	
	VISCOUS(PresetColour.BASE_PURPLE_DARK,
			false,
			"粘稠",
			"十分粘稠，会缓慢地成块滴落而下，如同浓厚的糖蜜一般。",
			"使得该液体拥有固体和液体之间的粘稠度。"),
	
	STICKY(PresetColour.BASE_YELLOW_LIGHT,
			false,
			"黏糊糊",
			"十分黏糊，不使用肥皂就不能清洗干净。",
			"使得该液体能够黏住接触的任何东西。"),
	
	SLIMY(PresetColour.BASE_BLUE_LIGHT,
			false,
			"粘滑",
			"拥有粘滑、油质的质感。",
			"使得该液体感觉像粘液。"),
	
	BUBBLING(PresetColour.BASE_LILAC_LIGHT,
			false,
			"起泡",
			"如碳酸饮料一般会起气泡。",
			"使得该液体会像碳酸饮料一样起泡。"),
	
	// SPECIAL EFFECTS:

	MUSKY(PresetColour.BASE_TAN,
			true,
			"气味浓重",
			"拥有强烈的淫味。",
			"气味浓重的精液或爱液如果在性交中沾染在某人身上，则会使其获得“被淫味标记”的效果。"),
	
	
	MINERAL_OIL(PresetColour.BASE_BLACK,
			true,
			"矿物油",
			"富含会滋润皮肤但腐蚀橡胶制品的矿物质。",
			"混入矿物油的液体能够迅速融化避孕套，导致其在高潮时破裂。"),
	
	ALCOHOLIC(PresetColour.BASE_ORANGE,
			true,
			"高度酒精",
			"含有高度酒精，会使得喝下该液体的角色醉酒。",
			"含高度酒精的液体会急剧增加喝下该液体角色的醉酒等级。") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			return target.incrementAlcoholLevel(millilitres * 0.001f); //TODO factor in body size
		}
	},

	ALCOHOLIC_WEAK(PresetColour.BASE_ORANGE_LIGHT,
			true,
			"含酒精",
			"酒精含量较低，但也会使人醉酒。",
			"含酒精的液体会增加饮用者的醉酒等级。") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			return target.incrementAlcoholLevel(millilitres * 0.0001f); //TODO factor in body size
		}
	},
	
	ADDICTIVE(PresetColour.BASE_PINK,
			true,
			"成瘾性",
			"有极高的成瘾性，无论谁只要喝了太多，就会迅速对其产生依赖。",
			"成瘾性液体会让任何饮用它们的人对这种特定类型的液体上瘾。") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			if(target==null || fluidProvider==null) {
				return ""; // catch for if one of the characters is null, which was the case in GameCharacter.calculateGenericSexEffects
			}
			if(target.isDoll()) {
				return "";
			}
			boolean curedWithdrawal = target.getAddiction(fluid.getType())!=null && Main.game.getMinutesPassed()-target.getAddiction(fluid.getType()).getLastTimeSatisfied()>=24*60;
			boolean appendAddiction = !Main.game.isInSex() || curedWithdrawal;
			if(target.addAddiction(new Addiction(fluid.getType(), Main.game.getMinutesPassed(), fluidProvider.getId()))) {
				return UtilText.parse(target,
						"<p style='padding:0; margin:0; text-align:center;'>"
							+ "由于"+(fluidProvider==null?"":(fluidProvider.equals(target)?"[npc.her]的":UtilText.parse(fluidProvider, "[npc.namePos]的")))+""+fluid.getName(fluidProvider)
								+"，[npc.name]发觉自己极其[style.colourArcane(渴望)]"
								+ "<span style='color:"+fluid.getType().getRace().getColour().toWebHexString()+";'>"+fluid.getType().getRace().getName(fluidProvider.getBody(), fluid.isFeral(fluidProvider))+"</span>的"+fluid.getName(fluidProvider)+"！"
						+ "</p>");
				
				
			} else {
				target.setLastTimeSatisfiedAddiction(fluid.getType(), Main.game.getMinutesPassed());
				if(appendAddiction) {
					return UtilText.parse(target, fluidProvider,
							"<p style='padding:0; margin:0; text-align:center;'>"
								+ "[npc.NamePos]对于<span style='color:"+fluid.getType().getRace().getColour().toWebHexString()+";'>"
									+fluid.getType().getRace().getName(fluidProvider.getBody(), fluid.isFeral(fluidProvider))
								+"</span>"+fluid.getName(fluidProvider)
									+"的[style.colourArcane(渴求)]得到了满足！"
								+ (curedWithdrawal
									?"[npc.She]感激着"+(fluidProvider==null?"":UtilText.parse(fluidProvider, "[npc.name]"))+"，能够提供给[npc.herHim]最需要的东西……"
											+ (target.isSlave()?target.incrementObedience(5):"")
									:"[npc.She]并没有受到戒断反应的影响，但仍旧感激"
											+(fluidProvider==null?"":UtilText.parse(fluidProvider, "[npc.name]"))+"，能够缓解[npc.her]的瘾感……")
							+ "</p>");
				}
				return "";
			}
		}
	},
	
	HALLUCINOGENIC(PresetColour.BASE_PINK_DEEP,
			true,
			"致幻性",
			"任何摄入该液体的角色都会受到致幻效果，表现为泌乳相关的幻觉或易受催眠暗示的影响。",
			"拥有致幻性的液体会导致摄入的角色经历一段幻觉，使其性高潮的体验被扭曲，并且有可能被催眠操控。") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			if(target.hasPerkAnywhereInTree(Perk.DOLL_PHYSICAL_3)) {
				return "";
			}
			target.addPsychoactiveFluidIngested(fluid.getType());
			boolean appendPsychoactive = !target.hasStatusEffect(StatusEffect.PSYCHOACTIVE);
			target.addStatusEffect(StatusEffect.PSYCHOACTIVE, 6*60*60);
			if(appendPsychoactive) {
				return UtilText.parse(target,
						"<p style='padding:0; margin:0; text-align:center;'>"
							+ "由于"+(fluidProvider==null?"":(fluidProvider.equals(target)?"[npc.her]的":UtilText.parse(fluidProvider, "[npc.namePos]的")))+""+fluid.getName(fluidProvider)
								+"致幻属性，[npc.name]<span style='color:"+PresetColour.PSYCHOACTIVE.toWebHexString()+";'>陷入幻觉</span>了！"
						+ "</p>");
			}
			return "";
		}
	};
	
	private Colour colour;
	private boolean specialEffects;
	private String name;
	private String description;
	private String briefDescription;
	
	private FluidModifier(Colour colour, boolean specialEffects, String name, String briefDescription, String description) {
		this.colour = colour;
		this.specialEffects = specialEffects;
		this.name = name;
		this.briefDescription = briefDescription;
		this.description = description;
	}

	public Colour getColour() {
		return colour;
	}

	public boolean isSpecialEffects() {
		return specialEffects;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBriefDescription() {
		return briefDescription;
	}
	
	public String getDescription() {
		return description;
	}

	public boolean isAppliesSpecialEffects() {
		return description!=null;
	}
	
	public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
		return "";
	}
}
