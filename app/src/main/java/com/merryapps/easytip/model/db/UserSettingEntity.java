package com.merryapps.easytip.model.db;

import com.merryapps.easytip.model.framework.EntityState;
import com.merryapps.easytip.model.tip.UserSettingType;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "USER_SETTINGS".
 */
public class UserSettingEntity implements com.merryapps.easytip.model.framework.Entity {

    private Long id;
    /** Not-null value. */
    private UserSettingType userSettingType;
    /** Not-null value. */
    private String userSettingValue;
    /** Not-null value. */
    private EntityState entityState;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public UserSettingEntity() {
    }

    public UserSettingEntity(Long id) {
        this.id = id;
    }

    public UserSettingEntity(Long id, UserSettingType userSettingType, String userSettingValue, EntityState entityState) {
        this.id = id;
        this.userSettingType = userSettingType;
        this.userSettingValue = userSettingValue;
        this.entityState = entityState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public UserSettingType getUserSettingType() {
        return userSettingType;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUserSettingType(UserSettingType userSettingType) {
        this.userSettingType = userSettingType;
    }

    /** Not-null value. */
    public String getUserSettingValue() {
        return userSettingValue;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUserSettingValue(String userSettingValue) {
        this.userSettingValue = userSettingValue;
    }

    /** Not-null value. */
    public EntityState getEntityState() {
        return entityState;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setEntityState(EntityState entityState) {
        this.entityState = entityState;
    }

    // KEEP METHODS - put your custom methods here
    public UserSettingEntity(UserSettingType userSettingType, String userSettingValue, EntityState entityState) {
        if(userSettingType == null || userSettingValue == null || entityState == null) {
            throw new NullPointerException("argument(s) cannot be null");
        }
        this.userSettingType = userSettingType;
        this.userSettingValue = userSettingValue;
        this.entityState = entityState;
    }

    public UserSettingEntity(UserSettingType userSettingType, String settingValue) {
        this(userSettingType, settingValue, EntityState.LOCAL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSettingEntity that = (UserSettingEntity) o;

        return userSettingType == that.userSettingType;

    }

    @Override
    public int hashCode() {
        return userSettingType != null ? userSettingType.hashCode() : 0;
    }
    // KEEP METHODS END

}