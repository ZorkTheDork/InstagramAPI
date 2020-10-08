package com.example.anotherapiproject.models


import com.google.gson.annotations.SerializedName

data class MediaResponse(
    @SerializedName("data")
    val `data`: MutableList<Data>,
    @SerializedName("paging")
    val paging: Paging
)