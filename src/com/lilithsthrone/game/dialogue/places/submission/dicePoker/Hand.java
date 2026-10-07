package com.lilithsthrone.game.dialogue.places.submission.dicePoker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public enum Hand {
	
	ONE_FIVE_OF_A_KIND(1, "五条", "33333", "五枚同点数的骰子。"),
	TWO_FOUR_OF_A_KIND(2, "四条", "55551", "四枚同点数的骰子。"),
	THREE_HIGH_STRAIGHT(3, "大顺子", "23456", "从二开始的连续数字。"),
	FOUR_FULL_HOUSE(4, "葫芦", "22666", "两枚同点数的骰子，加上三枚同点数，但与之前点数相异的骰子。"),
	FIVE_THREE_OF_A_KIND(5, "三条", "44415", "三枚同点数的骰子。"),
	SIX_LOW_STRAIGHT(6, "小顺子", "12345", "从一开始的连续数字。"),
	SEVEN_TWO_PAIR(7, "两对", "55331", "两枚同点数的骰子，加上两枚同点数，但与之前点数相异的骰子。"),
	EIGHT_PAIR(8, "一对", "22415", "两枚同点数的骰子。"),
	NINE_RUNT(9, "烂手", "13456", "所有骰子点数都不同。");
	
	
	private int ranking;
	private String name;
	private String example;
	private String exampleDescription;
	
	private Hand(int ranking, String name, String example, String exampleDescription) {
		this.ranking = ranking;
		this.name = name;
		this.example = example;
		this.exampleDescription = exampleDescription;
	}
	

	/**
	 * @return negative value for player losing, 0 for draw, positive value for player winning
	 */
	public static int compareHands(List<Dice> playerDice, List<Dice> gamblerDice) {
		Hand playerHand = getHand(playerDice);
		Hand gamblerHand = getHand(gamblerDice);
		
		if(playerHand.getRanking()==gamblerHand.getRanking()) {
			return  getValue(playerDice) - getValue(gamblerDice);
		}
		
		return gamblerHand.getRanking() - playerHand.getRanking();
	}
	
	public static Hand getHand(List<Dice> dice) {
		Map<DiceFace, Integer> faceCount = new HashMap<>();
		for(Dice d : dice) {
			faceCount.putIfAbsent(d.getFace(), 0);
			faceCount.put(d.getFace(), faceCount.get(d.getFace())+1);
		}
		
		if(faceCount.size()==1) {
			return Hand.ONE_FIVE_OF_A_KIND;
		
		} else if(faceCount.containsValue(4)) {
			return Hand.TWO_FOUR_OF_A_KIND;
			
		} else if(faceCount.containsKey(DiceFace.TWO)
				&& faceCount.containsKey(DiceFace.THREE)
				&& faceCount.containsKey(DiceFace.FOUR)
				&& faceCount.containsKey(DiceFace.FIVE)
				&& faceCount.containsKey(DiceFace.SIX)) {
			return Hand.THREE_HIGH_STRAIGHT;
			
		} else if(faceCount.containsValue(2) && faceCount.containsValue(3)) {
			return Hand.FOUR_FULL_HOUSE;
			
		} else if(faceCount.containsValue(3)) {
			return Hand.FIVE_THREE_OF_A_KIND;
			
		} else if(faceCount.containsKey(DiceFace.ONE)
				&& faceCount.containsKey(DiceFace.TWO)
				&& faceCount.containsKey(DiceFace.THREE)
				&& faceCount.containsKey(DiceFace.FOUR)
				&& faceCount.containsKey(DiceFace.FIVE)) {
			return Hand.SIX_LOW_STRAIGHT;
			
		} else if(faceCount.containsValue(2) && faceCount.size()==3) {
			return Hand.SEVEN_TWO_PAIR;
			
		} else if(faceCount.containsValue(2)) {
			return Hand.EIGHT_PAIR;
		}
		
		return Hand.NINE_RUNT;
	}
	
	public static List<Dice> getDiceInHand(List<Dice> dice) {
		List<Dice> diceInHand = new ArrayList<>(dice);
		Map<DiceFace, Integer> faceCount = new HashMap<>();
		for(Dice d : dice) {
			faceCount.putIfAbsent(d.getFace(), 0);
			faceCount.put(d.getFace(), faceCount.get(d.getFace())+1);
		}
		
		if(faceCount.size()==1) {
		
		} else if(faceCount.containsValue(4)) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()!=4) {
					diceInHand.removeIf((d)->d.getFace()==entry.getKey());
				}
			}
			
		} else if(faceCount.containsKey(DiceFace.TWO)
				&& faceCount.containsKey(DiceFace.THREE)
				&& faceCount.containsKey(DiceFace.FOUR)
				&& faceCount.containsKey(DiceFace.FIVE)
				&& faceCount.containsKey(DiceFace.SIX)) {
			
		} else if(faceCount.containsValue(2) && faceCount.containsValue(3)) {
			
		} else if(faceCount.containsValue(3)) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()!=3) {
					diceInHand.removeIf((d)->d.getFace()==entry.getKey());
				}
			}
			
		} else if(faceCount.containsKey(DiceFace.ONE)
				&& faceCount.containsKey(DiceFace.TWO)
				&& faceCount.containsKey(DiceFace.THREE)
				&& faceCount.containsKey(DiceFace.FOUR)
				&& faceCount.containsKey(DiceFace.FIVE)) {
			
		} else if(faceCount.containsValue(2) && faceCount.size()==3) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()!=2) {
					diceInHand.removeIf((d)->d.getFace()==entry.getKey());
				}
			}
			
		} else if(faceCount.containsValue(2)) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()!=2) {
					diceInHand.removeIf((d)->d.getFace()==entry.getKey());
				}
			}
		}

		return diceInHand;
	}
	
	public static int getValue(List<Dice> dice) {
		Map<DiceFace, Integer> faceCount = new HashMap<>();
		for(Dice d : dice) {
			faceCount.putIfAbsent(d.getFace(), 0);
			faceCount.put(d.getFace(), faceCount.get(d.getFace())+1);
		}
		
		if(faceCount.size()==1) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				return entry.getKey().getValue()*5;
			}
		
		} else if(faceCount.containsValue(4)) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()==4) {
					return entry.getKey().getValue()*4;
				}
			}
			
		} else if(faceCount.containsKey(DiceFace.TWO)
				&& faceCount.containsKey(DiceFace.THREE)
				&& faceCount.containsKey(DiceFace.FOUR)
				&& faceCount.containsKey(DiceFace.FIVE)
				&& faceCount.containsKey(DiceFace.SIX)) {
			return 2+3+4+5+6;
			
		} else if(faceCount.containsValue(2) && faceCount.containsValue(3)) {
			int total=0;
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				total += entry.getKey().getValue()*entry.getValue();
			}
			return total;
			
		} else if(faceCount.containsValue(3)) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()==3) {
					return entry.getKey().getValue()*3;
				}
			}
			
		} else if(faceCount.containsKey(DiceFace.ONE)
				&& faceCount.containsKey(DiceFace.TWO)
				&& faceCount.containsKey(DiceFace.THREE)
				&& faceCount.containsKey(DiceFace.FOUR)
				&& faceCount.containsKey(DiceFace.FIVE)) {
			return 1+2+3+4+5;
			
		} else if(faceCount.containsValue(2) && faceCount.size()==3) {
			int total=0;
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()==2) {
					total+= entry.getKey().getValue()*2;
				}
			}
			return total;
			
		} else if(faceCount.containsValue(2)) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()==2) {
					return entry.getKey().getValue()*2;
				}
			}
		}
		
		int total=0;
		for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
			total += entry.getKey().getValue()*entry.getValue();
		}
		return total;
	}
	
	public int getRanking() {
		return ranking;
	}

	public String getName() {
		return name;
	}

	public String getExample() {
		return example;
	}

	public String getExampleDescription() {
		return exampleDescription;
	}
	
}
