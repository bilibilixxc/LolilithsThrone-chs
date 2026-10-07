package com.lilithsthrone.game.occupantManagement.slaveEvent;

import com.lilithsthrone.game.character.GameCharacter;

/**
 * @since 0.2.2
 * @version 0.4.10.7
 * @author Innoxia
 */
public enum SlaveEvent {
	
	WASHED_BODY(SlaveEventType.MISCELLANEOUS, "洗净身体", "[npc.Name]洗净了身体。") {
		public void applyEffects(GameCharacter character) {
			character.washAllOrifices(true);
			character.cleanAllDirtySlots(true);
			character.calculateStatusEffects(0);
		}
	},
	
	WASHED_CLOTHES(SlaveEventType.MISCELLANEOUS, "洗净衣物", "[npc.Name]洗了[npc.her]的衣物。") {
		public void applyEffects(GameCharacter character) {
			character.cleanAllDirtySlots(false); //TODO should they be cleaning slots?
			character.cleanAllClothing(true, false);
			character.calculateStatusEffects(0);
		}
	},

	MIDDAY_UPDATE(SlaveEventType.MISCELLANEOUS, "正午更新", ""),
	
	DAILY_UPDATE(SlaveEventType.MISCELLANEOUS, "每日更新", ""),

	SLAVE_SEX(SlaveEventType.SEX, "[style.boldSex(Sex)]", "[npc.Name]和其他奴隶做爱了……"),
	
	SLAVE_BONDING(SlaveEventType.BONDING, "[style.boldAffection(羁绊)]", "[npc.Name]和另一个奴隶进行了交流……"),
	
	GAVE_BIRTH(SlaveEventType.SEX, "[style.boldExcellent(Gave birth)]", "莉莱雅给[npc.name]接生了。"),

	GAVE_BIRTH_INCUBATION(SlaveEventType.SEX, "[style.boldExcellent(Laid eggs)]", "莉莱雅帮[npc.name]产下了[npc.she]正在孵的卵。"),
	
	
	// Jobs:

//	JOB_CUM_MILKED("Cum Milked", "[npc.NamePos] [npc.cum+] was milked."),
	JOB_MILK_MILKED(SlaveEventType.JOB, "产奶", ""),
//	JOB_MILK_CROTCH_MILKED("Udders Milked", "[npc.Name] was milked of [npc.her] [npc.crotchMilk+]."),
//	JOB_GIRLCUM_MILKED("Girlcum Milked", "[npc.NamePos] [npc.girlcum+] was milked."),
	
	//TODO
//	JOB_CLEANING("Cleaning Fun", "<i>Placeholder event.</i>"),
//	
//	JOB_COOKING("Cooking Fun", "<i>Placeholder event.</i>"),
//	
//	JOB_LAB_ASSISTANT("Lilaya Fun", "<i>Placeholder event.</i>"),
//	
//	JOB_LIBRARIAN("Librarian Fun", "<i>Placeholder event.</i>"),
//
//	JOB_OFFICE("Office Fun", "<i>Placeholder event.</i>"),
	
	JOB_TEST_SUBJECT(SlaveEventType.JOB, "测试用具", "[npc.Name]被莉莱雅做了转化实验……"),
	
	JOB_PUBLIC_STOCKS(SlaveEventType.JOB, "锁在颈手枷上", "[npc.Name]被锁在了奴隶巷的公共颈手枷上……"),
	
	JOB_PROSTITUTE(SlaveEventType.JOB, "卖淫", "[npc.Name]在天使之吻当妓女。"),

//	JOB_IDLE("Resting", "<i>Placeholder event.</i>"),
	;
	
	private SlaveEventType type;
	private String name;
	private String description;
	
	private SlaveEvent(SlaveEventType type, String name, String description) {
		this.type = type;
		this.name = name;
		this.description = description;
	}

	public SlaveEventType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	// For use in overriding.
	public void applyEffects(GameCharacter character) {
	}
}
