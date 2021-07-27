package com.etnow.etnowlegends.drawer_content

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.etnow.etnowlegends.databinding.ActivityKetentuanLayananBinding

class KetentuanLayananActivity : AppCompatActivity() {

    private var binding: ActivityKetentuanLayananBinding? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKetentuanLayananBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.webview?.loadUrl("https://textuploader.com/tark3")


        binding?.imageButton2?.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}