package com.lilithsthrone.game.dialogue.npcDialogue;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class QuickTransformations {
	
	private static String dialoguePath;
	private static GameCharacter targetedNpc;
	private static DialogueNode endingNode;
	
	public static DialogueNode initQuickTransformations(String dialoguePath, GameCharacter targetedNpc, DialogueNode endingNode) {
		QuickTransformations.dialoguePath = dialoguePath;
		QuickTransformations.targetedNpc = targetedNpc;
		QuickTransformations.endingNode = endingNode;
		
		return QUICK_TRANSFORMATIONS_FEMININITY;
	}
	
	// Quick transformations:
	
	public static final DialogueNode QUICK_TRANSFORMATIONS_FEMININITY = new DialogueNode("转化", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_FEMININITY", targetedNpc);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			String femaleName = targetedNpc.getSubspecies().getSingularFemaleName(targetedNpc.getBody());
			String maleName = targetedNpc.getSubspecies().getSingularMaleName(targetedNpc.getBody());
			
			if (index == 1) {
				return new Response("[style.colourMasculineStrong(极端男性化)]",
						"告诉[npc.name]你想改变[npc.her]的女性化程度，让[npc.sheIs]成为一个"+maleName+"。",
						QUICK_TRANSFORMATIONS_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_FEMININITY_VERY_MASCULINE", targetedNpc));
						targetedNpc.setFemininity(10);
					}
				};
				
			} else if (index == 2) {
				return new Response("[style.colourMasculine(男性化)]",
						"告诉[npc.name]你想改变[npc.her]的女性化程度，让[npc.sheIs]成为一个"+maleName+"。",
						QUICK_TRANSFORMATIONS_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_FEMININITY_MASCULINE", targetedNpc));
						targetedNpc.setFemininity(30);
					}
				};
				
			} else if (index == 3) {
				return new Response("[style.colourAndrogynous(中性的)]",
						"告诉[npc.name]你想改变[npc.her]的女性化程度，让[npc.sheIs]成为一个"+femaleName+"。",
						QUICK_TRANSFORMATIONS_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_FEMININITY_ANDROGYNOUS", targetedNpc));
						targetedNpc.setFemininity(50);
					}
				};
				
			} else if (index == 4) {
				return new Response("[style.colourFeminine(女性)]",
						"告诉[npc.name]你想改变[npc.her]的女性化程度，让[npc.sheIs]成为一个"+femaleName+"。",
						QUICK_TRANSFORMATIONS_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_FEMININITY_FEMININE", targetedNpc));
						targetedNpc.setFemininity(70);
					}
				};
				
			} else if (index == 5) {
				return new Response("[style.colourFeminineStrong(极端女性化)]",
						"告诉[npc.name]你想改变[npc.her]的女性化程度，让[npc.sheIs]成为一个"+femaleName+"。",
						QUICK_TRANSFORMATIONS_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_FEMININITY_VERY_FEMININE", targetedNpc));
						targetedNpc.setFemininity(90);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	private static final DialogueNode QUICK_TRANSFORMATIONS_PENIS = new DialogueNode("转化", "", true, true) {
		

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_PENIS", targetedNpc);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("无阴茎",
						"告诉[npc.race]你不想让[npc.herHim]有一根鸡巴。",
						QUICK_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						targetedNpc.setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						targetedNpc.setPenisType(PenisType.NONE);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_PENIS_NONE", targetedNpc));
					}
				};
				
			} else if (index == 2) {
				return new Response("极小的阴茎",
						"告诉[npc.race]你想让[npc.herHim]长出一根[unit.size(3)]的小小雀雀。",
						QUICK_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						targetedNpc.setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						targetedNpc.setPenisType(RacialBody.valueOfRace(targetedNpc.getBody().getRaceFromPartWeighting()).getPenisType());
						targetedNpc.setPenisGirth(PenetrationGirth.TWO_NARROW);
						targetedNpc.setPenisSize(3);
						targetedNpc.setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						targetedNpc.setPenisCumStorage(CumProduction.ONE_TRICKLE.getMaximumValue());
						targetedNpc.setPenisStoredCum(CumProduction.ONE_TRICKLE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_PENIS_TINY", targetedNpc));
					}
				};
				
			} else if (index == 3) {
				return new Response("平均尺寸的阴茎",
						"告诉[npc.race]你想让[npc.herHim]长出一根[unit.size(15)]的普通鸡巴。",
						QUICK_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						targetedNpc.setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						targetedNpc.setPenisType(RacialBody.valueOfRace(targetedNpc.getBody().getRaceFromPartWeighting()).getPenisType());
						targetedNpc.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
						targetedNpc.setPenisSize(15);
						targetedNpc.setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						targetedNpc.setPenisCumStorage(CumProduction.THREE_AVERAGE.getMaximumValue());
						targetedNpc.setPenisStoredCum(CumProduction.THREE_AVERAGE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_PENIS_AVERAGE", targetedNpc));
					}
				};
				
			} if (index == 4) {
				return new Response("较大的阴茎",
						"告诉[npc.race]你想让[npc.herHim]长出一根[unit.size(30)]的较大鸡巴。",
						QUICK_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						targetedNpc.setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						targetedNpc.setPenisType(RacialBody.valueOfRace(targetedNpc.getBody().getRaceFromPartWeighting()).getPenisType());
						targetedNpc.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
						targetedNpc.setPenisSize(30);
						targetedNpc.setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						targetedNpc.setPenisCumStorage(CumProduction.FIVE_HUGE.getMedianValue());
						targetedNpc.setPenisStoredCum(CumProduction.FIVE_HUGE.getMedianValue());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_PENIS_LARGE", targetedNpc));
					}
				};
				
			} if (index == 5) {
				return new Response("巨大的阴茎",
						"告诉[npc.race]你想让[npc.herHim]长出一根[unit.size(50)]的巨大鸡巴。",
						QUICK_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						targetedNpc.setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						targetedNpc.setPenisType(RacialBody.valueOfRace(targetedNpc.getBody().getRaceFromPartWeighting()).getPenisType());
						targetedNpc.setPenisGirth(PenetrationGirth.FIVE_THICK);
						targetedNpc.setPenisSize(50);
						targetedNpc.setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						targetedNpc.setPenisCumStorage(CumProduction.FIVE_HUGE.getMaximumValue());
						targetedNpc.setPenisStoredCum(CumProduction.FIVE_HUGE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_PENIS_HUGE", targetedNpc));
					}
				};
				
			}else {
				return null;
			}
		}
	};
	
	private static final DialogueNode QUICK_TRANSFORMATIONS_VAGINA = new DialogueNode("转化", "", true, true) {


		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_VAGINA", targetedNpc);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("阴道",
						"告诉[npc.race]你想让[npc.herHim]长出小穴。",
						QUICK_TRANSFORMATIONS_BREASTS){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						targetedNpc.setAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer(), true);
						targetedNpc.setVaginaType(RacialBody.valueOfRace(targetedNpc.getBody().getRaceFromPartWeighting()).getVaginaType());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_VAGINA_ENABLED", targetedNpc));
					}
				};
				
			} else if (index == 2) {
				return new Response("无阴道",
						"告诉[npc.race][npc.she]不需要一个小穴。",
						QUICK_TRANSFORMATIONS_BREASTS){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						targetedNpc.setAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer(), true);
						targetedNpc.setVaginaType(VaginaType.NONE);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_VAGINA_DISABLED", targetedNpc));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	private static final DialogueNode QUICK_TRANSFORMATIONS_BREASTS = new DialogueNode("转化", "", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_BREASTS", targetedNpc);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("平坦的胸部",
						"告诉[npc.name][npc.she]不该有乳房。",
						QUICK_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_BREASTS_FLAT", targetedNpc));
						targetedNpc.setBreastSize(CupSize.FLAT.getMeasurement());
					}
				};
				
			} else if (index == 2) {
				return new Response("AA罩杯",
						"告诉[npc.name]让[npc.her]的胸变成AA罩杯。",
						QUICK_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_BREASTS_AA", targetedNpc));
						targetedNpc.setBreastSize(CupSize.AA.getMeasurement());
					}
				};
				
			} else if (index == 3) {
				return new Response("C罩杯",
						"告诉[npc.name]让[npc.her]的胸变成C罩杯。",
						QUICK_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_BREASTS_C", targetedNpc));
						targetedNpc.setBreastSize(CupSize.C.getMeasurement());
					}
				};
				
			} else if (index == 4) {
				return new Response("E罩杯",
						"告诉[npc.name]让[npc.her]的胸变成E罩杯。",
						QUICK_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_BREASTS_E", targetedNpc));
						targetedNpc.setBreastSize(CupSize.E.getMeasurement());
					}
				};
				
			} else if (index == 5) {
				return new Response("H罩杯",
						"告诉[npc.name]让[npc.her]的胸变成H罩杯。",
						QUICK_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_BREASTS_H", targetedNpc));
						targetedNpc.setBreastSize(CupSize.H.getMeasurement());
					}
				};
				
			} else if (index == 6) {
				return new Response("N罩杯",
						"告诉[npc.name]让[npc.her]的胸变成N罩杯。",
						QUICK_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "QUICK_TRANSFORMATIONS_BREASTS_N", targetedNpc));
						targetedNpc.setBreastSize(CupSize.N.getMeasurement());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	private static final DialogueNode QUICK_TRANSFORMATIONS_FINISHED = new DialogueNode("转化", "", true, true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return endingNode.getResponse(responseTab, index);
		}
	};
}
