package com.etnow.etnowlegends.drawer_content

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private var binding: ActivityAboutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.imageView4?.let {
            Glide
                .with(this)
                .load(R.drawable.logo_universitas)
                .into(it)
        }

        binding?.imageButton2?.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}