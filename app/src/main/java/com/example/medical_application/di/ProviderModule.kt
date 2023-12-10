package com.example.medical_application.di


import com.example.medical_application.ui.presenter.CreateQuizPresenter
import com.example.medical_application.ui.presenter.QuestionSettingPresenter
import com.example.medical_application.ui.presenter.UserPresenter
import com.example.medical_application.ui.presenter.UserQuizPresenter
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import moxy.presenter.ProvidePresenter
import javax.inject.Singleton

@Module
class ProviderModule {
    @Provides
    @Singleton
    fun provideCreateQuestionModule(

    ):QuestionSettingPresenter{
        return  QuestionSettingPresenter()
    }

    @Provides
    @Singleton
    fun provideQuizModule(

    ):CreateQuizPresenter{
        return  CreateQuizPresenter()
    }

    @Provides
    @Singleton
    fun provideUserModule(
    ):UserPresenter{
        return UserPresenter()
    }



    @Provides
    @Singleton
    fun provideUserQuizModule():UserQuizPresenter{
        return  UserQuizPresenter()
    }


}