package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.interfaces.ProfileRepository
import javax.inject.Inject

class GetProfileInfoUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke() = repository.getProfileDataFlow()
}