package com.merryapps.easytip.model.quote;

import com.merryapps.easytip.model.db.QuoteEntity;
import com.merryapps.easytip.model.framework.EntityState;
import com.merryapps.easytip.model.framework.ModelObject;

/**
 * Created by mephisto on 7/16/16.
 */
public class Quote implements ModelObject {

    private QuoteEntity quoteEntity;

    Quote(QuoteEntity quoteEntity) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quote quote = (Quote) o;

        return quoteEntity != null ? quoteEntity.equals(quote.quoteEntity) : quote.quoteEntity == null;

    }

    @Override
    public int hashCode() {
        return quoteEntity != null ? quoteEntity.hashCode() : 0;
    }
}
