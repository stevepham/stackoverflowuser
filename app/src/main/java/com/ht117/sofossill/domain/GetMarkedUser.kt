package com.ht117.sofossill.domain

import com.ht117.sofossill.app.base.BaseUseCaseNoParam
import com.ht117.sofossill.data.model.UserModel
import com.ht117.sofossill.data.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class GetMarkedUser(private val userRepo: IUserRepository): BaseUseCaseNoParam<Flow<UserModel>>() {

    override suspend fun execute(): Flow<UserModel> {
        TODO("Not yet implemented")
    }
}