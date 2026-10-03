package com.lilithsthrone.LolificationProject.Experiments.NMS;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.body.BreastCrotch;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

import java.io.*;
import java.lang.reflect.Field;

import static com.lilithsthrone.LolificationProject.Experiments.NMS.NPCModSys.currentNPCRequestingNMO;

/* Friendly Note : NMS is to be pronounced Nimis */
@SuppressWarnings({"StringConcatenationInsideStringBufferAppend", "ResultOfMethodCallIgnored", "unused"})
public class NMSOptions {
    private boolean hasBeenChanged = false, isModified = false;
    public String name = "", surname = "";
    public String gender = Gender.F_V_B_FEMALE.name();
    /* GENERAL */
    public int age = 18, apparentAge = 18, height = 177;
    public int femininity = Femininity.ANDROGYNOUS.getMedianFemininity();

    /* FACE */
    public int lipSize = LipSize.ONE_AVERAGE.getValue(),
            tongueLength = TongueLength.ZERO_NORMAL.getMedianValue(),
            oralElasticity = OrificeElasticity.ZERO_UNYIELDING.getValue(),
            oralPlasticity = OrificePlasticity.ZERO_RUBBERY.getValue(),
            oralWetness = Wetness.ZERO_DRY.getValue(),
            oralDepth = OrificeDepth.ZERO_EXTREMELY_SHALLOW.getValue(),
            oralCapacity = (int) Capacity.TWO_TIGHT.getMedianValue();
    public boolean oralVirginity = false;

    /* BREAST */
    public int cupsize = 3,
            nippleElasticity = OrificeElasticity.ZERO_UNYIELDING.getValue(),
            nipplePlasticity = OrificePlasticity.ZERO_RUBBERY.getValue(),
            nippleWetness = Wetness.ZERO_DRY.getValue(),
            nippleDepth = OrificeDepth.ZERO_EXTREMELY_SHALLOW.getValue(),
            areolaSize = AreolaeSize.ONE_SMALL.getValue(),
            nippleSize = NippleSize.ONE_SMALL.getValue(),
            nippleMilkStorage = Lactation.ONE_TRICKLE.getMedianValue(),
            nippleMilkRegen = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay(),
            nippleCapacity = (int) Capacity.TWO_TIGHT.getMedianValue();
    public boolean nippleVirginity = false;

    /* ASS */
    public int assSize = AssSize.TWO_SMALL.getValue(), hipSize = HipSize.TWO_NARROW.getValue(),
            analElasticity = OrificeElasticity.ZERO_UNYIELDING.getValue(),
            analPlasticity = OrificePlasticity.ZERO_RUBBERY.getValue(),
            analWetness = Wetness.ZERO_DRY.getValue(),
            analDepth = OrificeDepth.ZERO_EXTREMELY_SHALLOW.getValue(),
            analCapacity = (int) Capacity.TWO_TIGHT.getMedianValue();

    public boolean analVirginity = false, anusBleached = false;

    /* VAGINA */
    public int clitorisSize = ClitorisSize.ZERO_AVERAGE.getMedianValue(),
            clitorisGirth = PenetrationGirth.TWO_NARROW.getValue(),
            labiaSize = LabiaSize.TWO_AVERAGE.getValue(),
            vaginalElasticity = OrificeElasticity.ZERO_UNYIELDING.getValue(),
            vaginalPlasticity = OrificePlasticity.ZERO_RUBBERY.getValue(),
            vaginalWetness = Wetness.ZERO_DRY.getValue(),
            vaginalDepth = OrificeDepth.ZERO_EXTREMELY_SHALLOW.getValue(),
            vaginalCapacity = (int) Capacity.TWO_TIGHT.getMedianValue();
    public boolean vaginalVirginity = false, hasVagina = false, vaginaSquirter = false;

    /* PENIS */
    public int penileGirth = PenetrationGirth.TWO_NARROW.getValue(),
            penileLength = PenisLength.TWO_AVERAGE.getMedianValue(),
            testicleSize = TesticleSize.TWO_AVERAGE.getValue(),
            cumStorage = CumProduction.THREE_AVERAGE.getMedianValue();
    public boolean penileVirginity = false, hasPenis = false;

