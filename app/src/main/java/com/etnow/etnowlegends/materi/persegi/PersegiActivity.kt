package com.etnow.etnowlegends.materi.persegi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etnow.etnowlegends.databinding.ActivityPersegiBinding
import com.etnow.etnowlegends.utils.BottomSheetFragment
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi

class PersegiActivity : AppCompatActivity() {

    private var binding: ActivityPersegiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersegiBinding.inflate(layoutInflater)
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
            startActivity(Intent(this, Persegi2Activity::class.java))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}