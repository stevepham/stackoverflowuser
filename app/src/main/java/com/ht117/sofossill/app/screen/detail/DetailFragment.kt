package com.ht117.sofossill.app.screen.detail

import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.ht117.sofossill.R
import com.ht117.sofossill.app.Constants
import com.ht117.sofossill.app.adapter.ReputationAdapter
import com.ht117.sofossill.app.base.BaseFragment
import com.ht117.sofossill.app.base.IAction
import com.ht117.sofossill.app.base.IState
import com.ht117.sofossill.app.base.IView
import com.ht117.sofossill.app.showError
import com.ht117.sofossill.data.model.ReputationModel
import com.ht117.sofossill.data.model.UserModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

sealed class DetailState: IState {
    object InitState: DetailState()
    data class ShowReputation(val data: PagingData<ReputationModel>): DetailState()
    data class ErrorState(val message: String): DetailState()
}

sealed class DetailAction: IAction {
    data class LoadReputation(val userId: Long): DetailAction()
}

class DetailFragment: BaseFragment(R.layout.fragment_detail), IView<DetailState> {

    private val adapter = ReputationAdapter()
    private val viewModel: DetailViewModel by viewModel()

    override fun initView() {
        super.initView()
        rvReputation.adapter = adapter
        requireActivity().toolbar.title = (arguments?.get(Constants.USER) as UserModel?)?.displayName ?: getString(R.string.unknown)
    }

    override fun initLogic() {
        super.initLogic()

        lifecycleScope.launch {
            val userId = (arguments?.get(Constants.USER) as UserModel).userId
            viewModel.actions.send(DetailAction.LoadReputation(userId))
        }

        viewModel.state.observe(viewLifecycleOwner, Observer {
            render(it)
        })
    }

    override fun cleanUp() {
        viewModel.state.removeObservers(viewLifecycleOwner)
        super.cleanUp()
    }

    override fun render(state: DetailState) {
        lifecycleScope.launch {
            when (state) {
                is DetailState.ShowReputation -> {
                    adapter.submitData(state.data)
                }
            }
        }
    }
}