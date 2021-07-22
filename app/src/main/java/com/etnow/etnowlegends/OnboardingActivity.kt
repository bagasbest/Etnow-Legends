package com.etnow.etnowlegends

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.etnow.etnowlegends.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private var binding: ActivityOnboardingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(SlideModel(R.drawable.ob1))
        imageList.add(SlideModel(R.drawable.ob2))
        imageList.add(SlideModel(R.drawable.ob3))

        val imageSlider = findViewById<ImageSlider>(R.id.imageView2)
        imageSlider.setImageList(imageList)


        // KLIK MASUK DISINI
        binding?.view?.setOnClickListener {
            startActivity(Intent(this, IdentityActivity::class.java))
            finish()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}