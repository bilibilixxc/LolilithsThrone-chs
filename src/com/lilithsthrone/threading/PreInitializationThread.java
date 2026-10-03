package com.lilithsthrone.threading;

import com.lilithsthrone.LolificationProject.ModCommon.Logger;
import com.lilithsthrone.LolificationProject.ModCommon.TranslationUtils;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.*;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import javafx.application.Platform;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.lilithsthrone.LolificationProject.ConfigStuff.Configurations.MULTITHREADED_PRELOADING;
import static com.lilithsthrone.game.dialogue.utils.UtilText.engine;
import static com.lilithsthrone.game.dialogue.utils.UtilText.initScriptEngine;

/**
 * @since 0.4.7.6
 * @version 0.4.10.7
 * @author KeldonSlayer (DrZed)
 */
public class PreInitializationThread extends Thread {
    private static final boolean DEBUG_PRE_INIT = false, PRINT_COUNT = false;
    public static PreInitializationThread HelperThread1, HelperThread2, HelperThread3, HelperThread4, HelperThread5;
    public static final AtomicBoolean INIT_BODY_COVERING_TYPE = new AtomicBoolean(false),
            INIT_RACES = new AtomicBoolean(false), INIT_BREASTS = new AtomicBoolean(false),
            INIT_ANTENNA = new AtomicBoolean(false), INIT_ANUS = new AtomicBoolean(false),
            INIT_ARM = new AtomicBoolean(false), INIT_ASS = new AtomicBoolean(false),
            INIT_EAR = new AtomicBoolean(false), INIT_EYE = new AtomicBoolean(false),
            INIT_FACE = new AtomicBoolean(false), INIT_FLUID = new AtomicBoolean(false),
            INIT_FOOT = new AtomicBoolean(false), INIT_HAIR = new AtomicBoolean(false),
            INIT_HORN = new AtomicBoolean(false), INIT_LEG = new AtomicBoolean(false),
            INIT_MOUTH = new AtomicBoolean(false), INIT_NIPPLE = new AtomicBoolean(false),
            INIT_PENIS = new AtomicBoolean(false), INIT_TAIL = new AtomicBoolean(false),
            INIT_TENTACLE = new AtomicBoolean(false), INIT_TESTICLE = new AtomicBoolean(false),
            INIT_TONGUE = new AtomicBoolean(false), INIT_TORSO = new AtomicBoolean(false),
            INIT_VAGINA = new AtomicBoolean(false), INIT_WING = new AtomicBoolean(false),
            INIT_WORLD = new AtomicBoolean(false), INIT_PLACE = new AtomicBoolean(false),
            INIT_SUBSPECIES = new AtomicBoolean(false), INIT_DIALOGUE = new AtomicBoolean(false);
    private static boolean hasPrinted = false;
    private static int initializedItems = 0;

    public static void preload() {
        if (MULTITHREADED_PRELOADING) {
            PreInitializationThread.HelperThread1 = new PreInitializationThread("PRE-INIT-1");
            PreInitializationThread.HelperThread1.start();
            PreInitializationThread.HelperThread2 = new PreInitializationThread("PRE-INIT-2");
            PreInitializationThread.HelperThread2.start();
            PreInitializationThread.HelperThread3 = new PreInitializationThread("PRE-INIT-3");
            PreInitializationThread.HelperThread3.start();
            PreInitializationThread.HelperThread4 = new PreInitializationThread("PRE-INIT-4");
            PreInitializationThread.HelperThread4.start();
            PreInitializationThread.HelperThread5 = new PreInitializationThread("PRE-INIT-5");
            PreInitializationThread.HelperThread5.start();
        }
    }

