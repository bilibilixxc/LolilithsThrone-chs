package com.lilithsthrone.game.character.npc;

import com.lilithsthrone.LolificationProject.Lexi.character.npc.fields.Cecilia;
import com.lilithsthrone.LolificationProject.Library;
import com.lilithsthrone.LolificationProject.ModCommon.Logger;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.dominion.*;
import com.lilithsthrone.game.character.npc.fields.*;
import com.lilithsthrone.game.character.npc.misc.*;
import com.lilithsthrone.game.character.npc.submission.*;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static com.lilithsthrone.game.Game.loadingVersion;

/**
 * @since 0.4.10.7
 * @version 0.4.11.6
 * @author Innoxia, KeldonSlayer (DrZed)
 */
public class NPCRegistry {
    private static final boolean TIME_TESTING = false;
    private static HashMap<Class<? extends NPC>, CompletableFuture<NPC>> futures = new HashMap<>();
    private static final ArrayList<Class<? extends NPC>> allSimpleNPCClasses = new ArrayList<>(Arrays.asList(
            GenericMaleNPC.class, GenericFemaleNPC.class, GenericAndrogynousNPC.class, PrologueMale.class, PrologueFemale.class, GenericTrader.class, TestNPC.class,
            Lumi.class, Pazu.class, Ashley.class, Callie.class, Rose.class, Lilaya.class, Arthur.class, Angel.class, Bunny.class, Loppy.class, Jules.class,
            Kruger.class, Kalahari.class, Brax.class, CandiReceptionist.class, Wes.class, Elle.class, Sterope.class, Hammer.class, Angelixx.class, Sleip.class,
            Nir.class, Scarlett.class, Helena.class, HarpyBimbo.class, HarpyBimboCompanion.class, HarpyDominant.class, HarpyDominantCompanion.class,
            HarpyNympho.class, HarpyNymphoCompanion.class, Vanessa.class, Natalya.class, Finch.class, Sean.class, RentalMommy.class, Daddy.class, Saellatrix.class,
            Fiammetta.class, HeadlessHorseman.class, Lunette.class, Lovienne.class, Lyssieth.class, Elizabeth.class, Takahashi.class, DarkSiren.class, Roxy.class,
            Axel.class, Epona.class, Vengar.class, Shadow.class, Silence.class, Murk.class, HazmatRat.class, Felicia.class, Zaranix.class, Amber.class,
            ZaranixMaidKatherine.class, ZaranixMaidKelly.class, Claire.class, FortressAlphaLeader.class,  FortressFemalesLeader.class, FortressMalesLeader.class,
            SlimeQueen.class, SlimeGuardIce.class, SlimeGuardFire.class, SlimeRoyalGuard.class, Fae.class, Silvia.class, Kazik.class, Yui.class, Nizhoni.class,
            Moreno.class, Heather.class, Ziva.class, Eisek.class, Monica.class, Ceridwen.class, Imsu.class, Hale.class, Penelope.class, Belle.class, Daphne.class,
            Farah.class, Ralph.class, Nyan.class, NyanMum.class, Vicky.class, Pix.class, Hannah.class, Kate.class, SupplierLeader.class, SupplierPartner.class,
            Kay.class, Flash.class, Jess.class, Astrapi.class, Vronti.class, Kheiron.class, Arion.class, Minotallys.class, Evelyx.class, Dale.class, Lunexis.class,
            Ursa.class, Aurokaris.class, Oglix.class, Wynter.class, Ghost.class));
    public static void initUniqueNPCs(boolean skipRelationships) {
        long timeStarted = System.nanoTime();

        allSimpleNPCClasses.add(Cecilia.class);// Left this in as an option to add to the threaded preloading
        allSimpleNPCClasses.add(Shiranui.class);// Left this in as an option to add to the threaded preloading

        buildNPCThreadPool();
        initMiscNPCs();
        initDominionNPCs();
        initEnforcerNPCs();
        initShoppingNPCs();
        initHarpyNestNPCs();
        initAssortedNPCs();
        initSpecialLocationNPCs();
        initFieldsNPCs();
        initElisNPCs();
        initFarmersMarketNPCs();
        initWallsEndNPCs();
        initSubmissionNPCs();

        // We skip on loading save to prevent overwriting player relationship data
        if (skipRelationships) {
            addMissingRelationships();
        } else {
            initRelations(Main.game.getNPCMap().keySet());
        }

        if (TIME_TESTING)
            Logger.logTime("initUniqueNPCs took", System.nanoTime() - timeStarted);
    }

