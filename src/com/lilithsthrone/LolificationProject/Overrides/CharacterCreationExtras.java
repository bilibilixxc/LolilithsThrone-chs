package com.lilithsthrone.LolificationProject.Overrides;

import com.lilithsthrone.controller.CreationController;
import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.abstractTypes.*;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.*;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import org.w3c.dom.events.EventTarget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @since 0.4.11.3
 * @version 0.4.11.3
 * @author KeldonSlayer (DrZed)
 */
public class CharacterCreationExtras {
// Keldon add global racial selector at the top

    public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_RACE = new DialogueNode("种族", "", true) {

        @Override
        public String getHeaderContent() {
            UtilText.nodeContentSB.setLength(0);
            UtilText.nodeContentSB.append(
                    "<div class='container-full-width'>"
                            + "这是一个测试不足的功能，我花了几小时开发的，没有提示条。<br/>"
                            + "<b>这也可能会显示在生理转换菜单中，若遇到这种情况，请将鼠标悬停在对方种族的图标上。</b>"
                            + "</div>");
            UtilText.nodeContentSB.append(getRacePanel());
            UtilText.nodeContentSB.append(getRacialPanel());
            return UtilText.nodeContentSB.toString();
        }

        @Override
        public String getContent() {
            return "";
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            return CharCreationMenu.getBodyOptions(index);
        }

        @Override
        public DialogueNodeType getDialogueNodeType() {
            return DialogueNodeType.OPTIONS;
        }
    };

    private static void setRace(AbstractRace race) {
        if (!VaginaType.getVaginaTypes(race).isEmpty() && Main.game.getPlayer().hasVagina()) {
            AbstractVaginaType oldType = Main.game.getPlayer().getVaginaType(), newType = VaginaType.getVaginaTypes(race).getFirst();
            Main.game.getPlayer().setVaginaType(newType);
            updateChild(newType, oldType);
        }
        if (!PenisType.getPenisTypes(race).isEmpty() && Main.game.getPlayer().hasPenis()) {
            AbstractPenisType oldType = Main.game.getPlayer().getPenisType(), newType = PenisType.getPenisTypes(race).getFirst();
            Main.game.getPlayer().setPenisType(newType);
            updateChild(newType, oldType);
        }
        if (!BreastType.getBreastTypes(race).isEmpty() && Main.game.getPlayer().hasBreasts()) {
            AbstractBreastType oldType = Main.game.getPlayer().getBreastType(), newType = BreastType.getBreastTypes(race).getFirst();
            Main.game.getPlayer().setBreastType(newType);
            updateChild(newType, oldType);
        }
        if (!ArmType.getArmTypes(race).isEmpty()) {
            AbstractArmType oldType = Main.game.getPlayer().getArmType(), newType = ArmType.getArmTypes(race).getFirst();
            Main.game.getPlayer().setArmType(newType);
            updateChild(newType, oldType);
        }
        if (!LegType.getLegTypes(race).isEmpty()) {
            AbstractLegType oldType = Main.game.getPlayer().getLegType(), newType = LegType.getLegTypes(race).getFirst();
            Main.game.getPlayer().setLegType(newType);
            updateChild(newType, oldType);
        }
        if (!AssType.getAssTypes(race).isEmpty()) {
            AbstractAssType oldType = Main.game.getPlayer().getAssType(), newType = AssType.getAssTypes(race).getFirst();
            Main.game.getPlayer().setAssType(newType);
            updateChild(newType, oldType);
        }
        if (!EarType.getEarTypes(race).isEmpty()) {
            AbstractEarType oldType = Main.game.getPlayer().getEarType(), newType = EarType.getEarTypes(race).getFirst();
            Main.game.getPlayer().setEarType(newType);
            updateChild(newType, oldType);
        }
        if (!EyeType.getEyeTypes(race).isEmpty()) {
            AbstractEyeType oldType = Main.game.getPlayer().getEyeType(), newType = EyeType.getEyeTypes(race).getFirst();
            Main.game.getPlayer().setEyeType(newType);
            updateChild(newType, oldType);
        }
        if (!FaceType.getFaceTypes(race).isEmpty()) {
            AbstractFaceType oldType = Main.game.getPlayer().getFaceType(), newType = FaceType.getFaceTypes(race).getFirst();
            Main.game.getPlayer().setFaceType(newType);
            updateChild(newType, oldType);
        }
        if (!TorsoType.getTorsoTypes(race).isEmpty()) {
            AbstractTorsoType oldType = Main.game.getPlayer().getTorsoType(), newType = TorsoType.getTorsoTypes(race).getFirst();
            Main.game.getPlayer().setTorsoType(newType);
            updateChild(newType, oldType);
        }
//        if (!TesticleType.getTesticleTypes(race).isEmpty())
//            Main.game.getPlayer().setTesticleType(TesticleType.getTesticleTypes(race).getFirst());
        if (!HairType.getHairTypes(race).isEmpty()) {
            AbstractHairType oldType = Main.game.getPlayer().getHairType(), newType = HairType.getHairTypes(race).getFirst();
            Main.game.getPlayer().setHairType(newType);
            updateChild(newType, oldType);
        }
//        if (!TongueType.getTongueTypes(race).isEmpty())
//            Main.game.getPlayer().setTongu(TongueType.getTongueTypes(race).getFirst());
//        if (!NippleType.getNippleTypes(race).isEmpty())
//            Main.game.getPlayer().setNippleT(NippleType.getNippleTypes(race).getFirst());
//        if (!MouthType.getMouthTypes(race).isEmpty())
//            Main.game.getPlayer().setMou(MouthType.getMouthTypes(race).getFirst());
//        if (!AnusType.getAnusTypes(race).isEmpty())
//            Main.game.getPlayer().setAnus(AnusType.getAnusTypes(race).getFirst());
        if (!TailType.getTailTypes(race).isEmpty() && !(TailType.getTailTypes(race).size() == 1 && TailType.getTailTypes(race).getFirst() == TailType.NONE)) {
            AbstractTailType oldType = Main.game.getPlayer().getTailType(), newType = TailType.getTailTypes(race).getFirst();
            Main.game.getPlayer().setTailType(newType);
            updateChild(newType, oldType);
        } else {
            AbstractTailType oldType = Main.game.getPlayer().getTailType(), newType = TailType.NONE;
            Main.game.getPlayer().setTailType(newType);
            updateChild(newType, oldType);
        }

        if (race.isFlyingRace() && !WingType.getWingTypes(race).isEmpty() && !(WingType.getWingTypes(race).size() == 1 && WingType.getWingTypes(race).getFirst() == WingType.NONE)) {
            AbstractWingType oldType = Main.game.getPlayer().getWingType(), newType = WingType.getWingTypes(race).getFirst();
            Main.game.getPlayer().setWingType(newType);
            updateChild(newType, oldType);
        } else {
            AbstractWingType oldType = Main.game.getPlayer().getWingType(), newType = WingType.NONE;
            Main.game.getPlayer().setWingType(newType);
            updateChild(newType, oldType);
        }

        if (!TentacleType.getTentacleTypes(race).isEmpty() && !(TentacleType.getTentacleTypes(race).size() == 1 && TentacleType.getTentacleTypes(race).getFirst() == TentacleType.NONE)) {
            AbstractTentacleType oldType = Main.game.getPlayer().getTentacleType(), newType = TentacleType.getTentacleTypes(race).getFirst();
            Main.game.getPlayer().setTentacleType(newType);
            updateChild(newType, oldType);
        } else {
            AbstractTentacleType oldType = Main.game.getPlayer().getTentacleType(), newType = TentacleType.NONE;
            Main.game.getPlayer().setTentacleType(newType);
            updateChild(newType, oldType);
        }

        if (AntennaType.getAllAntennaTypes().size() > 1) {
            if (!AntennaType.getAntennaTypes(race).isEmpty() && !(AntennaType.getAntennaTypes(race).size() == 1 && AntennaType.getAntennaTypes(race).getFirst() == AntennaType.NONE)) {
                AbstractAntennaType oldType = Main.game.getPlayer().getAntennaType(), newType = AntennaType.getAntennaTypes(race).getFirst();
                Main.game.getPlayer().setAntennaType(newType);
                updateChild(newType, oldType);
            } else {
                AbstractAntennaType oldType = Main.game.getPlayer().getAntennaType(), newType = AntennaType.NONE;
                Main.game.getPlayer().setAntennaType(newType);
                updateChild(newType, oldType);
            }
        }
    }

