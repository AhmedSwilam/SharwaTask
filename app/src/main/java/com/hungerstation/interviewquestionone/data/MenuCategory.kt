package com.hungerstation.interviewquestionone.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MenuCategory(
    @SerializedName("id") var id: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("decscriptionText") var decscriptionText: String = "",
    @SerializedName("price") var price: String = ""
) : Parcelable