package com.merryapps.tipcalculator.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Generates Daos and Dto objects. Also generates supporting classes for database interaction
 */
public class TipCalculatorDaoGenerator {

    //the package where the entity itself will be generated
    private static final String ENTITY_PACKAGE_NAME = "com.merryapps.tipcalculator.model.db";
    //the package where the dao will be generated
    private static final String DAO_PACKAGE_NAME = "com.merryapps.tipcalculator.model.db";
    //the package where the test classes will be generated
    private static final String DAO_TEST_PACKAGE_NAME = "com.merryapps.tipcalculator.model.db";

    private static final String ENTITY_STATE_FQDN = "com.merryapps.tipcalculator.model.core.EntityState";
    private static final String ENTITY_STATE_CONVERTER_FQDN = "com.merryapps.tipcalculator.model.db.EntityStateConverter";
    private static final String ENTITY_INTERFACE_FQDN = "com.merryapps.tipcalculator.model.db.Entity";

    //table names
    private static final String QUOTES_TBL_NAME = "QUOTES";

    public static void main(String... args) throws Exception {

        Schema tipCalculatorDbSchema = new Schema(1, ENTITY_PACKAGE_NAME);

        //setting the package where the DAO code will be generated
        tipCalculatorDbSchema.setDefaultJavaPackageDao(DAO_PACKAGE_NAME);

        tipCalculatorDbSchema.setDefaultJavaPackageTest(DAO_TEST_PACKAGE_NAME);

        //enabling keep sections
        tipCalculatorDbSchema.enableKeepSectionsByDefault();

        //QuoteEntity
        Entity quoteEntity = tipCalculatorDbSchema.addEntity("QuoteEntity");
        describeQuoteEntityTable(quoteEntity);

        new DaoGenerator().generateAll(tipCalculatorDbSchema, "app/src/main/java");

    }

    private static void describeQuoteEntityTable(Entity quoteEntity) {
        quoteEntity.addIdProperty();
        quoteEntity.setTableName(QUOTES_TBL_NAME);
        quoteEntity.implementsInterface(ENTITY_INTERFACE_FQDN);
        quoteEntity.addStringProperty("quote").unique().notNull();
        quoteEntity.addStringProperty("author").unique().notNull();
        quoteEntity.addStringProperty("entityState")
                .customType(ENTITY_STATE_FQDN, ENTITY_STATE_CONVERTER_FQDN).notNull();
    }
}
