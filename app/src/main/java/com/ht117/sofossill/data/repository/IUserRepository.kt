package com.ht117.sofossill.data.repository

import com.ht117.sofossill.data.model.ReputationModel
import com.ht117.sofossill.data.model.UserModel
import com.ht117.sofossill.data.repository.network.SOResponse

interface IUserRepository {

    suspend fun getUsers(page: Int, pageSize: Int = 30): SOResponse<UserModel>

    suspend fun getUserReputation(userId: String, page: Int, pageSize: Int = 30): SOResponse<ReputationModel>
}