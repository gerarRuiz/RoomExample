package com.example.roomexample;

import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {EntidadBines.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {


    public abstract EntidadBinesDao entidadBinesDao();

    private static AppDatabase appDatabase;

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            database.execSQL("DROP INDEX index_CATALOGO_BINES__id");

            // Create the new table
            database.execSQL(
                    "CREATE TABLE bines_new (" + BaseColumns._ID + " INTEGER Not Null, cODIGO TEXT, tCPREFIJO INTEGER Not Null, tIPOFPGO INTEGER Not Null, sAIDFPGO INTEGER Not Null, tRIDFPGO TEXT Not Null," +
                            "tCDEBITO TEXT Not Null, fACTIVO INTEGER Not Null, tCTIPOCOM INTEGER Not Null,PRIMARY KEY(" + BaseColumns._ID + "))");

            database.execSQL("CREATE INDEX index_CATALOGO_BINES__id ON bines_new (_id)");


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
