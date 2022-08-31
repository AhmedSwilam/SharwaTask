package com.hungerstation.interviewquestionone.data.roomDataBase
import androidx.lifecycle.LiveData
import androidx.room.*
import com.hungerstation.interviewquestionone.data.ItemCart

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(itemCart: ItemCart)

    @Delete
    suspend fun deleteCartItem(itemCart: ItemCart)

    @Update
    suspend fun updateItemCart(itemCart: ItemCart)

    @Query("Select * from itemTable order by item_id ASC")
    fun getAllItems(): LiveData<List<ItemCart>>

    @Query("Delete FROM itemTable")
    suspend fun clearCart()

    @Query("DELETE FROM itemTable WHERE item_id = :id")
    suspend fun deleteItemById(id: Int)

//    @Query("SELECT * FROM shoe_table")
//    fun getAllCartItems(): LiveData<List<ItemCart?>?>?
//
//    @Query("UPDATE shoe_table SET quantity=:quantity WHERE id=:id")
//    fun updateQuantity(id: Int, quantity: Int)
//
//    @Query("UPDATE shoe_table SET totalItemPrice=:totalItemPrice WHERE id=:id")
//    fun updatePrice(id: Int, totalItemPrice: Double)
//
//    @Query("DELETE FROM shoe_table")
//    fun deleteAllItems()
}