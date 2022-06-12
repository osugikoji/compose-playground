package com.playground.domain.di

import com.playground.domain.usecase.TransactionUseCases
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get

class DomainModuleFactoryTest : KoinTest {

    @Before
    fun setUp() {
        startKoin { modules(DomainModuleFactory.buildModules("123")) }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `verify TransactionUseCases injection`() {
        // act
        val result = runCatching { get<TransactionUseCases>() }.getOrNull()

        // assert
        assertEquals(true, result != null)
    }
}
