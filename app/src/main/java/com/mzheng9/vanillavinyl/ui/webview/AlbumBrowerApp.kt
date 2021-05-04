package com.mzheng9.vanillavinyl.ui.webview

import android.app.Application

class AlbumBrowerApp : Application() {

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object {
        private lateinit var instance: AlbumBrowerApp
        const val DEFAULT_WEBVIEW_URL = "https://www.discogs.com/"
    }
}