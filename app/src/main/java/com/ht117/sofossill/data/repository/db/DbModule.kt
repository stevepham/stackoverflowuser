package com.ht117.sofossill.data.repository.db

import org.koin.dsl.module

val dbModule = module {

    single { SODatabase.getDatabase(get()).userDao() }

}