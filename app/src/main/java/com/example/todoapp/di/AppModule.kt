package com.example.todoapp.di

import androidx.room.Room
import com.example.todoapp.data.local.datastore.PreferenceManager
import com.example.todoapp.data.local.room.AppDatabase
import com.example.todoapp.data.repository.AuthRepository
import com.example.todoapp.ui.presentation.login.LoginViewModel
import com.example.todoapp.ui.presentation.onboarding.OnboardingViewModel
import com.example.todoapp.ui.presentation.signup.SignupViewModel
import com.example.todoapp.ui.presentation.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import androidx.datastore.preferences.preferencesDataStore

private val android.content.Context.dataStore by preferencesDataStore(name = "settings")

val appModule = module {
    single { androidContext().dataStore }
    single { PreferenceManager(get()) }
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "todo_db"
        ).build()
    }

    single { get<AppDatabase>().userDao() }

    single { AuthRepository(get()) }

    viewModel { SplashViewModel(get()) }
    viewModel { OnboardingViewModel() }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SignupViewModel(get(), get()) }
}
