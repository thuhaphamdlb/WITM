package com.example.witm.Database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM Item")
    List<Item> getAll();

    @Insert
    void insertAll(Item... items);

    @Insert
    void insert(Item items);
    @Delete
    void delete(Item items);

    @Update
    void update(Item items);

    @Query("DELETE FROM Item")
    void deleteAll();


}
