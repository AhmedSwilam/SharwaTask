package com.hungerstation.interviewquestionone.adapter.restaurant_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.hungerstation.interviewquestionone.R
import com.hungerstation.interviewquestionone.data.Resturant

class RestaurantsListAdapter : RecyclerView.Adapter<RestaurantsListAdapter.MyViewHolder>() {

    private var offersList = ArrayList<Resturant>()
    var restaurantClickedListeners:RestaurantClickedListeners?=null
    fun setUpdatedRepos(items: ArrayList<Resturant>) {
        offersList = items
        notifyDataSetChanged()
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView = itemView.findViewById(R.id.txt_offer_desc)
        private val linRestaurant: LinearLayout = itemView.findViewById(R.id.lin_restaurants)
        fun bind(data: ArrayList<Resturant>) {
            txtTitle.text = data[position].nameval
            linRestaurant.setOnClickListener {
                restaurantClickedListeners?.onCardClicked(data[position])
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_restaurant, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(offersList)
    }

    override fun getItemCount(): Int {
        return offersList.size
    }

}
interface RestaurantClickedListeners {
    fun onCardClicked(resturant: Resturant?)
}

