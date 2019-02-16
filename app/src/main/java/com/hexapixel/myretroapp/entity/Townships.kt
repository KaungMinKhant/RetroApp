package com.hexapixel.myretroapp.entity

import com.google.gson.annotations.SerializedName

data class Townships(
    @SerializedName("data")
    val `townships`: List<Township>
)