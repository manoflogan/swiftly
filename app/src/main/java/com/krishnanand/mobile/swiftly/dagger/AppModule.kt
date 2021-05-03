package com.krishnanand.mobile.swiftly.dagger

import com.krishnanand.mobile.swiftly.product.ProductActivity
import com.krishnanand.mobile.swiftly.product.ProductActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector(modules = [ProductActivityModule::class])
    abstract fun contributeMainActivity(): ProductActivity
}