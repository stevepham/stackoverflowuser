package com.ht117.sofossill.app.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ht117.sofossill.app.Constants
import com.ht117.sofossill.app.base.BaseViewModel
import com.ht117.sofossill.data.model.UserModel
import com.ht117.sofossill.domain.GetUserParam
import com.ht117.sofossill.domain.GetUsers
import com.ht117.sofossill.domain.UserPaging

class HomeViewModel(private val getUser: GetUsers): BaseViewModel() {

    var users: LiveData<PagedList<UserModel>> = MutableLiveData()
    private val config = PagedList.Config.Builder()
        .setPageSize(Constants.PAGE_SIZE)
        .setEnablePlaceholders(false)
        .build()

    fun loadUsers() {
        val param = GetUserParam(1, Constants.PAGE_SIZE)
        val dataSource = object: DataSource.Factory<GetUserParam, UserModel>() {
            override fun create(): DataSource<GetUserParam, UserModel> {
                return UserPaging(param, getUser, ioScope)
            }
        }
        users = LivePagedListBuilder<GetUserParam, UserModel>(dataSource, config).build()
    }
}