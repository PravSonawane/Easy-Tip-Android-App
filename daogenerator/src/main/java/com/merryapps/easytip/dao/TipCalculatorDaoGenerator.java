package com.merryapps.easytip.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Generates Daos and Dto objects. Also generates supporting classes for database interaction
 */
public class TipCalculatorDaoGenerator {

    private static final int DATABASE_VERSION = 2;

    //the package where the entity itself will be generated
    private static final String ENTITY_PACKAGE_NAME = "com.merryapps.easytip.model.db";
    //the package where the dao will be generated
    private static final String DAO_PACKAGE_NAME = "com.merryapps.easytip.model.db";
    //the package where the test classes will be generated
    private static final String DAO_TEST_PACKAGE_NAME = "com.merryapps.easytip.model.db";

    private static final String ENTITY_STATE_FQDN = "com.merryapps.easytip.model.framework.EntityState";
    private static final String ENTITY_STATE_CONVERTER_FQDN = "com.merryapps.easytip.model.framework.EntityStateConverter";
    private static final String ENTITY_INTERFACE_FQDN = "com.merryapps.easytip.model.framework.Entity";

    //table names
    private static final String QUOTES_TBL_NAME = "QUOTES";
    private static final String USER_SETTINGS_TBL_NAME = "USER_SETTINGS";

    public static void main(String... args) throws Exception {

        Schema tipCalculatorDbSchema = new Schema(DATABASE_VERSION, ENTITY_PACKAGE_NAME);

        //setting the package where the DAO code will be generated
        tipCalculatorDbSchema.setDefaultJavaPackageDao(DAO_PACKAGE_NAME);

        tipCalculatorDbSchema.setDefaultJavaPackageTest(DAO_TEST_PACKAGE_NAME);

        //enabling keep sections
        tipCalculatorDbSchema.enableKeepSectionsByDefault();

        //QuoteEntity
        Entity quoteEntity = tipCalculatorDbSchema.addEntity("QuoteEntity");
        describeQuoteEntityTable(quoteEntity);

        //UserSettings
        Entity userSettingEntity = tipCalculatorDbSchema.addEntity("UserSettingEntity");
        describeUserSettingsEntityTable(userSettingEntity);

        new DaoGenerator().generateAll(tipCalculatorDbSchema, "app/src/main/java");

    }

    private static void describeUserSettingsEntityTable(Entity userSettingsEntity) {
        final String USER_SETTING_TYPE_FQDN = "com.merryapps.easytip.model.tip.UserSettingType";
        final String USER_SETTING_TYPE_CONVERTER_FQDN = "com.merryapps.easytip.model.tip.UserSettingTypeConverter";

        userSettingsEntity.addIdProperty();
        userSettingsEntity.setTableName(USER_SETTINGS_TBL_NAME);
        userSettingsEntity.implementsInterface(ENTITY_INTERFACE_FQDN);
        userSettingsEntity.addStringProperty("userSettingType").unique()
                .customType(USER_SETTING_TYPE_FQDN, USER_SETTING_TYPE_CONVERTER_FQDN).notNull();
        userSettingsEntity.addStringProperty("userSettingValue").notNull();
        userSettingsEntity.addStringProperty("entityState")
                .customType(ENTITY_STATE_FQDN, ENTITY_STATE_CONVERTER_FQDN).notNull();
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
