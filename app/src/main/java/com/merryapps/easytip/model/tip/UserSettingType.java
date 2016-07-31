package com.merryapps.easytip.model.tip;

/**
 * Created by mephisto on 7/31/16.
 */
public enum UserSettingType {
    TIP_PERCENTAGE("TIP_PERCENTAGE"),
    PEOPLE_COUNT("PEOPLE_COUNT");

    private String settingName;

    UserSettingType(String settingName) {
        this.settingName = settingName;
    }

    public String get() {
        return this.settingName;
    }

    public static UserSettingType convert(String value) {
        if(value == null || value.isEmpty()) {
            throw new IllegalArgumentException("value cannot be null or empty");
        }

        switch (value) {
            case "TIP_PERCENTAGE": {
                return TIP_PERCENTAGE;
            }
            case "PEOPLE_COUNT": {
                return PEOPLE_COUNT;
            }
        }

        throw new IllegalArgumentException("no type defined for value:" + value);
    }


}
