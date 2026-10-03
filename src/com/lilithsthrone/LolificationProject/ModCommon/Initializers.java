package com.lilithsthrone.LolificationProject.ModCommon;

import com.lilithsthrone.LolificationProject.Archer.sex.*;
import com.lilithsthrone.LolificationProject.ConfigStuff.LTOptions;
import com.lilithsthrone.LolificationProject.Experiments.NMS.NMSController;
import com.lilithsthrone.LolificationProject.Experiments.NMS.NPCModSys;
import com.lilithsthrone.LolificationProject.Lexi.TussyListeners;
import com.lilithsthrone.LolificationProject.Lexi.sex.*;
import com.lilithsthrone.LolificationProject.Overrides.CharacterCreationExtras;
import com.lilithsthrone.LolificationProject.sex.GoldenDrink;
import com.lilithsthrone.LolificationProject.sex.GoldenShower;
import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.main.Main;
import org.w3c.dom.events.EventTarget;

public class Initializers {

    /*
    * This handles the init methods for our stuff in MainController.java #manageMainListeners() prior to setResponseEventListeners();
    * Going to be moving most if not all interactions with innocode to these 1-liners, as for the function itself,
    *   eventually will replace DialogueNode with our own version, and build in the init method to it to just call node.init()
    */
    public static void handleInits(DialogueNode currentNode) {
        if (currentNode.equals(ModOptions.MOD_CONTENT_PREFERENCE))
            ModOptionsController.addModdedListeners();
        else if (currentNode.equals(ModOptions.MOD_BALANCE_PREFERENCES))
            ModOptionsController.addBalanceListeners();
        else if (currentNode.equals(ModOptions.MOD_BODY_PREFERENCES))
            ModOptionsController.addBodyModListeners();
        else if (currentNode.equals(ModOptions.MOD_CHALLENGE_PREFERENCES))
            ModOptionsController.addChallengeModListeners();
        else if (currentNode.equals(ModOptions.ADVANCED_OPTIONS))
            ModOptionsController.addAdvancedSettingsListeners();
        else if (currentNode.equals(VirginityMenu.VIRGIN_RATES_MENU))
            ModOptionsController.addVirginityListeners();
        else if (currentNode.equals(NPCModSys.NPC_MOD_SYS))
            NMSController.addNPCModSysListeners();
        else if (currentNode.equals(CharacterCreationExtras.CHOOSE_ADVANCED_APPEARANCE_RACE))
            CharacterCreationExtras.initRacialPanelListeners();
        else if (currentNode.equals(NPCSummoner.SUMMON_NPC))
            NPCSummoner.initSummonListeners();
        else if (currentNode.equals(OptionsDialogue.OPTIONS))
            LTOptions.initOptionsListeners();
        else if (currentNode.equals(Menus.BODY_CHANGING_TUSSY)) {
            TussyListeners.initTussyCapacityListeners();
            TussyListeners.initTussyDepthListeners();
            TussyListeners.initTussyElasticityListeners();
            TussyListeners.initTussyModifierListeners();
            TussyListeners.initTussyPlasticityListeners();
            TussyListeners.initTussyWetnessListeners();
        }
        else if (currentNode.equals(CharacterCreationExtras.CHOOSE_ADVANCED_APPEARANCE_GENITALS))
            CharacterCreationExtras.initGenitalListeners();
    }

    public static void initFluidAmountListeners() {

        // Fluid amount button listeners:
        String id = "AMOUNT_MINUS_ONE";
        if (((EventTarget) MainController.document.getElementById(id)) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                if(MilkingRoom.INGESTION_AMOUNT >= 6) {
                    MilkingRoom.INGESTION_AMOUNT = MilkingRoom.INGESTION_AMOUNT - 1;
                }
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }

        id = "AMOUNT_MINUS_TEN";
        if (((EventTarget) MainController.document.getElementById(id)) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                if(MilkingRoom.INGESTION_AMOUNT >= 16) {
                    MilkingRoom.INGESTION_AMOUNT = MilkingRoom.INGESTION_AMOUNT - 10;
                } else {
                    MilkingRoom.INGESTION_AMOUNT = 5;
                }
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }

        id = "AMOUNT_MINUS_HUNDRED";
        if (((EventTarget) MainController.document.getElementById(id)) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                if(MilkingRoom.INGESTION_AMOUNT >= 106) {
                    MilkingRoom.INGESTION_AMOUNT = MilkingRoom.INGESTION_AMOUNT - 100;
                } else {
                    MilkingRoom.INGESTION_AMOUNT = 5;
                }
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }

        id = "AMOUNT_MINUS_THOUSAND";
        if (((EventTarget) MainController.document.getElementById(id)) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                if(MilkingRoom.INGESTION_AMOUNT >= 1006) {
                    MilkingRoom.INGESTION_AMOUNT = MilkingRoom.INGESTION_AMOUNT - 1000;
                } else {
                    MilkingRoom.INGESTION_AMOUNT = 5;
                }
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }

        id = "AMOUNT_PLUS_ONE";
        if (((EventTarget) MainController.document.getElementById(id)) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                if(MilkingRoom.INGESTION_AMOUNT <= 4999) {
                    MilkingRoom.INGESTION_AMOUNT = MilkingRoom.INGESTION_AMOUNT + 1;
                }
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }

        id = "AMOUNT_PLUS_TEN";
        if (((EventTarget) MainController.document.getElementById(id)) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                if(MilkingRoom.INGESTION_AMOUNT <= 4990) {
                    MilkingRoom.INGESTION_AMOUNT = MilkingRoom.INGESTION_AMOUNT + 10;
                } else {
                    MilkingRoom.INGESTION_AMOUNT = 5000;
                }
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }

        id = "AMOUNT_PLUS_HUNDRED";
        if (((EventTarget) MainController.document.getElementById(id)) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                if(MilkingRoom.INGESTION_AMOUNT <= 4900) {
                    MilkingRoom.INGESTION_AMOUNT = MilkingRoom.INGESTION_AMOUNT + 100;
                } else {
                    MilkingRoom.INGESTION_AMOUNT = 5000;
                }
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }

        id = "AMOUNT_PLUS_THOUSAND";
        if (((EventTarget) MainController.document.getElementById(id)) != null) {
            ((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
                if(MilkingRoom.INGESTION_AMOUNT <= 4000) {
                    MilkingRoom.INGESTION_AMOUNT = MilkingRoom.INGESTION_AMOUNT + 1000;
                } else {
                    MilkingRoom.INGESTION_AMOUNT = 5000;
                }
                Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
            }, false);
        }
    }

