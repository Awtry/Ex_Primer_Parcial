package com.awtry.ex_primer_parcial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private val KEY = "STATE_KEY"
    private var Go_Login: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Go_Login = savedInstanceState?.getBoolean(KEY,true) ?: true
        if (Go_Login)
            supportFragmentManager.beginTransaction().add(R.id.Padre, Frag_login()).commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run{
            putBoolean(KEY, false)
        }
        super.onSaveInstanceState(outState)
    }



}
