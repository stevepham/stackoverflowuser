package com.ht117.sofossill.data.repository.db

import androidx.paging.DataSource
import androidx.room.*
import com.ht117.sofossill.data.entity.UserEntity

@Dao
interface IUserDao {

    @Query("SELECT * FROM UserEntity ORDER BY reputation DESC")
    fun getAllUsers(): DataSource.Factory<Int, UserEntity>

    @Query("SELECT * FROM UserEntity WHERE book_marked = 1 ORDER BY reputation DESC")
    fun getBookmarkedUsers(): DataSource.Factory<Int, UserEntity>

    @Query("SELECT * from UserEntity ORDER BY reputation DESC LIMIT :pageSize OFFSET :offset")
    fun getAllUserInPage(pageSize: Int, offset: Int): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(users: List<UserEntity>)

    @Delete
    suspend fun delete(userEntity: UserEntity)

    @Query("UPDATE UserEntity SET book_marked = :bookmarked WHERE user_id = :userId")
    fun updateUser(userId: Long, bookmarked: Boolean)
}