    private static void addMissingRelationships() {
        if(Main.isVersionOlderThan(loadingVersion, "0.3.5.6")) {
            Main.game.getNpc(Roxy.class).setAffection(Main.game.getNpc(Vengar.class), -80);
            Main.game.getNpc(Vengar.class).setAffection(Main.game.getNpc(Roxy.class), 50);
        }
        if(Main.isVersionOlderThan(loadingVersion, "0.3.5.9")) {
            Main.game.getNpc(Silence.class).setAffection(Main.game.getNpc(Shadow.class), 100);
            Main.game.getNpc(Silence.class).getAffectionMap().remove(Main.game.getNpc(Silence.class).getId());
        }
    }

    public static void initMiscNPCs() {
        long timeStarted = System.nanoTime();
        // Misc.:
        addIfMissingSafely(GenericMaleNPC.class);
        addIfMissingSafely(GenericFemaleNPC.class);
        addIfMissingSafely(GenericAndrogynousNPC.class);
        addIfMissingSafely(PrologueMale.class);
        addIfMissingSafely(PrologueFemale.class);
        addIfMissingSafely(GenericTrader.class);
        addIfMissingSafely(TestNPC.class);

        // Contributors:
        addIfMissingSafely(Lumi.class);
        addIfMissingSafely(Pazu.class);
        addIfMissingSafely(Ashley.class);
        addIfMissingSafely(Callie.class);

        if (TIME_TESTING)
            Logger.logTime("initMiscNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initDominionNPCs() {
        long timeStarted = System.nanoTime();
        // Story:
        addIfMissingSafely(Rose.class);
        addIfMissingSafely(Lilaya.class);
        addIfMissingSafely(Arthur.class);

        // Angel's kiss:
        addIfMissingSafely(Angel.class);
        addIfMissingSafely(Bunny.class);
        addIfMissingSafely(Loppy.class);

        // Nightclub:
        addIfMissingSafely(Jules.class);
        addIfMissingSafely(Kruger.class);
        addIfMissingSafely(Kalahari.class);


        if (TIME_TESTING)
            Logger.logTime("initDominionNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initEnforcerNPCs() {
        long timeStarted = System.nanoTime();
        // Enforcers:
        addIfMissingSafely(Brax.class);
        addIfMissingSafely(CandiReceptionist.class);
        addIfMissingSafely(Wes.class);
        addIfMissingSafely(Elle.class);

        // Enforcer station:
        addIfMissingSafely(Sterope.class);
        addIfMissingSafely(Hammer.class);
        addIfMissingSafely(Angelixx.class);
        addIfMissingSafely(Sleip.class);
        addIfMissingSafely(Nir.class);

        if (TIME_TESTING)
            Logger.logTime("initEnforcerNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initHarpyNestNPCs() {
        long timeStarted = System.nanoTime();
        // Harpy nests:
        addIfMissingSafely(Scarlett.class);
        addIfMissingSafely(Helena.class);
        addIfMissingSafely(HarpyBimbo.class);
        addIfMissingSafely(HarpyBimboCompanion.class);
        addIfMissingSafely(HarpyDominant.class);
        addIfMissingSafely(HarpyDominantCompanion.class);
        addIfMissingSafely(HarpyNympho.class);
        addIfMissingSafely(HarpyNymphoCompanion.class);

        if (TIME_TESTING)
            Logger.logTime("initHarpyNestNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initAssortedNPCs() {
        long timeStarted = System.nanoTime();
        // City hall:
        addIfMissingSafely(Vanessa.class);

        // Dominion Express:
        addIfMissingSafely(Natalya.class);

        // Slaver alley:
        addIfMissingSafely(Finch.class);
        addIfMissingSafely(Sean.class);

        // Rental mommy;
        addIfMissingSafely(RentalMommy.class);

        // 'Daddy':
        addIfMissingSafely(Daddy.class);

        // Lovienne's Luxuries:
        addIfMissingSafely(Saellatrix.class);
        addIfMissingSafely(Fiammetta.class);

        // Headless horseman:
        addIfMissingSafely(HeadlessHorseman.class);

        // Elder lilin:
        addIfMissingSafely(Lunette.class);
        addIfMissingSafely(Lovienne.class);

        if (TIME_TESTING)
            Logger.logTime("initAssortedNPCs took", System.nanoTime() - timeStarted);
    }


    public static void initSubmissionNPCs() {
        long timeStarted = System.nanoTime();
        // Submission:

        // Story
        addIfMissingSafely(Lyssieth.class);
        addIfMissingSafely(Elizabeth.class);
        addIfMissingSafely(Takahashi.class);
        addIfMissingSafely(DarkSiren.class);

        // Gambling den:
        addIfMissingSafely(Roxy.class);
        addIfMissingSafely(Axel.class);
        addIfMissingSafely(Epona.class);

        // Rat Warrens:
        addIfMissingSafely(Vengar.class);
        addIfMissingSafely(Shadow.class);
        addIfMissingSafely(Silence.class);
        addIfMissingSafely(Murk.class);

        // Hazmat Rat:
        addIfMissingSafely(HazmatRat.class);

        if (TIME_TESTING)
            Logger.logTime("initSubmissionNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initSpecialLocationNPCs() {
        long timeStarted = System.nanoTime();
        // Sawlty Towers (Arthur/Felicia's apartment building):
        addIfMissingSafely(Felicia.class);

        // Zaranix's home:
        addIfMissingSafely(Zaranix.class);
        addIfMissingSafely(Amber.class);
        addIfMissingSafely(ZaranixMaidKatherine.class);
        addIfMissingSafely(ZaranixMaidKelly.class);

        // Fortress
        addIfMissingSafely(Claire.class);
        addIfMissingSafely(FortressAlphaLeader.class);
        addIfMissingSafely(FortressFemalesLeader.class);
        addIfMissingSafely(FortressMalesLeader.class);

        // Slime queen:
        addIfMissingSafely(SlimeQueen.class);
        addIfMissingSafely(SlimeGuardIce.class);
        addIfMissingSafely(SlimeGuardFire.class);
        addIfMissingSafely(SlimeRoyalGuard.class);

        if (TIME_TESTING)
            Logger.logTime("initSpecialLocationNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initFarmersMarketNPCs() {
        long timeStarted = System.nanoTime();
        // Farmer's Market:
        addIfMissingSafely(Fae.class);
        addIfMissingSafely(Silvia.class);
        addIfMissingSafely(Kazik.class);
        addIfMissingSafely(Yui.class);
        addIfMissingSafely(Nizhoni.class);
        addIfMissingSafely(Moreno.class);
        addIfMissingSafely(Heather.class);
        addIfMissingSafely(Ziva.class);
        addIfMissingSafely(Eisek.class);

        if (TIME_TESTING)
            Logger.logTime("initFarmersMarketNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initWallsEndNPCs() {
        long timeStarted = System.nanoTime();
        // Wall's End:
        addIfMissingSafely(Monica.class);
        addIfMissingSafely(Ceridwen.class);
        addIfMissingSafely(Imsu.class);
        addIfMissingSafely(Hale.class);
        addIfMissingSafely(Penelope.class);
        addIfMissingSafely(Belle.class);
        addIfMissingSafely(Daphne.class);
        addIfMissingSafely(Farah.class);

        if (TIME_TESTING)
            Logger.logTime("initWallsEndNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initShoppingNPCs() {
        long timeStarted = System.nanoTime();
        // Shopping Promenade:
        addIfMissingSafely(Ralph.class);
        addIfMissingSafely(Nyan.class);
        addIfMissingSafely(NyanMum.class);
        addIfMissingSafely(Vicky.class);
        addIfMissingSafely(Pix.class);
        addIfMissingSafely(Hannah.class);
        addIfMissingSafely(Kate.class);
        addIfMissingSafely(SupplierLeader.class);
        addIfMissingSafely(SupplierPartner.class);
        addIfMissingSafely(Kay.class);

        if (TIME_TESTING)
            Logger.logTime("initShoppingNPCs took", System.nanoTime() - timeStarted);
    }

    /*
     *          Elis
     */
    public static void initElisNPCs() {
        long timeStarted = System.nanoTime();
        // The Red Dragon:
        addIfMissingSafely(Flash.class);
        addIfMissingSafely(Jess.class);

        // Astrapi/Vronti/Kheiron:
        addIfMissingSafely(Astrapi.class);
        addIfMissingSafely(Vronti.class);
        addIfMissingSafely(Kheiron.class);

        // Minotallys/Arion
        addIfMissingSafely(Arion.class);
        addIfMissingSafely(Minotallys.class);

        if (TIME_TESTING)
            Logger.logTime("initElisNPCs took", System.nanoTime() - timeStarted);
    }

    public static void initFieldsNPCs() {
        long timeStarted = System.nanoTime();
        // Evelyx's Dairy:
        addIfMissingSafely(Evelyx.class);
        addIfMissingSafely(Dale.class);

        // Themiscyra:
        addIfMissingSafely(Lunexis.class);
        addIfMissingSafely(Ursa.class);
        addIfMissingSafely(Aurokaris.class);

        // The Crossed Blades:
        addIfMissingSafely(Oglix.class);
        NPC oglix = Main.game.getNpc(Oglix.class);
        addIfMissingSafely(Golix.class, Gender.F_P_B_SHEMALE, oglix);
        addIfMissingSafely(Wynter.class);

        addIfMissingSafely(Shiranui.class);
        addIfMissingSafely(Ghost.class);

        addIfMissingSafely(Cecilia.class);

        if (TIME_TESTING)
            Logger.logTime("initFieldsNPCs took", System.nanoTime() - timeStarted);
    }


    /**
     * Handles setting up Relationships after registering all the npcs
     * */
    public static void initRelations(Set<String> addedNpcs) {

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Lilaya.class))) {
            Main.game.getNpc(Lilaya.class).setAffection(Main.game.getPlayer(), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
            Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(Rose.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Lilaya.class).addSlave(Main.game.getNpc(Rose.class));

            Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(Lyssieth.class), -60);
            Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(DarkSiren.class), 15);
            Main.game.getNpc(Lilaya.class).setMother(Main.game.getNpc(Lyssieth.class));
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Rose.class))) {
            Main.game.getNpc(Rose.class).setAffection(Main.game.getPlayer(), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
            Main.game.getNpc(Rose.class).setAffection(Main.game.getNpc(Lilaya.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Rose.class).setObedience(ObedienceLevel.POSITIVE_FIVE_SUBSERVIENT.getMedianValue());
            Main.game.getNpc(Rose.class).setAffection(Main.game.getNpc(Lyssieth.class), -40);
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(DarkSiren.class))) {
            Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getNpc(Lyssieth.class), -25);
            Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getNpc(Lilaya.class), 35);
            Main.game.getNpc(DarkSiren.class).setMother(Main.game.getNpc(Lyssieth.class));
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Elizabeth.class))) {
            Main.game.getNpc(Elizabeth.class).setMother(Main.game.getNpc(Lyssieth.class));
            Main.game.getNpc(Elizabeth.class).setAffection(Main.game.getNpc(Lyssieth.class), 100);
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Lyssieth.class))) {
            Main.game.getNpc(Lyssieth.class).setAffection(Main.game.getNpc(Lilaya.class), 100);
            Main.game.getNpc(Lyssieth.class).setAffection(Main.game.getNpc(DarkSiren.class), 50);
            Main.game.getNpc(Lyssieth.class).setAffection(Main.game.getNpc(Elizabeth.class), 75);
            Main.game.getNpc(Lyssieth.class).setAffection(Main.game.getNpc(Rose.class), -80);
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Brax.class))) {
            Main.game.getNpc(Brax.class).setAffection(Main.game.getNpc(CandiReceptionist.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(CandiReceptionist.class))) {
            Main.game.getNpc(CandiReceptionist.class).setAffection(Main.game.getNpc(Brax.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Wes.class))) {
            Main.game.getNpc(Wes.class).setAffection(Main.game.getNpc(Elle.class), AffectionLevel.NEGATIVE_THREE_STRONG_DISLIKE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Elle.class))) {
            Main.game.getNpc(Elle.class).setAffection(Main.game.getNpc(Wes.class), AffectionLevel.NEGATIVE_ONE_ANNOYED.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Pix.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Hannah.class))) {
            Main.game.getNpc(Pix.class).setAffection(Main.game.getNpc(Hannah.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(Hannah.class).setAffection(Main.game.getNpc(Pix.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Nyan.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(NyanMum.class))) {
            Main.game.getNpc(Nyan.class).setAffection(Main.game.getNpc(NyanMum.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(NyanMum.class).setAffection(Main.game.getNpc(Nyan.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());

            Main.game.getNpc(Nyan.class).setAffection(Main.game.getNpc(Ashley.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
            Main.game.getNpc(Ashley.class).setAffection(Main.game.getNpc(Nyan.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(SupplierLeader.class))) {
            Main.game.getNpc(SupplierLeader.class).setAffection(Main.game.getNpc(SupplierPartner.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
            Main.game.getNpc(SupplierPartner.class).setAffection(Main.game.getNpc(SupplierLeader.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Kay.class))) {
            Main.game.getNpc(Nyan.class).setAffection(Main.game.getNpc(Kay.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
            Main.game.getNpc(Kay.class).setAffection(Main.game.getNpc(Nyan.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Scarlett.class))) {
            Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), AffectionLevel.NEGATIVE_TWO_DISLIKE.getMedianValue());
            Main.game.getNpc(Scarlett.class).setAffection(Main.game.getNpc(Helena.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            if(Main.game.getPlayer().hasQuest(QuestLine.MAIN) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) {
                Main.game.getNpc(Scarlett.class).setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
            }// Should this be in "addMissingRelationships()" which happens on loading save, instead of new game?
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Helena.class))) {
            Main.game.getNpc(Helena.class).setAffection(Main.game.getNpc(Scarlett.class), AffectionLevel.NEGATIVE_FOUR_HATE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(HarpyBimbo.class))) {
            Main.game.getNpc(HarpyBimbo.class).setAffection(Main.game.getNpc(HarpyBimboCompanion.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(HarpyBimboCompanion.class))) {
            Main.game.getNpc(HarpyBimboCompanion.class).setAffection(Main.game.getNpc(HarpyBimbo.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(HarpyDominant.class))) {
            Main.game.getNpc(HarpyDominant.class).setAffection(Main.game.getNpc(HarpyDominantCompanion.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(HarpyDominantCompanion.class))) {
            Main.game.getNpc(HarpyDominantCompanion.class).setAffection(Main.game.getNpc(HarpyDominant.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(HarpyNympho.class))) {
            Main.game.getNpc(HarpyNympho.class).setAffection(Main.game.getNpc(HarpyNymphoCompanion.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(HarpyNymphoCompanion.class))) {
            Main.game.getNpc(HarpyNymphoCompanion.class).setAffection(Main.game.getNpc(HarpyNympho.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Sean.class))) {
            Main.game.getNpc(Brax.class).setPetName(Main.game.getNpc(Sean.class), Main.game.getNpc(Sean.class).getName(false));
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Daddy.class))) {
            Main.game.getNpc(Rose.class).setAffection(Main.game.getNpc(Daddy.class), -50);
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Felicia.class))) {
            Main.game.getNpc(Felicia.class).setAffection(Main.game.getNpc(Arthur.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Fiammetta.class))) {
            Main.game.getNpc(Fiammetta.class).setAffection(Main.game.getNpc(Saellatrix.class), -100);
            Main.game.getNpc(Saellatrix.class).setAffection(Main.game.getNpc(Fiammetta.class), -50);
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Zaranix.class))) {
            Main.game.getNpc(Zaranix.class).setAffection(Main.game.getNpc(ZaranixMaidKatherine.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(Zaranix.class).setAffection(Main.game.getNpc(ZaranixMaidKelly.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(Zaranix.class).setAffection(Main.game.getNpc(Amber.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Amber.class))) {
            Main.game.getNpc(Amber.class).setAffection(Main.game.getNpc(Zaranix.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Amber.class).setAffection(Main.game.getNpc(ZaranixMaidKelly.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(Amber.class).setAffection(Main.game.getNpc(ZaranixMaidKatherine.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(ZaranixMaidKatherine.class))) {
            Main.game.getNpc(ZaranixMaidKatherine.class).setAffection(Main.game.getNpc(Zaranix.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(ZaranixMaidKatherine.class).setAffection(Main.game.getNpc(ZaranixMaidKelly.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(ZaranixMaidKatherine.class).setAffection(Main.game.getNpc(Amber.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(ZaranixMaidKelly.class))) {
            Main.game.getNpc(ZaranixMaidKelly.class).setAffection(Main.game.getNpc(Zaranix.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(ZaranixMaidKelly.class).setAffection(Main.game.getNpc(ZaranixMaidKatherine.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(ZaranixMaidKelly.class).setAffection(Main.game.getNpc(Amber.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Vengar.class))) {
            Main.game.getNpc(Vengar.class).setAffection(Main.game.getNpc(Shadow.class), 50);
            Main.game.getNpc(Vengar.class).setAffection(Main.game.getNpc(Silence.class), 50);
            Main.game.getNpc(Shadow.class).setAffection(Main.game.getNpc(Vengar.class), -10);
            Main.game.getNpc(Shadow.class).setAffection(Main.game.getNpc(Silence.class), 80);
            Main.game.getNpc(Silence.class).setAffection(Main.game.getNpc(Vengar.class), 20);
            Main.game.getNpc(Silence.class).setAffection(Main.game.getNpc(Shadow.class), 100);
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Flash.class)) && addedNpcs.contains(Main.game.getUniqueNPCId(Jess.class))) {
            Main.game.getNpc(Jess.class).setAffection(Main.game.getNpc(Flash.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Flash.class).setAffection(Main.game.getNpc(Jess.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Astrapi.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Vronti.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Kheiron.class))) {
            Main.game.getNpc(Astrapi.class).setAffection(Main.game.getNpc(Vronti.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Astrapi.class).setAffection(Main.game.getNpc(Kheiron.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());

            Main.game.getNpc(Vronti.class).setAffection(Main.game.getNpc(Astrapi.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Vronti.class).setAffection(Main.game.getNpc(Kheiron.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());

            Main.game.getNpc(Kheiron.class).setAffection(Main.game.getNpc(Astrapi.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Kheiron.class).setAffection(Main.game.getNpc(Vronti.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());

            Main.game.getNpc(Astrapi.class).setFather(Main.game.getNpc(Kheiron.class));
            Main.game.getNpc(Vronti.class).setFather(Main.game.getNpc(Kheiron.class));
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Arion.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Minotallys.class))) {
            Main.game.getNpc(Arion.class).setAffection(Main.game.getNpc(Minotallys.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
            Main.game.getNpc(Minotallys.class).setAffection(Main.game.getNpc(Arion.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Fae.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Silvia.class))) {
            Main.game.getNpc(Silvia.class).setAffection(Main.game.getNpc(Fae.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
            Main.game.getNpc(Fae.class).setAffection(Main.game.getNpc(Silvia.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Imsu.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Hale.class))) {
            Main.game.getNpc(Imsu.class).setAffection(Main.game.getNpc(Hale.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
            Main.game.getNpc(Hale.class).setAffection(Main.game.getNpc(Imsu.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Penelope.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Pix.class)) || Main.isVersionOlderThan(loadingVersion, "0.4.2.7")) {
            Main.game.getNpc(Penelope.class).setAffection(Main.game.getNpc(Pix.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(Pix.class).setAffection(Main.game.getNpc(Penelope.class), AffectionLevel.NEGATIVE_ONE_ANNOYED.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Oglix.class))) {
            Main.game.getNpc(Oglix.class).setAffection(Main.game.getNpc(Kheiron.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
            Main.game.getNpc(Kheiron.class).setAffection(Main.game.getNpc(Oglix.class), AffectionLevel.NEGATIVE_THREE_STRONG_DISLIKE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Golix.class))) {
            ((Oglix)Main.game.getNpc(Oglix.class)).createElemental(); // inits the summoner ID
            Main.game.getNpc(Kheiron.class).setAffection(Main.game.getNpc(Golix.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
            Main.game.getNpc(Golix.class).setAffection(Main.game.getNpc(Kheiron.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Hammer.class))) {
            Main.game.getNpc(Hammer.class).setAffection(Main.game.getNpc(Ghost.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
            Main.game.getNpc(Ghost.class).setAffection(Main.game.getNpc(Hammer.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Kalahari.class))) {
            Main.game.getNpc(Kalahari.class).setFather(Main.game.getNpc(Kruger.class));
            Main.game.getNpc(Kalahari.class).setAffection(Main.game.getNpc(Kruger.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Kruger.class))) {
            Main.game.getNpc(Kruger.class).setAffection(Main.game.getNpc(Kalahari.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Evelyx.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Dale.class))) {
            Main.game.getNpc(Evelyx.class).setAffection(Main.game.getNpc(Dale.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
            Main.game.getNpc(Dale.class).setAffection(Main.game.getNpc(Evelyx.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Angelixx.class)) || addedNpcs.contains(Main.game.getUniqueNPCId(Saellatrix.class))) {
            Main.game.getNpc(Angelixx.class).setAffection(Main.game.getNpc(Sleip.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
            Main.game.getNpc(Angelixx.class).setAffection(Main.game.getNpc(Nir.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());

            Main.game.getNpc(Saellatrix.class).setAffection(Main.game.getNpc(Angelixx.class), AffectionLevel.NEGATIVE_THREE_STRONG_DISLIKE.getMedianValue());
            Main.game.getNpc(Angelixx.class).setAffection(Main.game.getNpc(Saellatrix.class), AffectionLevel.NEGATIVE_TWO_DISLIKE.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Saellatrix.class)) || Main.isVersionOlderThan(loadingVersion, "0.4.9.12")) {
            Main.game.getNpc(Saellatrix.class).setAffection(Main.game.getNpc(Lilaya.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
            Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(Saellatrix.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Sleip.class))) {
            Main.game.getNpc(Sleip.class).setMother(Main.game.getNpc(Angelixx.class));
            Main.game.getNpc(Sleip.class).setAffection(Main.game.getNpc(Angelixx.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
            Main.game.getNpc(Sleip.class).setAffection(Main.game.getNpc(Nir.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Nir.class))) {
            Main.game.getNpc(Nir.class).setMother(Main.game.getNpc(Angelixx.class));
            Main.game.getNpc(Nir.class).setAffection(Main.game.getNpc(Angelixx.class), AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
            Main.game.getNpc(Nir.class).setAffection(Main.game.getNpc(Sleip.class), AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
        }
        if(addedNpcs.contains(Main.game.getUniqueNPCId(Lunette.class))) {
            Main.game.getNpc(Lunexis.class).setMother(Main.game.getNpc(Lunette.class));
            Main.game.getNpc(Lunexis.class).setAffection(Main.game.getNpc(Lunette.class), 100);
        }

        if(addedNpcs.contains(Main.game.getUniqueNPCId(Lovienne.class))) {
            Main.game.getNpc(Angelixx.class).setMother(Main.game.getNpc(Lovienne.class));
            Main.game.getNpc(Angelixx.class).setAffection(Main.game.getNpc(Lovienne.class), 100);
            Main.game.getNpc(Saellatrix.class).setMother(Main.game.getNpc(Lovienne.class));
            Main.game.getNpc(Saellatrix.class).setAffection(Main.game.getNpc(Lovienne.class), 100);
        }
    }


    /**
     *  if npc isn't in NPCMap it adds it using it's constructor
     * @param npc The class of the NPC to be added
     * @throws Exception technically it's a NPE, but this will be wrapped
     **/
    public static void addIfMissing(NPC npc) throws Exception {
        if (!Main.game.getNPCMap().containsKey(Main.game.getUniqueNPCId(npc.getClass()))) {
            Main.game.addNPC(npc, false);
        }
    }
    /**
     *  if npc isn't in NPCMap it adds it using it's constructor
     * @param npc The class of the NPC to be added
     * @throws Exception technically it's a NPE, but this will be wrapped
     **/
    public static void addIfMissing(Class<? extends NPC> npc) throws Exception {
        if (!Main.game.getNPCMap().containsKey(Main.game.getUniqueNPCId(npc))) {
            Main.game.addNPC(npc.getConstructor().newInstance(), false);
        }
    }

    /** This creates a virtual thread for every single NPC Class that's found in the above list */
    public static void buildNPCThreadPool() {
        for (Class<? extends NPC> npcClazz : allSimpleNPCClasses) {
            if (Library.loadedNPCs.contains(npcClazz.getSimpleName())) continue;
            CompletableFuture<NPC> addNPC = CompletableFuture.supplyAsync(() -> {
                NPC npc1 = null;
                try {
                    npc1 = npcClazz.getConstructor().newInstance();
                } catch (Exception e) {
                    System.err.println("Failed first attempt at NPC<" + npcClazz.getName() + "> generation, Attempting Second");
                    try {
                        npc1 = npcClazz.getConstructor(boolean.class).newInstance(false);
                    } catch (Exception e2) {
                        System.err.println("Failed alternative, Panic");
                        e2.printStackTrace();
                    }
                }
                return npc1;
            });
            futures.put(npcClazz, addNPC);
        }
    }

    /**
     * This wraps the function in a try/catch for cleanliness
     * it's better to keep T/C minimal wrapping to not cuck JIT from cross-compiling to assembly
     * @param npc the npc in question to be added
     * */
    public static void addIfMissingSafely(Class<? extends NPC> npc) {
        try {
            if (futures.containsKey(npc))
                addIfMissing(futures.get(npc).get());
        } catch (Exception p) {
            p.printStackTrace();
        }
    }

    /**
     * This wraps the function in a try/catch for cleanliness, sub-npc version
     * This is specifically used for Golix for now, but who knows what others may use it in the future
     * @param npc the npc in question to be added
     * @param gender gender of sub-npc
     * @param owner owner of sub-npc
     * */
    public static void addIfMissingSafely(Class<? extends NPC> npc, Gender gender, NPC owner) {
        try {
            CompletableFuture<NPC> addNPC = CompletableFuture.supplyAsync(() -> {
                NPC npc1 = null;
                try {
                    npc1 = npc.getConstructor(Gender.class, GameCharacter.class, boolean.class).newInstance(gender, owner, false);
                } catch (Exception e) {
                    System.err.println("Failed Special NPC Generation, Panic");
                    e.printStackTrace();
                }
                return npc1;
            });
            addIfMissing(addNPC.get());
        } catch (Exception p) {
            p.printStackTrace();
        }
    }

    public static void repairNPC(String npcID) {
        Class<? extends NPC> clazz = switch (npcID) {
            case "-1,GenericMaleNPC" -> GenericMaleNPC.class;
            case "-1,GenericFemaleNPC" -> GenericFemaleNPC.class;
            case "-1,GenericAndrogynousNPC" -> GenericAndrogynousNPC.class;
            case "-1,PrologueMale" -> PrologueMale.class;
            case "-1,PrologueFemale" -> PrologueFemale.class;
            case "-1,GenericTrader" -> GenericTrader.class;
            case "-1,TestNPC" -> TestNPC.class;
            case "-1,Lumi" -> Lumi.class;
            case "-1,Pazu" -> Pazu.class;
            case "-1,Ashley" -> Ashley.class;
            case "-1,Callie" -> Callie.class;
            case "-1,Rose" -> Rose.class;
            case "-1,Lilaya" -> Lilaya.class;
            case "-1,Arthur" -> Arthur.class;
            case "-1,Angel" -> Angel.class;
            case "-1,Bunny" -> Bunny.class;
            case "-1,Loppy" -> Loppy.class;
            case "-1,Jules" -> Jules.class;
            case "-1,Kruger" -> Kruger.class;
            case "-1,Kalahari" -> Kalahari.class;
            case "-1,Brax" -> Brax.class;
            case "-1,CandiReceptionist" -> CandiReceptionist.class;
            case "-1,Wes" -> Wes.class;
            case "-1,Elle" -> Elle.class;
            case "-1,Sterope" -> Sterope.class;
            case "-1,Hammer" -> Hammer.class;
            case "-1,Angelixx" -> Angelixx.class;
            case "-1,Sleip" -> Sleip.class;
            case "-1,Nir" -> Nir.class;
            case "-1,Scarlett" -> Scarlett.class;
            case "-1,Helena" -> Helena.class;
            case "-1,HarpyBimbo" -> HarpyBimbo.class;
            case "-1,HarpyBimboCompanion" -> HarpyBimboCompanion.class;
            case "-1,HarpyDominant" -> HarpyDominant.class;
            case "-1,HarpyDominantCompanion" -> HarpyDominantCompanion.class;
            case "-1,HarpyNympho" -> HarpyNympho.class;
            case "-1,HarpyNymphoCompanion" -> HarpyNymphoCompanion.class;
            case "-1,Vanessa" -> Vanessa.class;
            case "-1,Natalya" -> Natalya.class;
            case "-1,Finch" -> Finch.class;
            case "-1,Sean" -> Sean.class;
            case "-1,RentalMommy" -> RentalMommy.class;
            case "-1,Daddy" -> Daddy.class;
            case "-1,Saellatrix" -> Saellatrix.class;
            case "-1,Fiammetta" -> Fiammetta.class;
            case "-1,HeadlessHorseman" -> HeadlessHorseman.class;
            case "-1,Lunette" -> Lunette.class;
            case "-1,Lovienne" -> Lovienne.class;
            case "-1,Lyssieth" -> Lyssieth.class;
            case "-1,Elizabeth" -> Elizabeth.class;
            case "-1,Takahashi" -> Takahashi.class;
            case "-1,DarkSiren" -> DarkSiren.class;
            case "-1,Roxy" -> Roxy.class;
            case "-1,Axel" -> Axel.class;
            case "-1,Epona" -> Epona.class;
            case "-1,Vengar" -> Vengar.class;
            case "-1,Shadow" -> Shadow.class;
            case "-1,Silence" -> Silence.class;
            case "-1,Murk" -> Murk.class;
            case "-1,HazmatRat" -> HazmatRat.class;
            case "-1,Felicia" -> Felicia.class;
            case "-1,Zaranix" -> Zaranix.class;
            case "-1,Amber" -> Amber.class;
            case "-1,ZaranixMaidKatherine" -> ZaranixMaidKatherine.class;
            case "-1,ZaranixMaidKelly" -> ZaranixMaidKelly.class;
            case "-1,Claire" -> Claire.class;
            case "-1,FortressAlphaLeader" -> FortressAlphaLeader.class;
            case "-1,FortressFemalesLeader" -> FortressFemalesLeader.class;
            case "-1,FortressMalesLeader" -> FortressMalesLeader.class;
            case "-1,SlimeQueen" -> SlimeQueen.class;
            case "-1,SlimeGuardIce" -> SlimeGuardIce.class;
            case "-1,SlimeGuardFire" -> SlimeGuardFire.class;
            case "-1,SlimeRoyalGuard" -> SlimeRoyalGuard.class;
            case "-1,Fae" -> Fae.class;
            case "-1,Silvia" -> Silvia.class;
            case "-1,Kazik" -> Kazik.class;
            case "-1,Yui" -> Yui.class;
            case "-1,Nizhoni" -> Nizhoni.class;
            case "-1,Moreno" -> Moreno.class;
            case "-1,Heather" -> Heather.class;
            case "-1,Ziva" -> Ziva.class;
            case "-1,Eisek" -> Eisek.class;
            case "-1,Monica" -> Monica.class;
            case "-1,Ceridwen" -> Ceridwen.class;
            case "-1,Imsu" -> Imsu.class;
            case "-1,Hale" -> Hale.class;
            case "-1,Penelope" -> Penelope.class;
            case "-1,Belle" -> Belle.class;
            case "-1,Daphne" -> Daphne.class;
            case "-1,Farah" -> Farah.class;
            case "-1,Ralph" -> Ralph.class;
            case "-1,Nyan" -> Nyan.class;
            case "-1,NyanMum" -> NyanMum.class;
            case "-1,Vicky" -> Vicky.class;
            case "-1,Pix" -> Pix.class;
            case "-1,Hannah" -> Hannah.class;
            case "-1,Kate" -> Kate.class;
            case "-1,SupplierLeader" -> SupplierLeader.class;
            case "-1,SupplierPartner" -> SupplierPartner.class;
            case "-1,Kay" -> Kay.class;
            case "-1,Flash" -> Flash.class;
            case "-1,Jess" -> Jess.class;
            case "-1,Astrapi" -> Astrapi.class;
            case "-1,Vronti" -> Vronti.class;
            case "-1,Kheiron" -> Kheiron.class;
            case "-1,Arion" -> Arion.class;
            case "-1,Minotallys" -> Minotallys.class;
            case "-1,Evelyx" -> Evelyx.class;
            case "-1,Dale" -> Dale.class;
            case "-1,Lunexis" -> Lunexis.class;
            case "-1,Ursa" -> Ursa.class;
            case "-1,Aurokaris" -> Aurokaris.class;
            case "-1,Oglix" -> Oglix.class;
            case "-1,Wynter" -> Wynter.class;
            case "-1,Ghost" -> Ghost.class;
            default -> null;
        };
        try {
            if (clazz == null) {
                System.err.println("Invalid npc sent for repair : " + npcID);
                return;
            }
            addIfMissing(clazz);
        } catch (Exception e) {
            System.err.println("Failed to repair NPC : " + npcID);
        }
    }
}