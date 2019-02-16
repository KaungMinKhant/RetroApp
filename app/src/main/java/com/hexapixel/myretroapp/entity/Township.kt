package com.hexapixel.myretroapp.entity

import com.google.gson.annotations.SerializedName

data class Township(
    @SerializedName("_id")
    val id: String,
    @SerializedName("region_pcode")
    val regionPcode: String,
    @SerializedName("township_name_mm")
    val townshipNameMm: String,
    @SerializedName("township_pcode")
    val townshipPcode: String
)