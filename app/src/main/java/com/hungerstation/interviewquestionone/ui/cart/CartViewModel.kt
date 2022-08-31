package com.hungerstation.interviewquestionone.ui.cart

import com.hungerstation.interviewquestionone.repository.CartRepo
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hungerstation.interviewquestionone.data.ItemCart
import com.hungerstation.interviewquestionone.data.roomDataBase.CartDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    val allItems: LiveData<List<ItemCart>>
    val repository: CartRepo

    init {
        val cartDao = CartDatabase.getDatabase(application).getCartDAO()
        repository = CartRepo(cartDao)
        allItems = repository.getAllCartItemsLiveData()
    }

    fun insertCartItem(itemCart: ItemCart) {
        viewModelScope.launch(Dispatchers.IO) { repository.insertCartItem(itemCart) }
    }

    fun updateCart(itemCart: ItemCart) {
        viewModelScope.launch(Dispatchers.IO) { repository.updateCart(itemCart) }
    }

    fun deleteCartItem(itemCart: ItemCart) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteCartItem(itemCart) }
    }

    fun deleteItemById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteItemById(id) }
    }

    fun clearCart() {
        viewModelScope.launch(Dispatchers.IO) { repository.clearCart() }
    }
//    fun deleteAllCartItems() {
//        cartRepo!!.deleteAllCartItems()
//    }
//    fun updateQuantity(id: Int, quantity: Int) {
//        cartRepo!!.updateQuantity(id, quantity)
//    }
//
//    fun updatePrice(id: Int, price: Double) {
//        cartRepo!!.updatePrice(id, price)
//    }
//        fun getAllCartItems(): LiveData<List<ItemCart?>?>? {
//        return cartRepo!!.getAllCartItemsLiveData()
//    }
}