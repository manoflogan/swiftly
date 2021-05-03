package com.krishnanand.mobile.swiftly

import android.app.Application
import com.krishnanand.mobile.swiftly.dagger.DaggerSwiftlyProjectComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Starting point to the application.
 */
class SwiftlyApplication: Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        initApplication()
    }

    protected fun initApplication() {
        DaggerSwiftlyProjectComponent.builder().application(this).build().inject(this)
    }
}