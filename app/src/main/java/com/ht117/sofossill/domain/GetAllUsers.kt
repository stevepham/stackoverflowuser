package com.ht117.sofossill.domain

import androidx.paging.PagingData
import com.ht117.sofossill.app.base.BaseUseCaseNoParam
import com.ht117.sofossill.data.model.UserModel
import com.ht117.sofossill.data.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class GetAllUsers(private val userRepo: IUserRepository): BaseUseCaseNoParam<Flow<PagingData<UserModel>>>() {
    override suspend fun execute(): Flow<PagingData<UserModel>> {
        return userRepo.getUsers()
    }
}