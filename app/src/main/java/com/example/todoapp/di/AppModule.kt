package com.example.todoapp.di

import androidx.room.Room
import com.example.todoapp.data.local.datastore.PreferenceManager
import com.example.todoapp.utils.Constants
import com.example.todoapp.data.local.room.auth_database.AuthDatabase
import com.example.todoapp.ui.presentation.login.LoginViewModel
import com.example.todoapp.ui.presentation.onboarding.OnboardingViewModel
import com.example.todoapp.ui.presentation.signup.SignupViewModel
import com.example.todoapp.ui.presentation.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import androidx.datastore.preferences.preferencesDataStore
import com.example.todoapp.data.local.room.todo_database.TodoDatabase
import com.example.todoapp.data.repository.MainRepository
import com.example.todoapp.data.repository.MainRepositoryImpl
import com.example.todoapp.ui.presentation.calender_screen.CalenderViewModel
import com.example.todoapp.ui.presentation.home.MainViewModel
import com.example.todoapp.ui.presentation.home_screen.HomeViewModel
import com.example.todoapp.ui.presentation.task_screen.TodoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf

private val android.content.Context.dataStore by preferencesDataStore(name = Constants.DATASTORE_NAME)
val appModule = module {
    single { PreferenceManager(get()) }
    single { androidContext().dataStore }
    single {
        Room.databaseBuilder(
            androidContext(),
            AuthDatabase::class.java,
            Constants.AUTH_DATABASE_NAME
        )
            .build()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            TodoDatabase::class.java,
            Constants.TODO_DATABASE_NAME
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }
    single { get<AuthDatabase>().userDao() }
    single { get<TodoDatabase>().todoDao() }
    viewModelOf(::SplashViewModel)
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::SignupViewModel)
    viewModelOf(::TodoViewModel)
    viewModelOf(::CalenderViewModel)
    viewModelOf(::HomeViewModel)

    single<MainRepository> {
        MainRepositoryImpl(get(), get())
    }

    viewModel {
        MainViewModel()
    }
}
