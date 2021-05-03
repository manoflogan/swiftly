package com.krishnanand.mobile.swiftly.product

import androidx.recyclerview.widget.RecyclerView
import com.krishnanand.mobile.swiftly.data.ManagerSpecials
import com.krishnanand.mobile.swiftly.databinding.ProductItemBinding

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