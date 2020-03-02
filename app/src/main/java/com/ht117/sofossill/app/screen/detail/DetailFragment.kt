package com.ht117.sofossill.app.screen.detail

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ht117.sofossill.R
import com.ht117.sofossill.app.Constants
import com.ht117.sofossill.app.adapter.ReputationAdapter
import com.ht117.sofossill.app.base.BaseFragment
import com.ht117.sofossill.data.model.ReputationModel
import com.ht117.sofossill.data.model.UserModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment: BaseFragment() {

    override var layoutId = R.layout.fragment_detail
    private val adapter = ReputationAdapter()
    private val viewModel: DetailViewModel by viewModel()

    override fun initView() {
        super.initView()
        rvReputation.adapter = adapter
        requireActivity().toolbar.title = (arguments?.get(Constants.USER) as UserModel?)?.displayName ?: getString(R.string.unknown)
    }

    override fun initLogic() {
        super.initLogic()
        val userId = (arguments?.get(Constants.USER) as UserModel).userId

        viewModel.loadReputation(userId)
        viewModel.reputations.observe(this, reputationObserver)
    }

    override fun clearObserver() {
        super.clearObserver()
        viewModel.reputations.removeObserver(reputationObserver)
    }

    private val reputationObserver = Observer<PagedList<ReputationModel>> {
        adapter.submitList(it)
    }
}