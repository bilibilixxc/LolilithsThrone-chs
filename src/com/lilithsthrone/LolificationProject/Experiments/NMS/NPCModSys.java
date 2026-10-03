package com.lilithsthrone.LolificationProject.Experiments.NMS;

import com.lilithsthrone.LolificationProject.Overrides.MenuOverrides;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Amber;
import com.lilithsthrone.game.character.npc.dominion.Angel;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Ashley;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.Bunny;
import com.lilithsthrone.game.character.npc.dominion.Callie;
import com.lilithsthrone.game.character.npc.dominion.CandiReceptionist;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Elle;
import com.lilithsthrone.game.character.npc.dominion.Felicia;
import com.lilithsthrone.game.character.npc.dominion.Finch;
import com.lilithsthrone.game.character.npc.dominion.Hannah;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimbo;
import com.lilithsthrone.game.character.npc.dominion.HarpyBimboCompanion;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominant;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominantCompanion;
import com.lilithsthrone.game.character.npc.dominion.HarpyNympho;
import com.lilithsthrone.game.character.npc.dominion.HarpyNymphoCompanion;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Jules;
import com.lilithsthrone.game.character.npc.dominion.Kalahari;
import com.lilithsthrone.game.character.npc.dominion.Kate;
import com.lilithsthrone.game.character.npc.dominion.Kay;
import com.lilithsthrone.game.character.npc.dominion.Kruger;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Loppy;
import com.lilithsthrone.game.character.npc.dominion.Lumi;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.NyanMum;
import com.lilithsthrone.game.character.npc.dominion.Pazu;
import com.lilithsthrone.game.character.npc.dominion.Pix;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.character.npc.dominion.RentalMommy;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.dominion.Saellatrix;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.Sean;
import com.lilithsthrone.game.character.npc.dominion.SupplierLeader;
import com.lilithsthrone.game.character.npc.dominion.SupplierPartner;
import com.lilithsthrone.game.character.npc.dominion.Vanessa;
import com.lilithsthrone.game.character.npc.dominion.Vicky;
import com.lilithsthrone.game.character.npc.dominion.Wes;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKatherine;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.character.npc.fields.Angelixx;
import com.lilithsthrone.game.character.npc.fields.Arion;
import com.lilithsthrone.game.character.npc.fields.Astrapi;
import com.lilithsthrone.game.character.npc.fields.Aurokaris;
import com.lilithsthrone.game.character.npc.fields.Belle;
import com.lilithsthrone.game.character.npc.fields.Ceridwen;
import com.lilithsthrone.game.character.npc.fields.Dale;
import com.lilithsthrone.game.character.npc.fields.Daphne;
import com.lilithsthrone.game.character.npc.fields.Eisek;
import com.lilithsthrone.game.character.npc.fields.Evelyx;
import com.lilithsthrone.game.character.npc.fields.Fae;
import com.lilithsthrone.game.character.npc.fields.Farah;
import com.lilithsthrone.game.character.npc.fields.Flash;
import com.lilithsthrone.game.character.npc.fields.Ghost;
import com.lilithsthrone.game.character.npc.fields.Hale;
import com.lilithsthrone.game.character.npc.fields.Hammer;
import com.lilithsthrone.game.character.npc.fields.HeadlessHorseman;
import com.lilithsthrone.game.character.npc.fields.Heather;
import com.lilithsthrone.game.character.npc.fields.Imsu;
import com.lilithsthrone.game.character.npc.fields.Jess;
import com.lilithsthrone.game.character.npc.fields.Kazik;
import com.lilithsthrone.game.character.npc.fields.Kheiron;
import com.lilithsthrone.game.character.npc.fields.Lunexis;
import com.lilithsthrone.game.character.npc.fields.Minotallys;
import com.lilithsthrone.game.character.npc.fields.Monica;
import com.lilithsthrone.game.character.npc.fields.Moreno;
import com.lilithsthrone.game.character.npc.fields.Nir;
import com.lilithsthrone.game.character.npc.fields.Nizhoni;
import com.lilithsthrone.game.character.npc.fields.Oglix;
import com.lilithsthrone.game.character.npc.fields.Penelope;
import com.lilithsthrone.game.character.npc.fields.Silvia;
import com.lilithsthrone.game.character.npc.fields.Sleip;
import com.lilithsthrone.game.character.npc.fields.Sterope;
import com.lilithsthrone.game.character.npc.fields.Ursa;
import com.lilithsthrone.game.character.npc.fields.Vronti;
import com.lilithsthrone.game.character.npc.fields.Wynter;
import com.lilithsthrone.game.character.npc.fields.Yui;
import com.lilithsthrone.game.character.npc.fields.Ziva;
import com.lilithsthrone.game.character.npc.submission.Axel;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.Epona;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.character.npc.submission.FortressFemalesLeader;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.character.npc.submission.HazmatRat;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.npc.submission.Roxy;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardFire;
import com.lilithsthrone.game.character.npc.submission.SlimeGuardIce;
import com.lilithsthrone.game.character.npc.submission.SlimeQueen;
import com.lilithsthrone.game.character.npc.submission.SlimeRoyalGuard;
import com.lilithsthrone.game.character.npc.submission.Takahashi;
import com.lilithsthrone.game.character.npc.submission.Vengar;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.lilithsthrone.LolificationProject.Experiments.NMS.NMSUI.buildLeftSide;

