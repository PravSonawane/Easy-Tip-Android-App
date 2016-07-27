package com.merryapps.easytip.model.db;

import com.merryapps.easytip.model.tip.EntityState;

/**
 * Created by mephisto on 2/28/16.
 */
public interface Entity {

    Long getId();
    EntityState getEntityState();
}
