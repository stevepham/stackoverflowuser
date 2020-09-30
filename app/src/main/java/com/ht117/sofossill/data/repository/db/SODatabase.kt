package com.ht117.sofossill.data.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ht117.sofossill.data.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class SODatabase: RoomDatabase() {

    abstract fun userDao(): IUserDao
}