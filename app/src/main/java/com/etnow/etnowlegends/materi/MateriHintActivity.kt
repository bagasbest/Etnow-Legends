package com.etnow.etnowlegends.materi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivityMateriHintBinding
import com.etnow.etnowlegends.materi.persegi.PersegiActivity
import com.etnow.etnowlegends.materi.persegi_panjang.PPActivity
import com.etnow.etnowlegends.materi.segitiga.SG1Activity

class MateriHintActivity : AppCompatActivity() {

    private var binding: ActivityMateriHintBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriHintBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val getType = intent.getStringExtra(EXTRA_TYPE)

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

        when (getType) {
            "persegi" -> {
                val intent = Intent(this, PersegiActivity::class.java)
                intent.putExtra(PersegiActivity.EXTRA_OPT, "kerjakan")
                startActivity(intent)
            }
            "pp" -> {
                val intent = Intent(this, PPActivity::class.java)
                intent.putExtra(PPActivity.EXTRA_OPT, "kerjakan")
                startActivity(intent)
            }
            "sg" -> {
                val intent = Intent(this, SG1Activity::class.java)
                intent.putExtra(SG1Activity.EXTRA_OPT, "kerjakan")
                startActivity(intent)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        const val EXTRA_TYPE = "type"
    }
}