package com.krishnanand.mobile.swiftly.product

import androidx.annotation.UiThread
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.krishnanand.mobile.swiftly.data.Product
import com.krishnanand.mobile.swiftly.utils.CoroutinesTestRule
import com.krishnanand.mobile.swiftly.utils.readProductFromFile
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.stubbing.Answer
import retrofit2.Call
import java.io.IOException
import java.io.InputStreamReader

@ExperimentalCoroutinesApi
class ProductViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var productApi: ProductApi

    @Mock
    lateinit var enqueueCall: Call<Product>

    @InjectMocks
    lateinit var productViewModel: ProductViewModel

    lateinit var product: Product

    var fetchStatus: Boolean = false

    @Before
    @Throws(IOException::class)
    fun setUp() {
        product = readProductFromFile()
    }

    @Test
    @UiThread
    fun fetchProducts() = coroutinesTestRule.testDispatcher.runBlockingTest {
        Mockito.`when`(productApi.fetchProducts()).thenReturn(enqueueCall)
        Mockito.doAnswer(
            Answer {
                fetchStatus = true
            }
        ).`when`(enqueueCall).enqueue(Mockito.any())
        productViewModel.fetchProducts()
        delay(2000)
        Mockito.verify(productApi).fetchProducts()
        Mockito.verify(enqueueCall).enqueue(Mockito.any())
    }

}