package com.lilithsthrone.LolificationProject.ModCommon;

import com.lilithsthrone.LolificationProject.ConfigStuff.AdvancedModOptions;
import com.lilithsthrone.LolificationProject.ConfigStuff.Configurations;
import com.lilithsthrone.LolificationProject.ConfigStuff.NewModOptions;
import com.lilithsthrone.LolificationProject.Overrides.MenuOverrides;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

import static com.lilithsthrone.LolificationProject.ConfigStuff.Configurations.DRAKE_MODE;
import static com.lilithsthrone.LolificationProject.ConfigStuff.Configurations.EXPERIMENTAL;
import static com.lilithsthrone.LolificationProject.ModCommon.MiscStuff.paragraphWrap;

@SuppressWarnings("CommentedOutCode")
public class ModOptions {
    public static Response getModOptionsResponses(int index) {
        return switch (index) {
            case 1 -> Main.game.getCurrentDialogueNode() == MOD_CONTENT_PREFERENCE
                    ? new Response("常规", "你已经在查看这些选项了", null)
                    : new Response("常规", "进入常规模组选项菜单。", MOD_CONTENT_PREFERENCE);
            case 2 -> Main.game.getCurrentDialogueNode() == MOD_BALANCE_PREFERENCES
                    ? new Response("游戏平衡", "你已经在查看这些选项了", null)
                    : new Response("游戏平衡", "进入平衡模组选项菜单。", MOD_BALANCE_PREFERENCES);
            case 3 -> Main.game.getCurrentDialogueNode() == MOD_BODY_PREFERENCES
                    ? new Response("身体修改", "你已经在查看这些选项了", null)
                    : new Response("身体修改", "进入身体模组选项菜单。", MOD_BODY_PREFERENCES);
            case 5 -> Main.game.getCurrentDialogueNode() == MOD_CHALLENGE_PREFERENCES
                    ? new Response("挑战设置", "你已经在查看这些选项了", null)
                    : new Response("挑战设置", "进入挑战选项菜单。", MOD_CHALLENGE_PREFERENCES);
            case 6 -> Main.game.getCurrentDialogueNode() == ADVANCED_OPTIONS
                    ? new Response("高级设置", "你已经在查看这些选项了", null)
                    : new Response("高级设置", "进入高级选项菜单。", ADVANCED_OPTIONS);
            case 7 -> Main.game.getCurrentDialogueNode() == VirginityMenu.VIRGIN_RATES_MENU
                    ? new Response("处女率", "你已经在查看这些选项了", null)
                    : new Response("处女率", "进入处女率菜单。", VirginityMenu.VIRGIN_RATES_MENU);
            case 12 -> Main.game.getCurrentDialogueNode() == OptionsDialogue.OPTIONS
                    ? new Response("选项", "你已经在查看这些选项了", null)
                    : new Response("选项", "打开选项页面。", OptionsDialogue.OPTIONS){
                @Override
                public void effects() {
                    Main.saveProperties();
                }
            };
            case 13 -> Main.game.getCurrentDialogueNode() == OptionsDialogue.MISCELLANEOUS
                    ? new Response("内容选项", "你已经在查看这些选项了", null)
                    : new Response("内容选项", "设置你偏好的内容设置。", OptionsDialogue.MISCELLANEOUS){
                @Override
                public void effects() {
                    Main.saveProperties();
                }
            };
            case 14 ->
                    Main.game.getCurrentDialogueNode() == MOD_CONTENT_PREFERENCE || Main.game.getCurrentDialogueNode() == MOD_BALANCE_PREFERENCES || Main.game.getCurrentDialogueNode() == MOD_BODY_PREFERENCES
                            ? new Response("模组选项", "你已经在查看这些选项了", null)
                            : new Response("模组选项", "设置你偏好的模组设置。", ModOptions.MOD_CONTENT_PREFERENCE);
            case 0 -> new Response("返回", "返回主菜单。", MenuOverrides.MENU){
                @Override
                public void effects() {
                    Main.saveProperties();
                }
            };
            default -> null;
        };
    }

