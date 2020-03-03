package com.ht117.sofossill.domain

import com.ht117.sofossill.app.Constants
import com.ht117.sofossill.app.base.BaseUseCase
import com.ht117.sofossill.data.model.ReputationModel
import com.ht117.sofossill.data.repository.IUserRepository

data class GetReputationParam(val userId: String,
                              val page: Int,
                              val pageSize: Int = Constants.PAGE_SIZE)

class GetReputations(private val userRepo: IUserRepository): BaseUseCase<GetReputationParam, List<ReputationModel>>() {

    override suspend fun execute(param: GetReputationParam): List<ReputationModel> {
        return userRepo.getUserReputation(param.userId, param.page, param.pageSize)
    }
}