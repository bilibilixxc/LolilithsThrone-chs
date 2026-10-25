package com.lilithsthrone.LolificationProject.ModCommon;

import com.lilithsthrone.LolificationProject.Lexi.TussyDisplay;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.UtilText;

import static com.lilithsthrone.game.dialogue.utils.BodyChanging.*;

public class Menus {

    public static final DialogueNode BODY_CHANGING_TUSSY = new DialogueNode("尾穴", "", true) {

        @Override
        public String getHeaderContent() {
            UtilText.nodeContentSB.setLength(0);

            UtilText.nodeContentSB.append(
                    getSelfTransformDescription("tussy")

                            + "<div style='clear:left;'>"
                            + TussyDisplay.getSelfTransformTussyModifiersDiv()
                            + TussyDisplay.getSelfTransformTussyWetnessDiv()
                            + "</div>"

                            + "<div style='clear:left;'>"
                            + TussyDisplay.getSelfTransformTussyCapacityDiv()
                            + TussyDisplay.getSelfTransformTussyDepthDiv()
                            + "</div>"

                            + "<div style='clear:left;'>"
                            + TussyDisplay.getSelfTransformTussyElasticityDiv()
                            + TussyDisplay.getSelfTransformTussyPlasticityDiv()
                            + "</div>"

                            + CharacterModificationUtils.getKatesDivCoveringsNew(false,
                            BodyChanging.getTarget().getTailRace(),
                            BodyChanging.getTarget().getCovering(BodyCoveringType.VAGINA).getType(),
                            "尾穴颜色",
                            UtilText.parse(BodyChanging.getTarget(), "改变[npc.namePos]尾穴的颜色。"),
                            true, true));

            return UtilText.nodeContentSB.toString();
        }

        @Override
        public String getContent() {
            return "";
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            return getBodyChangingResponse(responseTab, index);
        }
    };
}