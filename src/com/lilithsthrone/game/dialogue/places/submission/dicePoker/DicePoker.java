package com.lilithsthrone.game.dialogue.places.submission.dicePoker;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.GamblingDenPatron;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.6
 * @version 0.3.5.5
 * @author Innoxia
 */
public class DicePoker {
	
	private static String dialoguePath;
	private static DialogueNode endingNode;
	private static int moneyPool;
	private static DicePokerTable table;
	private static NPC gambler;
	private static List<Dice> playerDice = new ArrayList<>();
	private static List<Dice> gamblerDice = new ArrayList<>();
	private static List<Dice> diceToReroll = new ArrayList<>();
	private static String[] progressDescriptions = new String[] {"摇点", "下注", "重摇", "支付"};
	public static int progress = 0;
	private static String responseContent;
	private static String buyInDescription;
	
	static {
		for(int i=0; i<5; i++) {
			playerDice.add(new Dice());
			gamblerDice.add(new Dice());
		}
	}
	
	public static DialogueNode initDicePoker(NPC gambler, DicePokerTable table, DialogueNode endingNode, String dialoguePath) {
		DicePoker.dialoguePath = dialoguePath;
		DicePoker.endingNode = endingNode;
		DicePoker.table = table;
		
		progress = 0;
		moneyPool = table.getInitialBet()*2;
		
		buyInDescription = Main.game.getPlayer().incrementMoney(-table.getInitialBet());
		
		DicePoker.gambler = gambler;
		if(DicePoker.gambler.getDice()!=null) {
			gamblerDice = new ArrayList<>(gambler.getDice());
			
		} else {
			gamblerDice = new ArrayList<>();
			for(int i=0; i<5; i++) {
				gamblerDice.add(new Dice());
			}
		}
		
		for(Dice d : playerDice) {
			d.setFace(DiceFace.ONE);
		}
		
		for(Dice d : gamblerDice) {
			d.setFace(DiceFace.ONE);
		}
		
		diceToReroll.clear();
		diceToReroll.addAll(playerDice);
		diceToReroll.addAll(gamblerDice);
		
//		gambler.setPlayerKnowsName(true);
		
		return START;
	}
	
	public static List<Dice> getPlayerDice() {
		return playerDice;
	}
	
	public static boolean isAbleToSelectReroll() {
		return !Main.game.getCurrentDialogueNode().equals(START)
				&& !Main.game.getCurrentDialogueNode().equals(END_WIN)
				&& !Main.game.getCurrentDialogueNode().equals(END_DRAW)
				&& !Main.game.getCurrentDialogueNode().equals(END_LOSS);
	}
	
	public static void setReroll(Dice d) {
		if(diceToReroll.contains(d)) {
			diceToReroll.remove(d);
		} else {
			diceToReroll.add(d);
		}
		Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
	}

	private static String getGamblingFormat(String turnText) {
		UtilText.nodeContentSB.setLength(0);
		
		int comparingHands = Hand.compareHands(playerDice, gamblerDice);
		
		UtilText.nodeContentSB.append("<div class='container-half-width'>");
			for(int i=0; i<playerDice.size(); i++) {
				UtilText.nodeContentSB.append("<div class='modifier-icon' style='width:18%; margin:0 1%; border:3px solid "+(diceToReroll.contains(playerDice.get(i))?PresetColour.GENERIC_MINOR_GOOD.toWebHexString():"")+";'>"
													+(Hand.getDiceInHand(playerDice).contains(playerDice.get(i))
															?"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>"
																	+SVGImages.SVG_IMAGE_PROVIDER.getDiceGlow()
																+ "</div>"
															:"")
													+"<div class='modifier-icon-content'>"+playerDice.get(i).getFace().getSVGString()+"</div>"
													+ "<div class='overlay' id='DICE_PLAYER_"+i+"'></div>"
											+ "</div>");
			}
			Hand playerHand = Hand.getHand(playerDice);
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "<b>"+playerHand.getRanking()+". "+playerHand.getName()+"</b>| [style.colourDisabled(价值: "+Hand.getValue(playerDice)+")]<br/>"
					+(comparingHands==0
						?"[style.colourDisabled(你"+(progress==3?"平局了":"要平局了")+"……)]"
						:(comparingHands<0
							?"[style.colourTerrible(你"+(progress==3?"输了":"要输了")+"！)]"
							:"[style.colourExcellent(你"+(progress==3?"赢了":"要赢了")+"！)]"))
						+ "</div>");
		UtilText.nodeContentSB.append("</div>");
		
