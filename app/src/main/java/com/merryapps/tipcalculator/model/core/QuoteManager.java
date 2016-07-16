package com.merryapps.tipcalculator.model.core;

import com.merryapps.tipcalculator.model.db.QuoteEntity;
import com.merryapps.tipcalculator.model.db.QuoteEntityDao;

import java.util.List;
import java.util.Random;

/**
 * Created by mephisto on 7/16/16.
 */
public class QuoteManager {

    private final List<QuoteEntity> quoteEntities;
    private QuoteEntityDao quoteEntityDao;

    public QuoteManager(QuoteEntityDao quoteEntityDao) {
        quoteEntities = quoteEntityDao.loadAll();
        this.quoteEntityDao = quoteEntityDao;
    }

    public Quote getRandomQuote() {
        return new Quote(quoteEntities.get(new Random().nextInt(quoteEntities.size())));
    }
}
