package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * All SexSlots that are used in the ALL_FOURS position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotAllFours {

	public static final SexSlot ALL_FOURS = new SexSlot(
			"四肢跪地",
			"四肢跪地",
			"[npc.Name]的[npc.legs]开始打颤，[npc.she]正在尽全力稳住自己，但随着[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false,
			SexSlotTag.ALL_FOURS);
	public static final SexSlot ALL_FOURS_TWO = new SexSlot(ALL_FOURS){
		@Override
		public String getDescription() {
			return "四肢跪地(二)";
		}
	};
	public static final SexSlot ALL_FOURS_THREE = new SexSlot(ALL_FOURS){
		@Override
		public String getDescription() {
			return "四肢跪地(三)";
		}
	};
	public static final SexSlot ALL_FOURS_FOUR = new SexSlot(ALL_FOURS){
		@Override
		public String getDescription() {
			return "四肢跪地(四)";
		}
	};
	

	public static final SexSlot BEHIND = new SexSlot(
			"骑乘交配/插入",
			"身后",
			"[npc.Name]将全身压在[npc2.namePos]身上，发出[npc.a_moan+]，准备好迎接高潮。",
			true,
			SexSlotTag.BEHIND_ALL_FOURS) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return (target.getLegConfiguration()==LegConfiguration.QUADRUPEDAL
						?"骑在"
						:standing?"站在":"跪在")
					+UtilText.parse(partner, "[npc.name]身后");
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			if(orgasmingCharacter.equals(targetedCharacter)) {
				return "[npc.Name]发出了一声[npc.a_moan+]，即将迎来高潮。";
			}
			if(orgasmingCharacter.isFeral()) {
				return "[npc.Name]将全身压在[npc2.namePos]身上，发出一声[npc.a_moan+]，准备好迎接高潮。"
						+ "[npc.she]迸发出野性的本能，一口咬在了[npc2.namePos]的脖子上，避免[npc2.herHim]在高潮时逃脱。";
			}
			return super.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return partner!=null && (partner.isSizeDifferenceTallerThan(target) || partner.isTaur());
		}
	};
	public static final SexSlot BEHIND_TWO = new SexSlot(
			"骑乘交配/插入",
			"身后(二)",
			"[npc.Name]将全身压在[npc2.namePos]身上，发出一声[npc.a_moan+]，准备好迎接高潮。",
			true,
			SexSlotTag.BEHIND_ALL_FOURS) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (target.getLegConfiguration()==LegConfiguration.QUADRUPEDAL
						?"骑在"
						:standing?"站在":"跪在")
					+UtilText.parse(partner, "[npc.name]身后");
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			if(orgasmingCharacter.equals(targetedCharacter)) {
				return "[npc.Name]发出了一声[npc.a_moan+]，即将迎来高潮。";
			}
			if(orgasmingCharacter.isFeral()) {
				return "[npc.Name]将全身压在[npc2.namePos]身上，发出一声[npc.a_moan+]，准备好迎接高潮。"
						+ "[npc.she]迸发出野性的本能，一口咬在了[npc2.namePos]的脖子上，避免[npc2.herHim]在高潮时逃脱。";
			}
			return super.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			return partner!=null && (partner.isSizeDifferenceTallerThan(target) || partner.isTaur());
		}
	};
	public static final SexSlot BEHIND_THREE = new SexSlot(
			"骑乘交配/插入",
			"身后(三)",
			"[npc.Name]将全身压在[npc2.namePos]身上，发出[npc.a_moan+]，准备好迎接高潮。",
			true,
			SexSlotTag.BEHIND_ALL_FOURS) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (target.getLegConfiguration()==LegConfiguration.QUADRUPEDAL
						?"骑在"
						:standing?"站在":"跪在")
					+UtilText.parse(partner, "[npc.name]身后");
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			if(orgasmingCharacter.equals(targetedCharacter)) {
				return "[npc.Name]发出了一声[npc.a_moan+]，即将迎来高潮。";
			}
			if(orgasmingCharacter.isFeral()) {
				return "[npc.Name]将全身压在[npc2.namePos]身上，发出一声[npc.a_moan+]，准备好迎接高潮。"
						+ "[npc.she]迸发出野性的本能，一口咬在了[npc2.namePos]的脖子上，避免[npc2.herHim]在高潮时逃脱。";
			}
			return super.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			return partner!=null && (partner.isSizeDifferenceTallerThan(target) || partner.isTaur());
		}
	};
	public static final SexSlot BEHIND_FOUR = new SexSlot(
			"骑乘交配/插入",
			"身后(四)",
			"[npc.Name]将全身压在[npc2.namePos]身上，发出一声[npc.a_moan+]，准备好迎接高潮。",
			true,
			SexSlotTag.BEHIND_ALL_FOURS) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (target.getLegConfiguration()==LegConfiguration.QUADRUPEDAL
						?"骑在"
						:standing?"站在":"跪在")
					+UtilText.parse(partner, "[npc.name]身后");
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			if(orgasmingCharacter.equals(targetedCharacter)) {
				return "[npc.Name]发出了一声[npc.a_moan+]，即将迎来高潮。";
			}
			if(orgasmingCharacter.isFeral()) {
				return "[npc.Name]将全身压在[npc2.namePos]身上，发出一声[npc.a_moan+]，准备好迎接高潮。"
						+ "[npc.she]迸发出野性的本能，一口咬在了[npc2.namePos]的脖子上，避免[npc2.herHim]在高潮时逃脱。";
			}
			return super.getOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			return partner!=null && (partner.isSizeDifferenceTallerThan(target) || partner.isTaur());
		}
	};
	
	public static final SexSlot HUMPING = new SexSlot(
			"趴背",
			"趴背",
			"[npc.Name]粗暴地顶向[npc2.name]，接着冒出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return "趴在"+UtilText.parse(partner, "[npc.name]")+"的背上";
		}
	};
	public static final SexSlot HUMPING_TWO = new SexSlot(
			"趴背",
			"趴背(二)",
			"[npc.Name]粗暴地顶向[npc2.name]，接着冒出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "趴在"+UtilText.parse(partner, "[npc.name]")+"的背上";
		}
	};
	public static final SexSlot HUMPING_THREE = new SexSlot(
			"趴背",
			"趴背(三)",
			"[npc.Name]粗暴地顶向[npc2.name]，接着冒出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "趴在"+UtilText.parse(partner, "[npc.name]")+"的背上";
		}
	};
	public static final SexSlot HUMPING_FOUR = new SexSlot(
			"趴背",
			"趴背(四)",
			"[npc.Name]粗暴地顶向[npc2.name]，接着冒出一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "趴在"+UtilText.parse(partner, "[npc.name]")+"的背上";
		}
	};
	
	
	public static final SexSlot BEHIND_ORAL = new SexSlot(
			"提供口交",
			"提供口交",
			"[npc.Name]伸出[npc.hand]放在[npc2.namePos]的[npc2.legs]上，接着就发出一声[npc.a_moan+]，[npc.she]已经准备要迎来高潮了。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return "为"+UtilText.parse(partner, "[npc.name]")+"提供口交";
		}
	};
	public static final SexSlot BEHIND_ORAL_TWO = new SexSlot(
			"提供口交",
			"提供口交(二)",
			"[npc.Name]伸出[npc.hand]放在[npc2.namePos]的[npc2.legs]上，接着就发出一声[npc.a_moan+]，[npc.she]已经准备要迎来高潮了。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "为"+UtilText.parse(partner, "[npc.name]")+"提供口交";
		}
	};
	public static final SexSlot BEHIND_ORAL_THREE = new SexSlot(
			"提供口交",
			"提供口交(三)",
			"[npc.Name]伸出[npc.hand]放在[npc2.namePos]的[npc2.legs]上，接着就发出一声[npc.a_moan+]，[npc.she]已经准备要迎来高潮了。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "为"+UtilText.parse(partner, "[npc.name]")+"提供口交";
		}
	};
	public static final SexSlot BEHIND_ORAL_FOUR = new SexSlot(
			"提供口交",
			"提供口交(四)",
			"[npc.Name]伸出[npc.hand]放在[npc2.namePos]的[npc2.legs]上，接着就发出一声[npc.a_moan+]，[npc.she]已经准备要迎来高潮了。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "为"+UtilText.parse(partner, "[npc.name]")+"提供口交";
		}
	};

	public static final SexSlot USING_FEET = new SexSlot(
			"使用足部",
			"使用足部",
			"[npc.Name]向着[npc2.name]靠近挪动了一下，便发出一声[npc.a_moan+]，即将迎接高潮的到来。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return "使用"+UtilText.parse(partner, "[npc.namePos]的[npc.feet]");
		}
	};
	public static final SexSlot USING_FEET_TWO = new SexSlot(
			"使用足部",
			"使用足部(二)",
			"[npc.Name]向着[npc2.name]靠近挪动了一下，便发出一声[npc.a_moan+]，即将迎接高潮的到来。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "使用"+UtilText.parse(partner, "[npc.namePos]的[npc.feet]");
		}
	};
	public static final SexSlot USING_FEET_THREE = new SexSlot(
			"使用足部",
			"使用足部(三)",
			"[npc.Name]向着[npc2.name]靠近挪动了一下，便发出一声[npc.a_moan+]，即将迎接高潮的到来。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "使用"+UtilText.parse(partner, "[npc.namePos]的[npc.feet]");
		}
	};
	public static final SexSlot USING_FEET_FOUR = new SexSlot(
			"使用足部",
			"使用足部(四)",
			"[npc.Name]向着[npc2.name]靠近挪动了一下，便发出一声[npc.a_moan+]，即将迎接高潮的到来。",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "使用"+UtilText.parse(partner, "[npc.namePos]的[npc.feet]");
		}
	};

	
	public static final SexSlot IN_FRONT = new SexSlot(
			"站立/跪下",
			"身前",
			"[npc.Name]向着[npc2.name]靠近挪动了一下，便发出一声[npc.a_moan+]，即将迎接高潮的到来。",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return (this.isStanding(target)?"站在":"跪在")+UtilText.parse(partner, "[npc.name]")+(target.isTaur()?"上面":"前面");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return partner.isSizeDifferenceTallerThan(target) || target.isTaur();
		}
	};
	public static final SexSlot IN_FRONT_TWO = new SexSlot(
			"站立/跪下",
			"身前(二)",
			"[npc.Name]向着[npc2.name]靠近挪动了一下，便发出一声[npc.a_moan+]，即将迎接高潮的到来。",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (this.isStanding(target)?"站在":"跪在")+UtilText.parse(partner, "[npc.name]")+(target.isTaur()?"上面":"前面");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return partner.isSizeDifferenceTallerThan(target) || target.isTaur();
		}
	};
	public static final SexSlot IN_FRONT_THREE = new SexSlot(
			"站立/跪下",
			"身前(三)",
			"[npc.Name]向着[npc2.name]靠近挪动了一下，便发出一声[npc.a_moan+]，即将迎接高潮的到来。",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (this.isStanding(target)?"站在":"跪在")+UtilText.parse(partner, "[npc.name]")+(target.isTaur()?"上面":"前面");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return partner.isSizeDifferenceTallerThan(target) || target.isTaur();
		}
	};
	public static final SexSlot IN_FRONT_FOUR = new SexSlot(
			"站立/跪下",
			"身前(四)",
			"[npc.Name]向着[npc2.name]靠近挪动了一下，便发出一声[npc.a_moan+]，即将迎接高潮的到来。",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (this.isStanding(target)?"站在":"跪在")+UtilText.parse(partner, "[npc.name]")+(target.isTaur()?"上面":"前面");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return partner.isSizeDifferenceTallerThan(target) || target.isTaur();
		}
	};


	public static final SexSlot IN_FRONT_ANAL = new SexSlot(
			"站立/跪下",
			"身前(背对)",
			"[npc.Name]将自己[npc.hips+]用力顶在[npc2.namePos]的[npc2.face]上，随着一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return "用屁股对着"+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot IN_FRONT_ANAL_TWO = new SexSlot(
			"站立/跪下",
			"身前(背对二)",
			"[npc.Name]将自己[npc.hips+]用力顶在[npc2.namePos]的[npc2.face]上，随着一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {
				partner = getCharacterInSlot(ALL_FOURS);
			}
			return "用屁股对着"+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			return partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot IN_FRONT_ANAL_THREE = new SexSlot(
			"站立/跪下",
			"身前(背对三)",
			"[npc.Name]将自己[npc.hips+]用力顶在[npc2.namePos]的[npc2.face]上，随着一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {
				partner = getCharacterInSlot(ALL_FOURS);
			}
			return "用屁股对着"+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			return partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot IN_FRONT_ANAL_FOUR = new SexSlot(
			"站立/跪下",
			"身前(背对四)",
			"[npc.Name]将自己[npc.hips+]用力顶在[npc2.namePos]的[npc2.face]上，随着一声[npc.a_moan+]，[npc.she]即将迎来高潮。",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {
				partner = getCharacterInSlot(ALL_FOURS);
			}
			return "用屁股对着"+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			return partner.isSizeDifferenceTallerThan(target);
		}
	};
}
