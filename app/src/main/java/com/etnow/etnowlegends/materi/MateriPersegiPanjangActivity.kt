package com.etnow.etnowlegends.materi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivityMateriPersegiPanjangBinding
import com.etnow.etnowlegends.materi.persegi.PersegiActivity
import com.etnow.etnowlegends.materi.persegi_panjang.PPActivity
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi

class MateriPersegiPanjangActivity : AppCompatActivity() {

    private var binding: ActivityMateriPersegiPanjangBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriPersegiPanjangBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        Glide
            .with(this)
            .load(R.drawable.ppc1)
            .into(binding?.roundedImageView2!!)

        Glide
            .with(this)
            .load(R.drawable.ppc2)
            .into(binding?.pc2!!)

        Glide
            .with(this)
            .load(R.drawable.ppc3)
            .into(binding?.pc3!!)

        Glide
            .with(this)
            .load(R.drawable.luas_pp)
            .into(binding?.luasPersegi!!)

        Glide
            .with(this)
            .load(R.drawable.kl_pp)
            .into(binding?.kelilingPersegi!!)

        binding?.back?.setOnClickListener {
            onBackPressed()
        }

        binding?.help?.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragmentPersegi()
            binding?.help?.setOnClickListener {
                bottomSheetFragment.show(supportFragmentManager, "ButtonSheetDialog")
            }
        }

        binding?.kerjakanBtn?.setOnClickListener {
            startActivity(Intent(this, PPActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}