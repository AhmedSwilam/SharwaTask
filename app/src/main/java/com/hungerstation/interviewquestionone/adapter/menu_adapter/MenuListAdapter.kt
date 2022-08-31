package com.hungerstation.interviewquestionone.adapter.menu_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hungerstation.interviewquestionone.R
import com.hungerstation.interviewquestionone.data.MenuCategory

class MenuListAdapter : RecyclerView.Adapter<MenuListAdapter.MyViewHolder>() {

    private var menuList = ArrayList<MenuCategory>()
    var cartClickedListeners: CartClickedListeners? = null
    fun setUpdatedMenuRepos(items: ArrayList<MenuCategory>) {
        menuList = items
        notifyDataSetChanged()
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView = itemView.findViewById(R.id.eachCartItemName)
        private val txtdesc: TextView = itemView.findViewById(R.id.eachCartItemDesc)
        private val txtPrice: TextView = itemView.findViewById(R.id.eachCartItemPriceTv)
        private val addItem: ImageView = itemView.findViewById(R.id.cartIv)
        //        private val linRestaurant: LinearLayout = itemView.findViewById(R.id.lin_restaurants)
        fun bind(data: ArrayList<MenuCategory>) {
            txtTitle.text = data[position].name
            txtdesc.text = data[position].decscriptionText
            txtPrice.text = data[position].price + " SAR"
            addItem.setOnClickListener {
                cartClickedListeners?.onAddToCartBtnClicked(data[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_menu, parent, false)
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
    fun onAddToCartBtnClicked(resturant: MenuCategory)

}