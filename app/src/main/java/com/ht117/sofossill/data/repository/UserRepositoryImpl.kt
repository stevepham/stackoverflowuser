package com.ht117.sofossill.data.repository

import com.ht117.sofossill.data.model.ReputationModel
import com.ht117.sofossill.data.model.UserModel
import com.ht117.sofossill.data.repository.network.IUserService
import com.ht117.sofossill.data.repository.network.SOResponse

class UserRepositoryImpl(private val userService: IUserService): IUserRepository {
    override suspend fun getUsers(page: Int, pageSize: Int): SOResponse<UserModel> {
        return userService.getUsers(page, pageSize)
    }

    override suspend fun getUserReputation(userId: String, page: Int, pageSize: Int): SOResponse<ReputationModel> {
        return userService.getUserReputation(userId, page, pageSize)
    }
}