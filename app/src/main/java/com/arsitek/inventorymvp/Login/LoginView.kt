package com.arsitek.inventorymvp.Login

import com.arsitek.inventorymvp.Model.ResponseLogin
import com.arsitek.inventorymvp.network.RepositoryCallback

interface LoginView : RepositoryCallback<ResponseLogin> {
    fun onShowLoading()
    fun onHideLoading()

}