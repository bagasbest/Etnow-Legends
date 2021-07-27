package com.etnow.etnowlegends

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private var binding: ActivitySplashScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.logo?.let { Glide.with(this).load(R.drawable.baselogo).into(it) }

        loadSplashScreen()

    }

    private fun loadSplashScreen() {
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                val mainIntent = Intent(this, OnboardingActivity::class.java)
                startActivity(mainIntent)
                finish()
            }, 3000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}