    @Override
    public void run() {
        if (this == HelperThread1)
            initCore();
        if (this == HelperThread2)
            initHead();
        if (this == HelperThread3)
            initBody();
        if (this == HelperThread4)
            initFinal();
        if (this == HelperThread5)
            initBigBoys();

        if (this.finished()) {
            if (engine == null) {
                long waited = 0;
                while (Main.game == null) {
                    waited++;
                    // do nothing
                }
                if (waited > 0)
                    System.err.println("Waited " + waited + " cycles for game to initialize.");
                initScriptEngine();
            }
            try { //Added as reminded by Ybyx
                TranslationUtils.init();
            } catch (IOException e) { // Safety Check, #Mandatory
                System.err.println("TL UTIL INIT FAIL");
                e.printStackTrace();
            }
            if (!hasPrinted) {
                hasPrinted = true;
                long waited = 0;
                while (Main.instance == null) {
                    waited++;
                }
                Platform.runLater(() -> Main.instance.resetContent());
                System.out.println("Waited " + waited + " cycles for instance to initialize.");
            }
            Platform.runLater(() -> Main.saveProperties());
        }
        finish();
    }

    private void initCore() {
        long timeStarted = System.nanoTime(), timeSegment = System.nanoTime();
        do {
            int cnt = BodyCoveringType.allBodyCoveringTypes.size();
            if (cnt >= 365) {
                INIT_BODY_COVERING_TYPE.set(true);
                if (PRINT_COUNT) System.out.println("INIT_BODY_COVERING_TYPE initialized : " + cnt);
            }
        } while (!INIT_BODY_COVERING_TYPE.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Body Covering in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = Race.getAllRaces().size();
            if (cnt >= 40) {
                INIT_RACES.set(true);
                if (PRINT_COUNT) System.out.println("INIT_RACES initialized : " + cnt);
            }
        } while (!INIT_RACES.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Races in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = FluidType.getAllFluidTypes().size();
            if (cnt >= 112) {
                INIT_FLUID.set(true);
                if (PRINT_COUNT) System.out.println("INIT_FLUID initialized : " + cnt);
            }
        } while (!INIT_FLUID.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Fluids in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }
    }

    private void initBigBoys() {
        long timeStarted = System.nanoTime(), timeSegment = System.nanoTime();


        do {
            int cnt = DialogueManager.getAllDialogues().size();
            if (cnt >= 1415) {
                INIT_DIALOGUE.set(true);
                if (PRINT_COUNT) System.out.println("INIT_DIALOGUE initialized : " + cnt);
            }
        } while (!INIT_DIALOGUE.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Place in", System.nanoTime() - timeSegment);
            Logger.logTime("Finished Core in", System.nanoTime() - timeStarted);
        }
        do {
            int cnt = PlaceType.getAllPlaceTypes().size();
            if (cnt >= 628) {
                INIT_PLACE.set(true);
                if (PRINT_COUNT) System.out.println("INIT_PLACE initialized : " + cnt);
            }
        } while (!INIT_PLACE.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Place in", System.nanoTime() - timeSegment);
            Logger.logTime("Finished Core in", System.nanoTime() - timeStarted);
        }
        do {
            int cnt = WorldType.getAllWorldTypes().size();
            if (cnt >= 65) {
                INIT_WORLD.set(true);
                if (PRINT_COUNT) System.out.println("INIT_WORLD initialized : " + cnt);
            }
        } while (!INIT_WORLD.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Worlds in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = Subspecies.subspeciesToIdMap.size();
            if (cnt >= 18) {
                INIT_SUBSPECIES.set(true);
                if (PRINT_COUNT) System.out.println("INIT_SUBSPECIES initialized : " + cnt);
            }
        } while (!INIT_SUBSPECIES.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Subspecies in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }
    }

