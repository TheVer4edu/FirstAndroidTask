package com.thever4.MockyBot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private var token_input: TextInputEditText? = null
    private var continue_button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        token_input = findViewById(R.id.token_input)
        continue_button = findViewById(R.id.continue_button)

        continue_button?.setOnClickListener {
            var intent: Intent = Intent(this, ConfirmationActivity::class.java).apply {
                putExtra(KEY_TG_TOKEN, token_input?.text.toString())
            }
            startActivity(intent)
        }
    }

    companion object {
        const val KEY_TG_TOKEN = "TG_TOKEN"
    }
}