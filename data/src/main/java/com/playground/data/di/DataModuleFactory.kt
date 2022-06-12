package com.playground.data.di

import com.playground.data.di.module.DataSourceModule
import com.playground.data.di.module.NetworkModule
import org.koin.core.module.Module

object DataModuleFactory {

    private const val BASE_URL = "https://openexchangerates.org/"

    fun buildModules(appId: String, baseUrl: String = BASE_URL): List<Module> {
        return NetworkModule(baseUrl).provide()
            .plus(DataSourceModule(appId).provide())
    }
}
