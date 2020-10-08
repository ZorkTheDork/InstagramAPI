package com.example.anotherapiproject.models


import com.google.gson.annotations.SerializedName

data class LongLiveTokenResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("token_type")
    val tokenType: String
)