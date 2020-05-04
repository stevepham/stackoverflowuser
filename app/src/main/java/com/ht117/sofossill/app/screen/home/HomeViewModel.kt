package com.ht117.sofossill.app.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ht117.sofossill.R
import com.ht117.sofossill.app.Constants
import com.ht117.sofossill.app.base.BaseViewModel
import com.ht117.sofossill.data.model.UserModel
import com.ht117.sofossill.data.repository.IUserRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val userRepo: IUserRepository): BaseViewModel() {

    var uiState = MutableLiveData<Int>()
    var users: LiveData<PagedList<UserModel>> = MutableLiveData()

    fun getUsers() {
        users = LivePagedListBuilder(allUsersSource, config)
            .setBoundaryCallback(boundary)
            .build()
    }

    fun getBookmarkedUsers() {
        users = LivePagedListBuilder(bookmarkedUsersSource, config)
            .build()
    }

    fun markedUser(userId: Long, isBookmarked: Boolean) {
        ioScope.launch {
            userRepo.markedUser(userId, isBookmarked)
        }
    }

    private val config = PagedList.Config.Builder()
        .setPageSize(Constants.PAGE_SIZE)
        .setEnablePlaceholders(false)
        .build()

    private val allUsersSource = object: DataSource.Factory<Int, UserModel>() {
        override fun create(): DataSource<Int, UserModel> {
            return userRepo.getUsersSource().create()
        }
    }

    private val bookmarkedUsersSource = object: DataSource.Factory<Int, UserModel>() {
        override fun create(): DataSource<Int, UserModel> {
            return userRepo.getBookmarkedUser().create()
        }
    }

    private val boundary = object: PagedList.BoundaryCallback<UserModel>() {
        private var prevPage = 1
        private var isCallingRequest = false

        override fun onZeroItemsLoaded() {
            loadUsers()
        }

        override fun onItemAtEndLoaded(itemAtEnd: UserModel) {
            loadUsers()
        }

        private fun loadUsers() {
            if (isCallingRequest) return
            isCallingRequest = true
            ioScope.launch {
                try {
                    userRepo.getUsersFromApiAndCache(prevPage++)
                } catch (exp: Exception) {
                    uiState.postValue(R.string.txt_failed_to_load_user)
                }
            }
            isCallingRequest = false
        }
    }
}