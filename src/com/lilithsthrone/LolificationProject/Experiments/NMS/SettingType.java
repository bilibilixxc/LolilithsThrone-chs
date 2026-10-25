package com.lilithsthrone.LolificationProject.Experiments.NMS;

public enum SettingType {
    GENERAL("", "", "通用设置"),
    FACE("oral", "口部", "面部 / 口腔"),
    BREAST("nipple", "乳房", "乳房 / 乳头"),
    VAGINA("vaginal", "阴道", "阴道"),
    PENIS("penile", "阴茎", "阴茎 / 睾丸"),
    ASS("anal", "肛门", "臀部 / 髋部"),
    SPINNERET("spinneret", "丝囊", "丝囊"),
    CROTCH_BREAST("crotchNipple", "胯部乳房", "胯部乳房 / 乳头"),
    PENILE_URETHRA("penileUrethra", "阴茎尿道", "阴茎尿道"),
    VAGINAL_URETHRA("vaginalUrethra", "阴道尿道", "阴道尿道"),
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