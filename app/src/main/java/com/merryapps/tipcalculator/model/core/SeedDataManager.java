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
        quoteEntityDao.insertOrReplace(new QuoteEntity("Let us not be satisfied with just giving money. Money is not enough, money can be got, but they need your hearts to love them. So, spread your love everywhere you go.",
                "Mother Teresa", EntityState.LOCAL));
        quoteEntityDao.insertOrReplace(new QuoteEntity("Real riches are the riches possessed inside.",
                "B.C. Forbes", EntityState.LOCAL));
        quoteEntityDao.insertOrReplace(new QuoteEntity("Wealth is the ability to fully experience life.",
                "Henry David Thoreau", EntityState.LOCAL));
        quoteEntityDao.insertOrReplace(new QuoteEntity("The hardest thing to understand in the world is the income tax.",
                "Albert Einstein", EntityState.LOCAL));
        quoteEntityDao.insertOrReplace(new QuoteEntity("Money can't buy love, but it improves your bargaining position.",
                "Christopher Marlowe", EntityState.LOCAL));
        quoteEntityDao.insertOrReplace(new QuoteEntity("It is not the creation of wealth that is wrong, but the love of money for its own sake.",
                "Margaret Thatcher", EntityState.LOCAL));
        quoteEntityDao.insertOrReplace(new QuoteEntity("When I was young I thought that money was the most important thing in life; now that I am old I know that it is.",
                "Oscar Wilde", EntityState.LOCAL));
        quoteEntityDao.insertOrReplace(new QuoteEntity("Many folks think they aren't good at earning money, when what they don't know is how to use it.",
                "Frank A. Clark", EntityState.LOCAL));
        quoteEntityDao.insertOrReplace(new QuoteEntity("If women didn't exist, all the money in the world would have no meaning.",
                "Aristotle Onassis", EntityState.LOCAL));
        quoteEntityDao.insertOrReplace(new QuoteEntity("The safe way to double your money is to fold it over once and put it in your pocket.",
                "Kin Hubbard", EntityState.LOCAL));
        quoteEntityDao.insertOrReplace(new QuoteEntity("It's morally wrong to allow a sucker to keep his money.",
                "W. C. Fields", EntityState.LOCAL));
        quoteEntityDao.insertOrReplace(new QuoteEntity("So you think that money is the root of all evil. Have you ever asked what is the root of all money?",
                "Ayn Rand", EntityState.LOCAL));




        Log.d(TAG, "initializeDb: Inserting InvitationCategory objects - done");



    }
}
