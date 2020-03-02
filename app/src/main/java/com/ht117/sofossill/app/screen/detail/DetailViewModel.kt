package com.ht117.sofossill.app.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ht117.sofossill.app.Constants
import com.ht117.sofossill.app.base.BaseViewModel
import com.ht117.sofossill.data.model.ReputationModel
import com.ht117.sofossill.domain.GetReputationParam
import com.ht117.sofossill.domain.GetReputations
import com.ht117.sofossill.domain.ReputationPaging

class DetailViewModel(private val getReputation: GetReputations): BaseViewModel() {

    var reputations: LiveData<PagedList<ReputationModel>> = MutableLiveData()
    private val config = PagedList.Config.Builder()
        .setPageSize(Constants.PAGE_SIZE)
        .setEnablePlaceholders(false)
        .build()

    fun loadReputation(userId: Long) {
        val param = GetReputationParam(userId.toString(), 1, Constants.PAGE_SIZE)
        val dataSource = object: DataSource.Factory<GetReputationParam, ReputationModel>() {
            override fun create(): DataSource<GetReputationParam, ReputationModel> {
                return ReputationPaging(param, getReputation, ioScope)
            }
        }
        reputations = LivePagedListBuilder<GetReputationParam, ReputationModel>(dataSource, config).build()
    }
}