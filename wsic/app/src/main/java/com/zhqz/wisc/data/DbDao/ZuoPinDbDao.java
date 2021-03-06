package com.zhqz.wisc.data.DbDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhqz.wisc.data.model.ZuoPinDb;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ZUO_PIN_DB".
*/
public class ZuoPinDbDao extends AbstractDao<ZuoPinDb, Long> {

    public static final String TABLENAME = "ZUO_PIN_DB";

    /**
     * Properties of entity ZuoPinDb.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property FileId = new Property(0, long.class, "fileId", true, "_id");
        public final static Property DownloadTime = new Property(1, long.class, "downloadTime", false, "DOWNLOAD_TIME");
        public final static Property DownloadTaskId = new Property(2, long.class, "downloadTaskId", false, "DOWNLOAD_TASK_ID");
        public final static Property FileName = new Property(3, String.class, "fileName", false, "FILE_NAME");
        public final static Property DownloadStatus = new Property(4, int.class, "downloadStatus", false, "DOWNLOAD_STATUS");
    }


    public ZuoPinDbDao(DaoConfig config) {
        super(config);
    }
    
    public ZuoPinDbDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ZUO_PIN_DB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: fileId
                "\"DOWNLOAD_TIME\" INTEGER NOT NULL ," + // 1: downloadTime
                "\"DOWNLOAD_TASK_ID\" INTEGER NOT NULL ," + // 2: downloadTaskId
                "\"FILE_NAME\" TEXT," + // 3: fileName
                "\"DOWNLOAD_STATUS\" INTEGER NOT NULL );"); // 4: downloadStatus
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ZUO_PIN_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ZuoPinDb entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getFileId());
        stmt.bindLong(2, entity.getDownloadTime());
        stmt.bindLong(3, entity.getDownloadTaskId());
 
        String fileName = entity.getFileName();
        if (fileName != null) {
            stmt.bindString(4, fileName);
        }
        stmt.bindLong(5, entity.getDownloadStatus());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ZuoPinDb entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getFileId());
        stmt.bindLong(2, entity.getDownloadTime());
        stmt.bindLong(3, entity.getDownloadTaskId());
 
        String fileName = entity.getFileName();
        if (fileName != null) {
            stmt.bindString(4, fileName);
        }
        stmt.bindLong(5, entity.getDownloadStatus());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public ZuoPinDb readEntity(Cursor cursor, int offset) {
        ZuoPinDb entity = new ZuoPinDb( //
            cursor.getLong(offset + 0), // fileId
            cursor.getLong(offset + 1), // downloadTime
            cursor.getLong(offset + 2), // downloadTaskId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // fileName
            cursor.getInt(offset + 4) // downloadStatus
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ZuoPinDb entity, int offset) {
        entity.setFileId(cursor.getLong(offset + 0));
        entity.setDownloadTime(cursor.getLong(offset + 1));
        entity.setDownloadTaskId(cursor.getLong(offset + 2));
        entity.setFileName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDownloadStatus(cursor.getInt(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ZuoPinDb entity, long rowId) {
        entity.setFileId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ZuoPinDb entity) {
        if(entity != null) {
            return entity.getFileId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ZuoPinDb entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
