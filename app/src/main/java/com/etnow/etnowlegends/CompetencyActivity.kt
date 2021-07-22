package com.etnow.etnowlegends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etnow.etnowlegends.databinding.ActivityCompetencyBinding
import com.etnow.etnowlegends.utils.BottomSheetFragment

class CompetencyActivity : AppCompatActivity() {

    var binding: ActivityCompetencyBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompetencyBinding.inflate(layoutInflater)
        setContentView(binding?.root)


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
}