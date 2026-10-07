package com.lilithsthrone.controller.eventListeners.tooltips;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MouseEvent;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.4.5
 * @author Innoxia
 */
public class TooltipResponseDescriptionEventListener implements EventListener {
	private int index;
	private boolean nextPage = false;
	private boolean previousPage = false;
	
	private static StringBuilder tooltipSB;
	static {
		tooltipSB = new StringBuilder();
	}

	@Override
	public void handleEvent(Event event) {

		Main.mainController.setTooltipContent("");

		if (nextPage) {
			if (Main.game.isHasNextResponsePage()) {

				Main.mainController.setTooltipSize(360, 60);
				
				double xPosition = ((MouseEvent) event).getScreenX() + 16 - 180;
				if (xPosition + 360 > Main.primaryStage.getX() + Main.primaryStage.getWidth() - 16)
					xPosition = Main.primaryStage.getX() + Main.primaryStage.getWidth() - 360 - 16;
				double yPosition = Main.primaryStage.getY() + Main.primaryStage.getHeight() - (34*(MainController.RESPONSE_COUNT/5) + 4) - Main.mainController.getTooltip().getHeight()
						- (Main.mainScene.getWindow().getHeight() - Main.mainScene.getHeight() - Main.mainScene.getY());

				Main.mainController.getTooltip().setAnchorX(xPosition);
				Main.mainController.getTooltip().setAnchorY(yPosition);

				Main.mainController.setTooltipContent("<div class='title'>下一页</div>");
				
				Main.mainController.getTooltip().setAnchorX(xPosition);
				Main.mainController.getTooltip().setAnchorY(yPosition);
				
				TooltipUpdateThread.updateToolTip(xPosition,yPosition);
			}
		} else if (previousPage) {
			if (Main.game.getResponsePage() != 0) {
				
				Main.mainController.setTooltipSize(360, 60);
				
				double xPosition = ((MouseEvent) event).getScreenX() + 16 - 180;
				if (xPosition + 360 > Main.primaryStage.getX() + Main.primaryStage.getWidth() - 16)
					xPosition = Main.primaryStage.getX() + Main.primaryStage.getWidth() - 360 - 16;
				double yPosition = Main.primaryStage.getY() + Main.primaryStage.getHeight() - (34*(MainController.RESPONSE_COUNT/5) + 4) - Main.mainController.getTooltip().getHeight()
						- (Main.mainScene.getWindow().getHeight() - Main.mainScene.getHeight() - Main.mainScene.getY());

				Main.mainController.getTooltip().setAnchorX(xPosition);
				Main.mainController.getTooltip().setAnchorY(yPosition);

				Main.mainController.setTooltipContent("<div class='title'>上一页</div>");
				
				Main.mainController.getTooltip().setAnchorX(xPosition);
				Main.mainController.getTooltip().setAnchorY(yPosition);
				
				TooltipUpdateThread.updateToolTip(xPosition,yPosition);
			}
			
		} else {
			Response response = null;
			if(Main.game.getCurrentDialogueNode()!=null) {
				if (Main.game.getResponsePage() == 0) {
					response = Main.game.getCurrentDialogueNode().getResponse(Main.game.getResponseTab(), index);
				} else {
					if (index != 0) {
						response = Main.game.getCurrentDialogueNode().getResponse(Main.game.getResponseTab(), Main.game.getResponsePage() * MainController.RESPONSE_COUNT + index - 1);
					} else {
						response = Main.game.getCurrentDialogueNode().getResponse(Main.game.getResponseTab(), Main.game.getResponsePage() * MainController.RESPONSE_COUNT + MainController.RESPONSE_COUNT-1);
					}
				}
			}
			
			if (response != null) {
				tooltipSB.setLength(0);
				
				int boxHeight = 130;
				
				if(!response.hasRequirements()) {
					if(response instanceof ResponseSex) {
						if(((ResponseSex)response).isMasturbation()) {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX_AS_DOM.toWebHexString() + ";'>自慰</span></div>");
						} else if(((ResponseSex)response).isPlayerInDominantSlot()) {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX_AS_DOM.toWebHexString() + ";'>支配型性爱</span></div>");
						} else {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>服从型性爱</span></div>");
						}
						boxHeight+=44;
						tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
						
					} else if(response.isCombatHighlight()) {
						tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_COMBAT.toWebHexString() + ";'>战斗</span></div>");
						boxHeight+=44;
						tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
						
					} else if(response.getAssociatedCombatMove()!=null) {
						AbstractCombatMove move = response.getAssociatedCombatMove();
						boolean coreMove = Main.game.getPlayer().getEquippedMoves().contains(move);
						
						tooltipSB.append("<div class='title'>"
											+ "<span style='color:" + (coreMove?PresetColour.GENERIC_MINOR_GOOD:PresetColour.GENERIC_MINOR_BAD).toWebHexString() + ";'>"
												+Util.capitaliseSentence(move.getName(0, Main.game.getPlayer()))
											+"</span>"
										+ "</div>");
						boxHeight+=44;
						
						int cost = move.getAPcost(Main.game.getPlayer());
						int cooldown = move.getCooldown(Main.game.getPlayer());
						
						tooltipSB.append(
								"<div class='subTitle' style='width:46%; margin:2% 2% 0% 2%;'>"
									+"<span style='color:"+(PresetColour.ACTION_POINT_COLOURS[cost]).toWebHexString()+";'>"
									+(coreMove?cost:(cost-1)+"[style.colourBad(+1)]")
									+"</span> AP"
								+ "</div>"
								+ "<div class='subTitle' style='width:46%; margin:2% 2% 0% 2%;'>"
									+ "<span style='color:"+(cooldown-(coreMove?0:1)<=0?PresetColour.GENERIC_MINOR_GOOD:PresetColour.GENERIC_MINOR_BAD).toWebHexString()+";'>"
									+(coreMove?cooldown:(cooldown-1)+"[style.colourBad(+1)]")
									+"</span>回合冷却"
								+ "</div>");
						
						boxHeight+=36;
						
						tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");

						tooltipSB.append(
								"<div class='description-small'>"
										+ (coreMove
											?"<i>这是你的[style.colourMinorGood(核心动作)]，所以你不需要额外的AP或冷却时间就可以使用它！</i>"
											:"<i>这[style.colourMinorBad(不是你的核心动作)]，所以你需要额外消耗[style.colourBad(+1行动点)]以及[style.colourBad(+1冷却回合)]来使用它！</i>")
								+"</div>");
						boxHeight+=54;
						
					} else {
						tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
					}
					
				
				} else {
					if(response.isAvailable()) {
						if(response instanceof ResponseSex) {
							if(((ResponseSex)response).isPlayerInDominantSlot()) {
								tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX_AS_DOM.toWebHexString() + ";'>支配性爱</span> (<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>可用</span>)</div>");
							} else {
								tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>服从型性爱</span> (<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>可用</span>)</div>");
							}
						} else if(response.isCombatHighlight()) {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_COMBAT.toWebHexString() + ";'>战斗</span> (<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>可用</span>)</div>");
						} else {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>可用</span></div>");
						}
						boxHeight+=44;
						
