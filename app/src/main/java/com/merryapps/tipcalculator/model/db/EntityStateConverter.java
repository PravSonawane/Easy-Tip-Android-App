package com.merryapps.tipcalculator.model.db;

import com.merryapps.tipcalculator.model.core.EntityState;

import de.greenrobot.dao.converter.PropertyConverter;

/**
 * Created by mephisto on 2/27/16.
 */
public class EntityStateConverter implements PropertyConverter<EntityState, String> {

    @Override
    public EntityState convertToEntityProperty(String databaseValue) {
        return EntityState.convertToEntityState(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(EntityState entityProperty) {
        return entityProperty.state();
    }
}
