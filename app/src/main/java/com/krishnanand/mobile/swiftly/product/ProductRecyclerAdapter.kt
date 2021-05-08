package com.krishnanand.mobile.swiftly.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.krishnanand.mobile.swiftly.R
import com.krishnanand.mobile.swiftly.data.Product
import com.krishnanand.mobile.swiftly.databinding.ProductItemBinding

class ProductRecyclerAdapter(
    private val viewModel: ProductViewModel
): RecyclerView.Adapter<ProductViewHolder>() {

    lateinit var product: Product

    lateinit var dimensions: Pair<Float, Float>

    override fun getItemCount(): Int = product.managerSpecials.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val productItemBinding: ProductItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.product_item, parent, false)
        /**val layoutParams = productItemBinding.root.layoutParams  as ViewGroup.MarginLayoutParams
        with(layoutParams) {
            val managerWidth = product.managerSpecials[viewType].width
            width = (parent.width * managerWidth.toFloat() / product.canvasUnit).toInt() - leftMargin - rightMargin
        }
        productItemBinding.root.layoutParams = layoutParams*/
        return ProductViewHolder(productItemBinding, viewModel)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindViewHolder(product.managerSpecials[position])
    }

    override fun getItemViewType(position: Int): Int = position
}