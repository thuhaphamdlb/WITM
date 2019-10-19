package com.example.witm;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item {

    @PrimaryKey(autoGenerate = true)
    public int Tid;

    @ColumnInfo(name = "itemName")
    public String itemName;

    @ColumnInfo(name = "itemPrice")
    public String itemPrice;

    public Item(String itemName, String itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
}
