package com.krishnanand.mobile.swiftly.product

import com.krishnanand.mobile.swiftly.data.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductApi {

    @GET("master/backup")
    fun fetchProducts(): Call<Product>
}