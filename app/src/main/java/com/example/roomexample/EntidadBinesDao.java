package com.example.roomexample;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface EntidadBinesDao {


    @Query("SELECT COUNT(*) FROM " + EntidadBines.TABLE_NAME)
    int count();

    @Insert
    long insert(EntidadBines bines);


}
