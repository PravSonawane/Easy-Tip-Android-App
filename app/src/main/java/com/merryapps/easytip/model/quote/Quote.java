package com.merryapps.easytip.model.quote;

import com.merryapps.easytip.model.db.QuoteEntity;
import com.merryapps.easytip.model.tip.EntityState;
import com.merryapps.easytip.model.tip.ModelObject;

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
