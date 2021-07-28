package com.etnow.etnowlegends.legends_adventure

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivityLegendsAdventureDashboardBinding
import com.etnow.etnowlegends.legends_adventure.stupa1.Stupa1Activity
import com.etnow.etnowlegends.legends_adventure.stupa2.Stupa2Activity
import com.etnow.etnowlegends.legends_adventure.stupa3.Stupa3Activity
import com.etnow.etnowlegends.legends_adventure.stupa4.Stupa4Activity
import com.etnow.etnowlegends.legends_adventure.stupa5.Stupa5Activity
import com.etnow.etnowlegends.legends_adventure.stupa6.Stupa6Activity

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
            val getStupa = intent.getStringExtra(EXTRA_STUPA)
            when (getStupa) {
                "stupa1" -> {
                    val intent = Intent(this, Stupa1Activity::class.java)
                    intent.putExtra(Stupa1Activity.EXTRA_OPT, "kerjakan")
                    startActivity(intent)
                }
                "stupa2" -> {
                    val intent = Intent(this, Stupa2Activity::class.java)
                    intent.putExtra(Stupa2Activity.EXTRA_OPT, "kerjakan")
                    startActivity(intent)
                }
                "stupa3" -> {
                    val intent = Intent(this, Stupa3Activity::class.java)
                    intent.putExtra(Stupa3Activity.EXTRA_OPT, "kerjakan")
                    startActivity(intent)
                }
                "stupa4" -> {
                    val intent = Intent(this, Stupa4Activity::class.java)
                    intent.putExtra(Stupa4Activity.EXTRA_OPT, "kerjakan")
                    startActivity(intent)
                }
                "stupa5" -> {
                    val intent = Intent(this, Stupa5Activity::class.java)
                    intent.putExtra(Stupa5Activity.EXTRA_OPT, "kerjakan")
                    startActivity(intent)
                }
                "stupa6" -> {
                    val intent = Intent(this, Stupa6Activity::class.java)
                    intent.putExtra(Stupa6Activity.EXTRA_OPT, "kerjakan")
                    startActivity(intent)
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        const val EXTRA_STUPA = "stupa"
    }
}