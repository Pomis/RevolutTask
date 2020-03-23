package com.revolut.currencies

import com.nhaarman.mockitokotlin2.*
import com.revolut.common.CommonContract
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CurrenciesPresenterTest {

    lateinit var sut: CurrenciesPresenter
    lateinit var orchestrator: CurrenciesContract.Orchestrator

    @Mock
    lateinit var view: CurrenciesContract.View

    private val testCoroutineScope: CommonContract.Scope = TestCoroutineScope()


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        orchestrator = mock {
            onAsync { getLatestRates() } doReturn mapOf(
                "USD" to 1f,
                "EUR" to 1.1f,
                "RUB" to 79f
            )
        }

        sut = CurrenciesPresenter(view, orchestrator, testCoroutineScope)
    }

    @Test
    fun `Presenter inits and loads currency list`() {
        sut.init()
        verify(view).init(eq(sut))
        assert(sut.currenciesCount == 3)
    }

    fun <T : Any, R> KStubbing<T>.onAsync(m: suspend T.() -> R) = runBlocking { whenever(mock.m()) }

}