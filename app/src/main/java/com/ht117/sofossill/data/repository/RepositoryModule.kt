package com.ht117.sofossill.data.repository

import org.koin.dsl.module

val repositoryModule = module {
    single<IUserRepository> { UserRepositoryImpl(get(), get()) }
}