    public static final DialogueNode MOD_CONTENT_PREFERENCE = new DialogueNode("模组选项", "", true) {
        @Override
        public String getHeaderContent() {
            UtilText.nodeContentSB.setLength(0);
            if (!DRAKE_MODE || Configurations.DEGEN_MODE)
                UtilText.nodeContentSB.append(NewModOptions.ORIGINAL_AGE.getDiv());
            if (Configurations.DEGEN_MODE)
                UtilText.nodeContentSB.append(NewModOptions.IMPORT_AGE.getDiv());
//            UtilText.nodeContentSB.append(NewModOptions.MIN_AGE_SPEECH.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.LEXI_MENU_OPTIONS.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.FETISH_PEE.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.FETISH_AGE.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.COMBAT_HUG.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.SHOW_AGE.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.SHOW_TRUE_AGE.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.KIN_MODE.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.OFFSPRING_INHERIT_LEVEL.getDiv());
            if (Configurations.DEGEN_MODE) {
//                UtilText.nodeContentSB.append(NewModOptions.OFFSPRING_AGE_PREF.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.SHOW_ENHANCED_DESCRIPTION.getDiv());
                    UtilText.nodeContentSB.append(NewModOptions.SHOTA_AGE.getDiv());
                    UtilText.nodeContentSB.append(NewModOptions.LOLI_AGE.getDiv());
                    UtilText.nodeContentSB.append(NewModOptions.YOUNG_AGE.getDiv());
                    UtilText.nodeContentSB.append(NewModOptions.OLD_AGE.getDiv());

                /*for (String div :  new String[]{"SHOTA_AGE", "LOLI_AGE", "YOUNG_AGE", "OLD_AGE"}) {
                    if (!ModProps.isShowEnhancedDescriptionEnabled())
                        ((Element) MainController.document.getElementById(div).getParentNode().getParentNode()).setAttribute("hidden",
                                String.valueOf(ModProps.isShowEnhancedDescriptionEnabled()));
                    else ((Element) MainController.document.getElementById(div).getParentNode().getParentNode()).removeAttribute("hidden");
                }*/
            }
            if (Configurations.DEGEN_MODE)
                UtilText.nodeContentSB.append(NewModOptions.AGE_PROGRESSION.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.EQUIP_UNIQUES.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ENSLAVE_LUMI.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.COMBAT_LEAKAGE.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.FORCE_CLEAN_SLOTS.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ENABLE_PENT_UP.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.NERF_AFF_OBED.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ENABLE_SLOVENLY.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.LOOT_THE_MUSEUM.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.WAKE_AFFECTION_LOSS.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.VANILLA_JOB_CONDITIONS.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ENABLE_FRENCH_KISS.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ENABLE_TUSSY_CONTENT.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ENABLE_EXTRA_SELF_CONTENT.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ENABLE_SEX_IMAGES.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ENABLE_PERSONALITY.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.FUCKABLE_CUP_SIZE.getDiv());
            return UtilText.nodeContentSB.toString();
        }

        @Override
        public String getContent() {
            return "";
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            return getModOptionsResponses(index);
        }

        @Override
        public DialogueNodeType getDialogueNodeType() {
            return DialogueNodeType.OPTIONS;
        }
    };