    /* PENILE URETHRA */
    public int penileUrethraElasticity = OrificeElasticity.ZERO_UNYIELDING.getValue(),
            penileUrethraPlasticity = OrificePlasticity.ZERO_RUBBERY.getValue(),
            penileUrethraDepth = OrificeDepth.ZERO_EXTREMELY_SHALLOW.getValue(),
            penileUrethraCapacity = (int) Capacity.TWO_TIGHT.getMedianValue(),
            cumExpulsion = FluidExpulsion.ONE_SMALL.getMedianValue();
    public boolean penileUrethraVirginity = false;

    /* VAGINAL URETHRA */
    public int vaginalUrethraElasticity = OrificeElasticity.ZERO_UNYIELDING.getValue(),
            vaginalUrethraPlasticity = OrificePlasticity.ZERO_RUBBERY.getValue(),
            vaginalUrethraDepth = OrificeDepth.ZERO_EXTREMELY_SHALLOW.getValue(),
            vaginalUrethraCapacity = (int) Capacity.TWO_TIGHT.getMedianValue();
    public boolean vaginalUrethraVirginity = false;

    /* SPINNERET */
    public int spinneretElasticity = OrificeElasticity.ZERO_UNYIELDING.getValue(),
            spinneretPlasticity = OrificePlasticity.ZERO_RUBBERY.getValue(),
            spinneretDepth = OrificeDepth.ZERO_EXTREMELY_SHALLOW.getValue(),
            spinneretWetness = Wetness.ZERO_DRY.getValue(),
            spinneretCapacity = (int) Capacity.TWO_TIGHT.getMedianValue();
    public boolean spinneretVirginity = false, hasSpinneret = false;

    /* CROTCH NIPPLE */
    public int crotchNippleElasticity = OrificeElasticity.ZERO_UNYIELDING.getValue(),
            crotchNipplePlasticity = OrificePlasticity.ZERO_RUBBERY.getValue(),
            crotchNippleDepth = OrificeDepth.ZERO_EXTREMELY_SHALLOW.getValue(),
            crotchNippleWetness = Wetness.ZERO_DRY.getValue(),
            crotchNippleCapacity = (int) Capacity.TWO_TIGHT.getMedianValue(),
            crotchNippleCupsize = CupSize.DD.getMeasurement(),
            crotchNippleSize = NippleSize.ONE_SMALL.getValue(),
            crotchNippleMilkStorage = Lactation.ONE_TRICKLE.getMedianValue(),
            crotchNippleAreolaSize = AreolaeSize.ONE_SMALL.getValue(),
            crotchNippleMilkRegen = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay();
    public boolean crotchNippleVirginity = false, hasCrotchNipple = false;

