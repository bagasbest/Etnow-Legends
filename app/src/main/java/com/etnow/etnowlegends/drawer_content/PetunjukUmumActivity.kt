package com.etnow.etnowlegends.drawer_content

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etnow.etnowlegends.databinding.ActivityPetunjukUmumBinding

class PetunjukUmumActivity : AppCompatActivity() {

    private var binding: ActivityPetunjukUmumBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetunjukUmumBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.imageButton2?.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}