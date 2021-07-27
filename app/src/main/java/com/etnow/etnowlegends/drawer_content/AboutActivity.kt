package com.etnow.etnowlegends.drawer_content

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.R
import com.etnow.etnowlegends.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private var binding: ActivityAboutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.imageView4?.let {
            Glide
                .with(this)
                .load(R.drawable.logo_universitas)
                .into(it)
        }

        binding?.imageView2?.let {
            Glide
                .with(this)
                .load(R.drawable.baselogo)
                .into(it)
        }

        binding?.userDosen?.let {
            Glide
                .with(this)
                .load(R.drawable.user_maylita_hasyim)
                .into(it)
        }

        binding?.userPm?.let {
            Glide
                .with(this)
                .load(R.drawable.user_widya_krismon)
                .into(it)
        }

        binding?.userContentCreator?.let {
            Glide
                .with(this)
                .load(R.drawable.user_nanda_fitri)
                .into(it)
        }


        binding?.userCreatorSoal?.let {
            Glide
                .with(this)
                .load(R.drawable.user_widia_nurhasanah)
                .into(it)
        }

        binding?.userUiUx?.let {
            Glide
                .with(this)
                .load(R.drawable.user_nadella_neno)
                .into(it)
        }

        binding?.userAppDev?.let {
            Glide
                .with(this)
                .load(R.drawable.user_sugeng_romadhoni)
                .into(it)
        }

        binding?.lisensi?.setOnClickListener {
            val browse = Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1N9RRxdhGNi23y1w4ono7a5Pr0K5rJXrp"))
            startActivity(browse)
        }

        binding?.saran?.setOnClickListener {
            val saran = Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSckS9OhBpK3XmzSRlGm-Fel1jeCJOVQkS8tLsFcKPmmNLdz-A/viewform"))
            startActivity(saran)
        }

        binding?.imageButton2?.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}