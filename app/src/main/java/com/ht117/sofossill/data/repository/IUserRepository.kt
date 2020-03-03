package com.ht117.sofossill.data.repository

import androidx.paging.DataSource
import com.ht117.sofossill.app.Constants
import com.ht117.sofossill.data.model.ReputationModel
import com.ht117.sofossill.data.model.UserModel

interface IUserRepository {

    fun getUsersSource(): DataSource.Factory<Int, UserModel>

    fun getBookmarkedUser(): DataSource.Factory<Int, UserModel>

    suspend fun getUsersFromApiAndCache(page: Int, pageSize: Int = Constants.PAGE_SIZE): List<UserModel>

    suspend fun getUsersFromLocal(page: Int, pageSize: Int): List<UserModel>

    suspend fun getUserReputation(userId: String, page: Int, pageSize: Int = Constants.PAGE_SIZE): List<ReputationModel>

    fun markedUser(userId: Long, bookmarked: Boolean)

}