    public static NMSOptions create(NPC npcInstance) {
        NMSOptions nmo = new NMSOptions();
        nmo.hasBeenChanged = true;
        nmo.name = npcInstance.getNameIgnoresPlayerKnowledge();
        nmo.surname = npcInstance.getSurname();
        nmo.gender = npcInstance.getGender().toString();

        nmo.age = npcInstance.getAgeValue();
        nmo.apparentAge = npcInstance.getAppearsAsAgeValue();
        nmo.femininity = npcInstance.getFemininityValue();
        nmo.height = npcInstance.getHeightValue();

        nmo.oralVirginity = npcInstance.isFaceVirgin();
        nmo.oralDepth = npcInstance.getFaceDepth().getValue();
        nmo.oralElasticity = npcInstance.getFaceElasticity().getValue();
        nmo.oralPlasticity = npcInstance.getFacePlasticity().getValue();
        nmo.oralWetness = npcInstance.getFaceWetness().getValue();
        nmo.oralCapacity = (int) (10f * npcInstance.getFaceRawCapacityValue());
        nmo.lipSize = npcInstance.getLipSizeValue();
        nmo.tongueLength = npcInstance.getTongueLengthValue();

        nmo.cupsize = npcInstance.getBreastRawSizeValue();
        nmo.areolaSize = npcInstance.getAreolaeSize().getValue();
        nmo.nippleVirginity = npcInstance.isNippleVirgin();
        nmo.nippleDepth = npcInstance.getNippleDepth().getValue();
        nmo.nippleElasticity = npcInstance.getNippleElasticity().getValue();
        nmo.nipplePlasticity = npcInstance.getNipplePlasticity().getValue();
        nmo.nippleWetness = npcInstance.getNippleWetness().getValue();
        nmo.nippleMilkStorage = npcInstance.getBreastRawMilkStorageValue();
        nmo.nippleMilkRegen = npcInstance.getBreastRawLactationRegenerationValue();
        nmo.nippleCapacity = (int) (10f * npcInstance.getNippleRawCapacityValue());
        nmo.nippleSize = npcInstance.getNippleSize().getValue();

        nmo.analVirginity = npcInstance.isAnalVirgin();
        nmo.analDepth = npcInstance.getAssDepth().getValue();
        nmo.analElasticity = npcInstance.getAssElasticity().getValue();
        nmo.analPlasticity = npcInstance.getAssPlasticity().getValue();
        nmo.analWetness = npcInstance.getAssWetness().getValue();
        nmo.analCapacity = (int) (10f * npcInstance.getAssRawCapacityValue());
        nmo.anusBleached = npcInstance.isAssBleached();
        nmo.assSize = npcInstance.getAssSize().getValue();
        nmo.hipSize = npcInstance.getHipSize().getValue();

        nmo.hasVagina = npcInstance.hasVagina();
        nmo.vaginalVirginity = npcInstance.isVaginaVirgin();
        nmo.vaginalDepth = npcInstance.getVaginaDepth().getValue();
        nmo.vaginalElasticity = npcInstance.getVaginaElasticity().getValue();
        nmo.vaginalPlasticity = npcInstance.getVaginaPlasticity().getValue();
        nmo.vaginalWetness = npcInstance.getVaginaWetness().getValue();
        nmo.clitorisSize = npcInstance.getVaginaRawClitorisSizeValue();
        nmo.clitorisGirth = npcInstance.getClitorisGirth().getValue();
        nmo.vaginalCapacity = (int) (10f * npcInstance.getVaginaRawCapacityValue());
        nmo.vaginaSquirter = npcInstance.isVaginaSquirter();
        nmo.labiaSize = npcInstance.getVaginaRawLabiaSizeValue();

        nmo.hasPenis = npcInstance.hasPenis();
        nmo.penileVirginity = npcInstance.isPenisVirgin();
        nmo.penileGirth = npcInstance.getPenisRawGirthValue();
        nmo.penileLength = npcInstance.getPenisRawSizeValue();
        nmo.cumExpulsion = npcInstance.getPenisRawCumExpulsionValue();
        nmo.testicleSize = npcInstance.getTesticleSize().getValue();
        nmo.cumStorage = npcInstance.getPenisRawCumStorageValue();

        nmo.penileUrethraElasticity = npcInstance.getUrethraElasticity().getValue();
        nmo.penileUrethraPlasticity = npcInstance.getUrethraPlasticity().getValue();
        nmo.penileUrethraDepth = npcInstance.getUrethraDepth().getValue();
        nmo.penileUrethraCapacity = (int) (10f * npcInstance.getPenisRawCapacityValue());
        nmo.penileUrethraVirginity = npcInstance.isUrethraVirgin();

        nmo.vaginalUrethraElasticity = npcInstance.getVaginaUrethraElasticity().getValue();
        nmo.vaginalUrethraPlasticity = npcInstance.getVaginaUrethraPlasticity().getValue();
        nmo.vaginalUrethraDepth = npcInstance.getVaginaUrethraDepth().getValue();
        nmo.vaginalUrethraCapacity = (int) (10f * npcInstance.getVaginaUrethraRawCapacityValue());
        nmo.vaginalUrethraVirginity = npcInstance.isVaginaUrethraVirgin();

        nmo.spinneretElasticity = npcInstance.getSpinneretElasticity().getValue();
        nmo.spinneretPlasticity = npcInstance.getSpinneretPlasticity().getValue();
        nmo.spinneretDepth = npcInstance.getSpinneretDepth().getValue();
        nmo.spinneretCapacity = (int) (10f * npcInstance.getSpinneretRawCapacityValue());
        nmo.spinneretWetness = npcInstance.getSpinneretWetness().getValue();
        nmo.spinneretVirginity = npcInstance.isSpinneretVirgin();
        nmo.hasSpinneret = npcInstance.hasSpinneret();

        nmo.crotchNippleElasticity = npcInstance.getNippleCrotchElasticity().getValue();
        nmo.crotchNipplePlasticity = npcInstance.getNippleCrotchPlasticity().getValue();
        nmo.crotchNippleDepth = npcInstance.getNippleCrotchDepth().getValue();
        nmo.crotchNippleCapacity = (int) (10f * npcInstance.getNippleCrotchRawCapacityValue());
        nmo.crotchNippleWetness = npcInstance.getNippleCrotchWetness().getValue();
        nmo.crotchNippleVirginity = npcInstance.isNippleCrotchVirgin();
        nmo.crotchNippleMilkStorage = npcInstance.getBreastCrotchRawMilkStorageValue();
        nmo.crotchNippleMilkRegen = npcInstance.getBreastCrotchRawLactationRegenerationValue();
        nmo.crotchNippleCupsize = npcInstance.getBreastCrotchSize().getMeasurement();
        nmo.crotchNippleAreolaSize = npcInstance.getAreolaeCrotchSize().getValue();
        nmo.crotchNippleSize = npcInstance.getNippleCrotchSize().getValue();
        nmo.hasCrotchNipple = npcInstance.hasBreastsCrotch();

        return nmo;
    }

