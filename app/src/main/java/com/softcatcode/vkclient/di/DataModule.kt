package com.softcatcode.vkclient.di

import android.content.Context
import com.softcatcode.vkclient.data.implementations.NewsManager
import com.softcatcode.vkclient.data.network.ApiFactory
import com.softcatcode.vkclient.data.network.ApiService
import com.softcatcode.vkclient.domain.interfaces.NewsManagerInterface
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindNewsManager(impl: NewsManager): NewsManagerInterface

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
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
