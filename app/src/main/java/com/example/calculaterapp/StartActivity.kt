package com.example.calculaterapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.calculaterapp.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    lateinit var binding : ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed(Runnable {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        },5000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}