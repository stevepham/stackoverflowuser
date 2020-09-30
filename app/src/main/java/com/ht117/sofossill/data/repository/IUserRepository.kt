package com.ht117.sofossill.data.repository

import androidx.paging.PagingData
import com.ht117.sofossill.data.model.ReputationModel
import com.ht117.sofossill.data.model.UserModel
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    suspend fun getUsers(): Flow<PagingData<UserModel>>

    suspend fun getReputation(userId: String): Flow<PagingData<ReputationModel>>
}