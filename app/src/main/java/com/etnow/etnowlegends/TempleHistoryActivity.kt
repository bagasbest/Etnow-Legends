package com.etnow.etnowlegends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.databinding.ActivityTempleHistoryBinding
import com.etnow.etnowlegends.utils.BottomSheetFragment

class TempleHistoryActivity : AppCompatActivity() {

    var binding: ActivityTempleHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTempleHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.roundedImageView?.let {
            Glide
                .with(this)
                .load("https://2.bp.blogspot.com/-0UTAa7F1aZQ/WVtCVXhPCmI/AAAAAAAAF6U/Op2N6YTL51QKxRO5FeJKOtCmqyhBTf9dwCKgBGAs/s1600/IMG_20170623_125412.jpg")
                .into(it)
        }

        binding?.back?.setOnClickListener {
            onBackPressed()
        }

        binding?.backBtn?.setOnClickListener {
            onBackPressed()
        }

        binding?.help?.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            binding?.help?.setOnClickListener {
                bottomSheetFragment.show(supportFragmentManager, "ButtonSheetDialog")
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}