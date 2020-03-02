package com.ht117.sofossill.domain.di

import com.ht117.sofossill.data.di.DataModule.getDataModules
import com.ht117.sofossill.domain.GetReputations
import com.ht117.sofossill.domain.GetUsers
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun getDomainModules(): List<Module> {
        return mutableListOf(domainModule).apply {
            addAll(getDataModules())
        }
    }

    private val domainModule = module {
        single { GetUsers(get()) }
        single { GetReputations(get()) }
    }
}