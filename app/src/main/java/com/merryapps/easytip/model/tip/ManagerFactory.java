package com.merryapps.easytip.model.tip;

import android.util.Log;

import com.merryapps.easytip.model.db.DaoMaster;
import com.merryapps.easytip.model.quote.QuoteManager;
import com.merryapps.easytip.model.quote.SeedDataManager;

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
        return new SeedDataManager(daoMaster.newSession().getQuoteEntityDao());
    }

    public QuoteManager quoteManager() {
        return new QuoteManager(daoMaster.newSession().getQuoteEntityDao());
    }

}
