package com.veles.authorizationflows.presentation.main.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.veles.authorizationflows.R
import com.veles.authorizationflows.base.DefaultNavigator
import com.veles.authorizationflows.base.mvvm.fragment.BaseFragment
import com.veles.authorizationflows.presentation.authorization.activity.AuthorizationActivity

import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.toolbar_exit.*

class UserFragment : BaseFragment<UserContract.ViewModel, DefaultNavigator>(),
    UserContract.View {

    override val viewModel: UserContract.ViewModel by viewModels { viewModelFactory }

    override val toolbarResource: Int
        get() = R.layout.toolbar_exit

    override val layoutResource: Int
        get() = R.layout.fragment_user


    override fun onViewReady(_savedInstanceState: Bundle?) {
        viewModel.apply {
            getUser()
            data.observe(viewLifecycleOwner, Observer {
                sntEtName.setText(it.displayName?:"")
                sntEtEmail.setText(it.email?:"")
            })
        }
        sntTvLogout.setOnClickListener {
            viewModel.logout()
            navigator.openScreen(AuthorizationActivity::class.java,true)
        }
    }

}