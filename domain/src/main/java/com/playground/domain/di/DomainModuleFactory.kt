package com.playground.domain.di

import com.playground.data.di.DataModuleFactory
import com.playground.domain.di.module.UseCaseModule
import org.koin.core.module.Module

object DomainModuleFactory {

    fun buildModules(appId: String, baseUrl: String): List<Module> {
        return UseCaseModule().provide()
            .plus(DataModuleFactory.buildModules(appId, baseUrl))
    }
}