    private void initHead() {
        long timeStarted = System.nanoTime(), timeSegment = System.nanoTime();
        do {
            if (!INIT_RACES.get()) continue;
            int cnt = EarType.getAllEarTypes().size();
            if (cnt >= 46) {
                INIT_EAR.set(true);
                if (PRINT_COUNT) System.out.println("INIT_EAR initialized : " + cnt);
            }
        } while (!INIT_EAR.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Ears in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = EyeType.getAllEyeTypes().size();
            if (cnt >= 39) {
                INIT_EYE.set(true);
                if (PRINT_COUNT) System.out.println("INIT_EYE initialized : " + cnt);
            }
        } while (!INIT_EYE.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Eyes in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = FaceType.getAllFaceTypes().size();
            if (cnt >= 41) {
                INIT_FACE.set(true);
                if (PRINT_COUNT) System.out.println("INIT_FACE initialized : " + cnt);
            }
        } while (!INIT_FACE.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Faces in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = HairType.getAllHairTypes().size();
            if (cnt >= 40) {
                INIT_HAIR.set(true);
                if (PRINT_COUNT) System.out.println("INIT_HAIR initialized : " + cnt);
            }
        } while (!INIT_HAIR.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Hair in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = HornType.getAllHornTypes().size();
            if (cnt >= 13) {
                INIT_HORN.set(true);
                if (PRINT_COUNT) System.out.println("INIT_HORN initialized : " + cnt);
            }
        } while (!INIT_HORN.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Horns in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = TongueType.getAllTongueTypes().size();
            if (cnt >= 38) {
                INIT_TONGUE.set(true);
                if (PRINT_COUNT) System.out.println("INIT_TONGUE initialized : " + cnt);
            }
        } while (!INIT_TONGUE.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Tongues in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = MouthType.getAllMouthTypes().size();
            if (cnt >= 38) {
                INIT_MOUTH.set(true);
                if (PRINT_COUNT) System.out.println("INIT_MOUTH initialized : " + cnt);
            }
        } while (!INIT_MOUTH.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Mouths in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = AntennaType.getAllAntennaTypes().size();
            if (cnt >= 1) {
                INIT_ANTENNA.set(true);
                if (PRINT_COUNT) System.out.println("INIT_ANTENNA initialized : " + cnt);
            }
        } while (!INIT_ANTENNA.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Antennae in", System.nanoTime() - timeSegment);
            Logger.logTime("Finished Head in", System.nanoTime() - timeStarted);
        }
    }

    private void initBody() {
        long timeStarted = System.nanoTime(), timeSegment = System.nanoTime();
        do {
            if (!INIT_RACES.get()) continue;
            int cnt = TorsoType.getAllTorsoTypes().size();
            if (cnt >= 40) {
                INIT_TORSO.set(true);
                if (PRINT_COUNT) System.out.println("INIT_TORSO initialized : " + cnt);
            }
        } while (!INIT_TORSO.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Torso in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = TailType.getAllTailTypes().size();
            if (cnt >= 50) {
                INIT_TAIL.set(true);
                if (PRINT_COUNT) System.out.println("INIT_TAIL initialized : " + cnt);
            }
        } while (!INIT_TAIL.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Tail in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = TentacleType.getAllTentacleTypes().size();
            if (cnt >= 4) {
                INIT_TENTACLE.set(true);
                if (PRINT_COUNT) System.out.println("INIT_TENTACLE initialized : " + cnt);
            }
        } while (!INIT_TENTACLE.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Tentacle in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = ArmType.getAllArmTypes().size();
            if (cnt >= 41) {
                INIT_ARM.set(true);
                if (PRINT_COUNT) System.out.println("INIT_ARM initialized : " + cnt);
            }
        } while (!INIT_ARM.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Arm in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }


        do {
            int cnt = LegType.getAllLegTypes().size();
            if (cnt >= 46) {
                INIT_LEG.set(true);
                if (PRINT_COUNT) System.out.println("INIT_LEG initialized : " + cnt);
            }
        } while (!INIT_LEG.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Leg in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = FootType.getAllFootTypes().size();
            if (cnt >= 9) {
                INIT_FOOT.set(true);
                if (PRINT_COUNT) System.out.println("INIT_FOOT initialized : " + cnt);
            }
        } while (!INIT_FOOT.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Foot in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = WingType.getAllWingTypes().size();
            if (cnt >= 11) {
                INIT_WING.set(true);
                if (PRINT_COUNT) System.out.println("INIT_WING initialized : " + cnt);
            }
        } while (!INIT_WING.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Wing in", System.nanoTime() - timeSegment);
            Logger.logTime("Finished Body in", System.nanoTime() - timeStarted);
        }
    }

