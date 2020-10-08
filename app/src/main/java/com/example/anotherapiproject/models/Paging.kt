package com.example.anotherapiproject.models


import com.google.gson.annotations.SerializedName

data class Paging(
    val cursors: Cursors,
    val next: String,
    val previous: String
)