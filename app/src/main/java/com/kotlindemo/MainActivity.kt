package com.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.android.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview.text = "Hello Kotlin!" // 相当于findviewbyid()之后再setText(),
    }

    // Error:(1, 1) Only the Kotlin standard library is allowed to use the 'kotlin' package
    // 包名不能出现 kotlin
}
