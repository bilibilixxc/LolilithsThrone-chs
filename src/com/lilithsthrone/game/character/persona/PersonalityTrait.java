package com.lilithsthrone.game.character.persona;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/***
 * @since 0.2.4
 * @version 0.4.9
 * @author Innoxia
 */
public enum PersonalityTrait {
	
	// Core traits:
	
	CONFIDENT(false, PersonalityCategory.CORE, "自信", "[npc.NameIsFull]非常有主见，对[npc.herself]相当自信。", "", PresetColour.BASE_GREEN_LIME) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(SHY);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(CONFIDENT)
								?"[style.colourDisabled([npc.Name]已经很自信了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorGood(更自信了)]！")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(CONFIDENT)
								?"[style.colourDisabled([npc.Name]已经缺乏自信了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorBad(不再自信了)]！")
						+ "</p>");
		}
	},
	
	SHY(false, PersonalityCategory.CORE, "害羞", "[npc.NameIsFull]在他人面前非常害羞，且倾向尽可能避开谈话。", "", PresetColour.BASE_YELLOW_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(CONFIDENT);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(SHY)
								?"[style.colourDisabled([npc.Name]已经很害羞了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorGood(更害羞了)]！")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(SHY)
								?"[style.colourDisabled([npc.Name]已经不再害羞了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorBad(不再害羞了)]！")
						+ "</p>");
		}
	},

	KIND(false, PersonalityCategory.CORE, "善良", "[npc.Name]总试着表现友善，体贴他人，有时甚至会不惜牺牲[npc.her]自身的快乐。", "", PresetColour.BASE_GREEN) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(SELFISH);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(KIND)
								?"[style.colourDisabled([npc.Name]已经很善良了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorGood(更善良了)]！")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(KIND)
								?"[style.colourDisabled([npc.Name]已经不怎么善良了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorBad(不再善良了)]！")
						+ "</p>");
		}
	},
	
	SELFISH(false, PersonalityCategory.CORE, "自私", "[npc.Name]总是把[npc.herself]放在首位，若[npc.herHim]不能直接受益的事情，大概率会对此无动于衷。", "", PresetColour.BASE_RED) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(KIND);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(SELFISH)
								?"[style.colourDisabled([npc.Name]已经很自私了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorGood(更自私了)]！")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(SELFISH)
								?"[style.colourDisabled([npc.Name]已经不再自私了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorBad(不再自私了)]！")
						+ "</p>");
		}
	},

	NAIVE(false, PersonalityCategory.CORE, "天真", "缺乏人生经验与智慧，[npc.name]完全无法理解现实的残酷。", "", PresetColour.BASE_PINK_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(CYNICAL);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(NAIVE)
								?"[style.colourDisabled([npc.Name]已经很天真了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorGood(更天真了)]！")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(NAIVE)
								?"[style.colourDisabled([npc.Name]已经不再天真了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorBad(不再天真了)]！")
						+ "</p>");
		}
	},
	
	CYNICAL(false, PersonalityCategory.CORE, "愤世嫉俗", "[npc.NameIsFull]对于他人的意图与动机抱有相当的疑心。", "", PresetColour.BASE_RED_DARK) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(NAIVE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(CYNICAL)
								?"[style.colourDisabled([npc.Name]已经愤世嫉俗了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorGood(更愤世嫉俗了)]！")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(CYNICAL)
								?"[style.colourDisabled([npc.Name]已经不再愤世嫉俗了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorBad(不再愤世嫉俗了)]！")
						+ "</p>");
		}
	},
	
	// Combat traits:
	
	BRAVE(false, PersonalityCategory.COMBAT, "勇敢", "[npc.Name]总是勇于行动，且不畏斗争。", "", PresetColour.BASE_ORANGE) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(COWARDLY);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(BRAVE)
								?"[style.colourDisabled([npc.Name]已经很勇敢了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorGood(更勇敢了)]！")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(BRAVE)
								?"[style.colourDisabled([npc.Name]已经不再勇敢了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorBad(不再勇敢了)]！")
						+ "</p>");
		}
	},
	
	COWARDLY(false, PersonalityCategory.COMBAT, "懦弱", "[npc.Name]很容易担惊受怕，比起直接解决争端，宁可直接逃开。", "", PresetColour.BASE_RED_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(BRAVE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(COWARDLY)
								?"[style.colourDisabled([npc.Name]已经很懦弱了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorGood(更懦弱了)]！")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(COWARDLY)
								?"[style.colourDisabled([npc.Name]已经不再懦弱了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorBad(不再懦弱了)]！")
						+ "</p>");
		}
	},

	// Sex traits:
	
	LEWD(false,
			PersonalityCategory.SEX,
			"好色",
			"[npc.NameHasFull]拥有大量各种与性有关的知识，并且乐于讨论这些下流的事情。",
			"", PresetColour.BASE_PINK) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(PRUDE, INNOCENT);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(LEWD)
								?"[style.colourDisabled([npc.Name]已经很好色了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorGood(更好色了)]！")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(LEWD)
								?"[style.colourDisabled([npc.Name]已经不再好色了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorBad(不再好色了)]！")
						+ "</p>");
		}
	},
	
	INNOCENT(false,
			PersonalityCategory.SEX,
			"纯洁",
			"讨论和进行性行为时，[npc.Name]总是表现得尴尬又无知。",
			"", PresetColour.BASE_BLUE_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LEWD, PRUDE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name]已经很纯洁了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorGood(更纯洁了)]！")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name]已经不再纯洁了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorBad(不再纯洁了)]！")
						+ "</p>");
		}
	},
	
	PRUDE(false,
			PersonalityCategory.SEX,
			"拘谨",
			"[npc.Name]不愿谈及性事，甚至拒绝承认自己知道任何与性相关的事情。",
			"", PresetColour.BASE_BLUE_STEEL) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LEWD, INNOCENT);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name]已经很拘谨了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorGood(更拘谨了)]！")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name]已经不再拘谨了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorBad(不再拘谨了)]！")
						+ "</p>");
		}
	},

	// Speech traits:
	
	LISP(false,
			PersonalityCategory.SPEECH,
			"口齿不清",
			"[npc.Name]说话口齿不清，会把“z”、“c”、“s”说成“zh”、“ch”、“sh”。",
			"[style.italicsBad([npc.namePos]游戏中的所有发言都会被这个个性影响！)]", PresetColour.BASE_PURPLE_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(LISP)
								?"[style.colourDisabled([npc.Name]说话已经口齿不清了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorBad(有些口齿不清了)]！")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(LISP)
								?"[style.colourDisabled([npc.Name]已经不再口齿不清了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorGood(说话更清楚了)]！")
						+ "</p>");
		}
	},

	STUTTER(false,
			PersonalityCategory.SPEECH,
			"口吃",
			"[npc.NameHasFull]习惯在说话时磕磕绊绊。",
			"[style.italicsBad([npc.namePos]游戏中的所有发言都会被这个个性影响！)]", PresetColour.BASE_PINK_SALMON) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.Name]说话已经结巴了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorBad(有些口吃了)]！")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.Name]已经不再口吃了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorGood(不再口吃了)]！")
						+ "</p>");
		}
	},

	MUTE(true,
			PersonalityCategory.SPEECH,
			"哑巴",
			"[npc.NameIsFull]是个哑巴，尽管还是能在兴奋时发出一些淫叫，但完全无法对话。",
			"[style.italicsBad([npc.namePos]游戏中的所有发言都会被移除！)]", PresetColour.BASE_CRIMSON) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LISP, STUTTER, SLOVENLY);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.NameIsFull]已经是哑巴了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorBad(不能说话了)]！")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.NameIsFull]已经能说话了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorGood(能说话了)]！")
						+ "</p>");
		}
	},

	SLOVENLY(false,
			PersonalityCategory.SPEECH,
			"发音模糊",
			"[npc.Name]发音很模糊；经常丢失元音或发错音，[npc.her]说的话通常都很难听懂。",
			"[style.italicsBad([npc.namePos]游戏中的所有发言都会被这个个性影响！)]", PresetColour.BASE_BROWN) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(SLOVENLY)
								?"[style.colourDisabled([npc.Name]说话已经发音模糊了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorBad(发音变模糊了)]！")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(SLOVENLY)
								?"[style.colourDisabled([npc.Name]已经不再发音模糊了，所以无事发生……)]"
								:"[npc.Name]意识到自己[style.colourMinorGood(发音不再模糊了)]！")
						+ "</p>");
		}
	},;
	
	private boolean specialRequirements;
	private PersonalityCategory personalityCategory;
	private String name;
	private String description;
	private String gameplayInformation;
	private Colour colour;
	
	private PersonalityTrait(boolean specialRequirements, PersonalityCategory personalityCategory, String name, String description, String gameplayInformation, Colour colour) {
		this.specialRequirements = specialRequirements;
		this.personalityCategory = personalityCategory;
		this.name = name;
		this.description = description;
		this.gameplayInformation = gameplayInformation;
		this.colour = colour;
	}
	
	public PersonalityCategory getPersonalityCategory() {
		return personalityCategory;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription(GameCharacter character, boolean withGameplayInformation, boolean withMutuallyExclusiveInformation) {
		StringBuilder sb = new StringBuilder();

		sb.append(UtilText.parse(character, description));
		
		if(withGameplayInformation) {
			if(gameplayInformation!=null && !gameplayInformation.isEmpty()) {
				sb.append("<br/>"+UtilText.parse(character, gameplayInformation));
			} else {
				sb.append("<br/>[style.italicsDisabled(没有游戏内实际效果……)]");
			}
		}
		
		if(withMutuallyExclusiveInformation) {
			if(!this.getMutuallyExclusiveSettings().isEmpty()) {
				sb.append("<br/>[style.colourBad(相斥属性)]是");
				List<String> names = new ArrayList<>();
				for(PersonalityTrait trait : this.getMutuallyExclusiveSettings()) {
					names.add("<span style='color:"+trait.getColour().toWebHexString()+";'>"+trait.getName()+"</span>");
				}
				sb.append(Util.stringsToStringList(names, false)+"！");
			}
		}
		
		return sb.toString();
	}
	
	public Colour getColour() {
		return colour;
	}

	public abstract List<PersonalityTrait> getMutuallyExclusiveSettings();

	public abstract String getAdditionDescription(GameCharacter target);
	
	public abstract String getRemovalDescription(GameCharacter target);
	
	public boolean isSpecialRequirements() {
		return specialRequirements;
	}
}
