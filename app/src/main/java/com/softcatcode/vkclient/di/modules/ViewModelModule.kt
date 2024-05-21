package com.softcatcode.vkclient.di.modules

import androidx.lifecycle.ViewModel
import com.softcatcode.vkclient.di.annotations.ViewModelKey
import com.softcatcode.vkclient.presentation.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    @Binds
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}
