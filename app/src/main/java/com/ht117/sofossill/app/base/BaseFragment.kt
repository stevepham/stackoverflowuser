package com.ht117.sofossill.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment(layoutId: Int): Fragment(layoutId) {

    override fun onResume() {
        super.onResume()
        initView()
        initLogic()
        initEvent()
    }

    override fun onStop() {
        cleanUp()
        super.onStop()
    }

    open fun initView() {}
    open fun initEvent() {}
    open fun initLogic() {}
    open fun cleanUp() {}

    fun navigateTo(destination: Int, params: Bundle = Bundle.EMPTY) {
        findNavController()
            .navigate(destination, params)
    }


}