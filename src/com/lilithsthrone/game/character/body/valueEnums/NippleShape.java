package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.3.21
 * @author Innoxia
 */
public enum NippleShape {

	NORMAL("普通", false),
	
	INVERTED("内陷", false),
	
	VAGINA("乳穴", true),
	
	LIPS("乳唇", true);
	
	private String descriptor;
	private boolean associatedWithPenetrationContent;

	private NippleShape(String descriptor, boolean associatedWithPenetrationContent) {
		this.descriptor = descriptor;
		this.associatedWithPenetrationContent = associatedWithPenetrationContent;
	}

	public String getName() {
		return descriptor;
	}

	public boolean isAssociatedWithPenetrationContent() {
		return associatedWithPenetrationContent;
	}

}
