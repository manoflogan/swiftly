package com.krishnanand.mobile.swiftly.product

import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexboxLayoutManager
import com.krishnanand.mobile.swiftly.data.ManagerSpecials
import com.krishnanand.mobile.swiftly.databinding.ProductItemBinding


class ProductViewHolder(
    private val productItemBinding: ProductItemBinding,
    private val viewModel: ProductViewModel
): RecyclerView.ViewHolder(
    productItemBinding.root
) {
    init {
        val lp = itemView.layoutParams
        if (lp is FlexboxLayoutManager.LayoutParams) {
            val flexboxLp = lp
            flexboxLp.flexShrink = 0.0f
            flexboxLp.alignSelf =  AlignItems.FLEX_START
        }
    }

    fun bindViewHolder(managerSpecial: ManagerSpecials?) {
        managerSpecial ?: return
        productItemBinding.managerSpecial = managerSpecial
        productItemBinding.viewModel = viewModel
        productItemBinding.executePendingBindings()
    }
}