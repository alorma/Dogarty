package com.alorma.dogarty.di

import com.alorma.dogarty.ui.screens.MainViewModel
import com.alorma.dogarty.ui.screens.login.LoginViewModel
import com.alorma.dogarty.ui.screens.user.LoggedUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    operator fun invoke() = module {
        viewModel { MainViewModel(get()) }
        viewModel { LoginViewModel(get()) }
        viewModel { LoggedUserViewModel(get(), get()) }
    }
}