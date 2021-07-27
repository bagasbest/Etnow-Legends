package com.etnow.etnowlegends.legends_adventure

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivityStupaBinding
import com.etnow.etnowlegends.legends_adventure.stupa1.Stupa1Activity
import com.etnow.etnowlegends.legends_adventure.stupa2.Stupa2Activity
import com.etnow.etnowlegends.legends_adventure.stupa3.Stupa3Activity
import com.etnow.etnowlegends.legends_adventure.stupa4.Stupa4Activity
import com.etnow.etnowlegends.legends_adventure.stupa5.Stupa5Activity
import com.etnow.etnowlegends.legends_adventure.stupa6.Stupa6Activity

class StupaActivity : AppCompatActivity() {

    private var binding: ActivityStupaBinding? = null
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStupaBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        prefs = getSharedPreferences(
            "com.etnow.etnowlegends", Context.MODE_PRIVATE
        )
        val stupa2 = prefs.getBoolean("stupa2", false)
        val stupa3 = prefs.getBoolean("stupa3", false)
        val stupa4 = prefs.getBoolean("stupa4", false)

        if(stupa2) {
            binding?.button2?.background?.setTint(resources.getColor(R.color.green))
            binding?.button2?.setTextColor(resources.getColor(R.color.white))
            binding?.lockStupa2?.visibility = View.GONE
        }
        if(stupa3) {
            binding?.button3?.background?.setTint(resources.getColor(R.color.green))
            binding?.button3?.setTextColor(resources.getColor(R.color.white))
            binding?.lockStupa3?.visibility = View.GONE

        }
        if(stupa4) {
            binding?.button4?.background?.setTint(resources.getColor(R.color.green))
            binding?.button4?.setTextColor(resources.getColor(R.color.white))
            binding?.lockStupa4?.visibility = View.GONE

            binding?.button7?.background?.setTint(resources.getColor(R.color.green))
            binding?.button7?.setTextColor(resources.getColor(R.color.white))
            binding?.lockStupa5?.visibility = View.GONE

            binding?.button8?.background?.setTint(resources.getColor(R.color.green))
            binding?.button8?.setTextColor(resources.getColor(R.color.white))
            binding?.lockStupa6?.visibility = View.GONE
        }

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

        binding?.back?.setOnClickListener {
            onBackPressed()
        }


        binding?.button?.setOnClickListener {
            val intent = Intent(this, Stupa1Activity::class.java)
            intent.putExtra(Stupa1Activity.EXTRA_OPT, "kerjakan")
            startActivity(intent)
        }

        binding?.button2?.setOnClickListener {
            if(stupa2) {
                val intent = Intent(this, Stupa2Activity::class.java)
                intent.putExtra(Stupa2Activity.EXTRA_OPT, "kerjakan")
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Kamu harus menyelesaikan Stupa 1, untuk membuka stupa 2", Toast.LENGTH_SHORT).show()
            }
        }

        binding?.button3?.setOnClickListener {
            if(stupa3) {
                val intent = Intent(this, Stupa3Activity::class.java)
                intent.putExtra(Stupa3Activity.EXTRA_OPT, "kerjakan")
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Kamu harus menyelesaikan Stupa 2, untuk membuka stupa 3", Toast.LENGTH_SHORT).show()
            }

        }

        binding?.button4?.setOnClickListener {
            if(stupa4) {
                val intent = Intent(this, Stupa4Activity::class.java)
                intent.putExtra(Stupa4Activity.EXTRA_OPT, "kerjakan")
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Kamu harus menyelesaikan Stupa 3, untuk membuka stupa 4, 5, dan 6", Toast.LENGTH_SHORT).show()
            }

        }

        binding?.button7?.setOnClickListener {
            if(stupa4) {
                val intent = Intent(this, Stupa5Activity::class.java)
                intent.putExtra(Stupa5Activity.EXTRA_OPT, "kerjakan")
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Kamu harus menyelesaikan Stupa 3, untuk membuka stupa 4, 5, dan 6", Toast.LENGTH_SHORT).show()
            }
        }

        binding?.button8?.setOnClickListener {
            if(stupa4) {
                val intent = Intent(this, Stupa6Activity::class.java)
                intent.putExtra(Stupa6Activity.EXTRA_OPT, "kerjakan")
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Kamu harus menyelesaikan Stupa 3, untuk membuka stupa 4, 5, dan 6", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}