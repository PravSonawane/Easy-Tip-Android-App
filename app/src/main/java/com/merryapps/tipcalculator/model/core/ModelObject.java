package com.merryapps.tipcalculator.model.core;

/**
 * An business object.
 */
public interface ModelObject {

    Long getId();

    void toNewState(EntityState newState);

    EntityState getState();

}
