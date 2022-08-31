package com.hungerstation.interviewquestionone.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "itemTable")
data class ItemCart(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    val id: Int?,
    @ColumnInfo(name = "itemName")
    val itemName: String?,
    @ColumnInfo(name = "itemDesc")
    val itemDesc: String?,
    @ColumnInfo(name = "itemImage")
    val itemImage: String?,
    @ColumnInfo(name = "itemPrice")
    var itemPrice: Double?,
    @ColumnInfo(name = "quantity")
    var quantity: Int?,
    @ColumnInfo(name = "totalItemPrice")
    val totalItemPrice: Double?
)