    public NPC applyChanges(NPC npc) {
        if (!isModified && !hasBeenChanged) return npc;
        npc.setBirthday(npc.getBirthday().plusYears(age - npc.getAgeValue())); // Excuse me? java.time.DateTimeException: Invalid value for Year (valid values -999999999 - 999999999): -1207957542
        npc.setAgeAppearanceDifferenceToAppearAsAge(apparentAge);
        npc.setFemininity(femininity);
        npc.setHeight(height, true);

        npc.setFaceVirgin(oralVirginity);
        npc.setFaceDepth(oralDepth);
        npc.setFaceElasticity(oralElasticity);
        npc.setFacePlasticity(oralPlasticity);
        npc.setFaceWetness(oralWetness);
        npc.setFaceCapacity(oralCapacity / 10f, false);
        npc.setLipSize(lipSize);
        npc.setTongueLength(tongueLength);

        npc.setBreastSize(cupsize);
//        npc.setWet(nippleWetness);
        npc.setBreastMilkStorage(nippleMilkStorage);
        npc.setBreastLactationRegeneration(nippleMilkRegen);
        npc.setAreolaeSize(areolaSize);
        npc.setNippleSize(nippleSize);
        if (Main.getProperties().hasValue(PropertyValue.nipplePenContent)) {
            npc.setNippleVirgin(nippleVirginity);
            npc.setNippleDepth(nippleDepth);
            npc.setNippleElasticity(nippleElasticity);
            npc.setNipplePlasticity(nipplePlasticity);
            npc.setNippleCapacity(nippleCapacity / 10f, false);
        }

        if (Main.getProperties().hasValue(PropertyValue.analContent)) {
            npc.setAssDepth(analDepth);
            npc.setAssElasticity(analElasticity);
            npc.setAssPlasticity(analPlasticity);
            npc.setAssWetness(analWetness);
            npc.setAssCapacity(analCapacity / 10f, false);
            npc.setAssBleached(anusBleached);
            npc.setAnalVirgin(analVirginity);
        }
        npc.setAssSize(assSize);
        npc.setHipSize(hipSize);

        if (npc.hasVagina()) {
            npc.setVaginaVirgin(vaginalVirginity);
            npc.setVaginaDepth(vaginalDepth);
            npc.setVaginaElasticity(vaginalElasticity);
            npc.setVaginaPlasticity(vaginalPlasticity);
            npc.setVaginaWetness(vaginalWetness);
            npc.setVaginaClitorisSize(clitorisSize);
            npc.setClitorisGirth(clitorisGirth);
            npc.setVaginaCapacity(vaginalCapacity / 10f, false);
            npc.setVaginaSquirter(vaginaSquirter);
            npc.setVaginaLabiaSize(labiaSize);
        }
        if (!npc.hasVagina() && hasVagina && npc.getRace() != null && !VaginaType.getVaginaTypes(npc.getRace()).isEmpty())
            npc.getBody().setVagina(new Vagina(VaginaType.getVaginaTypes(npc.getRace()).getFirst(), labiaSize, clitorisSize, clitorisGirth, vaginalWetness, vaginalCapacity / 10f, vaginalDepth, vaginalElasticity, vaginalPlasticity, vaginalVirginity));

        if (npc.hasPenis()) {
            npc.setPenisVirgin(penileVirginity);
            npc.setPenisGirth(penileGirth);
            npc.setPenisSize(penileLength);
            npc.setPenisCumExpulsion(cumExpulsion);
            npc.setTesticleSize(testicleSize);
            npc.setPenisCumStorage(cumStorage);
        }

        if (!npc.hasPenis() && hasPenis && npc.getRace() != null && !PenisType.getPenisTypes(npc.getRace()).isEmpty())
            npc.getBody().setPenis(new Penis(PenisType.getPenisTypes(npc.getRace()).getFirst(), penileLength, false, penileGirth, testicleSize, cumStorage, 2));


        if (npc.hasPenis() && Main.getProperties().hasValue(PropertyValue.urethralContent)) {
            npc.setUrethraElasticity(penileUrethraElasticity);
            npc.setUrethraPlasticity(penileUrethraPlasticity);
            npc.setUrethraDepth(penileUrethraDepth);
            npc.setPenisCapacity(penileUrethraCapacity / 10f, false);
            npc.setUrethraVirgin(penileUrethraVirginity);
        }

        if (npc.hasVagina() &&  Main.getProperties().hasValue(PropertyValue.urethralContent)) {
            npc.setVaginaUrethraElasticity(vaginalUrethraElasticity);
            npc.setVaginaUrethraPlasticity(vaginalUrethraPlasticity);
            npc.setVaginaUrethraDepth(vaginalUrethraDepth);
            npc.setVaginaUrethraCapacity(vaginalUrethraCapacity / 10f, false);
            npc.setVaginaUrethraVirgin(vaginalUrethraVirginity);
        }

//        if (!npc.hasSpinneret() && hasSpinneret)
//            npc.addSpinneret
        if (npc.hasSpinneret()) {
            npc.setSpinneretElasticity(spinneretElasticity);
            npc.setSpinneretPlasticity(spinneretPlasticity);
            npc.setSpinneretDepth(spinneretDepth);
            npc.setSpinneretCapacity(spinneretCapacity / 10f, false);
            npc.setSpinneretWetness(spinneretWetness);
            npc.setSpinneretVirgin(spinneretVirginity);
        }

        if (Main.getProperties().hasValue(PropertyValue.udderContent)) {
            if (!npc.hasBreastsCrotch() && hasCrotchNipple)
                npc.getBody().setBreastCrotch(new BreastCrotch(BreastType.getBreastTypes(npc.getRace()).get(0), Util.randomItemFrom(BreastShape.values()), crotchNippleCupsize, crotchNippleMilkStorage, 1, crotchNippleSize, NippleShape.NORMAL,
                        crotchNippleAreolaSize, AreolaeShape.NORMAL, 1, nippleCapacity, nippleDepth, nippleElasticity, nipplePlasticity, crotchNippleVirginity));
            npc.setNippleCrotchElasticity(crotchNippleElasticity);
            npc.setNippleCrotchPlasticity(crotchNipplePlasticity);
            npc.setNippleCrotchDepth(crotchNippleDepth);
            npc.setNippleCrotchCapacity(crotchNippleCapacity / 10f, false);
//        npc.setNippleCr(crotchNippleWetness);
            npc.setNippleCrotchVirgin(crotchNippleVirginity);
            npc.setBreastCrotchMilkStorage(crotchNippleMilkStorage);
            npc.setBreastCrotchLactationRegeneration(crotchNippleMilkRegen);
            npc.setBreastCrotchSize(crotchNippleCupsize);
            npc.setAreolaeCrotchSize(crotchNippleAreolaSize);
            npc.setNippleCrotchSize(crotchNippleSize);
        }
        return npc;
    }

