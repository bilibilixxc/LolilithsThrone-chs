package com.lilithsthrone.game.sex.sexActions.submission;

import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.3.5
 * @version 0.3.5
 * @author Innoxia
 */
public class SAClaireDangerSex {

	public static final SexAction PARTNER_INTERRUPTED = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().getWorldLocation()==WorldType.SUBMISSION
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Claire.class))
					&& !SexFlags.claireSexInterrupted
					&& Main.sex.getTurn() - SexFlags.claireSexInterruptedTurn > 4
					&& !Main.sex.isInForeplay(Main.game.getNpc(Claire.class))
					&& Main.game.getPlayer().getArousal()<75
					&& Main.game.getNpc(Claire.class).getArousal()<75
					&& Math.random()<0.25f;
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "门开着呢！";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return UtilText.parseFromXMLFile("characters/submission/claire", "PARTNER_INTERRUPTED");
		}
		@Override
		public void applyEffects() {
			SexFlags.claireSexInterrupted = true;
			SexFlags.claireSexInterruptedTurn = Main.sex.getTurn();
		}
	};
	
	public static final SexAction PLAYER_STAY_QUIET = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& SexFlags.claireSexInterrupted;
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "保持安静";
		}
		@Override
		public String getActionDescription() {
			return "按照克莱尔说的做，保持安静，直到执法者离开。";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("characters/submission/claire", "ENFORCER_ENTERING"));
			
			boolean foundPenetration = false;
			Map<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> map = Main.sex.getOngoingActionsMap(Main.game.getPlayer());
			if(map.containsKey(SexAreaPenetration.PENIS)
					&& map.get(SexAreaPenetration.PENIS).containsKey(Main.game.getNpc(Claire.class))) {
				SexAreaInterface area = map.get(SexAreaPenetration.PENIS).get(Main.game.getNpc(Claire.class)).iterator().next();
				if(area.isOrifice()) {
					foundPenetration = true;
					sb.append("<br/><br/>");
					sb.append("当执法者开始搜查储藏室时，你保持完全静止并沉默，突然你意识到自己[pc.cock+] ");
					switch((SexAreaOrifice)area) {
						case ANUS:
							sb.append("在克莱尔[claire.asshole]深处跳动着。"
									+ "现况使她异常兴奋，这只淫荡的猫女慢慢地将[claire.ass+]向后推，让你的鸡巴更深入[claire.asshole]中。");
							break;
						case ASS:
							sb.append("在克莱尔[claire.assSize]的臀瓣间抽插。"
									+ "现况使她异常兴奋，这只淫荡的猫女慢慢地向后顶她[claire.hips+]，使阴茎在她的[claire.asshole]里前后抽插。");
							break;
						case BREAST:
							sb.append("在克莱尔[claire.breastSize]的奶子间抽插。"
									+ "现况使她异常兴奋，这只淫荡的猫女慢慢地将[claire.breasts+]挤到一起，让你的阴茎夹在乳沟内。");
							break;
						case BREAST_CROTCH:
							sb.append("在克莱尔[claire.crotchBoobSize]的[claire.crotchBoobs]间抽插。"
									+ "现况使她兴奋得发狂，这只淫荡的猫女慢慢地把[claire.crotchBoobs+]挤到一起，把你的阴茎包进她的乳沟里。");
							break;
						case MOUTH:
							sb.append("在克莱尔的嘴里抽插。"
									+ "现况使她兴奋得发狂，这只淫荡的猫女慢慢地将头向前凑，令阴茎深深地插入喉中。");
							break;
						case NIPPLE:
							sb.append("深深插入克莱尔的[claire.nipple(true)]。"
									+ "处境使你们纷纷陷入癫狂似的兴奋当中。淫荡的猫女慢慢地将胸顶向你，让你的阴茎更深地插入[claire.nipple+]中。");
							break;
						case NIPPLE_CROTCH:
							sb.append("深深插入克莱尔的[claire.crotchNipple]。"
									+ "处境使你们纷纷陷入癫狂似的兴奋当中。淫荡的猫女慢慢地将腹部顶向你，让你的阴茎更深地插入[claire.crotchNipple+]中。");
							break;
						case THIGHS:
							sb.append("在克莱尔的丰臀内抽插。"
									+ "处境使你们纷纷陷入癫狂似的兴奋当中。淫荡的猫女慢慢地将[claire.hips+]顶向你，让你的阴茎更深地插入腿间。");
							break;
						case URETHRA_PENIS:
							break;
						case URETHRA_VAGINA:
							sb.append("深深插入克莱尔的[claire.urethraVagina]。"
									+ "处境使你们纷纷陷入癫狂似的兴奋当中。淫荡的猫女慢慢地[claire.hips+]顶向你，让你的阴茎更深地插入[claire.urethraVagina]。");
							break;
						case VAGINA:
							sb.append("深深插入克莱尔的[claire.pussy+]。"
									+ "处境使你们纷纷陷入癫狂似的兴奋当中。淫荡的猫女慢慢地[claire.hips+]顶向你，让你的阴茎更深地插入[claire.pussy+]里。");
							break;
						case ARMPITS:
							sb.append("抵着克莱尔的腋窝抽插。"
									+ "处境使你们纷纷陷入癫狂似的兴奋当中。淫荡的猫女抵着你磨蹭着，用[claire.armpit+]搓弄着你的阴茎。");
							break;
						case SPINNERET:
							break;
					}
					sb.append("<br/><br/>"
							+ "就在你即将攀上快感高峰之际，执法者找到了要找的东西，他们没有发现你们正在做爱，就像进来时那样迅速离开了。");
				}
			}
			
			if(!foundPenetration) {
				sb.append("<br/><br/>"
						+ "在令人紧张的三十多秒后，执法者终于找到了要找的东西，没有发现正在做爱的你们，就像进来时那样迅速离开了。");
			}
			sb.append("你和克莱尔松了一口气，回到之前的位置，继续工作……");

			return sb.toString();
		}
		@Override
		public void applyEffects() {
			SexFlags.claireSexInterrupted = false;
		}
	};
	

	public static final SexAction PLAYER_KEEP_GOING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			boolean foundPenetration = false;
			Map<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> map = Main.sex.getOngoingActionsMap(Main.game.getPlayer());
			if(map.containsKey(SexAreaPenetration.PENIS) && map.get(SexAreaPenetration.PENIS).containsKey(Main.game.getNpc(Claire.class))) {
				foundPenetration = map.get(SexAreaPenetration.PENIS).get(Main.game.getNpc(Claire.class)).iterator().next().isOrifice();
			}
			return foundPenetration
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& SexFlags.claireSexInterrupted;
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "继续";
		}
		@Override
		public String getActionDescription() {
			return "不理会克莱尔保持安静的请求，继续操她。";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("characters/submission/claire", "ENFORCER_ENTERING"));
			
			Map<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> map = Main.sex.getOngoingActionsMap(Main.game.getPlayer());
			SexAreaInterface area = map.get(SexAreaPenetration.PENIS).get(Main.game.getNpc(Claire.class)).iterator().next();
			if(area.isOrifice()) {
				sb.append("<br/><br/>");
				sb.append("你觉得没理由按克莱尔说的做，便忽略了有执法者在储藏室内搜查的事实，继续");
				switch((SexAreaOrifice)area) {
					case ANUS:
						sb.append("把你[pc.cock+]插入克莱尔[claire.asshole+]里前后抽插。"
								+ "这只淫荡的猫女用双手捂住嘴巴，不愿被人发现。你继续用力地操着她的屁股，而她用尽全力按耐着呻吟声。");
						break;
					case ASS:
						sb.append("把你[pc.cock+]插入克莱尔[claire.assSize]屁股的臀瓣里前后抽插。"
								+ "这只淫荡的猫女用双手捂住嘴巴，不愿被人发现。你继续用力地蹂躏她的臀沟，而她用尽全力按耐着呻吟声。");
						break;
					case BREAST:
						sb.append("把你[pc.cock+]插到克莱尔[claire.breastSize]奶子间前后抽插。"
								+ "这只淫荡的猫女用双手捂住嘴巴，不愿被人发现。你继续用力地蹂躏她的乳沟，而她用尽全力按耐着呻吟声。");
						break;
					case BREAST_CROTCH:
						sb.append("把你[pc.cock+]插到克莱尔[claire.crotchBoobSize]的[claire.crotchBoobs]间前后抽插。"
								+ "这只淫荡的猫女用双手捂住嘴巴，不愿被人发现。你继续用力地蹂躏她的胯乳沟，而她用尽全力按耐着呻吟声。");
						break;
					case MOUTH:
						sb.append("把你[pc.cock+]插到克莱尔的喉咙里前后抽插。"
								+ "这只淫荡的猫女不愿被人发现。你继续用力地操着她的嘴，而她用尽全力按耐着呻吟声。");
						break;
					case NIPPLE:
						sb.append("把你[pc.cock+]插到克莱尔[claire.nipple(true)+]里前后抽插。"
								+ "这只淫荡的猫女用双手捂住嘴巴，不愿被人发现。你继续用力地蹂躏她的[claire.nipple(true)]，而她用尽全力按耐着呻吟声。");
						break;
					case NIPPLE_CROTCH:
						sb.append("把你[pc.cock+]插到克莱尔[claire.crotchNipple(true)+]里前后抽插。"
								+ "这只淫荡的猫女用双手捂住嘴巴，不愿被人发现。你继续用力地蹂躏她的[claire.crotchNipple(true)]，而她用尽全力按耐着呻吟声。");
						break;
					case THIGHS:
						sb.append("把你[pc.cock+]插在克莱尔丰臀里前后抽插。"
								+ "这只淫荡的猫女用双手捂住嘴巴，不愿被人发现。你继续用力地蹂躏她的臀沟，而她用尽全力按耐着呻吟声。");
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						sb.append("把你[pc.cock+]插进克莱尔[claire.urethraVagina]里前后抽插。"
								+ "这只淫荡的猫女用双手捂住嘴巴，不愿被人发现。你继续用力地操着她[claire.urethraVagina]，而她用尽全力按耐着呻吟声。");
						break;
					case VAGINA:
						sb.append("用你[pc.cock+]在克莱尔[claire.pussy+]里前后抽动。"
								+ "这只淫荡的猫女用双手捂住嘴巴，不愿被人发现。你继续用力地操着她的[claire.pussy]，而她用尽全力按耐着呻吟声。");
						break;
					case ARMPITS:
						sb.append("掏出你[pc.cock+]上上下下蹭着克莱尔的[claire.armpit]。"
								+ "淫荡的猫女不想被人发现，便用手捂住嘴巴，尽力保持着安静。你继续无情地操弄她[claire.armpit+]。");
						break;
					case SPINNERET:
						break;
				}
				sb.append("<br/><br/>"
						+ "就在这位丰满的猫女似乎再也无法抑制的时候，执法者找到了他们要找的东西，他们没有发现你们两个正在交媾，就像进来时一样迅速离开了。");
			}
			
			sb.append("克莱尔终于打破了沉默，色情的呻吟脱口而出，[claire.speechNoEffects(~啊哈啊！~ [pc.Name]！~哦呜！~我说、我说你停一下！)]");
			sb.append("<br/><br/>");
			sb.append("你告诉你的性对象，你之前太上头了，没法按照她的要求去做。然后你向她露出一道腼腆的微笑，回到之前的位置，迅速投入进工作中……");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			SexFlags.claireSexInterrupted = false;
		}
	};
}
