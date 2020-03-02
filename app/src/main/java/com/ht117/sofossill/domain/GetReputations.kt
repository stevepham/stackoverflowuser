package com.ht117.sofossill.domain

import com.ht117.sofossill.app.base.BaseUseCase
import com.ht117.sofossill.data.model.ReputationModel
import com.ht117.sofossill.data.repository.IUserRepository
import com.ht117.sofossill.data.repository.network.SOResponse

data class GetReputationParam(val userId: String,
                              val page: Int,
                              val pageSize: Int = 30)

class GetReputations(private val userRepo: IUserRepository): BaseUseCase<GetReputationParam, SOResponse<ReputationModel>>() {

    override suspend fun execute(param: GetReputationParam): SOResponse<ReputationModel> {
        return userRepo.getUserReputation(param.userId, param.page, param.pageSize)
    }
}