package com.merryapps.tipcalculator.model.db;

import com.merryapps.tipcalculator.model.core.EntityState;

/**
 * Created by mephisto on 2/28/16.
 */
public interface Entity {

    Long getId();
    EntityState getEntityState();
}
