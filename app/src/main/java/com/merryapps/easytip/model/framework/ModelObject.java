package com.merryapps.easytip.model.framework;

import com.merryapps.easytip.model.framework.EntityState;

/**
 * An business object.
 */
public interface ModelObject {

    Long getId();

    void toNewState(EntityState newState);

    EntityState getState();

}
