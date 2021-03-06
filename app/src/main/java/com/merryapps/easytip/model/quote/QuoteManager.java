package com.merryapps.easytip.model.quote;

import com.merryapps.easytip.model.db.QuoteEntity;
import com.merryapps.easytip.model.db.QuoteEntityDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by mephisto on 7/16/16.
 */
public class QuoteManager {

    private QuoteEntityDao quoteEntityDao;
    private List<Quote> quotes;

    public QuoteManager(QuoteEntityDao quoteEntityDao) {
        this.quotes = Collections.emptyList();
        this.quoteEntityDao = quoteEntityDao;
    }

    public Quote getRandomQuote() {
        return getAll().get(new Random().nextInt(this.quotes.size()));
    }

    public List<Quote> getAll() {
        if(this.quotes.size() > 0) {
            return this.quotes;
        }

        if(getCount() > 0) {
            List<QuoteEntity> quoteEntities = this.getAllEntities();
            if(quoteEntities != null && quoteEntities.size() > 0) {
                this.quotes = new ArrayList<>(quoteEntities.size());
                for(QuoteEntity e : quoteEntities) {
                    this.quotes.add(new Quote(e));
                }
            }
        }

        return this.quotes;
    }

    List<QuoteEntity> getAllEntities() {
        return quoteEntityDao.loadAll();
    }

    long getCount() {
        if(quotes.size() > 0) {
            return quotes.size();
        }
        return quoteEntityDao.count();
    }
}