    public static final DialogueNode MOD_BALANCE_PREFERENCES = new DialogueNode("游戏平衡", "", true) {
        @Override
        public String getHeaderContent() {
            UtilText.nodeContentSB.setLength(0);
            UtilText.nodeContentSB.append(NewModOptions.ENABLE_ENFORCERS.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.FORCE_ORGASMIC_DRAIN.getDiv());
//            UtilText.nodeContentSB.append(NewModOptions.DEMON_CHANCE.getDiv());
//            UtilText.nodeContentSB.append(NewModOptions.ENSLAVEMENT_COST.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.XP_MULT.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ESS_MULT.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.MONEY_MULT.getDiv());;
            UtilText.nodeContentSB.append(NewModOptions.ENABLE_MULTI_INCOME.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.INTEREST_MULT.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.DROPS_MULT.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ENC_COST_MULT.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.MAX_LEVEL.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.PLAYER_PREG_DUR.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.NPC_PREG_DUR.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.MAX_PERKS.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ESSENCE_STOCK.getDiv());

            if (Main.getProperties().hasValue(PropertyValue.enchantmentLimits)) {
                 UtilText.nodeContentSB.append(NewModOptions.ENCH_STAB_BASE.getDiv());
                 UtilText.nodeContentSB.append(NewModOptions.ENCH_STAB_LIMIT.getDiv());
                 UtilText.nodeContentSB.append(NewModOptions.ENCH_STAB_PER_LEVEL.getDiv());
                 UtilText.nodeContentSB.append(NewModOptions.ENCH_STAB_PER_PHYS.getDiv());
                 UtilText.nodeContentSB.append(NewModOptions.ENCH_STAB_PER_ARC.getDiv());
                 UtilText.nodeContentSB.append(NewModOptions.ENCH_STAB_PER_CORR.getDiv());
            }

            UtilText.nodeContentSB.append(NewModOptions.MAX_ENCHANTMENTS.getDiv());
//            if (EXPERIMENTAL) {
                UtilText.nodeContentSB.append(NewModOptions.POTENCY_MINOR.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.POTENCY_MEDIUM.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.POTENCY_MAJOR.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.VALUE_MINOR.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.VALUE_MEDIUM.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.VALUE_MAJOR.getDiv());
//            }
            return UtilText.nodeContentSB.toString();
        }

        @Override
        public String getContent() {
            return "";
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            return getModOptionsResponses(index);
        }

        @Override
        public DialogueNodeType getDialogueNodeType() {
            return DialogueNodeType.OPTIONS;
        }
    };

    public static final DialogueNode MOD_BODY_PREFERENCES = new DialogueNode("身体模组选项", "", true) {
        @Override
        public String getHeaderContent() {
            UtilText.nodeContentSB.setLength(0);
            UtilText.nodeContentSB.append(NewModOptions.MINIMUM_AGE.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.MAXIMUM_AGE.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.CHANGE_APPARENT_AGE.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.APPARENT_AGE_OFFSET.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.AMORPHOUS_APPARENT_AGE_OFFSET.getDiv());
//            UtilText.nodeContentSB.append(NewModOptions.OFFSPRING_AGE.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.EXCLUDE_AMORPHOUS.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.IMP_PREFERENCES.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.IMP_HEIGHT.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ALPHA_IMP_HEIGHT.getDiv());
            if (Configurations.DEGEN_MODE) {
                UtilText.nodeContentSB.append(NewModOptions.HEIGHT_MOD.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.HEIGHT_GENDERED.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.HEIGHT_RELATIVE.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.BODY_AGE.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.BA_BODY.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.BA_BREASTS.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.BA_NIPPLE.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.BA_HIPS.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.BA_VAGINA.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.BA_PENIS.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.BA_TESTICLE.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.BA_THROAT.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.BA_ASS.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.BA_ANUS.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.BA_URETHRA.getDiv());
            }
            UtilText.nodeContentSB.append(NewModOptions.ENABLE_PIERCINGS.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ENABLE_TATTOOS.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ENABLE_MAKEUP.getDiv());
            if (Configurations.DEGEN_MODE) {
                UtilText.nodeContentSB.append(NewModOptions.SHOTA_ENDOWMENT.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.OPPAI_LOLI.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.LOLI_FERTILITY.getDiv());
            }
//            UtilText.nodeContentSB.append(NewModOptions.VIRGIN_LOVER.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.IMP_HMULT.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.A_IMP_HMULT.getDiv());
            if (Configurations.DEGEN_MODE) {
                UtilText.nodeContentSB.append(NewModOptions.HEIGHT_DEVIATION.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.HEIGHT_AGE_CAP.getDiv());
            }
//            UtilText.nodeContentSB.append(NewModOptions.HETEROCHROMIA_CHANCE.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ORGASMIC_LEVEL_DRAIN_CHANCE.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.CHUUNI_CHANCE.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.NPC_PREG_CHANCE.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.ALWAYS_MODDED.getDiv());
            UtilText.nodeContentSB.append(NewModOptions.PREVENT_GIANTS.getDiv());
            return UtilText.nodeContentSB.toString();
        }

        @Override
        public String getContent() {
            return "";
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            return getModOptionsResponses(index);
        }

        @Override
        public DialogueNodeType getDialogueNodeType() {
            return DialogueNodeType.OPTIONS;
        }
    };