						if(response.getSexPace()!=null) {
							tooltipSB.append("<div class='subTitle'><span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>性爱状态：</span>"
									+ "<span style='color:" + response.getSexPace().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(response.getSexPace().getName())+"</span></div>");
							boxHeight+=44;
						}
						
						tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
						
					} else if(response.isAbleToBypass()) {
						if(response instanceof ResponseSex) {
							if(((ResponseSex)response).isPlayerInDominantSlot()) {
								tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX_AS_DOM.toWebHexString() + ";'>支配型性爱</span>"
										+ "(<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>堕落</span>)</div>");
							} else {
								tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>服从型性爱</span>"
										+ "(<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>堕落</span>)</div>");
							}
						} else if(response.isCombatHighlight()) {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_COMBAT.toWebHexString() + ";'>战斗</span>(<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>堕落</span>)</div>");
						} else {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>堕落</span></div>");
						}
						boxHeight+=44;
						
						if(response.getSexPace()!=null) {
							tooltipSB.append("<div class='subTitle'><span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>性爱状态：</span>"
									+ "<span style='color:" + response.getSexPace().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(response.getSexPace().getName())+"</span></div>");
							boxHeight+=44;
						}
						
						tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
						
					} else {
						if(response instanceof ResponseSex) {
							if(((ResponseSex)response).isPlayerInDominantSlot()) {
								tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX_AS_DOM.toWebHexString() + ";'>支配型性爱</span>"
										+ "(<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>不可用</span>)</div>");
							} else {
								tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>服从型性爱</span>"
										+ "(<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>不可用</span>)</div>");
							}
						} else if(response.isCombatHighlight()) {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_COMBAT.toWebHexString() + ";'>战斗</span>(<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>不可用</span>)</div>");
						} else {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>不可用</span></div>");
						}
						boxHeight+=44;
						
						if(response.getSexPace()!=null) {
							tooltipSB.append("<div class='subTitle'><span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>性爱状态：</span>"
									+ "<span style='color:" + response.getSexPace().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(response.getSexPace().getName())+"</span></div>");
							boxHeight+=44;
						}
						
						tooltipSB.append("<div class='description'><span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>" + response.getTooltipText() + "</span></div>");
					}
					
					tooltipSB.append(
							"<div class='description' style='height:"+((response.lineHeight()+2)*18)+"; text-align:center;'>"
									+ "<b>执行要求：</b>"
									+response.getTooltipBlockingList()+response.getTooltipRequiredList()
							+"</div>");
					
					tooltipSB.append(
							"<div class='description-small'>"
									+response.getTooltipCorruptionBypassText()
							+"</div>");
					
					String extraSexInfo = response.getAdditionalSexActionInformationText();
					if(!extraSexInfo.isEmpty()) {
						tooltipSB.append(
								"<div class='description-small'>"
										+extraSexInfo
								+"</div>");
						boxHeight+=54;
					}
					
					boxHeight+=54;
					
					boxHeight+= 28 + ((response.lineHeight()+1)*18);
				}
				
				/* TODO
				 * Verify that there is no adverse effects to using this method to calculate the tooltip height,
				 * then remove all boxHeight calculations above, I guess, and apply this method to other
				 * tooltip types that could use this.
				 */
				int realHeight = Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
				
