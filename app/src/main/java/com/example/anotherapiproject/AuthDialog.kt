package com.example.anotherapiproject

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.anotherapiproject.constants.InstagramConstants
import com.example.anotherapiproject.interfaces.AuthenticationListener

class AuthDialog(context: Context, listener: AuthenticationListener): Dialog(context) {
    private val mListener = listener
    private lateinit var webView: WebView
    private lateinit var layout: LinearLayout
    private var mUrl = "${InstagramConstants.AUTH_BASE_URL}${InstagramConstants.CODE_AUTH_ENDPOINT}?client_id=${InstagramConstants.CLIENT_ID}&redirect_uri=${InstagramConstants.REDIRECT_URI}&scope=user_profile,user_media&response_type=code"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        setUpWebView()

        val scale = context.resources.displayMetrics.density
        addContentView(layout, FrameLayout.LayoutParams((380 * scale + 0.5f).toInt(),
            (420 * scale + 0.5f).toInt()))
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView() {
        webView = WebView(context)
        webView.isVerticalScrollBarEnabled = false
        webView.isHorizontalScrollBarEnabled = false
        webView.webViewClient = OAuthWebViewClient(mListener)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(mUrl)
        webView.layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(webView)
    }

    private class OAuthWebViewClient(val mListener: AuthenticationListener): WebViewClient() {


        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            if (url != null) {
                if (url.startsWith(InstagramConstants.REDIRECT_URI)) {
                    val urls: List<String> = url.split("=")
                    mListener.onCodeReceived(urls[1])
                    return true
                }
            }
            return false
        }

        override fun onReceivedError(
            view: WebView?,
            errorCode: Int,
            description: String?,
            failingUrl: String?
        ) {
            super.onReceivedError(view, errorCode, description, failingUrl)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }
    }
}