    public static NMSOptions load(Class<? extends NPC> npcClass) throws IOException, IllegalAccessException, NoSuchFieldException {
        NMSOptions nmo = new NMSOptions(); /* Create an instance to save variables to */
        File fHome = new File("./res/mods/lolification_project/extra"); /* config file directory */
        File AHome = new File(System.getProperty("user.home") + "/Lilith's Throne/NMS/"); /* config file directory */
        File defaultFile = new File(fHome, npcClass.getSimpleName() + ".cfg");
        File changedFile = new File(AHome, npcClass.getSimpleName() + ".cfg");
        nmo.isModified = changedFile.exists();
        if (!defaultFile.exists()) {
            nmo = create(currentNPCRequestingNMO);
            nmo.saveDefaults(npcClass);
        }
        BufferedReader reader = new BufferedReader(new FileReader(/* Buffered Reader is thread-safe and doesn't lock the file */
                changedFile.exists() ? changedFile : defaultFile));
        /* gets a list of all fields (variables) */
        for (String line : reader.lines().toList()) {
//            System.out.println("Line contents : " + line);
            String[] vars = line.split(" = ");
            String field = vars[0]; /* splits the string since I store fieldName = fieldValue for simple code usage */
            String value = vars.length == 2 ? vars[1] : "";
            Field fld = nmo.getClass().getField(field); /* uses the instanced options class to get instanced field */
            if (fld.getType().getName().equals("int")) /* checks if field is integer */
                fld.setInt(nmo, Integer.parseInt(value)); /* sets the integer value to the instanciated field */
            if (fld.getType().getName().equals("boolean")) /* ^ boolean tho */
                fld.setBoolean(nmo, Boolean.parseBoolean(value)); /* ^ */
            if (fld.getType().equals(String.class)) /* ^ boolean tho */
                fld.set(nmo, value); /* ^ */
        }
        reader.close(); /* closes reader to yeet memory */
        return nmo;
    }

