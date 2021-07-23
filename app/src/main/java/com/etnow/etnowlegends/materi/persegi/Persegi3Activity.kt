package com.etnow.etnowlegends.materi.persegi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etnow.etnowlegends.databinding.ActivityPersegi3Binding
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi

class Persegi3Activity : AppCompatActivity() {

    private var binding: ActivityPersegi3Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersegi3Binding.inflate(layoutInflater)
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

        binding?.finish?.setOnClickListener {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}