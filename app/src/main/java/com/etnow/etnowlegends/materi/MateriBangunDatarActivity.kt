package com.etnow.etnowlegends.materi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.HomeActivity
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivityMateriBangunDatarBinding
import com.etnow.etnowlegends.materi.persegi.PersegiActivity
import com.etnow.etnowlegends.materi.persegi_panjang.PPActivity
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi

class MateriBangunDatarActivity : AppCompatActivity() {

    private var binding: ActivityMateriBangunDatarBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriBangunDatarBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        Glide
            .with(this)
            .load(R.drawable.pc1)
            .into(binding?.roundedImageView2!!)

        Glide
            .with(this)
            .load(R.drawable.pc2)
            .into(binding?.pc2!!)

        Glide
            .with(this)
            .load(R.drawable.pc3)
            .into(binding?.pc3!!)

        Glide
            .with(this)
            .load(R.drawable.luas_rumus)
            .into(binding?.luasPersegi!!)

        Glide
            .with(this)
            .load(R.drawable.keliling_persegi_rumus)
            .into(binding?.kelilingPersegi!!)

        binding?.back?.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        binding?.help?.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragmentPersegi()
            binding?.help?.setOnClickListener {
                bottomSheetFragment.show(supportFragmentManager, "ButtonSheetDialog")
            }
        }

        binding?.kerjakanBtn?.setOnClickListener {
            val intent = Intent(this, MateriHintActivity::class.java)
            intent.putExtra(MateriHintActivity.EXTRA_TYPE, "persegi")
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}