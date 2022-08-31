package com.hungerstation.interviewquestionone.data.roomDataBase
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hungerstation.interviewquestionone.data.ItemCart

@Database(entities = [ItemCart::class], version = 1, exportSchema = false)
abstract class CartDatabase : RoomDatabase() {

    abstract fun getCartDAO(): CartDao

    companion object {
        @Volatile
        private var INSTANCE: CartDatabase? = null

        private const val DB_NAME = "cart_database.db"

        fun getDatabase(context: Context): CartDatabase {
            return  INSTANCE ?: synchronized(this){
                val  instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }


}