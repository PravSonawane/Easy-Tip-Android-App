package com.merryapps.tipcalculator.model.core;

import android.util.Log;

import com.merryapps.tipcalculator.model.db.QuoteEntity;
import com.merryapps.tipcalculator.model.db.QuoteEntityDao;

/**
 * Created by mephisto on 1/30/16.
 */
public class SeedDataManager {

    private static final String TAG = "SeedDataManager";

    private QuoteEntityDao quoteEntityDao;

    public SeedDataManager(QuoteEntityDao quoteEntityDao) {
        this.quoteEntityDao = quoteEntityDao;
    }

    public void initializeDb() {

        Log.d(TAG, "initializeDb: Inserting InvitationCategory objects - Initializing");
        quoteEntityDao.insertOrReplace(new QuoteEntity("The importance of money flows from it being a link between the present and the future.",
                "Ayrton Senna", EntityState.LOCAL));
        quoteEntityDao.insertOrReplace(new QuoteEntity("A little thought and a little kindness are often worth more than a great deal of money.",
                "John Ruskin", EntityState.LOCAL));

        Log.d(TAG, "initializeDb: Inserting InvitationCategory objects - done");



    }
}
