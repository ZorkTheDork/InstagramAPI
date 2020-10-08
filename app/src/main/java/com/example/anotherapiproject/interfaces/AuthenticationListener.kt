package com.example.anotherapiproject.interfaces

interface AuthenticationListener {
    fun onCodeReceived(code: String)
}