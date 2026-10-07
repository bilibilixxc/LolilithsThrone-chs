package com.lilithsthrone.game.dialogue.utils;

import java.util.List;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;

/**
 * @since 0.3.2
 * @version 0.4.4
 * @author Innoxia
 */
public class OffspringMapDialogue {
	
	private static List<OffspringSeed> getOffspringList() {
		AbstractWorldType worldType = Main.game.getPlayer().getWorldLocation();
		AbstractPlaceType placeType = Main.game.getPlayer().getLocationPlace().getPlaceType();
		
		AbstractEncounter enc = Main.game.getPlayer().getLocationPlace().getPlaceType().getEncounterType();
		return Main.game.getOffspringNotSpawned(os->
			os.getSubspecies().isAbleToNaturallySpawnInLocation(worldType, placeType)
				&& (worldType==WorldType.HARPY_NEST
						?(os.getHalfDemonSubspecies()==null || os.getHalfDemonSubspecies().getRace()==Race.HARPY)
						:(os.getHalfDemonSubspecies()==null || os.getHalfDemonSubspecies().getRace()!=Race.HARPY))
				// Allow youko in Elis alleyways:
				|| (enc==Encounter.getEncounterFromId("innoxia_elis_alleyway")
						&& (os.getSubspecies()==Subspecies.FOX_ASCENDANT
							|| os.getSubspecies()==Subspecies.FOX_ASCENDANT_ARCTIC
							|| os.getSubspecies()==Subspecies.FOX_ASCENDANT_FENNEC))
				// Allow Angels in Dominion:
				|| (enc==Encounter.DOMINION_ALLEY
						&& (os.getSubspecies()==Subspecies.ANGEL))
				// Allow alligators, slimes, and rats in Dominion canals:
				|| (enc==Encounter.DOMINION_CANAL
						&& (os.getSubspecies()==Subspecies.ALLIGATOR_MORPH
							|| os.getSubspecies()==Subspecies.SLIME
							|| os.getSubspecies()==Subspecies.RAT_MORPH))
				);
	}
	
	
	public static final DialogueNode OFFSPRING_CHOICE = new DialogueNode("后代列表", "-", true) {
		List<OffspringSeed> offspringList;
		@Override
		public void applyPreParsingEffects() {
			offspringList = getOffspringList();
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			boolean noOffspring = offspringList.isEmpty();
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "地图的奥术附魔在你面前投影出一份你未曾谋面的后代名单，并在地图上展示出对应的准确地点。"
						+ (noOffspring
								? "看来现在你并没有需要寻找的没见过面的后代……"
								: " 幸运的是，看起来你的一个后代就在这个区域。"
									+ "你看了看关键信息，得知对方是……")
					+ "</p>"
					+ "<p style='text-align:center;'>");
			
			if(Main.game.getOffspringNotSpawned(os->true).isEmpty()) {
				UtilText.nodeContentSB.append("[style.colourDisabled(后代不可用)]");
				
			} else {
				boolean foundAnyInArea = false;
				List<OffspringSeed> offspringToShow = Main.game.getOffspringNotSpawned(npc->offspringList.contains(npc));
				if(!offspringToShow.isEmpty()) {
					foundAnyInArea = true;
					UtilText.nodeContentSB.append("后代[style.colourMinorGood(可能在该区域)]:<br/>");
				}
				for(OffspringSeed os : offspringToShow) {
					UtilText.nodeContentSB.append("<span style='color:"+os.getFemininity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(os.getName())+"</span>");
					
					String unknownMotherName = "未知";
					if(os.getMother()==null && !os.getMotherName().equals("???")) {
						unknownMotherName = os.getMotherName();
					}
					String unknownFatherName = "未知";
					if(os.getFather()==null && !os.getFatherName().equals("???")) {
						unknownFatherName = os.getFatherName();
					}

					UtilText.nodeContentSB.append(" ("+(os.isFeral()?"<span style='color:"+RaceStage.FERAL.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(RaceStage.FERAL.getName())+"</span> ":"")+"<span style='color:"+os.getSubspecies().getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(os.getSubspecies().getName(os.getBody()))+"</span>)"
							+ " 母亲："+(os.getMother()==null?unknownMotherName:(os.getMother().isPlayer()?"[style.colourExcellent(你)]":os.getMother().getName(true)))
							+ " 父亲："+(os.getFather()==null?unknownFatherName:(os.getFather().isPlayer()?"[style.colourExcellent(你)]":os.getFather().getName(true)))
							+ "<br/>");
				}
				
				offspringToShow = Main.game.getOffspringNotSpawned(npc->!offspringList.contains(npc));
				if(!offspringToShow.isEmpty()) {
					if(foundAnyInArea) {
						UtilText.nodeContentSB.append("<br/>");
					}
					UtilText.nodeContentSB.append("后代[style.colourMinorBad(不在该区域)]:<br/>");
				}
				for(OffspringSeed os : offspringToShow) {
					UtilText.nodeContentSB.append("[style.colourDisabled("+Util.capitaliseSentence(os.getName())+")]");
					
					String unknownMotherName = "未知";
					if(os.getMother()==null && !os.getMotherName().equals("???")) {
						unknownMotherName = os.getMotherName();
					}
					String unknownFatherName = "未知";
					if(os.getFather()==null && !os.getFatherName().equals("???")) {
						unknownFatherName = os.getFatherName();
					}
					
					UtilText.nodeContentSB.append(" (<span style='color:"+os.getSubspecies().getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(os.getSubspecies().getName(os.getBody()))+"</span>)"
							+ " 母亲："+(os.getMother()==null?unknownMotherName:(os.getMother().isPlayer()?"[style.colourExcellent(你)]":os.getMother().getName(true)))
							+ " 父亲："+(os.getFather()==null?unknownFatherName:(os.getFather().isPlayer()?"[style.colourExcellent(你)]":os.getFather().getName(true)))
							+ "<br/>");
				}
			}
				
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<OffspringSeed> offspringToShow = Main.game.getOffspringNotSpawned(npc->offspringList.contains(npc));
			offspringToShow.addAll(Main.game.getOffspringNotSpawned(npc->!offspringList.contains(npc)));
			
			if (index == 0) {
				return new Response("返回", "还是决定不去寻找该地区的后代。", Main.game.getDefaultDialogue(false));
				
			} else if(index-1 < offspringToShow.size()) {
				OffspringSeed offspring = offspringToShow.get(index-1);
				
				if(!offspringList.contains(offspring)) {
					return new Response(offspring.getName(),
							"因为" + offspring.getName()+"的亚种，"+offspring.hisHer()+"在该区域无法找到……",
							null);
				}

				return new Response(offspring.getName(),
						"在附魔地图的帮助下，你很快就会在该区域找到"+offspring.getName()+"……",
						offspring.getEncounterDialogue()) {
					@Override
					public Colour getHighlightColour() {
						return offspring.getFemininity().getColour();
					}
					@Override
					public void effects() {
						NPC npc = new NPCOffspring(offspring);

						npc.setLocation(Main.game.getPlayer(), true);

						npc.equipClothing(EquipClothingSetting.getAllClothingSettings());

						Main.game.setActiveNPC(npc);

						Main.game.getTextStartStringBuilder().append(
								"<p><i>"
									+ UtilText.parse(npc,
										"通过查询地图，你很快找到了你的[npc.daughter][npc.name]，正住在这条小巷确切某处。"
										+ "你收好地图，开始寻找[npc.herHim]……")
								+ "</i></p>");
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
