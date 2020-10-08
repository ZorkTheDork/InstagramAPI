package com.example.anotherapiproject.models

import com.google.gson.annotations.SerializedName

data class AuthCodeResponse(
    @SerializedName("code")
    val code: String)