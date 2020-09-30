package com.ht117.sofossill.data.di

import com.ht117.sofossill.data.repository.IUserRepository
import com.ht117.sofossill.data.repository.UserRepositoryImpl
import com.ht117.sofossill.data.repository.db.dbModule
import com.ht117.sofossill.data.repository.network.networkModule
import org.koin.core.module.Module
import org.koin.dsl.module

object DataModule {

    fun getDataModules(): List<Module> {
        return mutableListOf(networkModule, repositoryModule, dbModule)
    }

    private val repositoryModule = module {
        single<IUserRepository> { UserRepositoryImpl(get(), get()) }
    }
}