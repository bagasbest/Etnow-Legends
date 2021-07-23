package com.etnow.etnowlegends.materi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivitySubMateriBinding
import com.etnow.etnowlegends.utils.BottomSheetFragment

class SubMateriActivity : AppCompatActivity() {
    private var binding: ActivitySubMateriBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubMateriBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.segitiga?.let {
            Glide
                .with(this)
                .load(R.drawable.segitiga)
                .into(it)
        }

        binding?.back?.setOnClickListener {
            onBackPressed()
        }

        binding?.help?.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            binding?.help?.setOnClickListener {
                bottomSheetFragment.show(supportFragmentManager, "ButtonSheetDialog")
            }
        }

        binding?.view4?.setOnClickListener {
            startActivity(Intent(this, MateriBangunDatarActivity::class.java))
        }

        binding?.view8?.setOnClickListener {
            startActivity(Intent(this, MateriPersegiPanjangActivity::class.java))
        }

        binding?.view10?.setOnClickListener {
            startActivity(Intent(this, MateriSegitigaActivity::class.java))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}