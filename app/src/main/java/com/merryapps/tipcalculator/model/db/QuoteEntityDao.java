package com.merryapps.tipcalculator.model.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.merryapps.tipcalculator.model.core.EntityState;

import com.merryapps.tipcalculator.model.db.QuoteEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "QUOTES".
*/
public class QuoteEntityDao extends AbstractDao<QuoteEntity, Long> {

    public static final String TABLENAME = "QUOTES";

    /**
     * Properties of entity QuoteEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Quote = new Property(1, String.class, "quote", false, "QUOTE");
        public final static Property Author = new Property(2, String.class, "author", false, "AUTHOR");
        public final static Property EntityState = new Property(3, String.class, "entityState", false, "ENTITY_STATE");
    };

    private final EntityStateConverter entityStateConverter = new EntityStateConverter();

    public QuoteEntityDao(DaoConfig config) {
        super(config);
    }
    
    public QuoteEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"QUOTES\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"QUOTE\" TEXT NOT NULL UNIQUE ," + // 1: quote
                "\"AUTHOR\" TEXT NOT NULL UNIQUE ," + // 2: author
                "\"ENTITY_STATE\" TEXT NOT NULL );"); // 3: entityState
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"QUOTES\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, QuoteEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getQuote());
        stmt.bindString(3, entity.getAuthor());
        stmt.bindString(4, entityStateConverter.convertToDatabaseValue(entity.getEntityState()));
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public QuoteEntity readEntity(Cursor cursor, int offset) {
        QuoteEntity entity = new QuoteEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // quote
            cursor.getString(offset + 2), // author
            entityStateConverter.convertToEntityProperty(cursor.getString(offset + 3)) // entityState
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, QuoteEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setQuote(cursor.getString(offset + 1));
        entity.setAuthor(cursor.getString(offset + 2));
        entity.setEntityState(entityStateConverter.convertToEntityProperty(cursor.getString(offset + 3)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(QuoteEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(QuoteEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
