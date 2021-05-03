package com.krishnanand.mobile.swiftly.product

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object ProductApiModule {

    @Provides
    fun provideProductApi(retrofit: Retrofit): ProductApi = retrofit.create(ProductApi::class.java)

}