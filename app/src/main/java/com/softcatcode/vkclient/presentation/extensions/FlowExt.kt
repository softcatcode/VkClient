package com.softcatcode.vkclient.presentation.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge

fun <T> Flow<T>.mergeWith(other: Flow<T>): Flow<T> {
    return merge(this, other)
}
