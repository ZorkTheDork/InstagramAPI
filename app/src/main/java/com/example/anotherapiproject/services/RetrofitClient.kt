package com.example.anotherapiproject.services

import com.example.anotherapiproject.constants.InstagramConstants
import com.example.anotherapiproject.interfaces.InstagramAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val okClient = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS).setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()


    fun getAuthClient(): InstagramAPI {
        val ret =  Retrofit.Builder()
            .baseUrl(InstagramConstants.AUTH_BASE_URL)
            .client(okClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return ret.create(InstagramAPI::class.java)
    }

    fun getAccessClient(): InstagramAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(InstagramConstants.ACCESS_BASE_URL)
            .client(okClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(InstagramAPI::class.java)
    }
}