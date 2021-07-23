package com.etnow.etnowlegends.materi.persegi_panjang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etnow.etnowlegends.databinding.ActivityPpactivityBinding

class PPActivity : AppCompatActivity() {


    private var binding : ActivityPpactivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPpactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}