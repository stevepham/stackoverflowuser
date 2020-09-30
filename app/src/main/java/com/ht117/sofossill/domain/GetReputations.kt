package com.ht117.sofossill.domain

import androidx.paging.PagingData
import com.ht117.sofossill.app.Constants
import com.ht117.sofossill.app.base.BaseUseCase
import com.ht117.sofossill.data.model.ReputationModel
import com.ht117.sofossill.data.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class GetReputations(private val userRepo: IUserRepository): BaseUseCase<String, Flow<PagingData<ReputationModel>>>() {

    override suspend fun execute(param: String): Flow<PagingData<ReputationModel>> {
        return userRepo.getReputation(param)
    }
}