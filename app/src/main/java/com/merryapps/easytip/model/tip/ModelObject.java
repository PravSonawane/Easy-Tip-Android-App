package com.merryapps.easytip.model.tip;

/**
 * An business object.
 */
public interface ModelObject {

    Long getId();

    void toNewState(EntityState newState);

    EntityState getState();

}
