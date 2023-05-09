package com.eastx7.generator

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
    }
}