//				if(false) {
//					// for every response tooltip, print the height values
//					// very spammy
//					System.out.println("predicted: " + boxHeight);
//					System.out.println("measured:  " + realHeight);
//				}
				
				boxHeight = realHeight;

				Main.mainController.setTooltipSize(360, boxHeight);
				
				double xPosition = ((MouseEvent) event).getScreenX() + 16 - 180;
				if (xPosition + 360 > Main.primaryStage.getX() + Main.primaryStage.getWidth() - 16)
					xPosition = Main.primaryStage.getX() + Main.primaryStage.getWidth() - 360 - 16;
				
				double yPosition = Main.primaryStage.getY() + Main.primaryStage.getHeight() - (34*(MainController.RESPONSE_COUNT/5) + 4) - boxHeight
						- (Main.mainScene.getWindow().getHeight() - Main.mainScene.getHeight() - Main.mainScene.getY());
				
				Main.mainController.getTooltip().setAnchorX(xPosition);
				Main.mainController.getTooltip().setAnchorY(yPosition);
				
				TooltipUpdateThread.updateToolTip(xPosition,yPosition);
			}
			
		}
	}

	public TooltipResponseDescriptionEventListener setIndex(int index) {
		this.index = index;

		nextPage = false;
		previousPage = false;
		return this;
	}

	public TooltipResponseDescriptionEventListener nextPage() {
		nextPage = true;
		previousPage = false;

		return this;
	}

	public TooltipResponseDescriptionEventListener previousPage() {
		nextPage = false;
		previousPage = true;

		return this;
	}

}