    public void save(Class<? extends NPC> npcClass) throws IOException, IllegalAccessException {
        if (!hasBeenChanged) return;
        File fHome = new File(System.getProperty("user.home") + "/Lilith's Throne/NMS/");
        if (!fHome.exists()) fHome.mkdirs();
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fHome, npcClass.getSimpleName() + ".cfg")));
        Field[] fields = this.getClass().getFields();
        for (Field field : fields) {
            writer.append(field.getName() + " = " + field.get(this));
            writer.newLine();
        }
        writer.close();
    }

    public void saveDefaults(Class<? extends NPC> npcClass) throws IOException, IllegalAccessException {
        if (!hasBeenChanged) return;
        File fHome = new File("./res/mods/lolification_project/extra");
        if (!fHome.exists()) fHome.mkdirs();
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fHome, npcClass.getSimpleName() + ".cfg")));
        Field[] fields = this.getClass().getFields();
        for (Field field : fields) {
            writer.append(field.getName() + " = " + field.get(this));
            writer.newLine();
        }
        writer.close();
    }

    public void setVariable(String variableName, int variableValue) throws IllegalAccessException {
        hasBeenChanged = true;
        for (Field field : this.getClass().getFields())
            if (field.getName().equals(variableName)) {
                field.setInt(this, variableValue);
                break;
            }
    }
    public void setVariable(String variableName, boolean variableValue) throws IllegalAccessException {
        hasBeenChanged = true;
        for (Field field : this.getClass().getFields())
            if (field.getName().equals(variableName)) {
                field.setBoolean(this, variableValue);
                break;
            }
    }

    public int getIVariable(String variableName) throws NoSuchFieldException, IllegalAccessException {
        return this.getClass().getField(variableName).getInt(this);
    }

    public boolean getBVariable(String variableName) throws NoSuchFieldException, IllegalAccessException {
        return this.getClass().getField(variableName).getBoolean(this);
    }
}