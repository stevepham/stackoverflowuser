package com.ht117.sofossill.data.repository.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ht117.sofossill.data.entity.UserEntity

@Dao
interface IUserDao {

    @Query("SELECT * from UserEntity")
    suspend fun getAll(): List<UserEntity>

    @Insert
    suspend fun insertAll(vararg users: UserEntity)

    @Delete
    suspend fun delete(userEntity: UserEntity)
}