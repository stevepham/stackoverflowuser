package com.ht117.sofossill.app.di

import com.ht117.sofossill.app.screen.detail.DetailViewModel
import com.ht117.sofossill.app.screen.home.HomeViewModel
import com.ht117.sofossill.domain.di.DomainModule.getDomainModules
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModule {

    fun getAppModules(): List<Module> {
        return mutableListOf(appModule).apply {
            addAll(getDomainModules())
        }
    }

    private val appModule = module {
        viewModel { HomeViewModel(get()) }
        viewModel { DetailViewModel(get()) }
    }
}