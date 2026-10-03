package com.lilithsthrone.LolificationProject.Lexi.dialogue.encounters;

import com.lilithsthrone.LolificationProject.Lexi.character.npc.fields.Cecilia;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.HarpyNestsAttacker;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.encounters.DominionEncounterDialogue;
import com.lilithsthrone.game.dialogue.encounters.EncounterType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.Weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LexiEncounter {
    public static AbstractEncounter CECILIA_AMBUSH = new AbstractEncounter() {
        @Override
        public Map<EncounterType, Float> getDialogues() {
            return Util.newHashMapOfValues(
                    new Util.Value<EncounterType, Float>(EncounterType.CECILIA_AMBUSH, 8f));
        }

        @Override
        public DialogueNode initialiseEncounter(EncounterType node) {
            if (node == EncounterType.CECILIA_AMBUSH && Main.game.isHourBetween(9, 18)) {
//                // Prioritise re-encountering the NPC on this tile:
//                List<NPC> encounterPossibilities = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
//                if(!encounterPossibilities.isEmpty()) {
//                    NPC encounterNpc = Util.randomItemFrom(encounterPossibilities);
//                    Main.game.setActiveNPC(encounterNpc);
//                    return Main.game.getActiveNPC().getEncounterDialogue();
//                }

                Main.game.getNpc(Cecilia.class).setLocation(Main.game.getPlayer().getLocation());
                Main.game.setActiveNPC(Main.game.getNpc(Cecilia.class));
                return Main.game.getActiveNPC().getEncounterDialogue();
            }

            return null;
        }
    };
}
