package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Ear implements BodyPartInterface {
	
	protected AbstractEarType type;
	public boolean pierced;

	public Ear(AbstractEarType type) {
		this.type = type;
		pierced = false;
	}

	public Ear(Ear earToCopy) {
		this.type = earToCopy.type;
		this.pierced = earToCopy.pierced;
	}
	
	@Override
	public AbstractEarType getType() {
		return type;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
	}

	@Override
	public String getName(GameCharacter gc) {
		return type.getName(gc);
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		return type.getNamePlural(gc);
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		return type.getDescriptor(gc);
	}
	
	public String setType(GameCharacter owner, AbstractEarType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type.equals(getType())) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]已经有[npc.a_earRace]的[npc.ears]了，所以无事发生……)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();

		sb.append("<p>");
		if(owner.isArmMovementHindered()) {
			sb.append("[npc.namePos]的[npc.ears]不由自主地抽动与瘙痒着，在感觉到转化正在发生时，[npc.she]惊讶地喘了口气。");
		} else {
			sb.append("[npc.namePos]的[npc.ears]不由自主地抽动与瘙痒着，在感觉到转化正在发生时，[npc.she]惊讶地喘了口气，伸手去揉。");
		}
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;

		sb.append(type.getTransformationDescription(owner));

		sb.append("</p>");
		
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public boolean isPierced() {
		return pierced;
	}
	
	public String setPierced(GameCharacter owner, boolean pierced) {
		if(this.pierced == pierced) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		this.pierced = pierced;
		
		if(pierced) {
			if(owner.isPlayer()) {
				return "<p>你的[pc.ears]现在已[style.boldGrow(穿孔)]！</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.namePos]的[pc.ears]现在已[style.boldGrow(穿孔)]！</p>");
			}
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_EAR);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			if(owner.isPlayer()) {
				return "<p>"
							+ "你的[pc.ears][style.boldShrink(不再有穿孔了)]！"
						+ "</p>"
						+piercingUnequip;
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.namePos]的[pc.ears][style.boldShrink(不再有穿孔了)]！"
						+ "</p>"
						+piercingUnequip);
			}
		}
		
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Ear.class) && getType().getRace().isFeralPartsAvailable());
	}

}
