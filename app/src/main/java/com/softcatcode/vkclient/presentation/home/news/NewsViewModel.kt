package com.softcatcode.vkclient.presentation.home.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softcatcode.vkclient.data.mapper.NewsFeedMapper
import com.softcatcode.vkclient.data.network.ApiFactory
import com.softcatcode.vkclient.domain.entities.StatisticsItem
import com.softcatcode.vkclient.domain.entities.StatisticsType
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(application: Application): AndroidViewModel(application) {

    private val _state = MutableLiveData<NewsScreenState>(NewsScreenState.Initial)
    val state: LiveData<NewsScreenState> = _state

    private val mapper = NewsFeedMapper()

    init {
        loadRecommended()
    }

    private fun updateStatList(list: List<StatisticsItem>, type: StatisticsType) = list
        .toMutableList()
        .apply {
            replaceAll {
                if (it.type == type)
                    it.copy(count = it.count + 1)
                else
                    it
            }
        }

    fun updateStatistics(postId: String, type: StatisticsType) {
        val currentState = state.value
        if (currentState !is NewsScreenState.Posts)
            return

        val postList = currentState.postList.toMutableList()
        postList.replaceAll {
            if (it.id == postId)
                it.copy(statistics = updateStatList(it.statistics, type))
            else
                it
        }
        _state.value = NewsScreenState.Posts(postList)
    }

    fun removePost(id: String) {
        val currentState = state.value
        if (currentState !is NewsScreenState.Posts)
            return

        val postList = currentState.postList.toMutableList().apply {
            removeAll { it.id == id }
        }
        _state.value = NewsScreenState.Posts(postList)
    }

    fun loadRecommended() {
        viewModelScope.launch(Dispatchers.IO) {
            val storage = VKPreferencesKeyValueStorage(getApplication())
            val token = VKAccessToken.restore(storage) ?: return@launch
            val response = ApiFactory.apiService.getRecommendations(token.accessToken)
            Log.i("mumu", "secret: ${token.secret}")
            Log.i("mumu", "email: ${token.email}")
            Log.i("mumu", "access_token: ${token.accessToken}")

            val postList = mapper.mapResponseToPosts(response)
            withContext(Dispatchers.Main) {
                _state.value = NewsScreenState.Posts(postList)
            }
        }
    }

}