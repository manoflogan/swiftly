package com.krishnanand.mobile.swiftly.product

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.krishnanand.mobile.swiftly.viewmodels.ViewModelInjectionModule
import com.krishnanand.mobile.swiftly.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelInjectionModule::class,
        ProductApiModule::class
    ]
)
interface ProductActivityModule {

    @Binds
    fun bindMainActivity(activity: ProductActivity): AppCompatActivity

    @[Binds ViewModelKey(ProductViewModel::class) IntoMap]
    fun bindMainViewModel(viewModel: ProductViewModel): ViewModel
}