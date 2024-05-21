package com.softcatcode.vkclient.data.implementations

import com.softcatcode.vkclient.data.mapper.DtoMapper
import com.softcatcode.vkclient.data.network.ApiService
import com.softcatcode.vkclient.domain.entities.ProfileData
import com.softcatcode.vkclient.domain.interfaces.ProfileRepository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val storage: VKPreferencesKeyValueStorage,
    private val apiService: ApiService,
    private val mapper: DtoMapper
): ProfileRepository {

    private var _profile: ProfileData? = null
    private val profile: ProfileData
        get() = _profile ?: throw RuntimeException("ProfileData is null.")
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val token
        get() = VKAccessToken.restore(storage)?.accessToken ?: throw RuntimeException("Access token is null.")


    private val loadProfileRequest = MutableSharedFlow<Unit>(replay = 1)
    private val profileFlow = flow {
        loadProfileRequest.emit(Unit)
        loadProfileRequest.collect {
            loadProfileData()
            emit(profile)
        }
    }.retry {
        delay(RETRY_TIMEOUT_MILLIS)
        true
    }

    private suspend fun loadProfileData() {
        val infoResponse = apiService.getProfileInfo(token)
        val friendsResponse = apiService.getFriends(token)
        val photosResponse = apiService.getPhotos(token, infoResponse.data.id)
        _profile = mapper.mapResponseToProfile(infoResponse, photosResponse, friendsResponse)
    }

    override suspend fun loadProfile() = loadProfileRequest.emit(Unit)

    override fun getProfileDataFlow() = profileFlow.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = profile
    )

    companion object {
        private const val RETRY_TIMEOUT_MILLIS = 1000L
    }
}