/* Friendly Note : NMS is to be pronounced Nimis */
public class NPCModSys {
    static Map<String, Integer> heightMap = new HashMap<>();
    public static final HashMap<LocationType, ArrayList<Class<? extends NPC>>> allNPCs = new HashMap<>();
    static {
        allNPCs.put(LocationType.DOMINION, new ArrayList<>(Arrays.asList(
                Amber.class, Arthur.class, Felicia.class, Kruger.class, Lilaya.class, Rose.class,
                Vanessa.class, Zaranix.class, ZaranixMaidKatherine.class, ZaranixMaidKelly.class
        )));
        allNPCs.put(LocationType.SUBMISSION, new ArrayList<>(Arrays.asList(
                Axel.class, DarkSiren.class, Elizabeth.class, Epona.class, FortressAlphaLeader.class, FortressFemalesLeader.class, FortressMalesLeader.class,
                HazmatRat.class, Lyssieth.class, Murk.class, Roxy.class, Shadow.class, Silence.class, SlimeGuardIce.class, SlimeGuardFire.class,
                SlimeQueen.class, SlimeRoyalGuard.class, Takahashi.class, Vengar.class
        )));
        allNPCs.put(LocationType.ELIS, new ArrayList<>(Arrays.asList(
                Arion.class, Belle.class, Daphne.class, Eisek.class, Fae.class, Farah.class, Flash.class, Hale.class, Heather.class,
                Imsu.class, Jess.class, Kazik.class, Kheiron.class, Minotallys.class, Monica.class, Moreno.class, Nizhoni.class, Oglix.class,
                Penelope.class, Silvia.class, Wynter.class, Yui.class, Ziva.class
        )));
        allNPCs.put(LocationType.THEMISCYRA, new ArrayList<>(Arrays.asList(
                Angelixx.class, Aurokaris.class, Nir.class, Sleip.class, Ursa.class
        )));
        allNPCs.put(LocationType.FIELDS, new ArrayList<>(Arrays.asList(
                Astrapi.class, Ceridwen.class, Dale.class, Evelyx.class, Lunexis.class,
                HeadlessHorseman.class, Sterope.class, Vronti.class
        )));
        allNPCs.put(LocationType.SHOPS, new ArrayList<>(Arrays.asList(
                Ashley.class, Callie.class, Finch.class, Kate.class, Nyan.class, Ralph.class, Saellatrix.class, Vicky.class
        )));
        allNPCs.put(LocationType.HARPIES, new ArrayList<>(Arrays.asList(
                HarpyBimbo.class, HarpyDominant.class, HarpyNympho.class, HarpyBimboCompanion.class, HarpyDominantCompanion.class, HarpyNymphoCompanion.class,
                Helena.class, Pazu.class, Scarlett.class
        )));
        allNPCs.put(LocationType.ENFORCERS, new ArrayList<>(Arrays.asList(
                Brax.class, CandiReceptionist.class, Elle.class, Sean.class, Claire.class, Ghost.class, Hammer.class
        )));
        allNPCs.put(LocationType.DOMINION_EXTRAS, new ArrayList<>(Arrays.asList(
                Angel.class, Bunny.class, Daddy.class, Hannah.class, Jules.class, Kalahari.class, Kay.class, Loppy.class, Lumi.class, Natalya.class,
                NyanMum.class, Pix.class, RentalMommy.class, SupplierLeader.class, SupplierPartner.class, Wes.class
        )));
    }

