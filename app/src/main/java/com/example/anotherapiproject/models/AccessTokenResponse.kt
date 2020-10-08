package com.example.anotherapiproject.models


import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("user_id")
    val userId: Long
)