package com.example.anotherapiproject.models


import com.google.gson.annotations.SerializedName

data class Data(
    val caption: String,
    val id: String,
    @SerializedName("media_url")
    val media_url: String
)