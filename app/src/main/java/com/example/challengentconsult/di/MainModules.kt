package com.example.challengentconsult.di

import com.example.challengentconsult.viewmodel.CheckInViewModel
import com.example.challengentconsult.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel() }
}

val checkInModule = module {
    viewModel { CheckInViewModel() }
}