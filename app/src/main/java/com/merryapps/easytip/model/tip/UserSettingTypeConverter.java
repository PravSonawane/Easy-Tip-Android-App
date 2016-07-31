package com.merryapps.easytip.model.tip;

import de.greenrobot.dao.converter.PropertyConverter;

/**
 * Created by mephisto on 7/31/16.
 */
public class UserSettingTypeConverter implements PropertyConverter<UserSettingType, String> {

    @Override
    public UserSettingType convertToEntityProperty(String databaseValue) {
        return UserSettingType.convert(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(UserSettingType entityProperty) {
        return entityProperty.get();
    }
}
