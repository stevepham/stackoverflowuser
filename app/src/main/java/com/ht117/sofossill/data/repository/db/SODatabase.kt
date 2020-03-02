package com.ht117.sofossill.data.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ht117.sofossill.app.Constants
import com.ht117.sofossill.data.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class SODatabase: RoomDatabase() {

    abstract fun userDao(): IUserDao

    companion object {

        @Volatile
        private var INSTANCE: SODatabase? = null

        fun getDatabase(context: Context): SODatabase {
            val temp = INSTANCE
            if (temp != null) {
                return temp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, SODatabase::class.java, Constants.DB_NAME)
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
}