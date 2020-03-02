package com.ht117.sofossill

import android.app.Application
import com.ht117.sofossill.app.di.AppModule.getAppModules
import com.ht117.sofossill.data.repository.db.SODatabase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SOApp: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initDB()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@SOApp)
            modules(getAppModules())
        }
    }

    private fun initDB() {
        SODatabase.getDatabase(this)
    }
}