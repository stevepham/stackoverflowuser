package com.ht117.sofossill.app.screen.home

import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ht117.sofossill.R
import com.ht117.sofossill.app.Constants.USER
import com.ht117.sofossill.app.adapter.UserAdapter
import com.ht117.sofossill.app.base.BaseFragment
import com.ht117.sofossill.data.model.UserModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment() {

    override var layoutId = R.layout.fragment_home
    private val adapter = UserAdapter {
        navigateTo(R.id.action_move_to_detail, bundleOf(USER to it))
    }
    private val viewModel: HomeViewModel by viewModel()

    override fun initView() {
        super.initView()
        requireActivity().toolbar.setTitle(R.string.app_name)
        rvUser.adapter = adapter
    }

    override fun initLogic() {
        super.initLogic()
        viewModel.loadUsers()
        viewModel.users.observe(this, userObserver)
    }

    override fun clearObserver() {
        super.clearObserver()
        viewModel.users.removeObserver(userObserver)
    }

    private val userObserver = Observer<PagedList<UserModel>> {
        adapter.submitList(it)
    }
}