package com.krishnanand.mobile.swiftly.dagger

import android.app.Application
import com.krishnanand.mobile.swiftly.SwiftlyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ApiModule::class
    ]
)
@Singleton
interface SwiftlyProjectComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): SwiftlyProjectComponent
    }

    fun inject(projectApplication: SwiftlyApplication)

}