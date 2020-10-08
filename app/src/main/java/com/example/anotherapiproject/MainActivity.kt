package com.example.anotherapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anotherapiproject.adapters.DataAdapter
import com.example.anotherapiproject.constants.InstagramConstants
import com.example.anotherapiproject.interfaces.AuthenticationListener
import com.example.anotherapiproject.models.AccessTokenResponse
import com.example.anotherapiproject.models.LongLiveTokenResponse
import com.example.anotherapiproject.models.MediaResponse
import com.example.anotherapiproject.services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), AuthenticationListener {
    private lateinit var dialog: AuthDialog
    private lateinit var accessToken: String
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialog = AuthDialog(this, this)
        dialog.show()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.visibility = View.GONE
    }

    override fun onCodeReceived(code: String) {
        dialog.dismiss()

        val fixedCode = code.replace("#_", "")

        val accessTokenCall: Call<AccessTokenResponse> = RetrofitClient().getAuthClient().getAccessToken(InstagramConstants.CLIENT_ID, InstagramConstants.CLIENT_SECRET, InstagramConstants.REDIRECT_URI, "authorization_code", fixedCode)
        accessTokenCall.enqueue(object: Callback<AccessTokenResponse> {
            override fun onResponse(
                call: Call<AccessTokenResponse>,
                response: Response<AccessTokenResponse>
            ) {
                if (response.isSuccessful) {
                    accessToken = response.body()!!.accessToken
                    getLongLiveToken(accessToken)
                }
            }

            override fun onFailure(call: Call<AccessTokenResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getLongLiveToken(shortAccessToken: String){
        val longTokenCall: Call<LongLiveTokenResponse> = RetrofitClient().getAccessClient().getLongToken("ig_exchange_token", InstagramConstants.CLIENT_SECRET, shortAccessToken)
        longTokenCall.enqueue(object: Callback<LongLiveTokenResponse> {
            override fun onResponse(
                call: Call<LongLiveTokenResponse>,
                response: Response<LongLiveTokenResponse>
            ) {
                Log.d("LongLiveToken", response.message())
                if (response.isSuccessful){
                    //textView.text = response.message()
                    accessToken = response.body()!!.accessToken
                    //val expires = response.body()!!.expires_in
                    getUserMedia()
                }
            }

            override fun onFailure(call: Call<LongLiveTokenResponse>, t: Throwable) {

            }
        })
    }

    fun getUserMedia() {
        val mediaCall: Call<MediaResponse> = RetrofitClient().getAccessClient().getMedia("id,media_url,caption", accessToken)
        mediaCall.enqueue(object: Callback<MediaResponse> {
            override fun onResponse(call: Call<MediaResponse>, response: Response<MediaResponse>) {
                if (response.isSuccessful) {
                    val media = response.body()

                    if (media!!.paging.next != ""){
                        getPagedResults(media)
                        return
                    }
                    recyclerView.adapter = DataAdapter(media, baseContext)
                    recyclerView.layoutManager = GridLayoutManager(baseContext, 3)
                    recyclerView.adapter?.notifyDataSetChanged()
                    recyclerView.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<MediaResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getPagedResults(mediaResponse: MediaResponse) {
        val pageCall: Call<MediaResponse> = RetrofitClient().getAccessClient().getPagedMedia("id,media_url,caption", accessToken, "0", "25", mediaResponse.paging.cursors.after)
        pageCall.enqueue(object: Callback<MediaResponse> {
            override fun onResponse(call: Call<MediaResponse>, response: Response<MediaResponse>) {
                if (response.isSuccessful) {
                    val test = response.body()
                    mediaResponse.data.addAll(test!!.data)
                    recyclerView.adapter = DataAdapter(mediaResponse, baseContext)
                    recyclerView.layoutManager = GridLayoutManager(baseContext, 3)
                    recyclerView.adapter?.notifyDataSetChanged()
                    recyclerView.visibility = View.VISIBLE

                }
            }

            override fun onFailure(call: Call<MediaResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}