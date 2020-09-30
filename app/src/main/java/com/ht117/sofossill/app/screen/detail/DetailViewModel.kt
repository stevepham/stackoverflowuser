package com.ht117.sofossill.app.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ht117.sofossill.app.base.BaseViewModel
import com.ht117.sofossill.app.base.IModel
import com.ht117.sofossill.domain.GetReputations
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val getReputation: GetReputations)
    : BaseViewModel(), IModel<DetailState, DetailAction> {

    override val actions: Channel<DetailAction> = Channel(Channel.UNLIMITED)
    private val _state = MutableLiveData<DetailState>().apply { value = DetailState.InitState }
    override val state: LiveData<DetailState>
        get() = _state

    init {
        handleActions()
    }

    private fun handleActions() = viewModelScope.launch {
        actions.consumeAsFlow().collect {
            when (it) {
                is DetailAction.LoadReputation -> {
                    loadReputation(it.userId)
                }
            }
        }
    }

    private fun loadReputation(userId: Long) = viewModelScope.launch {
        getReputation.invoke("$userId")
            .cachedIn(viewModelScope)
            .collect {
                _state.value = DetailState.ShowReputation(it)
            }
    }

}