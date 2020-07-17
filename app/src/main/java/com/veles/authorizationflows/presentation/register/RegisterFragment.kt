package com.veles.authorizationflows.presentation.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.veles.authorizationflows.R
import com.veles.authorizationflows.base.mvvm.fragment.BaseFragment
import com.veles.authorizationflows.common.Keys
import com.veles.authorizationflows.model.user.TypeUser
import com.veles.authorizationflows.model.user.User
import com.veles.authorizationflows.presentation.login.LoginFragmentDirections
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.simple_toolbar.*

class RegisterFragment : BaseFragment<RegisterContract.ViewModel>(),
    RegisterContract.View {

    override val viewModel: RegisterContract.ViewModel by viewModels { viewModelFactory }

    override val toolbarResource: Int
        get() = R.layout.simple_toolbar

    override val layoutResource: Int
        get() = R.layout.fragment_register

    private var user: User = User()

    override fun onViewReady(_savedInstanceState: Bundle?) {
        user.apply {
            sntEtName.setText(name)
            sntEtEmail.setText(email)
            sntEtPass.visibility = if (typeUser == TypeUser.EMAIL_PASS) View.VISIBLE else View.GONE
        }
        viewModel.data.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(LoginFragmentDirections.main())
        })
        viewModel.isProgress.observe(viewLifecycleOwner, Observer {
            sntProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        sntAdd.setOnClickListener(this::onRegistration)
    }


    override fun onRegistration(view: View) {
        when (user.typeUser) {
            TypeUser.EMAIL_PASS -> {
                viewModel.createUserWithEmailAndPassword(createUser())
            }
            TypeUser.SOCIAL_PROVIDER -> {
                viewModel.firebaseUserUpdate(createUser())
            }
        }
    }

    override fun createUser(): User = user.apply {
        user.name = sntEtName.text.toString()
        user.email = sntEtEmail.text.toString()
        user.pass = sntEtPass.text.toString()
    }


    override fun parseArguments(_arguments: Bundle) {
        user = _arguments.getParcelable(Keys.Args.USER_DATA)!!
    }

}