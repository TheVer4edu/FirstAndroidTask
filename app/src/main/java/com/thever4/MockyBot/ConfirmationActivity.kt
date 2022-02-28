package com.thever4.MockyBot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)
        val data = intent?.getStringExtra(MainActivity.KEY_TG_TOKEN)
        Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show()
    }
}