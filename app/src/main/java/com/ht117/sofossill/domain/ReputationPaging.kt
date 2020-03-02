package com.ht117.sofossill.domain

import androidx.paging.PageKeyedDataSource
import com.ht117.sofossill.data.model.ReputationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ReputationPaging(private val initParam: GetReputationParam,
                       private val getRepu: GetReputations,
                       private val scope: CoroutineScope): PageKeyedDataSource<GetReputationParam, ReputationModel>() {
    override fun loadInitial(
        params: LoadInitialParams<GetReputationParam>,
        callback: LoadInitialCallback<GetReputationParam, ReputationModel>
    ) {
        scope.launch {
            val result = getRepu.invoke(initParam)
            if (result.items.isNotEmpty() && result.hasMore) {
                val before = initParam.copy(page = initParam.page -1)
                val after = initParam.copy(page = initParam.page + 1)
                callback.onResult(result.items, before, after)
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<GetReputationParam>,
        callback: LoadCallback<GetReputationParam, ReputationModel>
    ) {
        scope.launch {
            val result = getRepu.invoke(params.key)
            if (result.items.isNotEmpty() && result.hasMore) {
                callback.onResult(result.items, params.key.copy(page = params.key.page + 1))
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<GetReputationParam>,
        callback: LoadCallback<GetReputationParam, ReputationModel>
    ) {
    }
}