package com.ht117.sofossill.app.screen.home

import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.ht117.sofossill.R
import com.ht117.sofossill.app.Constants
import com.ht117.sofossill.app.adapter.UserPagingAdapter
import com.ht117.sofossill.app.base.BaseFragment
import com.ht117.sofossill.app.base.IAction
import com.ht117.sofossill.app.base.IState
import com.ht117.sofossill.app.base.IView
import com.ht117.sofossill.data.model.UserModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

sealed class HomeState: IState {
    object InitState: HomeState()
    data class DataLoadedState(val users: PagingData<UserModel>): HomeState()
    data class MarkUserState(val id: Long): HomeState()
    data class FailedState(val message: String): HomeState()
}

sealed class HomeAction: IAction {
    object ViewAllUsers: HomeAction()
}

class HomeFragment: BaseFragment(R.layout.fragment_home), IView<HomeState> {

    private val viewModel: HomeViewModel by viewModel()

    private val adapter = UserPagingAdapter {
        navigateTo(R.id.action_move_to_detail, bundleOf(Constants.USER to it))
    }

    override fun initView() {
        super.initView()
        requireActivity().toolbar.setTitle(R.string.app_name)
        rvUser.adapter = adapter
        lifecycleScope.launch {
            viewModel.actions.send(HomeAction.ViewAllUsers)
        }
    }

    override fun initLogic() {
        super.initLogic()
        viewModel.state.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    override fun cleanUp() {
        super.cleanUp()
        rvUser.adapter = null
        viewModel.state.removeObservers(viewLifecycleOwner)
    }

    override fun render(state: HomeState){
        lifecycleScope.launch {
            when (state) {
                is HomeState.InitState -> {

                }
                is HomeState.DataLoadedState -> {
                    adapter.submitData(state.users)
                }
                is HomeState.MarkUserState -> {

                }
                is HomeState.FailedState -> {

                }
            }
        }
    }
}