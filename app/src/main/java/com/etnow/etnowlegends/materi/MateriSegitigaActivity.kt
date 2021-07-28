package com.etnow.etnowlegends.materi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.HomeActivity
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivityMateriSegitigaBinding
import com.etnow.etnowlegends.materi.persegi_panjang.PPActivity
import com.etnow.etnowlegends.materi.segitiga.SG1Activity
import com.etnow.etnowlegends.utils.BottomSheetFragmentPersegi

class MateriSegitigaActivity : AppCompatActivity() {

    private var binding: ActivityMateriSegitigaBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriSegitigaBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        Glide
            .with(this)
            .load(R.drawable.sc1)
            .into(binding?.roundedImageView2!!)

        Glide
            .with(this)
            .load(R.drawable.sc2)
            .into(binding?.pc2!!)

        Glide
            .with(this)
            .load(R.drawable.sc3)
            .into(binding?.sc3!!)

        Glide
            .with(this)
            .load(R.drawable.sc4)
            .into(binding?.sc4!!)

        Glide
            .with(this)
            .load(R.drawable.sc5)
            .into(binding?.sc5!!)

        Glide
            .with(this)
            .load(R.drawable.sc1)
            .into(binding?.pc3!!)

        Glide
            .with(this)
            .load(R.drawable.luas_sg)
            .into(binding?.luasPersegi!!)

        Glide
            .with(this)
            .load(R.drawable.kl_sg)
            .into(binding?.kelilingPersegi!!)

        Glide
            .with(this)
            .load(R.drawable.pj_ss_mrg_sg)
            .into(binding?.ssMrg!!)


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
            intent.putExtra(MateriHintActivity.EXTRA_TYPE, "sg")
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}