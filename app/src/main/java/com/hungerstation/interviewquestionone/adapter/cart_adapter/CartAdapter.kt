package com.hungerstation.interviewquestionone.adapter.cart_adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hungerstation.interviewquestionone.R
import com.hungerstation.interviewquestionone.data.ItemCart

class CartAdapter : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    private var menuList = ArrayList<ItemCart>()
    var cartClickedListeners: CartClickedListeners? = null
    fun setUpdatedMenuRepos(items: ArrayList<ItemCart>) {
        menuList = items
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView = itemView.findViewById(R.id.eachCartItemName)
        private val txtdesc: TextView = itemView.findViewById(R.id.eachCartItemDesc)
        private val txtPrice: TextView = itemView.findViewById(R.id.eachCartItemPriceTv)
        private val deleteItem: ImageView = itemView.findViewById(R.id.eachCartItemDeleteBtn)
        private val addQuantity: ImageButton =
            itemView.findViewById(R.id.eachCartItemAddQuantityBtn)
        private val minusQuantity: ImageButton =
            itemView.findViewById(R.id.eachCartItemMinusQuantityBtn)
        private val txtQuantity: TextView = itemView.findViewById(R.id.eachCartItemQuantityTV)

        //        private val linRestaurant: LinearLayout = itemView.findViewById(R.id.lin_restaurants)
        @SuppressLint("SetTextI18n")
        fun bind(data: ArrayList<ItemCart>) {
            txtTitle.text = data[position].itemName
            txtdesc.text = data[position].itemDesc
            txtQuantity.text = data[position].quantity.toString()
            txtPrice.text = data[position].itemPrice.toString() + " SAR"

            deleteItem.setOnClickListener {
                cartClickedListeners?.onDeleteClicked(data[position])
            }
            addQuantity.setOnClickListener {
                cartClickedListeners?.onPlusClicked(data[position])
            }
            minusQuantity.setOnClickListener {
                cartClickedListeners?.onMinusClicked(data[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_cart, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(menuList)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

}

interface CartClickedListeners {
    fun onDeleteClicked(itemCart: ItemCart)
    fun onPlusClicked(itemCart: ItemCart)
    fun onMinusClicked(itemCart: ItemCart)
}