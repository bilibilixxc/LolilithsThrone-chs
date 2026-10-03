package com.lilithsthrone.LolificationProject.Body;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.FluidInterface;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;
import java.util.Set;

public class FluidUrine implements FluidInterface {
    protected AbstractFluidType type = FluidType.URINE;
    protected FluidFlavour flavour = FluidType.URINE.getFlavour();

    public FluidUrine() {
    }

    public Element saveAsXML(Element parentElement, Document doc) {
        Element element = doc.createElement("urine");
        parentElement.appendChild(element);

        XMLUtil.addAttribute(doc, element, "type", this.type.toString());
        XMLUtil.addAttribute(doc, element, "flavour", this.flavour.toString());

        return element;
    }

    public static FluidUrine loadFromXML(Element parentElement, Document doc) {
        return loadFromXML(parentElement, doc, null);
    }

    /**
     * @param parentElement
     * @param doc
     * @param baseType      If you pass in a baseType, this method will ignore the saved type in parentElement.
     */
    public static FluidUrine loadFromXML(Element parentElement, Document doc, FluidType baseType) {
        return new FluidUrine();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof FluidUrine;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.getType().hashCode();
        result = 31 * result + this.getFlavour().hashCode();
        result = 31 * result + this.getFluidModifiers().hashCode();
        result = 31 * result + this.getTransformativeEffects().hashCode();
        return result;
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
        String modifierDescriptor = "";

        return UtilText.returnStringAtRandom(
                modifierDescriptor,
                flavour.getRandomFlavourDescriptor(),
                (type.getDescriptor(gc).equals("human") ? null : type.getDescriptor(gc)));
    }

    @Override
    public boolean isFeral(GameCharacter owner) {
        return false;
    }

    @Override
    public AbstractFluidType getType() {
        return type;
    }

    public void setType(BodyPartTypeInterface type) {
        this.type = (AbstractFluidType) type;
    }

    public FluidFlavour getFlavour() {
        return flavour;
    }

    public String setFlavour(GameCharacter owner, FluidFlavour flavour) {
        this.flavour = flavour;
        return "";
    }

    @Override
    public Set<FluidModifier> getFluidModifiers() {
        return null;
    }

    public boolean hasFluidModifier(FluidModifier fluidModifier) {
        return false;
    }

    @Override
    public String addFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
        return null;
    }

    @Override
    public String removeFluidModifier(GameCharacter owner, FluidModifier fluidModifier) {
        return null;
    }

    @Override
    public List<ItemEffect> getTransformativeEffects() {
        return null;
    }

    @Override
    public void addTransformativeEffect(ItemEffect ie) {

    }

    @Override
    public float getValuePerMl() {
        return 0;
    }
}