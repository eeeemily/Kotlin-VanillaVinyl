package com.mzheng9.vanillavinyl.ui.details

import android.app.Application

class WebsterApp : Application() {

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object {
        private lateinit var instance: WebsterApp
        const val DEFAULT_IMAGE_URL = "https://www.rochester.edu/libraries/images/rush-rhees.jpg"
        const val DEFAULT_RAW_URL = "https://google.com"
        const val DEFAULT_WEBVIEW_URL = "https://history.nasa.gov/"
        const val DEFAULT_STOCKS_URL = "https://financialmodelingprep.com"
    }
}