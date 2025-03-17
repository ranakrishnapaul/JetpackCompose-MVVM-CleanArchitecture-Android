package com.example.composepoc

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
* It's the Application entry point where Hilt dependency has been injected
* at the application level so that it will always be available in all the
* app modules throughout the life span of the application
* */
@HiltAndroidApp
class MyApplication : Application()