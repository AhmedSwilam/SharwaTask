package com.hungerstation.interviewquestionone.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hungerstation.interviewquestionone.R
import com.hungerstation.interviewquestionone.adapter.restaurant_adapter.RestaurantClickedListeners
import com.hungerstation.interviewquestionone.adapter.restaurant_adapter.RestaurantsListAdapter
import com.hungerstation.interviewquestionone.data.Resturant
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type

class RestaurantsFragment : Fragment() {

    lateinit var vendorsListAdapter: RestaurantsListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.restaurants_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restaurantsRecycler = view.findViewById<RecyclerView>(R.id.restaurantsRec)
        restaurantsRecycler.layoutManager = LinearLayoutManager(activity)
        vendorsListAdapter = RestaurantsListAdapter()
        restaurantsRecycler.adapter = vendorsListAdapter

        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        restaurantsRecycler.addItemDecoration(itemDecorator)
//
        val listUserType: Type = object : TypeToken<List<Resturant?>?>() {}.type
        val restaurantList: List<Resturant> =
            Gson().fromJson(parseFileToString("data.json"), listUserType)
        vendorsListAdapter.setUpdatedRepos(restaurantList as ArrayList<Resturant>)
        Log.d("error2", restaurantList.toString())
    }

    private fun parseFileToString(filename: String?): String? {
        try {
            val stream: InputStream = requireContext().assets.open(filename!!)
            val size = stream.available()
            val bytes = ByteArray(size)
            stream.read(bytes)
            stream.close()
            return String(bytes)
        } catch (e: IOException) {
            Log.d("error1", e.toString())
        }
        return null
    }

}