package com.ht117.sofossill.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment: Fragment() {

    abstract var layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onResume() {
        super.onResume()
        initView()
        initLogic()
        initEvent()
    }

    override fun onPause() {
        super.onPause()
        clearObserver()
    }

    open fun initView() {}
    open fun initEvent() {}
    open fun initLogic() {}
    open fun clearObserver() {}

    fun navigateTo(destination: Int, params: Bundle = Bundle.EMPTY) {
        findNavController()
            .navigate(destination, params)
    }


}