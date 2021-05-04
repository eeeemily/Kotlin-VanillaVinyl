package com.mzheng9.vanillavinyl.ui.webview

import android.app.Application

class AlbumBrowerApp : Application() {

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object {
        private lateinit var instance: AlbumBrowerApp
//        const val DEFAULT_IMAGE_URL = "https://www.rochester.edu/libraries/images/rush-rhees.jpg"
//        const val DEFAULT_RAW_URL = "https://google.com"
        const val DEFAULT_WEBVIEW_URL = "https://www.discogs.com/"
//        const val DEFAULT_STOCKS_URL = "https://financialmodelingprep.com"
    }
}