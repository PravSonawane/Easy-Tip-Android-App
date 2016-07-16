package com.merryapps.tipcalculator.model.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.merryapps.tipcalculator.model.db.QuoteEntity;

import com.merryapps.tipcalculator.model.db.QuoteEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig quoteEntityDaoConfig;

    private final QuoteEntityDao quoteEntityDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        quoteEntityDaoConfig = daoConfigMap.get(QuoteEntityDao.class).clone();
        quoteEntityDaoConfig.initIdentityScope(type);

        quoteEntityDao = new QuoteEntityDao(quoteEntityDaoConfig, this);

        registerDao(QuoteEntity.class, quoteEntityDao);
    }
    
    public void clear() {
        quoteEntityDaoConfig.getIdentityScope().clear();
    }

    public QuoteEntityDao getQuoteEntityDao() {
        return quoteEntityDao;
    }

}