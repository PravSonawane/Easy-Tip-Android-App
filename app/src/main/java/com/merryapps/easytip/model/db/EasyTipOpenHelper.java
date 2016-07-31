package com.merryapps.easytip.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by mephisto on 7/31/16.
 */
public class EasyTipOpenHelper extends DaoMaster.DevOpenHelper {

    private static final String TAG = "EasyTipOpenHelper";

    public EasyTipOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
        Log.d(TAG, "EasyTipOpenHelper() called with: " + "context = [" + context + "], name = [" + name + "], factory = [" + factory + "]");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade() called with: " + "db = [" + db + "], oldVersion = [" + oldVersion + "], newVersion = [" + newVersion + "]");

        switch(oldVersion) {

            case 1:
                //upgrade logic from version 1 to 2
                /*
                 * Things added in version 2
                 * 1. New table USER_SETTINGS
                 */
                UserSettingEntityDao.createTable(db, false);
                Log.v(TAG, "onUpgrade: USER_SETTINGS table created");
            case 2:
                //upgrade logic from version 2 to 3
            case 3:
                //upgrade logic from version 3 to 4
                break;
            default:
                throw new IllegalStateException(
                        "unknown oldVersion " + oldVersion);
        }
    }
}
