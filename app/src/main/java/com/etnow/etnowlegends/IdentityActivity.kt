package com.etnow.etnowlegends

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.etnow.etnowlegends.databinding.ActivityIdentityBinding
import com.etnow.etnowlegends.utils.BottomSheetFragmentOnboarding


class IdentityActivity : AppCompatActivity() {

    private var binding: ActivityIdentityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.bg?.let {
            Glide
                .with(this)
                .load(R.drawable.bg)
                .into(it)
        }

        // LOGIN
        clickLogin()


        // KEMBALI
        binding?.imageButton?.setOnClickListener {
            onBackPressed()
        }

        binding?.petunjuk?.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragmentOnboarding()
            bottomSheetFragment.show(supportFragmentManager, "ButtonSheetDialog")
        }

    }

    private fun clickLogin() {
        binding?.loginBtn?.setOnClickListener {
            val prefs = getSharedPreferences(
                "com.etnow.etnowlegends", Context.MODE_PRIVATE
            )
            val name = binding?.nameEt?.text.toString().trim()
            val className = binding?.classEt?.text.toString().trim()
            val school = binding?.schoolEt?.text.toString().trim()

            if (name.isEmpty()) {
                binding?.nameEt?.error = "Mohon isi nama kamu"
                return@setOnClickListener
            }
            if (className.isEmpty()) {
                binding?.classEt?.error = "Mohon isi nama kelas kamu"
                return@setOnClickListener
            }
            if (school.isEmpty()) {
                binding?.schoolEt?.error = "Mohon isi nama sekolah kamu"
                return@setOnClickListener
            }

            // SIMPAN DATA
            prefs.edit().putString("key", name).apply()
            prefs.edit().putString("class", className).apply()
            prefs.edit().putString("school", school).apply()


            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}