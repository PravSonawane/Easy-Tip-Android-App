package com.merryapps.easytip.model.quote;

import android.util.Log;

import com.merryapps.easytip.model.db.QuoteEntity;
import com.merryapps.easytip.model.db.QuoteEntityDao;
import com.merryapps.easytip.model.tip.EntityState;

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
        
        //do not insert if database already has quotes
        if(quoteEntityDao.count() > 0) {
            Log.d(TAG, "initializeDb: not inserting as database already has quotes.");
            return;
        }

        Log.d(TAG, "initializeDb: Inserting Quote objects - Initializing");
        quoteEntityDao.insertOrReplace(new QuoteEntity("The importance of money flows from it being a link between the present and the future.",
                "Ayrton Senna", EntityState.LOCAL));
        quoteEntityDao.insertOrReplace(new QuoteEntity("A little thought and a little kindness are often worth more than a great deal of money.",
                "John Ruskin", EntityState.LOCAL));
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

        quoteEntityDao.insertOrReplace(new QuoteEntity("Money won't create success, the freedom to make it will.",
                "Nelson Mandela", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Here's how I think of my money - as soldiers - I send them out to war everyday. I want them to take prisoners and come home, so there's more of them.",
        "Kevin O'Leary", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("If you can count your money, you don't have a billion dollars.",
        "J. Paul Getty", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Money is only a tool. It will take you wherever you wish, but it will not replace you as the driver.",
        "Ayn Rand", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("It's a kind of spiritual snobbery that makes people think they can be happy without money.",
        "Albert Camus", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("If we command our wealth, we shall be rich and free; if our wealth commands us, we are poor indeed.",
                "Edmund Burke", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("A rich man is nothing but a poor man with money.",
                "W. C. Fields", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Money is better than poverty, if only for financial reasons.",
        "Woody Allen", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Friendship is like money, easier made than kept.",
                "Samuel Butler", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("My goal wasn't to make a ton of money. It was to build good computers.",
        "Steve Wozniak", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Never spend your money before you have earned it.",
                "Thomas Jefferson", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("A simple fact that is hard to learn is that the time to save money is when you have some.",
        "Joe Moore", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("After a certain point, money is meaningless. It ceases to be the goal. The game is what counts.",
                "Aristotle Onassis", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Honesty is the best policy - when there is money in it.",
                "Mark Twain", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("A penny saved is a penny earned.",
                "Benjamin Franklin", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("The thing that differentiates man from animals is money.",
                "Gertrude Stein", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Money equals freedom.",
                "Kevin O'Leary", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("No complaint... is more common than that of a scarcity of money.",
        "Adam Smith", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Do what you love and the money will follow.",
                "Marsha Sinetar", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("We go to school to learn to work hard for money. I write books and create products that teach people how to have money work hard for them.",
                "Robert Kiyosaki", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Anybody who thinks money will make you happy, hasn't got money.",
        "David Geffen", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Money is a mechanism for control.",
                "David Korten", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("There's no such thing as a free lunch.",
        "Milton Friedman", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("I'd like to live as a poor man with lots of money.",
        "Pablo Picasso", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Greed is not a financial issue. It's a heart issue.",
        "Andy Stanley", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("You have to go broke three times to learn how to make a living.",
        "Casey Stengel", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Money is the barometer of a society's virtue.",
        "Ayn Rand", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("The foundation of a financial fresh start actually has nothing to do with money or specific financial dos and don'ts.",
        "Suze Orman", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("I'm scared to death of being poor. It's like a fat girl who loses 500 pounds but is always fat inside. I grew up poor and will always feel poor inside. It's my pet paranoia.",
                "Cher", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Banks have a new image. Now you have 'a friend,' your friendly banker. If the banks are so friendly, how come they chain down the pens?",
        "Alan King", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Wealth flows from energy and ideas.",
        "William Feather", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("For I can raise no money by vile means.",
                "William Shakespeare", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("I've made all my money on my own without my family and I work very hard.",
        "Paris Hilton", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("The greatest luxury of riches is that they enable you to escape so much good advice.",
        "Arthur Helps", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Money's a horrid thing to follow, but a charming thing to meet.",
        "Henry James", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Money often costs too much.",
                "Ralph Waldo Emerson", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Very few people can afford to be poor.",
        "George Bernard Shaw", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Credit buying is much like being drunk. The buzz happens immediately and gives you a lift... The hangover comes the day after.",
        "Joyce Brothers", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("No man's credit is as good as his money.",
        "John Dewey", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Money is to my social existence what health is to my body.",
        "Mason Cooley", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("It was an honor and privilege to arrive to this country 16 years ago with almost no money in my pocket. A lot has happened since then.",
        "Antonio Banderas", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("I've got all the money I'll ever need, if I die by four o'clock.",
        "Henny Youngman", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Well private money can take risks in a way that government money often isn't willing to.",
        "Bill Gates", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("That money talks, I'll not deny, I heard it once: It said, 'Goodbye'.",
        "Richard Armour", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("I'd rather lose my own money than someone else's.",
                "Dean Kamen", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Money does not make you happy but it quiets the nerves.",
                "Sean O'Casey", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Pennies do not come from heaven. They have to be earned here on earth.",
        "Margaret Thatcher", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("A woman's best protection is a little money of her own.",
        "Clare Boothe Luce", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Ben Franklin may have discovered electricity- but it is the man who invented the meter who made the money.",
        "Earl Warren", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Isn't it a shame that future generations can't be here to see all the wonderful things we're doing with their money?",
        "Earl Wilson", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Wealth and want equally harden the human heart.",
        "Theodore Parker", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Money will buy a pretty good dog, but it won't buy the wag of his tail.",
        "Josh Billings", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("All riches have their origin in mind. Wealth is in ideas - not money.",
                "Robert Collier", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Poverty makes you sad as well as wise.",
        "Bertolt Brecht", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("It is more rewarding to watch money change the world than watch it accumulate.",
        "Gloria Steinem", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("I'm not broke. Like everybody else, I owe money.",
        "Marlee Matlin", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("A successful man is one who makes more money than his wife can spend. A successful woman is one who can find such a man.",
                "Lana Turner", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Sooner or later, we sell out for money.",
                "Tony Randall", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Everyone needs a certain amount of money. Beyond that, we pursue money because we know how to obtain it. We don't necessarily know how to obtain happiness.",
        "Gregg Easterbrook", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Business is other people's money.",
        "Delphine de Girardin", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Time well spent results in more money to spend, more money to save, and more time to vacation.",
        "Zig Ziglar", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("My favorite things in life don't cost any money. It's really clear that the most precious resource we all have is time.",
                "Steve Jobs", EntityState.LOCAL));

        quoteEntityDao.insertOrReplace(new QuoteEntity("Time is money.",
                "Benjamin Franklin", EntityState.LOCAL));






        Log.d(TAG, "initializeDb: Inserting Quote objects - done");



    }
}
