package com.hungerstation.interviewquestionone.ui.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hungerstation.interviewquestionone.R
import com.hungerstation.interviewquestionone.adapter.menu_adapter.CartClickedListeners
import com.hungerstation.interviewquestionone.adapter.menu_adapter.MenuListAdapter
import com.hungerstation.interviewquestionone.data.ItemCart
import com.hungerstation.interviewquestionone.data.MenuCategory
import com.hungerstation.interviewquestionone.data.Resturant
import com.hungerstation.interviewquestionone.databinding.FragmentRestaurantMenuBinding
import com.hungerstation.interviewquestionone.ui.cart.CartViewModel

class RestaurantMenuFragment : Fragment(), CartClickedListeners {

    private val args: RestaurantMenuFragmentArgs? by navArgs()
    private lateinit var restaurant: Resturant
    private lateinit var binding: FragmentRestaurantMenuBinding
    lateinit var menuListAdapter: MenuListAdapter
    lateinit var cartViewModel: CartViewModel
    private var itemCartList: List<ItemCart>? = null
    val pos: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaurantMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurant = args?.restaurant!!
        val menuRecycler = view.findViewById<RecyclerView>(R.id.restaurant_menu)
        menuRecycler.layoutManager = LinearLayoutManager(activity)
        menuListAdapter = MenuListAdapter()
        menuRecycler.adapter = menuListAdapter
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        menuRecycler.addItemDecoration(itemDecorator)
        menuListAdapter.setUpdatedMenuRepos(restaurant.menuCategory as ArrayList<MenuCategory>)
        menuListAdapter.cartClickedListeners = this
    }

    override fun onAddToCartBtnClicked(resturant: MenuCategory) {
        val itemName = resturant.name
        val itemdesc = resturant.decscriptionText
        val itemPrice = resturant.price
        val quantity = intArrayOf(1)
        val id = IntArray(1)
        itemCartList = ArrayList()

        if (!(itemCartList as ArrayList<ItemCart>).isEmpty()) {
            for (i in (itemCartList as ArrayList<ItemCart>).indices) {
                if (resturant.name.equals((itemCartList as ArrayList<ItemCart>).get(i).itemName)) {
                    quantity[0] = (itemCartList as ArrayList<ItemCart>).get(i).itemPrice!!.toInt()
                    quantity[0]++
                    id[0] = (itemCartList as ArrayList<ItemCart>).get(i).id!!
                }
            }
        }

        Log.d("TAG", "onAddToCartBtnClicked: " + quantity[0])

        if (quantity[0] == 1) {
            resturant.price = quantity[0].toDouble().toString()
            var totalItemPrice = quantity[0].toDouble() * itemPrice.toDouble()
            cartViewModel = ViewModelProvider(requireActivity(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
            )[CartViewModel::class.java]
            cartViewModel.insertCartItem(ItemCart(null,itemName,itemdesc,""
                ,itemPrice.toDouble(),quantity.size,totalItemPrice))
//            Log.d("shoeCart", itemCart.quantity.toString())

        } else {
//            cartViewModel.updateCart(ItemCart(null,itemName,itemdesc,""
//                ,itemPrice.toDouble(),quantity.size,totalItemPrice))
//            cartViewModel.updatePrice(id[0], quantity[0] * itemCart.shoePrice)
        }

        makeSnackBar("Item Added To Cart")
    }

    private fun makeSnackBar(msg: String) {
        Snackbar.make(binding.constraint, msg, Snackbar.LENGTH_SHORT)
            .setAction(
                "Go to Cart"
            ) {
                val d =
                    RestaurantMenuFragmentDirections.actionRestaurantMenuFragmentToCartFragment()
                findNavController().navigate(d)
            }.show()
    }
}