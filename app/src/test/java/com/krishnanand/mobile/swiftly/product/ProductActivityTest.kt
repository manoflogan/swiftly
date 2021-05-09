package com.krishnanand.mobile.swiftly.product

import android.content.Context
import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.krishnanand.mobile.swiftly.R
import com.krishnanand.mobile.swiftly.SwiftlyTestAppliciation
import com.krishnanand.mobile.swiftly.data.Product
import com.krishnanand.mobile.swiftly.utils.InjectableActivityScenario
import com.krishnanand.mobile.swiftly.utils.injectableActivityScenario
import com.krishnanand.mobile.swiftly.utils.readProductFromFile
import dagger.android.DispatchingAndroidInjector
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.kotlin.*
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import org.robolectric.shadows.ShadowLooper

@Config(
    sdk = [
        Config.OLDEST_SDK
    ],
    application = SwiftlyTestAppliciation::class
)
@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
class ProductActivityTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var mockDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Mock
    lateinit var mockViewModelFactory: ViewModelProvider.Factory

    @Mock
    lateinit var mockProductViewModel: ProductViewModel

    lateinit var context: Context

    lateinit var product: Product

    lateinit var productLiveData: MutableLiveData<Product>

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        whenever(mockViewModelFactory.create(ProductViewModel::class.java)).thenReturn(mockProductViewModel)
        product = readProductFromFile()
        productLiveData = MutableLiveData()
        mockProductViewModel.stub {
            on {
                productsLiveData
            } doReturn productLiveData
        }
    }

    @Test
    fun testActivityLaunch__ReturnsProducts__ShowsSuccessView() {
        launchActivity(product) {
            ShadowLooper.shadowMainLooper().idle()
            verify(mockViewModelFactory).create(ProductViewModel::class.java)
            verify(mockProductViewModel).fetchProducts()
            Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).perform(
                RecyclerViewActions.scrollTo<ProductViewHolder>(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(product.managerSpecials[0].displayName)
                    )
                )
            )
        }
    }

    @Test
    fun testActivityLaunch__ReturnsNull__ShowsErrorView() {
        launchActivity(null) {
            ShadowLooper.shadowMainLooper().idle()
            verify(mockViewModelFactory).create(ProductViewModel::class.java)
            verify(mockProductViewModel).fetchProducts()
            Espresso.onView(ViewMatchers.withId(R.id.empty_view_container)).check(
                ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    private fun launchActivity(
        product: Product?,
        block: InjectableActivityScenario<ProductActivity>.() -> Unit
    ) {
        mockProductViewModel.stub {
            on {
                fetchProducts()
            } doAnswer {
                productLiveData.value = product
            }
        }
        injectableActivityScenario<ProductActivity> {
            injectActivity {
                this.dispatchingAndroidInjector = mockDispatchingAndroidInjector
                this.viewModelFactory = mockViewModelFactory
            }
            launch(null)
        }.use(block)
    }
}