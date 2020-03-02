package com.ht117.sofossill.domain

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.ht117.sofossill.data.model.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UserPaging(private val initParam: GetUserParam,
                 private val getUsers: GetUsers,
                 private val scope: CoroutineScope): PageKeyedDataSource<GetUserParam, UserModel>() {

    override fun loadInitial(
        params: LoadInitialParams<GetUserParam>,
        callback: LoadInitialCallback<GetUserParam, UserModel>
    ) {
        scope.launch {
            try {
                val result = getUsers.invoke(initParam)
                if (result.items.isNotEmpty() && result.hasMore) {
                    val before = initParam.copy(page = initParam.page - 1)
                    val after = initParam.copy(page = initParam.page + 1)
                    Log.d("Debug", "Users $result")
                    callback.onResult(result.items, before, after)
                }
            } catch (exp: Exception) {
                Log.d("Debug", "Error ${exp.message}")
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<GetUserParam>,
        callback: LoadCallback<GetUserParam, UserModel>
    ) {
        scope.launch {
            try {
                val result = getUsers.invoke(params.key)
                if (result.items.isNotEmpty() && result.hasMore) {
                    Log.d("Debug", "Users $result")
                    callback.onResult(result.items, params.key.copy(page = params.key.page + 1))
                }
            } catch (exp: Exception) {
                Log.d("Debug", "Error ${exp.message}")
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<GetUserParam>,
        callback: LoadCallback<GetUserParam, UserModel>
    ) {
    }
}