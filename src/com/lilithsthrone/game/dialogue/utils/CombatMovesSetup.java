package com.lilithsthrone.game.dialogue.utils;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.4
 * @version 0.3.4
 * @author Irbynx
 */
public class CombatMovesSetup {

    private static GameCharacter target;
    private static DialogueNode dialogueReturn;
    
    public static GameCharacter getTarget() {
        if(target==null) {
            return Main.game.getPlayer();
        }
        return target;
    }

    public static void setTarget(GameCharacter target, DialogueNode dialogueReturn) {
        CombatMovesSetup.target = target;
        CombatMovesSetup.dialogueReturn = dialogueReturn;
    }

    public static final DialogueNode COMBAT_MOVES_CORE = new DialogueNode("战斗动作", "", true) {
        @Override
        public String getHeaderContent() {
            UtilText.nodeContentSB.setLength(0);

            UtilText.nodeContentSB.append(
                    "<div class='container-full-width' style='padding:8px; text-align:center;'>"
                            + "虽然角色在战斗中可以认知和使用的招式数量没有限制，"
                            	+ "但同一时间只能选择"+String.valueOf(GameCharacter.MAX_COMBAT_MOVES)+"种[style.colourMinorGood(核心战斗动作)]。"
                            + "任何[style.colourMinorBad(非核心动作)]在战斗中消耗的[style.colourBad(行动点+1)]，且[style.colourBad(冷却回合+1)]。<br/>"
                            + UtilText.parse(getTarget(),
                            		"<i>(你可以点击下面的图标从[npc.namePos]的核心战斗行为中添加或删除它们。)</i>"
                            		+(getTarget().isPlayer()
                            				?""
                            				:"<i>(战斗中[npc.Name]只会使用[npc.her]的核心动作，除非没有任何可用的核心动作，在此情况下[npc.she]将从非核心动作中选取。)</i>"))
                            + "</div>"
                            + "<div class='container-full-width' style='padding:8px; text-align:center;'>"
                            + "<h6 style='text-align:center;'>[style.colourMinorGood(核心战斗动作)]</h6>");

            for(int i=0;i<GameCharacter.MAX_COMBAT_MOVES;i++) {
                AbstractCombatMove mv = null;
                if(i<target.getEquippedMoves().size()) {
                    mv = target.getEquippedMoves().get(i);
                }
                if(mv!=null) {
                    UtilText.nodeContentSB.append("<div id='MOVE_" + mv.getIdentifier() + "' class='square-button small' style='width:8%; display:inline-block; float:none; border:2px solid " + mv.getType().getColour().toWebHexString() + ";'>"
                            + "<div class='square-button-content'>"+mv.getSVGString()+"</div>"
                            + "</div>");

                } else {
                    UtilText.nodeContentSB.append("<div id='MOVE_" + i + "' class='square-button small' style='display:inline-block; float:none;'></div>");

                }
            }
            UtilText.nodeContentSB.append("</div>");

            UtilText.nodeContentSB.append(
                            "<div class='container-full-width' style='padding:8px; text-align:center;'>"
                            + "<h6 style='text-align:center;'>[style.colourMinorBad(非核心战斗动作)]</h6>");

            for(int i=0;i<target.getAvailableMoves().size();i++) {
                AbstractCombatMove mv = target.getAvailableMoves().get(i);
                if(!target.getEquippedMoves().contains(target.getAvailableMoves().get(i))) {
                    UtilText.nodeContentSB.append("<div id='MOVE_" + mv.getIdentifier() + "' class='square-button small' style='width:8%; display:inline-block; float:none; border:2px solid " + mv.getType().getColour().toWebHexString() + ";'>"
                            + "<div class='square-button-content'>" + mv.getSVGString() + "</div>"
                            + "</div>");
                }
            }
            UtilText.nodeContentSB.append("</div>");

            return UtilText.nodeContentSB.toString();
        }

        @Override
        public String getContent(){
            return "";
        }
		
		@Override
		public String getResponseTabTitle(int index) {
			return dialogueReturn.getResponseTabTitle(index);
		}
        
        @Override
        public Response getResponse(int responseTab, int index) {
        	return dialogueReturn.getResponse(responseTab, index);
//            if (index == 0) {
//                return new Response("Back", "Return to the previous menu.", dialogueReturn);
//            } else {
//                return null;
//            }
        }

        @Override
        public DialogueNodeType getDialogueNodeType() {
            return DialogueNodeType.PHONE;
        }
    };
}