		UtilText.nodeContentSB.append("<div class='container-half-width'>");
			for(int i=0; i<gamblerDice.size(); i++) {
				UtilText.nodeContentSB.append("<div class='modifier-icon' style='width:18%; margin:0 1%; border:3px solid "+(diceToReroll.contains(gamblerDice.get(i))?PresetColour.GENERIC_MINOR_GOOD.toWebHexString():"")+";'>"
													+(Hand.getDiceInHand(gamblerDice).contains(gamblerDice.get(i))
															?"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>"
																	+SVGImages.SVG_IMAGE_PROVIDER.getDiceGlow()
																+ "</div>"
															:"")
													+"<div class='modifier-icon-content'>"+gamblerDice.get(i).getFace().getSVGString()+"</div>"
											+ "</div>");
			}
			Hand gamblerHand = Hand.getHand(gamblerDice);
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ "<b>"+gamblerHand.getRanking()+"."+gamblerHand.getName()+"</b>| [style.colourDisabled(价值: "+Hand.getValue(gamblerDice)+")]<br/>"
					+UtilText.parse(gambler,(comparingHands==0
						?"[style.colourDisabled([npc.Name]"+(progress==3?"平局了":"要平局了")+"……)]"
						:(comparingHands>0
							?"[style.colourTerrible([npc.Name]"+(progress==3?"输了":"要输了")+"！)]"
							:"[style.colourExcellent([npc.Name]"+(progress==3?"赢了":"要赢了")+"！)]")))
					+ "</div>");
		UtilText.nodeContentSB.append("</div>");
		
		
		UtilText.nodeContentSB.append("<div class='container-full-width'>");
		UtilText.nodeContentSB.append("<div class='container-quarter-width' style='width:13%; margin:0 2%; text-align:center;'>"
				+ "进度:"
				+ "</div>");
		for(int i=0; i<progressDescriptions.length; i++) {
			UtilText.nodeContentSB.append(
					(i!=0
						?"<div class='container-quarter-width inner' style='width:5%; margin:0; text-align:center;'>"+(progress<i?"&gt;":"[style.colourDisabledDark(&gt;)]")+"</div>"
						:"")
					+ "<div class='container-quarter-width inner' style='box-sizing:border-box; width:13%; margin:0 2%; text-align:center; "+(progress==i?"border:1px solid #777;":"")+"'>"
					+ (progress==i
						?"[style.boldGood("+progressDescriptions[i]+")]"
						:progress<i
							?progressDescriptions[i]
							:"[style.colourDisabledDark("+progressDescriptions[i]+")]")
					+ "</div>");
		}
		UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
				+ "总奖池: "+UtilText.formatAsMoney(moneyPool, "span")
				+ "</div>");
		UtilText.nodeContentSB.append("</div>");
		
		UtilText.nodeContentSB.append(UtilText.parse(gambler,turnText));
					
		return UtilText.nodeContentSB.toString();
	}
	
	private static void rollRice() {
		for(Dice dice : diceToReroll) {
			dice.roll();
		}
		diceToReroll.clear();
	}
	
	private static long getRaiseAmount() {
		if(Main.game.getPlayer().getMoney()<table.getRaiseAmount()) {
			return Main.game.getPlayer().getMoney();
		} else {
			return table.getRaiseAmount();
		}
	}
	
	private static boolean isGamblerRaising() {
		int differenceWillingToRaiseAt = -Util.random.nextInt(3)-1;
		return Hand.getHand(playerDice)!=Hand.getHand(gamblerDice) && Hand.compareHands(playerDice, gamblerDice) < differenceWillingToRaiseAt;
	}
	
	private static boolean isGamblerFolding() {
		int differenceWillingToFoldAt = Util.random.nextInt(6)+4;
		return Hand.getHand(playerDice)!=Hand.getHand(gamblerDice) && Hand.compareHands(playerDice, gamblerDice) > differenceWillingToFoldAt;
	}
	
	private static void calculateGamblerRerolls() {
		List<Dice> rerollDice = new ArrayList<>(gamblerDice);
		
		if(Hand.getHand(gamblerDice)!=Hand.NINE_RUNT) {
			rerollDice.removeAll(Hand.getDiceInHand(gamblerDice));
		}
		
		diceToReroll.addAll(rerollDice);
	}
	
	/**
	 * For use in external dialogue files.
	 */
	public static final DialogueNode GAMBLING = new DialogueNode("骰子扑克桌", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<NPC> gamblers = Main.game.getNonCompanionCharactersPresent();
			
			if(index==0) {
				return null;
				
			} else if(index==gamblers.size()+1){
				return new Response("规则", "阅读附近展示骰子扑克规则的标志。", GAMBLING_RULES);
				
			} else {
				try {
					gamblers.sort((g1, g2) -> ((GamblingDenPatron) g1).getTable().compareTo(((GamblingDenPatron) g2).getTable()));
				} catch(Exception ex) {
				}
				
				if(index-1<gamblers.size()) {
					NPC gambler = gamblers.get(index-1);
					DicePokerTable table = 
							(gambler instanceof GamblingDenPatron && ((GamblingDenPatron) gambler).getTable()!=null)
								?((GamblingDenPatron) gambler).getTable()
								:DicePokerTable.COPPER;
					int buyIn = table.getInitialBet()+table.getRaiseAmount();
					if(Main.game.getPlayer().getMoney()>=buyIn) {
						return new ResponseEffectsOnly(
								"<span style='color:"+table.getColour().toWebHexString()+";'>"+UtilText.parse(gambler, "[npc.Name(a)]")+"</span> ("+UtilText.formatAsMoney(buyIn, "span")+")",
								UtilText.parse(gambler,
										"跟[npc.name]来一把骰子扑克。入注金额为"+UtilText.formatAsMoney(table.getInitialBet(), "span")
										+"，但想要加注则还需要"+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+"。")) {
							@Override
							public void effects() {
								Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler, table, Main.game.getDefaultDialogue(), "misc/dicePoker")));
							}
						};
						
					} else {
						return new Response(gambler.getName(true)+" ("+UtilText.formatAsMoneyUncoloured(buyIn, "span")+")",
								"入注金额为"+UtilText.formatAsMoney(table.getInitialBet(), "span")
								+"，但想要加注则还需要"+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+"。所以你钱不够在这个桌子上赌！",
								null);
					}
					
				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNode GAMBLING_RULES = new DialogueNode("骰子扑克桌", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/main", "GAMBLING_RULES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("返回", "读完规则，从旁边退开。", Main.game.getDefaultDialogue());
			}
			return null;
		}
	};
	
	private static final DialogueNode START = new DialogueNode("骰子扑克", "", true) {
		@Override
		public void applyPreParsingEffects() {
			UtilText.addSpecialParsingString(table.getName(), true);
			UtilText.addSpecialParsingString(UtilText.formatAsMoney(table.getInitialBet(), "span"), false);
			
			Main.game.appendToTextStartStringBuilder(getGamblingFormat(buyInDescription
					+UtilText.parseFromXMLFile(dialoguePath, "START", gambler)));
			
			gambler.setPlayerKnowsName(true);
		}
		@Override
		public String getLabel() {
			return "骰子扑克 -<b style='color:"+table.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(table.getName())+"</b>";
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("摇点", "摇骰子。", ROLL) {
					@Override
					public void effects() {
						rollRice();
						progress++;
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode ROLL = new DialogueNode("骰子扑克", "", true) {
		
		@Override
		public String getContent() {
			if(Hand.compareHands(playerDice, gamblerDice)>0) {
				return getGamblingFormat(UtilText.parseFromXMLFile(dialoguePath, "ROLL_WINNING", gambler));
					
			} else if(Hand.compareHands(playerDice, gamblerDice)==0) {
				return getGamblingFormat(UtilText.parseFromXMLFile(dialoguePath, "ROLL_DRAWING", gambler));
					
			} else {
				return getGamblingFormat(UtilText.parseFromXMLFile(dialoguePath, "ROLL_LOSING", gambler));
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly("跟注", "不提高赌注。") {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(UtilText.formatAsMoney(getRaiseAmount(), "span"), true);
						if(isGamblerRaising()) {
							moneyPool+=getRaiseAmount();
							responseContent = UtilText.parseFromXMLFile(dialoguePath, "ROLL_CALL_OPPONENT_RAISES", gambler);
							Main.game.setContent(new Response("", "", BET_NEED_REACT));
							
						} else {
							responseContent = UtilText.parseFromXMLFile(dialoguePath, "ROLL_CALL_OPPONENT_CALLS", gambler);
							calculateGamblerRerolls();
							progress++;
							Main.game.setContent(new Response("", "", REROLL));
						}
					}
				};
				
			} else if(index==2) {
				return new ResponseEffectsOnly("加注", "提高赌注。") {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(UtilText.formatAsMoney(getRaiseAmount(), "span"), true);
						if(isGamblerFolding()) {
							String moneyChange = Main.game.getPlayer().incrementMoney(moneyPool);
							UtilText.addSpecialParsingString(moneyChange, false);
							responseContent = UtilText.parseFromXMLFile(dialoguePath, "ROLL_RAISE_OPPONENT_FOLDS", gambler);
							progress++;
							progress++;
							Main.game.setContent(new Response("", "", END_WIN));
							
						} else {
							moneyPool+=getRaiseAmount()*2;
							String moneyChange = Main.game.getPlayer().incrementMoney(-getRaiseAmount());
							UtilText.addSpecialParsingString(moneyChange, false);
							responseContent = UtilText.parseFromXMLFile(dialoguePath, "ROLL_RAISE_OPPONENT_CALLS", gambler);
							calculateGamblerRerolls();
							progress++;
							Main.game.setContent(new Response("", "", REROLL));
						}
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode BET_NEED_REACT = new DialogueNode("骰子扑克", "", true) {
		
		@Override
		public String getContent() {
			return getGamblingFormat(responseContent);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly("跟注("+UtilText.formatAsMoney(getRaiseAmount(), "span")+")", UtilText.parse(gambler, "跟随[npc.namePos]加注"+UtilText.formatAsMoney(getRaiseAmount(), "span")+"。")) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(UtilText.formatAsMoney(getRaiseAmount(), "span"), true);
						moneyPool+=getRaiseAmount();
						String moneyChange = Main.game.getPlayer().incrementMoney(-getRaiseAmount());
						UtilText.addSpecialParsingString(moneyChange, false);
						responseContent = UtilText.parseFromXMLFile(dialoguePath, "BET_NEED_REACT_CALL", gambler);
						calculateGamblerRerolls();
						progress++;
						Main.game.setContent(new Response("", "", REROLL));
					}
				};
				
			} else if(index==2) {
				return new ResponseEffectsOnly("弃权", UtilText.parse(gambler, "向[npc.namePos]投降，让[npc.herHim]拿走奖池中"+UtilText.formatAsMoney(moneyPool, "span")+"。")) {
					@Override
					public void effects() {
						moneyPool+=getRaiseAmount();
						UtilText.addSpecialParsingString(UtilText.formatAsMoney(moneyPool, "span"), true);
						responseContent = UtilText.parseFromXMLFile(dialoguePath, "BET_NEED_REACT_FOLD", gambler);
						progress++;
						progress++;
						Main.game.setContent(new Response("", "", END_LOSS));
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode REROLL = new DialogueNode("骰子扑克", "", true) {
		
		@Override
		public String getContent() {
			return getGamblingFormat(responseContent);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			boolean reroll = false;
			for(Dice d : playerDice) {
				if(diceToReroll.contains(d)) {
					reroll=true;
					break;
				}
			}
			
			if(index==1) {
				return new ResponseEffectsOnly(
						reroll
							?"重摇"
							:"不重摇",
						reroll
							?"摇骰子。"
							:"选择不重摇任何一枚骰子。(点击选中骰子来重摇。)") {
					@Override
					public void effects() {
						boolean diceRerolled = !diceToReroll.isEmpty();
						rollRice();
						
						if(Hand.compareHands(playerDice, gamblerDice)==0) {
							UtilText.addSpecialParsingString(UtilText.formatAsMoney(moneyPool), true);
							UtilText.addSpecialParsingString(Main.game.getPlayer().incrementMoney(moneyPool/2), false);
							if(diceRerolled) {
								responseContent = UtilText.parseFromXMLFile(dialoguePath, "REROLL_DRAW_WITH_ROLL", gambler);
							} else {
								responseContent = UtilText.parseFromXMLFile(dialoguePath, "REROLL_DRAW_WITHOUT_ROLL", gambler);
							}
							progress++;
							Main.game.setContent(new Response("", "", END_DRAW));
							
						} else if(Hand.compareHands(playerDice, gamblerDice)>0) {
							String moneyChange = Main.game.getPlayer().incrementMoney(moneyPool);
							UtilText.addSpecialParsingString(moneyChange, true);
							if(diceRerolled) {
								responseContent = UtilText.parseFromXMLFile(dialoguePath, "REROLL_WIN_WITH_ROLL", gambler);
							} else {
								responseContent = UtilText.parseFromXMLFile(dialoguePath, "REROLL_WIN_WITHOUT_ROLL", gambler);
							}
							progress++;
							Main.game.setContent(new Response("", "", END_WIN));
						
						} else {
							UtilText.addSpecialParsingString(UtilText.formatAsMoney(moneyPool, "span"), true);
							if(diceRerolled) {
								responseContent = UtilText.parseFromXMLFile(dialoguePath, "REROLL_LOSS_WITH_ROLL", gambler);
							} else {
								responseContent = UtilText.parseFromXMLFile(dialoguePath, "REROLL_LOSS_WITHOUT_ROLL", gambler);
							}
							progress++;
							Main.game.setContent(new Response("", "", END_LOSS));
						}
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode END_WIN = new DialogueNode("骰子扑克", "", true) {
		
		@Override
		public String getContent() {
			return getGamblingFormat(responseContent);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "离桌子远点。", endingNode) {
					@Override
					public void effects() {
						progress = 0;
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode END_DRAW = new DialogueNode("骰子扑克", "", true) {
		
		@Override
		public String getContent() {
			return getGamblingFormat(responseContent);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "离桌子远点。", endingNode) {
					@Override
					public void effects() {
						progress = 0;
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode END_LOSS = new DialogueNode("骰子扑克", "", true) {
		
		@Override
		public String getContent() {
			return getGamblingFormat(responseContent);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("接受损失", "从桌旁走开。", endingNode) {
					@Override
					public void effects() {
						progress = 0;
					}
				};
				
			} else if(index==2) {
				if(!gambler.isAttractedTo(Main.game.getPlayer())) {
					return new Response("献上身体", UtilText.parse(gambler, "[npc.Name]并没有被你吸引，所以也就无法通过献上身体代替付钱。"), null);
				}
				return new Response("献上身体",
						UtilText.parse(gambler, "如果[npc.name]能把钱还回来，那你可以让[npc.she]随意使用你的身体。"),
						END_LOSS_OFFER_BODY);
			}
			return null;
		}
	};
	
	private static final DialogueNode END_LOSS_OFFER_BODY = new DialogueNode("骰子扑克", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(dialoguePath, "END_LOSS_OFFER_BODY", gambler);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("拒绝", "从桌旁走开。", endingNode) {
					@Override
					public void effects() {
						progress = 0;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile(dialoguePath, "END_LOSS_OFFER_BODY_DECLINE", gambler));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("接受",
						UtilText.parse(gambler, "允许[npc.name]公开跟你交媾，来取回你输掉的钱。"),
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(gambler),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						END_LOSS_AFTER_SEX,
						UtilText.parseFromXMLFile(dialoguePath, "END_LOSS_OFFER_BODY_ACCEPT", gambler)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(moneyPool/2));
					}
				};
			}
			return null;
		}
	};

	private static final DialogueNode END_LOSS_AFTER_SEX = new DialogueNode("结束", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(gambler, "[npc.Name]已经爽够了，性爱就此结束……");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile(dialoguePath, "END_LOSS_AFTER_SEX", gambler);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "离桌子远点。", endingNode) {
					@Override
					public void effects() {
						progress = 0;
					}
				};
			}
			return null;
		}
	};
}
