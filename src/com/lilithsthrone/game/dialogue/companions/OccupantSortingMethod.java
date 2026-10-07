package com.lilithsthrone.game.dialogue.companions;

/**
 * @since 0.4.4.1
 * @version 0.4.9.1
 * @author Anonymous-BCFED
 */
public enum OccupantSortingMethod {
    NONE("无", "重置排序顺序，以默认价值的顺序显示。"),
    
    NAME("名称", "根据奴隶名称首字母顺序排序。"),

    FEMININITY("女性化程度", "根据奴隶女性化程度排序。"),
    
    RACE("种族", "根据奴隶种族名首字母顺序排序。"),
    
    ROOM("房间", "根据所在房间名的首字母顺序排序。"),
    
    VALUE("价值", "根据奴隶的售价排序。");
    
	
	private String name;
	private String sortingDescription;

	private OccupantSortingMethod(String name, String sortingDescription) {
		this.name = name;
		this.sortingDescription = sortingDescription;
	}

	public String getName() {
		return name;
	}

	public String getSortingDescription() {
		return sortingDescription;
	}
}