package com.example.anotherapiproject.constants

class InstagramConstants {
    companion object {
        const val AUTH_BASE_URL = "https://api.instagram.com/"
        const val ACCESS_BASE_URL = "https://graph.instagram.com/"
        const val CODE_AUTH_ENDPOINT = "oauth/authorize"
        const val ACCESS_TOKEN_ENDPOINT = "oauth/access_token"
        const val LONG_TOKEN_ENDPOINT = "access_token"
        const val MEDIA_ENDPOINT = "me/media"
        const val REDIRECT_URI = "https://oauthdebugger.com/debug"
        const val CLIENT_ID = "720059425257493"
        const val CLIENT_SECRET = "dde50151281a5e5d92f735a1ea320071"
        const val SCOPE = "user_profile,user_media"
    }
}