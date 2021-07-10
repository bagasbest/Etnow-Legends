package com.etnow.etnowlegends

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etnow.etnowlegends.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private var binding: ActivityOnboardingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // KLIK MASUK DISINI
        binding?.view?.setOnClickListener {
            startActivity(Intent(this, IdentityActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}