    public static void initSexActions() {
        //Lolification Actions:
        if (ModProps.isPeeingEnabled()) {
            initPeeActions();
        }
        //Lexi Actions:
        if (ModProps.hasValue(PropertyValue.enableFrenchKiss)) {
            initFrenchKissActions();
        }

        SexActionPresets.tailAreas.add(SexAreaOrifice.TUSSY);
        SexActionPresets.lowerHalf.add(SexAreaOrifice.TUSSY);
        SexActionPresets.tailAndTentacle.add(SexAreaOrifice.TUSSY);
        SexActionPresets.allowedInterPenetrationAreas.add(SexAreaOrifice.TUSSY);
        SexActionPresets.groinAreas.add(SexAreaOrifice.TUSSY);
        SexActionPresets.appendageAreas.add(SexAreaOrifice.TUSSY);
        SexActionPresets.vaginaAreas.add(SexAreaOrifice.TUSSY);
        SexActionPresets.penisAreas.add(SexAreaOrifice.TUSSY);
        SexActionPresets.upperHalf.add(SexAreaOrifice.TUSSY);
        SexActionPresets.mouthAreas.add(SexAreaOrifice.TUSSY);

        if (ModProps.isTussyEnabled()) {
            initTussyActions();
        }

        if (ModProps.isExtraSelfContentEnabled()) {
            initExtraSelfActions();
        }
    }

    public static void initFrenchKissActions() {
        SexActionPresets.allCommonActions.add(FrenchKiss.class);
    }

    public static void removeFrenchKissActions() {
        SexActionPresets.allCommonActions.remove(FrenchKiss.class);
    }

    public static void initPeeActions() {
        SexActionPresets.allCommonActions.add(GoldenDrink.class);
        SexActionPresets.allCommonActions.add(GoldenShower.class);

    }

    public static void removePeeActions() {
        SexActionPresets.allCommonActions.remove(GoldenDrink.class);
        SexActionPresets.allCommonActions.remove(GoldenShower.class);
    }

    public static void initTussyActions() {
        SexActionPresets.allCommonActions.add(LexiPlayerTalk.class);
        SexActionPresets.allCommonActions.add(SelfTongueTussy.class);
        SexActionPresets.allCommonActions.add(SelfPenisTussy.class);
        SexActionPresets.allCommonActions.add(PenisTussy.class);
        SexActionPresets.allCommonActions.add(LexiPlayerTalk.class);
     //   SexActionPresets.sexActions.add(PussyClean.class);

    }

    public static void removeTussyActions() {
        SexActionPresets.allCommonActions.remove(SelfTongueTussy.class);
        SexActionPresets.allCommonActions.remove(SelfPenisTussy.class);
        SexActionPresets.allCommonActions.remove(PenisTussy.class);
        SexActionPresets.allCommonActions.remove(LexiPlayerTalk.class);
       // SexActionPresets.sexActions.remove(PussyClean.class);

    }

    public static void initExtraSelfActions() {
        SexActionPresets.allCommonActions.add(SelfPenisVagina.class);
        SexActionPresets.allCommonActions.add(SelfPenisAnus.class);
        SexActionPresets.allCommonActions.add(SelfPenisNipple.class);
        SexActionPresets.allCommonActions.add(SelfTongueAnus.class);
        SexActionPresets.allCommonActions.add(SelfTongueNipple.class);
        SexActionPresets.allCommonActions.add(SelfClitAnus.class);
        SexActionPresets.allCommonActions.add(SelfClitMouth.class);
        SexActionPresets.allCommonActions.add(SelfClitNipple.class);
        SexActionPresets.allCommonActions.add(SelfClitVagina.class);

    }

    public static void removeExtraSelfActions() {
        SexActionPresets.allCommonActions.remove(SelfPenisVagina.class);
        SexActionPresets.allCommonActions.remove(SelfPenisAnus.class);
        SexActionPresets.allCommonActions.remove(SelfPenisNipple.class);
        SexActionPresets.allCommonActions.remove(SelfTongueAnus.class);
        SexActionPresets.allCommonActions.remove(SelfTongueNipple.class);
        SexActionPresets.allCommonActions.remove(SelfClitAnus.class);
        SexActionPresets.allCommonActions.remove(SelfClitMouth.class);
        SexActionPresets.allCommonActions.remove(SelfClitNipple.class);
        SexActionPresets.allCommonActions.remove(SelfClitVagina.class);

    }
}
