package com.example.todoapp.di

import androidx.room.Room
import com.example.todoapp.data.local.datastore.PreferenceManager
import com.example.todoapp.data.local.room.auth_database.AuthDatabase
import com.example.todoapp.data.repository.AuthRepository
import com.example.todoapp.ui.presentation.login.LoginViewModel
import com.example.todoapp.ui.presentation.onboarding.OnboardingViewModel
import com.example.todoapp.ui.presentation.signup.SignupViewModel
import com.example.todoapp.ui.presentation.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import androidx.datastore.preferences.preferencesDataStore
import com.example.todoapp.data.local.room.todo_database.TodoDatabase
import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.data.repository.TodoRepositoryImpl
import com.example.todoapp.domain.usecase.AddTodoUseCase
import com.example.todoapp.domain.usecase.GetAllTodosUseCase
import com.example.todoapp.domain.usecase.UpdateTodoUseCase
import com.example.todoapp.ui.presentation.calender_screen.CalenderViewModel
import com.example.todoapp.ui.presentation.home_screen.HomeViewModel
import com.example.todoapp.ui.presentation.task_screen.TodoViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf

private val android.content.Context.dataStore by preferencesDataStore(name = "settings")
val appModule = module {
    single { PreferenceManager(get()) }
    single { androidContext().dataStore }
    single {
        Room.databaseBuilder(
            androidContext(),
            AuthDatabase::class.java,
            "auth_db"
        ).build()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }
    single { get<AuthDatabase>().userDao() }
    single { get<TodoDatabase>().todoDao() }
    singleOf(::AuthRepository)
    viewModelOf(::SplashViewModel)
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::SignupViewModel)
    viewModelOf(::TodoViewModel)
    viewModelOf(::CalenderViewModel)
    viewModelOf(::HomeViewModel)

    single<TodoRepository> {
        TodoRepositoryImpl(get())
    }
    factory {
        AddTodoUseCase(get())
    }
    factory {
        UpdateTodoUseCase(get())
    }
    factory {
        GetAllTodosUseCase(get())
    }
}
