package com.merryapps.easytip.model.tip;

import com.merryapps.easytip.model.db.UserSettingEntity;
import com.merryapps.easytip.model.db.UserSettingEntityDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mephisto on 7/31/16.
 */
public class UserSettingMananger {

    private UserSettingEntityDao userSettingEntityDao;
    private List<UserSetting> userSettings;

    public UserSettingMananger(UserSettingEntityDao userSettingEntityDao) {
        this.userSettings = Collections.emptyList();
        this.userSettingEntityDao = userSettingEntityDao;
    }

    public List<UserSetting> getAll() {
        if(this.userSettings.size() > 0) {
            return this.userSettings;
        }

        if(getCount() > 0) {
            List<UserSettingEntity> userSettingEntities = this.getAllEntities();
            if(userSettingEntities != null && userSettingEntities.size() > 0) {
                this.userSettings = new ArrayList<>(userSettingEntities.size());
                for(UserSettingEntity e : userSettingEntities) {
                    this.userSettings.add(new UserSetting(e));
                }
            }
        }

        return this.userSettings;
    }

    public long getCount() {
        if(userSettings.size() > 0) {
            return userSettings.size();
        }

        return userSettingEntityDao.count();
    }

    public String getValue(UserSettingType userSettingType) {
        for(UserSetting userSetting : this.getAll()) {
            if (userSetting.getUserSettingType().equals(userSettingType)) {
                return userSetting.getValue();
            }
        }

        throw new IllegalArgumentException("No value defined for type:" + userSettingType);
    }

    public UserSetting get(UserSettingType userSettingType) {
        for(UserSetting userSetting : this.getAll()) {
            if (userSetting.getUserSettingType().equals(userSettingType)) {
                return userSetting;
            }
        }

        throw new IllegalArgumentException("No value defined for type:" + userSettingType);
    }

    public void save(UserSetting userSetting) {
        this.userSettingEntityDao.update(userSetting.getEntity());
    }

    List<UserSettingEntity> getAllEntities() {
        return userSettingEntityDao.loadAll();
    }
}
