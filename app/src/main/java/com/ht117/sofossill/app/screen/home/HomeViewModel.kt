package com.ht117.sofossill.app.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ht117.sofossill.app.base.BaseViewModel
import com.ht117.sofossill.app.base.IModel
import com.ht117.sofossill.domain.GetAllUsers
import com.ht117.sofossill.domain.GetMarkedUser
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getAllUsers: GetAllUsers)
    : BaseViewModel(), IModel<HomeState, HomeAction> {

    override val actions: Channel<HomeAction> = Channel(Channel.UNLIMITED)
    private val _state = MutableLiveData<HomeState>().apply {
        value = HomeState.InitState
    }
    override val state: LiveData<HomeState> get() = _state

    init {
        handleActions()
    }

    private fun handleActions() = viewModelScope.launch {
        actions.consumeAsFlow().collect {
            when (it) {
                else -> {
                    handleGetAllUsers()
                }
            }
        }
    }

    private suspend fun handleGetAllUsers() {
        getAllUsers.invoke()
            .cachedIn(this.viewModelScope)
            .collect {
                _state.value = HomeState.DataLoadedState(users = it)
            }
    }
}