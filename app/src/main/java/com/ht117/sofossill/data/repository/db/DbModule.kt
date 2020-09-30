package com.ht117.sofossill.data.repository.db

import androidx.room.Room
import com.ht117.sofossill.app.Constants
import org.koin.dsl.module

val dbModule = module {

    single { Room.databaseBuilder(get(), SODatabase::class.java, Constants.DB_NAME)
        .fallbackToDestructiveMigration()
        .build()
    }
    single { provideUserDao(get()) }
}

fun provideUserDao(db: SODatabase): IUserDao {
    return db.userDao()
}