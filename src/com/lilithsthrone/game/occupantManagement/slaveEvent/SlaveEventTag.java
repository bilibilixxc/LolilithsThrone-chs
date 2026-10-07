package com.lilithsthrone.game.occupantManagement.slaveEvent;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.2
 * @version 0.4.10.7
 * @author Innoxia
 */
public enum SlaveEventTag {
	
	WASHED_BODY_ANAL_CREAMPIE("<span style='color:"+PresetColour.BASE_AQUA.toWebHexString()+";'>清理了肛门内射物</span>"),
	
	WASHED_BODY_VAGINAL_CREAMPIE("<span style='color:"+PresetColour.BASE_AQUA.toWebHexString()+";'>清理了阴道内射物</span>"),
	
	WASHED_BODY_NIPPLE_CREAMPIE("<span style='color:"+PresetColour.BASE_AQUA.toWebHexString()+";'>清理了乳头内射物</span>"),

	WASHED_BODY_REMOVED_MUSK("<span style='color:"+PresetColour.BASE_AQUA.toWebHexString()+";'>清除了浓重的气味</span>") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.clearMuskMarkers();
			character.calculateStatusEffects(0);
		}
	},
	
	WASHED_CLOTHES("<span style='color:"+PresetColour.BASE_AQUA.toWebHexString()+";'>清理了衣物</span>"),
	
	
	// Pills:

	DAILY_PILL_USE_LUBE("[style.colourSex(服用[#ITEM_innoxia_pills_lubrication.getName(false)])]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.useItem(Main.game.getItemGen().generateItem("innoxia_pills_lubrication"), character, false);
		}
	},
	
	DAILY_PILL_USE_STERILITY("[style.colourSex(服用[#ITEM_innoxia_pills_sterility.getName(false)])]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.useItem(Main.game.getItemGen().generateItem("innoxia_pills_sterility"), character, false);
		}
	},
	
	DAILY_PILL_USE_FERTILITY("[style.colourSex(服用[#ITEM_innoxia_pills_fertility.getName(false)])]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), character, false);
		}
	},

	DAILY_PILL_USE_BROODMOTHER("[style.colourSex(服用[#ITEM_innoxia_pills_broodmother.getName(false)])]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.useItem(Main.game.getItemGen().generateItem("innoxia_pills_broodmother"), character, false);
		}
	},
	
	
	// Muscle:
	
	DAILY_MUSCLE_LOSS_LARGE("[style.boldShrink(-5)][style.boldMuscleZero(肌肉量)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementMuscle(-5);
		}
	},
	
	DAILY_MUSCLE_LOSS("[style.boldShrink(-1)][style.boldMuscleOne(肌肉量)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementMuscle(-1);
		}
	},
	
	DAILY_MUSCLE_GAIN("[style.boldGrow(+1)][style.boldMuscleThree(肌肉量)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementMuscle(1);
		}
	},
	
	DAILY_MUSCLE_GAIN_LARGE("[style.boldGrow(+5)][style.boldMuscleFour(肌肉量)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementMuscle(5);
		}
	},
	
	// Body Size;
	
	DAILY_BODY_SIZE_LOSS_LARGE("[style.boldShrink(-5)][style.boldBodySizeZero(体型)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementBodySize(-5);
		}
	},
	
	DAILY_BODY_SIZE_LOSS("[style.boldShrink(-1)][style.boldBodySizeOne(体型)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementBodySize(-1);
		}
	},
	
	DAILY_BODY_SIZE_GAIN("[style.boldGrow(+1)][style.boldBodySizeThree(体型)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementBodySize(1);
		}
	},
	
	DAILY_BODY_SIZE_GAIN_LARGE("[style.boldGrow(+5)][style.boldBodySizeFour(体型)]") {
		@Override
		public void applyEffects(GameCharacter character) {
			character.incrementBodySize(5);
		}
	},
	
	// Jobs:

	JOB_MILK_SOLD(""),
//	JOB_CUM_SOLD("[npc.NamePos] [npc.cum] was sold!"),
//	JOB_MILK_SOLD("[npc.NamePos] [npc.milk] was sold!"),
//	JOB_GIRLCUM_SOLD("[npc.NamePos] [npc.girlcum] was sold!"),

	JOB_MILK_STORED(""),
//	JOB_CUM_MILKED("[npc.NamePos] [npc.cum] was milked!"),
//	JOB_MILK_MILKED("[npc.NamePos] [npc.milk] was milked!"),
//	JOB_MILK_CROTCH_MILKED("[npc.NamePos] udder-[npc.crotchMilk] was milked!"),
//	JOB_GIRLCUM_MILKED("[npc.NamePos] [npc.girlcum] was milked!"),

	
	JOB_LILAYA_INTRUSIVE_TESTING("莉莱雅在[npc.name]身上进行了一些侵入性实验。"),
	
	JOB_LILAYA_FEMININE_TF("莉莱雅在[npc.Name]身上进行了一些侵入性极强的女性转化实验。"),
	JOB_LILAYA_MASCULINE_TF("莉莱雅在[npc.Name]身上进行了一些侵入性极强的男性转化实验。"),

	JOB_STOCKS_USED("[npc.Name]被人群里的某人使用了。"),
	
	JOB_PROSTITUTE_USED("[npc.Name]被一名客户使用了。")
	;
	
	private String description;
	
	private SlaveEventTag(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	// For sue in overriding.
	public void applyEffects(GameCharacter character) {
	}
}
