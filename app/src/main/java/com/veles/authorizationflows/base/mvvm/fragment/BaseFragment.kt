package com.veles.authorizationflows.base.mvvm.fragment

import android.content.DialogInterface
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.veles.authorizationflows.base.mvvm.activity.IView
import com.veles.authorizationflows.base.mvvm.viewmodel.BaseViewModel
import com.veles.authorizationflows.base.mvvm.viewmodel.ViewModelFactory
import com.veles.authorizationflows.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel<*>> : Fragment(), IView, Injectable,
    HasAndroidInjector,
     DialogInterface.OnCancelListener {

    abstract val viewModel: VM

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    protected open val toolbarResource: Int
        get() = 0

    private fun initToolbar() {
        if (toolbarResource != 0)
            toolbarContainer?.let {
                updateToolbar(layoutInflater.inflate(toolbarResource, null))
            }
    }

    private fun updateToolbar(inflate: View) {
        toolbarContainer?.let {
            it.removeAllViews()
            it.addView(inflate)
        }
    }

    override fun onCancel(dialogInterface: DialogInterface) {}

    @get:LayoutRes
    protected abstract val layoutResource: Int

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arguments = arguments
        arguments?.let { parseArguments(it) }
    }

    protected open fun parseArguments(_arguments: Bundle) {}

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutResource, container, false)
        includeLayout(view)
        return view
    }

    protected open fun includeLayout(view: View?) {}

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initToolbar()
        onViewReady(savedInstanceState)
    }

    protected abstract fun onViewReady(_savedInstanceState: Bundle?)

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentDispatchingAndroidInjector
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
    }

}