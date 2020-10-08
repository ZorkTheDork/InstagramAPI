package com.example.anotherapiproject.interfaces

import android.provider.SyncStateContract
import com.example.anotherapiproject.constants.InstagramConstants
import com.example.anotherapiproject.models.AccessTokenResponse
import com.example.anotherapiproject.models.LongLiveTokenResponse
import com.example.anotherapiproject.models.MediaResponse
import retrofit2.Call
import retrofit2.http.*

interface InstagramAPI {

    @FormUrlEncoded
    @POST(InstagramConstants.ACCESS_TOKEN_ENDPOINT)
    fun getAccessToken(@Field("client_id") client_id:String, @Field("client_secret") client_secret: String, @Field("redirect_uri") redirectUri: String, @Field("grant_type") grant_type: String, @Field("code") code:String): Call<AccessTokenResponse>

    @GET(InstagramConstants.LONG_TOKEN_ENDPOINT)
    fun getLongToken(@Query("grant_type") grant_type: String, @Query("client_secret") client_secret: String, @Query("access_token") access_token: String): Call<LongLiveTokenResponse>

    @GET(InstagramConstants.MEDIA_ENDPOINT)
    fun getMedia(@Query("fields") fields: String, @Query("access_token") access_token: String) : Call<MediaResponse>

    @GET(InstagramConstants.MEDIA_ENDPOINT)
    fun getPagedMedia(@Query("fields") fields: String, @Query("access_token") access_token: String, @Query("pretty") pretty: String, @Query("limit") limit: String, @Query("after") after: String): Call<MediaResponse>
}