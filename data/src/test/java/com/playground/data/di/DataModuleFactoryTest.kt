package com.playground.data.di

import co.infinum.retromock.Retromock
import com.playground.data.executor.SourceExecutor
import com.playground.data.remote.api.TransactionApi
import com.playground.data.remote.api.TransactionMockApi
import com.playground.data.repository.TransactionRepository
import com.playground.data.repository.impl.TransactionRepositoryImpl
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import retrofit2.Retrofit

class DataModuleFactoryTest : KoinTest {

    @Before
    fun setUp() {
        startKoin { modules(DataModuleFactory.buildModules("123", "http://base.com")) }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `verify Moshi injection`() {
        // act
        val result = runCatching { get<Moshi>() }.getOrNull()

        // assert
        assertEquals(true, result != null)
    }

    @Test
    fun `verify OkHttpClient injection`() {
        // act
        val result = runCatching { get<OkHttpClient>() }.getOrNull()

        // assert
        assertEquals(true, result != null)
    }

    @Test
    fun `verify Retrofit injection`() {
        // act
        val result = runCatching { get<Retrofit>() }.getOrNull()

        // assert
        assertEquals(true, result != null)
    }

    @Test
    fun `verify TransactionRepository injection`() {
        // act
        val result = runCatching { get<TransactionRepository>() }.getOrNull()

        // assert
        assertEquals(true, result is TransactionRepositoryImpl)
    }

    @Test
    fun `verify TransactionApi injection`() {
        // act
        val result = runCatching { get<TransactionApi>() }.getOrNull()

        // assert
        assertEquals(true, result != null)
    }

    @Test
    fun `verify DataSourceExecutor injection`() {
        // act
        val result = runCatching { get<SourceExecutor>() }.getOrNull()

        // assert
        assertEquals(true, result != null)
    }

    @Test
    fun `verify Retromock injection`() {
        // act
        val result = runCatching { get<Retromock>() }.getOrNull()

        // assert
        assertEquals(true, result != null)
    }

    @Test
    fun `verify TransactionMockApi injection`() {
        // act
        val result = runCatching { get<TransactionMockApi>() }.getOrNull()

        // assert
        assertEquals(true, result != null)
    }
}
