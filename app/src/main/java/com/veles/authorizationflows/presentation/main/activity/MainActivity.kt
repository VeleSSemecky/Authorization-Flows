package com.veles.authorizationflows.presentation.main.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.veles.authorizationflows.R
import com.veles.authorizationflows.base.DefaultNavigator
import com.veles.authorizationflows.base.mvvm.activity.BaseActivity
import com.veles.authorizationflows.presentation.authorization.login.LoginFragment
import com.veles.authorizationflows.presentation.main.user.UserFragment


public class MainActivity : BaseActivity<MainContract.ViewModel, DefaultNavigator>(), MainContract.View {

    override val viewModel: MainContract.ViewModel by viewModels { viewModelFactory }

    override val layoutResource: Int = R.layout.activity_authorization

    override val fragmentContainerId: Int = R.id.fragment_container

    override fun onViewReady(_savedInstanceState: Bundle?) {
        super.onViewReady(_savedInstanceState)
        viewModel.openScreen(UserFragment::class.java).observe(this, Observer {
            navigator.openScreen(it,false,null)
        })
    }

}
