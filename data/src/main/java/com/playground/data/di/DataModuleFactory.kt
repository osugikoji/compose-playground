package com.playground.data.di

import com.playground.data.di.module.DataSourceModule
import com.playground.data.di.module.NetworkModule
import org.koin.core.module.Module

object DataModuleFactory {

    fun buildModules(appId: String, baseUrl: String): List<Module> {
        return NetworkModule(baseUrl).provide()
            .plus(DataSourceModule(appId).provide())
    }
}
