package com.etnow.etnowlegends.materi.segitiga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etnow.etnowlegends.databinding.ActivitySg1Binding

class SG1Activity : AppCompatActivity() {

    private var binding: ActivitySg1Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySg1Binding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}