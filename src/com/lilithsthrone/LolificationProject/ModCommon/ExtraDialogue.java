package com.lilithsthrone.LolificationProject.ModCommon;

import com.lilithsthrone.LolificationProject.Library;
import com.lilithsthrone.LolificationProject.Overrides.MenuOverrides;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.utils.Util;

import static com.lilithsthrone.LolificationProject.ConfigStuff.Configurations.MULTITHREAD_NPC_GENERATION;
import static com.lilithsthrone.LolificationProject.ModCommon.MiscStuff.paragraphWrap;

public class ExtraDialogue {
    /*
                    + "<p style='text-align:center'>"
                    + "You can check for updates at  (<a href='https://keldon.ninja/downloads/lilith.php' style='color:#0CC'>my website</a>)"
                    + "</p>"
                    + "<p style='text-align:center'>"
                    + "To submit issues use <a href='https://github.com/DegenDomain/LolilithsThroneIssueTracker/issues' style='color:#0CC'>
                    * The Github Issue Tracker</a> or DM me on <a href='https://f95zone.to/members/loliphile.15993/'
                    *  style='color:#0CC'>F95zone</a>, <a href='https://allthefallen.moe/forum/index.php?members/loliphile.5425/'
                    *  style='color:#0CC'>AllTheFallen</a>, <a href='https://lewdcorner.com/members/loliphile.136155/'
                    * style='color:#0CC'>LewdCorner</a>,
                    *  or Discord @keldonslayer"
                    + "</p>"
    **/


    public static final DialogueNode LINKS_OUT = new DialogueNode("Links", "Links", true) {

        @Override
        public String getLabel() {
            return "";
        }

        @Override
        public String getContent() {
            return "<br/>"
                    + paragraphWrap("You can contact me at any of the links below (except my site)", "#0CC")
                    + paragraphWrap("Alternatively on discord @keldonslayer, or email me theloliphile@gmail.com", "#0CC")
                    + paragraphWrap("<b style='#0CC;'>Please use either my website or github to get the latest <span style='color:#FD0'>modified</span> version of Lilith's Throne!</b>")
                    + (Library.newErrorLog ? paragraphWrap("You have a new error log file in /data/, If you had a UI Freeze check Advanced Settings for NPC Multithreading!", "#F44") : "")
                    + (MULTITHREAD_NPC_GENERATION ? "" : paragraphWrap("Multithreading is Disabled, In version 104 I have improved it, if you disabled before version 104, try it out again!"));
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            return switch (index) {
                case 1 -> new ResponseEffectsOnly("My Website", "Opens the page:<br/><br/><i>https://keldon.ninja/downloads/lilith.php</i><br/><br/><b>Externally in your default browser.</b>"){
                    @Override
                    public void effects() {
                        Util.openLinkInDefaultBrowser("https://keldon.ninja/downloads/lilith.php");
                    }
                };
                case 2 -> new ResponseEffectsOnly("Github Issues", "Opens the page:<br/><br/><i>https://github.com/DegenDomain/LolilithsThroneIssueTracker/issues</i><br/><br/><b>Externally in your default browser.</b>"){
                    @Override
                    public void effects() {
                        Util.openLinkInDefaultBrowser("https://github.com/DegenDomain/LolilithsThroneIssueTracker/issues");
                    }
                };
                case 3 -> new ResponseEffectsOnly("F95Zone", "Opens the page:<br/><br/><i>https://f95zone.to/members/loliphile.15993/</i><br/><br/><b>Externally in your default browser.</b>"){
                    @Override
                    public void effects() {
                        Util.openLinkInDefaultBrowser("https://f95zone.to/members/loliphile.15993/");
                    }
                };
                case 4 -> new ResponseEffectsOnly("AllTheFallen", "Opens the page:<br/><br/><i>https://allthefallen.moe/forum/index.php?members/loliphile.5425/</i><br/><br/><b>Externally in your default browser.</b>"){
                    @Override
                    public void effects() {
                        Util.openLinkInDefaultBrowser("https://allthefallen.moe/forum/index.php?members/loliphile.5425/");
                    }
                };
                case 5 -> new ResponseEffectsOnly("Lewd Corner", "Opens the page:<br/><br/><i>https://lewdcorner.com/members/loliphile.136155/</i><br/><br/><b>Externally in your default browser.</b>"){
                    @Override
                    public void effects() {
                        Util.openLinkInDefaultBrowser("https://lewdcorner.com/members/loliphile.136155/");
                    }
                };
                case 0 -> new Response("Back", "Go back to the main menu.", MenuOverrides.MENU);
                default -> null;
            };
        }

        @Override
        public DialogueNodeType getDialogueNodeType() {
            return DialogueNodeType.OPTIONS;
        }
    };

}