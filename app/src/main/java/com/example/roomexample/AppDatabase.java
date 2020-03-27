package com.example.roomexample;

import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {EntidadBines.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {


    public abstract EntidadBinesDao entidadBinesDao();

    private static AppDatabase appDatabase;

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {


            // Create the new table
            database.execSQL(
                    "CREATE TABLE bines_new (" + BaseColumns._ID + " INTEGER Not Null, cODIGO TEXT, tCPREFIJO INTEGER Not Null, tIPOFPGO INTEGER Not Null, sAIDFPGO INTEGER Not Null, tRIDFPGO TEXT," +
                            "tCDEBITO TEXT, fACTIVO INTEGER Not Null, tCTIPOCOM TEXT,PRIMARY KEY(" + BaseColumns._ID + "))");

            database.execSQL("DROP INDEX index_CATALOGO_BINES__id");

            database.execSQL("CREATE UNIQUE INDEX index_CATALOGO_BINES__id ON bines_new (" + BaseColumns._ID + ")");

            // Copy the data
            database.execSQL(
                    "INSERT INTO bines_new (" + BaseColumns._ID + ", cODIGO, tCPREFIJO, tIPOFPGO, sAIDFPGO, tRIDFPGO, tCDEBITO, fACTIVO, tCTIPOCOM) " +
                            "SELECT " + BaseColumns._ID + ", cODIGO, tCPREFIJO, tIPOFPGO, sAIDFPGO, tRIDFPGO, tCDEBITO, fACTIVO, tCTIPOCOM FROM " + EntidadBines.TABLE_NAME);
            // Remove the old table
            database.execSQL("DROP TABLE " + EntidadBines.TABLE_NAME);
            // Change the table name to the correct one
            database.execSQL("ALTER TABLE bines_new RENAME TO " + EntidadBines.TABLE_NAME);
        }

    };
}
