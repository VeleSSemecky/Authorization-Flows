package com.veles.authorizationflows.presentation.change

import javax.inject.Inject

class ChangeFieldViewModel @Inject constructor(
    private val model: ChangeFieldModel
): ChangeFieldContract.ViewModel(model){

}