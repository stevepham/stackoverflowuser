package com.ht117.sofossill.data.di

import com.ht117.sofossill.data.repository.network.networkModule
import com.ht117.sofossill.data.repository.repositoryModule
import org.koin.core.module.Module

object DataModule {

    fun getDataModules(): List<Module> {
        return mutableListOf(networkModule, repositoryModule)
    }
}