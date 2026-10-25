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
    public static final ToggleSwitch PENIS_SWITCH = new ToggleSwitch("拥有阴茎", "是否有阴茎", CharCreationMenu::togglePenis);
    public static final ToggleSwitch VAGINA_SWITCH = new ToggleSwitch("拥有阴道", "是否有阴道", CharCreationMenu::toggleVagina);

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
                    ? new Response("核心", "你已经处于所有身体核心方面的自定义菜单中。", null)
                    : new Response("核心", "进入自定义你身体所有核心方面的菜单。", CHOOSE_ADVANCED_APPEARANCE_CORE);

            case (2) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_FACE
                    ? new Response("脸部", "你已经处于与脸部相关方面的自定义菜单中。", null)
                    : new Response("脸部", "进入自定义脸部相关方面的菜单。", CHOOSE_ADVANCED_APPEARANCE_FACE);

            case (3) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_COSMETICS
                    ? new Response("妆容", "你已经处于妆容的自定义菜单中。", null)
                    : new Response("妆容", "进入自定义妆容的菜单。", CHOOSE_ADVANCED_APPEARANCE_COSMETICS);

            case (4) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_HAIR
                    ? new Response("头发", "你已经处于头发的自定义菜单中。", null)
                    : new Response("头发", "进入自定义头发的菜单。", CHOOSE_ADVANCED_APPEARANCE_HAIR);

            case (5) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_RACE
                    ? new Response("种族", "你已经处于种族方面的自定义菜单中。", null)
                    : new Response("种族", "进入自定义种族方面的菜单。", CHOOSE_ADVANCED_APPEARANCE_RACE);

            case (6) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_BREASTS
                    ? new Response("胸部", "你已经处于胸部的自定义菜单中。", null)
                    : new Response("胸部", "进入自定义胸部的菜单。", CHOOSE_ADVANCED_APPEARANCE_BREASTS);

            case (7) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_ASS
                    ? new Response("臀部与胯部", "你已经处于与臀部、胯部和肛门相关方面的自定义菜单中。", null)
                    : new Response("臀部与胯部", "进入自定义臀部、胯部和肛门相关方面的菜单。", CHOOSE_ADVANCED_APPEARANCE_ASS);

            case (8) -> Main.game.getCurrentDialogueNode() == CharacterCreationExtras.CHOOSE_ADVANCED_APPEARANCE_GENITALS
                    ? new Response("生殖器", "你已经处于生殖器相关方面的自定义菜单中。", null)
                    : new Response("生殖器", "进入自定义生殖器相关方面的菜单。", CharacterCreationExtras.CHOOSE_ADVANCED_APPEARANCE_GENITALS);

            case (11) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_PIERCINGS
                    ? new Response("穿孔", "你已经处于身体穿孔的自定义菜单中。", null)
                    : new Response("穿孔", "进入自定义身体穿孔的菜单。", CHOOSE_ADVANCED_APPEARANCE_PIERCINGS);

            case (12) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_TATTOOS
                    ? new Response("纹身", "你已经处于纹身的自定义菜单中。", null)
                    : new Response("纹身", "进入自定义纹身的菜单。", CHOOSE_ADVANCED_APPEARANCE_TATTOOS);

            case (13) -> Main.game.getCurrentDialogueNode() == CHOOSE_ADVANCED_APPEARANCE_BODY_HAIR
                    ? new Response("额外毛发", "你已经处于面部、阴部和身体毛发的自定义菜单中。", null)
                    : new Response("额外毛发", "进入自定义面部、阴部和身体毛发的菜单。", CHOOSE_ADVANCED_APPEARANCE_BODY_HAIR);

            case (14) ->
                    Main.game.getPlayer().getSurname().equalsIgnoreCase("Fallen")
                            ? new Response("登上舞台", "你已准备好登上舞台了。", CharacterCreation.CHOOSE_BACKGROUND) {
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
                            : new Response("完成", "匆匆赶到此地后，你的衣服有些凌乱。在前往主舞台之前，先整理一下自己。", InventoryDialogue.INVENTORY_MENU) {
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
            case (0) -> new Response("返回", "确认你的选择并返回内容偏好菜单。", CHOOSE_NAME) {
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