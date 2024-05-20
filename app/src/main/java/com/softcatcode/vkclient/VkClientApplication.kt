package com.softcatcode.vkclient

import android.app.Application
import com.softcatcode.vkclient.di.components.DaggerApplicationComponent

class VkClientApplication: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}