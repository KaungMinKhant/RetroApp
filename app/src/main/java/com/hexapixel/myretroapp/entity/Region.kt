package com.hexapixel.myretroapp.entity

import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("_id")
    val id: String,
    @SerializedName("region_name_mm")
    val regionNameMm: String,
    @SerializedName("region_pcode")
    val regionPcode: String
)