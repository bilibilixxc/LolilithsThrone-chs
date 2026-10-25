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


    public static final DialogueNode LINKS_OUT = new DialogueNode("链接", "链接", true) {

        @Override
        public String getLabel() {
            return "";
        }

        @Override
        public String getContent() {
            return "<br/>"
                    + paragraphWrap("你可以通过下方任意链接联系我（除了我的网站）", "#0CC")
                    + paragraphWrap("或者通过Discord @keldonslayer联系我，或发送邮件到 theloliphile@gmail.com", "#0CC")
                    + paragraphWrap("<b style='#0CC;'>请使用我的网站或GitHub获取莉莉王座（Lilith's Throne）最新的<span style='color:#FD0'>修改版</span>！</b>")
                    + (Library.newErrorLog ? paragraphWrap("你有一份新的错误日志文件位于 /data/ 目录下，如果遇到UI卡死，请在高级设置中检查 NPC 多线程选项！", "#F44") : "")
                    + (MULTITHREAD_NPC_GENERATION ? "" : paragraphWrap("多线程已被禁用。在104版本中我已对其进行了改进，如果你在104版本之前禁用了它，请再试一次！"));
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            return switch (index) {
                case 1 -> new ResponseEffectsOnly("我的网站", "打开页面：<br/><br/><i>https://keldon.ninja/downloads/lilith.php</i><br/><br/><b>将在你的默认浏览器中打开。</b>"){
                    @Override
                    public void effects() {
                        Util.openLinkInDefaultBrowser("https://keldon.ninja/downloads/lilith.php");
                    }
                };
                case 2 -> new ResponseEffectsOnly("GitHub Issues", "打开页面：<br/><br/><i>https://github.com/DegenDomain/LolilithsThroneIssueTracker/issues</i><br/><br/><b>将在你的默认浏览器中打开。</b>"){
                    @Override
                    public void effects() {
                        Util.openLinkInDefaultBrowser("https://github.com/DegenDomain/LolilithsThroneIssueTracker/issues");
                    }
                };
                case 3 -> new ResponseEffectsOnly("F95Zone", "打开页面：<br/><br/><i>https://f95zone.to/members/loliphile.15993/</i><br/><br/><b>将在你的默认浏览器中打开。</b>"){
                    @Override
                    public void effects() {
                        Util.openLinkInDefaultBrowser("https://f95zone.to/members/loliphile.15993/");
                    }
                };
                case 4 -> new ResponseEffectsOnly("AllTheFallen", "打开页面：<br/><br/><i>https://allthefallen.moe/forum/index.php?members/loliphile.5425/</i><br/><br/><b>将在你的默认浏览器中打开。</b>"){
                    @Override
                    public void effects() {
                        Util.openLinkInDefaultBrowser("https://allthefallen.moe/forum/index.php?members/loliphile.5425/");
                    }
                };
                case 5 -> new ResponseEffectsOnly("Lewd Corner", "打开页面：<br/><br/><i>https://lewdcorner.com/members/loliphile.136155/</i><br/><br/><b>将在你的默认浏览器中打开。</b>"){
                    @Override
                    public void effects() {
                        Util.openLinkInDefaultBrowser("https://lewdcorner.com/members/loliphile.136155/");
                    }
                };
                case 0 -> new Response("返回", "返回主菜单。", MenuOverrides.MENU);
                default -> null;
            };
        }

        @Override
        public DialogueNodeType getDialogueNodeType() {
            return DialogueNodeType.OPTIONS;
        }
    };

}