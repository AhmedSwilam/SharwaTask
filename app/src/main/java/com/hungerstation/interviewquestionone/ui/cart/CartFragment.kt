package com.hungerstation.interviewquestionone.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hungerstation.interviewquestionone.R
import com.hungerstation.interviewquestionone.adapter.cart_adapter.CartAdapter
import com.hungerstation.interviewquestionone.adapter.cart_adapter.CartClickedListeners
import com.hungerstation.interviewquestionone.data.ItemCart
import com.hungerstation.interviewquestionone.databinding.FragmentCartBinding
import kotlin.math.pow


class CartFragment : Fragment(), CartClickedListeners {

    private lateinit var binding: FragmentCartBinding
    lateinit var cartAdapter: CartAdapter
    lateinit var cartViewModel: CartViewModel
    private var itemCartList: List<ItemCart>? = null
    val pos: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuRecycler = view.findViewById<RecyclerView>(R.id.restaurant_cart)
        cartViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[CartViewModel::class.java]
        menuRecycler.layoutManager = LinearLayoutManager(activity)
        cartAdapter = CartAdapter()
        menuRecycler.adapter = cartAdapter
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        menuRecycler.addItemDecoration(itemDecorator)
        cartAdapter.cartClickedListeners = this

        cartViewModel.allItems.observe(requireActivity()) { list ->
            list.let {
                cartAdapter.setUpdatedMenuRepos(it as ArrayList<ItemCart>)
                Log.d("asd", list.toString())
            }
        }
    }

    override fun onDeleteClicked(resturant: ItemCart) {
        cartViewModel.deleteCartItem(resturant)
    }

    override fun onPlusClicked(itemCart: ItemCart) {
        var quantity = itemCart.quantity?.plus(1)
        itemCart.quantity = quantity!!.toInt()
        var price = itemCart.itemPrice
        itemCart.itemPrice = price!! * 2.0f
        cartViewModel.updateCart(itemCart)
//        cartViewModel.updatePrice(resturant?.id!!.toInt(),quantity1)
        cartAdapter.notifyDataSetChanged()
    }

    override fun onMinusClicked(itemCart: ItemCart) {
        var quantity = itemCart.quantity?.minus(1)
        itemCart.quantity = quantity!!.toInt()
        var price = itemCart.itemPrice?.div(2)
        itemCart.itemPrice = price!!
        cartViewModel.updateCart(itemCart)
//        cartViewModel.updatePrice(resturant?.id!!.toInt(),quantity1)
        cartAdapter.notifyDataSetChanged()
    }


}