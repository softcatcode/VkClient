package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.interfaces.ProfileRepository
import javax.inject.Inject

class LoadProfileInfoUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke() = repository.loadProfile()
}