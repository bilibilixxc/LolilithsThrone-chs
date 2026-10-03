package com.lilithsthrone.LolificationProject.Experiments.NMS.NMSElements;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.utils.colours.Colour;

public class NPCOptionMultiSelect {
    public final String OPTION_ID;
    public final Class<? extends NPC> OPTION_NPC;
    public final Colour OPTION_COLOUR;
    public final String OPTION_TITLE;
    public final String OPTION_DESCRIPTION;
    public final String OPTION_DISPLAY;
    public final int OPTION_MIN_VALUE;
    public final int OPTION_MIN_STEP;
    public final int OPTION_MAX_STEP;
    public final int OPTION_VALUE_PER_STEP;
    public final String OPTION_FIELD_NAME;

    public NPCOptionMultiSelect(String id, Class<? extends NPC> npc, Colour colour, String title, String description, String display, int minVal, int maxVal, int valPerStep, String field) {
        this(id, npc, colour, title, description, display, minVal, 0, maxVal, valPerStep, field);
    }

    public NPCOptionMultiSelect(String id, Class<? extends NPC> npc, Colour colour, String title, String description, String display, int minVal, int minStep, int maxSteps, int valPerStep, String field) {
        OPTION_ID = id;
        OPTION_NPC = npc;
        OPTION_COLOUR = colour;
        OPTION_TITLE = title;
        OPTION_DESCRIPTION = description;
        OPTION_DISPLAY = display;
        OPTION_MIN_VALUE = minVal;
        OPTION_MIN_STEP = minStep;
        OPTION_MAX_STEP = maxSteps;
        OPTION_VALUE_PER_STEP = valPerStep;
        OPTION_FIELD_NAME = field;
    }
}