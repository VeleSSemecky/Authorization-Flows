package com.veles.authorizationflows.base.mvvm.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.veles.authorizationflows.base.INavigator
import com.veles.authorizationflows.base.IToolbar
import com.veles.authorizationflows.base.mvvm.activity.IView
import com.veles.authorizationflows.base.mvvm.viewmodel.BaseViewModel
import com.veles.authorizationflows.base.mvvm.viewmodel.ViewModelFactory
import com.veles.authorizationflows.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel<*>, N : INavigator> : Fragment(), IView, Injectable,
    HasAndroidInjector,
    IToolbar, DialogInterface.OnCancelListener {

    abstract val viewModel :VM

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    protected lateinit var navigator: N

    protected open val toolbarResource: Int
        get() = 0

    private fun initToolbar() {
        if ( toolbarResource != 0)
        toolbarContainer?.let {
             updateToolbar(layoutInflater.inflate(toolbarResource, null))
        }
    }

    private fun updateToolbar(inflate: View) {
        toolbarContainer?.let {
            it.removeAllViews()
            it.addView(inflate)
        }
        createToolbar(inflate)
    }

    override fun createToolbar(toolbar: View?) {}

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
        navigator.dispose()
        super.onDestroy()
    }

    fun <T : Fragment?> openScreen(
        _fragmentToShow: Class<T>,
        _arguments: Bundle?
    ) {
        openScreen(_fragmentToShow, true, _arguments)
    }

    fun <T : Fragment?> openScreen(
        _fragmentToShow: Class<T>,
        _addToBackStack: Boolean,
        _arguments: Bundle?
    ) {
        /*  final Fragment currentFragment = getActivity().getSupportFragmentManager()
                .findFragmentById(getActivity().getFragmentContainerId());
        if (currentFragment != null && currentFragment.getClass().equals(_fragmentToShow)) return;*/
        val fragment: Fragment
        fragment = if (_arguments == null) {
            instantiate(requireActivity(), _fragmentToShow.name)
        } else {
            instantiate(requireActivity(), _fragmentToShow.name, _arguments)
        }
        val fragmentTransaction = requireActivity().supportFragmentManager
            .beginTransaction()
        if (_addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.name)
        }
        fragmentTransaction.replace(
            (requireView().parent as ViewGroup).id,
            fragment,
            fragment.javaClass.name
        )
        fragmentTransaction.commitAllowingStateLoss()
    }

    protected fun setFullScreen() {
        if (activity != null) requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    protected fun cleanFullScreen() {
        if (activity != null)  requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

}