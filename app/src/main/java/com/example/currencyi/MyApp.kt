package com.example.currencyi

import android.app.Application
import com.example.currencyi.di.mainModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { modules(mainModule) }
    }
}