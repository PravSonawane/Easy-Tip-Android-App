package com.merryapps.easytip.model.tip;

import com.merryapps.easytip.model.db.UserSettingEntity;

/**
 * Created by mephisto on 7/31/16.
 */
public class UserSetting {

    private UserSettingEntity userSettingsEntity;

    UserSetting(UserSettingEntity userSettingsEntity) {
        this.userSettingsEntity = userSettingsEntity;
    }

    public UserSettingType getUserSettingType() {
        return userSettingsEntity.getUserSettingType();
    }

    public String getValue() {
        return userSettingsEntity.getUserSettingValue();
    }

    public void setValue(String newValue) {
        userSettingsEntity.setUserSettingValue(newValue);
    }

    UserSettingEntity getEntity() {
        return userSettingsEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSetting that = (UserSetting) o;

        return userSettingsEntity != null ? userSettingsEntity.equals(that.userSettingsEntity) : that.userSettingsEntity == null;

    }

    @Override
    public int hashCode() {
        return userSettingsEntity != null ? userSettingsEntity.hashCode() : 0;
    }
}