    public static final DialogueNode MOD_CHALLENGE_PREFERENCES = new DialogueNode("挑战设置", "", true) {
        @Override
        public String getHeaderContent() {
            if (EXPERIMENTAL) {
                UtilText.nodeContentSB.setLength(0);
                UtilText.nodeContentSB.append("注意：带有星号*标记的选项需要重启游戏或重新读档/新开游戏才能生效！");
                UtilText.nodeContentSB.append(NewModOptions.INVENTORY_TABS.getDiv());
                UtilText.nodeContentSB.append(NewModOptions.INVENTORY_ROWS.getDiv());


                return UtilText.nodeContentSB.toString();

            }
            return "此页面目前只能通过调试菜单启用EXPERIMENTAL（实验性）设置后才能访问。"
                    + "</br></br>将来，你将能够在这里调整各种设置，以增加难度或鼓励不同的游戏风格。";
        }
        @Override
        public String getContent() {
            return "";
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            return getModOptionsResponses(index);
        }
    };

    public static final DialogueNode ADVANCED_OPTIONS = new DialogueNode("高级设置", "", true) {
        @Override
        public String getHeaderContent() {
            UtilText.nodeContentSB.setLength(0);
            UtilText.nodeContentSB.append(paragraphWrap("请注意，其中一些设置只有在重启游戏后才会生效！", "#F77"));
            UtilText.nodeContentSB.append(AdvancedModOptions.EXPERIMENTAL.getDiv());
            UtilText.nodeContentSB.append(AdvancedModOptions.REPAIR_MODS.getDiv());
            UtilText.nodeContentSB.append(AdvancedModOptions.LOG_UNPARSED_LINES.getDiv());
            UtilText.nodeContentSB.append(AdvancedModOptions.CHECK_FOR_UPDATES.getDiv());
            UtilText.nodeContentSB.append(AdvancedModOptions.DRAKE_MODE.getDiv());
            UtilText.nodeContentSB.append(AdvancedModOptions.LOG_HEIGHT_CALCS.getDiv());
            UtilText.nodeContentSB.append(AdvancedModOptions.TIME_LOG_NPC.getDiv());
            UtilText.nodeContentSB.append(AdvancedModOptions.TIME_LOG_PRELOAD.getDiv());
            UtilText.nodeContentSB.append(AdvancedModOptions.LOG_VIRGINITY_RATES.getDiv());
            UtilText.nodeContentSB.append(AdvancedModOptions.ARCHER_CONTENT.getDiv());
            UtilText.nodeContentSB.append(AdvancedModOptions.LEXI_CONTENT.getDiv());
            UtilText.nodeContentSB.append(AdvancedModOptions.MULTITHREAD_NPC_GENERATION.getDiv());
            UtilText.nodeContentSB.append(AdvancedModOptions.MULTITHREADED_PRELOADING.getDiv());
            UtilText.nodeContentSB.append(AdvancedModOptions.BACKUP_SAVES.getDiv());

            return UtilText.nodeContentSB.toString();
        }
        @Override
        public String getContent() {
            return "";
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            return getModOptionsResponses(index);
        }
    };
}