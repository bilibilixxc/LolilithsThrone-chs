package com.lilithsthrone.LolificationProject.Body;

import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.rendering.CachedImage;
import com.lilithsthrone.rendering.ImageCache;

import java.util.Set;

public abstract class ModCharacter {
    public Body body;
    public boolean haveWeModded;
    public int monthsHeightCalculatedAt;
    public float heightDeviation = -1.0f;
    public int averageWorkHours = -1;

    public abstract void loadFromXML(XMLLoader loader, CharacterImportSetting... settings);

    // ------------------------------ Tussy: ------------------------------ //

    // Misc.:
    public String getTussyDescriptor() {
        return body.getTussy().getDescriptor((GameCharacter) this);
    }
    public boolean hasTussy() {
        return body!=null && body.getTailType().isTussy();
    }
    public AbstractBodyCoveringType getTussyCovering() {
        return BodyCoveringType.VAGINA;
    }
    // Orifice stats:
    // Wetness:
    public Wetness getTussyWetness() {
        return body.getTussy().getWetness((GameCharacter) this);
    }
    public String setTussyWetness(int wetness) {
        return body.getTussy().setWetness((GameCharacter) this, wetness);
    }
    public String setTussyWetness(Wetness wetness) {
        return body.getTussy().setWetness((GameCharacter) this, wetness.getValue());
    }
    public String incrementTussyWetness(int increment) {
        return body.getTussy().setWetness((GameCharacter) this, getTussyWetness().getValue() + increment);
    }
    // Capacity:
    public Capacity getTussyCapacity() {
        return body.getTussy().getCapacity();
    }
    public float getTussyRawCapacityValue() {
        return body.getTussy().getRawCapacityValue();
    }
    public float getTussyStretchedCapacity() {
        return body.getTussy().getStretchedCapacity();
    }
    public void setTussyStretchedCapacity(float capacity){
        body.getTussy().setStretchedCapacity(capacity);
    }
    public void incrementTussyStretchedCapacity(float increment){
        body.getTussy().setStretchedCapacity(getTussyStretchedCapacity() + increment);
    }

    public String setTussyCapacity(float capacity) {
        return body.getTussy().setCapacity((GameCharacter) this, capacity, false);
    }
    public String setTussyCapacity(float capacity, boolean setStretchedValueToNewValue) {
        return body.getTussy().setCapacity((GameCharacter) this, capacity, setStretchedValueToNewValue);
    }
    public String setTussyCapacity(Capacity capacity) {
        return body.getTussy().setCapacity((GameCharacter) this, capacity.getMedianValue(), false);
    }
    public String setTussyCapacity(Capacity capacity, boolean setStretchedValueToNewValue) {
        return body.getTussy().setCapacity((GameCharacter) this, capacity.getMedianValue(), setStretchedValueToNewValue);
    }
    public String incrementTussyCapacity(float increment) {
        return setTussyCapacity(getTussyRawCapacityValue() + increment, false);
    }
    public String incrementTussyCapacity(float increment, boolean setStretchedValueToNewValue) {
        return setTussyCapacity(getTussyRawCapacityValue() + increment, setStretchedValueToNewValue);
    }
    // Depth:
    public int getTussyMaximumPenetrationDepthComfortable() {
        return body.getTussy().getMaximumPenetrationDepthComfortable((GameCharacter) this);
    }
    public int getTussyMaximumPenetrationDepthUncomfortable() {
        return body.getTussy().getMaximumPenetrationDepthUncomfortable((GameCharacter) this);
    }
    public OrificeDepth getTussyDepth() {
        return body.getTussy().getDepth((GameCharacter) this);
    }
    public String setTussyDepth(int depth) {
        return body.getTussy().setDepth((GameCharacter) this, depth);
    }
    public String incrementTussyDepth(int increment) {
        return setTussyDepth(getTussyDepth().getValue() + increment);
    }
    // Elasticity:
    public OrificeElasticity getTussyElasticity() {
        return body.getTussy().getElasticity();
    }
    public String setTussyElasticity(int elasticity) {
        return body.getTussy().setElasticity((GameCharacter) this, elasticity);
    }
    public String incrementTussyElasticity(int increment) {
        return setTussyElasticity(getTussyElasticity().getValue() + increment);
    }
    // Plasticity:
    public OrificePlasticity getTussyPlasticity() {
        return body.getTussy().getPlasticity();
    }
    public String setTussyPlasticity(int plasticity) {
        return body.getTussy().setPlasticity((GameCharacter) this, plasticity);
    }

    public String incrementTussyPlasticity(int increment) {
        return setTussyPlasticity(getTussyPlasticity().getValue() + increment);
    }
    // Virginity:
    public boolean isTussyVirgin() {
        return body.getTussy().isVirgin();
    }
    public void setTussyVirgin(boolean virgin) {
        body.getTussy().setVirgin(virgin);
    }
    // Modifiers:
    public Set<OrificeModifier> getTussyOrificeModifiers() {
        return body.getTussy().getOrificeModifiers();
    }
    public boolean hasTussyOrificeModifier(OrificeModifier modifier) {
        return body.getTussy().hasOrificeModifier(modifier);
    }
    public String addTussyOrificeModifier(OrificeModifier modifier) {
        return body.getTussy().addOrificeModifier((GameCharacter) this, modifier);
    }
    public String removeTussyOrificeModifier(OrificeModifier modifier) {
        return body.getTussy().removeOrificeModifier((GameCharacter) this, modifier);
    }
    public void clearTussyOrificeModifiers() {
        body.getTussy().clearOrificeModifiers();
    }

}
