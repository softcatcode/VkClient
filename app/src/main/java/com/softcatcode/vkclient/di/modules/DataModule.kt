package com.softcatcode.vkclient.di.modules

import android.app.Application
import android.content.Context
import com.softcatcode.vkclient.data.implementations.AuthRepositoryImpl
import com.softcatcode.vkclient.data.implementations.CommentsRepositoryImpl
import com.softcatcode.vkclient.data.implementations.ProfileRepositoryImpl
import com.softcatcode.vkclient.data.network.ApiFactory
import com.softcatcode.vkclient.data.network.ApiService
import com.softcatcode.vkclient.di.annotations.ApplicationScope
import com.softcatcode.vkclient.domain.interfaces.AuthRepository
import com.softcatcode.vkclient.domain.interfaces.CommentsRepository
import com.softcatcode.vkclient.domain.interfaces.ProfileRepository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @ApplicationScope
    @Binds
    fun bindCommentsRepository(impl: CommentsRepositoryImpl): CommentsRepository

    @ApplicationScope
    @Binds
    fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun provideStorage(application: Application): VKPreferencesKeyValueStorage {
            return VKPreferencesKeyValueStorage(application)
        }

        @ApplicationScope
        @Provides
        fun provideVkStorage(
            context: Context
        ): VKPreferencesKeyValueStorage {
            return VKPreferencesKeyValueStorage(context)
        }
    }
}