    public static void initRacialPanelListeners() {
        boolean hasPrintedBreast = false;
        for (AbstractRace allRace : Race.getAllRaces()) {
            String id = "RACE_SELECT_" + allRace.getDefaultTransformName().replaceAll(" ", "_");

            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    AbstractRace prevRace = Main.game.getPlayer().getRace();
                    setRace(allRace);
                    updateRace(prevRace, allRace);
                }, false);
            }
        }
        for (BodyPartInterface bodyPart : BodyChanging.getTarget().getAllBodyParts()) {
            HashMap<String, Integer> seen = new HashMap<>();
            for (BodyPartTypeInterface bodyPartType : getTypes(bodyPart)) {
                if (bodyPart.getType() instanceof AbstractFluidType/* || bodyPart.getType() instanceof AbstractBreastType*/)
                    continue;
                List<? extends BodyPartTypeInterface> partTypes = getTypes(bodyPart);
                if (partTypes.size() <= 1) continue;

                AbstractRace race = bodyPartType.getRace();
                String name = getRaceTransformName(bodyPartType);
                if (name.equalsIgnoreCase("none")) {
                    if (bodyPart == PenisType.DILDO)
                        name = "Dildo";
                    if (bodyPart == VaginaType.ONAHOLE)
                        name = "Onahole";
                }
                if (race == Race.ELEMENTAL) continue;
                String partName = getPartName(bodyPart);
                if (partName.equals("Breast")) {
                    if (hasPrintedBreast) partName = "Udders";
                    hasPrintedBreast = true;
                }
                String id = name.replaceAll(" ", "_") + "_" + partName;
                if (MainController.document.getElementById(id) != null) {
                    ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                        BodyPartTypeInterface oldPart = changePart(bodyPart, bodyPartType);
                        updateChild(bodyPart.getType(), oldPart);
                    }, false);
                }
                if (bodyPart.getType() == bodyPartType)
                    updateChild(bodyPart.getType(), bodyPartType);
            }
        }

    }

    private static void updateRace(AbstractRace prevRace, AbstractRace allRace) {
        String id = "RACE_SELECT_" + allRace.getDefaultTransformName().replaceAll(" ", "_");
        String oldId = "RACE_SELECT_" + prevRace.getDefaultTransformName().replaceAll(" ", "_");

        Main.mainController.getWebEngine().executeScript("document.getElementById('" + id + "').classList.add('selected');");
        Main.mainController.getWebEngine().executeScript("document.getElementById('" + oldId + "').classList.remove('selected');");
    }

    public static void updateChild(BodyPartTypeInterface bodyPart, BodyPartTypeInterface oldPart) {
        String partName = getPartName(bodyPart);
        String elementID = getRaceTransformName(bodyPart).replaceAll(" ", "_") + "_" + partName;
        String oldElementID = getRaceTransformName(oldPart).replaceAll(" ", "_") + "_" + getPartName(oldPart);
        String descID = "PART_DESCRIPTION_" + partName;
        if (MainController.document.getElementById(elementID) == null || MainController.document.getElementById(oldElementID) == null) return;
        String partDescription = "\"<span id='" + descID + "'>" + getPartDescription(bodyPart) + "</span>\"";
        Main.mainController.getWebEngine().executeScript("document.getElementById('" + descID + "').innerHTML = " + partDescription + ";");
        Main.mainController.getWebEngine().executeScript("document.getElementById('" + elementID + "').classList.add('selected');");
        Main.mainController.getWebEngine().executeScript("document.getElementById('" + oldElementID + "').classList.remove('selected');");
        refreshColourSelectDiv(bodyPart);
    }

    public static void updateChildCol(BodyPartTypeInterface bodyPart, String oldCol, String newCol) {
        String partName = getPartName(bodyPart);
        String descID = "PART_DESCRIPTION_" + partName;
        String partDescription = "\"<span id='" + descID + "'>" + getPartDescription(bodyPart) + "</span>\"";
        if (MainController.document.getElementById(descID) == null || MainController.document.getElementById(oldCol) == null || MainController.document.getElementById(newCol) == null) return;
        Main.mainController.getWebEngine().executeScript("document.getElementById('" + descID + "').innerHTML = " + partDescription + ";");
        Main.mainController.getWebEngine().executeScript("document.getElementById('" + oldCol + "').classList.remove('selected');");
        Main.mainController.getWebEngine().executeScript("document.getElementById('" + oldCol + "').style.removeProperty('background-color');");
        Main.mainController.getWebEngine().executeScript("document.getElementById('" + newCol + "').classList.add('selected');");
        Main.mainController.getWebEngine().executeScript("document.getElementById('" + newCol + "').style.background = \"" + PresetColour.BASE_GREEN.getShades()[4] + "\";");
    }

    private static void initListenersByIds(String colorSelect, BodyPartTypeInterface bodyPart) {
        Pattern idPattern = Pattern.compile("id='([a-zA-Z0-9_\\-]+)'");
        Matcher idMatcher = idPattern.matcher(colorSelect);
        while (idMatcher.find()) {
            String id = idMatcher.group(1);
            if (id.contains("_PRIMARY_") || id.contains("_SECONDARY_")) {
                initColorSelectionListener(id, bodyPart, id.contains("_PRIMARY_"));
            }
            if (id.contains("_PATTERN_")) {
                initPatternSelectionListener(id, bodyPart);
            }
        }
    }

    private static void initPatternSelectionListener(String id, BodyPartTypeInterface bodyPart) {
        if (MainController.document.getElementById(id) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                Covering cover = Main.game.getPlayer().getCovering(bodyPart.getBodyCoveringType(Main.game.getPlayer()));
                CoveringPattern oldPattern = cover.getPattern();
                CoveringPattern newPattern = CoveringPattern.valueOf(id.split("PATTERN_")[1]);
                cover.setPattern(newPattern);
                updateChildPattern(newPattern, oldPattern, bodyPart);
            }, false);
        }
    }

    private static void updateChildPattern(CoveringPattern newPattern, CoveringPattern oldPattern, BodyPartTypeInterface bodyPart) {
        String partName = getPartName(bodyPart);
        String patternIdBase = partName + "_" + BodyCoveringType.getIdFromBodyCoveringType(bodyPart.getBodyCoveringType(Main.game.getPlayer())) + "_PATTERN_";
        String patternID = patternIdBase + newPattern;
        String oldPatternID = patternIdBase + oldPattern;
        Covering cover = Main.game.getPlayer().getCovering(bodyPart.getBodyCoveringType(Main.game.getPlayer()));
        refreshPartDescription(bodyPart);
        Main.mainController.getWebEngine().executeScript("document.getElementById('" + oldPatternID + "').classList.remove('active');");
        Main.mainController.getWebEngine().executeScript("document.getElementById('" + oldPatternID + "').firstChild.style.color = \"" + PresetColour.TRANSFORMATION_GENERIC.getShades()[0] + "\";");
        Main.mainController.getWebEngine().executeScript("document.getElementById('" + patternID + "').classList.add('active');");
        Main.mainController.getWebEngine().executeScript("document.getElementById('" + patternID + "').firstChild.style.color = \"" + PresetColour.GENERIC_GOOD.toWebHexString()  + "\";");
        refreshColourSelectDiv(bodyPart);
    }

    private static void refreshPartDescription(BodyPartTypeInterface bodyPart) {
        String partName = getPartName(bodyPart);
        String descID = "PART_DESCRIPTION_" + partName.replaceAll(" ", "_");
        String partDescription = "\"<span id='" + descID + "'>" + getPartDescription(bodyPart) + "</span>\"";
        Main.mainController.getWebEngine().executeScript("document.getElementById('" + descID + "').innerHTML = " + partDescription + ";");
    }

    private static void refreshColourSelectDiv(BodyPartTypeInterface bodyPart) {
        String partName = getPartName(bodyPart);
        String colorSelect = "COLOR_SELECT_DIV_" + partName;

        if (!bodyPart.isOneOf(VaginaType.NONE,
                PenisType.NONE, BreastType.NONE, TailType.NONE, HornType.NONE, WingType.NONE, TentacleType.NONE,
                AntennaType.NONE)) {
            String colDiv = UtilText.parse(coveringColorDiv(bodyPart, true));
            Main.mainController.getWebEngine().executeScript("document.getElementById('" + colorSelect + "').innerHTML = \"" + colDiv + "\";");
            initListenersByIds(colDiv, bodyPart);
        }
        else Main.mainController.getWebEngine().executeScript("document.getElementById('" + colorSelect + "').innerHTML = \"\";");
    }

    private static void initColorSelectionListener(String colorSelectionDivID, BodyPartTypeInterface bodyPart, boolean primary) {
        if (MainController.document.getElementById(colorSelectionDivID) != null) {
            ((EventTarget) MainController.document.getElementById(colorSelectionDivID)).addEventListener("click", e -> {
                Covering cover = Main.game.getPlayer().getCovering(bodyPart.getBodyCoveringType(Main.game.getPlayer()));
                String colourID = colorSelectionDivID.split(primary ? "PRIMARY_" : "SECONDARY_")[1];
                String colourIDFragment = colorSelectionDivID.replace(colourID, "");
                Colour col = PresetColour.getColourFromId(colourID);
                Colour oldCol = (primary ? cover.getPrimaryColour() : cover.getSecondaryColour());
                if (primary)
                    cover.setPrimaryColour(col);
                else cover.setSecondaryColour(col);
                updateChildCol(bodyPart, colourIDFragment + oldCol.getId(), colourIDFragment + col.getId());
            }, false);
        }
    }

    public static String getRacialPanel() {
        StringBuilder sb = new StringBuilder();

        sb.append("<div class='container-full-width'>");

        boolean hasPrintedBreast = false;
        for (BodyPartInterface bodyPart : BodyChanging.getTarget().getAllBodyParts()) {
            if (bodyPart.getType() instanceof AbstractFluidType /*|| bodyPart.getType() instanceof AbstractBreastType*/)
                continue;
            List<? extends BodyPartTypeInterface> partTypes = getTypes(bodyPart);
            if (partTypes.size() <= 1) continue;

            String partName = getPartName(bodyPart);
            if (partName.equals("Breast")) {
                if (hasPrintedBreast) partName = "Udders";
                hasPrintedBreast = true;
            }
            sb.append("<div style='display:inline-block; margin-inline:4px auto;width:100%;'>" +
                            "<div style='display:flex; flex-direction: row;  margin:4px auto;'>" +
                            "<div style='width:140px; padding-right: 1px; float:left; text-align:center;'><b style='color:")
                    .append(PresetColour.BASE_WHITE.toWebHexString()).append(";'>")
                    .append(partName)
                    .append("</b></br>")
                    .append("<span id='PART_DESCRIPTION_" + partName + "'>" + getPartDescription(bodyPart.getType()) + "</span>")
                    .append("</div>");
            sb.append("<div style='display:inline-block; margin-inline:4px auto; width:79%;'>");

            for (BodyPartTypeInterface bodyPartType : partTypes) {
                AbstractRace race = bodyPartType.getRace();
                if (race == Race.ELEMENTAL) continue;
                String name = getRaceTransformName(bodyPartType);
                if (name.equalsIgnoreCase("none")) {
                    if (bodyPart == PenisType.DILDO)
                        name = "Dildo";
                    if (bodyPart == VaginaType.ONAHOLE)
                        name = "Onahole";
                }
                sb.append("<div id='").append(name.replaceAll(" ", "_")).append("_").append(getPartName(bodyPart))
                        .append("' class='bodyPart-button").append(bodyPart.getType() == bodyPartType ? " selected" : "").append("'>")
                        .append(getPartRaceDescription(bodyPartType))
                        .append("</div>");
            }

            sb.append("</div></div><div id='COLOR_SELECT_DIV_" + getPartName(bodyPart) + "'>");
            String name = getRaceTransformName(bodyPart.getType());
            if (!bodyPart.getType().isOneOf(VaginaType.NONE,
                    PenisType.NONE, BreastType.NONE, TailType.NONE, HornType.NONE, WingType.NONE, TentacleType.NONE,
                    AntennaType.NONE))
                sb.append(UtilText.parse(coveringColorDiv(bodyPart.getType(), true)));
            sb.append("</div></div><hr/>");
        }

        sb.append("</div>");
        return sb.toString();
    }

    public static String getRacePanel() {
        StringBuilder sb = new StringBuilder();

        sb.append("<div class='container-full-width'>");

        String partName = "全部";
        sb.append("<div style='display:inline-block; margin-inline:4px auto;width:100%;'>" +
                        "<div style='display:flex; flex-direction: row;  margin:4px auto;'>" +
                        "<div style='width:140px; padding-right: 1px; float:left; text-align:center;'><b style='color:")
                .append(PresetColour.BASE_WHITE.toWebHexString()).append(";'>")
                .append(partName)
                .append("</b></br></div>");
        sb.append("<div style='display:inline-block; margin-inline:4px auto; width:79%;'>");

        for (AbstractRace race : Race.getAllRaces()) {
            if (race == Race.ELEMENTAL) continue;
            String name = race.getDefaultTransformName();
            sb.append("<div id='").append("RACE_SELECT_").append(name.replaceAll(" ", "_"))
                    .append("' class='bodyPart-button").append(race == Main.game.getPlayer().getRace() ? " selected" : "").append("'>")
                    .append(getRaceDescription(race))
                    .append("</div>");
        }
        sb.append("</div></div><div id='COLOR_SELECT_DIV_RACE'>");
        String name = Main.game.getPlayer().getRace().getDefaultTransformName();
        sb.append(UtilText.parse(coveringColorDiv(Main.game.getPlayer().getBody().getArm().getType(), true)));
        sb.append("</div></div><hr/>");

        sb.append("</div>");
        return sb.toString();
    }

    /**
     @param partType the body part type being described
     @return The HTML Element span complete with coloured description
     **/
    private static String getPartDescription(BodyPartTypeInterface partType) {
        AbstractRace race = partType.getRace();
        AbstractBodyCoveringType coveringType = partType.getBodyCoveringType(Main.game.getPlayer());
        if (race == null || coveringType == null /*|| race == Race.NONE || partType.isOneOf(VaginaType.NONE,
                PenisType.NONE, BreastType.NONE, TailType.NONE, HornType.NONE, WingType.NONE, TentacleType.NONE,
                AntennaType.NONE)*/) return "无";
        GameCharacter character = Main.game.getPlayer();
        Covering covering = character.getCovering(coveringType);
        String raceName = race.getName(character.getBody(), false);
        String coveringName = covering.getName(character);

        String raceStr = (covering.getType() != BodyCoveringType.DILDO
                ? "<span id='RACE_COLOR_" + getPartName(partType) + "' style='color:" + race.getColour().toWebHexString() + ";'>"
                + Util.capitaliseSentence(raceName)
                : "<span style='color:" + PresetColour.BASE_PINK_DEEP.toWebHexString() + ";'>"
                + "假阳具")
                + "</span>";

        String coveringStr = covering.getColourDescriptor(character, true, true) + " " + Util.capitaliseSentence(coveringName);
        return raceStr + " " + coveringStr;
    }

    /**
     @param partType the compontent used to get the description
     @return the HTML div containing race description
     **/
    private static String getPartRaceDescription(BodyPartTypeInterface partType) {
        AbstractRace race = partType.getRace();
        GameCharacter character = Main.game.getPlayer();
        AbstractBodyCoveringType coveringType = partType.getBodyCoveringType(character);
        if (race == null || coveringType == null /*|| partType.isOneOf(VaginaType.NONE,
                PenisType.NONE, BreastType.NONE, TailType.NONE, HornType.NONE, WingType.NONE, TentacleType.NONE,
                AntennaType.NONE)*/) return "无";
        Covering covering = character.getCovering(coveringType);

        return (covering.getType() != BodyCoveringType.DILDO
                ? "<span id='RACE_COLOR_" + getPartName(partType) + "' style='color:" + race.getColour().toWebHexString() + ";'>"
                + Util.capitaliseSentence(getRaceTransformName(partType))
                : "<span style='color:" + PresetColour.BASE_PINK_DEEP.toWebHexString() + ";'>"
                + (partType == PenisType.DILDO ? "假阳具" : "飞机杯"))
                + "</span>";
    }

    /**
     @param race the race used to get the description
     @return the HTML div containing race description
     **/
    private static String getRaceDescription(AbstractRace race) {
        GameCharacter character = Main.game.getPlayer();

        return "<span id='RACE_COLOR_RACIAL' style='color:" + race.getColour().toWebHexString() + ";'>"
                + Util.capitaliseSentence(race.getDefaultTransformName())
                + "</span>";
    }

    private static BodyPartTypeInterface changePart(BodyPartInterface bodyPart, BodyPartTypeInterface bodyPartType) {
        if (bodyPart.getType() instanceof AbstractVaginaType && bodyPartType instanceof AbstractVaginaType type) {
            AbstractVaginaType vaginaType = BodyChanging.getTarget().getVaginaType();
            BodyChanging.getTarget().setVaginaType(type);
            return vaginaType;
        }
        if (bodyPart.getType() instanceof AbstractPenisType && bodyPartType instanceof AbstractPenisType type) {
            AbstractPenisType penisType = BodyChanging.getTarget().getPenisType();
            BodyChanging.getTarget().setPenisType(type);
            return penisType;
        }
        if (bodyPart.getType() instanceof AbstractBreastType && bodyPartType instanceof AbstractBreastType type) {
            AbstractBreastType breastType = BodyChanging.getTarget().getBreastType();
            BodyChanging.getTarget().setBreastType(type);
            return breastType;
        }
        if (bodyPart.getType() instanceof AbstractArmType && bodyPartType instanceof AbstractArmType type) {
            AbstractArmType armType = BodyChanging.getTarget().getArmType();
            BodyChanging.getTarget().setArmType(type);
            return armType;
        }
        if (bodyPart.getType() instanceof AbstractLegType && bodyPartType instanceof AbstractLegType type) {
            AbstractLegType legType = BodyChanging.getTarget().getLegType();
            BodyChanging.getTarget().setLegType(type);
            return legType;
        }
        if (bodyPart.getType() instanceof AbstractAssType && bodyPartType instanceof AbstractAssType type) {
            AbstractAssType assType = BodyChanging.getTarget().getAssType();
            BodyChanging.getTarget().setAssType(type);
            return assType;
        }
        if (bodyPart.getType() instanceof AbstractTailType && bodyPartType instanceof AbstractTailType type) {
            AbstractTailType tailType = BodyChanging.getTarget().getTailType();
            BodyChanging.getTarget().setTailType(type);
            return tailType;
        }
        if (bodyPart.getType() instanceof AbstractEarType && bodyPartType instanceof AbstractEarType type) {
            AbstractEarType earType = BodyChanging.getTarget().getEarType();
            BodyChanging.getTarget().setEarType(type);
            return earType;
        }
        if (bodyPart.getType() instanceof AbstractEyeType && bodyPartType instanceof AbstractEyeType type) {
            AbstractEyeType eyeType = BodyChanging.getTarget().getEyeType();
            BodyChanging.getTarget().setEyeType(type);
            return eyeType;
        }
        if (bodyPart.getType() instanceof AbstractFaceType && bodyPartType instanceof AbstractFaceType type) {
            AbstractFaceType faceType = BodyChanging.getTarget().getFaceType();
            BodyChanging.getTarget().setFaceType(type);
            return faceType;
        }
        if (bodyPart.getType() instanceof AbstractTorsoType && bodyPartType instanceof AbstractTorsoType type) {
            AbstractTorsoType torsoType = BodyChanging.getTarget().getTorsoType();
            BodyChanging.getTarget().setTorsoType(type);
            return torsoType;
        }
        if (bodyPart.getType() instanceof AbstractHairType && bodyPartType instanceof AbstractHairType type) {
            AbstractHairType hairType = BodyChanging.getTarget().getHairType();
            BodyChanging.getTarget().setHairType(type);
            return hairType;
        }
        if (bodyPart.getType() instanceof AbstractHornType && bodyPartType instanceof AbstractHornType type) {
            AbstractHornType hornType = BodyChanging.getTarget().getHornType();
            BodyChanging.getTarget().setHornType(type);
            return hornType;
        }
        if (bodyPart.getType() instanceof AbstractWingType && bodyPartType instanceof AbstractWingType type) {
            AbstractWingType wingType = BodyChanging.getTarget().getWingType();
            BodyChanging.getTarget().setWingType(type);
            return wingType;
        }
        if (bodyPart.getType() instanceof AbstractTentacleType && bodyPartType instanceof AbstractTentacleType type) {
            AbstractTentacleType tentacleType = BodyChanging.getTarget().getTentacleType();
            BodyChanging.getTarget().setTentacleType(type);
            return tentacleType;
        }
        if (bodyPart.getType() instanceof AbstractAntennaType && bodyPartType instanceof AbstractAntennaType type) {
            AbstractAntennaType antennaType = BodyChanging.getTarget().getAntennaType();
            BodyChanging.getTarget().setAntennaType(type);
            return antennaType;
        }
        return null;
    }


    public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_GENITALS = new DialogueNode("生殖器外观", "", true) {

        @Override
        public String getHeaderContent() {
            UtilText.nodeContentSB.setLength(0);
            UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
                    + "<i>所有选项都可以在之后的游戏过程中进行修改。</i>"
                    + "</div>");
            UtilText.nodeContentSB.append(CharCreationMenu.PENIS_SWITCH.getDiv(BodyChanging.getTarget().hasPenis()));
            UtilText.nodeContentSB.append(CharCreationMenu.VAGINA_SWITCH.getDiv(BodyChanging.getTarget().hasVagina()));
            if (BodyChanging.getTarget().hasPenis()) {
                UtilText.nodeContentSB.append(CharacterModificationUtils.getPenisSizeDiv())
                        .append(CharacterModificationUtils.getTesticleSizeDiv())
                        .append(CharacterModificationUtils.getSelfTransformCumProductionDiv());
            } else {
                UtilText.nodeContentSB.append("<p>")
                        .append(BodyChanging.getTarget().isPlayer() ? "你" : "ta")
                        .append("没有可自定义的阴茎...</p>");
            }
            if (BodyChanging.getTarget().hasVagina()) {
                UtilText.nodeContentSB.append(CharacterModificationUtils.getVaginaCapacityDiv())
                        .append(CharacterModificationUtils.getLabiaSizeDiv())
                        .append(CharacterModificationUtils.getClitorisSizeDiv());
            } else {
                UtilText.nodeContentSB.append("<p>")
                        .append(BodyChanging.getTarget().isPlayer() ? "你" : "ta")
                        .append("没有可自定义的阴道...</p>");
            }
            return UtilText.nodeContentSB.toString();
        }

        @Override
        public String getContent() {
            return "";
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            return CharCreationMenu.getBodyOptions(index);
        }
    };

    public static void initGenitalListeners() {
        CharCreationMenu.addListeners();
        if (Main.game.getPlayer().hasPenis()) {
            CreationController.initPenisSizeListeners(true);
            CreationController.initTesticleSizeListeners(true);
            CreationController.initPenisCumProductionListeners();
        }
        if (Main.game.getPlayer().hasVagina()) {
            CreationController.initClitorisSizeListeners(true);
            CreationController.initLabiaSizeListeners();
            CreationController.initVaginaCapacityListeners();
        }
    }


    public static String coveringColorDiv(BodyPartTypeInterface partType, boolean withSecondary) {
        GameCharacter target = Main.game.getPlayer();
        AbstractBodyCoveringType coveringType = partType.getBodyCoveringType(target);
        boolean withGlow = true;
        Main.game.getDialogueFlags().setFlag(DialogueFlagValue.coveringChangeListenersRequired, true);
        if (target.getCovering(coveringType) == null) return "";

        StringBuilder sb = new StringBuilder();

        Covering activeCovering = target.getCovering(coveringType);

        List<CoveringPattern> availablePatterns = new ArrayList<>(coveringType.getAllPatterns().keySet());

        String rainbow = "";

        List<Colour> availablePrimaryColours = new ArrayList<>(coveringType.getAllPrimaryColours());
        availablePrimaryColours.sort((c1, c2) -> c2.isMetallic() ? (c1.isMetallic() ? 0 : -1) : (c1.isMetallic() ? 1 : 0));

        List<Colour> availableSecondaryColours = new ArrayList<>(coveringType.getAllSecondaryColours());
        availableSecondaryColours.sort((c1, c2) -> c2.isMetallic() ? (c1.isMetallic() ? 0 : -1) : (c1.isMetallic() ? 1 : 0));

        String border = "border: 1px solid " + PresetColour.BACKGROUND.toWebHexString() + ";";

        boolean secondaryDisabled = activeCovering.getPattern() == CoveringPattern.NONE
                || activeCovering.getPattern() == CoveringPattern.EYE_IRISES
                || activeCovering.getPattern() == CoveringPattern.EYE_PUPILS
                || activeCovering.getPattern() == CoveringPattern.EYE_SCLERA
                || !withSecondary;
        sb.append("<div class='container-full-width' style='text-align:center;'>");
        sb.append("<div class='container-full-width' style='padding:0; margin:0; text-align:center;'>");
        sb.append("<span id='COVER_DESC'>" + Util.capitaliseSentence(target.getCovering(coveringType).getFullDescription(target, true)) + "</span>");
        sb.append("</div>");

        // Pattern and Modifiers section on left:
        sb.append("<div class='container-full-width' style='width:33.3%; padding:0; margin:0; text-align:center;'>");

        // Covering Pattern:
        sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; " + border + "'>");
        sb.append("<p style='padding:0;margin:0;text-align:center;'>花纹：</p>");
        for (CoveringPattern pattern : availablePatterns) {
            sb.append(
                    "<div id='" + getPartName(partType) + "_" + BodyCoveringType.getIdFromBodyCoveringType(coveringType) + "_PATTERN_" + pattern + "' class='cosmetics-button"
                            + (activeCovering.getPattern() == pattern ? " active" : "") + "'>"
                            + "<span style='color:"
                                + (activeCovering.getPattern() == pattern
                                    ? PresetColour.GENERIC_GOOD.toWebHexString()
                                    : PresetColour.TRANSFORMATION_GENERIC.getShades()[0])
                            + ";'>"
                                + Util.capitaliseSentence(pattern.getName())
                            + "</span></div>");
        }
        sb.append("</div>");

        // Covering Modifiers:
        sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; " + border + "'>");
        sb.append("<p style='padding:0;margin:0;text-align:center;'>修饰：</p>");
        if (activeCovering.getType().getNaturalModifiers().size() + activeCovering.getType().getExtraModifiers().size() > 1) {
            for (CoveringModifier mod : activeCovering.getType().getNaturalModifiers()) {
                if (activeCovering.getModifier() == mod) {
                    sb.append(
                            "<div class='cosmetics-button active'>"
                                    + "<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(mod.getName()) + "</span>"
                                    + "</div>");
                } else {
                    sb.append(
                            "<div id='" + BodyCoveringType.getIdFromBodyCoveringType(coveringType) + "_MODIFIER_" + mod.getName() + "_" + getRaceTransformName(partType).replaceAll(" ", "_") + "' class='cosmetics-button'>"
                                    + "<span style='color:" + PresetColour.TRANSFORMATION_GENERIC.getShades()[0] + ";'>" + Util.capitaliseSentence(mod.getName()) + "</span>"
                                    + "</div>");
                }
            }
            for (CoveringModifier mod : activeCovering.getType().getExtraModifiers()) {
                if (activeCovering.getModifier() == mod) {
                    sb.append(
                            "<div class='cosmetics-button active'>"
                                    + "<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(mod.getName()) + "</span>"
                                    + "</div>");
                } else {
                    sb.append(
                            "<div id='" + BodyCoveringType.getIdFromBodyCoveringType(coveringType) + "_MODIFIER_" + mod.getName() + "_" + getRaceTransformName(partType).replaceAll(" ", "_") + "' class='cosmetics-button'>"
                                    + "<span style='color:" + PresetColour.TRANSFORMATION_GENERIC.getShades()[0] + ";'>" + Util.capitaliseSentence(mod.getName()) + "</span>"
                                    + "</div>");
                }
            }

        } else {
            sb.append("<p style='padding:0;margin:0;text-align:center;'>[style.italicsDisabled(无可用)]</p>");
        }
        sb.append("</div></div>");

        // Primary and secondary colours:
        sb.append("<div class='container-full-width' style='width:66.6%; padding:0; margin:0; text-align:center;'>");

        // Primary:
        sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; " + border + "'>");
        sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; background:transparent;'>");
        sb.append("主色");
        sb.append(" | <span style='color:" + activeCovering.getPrimaryColour().toWebHexString() + ";"
                + (activeCovering.isPrimaryGlowing()
                ? "text-shadow: 0px 0px 4px " + activeCovering.getPrimaryColour().getShades()[4] + ";"
                : "") + "'>"
                + Util.capitaliseSentence(activeCovering.getPrimaryColour().getName())
                + "</span></div>");
        for (Colour c : availablePrimaryColours) {
            if (c.getRainbowColours() != null) {
                rainbow = c.getRainbowDiv(5);
            } else {
                rainbow = "";
            }
            sb.append("<div class='normal-button" + (activeCovering.getPrimaryColour() == c ? " selected" : "") + "' id='" + BodyCoveringType.getIdFromBodyCoveringType(coveringType) + "_PRIMARY_" + c.getId() + "'"
                    + " style='width:auto; margin-right:4px;" + (activeCovering.getPrimaryColour() == c ? " background-color:" + PresetColour.BASE_GREEN.getShades()[4] + ";" : "") + "'>"
                    + (c.isMetallic()
                    ? "<div class='phone-item-colour' style='background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
                    : (c.getRainbowColours() != null
                    ? "<div class='phone-item-colour' style='background: " + rainbow
                    : "<div class='phone-item-colour' style='background" + (c == PresetColour.COVERING_CLEAR ? "-image" : "-color") + ":" + (c.getCoveringIconColour()) + ";"))
                    + (c == PresetColour.COVERING_NONE
                    ? " color:" + PresetColour.BASE_RED.toWebHexString() + ";'>X"
                    : "'>")
                    + "</div></div>");
        }

        if (activeCovering.getPrimaryColour() != PresetColour.COVERING_NONE && withGlow) { // Glow:
            if (activeCovering.isPrimaryGlowing()) {
                sb.append(
                        "<div class='normal-button active' id='" + BodyCoveringType.getIdFromBodyCoveringType(coveringType) + "_PRIMARY_GLOW_OFF" + "' style='width:50%; margin:1% 25%; padding:0; text-align:center;'>"
                                + "[style.boldArcane(奥术辉光)]</div>");
            } else {
                sb.append(
                        "<div id='" + BodyCoveringType.getIdFromBodyCoveringType(coveringType) + "_PRIMARY_GLOW_ON" + "_" + "' class='normal-button' style='width:50%; margin:1% 25%; padding:0; text-align:center;'>"
                                + "<span style='color:" + PresetColour.GENERIC_ARCANE.getShades()[0] + ";'>奥术辉光</span></div>");
            }
        }
        sb.append("</div>");

        // Secondary:
        sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; " + border + "'>");
        sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; background:transparent;'>");
        sb.append("副色");
        if (!secondaryDisabled) {
            sb.append(" | <span style='color:" + activeCovering.getSecondaryColour().toWebHexString() + ";"
                    + (activeCovering.isSecondaryGlowing()
                    ? "text-shadow: 0px 0px 4px " + activeCovering.getSecondaryColour().getShades()[4] + ";"
                    : "") + "'>"
                    + Util.capitaliseSentence(activeCovering.getSecondaryColour().getName())
                    + "</span>");
        }
        sb.append("</div>");
        if (secondaryDisabled) {
            sb.append("<p style='padding:0;margin:0;text-align:center;'>[style.italicsDisabled(无可用)]</p>");

        } else {
            for (Colour c : availableSecondaryColours) {
                if (c.getRainbowColours() != null) {
                    rainbow = c.getRainbowDiv(5);
                } else {
                    rainbow = "";
                }
                sb.append("<div class='normal-button" + (activeCovering.getSecondaryColour() == c ? " selected" : "") + "' id='" + BodyCoveringType.getIdFromBodyCoveringType(coveringType) + "_SECONDARY_" + c.getId() + "'"
                        + " style='width:auto; margin-right:4px;" + (activeCovering.getSecondaryColour() == c ? " background-color:" + PresetColour.BASE_GREEN.getShades()[4] + ";" : "") + "'>"
                        + (c.isMetallic()
                        ? "<div class='phone-item-colour' style='background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
                        : (c.getRainbowColours() != null
                        ? "<div class='phone-item-colour' style='background: " + rainbow
                        : "<div class='phone-item-colour' style='background" + (c == PresetColour.COVERING_CLEAR ? "-image" : "-color") + ":" + (c.getCoveringIconColour()) + ";"))
                        + (c == PresetColour.COVERING_NONE
                        ? " color:" + PresetColour.BASE_RED.toWebHexString() + ";'>X"
                        : "'>")
                        + "</div></div>");
            }
        }

        if (activeCovering.getSecondaryColour() != PresetColour.COVERING_NONE && withGlow && !secondaryDisabled) { // Glow:
            if (activeCovering.isSecondaryGlowing()) {
                sb.append(
                        "<div class='normal-button active' id='" + getPartName(partType) + "_" + BodyCoveringType.getIdFromBodyCoveringType(coveringType) + "_SECONDARY_GLOW_OFF" + "' style='width:50%; margin:1% 25%; padding:0; text-align:center;'>"
                                + "[style.boldArcane(奥术辉光)]</div>");
            } else {
                sb.append(
                        "<div id='" + getPartName(partType) + "_" + BodyCoveringType.getIdFromBodyCoveringType(coveringType) + "_SECONDARY_GLOW_ON" + "' class='normal-button' style='width:50%; margin:1% 25%; padding:0; text-align:center;'>"
                                + "<span style='color:" + PresetColour.GENERIC_ARCANE.getShades()[0] + ";'>奥术辉光</span></div>");
            }
        }
        sb.append("</div></div></div>");
        return sb.toString();
    }

    private static String getCosmeticDiv(AbstractBodyCoveringType coveringType, CoveringModifier mod, BodyPartInterface partType) {
        return "<div id='" + BodyCoveringType.getIdFromBodyCoveringType(coveringType) + "_MODIFIER_" + mod.getName() + "' class='cosmetics-button'>"
                + "<span style='color:" + PresetColour.TRANSFORMATION_GENERIC.getShades()[0] + ";'>" + Util.capitaliseSentence(mod.getName()) + "</span>"
                + "</div>";
    }

    public static void initCoveringChangeListeners(BodyPartInterface bodyPart) {
        String id;
        for (AbstractBodyCoveringType bct : BodyCoveringType.getAllBodyCoveringTypes()) {
            id = "APPLY_COVERING_" + BodyCoveringType.getIdFromBodyCoveringType(bct);
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
                        @Override
                        public void effects() {
                            if (CharacterModificationUtils.getCoveringsToBeApplied().containsKey(bct)) {
                                BodyChanging.getTarget().setSkinCovering(new Covering(CharacterModificationUtils.getCoveringsToBeApplied().get(bct)), false);

                                if (BodyChanging.getTarget().isPlayer() && bct == BodyCoveringType.HUMAN) { // Start of game should reset skin colourings for all parts
                                    BodyChanging.getTarget().getBody().updateCoverings(false, false, false, true);
                                }
                            }
                        }
                    });
                }, false);
            }

            id = "RESET_COVERING_" + BodyCoveringType.getIdFromBodyCoveringType(bct);
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
                        @Override
                        public void effects() {
                            CharacterModificationUtils.getCoveringsToBeApplied().remove(bct);
                        }
                    });
                }, false);
            }

            id = BodyCoveringType.getIdFromBodyCoveringType(bct) + "_PRIMARY_GLOW_OFF";
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
                        @Override
                        public void effects() {
                            CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
                            CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryGlowing(false);
                        }
                    });
                }, false);
            }

            id = BodyCoveringType.getIdFromBodyCoveringType(bct) + "_PRIMARY_GLOW_ON";
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
                        @Override
                        public void effects() {
                            CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
                            CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryGlowing(true);
                        }
                    });
                }, false);
            }

            id = BodyCoveringType.getIdFromBodyCoveringType(bct) + "_SECONDARY_GLOW_OFF";
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
                        @Override
                        public void effects() {
                            CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
                            CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryGlowing(false);
                        }
                    });
                }, false);
            }

            id = BodyCoveringType.getIdFromBodyCoveringType(bct) + "_SECONDARY_GLOW_ON";
            if (MainController.document.getElementById(id) != null) {
                ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                    Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
                        @Override
                        public void effects() {
                            CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
                            CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryGlowing(true);
                        }
                    });
                }, false);
            }

            for (CoveringPattern pattern : CoveringPattern.values()) {
                id = getPartName(bodyPart) + "_" + BodyCoveringType.getIdFromBodyCoveringType(bct) + "_PATTERN_" + pattern;
                if (MainController.document.getElementById(id) != null) {
                    ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                        Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
                            @Override
                            public void effects() {
                                CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
                                CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPattern(pattern);
                            }
                        });
                    }, false);
                }
            }

            for (CoveringModifier modifier : CoveringModifier.values()) {
                id = BodyCoveringType.getIdFromBodyCoveringType(bct) + "_MODIFIER_" + modifier.getName();
                if (MainController.document.getElementById(id) != null) {
                    ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                        Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
                            @Override
                            public void effects() {
                                CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
                                CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setModifier(modifier);
                            }
                        });
                    }, false);
                }
            }

        }
        if (!bodyPart.getType().isOneOf(VaginaType.NONE,
                PenisType.NONE, BreastType.NONE, TailType.NONE, HornType.NONE, WingType.NONE, TentacleType.NONE,
                AntennaType.NONE))
            initListenersByIds(UtilText.parse(coveringColorDiv(bodyPart.getType(), true)), bodyPart.getType());
    }


    /**
     @param bodyPart the compontent used to get the name
     @return the body part's common name, pre-capitalized
      * **/
    private static String getPartName(BodyPartInterface bodyPart) {
        return getPartName(bodyPart.getType());
    }

    /**
     @param bodyPart the compontent used to get the name
     @return the body part's common name, pre-capitalized
      * **/
    private static String getPartName(BodyPartTypeInterface bodyPart) {
        return switch (bodyPart) {
            case AbstractVaginaType ignored -> "Vagina";
            case AbstractPenisType ignored -> "Penis";
            case AbstractBreastType ignored -> "Breast";
            case AbstractArmType ignored -> "Arm";
            case AbstractLegType ignored -> "Leg";
            case AbstractAssType ignored -> "Ass";
            case AbstractTailType ignored -> "Tail";
            case AbstractEarType ignored -> "Ear";
            case AbstractEyeType ignored -> "Eye";
            case AbstractFaceType ignored -> "Face";
            case AbstractTorsoType ignored -> "Torso";
            case AbstractTesticleType ignored -> "Testicle";
            case AbstractHairType ignored -> "Hair";
            case AbstractHornType ignored -> "Horn";
            case AbstractTongueType ignored -> "Tongue";
            case AbstractNippleType ignored -> "Nipple";
            case AbstractMouthType ignored -> "Mouth";
            case AbstractAnusType ignored -> "Anus";
            case AbstractWingType ignored -> "Wing";
            case AbstractTentacleType ignored -> "Tentacle";
            case AbstractAntennaType ignored -> "Antenna";
            default -> "NONE";
        };
    }

    /**
     @param bodyPart this is the component of the body to get the racial name from
     @return String this string returned will often have a space included, it's recommend to replace with '_' when used for HTML elementID
     **/
    private static String getRaceTransformName(BodyPartTypeInterface bodyPart) {
        return switch (bodyPart) {
            case AbstractVaginaType type -> type.getTransformName();
            case AbstractPenisType type -> type.getTransformName();
            case AbstractBreastType type -> type.getTransformName();
            case AbstractArmType type -> type.getTransformName();
            case AbstractLegType type -> type.getTransformName();
            case AbstractAssType type -> type.getTransformName();
            case AbstractTailType type -> type.getTransformName();
            case AbstractEarType type -> type.getTransformName();
            case AbstractEyeType type -> type.getTransformName();
            case AbstractFaceType type -> type.getTransformName();
            case AbstractTorsoType type -> type.getTransformName();
            case AbstractTesticleType type -> type.getTransformName();
            case AbstractHairType type -> type.getTransformName();
            case AbstractHornType type -> type.getTransformName();
            case AbstractTongueType type -> type.getTransformName();
            case AbstractNippleType type -> type.getTransformName();
            case AbstractMouthType type -> type.getTransformName();
            case AbstractAnusType type -> type.getTransformName();
            case AbstractWingType type -> type.getTransformName();
            case AbstractTentacleType type -> type.getTransformName();
            case AbstractAntennaType type -> type.getTransformName();
            default -> "NONE";
        };
    }

    /**
     @param bodyPart the body part to get the list of types from
     @return the list of applicable body parts, returns empty if not valid.
     **/
    private static List<? extends BodyPartTypeInterface> getTypes(BodyPartInterface bodyPart) {
        return switch (bodyPart.getType()) {
            case AbstractVaginaType ignored -> VaginaType.getAllVaginaTypes();
            case AbstractPenisType ignored -> PenisType.getAllPenisTypes();
            case AbstractBreastType ignored -> BreastType.getAllBreastTypes();
            case AbstractArmType ignored -> ArmType.getAllArmTypes();
            case AbstractLegType ignored -> LegType.getAllLegTypes();
            case AbstractAssType ignored -> AssType.getAllAssTypes();
            case AbstractTailType ignored -> TailType.getAllTailTypes();
            case AbstractEarType ignored -> EarType.getAllEarTypes();
            case AbstractEyeType ignored -> EyeType.getAllEyeTypes();
            case AbstractFaceType ignored -> FaceType.getAllFaceTypes();
            case AbstractTorsoType ignored -> TorsoType.getAllTorsoTypes();
            case AbstractTesticleType ignored -> TesticleType.getAllTesticleTypes();
            case AbstractHairType ignored -> HairType.getAllHairTypes();
            case AbstractHornType ignored -> HornType.getAllHornTypes();
            case AbstractTongueType ignored -> TongueType.getAllTongueTypes();
            case AbstractNippleType ignored -> NippleType.getAllNippleTypes();
            case AbstractMouthType ignored -> MouthType.getAllMouthTypes();
            case AbstractAnusType ignored -> AnusType.getAllAnusTypes();
            case AbstractWingType ignored -> WingType.getAllWingTypes();
            case AbstractTentacleType ignored -> TentacleType.getAllTentacleTypes();
            case AbstractAntennaType ignored -> AntennaType.getAllAntennaTypes();
            default -> new ArrayList<>();
        };
    }
}