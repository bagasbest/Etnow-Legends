package com.etnow.etnowlegends.drawer_content

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etnow.etnowlegends.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    var binding: ActivitySettingBinding? = null
    private lateinit var prefs: SharedPreferences
    private var music: Boolean? = false
    private var sfx: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        prefs = getSharedPreferences(
            "com.etnow.etnowlegends", Context.MODE_PRIVATE
        )

        loadPref()
        savePref()

        binding?.back?.setOnClickListener {
            onBackPressed()
        }

    }

    private fun savePref() {
        binding?.music?.setOnClickListener {
            if(music == true) {
                binding?.music?.isChecked = false
                music = false
                prefs.edit().putBoolean("music", false).apply()
            }
            else{
                binding?.music?.isChecked = true
                music = true
                prefs.edit().putBoolean("music", true).apply()
            }
        }


        binding?.sfx?.setOnClickListener {
            if(sfx == true) {
                binding?.sfx?.isChecked = false
                sfx = false
                prefs.edit().putBoolean("sfx", false).apply()
            }
            else{
                binding?.sfx?.isChecked = true
                sfx = true
                prefs.edit().putBoolean("sfx", true).apply()
            }
        }
    }

    private fun loadPref() {
        music = prefs.getBoolean("music", false)
        sfx = prefs.getBoolean("sfx", false)

        binding?.music?.isChecked = music as Boolean
        binding?.sfx?.isChecked = sfx as Boolean

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}