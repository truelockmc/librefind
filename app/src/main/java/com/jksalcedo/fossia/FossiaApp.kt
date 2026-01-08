package com.jksalcedo.fossia

import android.app.Application
import com.jksalcedo.fossia.di.appModule
import com.jksalcedo.fossia.di.networkModule
import com.jksalcedo.fossia.di.repositoryModule
import com.jksalcedo.fossia.di.useCaseModule
import com.jksalcedo.fossia.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FossiaApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FossiaApp)
            modules(appModule, networkModule, repositoryModule, useCaseModule, viewModelModule)
        }
    }
}
