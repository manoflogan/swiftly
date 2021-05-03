package com.krishnanand.mobile.swiftly.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.krishnanand.mobile.swiftly.data.ManagerSpecials
import com.krishnanand.mobile.swiftly.data.Product
import com.krishnanand.mobile.swiftly.R
import com.krishnanand.mobile.swiftly.databinding.ProductItemBinding
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
                recyclerView.visibility = View.VISIBLE
                productRecyclerAdapter.product = it
                productRecyclerAdapter.notifyDataSetChanged()
            }
            shimmer_container.stopShimmer()
            shimmer_container.visibility = View.GONE;
        })
        with(recyclerView) {
            val linearLayoutManager = LinearLayoutManager(this@ProductActivity)
            layoutManager = linearLayoutManager
            adapter = productRecyclerAdapter
            setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchProducts()
        if (emptyViewContainer.visibility == View.VISIBLE) {
            emptyRetryButton.setOnClickListener {
                viewModel.fetchProducts()
            }
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}

class ProductViewHolder(
    private val productItemBinding: ProductItemBinding,
    private val viewModel: ProductViewModel
): RecyclerView.ViewHolder(
    productItemBinding.root
) {

    fun bindViewHolder(managerSpecial: ManagerSpecials?) {
        productItemBinding.managerSpecial = managerSpecial
        productItemBinding.viewModel = viewModel
        productItemBinding.executePendingBindings()
    }
}

class ProductRecyclerAdapter(
    private val viewModel: ProductViewModel
): RecyclerView.Adapter<ProductViewHolder>() {

    var product: Product? = null;

    override fun getItemCount(): Int = product?.managerSpecials?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val productItemBinding: ProductItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.product_item, parent, false)
        return ProductViewHolder(productItemBinding, viewModel)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
       holder.bindViewHolder(product?.managerSpecials?.get(position))
    }
}