package com.veles.authorizationflows.base.mvvm.activity

import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.veles.authorizationflows.di.Injectable
import com.veles.authorizationflows.base.mvvm.viewmodel.BaseViewModel
import com.veles.authorizationflows.base.mvvm.viewmodel.ViewModelFactory
import com.veles.authorizationflows.base.INavigator
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<VM:BaseViewModel<*>> : AppCompatActivity(),  IView, Injectable, HasAndroidInjector {

    abstract val viewModel :VM

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @get:LayoutRes
    protected abstract val layoutResource: Int

    @CallSuper
    public override fun onCreate(_savedInstanceState: Bundle?) {
        super.onCreate(_savedInstanceState)
        if(layoutResource!=0)
        setContentView(layoutResource)
        parseExtras(intent)
        onViewReady(_savedInstanceState)
    }

    protected open fun onViewReady(_savedInstanceState: Bundle?) {}

    protected fun parseExtras(_intent: Intent?) {}


    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentDispatchingAndroidInjector
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
    }

    @CallSuper
    override fun onBackPressed() {
            super.onBackPressed()
    }

    inline fun <reified T> Flow<T>.toLiveData(coroutineContext: CoroutineContext = viewModel.viewModelScope.coroutineContext): LiveData<T> {
        return liveData<T>(context = coroutineContext) {
            emitSource(this@toLiveData.asLiveData())
        }
    }

}

