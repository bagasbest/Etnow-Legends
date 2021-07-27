package com.etnow.etnowlegends.drawer_content

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    var binding: ActivitySettingBinding? = null
    private lateinit var prefs: SharedPreferences
    private var mp: MediaPlayer? = null
    private var mpSfx: MediaPlayer? = null
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
                setOffMusic()
            }
            else{
                binding?.music?.isChecked = true
                music = true
                prefs.edit().putBoolean("music", true).apply()
                setOnMusic()
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
                setOffSfx()
            }
        }
    }

    private fun setOffSfx() {
        stopPlayer2()
    }

    private fun stopPlayer2() {
            mpSfx?.release()
            mpSfx = null
    }

    private fun setOnMusic() {
        if(mp == null) {
            mp = MediaPlayer.create(this, R.raw.sound)
            mp?.isLooping = true
            mp?.start()
        }
    }

    private fun setOffMusic() {
        stopPlayer()
    }

    private fun stopPlayer() {
        if(mp != null) {
            mp?.release()
            mp = null
        }
    }

    override fun onStop() {
        super.onStop()
        stopPlayer()
        stopPlayer2()
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

    companion object {
        const val EXTRA_SETTING = "setting"
    }
}