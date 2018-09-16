package com.zhqz.wisc.data.DbDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhqz.wisc.data.model.NoWifiData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NO_WIFI_DATA".
*/
public class NoWifiDataDao extends AbstractDao<NoWifiData, Void> {

    public static final String TABLENAME = "NO_WIFI_DATA";

    /**
     * Properties of entity NoWifiData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Code = new Property(0, String.class, "code", false, "CODE");
        public final static Property CardNum = new Property(1, String.class, "cardNum", false, "CARD_NUM");
        public final static Property TimesId = new Property(2, int.class, "timesId", false, "TIMES_ID");
        public final static Property Num = new Property(3, int.class, "num", false, "NUM");
        public final static Property TransactionTime = new Property(4, String.class, "transactionTime", false, "TRANSACTION_TIME");
    }


    public NoWifiDataDao(DaoConfig config) {
        super(config);
    }
    
    public NoWifiDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NO_WIFI_DATA\" (" + //
                "\"CODE\" TEXT," + // 0: code
                "\"CARD_NUM\" TEXT," + // 1: cardNum
                "\"TIMES_ID\" INTEGER NOT NULL ," + // 2: timesId
                "\"NUM\" INTEGER NOT NULL ," + // 3: num
                "\"TRANSACTION_TIME\" TEXT);"); // 4: transactionTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NO_WIFI_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NoWifiData entity) {
        stmt.clearBindings();
 
        String code = entity.getCode();
        if (code != null) {
            stmt.bindString(1, code);
        }
 
        String cardNum = entity.getCardNum();
        if (cardNum != null) {
            stmt.bindString(2, cardNum);
        }
        stmt.bindLong(3, entity.getTimesId());
        stmt.bindLong(4, entity.getNum());
 
        String transactionTime = entity.getTransactionTime();
        if (transactionTime != null) {
            stmt.bindString(5, transactionTime);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NoWifiData entity) {
        stmt.clearBindings();
 
        String code = entity.getCode();
        if (code != null) {
            stmt.bindString(1, code);
        }
 
        String cardNum = entity.getCardNum();
        if (cardNum != null) {
            stmt.bindString(2, cardNum);
        }
        stmt.bindLong(3, entity.getTimesId());
        stmt.bindLong(4, entity.getNum());
 
        String transactionTime = entity.getTransactionTime();
        if (transactionTime != null) {
            stmt.bindString(5, transactionTime);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public NoWifiData readEntity(Cursor cursor, int offset) {
        NoWifiData entity = new NoWifiData( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // code
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // cardNum
            cursor.getInt(offset + 2), // timesId
            cursor.getInt(offset + 3), // num
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // transactionTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NoWifiData entity, int offset) {
        entity.setCode(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setCardNum(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTimesId(cursor.getInt(offset + 2));
        entity.setNum(cursor.getInt(offset + 3));
        entity.setTransactionTime(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(NoWifiData entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(NoWifiData entity) {
        return null;
    }

    @Override
    public boolean hasKey(NoWifiData entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
