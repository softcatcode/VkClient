package com.softcatcode.vkclient.presentation.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcatcode.vkclient.domain.useCase.GetFavouritesUseCase
import com.softcatcode.vkclient.domain.useCase.LoadNextFavouritesUseCase
import com.softcatcode.vkclient.presentation.extensions.mergeWith
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(
    private val loadNextFavouritesUseCase: LoadNextFavouritesUseCase,
    getFavouritesUseCase: GetFavouritesUseCase,
): ViewModel() {

    private val favouritesFlow = getFavouritesUseCase()
    private val updatedStateFlow = MutableSharedFlow<FavouritesScreenState>()

    val state = favouritesFlow
        .filter { it.isNotEmpty() }
        .map { FavouritesScreenState.Content(posts = it) as FavouritesScreenState }
        .onStart { emit(FavouritesScreenState.Loading) }
        .mergeWith(updatedStateFlow)

    init {
        loadFavourites()
    }

    private fun loadFavourites() {
        viewModelScope.launch(Dispatchers.IO) {
            updatedStateFlow.emit(
                FavouritesScreenState.Content(
                    posts = favouritesFlow.value,
                    nextLoading = true
                )
            )
            loadNextFavouritesUseCase()
        }
    }
}