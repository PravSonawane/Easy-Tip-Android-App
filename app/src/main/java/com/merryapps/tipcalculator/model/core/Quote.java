package com.merryapps.tipcalculator.model.core;

import com.merryapps.tipcalculator.model.db.QuoteEntity;

/**
 * Created by mephisto on 7/16/16.
 */
public class Quote implements ModelObject {

    private QuoteEntity quoteEntity;

    public Quote(QuoteEntity quoteEntity) {
        this.quoteEntity = quoteEntity;
    }

    public String getQuote() {
        return quoteEntity.getQuote();
    }

    public String getAuthor() {
        return quoteEntity.getAuthor();
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void toNewState(EntityState newState) {
        quoteEntity.setEntityState(newState);
    }

    @Override
    public EntityState getState() {
        return quoteEntity.getEntityState();
    }
}
