package com.ht117.sofossill.domain

import android.util.Log
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
            try {
                val result = getRepu.invoke(initParam)
                val before = initParam.copy(page = initParam.page - 1)
                val after = initParam.copy(page = initParam.page + 1)
                callback.onResult(result, before, after)
            } catch (exp: Exception) {
                Log.d("Debug", "Failed to get reputation ${exp.message}")
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<GetReputationParam>,
        callback: LoadCallback<GetReputationParam, ReputationModel>
    ) {
        scope.launch {
            try {
                val result = getRepu.invoke(params.key)
                callback.onResult(result, params.key.copy(page = params.key.page + 1))
            } catch (exp: Exception) {
                Log.d("Debug", "Failed to load after ${exp.message}")
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<GetReputationParam>,
        callback: LoadCallback<GetReputationParam, ReputationModel>
    ) {
    }
}