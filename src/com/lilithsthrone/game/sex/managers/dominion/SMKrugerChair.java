package com.lilithsthrone.game.sex.managers.dominion;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Kruger;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.69.9
 * @version 0.4.2.2
 * @author Innoxia
 */
public class SMKrugerChair extends SexManagerDefault {

	public SMKrugerChair(Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(SexPosition.SITTING,
				dominants,
				submissives);
	}
	
	@Override
	public List<AbstractSexPosition> getAllowedSexPositions() {
		return Util.newArrayListOfValues(
				SexPosition.SITTING);
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}

	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return !character.isPlayer();
	}

	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		Map<GameCharacter, List<CoverableArea>> map = Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kruger.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
		
		if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
			map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH));
		} else if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
			map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
		} else {
			map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS));
		}
		
		return map;
	}
	
	@Override
	public boolean isPublicSex() {
		return true;
	}

	@Override
	public String getPublicSexStartingDescription() {
		return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
				+ "围坐在展台周围的狮女和斑马女看着你即将被克鲁格操一边笑着一边抚摸着自己……"
				+ "</p>";
	}

	@Override
	public String getRandomPublicSexDescription() {
		List<String> descriptions = Util.newArrayListOfValues(
				"有只狮女掀起裙子，把丁字裤撇到一旁，看着你和克鲁格扣了起来。",
				"两个斑马女仰坐在沙发上，互相亲热了起来。",
				"你听见有几个围观的狮女正在评价你的表现。",
				"有个斑马女看着你跟克鲁格亲热，你忽然听见她口中冒出一声色情呻吟。",
				"围观你表演的姑娘们围在一旁，嘻嘻哈哈地笑了起来，不时冒出些下流的评论。",
				"你瞄了一眼，发现有几个女孩已经一边看者你和克鲁格一边抚摸起下体。",
				"你继续在人群面前和克鲁格做爱，有个斑马女突然呼和起来。",
				"你继续在人群面前和克鲁格做爱，有个狮女忽然嬉笑着评论起来。");
		
		if(Main.sex.getOrificesBeingPenetratedBy(Main.game.getNpc(Kruger.class), SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.MOUTH)) {
			descriptions.add("母狮跪在你面前，把你的头按在克鲁格的鸡巴上。她一边咯咯笑，一边强迫你吮吸克鲁格[kruger.cock+]。");
			descriptions.add("一对斑马女来到了你的两侧，抓住你的脑袋在克鲁格[kruger.cock+]上做活塞运动。");
			descriptions.add("其中一只母狮倾向前咆哮道，[genericFemale.speech(就是如此，烂货，认清你的地位。)]");
			descriptions.add("一只母狮向前俯身，把你的头按在克鲁格的鸡巴上，咆哮着：[genericFemale.speech(来吧！荡妇！你的技术应该比这更好的！)]");
			descriptions.add("其中一个斑马女坐回沙发，欢呼道：[genericFemale.speech(加油，克鲁格。狠狠地操那个婊子的嘴！)]");
			descriptions.add("一个斑马女靠在克鲁格身边，笑着对他说：[genericFemale.speech(哦，看呐，克鲁格！你让那个荡妇无地自处了！)]");
		}
		
		if(Main.sex.getOrificesBeingPenetratedBy(Main.game.getNpc(Kruger.class), SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
			descriptions.add("一只母狮走到你身后，咯咯笑着，抓住你的肩膀用力下压，将克鲁格[kruger.cock+]插入你[pc.pussy+]深处。");
			descriptions.add("一对斑马女走到你的身边，然后抓住你的下半身上下抽动，以此来让克鲁格[kruger.cock+]在你[pc.pussy+]内进进出出。");
			descriptions.add("其中一只母狮倾向前，咆哮道，[genericFemale.speech(你就是个放荡的鸡巴套子，对不对啊，婊子？)]");
			descriptions.add("一只母狮向前一扑，抓住你[pc.hips+]并推倒在克鲁格的鸡巴上，然后咆哮道：[genericFemale.speech(来吧！荡妇！来插一把<i>深的</i>。)]");
			descriptions.add("其中一个斑马女坐在沙发上欢呼道：[genericFemale.speech(来吧，克鲁格，狠狠地操那个婊子！)]");
			descriptions.add("一个斑马女靠在克鲁格身边，笑着鼓励道：[genericFemale.speech(哦，看呐，克鲁格！你让那个荡妇无地自处了！)]");
		}

		if(Main.sex.getOrificesBeingPenetratedBy(Main.game.getNpc(Kruger.class), SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.ANUS)) {
			descriptions.add("一只母狮走到你身后，咯咯笑着的，抓住你的肩膀用力下压，将克鲁格[kruger.cock+]插入你[pc.asshole+]深处。");
			descriptions.add("一对斑马女走到你的身边，抓住你的下半身上下抽动，让克鲁格[kruger.cock+]在你[pc.asshole+]内进进出出。");
			descriptions.add("其中一只母狮倾向前咆哮道，[genericFemale.speech(你就是个下流的荡妇，是不是啊，婊子？)]");
			descriptions.add("一只母狮向前一扑，抓住你[pc.hips+]并推倒在克鲁格的鸡巴上，然后咆哮道：[genericFemale.speech(来吧！荡妇！来插一把<i>深的</i>。)]");
			descriptions.add("其中一个斑马女坐在沙发上欢呼道：[genericFemale.speech(来吧，克鲁格，狠狠地操那婊子的屁股吧！)]");
			descriptions.add("一个斑马女靠在克鲁格身边，笑着鼓励道：[genericFemale.speech(哦，看呐，克鲁格！你让这个荡妇摆正了自己的位置！)]");
		}
		
		return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
					+Util.randomItemFrom(descriptions)
				+"</p>";
	}
	
	@Override
	public boolean isPartnerWantingToStopSex(GameCharacter partner) {
		return Main.sex.getNumberOfOrgasms(Main.game.getNpc(Kruger.class))>=2
				&& (Main.sex.getNumberOfOrgasms(Main.game.getPlayer())>=1 || Main.sex.getNumberOfOrgasms(Main.game.getNpc(Kruger.class))>=5);
	}
}
