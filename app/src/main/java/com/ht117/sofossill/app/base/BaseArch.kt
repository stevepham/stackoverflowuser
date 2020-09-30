package com.ht117.sofossill.app.base

import androidx.lifecycle.LiveData
import kotlinx.coroutines.channels.Channel

interface IAction

interface IState

interface IModel<State: IState, Action: IAction> {
    val actions: Channel<Action>
    val state: LiveData<State>
}

interface IView<State: IState> {
    fun render(state: State)
}

