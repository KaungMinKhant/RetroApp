package com.hexapixel.myretroapp.entity

import com.google.gson.annotations.SerializedName

data class Regions(
    @SerializedName("data")
    val `regions`: List<Region>
)