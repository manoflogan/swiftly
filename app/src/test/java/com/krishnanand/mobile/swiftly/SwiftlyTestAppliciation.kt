package com.krishnanand.mobile.swiftly

import android.app.Application
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub
import org.mockito.kotlin.whenever

class SwiftlyTestAppliciation: Application(), HasAndroidInjector {

    @Mock
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        MockitoAnnotations.openMocks(this)
        dispatchingAndroidInjector.stub {
            on {
                maybeInject(any())
            } doReturn true
        }
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        // See <a href="https://github.com/square/picasso/issues/1929" />
        Picasso.setSingletonInstance(Picasso.Builder(this).build());
    }
}