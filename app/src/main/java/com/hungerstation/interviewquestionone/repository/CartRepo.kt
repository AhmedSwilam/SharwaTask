package com.hungerstation.interviewquestionone.repository
import com.hungerstation.interviewquestionone.data.roomDataBase.CartDao
import androidx.lifecycle.LiveData
import com.hungerstation.interviewquestionone.data.ItemCart

class CartRepo(private val cartDAO: CartDao) {

    fun getAllCartItemsLiveData(): LiveData<List<ItemCart>> {
        return  cartDAO.getAllItems()
    }

    suspend fun insertCartItem(itemCart: ItemCart) {
        cartDAO.insertCartItem(itemCart)
    }

   suspend fun deleteCartItem(itemCart: ItemCart) {
        cartDAO.deleteCartItem(itemCart)
    }

    suspend fun updateCart(itemCart: ItemCart){
        cartDAO.updateItemCart(itemCart)
    }

    suspend fun deleteItemById(id:Int){
        return cartDAO.deleteItemById(id)
    }

    suspend fun clearCart(){
        cartDAO.clearCart()
    }
//    fun updateQuantity(id: Int, quantity: Int) {
//        executor.execute { cartDAO?.updateQuantity(id, quantity) }
//    }
//
//    fun updatePrice(id: Int, price: Double) {
//        executor.execute { cartDAO?.updatePrice(id, price) }
//    }
//
//    fun deleteAllCartItems() {
//        executor.execute { cartDAO?.deleteAllItems() }
//    }
}