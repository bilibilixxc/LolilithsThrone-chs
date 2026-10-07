package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class Cowgirl {
	
	//TODO These need proper formatting before inclusion into sex actions
	
	public static String getTongueMouthDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]向着[npc2.namePos][npc2.penis+]坐下去，发出[npc.a_moan+]。[npc.she]俯下身子，紧紧扣住了[npc2.namePos]的脑袋，将[npc2.name]拉入了一场深沉的热吻。",
					"[npc.Name]发出一声[npc.a_moan+]，顺着[npc2.namePos][npc.penis+]便坐了下去，随后俯身在[npc2.her]的怀中，呼吸着那[npc2.scent]，最后将自己[npc.lips+]对了上去。",
					"[npc.Name]让[npc2.namePos][npc2.penis+]滑入[npc.her][npc.asshole+]的更深处，随后俯下身子，将[npc2.name]拖入了一场激情的热吻。",
					"[npc.Name]俯下身去，完完整整地包裹住了[npc2.namePos][npc.penis+]，不免地发出[npc.a_moan+]，接着就立刻将自己[npc.lips+]压了上去。"));
			
		} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]向着[npc2.namePos][npc2.penis+]坐下去，感受到那挺物深深进入[npc.her][npc.pussy+]时，[npc.she]顿时冒出了快乐的尖叫。"
								+ "最后肉棒被整根吞入，[npc.she]俯下身子，紧紧扣住了[npc2.namePos]的脑袋，将[npc2.her]拉入了一场深沉的热吻。",
					"[npc.Name]发出[npc.a_moan+]，顺着[npc2.namePos][npc.penis+]便坐了下去，随后俯身在[npc2.her]的怀中，呼吸着那[npc2.scent]，最后将自己[npc.lips+]对了上去。",
					"[npc.Name]让[npc2.namePos][npc2.penis+]滑入[npc.her][npc.pussy+]的更深处，随后俯下身子，将[npc2.name]拖入了一场激情的热吻。",
					"[npc.Name]俯下身去，完完整整地包裹住了[npc2.namePos][npc.penis+]，不免地发出[npc.a_moan+]，接着就立刻将自己[npc.lips+]压了上去。"));
			
		} else {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name]俯下身子，紧紧扣住了[npc2.namePos]的脑袋，将[npc2.name]拉入了一场深沉的热吻。",
					"[npc.Name]露出一抹笑容，俯身投入[npc2.namePos]的[npc2.breasts]中，呼吸着那[npc2.scent]，最后将自己[npc.lips+]对了上去。",
					"[npc.Name]低身向前，口中冒出一声[npc.a_moan+]，紧接着将[npc.lips+]压了上去，开启了一段热烈的深吻。"));
		}

		switch(Main.sex.getSexPace(Main.game.getPlayer())) {
			case SUB_EAGER:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]欣然将[npc2.tongue]深深探入对方嘴里，渴望地将[npc2.lips+]越贴越紧，愉快地[npc2.moaning]着，贪婪地回应着[npc.her]的爱意。",
						"[npc2.name]发出急切的[npc2.moan]，相互摩挲着下体，[npc2.name]贪婪地将[npc2.tongue]突破了[npc.her][npc.lips+]，接连不断的[npc2.moans]化作了回响的闷声。",
						"[npc2.name]愉悦地[npc2.Moaning]着，下身不由自主地磨蹭起来，用[npc2.lips+]紧紧贴着对方，欣然将[npc2.tongue]探入了[npc.her]的口中。"));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]将[npc2.tongue]伸进对方口中，[npc2.lips+]也严丝合缝地贴了上去，热切地回应着[npc.her]的爱意。",
						"伴随着[npc2.a_moan]，[npc2.name]也将身子尽量靠上去，急切地将[npc2.tongue]突破了[npc.her][npc.lips+]，口中不断是模糊的嗯嗯啊啊声。",
						"[npc2.name]愉悦地[npc2.Moaning]着，将身子尽量靠上去，欣然将[npc2.tongue]送入了对方口中，[npc2.lips+]则紧紧贴在一起。"));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]想要躲开，一边[npc2.sobbing]一边难受地扭动着身子，抗拒着[npc.she]的强奸。",
						"[npc2.NamePos][npc2.sob+]在口中化作了一段模糊的闷响，[npc2.name]努力想要躲开，不安地晃动着身子， 抗拒着[npc.she]的强奸。",
						"伴随着一声[npc2.a_sob+]，[npc2.name]企图躲开[npc.herHim]的强吻，但却只是白费功夫，对方将[npc.tongue]强行挤过了[npc2.namePos]的[npc2.lips]，引得[npc2.name]不舒服扭动着身子，表达着抗拒。"));
				break;
			default:
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getStartingVaginalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]握住[npc2.namePos][npc2.cock+]，随后挪了挪身子，找到了个合适的位置。"
								+ "[npc.she]先是用那[npc2.cockHead+]在自己的阴唇之间挑弄了一会儿，便缓缓地放下身子，让[npc2.namePos][npc2.cock+]插入了[npc.her][npc.pussy+]，一小声[npc.moan]随之冒出。",
						"[npc.Name]挪到一个更合适的位置，把[npc2.namePos][npc2.cock+]那[npc2.cockHead+]对准了自己[npc.legs+]之间，随后便缓缓地、轻轻地坐了下去，"
								+ "让[npc2.namePos][npc2.cock+]没入了[npc.her][npc.pussy+]。"));
				break;
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]抓住了[npc2.namePos][npc2.cock+]，然后挪到了个更合适的位置。"
								+ "[npc.she]先是用那[npc2.cockHead+]在自己的阴唇之间挑弄了一会儿，便急切地放下身子，让[npc2.namePos][npc2.cock+]插入了[npc.her][npc.pussy+]，随之冒出一阵[npc.a_moan+]。",
						"[npc.Name]挪到一个更合适的位置，把[npc2.namePos][npc2.cock+]那[npc2.cockHead+]对准了自己[npc.legs+]之间，随后迫不及待地一下子就坐了下去，"
								+ "让[npc2.namePos][npc2.cock+]没入了[npc.her][npc.pussy+]。"));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]粗暴地抓住了[npc2.namePos][npc2.cock+]，随后挪了挪身子，找到了个合适的位置。"
								+ "[npc.she]先是用那[npc2.cockHead+]在自己的阴唇之间挑弄了一会儿，便硬是让[npc2.namePos][npc2.cock+]插入了[npc.her][npc.pussy+]，一声[npc.a_moan+]随之冒出。",
						"[npc.Name]挪到一个更合适的位置，把[npc2.namePos][npc2.cock+]那[npc2.cockHead+]对准了自己[npc.legs+]之间，随后不管不顾地一下子就坐了下去，"
								+ "让[npc2.namePos][npc2.cock+]没入了[npc.her][npc.pussy+]。"));
				break;
			default:
				break;
		}
		switch(Main.sex.getSexPace(Main.game.getPlayer())) {
			case SUB_EAGER:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.name]进入[npc.herHim]体内后，发出了[npc2.a_moan+]，接着就急切地向对方挺起腰身，开始操[npc.her][npc.pussy+]。",
						"伴随着一声[npc2.a_moan+]，[npc2.name]迫不及待地挺腰送向对方的下体，将那[npc2.cock+]没入[npc.her][npc.pussy+]，激烈地抽插起来。"));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.name]进入[npc.herHim]体内后，发出了一声[npc2.a_moan+]，接着就挺腰向对方撞去，开始操[npc.her][npc.pussy+]。",
						"伴随着一声[npc2.a_moan+]，[npc2.name]挺腰送向对方的下体，将那[npc2.cock+]没入[npc.her][npc.pussy+]抽插起来。"));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.she]强行让[npc2.namePos]的[npc2.cock]进入了自己体内，[npc2.Name]口中溢出一声[npc2.a_sob+]，拼命无力地反抗着，想要把[npc.herHim]从身上推开。",
						"[npc.Name]强行将[npc2.name]的[npc2.cock]深深插入自己[npc.pussy+]，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
				break;
			default:
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getRidingCockGentle() {
		return UtilText.returnStringAtRandom(
				"[npc.Name]用腿支撑着，缓缓地上下摆动身子，让[npc2.namePos][npc2.penis+]在自己[npc.pussy+]里进进出出，口中不断[npc.moansVerb+]着。",
				"[npc.Name]伏上前去，用[npc.hands]支撑柱一些身体的重量，坐在[npc2.namePos][npc2.penis+]上，缓缓地上下摆动身躯。"
						+ "[npc.she]把[npc.face+]凑向[npc2.name]，深吸了一口[npc2.namePos]的[npc2.scent]，这种令人上瘾的香气让[npc.she]不禁咬住了[npc.lip]。",
				"[npc.Name]用[npc.hands]支撑着膝盖，慢慢地将身子上提下潜，任[npc2.namePos][npc2.cock+]反复地刺入体内，轻声的[npc.moans]不断从口中冒出。",
				"[npc.Name]先是慢慢地抬起身子，随后再缓坐下去，任[npc2.namePos][npc2.penis+]反复刺入[npc.her][npc.pussy+]，轻声的[npc.moans]不断从口中冒出。");
	}
	
	public static String getRidingCockNormal() {
		return UtilText.returnStringAtRandom(
				"[npc.Name]用腿支撑着，上下摆动身子，让[npc2.namePos][npc2.penis+]在自己[npc.pussy+]里进进出出，口中不断[npc.moansVerb+]着。",
				"[npc.Name]伏上前去，用[npc.hands]支撑柱一些身体的重量，坐在[npc2.namePos][npc2.penis+]上，迫切地上下摆动起身躯。"
						+ "[npc.she]把[npc.face+]凑向[npc2.name]，深吸了一口[npc2.namePos]的[npc2.scent]，这种令人上瘾的香气让[npc.she]不禁咬住了[npc.lip]。",
				"[npc.Name]用[npc.hands]支撑着膝盖，急切地将身子上提下潜，任[npc2.namePos][npc2.cock+]反复地刺入体内，[npc.moans+]不断从口中冒出。",
				"[npc.Name]先是抬起身子，随后再贪婪地坐下去，任[npc2.namePos][npc2.penis+]反复刺入[npc.her][npc.pussy+]，[npc.a_moan+]不断从口中冒出。");
	}
	
	public static String getRidingCockRough() {
		return UtilText.returnStringAtRandom(
				"[npc.Name]用腿支撑，上下弹跳着，用自己[npc.pussy+]来来回回地撞向[npc2.namePos][npc2.penis+]，口中不断[npc.moansVerb+]着。",
				"[npc.Name]伏上前去，用[npc.hands]支撑柱一些身体的重量，坐在[npc2.namePos][npc2.penis+]上，激烈地上下摆动起身躯。"
						+ "[npc.she]把[npc.face+]凑向[npc2.name]，深吸了一口[npc2.namePos]的[npc2.scent]，这种令人上瘾的香气让[npc.she]不禁咬住了[npc.lip]。",
				"[npc.Name]把[npc.hands]撑在地上，随后便激烈地上下弹跳起来，任[npc2.namePos][npc2.cock+]反复地刺入体内，[npc.moans+]颤颤巍巍地从口中冒出。",
				"[npc.Name]俯视着[npc2.name]，投出了一抹笑容，随后便急切地上下弹跳起来，任[npc2.namePos][npc2.penis+]反复刺入[npc.her][npc.pussy+]，[npc.a_moan+]不断从口中冒出。");
	}
	
	public static String getStoppingVaginalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append("[npc.Name]一把捏住[npc2.namePos][npc2.cock]的根部，随后一抬身子，让[npc2.namePos][npc2.cock+]从自己[npc.pussy+]中抽了出来。");
				break;
			default:
				UtilText.nodeContentSB.append("[npc.Name]握住了[npc2.namePos][npc2.cock]的根部，随后一抬身子，让[npc2.namePos][npc2.cock+]从自己[npc.pussy+]中滑了出来。");
				break;
		}
		
		switch(Main.sex.getSexPace(Main.game.getPlayer())) {
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]松了口气似的叹了一声，但随后就意识到[npc.she]还远没有结束，顿时又发出[npc2.a_sob+]。",
						"随着一声[npc2.a_sob+]，[npc2.name]继续抗拒挣扎起来，但[npc.she]却仍然死死地控制着[npc2.name]。"));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.she]从骑乘位上下来后，[npc2.name]发出了[npc2.a_moan+]。",
						"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]还想继续抽插[npc.namePos][npc.pussy+]的渴望。"));
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getStartingAnalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]握住[npc2.namePos][npc2.cock+]，随后挪了挪身子，找到了个合适的位置。"
								+ "[npc.she]先是用那[npc2.cockHead+]在自己[npc.asshole+]上挑弄了一会儿，便缓缓地放下身子，让[npc2.namePos][npc2.cock+]插入了[npc.her][npc.ass+]，一小声[npc.moan]随之冒出。",
						"[npc.Name]挪到一个更合适的位置，把[npc2.namePos][npc2.cock+]那[npc2.cockHead+]对准了自己的屁股缝，随后便缓缓地、轻轻地坐了下去，"
								+ "让[npc2.namePos][npc2.cock+]没入了[npc.her][npc.asshole+]。"));
				break;
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]抓住了[npc2.namePos][npc2.cock+]，然后挪到了个更合适的位置。"
								+ "[npc.she]先是用那[npc2.cockHead+]在自己[npc.asshole+]上挑弄了一会儿，便急切地放下身子，让[npc2.namePos][npc2.cock+]插入了[npc.her][npc.ass+]，一声[npc.a_moan+]随之冒出。",
						"[npc.Name]挪到一个更合适的位置，把[npc2.namePos][npc2.cock+]那[npc2.cockHead+]对准了自己的屁股缝，随后迫不及待地一下子就坐了下去，"
								+ "让[npc2.namePos][npc2.cock+]没入了[npc.her][npc.asshole+]。"));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name]粗暴地抓住了[npc2.namePos][npc2.cock+]，随后挪了挪身子，找到了个合适的位置。"
								+ "[npc.she]先是用那[npc2.cockHead+]在自己[npc.asshole+]上面挑弄了一会儿，便硬是让[npc2.namePos][npc2.cock+]插入了[npc.her][npc.ass+]，一声[npc.a_moan+]随之冒出。",
						"[npc.Name]挪到一个更合适的位置，把[npc2.namePos][npc2.cock+]那[npc2.cockHead+]对准了自己的屁股缝，随后不管不顾地一下子就坐了下去，"
								+ "让[npc2.namePos][npc2.cock+]没入了[npc.her][npc.asshole+]。"));
				break;
			default:
				break;
		}
		switch(Main.sex.getSexPace(Main.game.getPlayer())) {
			case SUB_EAGER:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"当[npc2.name]进入[npc.herHim]时，[npc2.Name]发出[npc2.a_moan+]，急切地朝对方挺进自己的[npc2.hips]，开始操[npc.her][npc.asshole+]。",
						"伴随着一声[npc2.a_moan+]，[npc2.name]迫不及待地挺腰送向对方的[npc.ass]，将那[npc2.cock+]没入[npc.her][npc.asshole+]，激烈地抽插起来。"));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"当[npc2.name]进入[npc.herHim]时，[npc2.Name]发出[npc2.a_moan+]，朝对方挺进自己的[npc2.hips]，开始操[npc.her][npc.asshole+]。",
						"伴随着一声[npc2.a_moan+]，[npc2.name]挺腰送向对方的[npc.ass]，将那[npc2.cock+]没入[npc.her][npc.asshole+]抽插起来。"));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.she]强行让[npc2.namePos]的[npc2.cock]进入了自己体内，[npc2.Name]口中溢出一声[npc2.a_sob+]，拼命无力地反抗着，想要把[npc.herHim]从身上推开。",
						"[npc.Name]强行将[npc2.name]的[npc2.cock]深深插入自己[npc.asshole+]，[npc2.name]拼命反抗，发出一声[npc2.a_sob+]。"));
				break;
			default:
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getRidingCockAnallyGentle() {
		return UtilText.returnStringAtRandom(
				"[npc.Name]用腿支撑着，缓缓地上下摆动身子，让[npc2.namePos][npc2.penis+]在自己[npc.asshole+]里进进出出，口中不断[npc.moansVerb+]着。",
				"[npc.Name]伏上前去，用[npc.hands]支撑柱一些身体的重量，坐在[npc2.namePos][npc2.penis+]上，缓缓地上下摆动身躯。"
						+ "[npc.she]把[npc.face+]凑向[npc2.name]，深吸了一口[npc2.namePos]的[npc2.scent]，这种令人上瘾的香气让[npc.she]不禁咬住了[npc.lip]。",
				"[npc.Name]用[npc.hands]支撑着膝盖，慢慢地将身子上提下潜，任[npc2.namePos][npc2.cock+]反复地刺入体内，轻声的[npc.moans]不断从口中冒出。",
				"[npc.Name]先是慢慢地抬起身子，随后再缓坐下去，任[npc2.namePos][npc2.penis+]反复刺入[npc.her][npc.asshole+]，轻声的[npc.moans]不断从口中冒出。");
	}
	
	public static String getRidingCockAnallyNormal() {
		return UtilText.returnStringAtRandom(
				"[npc.Name]用腿支撑着，上下摆动身子，让[npc2.namePos][npc2.penis+]在自己[npc.asshole+]里进进出出，口中不断[npc.moansVerb+]着。",
				"[npc.Name]伏上前去，用[npc.hands]支撑柱一些身体的重量，坐在[npc2.namePos][npc2.penis+]上，迫切地上下摆动起身躯。"
						+ "[npc.she]把[npc.face+]凑向[npc2.name]，深吸了一口[npc2.namePos]的[npc2.scent]，这种令人上瘾的香气让[npc.she]不禁咬住了[npc.lip]。",
				"[npc.Name]用[npc.hands]支撑着膝盖，急切地将身子上提下潜，任[npc2.namePos][npc2.cock+]反复地刺入体内，[npc.moans+]不断从口中冒出。",
				"[npc.Name]先是抬起身子，随后再贪婪地坐下去，任[npc2.namePos][npc2.penis+]反复刺入[npc.her][npc.asshole+]，[npc.a_moan+]不断从口中冒出。");
	}
	
	public static String getRidingCockAnallyRough() {
		return UtilText.returnStringAtRandom(
				"[npc.Name]用腿支撑，上下弹跳着，用自己[npc.asshole+]来来回回地撞向[npc2.namePos][npc2.penis+]，口中不断[npc.moansVerb+]着。",
				"[npc.Name]伏上前去，用[npc.hands]支撑柱一些身体的重量，坐在[npc2.namePos][npc2.penis+]上，激烈地上下摆动起身躯。"
						+ "[npc.she]把[npc.face+]凑向[npc2.name]，深吸了一口[npc2.namePos]的[npc2.scent]，这种令人上瘾的香气让[npc.she]不禁咬住了[npc.lip]。",
				"[npc.Name]把[npc.hands]撑在地上，随后便激烈地上下弹跳起来，任[npc2.namePos][npc2.cock+]反复地刺入体内，[npc.moans+]颤颤巍巍地从口中冒出。",
				"[npc.Name]俯视着[npc2.name]，投出了一抹笑容，随后便急切地上下弹跳起来，任[npc2.namePos][npc2.penis+]反复刺入[npc.her][npc.asshole+]，[npc.a_moan+]不断从口中冒出。");
	}
	
	public static String getStoppingAnalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append("[npc.Name]一把捏住[npc2.namePos][npc2.cock]的根部，随后一抬身子，让[npc2.namePos][npc2.cock+]从自己[npc.asshole+]中抽了出来。");
				break;
			default:
				UtilText.nodeContentSB.append("[npc.Name]握住了[npc2.namePos][npc2.cock]的根部，随后一抬身子，让[npc2.namePos][npc2.cock+]从自己[npc.asshole+]中滑了出来。");
				break;
		}
		
		switch(Main.sex.getSexPace(Main.game.getPlayer())) {
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]松了口气似的叹了一声，但随后就意识到[npc.she]还远没有结束，顿时又发出[npc2.a_sob+]。",
						"随着一声[npc2.a_sob+]，[npc2.name]继续抗拒挣扎起来，但[npc.she]却仍然死死地控制着[npc2.name]。"));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.she]从骑乘位上下来后，[npc2.name]发出了[npc2.a_moan+]。",
						"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]还想继续抽插[npc.namePos][npc.ass+]的渴望。"));
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}



}