    private void initFinal() {
        long timeStarted = System.nanoTime(), timeSegment = System.nanoTime();
        do {
            int cnt = VaginaType.getAllVaginaTypes().size();
            if (cnt >= 39) {
                INIT_VAGINA.set(true);
                if (PRINT_COUNT) System.out.println("INIT_VAGINA initialized : " + cnt);
            }
        } while (!INIT_VAGINA.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Vagina in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = NippleType.getAllNippleTypes().size();
            if (cnt >= 37) {
                INIT_NIPPLE.set(true);
                if (PRINT_COUNT) System.out.println("INIT_NIPPLE initialized : " + cnt);
            }
        } while (!INIT_NIPPLE.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Nipple in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = BreastType.getAllBreastTypes().size();
            if (cnt >= 38) {
                INIT_BREASTS.set(true);
                if (PRINT_COUNT) System.out.println("INIT_BREASTS initialized : " + cnt);
            }
        } while (!INIT_BREASTS.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Breasts in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = AnusType.getAllAnusTypes().size();
            if (cnt >= 37) {
                INIT_ANUS.set(true);
                if (PRINT_COUNT) System.out.println("INIT_ANUS initialized : " + cnt);
            }
        } while (!INIT_ANUS.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Anus in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = AssType.getAllAssTypes().size();
            if (cnt >= 36) {
                INIT_ASS.set(true);
                if (PRINT_COUNT) System.out.println("INIT_ASS initialized : " + cnt);
            }
        } while (!INIT_ASS.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Ass in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = TesticleType.getAllTesticleTypes().size();
            if (cnt >= 39) {
                INIT_TESTICLE.set(true);
                if (PRINT_COUNT) System.out.println("INIT_TESTICLE initialized : " + cnt);
            }
        } while (!INIT_TESTICLE.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Testicle in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        do {
            int cnt = PenisType.getAllPenisTypes().size();
            if (cnt >= 39) {
                INIT_PENIS.set(true);
                if (PRINT_COUNT) System.out.println("INIT_PENIS initialized : " + cnt);
            }
        } while (!INIT_PENIS.get());
        initializedItems++;
        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Penis in", System.nanoTime() - timeSegment);
            timeSegment = System.nanoTime();
        }

        Perk.generateSubspeciesPerks();

        if (DEBUG_PRE_INIT) {
            Logger.logTime("Finished Perks in", System.nanoTime() - timeSegment);
            Logger.logTime("Finished Final in", System.nanoTime() - timeStarted);
        }
    }

    public PreInitializationThread(String name) {
        super(name);
    }
    private void finish() {
        try {
//            System.out.println("Thread : " + Thread.currentThread().getName() + " Joining Main");
            join();
        } catch (InterruptedException e) {
            System.err.println("Thread joining failed");
            e.printStackTrace();
            System.err.println("Trying again");
            try {
                join();
            } catch (InterruptedException ex) {
                System.err.println("Failed Again, Panic.");
                ex.printStackTrace();
            }
        }
    }

    public static boolean finished() {
        if (initializedItems < 27) return false;
        return INIT_BREASTS.get() && INIT_PLACE.get() && INIT_WORLD.get() && INIT_ANTENNA.get() && INIT_ANUS.get() && INIT_ARM.get() && INIT_ASS.get() && INIT_EAR.get() && INIT_EYE.get() && INIT_FACE.get() && INIT_FLUID.get() && INIT_FOOT.get() && INIT_HAIR.get() && INIT_HORN.get() && INIT_LEG.get() && INIT_MOUTH.get() && INIT_NIPPLE.get() && INIT_PENIS.get() && INIT_TAIL.get() && INIT_TENTACLE.get() && INIT_TESTICLE.get() && INIT_TONGUE.get() && INIT_TORSO.get() && INIT_VAGINA.get() && INIT_WING.get();
    }

}