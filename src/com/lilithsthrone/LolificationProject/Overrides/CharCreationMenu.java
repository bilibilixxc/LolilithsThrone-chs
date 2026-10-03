package com.lilithsthrone.LolificationProject.Overrides;

import com.lilithsthrone.LolificationProject.ModCommon.MiscStuff;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

import java.util.HashMap;
import java.util.Map;

import static com.lilithsthrone.LolificationProject.Overrides.CharacterCreationExtras.CHOOSE_ADVANCED_APPEARANCE_RACE;
import static com.lilithsthrone.game.dialogue.story.CharacterCreation.*;

public class CharCreationMenu {
    public static final ToggleSwitch PENIS_SWITCH = new ToggleSwitch("Has Penis", "Whether or not there is a penis", CharCreationMenu::togglePenis);
    public static final ToggleSwitch VAGINA_SWITCH = new ToggleSwitch("Has Vagina", "Whether or not there is a vagina", CharCreationMenu::toggleVagina);

    public static void addListeners() {
        PENIS_SWITCH.addListener();
        VAGINA_SWITCH.addListener();
    }

    public static void togglePenis() {
        if (BodyChanging.getTarget().hasPenis())
            BodyChanging.getTarget().setPenisType(PenisType.NONE);
        else BodyChanging.getTarget().setPenisType(PenisType.HUMAN);
    }
    public static void toggleVagina() {
        if (BodyChanging.getTarget().hasVagina())
            BodyChanging.getTarget().setVaginaType(VaginaType.NONE);
        else BodyChanging.getTarget().setVaginaType(VaginaType.HUMAN);
    }

    public static Response getBodyOptions(int index) {
        return switch (index) {
            case (1) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_CORE
                    ? new Response("Core", "You are already in the customisation menu for all of your body's core aspects.", null)
                    : new Response("Core", "Enter the customisation menu for all of your body's core aspects.", CHOOSE_ADVANCED_APPEARANCE_CORE);

            case (2) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_FACE
                    ? new Response("Face", "You are already in the customisation menu for aspects related to your face.", null)
                    : new Response("Face", "Enter the customisation menu for aspects related to your face.", CHOOSE_ADVANCED_APPEARANCE_FACE);

            case (3) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_COSMETICS
                    ? new Response("Makeup", "You are already in the customisation menu for makeup.", null)
                    : new Response("Makeup", "Enter the customisation menu for makeup.", CHOOSE_ADVANCED_APPEARANCE_COSMETICS);

            case (4) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_HAIR
                    ? new Response("Hair", "You are already in the customisation menu for your hair.", null)
                    : new Response("Hair", "Enter the customisation menu for your hair.", CHOOSE_ADVANCED_APPEARANCE_HAIR);

            case (5) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_RACE
                    ? new Response("Race", "You are already in the customisation menu for your racial aspects.", null)
                    : new Response("Race", "Enter the customisation menu for your racial aspects.", CHOOSE_ADVANCED_APPEARANCE_RACE);

            case (6) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_BREASTS
                    ? new Response("Breasts", "You are already in the customisation menu for your breasts.", null)
                    : new Response("Breasts", "Enter the customisation menu for your breasts.", CHOOSE_ADVANCED_APPEARANCE_BREASTS);

            case (7) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_ASS
                    ? new Response("Ass & Hips", "You are already in the customisation menu for aspects related to your ass, hips, and anus.", null)
                    : new Response("Ass & Hips", "Enter the customisation menu for aspects related to your ass, hips, and anus.", CHOOSE_ADVANCED_APPEARANCE_ASS);

            case (8) -> Main.game.getCurrentDialogueNode() == CharacterCreationExtras.CHOOSE_ADVANCED_APPEARANCE_GENITALS
                    ? new Response("Genitals", "You are already in the customisation menu for aspects related to your genitals.", null)
                    : new Response("Genitals", "Enter the customisation menu for aspects related to your genitals.", CharacterCreationExtras.CHOOSE_ADVANCED_APPEARANCE_GENITALS);

            case (11) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_PIERCINGS
                    ? new Response("Piercings", "You are already in the customisation menu for body piercings.", null)
                    : new Response("Piercings", "Enter the customisation menu for body piercings.", CHOOSE_ADVANCED_APPEARANCE_PIERCINGS);

            case (12) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_TATTOOS
                    ? new Response("Tattoos", "You are already in the customisation menu for tattoos.", null)
                    : new Response("Tattoos", "Enter the customisation menu for tattoos.", CHOOSE_ADVANCED_APPEARANCE_TATTOOS);

            case (13) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_BODY_HAIR
                    ? new Response("Extra hair", "You are already in the customisation menu for facial, pubic, and body hair.", null)
                    : new Response("Extra hair", "Enter the customisation menu for facial, pubic, and body hair.", CHOOSE_ADVANCED_APPEARANCE_BODY_HAIR);

            case (14) ->
                    Main.game.getPlayer().getSurname().equalsIgnoreCase("Fallen")
                            ? new Response("To the stage", "You're ready to approach the stage now.", CharacterCreation.CHOOSE_BACKGROUND) {
                                @Override
                                public int getSecondsPassed() {
                                    return CharacterCreation.TIME_TO_BACKGROUND;
                                }

                                @Override
                                public void effects() {
                                    CharacterCreation.moveNPCIntoPlayerTile();
                                    MiscStuff.checkCheats();
                                    if (Main.getProperties().hasValue(PropertyValue.lootTheMuseum)) {
                                        for (Map.Entry<AbstractClothing, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllClothingInInventory()).entrySet()) {
                                            Main.game.getPlayer().addClothing(entry.getKey(), entry.getValue(), true, true);
                                        }
                                    }
                                }
                            }
                            : new Response("Finished", "Your clothes are a little messy after rushing here. Tidy yourself up before proceeding to the main stage.", InventoryDialogue.INVENTORY_MENU) {
                                @Override
                                public int getSecondsPassed() {
                                    return TIME_TO_CLOTHING;
                                }

                                @Override
                                public void effects() {
                                    equipPiercings();
                                    MiscStuff.checkCheats();
                                    if (Main.getProperties().hasValue(PropertyValue.lootTheMuseum)) {
                                        for (Map.Entry<AbstractClothing, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllClothingInInventory()).entrySet()) {
                                            Main.game.getPlayer().addClothing(entry.getKey(), entry.getValue(), true, true);
                                        }
                                    }
                                    InventoryDialogue.setBuyback(false);
                                    InventoryDialogue.setInventoryNPC(null);
                                    InventoryDialogue.setNPCInventoryInteraction(InventoryInteraction.CHARACTER_CREATION);
                                }
                            };
            case (0) -> new Response("Back", "Confirm your choices and return to the content preferences menu.", CHOOSE_NAME) {
                    @Override
                    public int getSecondsPassed() {
                        return -TIME_TO_APPEARANCE;
                    }

                    @Override
                    public void effects() {
                        Main.game.getPlayer().setLocation(WorldType.MUSEUM, PlaceType.MUSEUM_ENTRANCE, false);
                    }
                };
            default -> null;
        };
    }
}