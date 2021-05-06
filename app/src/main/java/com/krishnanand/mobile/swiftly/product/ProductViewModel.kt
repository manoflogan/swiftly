package com.krishnanand.mobile.swiftly.product


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishnanand.mobile.swiftly.data.ManagerSpecials
import com.krishnanand.mobile.swiftly.data.Product
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val mainApi: ProductApi
): ViewModel() {

    private val _productsLiveData: MutableLiveData<Product> = MutableLiveData()

    val productsLiveData: LiveData<Product>
        get() = _productsLiveData


    fun fetchProducts() {
        viewModelScope.launch {
            mainApi.fetchProducts().enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    _productsLiveData.value = if (response.errorBody() != null) null else response.body()
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    _productsLiveData.value = null
                }
            })
        }
    }

    fun selectSpecial(managerSpecial: ManagerSpecials) {

    }
}