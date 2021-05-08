package com.krishnanand.mobile.swiftly.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.krishnanand.mobile.swiftly.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_product.*
import javax.inject.Inject

class ProductActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ProductViewModel by viewModels {
        viewModelFactory
    }

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    private val emptyViewContainer: View by lazy {
        findViewById(R.id.empty_view_container)
    }

    private val emptyRetryButton: Button by lazy {
        emptyViewContainer.findViewById(R.id.empty_retry)
    }

    private val shimmerContainer: ShimmerFrameLayout by lazy {
        findViewById(R.id.shimmer_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        val productRecyclerAdapter = ProductRecyclerAdapter(viewModel)
        viewModel.productsLiveData.observe(this, {
            if (it == null) {
                recyclerView.visibility = View.INVISIBLE
                emptyViewContainer.visibility = View.VISIBLE
            } else {
                emptyViewContainer.visibility = View.INVISIBLE
                with(productRecyclerAdapter) {
                    product = it
                }
                with(recyclerView) {
                    layoutManager = FlexboxLayoutManager(this@ProductActivity).apply {
                        flexDirection = FlexDirection.ROW
                        justifyContent = JustifyContent.FLEX_START
                        visibility = View.VISIBLE
                    }

                    adapter = productRecyclerAdapter
                    setHasFixedSize(true)
                }
                productRecyclerAdapter.notifyDataSetChanged()
            }
            shimmerContainer.stopShimmer()
            shimmer_container.visibility = View.GONE;
        })
    }

    override fun onResume() {
        super.onResume()
        shimmerContainer.startShimmer()
        viewModel.fetchProducts()
        if (emptyViewContainer.visibility == View.VISIBLE) {
            emptyRetryButton.setOnClickListener {
                viewModel.fetchProducts()
            }
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}