package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTongueType;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Tongue implements BodyPartInterface {

	
	protected AbstractTongueType type;
	public Set<TongueModifier> tongueModifiers;
	public int tongueLength;
	public boolean pierced;

	public Tongue(AbstractTongueType type) {
		this.type = type;
		pierced = false;
		
		tongueLength = type.getDefaultLength();
		
		this.tongueModifiers = new HashSet<>(type.getDefaultRacialTongueModifiers());
	}

	public Tongue(Tongue tongueToCopy) {
		this.type = tongueToCopy.type;
		this.pierced = tongueToCopy.pierced;
		
		this.tongueLength = tongueToCopy.tongueLength;
		
		this.tongueModifiers = new HashSet<>(tongueToCopy.tongueModifiers);
	}
	
	@Override
	public AbstractTongueType getType() {
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
	
	public String getDescriptor(GameCharacter owner) {
		List<String> list = new ArrayList<>();
        
		for(TongueModifier tm : tongueModifiers) {
			list.add(tm.getName());
		}
		list.add(type.getDescriptor(owner));

		return Util.randomItemFrom(list);
	}
	
	/**
	 * Tongue type is set when FaceType changes.
	 */
	public void setType(AbstractTongueType type) {
		this.type = type;
		resetTongueModifiers();
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
				return "<p>你的[pc.tongue]现在已[style.boldGrow(穿孔)]！</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[npc.NamePos]的[npc.tongue]现在已[style.boldGrow(穿孔)]！</p>");
			}
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_TONGUE);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			if(owner.isPlayer()) {
				return "<p>"
							+ "你的[pc.tongue][style.boldShrink(不再有穿孔了)]！"
						+ "</p>"
						+piercingUnequip;
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.NamePos]的[npc.tongue][style.boldShrink(不再有穿孔了)]！"
						+ "</p>"
						+piercingUnequip);
			}
		}
	}
	
	public TongueLength getTongueLength() {
		return TongueLength.getTongueLengthFromInt(tongueLength);
	}
	
	public int getTongueLengthValue() {
		return tongueLength;
	}

	public String setTongueLength(GameCharacter owner, int tongueLength) {
		int oldTongueLength = this.tongueLength;
		this.tongueLength = Math.max(0, Math.min(tongueLength, TongueLength.FOUR_ABSURDLY_LONG.getMaximumValue()));
		int sizeChange = this.tongueLength - oldTongueLength;
		
		if(sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(你[pc.tongue]的程度没有变化……)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos][npc.tongue]的长度没有变化……)]</p>");
			}
		}
		
		if(sizeChange < 0) {
			if(owner.isPlayer()) {
				return "<p>一股舒缓的微凉感从[pc.tongue]上升起，你惊呼一声，感觉到[pc.tongue][style.boldShrink(变短了)]。<br/>"
						+ "你现在拥有"+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+"[style.boldTfGeneric("
						+ Units.size(this.tongueLength, Units.UnitType.LONG_SINGULAR)+"的[pc.tongue])]！</p>";
			} else {
				return UtilText.parse(owner, "<p>[npc.Name]感觉到[npc.tongue]上升起一丝舒缓的凉意，忍不住轻呼一声，随后[npc.tongue]便突然[style.boldShrink(变短了)]。<br/>"
						+ "[npc.Name]现在拥有"+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+"[style.boldTfGeneric("
						+ Units.size(this.tongueLength, Units.UnitType.LONG_SINGULAR)+"的[npc.tongue])]！</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return "<p>一股脉动的暖意从[pc.tongue]上升起，你惊呼一声，感觉到[pc.tongue][style.boldGrow(变长了)]。<br/>"
						+ "你现在拥有"+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+"[style.boldTfGeneric("
						+ Units.size(this.tongueLength, Units.UnitType.LONG_SINGULAR)+"的[pc.tongue])]！</p>";
			} else {
				return UtilText.parse(owner, "<p>[npc.Name]感觉到[npc.tongue]上升起一丝脉动的暖意，忍不住轻呼一声，随后[npc.tongue]便突然[style.boldGrow(变长了)]。<br/>"
						+ "[npc.Name]现在拥有"+UtilText.generateSingularDeterminer(Util.intToString(this.tongueLength))+"[style.boldTfGeneric("
						+ Units.size(this.tongueLength, Units.UnitType.LONG_SINGULAR)+"的[npc.tongue])]！</p>");
			}
		}
	}
	
	public boolean hasTongueModifier(TongueModifier modifier) {
		return tongueModifiers.contains(modifier);
	}

	public String addTongueModifier(GameCharacter owner, TongueModifier modifier) {
		if(hasTongueModifier(modifier)) {
			return !isCharacterInitialised(owner) ? "" : "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		tongueModifiers.add(modifier);
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<p>");
			switch(modifier) {
				case RIBBED:
					sb.append("[npc.Name]感到一阵强烈的压力不断从喉头处产生，[npc.she]还没来得及慌张，这种感觉便消失了，"
									+ "[npc.tongue]上现在衬着[style.boldGrow(稍硬的肉质螺纹)]。<br/>"
								+ "[style.boldSex([npc.NamePos]的[npc.tongue]现在长有螺纹！)]");
					break;
				case TENTACLED:
					sb.append("[npc.Name]感到强烈的压力不断从喉头处产生，于是不由自主地倒吸了一口凉气，"
									+ "[style.boldGrow(一连串扭动的细小触手)]布满了[npc.tongue]。<br/>"
								+ "[style.boldSex([npc.NamePos]的[npc.tongue]现在覆盖着细小的触手，自行蜿蜒扭动着！)]");
					break;
				case BIFURCATED:
					sb.append("[npc.Name]感到强烈的压力不断从喉头处产生，于是不由自主地叫了出来，"
									+ "[style.boldGrow([npc.her][npc.tongue]的顶端一分为二)]了。<br/>"
								+ "[style.boldSex([npc.NamePos]的[npc.tongue]现在像蛇一样分叉了！)]");
					break;
				case STRONG:
					sb.append("[npc.Name]感到一股平稳的压力不断从喉头处产生，但还没等反应，这感觉便冲向了[npc.tongue]，将其转化"
									+ "[style.boldGrow(得格外有力)]。<br/>"
								+ "[style.boldSex([npc.NamePos]的[npc.tongue]现在非常强壮！)]");
					break;
				case FLAT:
					sb.append("[npc.Name]感到一股平稳的压力不断从喉头处产生，但还没等反应，这感觉便冲向了[npc.tongue]，将其转化"
									+ "[style.boldGrow(得格外平直)]。<br/>"
								+ "[style.boldSex([npc.NamePos]的[npc.tongue]现在又平又直！)]");
					break;
				case WIDE:
					sb.append("[npc.Name]感到一股平稳的压力不断从喉头处产生，但还没等反应，这感觉便冲向了[npc.tongue]，将其转化"
									+ "[style.boldGrow(得格外宽大)]。<br/>"
								+ "[style.boldSex([npc.NamePos]的[npc.tongue]现在格外宽大！)]");
					break;
				case TAPERED:
					return "<p>"
							+ "[npc.Name]感到一阵从[npc.tongue]四面八方袭来的收缩感，于是倒吸了一口凉气，[npc.tongue]"
							+ "[style.boldGrow(变为锥形，越靠近顶端变得越细)]。<br/>"
							+ "[style.boldSex([npc.NamePos]的[npc.tongue]现在是锥形的！)]"
						+ "</p>";
			}
		sb.append("</p>");
		
		return UtilText.parse(owner, sb.toString());
	}

	public String removeTongueModifier(GameCharacter owner, TongueModifier modifier) {
		if(!hasTongueModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
		}
		
		tongueModifiers.remove(modifier);
		
		switch(modifier) {
			case RIBBED:
				return "<p>"
							+ "[npc.Name]感到一股轻微的凉意从[npc.tongue]上升起，但这感觉转瞬即逝，还没等[npc.she]做出任何反应，"
								+ "那稍硬的肉质螺纹便[style.boldShrink(消失了)]。<br/>"
							+ "[style.boldSex([npc.NamePos]的[npc.tongue]不再有螺纹了！)]"
						+ "</p>";
			case TENTACLED:
				return "<p>"
							+ "[npc.Name]感到一股轻微的凉意从[npc.tongue]上升起，于是倒吸了一口凉气，"
								+ "[style.boldShrink(那扭动的细小触手便消失了)]。<br/>"
							+ "[style.boldSex([npc.NamePos]的[npc.tongue]不再覆盖细小的触手了！)]"
						+ "</p>";
			case BIFURCATED:
				return "<p>"
							+ "[npc.Name]感到一股轻微的凉意从[npc.tongue]上升起，于是倒吸了一口凉气，"
							+ "[style.boldShrink(尖端便融合在了一起)]。<br/>"
							+ "[style.boldSex([npc.NamePos]的[npc.tongue]不再分叉了！)]"
						+ "</p>";
			case FLAT:
				return "<p>"
							+ "[npc.Name]感到一股脉动的压力从[npc.tongue]上升起，于是倒吸了一口凉气，[npc.tongue]"
							+ "[style.boldShrink(更厚了)]。<br/>"
							+ "[style.boldSex([npc.NamePos]的[npc.tongue]不再那么平直了！)]"
						+ "</p>";
			case STRONG:
				return "<p>"
						+ "[npc.Name]感到一股脉动的压力从[npc.tongue]上升起，于是倒吸了一口凉气，[npc.tongue]"
						+ "[style.boldShrink(软了下来，失去了力量)]。<br/>"
						+ "[style.boldSex([npc.NamePos]的[npc.tongue]不再格外有力了！)]"
					+ "</p>";
			case WIDE:
				return "<p>"
						+ "[npc.Name]感到一股脉动的压力从[npc.tongue]上升起，于是倒吸了一口凉气，[npc.tongue]"
						+ "[style.boldShrink(缩窄了)]。<br/>"
						+ "[style.boldSex([npc.NamePos]的[npc.tongue]不再那么宽大了！)]"
					+ "</p>";
			case TAPERED:
				return "<p>"
						+ "[npc.Name]感到一股脉动的压力不断从[npc.tongue]上产生，于是倒吸了一口凉气，[npc.tongue]"
						+ "[style.boldShrink(展了开来，不再是尖头的了)]。<br/>"
						+ "[style.boldSex([npc.NamePos]的[npc.tongue]不再是锥形的了！)]"
					+ "</p>";
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(无事发生……)]</p>";
	}

	public void resetTongueModifiers() {
		tongueModifiers = new HashSet<>(type.getDefaultRacialTongueModifiers());
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Tongue.class) && getType().getRace().isFeralPartsAvailable());
	}
}
