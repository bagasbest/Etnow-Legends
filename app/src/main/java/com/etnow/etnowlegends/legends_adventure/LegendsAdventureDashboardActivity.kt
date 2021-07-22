package com.etnow.etnowlegends.legends_adventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivityLegendsAdventureDashboardBinding

class LegendsAdventureDashboardActivity : AppCompatActivity() {

    private var binding: ActivityLegendsAdventureDashboardBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLegendsAdventureDashboardBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.bg?.let {
            Glide
                .with(this)
                .load(R.drawable.bg)
                .into(it)
        }

        binding?.view12?.let {
            Glide
                .with(this)
                .load(R.drawable.legends_adventure)
                .into(it)
        }

        binding?.kerjakanBtn?.setOnClickListener {
            startActivity(Intent(this, StupaActivity::class.java))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}