    public static final DialogueNode NPC_MOD_SYS = new DialogueNode("NPC Mod System", "", true) {
        @Override
        public String getContent() {
            UtilText.nodeContentSB.setLength(0);
            buildLeftSide();
            UtilText.nodeContentSB.append("<div id='NPCModSheet' style='width:100%;height:70%;'><p style='color: #3CC;'>NPC Modding options appear here once target is selected.</p></div>");
            return UtilText.nodeContentSB.toString();
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            if (index == 0) {
                return new Response("Back", "Go back to the options menu.", MenuOverrides.MENU) {
                    @Override
                    public void effects() {
                        Main.mainController.setAttributePanelContent("");
                        Main.mainController.setRightPanelContent("");
                        NMSController.save();
                    }
                };
            } else if (index == 1) {
                return new ResponseEffectsOnly("Randomize Height", "Ranomize height for all Unique NPCs</br>Note: Limits are 132cm to 200cm") {
                    @Override
                    public void effects() {
                        NMSController.massRandomInteger("height");
                    }
                };
            } else if (index == 2) {
                return new ResponseEffectsOnly("Randomize Genitals", "Randomizes genital arrangement") {
                    @Override
                    public void effects() {
                        NMSController.massRandomBoolean("hasPenis");
                        NMSController.massRandomBoolean("hasVagina");
                    }
                };
            } else if (index == 3) {
                return new ResponseEffectsOnly("Randomize Femininity", "Randomly set all Femininity values</br>CURRENTLY DOES NOTHING") {
                    @Override
                    public void effects() {
//                        NMSController.massRandomInteger("femininity"); // Commented out because Feminity is not adjustible with NMS yet
                    }
                };
            } else if (index == 6) {
                return new ResponseEffectsOnly("Shrimpify", "Make 'em all short") {
                    @Override
                    public void effects() {
                        NMSController.massAdjustInteger("height", 0.7f);
                    }
                };
            } else if (index == 7) {
                return new ResponseEffectsOnly("QuikGro", "Gotta rename this later...") {
                    @Override
                    public void effects() {
                        NMSController.massAdjustInteger("height", 1.15f);
                    }
                };
            } else if (index == 11) {
                return new ResponseEffectsOnly("Venus", "No dicks allowed") {
                    @Override
                    public void effects() {
                        NMSController.massRandomIntegerWithBounds("femininity", 65, 100);
                        NMSController.massRandomIntegerWithBounds("height", 140, 180);
                        NMSController.massAdjustBoolean("hasPenis", false);
                        NMSController.massAdjustBoolean("hasVagina", true);
                    }
                };
            } else if (index == 12) {
                return new ResponseEffectsOnly("Mars", "Manly men only") {
                    @Override
                    public void effects() {
                        NMSController.massRandomIntegerWithBounds("femininity", 0, 35);
                        NMSController.massRandomIntegerWithBounds("height", 160, 200);
                        NMSController.massAdjustBoolean("hasPenis", true);
                        NMSController.massAdjustBoolean("hasVagina", false);
                    }
                };
            } else if (index == 13) {
                return new ResponseEffectsOnly("Trapify", "ALL THE TRAPS") {
                    @Override
                    public void effects() {
                        NMSController.massRandomIntegerWithBounds("femininity", 45, 80);
                        NMSController.massRandomIntegerWithBounds("height", 132, 160);
                        NMSController.massAdjustBoolean("hasPenis", true);
                        NMSController.massAdjustBoolean("hasVagina", false);
                    }
                };
            }/* else if (index == 12 && DRAKE_MODE) { // FAETODO Trapify, without affecting Nyan and select other favorites
                return new ResponseEffectsOnly("Trapify (Selective)", "ALL THE TRAPS (well, mostly)") {
                    @Override
                    public void effects() {
                        NMSController.massRandomIntegerWithBounds("femininity", 45, 80);
                        NMSController.massRandomIntegerWithBounds("height", 132, 160);
                        NMSController.massAdjustBoolean("hasPenis", true);
                        NMSController.massAdjustBoolean("hasVagina", false);
                    }
                };
            }*/
            return null;
        }

        @Override
        public DialogueNodeType getDialogueNodeType() {
            return DialogueNodeType.OPTIONS;
        }
    };
    public static volatile NPC currentNPCRequestingNMO = null;
    public static NPC applyModifications(NPC npc) {
        currentNPCRequestingNMO = npc;
        NMSOptions nmo = NMSController.getNMO(npc.getClass());
        if (nmo != null)
            npc = nmo.applyChanges(npc);
        return npc;
    }
}