package com.merryapps.easytip.model.framework;

import android.util.Log;

import com.merryapps.easytip.model.db.DaoMaster;
import com.merryapps.easytip.model.quote.QuoteManager;
import com.merryapps.easytip.model.quote.SeedDataManager;
import com.merryapps.easytip.model.tip.UserSettingMananger;

/**
 * Created by mephisto on 1/17/16.
 */
public class ManagerFactory {

    private static String TAG = "ManagerFactory";
    private DaoMaster daoMaster;

    public ManagerFactory(DaoMaster daoMaster) {
        Log.d(TAG, "ManagerFactory() called with: " + "daoMaster = [" + daoMaster + "]");
        this.daoMaster = daoMaster;
    }

    public SeedDataManager seedDataManager() {
        Log.d(TAG, "seedDataManager() called");
        return new SeedDataManager(daoMaster.newSession().getQuoteEntityDao(), daoMaster.newSession().getUserSettingEntityDao());
    }

    public QuoteManager quoteManager() {
        Log.d(TAG, "quoteManager() called");
        return new QuoteManager(daoMaster.newSession().getQuoteEntityDao());
    }

    public UserSettingMananger userSettingManager() {
        Log.d(TAG, "userSettingManager() called");
        return new UserSettingMananger(daoMaster.newSession().getUserSettingEntityDao());
    }

}
