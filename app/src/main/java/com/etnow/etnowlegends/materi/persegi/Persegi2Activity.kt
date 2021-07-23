package com.etnow.etnowlegends.materi.persegi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etnow.etnowlegends.databinding.ActivityPersegi2Binding
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi

class Persegi2Activity : AppCompatActivity() {

    private var binding: ActivityPersegi2Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersegi2Binding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.back?.setOnClickListener {
            onBackPressed()
        }

        binding?.help?.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragmentPersegi()
            binding?.help?.setOnClickListener {
                bottomSheetFragment.show(supportFragmentManager, "ButtonSheetDialog")
            }
        }

        binding?.view18?.setOnClickListener {
            startActivity(Intent(this, Persegi3Activity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}