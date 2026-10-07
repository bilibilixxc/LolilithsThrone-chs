package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.3.9
 * @version 0.3.9
 * @author Innoxia
 */
public class LilayaMilkingRoomDialogue {
	
	private static GameCharacter getMilkingTarget() {
		return MilkingRoom.getTargetedCharacter();
	}
	
	public static final DialogueNode MILKING_ROOM = new DialogueNode("房间", "", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(Main.game.getPlayerCell().getType(), Main.game.getPlayerCell().getLocation());
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "<b style='color:"+PlaceUpgrade.LILAYA_MILKING_ROOM.getColour().toWebHexString()+";'>"+PlaceUpgrade.LILAYA_MILKING_ROOM.getName()+"</b><br/>"
						+ "这个房间已被改建成一处特殊产奶间，可以榨取八名奴隶的各种体液。"
						+ "左手边的墙上排列着四台机器，而另一边则是另外四台。"
					+ "</p>");
			
			sb.append(room.getRoomDescription());
			sb.append("<br/>");
			
			for(AbstractPlaceUpgrade up : Main.game.getPlayerCell().getPlace().getPlaceUpgrades()) {
				if(!up.isCoreRoomUpgrade()) {
					sb.append("<p>"
								+ "<b style='color:"+up.getColour().toWebHexString()+";'>"+up.getName()+"</b><br/>"
								+ up.getRoomDescription(Main.game.getPlayerCell())
							+ "</p>");
				}
			}
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			
			MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(Main.game.getPlayerCell().getType(), Main.game.getPlayerCell().getLocation());
			
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<NPC> slavesAssignedToRoom = new ArrayList<>();
			slavesAssignedToRoom.addAll(charactersPresent);
			
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("管理房间", "进入该房间的管理界面。", OccupantManagementDialogue.ROOM_UPGRADES) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = Main.game.getPlayerCell();
						}
					};
				} else {
					return new Response("管理房间", "在进入该界面前，你需要获得贩奴许可；莉莱雅要是允许你带人或玩偶住下的话那也行。",  null);
				}
				
			}  else if (index == 2) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("人员管理", "进入奴隶和友人住客的管理界面。", OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null)) {
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
						}
					};
				} else {
					return new Response("管理人员", "在进入该界面前，你需要获得贩奴许可；莉莱雅要是允许你带人或玩偶住下的话那也行。",  null);
				}
				
			} else if(index>=3 && index<6) {
				return null;
				
			} else if(index==6) {
				if(getMilkingTarget().getBreastRawStoredMilkValue()==0) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"挤奶",
							UtilText.parse(getMilkingTarget(), "[npc.namePos]的乳房里并没有储存的奶水，所以[npc.she]现在不能挤奶！"),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"挤奶",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull]无法使用[npc.her]的乳头，所以[npc.she]暂时无法挤奶！"),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"挤奶",
							UtilText.parse(getMilkingTarget(), "没有空闲的挤奶机供[npc.name]使用！"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"挤奶",
							UtilText.parse(getMilkingTarget(),
									"[npc.name]不是你的奴隶， [npc.sheIs]只有在"
											+ " <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span>你。"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& (getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative() || getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative())) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"挤奶",
							UtilText.parse(getMilkingTarget(),
									(getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative()
											?(getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative()
												?"[npc.name]不是你的奴隶，而且这两个性癖都不喜欢，所以不会让你这么做"
													+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+"和"+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+"性癖。"
												:"[npc.name]不是你的奴隶，而且不喜欢"+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+"这个性癖，所以不会让你这么做。")
											:"[npc.name]不是你的奴隶，而且不喜欢"+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+"这个性癖，所以不会让你这么做。")),
							null);
					
				} else {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"挤奶",
							"用房间的备用挤奶设备给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.name]"))+"挤奶。",
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualMilkPerHour(getMilkingTarget());
							if(milked < getMilkingTarget().getBreastRawStoredMilkValue() && milked < MilkingRoom.getMaximumMilkPerHour(getMilkingTarget())) {
								milked = (int) Math.min(getMilkingTarget().getBreastRawStoredMilkValue(), MilkingRoom.getMaximumMilkPerHour(getMilkingTarget()));
							}
							room.incrementFluidStored(new FluidStored(getMilkingTarget().getId(), getMilkingTarget().getMilk(), milked), milked);
							
							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_TARGET", getMilkingTarget()));
							}
							
							Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center; color:"+PresetColour.MILK.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), "将[npc.milk]放进储藏室中！")
								+ "</p>");
						}
						@Override
						public boolean postEndTurnEffects() {
							int milked = MilkingRoom.getActualMilkPerHour(getMilkingTarget());
							getMilkingTarget().incrementBreastStoredMilk(-milked);
							return true;
						}
					};
				}
				
			} else if(index==7) {
				if(!getMilkingTarget().hasPenisIgnoreDildo()) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨精",
							UtilText.parse(getMilkingTarget(), "[npc.Name]没有阴茎，所以[npc.she]不能产生精液……"),
							null);
					
				} else if(getMilkingTarget().getPenisRawStoredCumValue()==0) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨精",
							UtilText.parse(getMilkingTarget(), "[npc.namePos]的睾丸中没有储存精液，因此[npc.her]的鸡巴无法榨精……"),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨精",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull]无法使用[npc.her]的鸡巴，因此[npc.she]暂时无法榨精……"),
							null);
					
				} else if(!getMilkingTarget().isAbleToOrgasm()) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨精",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull]无法达到高潮，所以无法榨出[npc.her]的精液！"),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨精",
							UtilText.parse(getMilkingTarget(), "没有空闲的挤奶机供[npc.name]使用！"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion() && !getMilkingTarget().isSlave()
						&& getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨精",
							UtilText.parse(getMilkingTarget(),
									"[npc.name]不是你的奴隶， [npc.sheIs]只有在"
											+ " <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span>你。"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& (getMilkingTarget().getFetishDesire(Fetish.FETISH_CUM_STUD).isNegative() || getMilkingTarget().getFetishDesire(Fetish.FETISH_PENIS_GIVING).isNegative())) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨精",
							UtilText.parse(getMilkingTarget(),
									(getMilkingTarget().getFetishDesire(Fetish.FETISH_CUM_STUD).isNegative()
											?(getMilkingTarget().getFetishDesire(Fetish.FETISH_PENIS_GIVING).isNegative()
												?"[npc.name]不是你的奴隶，而且这两个性癖都不喜欢，所以不会让你这么做"
													+Fetish.FETISH_CUM_STUD.getName(getMilkingTarget())+"和"+Fetish.FETISH_PENIS_GIVING.getName(getMilkingTarget())+"性癖。"
												:"[npc.name]不是你的奴隶，而且不喜欢"+Fetish.FETISH_CUM_STUD.getName(getMilkingTarget())+"这个性癖，所以不会让你这么做。")
											:"[npc.name]不是你的奴隶，而且不喜欢"+Fetish.FETISH_PENIS_GIVING.getName(getMilkingTarget())+"这个性癖，所以不会让你这么做。")),
							null);
					
				} else {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨精",
							UtilText.parse(getMilkingTarget(), "使用该房间的备用挤奶设备给[npc.namePos]的鸡巴榨精。"),
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualCumPerHour(getMilkingTarget());
							room.incrementFluidStored(new FluidStored(getMilkingTarget(), getMilkingTarget().getCum(), milked), milked);
							
							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_COCK_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_COCK_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_COCK_TARGET", getMilkingTarget()));
							}

							Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center; color:"+PresetColour.CUM.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), "将[npc.cum]放入储藏室中！")
								+ "</p>");
						}
						@Override
						public boolean postEndTurnEffects() {
							int milked = MilkingRoom.getActualCumPerHour(getMilkingTarget());
							getMilkingTarget().incrementPenisStoredCum(-milked);
							getMilkingTarget().setLastTimeOrgasmedSeconds(Main.game.getSecondsPassed());
							return true;
						}
					};
				}
				
			} else if(index==8) {
				if(!getMilkingTarget().hasVagina()) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨妹汁",
							UtilText.parse(getMilkingTarget(), "[npc.Name]没有阴道，所以[npc.she]不能产生任何爱液……"),
							null);
					
				} else if(getMilkingTarget().getVaginaWetness().getValue()==0) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨妹汁",
							UtilText.parse(getMilkingTarget(), "[npc.NamePos]的小穴完全干涸了，连一滴爱液都分泌不出来……"),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨妹汁",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull]无法使用[npc.her]的阴部，因此目前无法榨出[npc.her]的爱液……"),
							null);
					
				} else if(!getMilkingTarget().isAbleToOrgasm()) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨妹汁",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull]无法达到高潮，所以无法榨出[npc.her]的爱液！"),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨妹汁",
							UtilText.parse(getMilkingTarget(), "没有空闲的挤奶机供[npc.name]使用！"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨妹汁",
							UtilText.parse(getMilkingTarget(),
									"[npc.name]不是你的奴隶， [npc.sheIs]只有在"
											+ " <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span>你。"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& getMilkingTarget().getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isNegative()) {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨妹汁",
							UtilText.parse(getMilkingTarget(), "[npc.name]不是你的奴隶，而且不喜欢"+Fetish.FETISH_VAGINAL_RECEIVING.getName(getMilkingTarget())+"这个性癖，所以不会让你这么做。"),
							null);
					
				}else {
					return new Response(
							"给"+(getMilkingTarget().isPlayer()?"自己":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+"榨妹汁",
							UtilText.parse(getMilkingTarget(), "使用该房间的备用挤奶设备给[npc.namePos]的小穴榨汁。"),
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualGirlcumPerHour(getMilkingTarget());
							room.incrementFluidStored(new FluidStored(getMilkingTarget().getId(), getMilkingTarget().getGirlcum(), milked), milked);
							
							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PUSSY_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PUSSY_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PUSSY_TARGET", getMilkingTarget()));
							}

							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center; color:"+PresetColour.GIRLCUM.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), "将[npc.girlcum]放入储藏室中！")
									+ "</p>");
						}
						@Override
						public boolean postEndTurnEffects() {
							getMilkingTarget().setLastTimeOrgasmedSeconds(Main.game.getSecondsPassed());
							return true;
						}
					};
				}
				
				
			} else if(index==9) {
				if(!getMilkingTarget().hasBreastsCrotch()) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"挤腹乳"
									:UtilText.parse(getMilkingTarget(), "给[npc.NamePos]的腹乳挤奶")),
							UtilText.parse(getMilkingTarget(), "[npc.Name]没有腹乳可以挤奶！"),
							null);
					
				} else if(getMilkingTarget().getBreastCrotchRawStoredMilkValue()==0) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"挤[pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "给[npc.NamePos]的[npc.crotchBoobs]挤奶")),
							UtilText.parse(getMilkingTarget(), "[npc.namePos]的[npc.crotchBoobs]中没有牛奶，所以[npc.she]暂时无法挤奶！"),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.NIPPLES_CROTCH, true)) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"挤[pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "给[npc.NamePos]的[npc.crotchBoobs]挤奶")),
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull]无法使用[npc.her]的[npc.crotchNipples]，因此暂时无法挤奶……"),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"挤[pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "给[npc.NamePos]的[npc.crotchBoobs]挤奶")),
							UtilText.parse(getMilkingTarget(), "没有空闲的挤奶机供[npc.name]使用！"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"挤[pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "给[npc.NamePos]的[npc.crotchBoobs]挤奶")),
							UtilText.parse(getMilkingTarget(),
									"[npc.name]不是你的奴隶， [npc.sheIs]只有在"
											+ "<span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span>你。"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& (getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative() || getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative())) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"挤[pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "给[npc.NamePos]的[npc.crotchBoobs]挤奶")),
							UtilText.parse(getMilkingTarget(),
									(getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative()
											?(getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative()
												?"[npc.name]不是你的奴隶，而且这两个性癖都不喜欢，所以不会让你这么做"
													+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+"和"+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+"性癖。"
												:"[npc.name]不是你的奴隶，而且不喜欢"+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+"这个性癖，所以不会让你这么做。")
											:"[npc.name]不是你的奴隶，而且不喜欢"+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+"这个性癖，所以不会让你这么做。")),
							null);
					
				} else {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"挤[pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "给[npc.NamePos]的[npc.crotchBoobs]挤奶")),
							UtilText.parse(getMilkingTarget(), "使用这个房间的备用挤奶设备给[npc.namePos]的[npc.crotchBoobs]挤奶。"),
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualCrotchMilkPerHour(getMilkingTarget());
							if(milked < getMilkingTarget().getBreastCrotchRawStoredMilkValue() && milked < MilkingRoom.getMaximumMilkPerHour(getMilkingTarget())) {
								milked = (int) Math.min(getMilkingTarget().getBreastCrotchRawStoredMilkValue(), MilkingRoom.getMaximumMilkPerHour(getMilkingTarget()));
							}
							room.incrementFluidStored(new FluidStored(getMilkingTarget().getId(), getMilkingTarget().getMilkCrotch(), milked), milked);

							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_UDDERS_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_UDDERS_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_UDDERS_TARGET", getMilkingTarget()));
							}
							
							Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center; color:"+PresetColour.MILK.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), "将[npc.crotchMilk]放入储藏室中！")
								+ "</p>");
						}
						@Override
						public boolean postEndTurnEffects() {
							int milked = MilkingRoom.getActualCrotchMilkPerHour(getMilkingTarget());
							getMilkingTarget().incrementBreastCrotchStoredMilk(-milked);
							return true;
						}
					};
				}
				
			} else if(index==10) {
				if(Main.game.getCharactersPresent().isEmpty()) {
					return new ResponseEffectsOnly(
							"目标: <span style='color:"+getMilkingTarget().getFemininity().getColour().toWebHexString()+";'>"+UtilText.parse(getMilkingTarget(), "[npc.Name]")+"</span>",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull]目前的目标是既要挤奶又要榨取[npc.herHim]的汁液。"
									+ "如果这个房间不是空的，你可以使用这个按钮在可用目标之间循环。")) {
						
					};
					
				} else {
					return new Response(
							"目标: <span style='color:"+getMilkingTarget().getFemininity().getColour().toWebHexString()+";'>"+UtilText.parse(getMilkingTarget(), "[npc.Name]")+"</span>",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull]目前的目标是既要挤奶又要榨取[npc.herHim]的汁液。激活此按钮可在可用目标之间循环切换。"),
							null) {
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getCurrentDialogueNode();
						}
						@Override
						public void effects() {
							List<GameCharacter> targetCharactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
							targetCharactersPresent.add(Main.game.getPlayer());
							for(int i=0; i<targetCharactersPresent.size();i++) {
								if(targetCharactersPresent.get(i).equals(getMilkingTarget())) {
									if(i==targetCharactersPresent.size()-1) {
										MilkingRoom.setTargetedCharacter(targetCharactersPresent.get(0));
									} else {
										MilkingRoom.setTargetedCharacter(targetCharactersPresent.get(i+1));
									}
									break;
								}
							}
							Main.game.updateResponses();
						}
					};
				}
				
			} else if(index-11<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-11);
				return LilayaHomeGeneric.interactWithNPC(slave);
			}
				
			return null;
		}
	};
	
	public static final DialogueNode MILKED = new DialogueNode("房间", "。", true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "把自己从挤奶机上解下来，继续上路。", MILKED) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	/**
	 * Used in OccupantController when a character consumes fluids.
	 */
	public static final DialogueNode INGEST = new DialogueNode("房间", "。", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "你思考着接下来该做什么……", INGEST) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
				};
			}
			return null;
		}
	};
}
