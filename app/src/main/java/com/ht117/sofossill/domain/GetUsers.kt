package com.ht117.sofossill.domain

import com.ht117.sofossill.app.base.BaseUseCase
import com.ht117.sofossill.data.model.UserModel
import com.ht117.sofossill.data.repository.IUserRepository
import com.ht117.sofossill.data.repository.network.SOResponse

data class GetUserParam(val page: Int, val pageSize: Int = 30)

class GetUsers(private val userRepo: IUserRepository): BaseUseCase<GetUserParam, SOResponse<UserModel>>() {

    override suspend fun execute(param: GetUserParam): SOResponse<UserModel> {
        return userRepo.getUsers(param.page, param.pageSize)
    }
}