package com.ht117.sofossill.app.screen.home

import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ht117.sofossill.R
import com.ht117.sofossill.app.Constants
import com.ht117.sofossill.app.adapter.UserAdapter
import com.ht117.sofossill.app.base.BaseFragment
import com.ht117.sofossill.app.showError
import com.ht117.sofossill.data.model.UserModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment() {

    override var layoutId = R.layout.fragment_home
    private val adapter = UserAdapter({ user ->
        navigateTo(R.id.action_move_to_detail, bundleOf(Constants.USER to user))
    }, { userId, isBookmarked ->
        viewModel.markedUser(userId, isBookmarked)
    })

    private val viewModel: HomeViewModel by viewModel()

    override fun initView() {
        super.initView()
        requireActivity().toolbar.setTitle(R.string.app_name)
        rvUser.adapter = adapter
    }

    override fun initEvent() {
        super.initEvent()
        switchMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.users.value?.dataSource?.invalidate()
            viewModel.users.removeObserver(userObserver)
            if (!isChecked) {
                viewModel.getUsers()
            } else {
                viewModel.getBookmarkedUsers()
            }
            viewModel.users.observe(this, userObserver)
        }
    }

    override fun initLogic() {
        super.initLogic()
        viewModel.getUsers()
        viewModel.users.observe(this, userObserver)
        viewModel.uiState.observe(this, messageObserver)
    }

    override fun clearObserver() {
        super.clearObserver()
        viewModel.users.removeObserver(userObserver)
        viewModel.uiState.removeObserver(messageObserver)
    }

    private val userObserver = Observer<PagedList<UserModel>> {
        adapter.submitList(it)
    }

    private val messageObserver = Observer<Int> {
        tvMessage.showError(it)
    }
}