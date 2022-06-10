package com.playground.data.di

import com.playground.data.di.module.DataSourceModule
import com.playground.data.di.module.NetworkModule
import org.koin.core.module.Module

object DataModuleFactory {

    fun buildModules(appId: String): List<Module> {
        return NetworkModule().provide()
            .plus(DataSourceModule(appId).provide())
    }
}
