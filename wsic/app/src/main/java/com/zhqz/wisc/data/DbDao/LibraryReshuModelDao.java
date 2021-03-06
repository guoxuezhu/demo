package com.zhqz.wisc.data.DbDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhqz.wisc.libraryui.main.LibraryReshuModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LIBRARY_RESHU_MODEL".
*/
public class LibraryReshuModelDao extends AbstractDao<LibraryReshuModel, Void> {

    public static final String TABLENAME = "LIBRARY_RESHU_MODEL";

    /**
     * Properties of entity LibraryReshuModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, int.class, "id", false, "ID");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Author = new Property(2, String.class, "author", false, "AUTHOR");
        public final static Property CoverUrl = new Property(3, String.class, "coverUrl", false, "COVER_URL");
        public final static Property Number = new Property(4, String.class, "number", false, "NUMBER");
        public final static Property Synopsis = new Property(5, String.class, "synopsis", false, "SYNOPSIS");
        public final static Property Sorting = new Property(6, double.class, "sorting", false, "SORTING");
        public final static Property Status = new Property(7, int.class, "status", false, "STATUS");
        public final static Property SchoolId = new Property(8, int.class, "schoolId", false, "SCHOOL_ID");
    }


    public LibraryReshuModelDao(DaoConfig config) {
        super(config);
    }
    
    public LibraryReshuModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LIBRARY_RESHU_MODEL\" (" + //
                "\"ID\" INTEGER NOT NULL ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"AUTHOR\" TEXT," + // 2: author
                "\"COVER_URL\" TEXT," + // 3: coverUrl
                "\"NUMBER\" TEXT," + // 4: number
                "\"SYNOPSIS\" TEXT," + // 5: synopsis
                "\"SORTING\" REAL NOT NULL ," + // 6: sorting
                "\"STATUS\" INTEGER NOT NULL ," + // 7: status
                "\"SCHOOL_ID\" INTEGER NOT NULL );"); // 8: schoolId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LIBRARY_RESHU_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LibraryReshuModel entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(3, author);
        }
 
        String coverUrl = entity.getCoverUrl();
        if (coverUrl != null) {
            stmt.bindString(4, coverUrl);
        }
 
        String number = entity.getNumber();
        if (number != null) {
            stmt.bindString(5, number);
        }
 
        String synopsis = entity.getSynopsis();
        if (synopsis != null) {
            stmt.bindString(6, synopsis);
        }
        stmt.bindDouble(7, entity.getSorting());
        stmt.bindLong(8, entity.getStatus());
        stmt.bindLong(9, entity.getSchoolId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LibraryReshuModel entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(3, author);
        }
 
        String coverUrl = entity.getCoverUrl();
        if (coverUrl != null) {
            stmt.bindString(4, coverUrl);
        }
 
        String number = entity.getNumber();
        if (number != null) {
            stmt.bindString(5, number);
        }
 
        String synopsis = entity.getSynopsis();
        if (synopsis != null) {
            stmt.bindString(6, synopsis);
        }
        stmt.bindDouble(7, entity.getSorting());
        stmt.bindLong(8, entity.getStatus());
        stmt.bindLong(9, entity.getSchoolId());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public LibraryReshuModel readEntity(Cursor cursor, int offset) {
        LibraryReshuModel entity = new LibraryReshuModel( //
            cursor.getInt(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // author
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // coverUrl
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // number
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // synopsis
            cursor.getDouble(offset + 6), // sorting
            cursor.getInt(offset + 7), // status
            cursor.getInt(offset + 8) // schoolId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LibraryReshuModel entity, int offset) {
        entity.setId(cursor.getInt(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAuthor(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCoverUrl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setNumber(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSynopsis(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSorting(cursor.getDouble(offset + 6));
        entity.setStatus(cursor.getInt(offset + 7));
        entity.setSchoolId(cursor.getInt(offset + 8));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(LibraryReshuModel entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(LibraryReshuModel entity) {
        return null;
    }

    @Override
    public boolean hasKey(LibraryReshuModel entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
