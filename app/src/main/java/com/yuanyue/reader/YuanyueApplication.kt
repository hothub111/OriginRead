package com.yuanyue.reader

import android.app.Application
import com.yuanyue.reader.core.AppContainer

class YuanyueApplication : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
