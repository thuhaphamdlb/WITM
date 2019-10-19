package com.example.witm;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM Item")
    List<Item> getAll();

    @Query("SELECT * FROM Item WHERE Tid IN (:userIds)")
    List<Item> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Item WHERE itemName LIKE :first AND " +
            "itemPrice LIKE :last LIMIT 1")
    Item findByName(String first, String last);

    @Insert
    void insertAll(Item... items);

    @Delete
    void delete(Item item);

    @Query("UPDATE Item SET itemName = :first_name WHERE Tid = :tid")
    void updateUser(int tid, String first_name);

    @Query("DELETE FROM Item")
    void deleteAll();

}
