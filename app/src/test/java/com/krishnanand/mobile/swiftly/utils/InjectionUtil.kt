package com.krishnanand.mobile.swiftly.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import javax.inject.Provider

/**
 * Fake activity injector for purposes of mocking.
 */
inline fun <reified T: Activity> activityInjector(
    crossinline block: T.() -> Unit
): DispatchingAndroidInjector<Any> {
    val injector = AndroidInjector<Activity> { instance ->

        if (instance is T) {
            instance.block()
        }
    }

    val factory = AndroidInjector.Factory<Activity> {
        injector
    }
    val map = mapOf(Pair<Class<*>, Provider<AndroidInjector.Factory<*>>>(T::class.java, Provider { factory }))
    return DispatchingAndroidInjector_Factory.newInstance(map, emptyMap())
}