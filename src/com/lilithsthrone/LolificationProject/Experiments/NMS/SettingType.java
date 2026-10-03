package com.lilithsthrone.LolificationProject.Experiments.NMS;

public enum SettingType {
    GENERAL("", "", "General"),
    FACE("oral", "Oral", "Face / Mouth"),
    BREAST("nipple", "Nipple", "Breasts / Nipples"),
    VAGINA("vaginal", "Vaginal", "Vagina"),
    PENIS("penile", "Penile", "Penis / Testicles"),
    ASS("anal", "Anal", "Ass / Hips"),
    SPINNERET("spinneret", "Spinneret", "Spinneret"),
    CROTCH_BREAST("crotchNipple", "Nipple Crotch", "Crotch Breasts / Nipples"),
    PENILE_URETHRA("penileUrethra", "Penile Urethra", "Penile Urethra"),
    VAGINAL_URETHRA("vaginalUrethra", "Vaginal Urethra", "Vaginal Urethra"),
    ;

    final String settingName, displayName, categoryName;
    SettingType(String valueName, String desc, String cat) {
        settingName = valueName; displayName = desc; categoryName = cat;
    }

    public String getSettingName() {
        return settingName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}