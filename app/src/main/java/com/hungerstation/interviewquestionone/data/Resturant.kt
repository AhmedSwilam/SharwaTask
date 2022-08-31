package com.hungerstation.interviewquestionone.data
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Resturant(
    @SerializedName("name") var nameval: String = "",
    @SerializedName("id") var idval: String = "",
    @SerializedName("icon") var iconval: String = "",
    @SerializedName("menuCategory") var menuCategory: List<MenuCategory> = emptyList(),
): Parcelable
