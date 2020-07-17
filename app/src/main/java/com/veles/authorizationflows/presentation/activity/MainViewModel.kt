package com.veles.authorizationflows.presentation.activity

import javax.inject.Inject

class MainViewModel @Inject internal constructor(mainModel: MainModel) :
    MainContract.ViewModel(mainModel) {

}
